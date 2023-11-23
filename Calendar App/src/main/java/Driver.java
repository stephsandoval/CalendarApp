import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class Driver extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Create a button
        //Button button = new Button("Click me");

        // Create an image view with an icon (replace "path/to/icon.png" with your actual icon path)
        Image icon = new Image("file:src/main/java/Images/successIcon.png");
        ImageView iconView = new ImageView(icon);
        iconView.setFitWidth(16); // Adjust the size as needed
        iconView.setFitHeight(16);

        // Create a label with text and an icon
        Label labeledButton = new Label("Click me too", iconView);

        // Set actions for the buttons (you can replace these with your actual actions)
        //button.setOnAction(e -> System.out.println("Button Clicked!"));
        labeledButton.setOnMouseClicked(e -> System.out.println("Labeled Button Clicked!"));

        // Create a layout and add the buttons
        VBox root = new VBox(10);
        root.getChildren().addAll(labeledButton);

        // Create a scene and set it on the stage
        Scene scene = new Scene(root, 300, 200);
        primaryStage.setScene(scene);

        // Set the stage title and show it
        primaryStage.setTitle("JavaFX Icon Button Example");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
