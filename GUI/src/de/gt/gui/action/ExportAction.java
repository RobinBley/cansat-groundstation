/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action;

import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.export.DataExporter;
import de.gt.api.export.Exporter;
import de.gt.api.export.ImageExporter;
import de.gt.api.export.PositionExporter;
import de.gt.gui.dialog.control.ExportDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.util.List;
import java.util.Map;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.openide.awt.ActionID;
import org.openide.awt.ActionReference;
import org.openide.awt.ActionRegistration;
import org.openide.util.Lookup;
import org.openide.util.NbBundle.Messages;
import org.openide.windows.WindowManager;

@ActionID(
        category = "File",
        id = "de.gt.gui.action.export.ExportAction"
)
@ActionRegistration(
        displayName = "#CTL_ExportAction"
)
@ActionReference(path = "Menu/File", position = 3333)
@Messages("CTL_ExportAction=Export")
public final class ExportAction implements ActionListener {

    private ExportDialog exportDialog;
    private DataPipeline pipeline;

    public ExportAction() {
        exportDialog = new ExportDialog(WindowManager.getDefault().getMainWindow(), true);

        pipeline = Lookup.getDefault().lookup(DataPipeline.class);

        exportDialog.addWindowListener(new WindowAdapter() {

            @Override
            public void windowClosed(WindowEvent e) {
                if (exportDialog != null) {
                    doExport(exportDialog.getExporter());
                }
            }
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        exportDialog.setVisible(true);
    }

    public void doExport(Exporter exporter) {
        //Dateispeicher Dialog anlegen
        JFileChooser fileSaver = new JFileChooser();

        //Filter entsprechend dem Export Format setzen
        fileSaver.setFileFilter(new FileNameExtensionFilter(exporter.getExporterName(), exporter.getFileExt()));

        //Speicherdialog anzeigen
        if (fileSaver.showSaveDialog(fileSaver) == JFileChooser.APPROVE_OPTION) {
            File exportTargetFile = fileSaver.getSelectedFile();

            //Der Nutzer möchte den Export durchführen
            if (exporter instanceof ImageExporter) {
                ImageExporter imageExporter = (ImageExporter) exporter;

            } else if (exporter instanceof PositionExporter) {
                PositionExporter positionExporter = (PositionExporter) exporter;

                if (!positionExporter.exportData(this.pipeline.exportData(), this.pipeline.getConfig().getGpsKey(), exportTargetFile)) {
                    JOptionPane.showMessageDialog(null, "Error while exporting");
                }
            } else if (exporter instanceof DataExporter) {
                DataExporter dataExporter = (DataExporter) exporter;

                if (!dataExporter.exportData(this.pipeline.exportData(), exportTargetFile)) {
                    JOptionPane.showMessageDialog(null, "Error while exporting");
                }
            } else {
                //TODO: Fehler beim Exportieren, weil der Exporter keines der drei dataexport interfaces unterstützt
            }
        }
    }
}
