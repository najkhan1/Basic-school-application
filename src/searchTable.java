import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class searchTable extends JScrollPane  {
    DefaultTableModel searchTableM0 ;
    JTable StuScroll ;
    nTabModel nm ;

    public searchTable(Object[] colNames,Object[][] data) {
        //setLocation(600, 100);
        setSize(650, 550);

        searchTableM0 = new DefaultTableModel(data, colNames);
        StuScroll = new JTable(searchTableM0);

        StuScroll.setFillsViewportHeight(true);
        StuScroll.setGridColor(Color.decode("#FAEBFA"));
        StuScroll.setRowHeight(40);
        StuScroll.setForeground(Color.decode("#AE52B3"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setBackground(Color.decode("#FAEBFA"));

        for(int x=0;x<colNames.length;x++){
            StuScroll.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        Dimension d = super.getPreferredSize();
        d.height = 50;
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer1.setBackground(Color.decode("#AE52B3"));
        centerRenderer1.setForeground(Color.decode("#FAEBFA"));
        centerRenderer1.setPreferredSize(d);
        StuScroll.getTableHeader().setDefaultRenderer(centerRenderer1);
        getViewport().add(StuScroll);
    }

    public searchTable(int search,String table, String colN,String colStu, Object[] arName) {
       //setLocation(600, 100);
        setSize(650, 550);

        RetriveData rv = new RetriveData();

        Object[][] temp = rv.searchByID(search,colStu, table, colN);
        System.out.println("name in search" + search);


        searchTableM0 = new DefaultTableModel(temp, arName);

        StuScroll = new JTable(searchTableM0);

        StuScroll.setFillsViewportHeight(true);
        StuScroll.setGridColor(Color.decode("#FAEBFA"));
        StuScroll.setRowHeight(40);
        StuScroll.setForeground(Color.decode("#AE52B3"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setBackground(Color.decode("#FAEBFA"));

        for(int x=0;x<arName.length;x++){
            StuScroll.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        Dimension d = super.getPreferredSize();
        d.height = 50;
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer1.setBackground(Color.decode("#AE52B3"));
        centerRenderer1.setForeground(Color.decode("#FAEBFA"));
        centerRenderer1.setPreferredSize(d);
        StuScroll.getTableHeader().setDefaultRenderer(centerRenderer1);

        getViewport().add(StuScroll);
        //setVisible(true);
    }
    public searchTable(String search,String table,String colN,String colReq,Object[] arName){

        //setLocation(600, 100);
        setSize(640, 550);

        RetriveData rv = new RetriveData();

        Object[][] temp = rv.searchByName (search,table,colN,colReq);
        System.out.println("name in search" + search);


        searchTableM0 = new DefaultTableModel(temp, arName);
        StuScroll = new JTable(searchTableM0);

        StuScroll.setFillsViewportHeight(true);
        StuScroll.setGridColor(Color.decode("#FAEBFA"));
        StuScroll.setRowHeight(40);
        StuScroll.setForeground(Color.decode("#AE52B3"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setBackground(Color.decode("#FAEBFA"));

        for(int x=0;x<arName.length;x++){
            StuScroll.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        Dimension d = super.getPreferredSize();
        d.height = 50;
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer1.setBackground(Color.decode("#AE52B3"));
        centerRenderer1.setForeground(Color.decode("#FAEBFA"));
        centerRenderer1.setPreferredSize(d);
        StuScroll.getTableHeader().setDefaultRenderer(centerRenderer1);


        getViewport().add(StuScroll);



    }
    public searchTable(String query, Object[] arName){

        //setLocation(600, 100);
        setSize(640, 550);

        RetriveData rv = new RetriveData();

        Object[][] temp = rv.getCurEnrolments(query);
       // System.out.println("name in search" + search);

        searchTableM0 = new DefaultTableModel(temp, arName);
        StuScroll = new JTable(searchTableM0);

        StuScroll.setFillsViewportHeight(true);
        StuScroll.setGridColor(Color.decode("#FAEBFA"));
        StuScroll.setRowHeight(40);
        StuScroll.setForeground(Color.decode("#AE52B3"));

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setBackground(Color.decode("#FAEBFA"));

        for(int x=0;x<arName.length;x++){

            StuScroll.getColumnModel().getColumn(x).setCellRenderer( centerRenderer );
        }
        Dimension d = super.getPreferredSize();
        d.height = 50;
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer1.setBackground(Color.decode("#AE52B3"));
        centerRenderer1.setForeground(Color.decode("#FAEBFA"));
        centerRenderer1.setPreferredSize(d);
        StuScroll.getTableHeader().setDefaultRenderer(centerRenderer1);


        getViewport().add(StuScroll);



    }
    public searchTable(Object[] arName,String query,JComboBox jc){

        ;
        setSize(640, 550);

        RetriveData rv = new RetriveData();

        Object[][] temp = rv.getCurEnrolments(query);

        System.out.println("length of the rigister = " + temp.length);
        searchTableM0 = new DefaultTableModel(temp, arName);
        DefaultCellEditor cellEditor = new DefaultCellEditor(jc);
        StuScroll = new JTable(searchTableM0);

        StuScroll.setFillsViewportHeight(true);
        StuScroll.setGridColor(Color.decode("#FAEBFA"));
        StuScroll.setRowHeight(40);
        StuScroll.setForeground(Color.decode("#AE52B3"));


        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer.setBackground(Color.decode("#FAEBFA"));

        for(int x=0;x<arName.length;x++){
            //if(2 !=x ) {
                StuScroll.getColumnModel().getColumn(x).setCellRenderer(centerRenderer);
            //}
            if(x==2){

                for (int i = 0;i<temp.length;i++) {
                    StuScroll.getModel().setValueAt("Click for options",i,2 );
                }
                StuScroll.getColumnModel().getColumn(x).setCellEditor(new DefaultCellEditor(jc));


            }
            if(x==1){

                Calendar cal = Calendar.getInstance();
                Date now = cal.getTime();
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                String strDate= formatter.format(now);
                //DefaultTableCellRenderer render = new DefaultTableCellRenderer();

               // render.setText(strDate+"");

                for (int i = 0;i<temp.length;i++) {
                    StuScroll.getModel().setValueAt(strDate,i,1 );
                }

                //StuScroll.getColumnModel().getColumn(x).setCellRenderer(render);
                //StuScroll.getColumnModel().getColumn(x).setCellEditor( (new TeaSpinner(model)));

            }
        }
        Dimension d = super.getPreferredSize();
        d.height = 50;
        DefaultTableCellRenderer centerRenderer1 = new DefaultTableCellRenderer();
        centerRenderer1.setHorizontalAlignment( JLabel.CENTER );
        centerRenderer1.setBackground(Color.decode("#AE52B3"));
        centerRenderer1.setForeground(Color.decode("#FAEBFA"));
        centerRenderer1.setPreferredSize(d);
        StuScroll.getTableHeader().setDefaultRenderer(centerRenderer1);
        getViewport().add(StuScroll);
    }
}


