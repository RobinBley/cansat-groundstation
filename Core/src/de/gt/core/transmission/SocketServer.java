package de.gt.core.transmission;

import de.gt.api.input.data.DataUnit;
import de.gt.api.relay.Receiver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;

/**
 *
 * @author Robin Ein Sever, welcher als Datenempfaenger dient und diese an alle
 * zu diesem Server verbundenen Clients convertiert weiterleitet.
 */
public class SocketServer implements Receiver, Runnable {

    private ArrayList<PrintWriter> writers;

    /**
     * Eine Nachricht wird an alle verbundenen Clients gesendet.
     *
     * @param message Die Nachricht, welche versendet wird.
     */
    public void broadcast(String message) {
        try {
            //Es wird druch die Liste aller verbundenen Clients iteriert
            for (PrintWriter client : writers) {
                //Die Nachricht wird dem jeweiligen Client uebergeben.
                if (client.checkError()) {
                    client.close();
                    writers.remove(client);
                    Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, "client removed");
                } else {
                    //Es wird versucht den Clients die Nachricht zu uebermitteln.
                    try {
                        client.println(message);
                        client.flush();

                    } catch (Exception exc) {
                        writers.remove(client);
                        client.close();
                        Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, "client removed");
                    }
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Der Server wird gestartet.
     */
    public void start() {
        writers = new ArrayList<PrintWriter>();
        try {
            //Ein ServerSocket wird erstellt und ihm wird ein Port zugewiesen.
            ServerSocket serverSock = new ServerSocket(5000);

            while (true) {
                //Ein Socket zu einem Client wird erstellt.
                Socket clientSocket = serverSock.accept();
                //Dem Socket wird ein timeout hinzugefuegt.
                writers.add(new PrintWriter(clientSocket.getOutputStream()));
                //Ein Thread wird fuer den Socket erstellt.
                Thread t = new Thread(new ClientHandler(clientSocket));
                //Der Thread wird gestartet.
                t.start();
            }

        } catch (Exception ex) {
            Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void receive(Map<String, DataUnit> data) {
        //Die uebergebenen Daten werden zu einem JSONObject geparsed.
        JSONObject jsonData = new JSONObject();
        for (String key : data.keySet()) {
            jsonData.put(key, data.get(key).getObjectValue());
        }
        //Das JSONObject wird als String den Clients gesendet.
        broadcast(jsonData.toString());
    }

    @Override
    public void run() {
        start();
    }

    /**
     * @author Robin Wartet auf Nachrichten von Clients und loggt diese.
     */
    public class ClientHandler implements Runnable {

        BufferedReader reader;

        Socket sock;

        public ClientHandler(Socket clientSocket) {
            //Eine Liste von PrintWriters wird erzeugt.
            try {
                //Ein neuer Socket wird erzeugt.
                sock = clientSocket;
                //Der Socket wird zum PrintWriter geparsed und der Liste hinzugefuegt.
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);

            } catch (Exception ex) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        @Override
        public void run() {
            String message;
            try {
                //Wenn Ein Client Daten schickt, werden sie geloggt.
                while ((message = reader.readLine()) != null) {
                    Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, "client: " + message);
                    System.out.println(message);
                }
            } catch (Exception e) {
                Logger.getLogger(SocketServer.class.getName()).log(Level.SEVERE, null, e);

            }
        }
    }
}
