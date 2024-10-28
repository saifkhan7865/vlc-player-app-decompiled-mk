package org.videolan.vlc.media;

import android.content.Context;
import androidx.paging.PagedList;
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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;
import org.videolan.vlc.providers.medialibrary.MedialibraryProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playTracks$2", f = "MediaUtils.kt", i = {}, l = {190}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playTracks$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    final /* synthetic */ int $position;
    final /* synthetic */ MedialibraryProvider<MediaWrapper> $provider;
    final /* synthetic */ boolean $shuffle;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playTracks$2(MedialibraryProvider<MediaWrapper> medialibraryProvider, Context context, int i, boolean z, Continuation<? super MediaUtils$playTracks$2> continuation) {
        super(2, continuation);
        this.$provider = medialibraryProvider;
        this.$context = context;
        this.$position = i;
        this.$shuffle = z;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playTracks$2(this.$provider, this.$context, this.$position, this.$shuffle, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$playTracks$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            MedialibraryProvider<MediaWrapper> medialibraryProvider = this.$provider;
            Context context = this.$context;
            final MedialibraryProvider<MediaWrapper> medialibraryProvider2 = this.$provider;
            final Context context2 = this.$context;
            final int i2 = this.$position;
            final boolean z = this.$shuffle;
            this.label = 1;
            if (medialibraryProvider.loadPagedList(context, new Function1<PlaybackService, List<? extends MediaWrapper>>() {
                public final List<MediaWrapper> invoke(PlaybackService playbackService) {
                    Intrinsics.checkNotNullParameter(playbackService, "it");
                    PagedList value = medialibraryProvider2.getPagedList().getValue();
                    return value == null ? CollectionsKt.emptyList() : value;
                }
            }, new Function2<List<? extends MediaWrapper>, PlaybackService, Unit>() {
                public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                    invoke((List<? extends MediaWrapper>) (List) obj, (PlaybackService) obj2);
                    return Unit.INSTANCE;
                }

                public final void invoke(List<? extends MediaWrapper> list, PlaybackService playbackService) {
                    Intrinsics.checkNotNullParameter(list, "list");
                    Intrinsics.checkNotNullParameter(playbackService, "<anonymous parameter 1>");
                    MediaUtils.INSTANCE.openList(context2, list, i2, z);
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
