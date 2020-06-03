package blokudoku;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
    private MainMenu mainMenu;
    private static ScoreBoard scoreBoard;
    private static InfoPanel infoPanel;
    private static OptionMenu optionMenu;
    private static Gameplay gameplay;
    private static EndGame endGame;
    private static JPanel cards;
    private static CardLayout layout;


    public Window(int width, int height, boolean opcje) {
        customizeWindow(width, height);
        layout = new CardLayout();
        cards = new JPanel(layout);

        mainMenu = new MainMenu();
        cards.add(mainMenu, "menu");

        infoPanel = new InfoPanel();
        cards.add(infoPanel, "infoPanel");

        scoreBoard = new ScoreBoard();
        cards.add(scoreBoard, "scoreBoard");

        optionMenu = new OptionMenu();
        cards.add(optionMenu, "optionMenu");

        endGame = new EndGame();
        cards.add(endGame, "endGame");

        add(cards);
        setVisible(true);

        if (opcje)
            showOptionMenu();
    }

    public static void startGame() {
        gameplay = new Gameplay(Main.WIDTH, Main.HEIGHT, scoreBoard);
        cards.add(gameplay, "game");
        layout.show(cards, "game");
        gameplay.start();
    }

    public static void setEndGame(int wynik) {
        endGame.setWynik(wynik);
        showEndGame();
        endGame.repaint();
    }

    public static void showEndGame() {
        layout.show(cards, "endGame");
    }

    public static void showScoreBoard() {
        layout.show(cards, "scoreBoard");
    }

    public static void showInfoPanel() {
        layout.show(cards, "infoPanel");
    }

    public static void showOptionMenu() {
        layout.show(cards, "optionMenu");
    }

    public static void back() {
        layout.show(cards, "menu");
    }

    private void customizeWindow(int width, int height) {
        setTitle("Blockudoku");
        setBounds(0, 0, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static Color tileColor() {
        if(optionMenu == null)
            return Color.blue;
        else
            return optionMenu.getTileColor();
    }

    public static BufferedImage rainbowTile() {
        if(optionMenu == null) {
            BufferedImage tileImage = null;
            try {
                tileImage = ImageIO.read(new File("src/blokudoku/images/rainbowTile.png"));
            } catch (IOException e) {
                System.out.println(e);
            }
            return tileImage;
        } else
            return optionMenu.getRainbowTileImage();
    }

    public static BufferedImage skinTile() {
        if(optionMenu == null) {
            BufferedImage tileImage = null;
            try {
                tileImage = ImageIO.read(new File("src/blokudoku/images/clean.png"));
            } catch (IOException e) {
                System.out.println(e);
            }
            return tileImage;
        } else
            return optionMenu.getSkinImage();
    }
}