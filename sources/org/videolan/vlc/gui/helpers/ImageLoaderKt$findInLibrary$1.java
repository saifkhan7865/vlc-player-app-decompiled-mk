package org.videolan.vlc.gui.helpers;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.helpers.ImageLoaderKt", f = "ImageLoader.kt", i = {0}, l = {346}, m = "findInLibrary", n = {"item"}, s = {"L$0"})
/* compiled from: ImageLoader.kt */
final class ImageLoaderKt$findInLibrary$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;

    ImageLoaderKt$findInLibrary$1(Continuation<? super ImageLoaderKt$findInLibrary$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return ImageLoaderKt.findInLibrary((MediaLibraryItem) null, false, this);
    }
}
