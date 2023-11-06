package Screens;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URL;
import java.util.ResourceBundle;

import Controllers.InstaController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class InstaScreen extends GeneralScreen implements Initializable{
    
    @FXML
    private ImageView post1, post2;
    @FXML
    private TextField screenImagePath;
    @FXML
    private TextArea screenDescription;
    @FXML
    private Button openFileExplorerButton, addPostButton;

    private Image image;

    private InputStream stream;
    private String description;

    private FileChooser fileChooser = new FileChooser();
    private InstaController controller = new InstaController();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        screenDescription.setWrapText(true);
        fileChooser.setInitialDirectory(new File("src"));
        image = new Image("tempImage.jpg");
        post1.setImage(image);
        post2.setImage(image);
    }

    public void addPost (){
        description = screenDescription.getText();
        controller.createNewPost(description, stream);
    }

    public void openFileExplorer () throws FileNotFoundException {
        screenImagePath.clear();
        File file = fileChooser.showOpenDialog(new Stage());
        screenImagePath.setText(file.getName());
        stream = new FileInputStream(file);
    }
}