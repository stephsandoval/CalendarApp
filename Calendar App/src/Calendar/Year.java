package Calendar;

import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;

public class Year {
 
    private HashMap <Month, ArrayList<Day>> year;
    private int yearNumber;

    public Year (int yearNumber){
        this.yearNumber = yearNumber;
        year = new HashMap<>();
        populateYear();
    }

    private void populateYear (){
        for (Month month : Month.values()){
            YearMonth yearMonth = YearMonth.of(yearNumber, month);
            int monthLength = yearMonth.lengthOfMonth();
            ArrayList<Day> monthDays = new ArrayList<>();
            for (int day = 1; day <= monthLength; day++){
                monthDays.add(new Day(yearMonth.atDay(day)));
            }
            year.put(month, monthDays);
        }
    }

    public ArrayList<Day> getMonth (Month month){
        return this.year.get(month);
    }

    public void addMonth (Month month, ArrayList<Day> monthDays){
        this.year.put(month, monthDays);
    }

    public void addDayToMonth (Month month, Day day){
        // if the day doesn't have any info, the day is added
        // if the day has info, it is replaced for the new information
        int dayNumber = day.getDate().getDayOfMonth();
        ArrayList<Day> monthDays = year.get(month);
        monthDays.set(dayNumber - 1, day);
    }

    public HashMap <Month, ArrayList<Day>> getYear (){
        return this.year;
    }

    public int getYearNumber (){
        return this.yearNumber;
    }

    public void setYear (HashMap <Month, ArrayList<Day>> year){
        this.year = year;
    }

    public void setYearNumber (int yearNumber){
        this.yearNumber = yearNumber;
    }
}