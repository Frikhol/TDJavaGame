import org.joml.Vector2f;
import org.joml.Vector3f;

import javax.swing.*;
import java.io.File;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game extends GameEngine implements Runnable{
    private static IntroMenu intro;

    public static void main(String[] args){

        intro = new IntroMenu();
    }

    public void run(){
        start();
        loadScene(intro.getSceneFile());
        getCurrentScene().setKeyList(new TestControls());
        FontType font = new FontType(Loader.loadTexture("calibri.png").getId(),new File("res/calibri.fnt"));
        GUIText text = new GUIText("FPS: "+ GameDisplay.getFPS(),1,font,new Vector2f((float)(GameDisplay.getWIDTH()[0]-(GameDisplay.getWIDTH()[0]/16))/GameDisplay.getWIDTH()[0],(float)(GameDisplay.getHEIGHT()[0]/64)/GameDisplay.getHEIGHT()[0]),(float)(GameDisplay.getWIDTH()[0]/16)/GameDisplay.getWIDTH()[0],true);

        //Main game loop
        while(!glfwWindowShouldClose(GameDisplay.getID())){
            text = text.updateText("FPS: "+GameDisplay.getFPS());
            text.setColour(1,0.9f,0);
            update();
            loop();
        }
        close();
    }

    private  void update(){
        getCurrentScene().getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));
    }

    private void saveAction(){
        int status = intro.fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            String saveFile = intro.fileChooser.getSelectedFile().getName();
            if (!saveFile.contains(".json"))
                saveScene("res/saves/" + saveFile + ".json");
            else
                saveScene("res/saves/" + saveFile);
            JOptionPane.showMessageDialog(intro, "Saving success");
        }
    }

    private  void close(){
        saveAction();
        stop();
        intro.setVisible(true);
    }

}
