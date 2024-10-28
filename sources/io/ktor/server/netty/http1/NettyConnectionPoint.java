package io.ktor.server.netty.http1;

import androidx.core.os.EnvironmentCompat;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.URLProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.HttpRequest;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import kotlin.Deprecated;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0018\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\u00020\b8BX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\f\u0012\u0004\b\r\u0010\u000e\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\u0011\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010R\u0014\u0010\u0013\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0010R\u0014\u0010\u0015\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR\u0014\u0010\u0017\u001a\u00020\u00188VX\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u00020\b8VX\u0004¢\u0006\f\u0012\u0004\b\u001c\u0010\u000e\u001a\u0004\b\u001d\u0010\nR\u0014\u0010\u001e\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0010R\u0014\u0010 \u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\u0010R\u0014\u0010\"\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\nR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010$\u001a\u00020\f8VX\u0002¢\u0006\f\n\u0004\b&\u0010'\u001a\u0004\b%\u0010\u0010R\u0014\u0010(\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b)\u0010\u0010R\u0014\u0010*\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\nR\u0014\u0010,\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b-\u0010\u0010R\u0014\u0010.\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0010¨\u00060"}, d2 = {"Lio/ktor/server/netty/http1/NettyConnectionPoint;", "Lio/ktor/http/RequestConnectionPoint;", "request", "Lio/netty/handler/codec/http/HttpRequest;", "context", "Lio/netty/channel/ChannelHandlerContext;", "(Lio/netty/handler/codec/http/HttpRequest;Lio/netty/channel/ChannelHandlerContext;)V", "defaultPort", "", "getDefaultPort", "()I", "host", "", "getHost$annotations", "()V", "getHost", "()Ljava/lang/String;", "localAddress", "getLocalAddress", "localHost", "getLocalHost", "localPort", "getLocalPort", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "port", "getPort$annotations", "getPort", "remoteAddress", "getRemoteAddress", "remoteHost", "getRemoteHost", "remotePort", "getRemotePort", "scheme", "getScheme", "scheme$delegate", "Lkotlin/Lazy;", "serverHost", "getServerHost", "serverPort", "getServerPort", "uri", "getUri", "version", "getVersion", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: NettyConnectionPoint.kt */
public final class NettyConnectionPoint implements RequestConnectionPoint {
    /* access modifiers changed from: private */
    public final ChannelHandlerContext context;
    private final HttpRequest request;
    private final Lazy scheme$delegate = LazyKt.lazy(new NettyConnectionPoint$scheme$2(this));

    @Deprecated(message = "Use localHost or serverHost instead")
    public static /* synthetic */ void getHost$annotations() {
    }

    @Deprecated(message = "Use localPort or serverPort instead")
    public static /* synthetic */ void getPort$annotations() {
    }

    public NettyConnectionPoint(HttpRequest httpRequest, ChannelHandlerContext channelHandlerContext) {
        Intrinsics.checkNotNullParameter(httpRequest, "request");
        Intrinsics.checkNotNullParameter(channelHandlerContext, "context");
        this.request = httpRequest;
        this.context = channelHandlerContext;
    }

    public String getVersion() {
        String text = this.request.protocolVersion().text();
        Intrinsics.checkNotNullExpressionValue(text, "request.protocolVersion().text()");
        return text;
    }

    public String getUri() {
        String uri = this.request.uri();
        Intrinsics.checkNotNullExpressionValue(uri, "request.uri()");
        return uri;
    }

    public HttpMethod getMethod() {
        HttpMethod.Companion companion = HttpMethod.Companion;
        String name = this.request.method().name();
        Intrinsics.checkNotNullExpressionValue(name, "request.method().name()");
        return companion.parse(name);
    }

    public String getScheme() {
        return (String) this.scheme$delegate.getValue();
    }

    public String getHost() {
        String substringBefore$default;
        String str = this.request.headers().get(HttpHeaders.INSTANCE.getHost());
        String str2 = null;
        if (str != null && (substringBefore$default = StringsKt.substringBefore$default(str, ":", (String) null, 2, (Object) null)) != null) {
            return substringBefore$default;
        }
        SocketAddress localAddress = this.context.channel().localAddress();
        InetSocketAddress inetSocketAddress = localAddress instanceof InetSocketAddress ? (InetSocketAddress) localAddress : null;
        if (inetSocketAddress != null && (str2 = inetSocketAddress.getHostName()) == null) {
            str2 = inetSocketAddress.getAddress().getHostAddress();
        }
        return str2 == null ? "localhost" : str2;
    }

    public int getPort() {
        SocketAddress localAddress = this.context.channel().localAddress();
        InetSocketAddress inetSocketAddress = localAddress instanceof InetSocketAddress ? (InetSocketAddress) localAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 80;
    }

    public String getLocalHost() {
        SocketAddress localAddress = this.context.channel().localAddress();
        String str = null;
        InetSocketAddress inetSocketAddress = localAddress instanceof InetSocketAddress ? (InetSocketAddress) localAddress : null;
        if (inetSocketAddress != null) {
            String hostName = inetSocketAddress.getHostName();
            str = hostName == null ? inetSocketAddress.getHostString() : hostName;
        }
        return str == null ? "localhost" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0012, code lost:
        r0 = kotlin.text.StringsKt.substringBefore$default(r0, ":", (java.lang.String) null, 2, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getServerHost() {
        /*
            r4 = this;
            io.netty.handler.codec.http.HttpRequest r0 = r4.request
            io.netty.handler.codec.http.HttpHeaders r0 = r0.headers()
            io.ktor.http.HttpHeaders r1 = io.ktor.http.HttpHeaders.INSTANCE
            java.lang.String r1 = r1.getHost()
            java.lang.String r0 = r0.get((java.lang.String) r1)
            if (r0 == 0) goto L_0x001c
            java.lang.String r1 = ":"
            r2 = 2
            r3 = 0
            java.lang.String r0 = kotlin.text.StringsKt.substringBefore$default((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r3)
            if (r0 != 0) goto L_0x0020
        L_0x001c:
            java.lang.String r0 = r4.getLocalHost()
        L_0x0020:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http1.NettyConnectionPoint.getServerHost():java.lang.String");
    }

    public String getLocalAddress() {
        SocketAddress localAddress = this.context.channel().localAddress();
        String str = null;
        InetSocketAddress inetSocketAddress = localAddress instanceof InetSocketAddress ? (InetSocketAddress) localAddress : null;
        if (inetSocketAddress != null) {
            str = inetSocketAddress.getHostString();
        }
        return str == null ? "localhost" : str;
    }

    private final int getDefaultPort() {
        return URLProtocol.Companion.createOrDefault(getScheme()).getDefaultPort();
    }

    public int getLocalPort() {
        SocketAddress localAddress = this.context.channel().localAddress();
        InetSocketAddress inetSocketAddress = localAddress instanceof InetSocketAddress ? (InetSocketAddress) localAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return getDefaultPort();
    }

    public int getServerPort() {
        String substringAfter;
        String str = this.request.headers().get(HttpHeaders.INSTANCE.getHost());
        if (str == null || (substringAfter = StringsKt.substringAfter(str, ":", String.valueOf(getDefaultPort()))) == null) {
            return getLocalPort();
        }
        return Integer.parseInt(substringAfter);
    }

    public String getRemoteHost() {
        SocketAddress remoteAddress = this.context.channel().remoteAddress();
        String str = null;
        InetSocketAddress inetSocketAddress = remoteAddress instanceof InetSocketAddress ? (InetSocketAddress) remoteAddress : null;
        if (inetSocketAddress != null) {
            String hostName = inetSocketAddress.getHostName();
            str = hostName == null ? inetSocketAddress.getAddress().getHostAddress() : hostName;
        }
        return str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
    }

    public int getRemotePort() {
        SocketAddress remoteAddress = this.context.channel().remoteAddress();
        InetSocketAddress inetSocketAddress = remoteAddress instanceof InetSocketAddress ? (InetSocketAddress) remoteAddress : null;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 0;
    }

    public String getRemoteAddress() {
        SocketAddress remoteAddress = this.context.channel().remoteAddress();
        String str = null;
        InetSocketAddress inetSocketAddress = remoteAddress instanceof InetSocketAddress ? (InetSocketAddress) remoteAddress : null;
        if (inetSocketAddress != null) {
            str = inetSocketAddress.getHostString();
        }
        return str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
    }
}
