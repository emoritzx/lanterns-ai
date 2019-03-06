package me.abiogenesis.lanterns;

public enum Direction {
    NORTH,
    EAST,
    SOUTH,
    WEST;

    public Direction clockwise() {
        return fromOrdinal(ordinal() + 1);
    }

    public Direction counterClockwise() {
        return fromOrdinal(ordinal() - 1);
    }

    private static Direction fromOrdinal(int index) {
        if (index < 0) {
            index += Direction.values().length;
        }
        return Direction.values()[index % Direction.values().length];
    }
}
