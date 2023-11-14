package Controllers;

import java.time.LocalDate;

import Calendar.Calendar;
import Records.CropRecord;
import Records.WaterRecord;
import Records.WeatherRecord;

public class EventController {

    private Calendar calendar;
 
    public EventController (){
        calendar = Calendar.getInstance();
    }

    public void createEvents (double temperature, double humidity, double precipitation, double sunlight, String weatherNotes, String waterSource, double waterAmount, double waterpH, String waterNotes, String crop, int cropAmount, String cropStatus, String pests, String cropNotes){
        WaterRecord waterRecord;
        WeatherRecord weatherRecord;
        CropRecord cropRecord;
        if (temperature != 0 || humidity != 0 || precipitation != 0 || sunlight != 0 || !(weatherNotes.equals(""))){
            weatherRecord = createWeatherRecord (temperature, humidity, precipitation, sunlight, weatherNotes);
        }
        if (!(waterSource.equals("")) || !(waterNotes.equals(""))){
            waterRecord = createWaterRecord(waterSource, waterAmount, waterpH, waterNotes);
        }
        if (!(crop.equals("")) || !(pests.equals("")) || !(cropNotes.equals(""))){
            cropRecord = createCropRecord(crop, cropAmount, cropStatus, pests, cropNotes);
        }
    }

    private WeatherRecord createWeatherRecord (double temperature, double humidity, double precipitation, double sunlight, String notes){
        WeatherRecord record = new WeatherRecord(temperature, humidity, precipitation, sunlight, notes);
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
}