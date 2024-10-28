package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.libvlc.interfaces.ILibVLC;
import org.videolan.libvlc.util.MediaBrowser;
import org.videolan.resources.VLCInstance;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "Lorg/videolan/libvlc/util/MediaBrowser;", "it", "Lorg/videolan/vlc/providers/BrowserProvider;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserProvider.kt */
final class BrowserProvider$initBrowser$1 extends Lambda implements Function1<BrowserProvider, MediaBrowser> {
    final /* synthetic */ BrowserProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BrowserProvider$initBrowser$1(BrowserProvider browserProvider) {
        super(1);
        this.this$0 = browserProvider;
    }

    public final MediaBrowser invoke(BrowserProvider browserProvider) {
        Intrinsics.checkNotNullParameter(browserProvider, "it");
        return new MediaBrowser((ILibVLC) VLCInstance.INSTANCE.getInstance(this.this$0.getContext()), (MediaBrowser.EventListener) null, BrowserProvider.Companion.getBrowserHandler());
    }
}
