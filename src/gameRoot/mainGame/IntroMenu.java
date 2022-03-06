import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;

public class IntroMenu extends JFrame implements IntroMenuI {
    private String sceneFile = "";
    public boolean loaded = false;
    public boolean played = false;
    private Game game= new Game();
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
        main.add(exitButton);
        loadButton.addActionListener(e->loadAction());
        exitButton.addActionListener(e->exitAction());
        playButton.addActionListener(e->playAction());
        fileChooser.addActionListener(e -> {});
        getContentPane().add(main);
        setVisible(true);
    }

    private void loadAction(){
        int status = fileChooser.showOpenDialog(this);
        if (status == JFileChooser.APPROVE_OPTION) {
            sceneFile = fileChooser.getSelectedFile().toString().substring(fileChooser.getSelectedFile().toString().indexOf("res\\"));
            currentSceneLabel.setText("Current scene: " + sceneFile.substring(sceneFile.lastIndexOf("\\")+1,sceneFile.indexOf(".json")));
            loaded = true;
        } else if (status == JFileChooser.CANCEL_OPTION) {
            JOptionPane.showMessageDialog(this, "Choose file to load");
        }
    }

    public String getSceneFile() {
        return sceneFile;
    }

    private void exitAction(){
        dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        System.exit(0);
    }
    private void playAction(){
        if(!loaded)
            JOptionPane.showMessageDialog(this, "Must to load scene before!");
        else {
            played = true;
            setVisible(false);
            new Thread(game).start();
        }
    }
}
