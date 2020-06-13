package swing;

import util.PanelConfig;

import javax.swing.*;

public class MainFrame extends JFrame {

    private JFrame frame;
    private int width, height;
    private PanelConfig config;

    public MainFrame(){
        super();
        initialize();
    }

    private void initialize() {
        config = new PanelConfig();
//        width = config.getFrameWidth();
//        height = config.getFrameHeight();

        this.setSize(width, height);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public JFrame getFrame() {
        return frame;
    }
}
