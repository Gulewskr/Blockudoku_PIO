package blokudoku;

import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JFrame {
    private MainMenu mainMenu;
    private static ScoreBoard scoreBoard;
    private static InfoPanel infoPanel;
    private static OptionMenu optionMenu;
    private static Gameplay gameplay;
    private static EndGame endGame;
    private static JPanel cards;
    private static CardLayout layout;


    public Window(int width, int height, boolean opcje)
    {
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

        if(opcje)
            showOptionMenu();
    }

    public static void startGame() {
        gameplay = new Gameplay(Main.WIDTH, Main.HEIGHT, scoreBoard);
        cards.add(gameplay, "game");
        layout.show(cards, "game");
        gameplay.start();
    }

    public static void setEndGame(int wynik)
    {
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

    private void customizeWindow(int width, int height)
    {
        setTitle("Blockudoku");
        setBounds(0, 0, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
    }

    public static Color tileColor()
    {
        return optionMenu.getTileColor();
    }

    public static BufferedImage rainbowTile()
    {
        return optionMenu.getRainbowTileImage();
    }

    public static BufferedImage skinTile()
    {
        return optionMenu.getSkinImage();
    }
}