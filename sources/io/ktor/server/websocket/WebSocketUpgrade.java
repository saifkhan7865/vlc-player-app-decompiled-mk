package io.ktor.server.websocket;

import androidx.core.app.NotificationCompat;
import io.ktor.http.Headers;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.content.OutgoingContent;
import io.ktor.http.websocket.UtilsKt;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPluginKt;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import io.ktor.utils.io.ByteReadChannel;
import io.ktor.utils.io.ByteWriteChannel;
import io.ktor.websocket.RawWebSocketJvmKt;
import io.ktor.websocket.WebSocketExtension;
import io.ktor.websocket.WebSocketExtensionHeader;
import io.ktor.websocket.WebSocketExtensionHeaderKt;
import io.ktor.websocket.WebSocketSession;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.EmptyCoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 -2\u00020\u0001:\u0001-BG\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012'\u0010\u0006\u001a#\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\rBO\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012'\u0010\u0006\u001a#\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\u0002\u0010\u0010J1\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020&H@ø\u0001\u0000¢\u0006\u0002\u0010(J\u0016\u0010)\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030+0**\u00020,H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R7\u0010\u0006\u001a#\b\u0001\u0012\u0004\u0012\u00020\b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t\u0012\u0006\u0012\u0004\u0018\u00010\u000b0\u0007¢\u0006\u0002\b\fø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0015\u001a\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u0017X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u000e\u0010\u000e\u001a\u00020\u000fX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u001cX\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u001e\u0002\u0004\n\u0002\b\u0019¨\u0006."}, d2 = {"Lio/ktor/server/websocket/WebSocketUpgrade;", "Lio/ktor/http/content/OutgoingContent$ProtocolUpgrade;", "call", "Lio/ktor/server/application/ApplicationCall;", "protocol", "", "handle", "Lkotlin/Function2;", "Lio/ktor/websocket/WebSocketSession;", "Lkotlin/coroutines/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;Lkotlin/jvm/functions/Function2;)V", "installExtensions", "", "(Lio/ktor/server/application/ApplicationCall;Ljava/lang/String;ZLkotlin/jvm/functions/Function2;)V", "getCall", "()Lio/ktor/server/application/ApplicationCall;", "getHandle", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "key", "plugin", "Lio/ktor/server/websocket/WebSockets;", "getProtocol", "()Ljava/lang/String;", "upgrade", "Lkotlinx/coroutines/Job;", "input", "Lio/ktor/utils/io/ByteReadChannel;", "output", "Lio/ktor/utils/io/ByteWriteChannel;", "engineContext", "Lkotlin/coroutines/CoroutineContext;", "userContext", "(Lio/ktor/utils/io/ByteReadChannel;Lio/ktor/utils/io/ByteWriteChannel;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/CoroutineContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeExtensions", "", "Lio/ktor/websocket/WebSocketExtension;", "Lio/ktor/http/HeadersBuilder;", "Companion", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: WebSocketUpgrade.kt */
public final class WebSocketUpgrade extends OutgoingContent.ProtocolUpgrade {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final CoroutineName WebSocketHandlerCoroutineName = new CoroutineName("raw-ws-handler");
    private final ApplicationCall call;
    private final Function2<WebSocketSession, Continuation<? super Unit>, Object> handle;
    private final Headers headers;
    private final boolean installExtensions;
    private final String key;
    private final WebSockets plugin;
    private final String protocol;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebSocketUpgrade(ApplicationCall applicationCall, String str, boolean z, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicationCall, (i & 2) != 0 ? null : str, (i & 4) != 0 ? false : z, function2);
    }

    public final ApplicationCall getCall() {
        return this.call;
    }

    public final String getProtocol() {
        return this.protocol;
    }

    public final Function2<WebSocketSession, Continuation<? super Unit>, Object> getHandle() {
        return this.handle;
    }

    public WebSocketUpgrade(ApplicationCall applicationCall, String str, boolean z, Function2<? super WebSocketSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(function2, "handle");
        this.call = applicationCall;
        this.protocol = str;
        this.installExtensions = z;
        this.handle = function2;
        String header = ApplicationRequestPropertiesKt.header(applicationCall.getRequest(), HttpHeaders.INSTANCE.getSecWebSocketKey());
        this.key = header;
        this.plugin = (WebSockets) ApplicationPluginKt.plugin(applicationCall.getApplication(), WebSockets.Plugin);
        Headers.Companion companion = Headers.Companion;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        headersBuilder.append(HttpHeaders.INSTANCE.getUpgrade(), "websocket");
        headersBuilder.append(HttpHeaders.INSTANCE.getConnection(), "Upgrade");
        if (header != null) {
            headersBuilder.append(HttpHeaders.INSTANCE.getSecWebSocketAccept(), UtilsKt.websocketServerAccept(header));
        }
        if (str != null) {
            headersBuilder.append(HttpHeaders.INSTANCE.getSecWebSocketProtocol(), str);
        }
        applicationCall.getAttributes().put(WebSockets.Plugin.getEXTENSIONS_KEY(), writeExtensions(headersBuilder));
        this.headers = headersBuilder.build();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ WebSocketUpgrade(ApplicationCall applicationCall, String str, Function2 function2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(applicationCall, (i & 2) != 0 ? null : str, function2);
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public WebSocketUpgrade(ApplicationCall applicationCall, String str, Function2<? super WebSocketSession, ? super Continuation<? super Unit>, ? extends Object> function2) {
        this(applicationCall, str, false, function2);
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(function2, "handle");
    }

    public Headers getHeaders() {
        return this.headers;
    }

    public Object upgrade(ByteReadChannel byteReadChannel, ByteWriteChannel byteWriteChannel, CoroutineContext coroutineContext, CoroutineContext coroutineContext2, Continuation<? super Job> continuation) {
        long maxFrameSize = this.plugin.getMaxFrameSize();
        boolean masking = this.plugin.getMasking();
        CoroutineContext coroutineContext3 = (Job) continuation.getContext().get(Job.Key);
        if (coroutineContext3 == null) {
            coroutineContext3 = EmptyCoroutineContext.INSTANCE;
        }
        WebSocketSession RawWebSocket = RawWebSocketJvmKt.RawWebSocket(byteReadChannel, byteWriteChannel, maxFrameSize, masking, coroutineContext.plus(coroutineContext3));
        Job unused = BuildersKt__Builders_commonKt.launch$default(RawWebSocket, WebSocketHandlerCoroutineName, (CoroutineStart) null, new WebSocketUpgrade$upgrade$2(this, RawWebSocket, (Continuation<? super WebSocketUpgrade$upgrade$2>) null), 2, (Object) null);
        CoroutineContext.Element element = RawWebSocket.getCoroutineContext().get(Job.Key);
        Intrinsics.checkNotNull(element);
        return element;
    }

    private final List<WebSocketExtension<?>> writeExtensions(HeadersBuilder headersBuilder) {
        List<WebSocketExtensionHeader> list;
        if (!this.installExtensions) {
            return CollectionsKt.emptyList();
        }
        String header = ApplicationRequestPropertiesKt.header(this.call.getRequest(), HttpHeaders.INSTANCE.getSecWebSocketExtensions());
        if (header == null || (list = WebSocketExtensionHeaderKt.parseWebSocketExtensions(header)) == null) {
            list = CollectionsKt.emptyList();
        }
        List<WebSocketExtension<?>> build = this.plugin.getExtensionsConfig().build();
        List arrayList = new ArrayList();
        List<WebSocketExtension<?>> arrayList2 = new ArrayList<>();
        for (WebSocketExtension webSocketExtension : build) {
            List<WebSocketExtensionHeader> serverNegotiation = webSocketExtension.serverNegotiation(list);
            if (!serverNegotiation.isEmpty()) {
                arrayList2.add(webSocketExtension);
                arrayList.addAll(serverNegotiation);
            }
        }
        if (!arrayList.isEmpty()) {
            headersBuilder.append(HttpHeaders.INSTANCE.getSecWebSocketExtensions(), CollectionsKt.joinToString$default(arrayList, ";", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null));
        }
        return arrayList2;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0005"}, d2 = {"Lio/ktor/server/websocket/WebSocketUpgrade$Companion;", "", "()V", "WebSocketHandlerCoroutineName", "Lkotlinx/coroutines/CoroutineName;", "ktor-server-websockets"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: WebSocketUpgrade.kt */
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
