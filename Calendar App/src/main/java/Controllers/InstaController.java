package Controllers;

import java.time.LocalDate;
import java.util.ArrayList;

import Posts.Post;
import Screens.InstaScreen;
import javafx.scene.layout.Pane;

public class InstaController {

    private ArrayList<Pane> posts;
    private PostUpdater postUpdater;
 
    public InstaController (){
        postUpdater = new PostUpdater();
        posts = new ArrayList<>();
        posts = loadPosts();
    }

    private ArrayList<Pane> loadPosts (){
        ArrayList<Post> postsRead = postUpdater.getPosts();
        for (Post post : postsRead){
            posts.add(post);
        }
        return posts;
    }

    public void registerMe (InstaScreen screen){
        postUpdater.registerObserver(screen);
    }

    public ArrayList<Pane> getPosts (){
        return this.posts;
    }

    public ArrayList<Pane> updatePosts (){
        ArrayList<Post> postsRead = postUpdater.getPosts();
        posts.clear();
        for (Post post : postsRead){
            posts.add(post);
        }
        return posts;
    }

    public void publishPost (String mediaPath, String description, String username){
        Post post = new Post();
        post.setUsername(username);
        post.setDate(LocalDate.now());
        post.setDescription(description);
        post.setVisualElement(mediaPath);
        postUpdater.addPost(post);
    }

    public double getPostOffset (){
        return Post.getPostHeight();
    }
}