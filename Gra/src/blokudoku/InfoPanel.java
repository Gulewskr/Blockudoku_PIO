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
        titleLabel.setBounds(Main.WIDTH / 2 - 250, 10, 500, 100);
        titleLabel.setForeground(white);
        titleLabel.setFont(new Font("NewFont", BOLD, 40));
        add(titleLabel);
    }

    private void addBackButton() {
        backButton = new JButton("◄ Powrót");
        backButton.setBounds(20, 20, 150, 35);
        backButton.setBackground(white);
        backButton.setFocusable(false);
        backButton.setFont(new Font("NewFont", BOLD, 15));
        backButton.addActionListener(this);
        add(backButton);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.gray.darker());
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(blue);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && (j != 0 || i % 3 == 0))
                    if (g.getColor() == white)
                        g.setColor(Color.darkGray);
                    else
                        g.setColor(white);
                g.fillRect(700 + j * 45, 150 + i * 45, 40, 40);

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
        title.setBounds(100, 90, 250, 100);
        title.setForeground(blue);
        title.setFont(new Font("NewFont", BOLD, 20));
        add(title);
        JTextArea textArea = new JTextArea(
                "BlockuDoku jest niezwykłym połączeniem sudoku oraz łamigłówek z klocków. Jest to prosta," +
                        " lecz wymagająca myślenia gra logiczna, od której nie będziesz się w stanie oderwać." +
                        " Łącz klocki w linie i kwadraty by usuwać je z planszy. Oczyszczaj planszę i pobij swój rekord.",
                6,
                20);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(white);
        textArea.setBounds(100, 160, 500, 100);
        add(textArea);
    }

    private void addRulesInfo() {
        JLabel title = new JLabel("• Funkcje:");
        title.setBounds(100, 250, 250, 100);
        title.setForeground(blue);
        title.setFont(new Font("NewFont", BOLD, 20));
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
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(white);
        textArea.setBounds(100, 320, 500, 250);
        add(textArea);
    }

    private void addAuthorsInfo() {
        JLabel title = new JLabel("• Autorzy:");
        title.setBounds(100, 510, 250, 100);
        title.setForeground(blue);
        title.setFont(new Font("NewFont", BOLD, 20));
        add(title);
        JTextArea textArea = new JTextArea(
                "Rafał Gulewski,   Franciszek Wysocki,   Antoni Malinowski,   " +
                        "Krzysztof Kowalski,   Patryk Dulnikiewicz,   Marek Nowakowski",

                6,
                20);
        textArea.setFont(new Font("Serif", Font.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setOpaque(false);
        textArea.setEditable(false);
        textArea.setForeground(white);
        textArea.setBounds(100, 580, 460, 100);
        add(textArea);
    }


}
