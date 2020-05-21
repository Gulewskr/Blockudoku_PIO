package blokudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import static java.awt.Font.BOLD;
import static javax.swing.SwingConstants.CENTER;

public class MainMenu extends JPanel implements ActionListener {
    private JButton startButton, boardButton, infoButton, optionsButton;
    private JTextField playerNameField;
    private JLabel playerNameTip;
    private JLabel titleLabel;
    private JLabel soundLabel;
    private JCheckBox soundCheckBox;


    public MainMenu() {
        setLayout(null);
        addPlayButton();
        addLeftButtons();
        addTitle();
        addSoundFields();
        addName();
    }

    private void addSoundFields() {
        soundLabel = new JLabel("Graj z dźwiękiem:", JLabel.LEFT);
        soundLabel.setBounds(28 * Main.WIDTH / 62, 223 * Main.HEIGHT / 558, 5 * Main.WIDTH / 62, Main.WIDTH / 62);
        add(soundLabel);

        soundCheckBox = new JCheckBox();
        soundCheckBox.setBounds(67 * Main.WIDTH / 124, 223 * Main.HEIGHT / 558, Main.WIDTH / 62, Main.WIDTH / 62);
        soundCheckBox.doClick();

        soundCheckBox.addActionListener(this);
        add(soundCheckBox);
    }

    private void addName() {
        playerNameTip = new JLabel("Wprowadź swoje imię/nick:", CENTER);
        playerNameTip.setFont(new Font("NewFont", BOLD, 9 * Main.WIDTH / 620));
        playerNameTip.setBounds(37 * Main.WIDTH / 124, 53 * Main.HEIGHT / 186, 25 * Main.WIDTH / 62, Main.WIDTH / 62);
        add(playerNameTip);

        playerNameField = new JTextField("", CENTER);
        playerNameField.setFont(new Font("NewFont", BOLD, 9 * Main.WIDTH / 620));
        playerNameField.setHorizontalAlignment(CENTER);
        playerNameField.setBounds(47 * Main.WIDTH / 124, 179 * Main.HEIGHT / 558, 15 * Main.WIDTH / 62, 5 * Main.WIDTH / 124);
        add(playerNameField);
    }

    private void addTitle() {
        titleLabel = new JLabel("BlokuDoku", CENTER);
        titleLabel.setBounds(37 * Main.WIDTH / 124, 13 * Main.HEIGHT / 186, 25 * Main.WIDTH / 62, 40 * Main.WIDTH / 279);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("NewFont", BOLD, 2 * Main.WIDTH / 31));
        add(titleLabel);
    }

    private void addPlayButton() {
        startButton = new JButton("Rozpocznij");
        startButton.setBounds(47 * Main.WIDTH / 124, Main.HEIGHT / 2, 15 * Main.WIDTH / 62, 3 * Main.WIDTH / 62);
        startButton.setForeground(Color.blue);
        startButton.setBackground(Color.black);
        startButton.setFocusable(false);
        startButton.setFont(new Font("NewFont", BOLD, 9 * Main.WIDTH / 620));
        startButton.addActionListener(this);
        add(startButton);

    }

    private void addLeftButtons() {

        infoButton = new JButton("Informacje");
        infoButton.setBounds(38 * Main.WIDTH / 124, 19 * Main.HEIGHT / 31, 16 * Main.WIDTH / 124 - 20, 5 * Main.WIDTH / 124);
        infoButton.setForeground(Color.blue);
        infoButton.setBackground(Color.black);
        infoButton.setFocusable(false);
        infoButton.setFont(new Font("NewFont", BOLD, 3 * Main.WIDTH / 248 - 2));
        infoButton.addActionListener(this);
        add(infoButton);

        boardButton = new JButton("Tabela wyników");
        boardButton.setBounds(54 * Main.WIDTH / 124, 19 * Main.HEIGHT / 31, 16 * Main.WIDTH / 124, 5 * Main.WIDTH / 124);
        boardButton.setForeground(Color.blue);
        boardButton.setBackground(Color.black);
        boardButton.setFocusable(false);
        boardButton.setFont(new Font("NewFont", BOLD, 3 * Main.WIDTH / 248 - 2));
        boardButton.addActionListener(this);
        add(boardButton);

        optionsButton = new JButton("Opcje");
        optionsButton.setBounds(72 * Main.WIDTH / 124, 19 * Main.HEIGHT / 31, 16 * Main.WIDTH / 124 - 20, 5 * Main.WIDTH / 124);
        optionsButton.setForeground(Color.blue);
        optionsButton.setBackground(Color.black);
        optionsButton.setFocusable(false);
        optionsButton.setFont(new Font("NewFont", BOLD, 3 * Main.WIDTH / 248 - 2));
        optionsButton.addActionListener(this);
        add(optionsButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.white);
        g.fillRect(37 * Main.WIDTH / 124, 13 * Main.HEIGHT / 136, 25 * Main.WIDTH / 62, 200 * Main.HEIGHT / 279);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object src = e.getSource();

        if (src == startButton) {
            String name = playerNameField.getText();
            if (!name.equals("")) {
                Gameplay.name = name;
                Gameplay.isSoundTurnedOn = soundCheckBox.isSelected();
                Window.startGame();
            }
        } else if (src == boardButton) {
            Window.showScoreBoard();
        } else if (src == infoButton) {
            Window.showInfoPanel();
        } else if (src == optionsButton) {
            Window.showOptionMenu();
        }
    }
}
