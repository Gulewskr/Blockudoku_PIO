package blokudoku;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.JPanel;

public class Plansza extends JPanel {
    public int[][] map = new int[9][9];
    private final int tileSize;
    private final int tileBreak;
    private static int WIDTHscreen;
    private static int HEIGHTscreen;
    private Color tileColor;
    private BufferedImage rainbowTileImage;
    private BufferedImage skinImage;

    public Plansza(int width, int height) {
        tileSize = 6 * height / 90;
        tileBreak = 2 * tileSize / 9;
        WIDTHscreen = 11 * width / 40;
        HEIGHTscreen = 4 * height / 100;
        this.setLayout(null);
        this.setBounds(WIDTHscreen, HEIGHTscreen, 9 * tileSize, 9 * tileSize);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                map[i][j] = 0;
            }
        }
        tileColor = Window.tileColor();
        rainbowTileImage = Window.rainbowTile();
        skinImage = Window.skinTile();
    }

    public int getTileSize() {
        return tileSize;
    }

    public int getTileBreak() {
        return tileBreak;
    }

    //Rysowanie planszy
    public void draw(Graphics2D grafika) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (map[i][j] == 1) {
                    grafika.setColor(tileColor);
                    if (grafika.getColor().getRGB() != new Color(0).getRGB()) {
                        grafika.fillRect(WIDTHscreen + j * tileSize, HEIGHTscreen + i * tileSize, tileSize - tileBreak, tileSize - tileBreak);
                        grafika.drawImage(skinImage, WIDTHscreen + j * tileSize, HEIGHTscreen + i * tileSize, tileSize - tileBreak, tileSize - tileBreak, null);
                    } else {
                        grafika.drawImage(rainbowTileImage, WIDTHscreen + j * tileSize, HEIGHTscreen + i * tileSize, tileSize - tileBreak, tileSize - tileBreak, null);
                        grafika.drawImage(skinImage, WIDTHscreen + j * tileSize, HEIGHTscreen + i * tileSize, tileSize - tileBreak, tileSize - tileBreak, null);
                    }
                } else if (((i > 2 && i < 6) && (j < 3 || j > 5)) || ((j > 2 && j < 6) && (i < 3 || i > 5))) {
                    grafika.setColor(Color.gray);
                    grafika.fillRect(WIDTHscreen + j * tileSize, HEIGHTscreen + i * tileSize, tileSize - tileBreak, tileSize - tileBreak);
                } else {
                    grafika.setColor(Color.white);
                    grafika.fillRect(WIDTHscreen + j * tileSize, HEIGHTscreen + i * tileSize, tileSize - tileBreak, tileSize - tileBreak);
                }
            }
        }

    }

    //Sprawdza pola 3x3 = t[0-8], wiersze = t[9-17] i kolumny = t[18-26] ,gdy uzupełnione nadaje wartość 1
    public void sprawdz(int[] t)
    {
        int x=0, y=0;
        for(int i=0; i<9; i++)
        {
            t[i]=check3x3(y,x);
            if((i+1)%3==0)
            {
                y=0;
                x+=3;
            }
            else y+=3;
        }
        for (int i = 0; i < 9; i++)
        {
            t[9 + i]=checkRow(i);
        }
        for (int i = 0; i < 9; i++)
        {
            t[18+i]=checkColumn(i);
        }
    }

    public int check3x3(int y, int x)
    {
        int tmp=0;
        for(int i=y; i<y+3 ;i++)
            for(int j=x; j<x+3 ;j++)
                if(map[i][j]==1)
                    tmp++;
        return tmp-8;
    }

    public int checkRow(int y)
    {
        int tmp=0;
        for(int i=0; i<9 ;i++)
            if(map[y][i]==1)
                tmp++;
        return tmp-8;
    }

    public int checkColumn(int x)
    {
        int tmp=0;
        for(int i=0; i<9 ; i++)
            if(map[i][x]==1)
                tmp++;
        return tmp-8;
    }

    public void delete3x3(int y, int x)
    {
        for(int i=y; i<y+3 ;i++)
            for(int j=x; j<x+3 ;j++)
                map[i][j]=0;
    }

    public void deleteColumn(int x)
    {
        for(int i=0; i<9; i++)
            map[i][x]=0;
    }

    public void deleteRow(int y)
    {
        for(int i=0; i<9; i++)
            map[y][i]=0;
    }

    //Usuwa pola analizując tabele wypełnioną funkcją sprawdz
    public int usun(int[] t) {
        int combo = 0;
        int x=0, y=0;
        for(int i=0; i<9; i++)
        {
            if(t[i]==1)
            {
                combo++;
                delete3x3(y, x);
            }
            if((i+1)%3==0)
            {
                y=0;
                x+=3;
            }
            else y+=3;
        }
        for(int i=9; i<18; i++)
        {
            if(t[i]==1)
            {
                combo++;
                deleteRow(i-9);
            }
        }
        for(int i=18; i<27; i++)
        {
            if(t[i]==1)
            {
                combo++;
                deleteColumn(i-18);
            }
        }
        return combo;
    }

    //Sprawdza czy można wstawić klocek jak ktoś wie jak to skrócić to poproszę pomysł xD
    public boolean sprawdzenie(Klocek block, int x, int y) {
        if(!checkBorders(block.zakres, x, y)) return false;
        switch (x) {
            case 0:
                switch (y)
                {
                    case 0:
                        if(!petlaY0X0(block.zakres,x,y))
                            return false;
                        break;
                    case 1:
                        if(!petlaY1X0(block.zakres,x,y))
                            return false;
                        break;
                    case 9:
                        if(!petlaY9X0(block.zakres,x,y))
                            return false;
                        break;
                    case 10:
                        if(!petlaY10X0(block.zakres,x,y))
                            return false;
                        break;
                    default:
                        if(!petlaX0(block.zakres,x,y))
                            return false;
                        break;
                }
                block.usunKlocek();
                return true;
            case 1:
                switch (y) {
                    case 0:
                        if(!petlaY0X1(block.zakres,x,y))
                            return false;
                        break;
                    case 1:
                        if(!petlaY1X1(block.zakres,x,y))
                            return false;
                        break;
                    case 9:
                        if(!petlaY9X1(block.zakres,x,y))
                            return false;
                        break;
                    case 10:
                        if(!petlaY10X1(block.zakres,x,y))
                            return false;
                        break;
                    default:
                        if(!petlaX1(block.zakres,x,y))
                            return false;
                        break;
                }
                block.usunKlocek();
                return true;
            case 9:
                switch (y) {
                    case 0:
                        if(!petlaY0X9(block.zakres,x,y))
                            return false;
                        break;
                    case 1:
                        if(!petlaY1X9(block.zakres,x,y))
                            return false;
                        break;
                    case 9:
                        if(!petlaY9X9(block.zakres,x,y))
                            return false;
                        break;
                    case 10:
                        if(!petlaY10X9(block.zakres,x,y))
                            return false;
                        break;
                    default:
                        if(!petlaX9(block.zakres,x,y))
                            return false;
                        break;
                }
                block.usunKlocek();
                return true;
            case 10:
                switch (y) {
                    case 0:
                        if(!petlaY0X10(block.zakres,x,y))
                            return false;
                        break;
                    case 1:
                        if(!petlaY1X10(block.zakres,x,y))
                            return false;
                        break;
                    case 9:
                        if(!petlaY9X10(block.zakres,x,y))
                            return false;
                        break;
                    case 10:
                        if(!petlaY10X10(block.zakres,x,y))
                            return false;
                        break;
                    default:
                        if(!petlaX10(block.zakres,x,y))
                            return false;
                        break;
                }
                block.usunKlocek();
                return true;
        }
        switch (y) {
            case 0:
                if(!petlaY0(block.zakres,x,y))
                    return false;
                block.usunKlocek();
                return true;
            case 1:
                if(!petlaY1(block.zakres,x,y))
                    return false;
                block.usunKlocek();
                return true;
            case 9:
                if(!petlaY9(block.zakres,x,y))
                    return false;
                block.usunKlocek();
                return true;
            case 10:
                if(!petlaY10(block.zakres,x,y))
                    return false;
                block.usunKlocek();
                return true;
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (block.zakres[i][j] == 1)
                    if ((map[y - 2 + i][x - 2 + j]) == 1)
                        return false;
            }
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (block.zakres[i][j] == 1)
                    map[y - 2 + i][x - 2 + j] = 1;
            }
        }
        block.usunKlocek();
        return true;
    }

    private boolean petlaY0X0(int[][] zakres, int x, int y)
    {
        for (int j = 2; j < 3; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 2; j < 3; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY1X0(int[][] zakres, int x, int y)
    {
        for (int j = 1; j < 3; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 1; j < 3; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY9X0(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 2; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 2; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY10X0(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 1; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 1; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY0X1(int[][] zakres, int x, int y)
    {
        for (int j = 2; j < 3; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 2; j < 3; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY1X1(int[][] zakres, int x, int y)
    {
        for (int j = 1; j < 3; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 1; j < 3; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY9X1(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 2; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 2; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY10X1(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 1; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 1; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY0X9(int[][] zakres, int x, int y)
    {
        for (int j = 2; j < 3; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 2; j < 3; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY1X9(int[][] zakres, int x, int y)
    {
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY9X9(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY10X9(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 1; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 1; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY0X10(int[][] zakres, int x, int y)
    {
        for (int j = 2; j < 3; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 2; j < 3; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY1X10(int[][] zakres, int x, int y)
    {
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY9X10(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY10X10(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 1; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 1; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY0(int[][] zakres, int x, int y)
    {
        for (int j = 2; j < 3; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY1(int[][] zakres, int x, int y)
    {
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 1; j < 3; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaY9(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 2; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }


    private boolean petlaY10(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 1; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 1; j++)
            for (int i = 0; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaX0(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 3; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 3; j++)
            for (int i = 2; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaX1(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 3; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 3; j++)
            for (int i = 1; i < 3; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean petlaX9(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 2; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }


    private boolean petlaX10(int[][] zakres, int x, int y)
    {
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    if ((map[y - 2 + j][x - 2 + i]) == 1)
                        return false;
        for (int j = 0; j < 3; j++)
            for (int i = 0; i < 1; i++)
                if (zakres[j][i] == 1)
                    map[y - 2 + j][x - 2 + i] = 1;
        return true;
    }

    private boolean checkBorders(int[][] zakres, int x, int y)
    {
        switch (x) {
            case 0:
                for (int i = 0; i < 3; i++)
                    if (zakres[i][0] == 1 || zakres[i][1] == 1)
                        return false;
                break;
            case 1:
                for (int i = 0; i < 3; i++)
                    if (zakres[i][0] == 1)
                        return false;
                break;
            case 9:
                for (int i = 0; i < 3; i++)
                    if (zakres[i][2] == 1)
                        return false;
                break;
            case 10:
                for (int i = 0; i < 3; i++)
                    if (zakres[i][2] == 1 || zakres[i][1] == 1)
                        return false;
                break;
        }
        switch (y) {
            case 0:
                for (int i = 0; i < 3; i++)
                    if (zakres[0][i] == 1 || zakres[1][i] == 1)
                        return false;
                break;
            case 1:
                for (int i = 0; i < 3; i++)
                    if (zakres[0][i] == 1)
                        return false;
                break;
            case 9:
                for (int i = 0; i < 3; i++)
                    if (zakres[2][i] == 1)
                        return false;
                break;
            case 10:
                for (int i = 0; i < 3; i++)
                    if (zakres[2][i] == 1 || zakres[1][i] == 1)
                        return false;
                break;
        }
        return true;
    }
}