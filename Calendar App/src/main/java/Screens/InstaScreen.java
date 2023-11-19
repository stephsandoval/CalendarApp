package Screens;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

import Controllers.InstaController;
import Notifications.Status;
import Observer.Observer;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InstaScreen extends GeneralScreen implements Initializable, Observer{
    
    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField screenImagePath, screenUsername;
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

    private HashMap<Status, String> messageMap;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        controller.registerMe(this);
        setPosts();
        populateMap();
        screenDescription.setWrapText(true);
        fileChooser.setInitialDirectory(new File(System.getProperty("user.home")));
    }

    public void openFileExplorer () throws FileNotFoundException {
        screenImagePath.clear();
        File file = fileChooser.showOpenDialog(new Stage());
        if (file != null){
            screenImagePath.setText(file.getPath());
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
        String description = screenDescription.getText();
        String mediaPath = screenImagePath.getText();
        String username = screenUsername.getText();
        Status status = controller.publishPost(mediaPath, description, username);
        showNotification(status, messageMap.get(status));
        cleanFields();
    }

    @Override
    public void update() {
        cleanFields();
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

    private void cleanFields (){
        screenImagePath.clear();
        screenUsername.clear();
        screenDescription.clear();
    }

    private void populateMap (){
        messageMap = new HashMap<>();
        messageMap.put(Status.ERROR, "Please provide a valid path");
        messageMap.put(Status.WARNING, "No information given for the post");
        messageMap.put(Status.SUCCESS, "The post was created and uploaded successfully");
    }
}