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
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class EventScreen extends GeneralScreen implements Initializable{
    
    @FXML
    private Slider screenTemperature, screenHumidity, screenPrecipitation, screenWaterAmount, screenWaterpH, screenCropAmount;
    @FXML
    private TextField screenWeatherNotes, screenWaterNotes, screenCropNotes;
    @FXML
    private ComboBox<String> screenWaterSource, screenCrop, screenCropStatus, screenPests;
    @FXML
    private Button addEventButton;
    @FXML
    private Label temperatureLabel, humidityLabel, precipitationLabel, waterAmountLabel, waterpHLabel, cropAmountLabel;
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
    }

    public void updateTemperature (){
        temperatureLabel.setText(Double.toString(roundDouble(screenTemperature.getValue(), "3")));
    }

    public void updateHumidity (){
        humidityLabel.setText(Double.toString(roundDouble(screenHumidity.getValue(), "3")));
    }

    public void updatePrecipitation (){
        precipitationLabel.setText(Double.toString(roundDouble(screenPrecipitation.getValue(), "3")));
    }

    public void updateWaterAmount (){
        waterAmountLabel.setText(Double.toString(roundDouble(screenWaterAmount.getValue(), "3")));
    }

    public void updateWaterpH (){
        waterpHLabel.setText(Double.toString(roundDouble(screenWaterpH.getValue(), "2")));
    }

    public void updateCropAmount (){
        cropAmountLabel.setText(Integer.toString((int) Math.floor(screenCropAmount.getValue())));
    }

    public void getValues (){
        temperature = roundDouble(screenTemperature.getValue(), "3");
        humidity = roundDouble(screenHumidity.getValue(), "3");
        precipitation = roundDouble(screenPrecipitation.getValue(), "3");
        weatherNotes = screenWeatherNotes.getText();

        waterSource = screenWaterSource.getValue();
        waterAmount = roundDouble(screenWaterAmount.getValue(), "3");
        waterpH = roundDouble(screenWaterpH.getValue(), "2");
        waterNotes = screenWaterNotes.getText();

        crop = screenCrop.getValue();
        cropAmount = (int) screenCropAmount.getValue();
        cropStatus = screenCropStatus.getValue();
        pests = screenPests.getValue();
        cropNotes = screenCropNotes.getText();

        date = screenDate.getValue();
        if (date == null){
            date = LocalDate.now();
        }
    }

    private double roundDouble (double number, String decimals){
        String formattedValue = String.format("%." + decimals + "f", number);
        double roundedValue = Double.parseDouble(formattedValue);
        return roundedValue;
    }

    public void createEvent () throws Exception{
        getValues();
        Status status = controller.createEvents(date, temperature, humidity, precipitation, weatherNotes, waterSource, waterAmount, waterpH, waterNotes, crop, cropAmount, cropStatus, pests, cropNotes);
        showNotification(status, messageMap.get(status));
        clearFields();
    }

    private void clearFields (){
        screenTemperature.setValue(screenTemperature.getMin());
        screenHumidity.setValue(screenHumidity.getMin());
        screenPrecipitation.setValue(screenTemperature.getMin());
        screenWeatherNotes.clear();

        updateTemperature();
        updateHumidity();
        updatePrecipitation();

        screenWaterSource.setValue("");
        screenWaterAmount.setValue(screenWaterAmount.getMin());
        screenWaterpH.setValue(screenWaterpH.getMin());
        screenWaterNotes.clear();

        updateWaterAmount();
        updateWaterpH();

        screenCrop.setValue("");
        screenCropAmount.setValue(screenCropAmount.getMin());
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
}