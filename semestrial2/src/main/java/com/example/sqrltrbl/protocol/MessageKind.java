package com.example.sqrltrbl.protocol;

public enum MessageKind {
    NONE,
    DUMMY,
    ACCEPT;

    public static MessageKind fromInt(int i) throws MessageException {
        for (MessageKind kind : MessageKind.values()) {
            if (kind.ordinal() == i) {
                return kind;
            }
        }
        throw new MessageException();
    }
}
