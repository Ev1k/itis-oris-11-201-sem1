package com.example.sqrltrbl.protocol.messages;

import lombok.Getter;
import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Response;

import java.nio.ByteBuffer;

@Getter
public class DynamicEntityPositionRequest extends Response {
    private static final int BYTES = KIND_BYTES + ID_BYTES + Integer.BYTES;

    int entityId;

    public DynamicEntityPositionRequest(int clientId, int entityId) {
        super(clientId);
        this.kind = MessageKind.DYNAMIC_ENTITY_POSITION_REQUEST;
        this.entityId = entityId;
    }

    public DynamicEntityPositionRequest(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.entityId = buffer.getInt();
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putInt(entityId);
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return BYTES;
    }

    @Override
    public String toString() {
        return "DynamicEntityPositionRequest{" +
                "clientId=" + clientId +
                ", entityId=" + entityId +
                '}';
    }
}
