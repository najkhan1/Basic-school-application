import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainClass extends JFrame  implements ActionListener {
    StaffInterface staffP;
    NPanel cHolder = new NPanel();
    NPanel mainPanel = new NPanel();
    CardLayout card = new CardLayout();
    NButton loginB = new NButton("Login");
    NButton logout = new NButton("Logout");
    ManagerInterface managerInterface;
    TeacherInterface teacherPanel;

    NLText loginT = new NLText("User Name");
    NLText passT = new NLText("Password",true);
    JPanel loginPanel = new JPanel();



    public MainClass(){
        setSize(1200,800);
        setLayout(null);

        mainPanel.setLayout(null);

        loginPanel.setLayout(null);
        loginPanel.setSize(280,150);
        loginPanel.setLocation(200,300);
        loginPanel.setBackground(Color.decode("#91266A"));
        mainPanel.add(loginPanel);

        mainPanel.setSize(1200,800);


        loginT.setLocation(5,20);
        passT.setLocation(5,80);
        loginPanel.add(loginT);
        loginPanel.add(passT);

        logout.setLocation(10,715);
        logout.setBackground(Color.decode("#0ad1f0"));
        logout.addActionListener(this);
        add(logout);

        cHolder.setLayout(card);
        add(cHolder);



        loginB.setLocation(330,455);
        loginB.addActionListener(this);
        mainPanel.add(loginB);

        cHolder.add(mainPanel,"main");



        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == loginB){
            RetriveData rv = new RetriveData();

            int check = rv.loginCheck(loginT.TF.getText(), passT.pass.getText());
            System.out.println("Password == " + passT.pass.getText());
            System.out.println( " check == " + check);
            loginT.TF.setText(null);
            passT.pass.setText(null);
            if (check == -1){
                managerInterface = new ManagerInterface();
                cHolder.add(managerInterface,"manager");
                card.show(cHolder,"manager");
            }else if (check == -2){
                staffP = new StaffInterface();
                cHolder.add(staffP,"staff");
                card.show(cHolder,"staff");
            }else if( check != 0 && check != -1 && check != -2){
                teacherPanel = new TeacherInterface(check);
                cHolder.add(teacherPanel,"teacher");
                card.show(cHolder,"teacher");
            }
        }
        if(e.getSource() == logout){
            card.show(cHolder,"main");
        }
    }
    public static void main(String[] args) throws  Exception{
        new MainClass();

    }
    }

