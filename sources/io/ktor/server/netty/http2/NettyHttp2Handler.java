package io.ktor.server.netty.http2;

import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.server.application.Application;
import io.ktor.server.engine.EnginePipeline;
import io.ktor.server.netty.NettyApplicationCallHandler;
import io.ktor.server.netty.NettyHttpHandlerState;
import io.ktor.server.netty.cio.NettyHttpResponsePipeline;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http2.DefaultHttp2Headers;
import io.netty.handler.codec.http2.Http2Connection;
import io.netty.handler.codec.http2.Http2DataFrame;
import io.netty.handler.codec.http2.Http2FrameCodec;
import io.netty.handler.codec.http2.Http2FrameStream;
import io.netty.handler.codec.http2.Http2FrameWriter;
import io.netty.handler.codec.http2.Http2Headers;
import io.netty.handler.codec.http2.Http2HeadersFrame;
import io.netty.handler.codec.http2.Http2MultiplexCodec;
import io.netty.handler.codec.http2.Http2ResetFrame;
import io.netty.handler.codec.http2.Http2Stream;
import io.netty.handler.codec.http2.Http2StreamChannel;
import io.netty.handler.codec.http2.Http2StreamChannelBootstrap;
import io.netty.util.AttributeKey;
import io.netty.util.concurrent.EventExecutorGroup;
import io.netty.util.concurrent.Future;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.ClosedChannelException;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CopyableThrowable;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;
import kotlinx.coroutines.channels.ChannelResult;
import kotlinx.coroutines.channels.SendChannel;

@ChannelHandler.Sharable
@Metadata(d1 = {"\u0000 \u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0001\u0018\u0000 ?2\u00020\u00012\u00020\u0002:\u0002?@B-\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\rJ\u0010\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010%\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010&\u001a\u00020'H\u0016J\u0010\u0010(\u001a\u00020\"2\u0006\u0010#\u001a\u00020$H\u0016J\u0018\u0010)\u001a\u00020\"2\u0006\u0010*\u001a\u00020$2\u0006\u0010+\u001a\u00020,H\u0016J\u0018\u0010-\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010.\u001a\u00020/H\u0002J\u001d\u00100\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u00101\u001a\u000202H\u0001¢\u0006\u0002\b3J\u0011\u00104\u001a\u00020\u0018*\u0006\u0012\u0002\b\u000305H\u0010J\u0014\u00106\u001a\u00020\"*\u0002072\u0006\u00108\u001a\u00020\fH\u0002J\u001c\u00109\u001a\u00020:*\u00020\u001e2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>H\u0002R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u001d\u0010\u0017\u001a\u0004\u0018\u00010\u00188BX\u0002¢\u0006\f\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0019\u0010\u001aR\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0018\u0010\u001d\u001a\u00020\u0018*\u00020\u001e8BX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010 ¨\u0006A"}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2Handler;", "Lio/netty/channel/ChannelInboundHandlerAdapter;", "Lkotlinx/coroutines/CoroutineScope;", "enginePipeline", "Lio/ktor/server/engine/EnginePipeline;", "application", "Lio/ktor/server/application/Application;", "callEventGroup", "Lio/netty/util/concurrent/EventExecutorGroup;", "userCoroutineContext", "Lkotlin/coroutines/CoroutineContext;", "runningLimit", "", "(Lio/ktor/server/engine/EnginePipeline;Lio/ktor/server/application/Application;Lio/netty/util/concurrent/EventExecutorGroup;Lkotlin/coroutines/CoroutineContext;I)V", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "handlerJob", "Lkotlinx/coroutines/CompletableJob;", "responseWriter", "Lio/ktor/server/netty/cio/NettyHttpResponsePipeline;", "state", "Lio/ktor/server/netty/NettyHttpHandlerState;", "streamKeyField", "Ljava/lang/reflect/Field;", "getStreamKeyField", "()Ljava/lang/reflect/Field;", "streamKeyField$delegate", "Lkotlin/Lazy;", "idField", "Lio/netty/handler/codec/http2/Http2FrameStream;", "getIdField", "(Lio/netty/handler/codec/http2/Http2FrameStream;)Ljava/lang/reflect/Field;", "channelActive", "", "context", "Lio/netty/channel/ChannelHandlerContext;", "channelRead", "message", "", "channelReadComplete", "exceptionCaught", "ctx", "cause", "", "startHttp2", "headers", "Lio/netty/handler/codec/http2/Http2Headers;", "startHttp2PushPromise", "builder", "Lio/ktor/server/response/ResponsePushBuilder;", "startHttp2PushPromise$ktor_server_netty", "findIdField", "Ljava/lang/Class;", "setId", "Lio/netty/handler/codec/http2/Http2StreamChannel;", "streamId", "setStreamAndProperty", "", "codec", "Lio/netty/handler/codec/http2/Http2FrameCodec;", "childStream", "Lio/netty/handler/codec/http2/Http2Stream;", "Companion", "Http2ClosedChannelException", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyHttp2Handler.kt */
public final class NettyHttp2Handler extends ChannelInboundHandlerAdapter implements CoroutineScope {
    /* access modifiers changed from: private */
    public static final AttributeKey<NettyHttp2ApplicationCall> ApplicationCallKey = AttributeKey.newInstance("ktor.ApplicationCall");
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final Application application;
    private final EventExecutorGroup callEventGroup;
    private final EnginePipeline enginePipeline;
    private final CompletableJob handlerJob;
    private NettyHttpResponsePipeline responseWriter;
    private final NettyHttpHandlerState state;
    private final Lazy streamKeyField$delegate = LazyKt.lazy(NettyHttp2Handler$streamKeyField$2.INSTANCE);
    private final CoroutineContext userCoroutineContext;

    public NettyHttp2Handler(EnginePipeline enginePipeline2, Application application2, EventExecutorGroup eventExecutorGroup, CoroutineContext coroutineContext, int i) {
        Intrinsics.checkNotNullParameter(enginePipeline2, "enginePipeline");
        Intrinsics.checkNotNullParameter(application2, "application");
        Intrinsics.checkNotNullParameter(eventExecutorGroup, "callEventGroup");
        Intrinsics.checkNotNullParameter(coroutineContext, "userCoroutineContext");
        this.enginePipeline = enginePipeline2;
        this.application = application2;
        this.callEventGroup = eventExecutorGroup;
        this.userCoroutineContext = coroutineContext;
        this.handlerJob = SupervisorKt.SupervisorJob((Job) coroutineContext.get(Job.Key));
        this.state = new NettyHttpHandlerState(i);
    }

    public CoroutineContext getCoroutineContext() {
        return this.handlerJob;
    }

    public void channelRead(ChannelHandlerContext channelHandlerContext, Object obj) {
        NettyHttp2ApplicationRequest request;
        NettyHttp2ApplicationRequest request2;
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(obj, "message");
        if (obj instanceof Http2HeadersFrame) {
            NettyHttpHandlerState.isChannelReadCompleted$FU$internal.compareAndSet(this.state, 1, 0);
            NettyHttpHandlerState.activeRequests$FU$internal.incrementAndGet(this.state);
            Http2Headers headers = ((Http2HeadersFrame) obj).headers();
            Intrinsics.checkNotNullExpressionValue(headers, "message.headers()");
            startHttp2(channelHandlerContext, headers);
            return;
        }
        Http2ClosedChannelException http2ClosedChannelException = null;
        if (obj instanceof Http2DataFrame) {
            NettyHttp2ApplicationCall access$getApplicationCall = Companion.getApplicationCall(channelHandlerContext);
            if (access$getApplicationCall == null || (request2 = access$getApplicationCall.getRequest()) == null) {
                ((Http2DataFrame) obj).release();
                return;
            }
            boolean isEndStream = ((Http2DataFrame) obj).isEndStream();
            ChannelResult.m2346isSuccessimpl(request2.getContentActor().m1139trySendJP2dKIU(obj));
            if (isEndStream) {
                SendChannel.DefaultImpls.close$default(request2.getContentActor(), (Throwable) null, 1, (Object) null);
                NettyHttpHandlerState.isCurrentRequestFullyRead$FU$internal.compareAndSet(this.state, 0, 1);
                return;
            }
            NettyHttpHandlerState.isCurrentRequestFullyRead$FU$internal.compareAndSet(this.state, 1, 0);
        } else if (obj instanceof Http2ResetFrame) {
            NettyHttp2ApplicationCall access$getApplicationCall2 = Companion.getApplicationCall(channelHandlerContext);
            if (access$getApplicationCall2 != null && (request = access$getApplicationCall2.getRequest()) != null) {
                Http2ResetFrame http2ResetFrame = (Http2ResetFrame) obj;
                if (http2ResetFrame.errorCode() != 0) {
                    http2ClosedChannelException = new Http2ClosedChannelException(http2ResetFrame.errorCode());
                }
                request.getContentActor().close(http2ClosedChannelException);
            }
        } else {
            channelHandlerContext.fireChannelRead(obj);
        }
    }

    public void channelActive(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        this.responseWriter = new NettyHttpResponsePipeline(channelHandlerContext, this.state, getCoroutineContext());
        ChannelPipeline pipeline = channelHandlerContext.pipeline();
        if (pipeline != null) {
            pipeline.addLast(this.callEventGroup, new NettyApplicationCallHandler(this.userCoroutineContext, this.enginePipeline));
        }
        channelHandlerContext.fireChannelActive();
    }

    public void channelReadComplete(ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        NettyHttpHandlerState.isChannelReadCompleted$FU$internal.compareAndSet(this.state, 0, 1);
        NettyHttpResponsePipeline nettyHttpResponsePipeline = this.responseWriter;
        if (nettyHttpResponsePipeline == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseWriter");
            nettyHttpResponsePipeline = null;
        }
        nettyHttpResponsePipeline.flushIfNeeded$ktor_server_netty();
        channelHandlerContext.fireChannelReadComplete();
    }

    public void exceptionCaught(ChannelHandlerContext channelHandlerContext, Throwable th) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "ctx");
        Intrinsics.checkNotNullParameter(th, "cause");
        channelHandlerContext.close();
    }

    private final void startHttp2(ChannelHandlerContext channelHandlerContext, Http2Headers http2Headers) {
        NettyHttp2ApplicationCall nettyHttp2ApplicationCall = new NettyHttp2ApplicationCall(this.application, channelHandlerContext, http2Headers, this, this.handlerJob.plus(Dispatchers.getUnconfined()), this.userCoroutineContext);
        Companion.setApplicationCall(channelHandlerContext, nettyHttp2ApplicationCall);
        channelHandlerContext.fireChannelRead(nettyHttp2ApplicationCall);
        NettyHttpResponsePipeline nettyHttpResponsePipeline = this.responseWriter;
        if (nettyHttpResponsePipeline == null) {
            Intrinsics.throwUninitializedPropertyAccessException("responseWriter");
            nettyHttpResponsePipeline = null;
        }
        nettyHttpResponsePipeline.processResponse$ktor_server_netty(nettyHttp2ApplicationCall);
    }

    @UseHttp2Push
    public final void startHttp2PushPromise$ktor_server_netty(ChannelHandlerContext channelHandlerContext, ResponsePushBuilder responsePushBuilder) {
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(responsePushBuilder, "builder");
        Channel channel = channelHandlerContext.channel();
        Intrinsics.checkNotNull(channel, "null cannot be cast to non-null type io.netty.handler.codec.http2.Http2StreamChannel");
        Http2StreamChannel http2StreamChannel = (Http2StreamChannel) channel;
        int id = http2StreamChannel.stream().id();
        ChannelHandler channelHandler = http2StreamChannel.parent().pipeline().get(Http2MultiplexCodec.class);
        Intrinsics.checkNotNull(channelHandler);
        Http2MultiplexCodec http2MultiplexCodec = (Http2MultiplexCodec) channelHandler;
        Http2Connection connection = http2MultiplexCodec.connection();
        if (connection.remote().allowPushTo()) {
            ChannelHandlerContext lastContext = http2StreamChannel.parent().pipeline().lastContext();
            int incrementAndGetNextStreamId = connection.local().incrementAndGetNextStreamId();
            DefaultHttp2Headers defaultHttp2Headers = new DefaultHttp2Headers();
            Url build = responsePushBuilder.getUrl().build();
            defaultHttp2Headers.method(responsePushBuilder.getMethod().getValue());
            defaultHttp2Headers.authority(URLUtilsKt.getHostWithPort(build));
            defaultHttp2Headers.scheme(build.getProtocol().getName());
            defaultHttp2Headers.path(build.getEncodedPathAndQuery());
            Http2StreamChannel http2StreamChannel2 = (Http2StreamChannel) new Http2StreamChannelBootstrap(http2StreamChannel.parent()).handler(this).open().get();
            Intrinsics.checkNotNullExpressionValue(http2StreamChannel2, "child");
            setId(http2StreamChannel2, incrementAndGetNextStreamId);
            ChannelPromise newPromise = lastContext.newPromise();
            Http2Stream createStream = connection.local().createStream(incrementAndGetNextStreamId, false);
            Http2FrameStream stream = http2StreamChannel2.stream();
            Intrinsics.checkNotNullExpressionValue(stream, "child.stream()");
            Intrinsics.checkNotNullExpressionValue(createStream, "childStream");
            if (!setStreamAndProperty(stream, http2MultiplexCodec, createStream)) {
                createStream.close();
                http2StreamChannel2.close();
                return;
            }
            Http2FrameWriter frameWriter = http2MultiplexCodec.encoder().frameWriter();
            Http2Headers http2Headers = defaultHttp2Headers;
            frameWriter.writePushPromise(lastContext, id, incrementAndGetNextStreamId, http2Headers, 0, newPromise);
            if (newPromise.isSuccess()) {
                ChannelHandlerContext firstContext = http2StreamChannel2.pipeline().firstContext();
                Intrinsics.checkNotNullExpressionValue(firstContext, "child.pipeline().firstContext()");
                startHttp2(firstContext, http2Headers);
                return;
            }
            newPromise.addListener(new NettyHttp2Handler$$ExternalSyntheticLambda0(this, http2StreamChannel2, defaultHttp2Headers));
        }
    }

    /* access modifiers changed from: private */
    public static final void startHttp2PushPromise$lambda$4(NettyHttp2Handler nettyHttp2Handler, Http2StreamChannel http2StreamChannel, DefaultHttp2Headers defaultHttp2Headers, Future future) {
        Intrinsics.checkNotNullParameter(nettyHttp2Handler, "this$0");
        Intrinsics.checkNotNullParameter(defaultHttp2Headers, "$headers");
        future.get();
        ChannelHandlerContext firstContext = http2StreamChannel.pipeline().firstContext();
        Intrinsics.checkNotNullExpressionValue(firstContext, "child.pipeline().firstContext()");
        nettyHttp2Handler.startHttp2(firstContext, defaultHttp2Headers);
    }

    private final void setId(Http2StreamChannel http2StreamChannel, int i) {
        Http2FrameStream stream = http2StreamChannel.stream();
        Intrinsics.checkNotNull(stream);
        getIdField(stream).setInt(stream, i);
    }

    private final Field getStreamKeyField() {
        return (Field) this.streamKeyField$delegate.getValue();
    }

    private final boolean setStreamAndProperty(Http2FrameStream http2FrameStream, Http2FrameCodec http2FrameCodec, Http2Stream http2Stream) {
        Field streamKeyField = getStreamKeyField();
        Method method = null;
        Object obj = streamKeyField != null ? streamKeyField.get(http2FrameCodec) : null;
        Http2Connection.PropertyKey propertyKey = obj instanceof Http2Connection.PropertyKey ? (Http2Connection.PropertyKey) obj : null;
        if (propertyKey == null) {
            return false;
        }
        Method[] declaredMethods = http2FrameStream.getClass().getDeclaredMethods();
        Intrinsics.checkNotNullExpressionValue(declaredMethods, "javaClass.declaredMethods");
        Object[] objArr = (Object[]) declaredMethods;
        int length = objArr.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                break;
            }
            Object obj2 = objArr[i];
            if (Intrinsics.areEqual((Object) ((Method) obj2).getName(), (Object) "setStreamAndProperty")) {
                method = obj2;
                break;
            }
            i++;
        }
        Method method2 = method;
        if (method2 != null) {
            method2.setAccessible(true);
            try {
                method2.invoke(http2FrameStream, new Object[]{propertyKey, http2Stream});
                return true;
            } catch (Throwable unused) {
            }
        }
        return false;
    }

    private final Field getIdField(Http2FrameStream http2FrameStream) {
        return findIdField(http2FrameStream.getClass());
    }

    private final Field findIdField(Class<?> cls) {
        Field field;
        Class<? super Object> superclass;
        do {
            Class<? super Object> cls2 = cls;
            try {
                field = cls2.getDeclaredField("id");
            } catch (NoSuchFieldException unused) {
                field = null;
            }
            if (field != null) {
                field.setAccessible(true);
                return field;
            }
            superclass = cls2.getSuperclass();
            cls2 = superclass;
        } while (superclass != null);
        throw new NoSuchFieldException("id field not found");
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\f\u001a\u00020\u0000H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\r"}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2Handler$Http2ClosedChannelException;", "Ljava/nio/channels/ClosedChannelException;", "Lkotlinx/coroutines/CopyableThrowable;", "errorCode", "", "(J)V", "getErrorCode", "()J", "message", "", "getMessage", "()Ljava/lang/String;", "createCopy", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyHttp2Handler.kt */
    private static final class Http2ClosedChannelException extends ClosedChannelException implements CopyableThrowable<Http2ClosedChannelException> {
        private final long errorCode;

        public final long getErrorCode() {
            return this.errorCode;
        }

        public Http2ClosedChannelException(long j) {
            this.errorCode = j;
        }

        public String getMessage() {
            return "Got close frame with code " + this.errorCode;
        }

        public Http2ClosedChannelException createCopy() {
            Http2ClosedChannelException http2ClosedChannelException = new Http2ClosedChannelException(this.errorCode);
            http2ClosedChannelException.initCause(this);
            return http2ClosedChannelException;
        }
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R2\u0010\u0003\u001a&\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005 \u0006*\u0012\u0012\f\u0012\n \u0006*\u0004\u0018\u00010\u00050\u0005\u0018\u00010\u00040\u0004X\u0004¢\u0006\u0002\n\u0000R,\u0010\b\u001a\u0004\u0018\u00010\u0005*\u00020\t2\b\u0010\u0007\u001a\u0004\u0018\u00010\u00058B@BX\u000e¢\u0006\f\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lio/ktor/server/netty/http2/NettyHttp2Handler$Companion;", "", "()V", "ApplicationCallKey", "Lio/netty/util/AttributeKey;", "Lio/ktor/server/netty/http2/NettyHttp2ApplicationCall;", "kotlin.jvm.PlatformType", "newValue", "applicationCall", "Lio/netty/channel/ChannelHandlerContext;", "getApplicationCall", "(Lio/netty/channel/ChannelHandlerContext;)Lio/ktor/server/netty/http2/NettyHttp2ApplicationCall;", "setApplicationCall", "(Lio/netty/channel/ChannelHandlerContext;Lio/ktor/server/netty/http2/NettyHttp2ApplicationCall;)V", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyHttp2Handler.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        /* access modifiers changed from: private */
        public final NettyHttp2ApplicationCall getApplicationCall(ChannelHandlerContext channelHandlerContext) {
            return (NettyHttp2ApplicationCall) channelHandlerContext.channel().attr(NettyHttp2Handler.ApplicationCallKey).get();
        }

        /* access modifiers changed from: private */
        public final void setApplicationCall(ChannelHandlerContext channelHandlerContext, NettyHttp2ApplicationCall nettyHttp2ApplicationCall) {
            channelHandlerContext.channel().attr(NettyHttp2Handler.ApplicationCallKey).set(nettyHttp2ApplicationCall);
        }
    }
}
