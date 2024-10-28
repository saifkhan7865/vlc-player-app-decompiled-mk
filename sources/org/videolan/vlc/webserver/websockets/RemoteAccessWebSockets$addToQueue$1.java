package org.videolan.vlc.webserver.websockets;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.videolan.vlc.webserver.RemoteAccessServer;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/vlc/webserver/RemoteAccessServer$WSMessage;", "invoke", "(Lorg/videolan/vlc/webserver/RemoteAccessServer$WSMessage;)Ljava/lang/Boolean;"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessWebSockets.kt */
final class RemoteAccessWebSockets$addToQueue$1 extends Lambda implements Function1<RemoteAccessServer.WSMessage, Boolean> {
    final /* synthetic */ RemoteAccessServer.WSMessage $wsMessage;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessWebSockets$addToQueue$1(RemoteAccessServer.WSMessage wSMessage) {
        super(1);
        this.$wsMessage = wSMessage;
    }

    public final Boolean invoke(RemoteAccessServer.WSMessage wSMessage) {
        Intrinsics.checkNotNullParameter(wSMessage, "it");
        return Boolean.valueOf(Intrinsics.areEqual((Object) wSMessage.getType(), (Object) this.$wsMessage.getType()));
    }
}
