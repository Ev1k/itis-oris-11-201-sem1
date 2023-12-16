package com.example.sqrltrbl.new_pack.process;

import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class GameProcess {
    private Pane root;
    private static final int WINDOW_WIDTH = 800;
    private static final int WINDOW_HEIGHT = 600;
    private static final int PLAYER_SIZE = 40;
    private static final int GROUND_HEIGHT = 100;

    public void start(Stage primaryStage) {
        root = new Pane();
        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);

        primaryStage.setTitle("Трагедия");
        primaryStage.setScene(scene);
        primaryStage.show();

        try {
            Socket socket = new Socket("localhost", 12345);
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

//            myOwnPlayerId = in.readLine();
            Rectangle myPlayer = new Rectangle(PLAYER_SIZE, PLAYER_SIZE);
            myPlayer.setLayoutX(50);
            myPlayer.setLayoutY(WINDOW_HEIGHT - GROUND_HEIGHT - PLAYER_SIZE);
//            playerMap.put(myOwnPlayerId, myPlayer);
            root.getChildren().add(myPlayer);

            scene.setOnKeyPressed(e -> {
                switch (e.getCode()) {
                    case LEFT:
                        myPlayer.setLayoutX(myPlayer.getLayoutX() - 5);
                        break;
                    case RIGHT:
                        myPlayer.setLayoutX(myPlayer.getLayoutX() + 5);
                        break;
                    case SPACE:
                        jumpPlayer(myPlayer);
                        break;
                }
                // Обновляем карту после перемещения
//                playerMap.put(myOwnPlayerId, myPlayer);
//                out.println("update," + myOwnPlayerId + "," + myPlayer.getLayoutX() + "," + myPlayer.getLayoutY());
            });

//            new Thread(() -> {
//                String response;
//                try {
//                    while ((response = in.readLine()) != null) {
//                        final String msg = response;
//                        Platform.runLater(() -> {
//                            updateGameState(msg);
//                        });
//                    }
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }).start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//    private void updatePlayerPosition(String playerId, double x, double y) {
//        Rectangle player = playerMap.get(playerId);
//        if (player != null) {
//            player.setLayoutX(x);
//            player.setLayoutY(y);
//        } else {
//            // Если это новый игрок, добавляем его в игру
//            Rectangle newPlayer = new Rectangle(PLAYER_SIZE, PLAYER_SIZE);
//            newPlayer.setLayoutX(x);
//            newPlayer.setLayoutY(y);
//            playerMap.put(playerId, newPlayer);
//            Platform.runLater(() -> root.getChildren().add(newPlayer));
//        }
//    }

//    private void updateGameState(String gameState) {
//        String[] players = gameState.split("\\|");
//        for (String playerState : players) {
//            String[] parts = playerState.split(":");
//            if (parts.length == 2) {
//                String playerId = parts[0];
//                String[] coords = parts[1].split(",");
//                if (coords.length == 2) {
//                    double x = Double.parseDouble(coords[0]);
//                    double y = Double.parseDouble(coords[1]);
//                    updatePlayerPosition(playerId, x, y);
//                }
//            }
//        }
//    }

    private void jumpPlayer(Rectangle player) {
        // TODO: Реализация прыжка
        player.setLayoutY(player.getLayoutY() - 100); // Прыжок вверх
        Platform.runLater(() -> {
            try {
                Thread.sleep(200); // Пауза для заметности прыжка
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            player.setLayoutY(player.getLayoutY() + 100); // Возвращение вниз
        });
    }

//    public void main(String[] args) {
//        Stage stage = new Stage();
//        this.start(stage);
//    }
}
