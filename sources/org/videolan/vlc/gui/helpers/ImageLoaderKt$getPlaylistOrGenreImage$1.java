package org.videolan.vlc.gui.helpers;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt", f = "ImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1}, l = {279, 280}, m = "getPlaylistOrGenreImage", n = {"v", "item", "binding", "bindChanged", "rebindCallbacks", "width", "card", "v", "item", "binding", "bindChanged", "rebindCallbacks", "card"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "I$0", "Z$0", "L$0", "L$1", "L$2", "L$3", "L$4", "Z$0"})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$getPlaylistOrGenreImage$1 extends ContinuationImpl {
    int I$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    int label;
    /* synthetic */ Object result;

    ImageLoaderKt$getPlaylistOrGenreImage$1(Continuation<? super ImageLoaderKt$getPlaylistOrGenreImage$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ImageLoaderKt.getPlaylistOrGenreImage((View) null, (MediaLibraryItem) null, (ViewDataBinding) null, 0, false, this);
    }
}
