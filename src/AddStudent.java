import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class AddStudent extends JPanel implements ActionListener {
    NLText stID = new NLText("Student ID");
    NLText name = new NLText("Name");
    NLText stuAddress = new NLText("Address");
    NLText DOB = new NLText("Date of Birth",1);

    NButton addB = new NButton("Add");
    CButton cancelB = new CButton("Cancel");
    Calendar cal = new GregorianCalendar(2019,Calendar.FEBRUARY,4);
    SaveToDB sv1 = new SaveToDB();

    public AddStudent(){
        setSize(280,250);
        setLayout(null);

        stID.setLocation(10,10);
        stID.TF.setText((sv1.getIDA("Students","StuID")+1)+"");
        stID.TF.setEditable(false);
        add(stID);

        name.setLocation(10,45);
        add(name);

        stuAddress.setLocation(10,85);
        add(stuAddress);

        DOB.setLocation(10,125);
        add(DOB);
        addB.setLocation(5,200);
        addB.addActionListener(this);
        add(addB);
        cancelB.setLocation(140,200);
        cancelB.addActionListener(this);
        add(cancelB);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == addB){
            SaveToDB sv = new SaveToDB();

            sv.setStudent(Integer.parseInt(stID.TF.getText()),name.TF.getText(),stuAddress.TF.getText(),
                    DOB.dateFormChange(DOB.sp.getModel().getValue()));
            stID.TF.setText((sv1.getIDA("Students","StuID")+1)+"");
        name.TF.setText(null);
        stuAddress.TF.setText(null);


        JOptionPane.showMessageDialog(this,
                "Student added to database successfully");
        DOB.sp.setValue(cal.getTime());
    }
        if(e.getSource() == cancelB){
        this.setVisible(false);
            DOB.sp.setValue(cal.getTime());
    }
    }
}