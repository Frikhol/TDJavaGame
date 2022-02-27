import org.joml.Vector3f;
import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.opengl.GL11.*;

public class GameEngine {

    private static int polygonMode = GL_FILL;



    private static Loader loader;
    private static StaticShader shader;
    private static Renderer renderer;
    private static Camera camera;
    private static Entity entity;
    private static Light light;

    public static Camera getCamera(){return camera;}
    public static Entity getEntity(){return entity;}
    public static void setCamera(Camera camera) {GameEngine.camera = camera;}
    public static void setEntity(Entity entity) {GameEngine.entity = entity;}

    public static void start(){
        GameDisplay.create();
        loader = new Loader();
        shader = new StaticShader();
        renderer = new Renderer(shader);
        camera = new Camera();
        GLFWKeyCallback keyCallback;
        glfwSetKeyCallback(GameDisplay.getID(), keyCallback = new KeyboardInput());
        RawModel model = OBJLoader.loadObjModel("stall",loader);
        ModelTexture texture = new ModelTexture(Loader.loadTexture("stallTexture.png").getId());
        TexturedModel texturedModel = new TexturedModel(model,texture);
        //glfwSetKeyCallback(GameDisplay.getID(), keyCallback = new KeyboardInput());
        entity = new Entity(texturedModel,new Vector3f(0,-5,-20),0,0,0,1);
        light = new Light(new Vector3f(0,0,0),new Vector3f(1,1,1));
    }

    public static void loop() {
        //========================================================== DON'T DELETE ====================================================================
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //background's color
        //========================================================== -----//----- ====================================================================
        if (KeyboardInput.isKeyDown(GLFW_KEY_F1)!=GLFW_RELEASE) {
            if(polygonMode == GL_FILL) polygonMode = GL_LINE;
            else polygonMode = GL_FILL;
            glPolygonMode(GL_FRONT_AND_BACK, polygonMode);
        }
        camera.move();
        shader.start();
        shader.loadLight(light);
        shader.loadViewMatrix(camera);
        renderer.render(entity,shader);
        shader.stop();
        glfwSwapBuffers(GameDisplay.getID()); // Don't delete
    }

    public static void stop(){
        shader.cleanUp();
        loader.cleanUP();
        GameDisplay.close();
        glfwTerminate();
        glfwSetErrorCallback(null).free();
    }
}
