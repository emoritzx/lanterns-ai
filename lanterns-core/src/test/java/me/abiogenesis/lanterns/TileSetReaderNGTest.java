package me.abiogenesis.lanterns;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Matcher;
import java.util.stream.IntStream;

public class TileSetReaderNGTest {

    @DataProvider
    public static Object[][] patternData() {
        return new Object[][] {
            {
                "X North East South West",
                true,
                true,
                Arrays.asList("North", "East", "South", "West")
            },
            {
                "North East South West",
                true,
                false,
                Arrays.asList("North", "East", "South", "West")
            },
            {
                "A giant butt toot",
                true,
                false,
                Arrays.asList("A", "giant", "butt", "toot")
            },
            {
                "Invalid string",
                false,
                false,
                Collections.EMPTY_LIST
            },
            {
                "X RED BLUE GREEN ORANGE",
                true,
                true,
                Arrays.asList("RED", "BLUE", "GREEN", "ORANGE")
            }
        };
    }

    @Test(dataProvider = "patternData")
    public void testPattern(String entry, boolean matches, boolean hasPlatform, List<String> groups) {
        Matcher matcher = TileSetReader.PATTERN.matcher(entry);
        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(matcher.matches(), matches, "Entry matches pattern");
        if (matches) {
            assertions.assertEquals(TileSetReader.PLATFORM_MARKER.equals(matcher.group("PLATFORM")), hasPlatform, "Entry has platform");
            IntStream.range(0, groups.size())
                .forEach(groupIndex -> assertions.assertEquals(matcher.group(groupIndex + 2), groups.get(groupIndex), "Group"));
        }
        assertions.assertAll();
    }

    @DataProvider
    public static Object[][] parseEntryData() {
        return new Object[][] {
            {
                "X RED BLUE GREEN ORANGE",
                true,
                Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE)
            },
            {
                "RED BLUE GREEN ORANGE",
                false,
                Arrays.asList(Color.RED, Color.BLUE, Color.GREEN, Color.ORANGE)
            }
        };
    }

    @Test(dataProvider = "parseEntryData")
    public void testParseEntry(String entry, boolean hasPlatform, List<Color> colors) {
        LanternFactory factory = new LanternFactory();
        Tile tile = TileSetReader.parseEntry(entry, factory);
        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(tile.hasPlatform(), hasPlatform, "Entry has platform");
        assertions.assertEquals(tile.getLantern(Direction.NORTH).getColor(), colors.get(0), "Color: NORTH");
        assertions.assertEquals(tile.getLantern(Direction.EAST).getColor(), colors.get(1), "Color: EAST");
        assertions.assertEquals(tile.getLantern(Direction.SOUTH).getColor(), colors.get(2), "Color: SOUTH");
        assertions.assertEquals(tile.getLantern(Direction.WEST).getColor(), colors.get(3), "Color: WEST");
        assertions.assertAll();
    }
}
