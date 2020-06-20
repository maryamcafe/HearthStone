package ap.hearthstone.UI.swing;

import javax.swing.*;

public class Display {

    private JFrame frame;
    private JPanel panel;
    private int rows, columns, width, height, boxSize, sidebarWidth, topMargin, sideMargin;

    public Display() {
        frame = new ap.hearthstone.UI.swing.MainFrame();
    }

    private void setPanel(JPanel panel)  {
// this class should get a panel and add it to the frame.
        frame.add(panel);
    }


//    private void initializePanel() {
//        panel = new swing.GamePanel(rows, columns);
//        panel.setPreferredSize(new Dimension(width, height));
//        panel.setMaximumSize(new Dimension(width, height));
//        panel.setMinimumSize(new Dimension(width, height));
//    }


}
