package Notifications;

import org.controlsfx.control.Notifications;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class SuccessNotification implements Notification{

    private String imagePath = "file:src/main/java/Images/successIcon.png";
    private static SuccessNotification instance;

    private SuccessNotification (){}

    public static synchronized SuccessNotification getInstance (){
        if (instance == null){
            instance = new SuccessNotification();
        }
        return instance;
    }

    @Override
    public void notifyUser(String message) {
        Image image = new Image(imagePath);
        Notifications notification = Notifications.create();
        notification.graphic(new ImageView(image));
        notification.text("    " + message);
        notification.title("SUCCESS");
        notification.hideAfter(Duration.seconds(4));
        notification.hideCloseButton();
        notification.show();
    }
}