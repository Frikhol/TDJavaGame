import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.opengl.GL11.*;

public class GameEngine {


    private static Loader loader;
    private static MasterRenderer renderer;
    private static GUIRenderer guiRenderer;
    private static Scene scene;


    public static Scene getCurrentScene() {
        return scene;
    }

    public static Loader getLoader() {
        return loader;
    }

    public static void setCurrentScene(Scene scene) {
        GameEngine.scene = scene;
    }

    public static void start(){
        GameDisplay.create();
        loader = new Loader();
        renderer = new MasterRenderer();
        guiRenderer = new GUIRenderer(loader);
        GLFWKeyCallback keyCallback  = new KeyboardInput();
        glfwSetKeyCallback(GameDisplay.getID(), keyCallback);
        checkWindowResize();
        org.lwjgl.glfw.GLFW.glfwSetTime(0);
    }

    public static void loop() {
        //========================================================== DON'T DELETE ====================================================================
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.74902f,  0.847059f, 0.847059f, 0.0f); //background's color
        //========================================================== -----//----- ====================================================================
        GUI.countFPS();
        processGameObjects();
        renderer.render(scene.getLight(),scene.getCamera());
        guiRenderer.render(scene.getCurrentGUI().getGUIList());
        glfwSwapBuffers(GameDisplay.getID()); // Don't delete
    }

    private static void processGameObjects(){
        for(GameObject object: scene.getGameObjectList()){
            renderer.processEntity(object);
        }
    }

    private static void checkWindowResize(){
        GLFWWindowSizeCallback sizeCallback = new GLFWWindowSizeCallback() {
            public void invoke(long window, int w, int h) {
                glViewport(0,0,w,h);
                renderer.getRenderer().createProjectionMatrix();
            }
        };
        glfwSetWindowSizeCallback(GameDisplay.getID(), sizeCallback);
    }

    public static void stop(){
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUP();
        GameDisplay.close();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
