package Posts;

import javafx.scene.Node;

public interface VisualElement {
 
    public abstract Node createVisual ();
    public abstract String getMediaPath ();
}