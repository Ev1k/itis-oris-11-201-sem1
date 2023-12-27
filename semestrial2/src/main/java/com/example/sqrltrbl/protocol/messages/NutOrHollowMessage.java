package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public class NutOrHollowMessage extends Message {
    private static final int BYTES = KIND_BYTES + Integer.BYTES * 2;
    private final int clientId;
    private final int nutOtHollow;

    public NutOrHollowMessage(int clientId, int nutOtHollow) {
        this.kind = MessageKind.NUT_OR_HOLLOW;
        this.clientId = clientId;
        this.nutOtHollow = nutOtHollow;
    }

    public NutOrHollowMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.clientId = buffer.getInt();
        this.nutOtHollow = buffer.getInt();
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putInt(clientId);
        buffer.putInt(nutOtHollow);
        buffer.flip();
        return buffer;
    }


    @Override
    public int bytes() {
        return BYTES;
    }

    @Override
    public String toString() {
        return "NutOrHollowMessage{" +
                "clientId=" + clientId +
                " Nut/Hollow=" + nutOtHollow +
                '}';
    }
}
