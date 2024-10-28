package org.videolan.vlc.media;

import android.content.Context;
import androidx.core.app.NotificationCompat;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Album;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAlbums$1", f = "MediaUtils.kt", i = {}, l = {198}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAlbums$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $position;
    final /* synthetic */ MedialibraryProvider<Album> $provider;
    final /* synthetic */ boolean $shuffle;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAlbums$1(MedialibraryProvider<Album> medialibraryProvider, Context context, boolean z, int i, Continuation<? super MediaUtils$playAlbums$1> continuation) {
        super(2, continuation);
        this.$provider = medialibraryProvider;
        this.$context = context;
        this.$shuffle = z;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playAlbums$1(this.$provider, this.$context, this.$shuffle, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$playAlbums$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MedialibraryProvider<Album> medialibraryProvider = this.$provider;
            Context context = this.$context;
            final MedialibraryProvider<Album> medialibraryProvider2 = this.$provider;
            final boolean z = this.$shuffle;
            final int i2 = this.$position;
            this.label = 1;
            if (medialibraryProvider.loadPagedList(context, new Function1<PlaybackService, List<? extends MediaWrapper>>() {
                public final List<MediaWrapper> invoke(PlaybackService playbackService) {
                    Intrinsics.checkNotNullParameter(playbackService, "it");
                    List<MediaWrapper> arrayList = new ArrayList<>();
                    for (Album tracks : (Album[]) medialibraryProvider2.getAll()) {
                        MediaWrapper[] tracks2 = tracks.getTracks();
                        if (tracks2 != null) {
                            Intrinsics.checkNotNull(tracks2);
                            CollectionsKt.addAll(arrayList, (T[]) tracks2);
                        }
                    }
                    return arrayList;
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
                        int i = i2;
                        if (z) {
                            i = new SecureRandom().nextInt(list.size());
                        }
                        playbackService.load(list, i);
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
