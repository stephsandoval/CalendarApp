package Screens;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start (Stage stage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LogInScreen.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.setResizable(false);
            stage.setTitle("Calendar App");
            stage.getIcons().add(new Image("plantLogo.png"));
            stage.show();
        } catch (Exception e){}
    }

    public static void main (String[] args){
        launch(args);
    }
}