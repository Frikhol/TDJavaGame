import org.joml.Vector3f;

import java.util.ArrayList;
import java.util.List;

public class GameObject {

    //private GameObject parent = new GameObject(new Mesh()); ДОДЕЛАТЬ
    private List<GameObject> child = new ArrayList<GameObject>();

    private Mesh mesh;
    private Transform transform;

    public GameObject(Mesh mesh) {
        this.mesh = mesh;
        this.transform = new Transform(new Vector3f(0,0,0),new Vector3f(0,0,0),1);
    }

    public void rotate(Vector3f rotation){
        transform.rotate(rotation);
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }
}

