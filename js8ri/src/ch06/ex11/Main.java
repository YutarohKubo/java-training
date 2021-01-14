package ch06.ex11;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.PasswordAuthentication;
import java.time.LocalTime;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Main {

    public static void main(String[] args) throws Exception {
        CompletableFuture<Void> completableFuture = CompletableFuture.supplyAsync(() -> new BufferedReader(new InputStreamReader(System.in))).thenComposeAsync(x -> repeat(() -> {
            log("input password > ");
            PasswordAuthentication auth = null;
            try {
                String password = x.readLine();
                auth = new PasswordAuthentication("", password.toCharArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return auth;
        }, y -> "secret".equals(String.valueOf(y.getPassword())))).thenAcceptAsync(z -> System.out.println("finish"));
        completableFuture.get();

        /*BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        CompletableFuture<PasswordAuthentication> completableFuture = repeat(() -> {
            PasswordAuthentication auth = null;
            try {
                log("input password > ");
                String password = reader.readLine();
                auth = new PasswordAuthentication("", password.toCharArray());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return auth;
        }, x -> "secret".equals(String.valueOf(x.getPassword())));*/
    }

    public static <T> CompletableFuture<T> repeat(Supplier<T> action, Predicate<T> until) {
        if (until.test(action.get())) {
            return CompletableFuture.supplyAsync(action);
        }
        return repeat(action, until);
    }

    public static void log(String msg) {
        System.out.println(LocalTime.now() + " ("
                + Thread.currentThread().getName() + ") " +  msg);
    }

}
