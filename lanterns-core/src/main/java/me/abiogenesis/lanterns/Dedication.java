package me.abiogenesis.lanterns;

import java.util.Collection;

@FunctionalInterface
public interface Dedication {

    public boolean accepts(Collection<Lantern> lanterns);

    public default Dedication andThen(Dedication append) {
        return lanterns -> accepts(lanterns) && append.accepts(lanterns);
    }

    public static final Dedication FOUR_OF_A_KIND = byCounting(4).andThen(byGrouping(1));
    public static final Dedication THREE_PAIR = byCounting(6).andThen(byGrouping(3));
    public static final Dedication SEVEN_UNIQUE = byCounting(7).andThen(byGrouping(7));

    public static Dedication byGrouping(int distinct) {
        return lanterns -> lanterns.stream().distinct().count() == distinct;
    }

    public static Dedication byCounting(int size) {
        return lanterns -> lanterns.size() == size;
    }
}
