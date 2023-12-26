package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.Message;
import com.example.sqrltrbl.protocol.MessageKind;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.nio.ByteBuffer;

@AllArgsConstructor
@Builder
public class DummyMessage extends Message {
    private static final int SIZE = HEADER_SIZE;

    @Override
    public MessageKind getKind() {
        return MessageKind.DUMMY;
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(SIZE);
        encodeHeader(buffer);
        buffer.flip();
        return buffer;
    }

    public static DummyMessage decode(ByteBuffer buffer) {
        return DummyMessage.builder().build();
    }

    @Override
    public String toString() {
        return "DummyMessage{}";
    }
}
