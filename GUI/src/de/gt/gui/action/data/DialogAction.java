package de.gt.gui.action.data;

import java.awt.Frame;
import java.awt.event.ActionListener;
import org.openide.windows.WindowManager;

/**
 * Basis für alle dialog actions
 *
 * @author Kevin
 */
public abstract class DialogAction implements ActionListener {

    protected Frame mainWindow;
    protected WindowManager windowManager;

    public DialogAction() {
        //Default Window Manager speichern
        windowManager = WindowManager.getDefault();

        //Main Window speichern
        mainWindow = windowManager.getMainWindow();
    }
}
