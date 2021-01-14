package ch08.ex14;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Main {

    public static void main(String[] args) {
        System.out.println(doubleInteger(null));
    }

    static Integer doubleInteger(Integer integer) {
        // 実際にintegerがnullだった時のみ、メッセージのStringやLocalDateTimeインスタンスを作成するため、第2引数をラムダで渡す
        return 2 * Objects.requireNonNull(integer, () -> {
            LocalDateTime dateTime = LocalDateTime.now();
            return "multiplied integer is null. (" + DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss:SSS").format(dateTime) + ")";
        });
    }

}
