package com.example.sqrltrbl.protocol.messages;

import lombok.Getter;
import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Request;

import java.nio.ByteBuffer;

@Getter
public class DynamicEntityPositionResponse extends Request {
    private static final int BYTES = KIND_BYTES + Integer.BYTES + Double.BYTES * 2;
    private int entityId;
    private double x;
    private double y;

    public DynamicEntityPositionResponse(int entityId, double x, double y) {
        this.kind = MessageKind.DYNAMIC_ENTITY_POSITION_RESPONSE;
        this.entityId = entityId;
        this.x = x;
        this.y = y;
    }

    public DynamicEntityPositionResponse(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.entityId = buffer.getInt();
        this.x = buffer.getDouble();
        this.y = buffer.getDouble();
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putInt(entityId);
        buffer.putDouble(x);
        buffer.putDouble(y);
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return BYTES;
    }

    @Override
    public String toString() {
        return "DynamicEntityPositionResponse{" +
                "entityId=" + entityId +
                ", x=" + x +
                ", y=" + y +
                '}';
    }
}
