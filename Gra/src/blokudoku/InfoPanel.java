package blokudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import static java.awt.Color.blue;
import static java.awt.Color.white;
import static java.awt.Font.BOLD;
import static javax.swing.SwingConstants.CENTER;

public class InfoPanel extends JPanel implements ActionListener {
    private JButton backButton;

    public InfoPanel() {
        setLayout(null);
        addBackButton();
        addTitleLabel();
        addInfo();
    }

    private void addInfo() {
        addBasicInfo();
        addRulesInfo();
        addAuthorsInfo();
    }


    private void addTitleLabel() {
        JLabel titleLabel = new JLabel("Informacje o grze", CENTER);
        titleLabel.setBounds(Main.WIDTH / 2 - 25 * Main.WIDTH / 124, Main.WIDTH / 124, 50 * Main.WIDTH / 124, 10 * Main.WIDTH / 124);
        titleLabel.setForeground(white);
        titleLabel.setFont(new Font("NewFont", BOLD, 4 * Main.WIDTH / 124));
        add(titleLabel);
    }

    private void addBackButton() {
        backButton = new JButton("◄ Powrót");
        backButton.setBounds(Main.WIDTH / 62, Main.WIDTH / 62, 15 * Main.WIDTH / 124, 7 * Main.WIDTH / 248);
        backButton.setBackground(white);
        backButton.setFocusable(false);
        backButton.setFont(new Font("NewFont", BOLD, 3 * Main.WIDTH / 248));
        backButton.addActionListener(this);
        add(backButton);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray.darker());
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && (j != 0 || i % 3 == 0))
                    if (g.getColor() == white)
                        g.setColor(Color.darkGray);
                    else
                        g.setColor(white);
                g.fillRect(35 * Main.WIDTH / 62 + j * 9 * Main.WIDTH / 248, 15 * Main.WIDTH / 124 + i * 9 * Main.WIDTH / 248, Main.WIDTH / 31, Main.WIDTH / 31);

            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == backButton) {
            Window.back();
        }
    }

    private void addBasicInfo() {
        JLabel title = new JLabel("• Czym jest BlokuDoku?");
        title.setBounds(5 * Main.WIDTH / 62, 9 * Main.WIDTH / 124, 25 * Main.WIDTH / 124, 5 * Main.WIDTH / 62);
        title.setForeground(blue);
        title.setFont(new Font("NewFont", BOLD, Main.WIDTH / 62));
        add(title);
        JTextArea textArea = new JTextArea(
                "BlockuDoku jest niezwykłym połączeniem sudoku oraz łamigłówek z klocków. Jest to prosta," +
                        " lecz wymagająca myślenia gra logiczna, od której nie będziesz się w stanie oderwać." +
                        " Łącz klocki w linie i kwadraty by usuwać je z planszy. Oczyszczaj planszę i pobij swój rekord.",
                6,
                20);
        textArea.setFont(new Font("Serif", Font.ITALIC, 2 * Main.WIDTH / 155));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(white);
        textArea.setBounds(5 * Main.WIDTH / 62, 4 * Main.WIDTH / 31, 25 * Main.WIDTH / 62, 5 * Main.WIDTH / 62);
        add(textArea);
    }

    private void addRulesInfo() {
        JLabel title = new JLabel("• Funkcje:");
        title.setBounds(5 * Main.WIDTH / 62, 25 * Main.WIDTH / 124, 25 * Main.WIDTH / 124, 5 * Main.WIDTH / 62);
        title.setForeground(blue);
        title.setFont(new Font("NewFont", BOLD, 1 * Main.WIDTH / 62));
        add(title);
        JTextArea textArea = new JTextArea(
                "✔ Plansza 9x9. Twórz linie i kwadraty na znanej z Sudoku siatce 9x9.\n" +
                        "✔ Bloki mają różne kształty. Rozmieszczaj je w liniach lub kwadratach by je zniszczyć, utrzymując pustą planszę.\n" +
                        "✔ Kombinacje. Zostań mistrzem, usuwając wiele klocków w jednym ruchu.\n" +
                        "✔ Serie. Zdobywaj więcej punktów usuwając klocki w kolejnych ruchach.\n" +
                        "✔ Unikalna mechanika. Gra logiczna BlockuDoku to połączenie sudoku i łamigłówek z klocków.\n" +
                        "✔ Wciągająca rozgrywka. Graj dla zabicia czasu lub ćwicz umysł – kiedy chcesz i gdzie chcesz.",
                6,
                20);
        textArea.setFont(new Font("Serif", Font.ITALIC, 2 * Main.WIDTH / 155));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(white);
        textArea.setBounds(5 * Main.WIDTH / 62, 8 * Main.WIDTH / 31, 25 * Main.WIDTH / 62, 25 * Main.WIDTH / 124);
        add(textArea);
    }

    private void addAuthorsInfo() {
        JLabel title = new JLabel("• Autorzy:");
        title.setBounds(5 * Main.WIDTH / 62, 51 * Main.WIDTH / 124, 25 * Main.WIDTH / 124, 5 * Main.WIDTH / 62);
        title.setForeground(blue);
        title.setFont(new Font("NewFont", BOLD, Main.WIDTH / 62));
        add(title);
        JTextArea textArea = new JTextArea(
                "Rafał Gulewski,   Franciszek Wysocki,   Antoni Malinowski,   " +
                        "Krzysztof Kowalski,   Patryk Dulnikiewicz,   Marek Nowakowski",

                6,
                20);
        textArea.setFont(new Font("Serif", Font.ITALIC, 2 * Main.WIDTH / 155));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(white);
        textArea.setBounds(5 * Main.WIDTH / 62, 29 * Main.WIDTH / 62, 23 * Main.WIDTH / 62, 5 * Main.WIDTH / 62);
        add(textArea);
    }


}
