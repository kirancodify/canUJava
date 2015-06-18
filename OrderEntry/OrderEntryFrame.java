package oe;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Rectangle;
import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;

public class OrderEntryFrame extends JInternalFrame {
    BorderLayout borderLayout1 = new BorderLayout();
    private JPanel jPanel1 = new JPanel();
    private GridLayout gridLayout1 = new GridLayout();
    private JPanel jPanel2 = new JPanel();
    private JScrollPane jScrollPane1 = new JScrollPane();
    private JLabel jLabel1 = new JLabel();
    private JTextField jTextField1 = new JTextField();

    public OrderEntryFrame() {
        try {
            jbInit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void jbInit() throws Exception {
        this.setBounds(0, 0, 400, 300);
        this.getContentPane().setLayout(borderLayout1);
        jPanel1.setLayout(gridLayout1);
        gridLayout1.setColumns(1);
        gridLayout1.setRows(2);
        jPanel2.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
        jPanel2.setLayout(null);
        jLabel1.setText("Order Id");
        jLabel1.setBounds(new Rectangle(10, 10, 50, 25));
        jTextField1.setBounds(new Rectangle(60, 15, 85, 20));
        jPanel2.add(jTextField1, null);
        jPanel2.add(jLabel1, null);
        jPanel1.add(jPanel2, null);
        jPanel1.add(jScrollPane1, null);
        this.getContentPane().add(jPanel1, BorderLayout.CENTER);
        this.setMaximizable(true);
        this.setIconifiable(true);
        this.setClosable(true);

    }
}
