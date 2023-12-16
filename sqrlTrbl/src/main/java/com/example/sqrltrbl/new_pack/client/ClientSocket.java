package com.example.sqrltrbl.new_pack.client;

import com.example.sqrltrbl.new_pack.models.Player;
import lombok.SneakyThrows;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.OutputStream;
import java.net.Socket;

public class ClientSocket {

    private Socket socket;
    private final InputStream clientInputStream;
    private final OutputStream clientOutputStream;
    private Player player;

    // here is accepted socket from server (serverSocket.accept())
    @SneakyThrows
    public ClientSocket(Socket socket) {
        this.socket = socket;
        clientInputStream = socket.getInputStream();
        clientOutputStream = socket.getOutputStream();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public InputStream getClientInputStream() {
        return clientInputStream;
    }

    public OutputStream getClientOutputStream() {
        return clientOutputStream;
    }

    //functional methods Player Section

    @SneakyThrows
    public void updatePlayerPosition(int x, int y) {
        player.setX(x);
        player.setY(y);
        clientOutputStream.write(player.serialize());
        ((ObjectInputStream) clientInputStream).readObject();
    }
}
