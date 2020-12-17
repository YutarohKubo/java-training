package ch03.ex18;

import java.util.function.Function;

public class Main {

    public static <T, U> Function<T, U> unchecked(UnaryCallable<T, U> future) {
        return (args1) -> {
            try {
                return future.call(args1);
            } catch (Exception e) {
                throw new RuntimeException();
            } catch (Throwable t) {
                throw t;
            }
        };
    }

}
