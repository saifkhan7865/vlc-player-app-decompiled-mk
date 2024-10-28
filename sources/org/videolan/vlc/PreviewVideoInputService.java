package org.videolan.vlc;

import android.content.Context;
import android.media.tv.TvInputService;
import android.net.Uri;
import android.view.Surface;
import java.util.concurrent.CancellationException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.libvlc.FactoryManager;
import org.videolan.libvlc.MediaPlayer;
import org.videolan.libvlc.interfaces.IComponentFactory;
import org.videolan.libvlc.interfaces.IMediaFactory;
import org.videolan.resources.AppContextProvider;
import org.videolan.resources.Constants;
import org.videolan.tools.AppUtils$$ExternalSyntheticApiModelOutline0;
import org.videolan.tools.LocaleUtilsKt;
import org.videolan.vlc.media.MediaPlayerEventListener;
import org.videolan.vlc.media.PlayerController;

@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002:\u0001\u0016B\u0005¢\u0006\u0002\u0010\u0003J\u0012\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0014J\b\u0010\u0010\u001a\u00020\u000fH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\b\u0010\u0015\u001a\u00020\rH\u0016R\u0012\u0010\u0004\u001a\u00020\u0005X\u0005¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/videolan/vlc/PreviewVideoInputService;", "Landroid/media/tv/TvInputService;", "Lkotlinx/coroutines/CoroutineScope;", "()V", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "factory", "Lorg/videolan/libvlc/interfaces/IMediaFactory;", "getFactory$vlc_android_release", "()Lorg/videolan/libvlc/interfaces/IMediaFactory;", "attachBaseContext", "", "newBase", "Landroid/content/Context;", "getApplicationContext", "onCreateSession", "Landroid/media/tv/TvInputService$Session;", "inputId", "", "onDestroy", "PreviewSession", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: PreviewVideoInputService.kt */
public final class PreviewVideoInputService extends TvInputService implements CoroutineScope {
    private final /* synthetic */ CoroutineScope $$delegate_0 = CoroutineScopeKt.MainScope();
    private final IMediaFactory factory;

    public CoroutineContext getCoroutineContext() {
        return this.$$delegate_0.getCoroutineContext();
    }

    public PreviewVideoInputService() {
        IComponentFactory factory2 = FactoryManager.getFactory(IMediaFactory.factoryId);
        Intrinsics.checkNotNull(factory2, "null cannot be cast to non-null type org.videolan.libvlc.interfaces.IMediaFactory");
        this.factory = (IMediaFactory) factory2;
    }

    public final IMediaFactory getFactory$vlc_android_release() {
        return this.factory;
    }

    public TvInputService.Session onCreateSession(String str) {
        Intrinsics.checkNotNullParameter(str, "inputId");
        return AppUtils$$ExternalSyntheticApiModelOutline0.m((Object) new PreviewSession(this, this));
    }

    /* access modifiers changed from: protected */
    public void attachBaseContext(Context context) {
        super.attachBaseContext(context != null ? LocaleUtilsKt.getContextWithLocale(context, AppContextProvider.INSTANCE.getLocale()) : null);
    }

    public Context getApplicationContext() {
        Context applicationContext = super.getApplicationContext();
        Intrinsics.checkNotNullExpressionValue(applicationContext, "getApplicationContext(...)");
        return LocaleUtilsKt.getContextWithLocale(applicationContext, AppContextProvider.INSTANCE.getLocale());
    }

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\b\u0004\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u000e\u0010\u0014\u001a\u00020\u0012H@¢\u0006\u0002\u0010\u0015J\u0016\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u0018H@¢\u0006\u0002\u0010\u0019J\b\u0010\u001a\u001a\u00020\u0012H\u0016J\u0010\u0010\u001b\u001a\u00020\u00122\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0010\u0010\u001e\u001a\u00020\u00122\u0006\u0010\u001f\u001a\u00020 H\u0016J\u0012\u0010!\u001a\u00020\u001d2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0016J \u0010\"\u001a\u00020\u00122\u0006\u0010#\u001a\u00020\u00072\u0006\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010$\u001a\u00020\u001d2\u0006\u0010%\u001a\u00020&H\u0016R\u000e\u0010\u0006\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\b\u001a\u00020\t8FX\u0002¢\u0006\f\n\u0004\b\f\u0010\r\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u000e\u001a\u00020\u000fX.¢\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0011X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lorg/videolan/vlc/PreviewVideoInputService$PreviewSession;", "Landroid/media/tv/TvInputService$Session;", "Lorg/videolan/vlc/media/MediaPlayerEventListener;", "context", "Landroid/content/Context;", "(Lorg/videolan/vlc/PreviewVideoInputService;Landroid/content/Context;)V", "height", "", "player", "Lorg/videolan/vlc/media/PlayerController;", "getPlayer", "()Lorg/videolan/vlc/media/PlayerController;", "player$delegate", "Lkotlin/Lazy;", "surface", "Landroid/view/Surface;", "surfaceReady", "Lkotlinx/coroutines/CompletableDeferred;", "", "width", "awaitSurface", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onEvent", "event", "Lorg/videolan/libvlc/MediaPlayer$Event;", "(Lorg/videolan/libvlc/MediaPlayer$Event;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onRelease", "onSetCaptionEnabled", "enabled", "", "onSetStreamVolume", "volume", "", "onSetSurface", "onSurfaceChanged", "format", "onTune", "uri", "Landroid/net/Uri;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
    /* compiled from: PreviewVideoInputService.kt */
    private final class PreviewSession extends TvInputService.Session implements MediaPlayerEventListener {
        /* access modifiers changed from: private */
        public int height;
        private final Lazy player$delegate;
        /* access modifiers changed from: private */
        public Surface surface;
        private CompletableDeferred<Unit> surfaceReady;
        final /* synthetic */ PreviewVideoInputService this$0;
        /* access modifiers changed from: private */
        public int width;

        public void onSetCaptionEnabled(boolean z) {
        }

        public void onSetStreamVolume(float f) {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public PreviewSession(PreviewVideoInputService previewVideoInputService, Context context) {
            super(context);
            Intrinsics.checkNotNullParameter(context, "context");
            this.this$0 = previewVideoInputService;
            this.player$delegate = LazyKt.lazy(LazyThreadSafetyMode.NONE, new PreviewVideoInputService$PreviewSession$player$2(previewVideoInputService));
        }

        public final PlayerController getPlayer() {
            return (PlayerController) this.player$delegate.getValue();
        }

        public void onRelease() {
            PlayerController.release$default(getPlayer(), (MediaPlayer) null, 1, (Object) null);
        }

        public boolean onTune(Uri uri) {
            Intrinsics.checkNotNullParameter(uri, Constants.KEY_URI);
            notifyVideoUnavailable(1);
            String lastPathSegment = uri.getLastPathSegment();
            if (lastPathSegment == null) {
                return false;
            }
            long parseLong = Long.parseLong(lastPathSegment);
            Job unused = BuildersKt__Builders_commonKt.launch$default(this.this$0, (CoroutineContext) null, (CoroutineStart) null, new PreviewVideoInputService$PreviewSession$onTune$1(this.this$0, parseLong, this, (Continuation<? super PreviewVideoInputService$PreviewSession$onTune$1>) null), 3, (Object) null);
            return true;
        }

        public boolean onSetSurface(Surface surface2) {
            if (surface2 == null) {
                return false;
            }
            this.surface = surface2;
            CompletableDeferred<Unit> completableDeferred = this.surfaceReady;
            if (completableDeferred == null) {
                return true;
            }
            completableDeferred.complete(Unit.INSTANCE);
            return true;
        }

        public void onSurfaceChanged(int i, int i2, int i3) {
            this.width = i2;
            this.height = i3;
        }

        public Object onEvent(MediaPlayer.Event event, Continuation<? super Unit> continuation) {
            if (event.type == 265) {
                PlayerController.release$default(getPlayer(), (MediaPlayer) null, 1, (Object) null);
            }
            return Unit.INSTANCE;
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final java.lang.Object awaitSurface(kotlin.coroutines.Continuation<? super kotlin.Unit> r5) {
            /*
                r4 = this;
                boolean r0 = r5 instanceof org.videolan.vlc.PreviewVideoInputService$PreviewSession$awaitSurface$1
                if (r0 == 0) goto L_0x0014
                r0 = r5
                org.videolan.vlc.PreviewVideoInputService$PreviewSession$awaitSurface$1 r0 = (org.videolan.vlc.PreviewVideoInputService$PreviewSession$awaitSurface$1) r0
                int r1 = r0.label
                r2 = -2147483648(0xffffffff80000000, float:-0.0)
                r1 = r1 & r2
                if (r1 == 0) goto L_0x0014
                int r5 = r0.label
                int r5 = r5 - r2
                r0.label = r5
                goto L_0x0019
            L_0x0014:
                org.videolan.vlc.PreviewVideoInputService$PreviewSession$awaitSurface$1 r0 = new org.videolan.vlc.PreviewVideoInputService$PreviewSession$awaitSurface$1
                r0.<init>(r4, r5)
            L_0x0019:
                java.lang.Object r5 = r0.result
                java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
                int r2 = r0.label
                r3 = 1
                if (r2 == 0) goto L_0x0036
                if (r2 != r3) goto L_0x002e
                java.lang.Object r0 = r0.L$0
                org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = (org.videolan.vlc.PreviewVideoInputService.PreviewSession) r0
                kotlin.ResultKt.throwOnFailure(r5)
                goto L_0x0053
            L_0x002e:
                java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
                java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
                r5.<init>(r0)
                throw r5
            L_0x0036:
                kotlin.ResultKt.throwOnFailure(r5)
                android.view.Surface r5 = r4.surface
                if (r5 != 0) goto L_0x0056
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                kotlinx.coroutines.CompletableDeferred r5 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred(r5)
                r4.surfaceReady = r5
                if (r5 == 0) goto L_0x0052
                r0.L$0 = r4
                r0.label = r3
                java.lang.Object r5 = r5.await(r0)
                if (r5 != r1) goto L_0x0052
                return r1
            L_0x0052:
                r0 = r4
            L_0x0053:
                r5 = 0
                r0.surfaceReady = r5
            L_0x0056:
                kotlin.Unit r5 = kotlin.Unit.INSTANCE
                return r5
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PreviewVideoInputService.PreviewSession.awaitSurface(kotlin.coroutines.Continuation):java.lang.Object");
        }
    }

    public void onDestroy() {
        CoroutineScopeKt.cancel$default(this, (CancellationException) null, 1, (Object) null);
        super.onDestroy();
    }
}
