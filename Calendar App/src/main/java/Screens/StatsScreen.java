package Screens;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controllers.StatsController;
import Json.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
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

    private WebEngine engine;

    private JsonParser items = JsonParser.getInstance();
    private StatsController controller = new StatsController();

    @Override
    public void initialize (URL location, ResourceBundle resources){
        screenAspectsStats.getItems().setAll(items.getAspects());
        engine = webView.getEngine();
        webView.setZoom(0.8);
    }

    public void loadPage () throws MalformedURLException{
        controller.getStats(screenDate.getValue());
        webView.getEngine().loadContent("");
        String aspect = screenAspectsStats.getValue();

        if (aspect != null && screenDate.getValue() != null) {
            String fileName = controller.getURL(aspect);
            File file = new File(fileName);
            String url = file.toURI().toURL().toExternalForm();
            engine.load(url);
        }
    }
}