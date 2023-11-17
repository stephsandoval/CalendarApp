package CalendarPrev;

import java.util.ArrayList;

import Calendar.Day;

public class DayPreviewCreator {
 
    private static DayPreviewCreator instance;
    private ArrayList<DayPreview> dayPreview;

    private DayPreviewCreator (){
        dayPreview = new ArrayList<>();
    }

    public static synchronized DayPreviewCreator getInstance (){
        if (instance == null){
            instance = new DayPreviewCreator();
        }
        return instance;
    }

    public ArrayList<DayPreview> getDayPreview (Day day){
        dayPreview.clear();
        if (day.getWeatherRecord() != null){
            dayPreview.add(new DayPreview("temperature", Double.toString(day.getWeatherRecord().getTemperature())));
            String humidity = Double.toString(day.getWeatherRecord().getHumidity());
            dayPreview.add(new DayPreview("humidity", humidity != "0.0" ? humidity : "--" ));
            dayPreview.add(new DayPreview("precipitation", Double.toString(day.getWeatherRecord().getPrecipitation())));
            dayPreview.add(new DayPreview("weather note", day.getWeatherRecord().getWeatherNote()));
        }
        if (day.getWaterRecord() != null){
            dayPreview.add(new DayPreview("water source", day.getWaterRecord().getWaterSource()));
            dayPreview.add(new DayPreview("water amount", Double.toString(day.getWaterRecord().getWaterAmount())));
            dayPreview.add(new DayPreview("water pH", Double.toString(day.getWaterRecord().getWaterpH())));
            dayPreview.add(new DayPreview("water note", day.getWaterRecord().getWaterNote()));
        }
        if (day.getCropRecord() != null){
            dayPreview.add(new DayPreview("crop type", day.getCropRecord().getCrop()));
            dayPreview.add(new DayPreview("crop amount", Integer.toString(day.getCropRecord().getAmount())));
            dayPreview.add(new DayPreview("crop status", day.getCropRecord().getCropStatus()));
            dayPreview.add(new DayPreview("pests", day.getCropRecord().getPests()));
            dayPreview.add(new DayPreview("crop note", day.getCropRecord().getCropNote()));
        }
        return dayPreview;
    }
}