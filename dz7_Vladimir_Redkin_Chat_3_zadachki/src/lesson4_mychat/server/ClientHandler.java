package lesson4_mychat.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ClientHandler {
    private MainServer server;
    Socket socket;
    DataOutputStream out;
    DataInputStream in;
    String nick;

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
                            if (msg.startsWith("/auth")) {
                                String[] tokens = msg.split(" ");
                                String newNick = AuthService.getNickByLoginAndPass(tokens[1], tokens[2]);
                                if (!server.haskNick(newNick)) {
                                    if (newNick != null) {
                                        sendMsg("/authok");
                                        System.out.println("/authok");
                                        nick = newNick;
                                        server.subscribe(ClientHandler.this);
                                        break;
                                    } else {
                                        sendMsg("Не верный логин/пароль");
                                        System.out.println("Не верный логин/пароль");
                                        break; // тут нужен  для того чтобы закрыть сокет, если не верно ввели пароль,
                                              // а для этого надо обработать команду /end, т.е. надо выйти из цикла
                                    }
                                } else {
                                    sendMsg("Такой пользователь уже в сети");
                                    break; // аналогичная ситуация, что и с неверно введенным паролем
                                }
                            }
                        }
                        while (true) {
                            String msg = in.readUTF();
                            if (msg.equals("/end")) {
                                System.out.println("Close command accepted. Server-STOP.");
                                out.writeUTF("/serverClosed");
                                server.unsubscribe(ClientHandler.this); //почему компилятор перед this просит добавить название класса?
                                break;
                            }
                            if(msg.startsWith("/w")){
                                String pattern=".*\\s(\\w+)\\s(.*)";
                                Pattern p=  Pattern.compile(pattern);
                                Matcher m=p.matcher(msg);
                                if(m.find()) server.sendPrivateMsg(ClientHandler.this,m.group(1),nick+": "+m.group(2));
                            } else {
                                System.out.println(nick + ": " + msg);
                                server.broadCastMsg(nick + ": " + msg);
                            }
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


    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteFromVector() {
        server.deleteClient(this);
    }

}
