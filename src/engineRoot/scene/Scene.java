import java.util.ArrayList;
import java.util.List;

public class Scene {
    private List<GameObject> gameObjectList = new ArrayList<GameObject>();
    private Light light;
    private Camera camera;

    public Scene(Light light, Camera camera) {
        this.light = light;
        this.camera = camera;
    }

    public void loadGameObject(String modelName, String textureName){
        gameObjectList.add(new GameObject(new Mesh(OBJLoader.loadObjModel(modelName,GameEngine.getLoader()),new MeshTexture(Loader.loadTexture(textureName+".png").getId()))));
    }

    public List<GameObject> getGameObjectList (){
        return gameObjectList;
    }

    public Light getLight() {
        return light;
    }

    public void setLight(Light light) {
        this.light = light;
    }

    public Camera getCamera() {
        return camera;
    }

    public void setCamera(Camera camera) {
        this.camera = camera;
    }
}
