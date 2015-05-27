/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.window;

import de.gt.api.config.Config;
import de.gt.api.relay.Receiver;
import java.util.Map;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.windows.TopComponent;
import org.openide.util.NbBundle.Messages;

/**
 * Top component which displays something.
 */
@TopComponent.Description(
        preferredID = "TextDisplayTopComponent",
        //iconBase="SET/PATH/TO/ICON/HERE", 
        persistenceType = TopComponent.PERSISTENCE_ALWAYS
)
@TopComponent.Registration(mode = "editor", openAtStartup = false)
@ActionID(category = "Window", id = "de.gt.gui.window.TextDisplayTopComponent")
@ActionReference(path = "Menu/Window" /*, position = 333 */)
@TopComponent.OpenActionRegistration(
        displayName = "#CTL_TextDisplayAction",
        preferredID = "TextDisplayTopComponent"
)
@Messages({
    "CTL_TextDisplayAction=TextDisplay",
    "CTL_TextDisplayTopComponent=TextDisplay Window",
    "HINT_TextDisplayTopComponent=This is a TextDisplay window"
})
public final class TextDisplayTopComponent extends DataReceiverComponent {

    public TextDisplayTopComponent() {
        super();
        initComponents();
        setName(Bundle.CTL_TextDisplayTopComponent());
        setToolTipText(Bundle.HINT_TextDisplayTopComponent());
    }

    @Override
    public void receive(Map<String, Double> datum) {
        StringBuilder sb = new StringBuilder();
        datum.entrySet().stream()
                .map(e -> String.format("%s: %s\n", e.getKey(), e.getValue()))
                .forEach(sb::append);
        sb.append("-----\n");
        Document d = jTextPane1.getDocument();
        try {
            d.insertString(d.getLength(), sb.toString(), null);
        } catch (BadLocationException ex) {
            System.err.println(ex);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        jTextPane1.setEditable(false);
        jScrollPane1.setViewportView(jTextPane1);

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
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void configChanged(Config newConfig) {
        //TODO: Default method in Receiver
    }
}
