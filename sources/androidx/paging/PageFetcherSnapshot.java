package androidx.paging;

import androidx.paging.LoadState;
import androidx.paging.PageEvent;
import androidx.paging.PageFetcherSnapshotState;
import androidx.paging.PagingSource;
import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicBoolean;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;
import kotlinx.coroutines.flow.Flow;
import kotlinx.coroutines.flow.FlowKt;

@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u00022\u00020\u0002By\u0012\b\u0010\u0004\u001a\u0004\u0018\u00018\u0000\u0012\u0012\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0016\b\u0002\u0010\f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\r\u0012\u0016\b\u0002\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000f\u0012\u000e\b\u0002\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011¢\u0006\u0002\u0010\u0012J\u000e\u0010(\u001a\u00020\u000b2\u0006\u0010)\u001a\u00020*J\u0006\u0010+\u001a\u00020\u000bJ\u001d\u0010,\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH@ø\u0001\u0000¢\u0006\u0002\u0010-J\u0011\u0010.\u001a\u00020\u000bH@ø\u0001\u0000¢\u0006\u0002\u0010-J!\u0010/\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012\u0006\u00102\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J%\u00105\u001a\b\u0012\u0004\u0012\u00028\u0000062\u0006\u00100\u001a\u0002012\b\u00107\u001a\u0004\u0018\u00018\u0000H\u0002¢\u0006\u0002\u00108J5\u00109\u001a\u00020:2\u0006\u00100\u001a\u0002012\b\u0010;\u001a\u0004\u0018\u00018\u00002\u0014\u0010<\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010=H\u0002¢\u0006\u0002\u0010>J\b\u0010?\u001a\u00020\u000bH\u0002J#\u0010@\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012\b\u0010)\u001a\u0004\u0018\u00010*H@ø\u0001\u0000¢\u0006\u0002\u0010AJ#\u0010B\u001a\u00020\u000b*\b\u0012\u0004\u0012\u00020C0\n2\u0006\u00100\u001a\u000201H@ø\u0001\u0000¢\u0006\u0002\u0010DJ7\u0010E\u001a\u0004\u0018\u00018\u0000*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010F2\u0006\u00100\u001a\u0002012\u0006\u0010G\u001a\u00020C2\u0006\u0010H\u001a\u00020CH\u0002¢\u0006\u0002\u0010IJ1\u0010J\u001a\u00020\u000b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010F2\u0006\u00100\u001a\u0002012\u0006\u0010K\u001a\u00020LH@ø\u0001\u0000¢\u0006\u0002\u0010MJ)\u0010N\u001a\u00020\u000b*\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010F2\u0006\u00100\u001a\u000201H@ø\u0001\u0000¢\u0006\u0002\u0010OJ\f\u0010P\u001a\u00020\u000b*\u00020QH\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u0004\u001a\u0004\u0018\u00018\u0000X\u0004¢\u0006\n\n\u0002\u0010\u0017\u001a\u0004\b\u0015\u0010\u0016R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0011X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001a0\u0019X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u001eX\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\u001a0\n¢\u0006\b\n\u0000\u001a\u0004\b \u0010!R \u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000fX\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\f\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\r¢\u0006\b\n\u0000\u001a\u0004\b$\u0010%R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u000b0\nX\u0004¢\u0006\u0002\n\u0000R\u001a\u0010&\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010'X\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006R"}, d2 = {"Landroidx/paging/PageFetcherSnapshot;", "Key", "", "Value", "initialKey", "pagingSource", "Landroidx/paging/PagingSource;", "config", "Landroidx/paging/PagingConfig;", "retryFlow", "Lkotlinx/coroutines/flow/Flow;", "", "remoteMediatorConnection", "Landroidx/paging/RemoteMediatorConnection;", "previousPagingState", "Landroidx/paging/PagingState;", "jumpCallback", "Lkotlin/Function0;", "(Ljava/lang/Object;Landroidx/paging/PagingSource;Landroidx/paging/PagingConfig;Lkotlinx/coroutines/flow/Flow;Landroidx/paging/RemoteMediatorConnection;Landroidx/paging/PagingState;Lkotlin/jvm/functions/Function0;)V", "hintHandler", "Landroidx/paging/HintHandler;", "getInitialKey$paging_common", "()Ljava/lang/Object;", "Ljava/lang/Object;", "pageEventCh", "Lkotlinx/coroutines/channels/Channel;", "Landroidx/paging/PageEvent;", "pageEventChCollected", "Ljava/util/concurrent/atomic/AtomicBoolean;", "pageEventChannelFlowJob", "Lkotlinx/coroutines/CompletableJob;", "pageEventFlow", "getPageEventFlow", "()Lkotlinx/coroutines/flow/Flow;", "getPagingSource$paging_common", "()Landroidx/paging/PagingSource;", "getRemoteMediatorConnection", "()Landroidx/paging/RemoteMediatorConnection;", "stateHolder", "Landroidx/paging/PageFetcherSnapshotState$Holder;", "accessHint", "viewportHint", "Landroidx/paging/ViewportHint;", "close", "currentPagingState", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "doInitialLoad", "doLoad", "loadType", "Landroidx/paging/LoadType;", "generationalHint", "Landroidx/paging/GenerationalViewportHint;", "(Landroidx/paging/LoadType;Landroidx/paging/GenerationalViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadParams", "Landroidx/paging/PagingSource$LoadParams;", "key", "(Landroidx/paging/LoadType;Ljava/lang/Object;)Landroidx/paging/PagingSource$LoadParams;", "loadResultLog", "", "loadKey", "result", "Landroidx/paging/PagingSource$LoadResult;", "(Landroidx/paging/LoadType;Ljava/lang/Object;Landroidx/paging/PagingSource$LoadResult;)Ljava/lang/String;", "onInvalidLoad", "retryLoadError", "(Landroidx/paging/LoadType;Landroidx/paging/ViewportHint;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "collectAsGenerationalViewportHints", "", "(Lkotlinx/coroutines/flow/Flow;Landroidx/paging/LoadType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "nextLoadKeyOrNull", "Landroidx/paging/PageFetcherSnapshotState;", "generationId", "presentedItemsBeyondAnchor", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;II)Ljava/lang/Object;", "setError", "error", "Landroidx/paging/LoadState$Error;", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;Landroidx/paging/LoadState$Error;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setLoading", "(Landroidx/paging/PageFetcherSnapshotState;Landroidx/paging/LoadType;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "startConsumingHints", "Lkotlinx/coroutines/CoroutineScope;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: PageFetcherSnapshot.kt */
public final class PageFetcherSnapshot<Key, Value> {
    /* access modifiers changed from: private */
    public final PagingConfig config;
    /* access modifiers changed from: private */
    public final HintHandler hintHandler;
    private final Key initialKey;
    /* access modifiers changed from: private */
    public final Function0<Unit> jumpCallback;
    /* access modifiers changed from: private */
    public final Channel<PageEvent<Value>> pageEventCh;
    /* access modifiers changed from: private */
    public final AtomicBoolean pageEventChCollected;
    private final CompletableJob pageEventChannelFlowJob;
    private final Flow<PageEvent<Value>> pageEventFlow;
    private final PagingSource<Key, Value> pagingSource;
    /* access modifiers changed from: private */
    public final PagingState<Key, Value> previousPagingState;
    private final RemoteMediatorConnection<Key, Value> remoteMediatorConnection;
    /* access modifiers changed from: private */
    public final Flow<Unit> retryFlow;
    /* access modifiers changed from: private */
    public final PageFetcherSnapshotState.Holder<Key, Value> stateHolder;

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: PageFetcherSnapshot.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(9:0|1|2|3|4|5|6|7|9) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        static {
            /*
                androidx.paging.LoadType[] r0 = androidx.paging.LoadType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.PREPEND     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.WhenMappings.<clinit>():void");
        }
    }

    public PageFetcherSnapshot(Key key, PagingSource<Key, Value> pagingSource2, PagingConfig pagingConfig, Flow<Unit> flow, RemoteMediatorConnection<Key, Value> remoteMediatorConnection2, PagingState<Key, Value> pagingState, Function0<Unit> function0) {
        Intrinsics.checkNotNullParameter(pagingSource2, "pagingSource");
        Intrinsics.checkNotNullParameter(pagingConfig, "config");
        Intrinsics.checkNotNullParameter(flow, "retryFlow");
        Intrinsics.checkNotNullParameter(function0, "jumpCallback");
        this.initialKey = key;
        this.pagingSource = pagingSource2;
        this.config = pagingConfig;
        this.retryFlow = flow;
        this.remoteMediatorConnection = remoteMediatorConnection2;
        this.previousPagingState = pagingState;
        this.jumpCallback = function0;
        if (pagingConfig.jumpThreshold == Integer.MIN_VALUE || pagingSource2.getJumpingSupported()) {
            this.hintHandler = new HintHandler();
            this.pageEventChCollected = new AtomicBoolean(false);
            this.pageEventCh = ChannelKt.Channel$default(-2, (BufferOverflow) null, (Function1) null, 6, (Object) null);
            this.stateHolder = new PageFetcherSnapshotState.Holder<>(pagingConfig);
            CompletableJob Job$default = JobKt.Job$default((Job) null, 1, (Object) null);
            this.pageEventChannelFlowJob = Job$default;
            this.pageEventFlow = FlowKt.onStart(CancelableChannelFlowKt.cancelableChannelFlow(Job$default, new PageFetcherSnapshot$pageEventFlow$1(this, (Continuation<? super PageFetcherSnapshot$pageEventFlow$1>) null)), new PageFetcherSnapshot$pageEventFlow$2(this, (Continuation<? super PageFetcherSnapshot$pageEventFlow$2>) null));
            return;
        }
        throw new IllegalArgumentException("PagingConfig.jumpThreshold was set, but the associated PagingSource has not marked support for jumps by overriding PagingSource.jumpingSupported to true.".toString());
    }

    public final Key getInitialKey$paging_common() {
        return this.initialKey;
    }

    public final PagingSource<Key, Value> getPagingSource$paging_common() {
        return this.pagingSource;
    }

    public final RemoteMediatorConnection<Key, Value> getRemoteMediatorConnection() {
        return this.remoteMediatorConnection;
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public /* synthetic */ PageFetcherSnapshot(java.lang.Object r11, androidx.paging.PagingSource r12, androidx.paging.PagingConfig r13, kotlinx.coroutines.flow.Flow r14, androidx.paging.RemoteMediatorConnection r15, androidx.paging.PagingState r16, kotlin.jvm.functions.Function0 r17, int r18, kotlin.jvm.internal.DefaultConstructorMarker r19) {
        /*
            r10 = this;
            r0 = r18 & 16
            r1 = 0
            if (r0 == 0) goto L_0x0007
            r7 = r1
            goto L_0x0008
        L_0x0007:
            r7 = r15
        L_0x0008:
            r0 = r18 & 32
            if (r0 == 0) goto L_0x000e
            r8 = r1
            goto L_0x0010
        L_0x000e:
            r8 = r16
        L_0x0010:
            r0 = r18 & 64
            if (r0 == 0) goto L_0x001a
            androidx.paging.PageFetcherSnapshot$1 r0 = androidx.paging.PageFetcherSnapshot.AnonymousClass1.INSTANCE
            kotlin.jvm.functions.Function0 r0 = (kotlin.jvm.functions.Function0) r0
            r9 = r0
            goto L_0x001c
        L_0x001a:
            r9 = r17
        L_0x001c:
            r2 = r10
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r14
            r2.<init>(r3, r4, r5, r6, r7, r8, r9)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.<init>(java.lang.Object, androidx.paging.PagingSource, androidx.paging.PagingConfig, kotlinx.coroutines.flow.Flow, androidx.paging.RemoteMediatorConnection, androidx.paging.PagingState, kotlin.jvm.functions.Function0, int, kotlin.jvm.internal.DefaultConstructorMarker):void");
    }

    public final Flow<PageEvent<Value>> getPageEventFlow() {
        return this.pageEventFlow;
    }

    /* access modifiers changed from: private */
    public final Object retryLoadError(LoadType loadType, ViewportHint viewportHint, Continuation<? super Unit> continuation) {
        if (WhenMappings.$EnumSwitchMapping$0[loadType.ordinal()] == 1) {
            Object doInitialLoad = doInitialLoad(continuation);
            return doInitialLoad == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? doInitialLoad : Unit.INSTANCE;
        } else if (viewportHint != null) {
            this.hintHandler.forceSetHint(loadType, viewportHint);
            return Unit.INSTANCE;
        } else {
            throw new IllegalStateException("Cannot retry APPEND / PREPEND load on PagingSource without ViewportHint".toString());
        }
    }

    public final void accessHint(ViewportHint viewportHint) {
        Intrinsics.checkNotNullParameter(viewportHint, "viewportHint");
        this.hintHandler.processHint(viewportHint);
    }

    public final void close() {
        Job.DefaultImpls.cancel$default((Job) this.pageEventChannelFlowJob, (CancellationException) null, 1, (Object) null);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object currentPagingState(kotlin.coroutines.Continuation<? super androidx.paging.PagingState<Key, Value>> r6) {
        /*
            r5 = this;
            boolean r0 = r6 instanceof androidx.paging.PageFetcherSnapshot$currentPagingState$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            androidx.paging.PageFetcherSnapshot$currentPagingState$1 r0 = (androidx.paging.PageFetcherSnapshot$currentPagingState$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$currentPagingState$1 r0 = new androidx.paging.PageFetcherSnapshot$currentPagingState$1
            r0.<init>(r5, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r4) goto L_0x0037
            java.lang.Object r1 = r0.L$2
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot r0 = (androidx.paging.PageFetcherSnapshot) r0
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0059
        L_0x0037:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r6)
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r5.stateHolder
            kotlinx.coroutines.sync.Mutex r6 = r2.lock
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r6
            r0.label = r4
            java.lang.Object r0 = r6.lock(r3, r0)
            if (r0 != r1) goto L_0x0057
            return r1
        L_0x0057:
            r0 = r5
            r1 = r6
        L_0x0059:
            androidx.paging.PageFetcherSnapshotState r6 = r2.state     // Catch:{ all -> 0x006b }
            androidx.paging.HintHandler r0 = r0.hintHandler     // Catch:{ all -> 0x006b }
            androidx.paging.ViewportHint$Access r0 = r0.getLastAccessHint()     // Catch:{ all -> 0x006b }
            androidx.paging.PagingState r6 = r6.currentPagingState$paging_common(r0)     // Catch:{ all -> 0x006b }
            r1.unlock(r3)
            return r6
        L_0x006b:
            r6 = move-exception
            r1.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.currentPagingState(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final void startConsumingHints(CoroutineScope coroutineScope) {
        if (this.config.jumpThreshold != Integer.MIN_VALUE) {
            Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, (CoroutineStart) null, new PageFetcherSnapshot$startConsumingHints$1(this, (Continuation<? super PageFetcherSnapshot$startConsumingHints$1>) null), 3, (Object) null);
        }
        CoroutineScope coroutineScope2 = coroutineScope;
        Job unused2 = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, (CoroutineContext) null, (CoroutineStart) null, new PageFetcherSnapshot$startConsumingHints$2(this, (Continuation<? super PageFetcherSnapshot$startConsumingHints$2>) null), 3, (Object) null);
        Job unused3 = BuildersKt__Builders_commonKt.launch$default(coroutineScope2, (CoroutineContext) null, (CoroutineStart) null, new PageFetcherSnapshot$startConsumingHints$3(this, (Continuation<? super PageFetcherSnapshot$startConsumingHints$3>) null), 3, (Object) null);
    }

    private final PagingSource.LoadParams<Key> loadParams(LoadType loadType, Key key) {
        return PagingSource.LoadParams.Companion.create(loadType, key, loadType == LoadType.REFRESH ? this.config.initialLoadSize : this.config.pageSize, this.config.enablePlaceholders);
    }

    /* JADX INFO: finally extract failed */
    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:100:0x0279, code lost:
        r0.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.PREPEND, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x0284, code lost:
        if (r3.getNextKey() != null) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:103:0x0286, code lost:
        r0.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.APPEND, r14);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x028f, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:105:0x0290, code lost:
        r1.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0293, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:107:0x0294, code lost:
        r14 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x0295, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x0298, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:111:0x029b, code lost:
        if ((r14 instanceof androidx.paging.PagingSource.LoadResult.Error) == false) goto L_0x0303;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x029d, code lost:
        r3 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x02a1, code lost:
        if (r3 == null) goto L_0x02b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x02a7, code lost:
        if (r3.isLoggable(2) != true) goto L_0x02b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x02a9, code lost:
        r3.log(2, r2.loadResultLog(androidx.paging.LoadType.REFRESH, r2.initialKey, r14), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:117:0x02b4, code lost:
        r3 = r2.stateHolder;
        r4 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r3);
        r0.L$0 = r2;
        r0.L$1 = r14;
        r0.L$2 = r3;
        r0.L$3 = r4;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:118:0x02ca, code lost:
        if (r4.lock((java.lang.Object) null, r0) != r1) goto L_0x02cd;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:119:0x02cc, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x02cd, code lost:
        r5 = r2;
        r2 = r4;
        r4 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:?, code lost:
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r3);
        r3 = new androidx.paging.LoadState.Error(((androidx.paging.PagingSource.LoadResult.Error) r4).getThrowable());
        r4 = androidx.paging.LoadType.REFRESH;
        r0.L$0 = r2;
        r0.L$1 = null;
        r0.L$2 = null;
        r0.L$3 = null;
        r0.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:123:0x02f1, code lost:
        if (r5.setError(r14, r4, r3, r0) != r1) goto L_0x02f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x02f3, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x02f4, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:?, code lost:
        r14 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:128:0x02f7, code lost:
        r0.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x02fc, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x02fd, code lost:
        r14 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x02fe, code lost:
        r0 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x02ff, code lost:
        r0.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:133:0x0302, code lost:
        throw r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:135:0x0305, code lost:
        if ((r14 instanceof androidx.paging.PagingSource.LoadResult.Invalid) == false) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:136:0x0307, code lost:
        r0 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x030b, code lost:
        if (r0 == null) goto L_0x031e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x0311, code lost:
        if (r0.isLoggable(2) != true) goto L_0x031e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x0313, code lost:
        r0.log(2, r2.loadResultLog(androidx.paging.LoadType.REFRESH, r2.initialKey, r14), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x031e, code lost:
        r2.onInvalidLoad();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x0323, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:?, code lost:
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r7);
        r7 = androidx.paging.LoadType.REFRESH;
        r0.L$0 = r8;
        r0.L$1 = r2;
        r0.L$2 = null;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00f8, code lost:
        if (r8.setLoading(r14, r7, r0) != r1) goto L_0x00fb;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x00fa, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x00fb, code lost:
        r7 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x00fc, code lost:
        r14 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x00fe, code lost:
        r2.unlock((java.lang.Object) null);
        r14 = r7.loadParams(androidx.paging.LoadType.REFRESH, r7.initialKey);
        r2 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x010d, code lost:
        if (r2 == null) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x0113, code lost:
        if (r2.isLoggable(3) != true) goto L_0x0132;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x0115, code lost:
        r2.log(3, "Start REFRESH with loadKey " + r7.initialKey + " on " + r7.pagingSource, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x0132, code lost:
        r2 = r7.pagingSource;
        r0.L$0 = r7;
        r0.L$1 = null;
        r0.label = 3;
        r14 = r2.load(r14, r0);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x013e, code lost:
        if (r14 != r1) goto L_0x0141;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x0140, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0141, code lost:
        r2 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x0142, code lost:
        r14 = (androidx.paging.PagingSource.LoadResult) r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0146, code lost:
        if ((r14 instanceof androidx.paging.PagingSource.LoadResult.Page) == false) goto L_0x0299;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x0148, code lost:
        r7 = r2.stateHolder;
        r8 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r7);
        r0.L$0 = r2;
        r0.L$1 = r14;
        r0.L$2 = r7;
        r0.L$3 = r8;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x015d, code lost:
        if (r8.lock((java.lang.Object) null, r0) != r1) goto L_0x0160;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x015f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0160, code lost:
        r9 = r2;
        r2 = r8;
        r8 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:?, code lost:
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r7);
        r7 = r14.insert(0, androidx.paging.LoadType.REFRESH, (androidx.paging.PagingSource.LoadResult.Page) r8);
        r14.getSourceLoadStates$paging_common().set(androidx.paging.LoadType.REFRESH, androidx.paging.LoadState.NotLoading.Companion.getIncomplete$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0189, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r8).getPrevKey() != null) goto L_0x019c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x018b, code lost:
        r14.getSourceLoadStates$paging_common().set(androidx.paging.LoadType.PREPEND, androidx.paging.LoadState.NotLoading.Companion.getComplete$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x01a3, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r8).getNextKey() != null) goto L_0x01b6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x01a5, code lost:
        r14.getSourceLoadStates$paging_common().set(androidx.paging.LoadType.APPEND, androidx.paging.LoadState.NotLoading.Companion.getComplete$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x01b9, code lost:
        if (r7 == false) goto L_0x021a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x01bb, code lost:
        r14 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x01bf, code lost:
        if (r14 == null) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x01c5, code lost:
        if (r14.isLoggable(3) != true) goto L_0x01d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x01c7, code lost:
        r14.log(3, r9.loadResultLog(androidx.paging.LoadType.REFRESH, r9.initialKey, r8), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x01d2, code lost:
        r3 = r9.stateHolder;
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r3);
        r0.L$0 = r9;
        r0.L$1 = r8;
        r0.L$2 = r3;
        r0.L$3 = r14;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x01e7, code lost:
        if (r14.lock((java.lang.Object) null, r0) != r1) goto L_0x01ea;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x01e9, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x01ea, code lost:
        r2 = r14;
        r4 = r8;
        r5 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r3);
        r3 = r5.pageEventCh;
        r14 = r14.toPageEvent$paging_common((androidx.paging.PagingSource.LoadResult.Page) r4, androidx.paging.LoadType.REFRESH);
        r0.L$0 = r5;
        r0.L$1 = r4;
        r0.L$2 = r2;
        r0.L$3 = null;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x020b, code lost:
        if (r3.send(r14, r0) != r1) goto L_0x020e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x020d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x020e, code lost:
        r3 = r4;
        r4 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0210, code lost:
        r14 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0212, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x021a, code lost:
        r14 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x021e, code lost:
        if (r14 == null) goto L_0x0231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0224, code lost:
        if (r14.isLoggable(2) != true) goto L_0x0231;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0226, code lost:
        r14.log(2, r9.loadResultLog(androidx.paging.LoadType.REFRESH, r9.initialKey, (androidx.paging.PagingSource.LoadResult) null), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0231, code lost:
        r3 = r8;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0235, code lost:
        if (r4.remoteMediatorConnection == null) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0237, code lost:
        r14 = (androidx.paging.PagingSource.LoadResult.Page) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x023e, code lost:
        if (r14.getPrevKey() == null) goto L_0x0246;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x0244, code lost:
        if (r14.getNextKey() != null) goto L_0x0321;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x0246, code lost:
        r2 = r4.stateHolder;
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r2);
        r0.L$0 = r4;
        r0.L$1 = r3;
        r0.L$2 = r2;
        r0.L$3 = r14;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x025b, code lost:
        if (r14.lock((java.lang.Object) null, r0) != r1) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x025d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x025e, code lost:
        r1 = r14;
        r0 = r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:97:?, code lost:
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r2).currentPagingState$paging_common(r0.hintHandler.getLastAccessHint());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x026e, code lost:
        r1.unlock((java.lang.Object) null);
        r3 = (androidx.paging.PagingSource.LoadResult.Page) r3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0277, code lost:
        if (r3.getPrevKey() != null) goto L_0x0280;
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0030  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x003c  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0051  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0066  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007a  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x00a4  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00bc  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object doInitialLoad(kotlin.coroutines.Continuation<? super kotlin.Unit> r14) {
        /*
            r13 = this;
            boolean r0 = r14 instanceof androidx.paging.PageFetcherSnapshot$doInitialLoad$1
            if (r0 == 0) goto L_0x0014
            r0 = r14
            androidx.paging.PageFetcherSnapshot$doInitialLoad$1 r0 = (androidx.paging.PageFetcherSnapshot$doInitialLoad$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r14 = r0.label
            int r14 = r14 - r2
            r0.label = r14
            goto L_0x0019
        L_0x0014:
            androidx.paging.PageFetcherSnapshot$doInitialLoad$1 r0 = new androidx.paging.PageFetcherSnapshot$doInitialLoad$1
            r0.<init>(r13, r14)
        L_0x0019:
            java.lang.Object r14 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 3
            r4 = 2
            r5 = 1
            r6 = 0
            switch(r2) {
                case 0: goto L_0x00cc;
                case 1: goto L_0x00bc;
                case 2: goto L_0x00ad;
                case 3: goto L_0x00a4;
                case 4: goto L_0x008f;
                case 5: goto L_0x007a;
                case 6: goto L_0x0066;
                case 7: goto L_0x0051;
                case 8: goto L_0x003c;
                case 9: goto L_0x0030;
                default: goto L_0x0028;
            }
        L_0x0028:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0030:
            java.lang.Object r0 = r0.L$0
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0039 }
            goto L_0x02f5
        L_0x0039:
            r14 = move-exception
            goto L_0x02ff
        L_0x003c:
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r3 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r3 = (androidx.paging.PageFetcherSnapshotState.Holder) r3
            java.lang.Object r4 = r0.L$1
            androidx.paging.PagingSource$LoadResult r4 = (androidx.paging.PagingSource.LoadResult) r4
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot r5 = (androidx.paging.PageFetcherSnapshot) r5
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x02d0
        L_0x0051:
            java.lang.Object r1 = r0.L$3
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r2 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r2 = (androidx.paging.PageFetcherSnapshotState.Holder) r2
            java.lang.Object r3 = r0.L$1
            androidx.paging.PagingSource$LoadResult r3 = (androidx.paging.PagingSource.LoadResult) r3
            java.lang.Object r0 = r0.L$0
            androidx.paging.PageFetcherSnapshot r0 = (androidx.paging.PageFetcherSnapshot) r0
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0260
        L_0x0066:
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r3 = r0.L$1
            androidx.paging.PagingSource$LoadResult r3 = (androidx.paging.PagingSource.LoadResult) r3
            java.lang.Object r4 = r0.L$0
            androidx.paging.PageFetcherSnapshot r4 = (androidx.paging.PageFetcherSnapshot) r4
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x0077 }
            goto L_0x0210
        L_0x0077:
            r14 = move-exception
            goto L_0x0216
        L_0x007a:
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r3 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r3 = (androidx.paging.PageFetcherSnapshotState.Holder) r3
            java.lang.Object r4 = r0.L$1
            androidx.paging.PagingSource$LoadResult r4 = (androidx.paging.PagingSource.LoadResult) r4
            java.lang.Object r5 = r0.L$0
            androidx.paging.PageFetcherSnapshot r5 = (androidx.paging.PageFetcherSnapshot) r5
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x01ed
        L_0x008f:
            java.lang.Object r2 = r0.L$3
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r7 = r0.L$2
            androidx.paging.PageFetcherSnapshotState$Holder r7 = (androidx.paging.PageFetcherSnapshotState.Holder) r7
            java.lang.Object r8 = r0.L$1
            androidx.paging.PagingSource$LoadResult r8 = (androidx.paging.PagingSource.LoadResult) r8
            java.lang.Object r9 = r0.L$0
            androidx.paging.PageFetcherSnapshot r9 = (androidx.paging.PageFetcherSnapshot) r9
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0163
        L_0x00a4:
            java.lang.Object r2 = r0.L$0
            androidx.paging.PageFetcherSnapshot r2 = (androidx.paging.PageFetcherSnapshot) r2
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0142
        L_0x00ad:
            java.lang.Object r2 = r0.L$1
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r7 = r0.L$0
            androidx.paging.PageFetcherSnapshot r7 = (androidx.paging.PageFetcherSnapshot) r7
            kotlin.ResultKt.throwOnFailure(r14)     // Catch:{ all -> 0x00b9 }
            goto L_0x00fc
        L_0x00b9:
            r14 = move-exception
            goto L_0x0324
        L_0x00bc:
            java.lang.Object r2 = r0.L$2
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            java.lang.Object r7 = r0.L$1
            androidx.paging.PageFetcherSnapshotState$Holder r7 = (androidx.paging.PageFetcherSnapshotState.Holder) r7
            java.lang.Object r8 = r0.L$0
            androidx.paging.PageFetcherSnapshot r8 = (androidx.paging.PageFetcherSnapshot) r8
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x00e6
        L_0x00cc:
            kotlin.ResultKt.throwOnFailure(r14)
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r7 = r13.stateHolder
            kotlinx.coroutines.sync.Mutex r14 = r7.lock
            r0.L$0 = r13
            r0.L$1 = r7
            r0.L$2 = r14
            r0.label = r5
            java.lang.Object r2 = r14.lock(r6, r0)
            if (r2 != r1) goto L_0x00e4
            return r1
        L_0x00e4:
            r8 = r13
            r2 = r14
        L_0x00e6:
            androidx.paging.PageFetcherSnapshotState r14 = r7.state     // Catch:{ all -> 0x00b9 }
            androidx.paging.LoadType r7 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x00b9 }
            r0.L$0 = r8     // Catch:{ all -> 0x00b9 }
            r0.L$1 = r2     // Catch:{ all -> 0x00b9 }
            r0.L$2 = r6     // Catch:{ all -> 0x00b9 }
            r0.label = r4     // Catch:{ all -> 0x00b9 }
            java.lang.Object r14 = r8.setLoading(r14, r7, r0)     // Catch:{ all -> 0x00b9 }
            if (r14 != r1) goto L_0x00fb
            return r1
        L_0x00fb:
            r7 = r8
        L_0x00fc:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00b9 }
            r2.unlock(r6)
            androidx.paging.LoadType r14 = androidx.paging.LoadType.REFRESH
            Key r2 = r7.initialKey
            androidx.paging.PagingSource$LoadParams r14 = r7.loadParams(r14, r2)
            androidx.paging.Logger r2 = androidx.paging.LoggerKt.getLOGGER()
            if (r2 == 0) goto L_0x0132
            boolean r8 = r2.isLoggable(r3)
            if (r8 != r5) goto L_0x0132
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Start REFRESH with loadKey "
            r8.<init>(r9)
            Key r9 = r7.initialKey
            r8.append(r9)
            java.lang.String r9 = " on "
            r8.append(r9)
            androidx.paging.PagingSource<Key, Value> r9 = r7.pagingSource
            r8.append(r9)
            java.lang.String r8 = r8.toString()
            r2.log(r3, r8, r6)
        L_0x0132:
            androidx.paging.PagingSource<Key, Value> r2 = r7.pagingSource
            r0.L$0 = r7
            r0.L$1 = r6
            r0.label = r3
            java.lang.Object r14 = r2.load(r14, r0)
            if (r14 != r1) goto L_0x0141
            return r1
        L_0x0141:
            r2 = r7
        L_0x0142:
            androidx.paging.PagingSource$LoadResult r14 = (androidx.paging.PagingSource.LoadResult) r14
            boolean r7 = r14 instanceof androidx.paging.PagingSource.LoadResult.Page
            if (r7 == 0) goto L_0x0299
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r7 = r2.stateHolder
            kotlinx.coroutines.sync.Mutex r8 = r7.lock
            r0.L$0 = r2
            r0.L$1 = r14
            r0.L$2 = r7
            r0.L$3 = r8
            r9 = 4
            r0.label = r9
            java.lang.Object r9 = r8.lock(r6, r0)
            if (r9 != r1) goto L_0x0160
            return r1
        L_0x0160:
            r9 = r2
            r2 = r8
            r8 = r14
        L_0x0163:
            androidx.paging.PageFetcherSnapshotState r14 = r7.state     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadType r7 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0294 }
            r10 = r8
            androidx.paging.PagingSource$LoadResult$Page r10 = (androidx.paging.PagingSource.LoadResult.Page) r10     // Catch:{ all -> 0x0294 }
            r11 = 0
            boolean r7 = r14.insert(r11, r7, r10)     // Catch:{ all -> 0x0294 }
            androidx.paging.MutableLoadStateCollection r10 = r14.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadType r11 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState$NotLoading$Companion r12 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState$NotLoading r12 = r12.getIncomplete$paging_common()     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState r12 = (androidx.paging.LoadState) r12     // Catch:{ all -> 0x0294 }
            r10.set(r11, r12)     // Catch:{ all -> 0x0294 }
            r10 = r8
            androidx.paging.PagingSource$LoadResult$Page r10 = (androidx.paging.PagingSource.LoadResult.Page) r10     // Catch:{ all -> 0x0294 }
            java.lang.Object r10 = r10.getPrevKey()     // Catch:{ all -> 0x0294 }
            if (r10 != 0) goto L_0x019c
            androidx.paging.MutableLoadStateCollection r10 = r14.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadType r11 = androidx.paging.LoadType.PREPEND     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState$NotLoading$Companion r12 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState$NotLoading r12 = r12.getComplete$paging_common()     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState r12 = (androidx.paging.LoadState) r12     // Catch:{ all -> 0x0294 }
            r10.set(r11, r12)     // Catch:{ all -> 0x0294 }
        L_0x019c:
            r10 = r8
            androidx.paging.PagingSource$LoadResult$Page r10 = (androidx.paging.PagingSource.LoadResult.Page) r10     // Catch:{ all -> 0x0294 }
            java.lang.Object r10 = r10.getNextKey()     // Catch:{ all -> 0x0294 }
            if (r10 != 0) goto L_0x01b6
            androidx.paging.MutableLoadStateCollection r14 = r14.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadType r10 = androidx.paging.LoadType.APPEND     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState$NotLoading$Companion r11 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState$NotLoading r11 = r11.getComplete$paging_common()     // Catch:{ all -> 0x0294 }
            androidx.paging.LoadState r11 = (androidx.paging.LoadState) r11     // Catch:{ all -> 0x0294 }
            r14.set(r10, r11)     // Catch:{ all -> 0x0294 }
        L_0x01b6:
            r2.unlock(r6)
            if (r7 == 0) goto L_0x021a
            androidx.paging.Logger r14 = androidx.paging.LoggerKt.getLOGGER()
            if (r14 == 0) goto L_0x01d2
            boolean r2 = r14.isLoggable(r3)
            if (r2 != r5) goto L_0x01d2
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            Key r4 = r9.initialKey
            java.lang.String r2 = r9.loadResultLog(r2, r4, r8)
            r14.log(r3, r2, r6)
        L_0x01d2:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r3 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r14 = r3.lock
            r0.L$0 = r9
            r0.L$1 = r8
            r0.L$2 = r3
            r0.L$3 = r14
            r2 = 5
            r0.label = r2
            java.lang.Object r2 = r14.lock(r6, r0)
            if (r2 != r1) goto L_0x01ea
            return r1
        L_0x01ea:
            r2 = r14
            r4 = r8
            r5 = r9
        L_0x01ed:
            androidx.paging.PageFetcherSnapshotState r14 = r3.state     // Catch:{ all -> 0x0077 }
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r3 = r5.pageEventCh     // Catch:{ all -> 0x0077 }
            r7 = r4
            androidx.paging.PagingSource$LoadResult$Page r7 = (androidx.paging.PagingSource.LoadResult.Page) r7     // Catch:{ all -> 0x0077 }
            androidx.paging.LoadType r8 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x0077 }
            androidx.paging.PageEvent r14 = r14.toPageEvent$paging_common(r7, r8)     // Catch:{ all -> 0x0077 }
            r0.L$0 = r5     // Catch:{ all -> 0x0077 }
            r0.L$1 = r4     // Catch:{ all -> 0x0077 }
            r0.L$2 = r2     // Catch:{ all -> 0x0077 }
            r0.L$3 = r6     // Catch:{ all -> 0x0077 }
            r7 = 6
            r0.label = r7     // Catch:{ all -> 0x0077 }
            java.lang.Object r14 = r3.send(r14, r0)     // Catch:{ all -> 0x0077 }
            if (r14 != r1) goto L_0x020e
            return r1
        L_0x020e:
            r3 = r4
            r4 = r5
        L_0x0210:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0077 }
            r2.unlock(r6)
            goto L_0x0233
        L_0x0216:
            r2.unlock(r6)
            throw r14
        L_0x021a:
            androidx.paging.Logger r14 = androidx.paging.LoggerKt.getLOGGER()
            if (r14 == 0) goto L_0x0231
            boolean r2 = r14.isLoggable(r4)
            if (r2 != r5) goto L_0x0231
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            Key r3 = r9.initialKey
            java.lang.String r2 = r9.loadResultLog(r2, r3, r6)
            r14.log(r4, r2, r6)
        L_0x0231:
            r3 = r8
            r4 = r9
        L_0x0233:
            androidx.paging.RemoteMediatorConnection<Key, Value> r14 = r4.remoteMediatorConnection
            if (r14 == 0) goto L_0x0321
            r14 = r3
            androidx.paging.PagingSource$LoadResult$Page r14 = (androidx.paging.PagingSource.LoadResult.Page) r14
            java.lang.Object r2 = r14.getPrevKey()
            if (r2 == 0) goto L_0x0246
            java.lang.Object r14 = r14.getNextKey()
            if (r14 != 0) goto L_0x0321
        L_0x0246:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r2 = r4.stateHolder
            kotlinx.coroutines.sync.Mutex r14 = r2.lock
            r0.L$0 = r4
            r0.L$1 = r3
            r0.L$2 = r2
            r0.L$3 = r14
            r5 = 7
            r0.label = r5
            java.lang.Object r0 = r14.lock(r6, r0)
            if (r0 != r1) goto L_0x025e
            return r1
        L_0x025e:
            r1 = r14
            r0 = r4
        L_0x0260:
            androidx.paging.PageFetcherSnapshotState r14 = r2.state     // Catch:{ all -> 0x028f }
            androidx.paging.HintHandler r2 = r0.hintHandler     // Catch:{ all -> 0x028f }
            androidx.paging.ViewportHint$Access r2 = r2.getLastAccessHint()     // Catch:{ all -> 0x028f }
            androidx.paging.PagingState r14 = r14.currentPagingState$paging_common(r2)     // Catch:{ all -> 0x028f }
            r1.unlock(r6)
            androidx.paging.PagingSource$LoadResult$Page r3 = (androidx.paging.PagingSource.LoadResult.Page) r3
            java.lang.Object r1 = r3.getPrevKey()
            if (r1 != 0) goto L_0x0280
            androidx.paging.RemoteMediatorConnection<Key, Value> r1 = r0.remoteMediatorConnection
            androidx.paging.LoadType r2 = androidx.paging.LoadType.PREPEND
            r1.requestLoad(r2, r14)
        L_0x0280:
            java.lang.Object r1 = r3.getNextKey()
            if (r1 != 0) goto L_0x0321
            androidx.paging.RemoteMediatorConnection<Key, Value> r0 = r0.remoteMediatorConnection
            androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND
            r0.requestLoad(r1, r14)
            goto L_0x0321
        L_0x028f:
            r14 = move-exception
            r1.unlock(r6)
            throw r14
        L_0x0294:
            r14 = move-exception
            r2.unlock(r6)
            throw r14
        L_0x0299:
            boolean r3 = r14 instanceof androidx.paging.PagingSource.LoadResult.Error
            if (r3 == 0) goto L_0x0303
            androidx.paging.Logger r3 = androidx.paging.LoggerKt.getLOGGER()
            if (r3 == 0) goto L_0x02b4
            boolean r7 = r3.isLoggable(r4)
            if (r7 != r5) goto L_0x02b4
            androidx.paging.LoadType r5 = androidx.paging.LoadType.REFRESH
            Key r7 = r2.initialKey
            java.lang.String r5 = r2.loadResultLog(r5, r7, r14)
            r3.log(r4, r5, r6)
        L_0x02b4:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r3 = r2.stateHolder
            kotlinx.coroutines.sync.Mutex r4 = r3.lock
            r0.L$0 = r2
            r0.L$1 = r14
            r0.L$2 = r3
            r0.L$3 = r4
            r5 = 8
            r0.label = r5
            java.lang.Object r5 = r4.lock(r6, r0)
            if (r5 != r1) goto L_0x02cd
            return r1
        L_0x02cd:
            r5 = r2
            r2 = r4
            r4 = r14
        L_0x02d0:
            androidx.paging.PageFetcherSnapshotState r14 = r3.state     // Catch:{ all -> 0x02fd }
            androidx.paging.LoadState$Error r3 = new androidx.paging.LoadState$Error     // Catch:{ all -> 0x02fd }
            androidx.paging.PagingSource$LoadResult$Error r4 = (androidx.paging.PagingSource.LoadResult.Error) r4     // Catch:{ all -> 0x02fd }
            java.lang.Throwable r4 = r4.getThrowable()     // Catch:{ all -> 0x02fd }
            r3.<init>(r4)     // Catch:{ all -> 0x02fd }
            androidx.paging.LoadType r4 = androidx.paging.LoadType.REFRESH     // Catch:{ all -> 0x02fd }
            r0.L$0 = r2     // Catch:{ all -> 0x02fd }
            r0.L$1 = r6     // Catch:{ all -> 0x02fd }
            r0.L$2 = r6     // Catch:{ all -> 0x02fd }
            r0.L$3 = r6     // Catch:{ all -> 0x02fd }
            r7 = 9
            r0.label = r7     // Catch:{ all -> 0x02fd }
            java.lang.Object r14 = r5.setError(r14, r4, r3, r0)     // Catch:{ all -> 0x02fd }
            if (r14 != r1) goto L_0x02f4
            return r1
        L_0x02f4:
            r0 = r2
        L_0x02f5:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0039 }
            r0.unlock(r6)
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x02fd:
            r14 = move-exception
            r0 = r2
        L_0x02ff:
            r0.unlock(r6)
            throw r14
        L_0x0303:
            boolean r0 = r14 instanceof androidx.paging.PagingSource.LoadResult.Invalid
            if (r0 == 0) goto L_0x0321
            androidx.paging.Logger r0 = androidx.paging.LoggerKt.getLOGGER()
            if (r0 == 0) goto L_0x031e
            boolean r1 = r0.isLoggable(r4)
            if (r1 != r5) goto L_0x031e
            androidx.paging.LoadType r1 = androidx.paging.LoadType.REFRESH
            Key r3 = r2.initialKey
            java.lang.String r14 = r2.loadResultLog(r1, r3, r14)
            r0.log(r4, r14, r6)
        L_0x031e:
            r2.onInvalidLoad()
        L_0x0321:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x0324:
            r2.unlock(r6)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.doInitialLoad(kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r15v10, resolved type: androidx.paging.PagingSource$LoadResult$Page} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v20, resolved type: androidx.paging.PagingSource$LoadResult$Page} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v96, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r5v73, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX WARNING: type inference failed for: r5v22, types: [java.lang.Throwable, androidx.paging.PagingSource$LoadResult, java.lang.Object] */
    /* access modifiers changed from: private */
    /* JADX WARNING: Can't fix incorrect switch cases order */
    /* JADX WARNING: Code restructure failed: missing block: B:101:0x03d3, code lost:
        throw new java.lang.IllegalArgumentException("Use doInitialLoad for LoadType == REFRESH");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:102:0x03d4, code lost:
        r5 = ((androidx.paging.PagingSource.LoadResult.Page) r2).getPrevKey();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:104:0x03e1, code lost:
        if (r9.pagingSource.getKeyReuseSupported() != false) goto L_0x0422;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:106:0x03e9, code lost:
        if (kotlin.jvm.internal.Intrinsics.areEqual(r5, (java.lang.Object) r10.element) != false) goto L_0x03ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:108:0x03ee, code lost:
        if (r13 != androidx.paging.LoadType.PREPEND) goto L_0x03f3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:109:0x03f0, code lost:
        r0 = "prevKey";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:110:0x03f3, code lost:
        r0 = "nextKey";
     */
    /* JADX WARNING: Code restructure failed: missing block: B:112:0x0421, code lost:
        throw new java.lang.IllegalStateException(kotlin.text.StringsKt.trimMargin$default("The same value, " + r10.element + ", was passed as the " + r0 + " in two\n                            | sequential Pages loaded from a PagingSource. Re-using load keys in\n                            | PagingSource is often an error, and must be explicitly enabled by\n                            | overriding PagingSource.keyReuseSupported.\n                            ", (java.lang.String) null, 1, (java.lang.Object) null).toString());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:113:0x0422, code lost:
        r5 = r9.stateHolder;
        r7 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r3.L$0 = r9;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r0;
        r3.L$6 = r8;
        r3.L$7 = r2;
        r3.L$8 = r5;
        r3.L$9 = r7;
        r3.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:114:0x0444, code lost:
        if (r7.lock((java.lang.Object) null, r3) != r4) goto L_0x0447;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:115:0x0446, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:116:0x0447, code lost:
        r14 = r9;
        r9 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:120:0x045c, code lost:
        if (androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5).insert(r12.getGenerationId(), r13, (androidx.paging.PagingSource.LoadResult.Page) r2) != false) goto L_0x0477;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:121:0x045e, code lost:
        r0 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:122:0x0462, code lost:
        if (r0 == null) goto L_0x06f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:124:0x046a, code lost:
        if (r0.isLoggable(2) != true) goto L_0x06f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x046c, code lost:
        r0.log(2, r14.loadResultLog(r13, r10.element, r5), r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:126:0x0477, code lost:
        r0 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:127:0x047c, code lost:
        if (r0 == null) goto L_0x048f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:129:0x0483, code lost:
        if (r0.isLoggable(3) != true) goto L_0x048f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:130:0x0485, code lost:
        r0.log(3, r14.loadResultLog(r13, r10.element, r2), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:131:0x048f, code lost:
        r7 = (androidx.paging.PagingSource.LoadResult.Page) r2;
        r11.element += r7.getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:132:0x04a1, code lost:
        if (r13 != androidx.paging.LoadType.PREPEND) goto L_0x04ac;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:134:0x04a7, code lost:
        if (r7.getPrevKey() == null) goto L_0x04aa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:137:0x04ae, code lost:
        if (r13 != androidx.paging.LoadType.APPEND) goto L_0x04b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:139:0x04b4, code lost:
        if (r7.getNextKey() != null) goto L_0x04b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:140:0x04b7, code lost:
        r9.element = true;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x04b9, code lost:
        r0 = r9;
        r9 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:142:0x04bd, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:143:0x04be, code lost:
        r7.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:144:0x04c2, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:146:0x04c7, code lost:
        if ((r2 instanceof androidx.paging.PagingSource.LoadResult.Error) == false) goto L_0x0548;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:147:0x04c9, code lost:
        r0 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:148:0x04cd, code lost:
        if (r0 == null) goto L_0x04e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:150:0x04d5, code lost:
        if (r0.isLoggable(2) != true) goto L_0x04e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:151:0x04d7, code lost:
        r0.log(2, r9.loadResultLog(r13, r10.element, r2), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:152:0x04e0, code lost:
        r5 = r9.stateHolder;
        r0 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r3.L$0 = r9;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r2;
        r3.L$4 = r5;
        r3.L$5 = r0;
        r3.L$6 = null;
        r3.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:153:0x04fc, code lost:
        if (r0.lock((java.lang.Object) null, r3) != r4) goto L_0x04ff;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:154:0x04fe, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:155:0x04ff, code lost:
        r6 = r2;
        r2 = r0;
        r0 = r3;
        r3 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:157:?, code lost:
        r5 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r7 = new androidx.paging.LoadState.Error(((androidx.paging.PagingSource.LoadResult.Error) r6).getThrowable());
        r0.L$0 = r3;
        r0.L$1 = r12;
        r0.L$2 = r2;
        r0.L$3 = r5;
        r0.L$4 = null;
        r0.L$5 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:158:0x0526, code lost:
        if (r9.setError(r5, r3, r7, r0) != r4) goto L_0x0529;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:159:0x0528, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:160:0x0529, code lost:
        r4 = r2;
        r0 = r5;
        r5 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:162:?, code lost:
        r0.getFailedHintsByLoadType$paging_common().put(r3, r5.getHint());
        r0 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:163:0x0539, code lost:
        r4.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:164:0x053f, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:165:0x0540, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:166:0x0541, code lost:
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:167:0x0544, code lost:
        r4.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:168:0x0547, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:170:0x054b, code lost:
        if ((r2 instanceof androidx.paging.PagingSource.LoadResult.Invalid) == false) goto L_0x056a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:171:0x054d, code lost:
        r0 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:172:0x0551, code lost:
        if (r0 == null) goto L_0x0564;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:174:0x0559, code lost:
        if (r0.isLoggable(2) != true) goto L_0x0564;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:175:0x055b, code lost:
        r0.log(2, r9.loadResultLog(r13, r10.element, r2), (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:176:0x0564, code lost:
        r9.onInvalidLoad();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:177:0x0569, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:179:0x0574, code lost:
        if (androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0[r13.ordinal()] != 2) goto L_0x0579;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:180:0x0576, code lost:
        r7 = androidx.paging.LoadType.APPEND;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:181:0x0579, code lost:
        r7 = androidx.paging.LoadType.PREPEND;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:182:0x057b, code lost:
        r5 = r9.stateHolder;
        r14 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r3.L$0 = r9;
        r3.L$1 = r13;
        r3.L$2 = r12;
        r3.L$3 = r11;
        r3.L$4 = r10;
        r3.L$5 = r0;
        r3.L$6 = r8;
        r3.L$7 = r2;
        r3.L$8 = r7;
        r3.L$9 = r5;
        r3.L$10 = r14;
        r3.label = 8;
        r18 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:183:0x05a2, code lost:
        if (r14.lock((java.lang.Object) null, r3) != r4) goto L_0x05a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:184:0x05a4, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:185:0x05a5, code lost:
        r15 = r13;
        r13 = r12;
        r12 = r11;
        r11 = r10;
        r10 = r9;
        r9 = r18;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:187:?, code lost:
        r0 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r5 = r0.dropEventOrNull(r7, r13.getHint());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:188:0x05b8, code lost:
        if (r5 == null) goto L_0x05ec;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:189:0x05ba, code lost:
        r0.drop(r5);
        r7 = r10.pageEventCh;
        r3.L$0 = r10;
        r3.L$1 = r15;
        r3.L$2 = r13;
        r3.L$3 = r12;
        r3.L$4 = r11;
        r3.L$5 = r9;
        r3.L$6 = r8;
        r3.L$7 = r2;
        r3.L$8 = r14;
        r3.L$9 = r0;
        r3.L$10 = null;
        r3.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:190:0x05de, code lost:
        if (r7.send(r5, r3) != r4) goto L_0x05e1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:191:0x05e0, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:192:0x05e1, code lost:
        r7 = r2;
        r5 = r14;
        r14 = r15;
        r15 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:194:?, code lost:
        r1 = kotlin.Unit.INSTANCE;
        r2 = r7;
        r7 = r8;
        r10 = r15;
        r15 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:195:0x05ec, code lost:
        r7 = r8;
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:196:0x05ee, code lost:
        r11.element = r10.nextLoadKeyOrNull(r0, r15, r13.getGenerationId(), r13.getHint().presentedItemsBeyondAnchor$paging_common(r15) + r12.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:197:0x0605, code lost:
        if (r11.element != null) goto L_0x062d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:199:0x0611, code lost:
        if ((r0.getSourceLoadStates$paging_common().get(r15) instanceof androidx.paging.LoadState.Error) != false) goto L_0x062d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:200:0x0613, code lost:
        r1 = r0.getSourceLoadStates$paging_common();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:201:0x0619, code lost:
        if (r9.element == false) goto L_0x0622;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:202:0x061b, code lost:
        r8 = androidx.paging.LoadState.NotLoading.Companion.getComplete$paging_common();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:203:0x0622, code lost:
        r8 = androidx.paging.LoadState.NotLoading.Companion.getIncomplete$paging_common();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:204:0x0628, code lost:
        r1.set(r15, r8);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:205:0x062d, code lost:
        r0 = r0.toPageEvent$paging_common((androidx.paging.PagingSource.LoadResult.Page) r2, r15);
        r1 = r10.pageEventCh;
        r3.L$0 = r10;
        r3.L$1 = r15;
        r3.L$2 = r13;
        r3.L$3 = r12;
        r3.L$4 = r11;
        r3.L$5 = r9;
        r3.L$6 = r7;
        r3.L$7 = r2;
        r3.L$8 = r5;
        r3.L$9 = null;
        r3.L$10 = null;
        r3.label = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:206:0x0655, code lost:
        if (r1.send(r0, r3) != r4) goto L_0x0658;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:207:0x0657, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:208:0x0658, code lost:
        r0 = r2;
        r8 = r9;
        r14 = r10;
        r9 = r11;
        r10 = r13;
        r11 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:209:0x065e, code lost:
        r1 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:210:0x0660, code lost:
        r5.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:211:0x0666, code lost:
        if ((r7 instanceof androidx.paging.PagingSource.LoadParams.Prepend) == false) goto L_0x0673;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:213:0x066f, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r0).getPrevKey() != null) goto L_0x0673;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:214:0x0671, code lost:
        r5 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:215:0x0673, code lost:
        r5 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:217:0x0676, code lost:
        if ((r7 instanceof androidx.paging.PagingSource.LoadParams.Append) == false) goto L_0x0682;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:219:0x067e, code lost:
        if (((androidx.paging.PagingSource.LoadResult.Page) r0).getNextKey() != null) goto L_0x0682;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:220:0x0680, code lost:
        r0 = 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:221:0x0682, code lost:
        r0 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:223:0x0685, code lost:
        if (r14.remoteMediatorConnection == null) goto L_0x06e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:224:0x0687, code lost:
        if (r5 != 0) goto L_0x068b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:225:0x0689, code lost:
        if (r0 == 0) goto L_0x06e9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:226:0x068b, code lost:
        r13 = r14.stateHolder;
        r1 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r13);
        r3.L$0 = r14;
        r3.L$1 = r11;
        r3.L$2 = r10;
        r3.L$3 = r12;
        r3.L$4 = r9;
        r3.L$5 = r8;
        r3.L$6 = r13;
        r3.L$7 = r1;
        r3.L$8 = null;
        r3.I$0 = r5;
        r3.I$1 = r0;
        r3.label = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:227:0x06b0, code lost:
        if (r1.lock((java.lang.Object) null, r3) != r4) goto L_0x06b3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:228:0x06b2, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:229:0x06b3, code lost:
        r16 = r14;
        r14 = r8;
        r8 = r9;
        r9 = r12;
        r12 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:231:?, code lost:
        r2 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r13).currentPagingState$paging_common(r12.hintHandler.getLastAccessHint());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:233:0x06cc, code lost:
        if (r5 == 0) goto L_0x06d5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:234:0x06ce, code lost:
        r12.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.PREPEND, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:235:0x06d5, code lost:
        if (r0 == 0) goto L_0x06de;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:236:0x06d7, code lost:
        r12.remoteMediatorConnection.requestLoad(androidx.paging.LoadType.APPEND, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:237:0x06de, code lost:
        r1 = r17;
        r0 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:238:0x06e3, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:239:0x06e4, code lost:
        r1.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:240:0x06e8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:241:0x06e9, code lost:
        r1 = r17;
        r0 = r8;
        r8 = r9;
        r9 = r12;
        r12 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:242:0x06f1, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:243:0x06f2, code lost:
        r5 = r14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:244:0x06f5, code lost:
        r5.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:245:0x06f8, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:247:0x06fb, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:248:0x06fc, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:249:0x06fd, code lost:
        r7 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:250:0x0700, code lost:
        r7.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:251:0x0703, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:254:0x0709, code lost:
        throw new java.lang.IllegalStateException("Use doInitialLoad for LoadType == REFRESH");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:255:0x070a, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:256:0x070b, code lost:
        r2.unlock((java.lang.Object) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:257:0x070f, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        r5 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r5);
        r9 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0[r0.ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x022d, code lost:
        if (r9 == 1) goto L_0x0704;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x0230, code lost:
        if (r9 == 2) goto L_0x027a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x0233, code lost:
        if (r9 == 3) goto L_0x0237;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0237, code lost:
        r9 = (r5.getInitialPageIndex$paging_common() + r8.getHint().getOriginalPageOffsetLast()) + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0245, code lost:
        if (r9 >= 0) goto L_0x0254;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x0247, code lost:
        r7.element += r10.config.pageSize * (-r9);
        r9 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0254, code lost:
        r11 = kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x025c, code lost:
        if (r9 > r11) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x025e, code lost:
        r7.element += ((androidx.paging.PagingSource.LoadResult.Page) r5.getPages$paging_common().get(r9)).getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0275, code lost:
        if (r9 == r11) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0277, code lost:
        r9 = r9 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x027a, code lost:
        r9 = (r5.getInitialPageIndex$paging_common() + r8.getHint().getOriginalPageOffsetFirst()) - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0291, code lost:
        if (r9 <= kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common())) goto L_0x02af;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0293, code lost:
        r7.element += r10.config.pageSize * (r9 - kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common()));
        r9 = kotlin.collections.CollectionsKt.getLastIndex(r5.getPages$paging_common());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x02af, code lost:
        if (r9 < 0) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x02b1, code lost:
        r11 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x02b2, code lost:
        r7.element += ((androidx.paging.PagingSource.LoadResult.Page) r5.getPages$paging_common().get(r11)).getData().size();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x02c9, code lost:
        if (r11 == r9) goto L_0x02ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x02cb, code lost:
        r11 = r11 + 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x02ce, code lost:
        r5 = kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x02d0, code lost:
        r2.unlock((java.lang.Object) null);
        r2 = new kotlin.jvm.internal.Ref.ObjectRef();
        r5 = r10.stateHolder;
        r9 = androidx.paging.PageFetcherSnapshotState.Holder.access$getLock$p(r5);
        r3.L$0 = r10;
        r3.L$1 = r0;
        r3.L$2 = r8;
        r3.L$3 = r7;
        r3.L$4 = r2;
        r3.L$5 = r5;
        r3.L$6 = r9;
        r3.L$7 = r2;
        r3.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x02f7, code lost:
        if (r9.lock((java.lang.Object) null, r3) != r4) goto L_0x02fa;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x02f9, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x02fa, code lost:
        r11 = r0;
        r0 = r2;
        r12 = r10;
        r10 = r8;
        r8 = r0;
        r16 = r7;
        r7 = r5;
        r5 = r9;
        r9 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:?, code lost:
        r2 = androidx.paging.PageFetcherSnapshotState.Holder.access$getState$p(r7);
        r7 = r12.nextLoadKeyOrNull(r2, r11, r10.getGenerationId(), r10.getHint().presentedItemsBeyondAnchor$paging_common(r11) + r9.element);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x031c, code lost:
        if (r7 == null) goto L_0x0341;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x031e, code lost:
        r3.L$0 = r12;
        r3.L$1 = r11;
        r3.L$2 = r10;
        r3.L$3 = r9;
        r3.L$4 = r8;
        r3.L$5 = r5;
        r3.L$6 = r7;
        r3.L$7 = r0;
        r3.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x0335, code lost:
        if (r12.setLoading(r2, r11, r3) != r4) goto L_0x0338;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x0337, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x0338, code lost:
        r16 = r7;
        r7 = r5;
        r5 = r16;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x033d, code lost:
        r2 = r5;
        r5 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0341, code lost:
        r2 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0343, code lost:
        r5.unlock((java.lang.Object) null);
        r0.element = r2;
        r0 = new kotlin.jvm.internal.Ref.BooleanRef();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x034f, code lost:
        if (r8.element == null) goto L_0x06f9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0351, code lost:
        r2 = r12.loadParams(r11, r8.element);
        r5 = androidx.paging.LoggerKt.getLOGGER();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x035b, code lost:
        if (r5 == null) goto L_0x038c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:87:0x0363, code lost:
        if (r5.isLoggable(3) != true) goto L_0x038c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x0365, code lost:
        r5.log(3, "Start " + r11 + " with loadKey " + r8.element + " on " + r12.pagingSource, (java.lang.Throwable) null);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:89:0x038c, code lost:
        r5 = r12.pagingSource;
        r3.L$0 = r12;
        r3.L$1 = r11;
        r3.L$2 = r10;
        r3.L$3 = r9;
        r3.L$4 = r8;
        r3.L$5 = r0;
        r3.L$6 = r2;
        r3.L$7 = null;
        r3.L$8 = null;
        r3.label = 4;
        r5 = r5.load(r2, r3);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:90:0x03a8, code lost:
        if (r5 != r4) goto L_0x03ab;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:91:0x03aa, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:92:0x03ab, code lost:
        r13 = r11;
        r11 = r9;
        r9 = r12;
        r12 = r10;
        r10 = r8;
        r8 = r2;
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:93:0x03b2, code lost:
        r2 = (androidx.paging.PagingSource.LoadResult) r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:94:0x03b6, code lost:
        if ((r2 instanceof androidx.paging.PagingSource.LoadResult.Page) == false) goto L_0x04c3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:95:0x03b8, code lost:
        r5 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0[r13.ordinal()];
     */
    /* JADX WARNING: Code restructure failed: missing block: B:96:0x03c1, code lost:
        if (r5 == 2) goto L_0x03d4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:98:0x03c4, code lost:
        if (r5 != 3) goto L_0x03ce;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x03c6, code lost:
        r5 = ((androidx.paging.PagingSource.LoadResult.Page) r2).getNextKey();
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0063  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0090  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c1  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0112  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0133  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0162  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x018a  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x01b1  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x01d6  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x01f4  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object doLoad(androidx.paging.LoadType r18, androidx.paging.GenerationalViewportHint r19, kotlin.coroutines.Continuation<? super kotlin.Unit> r20) {
        /*
            r17 = this;
            r1 = r17
            r0 = r18
            r2 = r20
            boolean r3 = r2 instanceof androidx.paging.PageFetcherSnapshot$doLoad$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            androidx.paging.PageFetcherSnapshot$doLoad$1 r3 = (androidx.paging.PageFetcherSnapshot$doLoad$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            androidx.paging.PageFetcherSnapshot$doLoad$1 r3 = new androidx.paging.PageFetcherSnapshot$doLoad$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            java.lang.String r6 = "Use doInitialLoad for LoadType == REFRESH"
            switch(r5) {
                case 0: goto L_0x01f4;
                case 1: goto L_0x01d6;
                case 2: goto L_0x01b1;
                case 3: goto L_0x018a;
                case 4: goto L_0x0162;
                case 5: goto L_0x0133;
                case 6: goto L_0x0112;
                case 7: goto L_0x00f9;
                case 8: goto L_0x00c1;
                case 9: goto L_0x0090;
                case 10: goto L_0x0063;
                case 11: goto L_0x0034;
                default: goto L_0x002c;
            }
        L_0x002c:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0034:
            int r0 = r3.I$1
            int r5 = r3.I$0
            java.lang.Object r12 = r3.L$7
            kotlinx.coroutines.sync.Mutex r12 = (kotlinx.coroutines.sync.Mutex) r12
            java.lang.Object r13 = r3.L$6
            androidx.paging.PageFetcherSnapshotState$Holder r13 = (androidx.paging.PageFetcherSnapshotState.Holder) r13
            java.lang.Object r14 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r14 = (kotlin.jvm.internal.Ref.BooleanRef) r14
            java.lang.Object r15 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r15 = (kotlin.jvm.internal.Ref.ObjectRef) r15
            java.lang.Object r7 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r8 = r3.L$2
            androidx.paging.GenerationalViewportHint r8 = (androidx.paging.GenerationalViewportHint) r8
            java.lang.Object r9 = r3.L$1
            androidx.paging.LoadType r9 = (androidx.paging.LoadType) r9
            java.lang.Object r11 = r3.L$0
            androidx.paging.PageFetcherSnapshot r11 = (androidx.paging.PageFetcherSnapshot) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r10 = r8
            r1 = r12
            r8 = r15
            r12 = r11
            r11 = r9
            r9 = r7
            goto L_0x06ba
        L_0x0063:
            java.lang.Object r0 = r3.L$8
            r5 = r0
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r0 = r3.L$7
            androidx.paging.PagingSource$LoadResult r0 = (androidx.paging.PagingSource.LoadResult) r0
            java.lang.Object r7 = r3.L$6
            androidx.paging.PagingSource$LoadParams r7 = (androidx.paging.PagingSource.LoadParams) r7
            java.lang.Object r8 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r8 = (kotlin.jvm.internal.Ref.BooleanRef) r8
            java.lang.Object r9 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r9 = (kotlin.jvm.internal.Ref.ObjectRef) r9
            java.lang.Object r11 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r3.L$2
            androidx.paging.GenerationalViewportHint r12 = (androidx.paging.GenerationalViewportHint) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.LoadType r13 = (androidx.paging.LoadType) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.PageFetcherSnapshot r14 = (androidx.paging.PageFetcherSnapshot) r14
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x00bd }
            r10 = r12
            r12 = r11
            r11 = r13
            goto L_0x065e
        L_0x0090:
            java.lang.Object r0 = r3.L$9
            androidx.paging.PageFetcherSnapshotState r0 = (androidx.paging.PageFetcherSnapshotState) r0
            java.lang.Object r5 = r3.L$8
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r7 = r3.L$7
            androidx.paging.PagingSource$LoadResult r7 = (androidx.paging.PagingSource.LoadResult) r7
            java.lang.Object r8 = r3.L$6
            androidx.paging.PagingSource$LoadParams r8 = (androidx.paging.PagingSource.LoadParams) r8
            java.lang.Object r9 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r11 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r11 = (kotlin.jvm.internal.Ref.ObjectRef) r11
            java.lang.Object r12 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r12 = (kotlin.jvm.internal.Ref.IntRef) r12
            java.lang.Object r13 = r3.L$2
            androidx.paging.GenerationalViewportHint r13 = (androidx.paging.GenerationalViewportHint) r13
            java.lang.Object r14 = r3.L$1
            androidx.paging.LoadType r14 = (androidx.paging.LoadType) r14
            java.lang.Object r15 = r3.L$0
            androidx.paging.PageFetcherSnapshot r15 = (androidx.paging.PageFetcherSnapshot) r15
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x00bd }
            goto L_0x05e5
        L_0x00bd:
            r0 = move-exception
        L_0x00be:
            r1 = 0
            goto L_0x06f5
        L_0x00c1:
            java.lang.Object r0 = r3.L$10
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$9
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r7 = r3.L$8
            androidx.paging.LoadType r7 = (androidx.paging.LoadType) r7
            java.lang.Object r8 = r3.L$7
            androidx.paging.PagingSource$LoadResult r8 = (androidx.paging.PagingSource.LoadResult) r8
            java.lang.Object r9 = r3.L$6
            androidx.paging.PagingSource$LoadParams r9 = (androidx.paging.PagingSource.LoadParams) r9
            java.lang.Object r11 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r11 = (kotlin.jvm.internal.Ref.BooleanRef) r11
            java.lang.Object r12 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r12 = (kotlin.jvm.internal.Ref.ObjectRef) r12
            java.lang.Object r13 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r13 = (kotlin.jvm.internal.Ref.IntRef) r13
            java.lang.Object r14 = r3.L$2
            androidx.paging.GenerationalViewportHint r14 = (androidx.paging.GenerationalViewportHint) r14
            java.lang.Object r15 = r3.L$1
            androidx.paging.LoadType r15 = (androidx.paging.LoadType) r15
            java.lang.Object r10 = r3.L$0
            androidx.paging.PageFetcherSnapshot r10 = (androidx.paging.PageFetcherSnapshot) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r8
            r8 = r9
            r9 = r11
            r11 = r12
            r12 = r13
            r13 = r14
            r14 = r0
            goto L_0x05ac
        L_0x00f9:
            java.lang.Object r0 = r3.L$3
            androidx.paging.PageFetcherSnapshotState r0 = (androidx.paging.PageFetcherSnapshotState) r0
            java.lang.Object r4 = r3.L$2
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r5 = r3.L$1
            androidx.paging.GenerationalViewportHint r5 = (androidx.paging.GenerationalViewportHint) r5
            java.lang.Object r3 = r3.L$0
            androidx.paging.LoadType r3 = (androidx.paging.LoadType) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x010e }
            goto L_0x052c
        L_0x010e:
            r0 = move-exception
        L_0x010f:
            r7 = 0
            goto L_0x0544
        L_0x0112:
            java.lang.Object r0 = r3.L$5
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$4
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r6 = r3.L$3
            androidx.paging.PagingSource$LoadResult r6 = (androidx.paging.PagingSource.LoadResult) r6
            java.lang.Object r7 = r3.L$2
            androidx.paging.GenerationalViewportHint r7 = (androidx.paging.GenerationalViewportHint) r7
            java.lang.Object r8 = r3.L$1
            androidx.paging.LoadType r8 = (androidx.paging.LoadType) r8
            java.lang.Object r9 = r3.L$0
            androidx.paging.PageFetcherSnapshot r9 = (androidx.paging.PageFetcherSnapshot) r9
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            r0 = r3
            r12 = r7
            r3 = r8
            goto L_0x0503
        L_0x0133:
            java.lang.Object r0 = r3.L$9
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$8
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r7 = r3.L$7
            androidx.paging.PagingSource$LoadResult r7 = (androidx.paging.PagingSource.LoadResult) r7
            java.lang.Object r8 = r3.L$6
            androidx.paging.PagingSource$LoadParams r8 = (androidx.paging.PagingSource.LoadParams) r8
            java.lang.Object r9 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r9 = (kotlin.jvm.internal.Ref.BooleanRef) r9
            java.lang.Object r10 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r10 = (kotlin.jvm.internal.Ref.ObjectRef) r10
            java.lang.Object r11 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r11 = (kotlin.jvm.internal.Ref.IntRef) r11
            java.lang.Object r12 = r3.L$2
            androidx.paging.GenerationalViewportHint r12 = (androidx.paging.GenerationalViewportHint) r12
            java.lang.Object r13 = r3.L$1
            androidx.paging.LoadType r13 = (androidx.paging.LoadType) r13
            java.lang.Object r14 = r3.L$0
            androidx.paging.PageFetcherSnapshot r14 = (androidx.paging.PageFetcherSnapshot) r14
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r7
            r7 = r0
            goto L_0x0449
        L_0x0162:
            java.lang.Object r0 = r3.L$6
            androidx.paging.PagingSource$LoadParams r0 = (androidx.paging.PagingSource.LoadParams) r0
            java.lang.Object r5 = r3.L$5
            kotlin.jvm.internal.Ref$BooleanRef r5 = (kotlin.jvm.internal.Ref.BooleanRef) r5
            java.lang.Object r7 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r7 = (kotlin.jvm.internal.Ref.ObjectRef) r7
            java.lang.Object r8 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r8 = (kotlin.jvm.internal.Ref.IntRef) r8
            java.lang.Object r9 = r3.L$2
            androidx.paging.GenerationalViewportHint r9 = (androidx.paging.GenerationalViewportHint) r9
            java.lang.Object r10 = r3.L$1
            androidx.paging.LoadType r10 = (androidx.paging.LoadType) r10
            java.lang.Object r11 = r3.L$0
            androidx.paging.PageFetcherSnapshot r11 = (androidx.paging.PageFetcherSnapshot) r11
            kotlin.ResultKt.throwOnFailure(r2)
            r12 = r9
            r13 = r10
            r9 = r11
            r10 = r7
            r11 = r8
            r8 = r0
            r0 = r5
            goto L_0x03b2
        L_0x018a:
            java.lang.Object r0 = r3.L$7
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            java.lang.Object r5 = r3.L$6
            java.lang.Object r7 = r3.L$5
            kotlinx.coroutines.sync.Mutex r7 = (kotlinx.coroutines.sync.Mutex) r7
            java.lang.Object r8 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r3.L$2
            androidx.paging.GenerationalViewportHint r10 = (androidx.paging.GenerationalViewportHint) r10
            java.lang.Object r11 = r3.L$1
            androidx.paging.LoadType r11 = (androidx.paging.LoadType) r11
            java.lang.Object r12 = r3.L$0
            androidx.paging.PageFetcherSnapshot r12 = (androidx.paging.PageFetcherSnapshot) r12
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x01ad }
            goto L_0x033d
        L_0x01ad:
            r0 = move-exception
        L_0x01ae:
            r1 = 0
            goto L_0x0700
        L_0x01b1:
            java.lang.Object r0 = r3.L$7
            kotlin.jvm.internal.Ref$ObjectRef r0 = (kotlin.jvm.internal.Ref.ObjectRef) r0
            java.lang.Object r5 = r3.L$6
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r7 = r3.L$5
            androidx.paging.PageFetcherSnapshotState$Holder r7 = (androidx.paging.PageFetcherSnapshotState.Holder) r7
            java.lang.Object r8 = r3.L$4
            kotlin.jvm.internal.Ref$ObjectRef r8 = (kotlin.jvm.internal.Ref.ObjectRef) r8
            java.lang.Object r9 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r9 = (kotlin.jvm.internal.Ref.IntRef) r9
            java.lang.Object r10 = r3.L$2
            androidx.paging.GenerationalViewportHint r10 = (androidx.paging.GenerationalViewportHint) r10
            java.lang.Object r11 = r3.L$1
            androidx.paging.LoadType r11 = (androidx.paging.LoadType) r11
            java.lang.Object r12 = r3.L$0
            androidx.paging.PageFetcherSnapshot r12 = (androidx.paging.PageFetcherSnapshot) r12
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0305
        L_0x01d6:
            java.lang.Object r0 = r3.L$5
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$4
            androidx.paging.PageFetcherSnapshotState$Holder r5 = (androidx.paging.PageFetcherSnapshotState.Holder) r5
            java.lang.Object r7 = r3.L$3
            kotlin.jvm.internal.Ref$IntRef r7 = (kotlin.jvm.internal.Ref.IntRef) r7
            java.lang.Object r8 = r3.L$2
            androidx.paging.GenerationalViewportHint r8 = (androidx.paging.GenerationalViewportHint) r8
            java.lang.Object r9 = r3.L$1
            androidx.paging.LoadType r9 = (androidx.paging.LoadType) r9
            java.lang.Object r10 = r3.L$0
            androidx.paging.PageFetcherSnapshot r10 = (androidx.paging.PageFetcherSnapshot) r10
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            r0 = r9
            goto L_0x0220
        L_0x01f4:
            kotlin.ResultKt.throwOnFailure(r2)
            androidx.paging.LoadType r2 = androidx.paging.LoadType.REFRESH
            if (r0 == r2) goto L_0x0710
            kotlin.jvm.internal.Ref$IntRef r7 = new kotlin.jvm.internal.Ref$IntRef
            r7.<init>()
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r1.stateHolder
            kotlinx.coroutines.sync.Mutex r2 = r5.lock
            r3.L$0 = r1
            r3.L$1 = r0
            r8 = r19
            r3.L$2 = r8
            r3.L$3 = r7
            r3.L$4 = r5
            r3.L$5 = r2
            r9 = 1
            r3.label = r9
            r9 = 0
            java.lang.Object r10 = r2.lock(r9, r3)
            if (r10 != r4) goto L_0x021f
            return r4
        L_0x021f:
            r10 = r1
        L_0x0220:
            androidx.paging.PageFetcherSnapshotState r5 = r5.state     // Catch:{ all -> 0x070a }
            int[] r9 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0     // Catch:{ all -> 0x070a }
            int r11 = r0.ordinal()     // Catch:{ all -> 0x070a }
            r9 = r9[r11]     // Catch:{ all -> 0x070a }
            r11 = 1
            if (r9 == r11) goto L_0x0704
            r12 = 2
            if (r9 == r12) goto L_0x027a
            r12 = 3
            if (r9 == r12) goto L_0x0237
            goto L_0x02ce
        L_0x0237:
            int r9 = r5.getInitialPageIndex$paging_common()     // Catch:{ all -> 0x070a }
            androidx.paging.ViewportHint r12 = r8.getHint()     // Catch:{ all -> 0x070a }
            int r12 = r12.getOriginalPageOffsetLast()     // Catch:{ all -> 0x070a }
            int r9 = r9 + r12
            int r9 = r9 + r11
            if (r9 >= 0) goto L_0x0254
            int r11 = r7.element     // Catch:{ all -> 0x070a }
            androidx.paging.PagingConfig r12 = r10.config     // Catch:{ all -> 0x070a }
            int r12 = r12.pageSize     // Catch:{ all -> 0x070a }
            int r9 = -r9
            int r12 = r12 * r9
            int r11 = r11 + r12
            r7.element = r11     // Catch:{ all -> 0x070a }
            r9 = 0
        L_0x0254:
            java.util.List r11 = r5.getPages$paging_common()     // Catch:{ all -> 0x070a }
            int r11 = kotlin.collections.CollectionsKt.getLastIndex(r11)     // Catch:{ all -> 0x070a }
            if (r9 > r11) goto L_0x02ce
        L_0x025e:
            int r12 = r7.element     // Catch:{ all -> 0x070a }
            java.util.List r13 = r5.getPages$paging_common()     // Catch:{ all -> 0x070a }
            java.lang.Object r13 = r13.get(r9)     // Catch:{ all -> 0x070a }
            androidx.paging.PagingSource$LoadResult$Page r13 = (androidx.paging.PagingSource.LoadResult.Page) r13     // Catch:{ all -> 0x070a }
            java.util.List r13 = r13.getData()     // Catch:{ all -> 0x070a }
            int r13 = r13.size()     // Catch:{ all -> 0x070a }
            int r12 = r12 + r13
            r7.element = r12     // Catch:{ all -> 0x070a }
            if (r9 == r11) goto L_0x02ce
            int r9 = r9 + 1
            goto L_0x025e
        L_0x027a:
            int r9 = r5.getInitialPageIndex$paging_common()     // Catch:{ all -> 0x070a }
            androidx.paging.ViewportHint r11 = r8.getHint()     // Catch:{ all -> 0x070a }
            int r11 = r11.getOriginalPageOffsetFirst()     // Catch:{ all -> 0x070a }
            int r9 = r9 + r11
            r11 = 1
            int r9 = r9 - r11
            java.util.List r11 = r5.getPages$paging_common()     // Catch:{ all -> 0x070a }
            int r11 = kotlin.collections.CollectionsKt.getLastIndex(r11)     // Catch:{ all -> 0x070a }
            if (r9 <= r11) goto L_0x02af
            int r11 = r7.element     // Catch:{ all -> 0x070a }
            androidx.paging.PagingConfig r12 = r10.config     // Catch:{ all -> 0x070a }
            int r12 = r12.pageSize     // Catch:{ all -> 0x070a }
            java.util.List r13 = r5.getPages$paging_common()     // Catch:{ all -> 0x070a }
            int r13 = kotlin.collections.CollectionsKt.getLastIndex(r13)     // Catch:{ all -> 0x070a }
            int r9 = r9 - r13
            int r12 = r12 * r9
            int r11 = r11 + r12
            r7.element = r11     // Catch:{ all -> 0x070a }
            java.util.List r9 = r5.getPages$paging_common()     // Catch:{ all -> 0x070a }
            int r9 = kotlin.collections.CollectionsKt.getLastIndex(r9)     // Catch:{ all -> 0x070a }
        L_0x02af:
            if (r9 < 0) goto L_0x02ce
            r11 = 0
        L_0x02b2:
            int r12 = r7.element     // Catch:{ all -> 0x070a }
            java.util.List r13 = r5.getPages$paging_common()     // Catch:{ all -> 0x070a }
            java.lang.Object r13 = r13.get(r11)     // Catch:{ all -> 0x070a }
            androidx.paging.PagingSource$LoadResult$Page r13 = (androidx.paging.PagingSource.LoadResult.Page) r13     // Catch:{ all -> 0x070a }
            java.util.List r13 = r13.getData()     // Catch:{ all -> 0x070a }
            int r13 = r13.size()     // Catch:{ all -> 0x070a }
            int r12 = r12 + r13
            r7.element = r12     // Catch:{ all -> 0x070a }
            if (r11 == r9) goto L_0x02ce
            int r11 = r11 + 1
            goto L_0x02b2
        L_0x02ce:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x070a }
            r5 = 0
            r2.unlock(r5)
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r10.stateHolder
            kotlinx.coroutines.sync.Mutex r9 = r5.lock
            r3.L$0 = r10
            r3.L$1 = r0
            r3.L$2 = r8
            r3.L$3 = r7
            r3.L$4 = r2
            r3.L$5 = r5
            r3.L$6 = r9
            r3.L$7 = r2
            r11 = 2
            r3.label = r11
            r11 = 0
            java.lang.Object r12 = r9.lock(r11, r3)
            if (r12 != r4) goto L_0x02fa
            return r4
        L_0x02fa:
            r11 = r0
            r0 = r2
            r12 = r10
            r10 = r8
            r8 = r0
            r16 = r7
            r7 = r5
            r5 = r9
            r9 = r16
        L_0x0305:
            androidx.paging.PageFetcherSnapshotState r2 = r7.state     // Catch:{ all -> 0x06fc }
            int r7 = r10.getGenerationId()     // Catch:{ all -> 0x06fc }
            androidx.paging.ViewportHint r13 = r10.getHint()     // Catch:{ all -> 0x06fc }
            int r13 = r13.presentedItemsBeyondAnchor$paging_common(r11)     // Catch:{ all -> 0x06fc }
            int r14 = r9.element     // Catch:{ all -> 0x06fc }
            int r13 = r13 + r14
            java.lang.Object r7 = r12.nextLoadKeyOrNull(r2, r11, r7, r13)     // Catch:{ all -> 0x06fc }
            if (r7 == 0) goto L_0x0341
            r3.L$0 = r12     // Catch:{ all -> 0x06fc }
            r3.L$1 = r11     // Catch:{ all -> 0x06fc }
            r3.L$2 = r10     // Catch:{ all -> 0x06fc }
            r3.L$3 = r9     // Catch:{ all -> 0x06fc }
            r3.L$4 = r8     // Catch:{ all -> 0x06fc }
            r3.L$5 = r5     // Catch:{ all -> 0x06fc }
            r3.L$6 = r7     // Catch:{ all -> 0x06fc }
            r3.L$7 = r0     // Catch:{ all -> 0x06fc }
            r13 = 3
            r3.label = r13     // Catch:{ all -> 0x06fc }
            java.lang.Object r2 = r12.setLoading(r2, r11, r3)     // Catch:{ all -> 0x06fc }
            if (r2 != r4) goto L_0x0338
            return r4
        L_0x0338:
            r16 = r7
            r7 = r5
            r5 = r16
        L_0x033d:
            r2 = r5
            r5 = r7
        L_0x033f:
            r7 = 0
            goto L_0x0343
        L_0x0341:
            r2 = 0
            goto L_0x033f
        L_0x0343:
            r5.unlock(r7)
            r0.element = r2
            kotlin.jvm.internal.Ref$BooleanRef r0 = new kotlin.jvm.internal.Ref$BooleanRef
            r0.<init>()
        L_0x034d:
            T r2 = r8.element
            if (r2 == 0) goto L_0x06f9
            T r2 = r8.element
            androidx.paging.PagingSource$LoadParams r2 = r12.loadParams(r11, r2)
            androidx.paging.Logger r5 = androidx.paging.LoggerKt.getLOGGER()
            if (r5 == 0) goto L_0x038c
            r7 = 3
            boolean r13 = r5.isLoggable(r7)
            r7 = 1
            if (r13 != r7) goto L_0x038c
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r13 = "Start "
            r7.<init>(r13)
            r7.append(r11)
            java.lang.String r13 = " with loadKey "
            r7.append(r13)
            T r13 = r8.element
            r7.append(r13)
            java.lang.String r13 = " on "
            r7.append(r13)
            androidx.paging.PagingSource<Key, Value> r13 = r12.pagingSource
            r7.append(r13)
            java.lang.String r7 = r7.toString()
            r13 = 3
            r14 = 0
            r5.log(r13, r7, r14)
        L_0x038c:
            androidx.paging.PagingSource<Key, Value> r5 = r12.pagingSource
            r3.L$0 = r12
            r3.L$1 = r11
            r3.L$2 = r10
            r3.L$3 = r9
            r3.L$4 = r8
            r3.L$5 = r0
            r3.L$6 = r2
            r7 = 0
            r3.L$7 = r7
            r3.L$8 = r7
            r7 = 4
            r3.label = r7
            java.lang.Object r5 = r5.load(r2, r3)
            if (r5 != r4) goto L_0x03ab
            return r4
        L_0x03ab:
            r13 = r11
            r11 = r9
            r9 = r12
            r12 = r10
            r10 = r8
            r8 = r2
            r2 = r5
        L_0x03b2:
            androidx.paging.PagingSource$LoadResult r2 = (androidx.paging.PagingSource.LoadResult) r2
            boolean r5 = r2 instanceof androidx.paging.PagingSource.LoadResult.Page
            if (r5 == 0) goto L_0x04c3
            int[] r5 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0
            int r7 = r13.ordinal()
            r5 = r5[r7]
            r7 = 2
            if (r5 == r7) goto L_0x03d4
            r7 = 3
            if (r5 != r7) goto L_0x03ce
            r5 = r2
            androidx.paging.PagingSource$LoadResult$Page r5 = (androidx.paging.PagingSource.LoadResult.Page) r5
            java.lang.Object r5 = r5.getNextKey()
            goto L_0x03db
        L_0x03ce:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            r0.<init>(r6)
            throw r0
        L_0x03d4:
            r5 = r2
            androidx.paging.PagingSource$LoadResult$Page r5 = (androidx.paging.PagingSource.LoadResult.Page) r5
            java.lang.Object r5 = r5.getPrevKey()
        L_0x03db:
            androidx.paging.PagingSource<Key, Value> r7 = r9.pagingSource
            boolean r7 = r7.getKeyReuseSupported()
            if (r7 != 0) goto L_0x0422
            T r7 = r10.element
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r5, (java.lang.Object) r7)
            if (r5 != 0) goto L_0x03ec
            goto L_0x0422
        L_0x03ec:
            androidx.paging.LoadType r0 = androidx.paging.LoadType.PREPEND
            if (r13 != r0) goto L_0x03f3
            java.lang.String r0 = "prevKey"
            goto L_0x03f5
        L_0x03f3:
            java.lang.String r0 = "nextKey"
        L_0x03f5:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "The same value, "
            r2.<init>(r3)
            T r3 = r10.element
            r2.append(r3)
            java.lang.String r3 = ", was passed as the "
            r2.append(r3)
            r2.append(r0)
            java.lang.String r0 = " in two\n                            | sequential Pages loaded from a PagingSource. Re-using load keys in\n                            | PagingSource is often an error, and must be explicitly enabled by\n                            | overriding PagingSource.keyReuseSupported.\n                            "
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            r2 = 1
            r3 = 0
            java.lang.String r0 = kotlin.text.StringsKt.trimMargin$default(r0, r3, r2, r3)
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r0 = r0.toString()
            r2.<init>(r0)
            throw r2
        L_0x0422:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r7 = r5.lock
            r3.L$0 = r9
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r0
            r3.L$6 = r8
            r3.L$7 = r2
            r3.L$8 = r5
            r3.L$9 = r7
            r14 = 5
            r3.label = r14
            r14 = 0
            java.lang.Object r15 = r7.lock(r14, r3)
            if (r15 != r4) goto L_0x0447
            return r4
        L_0x0447:
            r14 = r9
            r9 = r0
        L_0x0449:
            androidx.paging.PageFetcherSnapshotState r0 = r5.state     // Catch:{ all -> 0x04bd }
            int r5 = r12.getGenerationId()     // Catch:{ all -> 0x04bd }
            r15 = r2
            androidx.paging.PagingSource$LoadResult$Page r15 = (androidx.paging.PagingSource.LoadResult.Page) r15     // Catch:{ all -> 0x04bd }
            boolean r0 = r0.insert(r5, r13, r15)     // Catch:{ all -> 0x04bd }
            r5 = 0
            r7.unlock(r5)
            if (r0 != 0) goto L_0x0477
            androidx.paging.Logger r0 = androidx.paging.LoggerKt.getLOGGER()
            if (r0 == 0) goto L_0x06f9
            r2 = 2
            boolean r3 = r0.isLoggable(r2)
            r4 = 1
            if (r3 != r4) goto L_0x06f9
            T r3 = r10.element
            java.lang.String r3 = r14.loadResultLog(r13, r3, r5)
            r0.log(r2, r3, r5)
            goto L_0x06f9
        L_0x0477:
            androidx.paging.Logger r0 = androidx.paging.LoggerKt.getLOGGER()
            r5 = 3
            if (r0 == 0) goto L_0x048f
            boolean r7 = r0.isLoggable(r5)
            r15 = 1
            if (r7 != r15) goto L_0x048f
            T r7 = r10.element
            java.lang.String r7 = r14.loadResultLog(r13, r7, r2)
            r15 = 0
            r0.log(r5, r7, r15)
        L_0x048f:
            int r0 = r11.element
            r7 = r2
            androidx.paging.PagingSource$LoadResult$Page r7 = (androidx.paging.PagingSource.LoadResult.Page) r7
            java.util.List r15 = r7.getData()
            int r15 = r15.size()
            int r0 = r0 + r15
            r11.element = r0
            androidx.paging.LoadType r0 = androidx.paging.LoadType.PREPEND
            if (r13 != r0) goto L_0x04ac
            java.lang.Object r0 = r7.getPrevKey()
            if (r0 == 0) goto L_0x04aa
            goto L_0x04ac
        L_0x04aa:
            r0 = 1
            goto L_0x04b7
        L_0x04ac:
            androidx.paging.LoadType r0 = androidx.paging.LoadType.APPEND
            if (r13 != r0) goto L_0x04b9
            java.lang.Object r0 = r7.getNextKey()
            if (r0 != 0) goto L_0x04b9
            goto L_0x04aa
        L_0x04b7:
            r9.element = r0
        L_0x04b9:
            r0 = r9
            r9 = r14
            goto L_0x056a
        L_0x04bd:
            r0 = move-exception
            r14 = 0
            r7.unlock(r14)
            throw r0
        L_0x04c3:
            r5 = 3
            r14 = 0
            boolean r7 = r2 instanceof androidx.paging.PagingSource.LoadResult.Error
            if (r7 == 0) goto L_0x0548
            androidx.paging.Logger r0 = androidx.paging.LoggerKt.getLOGGER()
            if (r0 == 0) goto L_0x04e0
            r5 = 2
            boolean r6 = r0.isLoggable(r5)
            r7 = 1
            if (r6 != r7) goto L_0x04e0
            T r6 = r10.element
            java.lang.String r6 = r9.loadResultLog(r13, r6, r2)
            r0.log(r5, r6, r14)
        L_0x04e0:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r0 = r5.lock
            r3.L$0 = r9
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r2
            r3.L$4 = r5
            r3.L$5 = r0
            r6 = 0
            r3.L$6 = r6
            r7 = 6
            r3.label = r7
            java.lang.Object r7 = r0.lock(r6, r3)
            if (r7 != r4) goto L_0x04ff
            return r4
        L_0x04ff:
            r6 = r2
            r2 = r0
            r0 = r3
            r3 = r13
        L_0x0503:
            androidx.paging.PageFetcherSnapshotState r5 = r5.state     // Catch:{ all -> 0x0540 }
            androidx.paging.LoadState$Error r7 = new androidx.paging.LoadState$Error     // Catch:{ all -> 0x0540 }
            androidx.paging.PagingSource$LoadResult$Error r6 = (androidx.paging.PagingSource.LoadResult.Error) r6     // Catch:{ all -> 0x0540 }
            java.lang.Throwable r6 = r6.getThrowable()     // Catch:{ all -> 0x0540 }
            r7.<init>(r6)     // Catch:{ all -> 0x0540 }
            r0.L$0 = r3     // Catch:{ all -> 0x0540 }
            r0.L$1 = r12     // Catch:{ all -> 0x0540 }
            r0.L$2 = r2     // Catch:{ all -> 0x0540 }
            r0.L$3 = r5     // Catch:{ all -> 0x0540 }
            r6 = 0
            r0.L$4 = r6     // Catch:{ all -> 0x0540 }
            r0.L$5 = r6     // Catch:{ all -> 0x0540 }
            r6 = 7
            r0.label = r6     // Catch:{ all -> 0x0540 }
            java.lang.Object r0 = r9.setError(r5, r3, r7, r0)     // Catch:{ all -> 0x0540 }
            if (r0 != r4) goto L_0x0529
            return r4
        L_0x0529:
            r4 = r2
            r0 = r5
            r5 = r12
        L_0x052c:
            java.util.Map r0 = r0.getFailedHintsByLoadType$paging_common()     // Catch:{ all -> 0x010e }
            androidx.paging.ViewportHint r2 = r5.getHint()     // Catch:{ all -> 0x010e }
            r0.put(r3, r2)     // Catch:{ all -> 0x010e }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x010e }
            r2 = 0
            r4.unlock(r2)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0540:
            r0 = move-exception
            r4 = r2
            goto L_0x010f
        L_0x0544:
            r4.unlock(r7)
            throw r0
        L_0x0548:
            r7 = r14
            boolean r14 = r2 instanceof androidx.paging.PagingSource.LoadResult.Invalid
            if (r14 == 0) goto L_0x056a
            androidx.paging.Logger r0 = androidx.paging.LoggerKt.getLOGGER()
            if (r0 == 0) goto L_0x0564
            r3 = 2
            boolean r4 = r0.isLoggable(r3)
            r14 = 1
            if (r4 != r14) goto L_0x0564
            T r4 = r10.element
            java.lang.String r2 = r9.loadResultLog(r13, r4, r2)
            r0.log(r3, r2, r7)
        L_0x0564:
            r9.onInvalidLoad()
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x056a:
            r14 = 1
            int[] r7 = androidx.paging.PageFetcherSnapshot.WhenMappings.$EnumSwitchMapping$0
            int r15 = r13.ordinal()
            r7 = r7[r15]
            r15 = 2
            if (r7 != r15) goto L_0x0579
            androidx.paging.LoadType r7 = androidx.paging.LoadType.APPEND
            goto L_0x057b
        L_0x0579:
            androidx.paging.LoadType r7 = androidx.paging.LoadType.PREPEND
        L_0x057b:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r5 = r9.stateHolder
            kotlinx.coroutines.sync.Mutex r14 = r5.lock
            r3.L$0 = r9
            r3.L$1 = r13
            r3.L$2 = r12
            r3.L$3 = r11
            r3.L$4 = r10
            r3.L$5 = r0
            r3.L$6 = r8
            r3.L$7 = r2
            r3.L$8 = r7
            r3.L$9 = r5
            r3.L$10 = r14
            r15 = 8
            r3.label = r15
            r18 = r0
            r15 = 0
            java.lang.Object r0 = r14.lock(r15, r3)
            if (r0 != r4) goto L_0x05a5
            return r4
        L_0x05a5:
            r15 = r13
            r13 = r12
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r18
        L_0x05ac:
            androidx.paging.PageFetcherSnapshotState r0 = r5.state     // Catch:{ all -> 0x06f1 }
            androidx.paging.ViewportHint r5 = r13.getHint()     // Catch:{ all -> 0x06f1 }
            androidx.paging.PageEvent$Drop r5 = r0.dropEventOrNull(r7, r5)     // Catch:{ all -> 0x06f1 }
            if (r5 == 0) goto L_0x05ec
            r0.drop(r5)     // Catch:{ all -> 0x06f1 }
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r7 = r10.pageEventCh     // Catch:{ all -> 0x06f1 }
            r3.L$0 = r10     // Catch:{ all -> 0x06f1 }
            r3.L$1 = r15     // Catch:{ all -> 0x06f1 }
            r3.L$2 = r13     // Catch:{ all -> 0x06f1 }
            r3.L$3 = r12     // Catch:{ all -> 0x06f1 }
            r3.L$4 = r11     // Catch:{ all -> 0x06f1 }
            r3.L$5 = r9     // Catch:{ all -> 0x06f1 }
            r3.L$6 = r8     // Catch:{ all -> 0x06f1 }
            r3.L$7 = r2     // Catch:{ all -> 0x06f1 }
            r3.L$8 = r14     // Catch:{ all -> 0x06f1 }
            r3.L$9 = r0     // Catch:{ all -> 0x06f1 }
            r1 = 0
            r3.L$10 = r1     // Catch:{ all -> 0x06f1 }
            r1 = 9
            r3.label = r1     // Catch:{ all -> 0x06f1 }
            java.lang.Object r1 = r7.send(r5, r3)     // Catch:{ all -> 0x06f1 }
            if (r1 != r4) goto L_0x05e1
            return r4
        L_0x05e1:
            r7 = r2
            r5 = r14
            r14 = r15
            r15 = r10
        L_0x05e5:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bd }
            r2 = r7
            r7 = r8
            r10 = r15
            r15 = r14
            goto L_0x05ee
        L_0x05ec:
            r7 = r8
            r5 = r14
        L_0x05ee:
            int r1 = r13.getGenerationId()     // Catch:{ all -> 0x00bd }
            androidx.paging.ViewportHint r8 = r13.getHint()     // Catch:{ all -> 0x00bd }
            int r8 = r8.presentedItemsBeyondAnchor$paging_common(r15)     // Catch:{ all -> 0x00bd }
            int r14 = r12.element     // Catch:{ all -> 0x00bd }
            int r8 = r8 + r14
            java.lang.Object r1 = r10.nextLoadKeyOrNull(r0, r15, r1, r8)     // Catch:{ all -> 0x00bd }
            r11.element = r1     // Catch:{ all -> 0x00bd }
            T r1 = r11.element     // Catch:{ all -> 0x00bd }
            if (r1 != 0) goto L_0x062d
            androidx.paging.MutableLoadStateCollection r1 = r0.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x00bd }
            androidx.paging.LoadState r1 = r1.get(r15)     // Catch:{ all -> 0x00bd }
            boolean r1 = r1 instanceof androidx.paging.LoadState.Error     // Catch:{ all -> 0x00bd }
            if (r1 != 0) goto L_0x062d
            androidx.paging.MutableLoadStateCollection r1 = r0.getSourceLoadStates$paging_common()     // Catch:{ all -> 0x00bd }
            boolean r8 = r9.element     // Catch:{ all -> 0x00bd }
            if (r8 == 0) goto L_0x0622
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x00bd }
            androidx.paging.LoadState$NotLoading r8 = r8.getComplete$paging_common()     // Catch:{ all -> 0x00bd }
            goto L_0x0628
        L_0x0622:
            androidx.paging.LoadState$NotLoading$Companion r8 = androidx.paging.LoadState.NotLoading.Companion     // Catch:{ all -> 0x00bd }
            androidx.paging.LoadState$NotLoading r8 = r8.getIncomplete$paging_common()     // Catch:{ all -> 0x00bd }
        L_0x0628:
            androidx.paging.LoadState r8 = (androidx.paging.LoadState) r8     // Catch:{ all -> 0x00bd }
            r1.set(r15, r8)     // Catch:{ all -> 0x00bd }
        L_0x062d:
            r1 = r2
            androidx.paging.PagingSource$LoadResult$Page r1 = (androidx.paging.PagingSource.LoadResult.Page) r1     // Catch:{ all -> 0x00bd }
            androidx.paging.PageEvent r0 = r0.toPageEvent$paging_common(r1, r15)     // Catch:{ all -> 0x00bd }
            kotlinx.coroutines.channels.Channel<androidx.paging.PageEvent<Value>> r1 = r10.pageEventCh     // Catch:{ all -> 0x00bd }
            r3.L$0 = r10     // Catch:{ all -> 0x00bd }
            r3.L$1 = r15     // Catch:{ all -> 0x00bd }
            r3.L$2 = r13     // Catch:{ all -> 0x00bd }
            r3.L$3 = r12     // Catch:{ all -> 0x00bd }
            r3.L$4 = r11     // Catch:{ all -> 0x00bd }
            r3.L$5 = r9     // Catch:{ all -> 0x00bd }
            r3.L$6 = r7     // Catch:{ all -> 0x00bd }
            r3.L$7 = r2     // Catch:{ all -> 0x00bd }
            r3.L$8 = r5     // Catch:{ all -> 0x00bd }
            r8 = 0
            r3.L$9 = r8     // Catch:{ all -> 0x00bd }
            r3.L$10 = r8     // Catch:{ all -> 0x00bd }
            r8 = 10
            r3.label = r8     // Catch:{ all -> 0x00bd }
            java.lang.Object r0 = r1.send(r0, r3)     // Catch:{ all -> 0x00bd }
            if (r0 != r4) goto L_0x0658
            return r4
        L_0x0658:
            r0 = r2
            r8 = r9
            r14 = r10
            r9 = r11
            r10 = r13
            r11 = r15
        L_0x065e:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x00bd }
            r1 = 0
            r5.unlock(r1)
            boolean r1 = r7 instanceof androidx.paging.PagingSource.LoadParams.Prepend
            if (r1 == 0) goto L_0x0673
            r1 = r0
            androidx.paging.PagingSource$LoadResult$Page r1 = (androidx.paging.PagingSource.LoadResult.Page) r1
            java.lang.Object r1 = r1.getPrevKey()
            if (r1 != 0) goto L_0x0673
            r5 = 1
            goto L_0x0674
        L_0x0673:
            r5 = 0
        L_0x0674:
            boolean r1 = r7 instanceof androidx.paging.PagingSource.LoadParams.Append
            if (r1 == 0) goto L_0x0682
            androidx.paging.PagingSource$LoadResult$Page r0 = (androidx.paging.PagingSource.LoadResult.Page) r0
            java.lang.Object r0 = r0.getNextKey()
            if (r0 != 0) goto L_0x0682
            r0 = 1
            goto L_0x0683
        L_0x0682:
            r0 = 0
        L_0x0683:
            androidx.paging.RemoteMediatorConnection<Key, Value> r1 = r14.remoteMediatorConnection
            if (r1 == 0) goto L_0x06e9
            if (r5 != 0) goto L_0x068b
            if (r0 == 0) goto L_0x06e9
        L_0x068b:
            androidx.paging.PageFetcherSnapshotState$Holder<Key, Value> r13 = r14.stateHolder
            kotlinx.coroutines.sync.Mutex r1 = r13.lock
            r3.L$0 = r14
            r3.L$1 = r11
            r3.L$2 = r10
            r3.L$3 = r12
            r3.L$4 = r9
            r3.L$5 = r8
            r3.L$6 = r13
            r3.L$7 = r1
            r2 = 0
            r3.L$8 = r2
            r3.I$0 = r5
            r3.I$1 = r0
            r7 = 11
            r3.label = r7
            java.lang.Object r7 = r1.lock(r2, r3)
            if (r7 != r4) goto L_0x06b3
            return r4
        L_0x06b3:
            r16 = r14
            r14 = r8
            r8 = r9
            r9 = r12
            r12 = r16
        L_0x06ba:
            androidx.paging.PageFetcherSnapshotState r2 = r13.state     // Catch:{ all -> 0x06e3 }
            androidx.paging.HintHandler r7 = r12.hintHandler     // Catch:{ all -> 0x06e3 }
            androidx.paging.ViewportHint$Access r7 = r7.getLastAccessHint()     // Catch:{ all -> 0x06e3 }
            androidx.paging.PagingState r2 = r2.currentPagingState$paging_common(r7)     // Catch:{ all -> 0x06e3 }
            r7 = 0
            r1.unlock(r7)
            if (r5 == 0) goto L_0x06d5
            androidx.paging.RemoteMediatorConnection<Key, Value> r1 = r12.remoteMediatorConnection
            androidx.paging.LoadType r5 = androidx.paging.LoadType.PREPEND
            r1.requestLoad(r5, r2)
        L_0x06d5:
            if (r0 == 0) goto L_0x06de
            androidx.paging.RemoteMediatorConnection<Key, Value> r0 = r12.remoteMediatorConnection
            androidx.paging.LoadType r1 = androidx.paging.LoadType.APPEND
            r0.requestLoad(r1, r2)
        L_0x06de:
            r1 = r17
            r0 = r14
            goto L_0x034d
        L_0x06e3:
            r0 = move-exception
            r2 = 0
            r1.unlock(r2)
            throw r0
        L_0x06e9:
            r1 = r17
            r0 = r8
            r8 = r9
            r9 = r12
            r12 = r14
            goto L_0x034d
        L_0x06f1:
            r0 = move-exception
            r5 = r14
            goto L_0x00be
        L_0x06f5:
            r5.unlock(r1)
            throw r0
        L_0x06f9:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x06fc:
            r0 = move-exception
            r7 = r5
            goto L_0x01ae
        L_0x0700:
            r7.unlock(r1)
            throw r0
        L_0x0704:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException     // Catch:{ all -> 0x070a }
            r0.<init>(r6)     // Catch:{ all -> 0x070a }
            throw r0     // Catch:{ all -> 0x070a }
        L_0x070a:
            r0 = move-exception
            r1 = 0
            r2.unlock(r1)
            throw r0
        L_0x0710:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r6.toString()
            r0.<init>(r1)
            goto L_0x071b
        L_0x071a:
            throw r0
        L_0x071b:
            goto L_0x071a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.PageFetcherSnapshot.doLoad(androidx.paging.LoadType, androidx.paging.GenerationalViewportHint, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final String loadResultLog(LoadType loadType, Key key, PagingSource.LoadResult<Key, Value> loadResult) {
        if (loadResult == null) {
            return "End " + loadType + " with loadkey " + key + ". Load CANCELLED.";
        }
        return "End " + loadType + " with loadKey " + key + ". Returned " + loadResult;
    }

    /* access modifiers changed from: private */
    public final Object setLoading(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, Continuation<? super Unit> continuation) {
        if (Intrinsics.areEqual((Object) pageFetcherSnapshotState.getSourceLoadStates$paging_common().get(loadType), (Object) LoadState.Loading.INSTANCE)) {
            return Unit.INSTANCE;
        }
        pageFetcherSnapshotState.getSourceLoadStates$paging_common().set(loadType, LoadState.Loading.INSTANCE);
        Object send = this.pageEventCh.send(new PageEvent.LoadStateUpdate(pageFetcherSnapshotState.getSourceLoadStates$paging_common().snapshot(), (LoadStates) null), continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    /* access modifiers changed from: private */
    public final Object setError(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, LoadState.Error error, Continuation<? super Unit> continuation) {
        if (Intrinsics.areEqual((Object) pageFetcherSnapshotState.getSourceLoadStates$paging_common().get(loadType), (Object) error)) {
            return Unit.INSTANCE;
        }
        pageFetcherSnapshotState.getSourceLoadStates$paging_common().set(loadType, error);
        Object send = this.pageEventCh.send(new PageEvent.LoadStateUpdate(pageFetcherSnapshotState.getSourceLoadStates$paging_common().snapshot(), (LoadStates) null), continuation);
        return send == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? send : Unit.INSTANCE;
    }

    private final Key nextLoadKeyOrNull(PageFetcherSnapshotState<Key, Value> pageFetcherSnapshotState, LoadType loadType, int i, int i2) {
        if (i != pageFetcherSnapshotState.generationId$paging_common(loadType) || (pageFetcherSnapshotState.getSourceLoadStates$paging_common().get(loadType) instanceof LoadState.Error) || i2 >= this.config.prefetchDistance) {
            return null;
        }
        if (loadType == LoadType.PREPEND) {
            return ((PagingSource.LoadResult.Page) CollectionsKt.first(pageFetcherSnapshotState.getPages$paging_common())).getPrevKey();
        }
        return ((PagingSource.LoadResult.Page) CollectionsKt.last(pageFetcherSnapshotState.getPages$paging_common())).getNextKey();
    }

    private final void onInvalidLoad() {
        close();
        this.pagingSource.invalidate();
    }

    /* access modifiers changed from: private */
    public final Object collectAsGenerationalViewportHints(Flow<Integer> flow, LoadType loadType, Continuation<? super Unit> continuation) {
        Object collect = FlowKt.conflate(FlowExtKt.simpleRunningReduce(FlowExtKt.simpleTransformLatest(flow, new PageFetcherSnapshot$collectAsGenerationalViewportHints$$inlined$simpleFlatMapLatest$1((Continuation) null, this, loadType)), new PageFetcherSnapshot$collectAsGenerationalViewportHints$3(loadType, (Continuation<? super PageFetcherSnapshot$collectAsGenerationalViewportHints$3>) null))).collect(new PageFetcherSnapshot$collectAsGenerationalViewportHints$4(this, loadType), continuation);
        return collect == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? collect : Unit.INSTANCE;
    }
}
