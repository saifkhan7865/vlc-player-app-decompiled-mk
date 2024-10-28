package org.videolan.vlc.providers;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: NetworkProvider.kt */
final class NetworkProvider$refresh$list$2 extends Lambda implements Function0<List<MediaLibraryItem>> {
    final /* synthetic */ NetworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    NetworkProvider$refresh$list$2(NetworkProvider networkProvider) {
        super(0);
        this.this$0 = networkProvider;
    }

    public final List<MediaLibraryItem> invoke() {
        NetworkProvider networkProvider = this.this$0;
        String url = networkProvider.getUrl();
        Intrinsics.checkNotNull(url);
        return networkProvider.getList(url);
    }
}
