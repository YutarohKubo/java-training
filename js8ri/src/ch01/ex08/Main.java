package ch01.ex08;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String[] names = {"a", "b", "c"};
        List<Runnable> runners1 = new ArrayList<>();
        for (String name : names) {
            runners1.add(() -> {
               System.out.println(name);
            });
        }
        for (Runnable runnable : runners1) {
            runnable.run();
        }

        List<Runnable> runners2 = new ArrayList<>();
        for (int i = 0; i < names.length; i++) {
            int finalI = i;
            runners2.add(() -> {
                System.out.println(names[finalI]);
            });
        }
        for (Runnable runnable : runners2) {
            runnable.run();
        }
    }

}
