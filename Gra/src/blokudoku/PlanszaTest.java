package blokudoku;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class PlanszaTest {

    //test sprawdzania kolumny
    @Test
    public void TEST_CheckColumn() {
        // given
        Plansza p = new Plansza(200, 200);
        int expected = 1;
        int t;
        for(int i=0; i<9; i++)
            p.map[i][3]=1;

        // when
        t=p.checkColumn(3);

        // then
        Assert.assertEquals(expected, t);
    }

    //test sprawdzania wiersza
    @Test
    public void TEST_CheckRow() {
        // given
        Plansza p = new Plansza(200, 200);
        int expected = 1;
        int t;
        for(int i=0; i<9; i++)
            p.map[5][i]=1;

        // when
        t=p.checkRow(5);

        // then
        Assert.assertEquals(expected, t);
    }

    //test sprawdza wypełnie 3x3
    @Test
    public void TEST_Check3x3() {
        // given
        Plansza p = new Plansza(200, 200);
        int expected = 1;
        int t;
        for(int i=3; i<6;    i++)
            for(int j= 4; j<7; j++)
                p.map[i][j]=1;

        // when
        t=p.check3x3(3,4);

        // then
        Assert.assertEquals(expected, t);
    }

    //kilka testów sprawdzenia wypełnienia planszy
    @Test
    public void TEST_CheckBoard0() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        int ex = 27;
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 1);
        }

        // when
        p.sprawdz(arr);
        int checked = 0;

        for(int i=0; i<arr.length; i++)
           if(arr[i]==1) checked++;

        // then
        Assert.assertEquals(ex, checked);
    }

    @Test
    public void TEST_CheckBoard1() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        int ex = 5;
        for(int i=0;    i<9;    i+=2)
        {
            Arrays.fill(p.map[i], 1);
        }

        // when
        p.sprawdz(arr);
        int checked = 0;

        for(int i=0; i<arr.length; i++)
            if(arr[i]==1) checked++;

        // then
        Assert.assertEquals(ex, checked);
    }

    @Test
    public void TEST_CheckBoard2() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        int ex = 12;
        for(int i=0;    i<6;    i++)
        {
            Arrays.fill(p.map[i], 1);
        }

        // when
        p.sprawdz(arr);
        int checked = 0;

        for(int i=0; i<arr.length; i++)
            if(arr[i]==1) checked++;

        // then
        Assert.assertEquals(ex, checked);
    }


    //test usuwania wiersza
    @Test
    public void TEST_DeleteRow() {
        // given
        Plansza p = new Plansza(200, 200);
        int[][] arr = new int[9][9];
        for(int i=0;    i<9;    i++) {
            Arrays.fill(arr[i], 1);
            Arrays.fill(p.map[i], 1);
        }

        // when
        p.deleteRow(3);

        for(int i=0; i<9; i++)
            arr[3][i]=0;

        // then
        Assert.assertArrayEquals(arr, p.map);
    }

    //test usuwania kolumny
    @Test
    public void TEST_DeleteColumn() {
        // given
        Plansza p = new Plansza(200, 200);
        int[][] arr = new int[9][9];
        for(int i=0;    i<9;    i++) {
            Arrays.fill(arr[i], 1);
            Arrays.fill(p.map[i], 1);
        }

        // when
        p.deleteColumn(5);

        for(int i=0; i<9; i++)
            arr[i][5]=0;

        // then
        Assert.assertArrayEquals(arr, p.map);
    }

    //test usuwania plansz 3x3
    @Test
    public void TEST_Delete3x3() {
        // given
        Plansza p = new Plansza(200, 200);
        int[][] arr = new int[9][9];
        for(int i=0;    i<9;    i++) {
            Arrays.fill(arr[i], 1);
            Arrays.fill(p.map[i], 1);
        }

        // when
        p.delete3x3(0,0);
        p.delete3x3(6,6);

        for(int i=0; i<3; i++)
            for(int j=0; j<3; j++)
                arr[i][j]=0;
        for(int i=6; i<9; i++)
            for(int j=6; j<9; j++)
                arr[i][j]=0;

        // then
        Assert.assertArrayEquals(arr, p.map);
    }


    //kilka testów sprawdzających działanie funkcji odpowiedzialnych za sprawdzanie możliwości umieszczenia klocka
    @Test
    public void TEST_BlockInsert0() {
        // given
        Plansza p = new Plansza(200, 200);
        Klocek k = new Klocek(1, 20, 20, 10, 5);

        // when
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 0);
        }
        for(int i=0; i<3; i++)
        {
            Arrays.fill(k.zakres[i], 1);
        }

        // then
        Assert.assertFalse(p.sprawdzenie(k,2,1));
    }

    @Test
    public void TEST_BlockInsert1() {
        // given
        Plansza p = new Plansza(200, 200);
        Klocek k = new Klocek(1, 20, 20, 10, 5);

        // when
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 0);
        }
        for(int j=1; j<3; j++)
        for(int i=0; i<3; i++)
        {
            k.zakres[0][i]=1;
            k.zakres[j][i]=0;
        }


        // then
        Assert.assertTrue(p.sprawdzenie(k,6,10));
    }

    @Test
    public void TEST_BlockInsert2() {
        // given
        Plansza p = new Plansza(200, 200);
        Klocek k = new Klocek(1, 20, 20, 10, 5);

        // when
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 0);
        }
        for(int i=1; i<3; i++)
        {
            Arrays.fill(k.zakres[i], 1);
        }



        // then
        Assert.assertTrue(p.sprawdzenie(k,5,7));
    }

    @Test
    public void TEST_BlockInsert3() {
        // given
        Plansza p = new Plansza(200, 200);
        Klocek k = new Klocek(1, 20, 20, 10, 5);

        // when
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 0);
        }
        for(int i=1; i<3; i++)
        {
            Arrays.fill(k.zakres[i], 1);
        }

        // then
        Assert.assertFalse(p.sprawdzenie(k,1,1));
    }

    //Testy usuwania, zliczania combo
    @Test
    public void TEST_Delete_Free_Board() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];

        // when
        Arrays.fill(arr, 0);
        int removed = p.usun(arr);

        // then
        int expected = 0;
        Assert.assertEquals(expected, removed);
    }

    @Test
    public void TEST_DELETE_FULL_BOARD() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        int expected = arr.length;
        int[][] exTable = new int [9][9];

        // when
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 1);
            Arrays.fill(exTable[i],0);
        }
        Arrays.fill(arr, 1);
        int removed = p.usun(arr);

        // then
        Assert.assertEquals(expected, removed);
        Assert.assertArrayEquals(exTable, p.map);
    }

    @Test
    public void TEST_DELETE_5_3x3() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        int expected = 5;
        int examountofTile = 36;

        // when
        for(int i=0;    i<9;    i++)
        {
            Arrays.fill(p.map[i], 1);
        }
        for (int i = 0; i < 5; i++)
        {
            arr[i] = 1;
        }
        int removed = p.usun(arr);
        int amount=0;
        for(int i=0; i<9; i++)
            for(int j=0; j<9; j++)
                if(p.map[i][j]==1)
                    amount++;

        // then
        Assert.assertEquals(expected, removed);
        Assert.assertEquals(examountofTile, amount);
    }

    @Test
    public void usun_bledneDaneWTablicy_nieKasuje() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        for (int i = 1; i < arr.length; i += 2) {
            arr[i] = -111;
        }
        // when
        int removed = p.usun(arr);

        // then
        int expected = 0;
        Assert.assertEquals(expected, removed);
    }

    @Test(expected = NullPointerException.class)
    public void usun_pustaPlansza_rzucaWyjatek() throws IOException {
        // given
        Plansza p = null;
        int[] arr = new int[27];

        // when
        int removed = p.usun(arr);

        // then
        int expected = 0;
        Assert.assertEquals(expected, removed);
    }

    @Test(expected = NullPointerException.class)
    public void usun_niezainicjowanaTablica_rzucaWyjatek() throws IOException {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = null;

        // when
        int removed = p.usun(arr);

        // then
        int expected = 0;
        Assert.assertEquals(expected, removed);
    }


}
