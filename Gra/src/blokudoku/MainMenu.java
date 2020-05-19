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
    private JButton startButton, boardButton, infoButton;
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
        soundLabel.setBounds(Main.WIDTH / 2 - 60, Main.HEIGHT / 2 - 70, 100, 20);
        add(soundLabel);

        soundCheckBox = new JCheckBox();
        soundCheckBox.setBounds(Main.WIDTH / 2 + 50, Main.HEIGHT / 2 - 70, 20, 20);
        soundCheckBox.doClick();

        soundCheckBox.addActionListener(this);
        add(soundCheckBox);
    }

    private void addName() {
        playerNameTip = new JLabel("Wprowadź swoje imię/nick:", CENTER);
        playerNameTip.setFont(new Font("NewFont", BOLD, 18));
        playerNameTip.setBounds(Main.WIDTH / 2 - 250, Main.HEIGHT / 2 - 150, 500, 20);
        add(playerNameTip);

        playerNameField = new JTextField("", CENTER);
        playerNameField.setFont(new Font("NewFont", BOLD, 18));
        playerNameField.setHorizontalAlignment(CENTER);
        playerNameField.setBounds(Main.WIDTH / 2 - 150, Main.HEIGHT / 2 - 125, 300, 50);
        add(playerNameField);
    }

    private void addTitle() {
        titleLabel = new JLabel("BlokuDoku", CENTER);
        titleLabel.setBounds(Main.WIDTH / 2 - 250, Main.HEIGHT / 2 - 300, 500, 100);
        titleLabel.setForeground(Color.BLUE);
        titleLabel.setFont(new Font("NewFont", BOLD, 80));
        add(titleLabel);
    }

    private void addPlayButton() {
        startButton = new JButton("Rozpocznij");
        startButton.setBounds(Main.WIDTH / 2 - 150, Main.HEIGHT / 2, 300, 60);
        startButton.setForeground(Color.blue);
        startButton.setBackground(Color.black);
        startButton.setFocusable(false);
        startButton.setFont(new Font("NewFont", BOLD, 18));
        startButton.addActionListener(this);
        add(startButton);

    }

    private void addLeftButtons() {

        infoButton = new JButton("Informacje");
        infoButton.setBounds(Main.WIDTH / 2 - 180, Main.HEIGHT / 2 + 100, 160, 50);
        infoButton.setForeground(Color.blue);
        infoButton.setBackground(Color.black);
        infoButton.setFocusable(false);
        infoButton.setFont(new Font("NewFont", BOLD, 15));
        infoButton.addActionListener(this);
        add(infoButton);

        boardButton = new JButton("Tabela wyników");
        boardButton.setBounds(Main.WIDTH / 2 + 20, Main.HEIGHT / 2 + 100, 160, 50);
        boardButton.setForeground(Color.blue);
        boardButton.setBackground(Color.black);
        boardButton.setFocusable(false);
        boardButton.setFont(new Font("NewFont", BOLD, 15));
        boardButton.addActionListener(this);
        add(boardButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.blue);
        g.fillRect(0, 0, Main.WIDTH, Main.HEIGHT);

        g.setColor(Color.white);
        g.fillRect(Main.WIDTH / 2 - 250, Main.HEIGHT / 2 - 300, 500, 500);
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
        }
    }
}
