package com.example.sqrltrbl;

import java.net.InetSocketAddress;

public class Config {
    public static final String HOST = "127.0.0.1";
    public static final int PORT = 12345;
    public static final InetSocketAddress ADDRESS = new InetSocketAddress(HOST, PORT);
    public static final int NANOS_PER_UPDATE = 16_666_666;
    public static final int ENTITY_WIDTH = 32;
    public static final int ENTITY_HEIGHT = 32;
}
