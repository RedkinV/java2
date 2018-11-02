package lesson4_mychat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
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
            AuthService.connect();
            System.out.println(AuthService.getNickByLoginAndPass("log1","pass1")) ;
            server=new ServerSocket(PORT);
            System.out.println("Server started.");
            while (true) {
                socket = server.accept();
                System.out.println("Client connected.");
                new ClientHandler(this,socket);

            }
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
                server.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            AuthService.disconnect();
        }
    }

    public void broadCastMsg(String msg){
        for (ClientHandler o: clients) {
            o.sendMsg(msg);

        }
    }
    public void sendPrivateMsg (ClientHandler clHnd, String nickReceiver, String msg){
        for (ClientHandler c:clients) {
            if(c.nick.equals(nickReceiver)) {
                c.sendMsg(msg);
                clHnd.sendMsg(msg);
                return;
            }
        }
        clHnd.sendMsg("такого пользователя в сети нет");
    }

    public void deleteClient(ClientHandler c){
        clients.remove(c);
    }
    public void subscribe(ClientHandler c){
        clients.add(c);
    }
    public void unsubscribe(ClientHandler c){
        clients.remove(c);
    }
    public boolean haskNick (String chNick){
        for (ClientHandler c: clients) {
            if (c.nick.equals(chNick)) return true;
        }
        return false;
    }


}

