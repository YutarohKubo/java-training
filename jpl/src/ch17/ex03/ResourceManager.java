package ch17.ex03;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public final class ResourceManager {

    final ReferenceQueue<Object> queue;
    final Map<Reference<Integer>, Resource> refs;
    final Thread reaper;
    boolean shutdown = false;

    public static class Box {
        private Resource resource;
        private int key;

        Box (Resource resource, int key) {
            this.resource = resource;
            this.key = key;
        }

        public Resource getResource() {
            return resource;
        }

        public int getKey() {
            return key;
        }
    }

    public ResourceManager() {
        queue = new ReferenceQueue<>();
        refs = new HashMap<>();
        reaper = new ReaperThread();
        reaper.start();

        //リソースの初期化
    }

    public synchronized void shutdown() {
        if (!shutdown) {
            shutdown = true;
            reaper.interrupt();
        }
    }

    public synchronized Box getResource() {
        if (shutdown) {
            throw new IllegalStateException();
        }
        int key = makeKey();
        Resource res = new ResourceImpl(key);
        Reference<Integer> ref = new PhantomReference<>(key, queue);
        refs.put(ref, res);
        return new Box(res, key);
    }

    private int makeKey() {
        Random random = new Random();
        int key = random.nextInt();
        if (isContainKey(key)) {
            key = makeKey();
        }
        return key;
    }

    private boolean isContainKey (Integer key) {
        for (Reference<Integer> ref : refs.keySet()) {
            if (ref != null && key.equals(ref.get())) {
                return true;
            }
        }
        return false;
    }

    public int getMapSize() {
        return refs.size();
    }

    class ReaperThread extends Thread {
        @Override
        public void run() {
            // 割り込まれるまで実行
            while (true) {
                try {
                    Reference<?> ref = queue.remove();
                    Resource res = null;
                    synchronized (ResourceManager.this) {
                        res = refs.get(ref);
                        refs.remove(ref);
                    }
                    res.release();
                    ref.clear();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break; //全て終了
                }
            }
        }
    }

}
