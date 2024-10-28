package io.ktor.client.plugins;

import androidx.core.os.EnvironmentCompat;
import io.ktor.client.network.sockets.ConnectTimeoutException;
import io.ktor.client.network.sockets.SocketTimeoutException;
import io.ktor.client.plugins.HttpTimeout;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.client.request.HttpRequestData;
import io.ktor.client.utils.ExceptionUtilsJvmKt;
import io.ktor.util.InternalAPI;
import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000V\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a)\u0010\u0003\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0002\u0010\r\u001a\u001a\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u001a\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a%\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0016H\bø\u0001\u0000¢\u0006\u0002\u0010\u0017\u001a#\u0010\u000b\u001a\u00020\u0018*\u00020\u00192\u0017\u0010\u0015\u001a\u0013\u0012\u0004\u0012\u00020\u001b\u0012\u0004\u0012\u00020\u00180\u001a¢\u0006\u0002\b\u001c\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\u0002\n\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001d"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "ConnectTimeoutException", "Lio/ktor/client/network/sockets/ConnectTimeoutException;", "request", "Lio/ktor/client/request/HttpRequestData;", "cause", "", "url", "", "timeout", "", "(Ljava/lang/String;Ljava/lang/Long;Ljava/lang/Throwable;)Lio/ktor/client/network/sockets/ConnectTimeoutException;", "SocketTimeoutException", "Lio/ktor/client/network/sockets/SocketTimeoutException;", "convertLongTimeoutToIntWithInfiniteAsZero", "", "convertLongTimeoutToLongWithInfiniteAsZero", "unwrapRequestTimeoutException", "T", "block", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "", "Lio/ktor/client/request/HttpRequestBuilder;", "Lkotlin/Function1;", "Lio/ktor/client/plugins/HttpTimeout$HttpTimeoutCapabilityConfiguration;", "Lkotlin/ExtensionFunctionType;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpTimeout.kt */
public final class HttpTimeoutKt {
    /* access modifiers changed from: private */
    public static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpTimeout");

    @InternalAPI
    public static final int convertLongTimeoutToIntWithInfiniteAsZero(long j) {
        if (j == Long.MAX_VALUE) {
            return 0;
        }
        if (j < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        if (j > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        return (int) j;
    }

    @InternalAPI
    public static final long convertLongTimeoutToLongWithInfiniteAsZero(long j) {
        if (j == Long.MAX_VALUE) {
            return 0;
        }
        return j;
    }

    public static final void timeout(HttpRequestBuilder httpRequestBuilder, Function1<? super HttpTimeout.HttpTimeoutCapabilityConfiguration, Unit> function1) {
        Intrinsics.checkNotNullParameter(httpRequestBuilder, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = new HttpTimeout.HttpTimeoutCapabilityConfiguration((Long) null, (Long) null, (Long) null, 7, (DefaultConstructorMarker) null);
        function1.invoke(httpTimeoutCapabilityConfiguration);
        httpRequestBuilder.setCapability(HttpTimeout.Plugin, httpTimeoutCapabilityConfiguration);
    }

    public static /* synthetic */ ConnectTimeoutException ConnectTimeoutException$default(HttpRequestData httpRequestData, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        return ConnectTimeoutException(httpRequestData, th);
    }

    public static final ConnectTimeoutException ConnectTimeoutException(HttpRequestData httpRequestData, Throwable th) {
        Object obj;
        Intrinsics.checkNotNullParameter(httpRequestData, "request");
        StringBuilder sb = new StringBuilder("Connect timeout has expired [url=");
        sb.append(httpRequestData.getUrl());
        sb.append(", connect_timeout=");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestData.getCapabilityOrNull(HttpTimeout.Plugin);
        if (httpTimeoutCapabilityConfiguration == null || (obj = httpTimeoutCapabilityConfiguration.getConnectTimeoutMillis()) == null) {
            obj = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        sb.append(obj);
        sb.append(" ms]");
        return new ConnectTimeoutException(sb.toString(), th);
    }

    public static /* synthetic */ ConnectTimeoutException ConnectTimeoutException$default(String str, Long l, Throwable th, int i, Object obj) {
        if ((i & 4) != 0) {
            th = null;
        }
        return ConnectTimeoutException(str, l, th);
    }

    public static final ConnectTimeoutException ConnectTimeoutException(String str, Long l, Throwable th) {
        Intrinsics.checkNotNullParameter(str, RtspHeaders.Values.URL);
        StringBuilder sb = new StringBuilder("Connect timeout has expired [url=");
        sb.append(str);
        sb.append(", connect_timeout=");
        Object obj = l;
        if (l == null) {
            obj = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        sb.append(obj);
        sb.append(" ms]");
        return new ConnectTimeoutException(sb.toString(), th);
    }

    public static /* synthetic */ SocketTimeoutException SocketTimeoutException$default(HttpRequestData httpRequestData, Throwable th, int i, Object obj) {
        if ((i & 2) != 0) {
            th = null;
        }
        return SocketTimeoutException(httpRequestData, th);
    }

    public static final SocketTimeoutException SocketTimeoutException(HttpRequestData httpRequestData, Throwable th) {
        Object obj;
        Intrinsics.checkNotNullParameter(httpRequestData, "request");
        StringBuilder sb = new StringBuilder("Socket timeout has expired [url=");
        sb.append(httpRequestData.getUrl());
        sb.append(", socket_timeout=");
        HttpTimeout.HttpTimeoutCapabilityConfiguration httpTimeoutCapabilityConfiguration = (HttpTimeout.HttpTimeoutCapabilityConfiguration) httpRequestData.getCapabilityOrNull(HttpTimeout.Plugin);
        if (httpTimeoutCapabilityConfiguration == null || (obj = httpTimeoutCapabilityConfiguration.getSocketTimeoutMillis()) == null) {
            obj = EnvironmentCompat.MEDIA_UNKNOWN;
        }
        sb.append(obj);
        sb.append("] ms");
        return new SocketTimeoutException(sb.toString(), th);
    }

    public static final <T> T unwrapRequestTimeoutException(Function0<? extends T> function0) {
        Intrinsics.checkNotNullParameter(function0, "block");
        try {
            return function0.invoke();
        } catch (CancellationException e) {
            throw ExceptionUtilsJvmKt.unwrapCancellationException(e);
        }
    }
}
