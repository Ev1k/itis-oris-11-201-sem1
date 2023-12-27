package com.example.sqrltrbl.protocol;

import lombok.Getter;

import java.nio.ByteBuffer;

@Getter
public abstract class Response extends Message {
    protected static final int ID_BYTES = Integer.BYTES;
    private static final int BYTES = KIND_BYTES + ID_BYTES;
    protected int clientId;

    public Response(int clientId) {
        super();
        this.clientId = clientId;
    }

    public Response(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.clientId = buffer.getInt();
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.flip();
        return buffer;
    }

    @Override
    protected void encodeHeader(ByteBuffer buffer) {
        super.encodeHeader(buffer);
        buffer.putInt(clientId);
    }

    @Override
    public int bytes() {
        return BYTES;
    }
}
