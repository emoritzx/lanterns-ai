package me.abiogenesis.lanterns;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.testng.Assert.assertEquals;

public class DedicationNGTest {

    @DataProvider
    public static Object[][] fourOfAKindData() {
        LanternFactory lanternFactory = new LanternFactory();
        return new Object[][]{
            {
                Stream.of("RED", "RED", "RED", "RED")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                true
            },
            {
                Stream.of("RED", "RED", "RED")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                false
            },
            {
                Stream.of("RED", "RED", "RED", "BLUE")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                false
            }
        };
    }

    @Test(dataProvider = "fourOfAKindData")
    public void testFourOfAKindDedication(Collection<Lantern> lanterns, boolean accept) {
        assertEquals(Dedication.FOUR_OF_A_KIND.accepts(lanterns), accept, "Accept lanterns");
    }

    @DataProvider
    public static Object[][] threePairData() {
        LanternFactory lanternFactory = new LanternFactory();
        return new Object[][]{
            {
                Stream.of("RED", "RED", "RED", "RED", "RED", "RED")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                false
            },
            {
                Stream.of("RED", "RED", "RED")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                false
            },
            {
                Stream.of("RED", "RED", "GREEN", "GREEN", "BLUE", "BLUE")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                true
            }
        };
    }

    @Test(dataProvider = "threePairData")
    public void testThreePairDedication(Collection<Lantern> lanterns, boolean accept) {
        assertEquals(Dedication.THREE_PAIR.accepts(lanterns), accept, "Accept lanterns");
    }

    @DataProvider
    public static Object[][] sevenUniqueData() {
        LanternFactory lanternFactory = new LanternFactory();
        return new Object[][]{
            {
                Stream.of("RED", "RED", "RED", "RED", "RED", "RED", "RED")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                false
            },
            {
                Stream.of("RED", "RED", "RED")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                false
            },
            {
                Stream.of("RED", "ORANGE", "WHITE", "GREEN", "BLUE", "BLACK", "MAGENTA")
                    .map(lanternFactory::create)
                    .collect(Collectors.toList()),
                true
            }
        };
    }

    @Test(dataProvider = "sevenUniqueData")
    public void testSevenUniqueDedication(Collection<Lantern> lanterns, boolean accept) {
        assertEquals(Dedication.SEVEN_UNIQUE.accepts(lanterns), accept, "Accept lanterns");
    }
}
