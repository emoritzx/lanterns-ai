package me.abiogenesis.lanterns;

import java.util.List;
import java.util.Stack;

public class DedicationTokens {

    private final Stack<Integer> pile = new Stack<>();
    private final int baseValue;

    public DedicationTokens(int baseValue, List<Integer> values) {
        this.baseValue = baseValue;
        pile.addAll(values);
    }

    public int draw() {
        return pile.isEmpty() ? baseValue : pile.pop();
    }

    public int peek() {
        return pile.isEmpty() ? baseValue : pile.peek();
    }
}
