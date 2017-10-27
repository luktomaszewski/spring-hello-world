package xyz.lomasz.springhelloworld.model;

import java.time.ZonedDateTime;

public class Timestamp {

    private final String dateTime;
    private final String timeZone;
    private final String date;
    private final String time;

    public Timestamp() {
        ZonedDateTime currentDateTime = ZonedDateTime.now();
        dateTime = currentDateTime.toString();
        timeZone = currentDateTime.getZone().toString();
        date = currentDateTime.toLocalDate().toString();
        time = currentDateTime.toLocalTime().toString();
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }
}
