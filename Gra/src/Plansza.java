package Blockudoku;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import javax.swing.JPanel;
        
public class Plansza extends JPanel implements MouseMotionListener, MouseListener
{
    public int map[][] = new int[9][9];
    public JPanel button[][] = new JPanel[11][11];
    private final int tileSIZE; 
    private static int WIDTHscreen;
    private static int HEIGHTscreen;
    private final Movement mv[] = new Movement [3];
    
    Plansza(int width, int height)
    {
        tileSIZE = (int) (6* height/90); 
        WIDTHscreen = (int)(0.275 * width) - tileSIZE;
        HEIGHTscreen = (int)(0.04 * height) - tileSIZE;
        this.setLayout(null);
        addMouseMotionListener(this);
        addMouseListener(this);
        this.setBounds(WIDTHscreen, HEIGHTscreen, 11*tileSIZE, 11*tileSIZE);
        for(int i=0; i<9 ; i++)
        {
            for(int j=0; j<9; j++)
            {
                map[i][j]=0;
            }
        }
        //tworzy pola użyte jako "przyciski" dookoła planszy
        for(int i=0; i<11 ; i++)
        {
            for(int j=0; j<11; j++)
            {
                button[i][j] = new JPanel();
                button[i][j].setBounds(j * tileSIZE, i * tileSIZE, tileSIZE - 10, tileSIZE - 10);
                button[i][j].setVisible(true);
                button[i][j].setName(i+"x"+j);
                this.add(button[i][j]);
                button[i][j].addMouseMotionListener(this);
                button[i][j].addMouseListener(this);
            }
        }
    }
    
    public int getTileSize()
    {
        return this.tileSIZE; 
    }
    
    public void przypiszMV(Movement t[])
    {
        System.arraycopy(t, 0, this.mv, 0, 3);
    }
    
    //Rysowanie planszy
    public void draw(Graphics2D grafika)
    {
        for(int i=0; i<9; i++)
        {
            for(int j=0; j<9; j++)
            {
                if(map[i][j]==1)
                {
                            grafika.setColor(Color.blue);
                            grafika.fillRect(WIDTHscreen + (j+1) * tileSIZE, HEIGHTscreen + (i+1) * tileSIZE , tileSIZE - 10, tileSIZE - 10);
                }
                else if(((i>2 && i<6) && (j<3 || j>5)) || ((j>2 && j<6) && (i<3 || i>5)))
                {
                           grafika.setColor(Color.gray);
                           grafika.fillRect(WIDTHscreen + (j+1) * tileSIZE, HEIGHTscreen + (i+1) * tileSIZE , tileSIZE - 10, tileSIZE - 10);
                }
                else
                {
                           grafika.setColor(Color.white);
                           grafika.fillRect(WIDTHscreen + (j+1) * tileSIZE, HEIGHTscreen + (i+1) * tileSIZE , tileSIZE - 10, tileSIZE - 10);
                }
            }
        }

    }

    //Sprawdza czy można wstawić klocek jak ktoś wie jak to skrócić to poproszę pomysł xD
    public boolean sprawdzenie(Klocek kloc, int x, int y)
    {
    switch(x)
        {
        case 0:for(int i=0; i<3 ; i++)
                    if(kloc.zakres[i][0]==1 || kloc.zakres[i][1]==1)
                         return false;
               break;
        case 1:for(int i=0; i<3 ; i++)
                   if(kloc.zakres[i][0]==1)
                        return false;
               break;
        case 9:for(int i=0; i<3 ; i++)
                   if(kloc.zakres[i][2]==1)
                        return false;
               break;
        case 10:for(int i=0; i<3 ; i++)
                  if(kloc.zakres[i][2]==1 || kloc.zakres[i][1]==1)
                        return false;
              break;
        }
    switch(y)
        {
        case 0:for(int i=0; i<3 ; i++)
                    if(kloc.zakres[0][i]==1 || kloc.zakres[1][i]==1)
                         return false;
               break;
        case 1:for(int i=0; i<3 ; i++)
                   if(kloc.zakres[0][i]==1)
                        return false;
               break;
        case 9:for(int i=0; i<3 ; i++)
                   if(kloc.zakres[2][i]==1)
                        return false;
               break;
        case 10:for(int i=0; i<3 ; i++)
                  if(kloc.zakres[2][i]==1 || kloc.zakres[1][i]==1)
                        return false;
              break;
        }    
    
    switch(x)
        {
        case 0:
            switch (y) 
            {
                case 0:
                    for(int j=2; j<3 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=2; j<3 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 1:
                    for(int j=1; j<3 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=1; j<3 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 9:
                    for(int j=0; j<2 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<2 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 10:
                    for(int j=0; j<1 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<1 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                default:
                    for(int j=0; j<3 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<3 ; j++)
                        for(int i=2; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
            }
            kloc.usunKlocek();
            return true;
        case 1:
            switch (y) 
            {
                case 0:
                    for(int j=2; j<3 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=2; j<3 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 1:
                    for(int j=1; j<3 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=1; j<3 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 9:
                    for(int j=0; j<2 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<2 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 10:
                    for(int j=0; j<1 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<1 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                break;
                default:
                    for(int j=0; j<3 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<3 ; j++)
                        for(int i=1; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
            }
            kloc.usunKlocek();
            return true;
        case 9:
            switch (y) 
            {
                case 0:
                    for(int j=2; j<3 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=2; j<3 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 1:
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 9:
                    for(int j=0; j<2 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<2 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 10:
                    for(int j=0; j<1 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<1 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                default:
                    for(int j=0; j<3 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<3 ; j++)
                        for(int i=0; i<2 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
            }
            kloc.usunKlocek();
            return true;
        case 10:
            switch (y) 
            {
                case 0:
                    for(int j=2; j<3 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=2; j<3 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 1:
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 9:
                    for(int j=0; j<2 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<2 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                case 10:
                    for(int j=0; j<1 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<1 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
                default:
                    for(int j=0; j<3 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<3 ; j++)
                        for(int i=0; i<1 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    break;
            }
            kloc.usunKlocek();
            return true;
        }
    
        switch (y) 
            {
                case 0:
                    for(int j=2; j<3 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    kloc.usunKlocek();
                    return true;
                case 1:
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=1; j<3 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    kloc.usunKlocek();
                    return true;
                case 9:
                    for(int j=0; j<2 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<2 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    kloc.usunKlocek();
                    return true;
                case 10:
                    for(int j=0; j<1 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                if((map[y-2+j][x-2+i])==1)
                                    return false;
                    for(int j=0; j<1 ; j++)
                        for(int i=0; i<3 ; i++)
                            if(kloc.zakres[j][i]==1)
                                map[y-2+j][x-2+i]=1;
                    kloc.usunKlocek();
                    return true;
            }
        
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(kloc.zakres[i][j]==1)
                    if((map[y-2+i][x-2+j])==1)
                        return false;
            }
        }
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(kloc.zakres[i][j]==1)
                    map[y-2+i][x-2+j]=1;
            }
        }
        
        kloc.usunKlocek();
        return true;
    }
    
    //Sprawdza pola 3x3 = t[0-8], wiersze = t[9-17] i kolumny = t[18-26] ,gdy uzupełnione nadaje wartość 1
    public void sprawdz(int t[])    
    {
        int x=0;
        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[0]=1;
        else 
            t[0]=0;
        x=0;
        for(int i=0; i<3; i++)
            for(int j=3; j<6; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[1]=1;
        else 
            t[1]=0;
        x=0;
        for(int i=0; i<3; i++)
            for(int j=6; j<9; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[2]=1;
        else
            t[2]=0;
        x=0;
        for(int i=3; i<6; i++)
            for(int j=0; j<3; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[3]=1;
        else 
            t[3]=0;
        x=0;
        for(int i=3; i<6; i++)
            for(int j=3; j<6; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[4]=1;
        else 
            t[4]=0;
        x=0;
        for(int i=3; i<6; i++)
            for(int j=6; j<9; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[5]=1;
        else 
            t[5]=0;
        x=0;
        for(int i=6; i<9; i++)
            for(int j=0; j<3; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[6]=1;
        else 
            t[6]=0;
        x=0;
        for(int i=6; i<9; i++)
            for(int j=3; j<6; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[7]=1;
        else 
            t[7]=0;
        x=0;
        for(int i=6; i<9; i++)
            for(int j=6; j<9; j++)
                if(map[i][j]==1)x++;
        if(x==9)
            t[8]=1;
        else 
            t[8]=0;        
        for(int i=0; i<9; i++)
        {
            x=0;
            for(int j=0; j<9; j++)
                if(map[i][j]==1)x++;
            if(x == 9) 
                t[9+i]=1;
            else 
                t[9+i]=0;
        }
        
        for(int i=0; i<9; i++)
        {
            x=0;
            for(int j=0; j<9; j++)
                if(map[j][i]==1)x++;
            if(x == 9) 
                t[18+i]=1;
            else 
                t[18+i]=0;
        }
    }
    
    //Usuwa pola analizując tabele wypełnioną funkcją sprawdz
    public int usun(int t[])       
    {
        int combo=0;
        for(int i=0; i<27; i++)
        {
            if(t[i]==1)
            {
               combo++; 
                if(i<9)
                  switch(i){
                    case 0:
                        for(int x=0; x<3; x++)
                           for(int j=0; j<3; j++)
                               map[x][j]=0;
                        break;
                    case 1:
                        for(int x=0; x<3; x++)
                           for(int j=3; j<6; j++)
                               map[x][j]=0;
                        break;
                    case 2:
                        for(int x=0; x<3; x++)
                           for(int j=6; j<9; j++)
                               map[x][j]=0;
                        break;
                    case 3:
                        for(int x=3; x<6; x++)
                           for(int j=0; j<3; j++)
                               map[x][j]=0;
                        break;
                    case 4:
                        for(int x=3; x<6; x++)
                           for(int j=3; j<6; j++)
                               map[x][j]=0;
                        break;
                    case 5:
                        for(int x=3; x<6; x++)
                           for(int j=6; j<9; j++)
                               map[x][j]=0;
                        break;
                    case 6:
                        for(int x=6; x<9; x++)
                           for(int j=0; j<3; j++)
                               map[x][j]=0;
                        break;
                    case 7:
                        for(int x=6; x<9; x++)
                           for(int j=3; j<6; j++)
                               map[x][j]=0;
                        break;
                    case 8:
                        for(int x=6; x<9; x++)
                           for(int j=6; j<9; j++)
                               map[x][j]=0;
                        break;
                }
                else if(i<18)
                {
                 for(int j=0; j<9; j++)
                     map[i-9][j]=0;
                }
                else
                {
                 for(int j=0; j<9; j++)
                     map[j][i-18]=0;
                }
            }
        }
        return combo;
    }
    
    @Override
    public void mouseDragged(MouseEvent e){}
    @Override
    public void mouseClicked(MouseEvent e){}
    @Override
    public void mouseReleased(MouseEvent e){}
    @Override
    public void mouseEntered(MouseEvent e){}
    @Override
    public void mouseExited(MouseEvent e){} 
    
    @Override
    public void mouseMoved(MouseEvent e)
    {
    //Ponieważ Klocek jest "pod Planszą" zmienia pozycję dla aktualnego klocka analizując położenie względem planszy lub pola na planszy 
        if(e.getComponent().getX()==WIDTHscreen)
        {
            for(int i=0; i<3; i++)
                if(mv[i].using && mv[i].out)
                    mv[i].kloc.setLocation(e.getComponent().getX() + e.getX() - mv[i].kloc.getHeight() / 2, e.getComponent().getY() + e.getY() - mv[i].kloc.getHeight() / 2);
        }
        else
        {
            for(int i=0; i<3; i++)
                if(mv[i].using && mv[i].out)
                    mv[i].kloc.setLocation(WIDTHscreen + e.getComponent().getX() + e.getX() - mv[i].kloc.getHeight() / 2, HEIGHTscreen + e.getComponent().getY() + e.getY() - mv[i].kloc.getHeight() / 2);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) 
    {
       if(e.getComponent().getName() == null)
       {
           for(int i=0; i<3; i++)
                if(mv[i].using && mv[i].out)
                    mv[i].zresetujKlocek();
       }
       else
       {
           int x = e.getComponent().getX()/tileSIZE;
           int y = e.getComponent().getY()/tileSIZE;
           for(int i=0; i<3; i++)
                if(mv[i].using)
                {
                    mv[i].zresetujKlocek();
                    sprawdzenie(mv[i].kloc, x, y);
                }
       }
    }  
}
