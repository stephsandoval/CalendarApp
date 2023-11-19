package Screens;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import ApiClient.CalendarApiClient;
import ApiClient.PostApiClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class LogInScreen extends GeneralScreen implements Initializable{

    @FXML
    private Button logInButton;

    public void loadFirstInformation (ActionEvent event){
        CalendarApiClient.getInstance();
        PostApiClient.getInstance();
        try {
            loadCalendarScreen(event);
        } catch (IOException e) {}
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logInButton.setOnAction(e -> {
            loadFirstInformation(e);
        });
    }
}