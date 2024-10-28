package j$.time.chrono;

import j$.time.DateTimeException;

public enum ThaiBuddhistEra implements Era {
    BEFORE_BE,
    BE;

    public static ThaiBuddhistEra of(int i) {
        if (i == 0) {
            return BEFORE_BE;
        }
        if (i == 1) {
            return BE;
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    public int getValue() {
        return ordinal();
    }
}
