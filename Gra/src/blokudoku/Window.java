package blokudoku;

import java.awt.CardLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Window extends JFrame {
    private MainMenu mainMenu;
    private static ScoreBoard scoreBoard;
    private static Gameplay gameplay;
    private static JPanel cards;
    private static CardLayout layout;

    public Window(int width, int height) {
        customizeWindow(width, height);
        layout = new CardLayout();
        cards = new JPanel(layout);

        mainMenu = new MainMenu();
        cards.add(mainMenu, "menu");
        scoreBoard = new ScoreBoard();
        cards.add(scoreBoard, "scoreBoard");
        gameplay = new Gameplay(width, height, scoreBoard);
        cards.add(gameplay, "game");

        add(cards);
        setVisible(true);
    }

    public static void startGame() {
        layout.show(cards, "game");
        gameplay.start();
    }

    public static void showScoreBoard(){
        layout.show(cards, "scoreBoard");
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