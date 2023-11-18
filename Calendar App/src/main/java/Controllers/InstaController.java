package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import ApiClient.PostApiClient;
import Posts.Post;
import javafx.scene.layout.Pane;

public class InstaController {

    private ArrayList<Pane> posts;
 
    public InstaController (){
        posts = new ArrayList<>();
    }

    public ArrayList<Pane> getPosts (){
        PostApiClient reader = PostApiClient.getInstance();
        ArrayList<Post> postsRead = reader.readData();
        for (Post post : postsRead){
            posts.add(post);
        }
        return posts;
    }

    public double getPostOffset (){
        return Post.getPostHeight();
    }
}