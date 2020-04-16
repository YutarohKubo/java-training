package ch17.ex04;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.util.HashMap;
import java.util.Map;

public final class ResourceManager {

    final ReferenceQueue<Object> queue;
    final Map<Reference<?>, Resource> refs;
    final Thread reaper;
    boolean shutdown = false;

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
        }
    }

    public synchronized Resource getResource(Object key) {
        if (shutdown) {
            throw new IllegalStateException();
        }
        Resource res = new ResourceImpl(key);
        Reference<?> ref = new PhantomReference<>(key, queue);
        refs.put(ref, res);
        return res;
    }

    public boolean isReaperThreadAlive() {
        return reaper != null && reaper.isAlive();
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
                    if (shutdown && refs.size() == 0) {
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
