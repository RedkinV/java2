package ru.geekbrains.java2.lesson6.server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainServer {


    public static void main(String[] args) {
        final String IP="localhost";
        final int PORT=1889;
        ServerSocket server=null;
        Socket socket = null;

        try {
            server=new ServerSocket(PORT);
            System.out.println("Server started.");
            socket=server.accept();
            System.out.println("Client connected.");

            DataInputStream in=new DataInputStream(socket.getInputStream());
            DataOutputStream out=new DataOutputStream(socket.getOutputStream());

            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true){
                        System.out.println("input msg:");
                        Scanner scIn=new Scanner(System.in);
                        String msgOut=scIn.nextLine();
                        try {
                            out.writeUTF(msgOut);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }

                }
            });
            t.setDaemon(true);
            t.start();


            while(true){
                String msgIn=in.readUTF();
                if (msgIn.equals("/end")){
                    System.out.println("Close command accepted. Server-STOP.");
                    out.writeUTF("/serverClosed");
                    break;
                }
                System.out.println("Client: "+msgIn);
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

}
