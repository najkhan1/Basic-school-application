import javax.swing.*;
import java.awt.event.ActionEvent;

public class EditStudent extends AddStudent {
    NButton editB = new NButton("Edit");

    public EditStudent(int ID){
        super();
        stID.TF.setText(ID+"");
        addB.setVisible(false);

        editB.setLocation(5,200);
        editB.addActionListener(this);
        add(editB);

    }
    @Override
    public void actionPerformed(ActionEvent e){
        if (e.getSource() == editB){
            SaveToDB sv = new SaveToDB();

           // sv.setStudent(Integer.parseInt(stID.TF.getText()),name.TF.getText(),stuAddress.TF.getText(),
              //      DOB.dateFormChange(DOB.sp.getModel().getValue()));
            String query = "UPDATE Students" +
                    " set "+" StuAddress ='"+stuAddress.TF.getText() +"',"+
                    "DOB = Date '"+DOB.dateFormChange(DOB.sp.getModel().getValue())+"'" +", StuName = '"+ name.TF.getText() +"' where StuID = "+Integer.parseInt(stID.TF.getText())+"";
                    sv.EditDetails(query);

            //stID.TF.setText((sv1.getIDA("Students","StuID")+1)+"");
            name.TF.setText(null);
            stuAddress.TF.setText(null);
            DOB.sp.setValue(cal.getTime());


            JOptionPane.showMessageDialog(this,
                    "Student details edited in database successfully");
            DOB.sp.setValue(cal.getTime());

        }
        if(e.getSource() == cancelB){
            this.setVisible(false);
            DOB.sp.setValue(cal.getTime());
        }
    }
}
