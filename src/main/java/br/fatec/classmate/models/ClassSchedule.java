package br.fatec.classmate.models;

import java.time.DayOfWeek;

public class ClassSchedule
{
    private DayOfWeek weekday;
    public DayOfWeek getWeekday()
    {
        return weekday;
    }

    public void setWeekday(DayOfWeek weekday)
    {
        this.weekday = weekday;
    }

    public int getHour()
    {
        return hour;
    }

    public void setHour(int hour)
    {
        this.hour = hour;
    }

    public int getMinute()
    {
        return minute;
    }

    public void setMinute(int minute)
    {
        this.minute = minute;
    }

    private int hour;
    private int minute;

    public ClassSchedule(DayOfWeek pWeekDay, int pHour, int pMinute)
    {
        weekday = pWeekDay;
        hour = pHour;
        minute = pMinute;
    }

    public boolean equals(ClassSchedule pClass)
    {
        return this.getWeekday() == pClass.getWeekday() &&
                this.getHour() == pClass.getHour()
                && this.getMinute() == pClass.getMinute();
    }

    public boolean equals(DayOfWeek pDay, int pHour, int pMinute)
    {
        return this.getWeekday() == pDay && this.getHour() == pHour && this.getMinute() == pMinute;
    }

    @Override
    public String toString()
    {
        return String.format("&s [Weekday: %s, Hour: %s, Minute: %s]", this.getClass().getName(),
                weekday.toString(), hour, minute);

    }
}
