import javax.swing.*;

public class BSpinner extends JSpinner {


    public BSpinner() {
    }

    public BSpinner(SpinnerDateModel sd){
        super(sd);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(this, "dd-MM-yyyy");
        setEditor(editor);

    }
}
