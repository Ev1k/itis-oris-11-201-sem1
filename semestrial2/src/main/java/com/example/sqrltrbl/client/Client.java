package com.example.sqrltrbl.client;

import com.example.sqrltrbl.protocol.Message;

import java.util.Queue;

public interface Client {
    void connect() throws ClientException;

    void sendMessage(Message message) throws ClientException;

    Queue<Message> pendingMessages();
}
