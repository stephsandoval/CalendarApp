package Controllers;

import java.io.InputStream;
import java.time.LocalDate;

import Posts.Post;

public class InstaController {
 
    public InstaController (){}

    public void createNewPost (String description, InputStream stream){
        Post post = new Post(LocalDate.now(), "you", description, stream);
        System.out.println(post.toString());
    }
}