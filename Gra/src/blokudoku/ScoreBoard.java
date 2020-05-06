package blokudoku;

import java.awt.Color;
import java.awt.Font;
import static java.awt.Font.BOLD;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import static javax.swing.SwingConstants.CENTER;

public class ScoreBoard extends JPanel implements ActionListener {
    private JButton backButton;
    private JLabel titleLabel;
    private String[] headers = {"Pozycja", "Imię/Nick:", "Uzyskany wynik:", "Data:", "Godzina:"};
    private ScoresFile scoresFile;
    private Object[][] cells;
    private JTable table;

    public ScoreBoard() {
        scoresFile = new ScoresFile();
        cells = scoresFile.readScores("src/scores.txt");
        setLayout(null);
        addBackButton();
        addTitleLabel();
        addTable(cells, headers);
    }

    private void addTitleLabel() {
        titleLabel = new JLabel("Tabela wyników", CENTER);
        titleLabel.setBounds(Main.WIDTH / 2 - 250, 10, 500, 100);
        titleLabel.setBounds(Main.WIDTH / 2 - 250, 10, 500, 100);
        titleLabel.setForeground(Color.white);
        titleLabel.setFont(new Font("NewFont", BOLD, 40));
        add(titleLabel);
    }

    private void addTable(Object[][] cells, Object[] headers) {
        table = new JTable(cells, headers) {
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table.setAutoCreateRowSorter(true);
        JScrollPane jScrollPane = new JScrollPane(table);
        if (cells.length < 30)
            jScrollPane.setBounds(Main.WIDTH / 2 - 250, 100, 500, 25 + 16 * cells.length);
        else
            jScrollPane.setBounds(Main.WIDTH / 2 - 250, 100, 500, 500);
        add(jScrollPane);
    }

    private void addBackButton() {
        backButton = new JButton("◄ Powrót");
        backButton.setBounds(20, 20, 150, 35);
        backButton.setBackground(Color.white);
        backButton.setFocusable(false);
        backButton.setFont(new Font("NewFont", BOLD, 15));
        backButton.addActionListener(this);
        add(backButton);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.darkGray);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();
        if (src == backButton) {
            Window.back();
        }
    }

    public void addScore(String name, int score){
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        Object[] arr = {"", name, score, dateFormat.format(date), hourFormat.format(date)};
        scoresFile.saveScore(arr);
        cells = scoresFile.readScores("src/scores.txt");
        table = null;
        addTable(cells, headers);
    }
}
