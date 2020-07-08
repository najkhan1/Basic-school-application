import javax.swing.*;
import javax.swing.event.CellEditorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.EventObject;

public class TeaSpinner extends NSpinner implements TableCellEditor {
    //NSpinner spinner;

    public TeaSpinner() {
    }

    public  TeaSpinner(SpinnerDateModel item){
        super(item);
        //spinner.setModel(item);
//        spinner.setEditor(new JSpinner.DateEditor(spinner, "dd-MM-yyyy"));
        JTextField tf = ((JSpinner.DefaultEditor) this.getEditor()).getTextField();
        tf.setForeground(Color.decode("#91266A"));
        tf.setBackground(Color.decode("#F8D6FA"));
        tf.setFont(new Font("Lucida Sans Typewriter", Font.BOLD,15));
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {

        return this;
    }

    @Override
    public Object getCellEditorValue() {
        return this.getValue();
    }

    @Override
    public boolean isCellEditable(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent) {
        return false;
    }

    @Override
    public boolean stopCellEditing() {
        return false;
    }

    @Override
    public void cancelCellEditing() {

    }

    @Override
    public void addCellEditorListener(CellEditorListener l) {

    }

    @Override
    public void removeCellEditorListener(CellEditorListener l) {

    }
}
