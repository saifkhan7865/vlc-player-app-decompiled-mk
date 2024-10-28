package androidx.paging;

import androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 176)
@DebugMetadata(c = "androidx.paging.FlowExtKt$combineWithoutBatching$2$1$1$1", f = "FlowExt.kt", i = {}, l = {149, 152}, m = "emit", n = {}, s = {})
/* compiled from: FlowExt.kt */
final class FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1 extends ContinuationImpl {
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ FlowExtKt$combineWithoutBatching$2$1$1.AnonymousClass1<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1(FlowExtKt$combineWithoutBatching$2$1$1.AnonymousClass1<? super T> r1, Continuation<? super FlowExtKt$combineWithoutBatching$2$1$1$1$emit$1> continuation) {
        super(continuation);
        this.this$0 = r1;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.emit((Object) null, this);
    }
}
