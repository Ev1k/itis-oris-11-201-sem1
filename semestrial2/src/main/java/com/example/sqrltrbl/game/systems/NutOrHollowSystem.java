package com.example.sqrltrbl.game.systems;


import com.example.sqrltrbl.game.entities.DynamicEntity;
import com.example.sqrltrbl.game.entities.StaticEntity;
import lombok.AllArgsConstructor;

import java.util.HashMap;
import java.util.Map;

public class NutOrHollowSystem extends StaticEntitySystem {
    private Map<Integer, Integer> gotNutOrHollow;

    public NutOrHollowSystem(Callback callback) {
        this.callback = callback;
        this.gotNutOrHollow = new HashMap<>();
    }

    @FunctionalInterface
    public interface Callback {
        void callback(int clientId, int nutOrHollow);
    }

    private Callback callback;

    @Override
    public <U extends StaticEntity> void addEntity(U entity) {
        super.addEntity(entity);
        if (entity instanceof DynamicEntity) {
            gotNutOrHollow.put(((DynamicEntity) entity).getId(), 0);
        }
    }

    @Override
    public <U extends StaticEntity> void removeEntity(U entity) {
        super.removeEntity(entity);
        gotNutOrHollow.remove(((DynamicEntity) entity).getId());
    }

    @Override
    public void update(double dt) {
        getDynamicEntities().forEach(e -> {
            getStaticEntities().stream()
                    .filter(e::intersects)
                    .filter(StaticEntity::isSolid)
                    .forEach(s -> {
                        if (s.getNutOrHollow() == 1 && gotNutOrHollow.getOrDefault(e.getId(), 0) == 0) {
                            gotNutOrHollow.put(e.getId(), 1);
                            callback.callback(e.getId(), 1);
                        } else if (s.getNutOrHollow() == 2 && gotNutOrHollow.getOrDefault(e.getId(), 0) == 1) {
                            gotNutOrHollow.put(e.getId(), 2);
                            callback.callback(e.getId(), 2);
                        }
                    });
        });
    }
}
