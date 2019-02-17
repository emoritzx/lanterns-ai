package me.abiogenesis.lanterns;

import java.util.*;

import static me.abiogenesis.lanterns.Direction.*;

public class Tile {

    private final Map<Direction, Lantern> lanterns = new LinkedHashMap<>();
    private final Map<Direction, Tile> edges = new LinkedHashMap<>();
    private final boolean hasPlatform;

    public Tile(Lantern north, Lantern east, Lantern south, Lantern west, boolean hasPlatform) {
        lanterns.put(NORTH, north);
        lanterns.put(EAST, east);
        lanterns.put(SOUTH, south);
        lanterns.put(WEST, west);
        this.hasPlatform = hasPlatform;
    }

    public Lantern getLantern(Direction direction) {
        return lanterns.get(direction);
    }

    public boolean hasPlatform() {
        return hasPlatform;
    }

    public Optional<Tile> getEdge(Direction direction) {
        return Optional.ofNullable(edges.get(direction));
    }

    public Optional<Tile> removeEdge(Direction direction) {
        return Optional.ofNullable(edges.remove(direction));
    }

    public void clear() {
        Arrays.stream(Direction.values())
            .forEach(this::removeEdge);
    }
}
