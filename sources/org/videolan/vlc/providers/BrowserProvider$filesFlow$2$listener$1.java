package org.videolan.vlc.providers;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.channels.ProducerScope;
import kotlinx.coroutines.channels.SendChannel;
import org.videolan.libvlc.interfaces.IMedia;
import org.videolan.libvlc.util.MediaBrowser;

@Metadata(d1 = {"\u0000!\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H\u0016J\u0018\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u0018\u0010\t\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"org/videolan/vlc/providers/BrowserProvider$filesFlow$2$listener$1", "Lorg/videolan/libvlc/util/MediaBrowser$EventListener;", "onBrowseEnd", "", "onMediaAdded", "index", "", "media", "Lorg/videolan/libvlc/interfaces/IMedia;", "onMediaRemoved", "vlc-android_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: BrowserProvider.kt */
public final class BrowserProvider$filesFlow$2$listener$1 implements MediaBrowser.EventListener {
    final /* synthetic */ ProducerScope<IMedia> $$this$channelFlow;

    public void onMediaRemoved(int i, IMedia iMedia) {
        Intrinsics.checkNotNullParameter(iMedia, "media");
    }

    BrowserProvider$filesFlow$2$listener$1(ProducerScope<? super IMedia> producerScope) {
        this.$$this$channelFlow = producerScope;
    }

    public void onMediaAdded(int i, IMedia iMedia) {
        Intrinsics.checkNotNullParameter(iMedia, "media");
        if (!this.$$this$channelFlow.isClosedForSend()) {
            ProducerScope<IMedia> producerScope = this.$$this$channelFlow;
            iMedia.retain();
            producerScope.m1139trySendJP2dKIU(iMedia);
        }
    }

    public void onBrowseEnd() {
        if (!this.$$this$channelFlow.isClosedForSend()) {
            SendChannel.DefaultImpls.close$default(this.$$this$channelFlow, (Throwable) null, 1, (Object) null);
        }
    }
}
