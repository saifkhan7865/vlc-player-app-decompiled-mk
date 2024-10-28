package io.ktor.server.plugins;

import androidx.core.app.NotificationCompat;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.videolan.vlc.gui.dialogs.NetworkServerDialog;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0016\b\u0000\u0018\u00002\u00020\u0001B\u000f\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\u0002\u0010\bR\u001a\u0010\t\u001a\u00020\u00078VX\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\rR\u0014\u0010\u0010\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0014\u0010\u0016\u001a\u00020\u00178VX\u0004¢\u0006\u0006\u001a\u0004\b\u0018\u0010\u0019R\u001a\u0010\u001a\u001a\u00020\u00138VX\u0004¢\u0006\f\u0012\u0004\b\u001b\u0010\u000b\u001a\u0004\b\u001c\u0010\u0015R\u0014\u0010\u001d\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\rR\u0014\u0010\u001f\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b \u0010\rR\u0014\u0010!\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\u0015R\u0014\u0010#\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\rR\u0014\u0010%\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\rR\u0014\u0010'\u001a\u00020\u00138VX\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\u0015R\u0014\u0010)\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\rR\u0014\u0010+\u001a\u00020\u00078VX\u0004¢\u0006\u0006\u001a\u0004\b,\u0010\r¨\u0006-"}, d2 = {"Lio/ktor/server/plugins/OriginConnectionPoint;", "Lio/ktor/http/RequestConnectionPoint;", "call", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)V", "local", "hostHeaderValue", "", "(Lio/ktor/http/RequestConnectionPoint;Ljava/lang/String;)V", "host", "getHost$annotations", "()V", "getHost", "()Ljava/lang/String;", "localAddress", "getLocalAddress", "localHost", "getLocalHost", "localPort", "", "getLocalPort", "()I", "method", "Lio/ktor/http/HttpMethod;", "getMethod", "()Lio/ktor/http/HttpMethod;", "port", "getPort$annotations", "getPort", "remoteAddress", "getRemoteAddress", "remoteHost", "getRemoteHost", "remotePort", "getRemotePort", "scheme", "getScheme", "serverHost", "getServerHost", "serverPort", "getServerPort", "uri", "getUri", "version", "getVersion", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OriginConnectionPoint.kt */
public final class OriginConnectionPoint implements RequestConnectionPoint {
    private final String hostHeaderValue;
    private final RequestConnectionPoint local;

    @Deprecated(message = "Use localHost or serverHost instead")
    public static /* synthetic */ void getHost$annotations() {
    }

    @Deprecated(message = "Use localHost or serverHost instead")
    public static /* synthetic */ void getPort$annotations() {
    }

    public OriginConnectionPoint(RequestConnectionPoint requestConnectionPoint, String str) {
        Intrinsics.checkNotNullParameter(requestConnectionPoint, "local");
        this.local = requestConnectionPoint;
        this.hostHeaderValue = str;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public OriginConnectionPoint(ApplicationCall applicationCall) {
        this(applicationCall.getRequest().getLocal(), ApplicationRequestPropertiesKt.header(applicationCall.getRequest(), HttpHeaders.INSTANCE.getHost()));
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
    }

    public String getScheme() {
        return this.local.getScheme();
    }

    public String getVersion() {
        return this.local.getVersion();
    }

    public int getPort() {
        String substringAfter;
        Integer intOrNull;
        String str = this.hostHeaderValue;
        if (str == null || (substringAfter = StringsKt.substringAfter(str, ":", (String) NetworkServerDialog.HTTP_DEFAULT_PORT)) == null || (intOrNull = StringsKt.toIntOrNull(substringAfter)) == null) {
            return this.local.getPort();
        }
        return intOrNull.intValue();
    }

    public int getLocalPort() {
        return this.local.getLocalPort();
    }

    public int getServerPort() {
        return this.local.getServerPort();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0004, code lost:
        r0 = kotlin.text.StringsKt.substringBefore$default(r0, ":", (java.lang.String) null, 2, (java.lang.Object) null);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getHost() {
        /*
            r4 = this;
            java.lang.String r0 = r4.hostHeaderValue
            if (r0 == 0) goto L_0x000e
            java.lang.String r1 = ":"
            r2 = 2
            r3 = 0
            java.lang.String r0 = kotlin.text.StringsKt.substringBefore$default((java.lang.String) r0, (java.lang.String) r1, (java.lang.String) r3, (int) r2, (java.lang.Object) r3)
            if (r0 != 0) goto L_0x0014
        L_0x000e:
            io.ktor.http.RequestConnectionPoint r0 = r4.local
            java.lang.String r0 = r0.getHost()
        L_0x0014:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.plugins.OriginConnectionPoint.getHost():java.lang.String");
    }

    public String getLocalHost() {
        return this.local.getLocalHost();
    }

    public String getServerHost() {
        return this.local.getServerHost();
    }

    public String getLocalAddress() {
        return this.local.getLocalAddress();
    }

    public String getUri() {
        return this.local.getUri();
    }

    public HttpMethod getMethod() {
        return this.local.getMethod();
    }

    public String getRemoteHost() {
        return this.local.getRemoteHost();
    }

    public int getRemotePort() {
        return this.local.getRemotePort();
    }

    public String getRemoteAddress() {
        return this.local.getRemoteAddress();
    }
}
