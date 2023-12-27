package com.example.sqrltrbl.protocol;

import java.nio.ByteBuffer;

public abstract class Request extends Message {
    public Request() {
        super();
    }

    public Request(ByteBuffer buffer) throws MessageException {
        super(buffer);
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(KIND_BYTES);
        encodeHeader(buffer);
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return KIND_BYTES;
    }
}
