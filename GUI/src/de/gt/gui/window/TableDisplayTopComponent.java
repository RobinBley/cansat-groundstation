package de.gt.gui.window;

import de.gt.api.config.Config;
import java.util.Map;
import java.util.stream.IntStream;
import javax.swing.table.DefaultTableModel;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@TopComponent.Description(
        preferredID = "TableDisplayTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@Messages({
    "CTL_TableDisplayAction=TableDisplay",
    "CTL_TableDisplayTopComponent=TableDisplay Window",
    "HINT_TableDisplayTopComponent=This is a TableDisplay window"
})
public final class TableDisplayTopComponent extends DataReceiverComponent {

    public Config config;

    public TableDisplayTopComponent(Config config) {
        super();
        initComponents();
        setName("Table Visualisation");
        setToolTipText(Bundle.HINT_TableDisplayTopComponent());

        this.config = config;
        configChanged(config);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "null"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTable1.setColumnSelectionAllowed(true);
        jScrollPane1.setViewportView(jTable1);
        jTable1.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

    private DefaultTableModel model = null;

    @Override
    public void receive(Map<String, Double> datum) {
        Object[] rowData = datum.values().toArray();
        model.addRow(rowData);
    }

    @Override
    public void configChanged(Config newConfig) {
        Object[] keys = newConfig.getKeys().toArray();

        model = new DefaultTableModel(keys, 0) {
            private static final long serialVersionUID = 1L;

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        jTable1.setModel(model);
    }

    @Override
    public void clearData() {
        //Alle Zeilen löschen
        IntStream.range(0, model.getRowCount()).forEach(n -> model.removeRow(n));
    }
}
