package j$.time;

import j$.util.Objects;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public abstract /* synthetic */ class ZoneId$$ExternalSyntheticBackport1 {
    public static /* synthetic */ Map m(Map.Entry[] entryArr) {
        HashMap hashMap = new HashMap(entryArr.length);
        int length = entryArr.length;
        int i = 0;
        while (i < length) {
            Map.Entry entry = entryArr[i];
            Object requireNonNull = Objects.requireNonNull(entry.getKey());
            if (hashMap.put(requireNonNull, Objects.requireNonNull(entry.getValue())) == null) {
                i++;
            } else {
                throw new IllegalArgumentException("duplicate key: " + requireNonNull);
            }
        }
        return Collections.unmodifiableMap(hashMap);
    }
}
