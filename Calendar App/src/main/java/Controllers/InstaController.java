package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import ApiClient.PostApiClient;
import Posts.Post;
import javafx.scene.layout.Pane;

public class InstaController {

    private ArrayList<Pane> posts;
    private PostApiClient client;
 
    public InstaController (){
        client = PostApiClient.getInstance();
        posts = new ArrayList<>();
    }

    public ArrayList<Pane> getPosts (){
        ArrayList<Post> postsRead = client.readData();
        for (Post post : postsRead){
            posts.add(post);
        }
        return posts;
    }

    public void publishPost (String mediaPath, String description){
        Post post = new Post();
        post.setUsername("devilPotato");
        post.setDate(LocalDate.now());
        post.setDescription(description);
        post.setVisualElement(mediaPath);
        client.writeData(post);
    }

    public double getPostOffset (){
        return Post.getPostHeight();
    }
}