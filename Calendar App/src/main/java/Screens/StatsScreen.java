package Screens;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import Controllers.StatsController;
import Json.JsonParser;
import Notifications.Status;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class StatsScreen extends GeneralScreen implements Initializable {
    
    @FXML
    private Button loadPageButton;
    @FXML
    private ComboBox <String> screenAspectsStats;
    @FXML
    private DatePicker screenDate;
    @FXML
    private WebView webView;
    @FXML
    private ImageView background;

    private WebEngine engine;

    private JsonParser items = JsonParser.getInstance();
    private StatsController controller = new StatsController();
    private LocalDate date;
    private String aspect;

    @Override
    public void initialize (URL location, ResourceBundle resources){
        setBackground();
        screenAspectsStats.getItems().setAll(items.getAspects());
        engine = webView.getEngine();
        webView.setZoom(0.8);
    }

    public void loadPage () throws MalformedURLException{
        getValues();
        if (validFields() && validDate() && validAspect()){
            controller.getStats(date, aspect);
            webView.getEngine().loadContent("");
            String fileName = controller.getURL(aspect);
            File file = new File(fileName);
            String url = file.toURI().toURL().toExternalForm();
            engine.load(url);
        }
    }

    private void getValues (){
        date = screenDate.getValue();
        aspect = screenAspectsStats.getValue();
    }

    private boolean validDate (){
        if (date == null){
            showNotification(Status.WARNING, "Please provide a date");
            return false;
        }
        return true;
    }

    private boolean validAspect (){
        if (aspect == null){
           showNotification(Status.WARNING, "Please select an aspect to show stats about");
            return false;
        }
        return true;
    }

    private boolean validFields (){
        if (date == null && aspect == null){
            showNotification(Status.ERROR, "No information given to show stats!");
            return false;
        }
        return true;
    }

    private void setBackground (){
        Image image = new Image("file:src/main/java/Images/background.png");
        background.setImage(image);
    }
}