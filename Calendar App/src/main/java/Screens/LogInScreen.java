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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LogInScreen extends GeneralScreen implements Initializable{

    @FXML
    private Button logInButton;
    @FXML
    private ImageView background;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setBackground();
        logInButton.setOnAction(event -> {
            loadFirstInformation(event);
        });
    }

    public void loadFirstInformation (ActionEvent event){
        CalendarApiClient.getInstance();
        PostApiClient.getInstance();
        try {
            loadCalendarScreen(event);
        } catch (IOException exception) {}
    }

    private void setBackground (){
        Image image = new Image("file:src/main/java/Images/background-login.png");
        background.setImage(image);
    }
}