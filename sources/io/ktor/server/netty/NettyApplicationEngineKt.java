package io.ktor.server.netty;

import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollServerSocketChannel;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueServerSocketChannel;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import kotlin.Metadata;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0000\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001H\u0002¨\u0006\u0003"}, d2 = {"getChannelClass", "Lkotlin/reflect/KClass;", "Lio/netty/channel/socket/ServerSocketChannel;", "ktor-server-netty"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
public final class NettyApplicationEngineKt {
    /* access modifiers changed from: private */
    public static final KClass<? extends ServerSocketChannel> getChannelClass() {
        if (KQueue.isAvailable()) {
            return Reflection.getOrCreateKotlinClass(KQueueServerSocketChannel.class);
        }
        return Reflection.getOrCreateKotlinClass(Epoll.isAvailable() ? EpollServerSocketChannel.class : NioServerSocketChannel.class);
    }
}
