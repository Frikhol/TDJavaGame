import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL11.GL_FRONT_AND_BACK;

public class InputHandler{

    private static int polygonMode = GL_FILL;

    protected static GLFWKeyCallback keyCallback = new GLFWKeyCallback(){
        public void invoke(long window, int key, int scancode, int action, int mods) {
            KeyList keyList = GameEngine.getCurrentScene().getKeyList();
            if(keyList == null)
                return;
            switch (action){
                case 0:
                    keyList.release(key, mods);
                    break;
                case 1:
                    keyList.down(key,mods);
                    break;
                case 2:
                    keyList.hold(key,mods);
                    break;
            }
        }
    };

    public static void keyBindings(){
            polygonMode = polygonMode == GL_FILL? GL_LINE: GL_FILL;
            glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
    }

}

