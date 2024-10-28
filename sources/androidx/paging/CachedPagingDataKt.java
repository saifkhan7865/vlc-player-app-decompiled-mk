package androidx.paging;

import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.SharingStarted;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a6\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001aB\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bH\u0000¨\u0006\t"}, d2 = {"cachedIn", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PagingData;", "T", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "tracker", "Landroidx/paging/ActiveFlowTracker;", "paging-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CachedPagingData.kt */
public final class CachedPagingDataKt {
    public static final <T> Flow<PagingData<T>> cachedIn(Flow<PagingData<T>> flow, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        return cachedIn(flow, coroutineScope, (ActiveFlowTracker) null);
    }

    public static /* synthetic */ Flow cachedIn$default(Flow flow, CoroutineScope coroutineScope, ActiveFlowTracker activeFlowTracker, int i, Object obj) {
        if ((i & 2) != 0) {
            activeFlowTracker = null;
        }
        return cachedIn(flow, coroutineScope, activeFlowTracker);
    }

    public static final <T> Flow<PagingData<T>> cachedIn(Flow<PagingData<T>> flow, CoroutineScope coroutineScope, ActiveFlowTracker activeFlowTracker) {
        Intrinsics.checkNotNullParameter(flow, "<this>");
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        return FlowKt.shareIn(FlowKt.onCompletion(FlowKt.onStart(new CachedPagingDataKt$cachedIn$$inlined$map$1(FlowExtKt.simpleRunningReduce(FlowExtKt.simpleTransformLatest(flow, new CachedPagingDataKt$cachedIn$$inlined$simpleMapLatest$1((Continuation) null, coroutineScope, activeFlowTracker)), new CachedPagingDataKt$cachedIn$2((Continuation<? super CachedPagingDataKt$cachedIn$2>) null))), new CachedPagingDataKt$cachedIn$4(activeFlowTracker, (Continuation<? super CachedPagingDataKt$cachedIn$4>) null)), new CachedPagingDataKt$cachedIn$5(activeFlowTracker, (Continuation<? super CachedPagingDataKt$cachedIn$5>) null)), coroutineScope, SharingStarted.Companion.getLazily(), 1);
    }
}
