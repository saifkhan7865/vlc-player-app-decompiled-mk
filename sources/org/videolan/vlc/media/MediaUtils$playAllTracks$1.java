package org.videolan.vlc.media;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.tools.Settings;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.providers.medialibrary.VideoGroupsProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAllTracks$1", f = "MediaUtils.kt", i = {}, l = {222}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAllTracks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ MediaWrapper $mediaToPlay;
    final /* synthetic */ VideoGroupsProvider $provider;
    final /* synthetic */ boolean $shuffle;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAllTracks$1(VideoGroupsProvider videoGroupsProvider, Context context, boolean z, MediaWrapper mediaWrapper, Continuation<? super MediaUtils$playAllTracks$1> continuation) {
        super(2, continuation);
        this.$provider = videoGroupsProvider;
        this.$context = context;
        this.$shuffle = z;
        this.$mediaToPlay = mediaWrapper;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playAllTracks$1(this.$provider, this.$context, this.$shuffle, this.$mediaToPlay, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$playAllTracks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            VideoGroupsProvider videoGroupsProvider = this.$provider;
            Context context = this.$context;
            final VideoGroupsProvider videoGroupsProvider2 = this.$provider;
            final boolean z = this.$shuffle;
            final MediaWrapper mediaWrapper = this.$mediaToPlay;
            this.label = 1;
            if (videoGroupsProvider.loadPagedList(context, new Function1<PlaybackService, List<? extends MediaWrapper>>() {
                public final List<MediaWrapper> invoke(PlaybackService playbackService) {
                    Intrinsics.checkNotNullParameter(playbackService, "it");
                    VideoGroup[] all = videoGroupsProvider2.getAll();
                    Collection arrayList = new ArrayList();
                    for (VideoGroup videoGroup : all) {
                        MediaWrapper[] media = videoGroup.media(0, false, Settings.INSTANCE.getIncludeMissing(), false, videoGroup.mediaCount(), 0);
                        Intrinsics.checkNotNullExpressionValue(media, "media(...)");
                        CollectionsKt.addAll(arrayList, ArraysKt.toList((T[]) (Object[]) media));
                    }
                    return (List) arrayList;
                }
            }, new Function2<List<? extends MediaWrapper>, PlaybackService, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((List<? extends MediaWrapper>) (List) obj, (PlaybackService) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(List<? extends MediaWrapper> list, PlaybackService playbackService) {
                    Intrinsics.checkNotNullParameter(list, "l");
                    Intrinsics.checkNotNullParameter(playbackService, NotificationCompat.CATEGORY_SERVICE);
                    if (!(!list.isEmpty())) {
                        list = null;
                    }
                    if (list != null) {
                        boolean z = z;
                        playbackService.load(list, z ? new SecureRandom().nextInt(Math.min(list.size(), 500)) : CollectionsKt.indexOf(list, mediaWrapper));
                        if (z && !playbackService.isShuffling()) {
                            playbackService.shuffle();
                        }
                    }
                }
            }, this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
