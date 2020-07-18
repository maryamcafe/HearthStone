package ap.hearthstone.UI.gameView;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//panels should get data from states and draw them

public class GameView extends ap.hearthstone.UI.api.ViewPanel {


    private Graphics2D g2d;


    public GameView() {
        start();
    }

    @Override
    protected void addListeners() {

    }

    public void start() {
        Timer timer = new Timer(16, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tick();
                render();
            }
        });
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        requestFocus();
        drawBgImage();
        drawGameState();
    }


    private void drawBgImage() {
//        g2d.drawImage(gameData.getBgImage(), 0, 0, null);
    }

    private void drawGameState() {

    }

    public void tick() {
//        gameData.update();
    }

    private void render() {
        repaint();
        revalidate();
    }

    @Override
    protected void executeResponses() {

    }
}
