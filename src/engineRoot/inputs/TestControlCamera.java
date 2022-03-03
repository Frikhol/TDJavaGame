public class TestControlCamera implements KeyList {
    @Override
    public void down(int key, int mods) {
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

    @Override
    public void pressed(int key, int mods) {
        if(key == KeyCode.GLFW_KEY_F1)
            GUI.changePolyMode();
    }

    @Override
    public void released(int key, int mods) {

    }

}