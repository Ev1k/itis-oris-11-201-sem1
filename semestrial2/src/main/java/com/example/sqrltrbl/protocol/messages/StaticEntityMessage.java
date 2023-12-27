package com.example.sqrltrbl.protocol.messages;

import lombok.Getter;
import com.example.sqrltrbl.game.entities.StaticEntity;
import com.example.sqrltrbl.protocol.MessageException;
import com.example.sqrltrbl.protocol.MessageKind;
import com.example.sqrltrbl.protocol.Request;

import java.nio.ByteBuffer;

@Getter
public class StaticEntityMessage extends Request {
    private static final int BYTES = KIND_BYTES + Integer.BYTES * 2 + Double.BYTES * 4;
    private StaticEntity entity;

    public StaticEntityMessage(StaticEntity entity) {
        this.kind = MessageKind.STATIC_ENTITY;
        this.entity = entity;
    }

    public StaticEntityMessage(ByteBuffer buffer) throws MessageException {
        super(buffer);
        this.entity = new StaticEntity(
                buffer.getDouble(),
                buffer.getDouble(),
                buffer.getDouble(),
                buffer.getDouble(),
                buffer.getInt(),
                buffer.getInt() == 1
        );
    }

    @Override
    public ByteBuffer encode() {
        ByteBuffer buffer = ByteBuffer.allocate(BYTES);
        encodeHeader(buffer);
        buffer.putDouble(entity.getX());
        buffer.putDouble(entity.getY());
        buffer.putDouble(entity.getWidth());
        buffer.putDouble(entity.getHeight());
        buffer.putInt(entity.getNutOrHollow());
        buffer.putInt(entity.isSolid() ? 1 : 0);
        buffer.flip();
        return buffer;
    }

    @Override
    public int bytes() {
        return BYTES;
    }

    @Override
    public String toString() {
        return "StaticEntityMessage{" +
                "entity=" + entity +
                '}';
    }
}
