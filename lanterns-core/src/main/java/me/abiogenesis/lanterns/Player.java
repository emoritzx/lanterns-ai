package me.abiogenesis.lanterns;

import java.util.ArrayList;
import java.util.Collection;

public class Player {

    private final Collection<Tile> hand = new ArrayList<>();
    private int favorTokens = 0;
    private final String name;

    public Player(String name) {
        this.name = name;
    }

    public Player(String name, Collection<Tile> hand, int favorTokens) {
        this.name = name;
        this.hand.addAll(hand);
        this.favorTokens = favorTokens;
    }

    public Collection<Tile> getHand() {
        return hand;
    }

    public int getFavorTokens() {
        return favorTokens;
    }

    public String getName() {
        return name;
    }
}
