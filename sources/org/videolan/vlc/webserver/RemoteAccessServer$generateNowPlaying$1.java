package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer", f = "RemoteAccessServer.kt", i = {0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 2, 2, 2, 2}, l = {587, 588, 591}, m = "generateNowPlaying", n = {"this", "service", "media", "this", "service", "media", "bookmarks", "this", "service", "media", "bookmarks", "chapters", "sleepTimer", "speed"}, s = {"L$0", "L$1", "L$2", "L$0", "L$1", "L$2", "L$3", "L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "F$0"})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateNowPlaying$1 extends ContinuationImpl {
    float F$0;
    Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$generateNowPlaying$1(RemoteAccessServer remoteAccessServer, Continuation<? super RemoteAccessServer$generateNowPlaying$1> continuation) {
        super(continuation);
        this.this$0 = remoteAccessServer;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.generateNowPlaying(this);
    }
}
