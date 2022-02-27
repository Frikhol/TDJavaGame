import org.joml.Vector3f;

class Transform {
    private Vector3f position;
    private Vector3f rotation;
    private float scale;
    Transform(Vector3f position, Vector3f rotation) {
        this.position = position;
        this.rotation = rotation;
        this.scale = 1;
    }

    void rotate(Vector3f rotation){
        this.rotation.x += rotation.x;
        this.rotation.y += rotation.y;
        this.rotation.z += rotation.z;
    }

    void rotateX(float x){ this.rotation.x += x;}

    void rotateY(float y){ this.rotation.y += y;}

    void rotateZ(float z){ this.rotation.z += z;}

    void translate(Vector3f translation){
        this.position.x += translation.x;
        this.position.y += translation.y;
        this.position.z += translation.z;
    }

    void translateX(float x){ this.position.x += x;}

    void translateY(float y){ this.position.y += y;}

    void translateZ(float z){ this.position.z += z;}

    void scale(float scale){ this.scale *= scale;}

    Vector3f getPosition() {
        return position;
    }

    void setPosition(Vector3f position) {
        this.position = position;
    }

    Vector3f getRotation() {
        return rotation;
    }

    void setRotation(Vector3f rotation) {
        this.rotation = rotation;
    }

    float getScale() {
        return scale;
    }

    void setScale(float scale) {
        this.scale = scale;
    }
}
