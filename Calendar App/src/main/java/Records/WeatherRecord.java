package Records;

public class WeatherRecord {
 
    private double temperature, humidity, precipitation;
    private String weatherNote;

    public WeatherRecord (){
        this.temperature = 0.0;
        this.humidity = 0.0;
        this.precipitation = 0.0;
        this.weatherNote = "";
    }

    public WeatherRecord (double temperature, double humidity, double precipitation, String weatherNote){
        this();
        setTemperature(temperature);
        setHumidity(humidity);
        setPrecipitation(precipitation);
        setWeatherNote(weatherNote);
    }

    public double getTemperature (){
        return temperature;
    }

    public double getHumidity (){
        return humidity;
    }

    public double getPrecipitation (){
        return precipitation;
    }

    public String getWeatherNote (){
        return weatherNote;
    }

    public void setTemperature (double temperature){
        if (temperature > 0){
            this.temperature = temperature;
        }
    }

    public void setHumidity (double humidity){
        if (humidity > 0){
            this.humidity = humidity;
        }
    }

    public void setPrecipitation (double precipitation){
        if (precipitation > 0){
            this.precipitation = precipitation;
        }
    }

    public void setWeatherNote (String weatherNote){
        if (weatherNote != null && !weatherNote.isEmpty()){
            this.weatherNote = weatherNote;
        }
    }

    public boolean hasInformation (){
        return (temperature > 0 || humidity > 0 || precipitation > 0 || !weatherNote.isEmpty());
    }

    public String toString (){
        return "weather record >> temperature > " + temperature + " | humidity > " + humidity + " | precipitation > " + precipitation + " | note > " + weatherNote;
    }
}