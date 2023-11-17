package Posts;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;

public class Post extends Pane{
 
    private Label username, date;
    private Node media;
    private TextArea description;

    private static double height = 332;
    private static double width = 355;

    public Post (LocalDate date, String username, String description, VisualElement element){
        this.username = new Label();
        this.date = new Label();
        this.description = new TextArea();

        setUsernameLabel(username);
        setDateLabel(date);
        setDescriptionArea(description);

        this.media = element.createVisual();
        setPane();
    }

    private void setPane (){
        this.setPrefWidth(355);
        this.setPrefWidth(332);
        this.getChildren().add(username);
        this.getChildren().add(date);
        this.getChildren().add(description);
        this.getChildren().add(media);
    }

    private void setUsernameLabel (String username){
        setUsername(username);
        this.username.setAlignment(Pos.CENTER_LEFT);
        this.username.setLayoutX(14);
        this.username.setLayoutY(14);
    }

    private void setDateLabel (LocalDate date){
        setDate(date);
        this.date.setAlignment(Pos.CENTER_RIGHT);
        this.date.setLayoutX(275);
        this.date.setLayoutY(14);
    }

    private void setDescriptionArea (String description){
        setDescription(description);
        this.description.setWrapText(true);
        this.description.setEditable(false);
        this.description.setPrefWidth(326);
        this.description.setPrefHeight(85);
        this.description.setLayoutX(14);
        this.description.setLayoutY(232);
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
        if (username != null){
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

    public static double getPostHeight (){
        return height;
    }

    public String toString (){
        return "post >> date > " + date + " | username > " + username + " | visual of type " + media.getClass() + " | description > " + description;
    }
}