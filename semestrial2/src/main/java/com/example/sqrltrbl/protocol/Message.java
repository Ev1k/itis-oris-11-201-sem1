package com.example.sqrltrbl.protocol;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.nio.ByteBuffer;

@NoArgsConstructor
@Getter
public abstract class Message {
    protected static final int KIND_BYTES = Integer.BYTES;

    protected MessageKind kind;

    public Message(ByteBuffer buffer) throws MessageException {
        this.kind = MessageKind.fromInt(buffer.getInt());
    }

    public abstract ByteBuffer encode();

    protected void encodeHeader(ByteBuffer buffer) {
        buffer.putInt(getKind().ordinal());
    }

    public abstract int bytes();
}
