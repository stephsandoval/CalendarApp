package Controllers;

import java.time.LocalDate;
import java.time.Month;

import ApiClient.CalendarApiClient;
import Calendar.Calendar;
import Calendar.Day;
import Records.CropRecord;
import Records.WaterRecord;
import Records.WeatherRecord;

public class EventController {

    private Calendar calendar;
    private CalendarApiClient writer;
 
    public EventController (){
        calendar = Calendar.getInstance();
        writer = CalendarApiClient.getInstance();
    }

    public void createEvents (LocalDate date, double temperature, double humidity, double precipitation, String weatherNotes, String waterSource, double waterAmount, double waterpH, String waterNotes, String crop, int cropAmount, String cropStatus, String pests, String cropNotes){
        System.out.println(precipitation);
        WeatherRecord weatherRecord = new WeatherRecord(temperature, humidity, precipitation, weatherNotes);
        WaterRecord waterRecord = new WaterRecord(waterSource, waterAmount, waterpH, waterNotes);
        CropRecord cropRecord = new CropRecord(crop, cropAmount, cropStatus, pests, cropNotes);

        addDayToCalendar(date, waterRecord, weatherRecord, cropRecord);
    }

    private void addDayToCalendar (LocalDate date, WaterRecord waterRecord, WeatherRecord weatherRecord, CropRecord cropRecord){
        int year = date.getYear();
        Month month = date.getMonth();
        Day day = new Day (date, weatherRecord, waterRecord, cropRecord);
        calendar.getYear(year).addDayToMonth(month, day);
        //writer.writeData(day);
    }
}