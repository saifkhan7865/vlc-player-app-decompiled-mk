package org.videolan.vlc.media;

import kotlin.Metadata;
import org.videolan.medialibrary.interfaces.media.Playlist;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002¨\u0006\u0003"}, d2 = {"<anonymous>", "", "run", "kotlinx/coroutines/RunnableKt$Runnable$1"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: Runnable.kt */
public final class MediaUtils$deleteItem$$inlined$Runnable$2 implements Runnable {
    final /* synthetic */ MediaLibraryItem $item$inlined;

    public MediaUtils$deleteItem$$inlined$Runnable$2(MediaLibraryItem mediaLibraryItem) {
        this.$item$inlined = mediaLibraryItem;
    }

    public final void run() {
        MediaUtils.INSTANCE.deletePlaylist((Playlist) this.$item$inlined);
    }
}
