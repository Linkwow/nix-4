package ua.nix.calendar.entity;

public class MyDate extends DateEntity implements Comparable<MyDate> {

    @Override
    public int compareTo(MyDate dateObject) {
        int result = 0;
        if(this.getAllDateImMilliseconds() > dateObject.getAllDateImMilliseconds()){
            result = -1;
        } else if(this.getAllDateImMilliseconds() < dateObject.getAllDateImMilliseconds()){
            result = 1;
        } else if(this.getAllDateImMilliseconds() == dateObject.getAllDateImMilliseconds()){
            result = 0;
        }
        return result;
    }



    public void setDay(Long day) {
        this.day = day;
    }

    public void setMonth(Long month) {
        this.month = month;
    }

    public void setYear(Long year) {
        this.year = year;
    }

    public void setHour(Long hour) {
        this.hour = hour;
    }

    public void setMinutes(Long minutes) {
        this.minutes = minutes;
    }

    public void setSeconds(Long seconds) {
        this.seconds = seconds;
    }

    public void setMilliseconds(Long milliseconds) {
        this.milliseconds = milliseconds;
    }

    public void setAllDateImMilliseconds(Long allDateImMilliseconds) {  //сюда подаем то количество миллисекунд которое прошло до текущей даты.
        this.allDateImMilliseconds = allDateImMilliseconds;
    }

    public void setDayCount(Long dayCount){                             //вот сюда подаём то количество дней которое прошло до этой даты. -1
        this.dayCount = dayCount;
    }

    public void setLeapYear(Boolean leapYear){                          //сюда подаём високосный год или нет....
        this.leapYear = leapYear;
    }

    public void setId(Integer id){
        this.id = id;
    }

    public Long getDay() {
        return day;
    }

    public Long getMonth() {
        return month;
    }

    public Long getYear() {
        return year;
    }

    public Long getHour() {
        return hour;
    }

    public Long getMinutes() {
        return minutes;
    }

    public Long getSeconds() {
        return seconds;
    }

    public Long getMilliseconds() {
        return milliseconds;
    }

    public Long getAllDateImMilliseconds() {
        return allDateImMilliseconds;
    }

    public Long getDayCount() {
        return dayCount;
    }

    public Boolean getLeapYear() {
        return leapYear;
    }

    public Integer getId(){
        return id;
    }

    public String toString(){
        return day + "/" + month + "/" +  year + " " + hour + ":" + minutes + ":" + seconds + ":" + milliseconds;
    }
}
