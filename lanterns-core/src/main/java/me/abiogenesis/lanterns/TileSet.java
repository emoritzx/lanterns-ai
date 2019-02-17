package me.abiogenesis.lanterns;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class TileSet {

    public static final String TILESET_RESOURCE = "/me/abiogenesis/lanterns/default_tileset.dat";

    private final Tile startTile;
    private final Collection<Tile> tiles;
    private final Set<Lantern> lanternTypes;

    public TileSet(Tile startTile, Collection<Tile> tiles, Set<Lantern> lanternTypes) {
        this.startTile = startTile;
        this.tiles = tiles;
        this.lanternTypes = lanternTypes;
    }

    public Tile getStartTile() {
        return startTile;
    }

    public Collection<Tile> getTiles() {
        return tiles;
    }

    public Set<Lantern> getLanternTypes() {
        return lanternTypes;
    }

    private static class LazyHolder {

        static final TileSet INSTANCE;

        static {
            try (InputStream stream = Settings.class.getResourceAsStream(TILESET_RESOURCE);
                 InputStreamReader reader = new InputStreamReader(stream);
                 BufferedReader buffer = new BufferedReader(reader))
            {
                INSTANCE = TileSetReader.read(buffer, new LanternFactory());
            } catch (IOException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
    }

    public static TileSet getStock() {
        return LazyHolder.INSTANCE;
    }
}
