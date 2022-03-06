import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scene {


    private String name;
    private List<GameObject> gameObjectList = new ArrayList<GameObject>();
    private GUI currentGUI;
    private Light light;
    private Camera camera;
    @JsonIgnore
    private ObjectMapper mapper = new ObjectMapper();
    @JsonIgnore
    private KeyList keyList = null;

    public void setKeyList(KeyList keyList) {
        this.keyList = keyList;
    }

    public KeyList getKeyList(){
        return keyList;
    }

    public Scene(){
    }

    public Scene(String name,Light light, Camera camera) {
        this.name = name;
        this.light = light;
        this.camera = camera;
    }

    public Scene(String name,List<GameObject> gameObjectList,GUI currentGUI,Light light, Camera camera){
        this.name = name;
        for (GameObject gameObject : gameObjectList)
            this.loadGameObject(gameObject.getModelName(), gameObject.getTextureName(), gameObject.getTransform());
        this.currentGUI = currentGUI;
        this.light = light;
        this.camera = camera;
    }
    public void loadGameObject(String modelName, String textureName, Transform transform){
        gameObjectList.add(new GameObject(modelName,textureName,transform));
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

    public void setGameObjectList(List<GameObject> gameObjectList) {
        for (GameObject gameObject : gameObjectList)
            this.loadGameObject(gameObject.getModelName(), gameObject.getTextureName(),gameObject.getTransform());
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void saveScene(String saveFile){
        try {
            mapper.writeValue(new File(saveFile), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Scene loadScene(String loadFile){
        Scene scene = new Scene();
        try {
            scene = mapper.readValue(new File(loadFile), this.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return scene;
    }
}
