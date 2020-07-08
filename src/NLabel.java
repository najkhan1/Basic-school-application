import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class NLabel extends JLabel {
    Shape shape;

    public  NLabel(String name){
        super(name);

       setOpaque(true);
        setForeground(Color.decode("#FAEBFA"));
        setAlignmentX(10);
        setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));

    }

}
