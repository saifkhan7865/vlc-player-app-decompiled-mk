package org.videolan.vlc.util;

import android.graphics.Bitmap;
import android.net.Uri;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.Folder;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.medialibrary.interfaces.media.VideoGroup;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.vlc.gui.helpers.AudioUtil;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.ThumbnailsProvider$obtainBitmap$2", f = "ThumbnailsProvider.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: ThumbnailsProvider.kt */
final class ThumbnailsProvider$obtainBitmap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    final /* synthetic */ int $width;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ThumbnailsProvider$obtainBitmap$2(MediaLibraryItem mediaLibraryItem, int i, Continuation<? super ThumbnailsProvider$obtainBitmap$2> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.$width = i;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new ThumbnailsProvider$obtainBitmap$2(this.$item, this.$width, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((ThumbnailsProvider$obtainBitmap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MediaLibraryItem mediaLibraryItem = this.$item;
            if (mediaLibraryItem instanceof MediaWrapper) {
                return ThumbnailsProvider.INSTANCE.getMediaThumbnail((MediaWrapper) this.$item, this.$width);
            }
            if (mediaLibraryItem instanceof Folder) {
                return ThumbnailsProvider.INSTANCE.getFolderThumbnail((Folder) this.$item, this.$width);
            }
            if (mediaLibraryItem instanceof VideoGroup) {
                return ThumbnailsProvider.INSTANCE.getVideoGroupThumbnail((VideoGroup) this.$item, this.$width);
            }
            return AudioUtil.INSTANCE.readCoverBitmap(Uri.decode(this.$item.getArtworkMrl()), this.$width);
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
