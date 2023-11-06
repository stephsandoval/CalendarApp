package Posts;

import java.io.InputStream;
import java.time.LocalDate;
import javafx.scene.image.Image;

public class Post {
 
    private LocalDate date;
    private String username, description;
    private InputStream stream;
    private Image image;

    public Post (LocalDate date, String username, String description, InputStream stream){
        this.date = date;
        this.username = username;
        this.stream = stream;
        this.description = description;
        this.image = new Image(stream);
    }

    public LocalDate getDate (){
        return date;
    }

    public void setDate (LocalDate date){
        this.date = date;
    }

    public String getUsername (){
        return username;
    }
    
    public void setUsername (String username){
        this.username = username;
    }

    public String getDescription (){
        return description;
    }

    public void setDescription (String description){
        this.description = description;
    }

    public Image getImage (){
        return image;
    }

    public void setImage (Image image){
        this.image = image;
    }

    public String toString (){
        return "post >> date > " + date + " | username > " + username + " | image > " + stream.toString() + " | description > " + description;
    }
}