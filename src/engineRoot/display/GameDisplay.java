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
    private static int[] WIDTH = new int[1]; //make changeable
    private static int[] HEIGHT = new int[1]; //make changeable
    private static int FPS = 144;
    private static int fps;

    public static void setFps(int fps) {
        GameDisplay.fps = fps;
    }

    public static int getFps() {
        return fps;
    }

    public static int getFPS() {
        return FPS;
    }

    public static void setFPS(int FPS) {
        GameDisplay.FPS = FPS;
    }

    public static long getID(){
        return displayID;
    }

    public static int[] getWIDTH() {
        return WIDTH;
    }

    public static int[] getHEIGHT() {
        return HEIGHT;
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
        WIDTH[0] = 1280;
        HEIGHT[0] = 720;
        displayID = glfwCreateWindow(WIDTH[0], HEIGHT[0], "TDJavaGame", NULL, NULL);
        if (displayID == NULL) {
            throw new RuntimeException("Failed to create the GLFW window");
        }
        GLFWVidMode vidMode = glfwGetVideoMode(glfwGetPrimaryMonitor());
        glfwSetWindowPos(displayID,(vidMode.width() - WIDTH[0]) / 2,(vidMode.height() - HEIGHT[0]) / 2);
        glfwMakeContextCurrent(displayID);
        glfwSwapInterval(0);
        glfwShowWindow(displayID);
        GL.createCapabilities();
        glEnable(GL_DEPTH_TEST);
    }

    public static void countFPS(){
        int FPS = GameDisplay.getFps();
        if( glfwGetTime()>=1){
            GameDisplay.setFPS(FPS);
            GameDisplay.setFps(0);
            glfwSetTime(0);
        }
        GameDisplay.setFps(GameDisplay.getFps()+1);
    }

    public static void setFPSCup(int cup){
        double lastTime = glfwGetTime();
        while(glfwGetTime() < lastTime+1.0/cup){}
    }

    public static float deltaTime(){
        return 1/(float)FPS;
    }

    public static void close() {
        glfwFreeCallbacks(displayID);
        glfwDestroyWindow(displayID);
    }
}
