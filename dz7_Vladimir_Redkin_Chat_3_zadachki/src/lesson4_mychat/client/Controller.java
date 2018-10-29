package lesson4_mychat.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller  {

    @FXML
    Button sendButton;

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    @FXML
    HBox upperPanel;
    @FXML
    HBox bottomPanel;
    @FXML
    TextField loginField;
    @FXML
    PasswordField passwordField;
    @FXML
            Button loginBtn;

    Socket socket;
    DataInputStream in;
    DataOutputStream out;

    final String IP = "localhost";
    final int PORT = 1889;

    private boolean isAuthorised;

    public void setAuthorised (boolean isAuthorised){
        this.isAuthorised=isAuthorised;

        if(!isAuthorised) {
            upperPanel.setVisible(true);
            upperPanel.setManaged(true);
            bottomPanel.setVisible(false);
            bottomPanel.setManaged(false);
            textArea.clear();
        } else {
            upperPanel.setVisible(false);
            upperPanel.setManaged(false);
            bottomPanel.setVisible(true);
            bottomPanel.setManaged(true);
            textArea.clear();
        }


    }


    public void sendMessage() {

        try {
            out.writeUTF(textField.getText());
            textField.clear();
            textField.requestFocus();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //textArea.appendText(">" + textField.getText() + "\n");

    }


    public void connect () {
        try {
            socket = new Socket(IP, PORT);
            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            new Thread(new Runnable() {
                @Override
                public void run() {

                    try {
                        while(true){
                            String str=in.readUTF();
                            if (str.startsWith("/authok")){
                                setAuthorised(true);
                                break;
                            } else {
                                textArea.appendText(str+"\n");
                                out.writeUTF("/end");  // для того чтобы закрылся сокет (иначе кнопка Login перестанет работать стр.145)
                                try {
                                    Thread.sleep(500); // пауза, чтоб успеть прочитать сообщение оневерно введенном пароле
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                break;

                            }

                        }

                        while (true) {
                            String msg = in.readUTF();
                            if(msg.equals("/serverClosed")) {
                                setAuthorised(false);
                                System.out.println("/serverClosed received");
                                break;
                            }
                            textArea.appendText(msg + "\n");
                            System.out.println("Server: "+msg);
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

    public void tryToAuth(ActionEvent actionEvent) {
       if(socket==null||socket.isClosed()){   // при неверном вводе пароля и попытке зайти второй раз  не сработает  условие, если не закрыть сокет
                                            // сокет закрываем в стр. 103 этого же класса
            connect();
            try {
                out.writeUTF("/auth "+loginField.getText()+" "+passwordField.getText());
                loginField.clear();
                passwordField.clear();

            } catch (IOException e) {
                e.printStackTrace();
            }

       }
    }

}