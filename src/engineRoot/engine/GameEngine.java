import com.fasterxml.jackson.databind.ObjectMapper;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import java.io.File;
import java.io.IOException;

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
        TextMaster.init(loader);
        glfwSetKeyCallback(GameDisplay.getID(), InputHandler.keyCallback);
        checkWindowResize();
        glfwSetTime(0);
    }

    public static void loop() {
        GameDisplay.setFPSCup(144);
        //========================================================== DON'T DELETE ====================================================================
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.74902f,  0.847059f, 0.847059f, 0.0f); //background's color
        //========================================================== -----//----- ====================================================================
        processGameObjects();
        GameDisplay.countFPS();
        renderer.render(scene.getLight(),scene.getCamera());
        guiRenderer.render(scene.getCurrentGUI().getGUIList());
        TextMaster.render();
        glfwSwapBuffers(GameDisplay.getID()); // Don't delete
        glfwPollEvents();
        InputHandler.getInputs();
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

    public static void saveScene(String saveFile){
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper.writeValue(new File(saveFile), scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadScene(String loadFile){
        ObjectMapper mapper = new ObjectMapper();
        try {
            scene = mapper.readValue(new File(loadFile), Scene.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void stop(){
        TextMaster.cleanUp();
        guiRenderer.cleanUp();
        renderer.cleanUp();
        loader.cleanUP();
        GameDisplay.close();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
