import org.lwjgl.glfw.GLFWKeyCallback;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;

public class KeyboardInput  extends GLFWKeyCallback {

    public static int[] keys = new int[65536];
    private static int polygonMode = GL_FILL;
    @Override
    public void invoke(long window, int key, int scancode, int action, int mods) {
        keys[key] = action;
        keyBindings();
    }

    public static int isKeyDown(int keycode){
        return keys[keycode];
    }

    public static void keyBindings(){
        if (isKeyDown(GLFW_KEY_F1)!=GLFW_RELEASE) {
            if(polygonMode == GL_FILL) polygonMode = GL_LINE;
            else polygonMode = GL_FILL;
            glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
        }
    }

}
