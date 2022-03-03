import java.util.ArrayList;
import java.util.List;

public class Scene {

    private List<GameObject> gameObjectList = new ArrayList<GameObject>();
    private GUI currentGUI;
    private Light light;
    private Camera camera;

    private KeyList keyList = null;

    public void setKeyList(KeyList keyList) {
        this.keyList = keyList;
    }

    public KeyList getKeyList(){
        return keyList;
    }

    public Scene(Light light, Camera camera) {
        this.light = light;
        this.camera = camera;
    }

    public void loadGameObject(String modelName, String textureName){
        gameObjectList.add(new GameObject(new Mesh(OBJLoader.loadObjModel(modelName,GameEngine.getLoader()),new MeshTexture(Loader.loadTexture(textureName+".png").getId()))));
    }

    public GUI getCurrentGUI() {
        return currentGUI;
    }

    public void setCurrentGUI(GUI currentGUI) {
        this.currentGUI = currentGUI;
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
