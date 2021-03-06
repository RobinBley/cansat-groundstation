package de.gt.gui.dialog.sources;

import de.gt.api.config.Config;
import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.sources.DataSourceConfiguration;
import de.gt.api.sources.DataSourceConfigurationDialog;
import de.gt.gui.sources.DebugGenerator;
import org.openide.util.Lookup;

/**
 *
 * @author Kevin
 */
public class DebugConfigurationDialog extends DataSourceConfigurationDialog {

    DataSourceConfiguration configuration;

    /**
     * Creates new form DebugConfigurationForm
     */
    public DebugConfigurationDialog() {
        configuration = new DataSourceConfiguration();
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSetupCompleted = new javax.swing.JToggleButton();
        txtDummyGenerator = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        org.openide.awt.Mnemonics.setLocalizedText(btnSetupCompleted, org.openide.util.NbBundle.getMessage(DebugConfigurationDialog.class, "DebugConfigurationDialog.btnSetupCompleted.text")); // NOI18N
        btnSetupCompleted.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSetupCompletedActionPerformed(evt);
            }
        });

        txtDummyGenerator.setEditable(false);
        txtDummyGenerator.setText(org.openide.util.NbBundle.getMessage(DebugConfigurationDialog.class, "DebugConfigurationDialog.txtDummyGenerator.text")); // NOI18N

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtDummyGenerator)
                    .addComponent(btnSetupCompleted, javax.swing.GroupLayout.DEFAULT_SIZE, 370, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txtDummyGenerator, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSetupCompleted)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnSetupCompletedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSetupCompletedActionPerformed
        DataPipeline pipeline = Lookup.getDefault().lookup(DataPipeline.class);

        //Aktuelle Config aus Pipeline lesen
        Config actualConfig = pipeline.getConfig();

        //DebugGenerator auf Basis der aktuellen Satellitenkonfiguration initialisieren
        configuration.setConfigurationItem(DebugGenerator.class, DebugGenerator.createWithKeys(actualConfig.getKeys()));

        dispose();
    }//GEN-LAST:event_btnSetupCompletedActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnSetupCompleted;
    private javax.swing.JTextField txtDummyGenerator;
    // End of variables declaration//GEN-END:variables

    @Override
    public DataSourceConfiguration getConfiguration() {
        return configuration;
    }
}
