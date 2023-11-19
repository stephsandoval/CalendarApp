package Posts;

import java.io.File;
import java.net.URL;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ImageElement implements VisualElement{

    private String mediaPath;

    public ImageElement (String mediaPath){
        this.mediaPath = mediaPath;
    }

    @Override
    public Node createVisual () {
        Image image = null;
        if (isURL(mediaPath)){
            image = new Image(mediaPath);
        } else {
            try {
                File file = new File(mediaPath);
                image = new Image(file.toURI().toURL().toString());
            } catch (Exception e){}
        }
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(325);
        imageView.setFitHeight(189);
        imageView.setLayoutX(14);
        imageView.setLayoutY(35);
        return (Node)imageView;
    }

    private boolean isURL (String input){
        try {
            new URL(input);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}