package j$.util;

import java.util.LinkedHashSet;

public abstract /* synthetic */ class DesugarLinkedHashSet {
    public static Spliterator spliterator(LinkedHashSet linkedHashSet) {
        return Spliterators.spliterator(linkedHashSet, 17);
    }
}
