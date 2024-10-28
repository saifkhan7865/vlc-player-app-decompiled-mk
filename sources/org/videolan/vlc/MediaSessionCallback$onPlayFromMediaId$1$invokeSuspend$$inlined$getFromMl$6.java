package org.videolan.vlc;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
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
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.SearchAggregate;
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6", f = "MediaSessionCallback.kt", i = {0, 0, 0}, l = {338}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaWrapper>>, Object> {
    final /* synthetic */ String $query$inlined;
    final /* synthetic */ Context $this_getFromMl;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6(Context context, Continuation continuation, String str) {
        super(2, continuation);
        this.$this_getFromMl = context;
        this.$query$inlined = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6 mediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6 = new MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6(this.$this_getFromMl, continuation, this.$query$inlined);
        mediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6.L$0 = obj;
        return mediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaWrapper>> continuation) {
        return ((MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        MediaWrapper[] tracks;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final CoroutineScope coroutineScope = (CoroutineScope) this.L$0;
            final Medialibrary instance = Medialibrary.getInstance();
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(...)");
            if (instance.isStarted()) {
                SearchAggregate search = instance.search(this.$query$inlined, false, false);
                if (!(search == null || (tracks = search.getTracks()) == null)) {
                    Intrinsics.checkNotNull(tracks);
                    List list = ArraysKt.toList((T[]) tracks);
                    if (list != null) {
                        return list;
                    }
                }
                return CollectionsKt.emptyList();
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
                @DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6$1$1", f = "MediaSessionCallback.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6$1$1  reason: invalid class name */
                /* compiled from: Extensions.kt */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(cancellableContinuation, medialibrary, this, continuation, str);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0038, code lost:
                        if (r1 == null) goto L_0x003a;
                     */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
                        /*
                            r5 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r5.label
                            r2 = 1
                            if (r1 == 0) goto L_0x0017
                            if (r1 != r2) goto L_0x000f
                            kotlin.ResultKt.throwOnFailure(r6)
                            goto L_0x0051
                        L_0x000f:
                            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r6.<init>(r0)
                            throw r6
                        L_0x0017:
                            kotlin.ResultKt.throwOnFailure(r6)
                            kotlinx.coroutines.CancellableContinuation r6 = r5
                            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                            kotlin.Result$Companion r1 = kotlin.Result.Companion
                            org.videolan.medialibrary.interfaces.Medialibrary r1 = r6
                            java.lang.String r3 = r9
                            r4 = 0
                            org.videolan.medialibrary.media.SearchAggregate r1 = r1.search(r3, r4, r4)
                            if (r1 == 0) goto L_0x003a
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.getTracks()
                            if (r1 == 0) goto L_0x003a
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                            java.util.List r1 = kotlin.collections.ArraysKt.toList((T[]) r1)
                            if (r1 != 0) goto L_0x003e
                        L_0x003a:
                            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
                        L_0x003e:
                            java.lang.Object r1 = kotlin.Result.m1774constructorimpl(r1)
                            r6.resumeWith(r1)
                            r6 = r5
                            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
                            r5.label = r2
                            java.lang.Object r6 = kotlinx.coroutines.YieldKt.yield(r6)
                            if (r6 != r0) goto L_0x0051
                            return r0
                        L_0x0051:
                            org.videolan.medialibrary.interfaces.Medialibrary r6 = r6
                            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6$1 r0 = r7
                            org.videolan.medialibrary.interfaces.Medialibrary$OnMedialibraryReadyListener r0 = (org.videolan.medialibrary.interfaces.Medialibrary.OnMedialibraryReadyListener) r0
                            r6.removeOnMedialibraryReadyListener(r0)
                            kotlin.Unit r6 = kotlin.Unit.INSTANCE
                            return r6
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$6.AnonymousClass1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
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
