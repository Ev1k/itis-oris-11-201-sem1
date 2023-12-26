package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.MessageKind;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageException;

import java.nio.ByteBuffer;

@AllArgsConstructor
@Builder
public class AcceptMessage extends Message {
    private static final int SIZE = HEADER_SIZE + Integer.BYTES;

    private Integer clientId;

    @Override
    public MessageKind getKind() {
        return MessageKind.ACCEPT;
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        encodeHeader(buffer);
        buffer.putInt(clientId);
        buffer.flip();
        return buffer;
    }

    public static AcceptMessage decode(ByteBuffer buffer) throws MessageException {
        try {
            return AcceptMessage.builder()
                    .clientId(buffer.getInt())
                    .build();
        } catch (Exception e) {
            throw new MessageException(e);
        }
    }

    @Override
    public String toString() {
        return "AcceptMessage{" +
                "clientId=" + clientId +
                '}';
    }
}
