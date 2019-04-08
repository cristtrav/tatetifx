package com.exabit.tateti;

import java.io.DataInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.layout.GridPane;

public class SceneController implements Initializable {

    @FXML
    private ToggleButton tgb00;
    @FXML
    private ToggleButton tgb01;
    @FXML
    private ToggleButton tgb02;
    @FXML
    private ToggleButton tgb10;
    @FXML
    private ToggleButton tgb11;
    @FXML
    private ToggleButton tgb12;
    @FXML
    private ToggleButton tgb20;
    @FXML
    private ToggleButton tgb21;
    @FXML
    private ToggleButton tgb22;
    @FXML
    private TextField txfJugadorX;
    @FXML
    private TextField txfJugadorO;
    @FXML
    private Button btnReiniciar;
    @FXML
    private Label lblTurno;
    @FXML
    private GridPane gridJuego;
    @FXML
    private Button btnIniciar;

    boolean turnoX = true;//true=Turno de X; false=Turno de 0
    
    private ToggleButton[][] matrizBtn = new ToggleButton[3][3];
//    private final Service<Void> srvRed=new Service<Void>(){
//        @Override
//        protected Task<Void> createTask() {
//            return new Task<Void>(){
//                @Override
//                protected Void call() throws Exception {
//                    ServerSocket sc=new ServerSocket(9999);
//                    sc.setSoTimeout(10000);
//                    while(true){
//                        try{
//                            System.out.println("Esperando cliente en puerto "+sc.getLocalPort());
//                            Socket socket=sc.accept();
//                            System.out.println("Se conecto "+socket.getRemoteSocketAddress());
//                            DataInputStream data=new DataInputStream(socket.getInputStream());
//                            System.out.println("Mensaje: "+data.readUTF());
//                            
//                            DataOutputStream dataout=new DataOutputStream();
//                        }catch(SocketTimeoutException ex){
//                            
//                        }
//                    }
//                    return null;
//                }
//            };
//        }
//    };

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        matrizBtn[0][0] = this.tgb00;
        matrizBtn[0][1] = this.tgb01;
        matrizBtn[0][2] = this.tgb02;
        matrizBtn[1][0] = this.tgb10;
        matrizBtn[1][1] = this.tgb11;
        matrizBtn[1][2] = this.tgb12;
        matrizBtn[2][0] = this.tgb20;
        matrizBtn[2][1] = this.tgb21;
        matrizBtn[2][2] = this.tgb22;

        this.deshabilitarBotones(true);
    }

    @FXML
    private void onActionTgb00(ActionEvent event) {
        this.jugar(this.tgb00);
    }

    @FXML
    private void onActionTgb01(ActionEvent event) {
        this.jugar(this.tgb01);
    }

    @FXML
    private void onActionTgb02(ActionEvent event) {
        this.jugar(this.tgb02);
    }

    @FXML
    private void onActionTgb10(ActionEvent event) {
        this.jugar(this.tgb10);
    }

    @FXML
    private void onActionTgb11(ActionEvent event) {
        this.jugar(this.tgb11);
    }

    @FXML
    private void onActionTgb12(ActionEvent event) {
        this.jugar(this.tgb12);
    }

    @FXML
    private void onActionTgb20(ActionEvent event) {
        this.jugar(this.tgb20);
    }

    @FXML
    private void onActionTgb21(ActionEvent event) {
        this.jugar(this.tgb21);
    }

    @FXML
    private void onActionTgb22(ActionEvent event) {
        this.jugar(this.tgb22);
    }

    private void jugar(ToggleButton btnJuego) {
        //Marcar

        if (this.turnoX) {
            btnJuego.setText("X");
            this.lblTurno.setText("Turno de " + this.txfJugadorO.getText());
        } else {
            btnJuego.setText("O");
            this.lblTurno.setText("Turno de " + this.txfJugadorX.getText());
        }
        this.turnoX = !this.turnoX;

        if (!btnJuego.getText().isEmpty()) {
            btnJuego.setSelected(true);
        }

        //Verificar ganador
        if (!ganadorMetodo1().isEmpty()) {
            Alert al = new Alert(AlertType.INFORMATION);
            al.setContentText("Hay un ganador: " + ganadorMetodo1());
            al.show();
        }
    }

    private String ganadorMetodo1() {
        String ganador = "";
        if (!this.tgb00.getText().isEmpty() && this.tgb00.getText().equals(this.tgb01.getText()) && this.tgb00.getText().equals(this.tgb02.getText())) {
            ganador = this.tgb00.getText();
        } else if (!this.tgb10.getText().isEmpty() && this.tgb10.getText().equals(this.tgb11.getText()) && this.tgb10.getText().equals(this.tgb12.getText())) {
            ganador = this.tgb10.getText();
        } else if (!this.tgb20.getText().isEmpty() && this.tgb20.getText().equals(this.tgb21.getText()) && this.tgb20.getText().equals(this.tgb22.getText())) {
            ganador = this.tgb20.getText();
        } else if (!this.tgb00.getText().isEmpty() && this.tgb00.getText().equals(this.tgb10.getText()) && this.tgb00.getText().equals(this.tgb20.getText())) {
            ganador = this.tgb00.getText();
        } else if (!this.tgb01.getText().isEmpty() && this.tgb01.getText().equals(this.tgb11.getText()) && this.tgb01.getText().equals(this.tgb21.getText())) {
            ganador = this.tgb01.getText();
        } else if (!this.tgb02.getText().isEmpty() && this.tgb02.getText().equals(this.tgb12.getText()) && this.tgb20.getText().equals(this.tgb22.getText())) {
            ganador = this.tgb02.getText();
        } else if (!this.tgb00.getText().isEmpty() && this.tgb00.getText().equals(this.tgb11.getText()) && this.tgb00.getText().equals(this.tgb22.getText())) {
            ganador = this.tgb00.getText();
        } else if (!this.tgb02.getText().isEmpty() && this.tgb02.getText().equals(this.tgb11.getText()) && this.tgb02.getText().equals(this.tgb20.getText())) {
            ganador = this.tgb02.getText();
        }
        return ganador;
    }

    private String ganadorMetodo2() {
        String ganador = "";

        for (int x = 0; x < 3; x++) {
            if (matrizBtn[x][0].getText().equals(matrizBtn[x][1].getText()) && matrizBtn[x][0].getText().equals(matrizBtn[x][2].getText()) && !matrizBtn[x][0].getText().isEmpty()) {
                ganador = matrizBtn[x][0].getText();
                break;
            }

            if (matrizBtn[0][x].getText().equals(matrizBtn[1][x].getText()) && matrizBtn[0][x].getText().equals(matrizBtn[2][x].getText()) && !matrizBtn[0][x].getText().isEmpty()) {
                ganador = matrizBtn[0][x].getText();
                break;
            }
        }
        if (matrizBtn[0][0].getText().equals(matrizBtn[1][1].getText()) && matrizBtn[0][0].getText().equals(matrizBtn[2][2].getText()) && !matrizBtn[0][0].getText().isEmpty()) {
            ganador = matrizBtn[0][0].getText();

        } else if (matrizBtn[0][2].getText().equals(matrizBtn[1][1].getText()) && matrizBtn[0][2].getText().equals(matrizBtn[2][0].getText()) && !matrizBtn[0][2].getText().isEmpty()) {
            ganador = matrizBtn[0][2].getText();
        }
        return ganador;
    }

    @FXML
    private void onActionBtnReiniciar(ActionEvent event) {
        this.txfJugadorO.clear();
        this.txfJugadorX.clear();

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                this.matrizBtn[x][y].setText("");
                this.matrizBtn[x][y].setSelected(false);
            }
        }
    }

    @FXML
    private void onActionBtnIniciar(ActionEvent event) {
        if (!txfJugadorO.getText().isEmpty() && !txfJugadorX.getText().isEmpty()) {
            this.deshabilitarBotones(false);
        } else {
            Alert al = new Alert(AlertType.ERROR);
            al.setContentText("Ingrese los jugadores");
            al.show();
        }
    }

    private void deshabilitarBotones(boolean d) {
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                this.matrizBtn[x][y].setDisable(d);
            }
        }
    }

}
