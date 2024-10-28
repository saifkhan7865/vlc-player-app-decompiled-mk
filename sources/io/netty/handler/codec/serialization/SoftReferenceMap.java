package io.netty.handler.codec.serialization;

import java.lang.ref.Reference;
import java.lang.ref.SoftReference;
import java.util.Map;

final class SoftReferenceMap<K, V> extends ReferenceMap<K, V> {
    SoftReferenceMap(Map<K, Reference<V>> map) {
        super(map);
    }

    /* access modifiers changed from: package-private */
    public Reference<V> fold(V v) {
        return new SoftReference(v);
    }
}
