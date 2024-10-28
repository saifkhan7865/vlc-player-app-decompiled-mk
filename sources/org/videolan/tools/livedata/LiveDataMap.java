package org.videolan.tools.livedata;

import androidx.lifecycle.MutableLiveData;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u0003B\u0005¢\u0006\u0002\u0010\u0005J\u001b\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u00002\u0006\u0010\n\u001a\u00028\u0001¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\bJ\u0015\u0010\r\u001a\u0004\u0018\u00018\u00012\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004H\u0016J\u0013\u0010\u0010\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000¢\u0006\u0002\u0010\u0011R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/videolan/tools/livedata/LiveDataMap;", "K", "V", "Landroidx/lifecycle/MutableLiveData;", "", "()V", "emptyMap", "add", "", "key", "item", "(Ljava/lang/Object;Ljava/lang/Object;)V", "clear", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "getValue", "remove", "(Ljava/lang/Object;)V", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: LiveDataMap.kt */
public final class LiveDataMap<K, V> extends MutableLiveData<Map<K, V>> {
    private final Map<K, V> emptyMap = new LinkedHashMap();

    public Map<K, V> getValue() {
        Map<K, V> map = (Map) super.getValue();
        return map == null ? this.emptyMap : map;
    }

    public final void clear() {
        Map value = getValue();
        value.clear();
        setValue(value);
    }

    public final void add(K k, V v) {
        Map value = getValue();
        value.put(k, v);
        setValue(value);
    }

    public final void remove(K k) {
        Map value = getValue();
        value.remove(k);
        setValue(value);
    }

    public final V get(K k) {
        return getValue().get(k);
    }
}
