package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.List;
import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.X509TrustManager;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Protocol;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J(\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0016J\u0012\u0010\u000f\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\u0010\u001a\u00020\tH\u0016J\u0012\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016R\u0010\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform;", "Lokhttp3/internal/platform/Platform;", "setProtocolMethod", "Ljava/lang/reflect/Method;", "getProtocolMethod", "(Ljava/lang/reflect/Method;Ljava/lang/reflect/Method;)V", "configureTlsExtensions", "", "sslSocket", "Ljavax/net/ssl/SSLSocket;", "hostname", "", "protocols", "", "Lokhttp3/Protocol;", "getSelectedProtocol", "socket", "trustManager", "Ljavax/net/ssl/X509TrustManager;", "sslSocketFactory", "Ljavax/net/ssl/SSLSocketFactory;", "Companion", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: Jdk9Platform.kt */
public final class Jdk9Platform extends Platform {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public final Method getProtocolMethod;
    public final Method setProtocolMethod;

    public Jdk9Platform(Method method, Method method2) {
        Intrinsics.checkParameterIsNotNull(method, "setProtocolMethod");
        Intrinsics.checkParameterIsNotNull(method2, "getProtocolMethod");
        this.setProtocolMethod = method;
        this.getProtocolMethod = method2;
    }

    public void configureTlsExtensions(SSLSocket sSLSocket, String str, List<? extends Protocol> list) {
        Intrinsics.checkParameterIsNotNull(sSLSocket, "sslSocket");
        Intrinsics.checkParameterIsNotNull(list, "protocols");
        try {
            SSLParameters sSLParameters = sSLSocket.getSSLParameters();
            Collection alpnProtocolNames = Platform.Companion.alpnProtocolNames(list);
            Method method = this.setProtocolMethod;
            Object array = alpnProtocolNames.toArray(new String[0]);
            if (array != null) {
                method.invoke(sSLParameters, new Object[]{array});
                sSLSocket.setSSLParameters(sSLParameters);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
        } catch (IllegalAccessException e) {
            throw UInt$$ExternalSyntheticBackport0.m("failed to set SSL parameters", e);
        } catch (InvocationTargetException e2) {
            throw UInt$$ExternalSyntheticBackport0.m("failed to set SSL parameters", e2);
        }
    }

    public String getSelectedProtocol(SSLSocket sSLSocket) {
        Intrinsics.checkParameterIsNotNull(sSLSocket, "socket");
        try {
            String str = (String) this.getProtocolMethod.invoke(sSLSocket, (Object[]) null);
            if (str == null) {
                return null;
            }
            if (Intrinsics.areEqual((Object) str, (Object) "")) {
                return null;
            }
            return str;
        } catch (IllegalAccessException e) {
            throw UInt$$ExternalSyntheticBackport0.m("failed to get ALPN selected protocol", (Throwable) e);
        } catch (InvocationTargetException e2) {
            throw UInt$$ExternalSyntheticBackport0.m("failed to get ALPN selected protocol", (Throwable) e2);
        }
    }

    public X509TrustManager trustManager(SSLSocketFactory sSLSocketFactory) {
        Intrinsics.checkParameterIsNotNull(sSLSocketFactory, "sslSocketFactory");
        throw new UnsupportedOperationException("clientBuilder.sslSocketFactory(SSLSocketFactory) not supported on JDK 9+");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004¨\u0006\u0005"}, d2 = {"Lokhttp3/internal/platform/Jdk9Platform$Companion;", "", "()V", "buildIfSupported", "Lokhttp3/internal/platform/Jdk9Platform;", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: Jdk9Platform.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Jdk9Platform buildIfSupported() {
            try {
                Method method = SSLParameters.class.getMethod("setApplicationProtocols", new Class[]{String[].class});
                Method method2 = SSLSocket.class.getMethod("getApplicationProtocol", (Class[]) null);
                Intrinsics.checkExpressionValueIsNotNull(method, "setProtocolMethod");
                Intrinsics.checkExpressionValueIsNotNull(method2, "getProtocolMethod");
                return new Jdk9Platform(method, method2);
            } catch (NoSuchMethodException unused) {
                return null;
            }
        }
    }
}
