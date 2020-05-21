package blokudoku;

import java.awt.Cursor;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;

public class Movement implements MouseListener, MouseMotionListener {
    private int X, Y;
    private boolean using = false;

    public Klocek block;
    public Plansza mapa;
    private Cursor blankCursor;
    private Cursor systemCursor = Cursor.getDefaultCursor();

    public Movement(Klocek block, Plansza mapa) {
        block.addMouseListener(this);
        block.addMouseMotionListener(this);
        this.block = block;
        this.mapa = mapa;
    }

    public int wyrownajX() {
        return (X - block.getWidth() / 2);
    }

    public int wyrownajY() {
        return (Y - block.getHeight() / 2);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (block.aktywny) {
            X = e.getX();
            Y = e.getY();
            e.getComponent().setLocation(block.getX() + wyrownajX(), block.getY() + wyrownajY());
            if (using) {
                int x = (e.getComponent().getLocation().x + e.getComponent().getBounds().width / 2 - mapa.getBounds().x + 2 * mapa.getTileSize()) / mapa.getTileSize() - 1;
                int y = (e.getComponent().getLocation().y + e.getComponent().getBounds().width / 2 - mapa.getBounds().y + 2 * mapa.getTileSize()) / mapa.getTileSize() - 1;
                if (x >= 0 && x <= 10 && y >= 0 && y <= 10) {
                    block.resetKloca();
                    using = false;
                    showCursor();
                    mapa.sprawdzenie(block, x, y);
                } else {
                    using = false;
                    showCursor();
                    block.resetKloca();
                }
            } else {
                hideCursor();
                using = true;
                przesunNaGore();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
        block.resetKloca();
        using = false;
        showCursor();
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        if (using && block.aktywny)
            e.getComponent().setLocation(e.getX() + block.getX() - X + wyrownajX(), e.getY() + block.getY() - Y + wyrownajY());
    }

    private void przesunNaGore() {
        block.getParent().setComponentZOrder(block, 0);
    }

    private void showCursor() {
        block.setCursor(systemCursor);
        mapa.setCursor(systemCursor);
    }

    private void hideCursor() {
        BufferedImage cursorImg = new BufferedImage(16, 16, BufferedImage.TYPE_INT_ARGB);
        blankCursor = Toolkit.getDefaultToolkit().createCustomCursor(cursorImg, new Point(0, 0), "blank cursor");
        block.setCursor(blankCursor);
        mapa.setCursor(blankCursor);
    }

    public void setUsingoff() {
        using = false;
    }
}
