package boxgame;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import javax.sound.sampled.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;


class Game extends JFrame implements ActionListener {
    public static final int DEFAULT_WIDTH = 1200;
    public static final int DEFAULT_HEIGHT = 700;
    public Image backgroundImage;
    public JPanel backgroundPanel;
    public Clip soundtrack;
    public JButton start;
    public JButton end;
    public JButton chooseReady;
    public JLabel chooseFighter;
    public JLabel choosePlayer;
    public JLabel chooseEnemy;
    public JButton nextPlayer;
    public JButton nextEnemy;
    public JButton backPlayer;
    public JButton backEnemy;
    public Image enemyChoose;
    public Image playerChoose;
    public int fighterG=1;
    public int fighterP=2;
    public Player player;
    public Enemy enemy;
    public JLabel hpPLayer;
    public JLabel hpEnemy;
    public JLabel gameOver;
    public Game() {
        setTitle("BoxingGame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);


        // Buttons
        start = new JButton("Start game");
        start.setBounds(450, 100, 300, 100);
        start.addActionListener(this);
        add(start);

        end = new JButton("End game");
        end.setBounds(450, 250, 300, 100);
        end.addActionListener(this);
        add(end);

        chooseReady = new JButton("Ready");
        chooseReady.setBounds(475, 550, 250, 75);
        chooseReady.addActionListener(this);
        chooseReady.setVisible(false);
        add(chooseReady);

        backPlayer = new JButton("Back");
        backPlayer.setBounds(25, 350, 75, 50);
        backPlayer.addActionListener(this);
        backPlayer.setVisible(false);
        add(backPlayer);

        nextPlayer = new JButton("Next");
        nextPlayer.setBounds(350, 350, 75, 50);
        nextPlayer.addActionListener(this);
        nextPlayer.setVisible(false);
        add(nextPlayer);

        backEnemy = new JButton("Back");
        backEnemy.setBounds(775, 350, 75, 50);
        backEnemy.addActionListener(this);
        backEnemy.setVisible(false);
        add(backEnemy);

        nextEnemy = new JButton("Next");
        nextEnemy.setBounds(1075, 350, 75, 50);
        nextEnemy.addActionListener(this);
        nextEnemy.setVisible(false);
        add(nextEnemy);

        // Images
        try {
            backgroundImage = ImageIO.read(getClass().getResource("graphics/imagemenu.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            playerChoose = ImageIO.read(getClass().getResource("graphics/fighter1stanceG.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            enemyChoose = ImageIO.read(getClass().getResource("graphics/fighter2stanceP.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //JLabels
        chooseFighter = new JLabel("Choose fighter");
        chooseFighter.setBounds(350, 0, 1000,200);
        chooseFighter.setVisible(false);
        chooseFighter.setFont(new Font("Goudy Stout", Font.PLAIN, 30));
        chooseFighter.setForeground(Color.ORANGE);
        add(chooseFighter);

        choosePlayer = new JLabel();
        choosePlayer.setIcon(new ImageIcon(playerChoose));
        choosePlayer.setBounds(150, 230, playerChoose.getWidth(null), playerChoose.getHeight(null));
        choosePlayer.setVisible(false);
        add(choosePlayer);

        chooseEnemy = new JLabel();
        chooseEnemy.setIcon(new ImageIcon(enemyChoose));
        chooseEnemy.setBounds(875, 230, enemyChoose.getWidth(null), enemyChoose.getHeight(null));
        chooseEnemy.setVisible(false);
        add(chooseEnemy);

        hpPLayer = new JLabel("Your HP: 100");
        hpPLayer.setBounds(10, 550, 300, 100);
        hpPLayer.setFont(new Font("Georgia Pro Cond Black", Font.PLAIN, 15));
        hpPLayer.setForeground(Color.orange);
        hpPLayer.setVisible(false);

        hpEnemy = new JLabel("Enemy HP: 100");
        hpEnemy.setBounds(1070, 550, 300, 100);
        hpEnemy.setFont(new Font("Georgia Pro Cond Black", Font.PLAIN, 15));
        hpEnemy.setForeground(Color.orange);
        hpEnemy.setVisible(false);

        gameOver = new JLabel("Game over");
        gameOver.setBounds(255, -50, 1000,200);
        gameOver.setVisible(false);
        gameOver.setFont(new Font("Goudy Stout", Font.PLAIN, 30));
        gameOver.setForeground(Color.orange);
        add(gameOver);



        backgroundPanel = new JPanel() {
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, -20, null);
            }
        };
        backgroundPanel.setLayout(null);
        add(backgroundPanel);

        //Soundtrack

        try {
            URL soundtrackUrl = getClass().getResource("graphics/soundtrack.wav");
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundtrackUrl);
            soundtrack = AudioSystem.getClip();
            soundtrack.open(audioInputStream);
            soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }

    }


    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if(source== end){
            buttonEnd();
        }
        if(source== start){
            buttonStart();
        }
        if(source== chooseReady){
            buttonChooseReady();
        }
        if(source== nextPlayer){
            buttonChooseNextPlayer();
        }
        if(source== backPlayer){
            buttonChooseBackPlayer();
        }
        if(source== nextEnemy){
            buttonChooseNextEnemy();
        }
        if(source== backEnemy){
            buttonChooseBackEnemy();
        }

    }

    public void buttonEnd(){
        dispose();
    }

    public void buttonStart(){
        try {
            backgroundImage = ImageIO.read(getClass().getResource("graphics/chooseFighter.jpg"));
            backgroundPanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }
        gameOver.setVisible(false);
        start.setVisible(false);
        end.setVisible(false);
        chooseFighter.setVisible(true);
        chooseReady.setVisible(true);
        nextPlayer.setVisible(true);
        nextEnemy.setVisible(true);
        backPlayer.setVisible(true);
        backEnemy.setVisible(true);
        choosePlayer.setVisible(true);
        chooseEnemy.setVisible(true);
    }
    public void buttonChooseReady(){
        try {
            backgroundImage = ImageIO.read(getClass().getResource("graphics/arena.jpg"));
            backgroundPanel.repaint();
        } catch (IOException e) {
            e.printStackTrace();
        }

        chooseFighter.setVisible(false);
        chooseReady.setVisible(false);
        nextPlayer.setVisible(false);
        nextEnemy.setVisible(false);
        backPlayer.setVisible(false);
        backEnemy.setVisible(false);
        chooseEnemy.setVisible(false);
        choosePlayer.setVisible(false);

        backgroundPanel.add(hpEnemy);
        backgroundPanel.add(hpPLayer);
        hpEnemy.setVisible(true);
        hpPLayer.setVisible(true);

        if(fighterG==1){
            player = new Player(5,100,1,"graphics/fighter1stanceG.png");
        }
        else if(fighterG==2){
            player = new Player(5,100,2,"graphics/fighter2stanceG.png");
        }
        else if(fighterG==3){
            player = new Player(5,100,3,"graphics/fighter3stanceG.png");
        }
        addKeyListener(player);
        backgroundPanel.add(player);
        player.setBounds(0,0, 1200, 700);
        player.setOpaque(false);
        player.setVisible(true);


        if(fighterP==1){
            enemy = new Enemy(5,100,1,"graphics/fighter1stanceP.png");
        }
        else if(fighterP==2){
            enemy = new Enemy(5,100,2,"graphics/fighter2stanceP.png");
        }
        else if(fighterP==3){
            enemy = new Enemy(5,100,3,"graphics/fighter3stanceP.png");
        }
        backgroundPanel.add(enemy);
        enemy.setBounds(0,0, 1200, 700);
        enemy.setOpaque(false);
        enemy.setVisible(true);



        Timer timer1 = new Timer(200, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                enemy.moveEnemy();
                fight(player, enemy, hpPLayer, hpEnemy);
            }
        });
        timer1.start();

        Timer timer2 = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                enemy.actionsEnemy();
            }
        });
        timer2.start();

        Timer timer3 = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameEnd(timer1, timer2);            }
        });
        timer3.start();

    }

    public void buttonChooseNextPlayer(){
        if(fighterG==1){
            Icon newFighter = new ImageIcon("graphics/fighter2stanceG.png");
            choosePlayer.setIcon( newFighter);
            choosePlayer.repaint();
            fighterG=2;

        }

        else if(fighterG==2){
            Icon newFighter = new ImageIcon("graphics/fighter3stanceG.png");
            choosePlayer.setIcon(newFighter);
            choosePlayer.repaint();
            fighterG=3;
        }

        else if(fighterG==3){
            Icon newFighter = new ImageIcon("graphics/fighter1stanceG.png");
            choosePlayer.setIcon(newFighter);
            choosePlayer.repaint();
            fighterG=1;
        }

    }

    public void buttonChooseBackPlayer(){

        if(fighterG==1){
            Icon newFighter = new ImageIcon("graphics/fighter3stanceG.png");
            choosePlayer.setIcon( newFighter);
            choosePlayer.repaint();
            fighterG=3;

        }

        else if(fighterG==2){
            Icon newFighter = new ImageIcon("graphics/fighter1stanceG.png");
            choosePlayer.setIcon(newFighter);
            choosePlayer.repaint();
            fighterG=1;

        }

        else if(fighterG==3){
            Icon newFighter = new ImageIcon("graphics/fighter2stanceG.png");
            choosePlayer.setIcon(newFighter);
            choosePlayer.repaint();
            fighterG=2;
        }

    }

    public void buttonChooseNextEnemy(){

        if(fighterP==1){
            Icon newFighter = new ImageIcon("graphics/fighter2stanceP.png");
            chooseEnemy.setIcon( newFighter);
            chooseEnemy.repaint();
            fighterP=2;

        }

        else if(fighterP==2){
            Icon newFighter = new ImageIcon("graphics/fighter3stanceP.png");
            chooseEnemy.setIcon(newFighter);
            chooseEnemy.repaint();
            fighterP=3;

        }

        else if(fighterP==3){
            Icon newFighter = new ImageIcon("graphics/fighter1stanceP.png");
            chooseEnemy.setIcon(newFighter);
            chooseEnemy.repaint();
            fighterP=1;
        }

    }

    public void buttonChooseBackEnemy(){

        if(fighterP==1){
            Icon newFighter = new ImageIcon("graphics/fighter3stanceP.png");
            chooseEnemy.setIcon( newFighter);
            chooseEnemy.repaint();
            fighterP=3;

        }

        else if(fighterP==2){
            Icon newFighter = new ImageIcon("graphics/fighter1stanceP.png");
            chooseEnemy.setIcon(newFighter);
            chooseEnemy.repaint();
            fighterP=1;

        }

        else if(fighterG==3){
            Icon newFighter = new ImageIcon("graphics/fighter2stanceP.png");
            chooseEnemy.setIcon(newFighter);
            chooseEnemy.repaint();
            fighterP=2;
        }

    }

    public void fight(Player player, Enemy enemy, JLabel hpPlayer, JLabel hpEnemy){
        if((enemy.x_position - player.x_position)<=110){
            if(player.mode ==0&& enemy.mode ==0){
                //nothing
            }
            else if(player.mode ==0&& enemy.mode ==1){
                //nothing
            }
            else if(player.mode ==0&& enemy.mode ==2){
                player.hp -= enemy.damage;
                hpPlayer.setText("Your HP: "+ player.hp);
            }
            else if(player.mode ==1&& enemy.mode ==0){
                //nothing
            }
            else if(player.mode ==1&& enemy.mode ==1){
                //nothing
            }
            else if(player.mode ==1&& enemy.mode ==2){
                player.hp -= 2;
                hpPlayer.setText("Your HP: "+ player.hp);

            }
            else if(player.mode ==2&& enemy.mode ==0){
                enemy.hp -= player.damage;
                hpEnemy.setText("Enemy HP: " + enemy.hp);
            }
            else if(player.mode ==2&& enemy.mode ==1){
                enemy.hp -= 2;
                hpEnemy.setText("Enemy HP: " + enemy.hp);
            }
            else if(player.mode ==2&& enemy.mode ==2){
                enemy.hp -= player.damage;
                player.hp -= enemy.damage;
                hpEnemy.setText("Enemy HP: " + enemy.hp);
                hpPlayer.setText("Your HP: "+ player.hp);
            }
         }
        else{}
    }

    public void gameEnd(Timer timer1, Timer timer2){
        if(player.hp<=0|| enemy.hp<=0){
            timer1.stop();
            timer2.stop();
            removeKeyListener(player);
            player.setVisible(false);
            enemy.setVisible(false);
            backgroundPanel.remove(player);
            backgroundPanel.remove(enemy);

            hpPLayer.setVisible(false);
            hpEnemy.setVisible(false);
            start.setVisible(true);
            end.setVisible(true);
            gameOver.setVisible(true);
            if(player.hp<=0){
                gameOver.setText("Game over, you lost!");
            }
            else if(enemy.hp<=0){
                gameOver.setText("Game over, you win!");
            }
            player.hp = 100;
            enemy.hp = 100;
            hpPLayer.setText("Your HP: " + player.hp);
            hpPLayer.setText("Enemy HP: " + enemy.hp);
        }
    }
};