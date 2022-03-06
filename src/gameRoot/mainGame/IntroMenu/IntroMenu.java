package IntroMenu;

import javax.swing.*;
import java.awt.*;

public class IntroMenu extends JFrame implements IntroMenuI {
    private String sceneFile = "";
    public boolean loaded = false;
    public boolean played = false;
    public IntroMenu(){
        super("IntroMenu");
        setSize(512,256);
        setLocationRelativeTo(null);
        setResizable(false);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        main.setLayout(new GridLayout(4,1));
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(filter);
        main.add(playButton);
        main.add(currentSceneLabel);
        main.add(loadButton);
        //main.add(saveButton);
        main.add(exitButton);
        loadButton.addActionListener(e->loadAction());
        getContentPane().add(main);
        setVisible(true);
    }

    private void loadAction(){
        //if(!loaded){
            fileChooser.addActionListener(e -> {});
            int status = fileChooser.showOpenDialog(this);
            if (status == JFileChooser.APPROVE_OPTION) {
                sceneFile = fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().indexOf("res\\"));
                currentSceneLabel.setText("Current scene: " + sceneFile.substring(sceneFile.lastIndexOf("\\")+1,sceneFile.indexOf(".json")));
                loaded = true;
            } else if (status == JFileChooser.CANCEL_OPTION) {
                JOptionPane.showMessageDialog(this, "Choose file to load");
            }
        //}
        //else JOptionPane.showMessageDialog(this, "Already loaded!");
    }

    public String getSceneFile() {
        return sceneFile;
    }
}
