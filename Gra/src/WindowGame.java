package Blockudoku;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.lang.Thread;
import javax.swing.JFrame;

public class WindowGame extends JFrame
{
    
    public WindowGame(int width, int height)
    {
        Gameplay gameplay = new Gameplay(width, height);
        setTitle("Blockudoku");
        setLocationRelativeTo(null);
        setBounds(0,0, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        this.setFrameMouseActions();
        
        add(gameplay);
        
        Thread gameThread = new Thread(gameplay);
        gameThread.start();
    }

    //sprawdza czy myszka jest w oknie żeby klockek się nie zawieszał na brzegu
    //jak nie zresetuje się na "wyjściu myszki" to zrobi to raczej na "wejściu"

    public void setFrameMouseActions() 
    {
        this.addMouseListener(new MouseListener() 
        {
            public void mouseExited(MouseEvent e) 
            {
                for(int i=0; i<3; i++)
                    Gameplay.mv[i].zresetujKlocek();
            }
            
            public void mouseReleased(MouseEvent e) {}
            public void mousePressed(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) 
            {
                for(int i=0; i<3; i++)
                    Gameplay.mv[i].zresetujKlocek();
            }
            public void mouseClicked(MouseEvent e) {}
        }
        );
    }
}
