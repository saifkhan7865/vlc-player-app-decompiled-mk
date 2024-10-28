package io.ktor.network.selector;

import androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KMutableProperty1;
import kotlinx.coroutines.CancellableContinuation;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005¢\u0006\u0002\u0010\u0002J\u001c\u0010\r\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004J3\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u00132\u001d\u0010\u0014\u001a\u0019\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u00050\u0015¢\u0006\u0002\b\u0016H\bø\u0001\u0000J1\u0010\u0011\u001a\u00020\u00052#\u0010\u0014\u001a\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00050\u0017¢\u0006\u0002\b\u0016H\bø\u0001\u0000J\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u000e\u001a\u00020\u000fJ\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u00042\u0006\u0010\u0019\u001a\u00020\u0013J\b\u0010\u001a\u001a\u00020\u001bH\u0016R\u001c\u0010\u0003\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\u0006\u0010\u0002R\u001c\u0010\u0007\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\b\u0010\u0002R\u001c\u0010\t\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\n\u0010\u0002R\u001c\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\u0005\u0018\u00010\u0004X\u000e¢\u0006\b\n\u0000\u0012\u0004\b\f\u0010\u0002\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001d"}, d2 = {"Lio/ktor/network/selector/InterestSuspensionsMap;", "", "()V", "acceptHandlerReference", "Lkotlinx/coroutines/CancellableContinuation;", "", "getAcceptHandlerReference$annotations", "connectHandlerReference", "getConnectHandlerReference$annotations", "readHandlerReference", "getReadHandlerReference$annotations", "writeHandlerReference", "getWriteHandlerReference$annotations", "addSuspension", "interest", "Lio/ktor/network/selector/SelectInterest;", "continuation", "invokeForEachPresent", "readyOps", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "Lkotlin/Function2;", "removeSuspension", "interestOrdinal", "toString", "", "Companion", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InterestSuspensionsMap.kt */
public final class InterestSuspensionsMap {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final AtomicReferenceFieldUpdater<InterestSuspensionsMap, CancellableContinuation<Unit>>[] updaters;
    /* access modifiers changed from: private */
    public volatile CancellableContinuation<? super Unit> acceptHandlerReference;
    /* access modifiers changed from: private */
    public volatile CancellableContinuation<? super Unit> connectHandlerReference;
    /* access modifiers changed from: private */
    public volatile CancellableContinuation<? super Unit> readHandlerReference;
    /* access modifiers changed from: private */
    public volatile CancellableContinuation<? super Unit> writeHandlerReference;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: InterestSuspensionsMap.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                io.ktor.network.selector.SelectInterest[] r0 = io.ktor.network.selector.SelectInterest.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.network.selector.SelectInterest r1 = io.ktor.network.selector.SelectInterest.READ     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.network.selector.SelectInterest r1 = io.ktor.network.selector.SelectInterest.WRITE     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.network.selector.SelectInterest r1 = io.ktor.network.selector.SelectInterest.ACCEPT     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                io.ktor.network.selector.SelectInterest r1 = io.ktor.network.selector.SelectInterest.CONNECT     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.selector.InterestSuspensionsMap.WhenMappings.<clinit>():void");
        }
    }

    private static /* synthetic */ void getAcceptHandlerReference$annotations() {
    }

    private static /* synthetic */ void getConnectHandlerReference$annotations() {
    }

    private static /* synthetic */ void getReadHandlerReference$annotations() {
    }

    private static /* synthetic */ void getWriteHandlerReference$annotations() {
    }

    public final void addSuspension(SelectInterest selectInterest, CancellableContinuation<? super Unit> cancellableContinuation) {
        Intrinsics.checkNotNullParameter(selectInterest, "interest");
        Intrinsics.checkNotNullParameter(cancellableContinuation, "continuation");
        if (!AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(Companion.updater(selectInterest), this, (Object) null, cancellableContinuation)) {
            throw new IllegalStateException("Handler for " + selectInterest.name() + " is already registered");
        }
    }

    public final void invokeForEachPresent(int i, Function1<? super CancellableContinuation<? super Unit>, Unit> function1) {
        CancellableContinuation<Unit> removeSuspension;
        Intrinsics.checkNotNullParameter(function1, "block");
        int[] flags = SelectInterest.Companion.getFlags();
        int length = flags.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (!((flags[i2] & i) == 0 || (removeSuspension = removeSuspension(i2)) == null)) {
                function1.invoke(removeSuspension);
            }
        }
    }

    public final void invokeForEachPresent(Function2<? super CancellableContinuation<? super Unit>, ? super SelectInterest, Unit> function2) {
        Intrinsics.checkNotNullParameter(function2, "block");
        for (SelectInterest selectInterest : SelectInterest.Companion.getAllInterests()) {
            CancellableContinuation<Unit> removeSuspension = removeSuspension(selectInterest);
            if (removeSuspension != null) {
                function2.invoke(removeSuspension, selectInterest);
            }
        }
    }

    public final CancellableContinuation<Unit> removeSuspension(SelectInterest selectInterest) {
        Intrinsics.checkNotNullParameter(selectInterest, "interest");
        return (CancellableContinuation) Companion.updater(selectInterest).getAndSet(this, (Object) null);
    }

    public final CancellableContinuation<Unit> removeSuspension(int i) {
        return updaters[i].getAndSet(this, (Object) null);
    }

    public String toString() {
        return "R " + this.readHandlerReference + " W " + this.writeHandlerReference + " C " + this.connectHandlerReference + " A " + this.acceptHandlerReference;
    }

    @Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J$\u0010\u000b\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00052\u0006\u0010\f\u001a\u00020\rH\u0002R0\u0010\u0003\u001a\u001c\u0012\u0018\u0012\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u00070\u00050\u0004X\u0004¢\u0006\n\n\u0002\u0010\n\u0012\u0004\b\t\u0010\u0002¨\u0006\u000e"}, d2 = {"Lio/ktor/network/selector/InterestSuspensionsMap$Companion;", "", "()V", "updaters", "", "Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "Lio/ktor/network/selector/InterestSuspensionsMap;", "Lkotlinx/coroutines/CancellableContinuation;", "", "getUpdaters$annotations", "[Ljava/util/concurrent/atomic/AtomicReferenceFieldUpdater;", "updater", "interest", "Lio/ktor/network/selector/SelectInterest;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: InterestSuspensionsMap.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private static /* synthetic */ void getUpdaters$annotations() {
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final AtomicReferenceFieldUpdater<InterestSuspensionsMap, CancellableContinuation<Unit>> updater(SelectInterest selectInterest) {
            return InterestSuspensionsMap.updaters[selectInterest.ordinal()];
        }
    }

    static {
        KMutableProperty1 kMutableProperty1;
        SelectInterest[] allInterests = SelectInterest.Companion.getAllInterests();
        Collection arrayList = new ArrayList(allInterests.length);
        for (SelectInterest ordinal : allInterests) {
            int i = WhenMappings.$EnumSwitchMapping$0[ordinal.ordinal()];
            if (i == 1) {
                kMutableProperty1 = InterestSuspensionsMap$Companion$updaters$1$property$1.INSTANCE;
            } else if (i == 2) {
                kMutableProperty1 = InterestSuspensionsMap$Companion$updaters$1$property$2.INSTANCE;
            } else if (i == 3) {
                kMutableProperty1 = InterestSuspensionsMap$Companion$updaters$1$property$3.INSTANCE;
            } else if (i == 4) {
                kMutableProperty1 = InterestSuspensionsMap$Companion$updaters$1$property$4.INSTANCE;
            } else {
                throw new NoWhenBranchMatchedException();
            }
            AtomicReferenceFieldUpdater<U, W> newUpdater = AtomicReferenceFieldUpdater.newUpdater(InterestSuspensionsMap.class, CancellableContinuation.class, kMutableProperty1.getName());
            Intrinsics.checkNotNull(newUpdater, "null cannot be cast to non-null type java.util.concurrent.atomic.AtomicReferenceFieldUpdater<io.ktor.network.selector.InterestSuspensionsMap, kotlinx.coroutines.CancellableContinuation<kotlin.Unit>?>");
            arrayList.add(newUpdater);
        }
        updaters = (AtomicReferenceFieldUpdater[]) ((List) arrayList).toArray(new AtomicReferenceFieldUpdater[0]);
    }
}
