import IntroMenu.IntroMenu;
import org.joml.Vector2f;
import org.joml.Vector3f;

import javax.swing.*;
import java.awt.event.WindowEvent;
import java.io.File;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game {
    private static Game game = new Game();
    private static Scene scene = new Scene();
    private static IntroMenu intro;

    public static void main(String[] args){
        intro = new IntroMenu();
        game.menu();
    }

    private void menu(){
        intro.getPlayButton().addActionListener(e->{
            if(!intro.loaded)
                JOptionPane.showMessageDialog(intro, "Must to load scene before!");
            else {
                intro.played = true;
                intro.setVisible(false);
                game.run();
            }
        });
        //intro.getSaveButton().addActionListener(e->saveAction());
        intro.getExitButton().addActionListener(e->{
            intro.dispatchEvent(new WindowEvent(intro, WindowEvent.WINDOW_CLOSING));
            System.exit(0);
        });
    }

    private void saveAction() {
        JFileChooser fileChooser = intro.getFileChooser();
        fileChooser.addActionListener(ev -> {});
        int status = fileChooser.showSaveDialog(null);
        if (status == JFileChooser.APPROVE_OPTION) {
            String saveFile = fileChooser.getSelectedFile().getName();
            if (!saveFile.contains(".json"))
                scene.saveScene("res/saves/" + saveFile + ".json");
            else
                scene.saveScene("res/saves/" + saveFile);
            JOptionPane.showMessageDialog(intro, "Saving success");
        }
    }

    private void run(){
        GameEngine.start();
        scene = scene.loadScene(intro.getSceneFile());
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
        game.close();
    }

    private void update(){
        scene.getGameObjectList().get(0).rotate(new Vector3f(0,0.5f,0));
    }

    private void close(){
        saveAction();
        GameEngine.stop();
        intro.setVisible(true);
    }


}
