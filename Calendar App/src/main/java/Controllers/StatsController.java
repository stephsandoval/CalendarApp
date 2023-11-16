package Controllers;

import java.util.HashMap;

public class StatsController {
 
    private HashMap <String, String> urlMap;

    public StatsController (){
        urlMap = new HashMap<>();
        loadMap();
    }

    private void loadMap (){
        urlMap.put("crop", "src/main/java/Screens/chart.html");
    }

    public String getURL (String aspect) {
        System.out.println(urlMap.get(aspect));
        return urlMap.get(aspect);
    }
}