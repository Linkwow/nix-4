package ua.nix.calendar.entity;

public class Date extends DateEntity {                      //сущность хранит дни в милисекундах
    private static final long SECONDS_TO_MS_VALUE = 1000;
    private static final long MINUTE_TO_MS_VALUE = 60000;
    private static final long HOUR_TO_MS_VALUE = 3600000;
    private static final long DAY_TO_MS_VALUE = 86400000;

    private long day;
    private long month;
    private long year;
    private long hour;
    private long minutes;
    private long seconds;
    private long milliseconds;

    private long ms;

    public void setDay(long day) {
        this.day = day;
    }

    public void setMonth(long month) {
        this.month = month;
    }

    public void setYear(long year) {
        this.year = year;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public void setMinutes(long minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(long seconds) {
        this.seconds = seconds;
    }

    public void setMilliseconds(long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setId(int id){
        this.id = id;
    }

    public void setMs(long ms) {
        this.ms = ms;
    }

    public String toString(){
        return day + "/" + month + "/" +  year + " " + hour + ":" + minutes +  ":" + seconds;
    }
}
