package Screens;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Controllers.EventController;
import Json.JsonParser;
import android.widget.DatePicker;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;

public class EventScreen extends GeneralScreen implements Initializable{
    
    @FXML
    private Slider screenTemperature, screenHumidity, screenPrecipitation, screenSunlight, screenWaterAmount, screenWaterpH, screenCropAmount;
    @FXML
    private TextField screenWeatherNotes, screenWaterNotes, screenCrop, screenCropNotes;
    @FXML
    private ComboBox<String> screenWaterSource, screenCropStatus, screenPests;
    @FXML
    private Button addEventButton;
    @FXML
    private Label temperatureLabel, humidityLabel, precipitationLabel, sunlightLabel, waterAmountLabel, waterpHLabel, cropAmountLabel;
    @FXML
    private DatePicker screenDate;

    private double temperature, humidity, precipitation, sunlight, waterAmount, waterpH;
    private int cropAmount;
    private String weatherNotes, waterNotes, crop, cropNotes, waterSource, cropStatus, pests;
    private LocalDate date;

    private EventController controller = new EventController ();
    private JsonParser items = JsonParser.getInstance();

    @Override
    public void initialize (URL location, ResourceBundle resources) {
        clearFields();
        screenWaterSource.getItems().setAll(items.getWaterSource());
        screenCropStatus.getItems().setAll(items.getCropStatus());
        screenPests.getItems().setAll(items.getPests());
    }

    public void updateTemperature (){
        temperatureLabel.setText(Double.toString(Math.floor(screenTemperature.getValue())));
    }

    public void updateHumidity (){
        humidityLabel.setText(Double.toString(Math.floor(screenHumidity.getValue())));
    }

    public void updatePrecipitation (){
        precipitationLabel.setText(Double.toString((Math.floor(screenPrecipitation.getValue()))));
    }

    public void updateSunlight (){
        sunlightLabel.setText(Double.toString(Math.floor(screenSunlight.getValue())));
    }

    public void updateWaterAmount (){
        waterAmountLabel.setText(Double.toString(Math.floor(screenWaterAmount.getValue())));
    }

    public void updateWaterpH (){
        waterpHLabel.setText(Double.toString(Math.floor(screenWaterpH.getValue())));
    }

    public void updateCropAmount (){
        cropAmountLabel.setText(Double.toString(Math.floor(screenCropAmount.getValue())));
    }

    public void getValues (){
        temperature = screenTemperature.getValue();
        humidity = screenHumidity.getValue();
        precipitation = screenPrecipitation.getValue();
        sunlight = screenSunlight.getValue();
        weatherNotes = screenWeatherNotes.getText();

        waterSource = screenWaterSource.getValue();
        waterAmount = screenWaterAmount.getValue();
        waterpH = screenWaterpH.getValue();
        waterNotes = screenWaterNotes.getText();

        crop = screenCrop.getText().toLowerCase();
        cropAmount = (int) screenCropAmount.getValue();
        cropStatus = screenCropStatus.getValue();
        pests = screenPests.getValue();
        cropNotes = screenCropNotes.getText();
    }

    public void createEvent (){
        getValues();
        controller.createEvents(temperature, humidity, precipitation, sunlight, weatherNotes, waterSource, waterAmount, waterpH, waterNotes, crop, cropAmount, cropStatus, pests, cropNotes);
        clearFields();
    }

    private void clearFields (){
        screenTemperature.setValue(screenTemperature.getMin());
        screenHumidity.setValue(screenHumidity.getMin());
        screenPrecipitation.setValue(screenTemperature.getMin());
        screenSunlight.setValue(screenSunlight.getMin());
        screenWeatherNotes.clear();

        updateTemperature();
        updateHumidity();
        updatePrecipitation();
        updateSunlight();

        screenWaterSource.setValue("");
        screenWaterAmount.setValue(screenWaterAmount.getMin());
        screenWaterpH.setValue(screenWaterpH.getMin());
        screenWaterNotes.clear();

        updateWaterAmount();
        updateWaterpH();

        screenCrop.clear();
        screenCropAmount.setValue(screenCropAmount.getMin());
        screenCropStatus.setValue("");
        screenPests.setValue("");
        screenCropNotes.clear();
    }
}