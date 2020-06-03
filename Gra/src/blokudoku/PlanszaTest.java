package blokudoku;

import java.io.IOException;
import java.util.Arrays;
import org.junit.Assert;
import org.junit.Test;

public class PlanszaTest {

    @Test
    public void usun_pustaPlansza_nieUsuwa() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        // when
        int removed = p.usun(arr);

        // then
        int expected = 0;
        Assert.assertEquals(expected, removed);
    }

    @Test
    public void usun_pelnaPlansza_usuwaWszystko() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        Arrays.fill(arr, 1);
        // when
        int removed = p.usun(arr);

        // then
        int expected = arr.length;
        Assert.assertEquals(expected, removed);
    }

    @Test
    public void usun_połowaZapelniona_usuwaPolowe() {
        // given
        Plansza p = new Plansza(200, 200);
        int[] arr = new int[27];
        for (int i = 1; i < arr.length; i += 2) {
            arr[i] = 1;
        }
        // when
        int removed = p.usun(arr);

        // then
        int expected = arr.length / 2;
        Assert.assertEquals(expected, removed);
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
