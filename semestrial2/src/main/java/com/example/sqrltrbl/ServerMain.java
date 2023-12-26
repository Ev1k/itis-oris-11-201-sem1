package com.example.sqrltrbl;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.server.Server;
import com.example.sqrltrbl.server.ServerException;
import com.example.sqrltrbl.server.SocketChannelServer;
import com.example.sqrltrbl.util.SleepyRunnable;
import com.example.sqrltrbl.protocol.messages.DummyMessage;

import java.net.InetSocketAddress;

public class ServerMain extends Main {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress(HOST, PORT);
        Server server = new SocketChannelServer(address);

        new Thread(() -> {
            try {
                server.start();
            } catch (ServerException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(new SleepyRunnable(NANOS_PER_UPDATE, () -> {
            while (!server.pendingMessages().isEmpty()) {
                Message message = server.pendingMessages().poll();
                System.out.println(message);
            }
        })).start();

        new Thread(new SleepyRunnable(1_000_000_000, () -> {
            try {
                server.broadcastMessage(new DummyMessage());
            } catch (ServerException e) {
                throw new RuntimeException(e);
            }
        })).start();
    }
}
