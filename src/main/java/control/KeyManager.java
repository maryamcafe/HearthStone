package control;


import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyManager implements KeyListener {

    private final GameAction gameAction;
    private Timer right, left, tobeh;

    public KeyManager(JPanel panel) {
        panel.addKeyListener(this);
        gameAction = GameAction.getInstance();
        initilize();
    }

    private void initilize() {
//        right = new Timer(5, new MyActionListener(GameAction.Request.BLOCK_MOVE_RIGHT));
//        left = new Timer(5, new MyActionListener(GameAction.Request.BLOCK_MOVE_LEFT));
//        tobeh = new Timer(5, new MyActionListener(GameAction.Request.TOBEH));
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        createRequest(e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
        stopRequest(e);
    }

    private void stopRequest(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                left.start();
                break;
            case KeyEvent.VK_RIGHT:
                right.start();
                break;
            case KeyEvent.VK_UP:
                tobeh.start();
                break;
        }
    }

    private void createRequest(KeyEvent e) {

    }
}
