package me.abiogenesis.lanterns;

import java.awt.*;
import java.util.Objects;

public class Lantern {

    private final Color type;

    public Lantern(Color type) {
        this.type = type;
    }

    public Color getColor() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Lantern lantern = (Lantern) o;
        return type == lantern.type;
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
