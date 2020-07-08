import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

public class NButton extends JButton implements MouseListener {

    Shape shape;

    public NButton(String name){
        super(name);
        setSize(130,40);
        setBackground(Color.decode("#66B33E"));
        setFont(new Font("Lucida Sans Typewriter", Font.BOLD, 15));
        setForeground(Color.decode("#F5F0F2"));
        setContentAreaFilled(false);
        setBorder(BorderFactory.createBevelBorder(0));
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
    protected void paintComponent(Graphics g) {
        // if the button is pressed and ready to be released
        if (getModel().isArmed()) {
            g.setColor(Color.lightGray);
        } else {
            g.setColor(getBackground());
        }

        g.fillRoundRect(0, 0, getSize().width-1, getSize().height-1,20,20);

        super.paintComponent(g);
    }

    // paint a round border as opposed to a rectangular one
    protected void paintBorder(Graphics g) {
        g.setColor(getForeground());
        g.drawRoundRect(0, 0, getSize().width-1, getSize().height-1,20,20);
    }

    // only clicks within the round shape should be accepted
    public boolean contains(int x, int y) {
        if (shape == null || !shape.getBounds().equals(getBounds())) {
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());
        }

        return shape.contains(x, y);
    }
}
