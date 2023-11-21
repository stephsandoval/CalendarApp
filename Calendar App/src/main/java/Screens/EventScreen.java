package Screens;

import java.net.URL;
import java.time.LocalDate;
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

    private EventController controller = new EventController ();
    private JsonParser items = JsonParser.getInstance();

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        clearFields();
        screenWaterSource.getItems().setAll(items.getWaterSource());
        screenCrop.getItems().setAll(items.getCrops());
        screenCropStatus.getItems().setAll(items.getCropStatus());
        screenPests.getItems().setAll(items.getPests());
        setKeyboardActions();
    }

    public void getValues (){
        if (!screenTemperature.getText().equals("")){
            temperature = Double.parseDouble(screenTemperature.getText());
        } else {
            temperature = 0.0;
        }
        if (!screenHumidity.getText().equals("")){
            humidity = Double.parseDouble(screenHumidity.getText());
        } else {
            humidity = 0.0;
        }
        if (!screenPrecipitation.getText().equals("")){
            precipitation = Double.parseDouble(screenPrecipitation.getText());
        } else {
            precipitation = 0.0;
        }
        weatherNotes = screenWeatherNotes.getText();

        waterSource = screenWaterSource.getValue();
        if (!screenWaterAmount.getText().equals("")){
            waterAmount = Double.parseDouble(screenWaterAmount.getText());
        } else {
            waterAmount = 0.0;
        }
        if (!screenWaterpH.getText().equals("")){
            waterpH = Double.parseDouble(screenWaterpH.getText());
        } else {
            waterpH = 0.0;
        }
        waterNotes = screenWaterNotes.getText();

        crop = screenCrop.getValue();
        if (!screenCropAmount.getText().equals("")){
            cropAmount = Integer.parseInt(screenCropAmount.getText());
        } else {
            cropAmount = 0;
        }
        cropStatus = screenCropStatus.getValue();
        pests = screenPests.getValue();
        cropNotes = screenCropNotes.getText();

        date = screenDate.getValue();
    }

    public void createEvent () throws Exception{
        getValues();
        if (validFields() && validDate()){
            controller.createEvents(date, temperature, humidity, precipitation, weatherNotes, waterSource, waterAmount, waterpH, waterNotes, crop, cropAmount, cropStatus, pests, cropNotes);
            showNotification(Status.SUCCESS, "The event was successfully created!");
            clearFields();
        }
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

    private boolean validDate (){
        if (date == null){
            showNotification(Status.WARNING, "Missing date. Please provide one");
            return false;
        }
        return true;
    }

    private boolean validFields (){
        if (crop.equals("") && cropAmount == 0 && waterNotes.equals("") && weatherNotes.equals("") && cropStatus.equals("") && pests.equals("") && cropNotes.equals("") && waterSource.equals("") && waterAmount == 0 && temperature == 0 && humidity == 0 && precipitation == 0 && waterpH == 0){
            showNotification(Status.ERROR, "No information given to create the event!");
            return false;
        }
        return true;
    }
}