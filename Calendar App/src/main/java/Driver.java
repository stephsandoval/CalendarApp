import javafx.scene.image.Image;

public class Driver {
    public static void main (String [] args){
        String imagePath = "file:src/main/java/tempImage.jpg";
        try {
            Image image = new Image(imagePath);
            System.out.println(image.toString());
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}