package androidx.paging;

import androidx.paging.PagingSource;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "K", "", "V", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.LegacyPageFetcher$scheduleLoad$1", f = "LegacyPageFetcher.kt", i = {0}, l = {53}, m = "invokeSuspend", n = {"$this$launch"}, s = {"L$0"})
/* compiled from: LegacyPageFetcher.kt */
final class LegacyPageFetcher$scheduleLoad$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ PagingSource.LoadParams<K> $params;
    final /* synthetic */ LoadType $type;
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ LegacyPageFetcher<K, V> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    LegacyPageFetcher$scheduleLoad$1(LegacyPageFetcher<K, V> legacyPageFetcher, PagingSource.LoadParams<K> loadParams, LoadType loadType, Continuation<? super LegacyPageFetcher$scheduleLoad$1> continuation) {
        super(2, continuation);
        this.this$0 = legacyPageFetcher;
        this.$params = loadParams;
        this.$type = loadType;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        LegacyPageFetcher$scheduleLoad$1 legacyPageFetcher$scheduleLoad$1 = new LegacyPageFetcher$scheduleLoad$1(this.this$0, this.$params, this.$type, continuation);
        legacyPageFetcher$scheduleLoad$1.L$0 = obj;
        return legacyPageFetcher$scheduleLoad$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((LegacyPageFetcher$scheduleLoad$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        CoroutineScope coroutineScope;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            this.L$0 = coroutineScope2;
            this.label = 1;
            Object load = this.this$0.getSource().load(this.$params, this);
            if (load == coroutine_suspended) {
                return coroutine_suspended;
            }
            coroutineScope = coroutineScope2;
            obj = load;
        } else if (i == 1) {
            coroutineScope = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final PagingSource.LoadResult loadResult = (PagingSource.LoadResult) obj;
        if (this.this$0.getSource().getInvalid()) {
            this.this$0.detach();
            return Unit.INSTANCE;
        }
        final LegacyPageFetcher<K, V> legacyPageFetcher = this.this$0;
        final LoadType loadType = this.$type;
        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, this.this$0.notifyDispatcher, (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "K", "", "V", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.paging.LegacyPageFetcher$scheduleLoad$1$1", f = "LegacyPageFetcher.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.paging.LegacyPageFetcher$scheduleLoad$1$1  reason: invalid class name */
    /* compiled from: LegacyPageFetcher.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(loadResult, legacyPageFetcher, loadType, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                PagingSource.LoadResult<K, V> loadResult = loadResult;
                if (loadResult instanceof PagingSource.LoadResult.Page) {
                    legacyPageFetcher.onLoadSuccess(loadType, (PagingSource.LoadResult.Page) loadResult);
                } else if (loadResult instanceof PagingSource.LoadResult.Error) {
                    legacyPageFetcher.onLoadError(loadType, ((PagingSource.LoadResult.Error) loadResult).getThrowable());
                } else if (loadResult instanceof PagingSource.LoadResult.Invalid) {
                    legacyPageFetcher.onLoadInvalid();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
