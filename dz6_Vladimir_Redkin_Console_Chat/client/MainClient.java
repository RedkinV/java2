package ru.geekbrains.java2.lesson6.client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class MainClient {


    public static void main(String[] args) {

        final String IP = "localhost";
        final int PORT = 1889;
        Socket socket = null;
        DataInputStream in = null;
        DataOutputStream out = null;
        Scanner scIn = new Scanner(System.in);
        try {
            socket = new Socket(IP, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Connected to Server.");


        final DataInputStream finalIn = in;
        final DataOutputStream finalOut = out;
        final Socket finalSocket = socket;


        try {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {

                        System.out.println("Input msg: ");
                        String msgOut = scIn.nextLine();

                        try {
                            finalOut.writeUTF(msgOut);
                        } catch (IOException e) {
                            //e.printStackTrace();
                            System.out.println("Socket closed!!");
                        }
                    }
                }
            });
            t.setDaemon(true);
            t.start();

            while (true) {
                String msg = finalIn.readUTF();
                System.out.println("Server msg: " + msg+"");
                if (msg.equals("/serverClosed")) {
                    System.out.println("Server stopped. Goodbye.");

                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {

            try {
                finalIn.close();
                finalOut.close();
                finalSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
