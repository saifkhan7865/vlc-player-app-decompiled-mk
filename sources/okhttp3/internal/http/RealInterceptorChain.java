package okhttp3.internal.http;

import androidx.core.app.NotificationCompat;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.List;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.Transmitter;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001BU\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\n\u0012\u0006\u0010\u0011\u001a\u00020\n¢\u0006\u0002\u0010\u0012J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u0014\u001a\u00020\nH\u0016J\n\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0016J\u0006\u0010\u0007\u001a\u00020\bJ\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\fH\u0016J \u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\b\u0010\u0019\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016J\u0006\u0010\u0005\u001a\u00020\u0006J\u0018\u0010\u001a\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001e\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\u0018\u0010\u001f\u001a\u00020\u00012\u0006\u0010\u001b\u001a\u00020\n2\u0006\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010 \u001a\u00020\nH\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lokhttp3/internal/http/RealInterceptorChain;", "Lokhttp3/Interceptor$Chain;", "interceptors", "", "Lokhttp3/Interceptor;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "exchange", "Lokhttp3/internal/connection/Exchange;", "index", "", "request", "Lokhttp3/Request;", "call", "Lokhttp3/Call;", "connectTimeout", "readTimeout", "writeTimeout", "(Ljava/util/List;Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/Exchange;ILokhttp3/Request;Lokhttp3/Call;III)V", "calls", "connectTimeoutMillis", "connection", "Lokhttp3/Connection;", "proceed", "Lokhttp3/Response;", "readTimeoutMillis", "withConnectTimeout", "timeout", "unit", "Ljava/util/concurrent/TimeUnit;", "withReadTimeout", "withWriteTimeout", "writeTimeoutMillis", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: RealInterceptorChain.kt */
public final class RealInterceptorChain implements Interceptor.Chain {
    private final Call call;
    private int calls;
    private final int connectTimeout;
    private final Exchange exchange;
    private final int index;
    private final List<Interceptor> interceptors;
    private final int readTimeout;
    private final Request request;
    private final Transmitter transmitter;
    private final int writeTimeout;

    public RealInterceptorChain(List<? extends Interceptor> list, Transmitter transmitter2, Exchange exchange2, int i, Request request2, Call call2, int i2, int i3, int i4) {
        Intrinsics.checkParameterIsNotNull(list, "interceptors");
        Intrinsics.checkParameterIsNotNull(transmitter2, "transmitter");
        Intrinsics.checkParameterIsNotNull(request2, "request");
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        this.interceptors = list;
        this.transmitter = transmitter2;
        this.exchange = exchange2;
        this.index = i;
        this.request = request2;
        this.call = call2;
        this.connectTimeout = i2;
        this.readTimeout = i3;
        this.writeTimeout = i4;
    }

    public Connection connection() {
        Exchange exchange2 = this.exchange;
        return exchange2 != null ? exchange2.connection() : null;
    }

    public int connectTimeoutMillis() {
        return this.connectTimeout;
    }

    public Interceptor.Chain withConnectTimeout(int i, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, Util.checkDuration(RtspHeaders.Values.TIMEOUT, (long) i, timeUnit), this.readTimeout, this.writeTimeout);
    }

    public int readTimeoutMillis() {
        return this.readTimeout;
    }

    public Interceptor.Chain withReadTimeout(int i, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, Util.checkDuration(RtspHeaders.Values.TIMEOUT, (long) i, timeUnit), this.writeTimeout);
    }

    public int writeTimeoutMillis() {
        return this.writeTimeout;
    }

    public Interceptor.Chain withWriteTimeout(int i, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "unit");
        return new RealInterceptorChain(this.interceptors, this.transmitter, this.exchange, this.index, this.request, this.call, this.connectTimeout, this.readTimeout, Util.checkDuration(RtspHeaders.Values.TIMEOUT, (long) i, timeUnit));
    }

    public final Transmitter transmitter() {
        return this.transmitter;
    }

    public final Exchange exchange() {
        Exchange exchange2 = this.exchange;
        if (exchange2 == null) {
            Intrinsics.throwNpe();
        }
        return exchange2;
    }

    public Call call() {
        return this.call;
    }

    public Request request() {
        return this.request;
    }

    public Response proceed(Request request2) {
        Intrinsics.checkParameterIsNotNull(request2, "request");
        return proceed(request2, this.transmitter, this.exchange);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0040  */
    /* JADX WARNING: Removed duplicated region for block: B:44:0x0118  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final okhttp3.Response proceed(okhttp3.Request r17, okhttp3.internal.connection.Transmitter r18, okhttp3.internal.connection.Exchange r19) throws java.io.IOException {
        /*
            r16 = this;
            r0 = r16
            java.lang.String r1 = "request"
            r7 = r17
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r1)
            java.lang.String r1 = "transmitter"
            r4 = r18
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r1)
            int r1 = r0.index
            java.util.List<okhttp3.Interceptor> r2 = r0.interceptors
            int r2 = r2.size()
            if (r1 >= r2) goto L_0x0140
            int r1 = r0.calls
            r12 = 1
            int r1 = r1 + r12
            r0.calls = r1
            okhttp3.internal.connection.Exchange r1 = r0.exchange
            r13 = 0
            if (r1 == 0) goto L_0x003b
            okhttp3.internal.connection.RealConnection r1 = r1.connection()
            if (r1 != 0) goto L_0x002e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x002e:
            okhttp3.HttpUrl r2 = r17.url()
            boolean r1 = r1.supportsUrl(r2)
            if (r1 == 0) goto L_0x0039
            goto L_0x003b
        L_0x0039:
            r1 = 0
            goto L_0x003c
        L_0x003b:
            r1 = 1
        L_0x003c:
            java.lang.String r14 = "network interceptor "
            if (r1 == 0) goto L_0x0118
            okhttp3.internal.connection.Exchange r1 = r0.exchange
            if (r1 == 0) goto L_0x004b
            int r1 = r0.calls
            if (r1 > r12) goto L_0x0049
            goto L_0x004b
        L_0x0049:
            r1 = 0
            goto L_0x004c
        L_0x004b:
            r1 = 1
        L_0x004c:
            java.lang.String r15 = " must call proceed() exactly once"
            if (r1 == 0) goto L_0x00f2
            okhttp3.internal.http.RealInterceptorChain r1 = new okhttp3.internal.http.RealInterceptorChain
            java.util.List<okhttp3.Interceptor> r3 = r0.interceptors
            int r2 = r0.index
            int r6 = r2 + 1
            okhttp3.Call r8 = r0.call
            int r9 = r0.connectTimeout
            int r10 = r0.readTimeout
            int r11 = r0.writeTimeout
            r2 = r1
            r4 = r18
            r5 = r19
            r7 = r17
            r2.<init>(r3, r4, r5, r6, r7, r8, r9, r10, r11)
            java.util.List<okhttp3.Interceptor> r2 = r0.interceptors
            int r3 = r0.index
            java.lang.Object r2 = r2.get(r3)
            okhttp3.Interceptor r2 = (okhttp3.Interceptor) r2
            r3 = r1
            okhttp3.Interceptor$Chain r3 = (okhttp3.Interceptor.Chain) r3
            okhttp3.Response r3 = r2.intercept(r3)
            java.lang.String r4 = "interceptor "
            if (r3 == 0) goto L_0x00d9
            if (r19 == 0) goto L_0x0093
            int r5 = r0.index
            int r5 = r5 + r12
            java.util.List<okhttp3.Interceptor> r6 = r0.interceptors
            int r6 = r6.size()
            if (r5 >= r6) goto L_0x0093
            int r1 = r1.calls
            if (r1 != r12) goto L_0x0091
            goto L_0x0093
        L_0x0091:
            r1 = 0
            goto L_0x0094
        L_0x0093:
            r1 = 1
        L_0x0094:
            if (r1 == 0) goto L_0x00be
            okhttp3.ResponseBody r1 = r3.body()
            if (r1 == 0) goto L_0x009d
            goto L_0x009e
        L_0x009d:
            r12 = 0
        L_0x009e:
            if (r12 == 0) goto L_0x00a1
            return r3
        L_0x00a1:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r4)
            r1.append(r2)
            java.lang.String r2 = " returned a response with no body"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x00be:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r14)
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x00d9:
            java.lang.NullPointerException r1 = new java.lang.NullPointerException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>(r4)
            r3.append(r2)
            java.lang.String r2 = " returned null"
            r3.append(r2)
            java.lang.String r2 = r3.toString()
            r1.<init>(r2)
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        L_0x00f2:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r14)
            java.util.List<okhttp3.Interceptor> r2 = r0.interceptors
            int r3 = r0.index
            int r3 = r3 - r12
            java.lang.Object r2 = r2.get(r3)
            okhttp3.Interceptor r2 = (okhttp3.Interceptor) r2
            r1.append(r2)
            r1.append(r15)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0118:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>(r14)
            java.util.List<okhttp3.Interceptor> r2 = r0.interceptors
            int r3 = r0.index
            int r3 = r3 - r12
            java.lang.Object r2 = r2.get(r3)
            okhttp3.Interceptor r2 = (okhttp3.Interceptor) r2
            r1.append(r2)
            java.lang.String r2 = " must retain the same host and port"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.IllegalStateException r2 = new java.lang.IllegalStateException
            java.lang.String r1 = r1.toString()
            r2.<init>(r1)
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            throw r2
        L_0x0140:
            java.lang.AssertionError r1 = new java.lang.AssertionError
            r1.<init>()
            java.lang.Throwable r1 = (java.lang.Throwable) r1
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.http.RealInterceptorChain.proceed(okhttp3.Request, okhttp3.internal.connection.Transmitter, okhttp3.internal.connection.Exchange):okhttp3.Response");
    }
}
