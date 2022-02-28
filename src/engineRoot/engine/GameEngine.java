import org.joml.Vector3f;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.glfw.GLFW.glfwSetKeyCallback;
import static org.lwjgl.opengl.GL11.*;

public class GameEngine {

    private static long polygonMode = GL_FILL;

    private static Loader loader;
    private static StaticShader shader;
    private static Renderer renderer;
    private static Camera camera;
    private static Entity entity;

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
        glfwSetKeyCallback(GameDisplay.getID(), (window, key, scancode, action, mods) -> {  //Changing polygon mode
            if (key == GLFW_KEY_F1 && action == GLFW_RELEASE) {
                if(polygonMode == GL_FILL)
                    glPolygonMode( GL_FRONT_AND_BACK, GL_LINE );
                else glPolygonMode( GL_FRONT_AND_BACK, GL_FILL );
            }
        });

        RawModel model = OBJLoader.loadObjModel("stall",loader);
        ModelTexture texture = new ModelTexture(Loader.loadTexture("stallTexture.png").getId());
        TexturedModel texturedModel = new TexturedModel(model,texture);
        entity = new Entity(texturedModel,new Vector3f(0,-5,-20),0,0,0,1);

    }

    public static void loop() {
        //========================================================== DON'T DELETE ====================================================================
        glfwPollEvents();
        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
        glClearColor(0.0f, 0.0f, 0.0f, 0.0f); //background's color
        //========================================================== -----//----- ====================================================================
        camera.move();
        shader.start();
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
