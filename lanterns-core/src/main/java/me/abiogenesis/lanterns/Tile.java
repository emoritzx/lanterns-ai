package me.abiogenesis.lanterns;

import java.util.*;

import static me.abiogenesis.lanterns.Direction.*;

public class Tile {

    private final Map<Direction, Lantern> lanterns = new LinkedHashMap<>();
    private final Map<Direction, Tile> edges = new LinkedHashMap<>();
    private final String platform;

    public Tile(Lantern north, Lantern east, Lantern south, Lantern west) {
        this(north, east, south, west, null);
    }

    public Tile(Lantern north, Lantern east, Lantern south, Lantern west, String platform) {
        lanterns.put(NORTH, north);
        lanterns.put(EAST, east);
        lanterns.put(SOUTH, south);
        lanterns.put(WEST, west);
        this.platform = platform;
    }

    public Lantern getLantern(Direction direction) {
        return lanterns.get(direction);
    }

    public Optional<String> getPlatform() {
        return Optional.ofNullable(platform);
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

    public void rotate() {
        if (edges.isEmpty()) {
            Direction currentDirection = NORTH;
            Lantern currentLantern = lanterns.get(currentDirection);
            do {
                currentDirection = currentDirection.clockwise();
                currentLantern = lanterns.put(currentDirection, currentLantern);
            } while (currentDirection != NORTH);
        } else {
            throw new IllegalStateException("Cannot rotate a tile with edges");
        }
    }
}
