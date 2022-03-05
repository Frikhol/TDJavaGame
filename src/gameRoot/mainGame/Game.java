import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game {
    private static Game game = new Game();
    private static Scene scene;

    public static void main(String[] args){
        game.run();
    }

    private void run(){
        GameEngine.start();
        scene = Scene.loadScene("Main");
        GameEngine.setCurrentScene(scene);
        GameEngine.getCurrentScene().setKeyList(new TestControls());
        FontType font = new FontType(Loader.loadTexture("calibri.png").getId(),new File("res/calibri.fnt"));
        GUIText text = new GUIText("FPS: "+ GameDisplay.getFPS(),1,font,new Vector2f((float)(GameDisplay.getWIDTH()[0]-(GameDisplay.getWIDTH()[0]/16))/GameDisplay.getWIDTH()[0],(float)(GameDisplay.getHEIGHT()[0]/64)/GameDisplay.getHEIGHT()[0]),(float)(GameDisplay.getWIDTH()[0]/16)/GameDisplay.getWIDTH()[0],true);

        //Main game loop
        while(!glfwWindowShouldClose(GameDisplay.getID())){
            text = text.updateText("FPS: "+GameDisplay.getFPS());
            text.setColour(1,0.9f,0);
            update();
            GameEngine.loop();
        }

        GameEngine.stop();
        game.close();
    }

    private void update(){
        scene.getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));

    }


    private void close(){
        scene.saveScene();
    }

}
