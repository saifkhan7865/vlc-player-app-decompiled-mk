package org.videolan.vlc.webserver;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.media.MediaLibraryItem;
import org.videolan.tools.livedata.LiveDataset;
import org.videolan.vlc.providers.BrowserProvider;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt", f = "RemoteAccessRouting.kt", i = {0, 0, 0}, l = {1427}, m = "getProviderContent", n = {"context", "provider", "idPrefix"}, s = {"L$0", "L$1", "J$0"})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$getProviderContent$1 extends ContinuationImpl {
    long J$0;
    Object L$0;
    Object L$1;
    int label;
    /* synthetic */ Object result;

    RemoteAccessRoutingKt$getProviderContent$1(Continuation<? super RemoteAccessRoutingKt$getProviderContent$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return RemoteAccessRoutingKt.getProviderContent((Context) null, (BrowserProvider) null, (LiveDataset<MediaLibraryItem>) null, 0, this);
    }
}
