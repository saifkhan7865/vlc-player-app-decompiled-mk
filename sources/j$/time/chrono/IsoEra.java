package j$.time.chrono;

import j$.time.DateTimeException;

public enum IsoEra implements Era {
    BCE,
    CE;

    public static IsoEra of(int i) {
        if (i == 0) {
            return BCE;
        }
        if (i == 1) {
            return CE;
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    public int getValue() {
        return ordinal();
    }
}
