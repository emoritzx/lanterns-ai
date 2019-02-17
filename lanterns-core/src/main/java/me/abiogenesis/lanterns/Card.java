package me.abiogenesis.lanterns;

import java.util.Objects;

public class Card {

    private final Lantern type;

    public Card(Lantern type) {
        this.type = type;
    }

    public Lantern getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return type.equals(card.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type);
    }
}
