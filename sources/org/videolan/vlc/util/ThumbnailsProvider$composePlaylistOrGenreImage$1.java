package org.videolan.vlc.util;

import android.graphics.Bitmap;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.ThumbnailsProvider", f = "ThumbnailsProvider.kt", i = {0, 0, 0, 1, 1, 1, 1, 1}, l = {159, 171}, m = "composePlaylistOrGenreImage", n = {"iconAddition", "artworks", "width", "iconAddition", "cs", "comboImage", "images", "width"}, s = {"L$0", "L$1", "I$0", "L$0", "L$1", "L$2", "L$3", "I$0"})
/* compiled from: ThumbnailsProvider.kt */
final class ThumbnailsProvider$composePlaylistOrGenreImage$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ThumbnailsProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ThumbnailsProvider$composePlaylistOrGenreImage$1(ThumbnailsProvider thumbnailsProvider, Continuation<? super ThumbnailsProvider$composePlaylistOrGenreImage$1> continuation) {
        super(continuation);
        this.this$0 = thumbnailsProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.composePlaylistOrGenreImage((List<? extends MediaWrapper>) null, 0, (Bitmap) null, this);
    }
}
