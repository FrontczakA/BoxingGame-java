package boxgame;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URL;

class Game extends JFrame {
    public static final int DEFAULT_WIDTH = 1200;
    public static final int DEFAULT_HEIGHT = 700;
    private static final int FIGHTER_COUNT = 3;
    private Image backgroundImage;
    private final JPanel backgroundPanel;
    private Clip soundtrack;
    private final JButton start, end, chooseReady;
    private final JButton nextPlayer, nextEnemy, backPlayer, backEnemy;
    private final JLabel chooseFighter, choosePlayer, chooseEnemy;
    private final JLabel hpPlayer, hpEnemy, gameOver;
    private int fighterG = 1;
    private int fighterP = 2;
    private Player player;
    private Enemy enemy;

    public Game() {
        setTitle("BoxingGame");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setLayout(null);
        start = createButton("Start game", 450, 100, 300, 100, true, this::buttonStart);
        end = createButton("End game", 450, 250, 300, 100, true, this::buttonEnd);
        chooseReady = createButton("Ready", 475, 550, 250, 75, false, this::buttonChooseReady);
        backPlayer = createButton("Back", 25, 350, 75, 50, false, () -> changeFighter(true, -1));
        nextPlayer = createButton("Next", 350, 350, 75, 50, false, () -> changeFighter(true, 1));
        backEnemy = createButton("Back", 775, 350, 75, 50, false, () -> changeFighter(false, -1));
        nextEnemy = createButton("Next", 1075, 350, 75, 50, false, () -> changeFighter(false, 1));
        backgroundImage = loadImage("graphics/imagemenu.jpg");
        chooseFighter = createLabel("Choose fighter", 350, 0, 1000, 200, 30, "Goudy Stout");
        choosePlayer = createFighterLabel(fighterG, "G", 150, 230);
        chooseEnemy = createFighterLabel(fighterP, "P", 875, 230);
        hpPlayer = createLabel("Your HP: 100", 10, 550, 300, 100, 15, "Georgia Pro Cond Black");
        hpEnemy = createLabel("Enemy HP: 100", 1070, 550, 300, 100, 15, "Georgia Pro Cond Black");
        gameOver = createLabel("Game over", 255, -50, 1000, 200, 30, "Goudy Stout");
        backgroundPanel = new JPanel(null) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(backgroundImage, 0, -20, this);
            }
        };
        add(backgroundPanel);
        backgroundPanel.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        startSoundtrack();
    }

    private JButton createButton(String text, int x, int y, int width, int height, boolean visible, Runnable action) {
        JButton button = new JButton(text);
        button.setBounds(x, y, width, height);
        button.setVisible(visible);
        button.addActionListener(e -> action.run());
        add(button);
        return button;
    }

    private JLabel createLabel(String text, int x, int y, int width, int height, int fontSize, String fontName) {
        JLabel label = new JLabel(text);
        label.setBounds(x, y, width, height);
        label.setFont(new Font(fontName, Font.PLAIN, fontSize));
        label.setForeground(Color.ORANGE);
        label.setVisible(false);
        add(label);
        return label;
    }

    private JLabel createFighterLabel(int fighterNumber, String side, int x, int y) {
        Image image = loadImage(fighterPath(fighterNumber, side));
        JLabel label = new JLabel(new ImageIcon(image));
        label.setBounds(x, y, image.getWidth(null), image.getHeight(null));
        label.setVisible(false);
        add(label);
        return label;
    }

    private String fighterPath(int number, String side) {
        return "graphics/fighter" + number + "stance" + side + ".png";
    }

    private Image loadImage(String path) {
        URL url = getClass().getResource(path);
        if (url == null) {
            throw new IllegalArgumentException("Image not found: " + path);
        }
        try {
            return ImageIO.read(url);
        } catch (IOException e) {
            throw new IllegalStateException("Cannot load image: " + path, e);
        }
    }

    private void changeBackground(String path) {
        backgroundImage = loadImage(path);
        backgroundPanel.repaint();
    }

    private void startSoundtrack() {
        try {
            URL url = getClass().getResource("graphics/soundtrack.wav");
            if (url == null) throw new IllegalArgumentException("Soundtrack not found");
            try (AudioInputStream stream = AudioSystem.getAudioInputStream(url)) {
                soundtrack = AudioSystem.getClip();
                soundtrack.open(stream);
                soundtrack.loop(Clip.LOOP_CONTINUOUSLY);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void setSelectionVisible(boolean visible) {
        chooseFighter.setVisible(visible);
        chooseReady.setVisible(visible);
        nextPlayer.setVisible(visible);
        nextEnemy.setVisible(visible);
        backPlayer.setVisible(visible);
        backEnemy.setVisible(visible);
        choosePlayer.setVisible(visible);
        chooseEnemy.setVisible(visible);
    }

    private void buttonEnd() {
        if (soundtrack != null) soundtrack.close();
        dispose();
    }

    private void buttonStart() {
        changeBackground("graphics/chooseFighter.jpg");
        gameOver.setVisible(false);
        start.setVisible(false);
        end.setVisible(false);
        setSelectionVisible(true);
    }

    private void changeFighter(boolean isPlayer, int direction) {
        int current = isPlayer ? fighterG : fighterP;
        int next = (current - 1 + direction + FIGHTER_COUNT) % FIGHTER_COUNT + 1;
        String side = isPlayer ? "G" : "P";
        JLabel label = isPlayer ? choosePlayer : chooseEnemy;
        label.setIcon(new ImageIcon(loadImage(fighterPath(next, side))));
        if (isPlayer) fighterG = next;
        else fighterP = next;
    }

    private void buttonChooseReady() {
        changeBackground("graphics/arena.jpg");
        setSelectionVisible(false);
        backgroundPanel.add(hpEnemy);
        backgroundPanel.add(hpPlayer);
        hpEnemy.setVisible(true);
        hpPlayer.setVisible(true);
        player = new Player(5, 100, fighterG, fighterPath(fighterG, "G"));
        enemy = new Enemy(5, 100, fighterP, fighterPath(fighterP, "P"));
        addFighterToArena(player);
        addFighterToArena(enemy);
        addKeyListener(player);
        setFocusable(true);
        requestFocusInWindow();
        Timer movementTimer = new Timer(200, e -> {
            enemy.moveEnemy();
            fight();
        });
        Timer actionTimer = new Timer(500, e -> enemy.actionsEnemy());
        Timer endTimer = new Timer(500, null);
        endTimer.addActionListener(e -> gameEnd(movementTimer, actionTimer, endTimer));
        movementTimer.start();
        actionTimer.start();
        endTimer.start();
    }

    private void addFighterToArena(JPanel fighter) {
        backgroundPanel.add(fighter);
        fighter.setBounds(0, 0, DEFAULT_WIDTH, DEFAULT_HEIGHT);
        fighter.setOpaque(false);
        fighter.setVisible(true);
    }

    private void fight() {
        if (enemy.x_position - player.x_position > 110) return;
        if (player.mode == 2) {
            enemy.hp -= enemy.mode == 1 ? 2 : player.damage;
        }
        if (enemy.mode == 2) {
            player.hp -= player.mode == 1 ? 2 : enemy.damage;
        }
        hpPlayer.setText("Your HP: " + player.hp);
        hpEnemy.setText("Enemy HP: " + enemy.hp);
    }

    private void gameEnd(Timer movementTimer, Timer actionTimer, Timer endTimer) {
        if (player.hp > 0 && enemy.hp > 0) return;
        movementTimer.stop();
        actionTimer.stop();
        endTimer.stop();
        removeKeyListener(player);
        backgroundPanel.remove(player);
        backgroundPanel.remove(enemy);
        backgroundPanel.revalidate();
        backgroundPanel.repaint();
        hpPlayer.setVisible(false);
        hpEnemy.setVisible(false);
        start.setVisible(true);
        end.setVisible(true);
        gameOver.setText(player.hp <= 0 ? "Game over, you lost!" : "Game over, you win!");
        gameOver.setVisible(true);
        player.hp = 100;
        enemy.hp = 100;
        hpPlayer.setText("Your HP: 100");
        hpEnemy.setText("Enemy HP: 100");
    }
}