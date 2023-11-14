package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import Calendar.Calendar;
import CalendarPrev.DayPrev;

public class NewCalendarController {
 
    private int offset, maxDays;
    private Calendar calendar;

    public NewCalendarController (){
        calendar = Calendar.getInstance();
    }

    public int getOffset (){
        return this.offset;
    }

    public int getMaxDays (){
        return this.maxDays;
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