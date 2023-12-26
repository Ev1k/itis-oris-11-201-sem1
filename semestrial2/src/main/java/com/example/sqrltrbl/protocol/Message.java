package com.example.sqrltrbl.protocol;

import java.nio.ByteBuffer;

public abstract class Message {
    public static final int HEADER_SIZE = Character.BYTES * 2 + Integer.BYTES;
    public static final String MAGIC = "ST";

    public MessageKind getKind() {
        return MessageKind.NONE;
    }

    public abstract ByteBuffer encode();

    protected void encodeHeader(ByteBuffer buffer) {
        buffer.putChar(MAGIC.charAt(0));
        buffer.putChar(MAGIC.charAt(1));
        buffer.putInt(getKind().ordinal());
    }
}
