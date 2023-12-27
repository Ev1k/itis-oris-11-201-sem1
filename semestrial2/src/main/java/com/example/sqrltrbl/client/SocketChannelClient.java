package com.example.sqrltrbl.client;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageDecoder;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.messages.AcceptMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SocketChannelClient implements Client {
    private static final int BUFFER_CAPACITY = 1 << 12;
    private final InetSocketAddress address;

    private final Queue<Message> messages;
    private SocketChannel client;

    private int id;

    public SocketChannelClient(InetSocketAddress address) {
        this.address = address;
        this.messages = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void connect() throws ClientException {
        try {
            client = SocketChannel.open(address);

            while (client.isOpen()) {
                readMessage();
            }
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }

    @Override
    public void sendMessage(Message message) throws ClientException {
        System.out.println(message + " --> server");

        ByteBuffer buffer = message.encode();
        try {
            client.write(buffer);
        } catch (IOException e) {
            throw new ClientException(e);
        }
    }

    @Override
    public Queue<Message> pendingMessages() {
        return messages;
    }

    @Override
    public int getId() {
        return id;
    }

    private void readMessage() throws ClientException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        try {
            int bytesRead = client.read(buffer);
            if (bytesRead != -1) {
                buffer.flip();

                Message message = MessageDecoder.decode(buffer);

                if (message.getKind().equals(MessageKind.ACCEPT)) {
                    AcceptMessage acceptMessage = (AcceptMessage) message;
                    id = acceptMessage.getClientId();
                }

                messages.add(message);

                buffer.clear();
            }
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }
}
