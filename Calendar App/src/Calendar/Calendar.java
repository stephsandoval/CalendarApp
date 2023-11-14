package Calendar;

import java.util.HashMap;

public class Calendar {
    
    private HashMap <Integer, Year> calendar;
    private static Calendar instance;

    private Calendar (){
        this.calendar = new HashMap<>();
    }

    public static synchronized Calendar getInstance (){
        if (instance == null){
            instance = new Calendar();
        }
        return instance;
    }

    public void addYear (int yearNumber, Year year){
        this.calendar.put(yearNumber, year);
    }

    public Year getYear (int yearNumber){
        return this.calendar.get(yearNumber);
    }

    public void setYer (int yearNumber, Year year){
        this.calendar.replace(yearNumber, year);
    }

    public HashMap <Integer, Year> getCalendar (){
        return this.calendar;
    }

    public void setCalendar (HashMap <Integer, Year> calendar){
        this.calendar = calendar;
    }

    public boolean hasYear (int yearNumber){
        return this.calendar.containsKey(yearNumber);
    }
}