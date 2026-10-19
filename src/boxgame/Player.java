
package boxgame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;

public class Player extends JPanel implements KeyListener {

    public int hp;
    public int damage;
    public Image playerImage;
    public int x_position;
    public int whichFighter;
    //0 = nothing, 1 = block, 2 = attack
    public int mode;
    public int whichAttack;

    public Player(int damage, int hp, int whichFighter, String imagePath) {
        this.damage = damage;
        this.hp = hp;
        this.whichFighter = whichFighter;
        mode = 0;
        whichAttack = 1;
        x_position = 150;
        loadImage(imagePath);
        addKeyListener(this);
        setFocusable(true);
    }

    public void drawPlayer(Graphics g) {
        if (playerImage != null) {
            g.drawImage(playerImage, x_position, 230, this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayer(g);
    }

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT -> movePlayer(-40);
            case KeyEvent.VK_RIGHT -> movePlayer(40);
            case KeyEvent.VK_A -> block();
            case KeyEvent.VK_S -> attack();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_A ||
                e.getKeyCode() == KeyEvent.VK_S) {idle();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    private void movePlayer(int distance) {
        int newPosition = x_position + distance;
        if (newPosition >= 150 && newPosition <= 950) {
            x_position = newPosition;
            repaint();
        }
    }

    private void block() {
        mode = 1;
        updateImage("block");
    }

    private void attack() {
        mode = 2;
        updateImage("attack" + whichAttack);
        //alternate between attack 1 and attack 2
        whichAttack = (whichAttack == 1) ? 2 : 1;
    }

    private void idle() {
        mode = 0;
        updateImage("stance");
    }

    private void updateImage(String action) {
        String imagePath = "graphics/fighter" + whichFighter + action + "G.png";
        loadImage(imagePath);
    }

    private void loadImage(String imagePath) {
        try {
            playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
