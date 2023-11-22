package Calendar;

import java.util.HashMap;
import java.time.Month;

public class Year {
 
    private HashMap <Month, HashMap<Integer, Day>> year;
    private int yearNumber;

    public Year (int yearNumber){
        this.yearNumber = yearNumber;
        year = new HashMap<>();
        populateYear();
    }

    private void populateYear (){
        for (Month month : Month.values()){
            HashMap<Integer, Day> monthDays = new HashMap<>();
            year.put(month, monthDays);
        }
    }

    public HashMap<Integer, Day> getMonth (Month month){
        return this.year.get(month);
    }

    public void addMonth (Month month, HashMap<Integer, Day> monthDays){
        this.year.put(month, monthDays);
    }

    public void addDayToMonth (Month month, Day day){
        int dayNumber = day.getDate().getDayOfMonth();
        HashMap<Integer, Day> monthDays = year.get(month);
        if (monthDays.get(dayNumber) == null){
            year.get(month).put(dayNumber, day);
        } else {
            updateDay(year.get(month).get(dayNumber), day);
        }
    }

    public HashMap <Month, HashMap<Integer, Day>> getYear (){
        return this.year;
    }

    public int getYearNumber (){
        return this.yearNumber;
    }

    public void setYear (HashMap <Month, HashMap<Integer, Day>> year){
        this.year = year;
    }

    public void setYearNumber (int yearNumber){
        this.yearNumber = yearNumber;
    }

    private void updateDay (Day day, Day newDay){
        day.updateCropRecord(newDay.getCropRecord());
        day.updateWaterRecord(newDay.getWaterRecord());
        day.updateWeatherRecord(newDay.getWeatherRecord());
    }
}