package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Response;

import java.nio.ByteBuffer;

public class InputResponse extends Response {
    private static final int BYTES = KIND_BYTES + ID_BYTES + Integer.BYTES;
    public static final int LEFT = 1 << 0;
    public static final int RIGHT = 1 << 1;
    public static final int JUMP = 1 << 2;
    private int input;

    public InputResponse(int clientId, int input) {
        super(clientId);
        this.kind = MessageKind.INPUT_RESPONSE;
        this.input = input;
    }

    public InputResponse(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.input = buffer.getInt();
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putInt(input);
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return BYTES;
    }

    public boolean isLeft() {
        return (input & LEFT) != 0;
    }

    public boolean isRight() {
        return (input & RIGHT) != 0;
    }

    public boolean isJump() {
        return (input & JUMP) != 0;
    }

    @Override
    public String toString() {
        return "InputResponse{" +
                "clientId=" + clientId +
                ", left=" + isLeft() +
                ", right=" + isRight() +
                ", jump=" + isJump() +
                '}';
    }
}
