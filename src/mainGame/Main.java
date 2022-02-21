import org.joml.Vector3f;
import org.lwjgl.glfw.GLFW;
import org.lwjgl.glfw.GLFWKeyCallback;
import org.lwjgl.glfw.GLFWWindowSizeCallback;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11.*;


public class Main {

    Loader loader = new Loader();
    StaticShader shader = new StaticShader();
    Renderer renderer = new Renderer(shader);

    /*float[] vertices = {
            -0.5f, 0.5f, 0, //V0
            -0.5f, -0.5f, 0, //V1
            0.5f, -0.5f, 0, //V2
            0.5f, 0.5f, 0, //V3
    };

    int[] indices = {
            0,1,2,
            2,3,0,
    };

    float[] textureCoords = {
            0,0,
            0,1,
            1,1,
            1,0,
    };*/

    float[] vertices = {
            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,0.5f,-0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,-0.5f,0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            0.5f,0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,0.5f,-0.5f,
            -0.5f,-0.5f,-0.5f,
            -0.5f,-0.5f,0.5f,
            -0.5f,0.5f,0.5f,

            -0.5f,0.5f,0.5f,
            -0.5f,0.5f,-0.5f,
            0.5f,0.5f,-0.5f,
            0.5f,0.5f,0.5f,

            -0.5f,-0.5f,0.5f,
            -0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,-0.5f,
            0.5f,-0.5f,0.5f

    };

    float[] textureCoords = {

            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0,
            0,0,
            0,1,
            1,1,
            1,0


    };

    int[] indices = {
            0,1,3,
            3,1,2,
            4,5,7,
            7,5,6,
            8,9,11,
            11,9,10,
            12,13,15,
            15,13,14,
            16,17,19,
            19,17,18,
            20,21,23,
            23,21,22

    };

    public void run(){
        try{
            GLFWWindowSizeCallback sizeCallback = new GLFWWindowSizeCallback() { public void invoke(long window, int w, int h) { glfwSetWindowSize(window, w, h); renderer.createProjectionMatrix(); System.out.println("resized"); } };
            org.lwjgl.glfw.GLFW.glfwSetWindowSizeCallback(DisplayManager.getWindowID(), sizeCallback);
            RawModel model = loader.loadToVAO(vertices,indices,textureCoords);
            ModelTexture texture = new ModelTexture(Loader.loadTexture("image.png").getId());
            TexturedModel texturedModel = new TexturedModel(model,texture);
            Entity entity = new Entity(texturedModel,new Vector3f(0,0,-5),45,45,0,1);
            Camera camera = new Camera();
            while (!glfwWindowShouldClose(DisplayManager.getWindowID())) {
                glfwPollEvents();
                entity.increaseRotation(0.5f,0.5f,0);
                camera.move();
                glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
                glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
                shader.start();
                shader.loadViewMatrix(camera);
                renderer.render(entity,shader);
                shader.stop();
                glfwSwapBuffers(DisplayManager.getWindowID());
            }
        } finally {
            shader.cleanUp();
            loader.cleanUP();
            DisplayManager.closeDisplay();
            glfwTerminate();
            glfwSetErrorCallback(null).free();
        }
    }

    public static void main(String[] args){
        DisplayManager.createDisplay();

        new Main().run();
    }
}
