import java.awt.event.KeyEvent;

public class TestControlCamera implements KeyList {
    @Override
    public void release(int key, int mods) {

    }

    @Override
    public void down(int key, int mods) {
        f(key,mods);
    }

    @Override
    public void hold(int key, int mods) {
        f(key,mods);
    }
    void f(int key, int mods){
        if(key == KeyCode.GLFW_KEY_F1)
            KeyboardInput.keyBindings();
        Camera camera = GameEngine.getCurrentScene().getCamera();
        if(key == KeyCode.GLFW_KEY_S)
            camera.getTransform().translateZ(.3f);
        if(key == KeyCode.GLFW_KEY_W)
            camera.getTransform().translateZ(-.3f);
        if(key == KeyCode.GLFW_KEY_A)
            camera.getTransform().translateX(-.3f);
        if(key == KeyCode.GLFW_KEY_D)
            camera.getTransform().translateX(.3f);
    }
}