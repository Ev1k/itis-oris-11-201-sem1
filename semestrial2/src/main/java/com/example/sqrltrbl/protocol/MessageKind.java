package com.example.sqrltrbl.protocol;

public enum MessageKind {
    ACCEPT,
    CLIENT_READY,
    CLIENT_DISCONNECTED,
    HEALTHCHECK,
    INPUT_REQUEST,
    INPUT_RESPONSE,
    STATIC_ENTITY,
    DYNAMIC_ENTITY,
    DYNAMIC_ENTITY_POSITION_REQUEST,
    DYNAMIC_ENTITY_POSITION_RESPONSE,
    NUT_OR_HOLLOW;

    public static MessageKind fromInt(int i) throws MessageException {
        for (MessageKind kind : MessageKind.values()) {
            if (kind.ordinal() == i) {
                return kind;
            }
        }
        throw new MessageException();
    }
}
