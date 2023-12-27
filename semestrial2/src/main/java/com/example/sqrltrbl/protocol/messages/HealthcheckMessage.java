package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Response;

import java.nio.ByteBuffer;

public class HealthcheckMessage extends Response {
    public HealthcheckMessage(int clientId) {
        super(clientId);
        this.kind = MessageKind.HEALTHCHECK;
    }

    public HealthcheckMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
    }

    @Override
    public String toString() {
        return "HealthcheckMessage{" +
                "clientId=" + clientId +
                '}';
    }
}
