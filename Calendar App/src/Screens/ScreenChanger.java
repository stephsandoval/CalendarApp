package Screens;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class ScreenChanger {
    
    private Stage stage;
    private Scene scene;
    private Parent root;
    
    public void loadCalendarScreen (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("CalendarScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void loadEventScreen (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("EventScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void loadInstaScreen (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("InstaScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void loadStatsScreen (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("StatsScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void logout (ActionEvent event) throws IOException {
        root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }
}