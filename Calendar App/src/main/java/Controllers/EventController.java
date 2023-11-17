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
        WaterRecord waterRecord = null;
        WeatherRecord weatherRecord = null;
        CropRecord cropRecord = null;
        if (temperature != 0 || humidity != 0 || precipitation != 0 || !(weatherNotes.equals(""))){
            weatherRecord = createWeatherRecord (temperature, humidity, precipitation, weatherNotes);
        }
        if (!(waterSource.equals("")) || !(waterNotes.equals(""))){
            waterRecord = createWaterRecord(waterSource, waterAmount, waterpH, waterNotes);
        }
        if (!(crop.equals("")) || !(pests.equals("")) || !(cropNotes.equals(""))){
            cropRecord = createCropRecord(crop, cropAmount, cropStatus, pests, cropNotes);
        }
        addDayToCalendar(date, waterRecord, weatherRecord, cropRecord);
    }

    private WeatherRecord createWeatherRecord (double temperature, double humidity, double precipitation, String notes){
        WeatherRecord record = new WeatherRecord(temperature, humidity, precipitation, notes);
        return record;
    }

    private WaterRecord createWaterRecord (String waterSource, double waterAmount, double waterpH, String notes){
        WaterRecord record = new WaterRecord(waterSource, waterAmount, waterpH, notes);
        return record;
    }

    private CropRecord createCropRecord (String crop, int amount, String cropStatus, String pests, String notes){
        CropRecord record = new CropRecord(crop, amount, cropStatus, pests, notes);
        return record;
    }

    private void addDayToCalendar (LocalDate date, WaterRecord waterRecord, WeatherRecord weatherRecord, CropRecord cropRecord){
        int year = date.getYear();
        Month month = date.getMonth();
        Day day = new Day (date, weatherRecord, waterRecord, cropRecord);
        calendar.getYear(year).addDayToMonth(month, day);
        writer.writeData(day);
    }
}