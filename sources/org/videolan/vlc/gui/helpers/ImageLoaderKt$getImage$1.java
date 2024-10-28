package org.videolan.vlc.gui.helpers;

import android.view.View;
import androidx.databinding.ViewDataBinding;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt", f = "ImageLoader.kt", i = {0, 0, 0, 0, 0, 0, 0}, l = {245}, m = "getImage", n = {"v", "item", "binding", "bindChanged", "rebindCallbacks", "tv", "card"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "Z$0", "Z$1"})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$getImage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;

    ImageLoaderKt$getImage$1(Continuation<? super ImageLoaderKt$getImage$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ImageLoaderKt.getImage((View) null, (MediaLibraryItem) null, (ViewDataBinding) null, 0, false, false, this);
    }
}
