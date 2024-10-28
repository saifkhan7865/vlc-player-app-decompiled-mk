package org.videolan.resources.util;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$suspendCoroutineWithTimeout$2"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1", f = "Extensions.kt", i = {}, l = {337}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1 $block$inlined;
    final /* synthetic */ LiveData $data$inlined;
    final /* synthetic */ Ref.ObjectRef $finalValue;
    final /* synthetic */ Ref.BooleanRef $init$inlined;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1(Ref.ObjectRef objectRef, Continuation continuation, LiveData liveData, Ref.BooleanRef booleanRef, Function1 function1) {
        super(2, continuation);
        this.$finalValue = objectRef;
        this.$data$inlined = liveData;
        this.$init$inlined = booleanRef;
        this.$block$inlined = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1(this.$finalValue, continuation, this.$data$inlined, this.$init$inlined, this.$block$inlined);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$observeLiveDataUntil$2$invokeSuspend$$inlined$suspendCoroutineWithTimeout$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(T t) {
        Ref.ObjectRef objectRef;
        T coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(t);
            Ref.ObjectRef objectRef2 = this.$finalValue;
            this.L$0 = objectRef2;
            this.label = 1;
            Continuation continuation = this;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
            objectRef3.element = new ExtensionsKt$observeLiveDataUntil$2$1$1(this.$data$inlined.getValue(), this.$init$inlined, this.$block$inlined, cancellableContinuation, objectRef3, this.$data$inlined);
            this.$data$inlined.observeForever((Observer) objectRef3.element);
            cancellableContinuation.invokeOnCancellation(new ExtensionsKt$observeLiveDataUntil$2$1$2(objectRef3, this.$data$inlined));
            T result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            if (result == coroutine_suspended) {
                return coroutine_suspended;
            }
            objectRef = objectRef2;
            t = result;
        } else if (i == 1) {
            objectRef = (Ref.ObjectRef) this.L$0;
            ResultKt.throwOnFailure(t);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef.element = t;
        return Unit.INSTANCE;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        Ref.ObjectRef objectRef = this.$finalValue;
        InlineMarker.mark(0);
        Continuation continuation = this;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        objectRef2.element = new ExtensionsKt$observeLiveDataUntil$2$1$1(this.$data$inlined.getValue(), this.$init$inlined, this.$block$inlined, cancellableContinuation, objectRef2, this.$data$inlined);
        this.$data$inlined.observeForever((Observer) objectRef2.element);
        cancellableContinuation.invokeOnCancellation(new ExtensionsKt$observeLiveDataUntil$2$1$2(objectRef2, this.$data$inlined));
        T result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        objectRef.element = result;
        return Unit.INSTANCE;
    }
}
