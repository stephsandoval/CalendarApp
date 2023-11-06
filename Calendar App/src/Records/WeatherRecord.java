package Records;

public class WeatherRecord {
 
    private double temperature, humidity, precipitation, sunlight;
    private String weatherNote;

    public WeatherRecord (double temperature, double humidity, double precipitation, double sunlight, String weatherNote){
        this.temperature = temperature;
        this.humidity = humidity;
        this.precipitation = precipitation;
        this.sunlight = sunlight;
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

    public double getSunlight (){
        return sunlight;
    }

    public void setSunlight (double sunlight){
        this.sunlight = sunlight;
    }

    public String getWeatherNote (){
        return weatherNote;
    }

    public void setWeatherNote (String weatherNote){
        this.weatherNote = weatherNote;
    }

    public String toString (){
        return "weather record >> temperature > " + temperature + " | humidity > " + humidity + " | precipitation > " + precipitation + " | sunlight > " + sunlight + " | note > " + weatherNote;
    }
}