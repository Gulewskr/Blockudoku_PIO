package Blockudoku;

import javax.swing.JFrame;

public class Window extends JFrame
{

    public Window(int width, int height)
    {
        Gameplay gameplay = new Gameplay(width, height);
        setTitle("Blockudoku");
        setBounds(0,0, width, height);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
        setLocationRelativeTo(null);

        add(gameplay);

    }
}