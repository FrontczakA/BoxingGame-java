
package boxgame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Enemy extends Player {

    private final Random random = new Random();

    Enemy(int damage, int hp, int whichFighter, String imagePath) {
        super(damage, hp, whichFighter, imagePath);
        removeKeyListener(this);
        x_position = 900;
    }

    public void drawEnemy(Graphics g) {
        if (playerImage != null) {
            g.drawImage(playerImage, x_position, 230, this);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawEnemy(g);
    }

    public void moveEnemy() {
        if (x_position >= 900) {
            x_position -= 10;
        } else if (x_position <= 130) {
            x_position += 10;
        } else {
            // Random movement: left or right
            x_position += random.nextBoolean() ? 10 : -10;
        }
        repaint();
    }

    public void actionsEnemy() {
        int action = random.nextInt(6);

        switch (action) {
            case 0 -> block();
            case 1 -> attack();
            default -> idle();
        }
    }

    private void block() {
        mode = 1;
        updateImage("block");
    }

    private void attack() {
        mode = 2;
        updateImage("attack" + whichAttack);
        // Alternate between attack 1 and attack 2
        whichAttack = (whichAttack == 1) ? 2 : 1;
    }

    private void idle() {
        mode = 0;
        updateImage("stance");
    }

    private void updateImage(String action) {
        String imagePath = "graphics/fighter" + whichFighter + action + "P.png";
        try {
            playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource(imagePath)));
            repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
