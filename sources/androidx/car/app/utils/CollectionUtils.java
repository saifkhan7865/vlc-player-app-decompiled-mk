package androidx.car.app.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CollectionUtils {
    public static <T> List<T> emptyIfNull(List<T> list) {
        return list != null ? list : Collections.emptyList();
    }

    public static <K, V> Map<K, V> unmodifiableCopy(Map<K, V> map) {
        return map == null ? Collections.emptyMap() : Collections.unmodifiableMap(new HashMap(map));
    }

    public static <T> List<T> unmodifiableCopy(List<T> list) {
        return list == null ? Collections.emptyList() : Collections.unmodifiableList(new ArrayList(list));
    }

    private CollectionUtils() {
    }
}
