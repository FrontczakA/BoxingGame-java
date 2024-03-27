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
    public int mode; //mode =0 (nothing) =1 (block)  =2(atack)
    public int whichAttack;

    public Player(int damage_in, int hp_in, int whichFighter_in, String imagePath) {
        mode = 0;
        whichFighter = whichFighter_in;
        whichAttack = 1;
        damage = damage_in;
        hp = hp_in;

        try {
            playerImage = ImageIO.read(getClass().getResource(imagePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        x_position = 150;
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

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_LEFT) {
            if(x_position >150){
            x_position -= 40;
            repaint();
            }
        } else if (keyCode == KeyEvent.VK_RIGHT) {
            if(x_position <950){
                x_position += 40;
                repaint();
            }
        }
        else if (keyCode == KeyEvent.VK_A){
            if(this.whichFighter ==1) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1blockG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 1;
            }
            else if(this.whichFighter ==2){
                try {
                this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2blockG.png")));
            } catch (IOException g) {
                g.printStackTrace();
            }
                repaint();
                this.mode = 1;

            }
            else if(this.whichFighter ==3){
                try {
                this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3blockG.png")));
            } catch (IOException g) {
                g.printStackTrace();
            }
                repaint();
                this.mode = 1;

            }
        }

        else if (keyCode == KeyEvent.VK_S) {
            if (this.whichFighter == 1){

                if (this.whichAttack == 1) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1attack1G.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 2;
                    this.mode = 2;

                } else if (this.whichAttack == 2) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1attack2G.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 1;
                    this.mode = 2;
                }
            }
            else if (this.whichFighter == 2){

                if (this.whichAttack == 1) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2attack1G.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 2;
                    this.mode = 2;
                } else if (this.whichAttack == 2) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2attack2G.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 1;
                    this.mode = 2;
                }
            }
            else if (this.whichFighter == 3){

                if (this.whichAttack == 1) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3attack1G.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 2;
                    this.mode = 2;
                } else if (this.whichAttack == 2) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3attack2G.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 1;
                    this.mode = 2;
                }
            }
        }
    }

    public void keyReleased(KeyEvent e){
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_A) {
            if(this.whichFighter ==1) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1stanceG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 0;
            }
            else if(this.whichFighter ==2) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2stanceG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 0;
            }
            else if(this.whichFighter ==3) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3stanceG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 0;
            }
        }

        else if (keyCode == KeyEvent.VK_S) {
            if(this.whichFighter ==1) {
                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1stanceG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 0;
            }
            else if(this.whichFighter ==2) {
                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2stanceG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 0;
            }
            else if(this.whichFighter ==3) {
                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3stanceG.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
                this.mode = 0;
            }
        }
    }


    public void keyTyped(KeyEvent e){}

}
