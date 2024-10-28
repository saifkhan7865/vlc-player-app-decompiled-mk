package org.videolan.vlc;

import android.content.Intent;
import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.resources.Constants;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$broadcastMetadata$1", f = "PlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$broadcastMetadata$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $media;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$broadcastMetadata$1(PlaybackService playbackService, MediaWrapper mediaWrapper, Continuation<? super PlaybackService$broadcastMetadata$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
        this.$media = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$broadcastMetadata$1(this.this$0, this.$media, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$broadcastMetadata$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        String str;
        String currentChapter;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService playbackService = this.this$0;
            Intent intent = new Intent("com.android.music.metachanged");
            MediaWrapper mediaWrapper = this.$media;
            String str2 = null;
            if (mediaWrapper == null || (str = mediaWrapper.getNowPlaying()) == null) {
                MediaWrapper mediaWrapper2 = this.$media;
                str = mediaWrapper2 != null ? mediaWrapper2.getTitle() : null;
            }
            Intent putExtra = intent.putExtra("track", str).putExtra(ArtworkProvider.ARTIST, this.$media != null ? MediaUtils.INSTANCE.getMediaArtist(this.this$0, this.$media) : null);
            if (this.$media != null) {
                str2 = MediaUtils.INSTANCE.getMediaAlbum(this.this$0, this.$media);
            }
            Intent putExtra2 = putExtra.putExtra(ArtworkProvider.ALBUM, str2);
            MediaWrapper mediaWrapper3 = this.$media;
            Intent putExtra3 = putExtra2.putExtra(TypedValues.TransitionType.S_DURATION, mediaWrapper3 != null ? mediaWrapper3.getLength() : 0).putExtra("playing", this.this$0.isPlaying()).putExtra(Constants.SCHEME_PACKAGE, "org.videolan.vlc");
            PlaybackService playbackService2 = this.this$0;
            if (playbackService2.lastChaptersCount > 0 && (currentChapter = playbackService2.getCurrentChapter()) != null) {
                putExtra3.putExtra("chapter", currentChapter);
            }
            playbackService.sendBroadcast(putExtra3);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
