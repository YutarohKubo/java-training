package ch08.ex08;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayDeque;
import java.util.Collections;
import java.util.Queue;

public class Main {

    static final String CD = System.getProperty("user.dir");
    static Queue<Path> queue;

    public static void main(String[] args) {
        initQueue();
        enqueuePath();

        queue = Collections.checkedQueue(queue, Path.class);

        getMoreWork(queue); // 上でcheckedQueueすることで、このメソッド内で例外が発生し、誤りの箇所を見つけることができる
    }

    private static void initQueue() {
        queue = new ArrayDeque<>();
    }

    private static void enqueuePath() {
        Path path1 = Paths.get(String.format("%s\\src\\ch06\\ex06\\document1.txt", CD));
        Path path2 = Paths.get(String.format("%s\\src\\ch06\\ex06\\document2.txt", CD));
        Path path3 = Paths.get(String.format("%s\\src\\ch06\\ex06\\document3.txt", CD));
        queue.offer(path1);
        queue.offer(path2);
        queue.offer(path3);
    }

    static void getMoreWork(Queue q) {
        q.add("aaa");
    }

}
