import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Klocek extends JPanel
{
    private int width;
    private int height;
    private int tileSIZE;
    
    public int zakres[][] = new int [3][3];
    public int numerKlocka;
    public boolean aktywny;
    
    Klocek(int numer, int width, int height, int size)
    {
        this.aktywny = true;
        this.numerKlocka = numer;
        this.width=width;
        this.height=height;
        tileSIZE=size;
        this.setSize(3 * tileSIZE - 10, 3  * tileSIZE - 10);
        this.setName("Klocek :" + numer);
        this.setLayout(null);
        this.setBackground(new Color(0,0,0,0));
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                double x = Math.random();
                if(x > 0.5) zakres[i][j] = 1;
                else zakres[i][j] = 0;
            }
        }
        switch(numerKlocka)
        {
            case 1: 
                this.setBounds((int)(0.275 * width), 65 * height/ 90 , getWidth(), getHeight());
                break;
            case 2: 
                this.setBounds((int)(0.275 * width + 3 * tileSIZE), 65 * height/ 90 , getWidth(), getHeight());
                break;
            case 3: 
                this.setBounds((int)(0.275 * width + 6 * tileSIZE), 65 * height/ 90 , getWidth(), getHeight());
                break;
            default: 
                System.out.println("Nieobslugiwany numer klocka");
                break;
        }
    }
    
    public void draw(Graphics grafika)
    {
       switch(numerKlocka)
       {
            case 1: 
                grafika.setColor(new Color(0x8A2BE2));
                break;
            case 2: 
                grafika.setColor(new Color(0x00BFFF));
                break;
            case 3: 
                grafika.setColor(new Color(0x8A2BE2));
                break;
            default:
                System.out.println("Nieobslugiwany numer klocka");
                break;
       }
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                if(zakres[i][j]==1)
                {
                            grafika.fillRect(getLocation().x + j * tileSIZE , getLocation().y + i * tileSIZE , tileSIZE - 10, tileSIZE - 10 );
                }
            }
        }
    }
    
    public int getKlocekX()
    {
        return this.getBounds().width;
    }
    
    public int getKlocekY()
    {
        return this.getBounds().height;
    }
    
    public void resetKloca()
    {
    switch(numerKlocka)
        {
            case 1: 
                this.setBounds((int)(0.275 * width), 65 * height/ 90 , getWidth(), getHeight());
                break;
            case 2: 
                this.setBounds((int)(0.275 * width + 3 * tileSIZE), 65 * height/ 90 , getWidth(), getHeight());
                break;
            case 3: 
                this.setBounds((int)(0.275 * width + 6 * tileSIZE), 65 * height/ 90 , getWidth(), getHeight());
                break;
            default: 
                System.out.println("Nieobslugiwany numer klocka");
                break;
        }
    }
       
    //dezaktywuje klocek po umieszczeniu na plansze
    
    public void usunKlocek()
    {
        for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                zakres[i][j] = 0;
            }
        }
    aktywny=false;
    }
    
    public void odnowKlocek()
    {
    for(int i=0; i<3; i++)
        {
            for(int j=0; j<3; j++)
            {
                double x = Math.random();
                if(x > 0.5) zakres[i][j] = 1;
                else zakres[i][j] = 0;
            }
        }
    aktywny = true;
    }
}
