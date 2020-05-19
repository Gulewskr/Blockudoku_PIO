package blokudoku;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;

public class Gameplay extends JPanel implements Runnable {
    private static final Klocek[] klocek = new Klocek[3];

    private static Plansza mapa;
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
    private EndGame endpanel;

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
        mapa = new Plansza(width, height);
        add(mapa);
        for (int i = 0; i < 3; i++) {
            klocek[i] = new Klocek(i + 1, width, height, mapa.getTileSize(), mapa.getTileBreak());
            add(klocek[i]);
        }
        for (int i = 0; i < 3; i++) {
            mv[i] = new Movement(klocek[i], mapa);
        }
    }

    private void updateTimer() {
        if (timer == 0) {
            koniec = true;
            for (int i = 0; i < 3; i++) {
                klocek[i].aktywny = false;
                klocek[i].resetKloca();
            }
            endpanel = new EndGame(wynik, this);
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

            gameThread.join();
            running = false;
            for (int i = 0; i < 3; i++) {
                mv[i] = null;
            }
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

    public void render() {
        repaint();
    }

    public void run() {
        long lastTime = System.nanoTime();
        long msTimer = System.currentTimeMillis();
        double nsConvert = 1000000000.0 / 60;
        double deltaT = 0;

        while (running) {
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

    public void paint(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.black);
        g.fillRect(0, 0, width, height);

        mapa.draw((Graphics2D) g);

        for (int i = 0; i < 3; i++) {
            klocek[i].draw(g);
        }

        g.setColor(Color.red);
        g.fillRect(7 * width / 10 - 5, height / 5 - 5, width / 5 + 10, height / 3 + 10);
        g.setColor(Color.white);
        g.fillRect(7 * width / 10, height / 5, width / 5, height / 3);
        g.setColor(Color.black);
        g.setFont(new Font("arial", Font.BOLD, height / 20));
        g.drawString("Wynik: " + wynik, 72 * width / 100, height / 5 + (height / 20 + 10));
        g.drawString("Runda: " + runda, 72 * width / 100, height / 5 + 2 * (height / 20 + 10));
        g.drawString("Timer: " + timer, 72 * width / 100, height / 5 + 3 * (height / 20 + 10));
    }
}