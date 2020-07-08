import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddStaff extends JPanel implements ActionListener {

    NLText stID = new NLText("Staff ID");
    NLText name = new NLText("Name");
    NLText stuAddress = new NLText("Address");
    NLText NIN = new NLText("NI number");
    NLText salary = new NLText("Salary");
    NLText userN = new NLText("user Name");
    NLText pass = new NLText("Password");
    NLText quaL = new NLText("Qualification");


    String[] staffOrTeacher = {"Staff", "Manager", "Teacher"};
    JComboBox staffOrTeaComp = new JComboBox(staffOrTeacher);
    NLText staffOrTeaLabel = new NLText("Position",1,1);

    NButton addB = new NButton("Add");
    NButton editB = new NButton("Edit");
    CButton cancelB = new CButton("Cancel");
    SaveToDB sv1 = new SaveToDB();

    public AddStaff(){
        setSize(280,420);
        setLayout(null);

        stID.setLocation(10,10);
        stID.TF.setText((sv1.getIDA("Staff","StaID")+1) +"");
        stID.TF.setEditable(false);
        add(stID);

        name.setLocation(10,45);
        add(name);

        stuAddress.setLocation(10,85);
        add(stuAddress);

        NIN.setLocation(10,125);
        add(NIN);

        salary.setLocation(10,165);
        add(salary);

        userN.setLocation(10,205);
        add(userN);

        pass.setLocation(10,245);
        add(pass);

        staffOrTeaComp.setLocation(115, 285);
        staffOrTeaComp.setSize(145, 30);
        staffOrTeaComp.addActionListener(this);
        staffOrTeaComp.setBorder(BorderFactory.createLineBorder(Color.decode("#AE52B3"), 2));
        staffOrTeaComp.setBackground(Color.decode("#F8D6FA"));
        staffOrTeaComp.setForeground(Color.decode("#91266A"));
        staffOrTeaComp.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
        add(staffOrTeaComp);

        staffOrTeaLabel.setLocation(10, 285);
        add(staffOrTeaLabel);

        quaL.setLocation(10, 325);
        quaL.setVisible(false);
        add(quaL);




        addB.setLocation(5,370);
        addB.addActionListener(this);
        add(addB);
        cancelB.setLocation(140,370);
        cancelB.addActionListener(this);
        add(cancelB);

    }
    public AddStaff(int num){
        this();
        addB.setVisible(false);

        editB.setLocation(5,370);
        editB.addActionListener(this);
        add(editB);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int aCom = staffOrTeaComp.getSelectedIndex();
        SaveToDB sv = new SaveToDB();
        if(aCom == 2){
            quaL.setVisible(true);

        }
        if(aCom != 2){
            quaL.setVisible(false);
        }
        if(e.getSource() == addB){


            if(aCom == 2){
                sv.setStaff(Integer.parseInt(stID.TF.getText()),name.TF.getText(),stuAddress.TF.getText() ,NIN.TF.getText()
                        ,Double.parseDouble(salary.TF.getText()),userN.TF.getText(),pass.TF.getText(),staffOrTeacher[aCom],quaL.TF.getText());
                JOptionPane.showMessageDialog(this,
                        "Staff added to database successfully");

            }else{
                sv.setStaff(Integer.parseInt(stID.TF.getText()),name.TF.getText(),stuAddress.TF.getText() ,NIN.TF.getText()
                        ,Double.parseDouble(salary.TF.getText()),userN.TF.getText(),pass.TF.getText(),staffOrTeacher[aCom]);
                JOptionPane.showMessageDialog(this,
                        "Teacher added to database successfully");
            }


            int id = sv1.getIDA("Staff","StaID")+1;
            System.out.println("Staff ID = " + id);
            stID.TF.setText(id+"");


            name.TF.setText(null);
            stuAddress.TF.setText(null);
            salary.TF.setText(null);
            userN.TF.setText(null);
            pass.TF.setText(null);
            quaL.TF.setText(null);
            NIN.TF.setText(null);

        }
        if (e.getSource() == editB){
            if(aCom == 2){
                String query = "UPDATE Staff SET StaName = '"+name.TF.getText()+"', StaAddress = '"+stuAddress.TF.getText()+"', StaNI = '"+NIN.TF.getText()
                        +"', StaSalary = "+Double.parseDouble(salary.TF.getText())+", UserName = '"+userN.TF.getText()+"', StaPassword = '"+pass.TF.getText()+
                        "', Position = '"+staffOrTeacher[aCom]+"' WHERE StaID = "+Integer.parseInt(stID.TF.getText())+";";
                sv.EditDetails(query);

                String query2 = "UPDATE Teachers SET TeaQualification = '"+quaL.TF.getText()+"' WHERE StaID = "+Integer.parseInt(stID.TF.getText())+";";
                sv.EditDetails(query2);
                JOptionPane.showMessageDialog(this,
                        " Teacher added to database successfully");

            }else{
                String query = "UPDATE Staff SET StaName = '"+name.TF.getText()+"', StaAddress = '"+stuAddress.TF.getText()+"', StaNI = '"+NIN.TF.getText()
                        +"', StaSalary = "+Double.parseDouble(salary.TF.getText())+", UserName = '"+userN.TF.getText()+"', StaPassword = '"+pass.TF.getText()+
                        "', Position = '"+staffOrTeacher[aCom]+"' WHERE StaID = "+Integer.parseInt(stID.TF.getText())+";";
                sv.EditDetails(query);
                JOptionPane.showMessageDialog(this,
                        " Staff added to database successfully");
            }
            this.setVisible(false);
        }
        if(e.getSource() == cancelB){
            this.setVisible(false);
        }

    }
}
