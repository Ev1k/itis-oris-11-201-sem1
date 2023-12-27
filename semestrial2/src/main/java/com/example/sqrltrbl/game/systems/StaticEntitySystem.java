package com.example.sqrltrbl.game.systems;

import com.example.sqrltrbl.game.EntitySystem;
import com.example.sqrltrbl.game.entities.DynamicEntity;
import com.example.sqrltrbl.game.entities.StaticEntity;

import java.util.List;

public class StaticEntitySystem extends EntitySystem<StaticEntity> {
    public List<StaticEntity> getStaticEntities() {
        return entities.stream()
                .filter(e -> !(e instanceof DynamicEntity))
                .toList();
    }

    public List<DynamicEntity> getDynamicEntities() {
        return entities.stream()
                .filter(e -> e instanceof DynamicEntity)
                .map(e -> (DynamicEntity) e)
                .toList();
    }

    public DynamicEntity get(int index) {
        for (DynamicEntity entity : getDynamicEntities()) {
            if (entity.getId() == index) {
                return entity;
            }
        }
        return null;
    }

    @Override
    public void update(double dt) {}
}
