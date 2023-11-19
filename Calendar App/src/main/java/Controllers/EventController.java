package Controllers;

import java.time.LocalDate;
import java.time.Month;

import ApiClient.CalendarApiClient;
import Calendar.Calendar;
import Calendar.Day;
import Notifications.Status;
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

    public Status createEvents (LocalDate date, double temperature, double humidity, double precipitation, String weatherNotes, String waterSource, double waterAmount, double waterpH, String waterNotes, String crop, int cropAmount, String cropStatus, String pests, String cropNotes){
        if (crop.equals("") && cropAmount == 0 && waterNotes.equals("") && weatherNotes.equals("") && cropStatus.equals("") && pests.equals("") && cropNotes.equals("") && waterSource.equals("") && waterAmount == 0 && temperature == 0 && humidity == 0 && precipitation == 0 && waterpH == 1){
            return Status.WARNING;
        }
        WeatherRecord weatherRecord = createWeatherRecord(temperature, humidity, precipitation, weatherNotes);
        WaterRecord waterRecord = createWaterRecord(waterSource, waterAmount, waterpH, waterNotes);
        CropRecord cropRecord = createCropRecord(crop, cropAmount, cropStatus, pests, cropNotes);

        addDayToCalendar(date, waterRecord, weatherRecord, cropRecord);
        return Status.SUCCESS;
    }

    private void addDayToCalendar (LocalDate date, WaterRecord waterRecord, WeatherRecord weatherRecord, CropRecord cropRecord){
        int year = date.getYear();
        Month month = date.getMonth();
        Day day = new Day (date, weatherRecord, waterRecord, cropRecord);
        calendar.getYear(year).addDayToMonth(month, day);
        //writer.writeData(day);
    }

    private WeatherRecord createWeatherRecord (double temperature, double humidity, double precipitation, String weatherNotes){
        if (weatherNotes.equals("")){
            weatherNotes = "-";
        }
        return new WeatherRecord(temperature, humidity, precipitation, weatherNotes);
    }

    private WaterRecord createWaterRecord (String waterSource, double waterAmount, double waterpH, String waterNotes){
        if (waterSource.equals("")){
            waterSource = "-";
        }
        if (waterNotes.equals("")){
            waterNotes = "-";
        }
        return new WaterRecord(waterSource, waterAmount, waterpH, waterNotes);
    }

    private CropRecord createCropRecord (String crop, int cropAmount, String cropStatus, String pests, String cropNotes){
        if (crop.equals("")){
            crop = "-";
        }
        if (cropStatus.equals("")){
            cropStatus = "-";
        }
        if (pests.equals("")){
            pests = "-";
        }
        if (cropNotes.equals("")){
            cropNotes = "-";
        }
        return new CropRecord(crop, cropAmount, cropStatus, pests, cropNotes);
    }
}