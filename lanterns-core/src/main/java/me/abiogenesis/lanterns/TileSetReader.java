package me.abiogenesis.lanterns;

import java.io.*;
import java.util.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TileSetReader {

    public static final String PATTERN_FORMAT = "(?:X:(?<PLATFORM>\\p{Alnum}+))?\\s*(?<NORTH>\\p{Alnum}+)\\s+(?<EAST>\\p{Alnum}+)\\s+(?<SOUTH>\\p{Alnum}+)\\s+(?<WEST>\\p{Alnum}+)";
    public static final Pattern PATTERN = Pattern.compile(PATTERN_FORMAT);

    public static TileSet read(BufferedReader tileStream, LanternFactory lanternFactory) throws IOException {
        Tile startTile = parseEntry(tileStream.readLine(), lanternFactory);
        List<Tile> tiles = tileStream.lines()
            .map(line -> TileSetReader.parseEntry(line, lanternFactory))
            .collect(Collectors.toList());
        Set<Lantern> lanternTypes = lanternFactory.getTypes();
        return new TileSet(startTile, tiles, lanternTypes);
    }

    public static Tile parseEntry(String raw, LanternFactory lanternFactory) {
        Matcher matcher = PATTERN.matcher(raw);
        matcher.find();
        String platform = matcher.group("PLATFORM");
        return Arrays.stream(Direction.values())
            .map(Direction::name)
            .map(matcher::group)
            .map(lanternFactory::create)
            .collect(Collectors.collectingAndThen(
                Collectors.toList(),
                list -> new Tile(list.get(0), list.get(1), list.get(2), list.get(3), platform)
            ));
    }
}
