import org.joml.Vector3f;

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
        GameEngine.start(); //Necessary Game Engine prepare
        sceneList.add(new Scene(new Light(new Vector3f(1000,1000,2000),new Vector3f(1,1,1)),new Camera()));
        sceneList.get(0).loadGameObject("dragon","white");
        sceneList.get(0).getGameObjectList().get(0).setTransform(new Transform(new Vector3f(0,-5,-5),new Vector3f(0,0,0)));
        sceneList.get(0).setCurrentGUI(new GUI());
        GameEngine.setCurrentScene(sceneList.get(0));
        GameEngine.getCurrentScene().setKeyList(new TestControlCamera());
        GameEngine.getCurrentScene().getCurrentGUI().add(new GUIPane((GameDisplay.getWIDTH()[0]-(GameDisplay.getWIDTH()[0]/16)),0,(GameDisplay.getWIDTH()[0]/16),(GameDisplay.getHEIGHT()[0]/16)));


        //Main game loop
        while(!glfwWindowShouldClose(GameDisplay.getID())){ //while not closed
            update(); // USE THIS FUNCTION FOR GAME LOGIC UPDATE TO
            GameEngine.loop(); //Necessary update frames
        }

        GameEngine.stop(); //Necessary game engine endings
        game.close();
    }

    private void update(){
        sceneList.get(0).getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));

    }


    private void close(){

    }

}
