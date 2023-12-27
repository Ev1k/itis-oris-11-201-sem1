package com.example.sqrltrbl.protocol;

import com.example.sqrltrbl.protocol.messages.*;

import java.nio.ByteBuffer;

public class MessageDecoder {
    public static Message decode(ByteBuffer buffer) throws MessageException {
        MessageKind kind = MessageKind.fromInt(buffer.getInt());

        buffer.rewind();

        return switch (kind) {
            case ACCEPT -> new AcceptMessage(buffer);
            case NUT_OR_HOLLOW -> new NutOrHollowMessage(buffer);
            case CLIENT_READY -> new ClientReadyMessage(buffer);
            case CLIENT_DISCONNECTED -> new ClientDisconnectedMessage(buffer);
            case HEALTHCHECK -> new HealthcheckMessage(buffer);
            case INPUT_REQUEST -> new InputRequest(buffer);
            case INPUT_RESPONSE -> new InputResponse(buffer);
            case STATIC_ENTITY -> new StaticEntityMessage(buffer);
            case DYNAMIC_ENTITY -> new DynamicEntityMessage(buffer);
            case DYNAMIC_ENTITY_POSITION_REQUEST -> new DynamicEntityPositionRequest(buffer);
            case DYNAMIC_ENTITY_POSITION_RESPONSE -> new DynamicEntityPositionResponse(buffer);
        };
    }
}
