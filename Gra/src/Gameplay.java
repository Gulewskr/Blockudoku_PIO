package Blockudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Runnable;
import java.lang.Thread;

import javax.swing.JPanel;

public class Gameplay extends JPanel implements Runnable
{
    private static Klocek[] klocek = new Klocek[3];
    private static Plansza mapa;
    private int width;
    private int height;
    private int timer = 10;
    private int tick = 0;
    private int runda = 0;
    private int wynik = 0;
    private boolean koniec;
    
    public static Movement mv[] = new Movement[3];
    
    public Gameplay(int width,int height)
    {
        this.width = width;
        this.height = height;
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        koniec = false;
        mapa = new Plansza(width, height);
        add(mapa);
        for(int i=0; i<3; i++)
        {
            klocek[i] = new Klocek(i+1 , width , height , mapa.getTileSize());
            add(klocek[i]);
        }
        for(int i=0; i<3; i++)
            mv[i] = new Movement(klocek[i]);
        mapa.przypiszMV(mv);
    }
    
    private void updateTimer()
    {
        if(timer == 0)
        {
            koniec = true;
            System.out.println("Koniec Gry");
            System.out.println("Runda: " + runda);
            System.out.println("Wynik: " + wynik);
        }
        if(!koniec)
        timer--;
    }
    
    public void update()
    {
        int s[]= new int [27];
        int c=0, combo;
        mapa.sprawdz(s);
        combo = (mapa.usun(s));
        if(combo!=0)
        {
            wynik += combo * combo * 9 + runda;
        }
        for(int i=0; i<3; i++)
        {
            if(!klocek[i].aktywny) 
            {
                c++;
            }
        }
        if(c==3)
        {
            for(int i=0; i<3; i++)
            {
                klocek[i].odnowKlocek();
                timer=10;
           }
           runda++;
        }
    }
    
    public void render()
    {
        repaint();
    }
    
    public void run()
    {   
        long lastTime = System.nanoTime();
        double nanoSecondConversion = 1000000000.0 / 60;
        double roznicaCzasu = 0;
        
        while(true)
        {
            long now = System.nanoTime();
            
            roznicaCzasu += (now - lastTime) / nanoSecondConversion;
            while(roznicaCzasu >= 1)
            {
                tick++;
                update();
                roznicaCzasu=0;
            }
            render();
            lastTime = now;
            if(tick == 60)
            {
                tick=0;
                updateTimer();
            }
        }
    }  
    
    public void paint(Graphics g)
    {
        super.paintComponent(g);
        
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        
        mapa.draw((Graphics2D)g);
        
        for(int i=0; i<3; i++)
            klocek[i].draw((Graphics2D)g);
        
        g.setColor(Color.red);
        g.fillRect(7 * width / 10 - 5 , height / 5 - 5, width / 5 + 10, height / 3 + 10);
        g.setColor(Color.white);
        g.fillRect(7 * width / 10 , height / 5, width / 5 , height / 3);
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD,   height / 20));
        g.drawString("Wynik: " + wynik, 72 * width / 100, height / 5 + ( height / 20 + 10));
        g.drawString("Runda: " + runda, 72 * width / 100, height / 5 + 2 * ( height / 20 + 10));
        g.drawString("Timer: " + timer, 72 * width / 100, height / 5 + 3 * ( height / 20 + 10));
        
        if(koniec)
        {
            g.setColor(Color.red);
            g.fillRect( 87 * width / 200 - 100 , height / 3 - 100, 200, 200);
            g.setColor(Color.white);
            g.fillRect( 87 * width / 200 - 95 , height / 3 - 95, 190, 190);
            g.setColor(Color.black);
            g.drawString("Koniec Gry", 87 * width / 200 - 93 , height / 3);
            g.drawString("Wynik: " + wynik, 87 * width / 200 - 79 , height / 3 + height / 20 + 10);
        }
    }

}
