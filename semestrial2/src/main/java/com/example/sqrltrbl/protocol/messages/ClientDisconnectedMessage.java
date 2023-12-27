package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Response;

import java.nio.ByteBuffer;

public class ClientDisconnectedMessage extends Response {
    public ClientDisconnectedMessage(int clientId) {
        super(clientId);
        this.kind = MessageKind.CLIENT_DISCONNECTED;
    }

    public ClientDisconnectedMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
    }

    @Override
    public String toString() {
        return "ClientDisconnectedMessage{" +
                "clientId=" + clientId +
                '}';
    }
}
