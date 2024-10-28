package org.videolan.vlc.util;

import android.graphics.Bitmap;
import java.util.List;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.util.ThumbnailsProvider", f = "ThumbnailsProvider.kt", i = {0}, l = {113}, m = "getPlaylistOrGenreImage", n = {"saltedKey"}, s = {"L$0"})
/* compiled from: ThumbnailsProvider.kt */
final class ThumbnailsProvider$getPlaylistOrGenreImage$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ThumbnailsProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ThumbnailsProvider$getPlaylistOrGenreImage$1(ThumbnailsProvider thumbnailsProvider, Continuation<? super ThumbnailsProvider$getPlaylistOrGenreImage$1> continuation) {
        super(continuation);
        this.this$0 = thumbnailsProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getPlaylistOrGenreImage((String) null, (List<? extends MediaWrapper>) null, 0, (Bitmap) null, this);
    }
}
