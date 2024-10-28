package io.ktor.server.netty;

import androidx.core.app.NotificationCompat;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.content.OutgoingContent;
import io.ktor.server.engine.BaseApplicationResponse;
import io.ktor.utils.io.ByteChannel;
import io.ktor.utils.io.ByteChannelCtorKt;
import io.ktor.utils.io.ByteChannelKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.utils.io.ClosedWriteChannelException;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.LastHttpContent;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\b\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b&\u0018\u0000 ?2\u00020\u0001:\u0001?B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0002\u0010\tJ\u0006\u0010%\u001a\u00020&J\r\u0010'\u001a\u00020&H\u0000¢\u0006\u0002\b(J\r\u0010)\u001a\u00020&H\u0000¢\u0006\u0002\b*J\u000f\u0010+\u001a\u0004\u0018\u00010\u0015H\u0010¢\u0006\u0002\b,J\u0019\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020/H@ø\u0001\u0000¢\u0006\u0002\u00100J\u0019\u00101\u001a\u00020&2\u0006\u00102\u001a\u000203H@ø\u0001\u0000¢\u0006\u0002\u00104J\u0019\u00105\u001a\u00020&2\u0006\u00102\u001a\u000206H@ø\u0001\u0000¢\u0006\u0002\u00107J\u0011\u0010\u000e\u001a\u000208H@ø\u0001\u0000¢\u0006\u0002\u00109J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u001b2\u0006\u0010;\u001a\u00020\u001bH$J\u0018\u0010\u0014\u001a\u00020\u00152\u0006\u0010:\u001a\u00020\u001b2\u0006\u0010<\u001a\u00020/H\u0014J\u001f\u0010=\u001a\u00020&2\b\b\u0002\u0010:\u001a\u00020\u001b2\u0006\u00102\u001a\u00020\u000fH\u0000¢\u0006\u0002\b>R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\u00020\u000fX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0010\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0015X.¢\u0006\u000e\n\u0000\u001a\u0004\b\u0016\u0010\u0017\"\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u001bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u0014\u0010 \u001a\u00020!X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010#R\u0014\u0010\b\u001a\u00020\u0007X\u0004¢\u0006\b\n\u0000\u001a\u0004\b$\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006@"}, d2 = {"Lio/ktor/server/netty/NettyApplicationResponse;", "Lio/ktor/server/engine/BaseApplicationResponse;", "call", "Lio/ktor/server/netty/NettyApplicationCall;", "context", "Lio/netty/channel/ChannelHandlerContext;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "(Lio/ktor/server/netty/NettyApplicationCall;Lio/netty/channel/ChannelHandlerContext;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;)V", "getContext", "()Lio/netty/channel/ChannelHandlerContext;", "getEngineContext", "()Lkotlin/coroutines/CoroutineContext;", "responseChannel", "Lio/ktor/utils/io/ByteReadChannel;", "getResponseChannel$ktor_server_netty", "()Lio/ktor/utils/io/ByteReadChannel;", "setResponseChannel$ktor_server_netty", "(Lio/ktor/utils/io/ByteReadChannel;)V", "responseMessage", "", "getResponseMessage", "()Ljava/lang/Object;", "setResponseMessage", "(Ljava/lang/Object;)V", "responseMessageSent", "", "getResponseMessageSent", "()Z", "setResponseMessageSent", "(Z)V", "responseReady", "Lio/netty/channel/ChannelPromise;", "getResponseReady$ktor_server_netty", "()Lio/netty/channel/ChannelPromise;", "getUserContext", "cancel", "", "close", "close$ktor_server_netty", "ensureResponseSent", "ensureResponseSent$ktor_server_netty", "prepareTrailerMessage", "prepareTrailerMessage$ktor_server_netty", "respondFromBytes", "bytes", "", "([BLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondNoContent", "content", "Lio/ktor/http/content/OutgoingContent$NoContent;", "(Lio/ktor/http/content/OutgoingContent$NoContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "respondOutgoingContent", "Lio/ktor/http/content/OutgoingContent;", "(Lio/ktor/http/content/OutgoingContent;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Lio/ktor/utils/io/ByteWriteChannel;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "chunked", "last", "data", "sendResponse", "sendResponse$ktor_server_netty", "Companion", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyApplicationResponse.kt */
public abstract class NettyApplicationResponse extends BaseApplicationResponse {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final byte[] EmptyByteArray = new byte[0];
    /* access modifiers changed from: private */
    public static final HttpResponseStatus[] responseStatusCache;
    private final ChannelHandlerContext context;
    private final CoroutineContext engineContext;
    private ByteReadChannel responseChannel = ByteReadChannel.Companion.getEmpty();
    public Object responseMessage;
    private volatile boolean responseMessageSent;
    private final ChannelPromise responseReady;
    private final CoroutineContext userContext;

    public Object prepareTrailerMessage$ktor_server_netty() {
        return null;
    }

    /* access modifiers changed from: protected */
    public Object respondFromBytes(byte[] bArr, Continuation<? super Unit> continuation) {
        return respondFromBytes$suspendImpl(this, bArr, continuation);
    }

    /* access modifiers changed from: protected */
    public Object respondNoContent(OutgoingContent.NoContent noContent, Continuation<? super Unit> continuation) {
        return respondNoContent$suspendImpl(this, noContent, continuation);
    }

    /* access modifiers changed from: protected */
    public Object respondOutgoingContent(OutgoingContent outgoingContent, Continuation<? super Unit> continuation) {
        return respondOutgoingContent$suspendImpl(this, outgoingContent, continuation);
    }

    /* access modifiers changed from: protected */
    public Object responseChannel(Continuation<? super ByteWriteChannel> continuation) {
        return responseChannel$suspendImpl(this, continuation);
    }

    /* access modifiers changed from: protected */
    public abstract Object responseMessage(boolean z, boolean z2);

    /* access modifiers changed from: protected */
    public final ChannelHandlerContext getContext() {
        return this.context;
    }

    /* access modifiers changed from: protected */
    public final CoroutineContext getEngineContext() {
        return this.engineContext;
    }

    /* access modifiers changed from: protected */
    public final CoroutineContext getUserContext() {
        return this.userContext;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public NettyApplicationResponse(NettyApplicationCall nettyApplicationCall, ChannelHandlerContext channelHandlerContext, CoroutineContext coroutineContext, CoroutineContext coroutineContext2) {
        super(nettyApplicationCall);
        Intrinsics.checkNotNullParameter(nettyApplicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        Intrinsics.checkNotNullParameter(coroutineContext, "engineContext");
        Intrinsics.checkNotNullParameter(coroutineContext2, "userContext");
        this.context = channelHandlerContext;
        this.engineContext = coroutineContext;
        this.userContext = coroutineContext2;
        ChannelPromise newPromise = channelHandlerContext.newPromise();
        Intrinsics.checkNotNullExpressionValue(newPromise, "context.newPromise()");
        this.responseReady = newPromise;
    }

    public final ChannelPromise getResponseReady$ktor_server_netty() {
        return this.responseReady;
    }

    public final Object getResponseMessage() {
        Object obj = this.responseMessage;
        if (obj != null) {
            return obj;
        }
        Intrinsics.throwUninitializedPropertyAccessException("responseMessage");
        return Unit.INSTANCE;
    }

    public final void setResponseMessage(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "<set-?>");
        this.responseMessage = obj;
    }

    /* access modifiers changed from: protected */
    public final boolean getResponseMessageSent() {
        return this.responseMessageSent;
    }

    /* access modifiers changed from: protected */
    public final void setResponseMessageSent(boolean z) {
        this.responseMessageSent = z;
    }

    public final ByteReadChannel getResponseChannel$ktor_server_netty() {
        return this.responseChannel;
    }

    public final void setResponseChannel$ktor_server_netty(ByteReadChannel byteReadChannel) {
        Intrinsics.checkNotNullParameter(byteReadChannel, "<set-?>");
        this.responseChannel = byteReadChannel;
    }

    /* JADX WARNING: type inference failed for: r5v2, types: [io.ktor.utils.io.ByteReadChannel] */
    /* JADX WARNING: type inference failed for: r5v5, types: [io.ktor.utils.io.ByteReadChannel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x004b  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* JADX WARNING: Unknown variable types count: 2 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ java.lang.Object respondOutgoingContent$suspendImpl(io.ktor.server.netty.NettyApplicationResponse r5, io.ktor.http.content.OutgoingContent r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.netty.NettyApplicationResponse$respondOutgoingContent$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.netty.NettyApplicationResponse$respondOutgoingContent$1 r0 = (io.ktor.server.netty.NettyApplicationResponse$respondOutgoingContent$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.netty.NettyApplicationResponse$respondOutgoingContent$1 r0 = new io.ktor.server.netty.NettyApplicationResponse$respondOutgoingContent$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r5 = r0.L$0
            io.ktor.server.netty.NettyApplicationResponse r5 = (io.ktor.server.netty.NettyApplicationResponse) r5
            kotlin.ResultKt.throwOnFailure(r7)     // Catch:{ all -> 0x005a }
            goto L_0x0045
        L_0x002f:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r7)
            r0.L$0 = r5     // Catch:{ all -> 0x005a }
            r0.label = r3     // Catch:{ all -> 0x005a }
            java.lang.Object r6 = super.respondOutgoingContent(r6, r0)     // Catch:{ all -> 0x005a }
            if (r6 != r1) goto L_0x0045
            return r1
        L_0x0045:
            io.ktor.utils.io.ByteReadChannel r5 = r5.responseChannel
            boolean r6 = r5 instanceof io.ktor.utils.io.ByteWriteChannel
            if (r6 == 0) goto L_0x004e
            r4 = r5
            io.ktor.utils.io.ByteWriteChannel r4 = (io.ktor.utils.io.ByteWriteChannel) r4
        L_0x004e:
            if (r4 == 0) goto L_0x0057
            boolean r5 = io.ktor.utils.io.ByteWriteChannelKt.close(r4)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
        L_0x0057:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x005a:
            r6 = move-exception
            io.ktor.utils.io.ByteReadChannel r7 = r5.responseChannel     // Catch:{ all -> 0x006f }
            boolean r0 = r7 instanceof io.ktor.utils.io.ByteWriteChannel     // Catch:{ all -> 0x006f }
            if (r0 == 0) goto L_0x0064
            io.ktor.utils.io.ByteWriteChannel r7 = (io.ktor.utils.io.ByteWriteChannel) r7     // Catch:{ all -> 0x006f }
            goto L_0x0065
        L_0x0064:
            r7 = r4
        L_0x0065:
            if (r7 == 0) goto L_0x006e
            boolean r7 = r7.close(r6)     // Catch:{ all -> 0x006f }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r7)     // Catch:{ all -> 0x006f }
        L_0x006e:
            throw r6     // Catch:{ all -> 0x006f }
        L_0x006f:
            r6 = move-exception
            io.ktor.utils.io.ByteReadChannel r5 = r5.responseChannel
            boolean r7 = r5 instanceof io.ktor.utils.io.ByteWriteChannel
            if (r7 == 0) goto L_0x0079
            r4 = r5
            io.ktor.utils.io.ByteWriteChannel r4 = (io.ktor.utils.io.ByteWriteChannel) r4
        L_0x0079:
            if (r4 == 0) goto L_0x0082
            boolean r5 = io.ktor.utils.io.ByteWriteChannelKt.close(r4)
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r5)
        L_0x0082:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.NettyApplicationResponse.respondOutgoingContent$suspendImpl(io.ktor.server.netty.NettyApplicationResponse, io.ktor.http.content.OutgoingContent, kotlin.coroutines.Continuation):java.lang.Object");
    }

    static /* synthetic */ Object respondFromBytes$suspendImpl(NettyApplicationResponse nettyApplicationResponse, byte[] bArr, Continuation<? super Unit> continuation) {
        ByteReadChannel byteReadChannel;
        boolean areEqual = Intrinsics.areEqual((Object) nettyApplicationResponse.getHeaders().get(HttpHeaders.INSTANCE.getTransferEncoding()), (Object) HttpHeaders.Values.CHUNKED);
        if (nettyApplicationResponse.responseMessageSent) {
            return Unit.INSTANCE;
        }
        Object responseMessage2 = nettyApplicationResponse.responseMessage(areEqual, bArr);
        if (responseMessage2 instanceof LastHttpContent) {
            byteReadChannel = ByteReadChannel.Companion.getEmpty();
        } else {
            byteReadChannel = ByteChannelCtorKt.ByteReadChannel(bArr);
        }
        nettyApplicationResponse.responseChannel = byteReadChannel;
        nettyApplicationResponse.setResponseMessage(responseMessage2);
        nettyApplicationResponse.responseReady.setSuccess();
        nettyApplicationResponse.responseMessageSent = true;
        return Unit.INSTANCE;
    }

    static /* synthetic */ Object responseChannel$suspendImpl(NettyApplicationResponse nettyApplicationResponse, Continuation<? super ByteWriteChannel> continuation) {
        ByteChannel ByteChannel$default = ByteChannelKt.ByteChannel$default(false, 1, (Object) null);
        nettyApplicationResponse.sendResponse$ktor_server_netty(Intrinsics.areEqual((Object) nettyApplicationResponse.getHeaders().get(io.ktor.http.HttpHeaders.INSTANCE.getTransferEncoding()), (Object) HttpHeaders.Values.CHUNKED), ByteChannel$default);
        return ByteChannel$default;
    }

    static /* synthetic */ Object respondNoContent$suspendImpl(NettyApplicationResponse nettyApplicationResponse, OutgoingContent.NoContent noContent, Continuation<? super Unit> continuation) {
        Object respondFromBytes = nettyApplicationResponse.respondFromBytes(EmptyByteArray, continuation);
        return respondFromBytes == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? respondFromBytes : Unit.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public Object responseMessage(boolean z, byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "data");
        return responseMessage(z, true);
    }

    public static /* synthetic */ void sendResponse$ktor_server_netty$default(NettyApplicationResponse nettyApplicationResponse, boolean z, ByteReadChannel byteReadChannel, int i, Object obj) {
        if (obj == null) {
            if ((i & 1) != 0) {
                z = true;
            }
            nettyApplicationResponse.sendResponse$ktor_server_netty(z, byteReadChannel);
            return;
        }
        throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: sendResponse");
    }

    public final void sendResponse$ktor_server_netty(boolean z, ByteReadChannel byteReadChannel) {
        Object obj;
        Intrinsics.checkNotNullParameter(byteReadChannel, "content");
        if (!this.responseMessageSent) {
            this.responseChannel = byteReadChannel;
            if (byteReadChannel.isClosedForRead()) {
                obj = responseMessage(false, EmptyByteArray);
            } else {
                obj = responseMessage(z, false);
            }
            setResponseMessage(obj);
            this.responseReady.setSuccess();
            this.responseMessageSent = true;
        }
    }

    public final void ensureResponseSent$ktor_server_netty() {
        sendResponse$ktor_server_netty$default(this, false, ByteReadChannel.Companion.getEmpty(), 1, (Object) null);
    }

    public final void close$ktor_server_netty() {
        ByteReadChannel byteReadChannel = this.responseChannel;
        if (byteReadChannel instanceof ByteWriteChannel) {
            ((ByteWriteChannel) byteReadChannel).close(new ClosedWriteChannelException("Application response has been closed"));
            this.responseChannel = ByteReadChannel.Companion.getEmpty();
        }
        ensureResponseSent$ktor_server_netty();
    }

    public final void cancel() {
        if (!this.responseMessageSent) {
            this.responseChannel = ByteReadChannel.Companion.getEmpty();
            this.responseReady.setFailure(new CancellationException("Response was cancelled"));
            this.responseMessageSent = true;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\t¨\u0006\u000b"}, d2 = {"Lio/ktor/server/netty/NettyApplicationResponse$Companion;", "", "()V", "EmptyByteArray", "", "responseStatusCache", "", "Lio/netty/handler/codec/http/HttpResponseStatus;", "getResponseStatusCache", "()[Lio/netty/handler/codec/http/HttpResponseStatus;", "[Lio/netty/handler/codec/http/HttpResponseStatus;", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: NettyApplicationResponse.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final HttpResponseStatus[] getResponseStatusCache() {
            return NettyApplicationResponse.responseStatusCache;
        }
    }

    static {
        HttpResponseStatus httpResponseStatus;
        Iterable allStatusCodes = HttpStatusCode.Companion.getAllStatusCodes();
        Map linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(allStatusCodes, 10)), 16));
        for (Object next : allStatusCodes) {
            linkedHashMap.put(Integer.valueOf(((HttpStatusCode) next).getValue()), next);
        }
        HttpResponseStatus[] httpResponseStatusArr = new HttpResponseStatus[1000];
        for (int i = 0; i < 1000; i++) {
            if (linkedHashMap.keySet().contains(Integer.valueOf(i))) {
                Object obj = linkedHashMap.get(Integer.valueOf(i));
                Intrinsics.checkNotNull(obj);
                httpResponseStatus = new HttpResponseStatus(i, ((HttpStatusCode) obj).getDescription());
            } else {
                httpResponseStatus = null;
            }
            httpResponseStatusArr[i] = httpResponseStatus;
        }
        responseStatusCache = httpResponseStatusArr;
    }
}
