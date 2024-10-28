package org.videolan.vlc.media;

import android.app.Activity;
import androidx.core.app.NotificationCompat;
import java.security.SecureRandom;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAll$1", f = "MediaUtils.kt", i = {}, l = {211}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAll$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $context;
    final /* synthetic */ int $position;
    final /* synthetic */ MedialibraryProvider<MediaWrapper> $provider;
    final /* synthetic */ boolean $shuffle;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAll$1(MedialibraryProvider<MediaWrapper> medialibraryProvider, Activity activity, boolean z, int i, Continuation<? super MediaUtils$playAll$1> continuation) {
        super(2, continuation);
        this.$provider = medialibraryProvider;
        this.$context = activity;
        this.$shuffle = z;
        this.$position = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playAll$1(this.$provider, this.$context, this.$shuffle, this.$position, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$playAll$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            final MedialibraryProvider<MediaWrapper> medialibraryProvider = this.$provider;
            final boolean z = this.$shuffle;
            final int i2 = this.$position;
            this.label = 1;
            if (this.$provider.loadPagedList(this.$context, new Function1<PlaybackService, List<? extends MediaWrapper>>() {
                public final List<MediaWrapper> invoke(PlaybackService playbackService) {
                    Intrinsics.checkNotNullParameter(playbackService, "it");
                    return ArraysKt.toList((T[]) medialibraryProvider.getAll());
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
                            i = new SecureRandom().nextInt(Math.min(list.size(), 500));
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
