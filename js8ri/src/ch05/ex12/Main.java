package ch05.ex12;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.Set;

public class Main {

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm VV");

    private static Set<TaskData> taskDataSet;

    public static void main(String[] args) {
        createTaskDataSet();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    taskDataSet.stream().forEach(action -> {
                        ZonedDateTime time = action.getZonedDateTime();
                        LocalDateTime nowTime = LocalDateTime.now();
                        Duration duration = Duration.between(nowTime, time);
                        if (duration.toMinutes() < 60 && duration.toMinutes() > 0) {
                            action.checkNotified();
                            System.out.println("Notify within 1 hour [task : " + action.getTextTask() + "]");
                        }
                    });
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        t.start();
    }

    /**
     * 約束のロード
     */
    private static void createTaskDataSet() {
        taskDataSet = new HashSet<>();
        taskDataSet.add(new TaskData("play baseball", ZonedDateTime.parse("2020-12-18 10:00 America/Los_Angeles", formatter)));
        taskDataSet.add(new TaskData("team meeting", ZonedDateTime.parse("2020-12-18 08:00 Europe/Berlin", formatter)));
        taskDataSet.add(new TaskData("eat breakfast", ZonedDateTime.parse("2020-12-18 06:00 America/New_York", formatter)));
        taskDataSet.add(new TaskData("get home", ZonedDateTime.parse("2020-12-18 15:00 Australia/Brisbane", formatter)));
        taskDataSet.add(new TaskData("running", ZonedDateTime.parse("2020-12-18 17:00 Asia/Seoul", formatter)));
    }

}
