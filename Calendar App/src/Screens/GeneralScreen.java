package Screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Node;

public class GeneralScreen {

    private final String calendarPath = "src/FXML/NewCalendarScreen.fxml";
    private final String eventsPath = "src/FXML/EventScreen.fxml";
    private final String postsPath = "src/FXML/InstaScreen.fxml";
    private final String statsPath = "src/FXML/StatsScreen.fxml";
    private final String loginPath = "src/FXML/LogInScreen.fxml";

    private String path;

    private void loadScreen (ActionEvent event) throws IOException {
        File file = new File(path);
        InputStream stream = new FileInputStream(file);
        FXMLLoader loader = new FXMLLoader();
        Parent root = loader.load(stream);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.show();
    }

    public void loadCalendarScreen (ActionEvent event) throws IOException{
        path = calendarPath;
        loadScreen(event);
    }

    public void loadEventsScreen (ActionEvent event) throws IOException{
        path = eventsPath;
        loadScreen(event);
    }

    public void loadPostsScreen (ActionEvent event) throws IOException{
        path = postsPath;
        loadScreen(event);
    }

    public void loadStatsScreen (ActionEvent event) throws IOException{
        path = statsPath;
        loadScreen(event);
    }

    public void loadLoginScreen (ActionEvent event) throws IOException{
        path = loginPath;
        loadScreen(event);
    }
}