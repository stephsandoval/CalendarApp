package Screens;

import java.net.URL;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controllers.EventController;
import Json.JsonParser;
import Notifications.Status;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class EventScreen extends GeneralScreen implements Initializable{
    
    @FXML
    private TextField screenWeatherNotes, screenWaterNotes, screenCropNotes, screenCropAmount, screenTemperature, screenHumidity, screenPrecipitation, screenWaterAmount, screenWaterpH;
    @FXML
    private ComboBox<String> screenWaterSource, screenCrop, screenCropStatus, screenPests;
    @FXML
    private Button addEventButton;
    @FXML
    private DatePicker screenDate;

    private double temperature, humidity, precipitation, waterAmount, waterpH;
    private int cropAmount;
    private String weatherNotes, waterNotes, crop, cropNotes, waterSource, cropStatus, pests;
    private LocalDate date;
    private HashMap<Status, String> messageMap;

    private EventController controller = new EventController ();
    private JsonParser items = JsonParser.getInstance();

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        clearFields();
        populateMap();
        screenWaterSource.getItems().setAll(items.getWaterSource());
        screenCrop.getItems().setAll(items.getCrops());
        screenCropStatus.getItems().setAll(items.getCropStatus());
        screenPests.getItems().setAll(items.getPests());
        setKeyboardActions();
    }

    public void getValues (){
        temperature = Double.parseDouble(screenTemperature.getText());
        humidity = Double.parseDouble(screenHumidity.getText());
        precipitation = Double.parseDouble(screenPrecipitation.getText());;
        weatherNotes = screenWeatherNotes.getText();

        waterSource = screenWaterSource.getValue();
        waterAmount = Double.parseDouble(screenWaterAmount.getText());
        waterpH = Double.parseDouble(screenWaterpH.getText());
        waterNotes = screenWaterNotes.getText();

        crop = screenCrop.getValue();
        cropAmount = Integer.parseInt(screenCropAmount.getText());
        cropStatus = screenCropStatus.getValue();
        pests = screenPests.getValue();
        cropNotes = screenCropNotes.getText();

        date = screenDate.getValue();
        if (date == null){
            date = LocalDate.now();
        }
    }

    public void createEvent () throws Exception{
        getValues();
        Status status = controller.createEvents(date, temperature, humidity, precipitation, weatherNotes, waterSource, waterAmount, waterpH, waterNotes, crop, cropAmount, cropStatus, pests, cropNotes);
        showNotification(status, messageMap.get(status));
        clearFields();
    }

    private void clearFields (){
        screenTemperature.clear();
        screenHumidity.clear();
        screenPrecipitation.clear();
        screenWeatherNotes.clear();

        screenWaterSource.setValue("");
        screenWaterAmount.clear();
        screenWaterpH.clear();
        screenWaterNotes.clear();

        screenCrop.setValue("");
        screenCropAmount.clear();
        screenCropStatus.setValue("");
        screenPests.setValue("");
        screenCropNotes.clear();

        screenDate.setValue(null);
    }

    private void populateMap (){
        messageMap = new HashMap<>();
        messageMap.put(Status.WARNING, "No information given for the event");
        messageMap.put(Status.SUCCESS, "The event was created and uploaded successfully");
    }
    
    public void checkKey (KeyEvent event){
        char key = event.getCharacter().charAt(0);
        if (!Character.isDigit(key)) {
            event.consume();
        }
    }

    private void checkLength (TextField field, KeyEvent event){
        int length = field.getText().length();
        if (length == 255){
            event.consume();
        }
    }

    private void setKeyboardActions (){
        screenCropNotes.setOnKeyTyped(event -> {
            checkLength(screenCropNotes, event);
        });
        screenWaterNotes.setOnKeyTyped(event -> {
            checkLength(screenWaterNotes, event);
        });
        screenWeatherNotes.setOnKeyTyped(event -> {
            checkLength(screenWeatherNotes, event);
        });
    }
}