package org.videolan.vlc.viewmodels.mobile;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.text.StringsKt;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.Medialibrary;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u0001*\u00020\u0003H@"}, d2 = {"<anonymous>", "Lorg/videolan/medialibrary/interfaces/media/VideoGroup;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.VideosViewModel$createGroup$2", f = "VideosViewModel.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: VideosViewModel.kt */
final class VideosViewModel$createGroup$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super VideoGroup>, Object> {
    final /* synthetic */ List<MediaWrapper> $medias;
    int label;
    final /* synthetic */ VideosViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    VideosViewModel$createGroup$2(VideosViewModel videosViewModel, List<? extends MediaWrapper> list, Continuation<? super VideosViewModel$createGroup$2> continuation) {
        super(2, continuation);
        this.this$0 = videosViewModel;
        this.$medias = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new VideosViewModel$createGroup$2(this.this$0, this.$medias, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super VideoGroup> continuation) {
        return ((VideosViewModel$createGroup$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Medialibrary medialibrary = this.this$0.getMedialibrary();
            Iterable<MediaWrapper> iterable = this.$medias;
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (MediaWrapper id : iterable) {
                arrayList.add(Boxing.boxLong(id.getId()));
            }
            VideoGroup createVideoGroup = medialibrary.createVideoGroup(CollectionsKt.toLongArray((List) arrayList));
            CharSequence title = createVideoGroup.getTitle();
            if (title == null || StringsKt.isBlank(title)) {
                createVideoGroup.rename(this.$medias.get(0).getTitle());
                createVideoGroup.setTitle(this.$medias.get(0).getTitle());
            }
            return createVideoGroup;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
