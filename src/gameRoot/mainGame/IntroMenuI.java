import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;

interface IntroMenuI {
    JPanel main = new JPanel();
    JButton playButton = new JButton("Play");
    JLabel currentSceneLabel = new JLabel("Current scene: ");
    JButton loadButton = new JButton("Load");
    JButton saveButton = new JButton("Save");
    JButton exitButton = new JButton("Exit");
    JFileChooser fileChooser = new JFileChooser("res/saves");
    FileNameExtensionFilter filter = new FileNameExtensionFilter("JSON files", "json");
}
