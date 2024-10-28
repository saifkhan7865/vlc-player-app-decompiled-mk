package org.videolan.vlc.webserver.websockets;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;

@Metadata(k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets", f = "RemoteAccessWebSockets.kt", i = {0}, l = {391}, m = "closeAllSessions", n = {"iterator"}, s = {"L$0"})
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$closeAllSessions$1 extends ContinuationImpl {
    Object L$0;
    int label;
    /* synthetic */ Object result;
    final /* synthetic */ RemoteAccessWebSockets this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$closeAllSessions$1(RemoteAccessWebSockets remoteAccessWebSockets, Continuation<? super RemoteAccessWebSockets$closeAllSessions$1> continuation) {
        super(continuation);
        this.this$0 = remoteAccessWebSockets;
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return this.this$0.closeAllSessions(this);
    }
}
