import org.joml.Vector2f;
import org.joml.Vector3f;

import javax.swing.*;
import java.io.File;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game implements Runnable{
    private static Scene scene;
    private static IntroMenu intro;

    public static void main(String[] args){
        intro = new IntroMenu();
    }

    public void run(){
        GameEngine.start();
        GameEngine.loadScene(intro.getSceneFile());
        scene = GameEngine.getCurrentScene();
        GameEngine.getCurrentScene().setKeyList(new TestControls());
        FontType font = new FontType(Loader.loadTexture("calibri.png").getId(),new File("res/calibri.fnt"));
        GUIText text = new GUIText("FPS: "+ GameDisplay.getFPS(),1,font,new Vector2f((float)(GameDisplay.getWIDTH()[0]-(GameDisplay.getWIDTH()[0]/16))/GameDisplay.getWIDTH()[0],(float)(GameDisplay.getHEIGHT()[0]/64)/GameDisplay.getHEIGHT()[0]),(float)(GameDisplay.getWIDTH()[0]/16)/GameDisplay.getWIDTH()[0],true);
        System.out.println(GameDisplay.getID());
        //Main game loop
        while(!glfwWindowShouldClose(GameDisplay.getID())){
            text = text.updateText("FPS: "+GameDisplay.getFPS());
            text.setColour(1,0.9f,0);
            update();
            GameEngine.loop();
        }
        close();
    }

    private  void update(){
        scene.getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));
    }

    private void saveAction(){
        int status = intro.fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            String saveFile = intro.fileChooser.getSelectedFile().getName();
            if (!saveFile.contains(".json"))
                GameEngine.saveScene("res/saves/" + saveFile + ".json");
            else
                GameEngine.saveScene("res/saves/" + saveFile);
            JOptionPane.showMessageDialog(intro, "Saving success");
        }
    }

    private  void close(){
        saveAction();
        GameEngine.stop();
        intro.setVisible(true);
    }

}
