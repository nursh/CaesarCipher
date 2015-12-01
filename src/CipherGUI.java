/**
 * Created by Nuradeen on 2015-11-29.
 */

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class CipherGUI extends Application {

    private Label message;
    private Label modifiedMessage;
    private Label keyShift;
    private Label error;
    private TextField messageField;
    private TextField modifiedMessageField;
    private TextField key;
    private Button encrypt;
    private Button decrypt;

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Caesar's Cipher");

        message = new Label("Enter your message: ");
        modifiedMessage = new Label("Encrypted / Decrypted Message");
        keyShift = new Label("key (0-25)");
        error = new Label();

        messageField = new TextField();
        modifiedMessageField = new TextField();
        key = new TextField();
        key .setPrefWidth(2);
        messageField.setPrefColumnCount(40);
        modifiedMessageField.setPrefColumnCount(40);

        encrypt = new Button("Encrypt");
        decrypt = new Button("Decrypt");
        encrypt.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    error.setText("");
                    Cipher cp = new Cipher(Integer.parseInt(key.getText()), messageField.getText());
                    cp.encodeMessage();
                    modifiedMessageField.setText(cp.getEncodedMessage());
                }catch (IllegalArgumentException iae){
                    error.setText(iae.getMessage());
                }

            }
        });

        decrypt.setOnAction(new EventHandler<javafx.event.ActionEvent>() {
            @Override
            public void handle(javafx.event.ActionEvent event) {
                try {
                    error.setText("");
                Cipher cp = new Cipher(Integer.parseInt(key.getText()),messageField.getText());
                cp.decodeMessage();
                modifiedMessageField.setText(cp.getDecodedMessage());
                } catch (IllegalArgumentException iae){
                    error.setText(iae.getMessage());
                }
            }
        });

        VBox vb = new VBox();
        vb.setPadding (new Insets(0,0,20,0));
        vb.getChildren().addAll(message, messageField);

        VBox vb1 = new VBox();
        vb1.getChildren().addAll(modifiedMessage, modifiedMessageField);

        VBox vb4 = new VBox();
        vb4.getChildren().addAll(vb, vb1);

        VBox vb3 = new VBox();
        vb3.setPadding(new Insets(5));
        vb3.getChildren().addAll(keyShift, key,error);

        VBox vb2 = new VBox();
        vb2.setSpacing(10);
        vb2.setPadding(new Insets(20));
        vb2.getChildren().addAll(encrypt, decrypt);

        HBox hb = new HBox();
        hb.setPadding(new Insets(20));
        hb.setSpacing(100);
        hb.getChildren().addAll(vb4, vb3, vb2);

        StackPane sp = new StackPane();
        sp.getChildren().addAll(hb);

        primaryStage.show();
        primaryStage.setScene(new Scene(sp, 1000, 200));
    }

}
