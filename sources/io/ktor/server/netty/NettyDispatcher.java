package io.ktor.server.netty;

import io.netty.channel.ChannelHandlerContext;
import kotlin.Metadata;
import kotlin.coroutines.AbstractCoroutineContextElement;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001:\u0002\f\rB\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\u0010\u0007\u001a\u00060\bj\u0002`\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0016¨\u0006\u000e"}, d2 = {"Lio/ktor/server/netty/NettyDispatcher;", "Lkotlinx/coroutines/CoroutineDispatcher;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/CoroutineContext;", "block", "Ljava/lang/Runnable;", "Lkotlinx/coroutines/Runnable;", "isDispatchNeeded", "", "CurrentContext", "CurrentContextKey", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CIO.kt */
public final class NettyDispatcher extends CoroutineDispatcher {
    public static final NettyDispatcher INSTANCE = new NettyDispatcher();

    private NettyDispatcher() {
    }

    public boolean isDispatchNeeded(CoroutineContext coroutineContext) {
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        CoroutineContext.Element element = coroutineContext.get(CurrentContextKey.INSTANCE);
        Intrinsics.checkNotNull(element);
        return !((CurrentContext) element).getContext().executor().inEventLoop();
    }

    public void dispatch(CoroutineContext coroutineContext, Runnable runnable) {
        Intrinsics.checkNotNullParameter(coroutineContext, "context");
        Intrinsics.checkNotNullParameter(runnable, "block");
        CoroutineContext.Element element = coroutineContext.get(CurrentContextKey.INSTANCE);
        Intrinsics.checkNotNull(element);
        ((CurrentContext) element).getContext().executor().execute(runnable);
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/netty/NettyDispatcher$CurrentContext;", "Lkotlin/coroutines/AbstractCoroutineContextElement;", "context", "Lio/netty/channel/ChannelHandlerContext;", "(Lio/netty/channel/ChannelHandlerContext;)V", "getContext", "()Lio/netty/channel/ChannelHandlerContext;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CIO.kt */
    public static final class CurrentContext extends AbstractCoroutineContextElement {
        private final ChannelHandlerContext context;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public CurrentContext(ChannelHandlerContext channelHandlerContext) {
            super(CurrentContextKey.INSTANCE);
            Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
            this.context = channelHandlerContext;
        }

        public final ChannelHandlerContext getContext() {
            return this.context;
        }
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0003¨\u0006\u0004"}, d2 = {"Lio/ktor/server/netty/NettyDispatcher$CurrentContextKey;", "Lkotlin/coroutines/CoroutineContext$Key;", "Lio/ktor/server/netty/NettyDispatcher$CurrentContext;", "()V", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: CIO.kt */
    public static final class CurrentContextKey implements CoroutineContext.Key<CurrentContext> {
        public static final CurrentContextKey INSTANCE = new CurrentContextKey();

        private CurrentContextKey() {
        }
    }
}
