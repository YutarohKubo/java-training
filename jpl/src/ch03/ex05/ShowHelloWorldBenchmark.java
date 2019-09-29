package ch03.ex05;

public class ShowHelloWorldBenchmark extends Benchmark {
    @Override
    void benchmark() {
        System.out.println("Hello World");
    }

    public static void main(String[] args) {
        int count = Integer.parseInt(args[0]);
        long time = new ShowHelloWorldBenchmark().repeat(count);
        System.out.println(count + " methods in " + time + " nanoseconds");
    }
}
