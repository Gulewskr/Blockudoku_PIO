package blokudoku;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;
import javax.swing.JPanel;

public class Klocek extends JPanel {
    private final int width;
    private final int height;
    private final int tileSize;
    private final int tileBreak;

    public int[][] zakres = new int[3][3];
    public int numerKlocka;
    public boolean aktywny;

    private Color tileColor;
    private BufferedImage rainbowTileImage, skinTileImage;

    Klocek(int numer, int width, int height, int tileSize, int tileBreak) {
        this.aktywny = true;
        this.numerKlocka = numer;
        this.width = width;
        this.height = height;
        this.tileSize = tileSize;
        this.tileBreak = tileBreak;
        this.setSize(3 * tileSize - tileBreak, 3 * tileSize - tileBreak);
        this.setName("Klocek :" + numer);
        this.setLayout(null);
        this.setBackground(new Color(0, 0, 0, 0));
        nadajWartosc();
        switch (numerKlocka) {
            case 1:
                this.setBounds((int) (0.275 * width), 65 * height / 90, getWidth(), getHeight());
                break;
            case 2:
                this.setBounds((int) (0.275 * width + 3 * tileSize), 65 * height / 90, getWidth(), getHeight());
                break;
            case 3:
                this.setBounds((int) (0.275 * width + 6 * tileSize), 65 * height / 90, getWidth(), getHeight());
                break;
            default:
                System.out.println("Nieobslugiwany numer klocka");
                break;
        }
        tileColor = Window.tileColor();
        rainbowTileImage = Window.rainbowTile();
        skinTileImage = Window.skinTile();
    }

    public void draw(Graphics grafika) {
        switch (numerKlocka) {
            case 1:
            case 3:
                grafika.setColor(tileColor);
                break;
            case 2:
                grafika.setColor(new Color(tileColor.getRGB() + 200));
                break;
            default:
                System.out.println("Nieobslugiwany numer klocka");
                break;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (zakres[i][j] == 1) {
                    if (grafika.getColor().getRGB() != new Color(0).getRGB() && grafika.getColor().getRGB() != new Color(200).getRGB()) {
                        grafika.fillRect(getLocation().x + j * tileSize, getLocation().y + i * tileSize, tileSize - tileBreak, tileSize - tileBreak);
                        grafika.drawImage(skinTileImage, getLocation().x + j * tileSize, getLocation().y + i * tileSize, tileSize - tileBreak, tileSize - tileBreak, null);
                    } else {
                        grafika.drawImage(rainbowTileImage, getLocation().x + j * tileSize, getLocation().y + i * tileSize, tileSize - tileBreak, tileSize - tileBreak, null);
                        grafika.drawImage(skinTileImage, getLocation().x + j * tileSize, getLocation().y + i * tileSize, tileSize - tileBreak, tileSize - tileBreak, null);
                    }
                }
            }
        }
    }

    public void resetKloca() {
        switch (numerKlocka) {
            case 1:
                this.setBounds((int) (0.275 * width), 65 * height / 90, getWidth(), getHeight());
                break;
            case 2:
                this.setBounds((int) (0.275 * width + 3 * tileSize), 65 * height / 90, getWidth(), getHeight());
                break;
            case 3:
                this.setBounds((int) (0.275 * width + 6 * tileSize), 65 * height / 90, getWidth(), getHeight());
                break;
            default:
                System.out.println("Nieobslugiwany numer klocka");
                break;
        }
    }

    public void usunKlocek() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                zakres[i][j] = 0;
            }
        }
        aktywny = false;
    }

    public void odnowKlocek() {
        nadajWartosc();
        aktywny = true;
    }

    private void nadajWartosc() {
        Random r = new Random();
        int tmp = r.nextInt(12);
        double t = Math.random();
        switch (tmp + 1) {
            case 1:
                if (t < 0.5)
                    this.zakres = new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 0}};
                else
                    this.zakres = new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 0, 0}};
                break;
            case 2:
                if (t < 0.25) this.zakres = new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 1, 0}};
                else if (t < 0.5) this.zakres = new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 0, 0}};
                else if (t < 0.75) this.zakres = new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 1, 0}};
                else this.zakres = new int[][]{{0, 0, 0}, {1, 1, 1}, {0, 1, 0}};
                break;
            case 3:
                if (t < 0.25) this.zakres = new int[][]{{0, 0, 0}, {1, 1, 0}, {0, 1, 0}};
                else if (t < 0.5) this.zakres = new int[][]{{0, 1, 0}, {1, 1, 0}, {0, 0, 0}};
                else if (t < 0.75) this.zakres = new int[][]{{0, 1, 0}, {0, 1, 1}, {0, 0, 0}};
                else this.zakres = new int[][]{{0, 0, 0}, {0, 1, 1}, {0, 1, 0}};
                break;
            case 4:
                this.zakres = new int[][]{{0, 0, 0}, {0, 1, 0}, {0, 0, 0}};
                break;
            case 5:
                this.zakres = new int[][]{{0, 1, 0}, {1, 1, 1}, {0, 1, 0}};
                break;
            case 6:
                this.zakres = new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 0}};
                break;
            case 7:
                if (t < 0.25) this.zakres = new int[][]{{1, 1, 1}, {1, 0, 1}, {0, 0, 0}};
                else if (t < 0.5) this.zakres = new int[][]{{0, 1, 1}, {0, 0, 1}, {0, 1, 1}};
                else if (t < 0.75) this.zakres = new int[][]{{0, 0, 0}, {1, 0, 1}, {1, 1, 1}};
                else this.zakres = new int[][]{{1, 1, 0}, {1, 0, 0}, {1, 1, 0}};
                break;
            case 8:
                if (t < 0.25) this.zakres = new int[][]{{1, 1, 1}, {1, 0, 0}, {1, 0, 0}};
                else if (t < 0.5) this.zakres = new int[][]{{1, 1, 1}, {0, 0, 1}, {0, 0, 1}};
                else if (t < 0.75) this.zakres = new int[][]{{0, 0, 1}, {0, 0, 1}, {1, 1, 1}};
                else this.zakres = new int[][]{{1, 0, 0}, {1, 0, 0}, {1, 1, 1}};
                break;
            case 9:
                if (t < 0.25) this.zakres = new int[][]{{1, 1, 1}, {0, 1, 0}, {0, 1, 0}};
                else if (t < 0.5) this.zakres = new int[][]{{0, 0, 1}, {1, 1, 1}, {0, 0, 1}};
                else if (t < 0.75) this.zakres = new int[][]{{0, 1, 0}, {0, 1, 0}, {1, 1, 1}};
                else this.zakres = new int[][]{{1, 0, 0}, {1, 1, 1}, {1, 0, 0}};
                break;
            case 10:
                if (t < 0.25) this.zakres = new int[][]{{0, 0, 0}, {1, 1, 1}, {1, 0, 0}};
                else if (t < 0.5) this.zakres = new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 0}};
                else if (t < 0.75) this.zakres = new int[][]{{0, 0, 1}, {1, 1, 1}, {0, 0, 0}};
                else this.zakres = new int[][]{{0, 1, 0}, {0, 1, 0}, {0, 1, 1}};
                break;
            case 11:
                if (t < 0.5) this.zakres = new int[][]{{0, 0, 1}, {1, 1, 1}, {1, 0, 0}};
                else this.zakres = new int[][]{{1, 1, 0}, {0, 1, 0}, {0, 1, 1}};
                break;
            case 12:
                if (t < 0.5) this.zakres = new int[][]{{0, 0, 1}, {0, 1, 1}, {0, 1, 0}};
                else this.zakres = new int[][]{{1, 1, 0}, {0, 1, 1}, {0, 0, 0}};
                break;
        }
    }
}
