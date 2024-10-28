package org.videolan.vlc.webserver;

import android.content.Context;
import android.content.SharedPreferences;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CancellableContinuation;
import kotlinx.coroutines.CancellableContinuationImpl;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.YieldKt;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1", f = "RemoteAccessRouting.kt", i = {0, 0, 0}, l = {338}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super SearchAggregate>, Object> {
    final /* synthetic */ String $query$inlined;
    final /* synthetic */ Context $this_getFromMl;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1(Context context, Continuation continuation, String str) {
        super(2, continuation);
        this.$this_getFromMl = context;
        this.$query$inlined = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1 remoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1 = new RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1(this.$this_getFromMl, continuation, this.$query$inlined);
        remoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1.L$0 = obj;
        return remoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super SearchAggregate> continuation) {
        return ((RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final Medialibrary instance = Medialibrary.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
            if (instance.isStarted()) {
                return instance.search(this.$query$inlined, Settings.INSTANCE.getIncludeMissing(), false);
            }
            boolean z = ((SharedPreferences) Settings.INSTANCE.getInstance(this.$this_getFromMl)).getInt(SettingsKt.KEY_MEDIALIBRARY_SCAN, 0) == 0;
            Context context = this.$this_getFromMl;
            this.L$0 = coroutineScope;
            this.L$1 = instance;
            this.L$2 = context;
            this.I$0 = z ? 1 : 0;
            this.label = 1;
            Continuation continuation = this;
            CancellableContinuationImpl cancellableContinuationImpl = new CancellableContinuationImpl(IntrinsicsKt.intercepted(continuation), 1);
            cancellableContinuationImpl.initCancellability();
            final CancellableContinuation cancellableContinuation = cancellableContinuationImpl;
            final String str = this.$query$inlined;
            final AnonymousClass1 r6 = new Medialibrary.OnMedialibraryReadyListener() {
                public void onMedialibraryIdle() {
                }

                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H@¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
                @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.videolan.vlc.webserver.RemoteAccessRoutingKt$setupRouting$12$16$invokeSuspend$lambda$8$$inlined$getFromMl$1$1$1  reason: invalid class name */
                /* compiled from: Extensions.kt */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(cancellableContinuation, medialibrary, this, continuation, str);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    public final Object invokeSuspend(Object obj) {
                        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
                        int i = this.label;
                        if (i == 0) {
                            ResultKt.throwOnFailure(obj);
                            Result.Companion companion = Result.Companion;
                            cancellableContinuation.resumeWith(Result.m1774constructorimpl(medialibrary.search(str, Settings.INSTANCE.getIncludeMissing(), false)));
                            this.label = 1;
                            if (YieldKt.yield(this) == coroutine_suspended) {
                                return coroutine_suspended;
                            }
                        } else if (i == 1) {
                            ResultKt.throwOnFailure(obj);
                        } else {
                            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
                        }
                        medialibrary.removeOnMedialibraryReadyListener(this);
                        return Unit.INSTANCE;
                    }
                }

                public void onMedialibraryReady() {
                    if (!cancellableContinuation.isCompleted()) {
                        CoroutineScope coroutineScope = coroutineScope;
                        CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
                        final CancellableContinuation cancellableContinuation = cancellableContinuation;
                        final Medialibrary medialibrary = instance;
                        final String str = str;
                        Job unused = BuildersKt__Builders_commonKt.launch$default(coroutineScope, (CoroutineContext) null, coroutineStart, new AnonymousClass1((Continuation) null), 1, (Object) null);
                    }
                }
            };
            cancellableContinuation.invokeOnCancellation(new Function1<Throwable, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Throwable) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(Throwable th) {
                    instance.removeOnMedialibraryReadyListener(r6);
                }
            });
            instance.addOnMedialibraryReadyListener(r6);
            ExtensionsKt.startMedialibrary$default(context, false, false, z, false, (CoroutineContextProvider) null, 24, (Object) null);
            Object result = cancellableContinuationImpl.getResult();
            if (result == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            return result == coroutine_suspended ? coroutine_suspended : result;
        } else if (i == 1) {
            Context context2 = (Context) this.L$2;
            Medialibrary medialibrary = (Medialibrary) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
            return obj;
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
