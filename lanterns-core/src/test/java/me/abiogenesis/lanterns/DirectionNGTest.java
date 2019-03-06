package me.abiogenesis.lanterns;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static me.abiogenesis.lanterns.Direction.*;
import static org.testng.Assert.*;

public class DirectionNGTest {

    @DataProvider
    public static Object[][] clockwiseData() {
        return new Object[][]{
                {NORTH, EAST},
                {EAST, SOUTH},
                {SOUTH, WEST},
                {WEST, NORTH}
        };
    }

    @Test(dataProvider = "clockwiseData")
    public void testClockwise(Direction original, Direction expected) {
        Direction actual = original.clockwise();
        assertEquals(actual, expected, "Clockwise direction");
    }

    @DataProvider
    public static Object[][] counterClockwiseData() {
        return new Object[][]{
                {NORTH, WEST},
                {EAST, NORTH},
                {SOUTH, EAST},
                {WEST, SOUTH}
        };
    }

    @Test(dataProvider = "counterClockwiseData")
    public void testCounterClockwise(Direction original, Direction expected) {
        Direction actual = original.counterClockwise();
        assertEquals(actual, expected, "Counter clockwise direction");
    }
}
