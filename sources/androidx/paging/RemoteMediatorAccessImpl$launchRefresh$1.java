package androidx.paging;

import androidx.paging.RemoteMediator;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "Key", "", "Value", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1", f = "RemoteMediatorAccessor.kt", i = {0}, l = {314}, m = "invokeSuspend", n = {"launchAppendPrepend"}, s = {"L$0"})
/* compiled from: RemoteMediatorAccessor.kt */
final class RemoteMediatorAccessImpl$launchRefresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    Object L$0;
    int label;
    final /* synthetic */ RemoteMediatorAccessImpl<Key, Value> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteMediatorAccessImpl$launchRefresh$1(RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl, Continuation<? super RemoteMediatorAccessImpl$launchRefresh$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteMediatorAccessImpl;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteMediatorAccessImpl$launchRefresh$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteMediatorAccessImpl$launchRefresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Ref.BooleanRef booleanRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
            SingleRunner access$getIsolationRunner$p = this.this$0.isolationRunner;
            final RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl = this.this$0;
            this.L$0 = booleanRef2;
            this.label = 1;
            if (access$getIsolationRunner$p.runInIsolation(2, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            booleanRef = booleanRef2;
        } else if (i == 1) {
            booleanRef = (Ref.BooleanRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        if (booleanRef.element) {
            this.this$0.launchBoundary();
        }
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0003\"\b\b\u0001\u0010\u0004*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "Key", "", "Value"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1", f = "RemoteMediatorAccessor.kt", i = {}, l = {321}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: androidx.paging.RemoteMediatorAccessImpl$launchRefresh$1$1  reason: invalid class name */
    /* compiled from: RemoteMediatorAccessor.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function1<Continuation<? super Unit>, Object> {
        Object L$0;
        Object L$1;
        int label;

        public final Continuation<Unit> create(Continuation<?> continuation) {
            return new AnonymousClass1(remoteMediatorAccessImpl, booleanRef2, continuation);
        }

        public final Object invoke(Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            RemoteMediatorAccessImpl<Key, Value> remoteMediatorAccessImpl;
            Ref.BooleanRef booleanRef;
            boolean z;
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                PagingState pagingState = (PagingState) remoteMediatorAccessImpl.accessorState.use(RemoteMediatorAccessImpl$launchRefresh$1$1$pendingPagingState$1.INSTANCE);
                if (pagingState != null) {
                    remoteMediatorAccessImpl = remoteMediatorAccessImpl;
                    Ref.BooleanRef booleanRef2 = booleanRef2;
                    RemoteMediator access$getRemoteMediator$p = remoteMediatorAccessImpl.remoteMediator;
                    LoadType loadType = LoadType.REFRESH;
                    this.L$0 = remoteMediatorAccessImpl;
                    this.L$1 = booleanRef2;
                    this.label = 1;
                    obj = access$getRemoteMediator$p.load(loadType, pagingState, this);
                    if (obj == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                    booleanRef = booleanRef2;
                }
                return Unit.INSTANCE;
            } else if (i == 1) {
                booleanRef = (Ref.BooleanRef) this.L$1;
                remoteMediatorAccessImpl = (RemoteMediatorAccessImpl) this.L$0;
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            RemoteMediator.MediatorResult mediatorResult = (RemoteMediator.MediatorResult) obj;
            if (mediatorResult instanceof RemoteMediator.MediatorResult.Success) {
                z = ((Boolean) remoteMediatorAccessImpl.accessorState.use(new RemoteMediatorAccessImpl$launchRefresh$1$1$1$1(mediatorResult))).booleanValue();
            } else if (mediatorResult instanceof RemoteMediator.MediatorResult.Error) {
                z = ((Boolean) remoteMediatorAccessImpl.accessorState.use(new RemoteMediatorAccessImpl$launchRefresh$1$1$1$2(mediatorResult))).booleanValue();
            } else {
                throw new NoWhenBranchMatchedException();
            }
            booleanRef.element = z;
            return Unit.INSTANCE;
        }
    }
}
