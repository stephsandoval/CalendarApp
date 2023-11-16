package Controllers;

import java.time.LocalDate;
import java.time.Month;
import java.time.YearMonth;
import java.util.ArrayList;

import Calendar.Calendar;
import Calendar.Day;
import CalendarPrev.DayPreview;
import CalendarPrev.DayPreviewCreator;

public class NewCalendarController {
 
    private int yearNumber;
    private Month month;
    private Calendar calendar;
    private DayPreviewCreator previewCreator;

    public NewCalendarController (){
        calendar = Calendar.getInstance();
        previewCreator = DayPreviewCreator.getInstance();
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

    public ArrayList <DayPreview> getDayPreview (LocalDate date){
        ArrayList <DayPreview> preview = new ArrayList<>();
        Day day = calendar.getDay(date);
        if (day.getWaterRecord() != null || day.getWeatherRecord() != null || day.getCropRecord() != null){
            preview = previewCreator.getDayPreview(day);
        }
        return preview;
    }

    public boolean containsInfo (LocalDate date){
        return calendar.containsInfo(date);
    }
}