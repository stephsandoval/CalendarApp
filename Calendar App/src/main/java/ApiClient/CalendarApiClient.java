package ApiClient;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.contentful.java.cda.CDAArray;
import com.contentful.java.cda.CDAClient;
import com.contentful.java.cda.CDAEntry;
import com.contentful.java.cda.CDAResource;
import com.contentful.java.cma.CMAClient;
import com.contentful.java.cma.model.CMAEntry;

import Calendar.Day;

public class CalendarApiClient {
    
    private HashMap<String, Action> readActionMap;
    private HashMap<Class<?>, Action> writeActionMap;
    private ArrayList<String> entryFields;
    private ArrayList<Day> days, newDays, updateDays;
    private String writeToken, readToken, spaceId, environmentId, contentType;
    private static CalendarApiClient instance;

    private CalendarApiClient (){
        this.writeToken = "CFPAT-PD1g-fAbvgONwsLi0_CdbJilnPRFE4QyEJ_PuduEdQ4";
        this.readToken = "fjgizg8zVhAwhlEs-2h3tEjwUWQLqAGxtN4aAXFpvBA";
        this.spaceId = "7u4zyhwnzl64";
        this.environmentId = "master";
        this.contentType = "calendarEntry";

        this.readActionMap = new HashMap<>();
        this.writeActionMap = new HashMap<>();
        this.entryFields = new ArrayList<>();
        this.newDays = new ArrayList<>();
        this.updateDays = new ArrayList<>();

        populateFields();
        populateReadActionMap();
        populateWriteActionMap();
        readData();
    }

    public static synchronized CalendarApiClient getInstance (){
        if (instance == null){
            instance = new CalendarApiClient();
        }
        return instance;
    }

    public ArrayList<Day> getDays (){
        return this.days;
    }

    private void readData (){
        days = new ArrayList<>();
        ArrayList<CDAEntry> entries = fetchEntries();
        for (CDAEntry entry : entries){
            days.add(createDay(entry));

        }
    }

    public void addDay (Day day){
        this.newDays.add(day);
    }

    public void updateDay (Day day){
        for (Day newDay : newDays){
            if (newDay.getDate().equals(day.getDate())){
                return;
            }
        }
        this.updateDays.add(day);
    }

    public void writeData (){
        updateData();
        for (Day day : newDays){
            CMAClient client = new CMAClient.Builder().setAccessToken(writeToken).setSpaceId(spaceId).setEnvironmentId(environmentId).build();
            CMAEntry entry = createEntry(day);
            CMAEntry result = client.entries().create(contentType, entry);
            client.entries().publish(result);
        }
    }

    private void updateData (){
        for (Day day : updateDays){
            CMAClient client = new CMAClient.Builder().setAccessToken(writeToken).setSpaceId(spaceId).setEnvironmentId(environmentId).build();
            CMAEntry oldEntry = client.entries().fetchOne(day.getDate().toString() + "-calendar");
            client.entries().unPublish(oldEntry);
            client.entries().delete(oldEntry);
            newDays.add(day);
        }
    }

    private void performReadAction(String key, Object value, Object object) {
        Action action = readActionMap.get(key);
        if (action != null){
            action.performAction(key, value, object);
        }
    }

    private void performWriteAction (String key, Object value, Object object){
        Action action = writeActionMap.get(value.getClass());
        if (action != null){
            action.performAction(key, value, object);
        }
    }

    private ArrayList<CDAEntry> fetchEntries (){
        ArrayList<CDAEntry> calendarEntries = new ArrayList<>();
        CDAClient client = CDAClient.builder().setToken(readToken).setSpace(spaceId).setEnvironment(environmentId).build();
        CDAArray entries = client.fetch(CDAEntry.class).withContentType(contentType).all();
        for (CDAResource resource : entries.items()){
            if (resource instanceof CDAEntry){
                calendarEntries.add((CDAEntry) resource);
            }
        }
        return calendarEntries;
    }

    private Day createDay (CDAEntry entry){
        Day day = new Day();
        Map<String, Object> items = entry.rawFields();

        items.forEach((key, value) -> {
            Collection<Object> values = ((HashMap<Object, Object>)value).values();
            Iterator<Object> iterator = values.iterator();
            Object object = iterator.next();
            performReadAction(key, object, day);
        });
        return day;
    }

    private CMAEntry createEntry (Day day){
        int index = 0;
        CMAEntry entry = new CMAEntry();
        String date = day.getDate().toString();
        entry.setId(date + "-calendar");
        entry.setField("entryTitle", "en-US", date + "-calendar");
        performWriteAction(entryFields.get(index++), day.getDate().toString(), entry);
        performWriteAction(entryFields.get(index++), day.getWeatherRecord().getTemperature(), entry);
        performWriteAction(entryFields.get(index++), day.getWeatherRecord().getHumidity(), entry);
        performWriteAction(entryFields.get(index++), day.getWeatherRecord().getPrecipitation(), entry);
        performWriteAction(entryFields.get(index++), day.getWeatherRecord().getWeatherNote(), entry);
        performWriteAction(entryFields.get(index++), day.getWaterRecord().getWaterAmount(), entry);
        performWriteAction(entryFields.get(index++), day.getWaterRecord().getWaterpH(), entry);
        performWriteAction(entryFields.get(index++), day.getWaterRecord().getWaterSource(), entry);
        performWriteAction(entryFields.get(index++), day.getWaterRecord().getWaterNote(), entry);
        performWriteAction(entryFields.get(index++), day.getCropRecord().getCrop(), entry);
        performWriteAction(entryFields.get(index++), day.getCropRecord().getAmount(), entry);
        performWriteAction(entryFields.get(index++), day.getCropRecord().getCropStatus(), entry);
        performWriteAction(entryFields.get(index++), day.getCropRecord().getPests(), entry);
        performWriteAction(entryFields.get(index), day.getCropRecord().getCropNote(), entry);
        return entry;
    }

    private void populateReadActionMap (){
        int index = 0;
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).setDate(LocalDate.parse(value.toString())));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getWeatherRecord().setTemperature((Double) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getWeatherRecord().setHumidity((Double) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) ->  ((Day) object).getWeatherRecord().setPrecipitation((Double) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) ->  ((Day) object).getWeatherRecord().setWeatherNote((String) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getWaterRecord().setWaterAmount((Double) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getWaterRecord().setWaterpH((Double) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getWaterRecord().setWaterSource((String) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getWaterRecord().setWaterNote((String) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getCropRecord().setCrop((String) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getCropRecord().setAmount(((Double) value).intValue()));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getCropRecord().setCropStatus((String) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Day) object).getCropRecord().setPests((String) value));
        readActionMap.put(entryFields.get(index), (key, value, object) -> ((Day) object).getCropRecord().setCropNote((String) value));
    }

    private void populateWriteActionMap (){
        writeActionMap.put(String.class, (key, value, object) -> ((CMAEntry) object).setField(key, "en-US", (String) value));
        writeActionMap.put(Double.class, (key, value, object) -> ((CMAEntry) object).setField(key, "en-US", (Double) value));
        writeActionMap.put(Integer.class, (key, value, object) -> ((CMAEntry) object).setField(key, "en-US", (Integer) value));
    }

    private void populateFields (){
        String[] fields = {"date", "temperature", "humidity", "precipitation", "weatherNotes", "waterAmount", "waterpH", "waterSource", "waterNotes", "cropType", "cropAmount", "cropStatus", "pests", "cropNotes"};
        for (String field : fields){
            entryFields.add(field);
        }
    }
}