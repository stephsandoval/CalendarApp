package Controllers;

import java.util.HashMap;

public class StatsController {
 
    private HashMap <String, String> urlMap;

    public StatsController (){
        urlMap = new HashMap<>();
        loadMap();
    }

    private void loadMap (){
        urlMap.put("crop", "src/main/java/HMTL/chart.html");
    }

    public String getURL (String aspect) {
        return urlMap.get(aspect);
    }
}