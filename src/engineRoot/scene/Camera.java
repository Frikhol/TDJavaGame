import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class Camera {

    private Vector3f position = new Vector3f(0,0,0);
    private float pitch;
    private float yaw;
    private float roll;

    public Camera(){}

    public void move(){
        if(KeyboardInput.isKeyDown(GLFW_KEY_W)!=GLFW_RELEASE){
            position.z-=0.1f;
        }
        if(KeyboardInput.isKeyDown(GLFW_KEY_D)!=GLFW_RELEASE){
            position.x+=0.1f;
        }
        if(KeyboardInput.isKeyDown(GLFW_KEY_A)!=GLFW_RELEASE){
            position.x-=0.1f;
        }
        if(KeyboardInput.isKeyDown(GLFW_KEY_S)!=GLFW_RELEASE){
            position.z+=0.1f;
        }
    }

    public Vector3f getPosition() {
        return position;
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
