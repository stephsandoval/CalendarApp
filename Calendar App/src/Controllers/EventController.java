package Controllers;

import Records.CropRecord;
import Records.WaterRecord;
import Records.WeatherRecord;

public class EventController {
 
    public EventController (){}

    public void createWeatherRecord (double temperature, double humidity, double precipitation, double sunlight, String notes){
        WeatherRecord record = new WeatherRecord(temperature, humidity, precipitation, sunlight, notes);
        System.out.println(record.toString());
    }

    public void createWaterRecord (String waterSource, double waterAmount, double waterpH, String notes){
        WaterRecord record = new WaterRecord(waterSource, waterAmount, waterpH, notes);
        System.out.println(record.toString());
    }

    public void createCropRecord (String crop, String cropStatus, String pests, String notes){
        CropRecord record = new CropRecord(crop, cropStatus, pests, notes);
        System.out.println(record.toString());
    }
}