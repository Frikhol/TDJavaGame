import org.joml.Vector2f;
import org.joml.Vector3f;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game {
    private static Game game = new Game();
    private static List<Scene> sceneList = new ArrayList<Scene>();

    public static void main(String[] args){
        game.run();
    }

    private void run(){
        GameEngine.start();
        sceneList.add(new Scene(new Light(new Vector3f(1000,1000,2000),new Vector3f(1,1,1)),new Camera()));
        sceneList.get(0).loadGameObject("dragon","white");
        sceneList.get(0).getGameObjectList().get(0).setTransform(new Transform(new Vector3f(0,-5,-25),new Vector3f(0,0,0)));
        sceneList.get(0).setCurrentGUI(new GUI());
        GameEngine.setCurrentScene(sceneList.get(0));
        GameEngine.getCurrentScene().setKeyList(new TestControls());
        GameEngine.getCurrentScene().getCurrentGUI().add(new GUIPane((GameDisplay.getWIDTH()[0]-(GameDisplay.getWIDTH()[0]/16)),0,(GameDisplay.getWIDTH()[0]/16),(GameDisplay.getHEIGHT()[0]/16)));
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
        sceneList.get(0).getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));

    }


    private void close(){

    }

}
