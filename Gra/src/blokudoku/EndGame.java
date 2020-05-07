package blokudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Font.BOLD;

public class EndGame extends JPanel implements ActionListener
{
    private JButton returnButton, restartButton;
    private final int wynik;

    public EndGame(int wynik, JPanel gra)
    {
        setLayout(null);
        addReturnButton();
        addRestartButton();
        this.wynik = wynik;
        gra.getParent().add(this);
        setVisible(true);
    }

    private void addRestartButton() {
        restartButton = new JButton("R");
        restartButton.setBounds(87 * Main.WIDTH / 200 - 60, Main.HEIGHT / 3 + 30, 50, 60);
        restartButton.setForeground(Color.blue);
        restartButton.setBackground(Color.black);
        restartButton.setFocusable(false);
        restartButton.setFont(new Font("NewFont", BOLD, 18));
        restartButton.addActionListener(this);
        add(restartButton);
    }

    private void addReturnButton()
    {
        returnButton = new JButton("E");
        returnButton.setBounds(87 * Main.WIDTH / 200 + 10, Main.HEIGHT / 3 + 30, 50, 60);
        returnButton.setForeground(Color.blue);
        returnButton.setBackground(Color.black);
        returnButton.setFocusable(false);
        returnButton.setFont(new Font("NewFont", BOLD, 18));
        returnButton.addActionListener(this);
        add(returnButton);
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(87 * Main.WIDTH / 200 - 100,  Main.HEIGHT / 3 - 100, 200, 200);
        g.setColor(Color.white);
        g.fillRect(87 * Main.WIDTH / 200 - 95,  Main.HEIGHT / 3 - 95, 190, 190);
        g.setColor(Color.black);
        g.setFont(new Font("NewFont", BOLD, 18));
        g.drawString("Koniec Gry", 87 * Main.WIDTH  / 200 - 93 , Main.HEIGHT / 3 - 30);
        g.drawString("Wynik: " + wynik, 87 * Main.WIDTH  / 200 - 88 , Main.HEIGHT/ 3 + Main.HEIGHT / 20 - 20);

        g.setColor(Color.blue);
        g.fillRect(87 * Main.WIDTH / 200 + 10, Main.HEIGHT / 3 + 30, 50, 60);
        g.fillRect(87 * Main.WIDTH / 200 - 60, Main.HEIGHT / 3 + 30, 50, 60);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if (src == restartButton)
        {
            Window.startGame();
            setVisible(false);
        } else if( src == returnButton) {
            Window.back();
            setVisible(false);
        }
    }
}
