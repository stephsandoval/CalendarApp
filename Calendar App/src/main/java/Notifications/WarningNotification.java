package Notifications;

import org.controlsfx.control.Notifications;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

public class WarningNotification implements Notification{

    private String imagePath = "file:src/main/java/Images/warningIcon.png";
    private static WarningNotification instance;

    private WarningNotification (){}

    public static synchronized WarningNotification getInstance (){
        if (instance == null){
            instance = new WarningNotification();
        }
        return instance;
    }

    @Override
    public void notifyUser(String message) {
        Image image = new Image(imagePath);
        Notifications notification = Notifications.create();
        notification.graphic(new ImageView(image));
        notification.text("    " + message);
        notification.title("WARNING\n");
        notification.hideAfter(Duration.seconds(4));
        notification.hideCloseButton();
        notification.show();
    }
}