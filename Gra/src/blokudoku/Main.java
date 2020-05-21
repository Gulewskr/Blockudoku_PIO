package blokudoku;

public class Main {

    public static int WIDTH = 1240;
    public static int HEIGHT = WIDTH * 9/16;
    private static Window PEPE;

    public static void main(String[] args)
    {
        PEPE = new Window(WIDTH, HEIGHT, false);
    }

    public static void changeWindowSize(int width)
    {
        PEPE.dispose();
        WIDTH = width;
        HEIGHT = width * 9/16;
        PEPE = new Window(WIDTH, HEIGHT, true);
    }
}