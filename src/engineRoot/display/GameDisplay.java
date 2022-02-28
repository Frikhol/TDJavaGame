import org.lwjgl.glfw.GLFWErrorCallback;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;

import static org.lwjgl.glfw.Callbacks.glfwFreeCallbacks;
import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.GLFW_RESIZABLE;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.system.MemoryUtil.NULL;

public class GameDisplay {

    private static long displayID;
    private static int WIDTH = 1280; //make changeable
    private static int HEIGHT = 720; //make changeable

    public static long getID(){
        return displayID;
    }

    public static void create() {
        GLFWErrorCallback.createPrint(System.err).set();
        if(!glfwInit()){
            throw new IllegalStateException("Unable to initialize GLFW");
        }
        glfwDefaultWindowHints(); // optional, the current window hints are already the default
        glfwWindowHint(GLFW_CONTEXT_VERSION_MAJOR, 3);
        glfwWindowHint(GLFW_CONTEXT_VERSION_MINOR, 2);
        glfwWindowHint(GLFW_OPENGL_PROFILE, GLFW_OPENGL_CORE_PROFILE);
        glfwWindowHint(GLFW_OPENGL_FORWARD_COMPAT, GLFW_TRUE);
        glfwWindowHint(GLFW_VISIBLE, GL_FALSE); // the window will stay hidden after creation
        glfwWindowHint(GLFW_RESIZABLE, GL_TRUE); // the window will be resizable
        displayID = glfwCreateWindow(WIDTH, HEIGHT, "My first display!", NULL, NULL);
        if (displayID == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(displayID,(vidMode.width() - WIDTH) / 2,(vidMode.height() - HEIGHT) / 2);
        glfwMakeContextCurrent(displayID);
        glfwSwapInterval(1);
        glfwShowWindow(displayID);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
    }

    public static void close() {
        glfwFreeCallbacks(displayID);
        glfwDestroyWindow(displayID);
    }
}
