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

    public Day (LocalDate date){
        this.date = date;
        this.waterRecord = null;
        this.weatherRecord = null;
        this.cropRecord = null;
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
}