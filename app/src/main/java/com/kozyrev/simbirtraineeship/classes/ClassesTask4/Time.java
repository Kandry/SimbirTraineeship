package com.kozyrev.simbirtraineeship.classes.ClassesTask4;

public class Time {

    public static enum timeType {
        SEC,
        MIN,
        HOUR;
    }

    private int sec, min, hour;

    public Time(int sec, int min, int hour) throws Exception{
        if ((checkTime(sec, 60, timeType.SEC)) && (checkTime(min, 60, timeType.MIN)) && (checkTime(hour, 24, timeType.HOUR))){
            this.sec = sec;
            this.min = min;
            this.hour = hour;
        } else {
            throw new Exception("Некорректное значение времени. Минимальное возможное время: 00:00:00; Максимальное возможное время: 23:59:59");
        }
    }

    private boolean checkTime(int value, int maxValue, timeType type) throws Exception{
        switch (type){
            case SEC:
            case MIN:
            case HOUR:
                return (value > -1) && (value < maxValue);
            default:
                throw new Exception("Не задан тип проверяемго значения времени");
        }
    }

    public Time getTime(){
        return this;
    }

    public void setSec(int sec) throws Exception {
        if (checkTime(sec, 60, timeType.SEC)) this.sec = sec;
        else throw new Exception("Некорректное значение секунд. Минимальное возможное значение: 0; Максимальное возможное значение: 59");
    }

    public void setMin(int min) throws Exception {
        if (checkTime(min, 60, timeType.MIN)) this.min = min;
        else throw new Exception("Некорректное значение минут. Минимальное возможное значение: 0; Максимальное возможное значение: 59");
    }

    public void setHour(int hour) throws Exception {
        if (checkTime(hour, 24, timeType.HOUR)) this.hour = hour;
        else throw new Exception("Некорректное значение часов. Минимальное возможное значение: 0; Максимальное возможное значение: 23");
    }

    public void changeSec(int sec){
        //int newHour = sec / 3600;
        int newMin = sec / 60;
        int newSec = sec % 60;

        newSec += this.sec;
        int minsInSecs = newSec / 60;

        if (minsInSecs == 0) this.sec = newSec;
        else {
            if (minsInSecs > 0) this.sec = newSec - 60 * minsInSecs;
            else this.sec = newSec + 60 * minsInSecs;

            newMin += minsInSecs;
        }
/*
        if (minsInSecs > 0){
            this.sec = newSec - 60 * minsInSecs;
            newMin += minsInSecs;
        }
        else this.sec = newSec;*/

        if (newMin != 0) this.changeMin(newMin);
    }

    public void changeMin(int min){
        int newHour = min / 60;
        int newMin = min % 60;

        newMin += this.min;
        int hoursInMins = newMin / 60;

        if (hoursInMins == 0) this.min = newMin;
        else {
            if (hoursInMins > 0) this.min = newMin - 60 * hoursInMins;
            else this.min = newMin + 60 * hoursInMins;

            newHour += hoursInMins;
        }

        if (newHour != 0) this.changeHour(newHour);
    }

    public void changeHour(int hour){
        int newHour = hour % 24;
        newHour += this.hour;
        int daysInHours = newHour / 24;

        if (daysInHours == 0) this.hour = newHour;
        else {
            if (daysInHours > 0) this.hour = newHour - 24 * daysInHours;
            else this.hour = newHour + 24 * daysInHours;
        }
        /*
        if (daysInHours > 0) this.hour = newHour - 24 * daysInHours;
        else this.hour = newHour;*/
    }

    public void changeTime(int sec, int min, int hour){
        this.changeSec(sec);
        this.changeMin(min);
        this.changeHour(hour);
    }

    @Override
    public String toString(){
        return hour + ":" + min + ":" + sec;
    }
}
