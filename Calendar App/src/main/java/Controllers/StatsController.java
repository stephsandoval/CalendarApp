package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

import Stats.StatsCreator;

public class StatsController {
 
    private HashMap <String, String> urlMap;
    private StatsCreator statsCreator;

    public StatsController (){
        statsCreator = new StatsCreator();
        urlMap = new HashMap<>();
        loadMap();
    }

    private void loadMap (){
        urlMap.put("water", "src/main/java/HMTL/water.html");
        urlMap.put("weather", "src/main/java/HMTL/weather.html");
    }

    public String getURL (String aspect) {
        return urlMap.get(aspect);
    }

    public void getStats (LocalDate dayChosen){
        ArrayList<LocalDate> week = new ArrayList<>();
        LocalDate start = dayChosen.minusDays(dayChosen.getDayOfWeek().getValue());
        for (int increment = 0; increment < 7; increment++){
            week.add(start.plusDays(increment));
        }
        statsCreator.createStats(week);
    }
}