package de.gt.api.log;

import org.openide.windows.IOProvider;
import org.openide.windows.OutputWriter;

/**
 * Logs data to gui output console
 * @author mhuisi
 */
public class Out {
    
    private static final OutputWriter w = IOProvider.getDefault().getIO("Groundstation Output", true).getOut();
    
    /**
     * Logs the message to a netbeans platform output window called "Groundstation Output"
     * @param msg 
     */
    public static void log(String msg) {
        w.println(msg);
    }
    
}
