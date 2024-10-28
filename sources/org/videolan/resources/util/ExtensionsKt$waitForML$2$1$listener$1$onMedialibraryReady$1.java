package org.videolan.resources.util;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1", f = "Extensions.kt", i = {}, l = {73}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ ExtensionsKt$waitForML$2$1$listener$1 $cb;
    final /* synthetic */ CancellableContinuation<Function0<Unit>> $continuation;
    final /* synthetic */ Medialibrary $ml;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1(CancellableContinuation<? super Function0<Unit>> cancellableContinuation, Medialibrary medialibrary, ExtensionsKt$waitForML$2$1$listener$1 extensionsKt$waitForML$2$1$listener$1, Continuation<? super ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1> continuation) {
        super(2, continuation);
        this.$continuation = cancellableContinuation;
        this.$ml = medialibrary;
        this.$cb = extensionsKt$waitForML$2$1$listener$1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1(this.$continuation, this.$ml, this.$cb, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$waitForML$2$1$listener$1$onMedialibraryReady$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Result.Companion companion = Result.Companion;
            this.$continuation.resumeWith(Result.m1774constructorimpl(AnonymousClass1.INSTANCE));
            this.label = 1;
            if (YieldKt.yield(this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$ml.removeOnMedialibraryReadyListener(this.$cb);
        return Unit.INSTANCE;
    }
}
