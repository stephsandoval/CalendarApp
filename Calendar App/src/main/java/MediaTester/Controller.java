package MediaTester;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class Controller implements Initializable{

    @FXML
    private MediaView mediaView;

    File file;
    Media media;
    MediaPlayer mediaPlayer;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        file = new File("C:\\Users\\Stephanie\\OneDrive - Estudiantes ITCR\\Semestre II\\Programación Orientada a Objetos\\Caso 03\\Calendar App\\src\\main\\java\\Videos\\fields.mp4");
        media = new Media(file.toURI().toString());
        mediaPlayer = new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        mediaPlayer.play();
    }
}