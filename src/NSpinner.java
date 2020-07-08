import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.plaf.ComponentUI;
import javax.swing.plaf.UIResource;
import javax.swing.plaf.basic.BasicArrowButton;
import javax.swing.plaf.basic.BasicSpinnerUI;
import java.awt.*;

public class NSpinner extends BSpinner{

    public NSpinner() {
    }

    public NSpinner(SpinnerDateModel sd){
        super(sd);
        setUI(new NPSpinner());

    }

}

class NPSpinner extends BasicSpinnerUI {

    public static ComponentUI createUI(JComponent c) {
        return new NPSpinner();
    }

    @Override
    protected Component createNextButton() {
        Component c = createArrowButton(SwingConstants.NORTH);
        c.setName("Spinner.nextButton");
        installNextButtonListeners(c);
        return c;
    }

    @Override
    protected Component createPreviousButton() {
        Component c = createArrowButton(SwingConstants.SOUTH);
        c.setName("Spinner.previousButton");
        installPreviousButtonListeners(c);
        return c;
    }

    // copied from BasicSpinnerUI
    private Component createArrowButton(int direction) {
        JButton b = new BasicArrowButton(direction);
        b.setBackground(Color.decode("#66B33E"));

        //b.setForeground(Color.decode("#AE52B3"));
        b.setInheritsPopupMenu(true);
        return b;
    }

    @Override
    public void installUI(JComponent c) {
        super.installUI(c);
        c.removeAll();
        c.setLayout(new BorderLayout());
        c.add(createNextButton(), BorderLayout.BEFORE_LINE_BEGINS);
        c.add(createPreviousButton(), BorderLayout.AFTER_LINE_ENDS);
        c.add(createEditor(), BorderLayout.CENTER);

    }
}