public class Main 
{
    //wielkość okna wszystkie elementy są skalowane względem okna
    //możemy przez to później wprowadzić opcję zmiany wielkości okna
    
    public static int width = 1240;
    public static int height = width * 9/16;
    
    public static void main(String[] args)
    {
        WindowGame PEPE = new WindowGame(width, height);
    }
}
