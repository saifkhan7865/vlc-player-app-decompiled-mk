package androidx.room.util;

import androidx.collection.ArrayMap;
import androidx.collection.LongSparseArray;
import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.room.RoomDatabase;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aR\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u00042\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u00062\u0006\u0010\u0007\u001a\u00020\b2\u001e\u0010\t\u001a\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\u0006\u0012\u0004\u0012\u00020\u00010\n\u001ar\u0010\u000b\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\u0004\b\u0001\u0010\u00042\"\u0010\u0005\u001a\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\fj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0004`\r2\u0006\u0010\u0007\u001a\u00020\b2.\u0010\t\u001a*\u0012 \u0012\u001e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00040\fj\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u0004`\r\u0012\u0004\u0012\u00020\u00010\n\u001a<\u0010\u000e\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00040\u000f2\u0006\u0010\u0007\u001a\u00020\b2\u0018\u0010\t\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00040\u000f\u0012\u0004\u0012\u00020\u00010\n¨\u0006\u0010"}, d2 = {"recursiveFetchArrayMap", "", "K", "", "V", "map", "Landroidx/collection/ArrayMap;", "isRelationCollection", "", "fetchBlock", "Lkotlin/Function1;", "recursiveFetchHashMap", "Ljava/util/HashMap;", "Lkotlin/collections/HashMap;", "recursiveFetchLongSparseArray", "Landroidx/collection/LongSparseArray;", "room-runtime_release"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RelationUtil.kt */
public final class RelationUtil {
    public static final <K, V> void recursiveFetchHashMap(HashMap<K, V> hashMap, boolean z, Function1<? super HashMap<K, V>, Unit> function1) {
        int i;
        Intrinsics.checkNotNullParameter(hashMap, "map");
        Intrinsics.checkNotNullParameter(function1, "fetchBlock");
        HashMap hashMap2 = new HashMap(RoomDatabase.MAX_BIND_PARAMETER_CNT);
        loop0:
        while (true) {
            i = 0;
            for (K next : hashMap.keySet()) {
                if (z) {
                    Intrinsics.checkNotNullExpressionValue(next, LeanbackPreferenceDialogFragment.ARG_KEY);
                    hashMap2.put(next, hashMap.get(next));
                } else {
                    Intrinsics.checkNotNullExpressionValue(next, LeanbackPreferenceDialogFragment.ARG_KEY);
                    hashMap2.put(next, (Object) null);
                }
                i++;
                if (i == 999) {
                    function1.invoke(hashMap2);
                    if (!z) {
                        hashMap.putAll(hashMap2);
                    }
                    hashMap2.clear();
                }
            }
            break loop0;
        }
        if (i > 0) {
            function1.invoke(hashMap2);
            if (!z) {
                hashMap.putAll(hashMap2);
            }
        }
    }

    public static final <V> void recursiveFetchLongSparseArray(LongSparseArray<V> longSparseArray, boolean z, Function1<? super LongSparseArray<V>, Unit> function1) {
        int i;
        Intrinsics.checkNotNullParameter(longSparseArray, "map");
        Intrinsics.checkNotNullParameter(function1, "fetchBlock");
        LongSparseArray longSparseArray2 = new LongSparseArray(RoomDatabase.MAX_BIND_PARAMETER_CNT);
        int size = longSparseArray.size();
        int i2 = 0;
        loop0:
        while (true) {
            i = 0;
            while (i2 < size) {
                if (z) {
                    longSparseArray2.put(longSparseArray.keyAt(i2), longSparseArray.valueAt(i2));
                } else {
                    longSparseArray2.put(longSparseArray.keyAt(i2), null);
                }
                i2++;
                i++;
                if (i == 999) {
                    function1.invoke(longSparseArray2);
                    if (!z) {
                        longSparseArray.putAll(longSparseArray2);
                    }
                    longSparseArray2.clear();
                }
            }
            break loop0;
        }
        if (i > 0) {
            function1.invoke(longSparseArray2);
            if (!z) {
                longSparseArray.putAll(longSparseArray2);
            }
        }
    }

    public static final <K, V> void recursiveFetchArrayMap(ArrayMap<K, V> arrayMap, boolean z, Function1<? super ArrayMap<K, V>, Unit> function1) {
        int i;
        Intrinsics.checkNotNullParameter(arrayMap, "map");
        Intrinsics.checkNotNullParameter(function1, "fetchBlock");
        ArrayMap arrayMap2 = new ArrayMap((int) RoomDatabase.MAX_BIND_PARAMETER_CNT);
        int size = arrayMap.size();
        int i2 = 0;
        loop0:
        while (true) {
            i = 0;
            while (i2 < size) {
                if (z) {
                    arrayMap2.put(arrayMap.keyAt(i2), arrayMap.valueAt(i2));
                } else {
                    arrayMap2.put(arrayMap.keyAt(i2), (Object) null);
                }
                i2++;
                i++;
                if (i == 999) {
                    function1.invoke(arrayMap2);
                    if (!z) {
                        arrayMap.putAll(arrayMap2);
                    }
                    arrayMap2.clear();
                }
            }
            break loop0;
        }
        if (i > 0) {
            function1.invoke(arrayMap2);
            if (!z) {
                arrayMap.putAll(arrayMap2);
            }
        }
    }
}
