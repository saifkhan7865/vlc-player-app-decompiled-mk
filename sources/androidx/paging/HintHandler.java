package androidx.paging;

import androidx.paging.ViewportHint;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001:\u0002\u0012\u0013B\u0005¢\u0006\u0002\u0010\u0002J\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0006\u0010\u000b\u001a\u00020\fJ\u000e\u0010\u0011\u001a\u00020\n2\u0006\u0010\r\u001a\u00020\u000eR\u0013\u0010\u0003\u001a\u0004\u0018\u00010\u00048F¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00060\bR\u00020\u0000X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Landroidx/paging/HintHandler;", "", "()V", "lastAccessHint", "Landroidx/paging/ViewportHint$Access;", "getLastAccessHint", "()Landroidx/paging/ViewportHint$Access;", "state", "Landroidx/paging/HintHandler$State;", "forceSetHint", "", "loadType", "Landroidx/paging/LoadType;", "viewportHint", "Landroidx/paging/ViewportHint;", "hintFor", "Lkotlinx/coroutines/flow/Flow;", "processHint", "HintFlow", "State", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HintHandler.kt */
public final class HintHandler {
    private final State state = new State();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HintHandler.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        static {
            /*
                androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.HintHandler.WhenMappings.<clinit>():void");
        }
    }

    public final ViewportHint.Access getLastAccessHint() {
        return this.state.getLastAccessHint();
    }

    public final Flow<ViewportHint> hintFor(LoadType loadType) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        int i = WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()];
        if (i == 1) {
            return this.state.getPrependFlow();
        }
        if (i == 2) {
            return this.state.getAppendFlow();
        }
        throw new IllegalArgumentException("invalid load type for hints");
    }

    public final void forceSetHint(LoadType loadType, ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(loadType, "loadType");
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        if (loadType == LoadType.PREPEND || loadType == LoadType.APPEND) {
            this.state.modify((ViewportHint.Access) null, new HintHandler$forceSetHint$2(loadType, viewportHint));
            return;
        }
        throw new IllegalArgumentException(("invalid load type for reset: " + loadType).toString());
    }

    public final void processHint(ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        this.state.modify(viewportHint instanceof ViewportHint.Access ? (ViewportHint.Access) viewportHint : null, new HintHandler$processHint$1(viewportHint));
    }

    @Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002JP\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\f2>\u0010\u0018\u001a:\u0012\u0017\u0012\u00150\u0004R\u00020\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0012\u0012\u0017\u0012\u00150\u0004R\u00020\u0005¢\u0006\f\b\u001a\u0012\b\b\u001b\u0012\u0004\b\b(\u0003\u0012\u0004\u0012\u00020\u00160\u0019R\u0012\u0010\u0003\u001a\u00060\u0004R\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u00078F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\"\u0010\r\u001a\u0004\u0018\u00010\f2\b\u0010\u000b\u001a\u0004\u0018\u00010\f@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u0012\u0010\u0012\u001a\u00060\u0004R\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u00078F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\n¨\u0006\u001c"}, d2 = {"Landroidx/paging/HintHandler$State;", "", "(Landroidx/paging/HintHandler;)V", "append", "Landroidx/paging/HintHandler$HintFlow;", "Landroidx/paging/HintHandler;", "appendFlow", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/ViewportHint;", "getAppendFlow", "()Lkotlinx/coroutines/flow/Flow;", "<set-?>", "Landroidx/paging/ViewportHint$Access;", "lastAccessHint", "getLastAccessHint", "()Landroidx/paging/ViewportHint$Access;", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "prepend", "prependFlow", "getPrependFlow", "modify", "", "accessHint", "block", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HintHandler.kt */
    private final class State {
        private final HintFlow append;
        private ViewportHint.Access lastAccessHint;
        private final ReentrantLock lock = new ReentrantLock();
        private final HintFlow prepend;

        public State() {
            this.prepend = new HintFlow();
            this.append = new HintFlow();
        }

        public final ViewportHint.Access getLastAccessHint() {
            return this.lastAccessHint;
        }

        public final Flow<ViewportHint> getPrependFlow() {
            return this.prepend.getFlow();
        }

        public final Flow<ViewportHint> getAppendFlow() {
            return this.append.getFlow();
        }

        public final void modify(ViewportHint.Access access, Function2<? super HintFlow, ? super HintFlow, Unit> function2) {
            Intrinsics.checkNotNullParameter(function2, "block");
            Lock lock2 = this.lock;
            lock2.lock();
            if (access != null) {
                try {
                    this.lastAccessHint = access;
                } catch (Throwable th) {
                    lock2.unlock();
                    throw th;
                }
            }
            function2.invoke(this.prepend, this.append);
            Unit unit = Unit.INSTANCE;
            lock2.unlock();
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR(\u0010\n\u001a\u0004\u0018\u00010\u00052\b\u0010\n\u001a\u0004\u0018\u00010\u0005@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Landroidx/paging/HintHandler$HintFlow;", "", "(Landroidx/paging/HintHandler;)V", "_flow", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Landroidx/paging/ViewportHint;", "flow", "Lkotlinx/coroutines/flow/Flow;", "getFlow", "()Lkotlinx/coroutines/flow/Flow;", "value", "getValue", "()Landroidx/paging/ViewportHint;", "setValue", "(Landroidx/paging/ViewportHint;)V", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HintHandler.kt */
    private final class HintFlow {
        private final MutableSharedFlow<ViewportHint> _flow = SharedFlowKt.MutableSharedFlow$default(1, 0, BufferOverflow.DROP_OLDEST, 2, (Object) null);
        private ViewportHint value;

        public HintFlow() {
        }

        public final ViewportHint getValue() {
            return this.value;
        }

        public final void setValue(ViewportHint viewportHint) {
            this.value = viewportHint;
            if (viewportHint != null) {
                this._flow.tryEmit(viewportHint);
            }
        }

        public final Flow<ViewportHint> getFlow() {
            return this._flow;
        }
    }
}
