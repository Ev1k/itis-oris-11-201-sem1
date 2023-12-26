package com.example.sqrltrbl.protocol;

import com.example.sqrltrbl.protocol.messages.AcceptMessage;
import com.example.sqrltrbl.protocol.messages.DummyMessage;

import java.nio.ByteBuffer;

public class MessageDecoder {
    public static Message decode(ByteBuffer buffer) throws MessageException {
        char magic0 = buffer.getChar();
        char magic1 = buffer.getChar();

        if (magic0 != Message.MAGIC.charAt(0) || magic1 != Message.MAGIC.charAt(1)) {
            throw new MessageException();
        }

        MessageKind kind = MessageKind.fromInt(buffer.getInt());

        return switch (kind) {
            case NONE -> null;
            case DUMMY -> DummyMessage.decode(buffer);
            case ACCEPT -> AcceptMessage.decode(buffer);
        };
    }
}
