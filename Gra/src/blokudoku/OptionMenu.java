package blokudoku;

import java.awt.*;

import static java.awt.Color.*;
import static java.awt.Font.BOLD;

import java.awt.datatransfer.FlavorEvent;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.*;

import static javax.swing.SwingConstants.CENTER;

public class OptionMenu extends JPanel implements ActionListener
{
    private JButton backButton;
    private JComboBox colorBox, skinBox, sizeBox;
    private BufferedImage rainbowTileImage;
    private Color tileColor = new Color(0x17ACF3);
    private BufferedImage tileImage;


    public OptionMenu()
    {
        try
        {
            rainbowTileImage = ImageIO.read(new File("src/blokudoku/images/rainbowTile.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        setTileImageClean();
        setLayout(null);
        setBackground(gray);
        addColorChangePanel();
        addSkinChangePanel();
        addWindowSizeChangePanel();
        addTitleLabel();
        addBackButton();
    }

    private void addColorChangePanel()
    {
        JPanel colorChangePanel = new JPanel();
        colorChangePanel.setBounds( 3*Main.WIDTH/31 ,22*Main.HEIGHT/100, 8*Main.WIDTH/10, 10*Main.WIDTH/124);
        colorChangePanel.setBackground(blue);
        colorChangePanel.setLayout(null);
        colorChangePanel.setName("colorChangePanel");
        addColorChangePanelComponents(colorChangePanel);
        add(colorChangePanel);
    }

    private void addSkinChangePanel()
    {
        JPanel skinChangePanel = new JPanel();
        skinChangePanel.setBounds( 3*Main.WIDTH/31 ,44*Main.HEIGHT/100, 7*Main.WIDTH/10, 10*Main.WIDTH/124);
        skinChangePanel.setBackground(blue);
        skinChangePanel.setLayout(null);
        skinChangePanel.setName("skinChangePanel");
        addSkinChangePanelComponents(skinChangePanel);
        add(skinChangePanel);
    }

    private void addWindowSizeChangePanel()
    {
        JPanel windowSizeChangePanel = new JPanel();
        windowSizeChangePanel.setBounds( 3*Main.WIDTH/31 ,66*Main.HEIGHT/100, 8*Main.WIDTH/10, 10*Main.WIDTH/124);
        windowSizeChangePanel.setBackground(blue);
        windowSizeChangePanel.setLayout(null);
        windowSizeChangePanel.setName("windowSizeChange");
        addSizeChangePanelComponents(windowSizeChangePanel);
        add(windowSizeChangePanel);
    }

    private void addColorChangePanelComponents(JPanel panel)
    {
        addColorComboBox(panel);
        addColorLabel1(panel);
        addLabel2(panel);
        addColorTile(panel);
    }

    private void addSkinChangePanelComponents(JPanel panel)
    {
        addSkinComboBox(panel);
        addSkinLabel(panel);
        addSkinLabel2(panel);
    }

    private void addSizeChangePanelComponents(JPanel panel)
    {
        addSizeComboBox(panel);
        addSizeLabel(panel);
    }

    private void addColorLabel1(JPanel panel)
    {
        JLabel label = new JLabel();
        label.setName("ColorLabel1");
        label.setBounds(panel.getWidth()/20, panel.getHeight()/5, panel.getWidth()/5, 3*panel.getHeight()/5 );
        label.setForeground(white);
        label.setBackground(gray);
        label.setFont(new Font("NewFont", BOLD, 3*panel.getHeight()/5));
        label.setText("Kolor");
        panel.add(label);
    }

    private void addSkinLabel(JPanel panel)
    {
        JLabel label = new JLabel();
        label.setBounds(panel.getWidth()/16, panel.getHeight()/5, panel.getWidth()/5, 3*panel.getHeight()/5 );
        label.setForeground(white);
        label.setBackground(null);
        label.setFont(new Font("NewFont", BOLD, 3*panel.getHeight()/5));
        label.setText("Skin");
        panel.add(label);
    }

    private void addSizeLabel(JPanel panel)
    {
        JLabel label = new JLabel();
        label.setBounds(panel.getWidth()/10, panel.getHeight()/5, panel.getWidth()/2, 3*panel.getHeight()/5 );
        label.setForeground(white);
        label.setBackground(null);
        label.setFont(new Font("NewFont", BOLD, 3*panel.getHeight()/5));
        label.setText("Rozdzielczość");
        panel.add(label);
    }

    private void addLabel2(JPanel panel)
    {
        JLabel label = new JLabel();
        label.setBounds( panel.getWidth()/2, panel.getHeight()/5, panel.getWidth()/2, 3*panel.getHeight()/5 );
        label.setForeground(white);
        label.setBackground(null);
        label.setFont(new Font("NewFont", BOLD,   panel.getHeight()/2));
        label.setText("Wygląd w grze");
        panel.add(label);
    }

    private void addSkinLabel2(JPanel panel)
    {
        JLabel label = new JLabel();
        label.setBounds( 9*panel.getWidth()/16, panel.getHeight()/5, panel.getWidth()/2, 3*panel.getHeight()/5 );
        label.setForeground(white);
        label.setBackground(null);
        label.setFont(new Font("NewFont", BOLD,   panel.getHeight()/2));
        label.setText("Wygląd w grze");
        panel.add(label);
    }

    private void addColorTile(JPanel panel)
    {
        JPanel colorTile = new JPanel();
        colorTile.setName("colorTile");
        colorTile.setBounds(9*panel.getWidth()/10, panel.getHeight()/5, 3*panel.getHeight()/5, 3*panel.getHeight()/5 );
        colorTile.setBackground(tileColor);
        colorTile.setLayout(null);

        JLabel label = new JLabel();
        label.setBounds(0, 0, 3*panel.getHeight()/5, 3*panel.getHeight()/5 );
        label.setIcon(new ImageIcon(rainbowTileImage.getScaledInstance(3*panel.getHeight()/5, 3*panel.getHeight()/5, Image.SCALE_DEFAULT)));
        colorTile.add(label);

        panel.add(colorTile);
    }

    private void addColorComboBox(JPanel panel)
    {
        colorBox = new JComboBox();
        colorBox.setName("ColorBox");
        colorBox.setBounds( 4*panel.getWidth()/17 , panel.getHeight()/5, 1*panel.getWidth()/5, 3 * panel.getHeight()/5 );
        colorBox.addItem("Niebieski");
        colorBox.addItem("Czarny");
        colorBox.addItem("Czerwoniutki");
        colorBox.addItem("Pomarańczowy");
        colorBox.addItem("Zieloniutki");
        colorBox.addItem("Różowy");
        colorBox.addItem("Tęcza");
        panel.add(colorBox);
        colorBox.addActionListener(this);
    }

    private void addSkinComboBox(JPanel panel)
    {
        skinBox = new JComboBox();
        skinBox.setName("skinBox");
        skinBox.setBounds( 9*panel.getWidth()/34 , panel.getHeight()/5, 6*panel.getWidth()/25, 3 * panel.getHeight()/5 );
        skinBox.addItem("Brak");
        skinBox.addItem("Skrzynia");
        skinBox.addItem("Korona");
        skinBox.addItem("Serce");
        skinBox.addItem("Ciastko");
        skinBox.addItem("Chmura");
        panel.add(skinBox);
        skinBox.addActionListener(this);
    }

    private void setTileImageClean()
    {
        try
        {
            tileImage = ImageIO.read(new File("src/blokudoku/images/clean.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void setTileImageKorona()
    {
        try
        {
            tileImage = ImageIO.read(new File("src/blokudoku/images/korona.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }
    private void setTileImageSerce()
    {
        try
        {
            tileImage = ImageIO.read(new File("src/blokudoku/images/serce.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void setTileImageSkrzynia()
    {
        try
        {
            tileImage = ImageIO.read(new File("src/blokudoku/images/skrzynia.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void setTileImageChmura()
    {
        try
        {
            tileImage = ImageIO.read(new File("src/blokudoku/images/Chmura.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void setTileImageCiastko()
    {
        try
        {
            tileImage = ImageIO.read(new File("src/blokudoku/images/Ciastko.png"));
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
    }

    private void addSizeComboBox(JPanel panel)
    {
        sizeBox = new JComboBox();
        sizeBox.setName("sizeBox");
        sizeBox.setBounds( 11*panel.getWidth()/17 , panel.getHeight()/5, 1*panel.getWidth()/5, 3 * panel.getHeight()/5 );
        sizeBox.addItem("900 x "+ 900 * 9/16);
        sizeBox.addItem("1240 x "+ 1240 * 9/16);
        sizeBox.addItem("1600 x "+ 1600 * 9/16);
        sizeBox.addItem("1800 x "+ 1800 * 9/16);
        panel.add(sizeBox);
        sizeBox.setSelectedItem(Main.WIDTH + " x " + Main.HEIGHT);
        sizeBox.addActionListener(this);
    }

    private void addTitleLabel()
    {
        JLabel titleLabel = new JLabel("Opcje gry", CENTER);
        titleLabel.setBounds(37*Main.WIDTH/124, Main.WIDTH/124, 25*Main.WIDTH/62, 10*Main.WIDTH/124);
        titleLabel.setForeground(white);
        titleLabel.setFont(new Font("NewFont", BOLD, Main.WIDTH/31));
        add(titleLabel);
    }

    private void addBackButton() {
        backButton = new JButton("◄ Powrót");
        backButton.setBounds(Main.WIDTH/62, Main.WIDTH/62, 15*Main.WIDTH/124, 7*Main.WIDTH/248);
        backButton.setBackground(white);
        backButton.setFocusable(false);
        backButton.setFont(new Font("NewFont", BOLD, 3*Main.WIDTH/248));
        backButton.addActionListener(this);
        add(backButton);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(blue);
        g.fillRect(Main.HEIGHT/10, 15*Main.HEIGHT/100, Main.WIDTH - Main.HEIGHT/5, Main.HEIGHT - Main.HEIGHT/4);
        for(int i=0 ; i < this.getComponents().length; i++)
        {
            this.getComponent(i).repaint();
            if(getComponent(i).getName() == "colorChangePanel")
            {
                JPanel panel = (JPanel) getComponent(i);
                for (int j = 0; j < panel.getComponentCount() ; j++)
                    if(panel.getComponent(j).getName() == "colorTile")
                    {
                        if(tileColor.getRGB() != new Color(0).getRGB() )
                        {
                            hideComponentsOfJPanel((JPanel) panel.getComponent(j));
                            panel.getComponent(j).setBackground(tileColor);
                        }
                        else
                        {
                            showComponentsOfJPanel((JPanel) panel.getComponent(j));
                        }
                    }
            }
        }
        g.setColor(tileColor);
        g.fillRect(101*Main.WIDTH/124,47*Main.HEIGHT/100,3*Main.WIDTH/62,3*Main.WIDTH/62);
        g.drawImage(tileImage,101*Main.WIDTH/124,47*Main.HEIGHT/100,3*Main.WIDTH/62,3*Main.WIDTH/62,null);
    }

    private void hideComponentsOfJPanel(JPanel panel)
    {
        for(int i=0 ; i< panel.getComponents().length; i++)
        {
            panel.getComponent(i).setVisible(false);
        }
    }

    private void showComponentsOfJPanel(JPanel panel)
    {
        for(int i=0 ; i< panel.getComponents().length; i++)
        {
            panel.getComponent(i).setVisible(true);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        Object src = e.getSource();
        if(src == colorBox)
        {
            switch (colorBox.getSelectedIndex())
            {
                case 0: tileColor = new Color(0x17ACF3); break;
                case 1: tileColor = new Color(0x06181A); break;
                case 2: tileColor = new Color(0xE2050C); break;
                case 3: tileColor = new Color(0xFF6200); break;
                case 4: tileColor = new Color(0x52EA18); break;
                case 5: tileColor = new Color(0xFB00FF); break;
                case 6: tileColor = new Color(0); break;
            }
        }
        else if(src == skinBox)
        {
            switch (skinBox.getSelectedIndex())
            {
                case 0: setTileImageClean();  break;
                case 1: setTileImageSkrzynia(); break;
                case 2: setTileImageKorona(); break;
                case 3: setTileImageSerce(); break;
                case 4: setTileImageCiastko(); break;
                case 5: setTileImageChmura(); break;
            }
        }
        else if(src == sizeBox)
        {
            switch (sizeBox.getSelectedIndex())
            {
                case 0: Main.changeWindowSize(900);  break;
                case 1: Main.changeWindowSize(1240);  break;
                case 2: Main.changeWindowSize(1600);  break;
                case 3: Main.changeWindowSize(1800);  break;
            }
        } else if (src == backButton) {
            Window.back();
        }
        repaint();
    }

    public Color getTileColor()
    {
        return  tileColor;
    }

    public BufferedImage getRainbowTileImage()
    {
        return rainbowTileImage;
    }

    public BufferedImage getSkinImage()
    {
        return tileImage;
    }
}