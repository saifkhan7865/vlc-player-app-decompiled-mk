package j$.time.chrono;

import j$.time.DateTimeException;

public enum MinguoEra implements Era {
    BEFORE_ROC,
    ROC;

    public static MinguoEra of(int i) {
        if (i == 0) {
            return BEFORE_ROC;
        }
        if (i == 1) {
            return ROC;
        }
        throw new DateTimeException("Invalid era: " + i);
    }

    public int getValue() {
        return ordinal();
    }
}
