package com.example.sqrltrbl.protocol.messages;

import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Request;

import java.nio.ByteBuffer;

public class InputRequest extends Request {
    public InputRequest() {
        this.kind = MessageKind.INPUT_REQUEST;
    }

    public InputRequest(ByteBuffer buffer) throws MessageException {
        super(buffer);
    }

    @Override
    public String toString() {
        return "InputRequest{}";
    }
}
