package com.example.sqrltrbl.server;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageDecoder;
import com.example.sqrltrbl.protocol.messages.AcceptMessage;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

public class SocketChannelServer implements Server {
    private static final int BUFFER_CAPACITY = 256;
    private final InetSocketAddress address;
    private final List<SocketChannel> clients;
    private final Queue<Message> messages;

    private Selector selector;
    private ServerSocketChannel server;

    public SocketChannelServer(InetSocketAddress address) {
        this.address = address;
        this.clients = new ArrayList<>();
        this.messages = new ConcurrentLinkedDeque<>();
    }

    @Override
    public void start() throws ServerException {
        try {
            selector = Selector.open();
            server = ServerSocketChannel.open();

            server.bind(address);
            server.configureBlocking(false);
            server.register(selector, SelectionKey.OP_ACCEPT);

            while (selector.isOpen()) {
                selector.select();
                Set<SelectionKey> keys = selector.selectedKeys();
                Iterator<SelectionKey> iter = keys.iterator();

                while (iter.hasNext()) {
                    SelectionKey key = iter.next();

                    if (key.isAcceptable()) {
                        accept();
                    }

                    if (key.isReadable()) {
                        readMessage((SocketChannel) key.channel());
                    }

                    iter.remove();
                }
            }
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void sendMessage(int clientId, Message message) throws ServerException {
        SocketChannel client = clients.get(clientId);
        ByteBuffer buffer = message.encode();
        try {
            client.write(buffer);
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public void broadcastMessage(Message message) throws ServerException {
        ByteBuffer buffer = message.encode();
        try {
            for (SocketChannel client : clients) {
                client.write(buffer);
                buffer.rewind();
            }
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    @Override
    public Queue<Message> pendingMessages() {
        return messages;
    }

    private void accept() throws ServerException {
        try {
            SocketChannel client = server.accept();
            client.configureBlocking(false);
            client.register(selector, SelectionKey.OP_READ | SelectionKey.OP_WRITE);
            clients.add(client);

            int clientId = clients.lastIndexOf(client);
            Message message = new AcceptMessage(clientId);
            System.out.println(message);
            sendMessage(clientId, message);

            System.out.println("Accepted client");
        } catch (IOException e) {
            throw new ServerException(e);
        }
    }

    private void readMessage(SocketChannel client) throws ServerException {
        ByteBuffer buffer = ByteBuffer.allocate(BUFFER_CAPACITY);
        try {
            if (client.read(buffer) != -1) {
                buffer.flip();
                Message message = MessageDecoder.decode(buffer);
                messages.add(message);
                buffer.clear();
            }
        } catch (Exception e) {
            throw new ServerException(e);
        }
    }
}
