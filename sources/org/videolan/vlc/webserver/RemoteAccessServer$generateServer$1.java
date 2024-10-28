package org.videolan.vlc.webserver;

import io.ktor.server.netty.NettyApplicationEngine;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/netty/NettyApplicationEngine$Configuration;", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateServer$1 extends Lambda implements Function1<NettyApplicationEngine.Configuration, Unit> {
    public static final RemoteAccessServer$generateServer$1 INSTANCE = new RemoteAccessServer$generateServer$1();

    RemoteAccessServer$generateServer$1() {
        super(1);
    }

    public final void invoke(NettyApplicationEngine.Configuration configuration) {
        Intrinsics.checkNotNullParameter(configuration, "$this$embeddedServer");
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((NettyApplicationEngine.Configuration) obj);
        return Unit.INSTANCE;
    }
}
