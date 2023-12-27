package com.example.sqrltrbl.game.entities;

import lombok.Getter;
import lombok.Setter;
import com.example.sqrltrbl.game.Direction;

import java.util.Objects;

@Getter
@Setter
public class DynamicEntity extends StaticEntity {
    protected int id;
    protected double dx;
    protected double dy;
    protected Direction direction;
    protected boolean onGround;
    protected boolean jumping;

    public DynamicEntity(int id, double x, double y, double width, double height) {
        super(x, y, width, height, 0,true);
        this.id = id;
        this.direction = Direction.NONE;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
//        if (!super.equals(o)) return false;
        DynamicEntity entity = (DynamicEntity) o;
        return id == entity.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), id);
    }

    @Override
    public String toString() {
        return "DynamicEntity{" +
                "id=" + id +
                ", x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                ", dx=" + dx +
                ", dy=" + dy +
                ", direction=" + direction +
                ", onGround=" + onGround +
                ", jumping=" + jumping +
                '}';
    }
}
