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
        if (day.getWeatherRecord().hasInformation()){
            writeWeatherPreview(day);
        }
        if (day.getWaterRecord().hasInformation()){
            writeWaterPreview(day);
        }
        if (day.getCropRecord().hasInformation()){
            writeCropPreview(day);
        }
        return dayPreview;
    }

    private void writeWeatherPreview (Day day){
        String temperature = Double.toString(day.getWeatherRecord().getTemperature());
        String humidity = Double.toString(day.getWeatherRecord().getHumidity());
        String precipitation = Double.toString(day.getWeatherRecord().getPrecipitation());
        String note = day.getWeatherRecord().getWeatherNote();

        if (!temperature.equals("0.0")){
            dayPreview.add(new DayPreview("temperature", temperature + " °C"));
        }
        if (!humidity.equals("0.0")){
            dayPreview.add(new DayPreview("humidity", humidity + " %"));
        }
        if (!precipitation.equals("0.0")){
            dayPreview.add(new DayPreview("precipitation", precipitation + " %"));
        }
        if (!note.equals("")){
            dayPreview.add(new DayPreview("weather note", note));
        }
    }

    private void writeWaterPreview (Day day){
        String source = day.getWaterRecord().getWaterSource();
        String amount = Double.toString(day.getWaterRecord().getWaterAmount());
        String pH = Double.toString(day.getWaterRecord().getWaterpH());
        String note = day.getWaterRecord().getWaterNote();

        if (!source.equals("")){
            dayPreview.add(new DayPreview("water source", source));
        }
        if (!amount.equals("0.0")){
            dayPreview.add(new DayPreview("water amount", amount + " L"));
        }
        if (!pH.equals("0.0")){
            dayPreview.add(new DayPreview("water pH", pH));
        }
        if (!note.equals("")){
            dayPreview.add(new DayPreview("water note", note));
        }
    }

    private void writeCropPreview (Day day){
        String type = day.getCropRecord().getCrop();
        String amount = Integer.toString(day.getCropRecord().getAmount());
        String status = day.getCropRecord().getCropStatus();
        String pests = day.getCropRecord().getPests();
        String note = day.getCropRecord().getCropNote();

        if (!type.equals("")){
            dayPreview.add(new DayPreview("crop type", type));
        }
        if (!amount.equals("0")){
            dayPreview.add(new DayPreview("crop amount", amount + " plants"));
        }
        if (!status.equals("")){
            dayPreview.add(new DayPreview("crop status", status));
        }
        if (!pests.equals("")){
            dayPreview.add(new DayPreview("pests", pests));
        }
        if (!note.equals("")){
            dayPreview.add(new DayPreview("crop note", note));
        }
    }
}