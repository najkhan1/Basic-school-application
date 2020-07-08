import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class AddLessons extends JPanel implements ActionListener , MouseListener, FocusListener {

    NLText lesID = new NLText("Lesson ID");
    String[] list = {"Monday","Tuesday","Wednesday","Thursday","Friday"};
    NLText dayOfWeek = new NLText("Day of week",list);
    NLText lesDuration = new NLText("Duration");
    NLText teaID = new NLText("Teacher ID");
    NLText corID = new NLText("Course ID");
    NLText roomNo = new NLText("Room Number");
    NLText lStart = new NLText("Start time");
    JLabel en = new JLabel("Add Lessons");
    JLabel curLes = new JLabel("Lesson already assigned to this teacher");
    JLabel corLabel = new JLabel("Please select course for the Lesson");

    NButton addB = new NButton("Add");
    CButton cancelB = new CButton("Cancel");
    SaveToDB sv1 = new SaveToDB();
    JPanel panelLes = new JPanel();
    CardLayout card = new CardLayout();
    JPanel cardHolder = new JPanel();
    String query = "SELECT t.TeaID,s.StaName, t.TeaQualification FROM staff s, teachers t WHERE s.StaID = t.StaID";
    Object[] teaCol = {"Teacher ID","Teacher Name","Qualifications"};
    searchTable teaTable = new searchTable(query,teaCol);
    searchTable crT;
    RetriveData rv = new RetriveData();
    int id = 0;

    searchTable slTLTable;

    public AddLessons() {
        setSize(1150, 550);
        setLayout(null);
        addFocusListener(this);

        cardHolder.setSize(280,450);
        cardHolder.setLocation(5,5);
        cardHolder.setLayout(card);

        panelLes. setSize(280, 450);
        panelLes.setLocation(5,5);
        panelLes.setLayout(null);

        teaTable.setSize(280,450);
        teaTable.setLocation(5,5);
        teaTable.StuScroll.addMouseListener(this);
        teaTable.StuScroll.addFocusListener(this);

        curLes.setSize(300,30);
        curLes.setForeground(Color.decode("#AE52B3"));
        curLes.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        curLes.setLocation(700,5);
        curLes.setVisible(false);
        add(curLes);

        corLabel.setSize(300,30);
        corLabel.setForeground(Color.decode("#AE52B3"));
        corLabel.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,12));
        corLabel.setLocation(300,5);
        corLabel.setVisible(false);
        add(corLabel);

        lesID.setLocation(10, 70);
        lesID.TF.setText((sv1.getIDA("Lessons", "LesID") + 1) + "");
        lesID.TF.setEditable(false);
       panelLes.add(lesID);

        en.setBackground(Color.WHITE);
        en.setForeground(Color.decode("#800080"));
        en.setFont(new Font("Serif", Font.ITALIC, 40));
        en.setSize(250, 70);
        en.setLocation(10, 10);
        //en.setForeground(Color.blue);
        panelLes.add(en);


        lesDuration.setLocation(10, 105);
        panelLes.add(lesDuration);

        lStart.setLocation(10, 145);
        panelLes.add(lStart);

        dayOfWeek.setLocation(10, 185);
        panelLes.add(dayOfWeek);

        corID.setLocation(10, 225);
        panelLes.add(corID);

        roomNo.setLocation(10, 265);
        panelLes.add(roomNo);

        teaID.setLocation(10, 305);
        panelLes.add(teaID);

        addB.setLocation(5, 370);
        addB.addActionListener(this);
        panelLes.add(addB);
        cancelB.setLocation(140, 370);
        cancelB.addActionListener(this);
        panelLes.add(cancelB);

        cardHolder.add(teaTable,"teaTable");
        cardHolder.add(panelLes,"lesPanel");
        add(cardHolder);


        teaID.TF.setEditable(false);
        corID.TF.setEditable(false);




    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addB) {
           // int id2 = Integer.parseInt(crT.StuScroll.getValueAt(crT.StuScroll.getSelectedRow(), 0).toString());
            Object[] b = {lesID.TF.getText(),lStart.TF.getText(),lesDuration.TF.getText(),list[dayOfWeek.jc.getSelectedIndex()],corID.TF.getText()};
            int valData = rv.validateLes(id, b);
            System.out.println("length of  valdata = "+ valData);
            if(valData == 0) {
                SaveToDB sv = new SaveToDB();
                sv.setLesson(Integer.parseInt(lesID.TF.getText()), list[dayOfWeek.jc.getSelectedIndex()],
                        Integer.parseInt(lesDuration.TF.getText()), Integer.parseInt(teaID.TF.getText()),
                        Integer.parseInt(corID.TF.getText()), Integer.parseInt(roomNo.TF.getText()),
                        Integer.parseInt(lStart.TF.getText()));

                System.out.println("enu ID = " + id);
               rv.addRegForLes(Integer.parseInt(lesID.TF.getText()));
                int id = sv1.getIDA("Lessons", "LesID") + 1;
                lesID.TF.setText(id + "");
                dayOfWeek.jc.setSelectedIndex(0);
                lesDuration.TF.setText(null);
                teaID.TF.setText(null);
                corID.TF.setText(null);
                roomNo.TF.setText(null);
                lStart.TF.setText(null);
                JOptionPane.showMessageDialog(this,
                        "Lesson added to database successfully");
            }
        }
        if (e.getSource() == cancelB) {
            panelLes.setVisible(false);
            curLes.setVisible(false);
            corLabel.setVisible(false);
            slTLTable.setVisible(false);
            crT.setVisible(false);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

        if(teaTable.StuScroll.hasFocus()){
            id = Integer.parseInt(teaTable.StuScroll.getValueAt(teaTable.StuScroll.getSelectedRow(),0).toString());
            teaID.TF.setText(id+"");
            card.show(cardHolder,"lesPanel");
            String query2 ="SELECT l.LesID,l.CorID,l.LesStart,l.LesDay,l.lesDuration from Lessons l where l.TeaID = "+id+";";
            Object[] selTLCol = {"Lesson ID","Course ID","Lesson Start","Day","Duration"};
            slTLTable = new searchTable(query2,selTLCol);
            slTLTable.setSize(440,450);
            slTLTable.setLocation(700,40);
            add(slTLTable);
            curLes.setVisible(true);
            corLabel.setVisible(true);
            String query3 = "SELECT * FROM Courses where CorStatus = 'active'";
            Object[] corCols = {"Course ID","Course Name","Course Status"};
            crT = new searchTable(query3,corCols);
            crT.setSize(390,450);
            crT.setLocation(300,40);
            crT.StuScroll.addMouseListener(this);
            crT.StuScroll.addFocusListener(this);
            add(crT);
        }
        if(crT.StuScroll.hasFocus()) {


            int id2 = Integer.parseInt(crT.StuScroll.getValueAt(crT.StuScroll.getSelectedRow(), 0).toString());

            // l.LesID,l.LesStart,l.LesDuration,l.LesDay,l.CorID
            corID.TF.setText(id2 + "");

        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void focusGained(FocusEvent e) {


    }

    @Override
    public void focusLost(FocusEvent e) {
        if(this.isVisible()) {
           // setVisible(false);
        }
    }
}