package com.example.sqrltrbl.client;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageDecoder;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SocketChannelClient implements Client {
    private static final int BUFFER_CAPACITY = 256;
    private final InetSocketAddress address;

    private final Queue<Message> messages;
    private SocketChannel client;
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

    private void readMessage() throws ClientException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        try {
            if (client.read(buffer) != -1) {
                buffer.flip();
                Message message = MessageDecoder.decode(buffer);
                messages.add(message);
                buffer.clear();
            }
        } catch (Exception e) {
            throw new ClientException(e);
        }
    }
}
