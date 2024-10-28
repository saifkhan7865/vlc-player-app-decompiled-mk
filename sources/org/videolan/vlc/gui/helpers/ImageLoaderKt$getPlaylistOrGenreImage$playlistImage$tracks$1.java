package org.videolan.vlc.gui.helpers;

import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u00020\u0001*\u00020\u0004H@"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;", "kotlin.jvm.PlatformType", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1", f = "ImageLoader.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super List<? extends MediaWrapper>>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1(MediaLibraryItem mediaLibraryItem, Continuation<? super ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1(this.$item, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super List<? extends MediaWrapper>> continuation) {
        return ((ImageLoaderKt$getPlaylistOrGenreImage$playlistImage$tracks$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaWrapper[] tracks = this.$item.getTracks();
            Intrinsics.checkNotNullExpressionValue(tracks, "getTracks(...)");
            return ArraysKt.toList((T[]) (Object[]) tracks);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
