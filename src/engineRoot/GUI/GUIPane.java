import org.joml.Vector2f;

public class GUIPane extends GUITexture{
    private Vector2f position;
    private Vector2f size;

    public GUIPane(int posX,int posY,int width,int height) {
        super("GUIPane",GUI.getPosition(posX,posY,width,height),GUI.getScale(width,height));
        this.position = new Vector2f(posX,posY);
        this.size = new Vector2f(width,height);
    }
}
