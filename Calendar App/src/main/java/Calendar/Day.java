package Calendar;

import java.time.LocalDate;

import Records.CropRecord;
import Records.WaterRecord;
import Records.WeatherRecord;

public class Day {

    private LocalDate date;
    private WeatherRecord weatherRecord;
    private WaterRecord waterRecord;
    private CropRecord cropRecord;

    public Day (){
        this.date = null;
        this.waterRecord = new WaterRecord();
        this.weatherRecord = new WeatherRecord();
        this.cropRecord = new CropRecord();
    }

    public Day (LocalDate date){
        this.date = date;
        this.waterRecord = new WaterRecord();
        this.weatherRecord = new WeatherRecord();
        this.cropRecord = new CropRecord();
    }

    public Day (LocalDate date, WeatherRecord weatherRecord, WaterRecord waterRecord, CropRecord cropRecord){
        this.date = date;
        this.weatherRecord = weatherRecord;
        this.waterRecord = waterRecord;
        this.cropRecord = cropRecord;
    }

    public LocalDate getDate (){
        return this.date;
    }

    public WeatherRecord getWeatherRecord (){
        return this.weatherRecord;
    }

    public WaterRecord getWaterRecord (){
        return this.waterRecord;
    }

    public CropRecord getCropRecord (){
        return this.cropRecord;
    }

    public void setDate (LocalDate date){
        this.date = date;
    }

    public void setWeatherRecord (WeatherRecord weatherRecord){
        this.weatherRecord = weatherRecord;
    }

    public void setWaterRecord (WaterRecord waterRecord){
        this.waterRecord = waterRecord;
    }

    public void setCropRecord (CropRecord cropRecord){
        this.cropRecord = cropRecord;
    }

    public void updateCropRecord (CropRecord cropRecord){
        this.cropRecord.setCrop(cropRecord.getCrop());
        this.cropRecord.setAmount(cropRecord.getAmount());
        this.cropRecord.setCropStatus(cropRecord.getCropStatus());
        this.cropRecord.setPests(cropRecord.getPests());
        this.cropRecord.setCropNote(cropRecord.getCropNote());
    }

    public void updateWaterRecord (WaterRecord waterRecord){
        this.waterRecord.setWaterSource(waterRecord.getWaterSource());
        this.waterRecord.setWaterAmount(waterRecord.getWaterAmount());
        this.waterRecord.setWaterpH(waterRecord.getWaterpH());
        this.waterRecord.setWaterNote(waterRecord.getWaterNote());
    }

    public void updateWeatherRecord (WeatherRecord weatherRecord){
        this.weatherRecord.setTemperature(weatherRecord.getTemperature());
        this.weatherRecord.setHumidity(weatherRecord.getHumidity());
        this.weatherRecord.setPrecipitation(weatherRecord.getPrecipitation());
        this.weatherRecord.setWeatherNote(weatherRecord.getWeatherNote());
    }

    public String toString (){
        return "day >> " + date.toString() + " | " + weatherRecord + " | " + waterRecord + " | " + cropRecord + " ";
    }
}