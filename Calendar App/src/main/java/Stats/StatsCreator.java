package Stats;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import Calendar.Calendar;
import Records.CropRecord;
import Records.WaterRecord;
import Records.WeatherRecord;

public class StatsCreator {

    private Calendar calendar;
    private ArrayList<LocalDate> week;

    private final String header = getContents(new File("src/main/java/Stats/header.txt"));
    private final String footer = getContents(new File("src/main/java/Stats/footer.txt"));

    public StatsCreator (){
        calendar = Calendar.getInstance();
        this.week = new ArrayList<>();
    }

    public void setWeek (ArrayList<LocalDate> week){
        this.week = week;
    }

    private String getContents (File file){
        Scanner scanner;
        String contents = "";
        try {
            scanner = new Scanner(file);
            while (scanner.hasNextLine()){
                contents += scanner.nextLine() + "\r\n";
            }
            scanner.close();
        } catch (Exception exception) {}
        return contents;
    }

    public void createWeatherStats (){
        File htmlFile = new File("src/main/java/HMTL/weather.html");
        try {
            htmlFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
            writer.write(header + setWeatherStats() + footer);
            writer.close();
        } catch (Exception exception){}
    }

    public void createWaterStats (){
        File htmlFile = new File("src/main/java/HMTL/water.html");
        try {
            htmlFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
            writer.write(header + setWaterStats() + footer);
            writer.close();
        } catch (Exception exception){}
    }

    public void createCropStats (LocalDate date){
        File htmlFile = new File("src/main/java/HMTL/crop.html");
        try {
            htmlFile.createNewFile();
            BufferedWriter writer = new BufferedWriter(new FileWriter(htmlFile));
            writer.write(header + setCropStats(date) + footer);
            writer.close();
        } catch (Exception exception){}
    }

    private String setWeatherStats (){
        String content = "        var data = google.visualization.arrayToDataTable([\r\n" + //
                "          ['Day', 'Temperature', 'Precipitation', 'Humidity'],\r\n";
        for (LocalDate weekDay : week){
            if (calendar.getDay(weekDay) != null){
                WeatherRecord weather = calendar.getDay(weekDay).getWeatherRecord();
                if (weather != null){
                    content += "          ['" + weekDay.getDayOfWeek().toString() + " " + weekDay.getDayOfMonth() + "', " + weather.getTemperature() + ", " + weather.getPrecipitation() + ", " + weather.getHumidity() + "],\r\n";
                    continue;
                }
            }
            content += "          ['" + weekDay.getDayOfWeek().toString() + " " +  weekDay.getDayOfMonth() + "', " + 0 + ", " + 0 + ", " + 0 + "],\r\n";
        }
        content += "        ]);\r\n" + //
                "\r\n" + //
                "        var materialOptions = {\r\n" + //
                "          width: 930,\r\n" + //
                "          chart: {\r\n" + //
                "            title: 'Weather Statistics'\r\n" + //
                "          },\r\n" + //
                "          series: {\r\n" + //
                "            0: { axis: 'Temperature' },\r\n" + //
                "            1: { axis: 'Precipitation' },\r\n" + //
                "            2: { axis: 'Humidity'}\r\n" + //
                "          },\r\n" + //
                "          colors: ['#90ee90', '#20b2aa', '#66cdaa']\r\n" + //
                "        };\r\n" + //
                "        \r\n";
        return content;
    }

    private String setWaterStats (){
        String content = "        var data = google.visualization.arrayToDataTable([\r\n" + //
                "          ['Day', 'Amount', 'pH'],\r\n";
        for (LocalDate weekDay : week){
            if (calendar.getDay(weekDay) != null){
                WaterRecord waterRecord = calendar.getDay(weekDay).getWaterRecord();
                if (waterRecord.hasInformation()){
                    content += "          ['" + weekDay.getDayOfWeek().toString() + " " + weekDay.getDayOfMonth() + "', " + waterRecord.getWaterAmount() + ", " + waterRecord.getWaterpH() + "],\r\n";
                    continue;
                }
            }
            content += "          ['" + weekDay.getDayOfWeek().toString() + " " + weekDay.getDayOfMonth() + "', " + 0 + ", " + 0 + "],\r\n";
        }
        content += "        ]);\r\n" + //
                "\r\n" + //
                "        var materialOptions = {\r\n" + //
                "          width: 930,\r\n" + //
                "          chart: {\r\n" + //
                "            title: 'Water Statistics'\r\n" + //
                "          },\r\n" + //
                "          series: {\r\n" + //
                "            0: { axis: 'Amount' },\r\n" + //
                "            1: { axis: 'pH' },\r\n" + //
                "          },\r\n" + //
                "          colors: ['#20b2aa', '#66cdaa']\r\n" + //
                "        };\r\n" + //
                "        \r\n";
        return content;
    }

    private String setCropStats (LocalDate date){
        String content = "        var data = google.visualization.arrayToDataTable([\r\n" + //
                "          ['Day', 'Amount'],\r\n";
        String crop = calendar.getDay(date).getCropRecord().getCrop();
        for (LocalDate weekDay : week){
            if (calendar.getDay(weekDay) != null){
                CropRecord cropRecord = calendar.getDay(weekDay).getCropRecord();
                if (cropRecord.hasInformation() && crop != null && calendar.getDay(weekDay).getCropRecord().getCrop().equals(crop)){
                    content += "          ['" + weekDay.getDayOfWeek().toString() + " " + weekDay.getDayOfMonth() + "', " + cropRecord.getAmount() + "],\r\n";
                    continue;
                }
            }
            content += "          ['" + weekDay.getDayOfWeek().toString() + " " + weekDay.getDayOfMonth() + "', " + 0 + "],\r\n";
        }
        content += "        ]);\r\n" + //
                "\r\n" + //
                "        var materialOptions = {\r\n" + //
                "          width: 930,\r\n" + //
                "          chart: {\r\n" + //
                "            title: 'Crop Statistics'\r\n" + //
                "          },\r\n" + //
                "          series: {\r\n" + //
                "            0: { axis: 'Amount' },\r\n" + //
                "          },\r\n" + //
                "          colors: ['#20b2aa']\r\n" + //
                "        };\r\n" + //
                "        \r\n";
        return content;
    }
}