import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCourse extends JPanel implements ActionListener {
    NLText corID = new NLText("Course ID");
    NLText corName = new NLText("Course Name");
    NLText corStatus = new NLText("Course status");

    NButton editB = new NButton("Edit");
    NButton addB = new NButton("Add");
    CButton cancelB = new CButton("Cancel");
    SaveToDB sv1 = new SaveToDB();
    public AddCourse(){
        setSize(280,250);
        setLayout(null);

        corID.setLocation(10,10);
        corID.TF.setText((sv1.getIDA("Courses","CorID")+1)+"");
        corID.TF.setEditable(false);
        add(corID);

        corName.setLocation(10,45);
        add(corName);

        corStatus.setLocation(10,85);
        add(corStatus);

        addB.setLocation(5,200);
        addB.addActionListener(this);
        add(addB);

        cancelB.setLocation(140,200);
        cancelB.addActionListener(this);
        add(cancelB);

    }
    public AddCourse(int num){
        this();
        addB.setVisible(false);

        editB.setLocation(5,200);
        editB.addActionListener(this);
        add(editB);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        SaveToDB sv = new SaveToDB();
        if(e.getSource() == addB){

            sv.setCourse(Integer.parseInt(corID.TF.getText()),corName.TF.getText(),corStatus.TF.getText());

            corID.TF.setText((sv1.getIDA("Courses","CorID")+1)+"");
            corStatus.TF.setText(null);
            corName.TF.setText(null);

            JOptionPane.showMessageDialog(this,
                    "Course added to database successfully");

        }
        if (e.getSource() == editB){
            String query = "UPDATE Courses SET CorName = '"+corName.TF.getText()+"', CorStatus = '"+
                    corStatus.TF.getText()+"' WHERE CorID = "+ Integer.parseInt(corID.TF.getText()) +";";
            sv.EditDetails(query);
            JOptionPane.showMessageDialog(this,
                    "Course added to database successfully");
            this.setVisible(false);
        }
        if(e.getSource() == cancelB){
            this.setVisible(false);
        }
    }
}
