package org.videolan.resources.util;

import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.YieldKt;
import org.videolan.medialibrary.interfaces.Medialibrary;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1", f = "Extensions.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Medialibrary, T> $block;
    final /* synthetic */ ExtensionsKt$getFromMl$2$1$listener$1 $cb;
    final /* synthetic */ CancellableContinuation<T> $continuation;
    final /* synthetic */ Medialibrary $ml;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1(CancellableContinuation<? super T> cancellableContinuation, Function1<? super Medialibrary, ? extends T> function1, Medialibrary medialibrary, ExtensionsKt$getFromMl$2$1$listener$1 extensionsKt$getFromMl$2$1$listener$1, Continuation<? super ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1> continuation) {
        super(2, continuation);
        this.$continuation = cancellableContinuation;
        this.$block = function1;
        this.$ml = medialibrary;
        this.$cb = extensionsKt$getFromMl$2$1$listener$1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1(this.$continuation, this.$block, this.$ml, this.$cb, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            Result.Companion companion = Result.Companion;
            this.$continuation.resumeWith(Result.m1774constructorimpl(this.$block.invoke(this.$ml)));
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

    public final Object invokeSuspend$$forInline(Object obj) {
        Result.Companion companion = Result.Companion;
        this.$continuation.resumeWith(Result.m1774constructorimpl(this.$block.invoke(this.$ml)));
        InlineMarker.mark(0);
        YieldKt.yield(this);
        InlineMarker.mark(1);
        this.$ml.removeOnMedialibraryReadyListener(this.$cb);
        return Unit.INSTANCE;
    }
}
