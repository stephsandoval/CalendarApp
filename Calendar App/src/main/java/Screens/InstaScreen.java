package Screens;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controllers.InstaController;
import Notifications.Status;
import Observers.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InstaScreen extends GeneralScreen implements Initializable, Observer {
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField screenMediaPath, screenUsername;
    @FXML
    private TextArea screenDescription;
    @FXML
    private Button openFileExplorerButton, addPostButton;
    @FXML
    private AnchorPane postPane;
    @FXML
    private ScrollPane scrollPane;

    private FileChooser fileChooser = new FileChooser();
    private InstaController controller = new InstaController();
    private double yCoordinate = 0;
    private double postOffset = controller.getPostOffset();
    private ArrayList<Pane> posts = new ArrayList<>();

    private String description, username, mediaPath;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller.registerMe(this);
        setPosts();
        screenDescription.setWrapText(true);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
        setKeyboardActions();
    }

    public void openFileExplorer () throws FileNotFoundException {
        screenMediaPath.clear();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null){
            screenMediaPath.setText(file.getPath());
        }
    }

    private void setPosts (){
        posts = controller.getPosts();
        fixPaneSizes(posts.size());
        for (Pane post : posts){
            post.setLayoutY(yCoordinate);
            postPane.getChildren().add(post);
            yCoordinate += postOffset;
        }
    }

    private void fixPaneSizes (int amountPost){
        double height = amountPost * postOffset;
        postPane.setPrefHeight(height);
        scrollPane.setPrefViewportHeight(height);
    }

    public void addPost (){
        getValues();
        if (validFields() && validDescriptionLength() && validPath(mediaPath)){
            controller.publishPost(mediaPath, description, username);
            showNotification(Status.SUCCESS, "The post was succesfully created");
            clearFields();
        }
    }

    @Override
    public void update() {
        clearFields();
        fixPaneSizes(posts.size() + 1);
    
        for (Pane post : posts) {
            double previousCoordinate = post.getLayoutY();
            double newCoordinate = previousCoordinate + postOffset;
            post.setLayoutY(newCoordinate);
        }
    
        posts.add(controller.updatePosts().get(0));
        Pane post = posts.get(0);
        post.setLayoutY(0);
        postPane.getChildren().add(post);
        postPane.layout();
        yCoordinate += postOffset;
    }

    private void getValues (){
        description = screenDescription.getText();
        username = screenUsername.getText();
        mediaPath = screenMediaPath.getText();
    }

    private void clearFields (){
        screenMediaPath.clear();
        screenUsername.clear();
        screenDescription.clear();
    }

    private void checkLength (TextField field, KeyEvent event){
        int length = field.getText().length();
        if (length == 255){
            event.consume();
        }
    }

    private void setKeyboardActions (){
        screenUsername.setOnKeyTyped(event -> {
            checkLength(screenUsername, event);
        });
    }

    private boolean validDescriptionLength (){
        if (description.length() == 2000){
            showNotification(Status.WARNING, "The description cannot exceed the 2000 characters");
            return false;
        }
        return true;
    }

    private boolean validPath (String mediaPath){
        try {
            Path path = Paths.get(mediaPath);
            if (path.isAbsolute() && path.normalize().equals(path)){
                return true;
            }
        } catch (Exception e){}
        showNotification(Status.WARNING, "Invalid path. Please provide a valid path for the visual media");
        return false;
    }

    private boolean validFields (){
        if (description.equals("") && username.equals("") && mediaPath.equals("")){
            showNotification(Status.ERROR, "No information given to create the post!");
            return false;
        }
        return true;
    }
}