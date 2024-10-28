package org.videolan.vlc;

import android.content.Context;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ArtworkProvider", f = "ArtworkProvider.kt", i = {0, 0, 0}, l = {397}, m = "getHomeImage", n = {"this", "context", "cover"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: ArtworkProvider.kt */
final class ArtworkProvider$getHomeImage$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ ArtworkProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ArtworkProvider$getHomeImage$1(ArtworkProvider artworkProvider, Continuation<? super ArtworkProvider$getHomeImage$1> continuation) {
        super(continuation);
        this.this$0 = artworkProvider;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.getHomeImage((Context) null, (String) null, (MediaWrapper[]) null, this);
    }
}
