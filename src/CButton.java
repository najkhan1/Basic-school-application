import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CButton extends JButton  implements MouseListener {
    public CButton(String name){
        super(name);
        setSize(130,40);
        setBackground(Color.decode("#EBD872"));

    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        BorderFactory.createRaisedBevelBorder();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        BorderFactory.createLoweredBevelBorder();

    }
}
