/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input.transmission;

import de.gt.data.DataUnit;
import de.gt.relay.Receiver;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author Robin
 */
public class TransmissionServer implements Receiver {

    ArrayList<PrintWriter> clients;

    public void broadcast(String message) {
        try {
            for (PrintWriter client : clients) {
                client.println(message);
                client.flush();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void start() {

        try {
            ServerSocket serverSock = new ServerSocket(5555);

            while (true) {
                Socket clientSocket = serverSock.accept();
                clientSocket.setSoTimeout(100000);
                clients.add(new PrintWriter(clientSocket.getOutputStream()));
                Thread t = new Thread(new ClientHandler(clientSocket));
                t.start();

            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void receive(Map<String, DataUnit> data) {
        JSONObject jsonData = new JSONObject();
        for (String key : data.keySet()) {
            jsonData.put(key, data.get(key).getObjectValue());
        }
        broadcast(jsonData.toString());
    }

    public class ClientHandler implements Runnable {

        BufferedReader reader;

        Socket sock;

        public ClientHandler(Socket clientSocket) {
            clients = new ArrayList<PrintWriter>();
            try {
                sock = clientSocket;
                InputStreamReader isReader = new InputStreamReader(sock.getInputStream());
                reader = new BufferedReader(isReader);

            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        @Override
        public void run() {
            String message;

            try {
                while ((message = reader.readLine()) != null) {

                }
            } catch (Exception e) {

            }
        }
    }
}
