package ch02.ex03;

import java.util.Arrays;
import java.util.List;

public class Main {

    private static final String sentence = "The oldest evidence of milk consumption was found at sites more than１０，０００years old. Surprisingly, it was not cow’s milk but sheep’s milk.People raised sheep for their meat, wool, and milk long before they began toraise cows. They turned this milk into the first types of cheese. Sheep’smilk has over５０％more fat than cow’s milk along with about twice theamount of protein. As fat content plays an important part in making cheese,sheep’s milk is often used to produce a number of cheeses. Goats, anotheranimal raised before cows, also provide milk. Goat’s milk has a similar levelof fat to cow’s milk, but less sugar. More recent examples of animal milkinclude deer’s milk and horse’s milk. Deer’s milk has higher levels of proteinand fat than cow’s milk. Horse’s milk has less protein than cow’s milk, butsix times as much vitamin C";

    public static void main(String[] args) {
        List<String> words = Arrays.asList(sentence.split("[\\P{L}]"));
        long startNanoTimeStream = System.nanoTime();
        Object[] o1 = words.stream().filter(s -> {
            return s.length() >= 100;
        }).toArray();
        System.out.println("running time (Stream) = " + (System.nanoTime() - startNanoTimeStream));
        long startNanoTimeParallelStream = System.nanoTime();
        Object[] o2 = words.parallelStream().filter(s -> {
            return s.length() >= 100;
        }).toArray();
        System.out.println("running time (ParallelStream) = " + (System.nanoTime() - startNanoTimeParallelStream));
    }

}
