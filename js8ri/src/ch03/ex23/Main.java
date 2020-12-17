package ch03.ex23;

import java.util.function.Function;

public class Main {

    public static void main(String[] args) {
        Pair<String> pair = new Pair<>("waffle", "食べ物");
        Pair<Integer> newPair = pair.map(s -> s.length());
        System.out.println(newPair);
    }

    static class Pair<T> {

        private T key;
        private T value;

        Pair (T key, T value) {
            this.key = key;
            this.value = value;
        }

        private <U> Pair<U> map(Function<? super T, ? extends U> f) {
            U newKey = f.apply(this.key);
            U newValue = f.apply(this.value);
            return new Pair<>(newKey, newValue);
        }

        @Override
        public String toString() {
            return "(" + key + " , " + value + ")";
        }
    }

}
