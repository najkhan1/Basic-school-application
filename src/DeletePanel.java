import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class DeletePanel extends NPanel implements ActionListener {
    NButton deleteB = new NButton("Delete");
    searchTable tobeDeleted;
    searchTable tobeEdited;
    String[] listForDel = {"","Students", "Enrollments", "Lessons","Staff"};
    JComboBox delC = new JComboBox(listForDel);
    int com;
    JLabel deletLabel = new JLabel("Click a record in the table to delete it", SwingConstants.CENTER);

    NButton editB = new NButton("Edit");
    String[] listForEdit = { "","Courses","Enrollments","Staff"};
    JComboBox editC = new JComboBox(listForEdit);
    int comEdit;
    NButton cancelB = new NButton("Cancel");
    SaveToDB sv = new SaveToDB();
    RetriveData rv = new RetriveData();
    JLabel editLabel = new JLabel("Click on the record to Edit",SwingConstants.CENTER);

    Object[] staffCols = {"Staff ID", "Staff name", "Staff Address", "Staff NI","Staff Salary","User name", "Password","Position"};

    AddCourse corPanel = new AddCourse(1);
    AddEnrolments enuPanel = new AddEnrolments(1);
    AddStaff staPanel = new AddStaff(1);


    public DeletePanel (){
        setLayout(null);
        setSize(1100,700);

        deleteB.setLocation(10,10);
        deleteB.setBackground(Color.decode("#c73200"));
        deleteB.addActionListener(this);
        add(deleteB);

        delC.setSize(130,30);
        delC.setLocation(10,55);
        delC.setBackground(Color.decode("#c73200"));
        delC.setForeground(Color.white);
        delC.addActionListener(this);
        add(delC);

        deletLabel.setSize(640,40);
        deletLabel.setForeground(Color.decode("#c73200"));
        deletLabel.setLocation(10, 80);
        deletLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
        deletLabel.setVisible(false);
        add(deletLabel);

        editLabel.setSize(640,40);
        editLabel.setForeground(Color.decode("#c73200"));
        editLabel.setLocation(10, 80);
        editLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
        editLabel.setVisible(false);
        add(editLabel);

        editB.setLocation(170,10);
        editB.addActionListener(this);
        editB.setBackground(Color.decode("#3cc9f0"));
        add(editB);

        cancelB.setLocation(320,10);
        cancelB.addActionListener(this);
        cancelB.setBackground(Color.decode("#cccc00"));
        cancelB.setVisible(false);
        add(cancelB);

        editC.setLocation(170,55);
        editC.setSize(130,30);
        editC.setBackground(Color.decode("#3cc9f0"));
        editC.setForeground(Color.white);
        editC.addActionListener(this);
        add(editC);

        corPanel.setLocation(780,120);
        corPanel.setVisible(false);
        add(corPanel);

        enuPanel.setLocation(780,120);
        enuPanel.setVisible(false);
        add(enuPanel);

        staPanel.setLocation(780,120);
        staPanel.setVisible(false);
        add(staPanel);


    }

    @Override
    public void actionPerformed(ActionEvent e) {



        if (e.getSource() == deleteB){
            com = delC.getSelectedIndex();
            if (com != 0){
                cancelB.setVisible(true);
            }
            if (com == 1){

                deletLabel.setVisible(true);
                String query = "SELECT * FROM Students";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeDeleted = new searchTable(RetriveData.stuCols,stuArray);
                tobeDeleted.setSize(640,420);
                tobeDeleted.setLocation(10,120);
                tobeDeleted.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selecRow = tobeDeleted.StuScroll.getSelectedRow();
                        System.out.println("selected row  com1 =" + selecRow);
                        int id = Integer.parseInt(tobeDeleted.StuScroll.getValueAt(selecRow, 0).toString());
                        Object message = "Do you want to permanently delete Student with ID " + id + " from database";
                        int choice = JOptionPane.showConfirmDialog(null, message, "Warning", 2, JOptionPane.WARNING_MESSAGE);
                        if (choice == 0) {
                            String query = "DELETE FROM Students WHERE StuID = " + id + ";";
                            sv.EditDetails(query);
                            ((DefaultTableModel) tobeDeleted.StuScroll.getModel()).removeRow(selecRow);
                        }
                        tobeDeleted.setVisible(false);
                        deletLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeDeleted);
                delC.setSelectedIndex(0);
            }
            if (com == 2){

                deletLabel.setVisible(true);
                String query = "SELECT * FROM Enrolments";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeDeleted = new searchTable(RetriveData.enrolCols,stuArray);
                tobeDeleted.setSize(640,420);
                tobeDeleted.setLocation(10,120);
                tobeDeleted.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selecRow = tobeDeleted.StuScroll.getSelectedRow();
                        System.out.println("selected row  com2 =" + selecRow);
                        int id = Integer.parseInt(tobeDeleted.StuScroll.getValueAt(selecRow, 0).toString());
                        Object message = "Do you want to permanently delete Enrollment with ID " + id + " from database";
                        int choice = JOptionPane.showConfirmDialog(null, message, "Warning", 2, JOptionPane.WARNING_MESSAGE);
                        if (choice == 0) {
                            String query = "DELETE FROM Enrolments WHERE EnuID = " + id + ";";
                            sv.EditDetails(query);
                            ((DefaultTableModel) tobeDeleted.StuScroll.getModel()).removeRow(selecRow);
                        }
                        tobeDeleted.setVisible(false);
                        deletLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeDeleted);
                delC.setSelectedIndex(0);
            }
            if (com == 3){

                deletLabel.setVisible(true);
                String query = "SELECT * FROM Lessons";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeDeleted = new searchTable(RetriveData.lesCols,stuArray);
                tobeDeleted.setSize(640,420);
                tobeDeleted.setLocation(10,120);
                tobeDeleted.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selecRow = tobeDeleted.StuScroll.getSelectedRow();
                        System.out.println("selected row  com3 =" + selecRow);
                        int id = Integer.parseInt(tobeDeleted.StuScroll.getValueAt(selecRow, 0).toString());
                        Object message = "Do you want to permanently delete Lesson with ID " + id + " from database";
                        int choice = JOptionPane.showConfirmDialog(null, message, "Warning", 2, JOptionPane.WARNING_MESSAGE);
                        if (choice == 0) {
                            String query = "DELETE FROM Lessons WHERE LesID = " + id + ";";
                            sv.EditDetails(query);
                            ((DefaultTableModel) tobeDeleted.StuScroll.getModel()).removeRow(selecRow);
                        }
                        tobeDeleted.setVisible(false);
                        deletLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeDeleted);
                delC.setSelectedIndex(0);
            }
            if (com == 4){

                deletLabel.setVisible(true);
                String query = "SELECT * FROM Staff";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeDeleted = new searchTable(staffCols,stuArray);
                tobeDeleted.setSize(640,420);
                tobeDeleted.setLocation(10,120);
                tobeDeleted.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        int selecRow = tobeDeleted.StuScroll.getSelectedRow();
                        System.out.println("selected row  com4 =" + selecRow);
                        int id = Integer.parseInt(tobeDeleted.StuScroll.getValueAt(selecRow, 0).toString());
                        Object message = "Do you want to permanently delete Staff with ID " + id + " from database";
                        int choice = JOptionPane.showConfirmDialog(null, message, "Warning", 2, JOptionPane.WARNING_MESSAGE);
                        if (choice == 0) {
                            String query = "DELETE FROM Staff WHERE StaID = " + id + ";";
                            sv.EditDetails(query);
                            ((DefaultTableModel) tobeDeleted.StuScroll.getModel()).removeRow(selecRow);
                        }
                        tobeDeleted.setVisible(false);
                        deletLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeDeleted);
                delC.setSelectedIndex(0);

            }

        }
        if (e.getSource() == editB){


            deletLabel.setVisible(false);
            comEdit = editC.getSelectedIndex();
            if (comEdit != 0){
                cancelB.setVisible(true);
            }

            if (comEdit == 1){

                editLabel.setVisible(true);
                String query = "SELECT * FROM Courses";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeEdited = new searchTable(RetriveData.corCols,stuArray);
                tobeEdited.setSize(640,420);
                tobeEdited.setLocation(10,120);
                tobeEdited.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        corPanel.setVisible(true);
                        int selecRow = tobeEdited.StuScroll.getSelectedRow();
                        System.out.println("selected row  com1 =" + selecRow);
                        int id = Integer.parseInt(tobeEdited.StuScroll.getValueAt(selecRow, 0).toString());
                        String corName = tobeEdited.StuScroll.getValueAt(selecRow, 1).toString();
                        String corStatus = tobeEdited.StuScroll.getValueAt(selecRow, 2).toString();

                        corPanel.corID.TF.setText(id + "");
                        corPanel.corID.TF.setEditable(false);
                        corPanel.corName.TF.setText(corName + "");
                        corPanel.corStatus.TF.setText(corStatus + "");
                        tobeEdited.setVisible(false);
                        editLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) { }
                    @Override
                    public void mouseReleased(MouseEvent e) { }
                    @Override
                    public void mouseEntered(MouseEvent e) { }
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeEdited);
                editC.setSelectedIndex(0);
            }
            if (comEdit == 2){
                deletLabel.setVisible(true);
                editLabel.setVisible(true);
                String query = "SELECT * FROM Enrolments";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeEdited = new searchTable(RetriveData.enrolCols,stuArray);
                tobeEdited.setSize(640,420);
                tobeEdited.setLocation(10,120);
                tobeEdited.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        enuPanel.setVisible(true);
                        int selecRow = tobeEdited.StuScroll.getSelectedRow();
                        System.out.println("selected row  com1 =" + selecRow);
                        int id = Integer.parseInt(tobeEdited.StuScroll.getValueAt(selecRow, 0).toString());
                        String enuDate = tobeEdited.StuScroll.getValueAt(selecRow, 1).toString();
                        String grade = tobeEdited.StuScroll.getValueAt(selecRow, 2).toString();
                        int stuID = Integer.parseInt(tobeEdited.StuScroll.getValueAt(selecRow, 3).toString());
                        int corID = Integer.parseInt(tobeEdited.StuScroll.getValueAt(selecRow, 4).toString());

                        enuPanel.enID.TF.setText(id + "");
                        enuPanel.dEnu.TF.setText(enuDate+"");
                        enuPanel.enGrade.TF.setText(grade+"");
                        enuPanel.stID.TF.setText(stuID+"");
                        enuPanel.corID.TF.setText(corID+"");
                        tobeEdited.setVisible(false);
                        editLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeEdited);
                editC.setSelectedIndex(0);
            }
            if (comEdit == 3){

                editLabel.setVisible(true);
                String query = "SELECT * FROM Staff";
                Object[][] stuArray = rv.getCurEnrolments(query);
                tobeEdited = new searchTable(staffCols,stuArray);
                tobeEdited.setSize(640,420);
                tobeEdited.setLocation(10,120);
                tobeEdited.StuScroll.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        staPanel.setVisible(true);
                        int selecRow = tobeEdited.StuScroll.getSelectedRow();
                        System.out.println("selected row  com1 =" + selecRow);
                        int id = Integer.parseInt(tobeEdited.StuScroll.getValueAt(selecRow, 0).toString());
                        String staName = tobeEdited.StuScroll.getValueAt(selecRow, 1).toString();
                        String staAdd = tobeEdited.StuScroll.getValueAt(selecRow, 2).toString();
                        String  staNI = tobeEdited.StuScroll.getValueAt(selecRow, 3).toString();
                        double staSal = Double.parseDouble(tobeEdited.StuScroll.getValueAt(selecRow, 4).toString());
                        String uName = tobeEdited.StuScroll.getValueAt(selecRow, 5).toString();
                        String pass = tobeEdited.StuScroll.getValueAt(selecRow, 6).toString();
                        String pos = tobeEdited.StuScroll.getValueAt(selecRow, 7).toString();

                        staPanel.stID.TF.setText(id+"");
                        staPanel.name.TF.setText(staName+"");
                        staPanel.stuAddress.TF.setText(staAdd+"");
                        staPanel.NIN.TF.setText(staNI+"");
                        staPanel.salary.TF.setText(staSal+"");
                        staPanel.userN.TF.setText(uName+"");
                        staPanel.pass.TF.setText(pass+"");
                        if (pos.equals("Staff")) {
                            staPanel.staffOrTeaComp.setSelectedIndex(0);
                        }else if (pos.equals("Manager")){
                            staPanel.staffOrTeaComp.setSelectedIndex(1);
                        }else if (pos.equals("Teacher")){
                            staPanel.staffOrTeaComp.setSelectedIndex(2);
                            String query = "SELECT TeaQualification FROM Teachers WHERE StaID ="+id+";";
                            Object[][] teachQual = rv.getCurEnrolments(query);
                            String qual = teachQual[0][0].toString();
                            staPanel.quaL.TF.setText(qual+"");
                        }
                        tobeEdited.setVisible(false);
                        editLabel.setVisible(false);
                    }
                    @Override
                    public void mousePressed(MouseEvent e) {}
                    @Override
                    public void mouseReleased(MouseEvent e) {}
                    @Override
                    public void mouseEntered(MouseEvent e) {}
                    @Override
                    public void mouseExited(MouseEvent e) { }
                });
                add(tobeEdited);
                editC.setSelectedIndex(0);
            }

        }
        if (e.getSource() == cancelB){
            this.revalidate();
            if (tobeEdited != null) {
                tobeEdited.setVisible(false);
            }
            if (tobeDeleted != null) {
                tobeDeleted.setVisible(false);
            }

            deletLabel.setVisible(false);
            editLabel.setVisible(false);
        }
    }
}




