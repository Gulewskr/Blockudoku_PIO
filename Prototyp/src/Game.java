import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Game extends JFrame implements ActionListener {
    public final static int NUMBEROFSHAPES = 6;     //stała liczba mówiąca ile mamy różnych kształtów

    private int index1, index2, index3;            //indexy ksztatów, które będą na przyciskach (będą losowane)
    private int numberOfField = 0;                 //ilość wstawionych przez gracza pól
    private int score = 0;

    private boolean b1, b2, b3, isEnd;                   //oznacznik czy dane figury były już wstawiane
    private boolean[] isOccupied;                 //które przyciski były naciśnięte w całej grze
    private boolean[] isSetNow;                   //które podczas wstawiania jednej figury

    private JButton bFig1, bFig2, bFig3, chosenButton;          //przyciski z wyborem figur
    private JButton[] jButtons;                   //przyciski na planszy

    private Shape chosenShape;
    private Shape[] shapes;

    private Icon blankIcon = new ImageIcon("img/blank.png");

    private JLabel jLabel;

    public Game() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocation(450, 200);
        setTitle("BlockuDoku");
        setSize(600, 600);
        setLayout(null);
        isOccupied = new boolean[81];
        isSetNow = new boolean[81];
        boolean isWhite = true;

        jButtons = new JButton[81];
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && (j != 0 || i % 3 == 0))
                    isWhite = !isWhite;
                jButtons[j + (i * 9)] = new JButton();
                jButtons[j + (i * 9)].setBounds(100 + j * 42, 50 + i * 42, 40, 40);
                setBoardColor(isWhite, i, j);
                jButtons[j + (i * 9)].addActionListener(this);
                add(jButtons[j + (i * 9)]);
            }
        }
        setEnabledBoard(false);

        shapes = new Shape[NUMBEROFSHAPES];
        shapes[0] = new Steps("img/3.0.png", "img/3.1.png");
        shapes[1] = new Square("img/2.0.png", "img/2.1.png");
        shapes[2] = new ShortLine("img/1.0.png", "img/1.1.png");
        shapes[3] = new SmallLadder("img/4.0.png", "img/4.1.png");
        shapes[4] = new Boat("img/5.0.png", "img/5.1.png");
        shapes[5] = new ReversedL("img/6.0.png", "img/6.1.png");


        bFig1 = new JButton();
        bFig1.setBounds(142, 450, 85, 85);
        bFig1.addActionListener(this);
        bFig1.setEnabled(true);
        add(bFig1);

        bFig2 = new JButton();
        bFig2.setBounds(247, 450, 85, 85);
        bFig2.addActionListener(this);
        bFig2.setEnabled(true);
        add(bFig2);

        bFig3 = new JButton();
        bFig3.setBounds(352, 450, 85, 85);
        bFig3.addActionListener(this);
        bFig3.setEnabled(true);
        add(bFig3);

        giveNextShapes();

        jLabel = new JLabel("Wynik: " + score);
        jLabel.setBounds(260, 15, 150, 25);
        jLabel.setFont(new Font("SansSerif", Font.BOLD, 16));
        add(jLabel);

        setVisible(true);
    }

    public void setEnabledBoard(boolean b) {
        for (int i = 0; i < 81; i++) {
            if (!isOccupied[i])
                jButtons[i].setEnabled(b);
        }
    }

    public void adjust(int indexOfButton) {
        jButtons[indexOfButton].setBackground(new Color(117, 163, 249));
        isOccupied[indexOfButton] = true;
        isSetNow[indexOfButton] = true;
        numberOfField++;
    }

    public void checkShapes() {
        if (numberOfField == chosenShape.getNumberOfField()) {
            if (chosenShape.checkShape(isSetNow)) {
                score += chosenShape.numberOfField;
                numberOfField = 0;
                bFig1.setEnabled(!b1);
                bFig2.setEnabled(!b2);
                bFig3.setEnabled(!b3);

                if (b1)
                    bFig1.setIcon(blankIcon);
                if (b2)
                    bFig2.setIcon(blankIcon);
                if (b3)
                    bFig3.setIcon(blankIcon);

                jLabel.setText("Wynik: " + score);
                chosenShape = null;
                setEnabledBoard(false);
                for (int i = 0; i < 81; i++) {
                    if (isSetNow[i]) {
                        jButtons[i].setBackground(Color.BLUE);
                        jButtons[i].setEnabled(false);
                        isSetNow[i] = false;
                    }
                }

                if (b1 && b2 && b3) {
                    bFig1.setEnabled(true);
                    bFig2.setEnabled(true);
                    bFig3.setEnabled(true);
                    b1 = b2 = b3 = false;
                    giveNextShapes();
                }
                clearFilledFields();
            } else {
                isEnd = true;
                jLabel.setBounds(180, 15, 400, 25);
                jLabel.setForeground(Color.red);
                jLabel.setText("Twój ostateczny wynik to: " + score);
                chosenButton.setIcon(chosenShape.getRedImage());
                setEnabledBoard(false);
                for (int i = 0; i < 81; i++) {
                    if (isSetNow[i]) {
                        jButtons[i].setBackground(Color.RED);
                    }
                }
            }
        }
    }


    public void giveNextShapes() {
        b1 = b2 = b3 = false;
        Random r = new Random();
        index1 = r.nextInt(NUMBEROFSHAPES);
        index2 = r.nextInt(NUMBEROFSHAPES);
        index3 = r.nextInt(NUMBEROFSHAPES);
        while (index2 == index1)
            index2 = r.nextInt(NUMBEROFSHAPES);
        while (index3 == index2 || index3 == index1)
            index3 = r.nextInt(NUMBEROFSHAPES);

        bFig1.setIcon(shapes[index1].getBlueImage());
        bFig2.setIcon(shapes[index2].getBlueImage());
        bFig3.setIcon(shapes[index3].getBlueImage());
    }


    public void clearFilledFields() {
        boolean[] temp = new boolean[81];
        System.arraycopy(isOccupied, 0, temp, 0, 81);
        int n;
        for (int i = 0; i < 9; i++) {      //sprawdzanie linii poziomych
            n = 0;
            for (int j = 0; j < 9; j++) {
                if (temp[j + (i * 9)])
                    n++;
                if (n == 9)
                    for (j = 0; j < 9; j++)
                        isOccupied[j + (i * 9)] = false;
            }
        }

        for (int j = 0; j < 9; j++) {      //sprawdzanie linii pionowych
            n = 0;
            for (int i = 0; i < 9; i++) {
                if (temp[j + (i * 9)])
                    n++;
                if (n == 9) {
                    for (i = 0; i < 9; i++)
                        isOccupied[j + (i * 9)] = false;
                }
            }
        }

        for (int k = 0; k < 3; k++)             //sprawdzanie kwadratów
            for (int l = 0; l < 3; l++) {
                n = 0;
                for (int i = 0; i < 3; i++)
                    for (int j = 0; j < 3; j++) {
                        if (temp[(3 * k + i) + ((j + 3 * l) * 9)]) {
                            n++;
                        }
                        if (n == 9) {
                            for (i = 0; i < 3; i++)
                                for (j = 0; j < 3; j++)
                                    isOccupied[(3 * k + i) + ((j + 3 * l) * 9)] = false;
                        }
                    }
            }

        boolean isWhite = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && (j != 0 || i % 3 == 0))
                    isWhite = !isWhite;
                if (!isOccupied[j + (i * 9)] && temp[j + (i * 9)]) {
                    setBoardColor(isWhite, i, j);
                }
            }
        }
    }

    public void setBoardColor(boolean isWhite, int i, int j) {
        if (isWhite)
            jButtons[j + (i * 9)].setBackground(Color.white);
        else
            jButtons[j + (i * 9)].setBackground(Color.LIGHT_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object z = e.getSource();
        if (z == bFig1 && !isEnd) {
            setEnabledBoard(true);
            bFig2.setEnabled(false);
            bFig3.setEnabled(false);
            chosenShape = shapes[index1];
            chosenButton = bFig1;
            b1 = true;
        } else if (z == bFig2 && !isEnd) {
            setEnabledBoard(true);
            bFig1.setEnabled(false);
            bFig3.setEnabled(false);
            chosenShape = shapes[index2];
            chosenButton = bFig2;
            b2 = true;
        } else if (z == bFig3 && !isEnd) {
            setEnabledBoard(true);
            bFig1.setEnabled(false);
            bFig2.setEnabled(false);
            chosenShape = shapes[index3];
            chosenButton = bFig3;
            b3 = true;
        }

        boolean isWhite = true;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (j % 3 == 0 && (j != 0 || i % 3 == 0))
                    isWhite = !isWhite;
                if (z == jButtons[j + (i * 9)]) {
                    if (!isSetNow[j + (i * 9)]) {
                        adjust(j + (i * 9));
                        checkShapes();
                    } else {
                        numberOfField--;
                        isSetNow[j + (i * 9)] = false;
                        setBoardColor(isWhite, i, j);
                    }
                }
            }
        }
    }


    public static void main(String[] args) {
        new Game();
    }
}
