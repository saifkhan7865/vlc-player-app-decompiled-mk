package org.videolan.vlc.providers.medialibrary;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.Dispatchers;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001\"\b\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@"}, d2 = {"<anonymous>", "", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "service", "Lorg/videolan/vlc/PlaybackService;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.medialibrary.MedialibraryProvider$loadPagedList$2", f = "MedialibraryProvider.kt", i = {0}, l = {91}, m = "invokeSuspend", n = {"service"}, s = {"L$0"})
/* compiled from: MedialibraryProvider.kt */
final class MedialibraryProvider$loadPagedList$2 extends SuspendLambda implements Function2<PlaybackService, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function2<List<? extends MediaWrapper>, PlaybackService, Unit> $loadLambda;
    final /* synthetic */ Function1<PlaybackService, List<MediaWrapper>> $pageSizeLambda;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MedialibraryProvider<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MedialibraryProvider$loadPagedList$2(Function2<? super List<? extends MediaWrapper>, ? super PlaybackService, Unit> function2, MedialibraryProvider<T> medialibraryProvider, Function1<? super PlaybackService, ? extends List<? extends MediaWrapper>> function1, Continuation<? super MedialibraryProvider$loadPagedList$2> continuation) {
        super(2, continuation);
        this.$loadLambda = function2;
        this.this$0 = medialibraryProvider;
        this.$pageSizeLambda = function1;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MedialibraryProvider$loadPagedList$2 medialibraryProvider$loadPagedList$2 = new MedialibraryProvider$loadPagedList$2(this.$loadLambda, this.this$0, this.$pageSizeLambda, continuation);
        medialibraryProvider$loadPagedList$2.L$0 = obj;
        return medialibraryProvider$loadPagedList$2;
    }

    public final Object invoke(PlaybackService playbackService, Continuation<? super Unit> continuation) {
        return ((MedialibraryProvider$loadPagedList$2) create(playbackService, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        PlaybackService playbackService;
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            PlaybackService playbackService2 = (PlaybackService) this.L$0;
            this.L$0 = playbackService2;
            this.label = 1;
            Object withContext = BuildersKt.withContext(Dispatchers.getIO(), new MedialibraryProvider$loadPagedList$2$list$1(this.this$0, this.$pageSizeLambda, playbackService2, (Continuation<? super MedialibraryProvider$loadPagedList$2$list$1>) null), this);
            if (withContext == coroutine_suspended) {
                return coroutine_suspended;
            }
            playbackService = playbackService2;
            obj = withContext;
        } else if (i == 1) {
            playbackService = (PlaybackService) this.L$0;
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        this.$loadLambda.invoke((List) obj, playbackService);
        return Unit.INSTANCE;
    }
}
