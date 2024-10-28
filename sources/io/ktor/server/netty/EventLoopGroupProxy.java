package io.ktor.server.netty;

import androidx.tvprovider.media.tv.TvContractCompat;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelPromise;
import io.netty.channel.EventLoop;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.Epoll;
import io.netty.channel.epoll.EpollEventLoopGroup;
import io.netty.channel.kqueue.KQueue;
import io.netty.channel.kqueue.KQueueEventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.ServerSocketChannel;
import io.netty.util.concurrent.DefaultThreadFactory;
import io.netty.util.concurrent.EventExecutor;
import io.netty.util.concurrent.ScheduledFuture;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;

@Metadata(d1 = {"\u0000\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0002\b\b\n\u0002\u0010)\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 92\u00020\u0001:\u00019B\u001d\u0012\u000e\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0001¢\u0006\u0002\u0010\u0006J!\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u000e\u0010\r\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001J\u0019\u0010\u0010\u001a\u00020\u00112\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010\u00120\u0012H\u0001JÝ\u0001\u0010\u0013\u001a^\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u00150\u0015 \u000f*.\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u00150\u0015\u0018\u00010\u00170\u0014\"\u0010\b\u0000\u0010\u0016*\n \u000f*\u0004\u0018\u00010\u00180\u00182d\u0010\u000b\u001a`\u0012*\b\u0001\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a \u000f*.\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a\u0018\u00010\u001b0\u0019H\u0001Jõ\u0001\u0010\u0013\u001a^\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u00150\u0015 \u000f*.\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u00150\u0015\u0018\u00010\u00170\u0014\"\u0010\b\u0000\u0010\u0016*\n \u000f*\u0004\u0018\u00010\u00180\u00182d\u0010\u000b\u001a`\u0012*\b\u0001\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a \u000f*.\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a\u0018\u00010\u001b0\u00192\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u001c\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001J\u0001\u0010\u001d\u001a\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\"\u0010\b\u0000\u0010\u0016*\n \u000f*\u0004\u0018\u00010\u00180\u00182d\u0010\u000b\u001a`\u0012*\b\u0001\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a \u000f*.\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a\u0018\u00010\u001b0\u0019H\u0001¢\u0006\u0002\u0010\u001eJ¦\u0001\u0010\u001d\u001a\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\"\u0010\b\u0000\u0010\u0016*\n \u000f*\u0004\u0018\u00010\u00180\u00182d\u0010\u000b\u001a`\u0012*\b\u0001\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a \u000f*.\u0012(\u0012&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001a\u0018\u00010\u001b0\u00192\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u001c\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001¢\u0006\u0002\u0010\u001fJ\t\u0010 \u001a\u00020\nH\u0001J\t\u0010!\u001a\u00020\nH\u0001J\t\u0010\"\u001a\u00020\nH\u0001J\u0017\u0010#\u001a\u0010\u0012\f\u0012\n \u000f*\u0004\u0018\u00010%0%0$H\u0003J\u0011\u0010&\u001a\n \u000f*\u0004\u0018\u00010'0'H\u0003J!\u0010(\u001a\n \u000f*\u0004\u0018\u00010)0)2\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010*0*H\u0001J1\u0010(\u001a\n \u000f*\u0004\u0018\u00010)0)2\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010*0*2\u000e\u0010\r\u001a\n \u000f*\u0004\u0018\u00010+0+H\u0001J!\u0010(\u001a\n \u000f*\u0004\u0018\u00010)0)2\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010+0+H\u0001JA\u0010,\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u00010-0-2\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010\u00120\u00122\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u001c\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001J\u0001\u0010,\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H.H. \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H.H.\u0018\u00010-0-\"\u0010\b\u0000\u0010.*\n \u000f*\u0004\u0018\u00010\u00180\u00182*\u0010\u000b\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H.H. \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H.H.\u0018\u00010\u001a0\u001a2\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u001c\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001JI\u0010/\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u00010-0-2\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010\u00120\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u000e\u00100\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001JI\u00101\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u00010-0-2\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010\u00120\u00122\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u001c\u001a\u00020\f2\u000e\u00100\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001J\t\u00102\u001a\u00020\u0011H\u0001J\u0019\u00103\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u00010404H\u0001J9\u00103\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u000104042\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\u000e\u0010\u001c\u001a\n \u000f*\u0004\u0018\u00010\u000e0\u000eH\u0001J-\u00105\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00120\u0012 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u00010\u00120\u0012\u0018\u00010\u00170\u0014H\u0001J)\u00106\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u000104042\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010\u00120\u0012H\u0001Jd\u00106\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010404\"\u0010\b\u0000\u0010\u0016*\n \u000f*\u0004\u0018\u00010\u00180\u00182\u000e\u0010\u000b\u001a\n \u000f*\u0004\u0018\u00010\u00120\u00122\u000e\u0010\r\u001a\n \u000f*\u0004\u0018\u0001H\u0016H\u0016H\u0001¢\u0006\u0002\u00107Jk\u00106\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010404\"\u0010\b\u0000\u0010\u0016*\n \u000f*\u0004\u0018\u00010\u00180\u00182*\u0010\u000b\u001a&\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016 \u000f*\u0012\u0012\f\u0012\n \u000f*\u0004\u0018\u0001H\u0016H\u0016\u0018\u00010\u001a0\u001aH\u0001J\u0019\u00108\u001a\u0012\u0012\u0002\b\u0003 \u000f*\b\u0012\u0002\b\u0003\u0018\u00010404H\u0001R\u0019\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006:"}, d2 = {"Lio/ktor/server/netty/EventLoopGroupProxy;", "Lio/netty/channel/EventLoopGroup;", "channel", "Lkotlin/reflect/KClass;", "Lio/netty/channel/socket/ServerSocketChannel;", "group", "(Lkotlin/reflect/KClass;Lio/netty/channel/EventLoopGroup;)V", "getChannel", "()Lkotlin/reflect/KClass;", "awaitTermination", "", "p0", "", "p1", "Ljava/util/concurrent/TimeUnit;", "kotlin.jvm.PlatformType", "execute", "", "Ljava/lang/Runnable;", "invokeAll", "", "Ljava/util/concurrent/Future;", "T", "", "", "", "Ljava/util/concurrent/Callable;", "", "p2", "invokeAny", "(Ljava/util/Collection;)Ljava/lang/Object;", "(Ljava/util/Collection;JLjava/util/concurrent/TimeUnit;)Ljava/lang/Object;", "isShutdown", "isShuttingDown", "isTerminated", "iterator", "", "Lio/netty/util/concurrent/EventExecutor;", "next", "Lio/netty/channel/EventLoop;", "register", "Lio/netty/channel/ChannelFuture;", "Lio/netty/channel/Channel;", "Lio/netty/channel/ChannelPromise;", "schedule", "Lio/netty/util/concurrent/ScheduledFuture;", "V", "scheduleAtFixedRate", "p3", "scheduleWithFixedDelay", "shutdown", "shutdownGracefully", "Lio/netty/util/concurrent/Future;", "shutdownNow", "submit", "(Ljava/lang/Runnable;Ljava/lang/Object;)Lio/netty/util/concurrent/Future;", "terminationFuture", "Companion", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
public final class EventLoopGroupProxy implements EventLoopGroup {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy<Method> prohibitParkingFunction$delegate = LazyKt.lazy(EventLoopGroupProxy$Companion$prohibitParkingFunction$2.INSTANCE);
    private final /* synthetic */ EventLoopGroup $$delegate_0;
    private final KClass<? extends ServerSocketChannel> channel;

    public boolean awaitTermination(long j, TimeUnit timeUnit) {
        return this.$$delegate_0.awaitTermination(j, timeUnit);
    }

    public void execute(Runnable runnable) {
        this.$$delegate_0.execute(runnable);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection) {
        return this.$$delegate_0.invokeAll(collection);
    }

    public <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return this.$$delegate_0.invokeAll(collection, j, timeUnit);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection) {
        return this.$$delegate_0.invokeAny(collection);
    }

    public <T> T invokeAny(Collection<? extends Callable<T>> collection, long j, TimeUnit timeUnit) {
        return this.$$delegate_0.invokeAny(collection, j, timeUnit);
    }

    public boolean isShutdown() {
        return this.$$delegate_0.isShutdown();
    }

    public boolean isShuttingDown() {
        return this.$$delegate_0.isShuttingDown();
    }

    public boolean isTerminated() {
        return this.$$delegate_0.isTerminated();
    }

    public Iterator<EventExecutor> iterator() {
        return this.$$delegate_0.iterator();
    }

    public EventLoop next() {
        return this.$$delegate_0.next();
    }

    public ChannelFuture register(Channel channel2) {
        return this.$$delegate_0.register(channel2);
    }

    @Deprecated(message = "Deprecated in Java")
    public ChannelFuture register(Channel channel2, ChannelPromise channelPromise) {
        return this.$$delegate_0.register(channel2, channelPromise);
    }

    public ChannelFuture register(ChannelPromise channelPromise) {
        return this.$$delegate_0.register(channelPromise);
    }

    public ScheduledFuture<?> schedule(Runnable runnable, long j, TimeUnit timeUnit) {
        return this.$$delegate_0.schedule(runnable, j, timeUnit);
    }

    public <V> ScheduledFuture<V> schedule(Callable<V> callable, long j, TimeUnit timeUnit) {
        return this.$$delegate_0.schedule(callable, j, timeUnit);
    }

    public ScheduledFuture<?> scheduleAtFixedRate(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.$$delegate_0.scheduleAtFixedRate(runnable, j, j2, timeUnit);
    }

    public ScheduledFuture<?> scheduleWithFixedDelay(Runnable runnable, long j, long j2, TimeUnit timeUnit) {
        return this.$$delegate_0.scheduleWithFixedDelay(runnable, j, j2, timeUnit);
    }

    @Deprecated(message = "Deprecated in Java")
    public void shutdown() {
        this.$$delegate_0.shutdown();
    }

    public io.netty.util.concurrent.Future<?> shutdownGracefully() {
        return this.$$delegate_0.shutdownGracefully();
    }

    public io.netty.util.concurrent.Future<?> shutdownGracefully(long j, long j2, TimeUnit timeUnit) {
        return this.$$delegate_0.shutdownGracefully(j, j2, timeUnit);
    }

    @Deprecated(message = "Deprecated in Java")
    public List<Runnable> shutdownNow() {
        return this.$$delegate_0.shutdownNow();
    }

    public io.netty.util.concurrent.Future<?> submit(Runnable runnable) {
        return this.$$delegate_0.submit(runnable);
    }

    public <T> io.netty.util.concurrent.Future<T> submit(Runnable runnable, T t) {
        return this.$$delegate_0.submit(runnable, t);
    }

    public <T> io.netty.util.concurrent.Future<T> submit(Callable<T> callable) {
        return this.$$delegate_0.submit(callable);
    }

    public io.netty.util.concurrent.Future<?> terminationFuture() {
        return this.$$delegate_0.terminationFuture();
    }

    public EventLoopGroupProxy(KClass<? extends ServerSocketChannel> kClass, EventLoopGroup eventLoopGroup) {
        Intrinsics.checkNotNullParameter(kClass, TvContractCompat.PARAM_CHANNEL);
        Intrinsics.checkNotNullParameter(eventLoopGroup, "group");
        this.channel = kClass;
        this.$$delegate_0 = eventLoopGroup;
    }

    public final KClass<? extends ServerSocketChannel> getChannel() {
        return this.channel;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\b\u0010\r\u001a\u00020\u000eH\u0002R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048BX\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000f"}, d2 = {"Lio/ktor/server/netty/EventLoopGroupProxy$Companion;", "", "()V", "prohibitParkingFunction", "Ljava/lang/reflect/Method;", "getProhibitParkingFunction", "()Ljava/lang/reflect/Method;", "prohibitParkingFunction$delegate", "Lkotlin/Lazy;", "create", "Lio/ktor/server/netty/EventLoopGroupProxy;", "parallelism", "", "markParkingProhibited", "", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyApplicationEngine.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final EventLoopGroupProxy create(int i) {
            EventLoopGroupProxy$Companion$$ExternalSyntheticLambda1 eventLoopGroupProxy$Companion$$ExternalSyntheticLambda1 = new EventLoopGroupProxy$Companion$$ExternalSyntheticLambda1(new DefaultThreadFactory((Class<?>) EventLoopGroupProxy.class, true));
            KClass access$getChannelClass = NettyApplicationEngineKt.getChannelClass();
            if (KQueue.isAvailable()) {
                return new EventLoopGroupProxy(access$getChannelClass, new KQueueEventLoopGroup(i, (ThreadFactory) eventLoopGroupProxy$Companion$$ExternalSyntheticLambda1));
            }
            if (Epoll.isAvailable()) {
                return new EventLoopGroupProxy(access$getChannelClass, new EpollEventLoopGroup(i, (ThreadFactory) eventLoopGroupProxy$Companion$$ExternalSyntheticLambda1));
            }
            return new EventLoopGroupProxy(access$getChannelClass, new NioEventLoopGroup(i, (ThreadFactory) eventLoopGroupProxy$Companion$$ExternalSyntheticLambda1));
        }

        /* access modifiers changed from: private */
        public static final Thread create$lambda$1(DefaultThreadFactory defaultThreadFactory, Runnable runnable) {
            Intrinsics.checkNotNullParameter(defaultThreadFactory, "$defaultFactory");
            return defaultThreadFactory.newThread(new EventLoopGroupProxy$Companion$$ExternalSyntheticLambda0(runnable));
        }

        /* access modifiers changed from: private */
        public static final void create$lambda$1$lambda$0(Runnable runnable) {
            EventLoopGroupProxy.Companion.markParkingProhibited();
            runnable.run();
        }

        private final Method getProhibitParkingFunction() {
            return (Method) EventLoopGroupProxy.prohibitParkingFunction$delegate.getValue();
        }

        private final void markParkingProhibited() {
            try {
                Method prohibitParkingFunction = getProhibitParkingFunction();
                if (prohibitParkingFunction != null) {
                    prohibitParkingFunction.invoke((Object) null, (Object[]) null);
                }
            } catch (Throwable unused) {
            }
        }
    }
}
