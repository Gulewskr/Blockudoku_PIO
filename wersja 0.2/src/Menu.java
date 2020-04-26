package Blockudoku;

import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Menu extends JPanel implements MouseListener
{

    private int height;
    private int width;
    private Image startButton = Toolkit.getDefaultToolkit().getImage("src/images/startButton.png");
    private Image optionButton = Toolkit.getDefaultToolkit().getImage("src/images/optionButton.png");
    private Image exitButton = Toolkit.getDefaultToolkit().getImage("src/images/exitButton.png");
    private boolean play = false;

    public Menu(int height, int width, JPanel okno)
    {
        this.height = height;
        this.width = width;
        okno.add(this);
        okno.addMouseListener(this);
    }

    public void update()
    {
    }

    public void paint(Graphics g)
    {
        g.setColor(new Color(0xF167EE));
        g.fillRect(0,0,width,height);
        g.drawImage(startButton,width / 3 , height / 5 , width / 3 , height / 7, null);
        g.drawImage(optionButton,width / 3 , height / 5  + 3 * height / 14, width / 3 , height / 7, null);
        g.drawImage(exitButton,width / 3 , height / 5  + 6 * height / 14, width / 3 , height / 7, null);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int X = e.getX();
        int Y = e.getY();
        if(X > width / 3 && X < 2 * width / 3)
            if(Y > height / 5 && Y < height / 5 + height / 7)
            {
                System.out.println("PLAY");
                play = true;
            }
            else if(Y > height / 5  + 3 * height / 14 && Y < height / 5  + 3 * height / 14 + height / 7)
            {
                System.out.println("OPTION");
            }
            else if(Y > height / 5  + 6 * height / 14 && Y < height / 5  + 6 * height / 14 + height / 7)
            {
                System.out.println("EXIT");
                System.exit(0);
            }
    }

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    public boolean play()
    {
        if(play){
            play = false;
            return true;
        }else
        return play;
    }
}
