package lesson4_mychat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;

public class MainServer {
    private Vector<ClientHandler> clients;

    public MainServer() {
        final String IP="localhost";
        final int PORT=1889;
        ServerSocket server=null;
        Socket socket = null;
        clients=new Vector<>();

        try {
            server=new ServerSocket(PORT);
            System.out.println("Server started.");
            while (true) {
                socket = server.accept();
                System.out.println("Client connected.");
                clients.add(new ClientHandler(this,socket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void broadCastMsg(String msg){
        for (ClientHandler o: clients) {
            o.sendMsg(msg);

        }
    }
    public void deleteClient(ClientHandler c){
        clients.remove(c);
    }
}

