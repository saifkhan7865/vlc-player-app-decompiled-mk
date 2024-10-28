package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0019\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0010\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0004J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\u0007H\u0004R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lkotlinx/serialization/json/internal/CharArrayPoolBase;", "", "()V", "arrays", "Lkotlin/collections/ArrayDeque;", "", "charsTotal", "", "releaseImpl", "", "array", "take", "size", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ArrayPools.kt */
public class CharArrayPoolBase {
    private final ArrayDeque<char[]> arrays = new ArrayDeque<>();
    private int charsTotal;

    /* access modifiers changed from: protected */
    public final char[] take(int i) {
        char[] removeLastOrNull;
        synchronized (this) {
            removeLastOrNull = this.arrays.removeLastOrNull();
            if (removeLastOrNull != null) {
                this.charsTotal -= removeLastOrNull.length;
            } else {
                removeLastOrNull = null;
            }
        }
        return removeLastOrNull == null ? new char[i] : removeLastOrNull;
    }

    /* access modifiers changed from: protected */
    public final void releaseImpl(char[] cArr) {
        Intrinsics.checkNotNullParameter(cArr, "array");
        synchronized (this) {
            if (this.charsTotal + cArr.length < ArrayPoolsKt.MAX_CHARS_IN_POOL) {
                this.charsTotal += cArr.length;
                this.arrays.addLast(cArr);
            }
            Unit unit = Unit.INSTANCE;
        }
    }
}
