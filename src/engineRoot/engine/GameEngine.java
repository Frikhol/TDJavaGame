import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.opengl.GL11.*;

public class GameEngine {


    private static Loader loader;
    private static MasterRenderer renderer;
    private static Scene scene;

    public static Loader getLoader() {
        return loader;
    }

    public static void setScene(Scene scene) {
        GameEngine.scene = scene;
    }

    public static void start(){
        GameDisplay.create();
        loader = new Loader();
        renderer = new MasterRenderer();
        GLFWKeyCallback keyCallback  = new KeyboardInput();
        glfwSetKeyCallback(GameDisplay.getID(), keyCallback);
    }

    public static void loop() {
        //========================================================== DON'T DELETE ====================================================================
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.74902f,  0.847059f, 0.847059f, 0.0f); //background's color
        //========================================================== -----//----- ====================================================================

        processGameObjects();
        renderer.render(scene.getLight(),scene.getCamera());
        glfwSwapBuffers(GameDisplay.getID()); // Don't delete
    }

    private static void processGameObjects(){
        for(GameObject object: scene.getGameObjectList()){
            renderer.processEntity(object);
        }
    }

    public static void stop(){
        renderer.cleanUp();
        loader.cleanUP();
        GameDisplay.close();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
