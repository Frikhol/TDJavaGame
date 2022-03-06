import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private Transform transform = new Transform(
            new Vector3f(0,0,0),
            new Vector3f(0,0,0)
    );

    private float pitch;
    private float yaw;
    private float roll;

    public Camera(){}

    public Camera(Transform transform, float pitch, float yaw, float roll) {
        this.transform = transform;
        this.pitch = pitch;
        this.yaw = yaw;
        this.roll = roll;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public float getPitch() {
        return pitch;
    }

    public float getYaw() {
        return yaw;
    }

    public float getRoll() {
        return roll;
    }
}
