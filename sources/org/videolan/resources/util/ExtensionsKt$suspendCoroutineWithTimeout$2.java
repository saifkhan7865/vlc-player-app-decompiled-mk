package org.videolan.resources.util;

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

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$suspendCoroutineWithTimeout$2", f = "Extensions.kt", i = {0}, l = {337}, m = "invokeSuspend", n = {"block$iv"}, s = {"L$0"})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$suspendCoroutineWithTimeout$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<CancellableContinuation<? super T>, Unit> $block;
    final /* synthetic */ Ref.ObjectRef<T> $finalValue;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$suspendCoroutineWithTimeout$2(Ref.ObjectRef<T> objectRef, Function1<? super CancellableContinuation<? super T>, Unit> function1, Continuation<? super ExtensionsKt$suspendCoroutineWithTimeout$2> continuation) {
        super(2, continuation);
        this.$finalValue = objectRef;
        this.$block = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$suspendCoroutineWithTimeout$2(this.$finalValue, this.$block, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$suspendCoroutineWithTimeout$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(T t) {
        Ref.ObjectRef<T> objectRef;
        T coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(t);
            Ref.ObjectRef<T> objectRef2 = this.$finalValue;
            Function1<CancellableContinuation<? super T>, Unit> function1 = this.$block;
            this.L$0 = function1;
            this.L$1 = objectRef2;
            this.label = 1;
            Continuation continuation = this;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            function1.invoke(cancellableContinuationImpl);
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
            objectRef = (Ref.ObjectRef) this.L$1;
            Function1 function12 = (Function1) this.L$0;
            ResultKt.throwOnFailure(t);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        objectRef.element = t;
        return Unit.INSTANCE;
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        Ref.ObjectRef<T> objectRef = this.$finalValue;
        Function1<CancellableContinuation<? super T>, Unit> function1 = this.$block;
        InlineMarker.mark(0);
        Continuation continuation = this;
        Continuation continuation2 = continuation;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        function1.invoke(cancellableContinuationImpl);
        T result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        objectRef.element = result;
        return Unit.INSTANCE;
    }
}
