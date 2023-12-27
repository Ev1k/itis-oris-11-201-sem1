package com.example.sqrltrbl.game.entities;

import com.example.sqrltrbl.game.Entity;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
public class StaticEntity extends Entity {
    protected double width;
    protected double height;
    private boolean isSolid;
    private int nutOrHollow; // 1 - nut, 2 - hollow, 0-else


    public StaticEntity(double x, double y, double width, double height, int nutOrHollow, boolean isSolid) {
        super(x, y);
        this.width = width;
        this.height = height;
        this.nutOrHollow = nutOrHollow;
        this.isSolid = isSolid;
    }

    public double top() {
        return y;
    }

    public double right() {
        return x + width;
    }

    public double bottom() {
        return y + height;
    }

    public double left() {
        return x;
    }

    public boolean intersects(StaticEntity other) {
        return right() > other.left() &&
                left() < other.right() &&
                top() < other.bottom() &&
                bottom() > other.top();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        StaticEntity entity = (StaticEntity) o;
        return Double.compare(width, entity.width) == 0 && Double.compare(height, entity.height) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), width, height);
    }

    @Override
    public String toString() {
        return "StaticEntity{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
