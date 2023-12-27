package com.example.sqrltrbl.protocol.messages;

import lombok.Getter;
import com.example.sqrltrbl.game.entities.DynamicEntity;
import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Request;

import java.nio.ByteBuffer;

@Getter
public class DynamicEntityMessage extends Request {
    private static final int BYTES = KIND_BYTES + Integer.BYTES + Double.BYTES * 4;
    private DynamicEntity entity;

    public DynamicEntityMessage(DynamicEntity entity) {
        this.kind = MessageKind.DYNAMIC_ENTITY;
        this.entity = entity;
    }

    public DynamicEntityMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.entity = new DynamicEntity(
                buffer.getInt(),
                buffer.getDouble(),
                buffer.getDouble(),
                buffer.getDouble(),
                buffer.getDouble()
        );
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putInt(entity.getId());
        buffer.putDouble(entity.getX());
        buffer.putDouble(entity.getY());
        buffer.putDouble(entity.getWidth());
        buffer.putDouble(entity.getHeight());
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return BYTES;
    }

    @Override
    public String toString() {
        return "DynamicEntityMessage{" +
                "entity=" + entity +
                ", kind=" + kind +
                '}';
    }
}
