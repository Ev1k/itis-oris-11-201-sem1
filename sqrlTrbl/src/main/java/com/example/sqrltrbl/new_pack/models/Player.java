package com.example.sqrltrbl.new_pack.models;

import javafx.scene.image.Image;
import lombok.SneakyThrows;

import java.io.*;
import java.util.UUID;

public class Player implements Serializable, PossibleToSerialize {

    public final String serialVersion = "111b34";

    private int x;
    private int y;
    private Image image;
    private UUID uuid;

    public Player() {

    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public Player(int x, int y, Image image, UUID uuid) {
        this.x = x;
        this.y = y;
        this.image = image;
        this.uuid = uuid;
    }

    @Override
    @SneakyThrows
    public byte[] serialize() {
        try(ByteArrayOutputStream b = new ByteArrayOutputStream()){
            try(ObjectOutputStream o = new ObjectOutputStream(b)){
                o.writeObject(this);
            }
            return b.toByteArray();
        }
    }

    @Override
    @SneakyThrows
    public Object serialize(byte[] bytes) {
        try(ByteArrayInputStream b = new ByteArrayInputStream(bytes)){
            try(ObjectInputStream o = new ObjectInputStream(b)){
                return o.readObject();
            }
        }
    }
}
