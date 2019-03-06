package me.abiogenesis.lanterns;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static java.awt.Color.*;
import static me.abiogenesis.lanterns.Direction.*;

public class TileNGTest {

    @Test
    public void testRotate() throws Exception {
        LanternFactory lanternFactory = new LanternFactory();
        Lantern north = lanternFactory.create(RED);
        Lantern east = lanternFactory.create(GREEN);
        Lantern south = lanternFactory.create(BLUE);
        Lantern west = lanternFactory.create(WHITE);
        Tile tile = new Tile(north, east, south, west);
        tile.rotate();
        SoftAssert assertions = new SoftAssert();
        assertions.assertEquals(tile.getLantern(NORTH), west, "West -> North");
        assertions.assertEquals(tile.getLantern(EAST), north, "North -> East");
        assertions.assertEquals(tile.getLantern(SOUTH), east, "East -> South");
        assertions.assertEquals(tile.getLantern(WEST), south, "South -> West");
        assertions.assertAll();
    }
}
