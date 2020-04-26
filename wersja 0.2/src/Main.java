package Blockudoku;

public class Main {

    public static int width = 1240;
    public static int height = width * 9/16;

    public static void main(String[] args)
    {
        Organizer program = new Organizer(width, height);
        Window PEPE = new Window(width, height);
        PEPE.add(program);
    }
}