package com.example.sqrltrbl;

import com.example.sqrltrbl.PlatformerGame;
import com.example.sqrltrbl.new_pack.client.ClientSocket;
import com.example.sqrltrbl.new_pack.models.Player;
import com.example.sqrltrbl.new_pack.process.GameProcess;
import com.example.sqrltrbl.new_pack.server.MainServerSocket;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.UUID;
import java.util.concurrent.ConcurrentMap;

public class Initializer extends Application {
    static Socket socket;
    ClientSocket clientSocket;

    private static ConcurrentMap<UUID, ClientSocket> playersConnectionsStateMap;

    public Initializer() throws Exception{
    }

    @Override
    public void start(Stage primaryStage) throws IOException {//TODO^ убрать из назв эксепшн
        GameProcess gm = new GameProcess();
        playersConnectionsStateMap = MainServerSocket.getPlayersConnectionsStateMap();
        socket = new Socket("localhost", 12345);
        clientSocket = new ClientSocket(socket);

        BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getClientInputStream(), StandardCharsets.UTF_8));
        while (true) {
            if (input.ready()) {
                UUID uuid = UUID.fromString(input.readLine());
                System.out.println(uuid);
                //TODO: извлечь игрока по айди

//            if (uuid != null) {
//                break;
//            }
                break;
            }
        }
//        gm.start(new Stage());
        Button startButton = new Button("Начать игру");
        startButton.setOnAction(event -> handleStartButton());

        StackPane root = new StackPane();
        root.getChildren().add(startButton);

        Scene scene = new Scene(root, 600, 500);
        primaryStage.setTitle("Трагедия б");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void handleStartButton() {
        try {
//            Socket socket = new Socket("localhost", 12345);
//            ClientSocket clientSocket = new ClientSocket(socket);
            Player player = clientSocket.getPlayer();
            BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getClientOutputStream(), StandardCharsets.UTF_8));
            output.write("вы подключились:" + clientSocket.getPlayer().getUuid());
            output.flush();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
