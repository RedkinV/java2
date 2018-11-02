package lesson4_mychat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Vector;

public class MainServer {
    private Vector<ClientHandler> clients;

    public MainServer() {
        final String IP = "localhost";
        final int PORT = 1889;
        ServerSocket server = null;
        Socket socket = null;
        clients = new Vector<>();

        try {
            AuthService.connect();
//            AuthService.addUser("log1","pass1","nick1");
//            AuthService.addUser("log2","pass2","nick2");
//            AuthService.addUser("log3","pass3","nick3");
//            AuthService.addUser("log4","pass4","nick4");
            //System.out.println(AuthService.getNickByLoginAndPass("log1","pass1"));
            server = new ServerSocket(PORT);
            System.out.println("Server started.");
            while (true) {
                socket = server.accept();
                System.out.println("Client connected.");
                new ClientHandler(this, socket);

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

    public boolean isInBlackList(String nick, ArrayList<String> blacklist) {
        for (String s : blacklist) {
            if (nick.equals(s)) return true;
        }
        return false;
    }

    public void broadCastMsg(String msg, ClientHandler c) {
        if (c != null) {
            ArrayList<String> blacklist = AuthService.getBlacklistByNick(c.getNick());
            for (ClientHandler o : clients) {

                if (!isInBlackList(o.getNick(), blacklist)) {
                    o.sendMsg(msg);
                }
            }
        } else {
            for (ClientHandler o : clients) {
                o.sendMsg(msg);
            }
        }
    }

    public void sendPrivateMsg(ClientHandler clHnd, String nickTo, String msg) {

        ArrayList<String> blacklist = AuthService.getBlacklistByNick(nickTo);
        for (ClientHandler c : clients) {
            if (c.nick.equals(nickTo)) {
                if (!isInBlackList(clHnd.getNick(), blacklist)) {
                    c.sendMsg(msg);
                    clHnd.sendMsg(msg);
                    return;
                } else {
                    clHnd.sendMsg("Вы в черном списке");
                    return;
                }
            }
        }
        clHnd.sendMsg("такого пользователя в сети нет.");
    }

    public void deleteClient(ClientHandler c) {
        clients.remove(c);
    }

    public void subscribe(ClientHandler c) {
        clients.add(c);
        broadcastClientList();
    }

    public void unsubscribe(ClientHandler c) {
        clients.remove(c);
        broadcastClientList();
    }

    public boolean haskNick(String chNick) {
        for (ClientHandler c : clients) {
            if (c.nick.equals(chNick)) return true;
        }
        return false;
    }

    public void broadcastClientList() {
        StringBuilder sb = new StringBuilder();
        sb.append("/clientList ");
        for (ClientHandler c : clients) {
            sb.append(c.getNick() + " ");
        }
        String str = sb.toString();
        broadCastMsg(str,null);
    }


}

