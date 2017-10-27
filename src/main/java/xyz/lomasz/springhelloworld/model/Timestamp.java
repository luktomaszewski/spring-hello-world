package xyz.lomasz.springhelloworld.model;

import io.swagger.annotations.ApiModelProperty;

import java.time.ZonedDateTime;

public class Timestamp {

    @ApiModelProperty(notes = "full datetime with Time Zone")
    private final String dateTime;
    @ApiModelProperty(notes = "Time Zone ID")
    private final String timeZone;
    @ApiModelProperty(notes = "Date")
    private final String date;
    @ApiModelProperty(notes = "Time")
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
