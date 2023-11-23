package Json;

import java.io.FileReader;
import java.util.ArrayList;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.json.JsonValue;

public class JsonParser {
    
    private static JsonParser instance;
    private JsonArray jsonWaterSource, jsonCrops, jsonCropStatus, jsonPests, jsonAspects;
    private ArrayList <String> waterSource, crops, cropStatus, pests, aspects;
    private ArrayList <String> basic;

    private JsonParser (){
        loadJson ();
        waterSource = toArray(jsonWaterSource);
        crops = toArray(jsonCrops);
        cropStatus = toArray(jsonCropStatus);
        pests = toArray(jsonPests);
        aspects = toArray(jsonAspects);
        // empty array to send in case of an error
        basic = new ArrayList<>();
    }

    public static synchronized JsonParser getInstance (){
        if (instance == null){
            instance = new JsonParser();
        }
        return instance;
    }

    private void loadJson (){
        String filePath = "src/main/java/Json/config.json";
        try (JsonReader reader = Json.createReader(new FileReader(filePath))){
            JsonObject jsonParser = reader.readObject();

            jsonWaterSource = jsonParser.getJsonArray("water source");
            jsonCrops = jsonParser.getJsonArray("crops");
            jsonCropStatus = jsonParser.getJsonArray("crop status");
            jsonPests = jsonParser.getJsonArray("pests");
            jsonAspects = jsonParser.getJsonArray("aspects");
        } catch (Exception exception){}
    }

    private ArrayList<String> toArray (JsonArray jsonArray){
        if (jsonArray == null){
            return null;
        }
        int beginning = 1;
        ArrayList<String> array = new ArrayList<>();
        for (JsonValue element : jsonArray){
            String data = element.toString();
            array.add(data.substring(beginning, data.length()-1));
        }
        return array;
    }

    public ArrayList<String> getWaterSource (){
        if (this.waterSource == null){
            return basic;
        }
        return this.waterSource;
    }

    public ArrayList<String> getCrops (){
        if (this.crops == null){
            return basic;
        }
        return this.crops;
    }

    public ArrayList<String> getCropStatus (){
        if (this.cropStatus == null){
            return basic;
        }
        return this.cropStatus;
    }

    public ArrayList<String> getPests (){
        if (this.pests == null){
            return basic;
        }
        return this.pests;
    }

    public ArrayList<String> getAspects (){
        if (this.aspects == null){
            return basic;
        }
        return this.aspects;
    }
}