package org.videolan.vlc;

import android.os.Bundle;
import android.support.v4.media.MediaBrowserCompat;
import android.util.Log;
import androidx.lifecycle.LifecycleOwnerKt;
import androidx.media.MediaBrowserServiceCompat;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import org.videolan.vlc.media.MediaSessionBrowser;
import org.videolan.vlc.util.KextensionsKt;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$onLoadChildren$1", f = "PlaybackService.kt", i = {}, l = {1873}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$onLoadChildren$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $parentId;
    final /* synthetic */ boolean $reload;
    final /* synthetic */ MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> $result;
    final /* synthetic */ Bundle $rootHints;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$onLoadChildren$1(PlaybackService playbackService, MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result, String str, Bundle bundle, boolean z, Continuation<? super PlaybackService$onLoadChildren$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$result = result;
        this.$parentId = str;
        this.$rootHints = bundle;
        this.$reload = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$onLoadChildren$1(this.this$0, this.$result, this.$parentId, this.$rootHints, this.$reload, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$onLoadChildren$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (KextensionsKt.awaitMedialibraryStarted(this.this$0, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        final MediaBrowserServiceCompat.Result<List<MediaBrowserCompat.MediaItem>> result = this.$result;
        final PlaybackService playbackService = this.this$0;
        final String str = this.$parentId;
        final Bundle bundle = this.$rootHints;
        final boolean z = this.$reload;
        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), Dispatchers.getIO(), (CoroutineStart) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 2, (Object) null);
        return Unit.INSTANCE;
    }

    @Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.PlaybackService$onLoadChildren$1$1", f = "PlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.PlaybackService$onLoadChildren$1$1  reason: invalid class name */
    /* compiled from: PlaybackService.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            return new AnonymousClass1(result, playbackService, str, bundle, z, continuation);
        }

        public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                try {
                    result.sendResult(MediaSessionBrowser.Companion.browse(playbackService.getApplicationContext(), str, playbackService.isShuffling(), bundle));
                    if (z && !playbackService.getMedialibrary$vlc_android_release().isWorking()) {
                        MediaParsingServiceKt.reloadLibrary(playbackService.getApplicationContext());
                    }
                } catch (RuntimeException e) {
                    Log.e("VLC/PlaybackService", "Failed to load children for " + str, e);
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
