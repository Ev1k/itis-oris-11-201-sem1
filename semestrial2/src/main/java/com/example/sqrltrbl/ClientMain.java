package com.example.sqrltrbl;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.util.SleepyRunnable;
import com.example.sqrltrbl.client.Client;
import com.example.sqrltrbl.client.ClientException;
import com.example.sqrltrbl.client.SocketChannelClient;
import com.example.sqrltrbl.protocol.messages.DummyMessage;

import java.net.InetSocketAddress;

public class ClientMain extends Main {
    public static void main(String[] args) {
        InetSocketAddress address = new InetSocketAddress(HOST, PORT);
        Client client = new SocketChannelClient(address);

        Thread clientThread = new Thread(() -> {
            try {
                client.connect();
            } catch (ClientException e) {
                e.printStackTrace();
            }
        });

        Thread messageQueueThread = new Thread(new SleepyRunnable(NANOS_PER_UPDATE / 10, () -> {
            while (!client.pendingMessages().isEmpty()) {
                Message message = client.pendingMessages().poll();
                System.out.println(message);
            }
        }));

        Thread dummySenderThread = new Thread(new SleepyRunnable(1_000_000_000, () -> {
            try {
                client.sendMessage(new DummyMessage());
            } catch (ClientException e) {
                throw new RuntimeException(e);
            }
        }));

        clientThread.start();
        messageQueueThread.start();
        dummySenderThread.start();
    }
}
