package org.videolan.vlc;

import android.content.Context;
import android.content.res.Resources;
import android.net.Uri;
import android.support.v4.media.MediaDescriptionCompat;
import android.support.v4.media.session.MediaSessionCompat;
import java.util.ArrayList;
import java.util.List;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.KotlinExtensionsKt;
import org.videolan.vlc.media.MediaSessionBrowser;
import org.videolan.vlc.media.MediaUtils;
import org.videolan.vlc.util.BrowserutilsKt;
import org.videolan.vlc.util.ThumbnailsProvider;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0012\u0012\u0004\u0012\u00020\u00020\u0001j\b\u0012\u0004\u0012\u00020\u0002`\u0003*\u00020\u0004H@"}, d2 = {"<anonymous>", "Ljava/util/ArrayList;", "Landroid/support/v4/media/session/MediaSessionCompat$QueueItem;", "Lkotlin/collections/ArrayList;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$buildQueue$1$queue$1", f = "PlaybackService.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$buildQueue$1$queue$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super ArrayList<MediaSessionCompat.QueueItem>>, Object> {
    final /* synthetic */ PlaybackService $ctx;
    final /* synthetic */ int $fromIndex;
    final /* synthetic */ List<MediaWrapper> $mediaList;
    final /* synthetic */ int $toIndex;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$buildQueue$1$queue$1(int i, int i2, List<? extends MediaWrapper> list, PlaybackService playbackService, PlaybackService playbackService2, Continuation<? super PlaybackService$buildQueue$1$queue$1> continuation) {
        super(2, continuation);
        this.$toIndex = i;
        this.$fromIndex = i2;
        this.$mediaList = list;
        this.$ctx = playbackService;
        this.this$0 = playbackService2;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PlaybackService$buildQueue$1$queue$1(this.$toIndex, this.$fromIndex, this.$mediaList, this.$ctx, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super ArrayList<MediaSessionCompat.QueueItem>> continuation) {
        return ((PlaybackService$buildQueue$1$queue$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Uri uri;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            ArrayList arrayList = new ArrayList(this.$toIndex - this.$fromIndex);
            List<MediaWrapper> list = this.$mediaList;
            int i = this.$fromIndex;
            int i2 = this.$toIndex;
            PlaybackService playbackService = this.$ctx;
            PlaybackService playbackService2 = this.this$0;
            int i3 = 0;
            for (MediaWrapper next : list.subList(i, i2)) {
                int i4 = i3 + 1;
                String nowPlaying = next.getNowPlaying();
                if (nowPlaying == null) {
                    nowPlaying = next.getTitle();
                }
                String generateMediaId = MediaSessionBrowser.Companion.generateMediaId(next);
                if (BrowserutilsKt.isSchemeHttpOrHttps(next.getArtworkMrl())) {
                    uri = ArtworkProvider.Companion.buildUri(playbackService, new Uri.Builder().appendPath(ArtworkProvider.REMOTE).appendQueryParameter(ArtworkProvider.PATH, next.getArtworkMrl()).build());
                } else if (ThumbnailsProvider.INSTANCE.isMediaVideo(next)) {
                    uri = ArtworkProvider.Companion.buildMediaUri(playbackService, next);
                } else {
                    Map access$getArtworkMap$p = playbackService2.artworkMap;
                    if (access$getArtworkMap$p == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("artworkMap");
                        access$getArtworkMap$p = null;
                    }
                    uri = (Uri) access$getArtworkMap$p.get(generateMediaId);
                    if (uri == null) {
                        Resources resources = playbackService.getResources();
                        Intrinsics.checkNotNullExpressionValue(resources, "getResources(...)");
                        uri = KotlinExtensionsKt.getResourceUri(resources, R.drawable.ic_auto_nothumb);
                    }
                }
                Context context = playbackService;
                arrayList.add(new MediaSessionCompat.QueueItem(new MediaDescriptionCompat.Builder().setTitle(nowPlaying).setSubtitle(MediaUtils.INSTANCE.getMediaArtist(context, next)).setDescription(MediaUtils.INSTANCE.getMediaAlbum(context, next)).setIconUri(uri).setMediaUri(next.getUri()).setMediaId(generateMediaId).build(), (long) (i3 + i)));
                i3 = i4;
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
