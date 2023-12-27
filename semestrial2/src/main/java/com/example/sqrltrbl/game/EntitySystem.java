package com.example.sqrltrbl.game;

import lombok.Getter;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public abstract class EntitySystem<T extends Entity> {
    @Getter
    protected final Queue<T> entities;

    public EntitySystem() {
        entities = new ConcurrentLinkedDeque<>();
    }

    public <U extends T> void addEntity(U entity) {
        entities.add(entity);
    }

    public <U extends T> void removeEntity(U entity) {
        entities.remove(entity);
    }

    public abstract void update(double dt);
}
