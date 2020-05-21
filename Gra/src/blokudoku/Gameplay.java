package blokudoku;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.*;

import static java.awt.Font.BOLD;

public class Gameplay extends JPanel implements Runnable ,ActionListener{
    private static final Klocek[] klocek = new Klocek[3];

    private static Plansza mapa;
    private static JButton stopButton;
    private final int width;
    private final int height;
    private int timer = 10;
    private int frames = 0;
    private int runda = 0;
    private int wynik = 0;
    private boolean koniec;
    private boolean running;
    private ScoreBoard scoreBoard;
    private Thread gameThread;

    public static boolean isSoundTurnedOn;
    public static MusicPlayer mainThemeThread;
    public static Movement[] mv = new Movement[3];
    public static String name;


    public Gameplay(int width, int height, ScoreBoard scoreBoard) {
        this.width = width;
        this.height = height;
        this.scoreBoard = scoreBoard;
        this.setBounds(0, 0, width, height);
        this.setLayout(null);
        this.setBackground(Color.black);
        addStopButton();
        mapa = new Plansza(width, height);
        for (int i = 0; i < 3; i++) {
            klocek[i] = new Klocek(i + 1, width, height, mapa.getTileSize(), mapa.getTileBreak());
            add(klocek[i]);
        }
        for (int i = 0; i < 3; i++) {
            mv[i] = new Movement(klocek[i], mapa);
        }
        setVisible(true);
    }

    private void updateTimer() {
        if (timer == 0) {
            koniec = true;
            for (int i = 0; i < 3; i++) {
                klocek[i].aktywny = false;
                klocek[i].resetKloca();
            }
            System.out.println("Koniec Gry");
            System.out.println("Runda: " + runda);
            System.out.println("Wynik: " + wynik);
            scoreBoard.addScore(name, wynik);
            stop();
        }
        if (!koniec)
            timer--;
    }

    public synchronized void start() {
        gameThread = new Thread(this);

        if (isSoundTurnedOn)
            if (mainThemeThread == null) {
                mainThemeThread = new MusicPlayer();
                mainThemeThread.start();
            } else {
                mainThemeThread.resumeMusic();
            }
        gameThread.start();
        running = true;
    }

    public synchronized void stop() {
        try {
            if (isSoundTurnedOn){
                Sounds.playGameOverSound();
                mainThemeThread.stopMusic();
            }
            running = false;
            for (int i = 0; i < 3; i++) {
                mv[i] = null;
            }
            Window.setEndGame(wynik);
            gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void update() {
        int[] s = new int[27];
        int c = 0, combo;
        mapa.sprawdz(s);
        combo = (mapa.usun(s));
        if (combo != 0) {
            wynik += combo * combo * 9 + runda;
            if (isSoundTurnedOn)
                Sounds.playGamePointSound();
        }
        for (int i = 0; i < 3; i++) {
            if (!klocek[i].aktywny) {
                c++;
            }
        }
        if (c == 3) {
            for (int i = 0; i < 3; i++) {
                klocek[i].odnowKlocek();
            }
            timer += 7;
            runda++;
        }

    }

    public void render()
    {
        repaint();
    }

    public void run() {
        long lastTime = System.nanoTime();
        long msTimer = System.currentTimeMillis();
        double nsConvert = 1000000000.0 / 60;
        double deltaT = 0;

        while (running)
        {
            long now = System.nanoTime();
            deltaT += (now - lastTime) / nsConvert;
            lastTime = now;
            while (deltaT >= 1) {
                update();
                deltaT--;
            }
            if (running)
                render();
            frames++;

            if (System.currentTimeMillis() - msTimer > 1000) {
                msTimer += 1000;
                updateTimer();
                System.out.println("FPS: " + frames);
                frames = 0;
            }
        }
        stop();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);
        mapa.draw((Graphics2D) g);
        for (int i = 0; i < 3; i++)
        {
            klocek[i].draw(g);
        }
        stopButton.repaint();
        g.setColor(Color.red);
        g.fillRect(863*width/1240, 269*height/1395, 129*width/620, 97*height/279);
        g.setColor(Color.white);
        g.fillRect(7*width/10, height/5, width/5, height/3);
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, height/20));
        g.drawString("Wynik: " + wynik, 72*width/100, height/5 + (359*height/5580));
        g.drawString("Runda: " + runda, 72*width/100, height/5 + 2*(359*height/5580));
        g.drawString("Timer: " + timer, 72*width/100, height/5 + 3*(359*height/5580));
    }

    private void addStopButton() {
        stopButton = new JButton("Zakończ");
        stopButton.setBounds(86*Main.WIDTH/124 , 8*Main.HEIGHT/11, 15*Main.WIDTH/62, 3*Main.WIDTH/62);
        stopButton.setForeground(Color.blue);
        stopButton.setBackground(Color.black);
        stopButton.setFont(new Font("NewFont", BOLD, 9*Main.WIDTH/620));
        stopButton.addActionListener(this);
        this.add(stopButton);
        stopButton.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if (src == stopButton) {
            timer = 0;
        }
    }
}