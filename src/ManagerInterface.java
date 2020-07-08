import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ManagerInterface extends NPanel implements ActionListener, FocusListener , ListSelectionListener{

    NButton addStaff = new NButton("Add staff");
    NButton searchB = new NButton("Search");
    NText searchT = new NText();
    NButton enrolB = new NButton("Enroll");
    NButton addStuB = new NButton("Add new Student");

    NButton deleteB = new NButton("Edit");
    DeletePanel delPanel = new DeletePanel();

    NButton addCourseB = new NButton("Add Course");
    AddCourse addCourse = new AddCourse();

    NButton addLessonB = new NButton("Add Lesson");
    AddLessons addLessons ;

    NRButton byName = new NRButton("Search by name");
    NRButton byID = new NRButton("Search by ID");
    NRButton byStudentID = new NRButton("Search by Student ID");
    NRButton byCourseID = new NRButton(" Search by Course ID");
    NRButton byTeach = new NRButton(" Search by Teacher ID");

    ButtonGroup rGroup = new ButtonGroup();

    String[] stuEnu = {"Search Student", "Search Enrollments", "Search courses","Search Lessons","Search Staff"};

    JComboBox stuEnuC = new JComboBox(stuEnu);

    searchTable StuScroll ;

    AddStudent addStuP = new AddStudent();
    AddStaff staffInt = new AddStaff();
    //AddEnrolments addEnrollment = new AddEnrolments();
    EnuPanel enuPanel;

    public ManagerInterface() {

        setLayout(null);

        addStuB.setLocation(10, 20);
        addStuB.setSize(170, 60);
        addStuB.addActionListener(this);
        add(addStuB);


        addStaff.setLocation(360, 20);
        addStaff.setSize(170, 60);
        addStaff.addActionListener(this);

        add(addStaff);

        addCourseB.setLocation(185,20);
        addCourseB.setSize(170, 60);
        addCourseB.addActionListener(this);
        add(addCourseB);

        addLessonB.setLocation(535, 20);
        addLessonB.setSize(170, 60);
        addLessonB.addActionListener(this);
        add(addLessonB);


        searchB.setLocation(1050, 10);
        searchB.addActionListener(this);
        add(searchB);

        deleteB.setLocation(708,10);
        deleteB.setSize(90, 40);

        deleteB.setBackground(Color.decode("#c73200"));
        deleteB.addActionListener(this);
        add(deleteB);

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

        addCourse.setLocation(10, 100);
        addCourse.setVisible(false);
        add(addCourse);

        staffInt.setLocation(10, 100);
        staffInt.setVisible(false);
        add(staffInt);


        delPanel.setLocation(10,100);
        delPanel.setVisible(false);
        add(delPanel);
    }

    public void hideShow(Component np){
        Component[] jp = {addStuP,addCourse,staffInt,addLessons,StuScroll, delPanel};
        for(int i = 0;i<jp.length;i++){
            if(jp[i]!=null) {
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
    public void checkSelection(){
        if((!byID.isSelected()) && (!byName.isSelected() && !byTeach.isSelected()
                && !byCourseID.isSelected() && !byStudentID.isSelected())){
            JOptionPane.showMessageDialog(null,"Please select one of the radio button options");
            colorChange("#FF1632");

            return;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == deleteB){

            hideShow(delPanel);
        }
        if (e.getSource() == addStaff) {
            hideShow(staffInt);
        }
        if (e.getSource() == addStuB) {
           hideShow(addStuP);
        }
        if (e.getSource() == addCourseB) {
            hideShow(addCourse);
        }
        if(e.getSource() == addLessonB){
            addLessons = new AddLessons();
            addLessons.setLocation(10,100);
            addLessons.setVisible(false);
            add(addLessons);
            hideShow(addLessons);
        }
        try {
            if (addLessons.isVisible()) {
                addLessonB.setEnabled(false);
            } else {
                addLessonB.setEnabled(true);
            }
        }catch (Exception a){
        }
        if(stuEnuC.getSelectedIndex() == 1 || stuEnuC.getSelectedIndex()==3){
            //remove(addCourseB);
                    addLessonB.setLocation(535, 5);
                    addLessonB.setSize(150, 50);
        }else {
            addLessonB.setLocation(535, 20);
            addLessonB.setSize(170, 60);
        }
        if (e.getSource() == byName || e.getSource() == byID || e.getSource() == byTeach || e.getSource() == byStudentID || e.getSource() == byCourseID) {
            colorChange("#ccffcc");
        }
        int selec = stuEnuC.getSelectedIndex();
        if(selec == 1){
            hideShow(byCourseID,byStudentID);
        }else if(selec==3){
            hideShow(byTeach,byCourseID);
        }else if(selec==0 || selec==2 || selec==4){
            byName.setVisible(true);
            byStudentID.setVisible(false);
            byCourseID.setVisible(false);
            byTeach.setVisible(false);
        }
        if (e.getSource() == searchB) {

            int aCom = stuEnuC.getSelectedIndex();
            checkSelection();

            if(byName.isSelected() && aCom == 0) {
                String colStu = " StuID, StuName, StuAddress, DOB ";
                String search = searchT.getText();
                StuScroll = new searchTable(search," Students "," StuName ",colStu,RetriveData.stuCols);
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
                add(StuScroll);
            }

            if((byName.isSelected()) && (aCom == 4)){
                String colStaff = "StaID, StaName, StaAddress, StaNI,StaSalary,UserName, Position";
                String search = searchT.getText();
                StuScroll = new searchTable(search,"Staff","StaName",colStaff,RetriveData.staffCols);
                StuScroll.setLocation(10,100);
                add(StuScroll);
            }
            if(byID.isSelected() && aCom == 4){
                String colStaff = "StaID, StaName, StaAddress, StaNI,StaSalary,UserName, Position";
                int search = Integer.parseInt(searchT.getText());
                StuScroll = new searchTable(search,"Staff","StaID",colStaff,RetriveData.staffCols);
                StuScroll.setLocation(10,100);
                add(StuScroll);
            }

            hideShow(StuScroll);
        }
    }
    @Override
    public void focusGained(FocusEvent e) {

        try{
            StuScroll.setVisible(false);
            System.out.println("removing table student");
        }catch(Exception ea){
            System.out.println("error no table yet");
        }

    }

    @Override
    public void focusLost(FocusEvent e) {

    }
        @Override
        public void valueChanged(ListSelectionEvent event) {

            int id = Integer.parseInt(StuScroll.StuScroll.getValueAt(StuScroll.StuScroll.getSelectedRow(), 0).toString());
            enuPanel = new EnuPanel(id);
            add(enuPanel);
            StuScroll.setVisible(false);
        }
    }
