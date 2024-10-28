package androidx.paging;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "Landroidx/paging/PagingSource;", "Key", "Value", ""}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.Pager$flow$2", f = "Pager.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Pager.kt */
final class Pager$flow$2 extends SuspendLambda implements Function1<Continuation<? super PagingSource<Key, Value>>, Object> {
    final /* synthetic */ Function0<PagingSource<Key, Value>> $pagingSourceFactory;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    Pager$flow$2(Function0<? extends PagingSource<Key, Value>> function0, Continuation<? super Pager$flow$2> continuation) {
        super(1, continuation);
        this.$pagingSourceFactory = function0;
    }

    public final Continuation<Unit> create(Continuation<?> continuation) {
        return new Pager$flow$2(this.$pagingSourceFactory, continuation);
    }

    public final Object invoke(Continuation<? super PagingSource<Key, Value>> continuation) {
        return ((Pager$flow$2) create(continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            return this.$pagingSourceFactory.invoke();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
