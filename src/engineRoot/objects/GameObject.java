import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

class GameObject {


    @JsonIgnore private GameObject parent;
    @JsonIgnore private List<GameObject> childs = new ArrayList<GameObject>();
    @JsonIgnore private Model model;
    private Transform transform;
    private String modelName;
    private String textureName;

    public GameObject() {
    }

    public GameObject(Model model) {
        this.model = model;
        this.transform = new Transform(new Vector3f(0,0,0),new Vector3f(0,0,0));
    }

    public GameObject(String modelName, String textureName,Transform transform) {
        this(new Model(OBJLoader.loadObjModel(modelName,GameEngine.getLoader()),new MeshTexture(Loader.loadTexture(textureName+".png").getId())));
        this.modelName = modelName;
        this.textureName = textureName;
        this.transform = transform;
    }

    public GameObject(GameObject parent, List<GameObject> childs, Model model, Transform transform) {
        this.parent = parent;
        this.childs = childs;
        this.model = model;
        this.transform = transform;
    }

    public void addChild(GameObject gameObject){
        childs.add(gameObject);
    }

    public void rotate(Vector3f rotation){
        transform.rotate(rotation);
        for(GameObject child: childs)
            child.getTransform().rotate(rotation);
    }

    public void rotateX(float x){
        transform.rotateX(x);
        for(GameObject child: childs)
            child.getTransform().rotateX(x);
    }

    public void rotateY(float y){
        transform.rotateX(y);
        for(GameObject child: childs)
            child.getTransform().rotateY(y);
    }

    public void rotateZ(float z){
        transform.rotateX(z);
        for(GameObject child: childs)
            child.getTransform().rotateZ(z);
    }

    public void translate(Vector3f translation){
        transform.translate(translation);
        for(GameObject child: childs)
            child.getTransform().translate(translation);
    }

    public void translateX(float x){
        transform.translateX(x);
        for(GameObject child: childs)
            child.getTransform().translateX(x);
    }

    public void translateY(float y){
        transform.translateY(y);
        for(GameObject child: childs)
            child.getTransform().translateY(y);
    }

    public void translateZ(float z){
        transform.translateZ(z);
        for(GameObject child: childs)
            child.getTransform().translateZ(z);
    }

    public void scale(float scale){
        transform.scale(scale);
        for(GameObject child: childs)
            child.getTransform().scale(scale);
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public GameObject getParent() {
        return parent;
    }

    public void setParent(GameObject parent) {
        this.parent = parent;
    }

    public List<GameObject> getChilds() {
        return childs;
    }

    public void setChilds(List<GameObject> childs) {
        this.childs = childs;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public String getTextureName() {
        return textureName;
    }

    public void setTextureName(String textureName) {
        this.textureName = textureName;
    }
}

