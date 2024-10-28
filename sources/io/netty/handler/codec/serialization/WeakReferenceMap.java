package io.netty.handler.codec.serialization;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Map;

final class WeakReferenceMap<K, V> extends ReferenceMap<K, V> {
    WeakReferenceMap(Map<K, Reference<V>> map) {
        super(map);
    }

    /* access modifiers changed from: package-private */
    public Reference<V> fold(V v) {
        return new WeakReference(v);
    }
}
