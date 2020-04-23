import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Movement implements MouseListener, MouseMotionListener
{
    private int X,Y;
    
    //zmienne pomagające sprawdzić który Klocek jest aktualnie używany
    
    public boolean using = false;
    public boolean out = true;
    public Klocek kloc;
    
    public Movement(Klocek blok)
    {
        blok.addMouseListener(this);
        blok.addMouseMotionListener(this);
        kloc = blok;
    }

    public int wyrownajX()
    {
       return (X - kloc.getWidth()/2);
    }
    
    public int wyrownajY()
    {
       return (Y - kloc.getHeight()/2);
    }
    
    @Override
    public void mousePressed(MouseEvent e) 
    {
        if(kloc.aktywny)
        {
        X = e.getX();
        Y = e.getY();
        e.getComponent().setLocation(kloc.getX() + wyrownajX(), kloc.getY() + wyrownajY());
        if(using)
        {
            using = false;
            kloc.resetKloca();
        }
        else
            using = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {}
    
    @Override
    public void mouseReleased(MouseEvent e){}

    @Override
    public void mouseEntered(MouseEvent e) 
    {
        out = false;
    }

    @Override
    public void mouseExited(MouseEvent e) 
    {
        out = true;
    }

    @Override
    public void mouseDragged(MouseEvent e){}

    @Override
    public void mouseMoved(MouseEvent e) 
    {
        if(using && kloc.aktywny)
        e.getComponent().setLocation(e.getX() + kloc.getX() - X + wyrownajX(), e.getY() + kloc.getY() - Y + wyrownajY());
    }
    
    public void zresetujKlocek()
    {
        kloc.resetKloca();
        using = false;
        out = false;
    }
}
