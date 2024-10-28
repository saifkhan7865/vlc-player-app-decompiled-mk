package io.ktor.server.netty;

import io.ktor.events.EventsKt;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.DefaultApplicationEventsKt;
import io.ktor.server.engine.ApplicationEngine;
import io.ktor.server.engine.ApplicationEngineEnvironment;
import io.ktor.server.engine.BaseApplicationEngine;
import io.ktor.server.engine.DefaultUncaughtExceptionHandler;
import io.ktor.server.engine.EngineConnectorConfig;
import io.ktor.server.engine.EngineConnectorConfigJvmKt;
import io.ktor.server.engine.EngineContextCancellationHelperKt;
import io.ktor.server.engine.EnginePipeline;
import io.ktor.server.engine.ShutdownHookJvmKt;
import io.ktor.util.network.NetworkAddressJvmKt;
import io.ktor.util.pipeline.PipelineContext;
import io.ktor.util.pipeline.PipelinePhase;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.handler.codec.http.HttpServerCodec;
import java.net.BindException;
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.ExecutorCoroutineDispatcher;

@Metadata(d1 = {"\u0000|\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001>B(\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0019\b\u0002\u0010\u0004\u001a\u0013\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\b\b¢\u0006\u0002\u0010\tJ\u0010\u00101\u001a\u00020\f2\u0006\u00102\u001a\u000203H\u0002J\u0010\u00104\u001a\u00020\u00002\u0006\u00105\u001a\u000206H\u0016J\u0018\u00107\u001a\u00020\u00072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u000209H\u0016J\b\u0010;\u001a\u00020\u0007H\u0002J\b\u0010<\u001a\u00020=H\u0016R!\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b8@X\u0002¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000eR\u001b\u0010\u0011\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0010\u001a\u0004\b\u0013\u0010\u0014R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0018\u001a\n\u0012\u0004\u0012\u00020\u0019\u0018\u00010\u000bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001a\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u001b\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b\u001d\u0010\u0010\u001a\u0004\b\u001c\u0010\u0014R\u001b\u0010\u001e\u001a\u00020\f8BX\u0002¢\u0006\f\n\u0004\b!\u0010\u0010\u001a\u0004\b\u001f\u0010 R\u001b\u0010\"\u001a\u00020#8BX\u0002¢\u0006\f\n\u0004\b&\u0010\u0010\u001a\u0004\b$\u0010%R\u000e\u0010'\u001a\u00020(X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010)\u001a\u00020*8BX\u0002¢\u0006\f\n\u0004\b-\u0010\u0010\u001a\u0004\b+\u0010,R\u001b\u0010.\u001a\u00020\u00128BX\u0002¢\u0006\f\n\u0004\b0\u0010\u0010\u001a\u0004\b/\u0010\u0014¨\u0006?"}, d2 = {"Lio/ktor/server/netty/NettyApplicationEngine;", "Lio/ktor/server/engine/BaseApplicationEngine;", "environment", "Lio/ktor/server/engine/ApplicationEngineEnvironment;", "configure", "Lkotlin/Function1;", "Lio/ktor/server/netty/NettyApplicationEngine$Configuration;", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/engine/ApplicationEngineEnvironment;Lkotlin/jvm/functions/Function1;)V", "bootstraps", "", "Lio/netty/bootstrap/ServerBootstrap;", "getBootstraps$ktor_server_netty", "()Ljava/util/List;", "bootstraps$delegate", "Lkotlin/Lazy;", "callEventGroup", "Lio/netty/channel/EventLoopGroup;", "getCallEventGroup", "()Lio/netty/channel/EventLoopGroup;", "callEventGroup$delegate", "cancellationDeferred", "Lkotlinx/coroutines/CompletableJob;", "channels", "Lio/netty/channel/Channel;", "configuration", "connectionEventGroup", "getConnectionEventGroup", "connectionEventGroup$delegate", "customBootstrap", "getCustomBootstrap", "()Lio/netty/bootstrap/ServerBootstrap;", "customBootstrap$delegate", "nettyDispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "getNettyDispatcher", "()Lkotlinx/coroutines/CoroutineDispatcher;", "nettyDispatcher$delegate", "userContext", "Lkotlin/coroutines/CoroutineContext;", "workerDispatcher", "Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "getWorkerDispatcher", "()Lkotlinx/coroutines/ExecutorCoroutineDispatcher;", "workerDispatcher$delegate", "workerEventGroup", "getWorkerEventGroup", "workerEventGroup$delegate", "createBootstrap", "connector", "Lio/ktor/server/engine/EngineConnectorConfig;", "start", "wait", "", "stop", "gracePeriodMillis", "", "timeoutMillis", "terminate", "toString", "", "Configuration", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationEngine.kt */
public final class NettyApplicationEngine extends BaseApplicationEngine {
    private final Lazy bootstraps$delegate;
    private final Lazy callEventGroup$delegate;
    private CompletableJob cancellationDeferred;
    private List<? extends Channel> channels;
    /* access modifiers changed from: private */
    public final Configuration configuration;
    private final Lazy connectionEventGroup$delegate;
    private final Lazy customBootstrap$delegate;
    private final Lazy nettyDispatcher$delegate;
    private final CoroutineContext userContext;
    private final Lazy workerDispatcher$delegate;
    private final Lazy workerEventGroup$delegate;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ NettyApplicationEngine(ApplicationEngineEnvironment applicationEngineEnvironment, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicationEngineEnvironment, (i & 2) != 0 ? AnonymousClass1.INSTANCE : function1);
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyApplicationEngine(ApplicationEngineEnvironment applicationEngineEnvironment, Function1<? super Configuration, Unit> function1) {
        super(applicationEngineEnvironment, (EnginePipeline) null, 2, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(applicationEngineEnvironment, "environment");
        Intrinsics.checkNotNullParameter(function1, "configure");
        Configuration configuration2 = new Configuration();
        function1.invoke(configuration2);
        this.configuration = configuration2;
        this.connectionEventGroup$delegate = LazyKt.lazy(new NettyApplicationEngine$connectionEventGroup$2(this));
        this.workerEventGroup$delegate = LazyKt.lazy(new NettyApplicationEngine$workerEventGroup$2(this));
        this.customBootstrap$delegate = LazyKt.lazy(new NettyApplicationEngine$customBootstrap$2(this));
        this.callEventGroup$delegate = LazyKt.lazy(new NettyApplicationEngine$callEventGroup$2(this));
        this.nettyDispatcher$delegate = LazyKt.lazy(NettyApplicationEngine$nettyDispatcher$2.INSTANCE);
        this.workerDispatcher$delegate = LazyKt.lazy(new NettyApplicationEngine$workerDispatcher$2(this));
        this.bootstraps$delegate = LazyKt.lazy(new NettyApplicationEngine$bootstraps$2(applicationEngineEnvironment, this));
        this.userContext = applicationEngineEnvironment.getParentCoroutineContext().plus(getNettyDispatcher()).plus(NettyApplicationCallHandler.Companion.getCallHandlerCoroutineName$ktor_server_netty()).plus(new DefaultUncaughtExceptionHandler(applicationEngineEnvironment.getLog()));
        PipelinePhase pipelinePhase = new PipelinePhase("After");
        getPipeline().insertPhaseAfter(EnginePipeline.Companion.getCall(), pipelinePhase);
        getPipeline().intercept(pipelinePhase, new AnonymousClass2((Continuation<? super AnonymousClass2>) null));
    }

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0017\n\u0002\u0010\u000b\n\u0002\b\t\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\b\u00108\u001a\u00020\u0012H\u0002R+\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u000bR+\u0010\f\u001a\u0013\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0002\b\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\t\"\u0004\b\u000f\u0010\u000bR \u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u001a\u0010\u0017\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR\u001a\u0010\u001d\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001e\u0010\u001a\"\u0004\b\u001f\u0010\u001cR\u001a\u0010 \u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u001a\"\u0004\b\"\u0010\u001cR\u001a\u0010#\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u001a\"\u0004\b%\u0010\u001cR\u001a\u0010&\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR\u001a\u0010)\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u001a\"\u0004\b+\u0010\u001cR\u001a\u0010,\u001a\u00020\u0018X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b-\u0010\u001a\"\u0004\b.\u0010\u001cR\u001a\u0010/\u001a\u000200X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b1\u00102\"\u0004\b3\u00104R\u001a\u00105\u001a\u000200X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b6\u00102\"\u0004\b7\u00104¨\u00069"}, d2 = {"Lio/ktor/server/netty/NettyApplicationEngine$Configuration;", "Lio/ktor/server/engine/BaseApplicationEngine$Configuration;", "()V", "channelPipelineConfig", "Lkotlin/Function1;", "Lio/netty/channel/ChannelPipeline;", "", "Lkotlin/ExtensionFunctionType;", "getChannelPipelineConfig", "()Lkotlin/jvm/functions/Function1;", "setChannelPipelineConfig", "(Lkotlin/jvm/functions/Function1;)V", "configureBootstrap", "Lio/netty/bootstrap/ServerBootstrap;", "getConfigureBootstrap", "setConfigureBootstrap", "httpServerCodec", "Lkotlin/Function0;", "Lio/netty/handler/codec/http/HttpServerCodec;", "getHttpServerCodec", "()Lkotlin/jvm/functions/Function0;", "setHttpServerCodec", "(Lkotlin/jvm/functions/Function0;)V", "maxChunkSize", "", "getMaxChunkSize", "()I", "setMaxChunkSize", "(I)V", "maxHeaderSize", "getMaxHeaderSize", "setMaxHeaderSize", "maxInitialLineLength", "getMaxInitialLineLength", "setMaxInitialLineLength", "requestQueueLimit", "getRequestQueueLimit", "setRequestQueueLimit", "requestReadTimeoutSeconds", "getRequestReadTimeoutSeconds", "setRequestReadTimeoutSeconds", "responseWriteTimeoutSeconds", "getResponseWriteTimeoutSeconds", "setResponseWriteTimeoutSeconds", "runningLimit", "getRunningLimit", "setRunningLimit", "shareWorkGroup", "", "getShareWorkGroup", "()Z", "setShareWorkGroup", "(Z)V", "tcpKeepAlive", "getTcpKeepAlive", "setTcpKeepAlive", "defaultHttpServerCodec", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyApplicationEngine.kt */
    public static final class Configuration extends BaseApplicationEngine.Configuration {
        private Function1<? super ChannelPipeline, Unit> channelPipelineConfig = NettyApplicationEngine$Configuration$channelPipelineConfig$1.INSTANCE;
        private Function1<? super ServerBootstrap, Unit> configureBootstrap = NettyApplicationEngine$Configuration$configureBootstrap$1.INSTANCE;
        private Function0<HttpServerCodec> httpServerCodec = new NettyApplicationEngine$Configuration$httpServerCodec$1(this);
        private int maxChunkSize = 8192;
        private int maxHeaderSize = 8192;
        private int maxInitialLineLength = 4096;
        private int requestQueueLimit = 16;
        private int requestReadTimeoutSeconds;
        private int responseWriteTimeoutSeconds = 10;
        private int runningLimit = 32;
        private boolean shareWorkGroup;
        private boolean tcpKeepAlive;

        public final int getRequestQueueLimit() {
            return this.requestQueueLimit;
        }

        public final void setRequestQueueLimit(int i) {
            this.requestQueueLimit = i;
        }

        public final int getRunningLimit() {
            return this.runningLimit;
        }

        public final void setRunningLimit(int i) {
            this.runningLimit = i;
        }

        public final boolean getShareWorkGroup() {
            return this.shareWorkGroup;
        }

        public final void setShareWorkGroup(boolean z) {
            this.shareWorkGroup = z;
        }

        public final Function1<ServerBootstrap, Unit> getConfigureBootstrap() {
            return this.configureBootstrap;
        }

        public final void setConfigureBootstrap(Function1<? super ServerBootstrap, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.configureBootstrap = function1;
        }

        public final int getResponseWriteTimeoutSeconds() {
            return this.responseWriteTimeoutSeconds;
        }

        public final void setResponseWriteTimeoutSeconds(int i) {
            this.responseWriteTimeoutSeconds = i;
        }

        public final int getRequestReadTimeoutSeconds() {
            return this.requestReadTimeoutSeconds;
        }

        public final void setRequestReadTimeoutSeconds(int i) {
            this.requestReadTimeoutSeconds = i;
        }

        public final boolean getTcpKeepAlive() {
            return this.tcpKeepAlive;
        }

        public final void setTcpKeepAlive(boolean z) {
            this.tcpKeepAlive = z;
        }

        public final int getMaxInitialLineLength() {
            return this.maxInitialLineLength;
        }

        public final void setMaxInitialLineLength(int i) {
            this.maxInitialLineLength = i;
        }

        public final int getMaxHeaderSize() {
            return this.maxHeaderSize;
        }

        public final void setMaxHeaderSize(int i) {
            this.maxHeaderSize = i;
        }

        public final int getMaxChunkSize() {
            return this.maxChunkSize;
        }

        public final void setMaxChunkSize(int i) {
            this.maxChunkSize = i;
        }

        public final Function0<HttpServerCodec> getHttpServerCodec() {
            return this.httpServerCodec;
        }

        public final void setHttpServerCodec(Function0<HttpServerCodec> function0) {
            Intrinsics.checkNotNullParameter(function0, "<set-?>");
            this.httpServerCodec = function0;
        }

        public final Function1<ChannelPipeline, Unit> getChannelPipelineConfig() {
            return this.channelPipelineConfig;
        }

        public final void setChannelPipelineConfig(Function1<? super ChannelPipeline, Unit> function1) {
            Intrinsics.checkNotNullParameter(function1, "<set-?>");
            this.channelPipelineConfig = function1;
        }

        /* access modifiers changed from: private */
        public final HttpServerCodec defaultHttpServerCodec() {
            return new HttpServerCodec(this.maxInitialLineLength, this.maxHeaderSize, this.maxChunkSize);
        }
    }

    private final EventLoopGroup getConnectionEventGroup() {
        return (EventLoopGroup) this.connectionEventGroup$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final EventLoopGroup getWorkerEventGroup() {
        return (EventLoopGroup) this.workerEventGroup$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final ServerBootstrap getCustomBootstrap() {
        return (ServerBootstrap) this.customBootstrap$delegate.getValue();
    }

    private final EventLoopGroup getCallEventGroup() {
        return (EventLoopGroup) this.callEventGroup$delegate.getValue();
    }

    private final CoroutineDispatcher getNettyDispatcher() {
        return (CoroutineDispatcher) this.nettyDispatcher$delegate.getValue();
    }

    private final ExecutorCoroutineDispatcher getWorkerDispatcher() {
        return (ExecutorCoroutineDispatcher) this.workerDispatcher$delegate.getValue();
    }

    public final List<ServerBootstrap> getBootstraps$ktor_server_netty() {
        return (List) this.bootstraps$delegate.getValue();
    }

    /* access modifiers changed from: private */
    public final ServerBootstrap createBootstrap(EngineConnectorConfig engineConnectorConfig) {
        ServerBootstrap clone = getCustomBootstrap().clone();
        if (clone.config().group() == null && clone.config().childGroup() == null) {
            clone.group(getConnectionEventGroup(), getWorkerEventGroup());
        }
        if (clone.config().channelFactory() == null) {
            clone.channel(JvmClassMappingKt.getJavaClass(NettyApplicationEngineKt.getChannelClass()));
        }
        clone.childHandler(new NettyChannelInitializer(getPipeline(), getEnvironment(), getCallEventGroup(), getWorkerDispatcher(), this.userContext, engineConnectorConfig, this.configuration.getRequestQueueLimit(), this.configuration.getRunningLimit(), this.configuration.getResponseWriteTimeoutSeconds(), this.configuration.getRequestReadTimeoutSeconds(), this.configuration.getHttpServerCodec(), this.configuration.getChannelPipelineConfig()));
        if (this.configuration.getTcpKeepAlive()) {
            clone.childOption(ChannelOption.SO_KEEPALIVE, true);
        }
        Intrinsics.checkNotNullExpressionValue(clone, "customBootstrap.clone().…)\n            }\n        }");
        return clone;
    }

    @Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u000e\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0004\u001a\u00020\u0001H@"}, d2 = {"<anonymous>", "", "Lio/ktor/util/pipeline/PipelineContext;", "Lio/ktor/server/application/ApplicationCall;", "it"}, k = 3, mv = {1, 8, 0}, xi = 48)
    @DebugMetadata(c = "io.ktor.server.netty.NettyApplicationEngine$2", f = "NettyApplicationEngine.kt", i = {}, l = {207}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: io.ktor.server.netty.NettyApplicationEngine$2  reason: invalid class name */
    /* compiled from: NettyApplicationEngine.kt */
    static final class AnonymousClass2 extends SuspendLambda implements Function3<PipelineContext<Unit, ApplicationCall>, Unit, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Object invoke(PipelineContext<Unit, ApplicationCall> pipelineContext, Unit unit, Continuation<? super Unit> continuation) {
            AnonymousClass2 r2 = new AnonymousClass2(continuation);
            r2.L$0 = pipelineContext;
            return r2.invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
            int i = this.label;
            if (i == 0) {
                ResultKt.throwOnFailure(obj);
                ApplicationCall applicationCall = (ApplicationCall) ((PipelineContext) this.L$0).getContext();
                NettyApplicationCall nettyApplicationCall = applicationCall instanceof NettyApplicationCall ? (NettyApplicationCall) applicationCall : null;
                if (nettyApplicationCall != null) {
                    this.label = 1;
                    if (nettyApplicationCall.finish$ktor_server_netty(this) == coroutine_suspended) {
                        return coroutine_suspended;
                    }
                }
            } else if (i == 1) {
                ResultKt.throwOnFailure(obj);
            } else {
                throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
            }
            return Unit.INSTANCE;
        }
    }

    public NettyApplicationEngine start(boolean z) {
        ApplicationEngine applicationEngine = this;
        ShutdownHookJvmKt.addShutdownHook(applicationEngine, new NettyApplicationEngine$start$1(this));
        getEnvironment().start();
        try {
            Iterable<Pair> zip = CollectionsKt.zip(getBootstraps$ktor_server_netty(), getEnvironment().getConnectors());
            Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(zip, 10));
            for (Pair pair : zip) {
                arrayList.add(((ServerBootstrap) pair.getFirst()).bind(((EngineConnectorConfig) pair.getSecond()).getHost(), ((EngineConnectorConfig) pair.getSecond()).getPort()));
            }
            Iterable<ChannelFuture> iterable = (List) arrayList;
            Collection arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
            for (ChannelFuture sync : iterable) {
                arrayList2.add(sync.sync().channel());
            }
            List<? extends Channel> list = (List) arrayList2;
            this.channels = list;
            Intrinsics.checkNotNull(list);
            Iterable<Pair> zip2 = CollectionsKt.zip(list, getEnvironment().getConnectors());
            Collection arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(zip2, 10));
            for (Pair pair2 : zip2) {
                SocketAddress localAddress = ((Channel) pair2.getFirst()).localAddress();
                Intrinsics.checkNotNullExpressionValue(localAddress, "it.first.localAddress()");
                arrayList3.add(EngineConnectorConfigJvmKt.withPort((EngineConnectorConfig) pair2.getSecond(), NetworkAddressJvmKt.getPort(localAddress)));
            }
            getResolvedConnectors().complete((List) arrayList3);
            EventsKt.raiseCatching(getEnvironment().getMonitor(), DefaultApplicationEventsKt.getServerReady(), getEnvironment(), getEnvironment().getLog());
            this.cancellationDeferred = EngineContextCancellationHelperKt.stopServerOnCancellation(applicationEngine, this.configuration.getShutdownGracePeriod(), this.configuration.getShutdownTimeout());
            if (z) {
                List<? extends Channel> list2 = this.channels;
                if (list2 != null) {
                    Iterable<Channel> iterable2 = list2;
                    Collection arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable2, 10));
                    for (Channel closeFuture : iterable2) {
                        arrayList4.add(closeFuture.closeFuture());
                    }
                    for (ChannelFuture sync2 : (List) arrayList4) {
                        sync2.sync();
                    }
                }
                stop(this.configuration.getShutdownGracePeriod(), this.configuration.getShutdownTimeout());
            }
            return this;
        } catch (BindException e) {
            terminate();
            throw e;
        }
    }

    private final void terminate() {
        getConnectionEventGroup().shutdownGracefully().sync();
        getWorkerEventGroup().shutdownGracefully().sync();
    }

    /*  JADX ERROR: StackOverflow in pass: MarkFinallyVisitor
        jadx.core.utils.exceptions.JadxOverflowException: 
        	at jadx.core.utils.ErrorsCounter.addError(ErrorsCounter.java:47)
        	at jadx.core.utils.ErrorsCounter.methodError(ErrorsCounter.java:81)
        */
    public void stop(long r9, long r11) {
        /*
            r8 = this;
            kotlinx.coroutines.CompletableJob r0 = r8.cancellationDeferred
            if (r0 == 0) goto L_0x0007
            r0.complete()
        L_0x0007:
            io.ktor.server.engine.ApplicationEngineEnvironment r0 = r8.getEnvironment()
            io.ktor.events.Events r0 = r0.getMonitor()
            io.ktor.events.EventDefinition r1 = io.ktor.server.application.DefaultApplicationEventsKt.getApplicationStopPreparing()
            io.ktor.server.engine.ApplicationEngineEnvironment r2 = r8.getEnvironment()
            r0.raise(r1, r2)
            java.util.List<? extends io.netty.channel.Channel> r0 = r8.channels
            r1 = 0
            if (r0 == 0) goto L_0x004d
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Collection r2 = (java.util.Collection) r2
            java.util.Iterator r0 = r0.iterator()
        L_0x002c:
            boolean r3 = r0.hasNext()
            if (r3 == 0) goto L_0x004a
            java.lang.Object r3 = r0.next()
            io.netty.channel.Channel r3 = (io.netty.channel.Channel) r3
            boolean r4 = r3.isOpen()
            if (r4 == 0) goto L_0x0043
            io.netty.channel.ChannelFuture r3 = r3.close()
            goto L_0x0044
        L_0x0043:
            r3 = r1
        L_0x0044:
            if (r3 == 0) goto L_0x002c
            r2.add(r3)
            goto L_0x002c
        L_0x004a:
            r1 = r2
            java.util.List r1 = (java.util.List) r1
        L_0x004d:
            if (r1 != 0) goto L_0x0053
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0053:
            io.netty.channel.EventLoopGroup r2 = r8.getConnectionEventGroup()     // Catch:{ all -> 0x00aa }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00aa }
            r3 = r9
            r5 = r11
            io.netty.util.concurrent.Future r0 = r2.shutdownGracefully(r3, r5, r7)     // Catch:{ all -> 0x00aa }
            r0.await()     // Catch:{ all -> 0x00aa }
            io.netty.channel.EventLoopGroup r2 = r8.getWorkerEventGroup()     // Catch:{ all -> 0x00aa }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00aa }
            r3 = r9
            r5 = r11
            io.netty.util.concurrent.Future r0 = r2.shutdownGracefully(r3, r5, r7)     // Catch:{ all -> 0x00aa }
            io.ktor.server.netty.NettyApplicationEngine$Configuration r2 = r8.configuration     // Catch:{ all -> 0x00aa }
            boolean r2 = r2.getShareWorkGroup()     // Catch:{ all -> 0x00aa }
            if (r2 == 0) goto L_0x007a
            r0.await()     // Catch:{ all -> 0x00aa }
            goto L_0x008c
        L_0x007a:
            io.netty.channel.EventLoopGroup r2 = r8.getCallEventGroup()     // Catch:{ all -> 0x00aa }
            java.util.concurrent.TimeUnit r7 = java.util.concurrent.TimeUnit.MILLISECONDS     // Catch:{ all -> 0x00aa }
            r3 = r9
            r5 = r11
            io.netty.util.concurrent.Future r9 = r2.shutdownGracefully(r3, r5, r7)     // Catch:{ all -> 0x00aa }
            r0.await()     // Catch:{ all -> 0x00aa }
            r9.await()     // Catch:{ all -> 0x00aa }
        L_0x008c:
            io.ktor.server.engine.ApplicationEngineEnvironment r9 = r8.getEnvironment()     // Catch:{ all -> 0x00aa }
            r9.stop()     // Catch:{ all -> 0x00aa }
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r9 = r1.iterator()
        L_0x0099:
            boolean r10 = r9.hasNext()
            if (r10 == 0) goto L_0x00a9
            java.lang.Object r10 = r9.next()
            io.netty.channel.ChannelFuture r10 = (io.netty.channel.ChannelFuture) r10
            r10.sync()
            goto L_0x0099
        L_0x00a9:
            return
        L_0x00aa:
            r9 = move-exception
            java.lang.Iterable r1 = (java.lang.Iterable) r1
            java.util.Iterator r10 = r1.iterator()
        L_0x00b1:
            boolean r11 = r10.hasNext()
            if (r11 == 0) goto L_0x00c1
            java.lang.Object r11 = r10.next()
            io.netty.channel.ChannelFuture r11 = (io.netty.channel.ChannelFuture) r11
            r11.sync()
            goto L_0x00b1
        L_0x00c1:
            goto L_0x00c3
        L_0x00c2:
            throw r9
        L_0x00c3:
            goto L_0x00c2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.NettyApplicationEngine.stop(long, long):void");
    }

    public String toString() {
        return "Netty(" + getEnvironment() + ')';
    }
}
