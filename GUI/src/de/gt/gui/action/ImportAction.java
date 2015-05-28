/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action;

import de.gt.api.datapipeline.DataPipeline;
import de.gt.api.importer.Importer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;
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
        id = "de.gt.gui.action.importer.ImportAction"
)
@ActionRegistration(
        displayName = "#CTL_ImportAction"
)
@ActionReference(path = "Menu/File", position = 3333)
@Messages("CTL_ImportAction=Import")
public final class ImportAction implements ActionListener {

    JFileChooser chooser;
    FileNameExtensionFilter filter;
    DataPipeline pipeline;

    public ImportAction() {
        //Filechooser für Import initialisieren
        chooser = new JFileChooser();

        //Pipeline holen
        pipeline = Lookup.getDefault().lookup(DataPipeline.class);
    }

    private String[] getValidFileNameExt(Collection<? extends Importer> availableImporters) {
        Object[] validExtensions = availableImporters.stream()
                .map(i -> i.importFileExt())
                .collect(Collectors.toSet())
                .toArray();

        return Arrays.copyOf(validExtensions, validExtensions.length, String[].class);
    }

    private String getFileExt(File choosenFile) {
        String[] extParts = choosenFile.getName().split("[.]");

        return extParts[extParts.length - 1];
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        boolean showImportDialog = false;

        if (this.pipeline.isStreamRunning()) {
            //Wenn der Stream läuft
            int val = JOptionPane.showConfirmDialog(WindowManager.getDefault().getMainWindow(), "Import will stop the datastream which is currently running, do you want to proceed?");

            if (val == JOptionPane.YES_OPTION) {
                //User möchte stream beenden, dialog zeigen
                showImportDialog = true;
            }
        } else {
            //Kein Stream, darum import dialog zeigen
            showImportDialog = true;
        }

        if (showImportDialog) {
            //Aktuelle Importer abrufen
            Collection<? extends Importer> availableImporters = Lookup.getDefault().lookupAll(Importer.class);

            //Filter erneuern mit aktuellen Importern
            filter = new FileNameExtensionFilter("Data files", getValidFileNameExt(availableImporters));
            chooser.setFileFilter(filter);

            //Status der Dateiauswahl
            int fileChoosenStatus = chooser.showOpenDialog(WindowManager.getDefault().getMainWindow());

            if (fileChoosenStatus == JFileChooser.APPROVE_OPTION) {
                //Wenn eine Exception aufgetreten
                File choosenFile = chooser.getSelectedFile();

                //Datei-Endung der ausgewählten Datei feststellen
                String fileExt = getFileExt(choosenFile);

                //Richtigen Importer finden
                Importer usableImporter = availableImporters.stream()
                        .filter(i -> i.importFileExt().equals(fileExt))
                        .findFirst()
                        .get();

                //Daten in die Pipeline importieren
                this.pipeline.importData(usableImporter.importData(choosenFile));
            }
        }
    }
}
