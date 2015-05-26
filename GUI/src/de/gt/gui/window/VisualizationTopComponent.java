package de.gt.gui.window;

import de.gt.api.config.Config;
import info.monitorenter.gui.chart.IAxis;
import info.monitorenter.gui.chart.ITrace2D;
import info.monitorenter.gui.chart.traces.Trace2DSimple;
import java.util.Collection;
import java.util.Map;
import javax.swing.DefaultComboBoxModel;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.TopComponent;

/**
 *
 */
@TopComponent.Description(
        preferredID = "VisualizationTopComponent",
        persistenceType = TopComponent.PERSISTENCE_NEVER
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@Messages({
    "CTL_VisualizationAction=Visualization",
    "CTL_VisualizationTopComponent=Visualization Window",
    "HINT_VisualizationTopComponent=This is a Visualization window"
})
public class VisualizationTopComponent extends DataReceiverComponent {

    private Config config;

    private DefaultComboBoxModel<String> xAxisVal;
    private DefaultComboBoxModel<String> yAxisVal;

    private ITrace2D trace;

    /**
     * Creates new form VizualizationTopComponent
     */
    public VisualizationTopComponent(Config currentConfig) {
        super();
        //Trace intialisieren
        trace = new Trace2DSimple();

        //Komboboxmodel für X Achsen-Wertauswahl
        xAxisVal = new DefaultComboBoxModel<>();

        //Komboboxmodel für Y Achsen-Wertauswahl
        yAxisVal = new DefaultComboBoxModel<>();

        //Config Keys auslesen
        Collection<String> availableKeys = currentConfig.getKeys();

        //Komboboxen mit Config initialisieren
        initComboBoxModel(availableKeys, xAxisVal);
        initComboBoxModel(availableKeys, yAxisVal);

        //GUI Komponenten intialisieren
        initComponents();

        //Tracer zum Chart hinzufügen
        chart.addTrace(trace);

        this.config = currentConfig;

    }

    @Override
    public int getPersistenceType() {
        //Komponenten schließen, bei neustart der Applikation
        return TopComponent.PERSISTENCE_NEVER;
    }

    private void setXAxisDescription(String title) {
        chart.getAxisX().setAxisTitle(new IAxis.AxisTitle(title));
    }

    private void setYAxisDescription(String title) {
        chart.getAxisY().setAxisTitle(new IAxis.AxisTitle(title));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cmbXAxis = new javax.swing.JComboBox();
        cmbYAxis = new javax.swing.JComboBox();
        lblXAxis = new javax.swing.JLabel();
        lblYAxis = new javax.swing.JLabel();
        btnUpdateView = new javax.swing.JButton();
        chart = new info.monitorenter.gui.chart.ZoomableChart();

        setBackground(java.awt.Color.lightGray);

        cmbXAxis.setModel(xAxisVal);

        cmbYAxis.setModel(yAxisVal);

        org.openide.awt.Mnemonics.setLocalizedText(lblXAxis, org.openide.util.NbBundle.getMessage(VisualizationTopComponent.class, "VisualizationTopComponent.lblXAxis.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(lblYAxis, org.openide.util.NbBundle.getMessage(VisualizationTopComponent.class, "VisualizationTopComponent.lblYAxis.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(btnUpdateView, org.openide.util.NbBundle.getMessage(VisualizationTopComponent.class, "VisualizationTopComponent.btnUpdateView.text")); // NOI18N
        btnUpdateView.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateViewActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout chartLayout = new javax.swing.GroupLayout(chart);
        chart.setLayout(chartLayout);
        chartLayout.setHorizontalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        chartLayout.setVerticalGroup(
            chartLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 282, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(chart, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblYAxis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbXAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lblXAxis)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(cmbYAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnUpdateView)
                        .addGap(0, 281, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmbXAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmbYAxis, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblXAxis)
                    .addComponent(lblYAxis)
                    .addComponent(btnUpdateView))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(chart, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnUpdateViewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateViewActionPerformed

    }//GEN-LAST:event_btnUpdateViewActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnUpdateView;
    private info.monitorenter.gui.chart.ZoomableChart chart;
    private javax.swing.JComboBox cmbXAxis;
    private javax.swing.JComboBox cmbYAxis;
    private javax.swing.JLabel lblXAxis;
    private javax.swing.JLabel lblYAxis;
    // End of variables declaration//GEN-END:variables

    @Override
    public void receive(Map<String, Double> datum) {
        trace.addPoint(datum.get(xAxisVal.getSelectedItem()), datum.get(yAxisVal.getSelectedItem()));
    }

    private void initComboBoxModel(Collection<String> newVals, DefaultComboBoxModel<String> model) {
        //Alle alten Elemente entfernen
        model.removeAllElements();

        //Alle neuen Elemente hinzufügen
        newVals.stream().forEach(i -> model.addElement(i));
    }

    @Override
    public void configChanged(Config newConfig) {
        Collection<String> keys = newConfig.getKeys();

        //Models neu intialisieren
        initComboBoxModel(keys, xAxisVal);
        initComboBoxModel(keys, yAxisVal);
    }
}
