package blokudoku;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
    private MainMenu mainMenu;
    private static ScoreBoard scoreBoard;
    private static InfoPanel infoPanel;
    private static Gameplay gameplay;
    private static JPanel cards;
    private static CardLayout layout;


    public Window(int width, int height) {
        customizeWindow(width, height);
        layout = new CardLayout();
        cards = new JPanel(layout);

        mainMenu = new MainMenu();
        cards.add(mainMenu, "menu");

        infoPanel = new InfoPanel();
        cards.add(infoPanel, "infoPanel");

        scoreBoard = new ScoreBoard();
        cards.add(scoreBoard, "scoreBoard");

        add(cards);
        setVisible(true);
    }

    public static void startGame() {
        gameplay = new Gameplay(Main.WIDTH, Main.HEIGHT, scoreBoard);
        cards.add(gameplay, "game");
        layout.show(cards, "game");
        gameplay.start();
    }

    public static void showScoreBoard() {
        layout.show(cards, "scoreBoard");
    }

    public static void showInfoPanel() {
        layout.show(cards, "infoPanel");
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
}