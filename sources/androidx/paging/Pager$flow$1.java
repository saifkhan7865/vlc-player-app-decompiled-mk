package androidx.paging;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.SuspendFunction;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Pager.kt */
/* synthetic */ class Pager$flow$1 extends FunctionReferenceImpl implements Function1<Continuation<? super PagingSource<Key, Value>>, Object>, SuspendFunction {
    Pager$flow$1(Object obj) {
        super(1, obj, SuspendingPagingSourceFactory.class, "create", "create(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", 0);
    }

    public final Object invoke(Continuation<? super PagingSource<Key, Value>> continuation) {
        return ((SuspendingPagingSourceFactory) this.receiver).create(continuation);
    }
}
