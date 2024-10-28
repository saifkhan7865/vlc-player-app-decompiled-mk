package org.videolan.resources.util;

import androidx.lifecycle.LiveData;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.TimeoutKt;

@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$observeLiveDataUntil$2", f = "Extensions.kt", i = {0}, l = {338}, m = "invokeSuspend", n = {"finalValue$iv"}, s = {"L$0"})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$observeLiveDataUntil$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function1<T, Boolean> $block;
    final /* synthetic */ LiveData<T> $data;
    final /* synthetic */ long $timeout;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$observeLiveDataUntil$2(long j, LiveData<T> liveData, Function1<? super T, Boolean> function1, Continuation<? super ExtensionsKt$observeLiveDataUntil$2> continuation) {
        super(2, continuation);
        this.$timeout = j;
        this.$data = liveData;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$observeLiveDataUntil$2(this.$timeout, this.$data, this.$block, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
        return ((ExtensionsKt$observeLiveDataUntil$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Ref.ObjectRef objectRef;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Ref.BooleanRef booleanRef = new Ref.BooleanRef();
            long j = this.$timeout;
            LiveData<T> liveData = this.$data;
            Function1<T, Boolean> function1 = this.$block;
            Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
            this.L$0 = objectRef2;
            this.label = 1;
            if (TimeoutKt.withTimeoutOrNull(j, new ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1(objectRef2, (Continuation) null, liveData, booleanRef, function1), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef2;
        } else if (i == 1) {
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return objectRef.element;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        long j = this.$timeout;
        LiveData<T> liveData = this.$data;
        Function1<T, Boolean> function1 = this.$block;
        Ref.ObjectRef objectRef = new Ref.ObjectRef();
        InlineMarker.mark(0);
        TimeoutKt.withTimeoutOrNull(j, new ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1(objectRef, (Continuation) null, liveData, booleanRef, function1), this);
        InlineMarker.mark(1);
        return objectRef.element;
    }
}
