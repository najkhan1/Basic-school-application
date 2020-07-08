import javax.swing.table.AbstractTableModel;

public class nTabModel extends AbstractTableModel {
    public nTabModel(){
        //super(data,cols);
    }
    @Override
    public boolean isCellEditable(int row, int column){
        return false;
    }
    @Override
    public int getRowCount() {
        return 0;
    }

    @Override
    public int getColumnCount() {
        return 0;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return null;
    }
}
