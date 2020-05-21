package blokudoku;

import javax.swing.*;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;
import static javax.swing.SwingConstants.CENTER;

public class EndGame extends JPanel implements ActionListener
{
    private JButton returnButton, restartButton;
    private int wynik = 0;

    public EndGame()
    {
        setLayout(null);
        addEndPanel();
        setBackground(Color.black);
    }

    private void addEndPanel()
    {
        JPanel endPanel = new JPanel();
        endPanel.setBounds( Main.HEIGHT/10 ,Main.HEIGHT/10, Main.WIDTH - 2*Main.HEIGHT/10 - 20, Main.HEIGHT - 2*Main.HEIGHT/10- 40);
        endPanel.setBackground(blue);
        endPanel.setLayout(null);
        endPanel.setName("endPanel");

        addEndPanelComponents(endPanel);

        add(endPanel);
    }

    private void addEndPanelComponents(JPanel panel)
    {
        addKoniecGryLabel(panel);
        addReturnButton(panel);
        addRestartButton(panel);
        addWynikGryLabel(panel);
    }

    private void addKoniecGryLabel(JPanel panel)
    {
        JLabel label = new JLabel("Koniec Gry", CENTER);
        label.setName("koniecGryLabel");
        label.setBounds(0, panel.getHeight()/20, panel.getWidth(), panel.getHeight()/6 );
        label.setForeground(white);
        label.setBackground(gray);
        label.setFont(new Font("NewFont", BOLD, panel.getHeight()/8));
        panel.add(label);
    }

    private void addWynikGryLabel(JPanel panel)
    {
        JLabel label = new JLabel("Wynik: "+ wynik, CENTER);
        label.setName("wynikGryLabel");
        label.setBounds(0, panel.getHeight()/4, 17*panel.getWidth()/20, panel.getHeight()/6 );
        label.setForeground(white);
        label.setBackground(gray);
        label.setFont(new Font("NewFont", BOLD, panel.getHeight()/8));
        panel.add(label);
    }

    public void setWynik(int wynik)
    {
        this.wynik = wynik;
        for(int i=0; i<this.getComponents().length; i++)
            if(getComponent(i).getName() == "endPanel")
                refreshWynik((JPanel) getComponent(i));
    }

    private void refreshWynik(JPanel panel)
    {
        for(int i=0; i<panel.getComponents().length; i++)
            if(panel.getComponent(i).getName() == "wynikGryLabel")
            {
                panel.remove(i);
                addWynikGryLabel(panel);
                return;
            }
    }

    private void addRestartButton(JPanel panel)
    {
        restartButton = new JButton("Zagraj ponownie");
        restartButton.setBounds(panel.getWidth()/10, 2*panel.getHeight()/3, panel.getWidth()/3, panel.getHeight()/4);
        restartButton.setForeground(Color.blue);
        restartButton.setBackground(Color.black);
        restartButton.setFocusable(false);
        restartButton.setFont(new Font("NewFont", BOLD, panel.getHeight()/15));
        restartButton.addActionListener(this);
        panel.add(restartButton);
    }

    private void addReturnButton(JPanel panel)
    {
        returnButton = new JButton("Powrót do Menu");
        returnButton.setBounds(6*panel.getWidth()/10, 2*panel.getHeight()/3, panel.getWidth()/3, panel.getHeight()/4);
        returnButton.setForeground(Color.blue);
        returnButton.setBackground(Color.black);
        returnButton.setFocusable(false);
        returnButton.setFont(new Font("NewFont", BOLD, panel.getHeight()/15));
        returnButton.addActionListener(this);
        panel.add(returnButton);
    }


    @Override
    protected void paintComponent(Graphics g)
    {
        super.paintComponent(g);
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
