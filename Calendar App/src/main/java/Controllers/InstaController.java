package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import Posts.ImageElement;
import Posts.Post;
import Posts.VideoElement;
import javafx.scene.layout.Pane;

public class InstaController {

    private ArrayList<Pane> posts;
 
    public InstaController (){
        posts = new ArrayList<>();
    }

    public ArrayList<Pane> getPosts (){
        String videoPath = "src/main/java/Videos/seedlings.mp4";
        String potatoPath = "file:src/main/java/tempImage.jpg";
        VideoElement video = new VideoElement(videoPath);
        ImageElement image = new ImageElement(potatoPath);
        String username = "stephsandoval";
        String description1 = "Understaing the local climate and adjusting your crop choices accordingly can significantly impact yield and quality.";
        String description2 = "Tip: try implementing crop rotation practices.\nTo maintain soil health and reduce the risk of diseases and pests, practice crop rotation.\nFor example, after harvesting potatoes, consider planting carrots in the same area and then follow with apples or another crop. This practice helps balance soil fertility, minimize the risk of soil-borne diseases, and supports sustainable agriculture.";
        Post postVideo = new Post(LocalDate.now(), username, description1, video);
        Post postImage = new Post(LocalDate.now(), username, description2, image);
        posts.add(postVideo);
        posts.add(postImage);
        return posts;
    }

    public double getPostOffset (){
        return Post.getPostHeight();
    }
}