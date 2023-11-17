package Calendar;

import java.time.LocalDate;
import java.time.Month;
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

    public Day getDay (LocalDate date){
        int year = date.getYear();
        Month month = date.getMonth();
        int day = date.getDayOfMonth();
        return getYear(year).getMonth(month).get(day-1);
    }

    public boolean containsInfo (LocalDate date){
        Day day = getDay(date);
        if (day.getCropRecord().hasInformation() || day.getWaterRecord().hasInformation() || day.getWeatherRecord().hasInformation()){
            return true;
        }
        return false;
    }

    public void addYear (int yearNumber, Year year){
        this.calendar.put(yearNumber, year);
    }

    public Year getYear (int yearNumber){
        if (this.calendar.containsKey(yearNumber) == false){
            Year year = new Year(yearNumber);
            this.calendar.put(yearNumber, year);
        }
        return this.calendar.get(yearNumber);
    }

    public void setYear (int yearNumber, Year year){
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