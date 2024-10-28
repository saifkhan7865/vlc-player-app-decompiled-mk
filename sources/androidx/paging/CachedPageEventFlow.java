package androidx.paging;

import androidx.paging.PageEvent;
import io.ktor.server.auth.OAuth2RequestParameters;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.collections.IndexedValue;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;
import kotlinx.coroutines.flow.MutableSharedFlow;
import kotlinx.coroutines.flow.SharedFlow;
import kotlinx.coroutines.flow.SharedFlowKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B!\u0012\u0012\u0010\u0003\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u0006\u0010\u0015\u001a\u00020\u0016J\u0015\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0018H\u0000¢\u0006\u0002\b\u0019R\u001d\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\"\u0010\u000e\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0018\u00010\u00100\u000fX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00028\u00000\u0012X\u0004¢\u0006\u0002\n\u0000R\"\u0010\u0013\u001a\u0016\u0012\u0012\u0012\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u0005\u0018\u00010\u00100\u0014X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Landroidx/paging/CachedPageEventFlow;", "T", "", "src", "Lkotlinx/coroutines/flow/Flow;", "Landroidx/paging/PageEvent;", "scope", "Lkotlinx/coroutines/CoroutineScope;", "(Lkotlinx/coroutines/flow/Flow;Lkotlinx/coroutines/CoroutineScope;)V", "downstreamFlow", "getDownstreamFlow", "()Lkotlinx/coroutines/flow/Flow;", "job", "Lkotlinx/coroutines/Job;", "mutableSharedSrc", "Lkotlinx/coroutines/flow/MutableSharedFlow;", "Lkotlin/collections/IndexedValue;", "pageController", "Landroidx/paging/FlattenedPageController;", "sharedForDownstream", "Lkotlinx/coroutines/flow/SharedFlow;", "close", "", "getCachedEvent", "Landroidx/paging/PageEvent$Insert;", "getCachedEvent$paging_common", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CachedPageEventFlow.kt */
public final class CachedPageEventFlow<T> {
    private final Flow<PageEvent<T>> downstreamFlow;
    /* access modifiers changed from: private */
    public final Job job;
    /* access modifiers changed from: private */
    public final MutableSharedFlow<IndexedValue<PageEvent<T>>> mutableSharedSrc;
    /* access modifiers changed from: private */
    public final FlattenedPageController<T> pageController = new FlattenedPageController<>();
    /* access modifiers changed from: private */
    public final SharedFlow<IndexedValue<PageEvent<T>>> sharedForDownstream;

    public CachedPageEventFlow(Flow<? extends PageEvent<T>> flow, CoroutineScope coroutineScope) {
        Intrinsics.checkNotNullParameter(flow, "src");
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        MutableSharedFlow<IndexedValue<PageEvent<T>>> MutableSharedFlow = SharedFlowKt.MutableSharedFlow(1, Integer.MAX_VALUE, BufferOverflow.SUSPEND);
        this.mutableSharedSrc = MutableSharedFlow;
        this.sharedForDownstream = FlowKt.onSubscription(MutableSharedFlow, new CachedPageEventFlow$sharedForDownstream$1(this, (Continuation<? super CachedPageEventFlow$sharedForDownstream$1>) null));
        Job launch$default = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, CoroutineStart.LAZY, new CachedPageEventFlow$job$1(flow, this, (Continuation<? super CachedPageEventFlow$job$1>) null), 1, (Object) null);
        launch$default.invokeOnCompletion(new CachedPageEventFlow$job$2$1(this));
        this.job = launch$default;
        this.downstreamFlow = FlowKt.flow(new CachedPageEventFlow$downstreamFlow$1(this, (Continuation<? super CachedPageEventFlow$downstreamFlow$1>) null));
    }

    public final void close() {
        Job.DefaultImpls.cancel$default(this.job, (CancellationException) null, 1, (Object) null);
    }

    public final Flow<PageEvent<T>> getDownstreamFlow() {
        return this.downstreamFlow;
    }

    public final PageEvent.Insert<T> getCachedEvent$paging_common() {
        return this.pageController.getCachedEvent();
    }
}
