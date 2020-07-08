import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.*;
import java.awt.event.*;

public class TeacherInterface extends NPanel implements ActionListener, MouseListener, FocusListener, ListSelectionListener {

    NButton lessB = new NButton("Lessons");
    searchTable lessonTable;
    CardLayout card = new CardLayout();
    //1150,550
    JPanel cHolder = new JPanel();
    searchTable regTable;

    NButton updateB = new NButton("Update Record");
    NButton updateReg = new NButton("update register");

    NButton searchB = new NButton("Search");
    NText searchT = new NText();
    NButton enrolB = new NButton("Enroll");
    NButton addStuB = new NButton("Add new Student");
    NButton cancelB = new NButton("Cancel");

    NRButton byName = new NRButton("Search by name");
    NRButton byID = new NRButton("Search by ID");
    NRButton byStudentID = new NRButton("Search by Student ID");
    NRButton byCourseID = new NRButton(" Search by Course ID");
    NRButton byTeach = new NRButton(" Search by Teacher ID");

    ButtonGroup rGroup = new ButtonGroup();

    String[] stuEnu = {"Search Student", "Search Enrollments", "Search courses","Search Lessons"};
    JComboBox stuEnuC = new JComboBox(stuEnu);

    searchTable StuScroll ;
    AddEnrolments addEnrollment = new AddEnrolments();

    AddStudent addStuP = new AddStudent();
    //AddStaff staffInt = new AddStaff();
    int teachID;
    EnuPanel enuPanel;

    public TeacherInterface(int TID) {
        teachID = TID;
        setLayout(null);
        cHolder.setSize(1150,550);
        cHolder.setLocation(10,100);
        cHolder.setLayout(card);
        cHolder.setBackground(Color.decode("#F8D6FA"));


        lessB.setLocation(185,20);
        lessB.setSize(170,60);
        lessB.addActionListener(this);
        add(lessB);

        updateReg.setLocation(220, 650);
        updateReg.setSize(190,40);
        updateReg.addActionListener(this);
        updateReg.setVisible(false);
        add(updateReg);

        cancelB.setLocation(420, 650);
        cancelB.addActionListener(this);
        cancelB.setVisible(false);
        add(cancelB);

        addStuB.setLocation(10, 20);
        addStuB.setSize(170, 60);
        addStuB.addActionListener(this);
        add(addStuB);

        searchB.setLocation(1050, 10);
        searchB.addActionListener(this);
        add(searchB);

        searchT.setLocation(800, 10);
        searchT.setBorder(BorderFactory.createLineBorder(Color.decode("#66B33E"), 2));
        searchT.addFocusListener(this);
        searchT.setSize(250, 40);
        add(searchT);

        byID.setLocation(920, 60);
        byID.setSize(120, 30);
        byID.addActionListener(this);
        add(byID);

        byName.setLocation(765, 60);
        byName.setSize(150,30);
        byName.addActionListener(this);
        add(byName);

        byStudentID.setLocation(735, 60);
        byStudentID.addActionListener(this);
        byStudentID.setVisible(false);
        add(byStudentID);

        byTeach.setLocation(735, 60);
        byTeach.addActionListener(this);
        byTeach.setVisible(false);
        add(byTeach);

        byCourseID.setLocation(545, 60);
        byCourseID.addActionListener(this);
        byCourseID.setVisible(false);
        add(byCourseID);

        rGroup.add(byName);
        rGroup.add(byID);
        rGroup.add(byStudentID);
        rGroup.add(byCourseID);
        rGroup.add(byTeach);

        stuEnuC.setLocation(1045, 60);
        stuEnuC.setSize(135, 30);
        stuEnuC.addActionListener(this);
        stuEnuC.setBorder(BorderFactory.createLoweredBevelBorder());
        stuEnuC.setBackground(Color.decode("#F8D6FA"));
        stuEnuC.setForeground(Color.decode("#91266A"));
        stuEnuC.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        add(stuEnuC);

        addStuP.setLocation(10, 100);
        addStuP.setVisible(false);
        add(addStuP);
    }
    public void hideShow(Component np) {
        Component[] jp = {addStuP, regTable,lessonTable};
        for (int i = 0; i < jp.length; i++) {
            if (jp[i] != null) {
                if (np == jp[i]) {
                    jp[i].setVisible(true);
                } else {
                    jp[i].setVisible(false);
                }
            }
        }
    }
    public void hideShow(NRButton np,NRButton sp){
        NRButton[] jp = {byName,byTeach,byCourseID,byStudentID};
        for(int i = 0;i<jp.length;i++){
            if(np == jp[i] || sp == jp[i] ){
                jp[i].setVisible(true);
                System.out.println("setting visible  " +jp[i].getName());

            }else{
                jp[i].setVisible(false);
                System.out.println("hiding  " +jp[i].getName());

            }
        }
    }
    public void colorChange(String np){
        NRButton[] jp = {byName,byTeach,byCourseID,byStudentID,byID};
        for(int i = 0;i<jp.length;i++){
            jp[i].setBackground(Color.decode(np));
        }
    }



        @Override
    public void actionPerformed(ActionEvent e) {
            if(e.getSource()==cancelB){
                regTable.setVisible(false);
                lessonTable.setVisible(false);
                cHolder.setVisible(false);
            }
            if (e.getSource() == enrolB) {
                hideShow(addEnrollment);
            }
            if (e.getSource() == addStuB) {
                hideShow(addStuP);
            }
            if (e.getSource() == byName || e.getSource() == byID || e.getSource() == byTeach || e.getSource() == byStudentID || e.getSource() == byCourseID) {
                colorChange("#ccffcc");

            }
            int selec = stuEnuC.getSelectedIndex();
            if(selec == 1){
                hideShow(byCourseID,byStudentID);
            }else if(selec==3){
                hideShow(byTeach,byCourseID);
            }else if(selec==0 || selec==2 ){
                byName.setVisible(true);
                byStudentID.setVisible(false);
                byCourseID.setVisible(false);
                byTeach.setVisible(false);
            }
        if(e.getSource() == updateReg){

            int rowNum = regTable.StuScroll.getRowCount();
            SaveToDB sv = new SaveToDB();
            for (int i = 0; i < rowNum; i++){
                //attendanceData[i][0] = regTable.StuScroll.getValueAt(i+1,0);
                String query = "INSERT INTO Register  VALUES ( null , Date '"+regTable.StuScroll.getValueAt(i,1).toString()
                        +"',  '"+regTable.StuScroll.getValueAt(i,2).toString()+"', "+Integer.parseInt(regTable.StuScroll.getValueAt(i,3).toString())
                        +", "+Integer.parseInt(regTable.StuScroll.getValueAt(i,4).toString())+"); ";

                sv.EditDetails(query);

            }
        }
        if (e.getSource() == lessB) {
            //hideShow(lessonTable);
            cHolder.setVisible(true);

            cancelB.setVisible(true);


            System.out.println("teach id teach class: " + teachID);
            String query = "SELECT * FROM lessons where TeaID = "+teachID+";";
            lessonTable = new searchTable(query,RetriveData.lesCols);
            lessonTable.setSize(1150,550);
            lessonTable.StuScroll.setSize(1140,540);
            lessonTable.setLocation(10,100);
            lessonTable.StuScroll.addMouseListener(this);

            //cHolder.add(lessonTable,"lessons");
            add(lessonTable);
            lessonTable.StuScroll.clearSelection();

        }

        if (e.getSource() == addStuB) {
           /* addEnrollment.setVisible(false);
            addEnrollment.setVisible(false);
            addStuP.setVisible(true);*/
            hideShow(addStuP);
        }
        if (e.getSource() == byName || e.getSource() == byID) {

            byName.setBackground(Color.WHITE);
            byID.setBackground(Color.WHITE);

        }
            if (e.getSource() == searchB) {

                int aCom = stuEnuC.getSelectedIndex();
                if((!byID.isSelected()) && (!byName.isSelected() && !byTeach.isSelected()
                        && !byCourseID.isSelected() && !byStudentID.isSelected())){
                    JOptionPane.showMessageDialog(null,"Please select one of the radio button options");
                    colorChange("#FF1632");

                    return;
                }
                if(byName.isSelected() && aCom == 0) {
                    String colStu = "StuID, StuName, StuAddress, DOB";
                    String search = searchT.getText();
                    StuScroll = new searchTable(search,"Students","StuName",colStu,RetriveData.stuCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                    StuScroll.StuScroll.getSelectionModel().addListSelectionListener(this);

                }
                if(byID.isSelected() && aCom == 0) {
                    String colStu = "StuID, StuName, StuAddress, DOB";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Students","StuID",colStu,RetriveData.stuCols);
                    StuScroll.StuScroll.getSelectionModel().addListSelectionListener(this);

                    StuScroll.setLocation(10,100);
                    add(StuScroll);


                }
                if(byID.isSelected() && aCom==3){
                    String colLes = "LesID, LesDay,LesStart,LesDuration,TeaID,CorID,RoomNo";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Lessons","LesID",colLes,RetriveData.lesCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                }
                if(byTeach.isSelected() && aCom == 3){
                    String colLes = "LesID, LesDay,LesStart,LesDuration,TeaID,CorID,RoomNo";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Lessons","TeaID",colLes,RetriveData.lesCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                }
                if(byCourseID.isSelected() && aCom == 3){
                    String colLes = "LesID, LesDay,LesStart,LesDuration,TeaID,CorID,RoomNo";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Lessons","CorID",colLes,RetriveData.lesCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                }
                if (aCom == 1 && byID.isSelected()) {
                    String colEnu = "EnuID, EnuDate, Grade, StuID, CorID";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Enrolments","EnuID",colEnu,RetriveData.enrolCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                }
                if(byStudentID.isSelected() && aCom == 1){
                    String colEnu = "EnuID, EnuDate, Grade, StuID, CorID";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Enrolments","StuID",colEnu,RetriveData.enrolCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                }
                if(byCourseID.isSelected() && aCom == 1){
                    String colEnu = "EnuID, EnuDate, Grade, StuID, CorID";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Enrolments","CorID",colEnu,RetriveData.enrolCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);

                }
                if(byName.isSelected() && aCom == 2){

                    String colCor = "CorID, CorName, CorStatus";
                    String search = searchT.getText();
                    StuScroll = new searchTable(search,"Courses","CorName",colCor,RetriveData.corCols);
                    StuScroll.setLocation(10,100);
                    add(StuScroll);
                }
                if(byID.isSelected() && aCom == 2){
                    String colCor = "CorID, CorName, CorStatus";
                    int search = Integer.parseInt(searchT.getText());
                    StuScroll = new searchTable(search,"Courses","CorID",colCor,RetriveData.corCols);
                    StuScroll.setLocation(10,100);
                    StuScroll.StuScroll.setDefaultEditor(Object.class, null);
                    add(StuScroll);
                }
                hideShow(StuScroll);
            }
    }

    @Override
    public void focusGained(FocusEvent e) {

    }

    @Override
    public void focusLost(FocusEvent e) {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int lesID = Integer.parseInt(lessonTable.StuScroll.getValueAt(lessonTable.StuScroll.getSelectedRow(),0).toString());
        String query = "SELECT * FROM Register where LesID = "+lesID+" AND RegDate IS null;";
        String [] list1 = {"","Present","Absent","Sick","Other"};
        DefaultComboBoxModel model = new DefaultComboBoxModel(list1);
        JComboBox com = new JComboBox(model);
        updateReg.setVisible(true);
        com.addActionListener(this);
        //com.setSelectedIndex(0);
        regTable = new searchTable(RetriveData.regCols,query,com);
        regTable.setSize(1150,550);
        regTable.StuScroll.setSize(1140,540);
        regTable.setLocation(10,100);
        regTable.StuScroll.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent f) {
                if(com.getSelectedIndex() != 0 && regTable.StuScroll.getSelectedRow() != 0 && regTable.StuScroll.getSelectedRow() <=regTable.StuScroll.getRowCount()){
                // regTable.StuScroll.setValueAt(list1[com.getSelectedIndex()],regTable.StuScroll.getSelectedRow(),2);
                 regTable.searchTableM0.fireTableDataChanged();
                }

            }
            @Override
            public void mousePressed(MouseEvent e) { }
            @Override
            public void mouseReleased(MouseEvent e) { }
            @Override
            public void mouseEntered(MouseEvent e) { }
            @Override
            public void mouseExited(MouseEvent e) { }});
        lessonTable.setVisible(false);
        add(regTable);
        //card.show(cHolder,"register");
    }
    @Override
    public void mousePressed(MouseEvent e) { }
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) { }
    @Override
    public void mouseExited(MouseEvent e) { }

    @Override
    public void valueChanged(ListSelectionEvent e) {
        int id = Integer.parseInt(StuScroll.StuScroll.getValueAt(StuScroll.StuScroll.getSelectedRow(), 0).toString());
        enuPanel = new EnuPanel(id);
        add(enuPanel);
        StuScroll.setVisible(false);
    }
}