import org.joml.Matrix4f;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL13;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import java.util.List;
import java.util.Map;

import static org.lwjgl.glfw.GLFW.*;

public class Renderer {

    private static final float FOV = 70;
    private static final float NEAR_PLANE = 0.1f;
    private static final float FAR_PLANE = 1000;
    private Matrix4f projectionMatrix;
    private StaticShader shader;

    public Renderer(StaticShader shader){
        this.shader = shader;
        GL11.glEnable(GL11.GL_CULL_FACE);
        GL11.glCullFace(GL11.GL_BACK);
        createProjectionMatrix();
        shader.start();
        shader.loadProjectionMatrix(projectionMatrix);
        shader.stop();
    }

    public void render(Map<Model, List<GameObject>> gameObjects){
        for(Model model: gameObjects.keySet()){
            prepareTexturedModel(model);
            List<GameObject> batch = gameObjects.get(model);
            for(GameObject gameObject :batch){
                prepareInstance((gameObject));
                GL11.glDrawElements(GL11.GL_TRIANGLES,model.getMesh().getVertexCount(),GL11.GL_UNSIGNED_INT,0);
            }
            unbindTexturedModel();
        }
    }

    private void prepareTexturedModel(Model model){
        Mesh mesh = model.getMesh();
        GL30.glBindVertexArray(mesh.getVaoID());
        GL20.glEnableVertexAttribArray(0);
        GL20.glEnableVertexAttribArray(1);
        GL20.glEnableVertexAttribArray(2);
        MeshTexture texture = model.getTexture();
        shader.loadShineVariables(texture.getShineDamper(),texture.getReflectivity());
        /*GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MAG_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_MIN_FILTER, GL11.GL_LINEAR);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_S, GL11.GL_REPEAT);
        GL11.glTexParameteri(GL11.GL_TEXTURE_2D, GL11.GL_TEXTURE_WRAP_T, GL11.GL_REPEAT);*/
        GL13.glActiveTexture(GL13.GL_TEXTURE0);
        GL11.glBindTexture(GL11.GL_TEXTURE_2D, model.getTexture().getID());
    }

    private void unbindTexturedModel(){
        GL20.glDisableVertexAttribArray(0);
        GL20.glDisableVertexAttribArray(1);
        GL20.glDisableVertexAttribArray(2);
        GL30.glBindVertexArray(0);
    }

    private void prepareInstance(GameObject gameObject){
        Matrix4f transformationMatrix = Maths.createTransformationMatrix(gameObject.getTransform().getPosition(), gameObject.getTransform().getRotation(), gameObject.getTransform().getScale());
        shader.loadTransformationMatrix(transformationMatrix);
    }

    protected void createProjectionMatrix(){
        glfwGetWindowSize(GameDisplay.getID(),GameDisplay.getWIDTH(),GameDisplay.getHEIGHT());
        int width = GameDisplay.getWIDTH()[0];
        int height = GameDisplay.getHEIGHT()[0];
        float aspectRatio = (float)width/height;
        projectionMatrix = new Matrix4f();
        projectionMatrix.identity();
        projectionMatrix.perspective(FOV, aspectRatio, NEAR_PLANE, FAR_PLANE);
    }
}
