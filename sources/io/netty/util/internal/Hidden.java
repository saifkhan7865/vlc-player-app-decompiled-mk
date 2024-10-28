package io.netty.util.internal;

import io.netty.util.concurrent.FastThreadLocalThread;
import j$.util.function.Function$CC;
import j$.util.function.Predicate$CC;
import java.util.function.Function;
import java.util.function.Predicate;
import reactor.blockhound.BlockHound;
import reactor.blockhound.integration.BlockHoundIntegration;

class Hidden {
    Hidden() {
    }

    public static final class NettyBlockHoundIntegration implements BlockHoundIntegration {
        public int compareTo(BlockHoundIntegration blockHoundIntegration) {
            return 0;
        }

        public void applyTo(BlockHound.Builder builder) {
            builder.allowBlockingCallsInside("io.netty.channel.nio.NioEventLoop", "handleLoopException");
            builder.allowBlockingCallsInside("io.netty.channel.kqueue.KQueueEventLoop", "handleLoopException");
            builder.allowBlockingCallsInside("io.netty.channel.epoll.EpollEventLoop", "handleLoopException");
            builder.allowBlockingCallsInside("io.netty.util.HashedWheelTimer", "start");
            builder.allowBlockingCallsInside("io.netty.util.HashedWheelTimer", "stop");
            builder.allowBlockingCallsInside("io.netty.util.HashedWheelTimer$Worker", "waitForNextTick");
            builder.allowBlockingCallsInside("io.netty.util.concurrent.SingleThreadEventExecutor", "confirmShutdown");
            builder.allowBlockingCallsInside("io.netty.buffer.PoolArena", "lock");
            builder.allowBlockingCallsInside("io.netty.buffer.PoolSubpage", "lock");
            builder.allowBlockingCallsInside("io.netty.buffer.PoolChunk", "allocateRun");
            builder.allowBlockingCallsInside("io.netty.buffer.PoolChunk", "free");
            builder.allowBlockingCallsInside("io.netty.handler.ssl.SslHandler", "handshake");
            builder.allowBlockingCallsInside("io.netty.handler.ssl.SslHandler", "runAllDelegatedTasks");
            builder.allowBlockingCallsInside("io.netty.handler.ssl.SslHandler", "runDelegatedTasks");
            builder.allowBlockingCallsInside("io.netty.util.concurrent.GlobalEventExecutor", "takeTask");
            builder.allowBlockingCallsInside("io.netty.util.concurrent.GlobalEventExecutor", "addTask");
            builder.allowBlockingCallsInside("io.netty.util.concurrent.SingleThreadEventExecutor", "takeTask");
            builder.allowBlockingCallsInside("io.netty.util.concurrent.SingleThreadEventExecutor", "addTask");
            builder.allowBlockingCallsInside("io.netty.handler.ssl.ReferenceCountedOpenSslClientContext$ExtendedTrustManagerVerifyCallback", "verify");
            builder.allowBlockingCallsInside("io.netty.handler.ssl.JdkSslContext$Defaults", "init");
            builder.allowBlockingCallsInside("sun.security.ssl.SSLEngineImpl", "unwrap");
            builder.allowBlockingCallsInside("sun.security.ssl.SSLEngineImpl", "wrap");
            builder.allowBlockingCallsInside("io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider", "parse");
            builder.allowBlockingCallsInside("io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider", "parseEtcResolverSearchDomains");
            builder.allowBlockingCallsInside("io.netty.resolver.dns.UnixResolverDnsServerAddressStreamProvider", "parseEtcResolverOptions");
            builder.allowBlockingCallsInside("io.netty.resolver.HostsFileEntriesProvider$ParserImpl", "parse");
            builder.allowBlockingCallsInside("io.netty.util.NetUtil$SoMaxConnAction", "run");
            builder.allowBlockingCallsInside("io.netty.util.internal.ReferenceCountUpdater", "retryRelease0");
            builder.allowBlockingCallsInside("io.netty.util.internal.PlatformDependent", "createTempFile");
            builder.nonBlockingThreadPredicate(new Function<Predicate<Thread>, Predicate<Thread>>() {
                public /* synthetic */ Function andThen(Function function) {
                    return Function$CC.$default$andThen(this, function);
                }

                public /* synthetic */ Function compose(Function function) {
                    return Function$CC.$default$compose(this, function);
                }

                public Predicate<Thread> apply(final Predicate<Thread> predicate) {
                    return new Predicate<Thread>() {
                        public /* synthetic */ Predicate and(Predicate predicate) {
                            return Predicate$CC.$default$and(this, predicate);
                        }

                        public /* synthetic */ Predicate negate() {
                            return Predicate$CC.$default$negate(this);
                        }

                        public /* synthetic */ Predicate or(Predicate predicate) {
                            return Predicate$CC.$default$or(this, predicate);
                        }

                        public boolean test(Thread thread) {
                            return predicate.test(thread) || ((thread instanceof FastThreadLocalThread) && !((FastThreadLocalThread) thread).permitBlockingCalls());
                        }
                    };
                }
            });
        }
    }
}
