package Screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import Controllers.InstaController;
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

public class InstaScreen extends GeneralScreen implements Initializable{
    
    @FXML
    private TextField screenImagePath;
    @FXML
    private TextArea screenDescription;
    @FXML
    private Button openFileExplorerButton, addPostButton;
    @FXML
    private AnchorPane postPane;
    @FXML
    private ScrollPane scrollPane;

    private InputStream stream;

    private FileChooser fileChooser = new FileChooser();
    private InstaController controller = new InstaController();
    private double yCoordinate = 0;
    private double postOffset = controller.getPostOffset();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setInitialPosts();
        screenDescription.setWrapText(true);
        fileChooser.setInitialDirectory(new File("src"));
    }

    public void openFileExplorer () throws FileNotFoundException {
        screenImagePath.clear();
        File file = fileChooser.showOpenDialog(new Stage());
        screenImagePath.setText(file.getName());
        //stream = new FileInputStream(file);
    }

    private void setInitialPosts (){
        ArrayList<Pane> posts = controller.getPosts();
        fixlPaneSizes(posts.size());
        for (Pane post : posts){
            post.setLayoutY(yCoordinate);
            postPane.getChildren().add(post);
            yCoordinate += postOffset;
        }
    }

    private void fixlPaneSizes (int amountPost){
        double height = amountPost * postOffset;
        postPane.setPrefHeight(height);
        scrollPane.setPrefViewportHeight(height);
    }

    public void addPost (){
        
    }
}