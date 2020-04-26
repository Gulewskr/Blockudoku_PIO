package Blockudoku;

import javax.swing.JFrame;

public class Window extends JFrame
{

    public Window(int width, int height)
    {
        setTitle("Blockudoku");
        setSize(width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);
    }
}