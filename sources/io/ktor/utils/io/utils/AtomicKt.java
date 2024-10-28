package io.ktor.utils.io.utils;

import java.util.concurrent.atomic.AtomicLongFieldUpdater;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty1;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0001H\u0000\u001a/\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00070\u0006\"\n\b\u0000\u0010\u0007\u0018\u0001*\u00020\b2\u0012\u0010\t\u001a\u000e\u0012\u0004\u0012\u0002H\u0007\u0012\u0004\u0012\u00020\u000b0\nH\b¨\u0006\f"}, d2 = {"getIOIntProperty", "", "name", "", "default", "longUpdater", "Ljava/util/concurrent/atomic/AtomicLongFieldUpdater;", "Owner", "", "p", "Lkotlin/reflect/KProperty1;", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Atomic.kt */
public final class AtomicKt {
    public static final /* synthetic */ <Owner> AtomicLongFieldUpdater<Owner> longUpdater(KProperty1<Owner, Long> kProperty1) {
        Intrinsics.checkNotNullParameter(kProperty1, "p");
        Intrinsics.reifiedOperationMarker(4, "Owner");
        Class<Object> cls = Object.class;
        Class cls2 = cls;
        AtomicLongFieldUpdater<Owner> newUpdater = AtomicLongFieldUpdater.newUpdater(cls, kProperty1.getName());
        Intrinsics.checkNotNullExpressionValue(newUpdater, "newUpdater(Owner::class.java, p.name)");
        AtomicLongFieldUpdater atomicLongFieldUpdater = newUpdater;
        return newUpdater;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x001b, code lost:
        r2 = kotlin.text.StringsKt.toIntOrNull(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int getIOIntProperty(java.lang.String r2, int r3) {
        /*
            java.lang.String r0 = "io.ktor.utils.io."
            java.lang.String r1 = "name"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SecurityException -> 0x0018 }
            r1.<init>(r0)     // Catch:{ SecurityException -> 0x0018 }
            r1.append(r2)     // Catch:{ SecurityException -> 0x0018 }
            java.lang.String r2 = r1.toString()     // Catch:{ SecurityException -> 0x0018 }
            java.lang.String r2 = java.lang.System.getProperty(r2)     // Catch:{ SecurityException -> 0x0018 }
            goto L_0x0019
        L_0x0018:
            r2 = 0
        L_0x0019:
            if (r2 == 0) goto L_0x0025
            java.lang.Integer r2 = kotlin.text.StringsKt.toIntOrNull(r2)
            if (r2 == 0) goto L_0x0025
            int r3 = r2.intValue()
        L_0x0025:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.utils.AtomicKt.getIOIntProperty(java.lang.String, int):int");
    }
}
