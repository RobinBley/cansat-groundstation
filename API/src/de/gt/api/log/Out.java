package de.gt.api.log;

import org.openide.windows.IOProvider;
import org.openide.windows.OutputWriter;

/**
 * Loggt Daten zur GUI-Output-Konsole
 *
 * @author mhuisi
 */
public class Out {

    private static final OutputWriter w = IOProvider.getDefault().getIO("Groundstation Output", true).getOut();

    /**
     * Loggt die Nachrichten im Netbeans Platform Output Fenster "Groundstation
     * Output"
     *
     * @param msg
     */
    public static void log(String msg) {
        w.println(msg);
    }

}
