package Screens;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ResourceBundle;

import Controllers.StatsController;
import Json.JsonParser;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class StatsScreen extends GeneralScreen implements Initializable {
    
    @FXML
    private Button loadPage;
    @FXML
    private ComboBox <String> aspectsStats;
    @FXML
    private WebView webView;

    private WebEngine engine;

    private JsonParser items = JsonParser.getInstance();
    private StatsController controller = new StatsController();

    @Override
    public void initialize (URL location, ResourceBundle resources){
        aspectsStats.getItems().setAll(items.getAspects());
        engine = webView.getEngine();
        webView.setZoom(0.8);
    }

    public void loadPage () throws MalformedURLException{
        webView.getEngine().loadContent("");
        String aspect = aspectsStats.getValue();

        if (aspect != null && aspect.equals("crop")) {
            String fileName = controller.getURL("crop");
            File file = new File(fileName);
            String url = file.toURI().toURL().toExternalForm();
            engine.load(url);
        }
    }
}