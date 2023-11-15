package Controllers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

import Calendar.Calendar;
import Calendar.Year;
import CalendarPrev.DayPrev;

public class NewCalendarController {
 
    private int yearNumber;
    private Month month;
    private Calendar calendar;

    public NewCalendarController (){
        calendar = Calendar.getInstance();
    }

    public void setMonthandYear (Month month, int yearNumber){
        this.month = month;
        this.yearNumber = yearNumber;
    }

    public int getOffset (){
        YearMonth yearMonth = YearMonth.of(yearNumber, month);
        LocalDate date = yearMonth.atDay(1);
        return date.getDayOfWeek().getValue() % 7;
    }

    public int getMaxDays (){
        YearMonth yearMonth = YearMonth.of(yearNumber, month);
        int monthLength = yearMonth.lengthOfMonth();
        return monthLength;
    }

    public ArrayList <DayPrev> getDayPreview (LocalDate day){
        // verify the day exists, if it does, return array with events
        ArrayList <DayPrev> preview = new ArrayList<>();
        preview.add(new DayPrev("temperature", "25°C"));
        preview.add(new DayPrev("notes", "sunny weather"));
        preview.add(new DayPrev("water source", "groundwater"));
        return preview;
    }
}