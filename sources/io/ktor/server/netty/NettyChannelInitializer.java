package io.ktor.server.netty;

import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.EngineConnectorConfig;
import io.ktor.server.engine.EnginePipeline;
import io.ktor.server.engine.EngineSSLConnectorConfig;
import io.ktor.server.netty.http1.NettyHttp1Handler;
import io.ktor.server.netty.http2.NettyHttp2Handler;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.HttpServerExpectContinueHandler;
import io.netty.handler.codec.http2.Http2MultiplexCodecBuilder;
import io.netty.handler.codec.http2.Http2SecurityUtil;
import io.netty.handler.codec.rtsp.RtspHeaders;
import io.netty.handler.ssl.ApplicationProtocolConfig;
import io.netty.handler.ssl.ApplicationProtocolNames;
import io.netty.handler.ssl.ApplicationProtocolNegotiationHandler;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.ssl.SslProvider;
import io.netty.handler.ssl.SupportedCipherSuiteFilter;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import java.nio.channels.ClosedChannelException;
import java.security.Key;
import java.security.PrivateKey;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CancellationException;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.TrustManagerFactory;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScopeKt;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 )2\b\u0012\u0004\u0012\u00020\u00020\u0001:\u0002)*B|\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u000f\u0012\u0006\u0010\u0012\u001a\u00020\u000f\u0012\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014\u0012\u0017\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017¢\u0006\u0002\b\u001a¢\u0006\u0002\u0010\u001bJ\u0018\u0010\u001e\u001a\u00020\u00192\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!H\u0002J\u0010\u0010\"\u001a\u00020\u00192\u0006\u0010#\u001a\u00020\u0002H\u0014J\f\u0010$\u001a\u00020%*\u00020&H\u0002J\u000e\u0010'\u001a\u0004\u0018\u00010(*\u00020&H\u0002R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u001f\u0010\u0016\u001a\u0013\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017¢\u0006\u0002\b\u001aX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u0014X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001c\u001a\u0004\u0018\u00010\u001dX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lio/ktor/server/netty/NettyChannelInitializer;", "Lio/netty/channel/ChannelInitializer;", "Lio/netty/channel/socket/SocketChannel;", "enginePipeline", "Lio/ktor/server/engine/EnginePipeline;", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "callEventGroup", "Lio/netty/util/concurrent/EventExecutorGroup;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "connector", "Lio/ktor/server/engine/EngineConnectorConfig;", "requestQueueLimit", "", "runningLimit", "responseWriteTimeout", "requestReadTimeout", "httpServerCodec", "Lkotlin/Function0;", "Lio/netty/handler/codec/http/HttpServerCodec;", "channelPipelineConfig", "Lkotlin/Function1;", "Lio/netty/channel/ChannelPipeline;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/engine/EnginePipeline;Lio/ktor/server/engine/ApplicationEngineEnvironment;Lio/netty/util/concurrent/EventExecutorGroup;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Lio/ktor/server/engine/EngineConnectorConfig;IIIILkotlin/jvm/functions/Function0;Lkotlin/jvm/functions/Function1;)V", "sslContext", "Lio/netty/handler/ssl/SslContext;", "configurePipeline", "pipeline", "protocol", "", "initChannel", "ch", "hasTrustStore", "", "Lio/ktor/server/engine/EngineSSLConnectorConfig;", "trustManagerFactory", "Ljavax/net/ssl/TrustManagerFactory;", "Companion", "NegotiatedPipelineInitializer", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyChannelInitializer.kt */
public final class NettyChannelInitializer extends ChannelInitializer<SocketChannel> {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    /* access modifiers changed from: private */
    public static final Lazy<SslProvider> alpnProvider$delegate = LazyKt.lazy(NettyChannelInitializer$Companion$alpnProvider$2.INSTANCE);
    private final EventExecutorGroup callEventGroup;
    private final Function1<ChannelPipeline, Unit> channelPipelineConfig;
    private final EngineConnectorConfig connector;
    private final CoroutineContext engineContext;
    private final EnginePipeline enginePipeline;
    private final ApplicationEngineEnvironment environment;
    private final Function0<HttpServerCodec> httpServerCodec;
    private final int requestQueueLimit;
    private final int requestReadTimeout;
    private final int responseWriteTimeout;
    private final int runningLimit;
    private SslContext sslContext;
    private final CoroutineContext userContext;

    public NettyChannelInitializer(EnginePipeline enginePipeline2, ApplicationEngineEnvironment applicationEngineEnvironment, EventExecutorGroup eventExecutorGroup, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, EngineConnectorConfig engineConnectorConfig, int i, int i2, int i3, int i4, Function0<HttpServerCodec> function0, Function1<? super ChannelPipeline, Unit> function1) {
        Intrinsics.checkNotNullParameter(enginePipeline2, "enginePipeline");
        Intrinsics.checkNotNullParameter(applicationEngineEnvironment, "environment");
        Intrinsics.checkNotNullParameter(eventExecutorGroup, "callEventGroup");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        Intrinsics.checkNotNullParameter(engineConnectorConfig, "connector");
        Intrinsics.checkNotNullParameter(function0, "httpServerCodec");
        Intrinsics.checkNotNullParameter(function1, "channelPipelineConfig");
        this.enginePipeline = enginePipeline2;
        this.environment = applicationEngineEnvironment;
        this.callEventGroup = eventExecutorGroup;
        this.engineContext = coroutineContext;
        this.userContext = coroutineContext2;
        this.connector = engineConnectorConfig;
        this.requestQueueLimit = i;
        this.runningLimit = i2;
        this.responseWriteTimeout = i3;
        this.requestReadTimeout = i4;
        this.httpServerCodec = function0;
        this.channelPipelineConfig = function1;
        if (engineConnectorConfig instanceof EngineSSLConnectorConfig) {
            Certificate[] certificateChain = ((EngineSSLConnectorConfig) engineConnectorConfig).getKeyStore().getCertificateChain(((EngineSSLConnectorConfig) engineConnectorConfig).getKeyAlias());
            Intrinsics.checkNotNullExpressionValue(certificateChain, "connector.keyStore.getCe…Chain(connector.keyAlias)");
            List list = ArraysKt.toList((T[]) (Object[]) certificateChain);
            Intrinsics.checkNotNull(list, "null cannot be cast to non-null type kotlin.collections.List<java.security.cert.X509Certificate>");
            X509Certificate[] x509CertificateArr = (X509Certificate[]) CollectionsKt.toList(list).toArray(new X509Certificate[0]);
            char[] invoke = ((EngineSSLConnectorConfig) engineConnectorConfig).getPrivateKeyPassword().invoke();
            Key key = ((EngineSSLConnectorConfig) engineConnectorConfig).getKeyStore().getKey(((EngineSSLConnectorConfig) engineConnectorConfig).getKeyAlias(), invoke);
            Intrinsics.checkNotNull(key, "null cannot be cast to non-null type java.security.PrivateKey");
            ArraysKt.fill$default(invoke, 0, 0, 0, 6, (Object) null);
            SslContextBuilder forServer = SslContextBuilder.forServer((PrivateKey) key, (X509Certificate[]) Arrays.copyOf(x509CertificateArr, x509CertificateArr.length));
            Companion companion = Companion;
            if (companion.getAlpnProvider$ktor_server_netty() != null) {
                forServer.sslProvider(companion.getAlpnProvider$ktor_server_netty());
                forServer.ciphers(Http2SecurityUtil.CIPHERS, SupportedCipherSuiteFilter.INSTANCE);
                forServer.applicationProtocolConfig(new ApplicationProtocolConfig(ApplicationProtocolConfig.Protocol.ALPN, ApplicationProtocolConfig.SelectorFailureBehavior.NO_ADVERTISE, ApplicationProtocolConfig.SelectedListenerFailureBehavior.ACCEPT, ApplicationProtocolNames.HTTP_2, ApplicationProtocolNames.HTTP_1_1));
            }
            TrustManagerFactory trustManagerFactory = trustManagerFactory((EngineSSLConnectorConfig) engineConnectorConfig);
            if (trustManagerFactory != null) {
                forServer.trustManager(trustManagerFactory);
            }
            this.sslContext = forServer.build();
        }
    }

    /* access modifiers changed from: protected */
    public void initChannel(SocketChannel socketChannel) {
        Intrinsics.checkNotNullParameter(socketChannel, "ch");
        ChannelPipeline pipeline = socketChannel.pipeline();
        if (this.connector instanceof EngineSSLConnectorConfig) {
            SslContext sslContext2 = this.sslContext;
            Intrinsics.checkNotNull(sslContext2);
            SSLEngine newEngine = sslContext2.newEngine(socketChannel.alloc());
            if (hasTrustStore((EngineSSLConnectorConfig) this.connector)) {
                newEngine.setUseClientMode(false);
                newEngine.setNeedClientAuth(true);
            }
            List<String> enabledProtocols = ((EngineSSLConnectorConfig) this.connector).getEnabledProtocols();
            if (enabledProtocols != null) {
                newEngine.setEnabledProtocols((String[]) enabledProtocols.toArray(new String[0]));
            }
            pipeline.addLast("ssl", (ChannelHandler) new SslHandler(newEngine));
            if (Companion.getAlpnProvider$ktor_server_netty() != null) {
                pipeline.addLast(new NegotiatedPipelineInitializer());
                return;
            }
            Intrinsics.checkNotNullExpressionValue(pipeline, "this");
            configurePipeline(pipeline, ApplicationProtocolNames.HTTP_1_1);
            return;
        }
        Intrinsics.checkNotNullExpressionValue(pipeline, "this");
        configurePipeline(pipeline, ApplicationProtocolNames.HTTP_1_1);
    }

    /* access modifiers changed from: private */
    public final void configurePipeline(ChannelPipeline channelPipeline, String str) {
        if (Intrinsics.areEqual((Object) str, (Object) ApplicationProtocolNames.HTTP_2)) {
            NettyHttp2Handler nettyHttp2Handler = new NettyHttp2Handler(this.enginePipeline, this.environment.getApplication(), this.callEventGroup, this.userContext, this.runningLimit);
            channelPipeline.addLast(Http2MultiplexCodecBuilder.forServer(nettyHttp2Handler).build());
            channelPipeline.channel().closeFuture().addListener(new NettyChannelInitializer$$ExternalSyntheticLambda0(nettyHttp2Handler));
            this.channelPipelineConfig.invoke(channelPipeline);
        } else if (Intrinsics.areEqual((Object) str, (Object) ApplicationProtocolNames.HTTP_1_1)) {
            NettyHttp1Handler nettyHttp1Handler = new NettyHttp1Handler(this.enginePipeline, this.environment, this.callEventGroup, this.engineContext, this.userContext, this.runningLimit);
            if (this.requestReadTimeout > 0) {
                channelPipeline.addLast("readTimeout", (ChannelHandler) new ReadTimeoutHandler(this.requestReadTimeout));
            }
            channelPipeline.addLast("codec", (ChannelHandler) this.httpServerCodec.invoke());
            channelPipeline.addLast("continue", (ChannelHandler) new HttpServerExpectContinueHandler());
            channelPipeline.addLast(RtspHeaders.Values.TIMEOUT, (ChannelHandler) new WriteTimeoutHandler(this.responseWriteTimeout));
            channelPipeline.addLast("http1", (ChannelHandler) nettyHttp1Handler);
            this.channelPipelineConfig.invoke(channelPipeline);
            channelPipeline.context("codec").fireChannelActive();
        } else {
            Logger log = this.environment.getLog();
            log.error("Unsupported protocol " + str);
            channelPipeline.close();
        }
    }

    /* access modifiers changed from: private */
    public static final void configurePipeline$lambda$5(NettyHttp2Handler nettyHttp2Handler, Future future) {
        Intrinsics.checkNotNullParameter(nettyHttp2Handler, "$handler");
        CoroutineScopeKt.cancel$default(nettyHttp2Handler, (CancellationException) null, 1, (Object) null);
    }

    private final boolean hasTrustStore(EngineSSLConnectorConfig engineSSLConnectorConfig) {
        return (engineSSLConnectorConfig.getTrustStore() == null && engineSSLConnectorConfig.getTrustStorePath() == null) ? false : true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0029, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x002a, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r4);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x002d, code lost:
        throw r1;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final javax.net.ssl.TrustManagerFactory trustManagerFactory(io.ktor.server.engine.EngineSSLConnectorConfig r4) {
        /*
            r3 = this;
            java.security.KeyStore r0 = r4.getTrustStore()
            r1 = 0
            if (r0 != 0) goto L_0x002f
            java.io.File r4 = r4.getTrustStorePath()
            if (r4 == 0) goto L_0x002e
            java.io.FileInputStream r0 = new java.io.FileInputStream
            r0.<init>(r4)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r4 = r0
            java.io.FileInputStream r4 = (java.io.FileInputStream) r4     // Catch:{ all -> 0x0027 }
            java.lang.String r2 = "JKS"
            java.security.KeyStore r2 = java.security.KeyStore.getInstance(r2)     // Catch:{ all -> 0x0027 }
            java.io.InputStream r4 = (java.io.InputStream) r4     // Catch:{ all -> 0x0027 }
            r2.load(r4, r1)     // Catch:{ all -> 0x0027 }
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            r0 = r2
            goto L_0x002f
        L_0x0027:
            r4 = move-exception
            throw r4     // Catch:{ all -> 0x0029 }
        L_0x0029:
            r1 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r4)
            throw r1
        L_0x002e:
            r0 = r1
        L_0x002f:
            if (r0 == 0) goto L_0x003c
            java.lang.String r4 = javax.net.ssl.TrustManagerFactory.getDefaultAlgorithm()
            javax.net.ssl.TrustManagerFactory r1 = javax.net.ssl.TrustManagerFactory.getInstance(r4)
            r1.init(r0)
        L_0x003c:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.NettyChannelInitializer.trustManagerFactory(io.ktor.server.engine.EngineSSLConnectorConfig):javax.net.ssl.TrustManagerFactory");
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\b\u0004\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0014J\u001a\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014¨\u0006\f"}, d2 = {"Lio/ktor/server/netty/NettyChannelInitializer$NegotiatedPipelineInitializer;", "Lio/netty/handler/ssl/ApplicationProtocolNegotiationHandler;", "(Lio/ktor/server/netty/NettyChannelInitializer;)V", "configurePipeline", "", "ctx", "Lio/netty/channel/ChannelHandlerContext;", "protocol", "", "handshakeFailure", "cause", "", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyChannelInitializer.kt */
    private final class NegotiatedPipelineInitializer extends ApplicationProtocolNegotiationHandler {
        public NegotiatedPipelineInitializer() {
            super(ApplicationProtocolNames.HTTP_1_1);
        }

        /* access modifiers changed from: protected */
        public void configurePipeline(ChannelHandlerContext channelHandlerContext, String str) {
            Intrinsics.checkNotNullParameter(channelHandlerContext, "ctx");
            Intrinsics.checkNotNullParameter(str, "protocol");
            NettyChannelInitializer nettyChannelInitializer = NettyChannelInitializer.this;
            ChannelPipeline pipeline = channelHandlerContext.pipeline();
            Intrinsics.checkNotNullExpressionValue(pipeline, "ctx.pipeline()");
            nettyChannelInitializer.configurePipeline(pipeline, str);
        }

        /* access modifiers changed from: protected */
        public void handshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th) {
            Intrinsics.checkNotNullParameter(channelHandlerContext, "ctx");
            if (th instanceof ClosedChannelException) {
                channelHandlerContext.close();
            } else {
                super.handshakeFailure(channelHandlerContext, th);
            }
        }
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\n\u0010\t\u001a\u0004\u0018\u00010\u0004H\u0002R\u001d\u0010\u0003\u001a\u0004\u0018\u00010\u00048@X\u0002¢\u0006\f\n\u0004\b\u0007\u0010\b\u001a\u0004\b\u0005\u0010\u0006¨\u0006\n"}, d2 = {"Lio/ktor/server/netty/NettyChannelInitializer$Companion;", "", "()V", "alpnProvider", "Lio/netty/handler/ssl/SslProvider;", "getAlpnProvider$ktor_server_netty", "()Lio/netty/handler/ssl/SslProvider;", "alpnProvider$delegate", "Lkotlin/Lazy;", "findAlpnProvider", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyChannelInitializer.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final SslProvider getAlpnProvider$ktor_server_netty() {
            return (SslProvider) NettyChannelInitializer.alpnProvider$delegate.getValue();
        }

        /* access modifiers changed from: private */
        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|(2:3|4)|5|6|(2:8|9)(1:12)) */
        /* JADX WARNING: Code restructure failed: missing block: B:11:?, code lost:
            return null;
         */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x000b */
        /* JADX WARNING: Removed duplicated region for block: B:12:? A[RETURN, SYNTHETIC] */
        /* JADX WARNING: Removed duplicated region for block: B:8:0x0013 A[Catch:{ all -> 0x0016 }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public final io.netty.handler.ssl.SslProvider findAlpnProvider() {
            /*
                r1 = this;
                io.netty.handler.ssl.SslProvider r0 = io.netty.handler.ssl.SslProvider.OPENSSL     // Catch:{ all -> 0x000b }
                boolean r0 = io.netty.handler.ssl.SslProvider.isAlpnSupported(r0)     // Catch:{ all -> 0x000b }
                if (r0 == 0) goto L_0x000b
                io.netty.handler.ssl.SslProvider r0 = io.netty.handler.ssl.SslProvider.OPENSSL     // Catch:{ all -> 0x000b }
                return r0
            L_0x000b:
                io.netty.handler.ssl.SslProvider r0 = io.netty.handler.ssl.SslProvider.JDK     // Catch:{ all -> 0x0016 }
                boolean r0 = io.netty.handler.ssl.SslProvider.isAlpnSupported(r0)     // Catch:{ all -> 0x0016 }
                if (r0 == 0) goto L_0x0016
                io.netty.handler.ssl.SslProvider r0 = io.netty.handler.ssl.SslProvider.JDK     // Catch:{ all -> 0x0016 }
                return r0
            L_0x0016:
                r0 = 0
                return r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.NettyChannelInitializer.Companion.findAlpnProvider():io.netty.handler.ssl.SslProvider");
        }
    }
}
