package io.ktor.server.plugins;

import io.ktor.http.HttpMethod;
import io.ktor.http.RequestConnectionPoint;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MutablePropertyReference1Impl;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KProperty;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b,\u0018\u00002\u00020\u0001B\u000f\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0002\u0010\u0003R1\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0018\n\u0004\b\r\u0010\u000e\u0012\u0004\b\u0007\u0010\b\u001a\u0004\b\t\u0010\n\"\u0004\b\u000b\u0010\fR+\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\b\u0012\u0010\u000e\u001a\u0004\b\u0010\u0010\n\"\u0004\b\u0011\u0010\fR+\u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\b\u0016\u0010\u000e\u001a\u0004\b\u0014\u0010\n\"\u0004\b\u0015\u0010\fR+\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00178V@VX\u0002¢\u0006\u0012\n\u0004\b\u001d\u0010\u000e\u001a\u0004\b\u0019\u0010\u001a\"\u0004\b\u001b\u0010\u001cR+\u0010\u001f\u001a\u00020\u001e2\u0006\u0010\u0004\u001a\u00020\u001e8V@VX\u0002¢\u0006\u0012\n\u0004\b$\u0010\u000e\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R1\u0010%\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00178V@VX\u0002¢\u0006\u0018\n\u0004\b)\u0010\u000e\u0012\u0004\b&\u0010\b\u001a\u0004\b'\u0010\u001a\"\u0004\b(\u0010\u001cR+\u0010*\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\b-\u0010\u000e\u001a\u0004\b+\u0010\n\"\u0004\b,\u0010\fR+\u0010.\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\b1\u0010\u000e\u001a\u0004\b/\u0010\n\"\u0004\b0\u0010\fR+\u00102\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00178V@VX\u0002¢\u0006\u0012\n\u0004\b5\u0010\u000e\u001a\u0004\b3\u0010\u001a\"\u0004\b4\u0010\u001cR+\u00106\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\b9\u0010\u000e\u001a\u0004\b7\u0010\n\"\u0004\b8\u0010\fR+\u0010:\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\b=\u0010\u000e\u001a\u0004\b;\u0010\n\"\u0004\b<\u0010\fR+\u0010>\u001a\u00020\u00172\u0006\u0010\u0004\u001a\u00020\u00178V@VX\u0002¢\u0006\u0012\n\u0004\bA\u0010\u000e\u001a\u0004\b?\u0010\u001a\"\u0004\b@\u0010\u001cR+\u0010B\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\bE\u0010\u000e\u001a\u0004\bC\u0010\n\"\u0004\bD\u0010\fR+\u0010F\u001a\u00020\u00052\u0006\u0010\u0004\u001a\u00020\u00058V@VX\u0002¢\u0006\u0012\n\u0004\bI\u0010\u000e\u001a\u0004\bG\u0010\n\"\u0004\bH\u0010\f¨\u0006J"}, d2 = {"Lio/ktor/server/plugins/MutableOriginConnectionPoint;", "Lio/ktor/http/RequestConnectionPoint;", "delegate", "(Lio/ktor/http/RequestConnectionPoint;)V", "<set-?>", "", "host", "getHost$annotations", "()V", "getHost", "()Ljava/lang/String;", "setHost", "(Ljava/lang/String;)V", "host$delegate", "Lio/ktor/server/plugins/AssignableWithDelegate;", "localAddress", "getLocalAddress", "setLocalAddress", "localAddress$delegate", "localHost", "getLocalHost", "setLocalHost", "localHost$delegate", "", "localPort", "getLocalPort", "()I", "setLocalPort", "(I)V", "localPort$delegate", "Lio/ktor/http/HttpMethod;", "method", "getMethod", "()Lio/ktor/http/HttpMethod;", "setMethod", "(Lio/ktor/http/HttpMethod;)V", "method$delegate", "port", "getPort$annotations", "getPort", "setPort", "port$delegate", "remoteAddress", "getRemoteAddress", "setRemoteAddress", "remoteAddress$delegate", "remoteHost", "getRemoteHost", "setRemoteHost", "remoteHost$delegate", "remotePort", "getRemotePort", "setRemotePort", "remotePort$delegate", "scheme", "getScheme", "setScheme", "scheme$delegate", "serverHost", "getServerHost", "setServerHost", "serverHost$delegate", "serverPort", "getServerPort", "setServerPort", "serverPort$delegate", "uri", "getUri", "setUri", "uri$delegate", "version", "getVersion", "setVersion", "version$delegate", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OriginConnectionPoint.kt */
public final class MutableOriginConnectionPoint implements RequestConnectionPoint {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties;
    private final AssignableWithDelegate host$delegate;
    private final AssignableWithDelegate localAddress$delegate;
    private final AssignableWithDelegate localHost$delegate;
    private final AssignableWithDelegate localPort$delegate;
    private final AssignableWithDelegate method$delegate;
    private final AssignableWithDelegate port$delegate;
    private final AssignableWithDelegate remoteAddress$delegate;
    private final AssignableWithDelegate remoteHost$delegate;
    private final AssignableWithDelegate remotePort$delegate;
    private final AssignableWithDelegate scheme$delegate;
    private final AssignableWithDelegate serverHost$delegate;
    private final AssignableWithDelegate serverPort$delegate;
    private final AssignableWithDelegate uri$delegate;
    private final AssignableWithDelegate version$delegate;

    @Deprecated(message = "Use localHost or serverHost instead")
    public static /* synthetic */ void getHost$annotations() {
    }

    @Deprecated(message = "Use localPort or serverPort instead")
    public static /* synthetic */ void getPort$annotations() {
    }

    public MutableOriginConnectionPoint(RequestConnectionPoint requestConnectionPoint) {
        Intrinsics.checkNotNullParameter(requestConnectionPoint, "delegate");
        this.version$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$version$2(requestConnectionPoint));
        this.uri$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$uri$2(requestConnectionPoint));
        this.method$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$method$2(requestConnectionPoint));
        this.scheme$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$scheme$2(requestConnectionPoint));
        this.host$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$host$2(requestConnectionPoint));
        this.localHost$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$localHost$2(requestConnectionPoint));
        this.serverHost$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$serverHost$2(requestConnectionPoint));
        this.localAddress$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$localAddress$2(requestConnectionPoint));
        this.port$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$port$2(requestConnectionPoint));
        this.localPort$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$localPort$2(requestConnectionPoint));
        this.serverPort$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$serverPort$2(requestConnectionPoint));
        this.remoteHost$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$remoteHost$2(requestConnectionPoint));
        this.remotePort$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$remotePort$2(requestConnectionPoint));
        this.remoteAddress$delegate = new AssignableWithDelegate(new MutableOriginConnectionPoint$remoteAddress$2(requestConnectionPoint));
    }

    static {
        Class<MutableOriginConnectionPoint> cls = MutableOriginConnectionPoint.class;
        $$delegatedProperties = new KProperty[]{Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "version", "getVersion()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, Constants.KEY_URI, "getUri()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "method", "getMethod()Lio/ktor/http/HttpMethod;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "scheme", "getScheme()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "host", "getHost()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "localHost", "getLocalHost()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "serverHost", "getServerHost()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "localAddress", "getLocalAddress()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, RtspHeaders.Values.PORT, "getPort()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "localPort", "getLocalPort()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "serverPort", "getServerPort()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "remoteHost", "getRemoteHost()Ljava/lang/String;", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "remotePort", "getRemotePort()I", 0)), Reflection.mutableProperty1(new MutablePropertyReference1Impl(cls, "remoteAddress", "getRemoteAddress()Ljava/lang/String;", 0))};
    }

    public String getVersion() {
        return (String) this.version$delegate.getValue(this, $$delegatedProperties[0]);
    }

    public void setVersion(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.version$delegate.setValue(this, $$delegatedProperties[0], str);
    }

    public String getUri() {
        return (String) this.uri$delegate.getValue(this, $$delegatedProperties[1]);
    }

    public void setUri(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.uri$delegate.setValue(this, $$delegatedProperties[1], str);
    }

    public HttpMethod getMethod() {
        return (HttpMethod) this.method$delegate.getValue(this, $$delegatedProperties[2]);
    }

    public void setMethod(HttpMethod httpMethod) {
        Intrinsics.checkNotNullParameter(httpMethod, "<set-?>");
        this.method$delegate.setValue(this, $$delegatedProperties[2], httpMethod);
    }

    public String getScheme() {
        return (String) this.scheme$delegate.getValue(this, $$delegatedProperties[3]);
    }

    public void setScheme(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.scheme$delegate.setValue(this, $$delegatedProperties[3], str);
    }

    public String getHost() {
        return (String) this.host$delegate.getValue(this, $$delegatedProperties[4]);
    }

    public void setHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.host$delegate.setValue(this, $$delegatedProperties[4], str);
    }

    public String getLocalHost() {
        return (String) this.localHost$delegate.getValue(this, $$delegatedProperties[5]);
    }

    public void setLocalHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localHost$delegate.setValue(this, $$delegatedProperties[5], str);
    }

    public String getServerHost() {
        return (String) this.serverHost$delegate.getValue(this, $$delegatedProperties[6]);
    }

    public void setServerHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.serverHost$delegate.setValue(this, $$delegatedProperties[6], str);
    }

    public String getLocalAddress() {
        return (String) this.localAddress$delegate.getValue(this, $$delegatedProperties[7]);
    }

    public void setLocalAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.localAddress$delegate.setValue(this, $$delegatedProperties[7], str);
    }

    public int getPort() {
        return ((Number) this.port$delegate.getValue(this, $$delegatedProperties[8])).intValue();
    }

    public void setPort(int i) {
        this.port$delegate.setValue(this, $$delegatedProperties[8], Integer.valueOf(i));
    }

    public int getLocalPort() {
        return ((Number) this.localPort$delegate.getValue(this, $$delegatedProperties[9])).intValue();
    }

    public void setLocalPort(int i) {
        this.localPort$delegate.setValue(this, $$delegatedProperties[9], Integer.valueOf(i));
    }

    public int getServerPort() {
        return ((Number) this.serverPort$delegate.getValue(this, $$delegatedProperties[10])).intValue();
    }

    public void setServerPort(int i) {
        this.serverPort$delegate.setValue(this, $$delegatedProperties[10], Integer.valueOf(i));
    }

    public String getRemoteHost() {
        return (String) this.remoteHost$delegate.getValue(this, $$delegatedProperties[11]);
    }

    public void setRemoteHost(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.remoteHost$delegate.setValue(this, $$delegatedProperties[11], str);
    }

    public int getRemotePort() {
        return ((Number) this.remotePort$delegate.getValue(this, $$delegatedProperties[12])).intValue();
    }

    public void setRemotePort(int i) {
        this.remotePort$delegate.setValue(this, $$delegatedProperties[12], Integer.valueOf(i));
    }

    public String getRemoteAddress() {
        return (String) this.remoteAddress$delegate.getValue(this, $$delegatedProperties[13]);
    }

    public void setRemoteAddress(String str) {
        Intrinsics.checkNotNullParameter(str, "<set-?>");
        this.remoteAddress$delegate.setValue(this, $$delegatedProperties[13], str);
    }
}
