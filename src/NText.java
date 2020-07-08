import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

public class NText extends JTextField {
    Shape shape;
    public NText(){
        setSize(250,60);
        setBackground(Color.decode("#F8D6FA"));
        setForeground(Color.decode("#91266A"));
        setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
        setBorder(BorderFactory.createLineBorder(Color.decode("#AE52B3"), 2));
        setHorizontalAlignment(JTextField.RIGHT);
        setOpaque(false);

    }
    @Override
    protected void paintComponent(Graphics g) {
        // if the button is pressed and ready to be released
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getSize().width-1, getSize().height-1,20,20);

        super.paintComponent(g);
    }

    // paint a round border as opposed to a rectangular one
    @Override
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
