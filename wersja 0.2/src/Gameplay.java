package Blockudoku;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.*;

public class Gameplay extends JLayeredPane
{
    private static final Klocek[] klocek = new Klocek[3];
    private static Plansza mapa;
    private final int width;
    private final int height;
    private boolean gameEND = false;
    private boolean gamePAUSED = true;
    private boolean powrot = false;
    private Movement[] mv =  new Movement[3];
    private int tick = 0;
    private Gui gui;

    public Gameplay(int width, int height)
    {
        this.width = width;
        this.height = height;
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        this.setName("Gra");
        start();
    }

    private void powrotMenu()
    {
        powrot = true;
        gui = null;
    }

    public synchronized void start()
    {
        gameEND = false;
        gui = new Gui(width, height, this);
        mapa = new Plansza(width, height);
        this.add(mapa);
        for (int i = 0; i < 3; i++)
        {
            klocek[i] = new Klocek(i + 1, width, height, mapa.getTileSize(), mapa.getTileBreak());
            this.add(klocek[i]);
        }
        for (int i = 0; i < 3; i++)
        {
            mv[i] = new Movement(klocek[i], mapa);
        }
    }

    public void tick()
    {
        if(!gameEND)
        {
            tick++;
            update();
            if (tick == 60) {
                gui.updateTimer();
                tick = 0;
            }
            if (gui.isKoniec())
            {
                gameEND = true;
                stop();
            }
        }else
        {
            if(gui.getOption1())
                start();
            else if(gui.getOption2())
            {
                powrotMenu();
            }
        }
    }

    public void update()
    {
        int[] s = new int[27];
        mapa.sprawdz(s);
        gui.updateScore(mapa.usun(s));
        if (checkBlockAmount() == 0)
        {
            nextRound();
        }
    }

    public synchronized void stop()
    {
        for (int i = 0; i < 3; i++) {
            mv[i] = null;
            this.remove(klocek[i]);
        }
        this.remove(mapa);
    }

    private int checkBlockAmount()
    {
        int x = 0;
        for (int i = 0; i < 3; i++)
            if (klocek[i].aktywny)
                x++;
        return x;
    }

    private void nextRound()
    {
        for (int i = 0; i < 3; i++) {
            klocek[i].odnowKlocek();
        }
        gui.nextRound();
    }

    public void paint(Graphics g)
    {
        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        mapa.draw(g);
        for(int i=0; i<3; i++)
        {
            klocek[i].draw(g);
        }
        gui.draw(g);
    }

    public boolean getStatus()
    {
        return powrot;
    }
}