package org.videolan.vlc.media;

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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.tools.Settings;
import org.videolan.vlc.providers.medialibrary.FoldersProvider;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$playAllTracks$2$1$list$1", f = "MediaUtils.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaUtils.kt */
final class MediaUtils$playAllTracks$2$1$list$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaWrapper>>, Object> {
    final /* synthetic */ Ref.IntRef $index;
    final /* synthetic */ int $pageCount;
    final /* synthetic */ FoldersProvider $provider;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$playAllTracks$2$1$list$1(FoldersProvider foldersProvider, int i, Ref.IntRef intRef, Continuation<? super MediaUtils$playAllTracks$2$1$list$1> continuation) {
        super(2, continuation);
        this.$provider = foldersProvider;
        this.$pageCount = i;
        this.$index = intRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaUtils$playAllTracks$2$1$list$1(this.$provider, this.$pageCount, this.$index, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaWrapper>> continuation) {
        return ((MediaUtils$playAllTracks$2$1$list$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            Folder[] page = this.$provider.getPage(this.$pageCount, this.$index.element);
            FoldersProvider foldersProvider = this.$provider;
            Collection arrayList = new ArrayList();
            for (Folder folder : page) {
                MediaWrapper[] media = folder.media(foldersProvider.getType(), 0, false, Settings.INSTANCE.getIncludeMissing(), false, folder.mediaCount(foldersProvider.getType()), 0);
                Intrinsics.checkNotNullExpressionValue(media, "media(...)");
                CollectionsKt.addAll(arrayList, ArraysKt.toList((T[]) (Object[]) media));
            }
            return (List) arrayList;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
