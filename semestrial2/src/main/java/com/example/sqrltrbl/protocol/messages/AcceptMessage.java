package com.example.sqrltrbl.protocol.messages;

import lombok.Getter;
import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;

import java.nio.ByteBuffer;

@Getter
public class AcceptMessage extends Message {
    private static final int BYTES = KIND_BYTES + Integer.BYTES;

    private final int clientId;

    public AcceptMessage(int clientId) {
        this.kind = MessageKind.ACCEPT;
        this.clientId = clientId;
    }

    public AcceptMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.clientId = buffer.getInt();
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putInt(clientId);
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return BYTES;
    }

    @Override
    public String toString() {
        return "AcceptMessage{" +
                "clientId=" + clientId +
                '}';
    }
}