package androidx.paging;

import androidx.paging.LoadState;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.concurrent.CopyOnWriteArrayList;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableStateFlow;
import kotlinx.coroutines.flow.StateFlow;
import kotlinx.coroutines.flow.StateFlowKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u001a\u0010\u000e\u001a\u00020\t2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bJ*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00112\u0006\u0010\u0014\u001a\u00020\u00112\b\u0010\u0015\u001a\u0004\u0018\u00010\u0011H\u0002J$\u0010\u0016\u001a\u00020\u00052\b\u0010\u0012\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0002J-\u0010\u001a\u001a\u00020\t2#\u0010\u0016\u001a\u001f\u0012\u0015\u0012\u0013\u0018\u00010\u0005¢\u0006\f\b\u001b\u0012\b\b\u001c\u0012\u0004\b\b(\u001d\u0012\u0004\u0012\u00020\u00050\bH\u0002J\u0018\u0010\u001e\u001a\u0004\u0018\u00010\u00112\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"J\u001a\u0010#\u001a\u00020\t2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\bJ\u0018\u0010$\u001a\u00020\t2\u0006\u0010%\u001a\u00020\u00182\b\u0010&\u001a\u0004\u0018\u00010\u0018J\u001e\u0010$\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010'\u001a\u00020\u0011R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0004¢\u0006\u0002\n\u0000R \u0010\u0006\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006("}, d2 = {"Landroidx/paging/MutableCombinedLoadStateCollection;", "", "()V", "_stateFlow", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroidx/paging/CombinedLoadStates;", "listeners", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlin/Function1;", "", "stateFlow", "Lkotlinx/coroutines/flow/StateFlow;", "getStateFlow", "()Lkotlinx/coroutines/flow/StateFlow;", "addListener", "listener", "computeHelperState", "Landroidx/paging/LoadState;", "previousState", "sourceRefreshState", "sourceState", "remoteState", "computeNewState", "newSource", "Landroidx/paging/LoadStates;", "newRemote", "dispatchNewState", "Lkotlin/ParameterName;", "name", "currState", "get", "type", "Landroidx/paging/LoadType;", "remote", "", "removeListener", "set", "sourceLoadStates", "remoteLoadStates", "state", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MutableCombinedLoadStateCollection.kt */
public final class MutableCombinedLoadStateCollection {
    private final MutableStateFlow<CombinedLoadStates> _stateFlow;
    private final CopyOnWriteArrayList<Function1<CombinedLoadStates, Unit>> listeners = new CopyOnWriteArrayList<>();
    private final StateFlow<CombinedLoadStates> stateFlow;

    public MutableCombinedLoadStateCollection() {
        MutableStateFlow<CombinedLoadStates> MutableStateFlow = StateFlowKt.MutableStateFlow(null);
        this._stateFlow = MutableStateFlow;
        this.stateFlow = FlowKt.asStateFlow(MutableStateFlow);
    }

    public final StateFlow<CombinedLoadStates> getStateFlow() {
        return this.stateFlow;
    }

    public final void set(LoadStates loadStates, LoadStates loadStates2) {
        Intrinsics.checkNotNullParameter(loadStates, "sourceLoadStates");
        dispatchNewState(new MutableCombinedLoadStateCollection$set$1(this, loadStates, loadStates2));
    }

    public final void set(LoadType loadType, boolean z, LoadState loadState) {
        Intrinsics.checkNotNullParameter(loadType, "type");
        Intrinsics.checkNotNullParameter(loadState, OAuth2RequestParameters.State);
        dispatchNewState(new MutableCombinedLoadStateCollection$set$2(z, loadType, loadState, this));
    }

    /* JADX WARNING: Removed duplicated region for block: B:10:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0022  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final androidx.paging.LoadState get(androidx.paging.LoadType r3, boolean r4) {
        /*
            r2 = this;
            java.lang.String r0 = "type"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r3, r0)
            kotlinx.coroutines.flow.MutableStateFlow<androidx.paging.CombinedLoadStates> r0 = r2._stateFlow
            java.lang.Object r0 = r0.getValue()
            androidx.paging.CombinedLoadStates r0 = (androidx.paging.CombinedLoadStates) r0
            r1 = 0
            if (r4 == 0) goto L_0x0018
            if (r0 == 0) goto L_0x001f
            androidx.paging.LoadStates r4 = r0.getMediator()
            goto L_0x0020
        L_0x0018:
            if (r0 == 0) goto L_0x001f
            androidx.paging.LoadStates r4 = r0.getSource()
            goto L_0x0020
        L_0x001f:
            r4 = r1
        L_0x0020:
            if (r4 == 0) goto L_0x0026
            androidx.paging.LoadState r1 = r4.get$paging_common(r3)
        L_0x0026:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.MutableCombinedLoadStateCollection.get(androidx.paging.LoadType, boolean):androidx.paging.LoadState");
    }

    public final void addListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.listeners.add(function1);
        CombinedLoadStates value = this._stateFlow.getValue();
        if (value != null) {
            function1.invoke(value);
        }
    }

    public final void removeListener(Function1<? super CombinedLoadStates, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "listener");
        this.listeners.remove(function1);
    }

    private final void dispatchNewState(Function1<? super CombinedLoadStates, CombinedLoadStates> function1) {
        CombinedLoadStates value;
        CombinedLoadStates invoke;
        MutableStateFlow<CombinedLoadStates> mutableStateFlow = this._stateFlow;
        do {
            value = mutableStateFlow.getValue();
            CombinedLoadStates combinedLoadStates = value;
            invoke = function1.invoke(combinedLoadStates);
            if (Intrinsics.areEqual((Object) combinedLoadStates, (Object) invoke)) {
                return;
            }
        } while (!mutableStateFlow.compareAndSet(value, invoke));
        if (invoke != null) {
            for (Function1 invoke2 : this.listeners) {
                invoke2.invoke(invoke);
            }
        }
    }

    /* access modifiers changed from: private */
    public final CombinedLoadStates computeNewState(CombinedLoadStates combinedLoadStates, LoadStates loadStates, LoadStates loadStates2) {
        LoadState loadState;
        LoadState loadState2;
        LoadState loadState3;
        if (combinedLoadStates == null || (loadState = combinedLoadStates.getRefresh()) == null) {
            loadState = LoadState.NotLoading.Companion.getIncomplete$paging_common();
        }
        LoadState loadState4 = null;
        LoadState computeHelperState = computeHelperState(loadState, loadStates.getRefresh(), loadStates.getRefresh(), loadStates2 != null ? loadStates2.getRefresh() : null);
        if (combinedLoadStates == null || (loadState2 = combinedLoadStates.getPrepend()) == null) {
            loadState2 = LoadState.NotLoading.Companion.getIncomplete$paging_common();
        }
        LoadState computeHelperState2 = computeHelperState(loadState2, loadStates.getRefresh(), loadStates.getPrepend(), loadStates2 != null ? loadStates2.getPrepend() : null);
        if (combinedLoadStates == null || (loadState3 = combinedLoadStates.getAppend()) == null) {
            loadState3 = LoadState.NotLoading.Companion.getIncomplete$paging_common();
        }
        LoadState refresh = loadStates.getRefresh();
        LoadState append = loadStates.getAppend();
        if (loadStates2 != null) {
            loadState4 = loadStates2.getAppend();
        }
        return new CombinedLoadStates(computeHelperState, computeHelperState2, computeHelperState(loadState3, refresh, append, loadState4), loadStates, loadStates2);
    }

    private final LoadState computeHelperState(LoadState loadState, LoadState loadState2, LoadState loadState3, LoadState loadState4) {
        if (loadState4 == null) {
            return loadState3;
        }
        return (!(loadState instanceof LoadState.Loading) || ((loadState2 instanceof LoadState.NotLoading) && (loadState4 instanceof LoadState.NotLoading)) || (loadState4 instanceof LoadState.Error)) ? loadState4 : loadState;
    }
}
