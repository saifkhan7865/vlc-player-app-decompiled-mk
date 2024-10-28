package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Runnable.kt */
public final class MediaUtils$deleteItem$$inlined$Runnable$3 implements Runnable {
    final /* synthetic */ MediaLibraryItem $item$inlined;
    final /* synthetic */ Function1 $onDeleteFailed$inlined;

    public MediaUtils$deleteItem$$inlined$Runnable$3(Function1 function1, MediaLibraryItem mediaLibraryItem) {
        this.$onDeleteFailed$inlined = function1;
        this.$item$inlined = mediaLibraryItem;
    }

    public final void run() {
        this.$onDeleteFailed$inlined.invoke(this.$item$inlined);
    }
}
