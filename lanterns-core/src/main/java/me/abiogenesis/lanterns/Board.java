package me.abiogenesis.lanterns;

public class Board {

    private final Tile root;

    public Board(Tile root) {
        this.root = root;
    }

    public Tile getStartTile() {
        return root;
    }
}
