import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;

import ApiClient.CalendarApiClient;
import Calendar.Day;
import Records.CropRecord;
import Records.WaterRecord;
import Records.WeatherRecord;

public class ContentfulConnector {
    public static void main(String[] args) {

        CalendarApiClient api = CalendarApiClient.getInstance();
        /*CropRecord crop = new CropRecord("potato", 85, "germination", null, "going well");
        Day day = new Day(LocalDate.now(), new WeatherRecord(), new WaterRecord(), crop);
        ArrayList<Day> days = new ArrayList<>();
        days.add(day);
        api.writeData(days);*/

        ArrayList<Day> days = api.readData();
        for (Day day : days){
            System.out.println(day + "\n\n");
        }

        System.exit(0);
    }
}
