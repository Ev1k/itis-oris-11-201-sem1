package com.example.sqrltrbl.new_pack.server;

import com.example.sqrltrbl.new_pack.client.ClientSocket;
import com.example.sqrltrbl.new_pack.models.Player;
import javafx.scene.image.Image;
import lombok.SneakyThrows;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MainServerSocket {
    private ConcurrentMap<UUID, Player> playersStateMap;
    private static ConcurrentMap<UUID, ClientSocket> playersConnectionsStateMap;
    private ServerSocket serverSocket;

    public static ConcurrentMap<UUID, ClientSocket> getPlayersConnectionsStateMap() {
        return playersConnectionsStateMap;
    }

    public void setPlayersConnectionsStateMap(ConcurrentMap<UUID, ClientSocket> playersConnectionsStateMap) {
        this.playersConnectionsStateMap = playersConnectionsStateMap;
    }

    public static void main(String[] args) {
        MainServerSocket mainServerSocket = new MainServerSocket();
        mainServerSocket.start(12345);
    }

    @SneakyThrows
    public void start(int port) {
        System.out.println("Сервер запущен, ожидание игроков...");
        playersStateMap = new ConcurrentHashMap<>();
        playersConnectionsStateMap = new ConcurrentHashMap<>();
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                ClientSocket clientSocket = new ClientSocket(serverSocket.accept());
                Player player = addPlayerToMap(0, 0, null);
                System.out.println("сокет есть");
                playersConnectionsStateMap.put(player.getUuid(), clientSocket); // достать в иниц этот сокет по айди


                BufferedReader input = new BufferedReader(new InputStreamReader(clientSocket.getClientInputStream(), StandardCharsets.UTF_8));
                BufferedWriter output = new BufferedWriter(new OutputStreamWriter(clientSocket.getClientOutputStream(), StandardCharsets.UTF_8));
                output.write(player.getUuid().toString());
                System.out.println(player.getUuid().toString());
                clientSocket.setPlayer(player);
                output.write("Игрок подключен:" + clientSocket.getPlayer().getUuid());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @SneakyThrows
    public void stop() {
        serverSocket.close();
    }

    public ConcurrentMap<UUID, Player> getPlayersStateMap() {
        return playersStateMap;
    }

    public void setPlayersStateMap(ConcurrentMap<UUID, Player> playersStateMap) {
        this.playersStateMap = playersStateMap;
    }

    public Player addPlayerToMap(int x, int y, Image image) {
        UUID uuid = UUID.randomUUID();
        Player player = new Player(x, y, image, uuid);
        playersStateMap.put(uuid, player);
        // send response to client  (approve registration)
        return player;
    }

//    @SneakyThrows
//    public void initConnection() {
//        ClientSocket socket = new ClientSocket(serverSocket.accept());
//        socket.setPlayer(addPlayerToMap(0, 0, null));
//    }
}
