package j$.util;

import j$.util.Comparator;
import java.util.Comparator;

enum Comparators$NaturalOrderComparator implements Comparator, Comparator {
    INSTANCE;

    public int compare(Comparable comparable, Comparable comparable2) {
        return comparable.compareTo(comparable2);
    }

    public Comparator reversed() {
        return Comparator.CC.reverseOrder();
    }
}
