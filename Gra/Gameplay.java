import java.awt.Color;
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
    
    public static Movement mv[] = new Movement[3];
    
    public Gameplay(int width,int height)
    {
        this.width = width;
        this.height = height;
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
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
    
    public void update()
    {
        int s[]= new int [27];
        int c=0;
        mapa.sprawdz(s);
        mapa.usun(s);
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
           }
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
                update();
                roznicaCzasu=0;
            }
            render();
            lastTime = now;
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
    }

}
