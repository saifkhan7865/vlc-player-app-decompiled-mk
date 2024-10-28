package org.videolan.resources.util;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$waitForML$2", f = "Extensions.kt", i = {0, 0}, l = {337}, m = "invokeSuspend", n = {"$this$withContext", "ml"}, s = {"L$0", "L$1"})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$waitForML$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    public ExtensionsKt$waitForML$2(Continuation<? super ExtensionsKt$waitForML$2> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ExtensionsKt$waitForML$2 extensionsKt$waitForML$2 = new ExtensionsKt$waitForML$2(continuation);
        extensionsKt$waitForML$2.L$0 = obj;
        return extensionsKt$waitForML$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$waitForML$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Medialibrary instance = Medialibrary.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
            if (!instance.isStarted()) {
                this.L$0 = coroutineScope;
                this.L$1 = instance;
                this.label = 1;
                Continuation continuation = this;
                CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
                cancellableContinuationImpl.initCancellability();
                CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
                ExtensionsKt$waitForML$2$1$listener$1 extensionsKt$waitForML$2$1$listener$1 = new ExtensionsKt$waitForML$2$1$listener$1(cancellableContinuation, coroutineScope, instance);
                cancellableContinuation.invokeOnCancellation(new ExtensionsKt$waitForML$2$1$1(instance, extensionsKt$waitForML$2$1$listener$1));
                instance.addOnMedialibraryReadyListener(extensionsKt$waitForML$2$1$listener$1);
                Object result = cancellableContinuationImpl.getResult();
                if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                    DebugProbesKt.probeCoroutineSuspended(continuation);
                }
                if (result == coroutine_suspended) {
                    return coroutine_suspended;
                }
            }
        } else if (i == 1) {
            Medialibrary medialibrary = (Medialibrary) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
