package com.example.sqrltrbl.game.systems;

import com.example.sqrltrbl.ClientConfig;
import com.example.sqrltrbl.game.Direction;

public class
PhysicsSystem extends StaticEntitySystem {
    private static final double GRAVITY = 100.0;
    private static final double X_SPEED = 100.0;
    private static final double Y_SPEED = 100.0;


    @Override
    public void update(double dt) {
        getDynamicEntities().forEach(e -> {
            e.setDx(0);
            e.setDy(e.getDy() + GRAVITY * dt);

            if (e.getDirection().equals(Direction.LEFT)) {
                e.setDx(-X_SPEED);
            } else if (e.getDirection().equals(Direction.RIGHT)) {
                e.setDx(X_SPEED);
            }

            if (e.isOnGround()) {
                if (e.isJumping()) {
                    e.setJumping(false);
                    e.setOnGround(false);
                    e.setDy(-Y_SPEED);
                }
            }

            e.setX(e.getX() + e.getDx() * dt);

            if (e.getX() < 0) {
                e.setX(0);
            } else if (e.getX() + e.getWidth() > ClientConfig.WIDTH) {
                e.setX(ClientConfig.WIDTH - e.getWidth());
            }

            getStaticEntities().stream()
                    .filter(e::intersects)
                    .forEach(s -> {
                        if(!s.isSolid()) {
                            if (e.getDirection().equals(Direction.LEFT)) {
                                e.setX(s.right());
                            } else if (e.getDirection().equals(Direction.RIGHT)) {
                                e.setX(s.left() - e.getWidth());
                            }
                        }
                    });

            e.setY(e.getY() + e.getDy() * dt);

            getStaticEntities().stream()
                    .filter(e::intersects)
                    .forEach(s -> {
                        if (!s.isSolid()) {
                            if (e.getDy() < 0) {
                                e.setY(s.bottom());
                            } else {
                                e.setDy(0);
                                e.setY(s.top() - e.getHeight());
                                e.setOnGround(true);
                            }
                        }
                    });
        });
    }
}
