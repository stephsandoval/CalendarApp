package Posts;

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
        Image image = new Image(mediaPath);
        ImageView imageView = new ImageView(image);
        imageView.setPreserveRatio(false);
        imageView.setFitWidth(325);
        imageView.setFitHeight(189);
        imageView.setLayoutX(14);
        imageView.setLayoutY(35);
        return (Node)imageView;
    }

    @Override
    public String getMediaPath (){
        return this.mediaPath;
    }
}