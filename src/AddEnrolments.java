import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddEnrolments extends JPanel implements ActionListener {

    NLText enID = new NLText("Enrolment ID");

    NLText enGrade = new NLText("Grade");
    NLText stID = new NLText("Student ID");
    NLText corID = new NLText("Course ID");
    NLText dEnu = new NLText("Date ");
    JLabel en = new JLabel("Enrol");

    NButton addB = new NButton("Add");
    NButton editB = new NButton("Edit");
    CButton cancelB = new CButton("Cancel");

    SaveToDB sv1 = new SaveToDB();
    RetriveData rv;

    public AddEnrolments(){
        setSize(280,420);
        setLayout(null);

        enID.setLocation(10,70);
        enID.TF.setText((sv1.getIDA("Enrolments","EnuID")+1)+"");
        enID.TF.setEditable(false);
        add(enID);

        en.setBackground( Color.WHITE);
        en.setForeground(Color.decode("#800080"));
        en.setFont(new Font ("Serif", Font.ITALIC, 40));
        en.setSize(150,70);
        en.setLocation(10,10);
        //en.setForeground(Color.blue);
        add(en);


        stID.setLocation(10,105);
        add(stID);

        corID.setLocation(10,145);
        add(corID);

        enGrade.setLocation(10,185);
        add(enGrade);

        dEnu.setLocation(10, 225);
        dEnu.TF.setText(curDate());
        dEnu.TF.setEditable(false);
        add(dEnu);

        addB.setLocation(5,370);
        addB.addActionListener(this);
        add(addB);
        cancelB.setLocation(140,370);
        cancelB.addActionListener(this);
        add(cancelB);

    }
    public AddEnrolments(int num){
        this();
        addB.setVisible(false);
        corID.TF.setEditable(false);
        enID.TF.setEditable(false);
        dEnu.TF.setEditable(false);
        stID.TF.setEditable(false);


        editB.setLocation(5,370);
        editB.addActionListener(this);
        add(editB);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveToDB sv = new SaveToDB();
        rv = new RetriveData();
        if(e.getSource() == addB){

            sv.setEnrolments(Integer.parseInt(enID.TF.getText()),dEnu.TF.getText(), enGrade.TF.getText() ,Integer.parseInt(stID.TF.getText()),Integer.parseInt(corID.TF.getText()));
            rv.addRegForEn(Integer.parseInt(enID.TF.getText()));
            int id = sv1.getIDA("Enrolments","EnuID")+1;
            System.out.println("enu ID = " + id);
            enID.TF.setText(id+"");

            enGrade.TF.setText(null);
           // stID.TF.setText(null);
            corID.TF.setText(null);
            dEnu.TF.setText(curDate());

            JOptionPane.showMessageDialog(this,
                    "Enrolment added to database successfully");
        }
        if (e.getSource() == editB){
            String query = "UPDATE Enrolments SET Grade = '"+enGrade.TF.getText()+"' WHERE EnuID = "+
                    Integer.parseInt(enID.TF.getText())+";";
            sv.EditDetails(query);
            JOptionPane.showMessageDialog(this,
                    "Enrolment added to database successfully");
        }
        if(e.getSource() == cancelB){
            this.setVisible(false);


        }

    }
    public String curDate(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return dtf.format(LocalDateTime.now());
    }

    }

