package Blockudoku;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Gui implements MouseListener
{
    private int width;
    private int height;
    private int timer = 10;
    private int runda = 0;
    private int wynik = 0;
    private boolean koniec = false;
    private boolean reset = false;
    private boolean goToMenu = false;
    private JPanel panelWyniku;
    private JPanel panelKoncowy;
    private JLayeredPane gra;
    private Graphics g;
    private Image restartButton = Toolkit.getDefaultToolkit().getImage("src/images/restartButton.png");
    //private Image restartButton = Toolkit.getDefaultToolkit().getImage("src/images/restartD.png");
    private Image returnButton = Toolkit.getDefaultToolkit().getImage("src/images/returnButton.png");

    public Gui(int width, int height , JLayeredPane gra)
    {
        this.width = width;
        this.height = height;
        this.gra = gra;
        panelWyniku = new JPanel();
        panelKoncowy = new JPanel();
        panelKoncowy.setBounds( 87 * width / 200 - 100 , height / 3 - 100, 200, 200);
        panelWyniku.setBounds(7 * width / 10 - 5 , height / 5 - 5, width / 5 + 10, height / 3 + 10);
        gra.add(panelWyniku);
    }

    public void updateTimer()
    {
        if(timer == 0)
        {
            gra.add(panelKoncowy);
            panelKoncowy.addMouseListener(this);
            koniec = true;
        }
        if(!koniec)
            timer--;
    }

    public void updateScore(int combo)
    {
        if(combo!=0)
            wynik += combo * combo * 9 + runda;
    }

    public void nextRound()
    {
        runda++;
        resetTimer();
    }

    private void resetTimer()
    {
        timer += 7;
    }

    public boolean isKoniec()
    {
        return koniec;
    }

    public void draw(Graphics g)
    {
        drawScorePanel(g);
        if(koniec)
            drawEndPanel(g);
    }

    public void drawEndPanel(Graphics g)
    {
            g.setColor(Color.red);
            g.fillRect( 87 * width / 200 - 100 , height / 3 - 100, 200, 200);
            g.setColor(Color.white);
            g.fillRect( 87 * width / 200 - 95 , height / 3 - 95, 190, 190);
            g.setColor(Color.black);
            g.setFont(new Font("arial", Font.BOLD,   height / 20));
            g.drawString("Koniec Gry", 87 * width / 200 - 93 , height / 3 - 30);
            g.drawString("Wynik: " + wynik, 87 * width / 200 - 88 , height / 3 + height / 20 - 20);
            g.drawImage(restartButton, 87 * width / 200 - 60, height / 3 + 30, 50, 60, null);
            g.drawImage(returnButton, 87 * width / 200 + 10, height / 3 + 30, 50, 60, null);
    };

    public void drawScorePanel(Graphics g)
    {
        g.setColor(Color.red);
        g.fillRect(7 * width / 10 - 5 , height / 5 - 5, width / 5 + 10, height / 3 + 10);
        g.setColor(Color.white);
        g.fillRect(7 * width / 10 , height / 5, width / 5 , height / 3);
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD,   height / 20));
        g.drawString("Wynik: " + wynik, 72 * width / 100, height / 5 + ( height / 20 + 10));
        g.drawString("Runda: " + runda, 72 * width / 100, height / 5 + 2 * ( height / 20 + 10));
        g.drawString("Timer: " + timer, 72 * width / 100, height / 5 + 3 * ( height / 20 + 10));
    };

    public boolean getOption1()
    {
        if(reset)
        {
            reset = false;
            return true;
        }
        return false;
    }

    public boolean getOption2()
    {
        return goToMenu;
    }

    private void restart()
    {
        panelKoncowy.removeMouseListener(this);
        gra.remove(panelKoncowy);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        int X = e.getX() + e.getComponent().getX();
        int Y = e.getY() + e.getComponent().getY();
        if( Y > height / 3 + 30  && Y < height / 3 + 90 ) {
            if (X > 87 * width / 200 - 60 && X < 87 * width / 200 - 10 )
            {
                reset = true;
                restart();
                System.out.println("reset");
            } else if (X > 87 * width / 200 + 10 && X < 87 * width / 200 + 60) {
                goToMenu = true;
                System.out.println("return");
                restart();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
}
