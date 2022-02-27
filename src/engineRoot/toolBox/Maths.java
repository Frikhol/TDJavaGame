import org.joml.Matrix4f;
import org.joml.Vector3f;

public class Maths {
    public static Matrix4f createTransformationMatrix(Vector3f translation, float rx, float ry, float rz, float scale){
        Matrix4f matrix = new Matrix4f();
        matrix.identity();
        matrix.translate(translation);
        matrix.rotateX((float) Math.toRadians(rx));
        matrix.rotateY((float) Math.toRadians(ry));
        matrix.rotateZ((float) Math.toRadians(rz));
        matrix.scale(scale);
        return matrix;
    }
    public static Matrix4f createViewMatrix(Camera camera){
        Matrix4f viewMatrix = new Matrix4f();
        viewMatrix.identity();
        viewMatrix.rotateX((float) Math.toRadians(camera.getPitch()));
        viewMatrix.rotateY((float) Math.toRadians(camera.getYaw()));
        Vector3f cameraPos = camera.getPosition();
        Vector3f negativeCameraPos = new Vector3f(-cameraPos.x,-cameraPos.y,-cameraPos.z);
        viewMatrix.translate(negativeCameraPos);
        return viewMatrix;
    }
}