import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.text.DateFormatter;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class NLText extends JPanel  {
    NText TF = new NText();
    JLabel lb = new JLabel("empty");
    SpinnerDateModel model;
    NSpinner sp;
    JComboBox jc;
    NPassField pass = new NPassField();

    public NLText(String label,String [] list){
        setSize(250,30);
        setLayout(null);
        setBackground(Color.decode("#AE52B3"));

        lb.setText(label);
        lb.setLocation(5,0);
        lb.setForeground(Color.decode("#FAEBFA"));
        lb.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        lb.setSize(100,30);
        add(lb);

        jc = new JComboBox(list);
        jc.setLocation(105,0);
        jc.setSize(145,30);
        add(jc);

    }


    public NLText(String label){
        setSize(250,30);
        setLayout(null);
        setBackground(Color.decode("#AE52B3"));


        /*LineBorder line = new LineBorder(Color.blue, 1, true); // color, thickness, rounded
        lb.setBorder(line);
        add(lb, BorderLayout.CENTER);*/

        lb.setText(label);
        lb.setLocation(5,0);
        lb.setForeground(Color.decode("#FAEBFA"));
        lb.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        lb.setSize(100,30);
        add(lb);

        TF.setLocation(105,0);
        TF.setSize(145,30);
        add(TF);

    }
    public NLText(String label, boolean placeHold){
        setSize(250,30);
        setLayout(null);
        setBackground(Color.decode("#AE52B3"));


        /*LineBorder line = new LineBorder(Color.blue, 1, true); // color, thickness, rounded
        lb.setBorder(line);
        add(lb, BorderLayout.CENTER);*/

        lb.setText(label);
        lb.setLocation(5,0);
        lb.setForeground(Color.decode("#FAEBFA"));
        lb.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        lb.setSize(100,30);
        add(lb);
        pass.setLocation(105,0);
        pass.setSize(145,30);
        add(pass);
    }
    public NLText(String label,int num,int num2){
        setSize(110,30);
        setLayout(null);
        setBackground(Color.decode("#AE52B3"));


        lb.setText(label);
        lb.setLocation(5,0);
        lb.setForeground(Color.decode("#FAEBFA"));
        lb.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        lb.setSize(100,30);
        add(lb);
    }
    public NLText(String label,int num) {
        setSize(250, 30);
        setLayout(null);

        Calendar cal = Calendar.getInstance();
        Date now = cal.getTime();
        cal.add(Calendar.YEAR, -90);
        Date startDate = cal.getTime();
        cal.add(Calendar.YEAR, 100);
        Date endDate = cal.getTime();
        model = new SpinnerDateModel(now,startDate,endDate,Calendar.YEAR);
        sp = new NSpinner(model);
        JSpinner.DateEditor editor = new JSpinner.DateEditor(sp, "dd-MM-yyyy");
        //sp.setEditor(editor);
        DateFormatter formatter = (DateFormatter)editor.getTextField().getFormatter();
        JTextField tf = ((JSpinner.DefaultEditor) sp.getEditor()).getTextField();
        tf.setForeground(Color.decode("#91266A"));
        tf.setBackground(Color.decode("#F8D6FA"));
        tf.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));



        setBackground(Color.decode("#AE52B3"));
        lb.setText(label);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);

        lb.setLocation(5, 0);
        lb.setAlignmentX(2);
        lb.setForeground (Color.decode("#FAEBFA"));
        lb.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        lb.setSize(100, 30);
        add(lb);

        sp.setLocation(105,0);
        sp.setBorder(BorderFactory.createLineBorder(Color.decode("#AE52B3"), 2));




        sp.setSize(145,30);
        add(sp);
    }
    public String dateFormChange(Object da){
        //Date date = da;
        SimpleDateFormat fomate = new SimpleDateFormat("yyyy-MM-dd");
        String nDate = fomate.format(da);
        return nDate;
    }
}
