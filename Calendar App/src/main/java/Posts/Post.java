package Posts;

import java.time.LocalDate;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

public class Post {
 
    private Label username, date;
    private Node media;
    private TextArea description;

    public Post (LocalDate date, String username, String description, VisualElement element){
        setUsernameLabel(username);
        setDateLabel(date);
        setDescriptionArea(description);
        this.media = element.createVisual();
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
        this.date.setLayoutX(314);
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

    public String toString (){
        return "post >> date > " + date + " | username > " + username + " | visual of type " + media.getClass() + " | description > " + description;
    }
}