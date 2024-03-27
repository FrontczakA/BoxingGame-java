package boxgame;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Enemy extends Player {

    Enemy(int damage_in, int hp_in, int whichFighter_in, String imagePath){
        super(damage_in, hp_in, whichFighter_in, imagePath);
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

    public void moveEnemy(){
        Random rand = new Random();
        int los = rand.nextInt(2);
        if(this.x_position <900 && this.x_position >130) {
            if (los == 0) {
                this.x_position += 10;
                repaint();
            } else if (los == 1) {
                this.x_position -= 10;
                repaint();
            } else if (los == 2) {
            //do nothing
            }
        }
        else if(this.x_position ==900){
            this.x_position -=10;
            repaint();
        }
        else if(this.x_position ==130){
            this.x_position +=10;
            repaint();
        }
    }

    public void actionsEnemy(){
        Random rand = new Random();
        int random = rand.nextInt(6);
        if(random==0) {
            this.mode = 1;
            if (this.whichFighter == 1) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1blockP.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();

            } else if (this.whichFighter == 2) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2blockP.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();

            } else if (this.whichFighter == 3) {

                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3blockP.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();

            }
        }
        if(random==1) {
            this.mode = 2;
            if (this.whichFighter == 1) {
                if (this.whichAttack == 1) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1attack1P.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 2;


                } else if (this.whichAttack == 2) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1attack2P.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 1;

                }
            } else if (this.whichFighter == 2) {
                if (this.whichAttack == 1) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2attack1P.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 2;


                } else if (this.whichAttack == 2) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2attack2P.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 1;

                }
            } else if (this.whichFighter == 3) {
                if (this.whichAttack == 1) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3attack1P.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 2;


                } else if (this.whichAttack == 2) {
                    try {
                        this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3attack2P.png")));
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                    repaint();
                    this.whichAttack = 1;

                }
            }
        }
        if(random==2||random==3||random==4||random==5) {
            this.mode = 0;
            if(whichFighter ==1){
                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter1stanceP.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
            }
            else if(whichFighter ==2){
                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter2stanceP.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
            }
            else if(whichFighter ==3){
                try {
                    this.playerImage = ImageIO.read(Objects.requireNonNull(getClass().getResource("graphics/fighter3stanceP.png")));
                } catch (IOException g) {
                    g.printStackTrace();
                }
                repaint();
            }
        }
    }
}
