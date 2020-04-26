package Blockudoku;

import java.awt.Color;
import java.awt.Graphics;

import java.lang.Runnable;
import java.lang.Thread;

import javax.swing.JPanel;

public class Organizer extends JPanel implements Runnable
{
    private final int width;
    private final int height;
    private boolean running;
    private Thread watekGlowny;
    private enum Status
    {
        Game,
        Menu,
        Option
    };
    private Status status = Status.Menu;
    private Gameplay gra;
    private Menu menu;

    public Organizer(int width,int height)
    {
        this.width = width;
        this.height = height;
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        menu = new Menu(height, width, this);
        start();
    }

    public synchronized void start()
    {
        watekGlowny = new Thread(this);
        watekGlowny.start();
        running = true;
    }

    public void run()
    {

        long lastTime = System.nanoTime();
        double nsConvert = 1000000000.0 / 60;
        double deltaT = 0;

        while(running)
        {
            long now = System.nanoTime();
            deltaT += (now - lastTime) / nsConvert;
            lastTime = now;

            if(running)
                render();

            while(deltaT >= 1)
            {
                update();
                deltaT--;
            }
        }

        stop();
    }

    public synchronized void stop()
    {
        try
        {
            watekGlowny.join();
        } catch(InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update()
    {
        if(this.status == Status.Game)
        {
            gra.tick();
            if(gra.getStatus())
            {
                changeStatusFromGameToMenu();
            }
        }
        if(this.status == Status.Menu)
        {
            menu.update();
            if(menu.play())
            {
                changeStatusFromMenuToGame();
            }
        }
        if(this.status == Status.Option)
        {

        }
    }

    private void gameStart()
    {
        gra = new Gameplay(width, height);
        add(gra);
    }

    private void gameStop()
    {
        gra = null;
    }

    public void render()
    {
        repaint();
    }

    public void paint(Graphics g)
    {
        super.paintComponent(g);

        switch (this.status) {
            case Game:
                try {
                    gra.paint(g);
                    break;
                }catch(Exception e)
                {
                    System.out.println(e);
                    break;
                }
            case Menu:
                try {
                    menu.paint(g);
                    break;
                }catch(Exception e)
                {
                     System.out.println(e);
                     break;
                }
            case Option:
                try {
                    break;
                }catch(Exception e)
                {
                    System.out.println(e);
                    break;
                }
            default:
                g.setColor(Color.black);
                g.fillRect(0, 0, width, height);
                break;
        }
    }

    private void changeStatusFromMenuToGame()
    {
        status = Status.Game;
        gameStart();
        remove(menu);
        removeMouseListener(menu);
    }
    private void changeStatusFromMenuToOption() {}
    private void changeStatusFromGameToMenu()
    {
        status = Status.Menu;
        gameStop();
        addMouseListener(menu);
        try {
            remove(gra);
            add(menu);
        }catch (Exception e) {
        System.out.println(e);
        }
    }
    private void changeStatusFromGameToOption() {}
    private void changeStatusOptionToMenu() {}

}
