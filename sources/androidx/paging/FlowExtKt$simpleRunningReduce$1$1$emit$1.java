package androidx.paging;

import androidx.paging.FlowExtKt$simpleRunningReduce$1;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "androidx.paging.FlowExtKt$simpleRunningReduce$1$1", f = "FlowExt.kt", i = {0}, l = {74, 77}, m = "emit", n = {"this"}, s = {"L$0"})
/* compiled from: FlowExt.kt */
final class FlowExtKt$simpleRunningReduce$1$1$emit$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowExtKt$simpleRunningReduce$1.AnonymousClass1<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowExtKt$simpleRunningReduce$1$1$emit$1(FlowExtKt$simpleRunningReduce$1.AnonymousClass1<? super T> r1, Continuation<? super FlowExtKt$simpleRunningReduce$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = r1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit(null, this);
    }
}
