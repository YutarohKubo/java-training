package ch05.ex07;

import java.time.LocalDateTime;

public class TimeInterval {

    private LocalDateTime mStart;
    private LocalDateTime mEnd;

    public TimeInterval (LocalDateTime startDateTime, LocalDateTime endDateTime) {
        if (!startDateTime.isBefore(endDateTime)) {
            throw new IllegalArgumentException("startDateTime is after or equal endDateTime");
        }
        this.mStart = startDateTime;
        this.mEnd = endDateTime;
    }

    /**
     * 重なりチェック
     */
    public boolean isOverlapped(TimeInterval another) {
        return mStart.isBefore(another.mEnd) && mEnd.isAfter(another.mStart);
    }

}
