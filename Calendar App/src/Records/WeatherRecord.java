package Records;

public class WeatherRecord {
 
    private double temperature, humidity, precipitation;
    private String weatherNote;

    public WeatherRecord (double temperature, double humidity, double precipitation, String weatherNote){
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.weatherNote = weatherNote;
    }

    public double getTemperature (){
        return temperature;
    }

    public void setTemperature (double temperature){
        this.temperature = temperature;
    }

    public double getHumidity (){
        return humidity;
    }

    public void setHumidity (double humidity){
        this.humidity = humidity;
    }

    public double getPrecipitation (){
        return precipitation;
    }

    public void setPrecipitation (double precipitation){
        this.precipitation = precipitation;
    }

    public String getWeatherNote (){
        return weatherNote;
    }

    public void setWeatherNote (String weatherNote){
        this.weatherNote = weatherNote;
    }

    public String toString (){
        return "weather record >> temperature > " + temperature + " | humidity > " + humidity + " | precipitation > " + precipitation + " | note > " + weatherNote;
    }
}