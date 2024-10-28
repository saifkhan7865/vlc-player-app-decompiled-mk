package org.videolan.vlc;

import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
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
import org.videolan.resources.util.ExtensionsKt;
import org.videolan.tools.CoroutineContextProvider;
import org.videolan.tools.Settings;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.media.MediaSessionBrowser;

@Metadata(d1 = {"\u0000\n\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0002H\u0001\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u0002H@¨\u0006\u0003"}, d2 = {"<anonymous>", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3", f = "MediaSessionCallback.kt", i = {0, 0, 0}, l = {341}, m = "invokeSuspend", n = {"$this$withContext", "ml", "scan"}, s = {"L$0", "L$1", "I$0"})
/* compiled from: Extensions.kt */
public final class MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaWrapper>>, Object> {
    final /* synthetic */ Context $this_getFromMl;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3(Context context, Continuation continuation) {
        super(2, continuation);
        this.$this_getFromMl = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3 mediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3 = new MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3(this.$this_getFromMl, continuation);
        mediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3.L$0 = obj;
        return mediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaWrapper>> continuation) {
        return ((MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                MediaWrapper[] history = instance.history(1);
                if (history != null) {
                    Intrinsics.checkNotNull(history);
                    List list = ArraysKt.toList((T[]) history);
                    if (list != null) {
                        Collection arrayList = new ArrayList();
                        for (Object next : list) {
                            MediaWrapper mediaWrapper = (MediaWrapper) next;
                            MediaSessionBrowser.Companion companion = MediaSessionBrowser.Companion;
                            Intrinsics.checkNotNull(mediaWrapper);
                            if (companion.isMediaAudio(mediaWrapper)) {
                                arrayList.add(next);
                            }
                        }
                        return (List) arrayList;
                    }
                }
                return null;
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
            final AnonymousClass1 r5 = new Medialibrary.OnMedialibraryReadyListener() {
                public void onMedialibraryIdle() {
                }

                @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001\"\u0006\b\u0000\u0010\u0002\u0018\u0001*\u00020\u0003H@¨\u0006\u0004"}, d2 = {"<anonymous>", "", "T", "Lkotlinx/coroutines/CoroutineScope;", "org/videolan/resources/util/ExtensionsKt$getFromMl$2$1$listener$1$onMedialibraryReady$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
                @DebugMetadata(c = "org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3$1$1", f = "MediaSessionCallback.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
                /* renamed from: org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3$1$1  reason: invalid class name */
                /* compiled from: Extensions.kt */
                public static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
                    int label;

                    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
                        return new AnonymousClass1(cancellableContinuation, medialibrary, this, continuation);
                    }

                    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
                        return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
                    }

                    /* JADX WARNING: Removed duplicated region for block: B:20:0x0072 A[RETURN] */
                    /* Code decompiled incorrectly, please refer to instructions dump. */
                    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
                        /*
                            r7 = this;
                            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                            int r1 = r7.label
                            r2 = 1
                            if (r1 == 0) goto L_0x0017
                            if (r1 != r2) goto L_0x000f
                            kotlin.ResultKt.throwOnFailure(r8)
                            goto L_0x0073
                        L_0x000f:
                            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
                            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                            r8.<init>(r0)
                            throw r8
                        L_0x0017:
                            kotlin.ResultKt.throwOnFailure(r8)
                            kotlinx.coroutines.CancellableContinuation r8 = r2
                            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                            kotlin.Result$Companion r1 = kotlin.Result.Companion
                            org.videolan.medialibrary.interfaces.Medialibrary r1 = r4
                            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r1 = r1.history(r2)
                            if (r1 == 0) goto L_0x005f
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
                            java.util.List r1 = kotlin.collections.ArraysKt.toList((T[]) r1)
                            if (r1 == 0) goto L_0x005f
                            java.lang.Iterable r1 = (java.lang.Iterable) r1
                            java.util.ArrayList r3 = new java.util.ArrayList
                            r3.<init>()
                            java.util.Collection r3 = (java.util.Collection) r3
                            java.util.Iterator r1 = r1.iterator()
                        L_0x003e:
                            boolean r4 = r1.hasNext()
                            if (r4 == 0) goto L_0x005c
                            java.lang.Object r4 = r1.next()
                            r5 = r4
                            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
                            org.videolan.vlc.media.MediaSessionBrowser$Companion r6 = org.videolan.vlc.media.MediaSessionBrowser.Companion
                            kotlin.jvm.internal.Intrinsics.checkNotNull(r5)
                            org.videolan.medialibrary.media.MediaLibraryItem r5 = (org.videolan.medialibrary.media.MediaLibraryItem) r5
                            boolean r5 = r6.isMediaAudio(r5)
                            if (r5 == 0) goto L_0x003e
                            r3.add(r4)
                            goto L_0x003e
                        L_0x005c:
                            java.util.List r3 = (java.util.List) r3
                            goto L_0x0060
                        L_0x005f:
                            r3 = 0
                        L_0x0060:
                            java.lang.Object r1 = kotlin.Result.m1774constructorimpl(r3)
                            r8.resumeWith(r1)
                            r8 = r7
                            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
                            r7.label = r2
                            java.lang.Object r8 = kotlinx.coroutines.YieldKt.yield(r8)
                            if (r8 != r0) goto L_0x0073
                            return r0
                        L_0x0073:
                            org.videolan.medialibrary.interfaces.Medialibrary r8 = r4
                            org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3$1 r0 = r7
                            org.videolan.medialibrary.interfaces.Medialibrary$OnMedialibraryReadyListener r0 = (org.videolan.medialibrary.interfaces.Medialibrary.OnMedialibraryReadyListener) r0
                            r8.removeOnMedialibraryReadyListener(r0)
                            kotlin.Unit r8 = kotlin.Unit.INSTANCE
                            return r8
                        */
                        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaSessionCallback$onPlayFromMediaId$1$invokeSuspend$$inlined$getFromMl$3.AnonymousClass1.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
                    }
                }

                public void onMedialibraryReady() {
                    if (!cancellableContinuation.isCompleted()) {
                        CoroutineScope coroutineScope = coroutineScope;
                        CoroutineStart coroutineStart = CoroutineStart.UNDISPATCHED;
                        final CancellableContinuation cancellableContinuation = cancellableContinuation;
                        final Medialibrary medialibrary = instance;
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
                    instance.removeOnMedialibraryReadyListener(r5);
                }
            });
            instance.addOnMedialibraryReadyListener(r5);
            ExtensionsKt.startMedialibrary$default(context, false, false, z, false, (CoroutineContextProvider) null, 24, (Object) null);
            obj = cancellableContinuationImpl.getResult();
            if (obj == IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
                DebugProbesKt.probeCoroutineSuspended(continuation);
            }
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            Context context2 = (Context) this.L$2;
            Medialibrary medialibrary = (Medialibrary) this.L$1;
            CoroutineScope coroutineScope2 = (CoroutineScope) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
