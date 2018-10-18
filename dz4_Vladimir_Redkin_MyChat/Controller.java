package lesson4_mychat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class Controller {

    @FXML
    Button sendButton;

    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    public void sendMessage(){
        textArea.appendText(">" + textField.getText() + "\n");
        textField.clear();
        textField.requestFocus();
    }


//    @FXML
//    void initialize() {
//        sendButton.setOnAction(event -> {
//            textArea.appendText(">" + textField.getText() + "\n");
//            textField.clear();
//            textField.requestFocus();
//        });
//        textField.setOnAction(event -> {
//            textArea.appendText(">" + textField.getText() + "\n");
//            textField.clear();
//            textField.requestFocus();
//        });
//    }
}