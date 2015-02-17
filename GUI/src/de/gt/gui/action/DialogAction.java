/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.gui.action;

import java.awt.Frame;
import java.awt.event.ActionListener;
import org.openide.windows.WindowManager;

/**
 *
 * @author Kevin
 */
public abstract class DialogAction implements ActionListener{
    protected Frame mainWindow;
    protected WindowManager windowManager;
    
    public DialogAction(){
        //Default Window Manager speichern
        windowManager = WindowManager.getDefault();
        
        //Main Window speichern
        mainWindow = windowManager.getMainWindow();
    }
}
