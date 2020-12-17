package ch05.ex12;

import java.time.ZonedDateTime;

public class TaskData {

    private String mTextTask;
    private ZonedDateTime mZonedDateTime;
    private boolean mNotified;

    public TaskData(String textTask, ZonedDateTime zonedDateTime) {
        this.mTextTask = textTask;
        this.mZonedDateTime = zonedDateTime;
    }

    public String getTextTask() {
        return mTextTask;
    }

    public ZonedDateTime getZonedDateTime() {
        return mZonedDateTime;
    }

    public boolean isNotified() {
        return mNotified;
    }

    public void checkNotified() {
        mNotified = true;
    }
}
