import javax.swing.*;
import java.awt.*;

public class NRButton extends JRadioButton {
    public NRButton(String name){
        super(name);
        setBackground(Color.decode("#ccffcc"));
        //setBorder(BorderFactory.createLineBorder(Color.decode("#66B33E"), 2));
        setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        setBorder(BorderFactory.createLoweredBevelBorder());
        setBorderPainted(true);
        setForeground(Color.decode("#AE52B3"));
        setSize(180,30);
    }
}
