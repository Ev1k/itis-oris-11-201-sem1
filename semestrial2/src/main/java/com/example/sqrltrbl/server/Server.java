package com.example.sqrltrbl.server;

import com.example.sqrltrbl.protocol.Message;

import java.util.Queue;

public interface Server {
    void start() throws ServerException;

    void sendMessage(int clientId, Message message) throws ServerException;

    void broadcastMessage(Message message) throws ServerException;

    Queue<Message> pendingMessages();

    void setClientReady(int clientId);
}
