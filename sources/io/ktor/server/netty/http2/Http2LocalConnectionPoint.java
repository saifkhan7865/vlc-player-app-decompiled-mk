package io.ktor.server.netty.http2;

import androidx.core.os.EnvironmentCompat;
import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.URLProtocol;
import io.netty.handler.codec.http2.Http2Headers;
import java.net.InetSocketAddress;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0000\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8BX\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u001a\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\f\u0012\u0004\b\u000e\u0010\u000f\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0014\u0010\u0014\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0016\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u000bR\u0014\u0010\u0018\u001a\u00020\u0019X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001c\u001a\u00020\t8VX\u0004¢\u0006\f\u0012\u0004\b\u001d\u0010\u000f\u001a\u0004\b\u001e\u0010\u000bR\u0014\u0010\u001f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\u0011R\u0014\u0010!\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0011R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010#\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\u000bR\u0014\u0010%\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\u0011R\u0014\u0010'\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0011R\u0014\u0010)\u001a\u00020\t8VX\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\u000bR\u0014\u0010+\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\u0011R\u0014\u0010-\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\u0011¨\u0006/"}, d2 = {"Lio/ktor/server/netty/http2/Http2LocalConnectionPoint;", "Lio/ktor/http/RequestConnectionPoint;", "nettyHeaders", "Lio/netty/handler/codec/http2/Http2Headers;", "localNetworkAddress", "Ljava/net/InetSocketAddress;", "remoteNetworkAddress", "(Lio/netty/handler/codec/http2/Http2Headers;Ljava/net/InetSocketAddress;Ljava/net/InetSocketAddress;)V", "defaultPort", "", "getDefaultPort", "()I", "host", "", "getHost$annotations", "()V", "getHost", "()Ljava/lang/String;", "localAddress", "getLocalAddress", "localHost", "getLocalHost", "localPort", "getLocalPort", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "port", "getPort$annotations", "getPort", "remoteAddress", "getRemoteAddress", "remoteHost", "getRemoteHost", "remotePort", "getRemotePort", "scheme", "getScheme", "serverHost", "getServerHost", "serverPort", "getServerPort", "uri", "getUri", "version", "getVersion", "ktor-server-netty"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Http2LocalConnectionPoint.kt */
public final class Http2LocalConnectionPoint implements RequestConnectionPoint {
    private final InetSocketAddress localNetworkAddress;
    private final HttpMethod method;
    private final Http2Headers nettyHeaders;
    private final InetSocketAddress remoteNetworkAddress;

    @Deprecated(message = "Use localHost or serverHost instead")
    public static /* synthetic */ void getHost$annotations() {
    }

    @Deprecated(message = "Use localPort or serverPort instead")
    public static /* synthetic */ void getPort$annotations() {
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0014, code lost:
        r2 = io.ktor.http.HttpMethod.Companion.parse(r2.toString());
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public Http2LocalConnectionPoint(io.netty.handler.codec.http2.Http2Headers r2, java.net.InetSocketAddress r3, java.net.InetSocketAddress r4) {
        /*
            r1 = this;
            java.lang.String r0 = "nettyHeaders"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r2, r0)
            r1.<init>()
            r1.nettyHeaders = r2
            r1.localNetworkAddress = r3
            r1.remoteNetworkAddress = r4
            java.lang.CharSequence r2 = r2.method()
            if (r2 == 0) goto L_0x0020
            io.ktor.http.HttpMethod$Companion r3 = io.ktor.http.HttpMethod.Companion
            java.lang.String r2 = r2.toString()
            io.ktor.http.HttpMethod r2 = r3.parse(r2)
            if (r2 != 0) goto L_0x0026
        L_0x0020:
            io.ktor.http.HttpMethod$Companion r2 = io.ktor.http.HttpMethod.Companion
            io.ktor.http.HttpMethod r2 = r2.getGet()
        L_0x0026:
            r1.method = r2
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.Http2LocalConnectionPoint.<init>(io.netty.handler.codec.http2.Http2Headers, java.net.InetSocketAddress, java.net.InetSocketAddress):void");
    }

    public HttpMethod getMethod() {
        return this.method;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getScheme() {
        /*
            r1 = this;
            io.netty.handler.codec.http2.Http2Headers r0 = r1.nettyHeaders
            java.lang.CharSequence r0 = r0.scheme()
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            java.lang.String r0 = "http"
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.Http2LocalConnectionPoint.getScheme():java.lang.String");
    }

    public String getVersion() {
        return "HTTP/2";
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = r0.toString();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getUri() {
        /*
            r1 = this;
            io.netty.handler.codec.http2.Http2Headers r0 = r1.nettyHeaders
            java.lang.CharSequence r0 = r0.path()
            if (r0 == 0) goto L_0x000e
            java.lang.String r0 = r0.toString()
            if (r0 != 0) goto L_0x0010
        L_0x000e:
            java.lang.String r0 = "/"
        L_0x0010:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.Http2LocalConnectionPoint.getUri():java.lang.String");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = kotlin.text.StringsKt.substringBefore$default((r0 = r0.toString()), ":", (java.lang.String) null, 2, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getHost() {
        /*
            r4 = this;
            io.netty.handler.codec.http2.Http2Headers r0 = r4.nettyHeaders
            java.lang.CharSequence r0 = r0.authority()
            if (r0 == 0) goto L_0x0018
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L_0x0018
            java.lang.String r1 = ":"
            r2 = 2
            r3 = 0
            java.lang.String r0 = kotlin.text.StringsKt.substringBefore$default((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r3)
            if (r0 != 0) goto L_0x001a
        L_0x0018:
            java.lang.String r0 = "localhost"
        L_0x001a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.Http2LocalConnectionPoint.getHost():java.lang.String");
    }

    public int getPort() {
        String obj;
        String substringAfter;
        CharSequence authority = this.nettyHeaders.authority();
        if (!(authority == null || (obj = authority.toString()) == null || (substringAfter = StringsKt.substringAfter(obj, ":", "")) == null)) {
            if (substringAfter.length() <= 0) {
                substringAfter = null;
            }
            if (substringAfter != null) {
                return Integer.parseInt(substringAfter);
            }
        }
        InetSocketAddress inetSocketAddress = this.localNetworkAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 80;
    }

    public String getLocalHost() {
        String str;
        InetSocketAddress inetSocketAddress = this.localNetworkAddress;
        if (inetSocketAddress != null) {
            str = inetSocketAddress.getHostName();
            if (str == null) {
                str = inetSocketAddress.getHostString();
            }
        } else {
            str = null;
        }
        return str == null ? "localhost" : str;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x000e, code lost:
        r0 = kotlin.text.StringsKt.substringBefore$default((r0 = r0.toString()), ":", (java.lang.String) null, 2, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getServerHost() {
        /*
            r4 = this;
            io.netty.handler.codec.http2.Http2Headers r0 = r4.nettyHeaders
            java.lang.CharSequence r0 = r0.authority()
            if (r0 == 0) goto L_0x0019
            java.lang.String r0 = r0.toString()
            if (r0 == 0) goto L_0x0019
            java.lang.String r1 = ":"
            r2 = 2
            r3 = 0
            java.lang.String r0 = kotlin.text.StringsKt.substringBefore$default((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r3)
            if (r0 == 0) goto L_0x0019
            goto L_0x001d
        L_0x0019:
            java.lang.String r0 = r4.getLocalHost()
        L_0x001d:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.netty.http2.Http2LocalConnectionPoint.getServerHost():java.lang.String");
    }

    public String getLocalAddress() {
        InetSocketAddress inetSocketAddress = this.localNetworkAddress;
        String m = inetSocketAddress != null ? inetSocketAddress.getHostString() : null;
        return m == null ? "localhost" : m;
    }

    private final int getDefaultPort() {
        return URLProtocol.Companion.createOrDefault(getScheme()).getDefaultPort();
    }

    public int getLocalPort() {
        InetSocketAddress inetSocketAddress = this.localNetworkAddress;
        return inetSocketAddress != null ? inetSocketAddress.getPort() : getDefaultPort();
    }

    public int getServerPort() {
        String obj;
        String substringAfter;
        CharSequence authority = this.nettyHeaders.authority();
        if (authority == null || (obj = authority.toString()) == null || (substringAfter = StringsKt.substringAfter(obj, ":", String.valueOf(getDefaultPort()))) == null) {
            return getLocalPort();
        }
        return Integer.parseInt(substringAfter);
    }

    public String getRemoteHost() {
        String str;
        InetSocketAddress inetSocketAddress = this.remoteNetworkAddress;
        if (inetSocketAddress != null) {
            str = inetSocketAddress.getHostName();
            if (str == null) {
                str = inetSocketAddress.getAddress().getHostAddress();
            }
        } else {
            str = null;
        }
        return str == null ? EnvironmentCompat.MEDIA_UNKNOWN : str;
    }

    public int getRemotePort() {
        InetSocketAddress inetSocketAddress = this.remoteNetworkAddress;
        if (inetSocketAddress != null) {
            return inetSocketAddress.getPort();
        }
        return 0;
    }

    public String getRemoteAddress() {
        InetSocketAddress inetSocketAddress = this.remoteNetworkAddress;
        String m = inetSocketAddress != null ? inetSocketAddress.getHostString() : null;
        return m == null ? EnvironmentCompat.MEDIA_UNKNOWN : m;
    }
}
