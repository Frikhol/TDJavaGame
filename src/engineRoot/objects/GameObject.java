import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

class GameObject {

    private GameObject parent;
    private List<GameObject> childs = new ArrayList<GameObject>();

    private Mesh mesh;
    private Transform transform;

    GameObject(Mesh mesh) {
        this.mesh = mesh;
        this.transform = new Transform(new Vector3f(0,0,0),new Vector3f(0,0,0));
    }

    void addChild(GameObject gameObject){
        childs.add(gameObject);
    }

    void rotate(Vector3f rotation){
        transform.rotate(rotation);
        for(GameObject child: childs)
            child.getTransform().rotate(rotation);
    }

    void rotateX(float x){
        transform.rotateX(x);
        for(GameObject child: childs)
            child.getTransform().rotateX(x);
    }

    void rotateY(float y){
        transform.rotateX(y);
        for(GameObject child: childs)
            child.getTransform().rotateY(y);
    }

    void rotateZ(float z){
        transform.rotateX(z);
        for(GameObject child: childs)
            child.getTransform().rotateZ(z);
    }

    void translate(Vector3f translation){
        transform.translate(translation);
        for(GameObject child: childs)
            child.getTransform().translate(translation);
    }

    void translateX(float x){
        transform.translateX(x);
        for(GameObject child: childs)
            child.getTransform().translateX(x);
    }

    void translateY(float y){
        transform.translateY(y);
        for(GameObject child: childs)
            child.getTransform().translateY(y);
    }

    void translateZ(float z){
        transform.translateZ(z);
        for(GameObject child: childs)
            child.getTransform().translateZ(z);
    }

    void scale(float scale){
        transform.scale(scale);
        for(GameObject child: childs)
            child.getTransform().scale(scale);
    }

    Transform getTransform() {
        return transform;
    }

    void setTransform(Transform transform) {
        this.transform = transform;
    }

    Mesh getMesh() {
        return mesh;
    }

    void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}

