package Blockudoku;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import javax.swing.JPanel;

public class Klocek extends JPanel
{
    private int width;
    private int height;
    private int tileSIZE;
    
    public int zakres[][] = new int[3][3];
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
        nadajRoksane();
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
    
    private void nadajRoksane()
    {
        Random r = new Random();
        int tmp = r.nextInt(12);
        double t = Math.random();
        switch(tmp+1)
        {
            case 1:
                if(t<0.5)
                    this.zakres = new int[][] {{0,1,0},{0,1,0},{0,1,0}};
                else
                    this.zakres = new int[][] {{0,0,0},{1,1,1},{0,0,0}};
                break;
            case 2:
                if(t<0.25) this.zakres = new int[][] {{0,1,0},{1,1,0},{0,1,0}};
                else if(t<0.5) this.zakres = new int[][] {{0,1,0},{1,1,1},{0,0,0}};
                else if(t<0.75) this.zakres = new int[][] {{0,1,0},{0,1,1},{0,1,0}};
                else this.zakres = new int[][] {{0,0,0},{1,1,1},{0,1,0}};
                break;
            case 3:
                if(t<0.25) this.zakres = new int[][] {{0,0,0},{1,1,0},{0,1,0}};
                else if(t<0.5) this.zakres = new int[][] {{0,1,0},{1,1,0},{0,0,0}};
                else if(t<0.75) this.zakres = new int[][] {{0,1,0},{0,1,1},{0,0,0}};
                else this.zakres = new int[][] {{0,0,0},{0,1,1},{0,1,0}};
                break;
            case 4:
                this.zakres = new int[][] {{0,0,0},{0,1,0},{0,0,0}};
                break;
            case 5:
                this.zakres = new int[][] {{0,1,0},{1,1,1},{0,1,0}};
                break;
            case 6:
                this.zakres = new int[][] {{1,1,0},{1,1,0},{0,0,0}};
                break;
            case 7:
                if(t<0.25) this.zakres = new int[][] {{1,1,1},{1,0,1},{0,0,0}};
                else if(t<0.5) this.zakres = new int[][] {{0,1,1},{0,0,1},{0,1,1}};
                else if(t<0.75) this.zakres = new int[][] {{0,0,0},{1,0,1},{1,1,1}};
                else this.zakres = new int[][] {{1,1,0},{1,0,0},{1,1,0}};
                break;
            case 8:
                if(t<0.25) this.zakres = new int[][] {{1,1,1},{1,0,0},{1,0,0}};
                else if(t<0.5) this.zakres = new int[][] {{1,1,1},{0,0,1},{0,0,1}};
                else if(t<0.75) this.zakres = new int[][] {{0,0,1},{0,0,1},{1,1,1}};
                else this.zakres = new int[][] {{1,0,0},{1,0,0},{1,1,1}};
                break;
            case 9:
                if(t<0.25) this.zakres = new int[][] {{1,1,1},{0,1,0},{0,1,0}};
                else if(t<0.5) this.zakres = new int[][] {{0,0,1},{1,1,1},{0,0,1}};
                else if(t<0.75) this.zakres = new int[][] {{0,1,0},{0,1,0},{1,1,1}};
                else this.zakres = new int[][] {{1,0,0},{1,1,1},{1,0,0}};
                break;
            case 10:
                if(t<0.25) this.zakres = new int[][] {{0,0,0},{1,1,1},{1,0,0}};
                else if(t<0.5) this.zakres = new int[][] {{1,1,0},{0,1,0},{0,1,0}};
                else if(t<0.75) this.zakres = new int[][] {{0,0,1},{1,1,1},{0,0,0}};
                else this.zakres = new int[][] {{0,1,0},{0,1,0},{0,1,1}};
                break;
            case 11:
                if(t<0.5) this.zakres = new int[][] {{0,0,1},{1,1,1},{1,0,0}};
                else this.zakres = new int[][] {{1,1,0},{0,1,0},{0,1,1}};
                break;
            case 12:
                if(t<0.5) this.zakres = new int[][] {{0,0,1},{0,1,1},{0,1,0}};
                else this.zakres = new int[][] {{1,1,0},{0,1,1},{0,0,0}};
                break;
        }
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
    nadajRoksane();
    aktywny = true;
    }
}
