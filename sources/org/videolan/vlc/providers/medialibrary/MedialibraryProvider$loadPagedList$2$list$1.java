package org.videolan.vlc.providers.medialibrary;

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
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.PlaybackService;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001\"\b\b\u0000\u0010\u0003*\u00020\u0004*\u00020\u0005H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "T", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.providers.medialibrary.MedialibraryProvider$loadPagedList$2$list$1", f = "MedialibraryProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MedialibraryProvider.kt */
final class MedialibraryProvider$loadPagedList$2$list$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaWrapper>>, Object> {
    final /* synthetic */ Function1<PlaybackService, List<MediaWrapper>> $pageSizeLambda;
    final /* synthetic */ PlaybackService $service;
    int label;
    final /* synthetic */ MedialibraryProvider<T> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MedialibraryProvider$loadPagedList$2$list$1(MedialibraryProvider<T> medialibraryProvider, Function1<? super PlaybackService, ? extends List<? extends MediaWrapper>> function1, PlaybackService playbackService, Continuation<? super MedialibraryProvider$loadPagedList$2$list$1> continuation) {
        super(2, continuation);
        this.this$0 = medialibraryProvider;
        this.$pageSizeLambda = function1;
        this.$service = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MedialibraryProvider$loadPagedList$2$list$1(this.this$0, this.$pageSizeLambda, this.$service, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaWrapper>> continuation) {
        return ((MedialibraryProvider$loadPagedList$2$list$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            int totalCount = this.this$0.getTotalCount();
            if (totalCount == 0) {
                return CollectionsKt.emptyList();
            }
            if (1 <= totalCount && totalCount < 501) {
                return this.$pageSizeLambda.invoke(this.$service);
            }
            List arrayList = new ArrayList();
            MedialibraryProvider<T> medialibraryProvider = this.this$0;
            int i = 0;
            while (i < totalCount) {
                int min = Math.min(500, totalCount - i);
                for (MediaLibraryItem tracks : medialibraryProvider.getPage(min, i)) {
                    MediaWrapper[] tracks2 = tracks.getTracks();
                    Intrinsics.checkNotNullExpressionValue(tracks2, "getTracks(...)");
                    CollectionsKt.addAll(arrayList, (T[]) (Object[]) tracks2);
                }
                i += min;
            }
            return arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
