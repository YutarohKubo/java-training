package ch03.ex16;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

/**
 * ユースケース:
 * firstに数値から天気を得るweatherFromNumber(int num)の結果返す処理を与えたが、
 * numに仕様に反した予期せぬ数字が入ってきて例外をスローしたかどうかの判断を、
 * secondの第二引数のthrowableのインスタンスをチェックすることで実施し、
 * 例外がスローされた後の処理を実施する.
 */
class MainTest {

    enum Weather {
        SUNNY,
        CLOUDY,
        RAINY,
        SNOWY,
    }

    /**
     * 仕様:
     * num = 0でSUNNY, num = 1でCLOUDY, num = 2でRAINY, num = 3でSNOWYを得る
     */
    private Weather weatherFromNumber(int num) {
        switch (num) {
            case 0:
                return Weather.SUNNY;
            case 1:
                return Weather.CLOUDY;
            case 2:
                return Weather.RAINY;
            case 3:
                return Weather.SNOWY;
            default:
                throw new IllegalArgumentException();
        }
    }

    private String todoToday(Weather weather) {
        switch (weather) {
            case SUNNY:
                return "nice!";
            case CLOUDY:
                return "bad!";
            case RAINY:
                return "too bad!";
            case SNOWY:
                return "cold!";
            default:
                throw new IllegalStateException();
        }
    }

    @Test
    void firstProcessingIsSuccess() {
        Main.doInOrderAsync(() -> weatherFromNumber(0), (result, throwable) -> {
            if (throwable instanceof IllegalArgumentException) {
                System.out.println("Supplied weather is invalid.");
                fail();
                return;
            }
            assertEquals(todoToday(result), "nice!");
        });
    }

    @Test
    void firstProcessingThrowException() {
        Main.doInOrderAsync(() -> weatherFromNumber(4), (result, throwable) -> {
            if (throwable instanceof IllegalArgumentException) {
                System.out.println("Supplied weather is invalid.");
                return;
            }
            fail();
            assertEquals(todoToday(result), "nice!");
        });
    }

}
