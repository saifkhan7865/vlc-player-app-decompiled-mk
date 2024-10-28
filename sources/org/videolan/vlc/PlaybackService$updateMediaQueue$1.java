package org.videolan.vlc;

import android.net.Uri;
import android.util.Log;
import java.util.HashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.util.VLCCrashHandler;
import org.videolan.vlc.media.MediaSessionBrowser;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$updateMediaQueue$1", f = "PlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$updateMediaQueue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$updateMediaQueue$1(PlaybackService playbackService, Continuation<? super PlaybackService$updateMediaQueue$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$updateMediaQueue$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$updateMediaQueue$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (this.this$0.mediaSession == null) {
                this.this$0.initMediaSession();
            }
            PlaybackService playbackService = this.this$0;
            HashMap hashMap = new HashMap();
            PlaybackService playbackService2 = this.this$0;
            HashMap hashMap2 = new HashMap();
            for (MediaWrapper next : playbackService2.getPlaylistManager().getMediaList()) {
                try {
                    String artworkMrl = next.getArtworkMrl();
                    CharSequence charSequence = artworkMrl;
                    if (charSequence != null) {
                        if (charSequence.length() != 0) {
                            Intrinsics.checkNotNull(artworkMrl);
                            if (FileProviderKt.isPathValid(artworkMrl)) {
                                Map map = hashMap2;
                                Object obj2 = map.get(artworkMrl);
                                if (obj2 == null) {
                                    obj2 = ArtworkProvider.Companion.buildMediaUri(playbackService2, next);
                                    map.put(artworkMrl, obj2);
                                }
                                Map map2 = hashMap;
                                map2.put(MediaSessionBrowser.Companion.generateMediaId(next), (Uri) obj2);
                            }
                        }
                    }
                } catch (NullPointerException e) {
                    Throwable th = e;
                    Log.e("PlaybackService", "Caught NullPointerException", th);
                    VLCCrashHandler.Companion.saveLog(th, "NullPointerException in PlaybackService updateMediaQueue");
                }
            }
            hashMap2.clear();
            playbackService.artworkMap = hashMap;
            Job unused = this.this$0.updateMediaQueueSlidingWindow(true);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
