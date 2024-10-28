package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.providers.BrowserProvider;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt", f = "RemoteAccessRouting.kt", i = {0, 0, 1, 1}, l = {1360, 1495}, m = "getMediaFromProvider", n = {"provider", "dataset", "dataset", "descriptions"}, s = {"L$0", "L$1", "L$0", "L$1"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$getMediaFromProvider$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RemoteAccessRoutingKt$getMediaFromProvider$1(Continuation<? super RemoteAccessRoutingKt$getMediaFromProvider$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RemoteAccessRoutingKt.getMediaFromProvider((BrowserProvider) null, (LiveDataset<MediaLibraryItem>) null, this);
    }
}
