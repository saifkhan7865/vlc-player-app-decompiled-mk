package org.videolan.resources.util;

import android.content.Context;
import android.content.SharedPreferences;
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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000\b\n\u0002\b\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 176)
@DebugMetadata(c = "org.videolan.resources.util.ExtensionsKt$getFromMl$2", f = "Extensions.kt", i = {0, 0, 0}, l = {337}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class ExtensionsKt$getFromMl$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super T>, Object> {
    final /* synthetic */ Function1<Medialibrary, T> $block;
    final /* synthetic */ Context $this_getFromMl;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public ExtensionsKt$getFromMl$2(Function1<? super Medialibrary, ? extends T> function1, Context context, Continuation<? super ExtensionsKt$getFromMl$2> continuation) {
        super(2, continuation);
        this.$block = function1;
        this.$this_getFromMl = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        Intrinsics.needClassReification();
        ExtensionsKt$getFromMl$2 extensionsKt$getFromMl$2 = new ExtensionsKt$getFromMl$2(this.$block, this.$this_getFromMl, continuation);
        extensionsKt$getFromMl$2.L$0 = obj;
        return extensionsKt$getFromMl$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super T> continuation) {
        return ((ExtensionsKt$getFromMl$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            Medialibrary instance = Medialibrary.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
            if (instance.isStarted()) {
                return this.$block.invoke(instance);
            }
            boolean z = ((SharedPreferences) Settings.INSTANCE.getInstance(this.$this_getFromMl)).getInt(SettingsKt.KEY_MEDIALIBRARY_SCAN, 0) == 0;
            Context context = this.$this_getFromMl;
            Function1<Medialibrary, T> function1 = this.$block;
            this.L$0 = coroutineScope;
            this.L$1 = instance;
            this.L$2 = context;
            this.L$3 = function1;
            this.I$0 = z ? 1 : 0;
            this.label = 1;
            Continuation continuation = this;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            ExtensionsKt$getFromMl$2$1$listener$1 extensionsKt$getFromMl$2$1$listener$1 = new ExtensionsKt$getFromMl$2$1$listener$1(cancellableContinuation, coroutineScope, function1, instance);
            cancellableContinuation.invokeOnCancellation(new ExtensionsKt$getFromMl$2$1$1(instance, extensionsKt$getFromMl$2$1$listener$1));
            instance.addOnMedialibraryReadyListener(extensionsKt$getFromMl$2$1$listener$1);
            ExtensionsKt.startMedialibrary$default(context, false, false, z, false, (CoroutineContextProvider) null, 24, (Object) null);
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == coroutine_suspended ? coroutine_suspended : result;
        } else if (i == 1) {
            Function1 function12 = (Function1) this.L$3;
            Context context2 = (Context) this.L$2;
            Medialibrary medialibrary = (Medialibrary) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }

    public final Object invokeSuspend$$forInline(Object obj) {
        CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
        Medialibrary instance = Medialibrary.getInstance();
        Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
        Medialibrary medialibrary = instance;
        if (instance.isStarted()) {
            return this.$block.invoke(instance);
        }
        boolean z = ((SharedPreferences) Settings.INSTANCE.getInstance(this.$this_getFromMl)).getInt(SettingsKt.KEY_MEDIALIBRARY_SCAN, 0) == 0;
        Context context = this.$this_getFromMl;
        Function1<Medialibrary, T> function1 = this.$block;
        InlineMarker.mark(0);
        Continuation continuation = this;
        Continuation continuation2 = continuation;
        CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
        cancellableContinuationImpl.initCancellability();
        CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
        ExtensionsKt$getFromMl$2$1$listener$1 extensionsKt$getFromMl$2$1$listener$1 = new ExtensionsKt$getFromMl$2$1$listener$1(cancellableContinuation, coroutineScope, function1, instance);
        cancellableContinuation.invokeOnCancellation(new ExtensionsKt$getFromMl$2$1$1(instance, extensionsKt$getFromMl$2$1$listener$1));
        instance.addOnMedialibraryReadyListener(extensionsKt$getFromMl$2$1$listener$1);
        ExtensionsKt.startMedialibrary$default(context, false, false, z, false, (CoroutineContextProvider) null, 24, (Object) null);
        Unit unit = Unit.INSTANCE;
        Object result = cancellableContinuationImpl.getResult();
        if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
            DebugProbesKt.probeCoroutineSuspended(continuation);
        }
        InlineMarker.mark(1);
        return result;
    }
}
