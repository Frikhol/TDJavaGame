import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.joml.Vector3f;

class Transform {
    @JsonIgnoreProperties({"finite"})
    private Vector3f position;
    @JsonIgnoreProperties({"finite"})
    private Vector3f rotation;
    private float scale;

    public Transform() {
    }

    public Transform(Vector3f position, Vector3f rotation, float scale) {
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Transform(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
        this.scale = 1;
    }

    public void rotate(Vector3f rotation){
        this.rotation.x += rotation.x;
        this.rotation.y += rotation.y;
        this.rotation.z += rotation.z;
    }

    public void rotateX(float x){ this.rotation.x += x;}

    public void rotateY(float y){ this.rotation.y += y;}

    public void rotateZ(float z){ this.rotation.z += z;}

    public void translate(Vector3f translation){
        this.position.x += translation.x;
        this.position.y += translation.y;
        this.position.z += translation.z;
    }

    public void translateX(float x){ this.position.x += x;}

    public void translateY(float y){ this.position.y += y;}

    public void translateZ(float z){ this.position.z += z;}

    public void scale(float scale){ this.scale *= scale;}

    public Vector3f getPosition() {
        return position;
    }

    public void setPosition(Vector3f position) {
        this.position = position;
    }

    public Vector3f getRotation() {
        return rotation;
    }

    public void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }


}
