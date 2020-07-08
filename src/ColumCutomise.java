import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

public class ColumCutomise extends JLabel implements TableCellRenderer {

    public ColumCutomise() {
        setFont(new Font("Consolas", Font.BOLD, 14));
        setOpaque(true);
        setForeground(Color.decode("#FAEBFA"));
       // setBorder(BorderFactory.createLoweredBevelBorder());
        setBackground(Color.decode("#AE52B3"));
        Dimension d = super.getPreferredSize();
        d.height = 32;
        setPreferredSize(d);

    }
    public ColumCutomise(int num) {
        setFont(new Font("Consolas", Font.BOLD, 14));
        setOpaque(true);
        setForeground(Color.decode("#AE52B3"));
        //setBorder(BorderFactory.createEtchedBorder());
        setBackground(Color.decode("#FAEBFA"));
        Dimension d = super.getPreferredSize();
        d.height = 40;
        setPreferredSize(d);

    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
                                                   boolean isSelected, boolean hasFocus,
                                                   int row, int column) {
        setText(value.toString());
        return this;
    }

}