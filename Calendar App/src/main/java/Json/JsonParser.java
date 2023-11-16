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

    private JsonParser (){
        loadJson ();
        waterSource = toArray(jsonWaterSource);
        crops = toArray(jsonCrops);
        cropStatus = toArray(jsonCropStatus);
        pests = toArray(jsonPests);
        aspects = toArray(jsonAspects);
    }

    public static synchronized JsonParser getInstance (){
        if (instance == null){
            instance = new JsonParser();
        }
        return instance;
    }

    private void loadJson (){
        String filePath = "C:\\Users\\Stephanie\\OneDrive - Estudiantes ITCR\\Semestre II\\Programación Orientada a Objetos\\Caso 03\\Calendar App\\src\\Json\\config.json";
        try (JsonReader reader = Json.createReader(new FileReader(filePath))){
            JsonObject jsonParser = reader.readObject();

            jsonWaterSource = jsonParser.getJsonArray("water source");
            jsonCrops = jsonParser.getJsonArray("crops");
            jsonCropStatus = jsonParser.getJsonArray("crop status");
            jsonPests = jsonParser.getJsonArray("pests");
            jsonAspects = jsonParser.getJsonArray("aspects");
        } catch (Exception e){}
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
        return this.waterSource;
    }

    public ArrayList<String> getCrops (){
        return this.crops;
    }

    public ArrayList<String> getCropStatus (){
        return this.cropStatus;
    }

    public ArrayList<String> getPests (){
        return this.pests;
    }

    public ArrayList<String> getAspects (){
        return this.aspects;
    }
}