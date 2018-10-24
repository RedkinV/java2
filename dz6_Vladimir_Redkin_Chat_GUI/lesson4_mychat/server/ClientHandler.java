package lesson4_mychat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;


public class ClientHandler {
    private MainServer server;
    Socket socket;
    DataOutputStream out;
    DataInputStream in;

    public ClientHandler(MainServer server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;

            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.equals("/end")) {
                                System.out.println("Close command accepted. Server-STOP.");
                                out.writeUTF("/serverClosed");
                                deleteFromVector(); // попробовал отсюда вызвать метод класса MainServer  .deleteClient().
                                                    // Компилятор ругается. Пришлось создавать метод внутри этого класса deleteFromVector(),
                                                    //который уже вызывает server.deleteClient(). Почему так происходит?
                                break;
                            }
                            System.out.println("Client: " + msg);
                            server.broadCastMsg(msg);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            in.close();
                            out.close();
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMsg(String msg){
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void deleteFromVector(){
        server.deleteClient(this);

    }
}
