package ApiClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;

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
import com.contentful.java.cma.model.CMAAsset;
import com.contentful.java.cma.model.CMAEntry;
import com.contentful.java.cma.model.CMAField;
import com.contentful.java.cma.model.CMAType;
import com.google.gson.internal.LinkedTreeMap;

import Posts.Post;

public class PostApiClient {
 
    private HashMap<String, Action> readActionMap;
    private HashMap<Class<?>, Action> writeActionMap;
    private ArrayList<String> entryFields;

    private String writeToken, readToken, spaceId, environmentId, contentType;
    private static PostApiClient instance;

    private PostApiClient (){
        this.writeToken = "CFPAT-PD1g-fAbvgONwsLi0_CdbJilnPRFE4QyEJ_PuduEdQ4";
        this.readToken = "fjgizg8zVhAwhlEs-2h3tEjwUWQLqAGxtN4aAXFpvBA";
        this.spaceId = "7u4zyhwnzl64";
        this.environmentId = "master";
        this.contentType = "post";

        this.readActionMap = new HashMap<>();
        this.writeActionMap = new HashMap<>();
        this.entryFields = new ArrayList<>();

        populateFields();
        populateReadActionMap();
        populateWriteActionMap();
    }

    public static synchronized PostApiClient getInstance (){
        if (instance == null){
            instance = new PostApiClient();
        }
        return instance;
    }

    public ArrayList<Post> readData (){
        ArrayList<Post> posts = new ArrayList<>();
        ArrayList<CDAEntry> entries = fetchEntries();
        for (CDAEntry entry : entries){
            posts.add(createPost(entry));
        }
        return posts; 
    }

    public void writeData (Post post){
        CMAClient client = new CMAClient.Builder().setAccessToken(writeToken).setSpaceId(spaceId).setEnvironmentId(environmentId).build();
        CMAEntry entry = createEntry(post);
        CMAEntry result = client.entries().create(contentType, entry);
        client.entries().publish(result);
    }

    private void performWriteAction (String key, Object value, Object object){
        Action action = writeActionMap.get(value.getClass());
        if (action != null){
            action.performAction(key, value, object);
        }
    }

    private CMAEntry createEntry (Post post){
        int index = 0;
        CMAEntry entry = new CMAEntry();
        entry.setField("postTitle", "en-US", post.getDate().toString() + "-" + post.getUsername());
        performWriteAction(entryFields.get(index++), post.getUsername(), entry);
        performWriteAction(entryFields.get(index++), post.getDate(), entry);
        performWriteAction(entryFields.get(index++), post.getDescription(), entry);
        performWriteAction(entryFields.get(index), post.getVisualPath(), entry);
        return entry;
    }

    private void writeImage (String key, Object value, Object object){
        // something
    }

    private void performReadAction(String key, Object value, Object object) {
        Action action = readActionMap.get(key);
        if (action != null){
            action.performAction(key, value, object);
        }
    }

    private ArrayList<CDAEntry> fetchEntries (){
        ArrayList<CDAEntry> posts = new ArrayList<>();
        CDAClient client = CDAClient.builder().setToken(readToken).setSpace(spaceId).setEnvironment(environmentId).build();
        CDAArray entries = client.fetch(CDAEntry.class).withContentType(contentType).all();
        for (CDAResource resource : entries.items()){
            if (resource instanceof CDAEntry){
                posts.add((CDAEntry) resource);
            }
        }
        return posts;
    }

    private Post createPost (CDAEntry entry){
        Post post = new Post();
        Map<String, Object> items = entry.rawFields();

        items.forEach((key, value) -> {
            Collection<Object> values = ((HashMap<Object, Object>)value).values();
            Iterator<Object> iterator = values.iterator();
            Object object = iterator.next();
            performReadAction(key, object, post);
        });
        return post;
    }

    private void getJsonURL (String key, Object value, Object object){
        LinkedTreeMap<String, Object> imageMap = (LinkedTreeMap<String, Object>)value;
        LinkedTreeMap<String, Object> sysMap = (LinkedTreeMap<String, Object>) imageMap.get("sys");
        String assetURL = " ";
        if (sysMap != null) {
            String linkId = (String) sysMap.get("id");
            if (linkId != null){
                assetURL = "https://cdn.contentful.com/spaces/" + spaceId + "/assets/" + linkId + "?access_token=" + readToken;
            }
        }
        String imageURL = "https:" + getImageURL(assetURL);
        System.out.println(imageURL);
        ((Post) object).setVisualElement(imageURL);
    }

    private String getImageURL (String assetURL){
        HttpClient client = HttpClients.createDefault();
        HttpGet entryRequest = new HttpGet(assetURL);
        HttpResponse entryResponse;
        BufferedReader entryReader;
        StringBuilder entryContent = null;
        try {
            entryResponse = client.execute(entryRequest);
            entryReader = new BufferedReader(new InputStreamReader(entryResponse.getEntity().getContent()));
            entryContent = new StringBuilder();
            String entryLine;
            while ((entryLine = entryReader.readLine()) != null){
                entryContent.append(entryLine);
            }
            entryReader.close();
        } catch (Exception e){}
        String imageURL = parseJson(entryContent.toString());
        return imageURL;
    }

    private String parseJson(String jsonContent) {
        String imageURL = "";
        try (JsonReader reader = Json.createReader(new StringReader(jsonContent))) {
            JsonObject jsonObject = reader.readObject();
            JsonObject fields = jsonObject.getJsonObject("fields");
            JsonObject file = fields.getJsonObject("file");
            imageURL = file.getString("url");
        } catch (Exception e) {}
        return imageURL;
    }    

    private void populateReadActionMap (){
        int index = 0;
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Post) object).setUsername((String) value));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Post) object).setDate(LocalDate.parse(value.toString())));
        readActionMap.put(entryFields.get(index++), (key, value, object) -> ((Post) object).setDescription((String) value));
        readActionMap.put(entryFields.get(index), (key, value, object) -> getJsonURL(key, value, object));
    }

    private void populateWriteActionMap (){
        writeActionMap.put(LocalDate.class, (key, value, object) -> ((CMAEntry) object).setField(key, "en-US", ((LocalDate) value).toString()));
        writeActionMap.put(String.class, (key, value, object) -> ((CMAEntry) object).setField(key, "en-US", (String) value));
    }

    private void populateFields (){
        String[] fields = {"username", "date", "description", "visualMedia"};
        for (String field : fields){
            entryFields.add(field);
        }
    }
}