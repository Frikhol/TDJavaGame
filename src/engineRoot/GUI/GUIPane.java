import org.joml.Vector2f;

public class GUIPane extends GUITexture{
    Vector2f position;
    Vector2f size;

    public GUIPane(int posX,int posY,int width,int height) {
        super(Loader.loadTexture("GUIPane.png").getId(),GUI.getPosition(posX,posY,width,height),GUI.getScale(width,height));
    }
}
