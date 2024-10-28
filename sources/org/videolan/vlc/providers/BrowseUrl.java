package org.videolan.vlc.providers;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\u0002\u0010\bR\u001d\u0010\u0004\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/videolan/vlc/providers/BrowseUrl;", "Lorg/videolan/vlc/providers/BrowserAction;", "url", "", "deferred", "Lkotlinx/coroutines/CompletableDeferred;", "", "Lorg/videolan/medialibrary/media/MediaLibraryItem;", "(Ljava/lang/String;Lkotlinx/coroutines/CompletableDeferred;)V", "getDeferred", "()Lkotlinx/coroutines/CompletableDeferred;", "getUrl", "()Ljava/lang/String;", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserProvider.kt */
final class BrowseUrl extends BrowserAction {
    private final CompletableDeferred<List<MediaLibraryItem>> deferred;
    private final String url;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public BrowseUrl(String str, CompletableDeferred<List<MediaLibraryItem>> completableDeferred) {
        super((DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        Intrinsics.checkNotNullParameter(completableDeferred, "deferred");
        this.url = str;
        this.deferred = completableDeferred;
    }

    public final CompletableDeferred<List<MediaLibraryItem>> getDeferred() {
        return this.deferred;
    }

    public final String getUrl() {
        return this.url;
    }
}
