import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;

public class KeyboardInput  extends GLFWKeyCallback {

    public static int[] keys = new int[65536];
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action;
    }

    public static int isKeyDown(int keycode){
        return keys[keycode];
    }

}
