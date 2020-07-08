import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class EnuPanel extends JPanel  implements ActionListener, MouseListener {

    NButton enuB = new NButton("Enrol");
    NButton editB = new NButton("Edit");
    CButton cancelB = new CButton("Cancel");
    JLabel lb = new JLabel("  Lessons for already enrolled courses ");
    JLabel corLab = new JLabel(" Please select a course");

    int corID = 0;

    JFrame jFrame;
    NButton confirm = new NButton("Continue?");
    CButton cancel1 = new CButton("Cancel");

    searchTable avCrTab;
    AddEnrolments addEnrollment = new AddEnrolments();
    EditStudent editStudent;
    private  int ID;

    public EnuPanel(int ID){

        this.ID = ID;
        setLocation(10,100);
        setSize(1150, 550);
        setLayout(null);
        setBackground(Color.decode("#F8D6FA"));

        enuB.setLocation(10,10);
        enuB.addActionListener(this);
        add(enuB);

        editB.setLocation(150,10);
        editB.addActionListener(this);
        add(editB);

        cancelB.setLocation(20,490);
        cancelB.addActionListener(this);
        add(cancelB);
        //
        lb.setLocation(700,30);
        lb.setSize(360,30);
        lb.setForeground(Color.decode("#AE52B3"));
        lb.setBorder(BorderFactory.createRaisedBevelBorder());
        lb.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
        lb.setVisible(false);
        add(lb);

        corLab.setLocation(320,30);
        corLab.setBorder(BorderFactory.createRaisedBevelBorder());
        corLab.setSize(280,30);
        corLab.setForeground(Color.decode("#AE52B3"));
        corLab.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,18));
        corLab.setVisible(false);
        add(corLab);

        addEnrollment.setLocation(5,60);
        addEnrollment.corID.TF.setEditable(false);
        addEnrollment.stID.TF.setText(this.ID +"");
        addEnrollment.setVisible(false);
        add(addEnrollment);


    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cancelB){

            this.setVisible(false);


        }
        if(e.getSource() == enuB){
            addEnrollment.setVisible(true);
            //EditStudent.setVisible(false);
            lb.setVisible(true);
            corLab.setVisible(true);
            Object[] arName = {"current course","Lesson Day", "Lesson start","Duration"};
            String query = "SELECT l.CorID,l.LesDay,l.LesStart,l.LesDuration from lessons l where l.CorID IN(SELECT c.CorID " +
                    "from enrolments c WHERE c.StuID =  "+this.ID+") AND l.CorID IN (SELECT e.CorID FROM courses e WHERE e.CorStatus = 'active');";
/*
            SELECT l.CorID,l.LesDay,l.LesStart,l.LesDuration from lessons l where l.CorID IN(SELECT c.CorID
            from enrolments c WHERE c.StuID =  78) AND l.CorID IN (SELECT e.CorID FROM courses e WHERE e.CorStatus = 'Active');*/
            searchTable enTab = new searchTable(query,arName);
            //350 witdh
            enTab.setSize(380,420);
            enTab.setLocation(685,60);
            enTab.StuScroll.setCellSelectionEnabled(false);
            enTab.StuScroll.setRowSelectionAllowed(false);
            enTab.setBorder(BorderFactory.createRaisedBevelBorder());
            enTab.StuScroll.setDefaultEditor(Object.class, null);
            add(enTab);
            //SELECT c.CorID, (SELECT 25-count(a.CorID) FROM enrolments a WHERE c.CorID = a.CorID) as enrols FROM courses c WHERE c.CorID NOT IN (SELECT l.CorID from enrolments l WHERE l.StuID=78)
            //AND c.CorStatus = 'Active'
            //SELECT c.CorID, c.CorName FROM courses c WHERE c.CorID NOT IN(SELECT e.CorID from enrolments e where StuID =
            String query1 = "SELECT c.CorID,c.CorName, (SELECT 25-count(a.CorID) FROM enrolments a WHERE c.CorID = a.CorID) as enrols FROM courses c WHERE c.CorID NOT IN (SELECT l.CorID from enrolments l WHERE l.StuID= "+this.ID+") AND c.CorStatus = 'Active';";
            Object[] arNameCor = {"Course ID","Course Name","Remaining Capacity"};
            avCrTab = new searchTable(query1,arNameCor);
            avCrTab.setSize(380,420);

            avCrTab.setLocation( 295,60);
            avCrTab.StuScroll.addMouseListener(this);

            add(avCrTab);
        }
        if(e.getSource() == editB){
            editStudent = new EditStudent(ID);
            editStudent.setLocation(5,60);
            editStudent.setVisible(false);
            add(editStudent);
            editStudent.setVisible(true);
            addEnrollment.setVisible(false);
        }
        /*if(e.getSource()== confirm){
            addEnrollment.corID.TF.setText(corID + "");
        }*/
        if(e.getSource() == cancel1){
           // jFrame.dispatchEvent(e);
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {

        corID = Integer.parseInt(avCrTab.StuScroll.getValueAt(avCrTab.StuScroll.getSelectedRow(), 0).toString());
        System.out.println("cor IDDD " + corID);
        RetriveData rv = new RetriveData();
        Object[][] data = rv.validateEnu(this.ID, corID);
        // lessID x of corID y startTime z on dayofweek overlaps with lessonID xx of coID yy start StartTime zz by mm mins

        if (data[0][0] == null) {
            addEnrollment.corID.TF.setText(corID + "");
        } else if(data[0][0] != null) {

            jFrame = new JFrame();
            jFrame.setLayout(null);
            jFrame.setSize(750,400);
            jFrame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
            /*JPanel pp = new JPanel();
            pp.setSize(700,200);

            pp.setLocation(5,5);
            pp.setLayout(null);*/
            String [] jlAr = new String[data.length];
            JList ar = new JList(jlAr);
            DefaultListCellRenderer centerRenderer1 = new DefaultListCellRenderer();
            Dimension d = super.getPreferredSize();
            d.height = 70;
            ar.setBackground(Color.decode("#AE52B3"));
            ar.setForeground(Color.decode("#FAEBFA"));
            ar.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
            centerRenderer1.setPreferredSize(d);
            centerRenderer1.setBorder(BorderFactory.createLineBorder(Color.BLACK,5));
            ar.setCellRenderer(centerRenderer1);

            int yPos = 5;
            for (int i = 0; i < data.length; i++) {
                if (data[i][0] != null) {
                    String cols = "<HTML>Current Lesson ID " + data[i][0] + ", Of Current course ID " + data[i][1] + ", Starting at: " + data[i][2] + ", on " + data[i][3] +
                            " <br>Overlaps with Lesson ID :" + data[i][4] + ", <br>of selected course with ID : " + data[i][5] + ", starting at :" + data[i][6] + " by " + data[i][7] + "mins</HTML>";
                    System.out.println("lesssson ID " + data[i][0] );

                    jlAr[i] =cols;
                }
            }

            Object [] col = {"Lesson Overlaps"};
            JScrollPane nsp = new JScrollPane(ar);
            //nsp.getViewport().add(pp);
            nsp.setSize(700,200);
            nsp.setBackground(Color.decode("#ccffcc"));
            nsp.setLocation(10,10);

            confirm.setLocation(10,220);
            confirm.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addEnrollment.corID.TF.setText(corID + "");
                    jFrame.setVisible(false);
                }
            });

            cancel1.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    jFrame.setVisible(false);
                }
            });
            cancel1.setLocation(180,220);
            jFrame.getContentPane().add(nsp);
            jFrame.getContentPane().add(confirm);
            jFrame.getContentPane().add(cancel1);
            jFrame.setVisible(true);
           // repaint();
          // JOptionPane.showConfirmDialog(null,new NScrollPane().getViewport().add(pp));

    }
        data = null;
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
}
