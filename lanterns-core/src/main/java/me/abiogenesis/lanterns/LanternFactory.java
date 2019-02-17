package me.abiogenesis.lanterns;

import java.awt.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class LanternFactory {

    private final Map<Color, Lantern> lanterns = new LinkedHashMap<>();

    public Set<Lantern> getTypes() {
        return lanterns.values().stream()
            .collect(Collectors.toSet());
    }

    public Lantern create(String raw) {
        return create(convert(raw));
    }

    public Lantern create(Color value) {
        return lanterns.computeIfAbsent(value, Lantern::new);
    }

    public Color convert(String name) {
        try {
            return (Color) Class.forName("java.awt.Color")
                .getField(name)
                .get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            throw new IllegalArgumentException(e);
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException(e);
        }
    }
}
