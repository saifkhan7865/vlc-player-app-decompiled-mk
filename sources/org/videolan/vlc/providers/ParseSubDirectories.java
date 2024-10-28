package org.videolan.vlc.providers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0010\b\u0002\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0005R\u0019\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/videolan/vlc/providers/ParseSubDirectories;", "Lorg/videolan/vlc/providers/BrowserAction;", "list", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Ljava/util/List;)V", "getList", "()Ljava/util/List;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserProvider.kt */
final class ParseSubDirectories extends BrowserAction {
    private final List<MediaLibraryItem> list;

    public ParseSubDirectories() {
        this((List) null, 1, (DefaultConstructorMarker) null);
    }

    public ParseSubDirectories(List<? extends MediaLibraryItem> list2) {
        super((DefaultConstructorMarker) null);
        this.list = list2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ ParseSubDirectories(List list2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? null : list2);
    }

    public final List<MediaLibraryItem> getList() {
        return this.list;
    }
}
