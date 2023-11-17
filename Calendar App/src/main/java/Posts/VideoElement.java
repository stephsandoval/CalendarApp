package Posts;

import java.io.File;

import javafx.scene.Node;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;

public class VideoElement implements VisualElement{

    private String mediaPath;

    public VideoElement (String mediaPath){
        this.mediaPath = mediaPath;
    }

    @Override
    public Node createVisual () {
        File file = new File(mediaPath);
        Media media = new Media(file.toURI().toString());
        MediaPlayer mediaPlayer = new MediaPlayer(media);
        MediaView mediaView = new MediaView(mediaPlayer);
        mediaView.setPreserveRatio(true);
        mediaView.setFitWidth(325);
        mediaView.setFitHeight(189);
        mediaView.setLayoutX(14);
        mediaView.setLayoutY(35);
        return (Node) mediaView;
    }
}