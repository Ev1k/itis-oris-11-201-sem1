package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Response;

import java.nio.ByteBuffer;

public class ClientReadyMessage extends Response {
    public ClientReadyMessage(int clientId) {
        super(clientId);
        this.kind = MessageKind.CLIENT_READY;
    }

    public ClientReadyMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
    }

    @Override
    public String toString() {
        return "ClientReadyMessage{" +
                "clientId=" + clientId +
                '}';
    }
}
