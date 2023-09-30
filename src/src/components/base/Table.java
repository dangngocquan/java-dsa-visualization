package src.components.base;


import javax.swing.*;
import java.awt.*;

public class Table extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;
    private JTable table;
    private JScrollPane scrollPane;

    public Table(int x, int y, int width, int height, Object[][] rowData, Object[] columnNames) {
        super();
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);

        table = new JTable(rowData, columnNames);
        table.setFont(new Font("Arial", Font.BOLD, 18));
        table.setEnabled(false);
        int gapWidth = 10;
        int gapHeight = 4;
        table.setIntercellSpacing(new Dimension(gapWidth, gapHeight));
        scrollPane = new JScrollPane(table);
        scrollPane.setBounds(0, 0, width, height);

        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 18));
        table.getTableHeader().setBounds(0, 0, width, 30);

        add(table.getTableHeader());
        add(scrollPane);

        setVisible(true);
    }

    public JTable getTable() {
        return table;
    }
}
