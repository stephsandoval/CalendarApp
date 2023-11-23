package Posts;

import java.time.LocalDate;

import org.apache.tika.Tika;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Post extends Pane{
 
    private Label username, date;
    private Node media;
    private TextArea description;

    private String mediaPath;
    private static double height = 332;

    public Post (){
        this.username = new Label();
        this.date = new Label();
        this.description = new TextArea();
        setUsernameLabel("");
        setDateLabel(null);
        setDescriptionArea("");
    }

    public Post (LocalDate date, String username, String description, String mediaPath){
        this();
        setUsernameLabel(username);
        setDateLabel(date);
        setDescriptionArea(description);
        setVisualElement(mediaPath);
        setPane();
        this.mediaPath = mediaPath;
    }

    private void setPane (){
        this.setPrefWidth(355);
        this.setPrefHeight(332);
        this.getChildren().add(username);
        this.getChildren().add(date);
        this.getChildren().add(description);
        this.getChildren().add(media);
        this.setStyle("-fx-background-color : transparent;");
    }

    private void setUsernameLabel (String username){
        setUsername(username);
        this.username.setAlignment(Pos.CENTER_LEFT);
        this.username.setLayoutX(14);
        this.username.setLayoutY(14);
        this.username.setStyle("-fx-font-family : \"Candara\"; -fx-font-weight : bold");
    }

    private void setDateLabel (LocalDate date){
        setDate(date);
        this.date.setAlignment(Pos.CENTER_RIGHT);
        this.date.setLayoutX(275);
        this.date.setLayoutY(14);
        this.date.setStyle("-fx-font-family : \"Candara\"; -fx-font-weight : bold");
    }

    private void setDescriptionArea (String description){
        setDescription(description);
        this.description.setWrapText(true);
        this.description.setEditable(false);
        this.description.setPrefWidth(328);
        this.description.setPrefHeight(85);
        this.description.setLayoutX(14);
        this.description.setLayoutY(232);
        this.description.setStyle("-fx-background-color : transparent; -fx-text-fill : rgb(61, 61, 61); -fx-font-family : \"Candara\"; -fx-font-size : 12;");
    }

    public LocalDate getDate (){
        return LocalDate.parse(date.getText());
    }

    public void setDate (LocalDate date){
        if (date != null){
            this.date.setText(date.toString());
        } else {
            this.date.setText(LocalDate.now().toString());
        }
    }

    public String getUsername (){
        return username.getText();
    }
    
    public void setUsername (String username){
        if (username != null && !username.equals("")){
            this.username.setText(username);
        } else {
            this.username.setText("anonymous");
        }
    }

    public String getDescription (){
        return description.getText();
    }

    public void setDescription (String description){
        if (description != null){
            this.description.setText(description);
        } else {
            this.description.setText(" ");
        }
    }

    public String getVisualPath (){
        return this.mediaPath;
    }

    public void setVisualElement (String mediaPath){
        this.mediaPath = mediaPath;
        Tika tika = new Tika();
        String type = tika.detect(mediaPath).split("/")[0];
        if (type.equals("video")){
            this.media = new VideoElement(mediaPath).createVisual();
        }
        if (type.equals("image")){
            this.media = new ImageElement(mediaPath).createVisual();
        }
        setPane();
    }

    public static double getPostHeight (){
        return height;
    }
}