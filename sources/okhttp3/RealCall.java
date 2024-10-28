package okhttp3;

import androidx.core.app.NotificationCompat;
import java.io.Closeable;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.atomic.AtomicInteger;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.Util;
import okhttp3.internal.cache.CacheInterceptor;
import okhttp3.internal.connection.ConnectInterceptor;
import okhttp3.internal.connection.Exchange;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.http.BridgeInterceptor;
import okhttp3.internal.http.CallServerInterceptor;
import okhttp3.internal.http.RealInterceptorChain;
import okhttp3.internal.http.RetryAndFollowUpInterceptor;
import okio.Timeout;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u0000 '2\u00020\u0001:\u0002&'B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\b\u0010\u0015\u001a\u00020\u0016H\u0016J\b\u0010\u0017\u001a\u00020\u0000H\u0016J\u0010\u0010\u0018\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\u0006\u0010\u001d\u001a\u00020\u001cJ\b\u0010\u001e\u001a\u00020\u0007H\u0016J\b\u0010\u001f\u001a\u00020\u0007H\u0016J\u0006\u0010 \u001a\u00020!J\b\u0010\"\u001a\u00020\u0005H\u0016J\b\u0010#\u001a\u00020$H\u0016J\u0006\u0010%\u001a\u00020!R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u001a\u0010\u000b\u001a\u00020\u0007X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u000e\u0010\u0013\u001a\u00020\u0014X.¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lokhttp3/RealCall;", "Lokhttp3/Call;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "(Lokhttp3/OkHttpClient;Lokhttp3/Request;Z)V", "getClient", "()Lokhttp3/OkHttpClient;", "executed", "getExecuted", "()Z", "setExecuted", "(Z)V", "getForWebSocket", "getOriginalRequest", "()Lokhttp3/Request;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "cancel", "", "clone", "enqueue", "responseCallback", "Lokhttp3/Callback;", "execute", "Lokhttp3/Response;", "getResponseWithInterceptorChain", "isCanceled", "isExecuted", "redactedUrl", "", "request", "timeout", "Lokio/Timeout;", "toLoggableString", "AsyncCall", "Companion", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: RealCall.kt */
public final class RealCall implements Call {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private final OkHttpClient client;
    private boolean executed;
    private final boolean forWebSocket;
    private final Request originalRequest;
    /* access modifiers changed from: private */
    public Transmitter transmitter;

    private RealCall(OkHttpClient okHttpClient, Request request, boolean z) {
        this.client = okHttpClient;
        this.originalRequest = request;
        this.forWebSocket = z;
    }

    public /* synthetic */ RealCall(OkHttpClient okHttpClient, Request request, boolean z, DefaultConstructorMarker defaultConstructorMarker) {
        this(okHttpClient, request, z);
    }

    public static final /* synthetic */ Transmitter access$getTransmitter$p(RealCall realCall) {
        Transmitter transmitter2 = realCall.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter2;
    }

    public final OkHttpClient getClient() {
        return this.client;
    }

    public final Request getOriginalRequest() {
        return this.originalRequest;
    }

    public final boolean getForWebSocket() {
        return this.forWebSocket;
    }

    public final boolean getExecuted() {
        return this.executed;
    }

    public final void setExecuted(boolean z) {
        this.executed = z;
    }

    public synchronized boolean isExecuted() {
        return this.executed;
    }

    public boolean isCanceled() {
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter2.isCanceled();
    }

    public Request request() {
        return this.originalRequest;
    }

    public Response execute() {
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Already Executed".toString());
            }
        }
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.timeoutEnter();
        Transmitter transmitter3 = this.transmitter;
        if (transmitter3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter3.callStart();
        try {
            this.client.dispatcher().executed$okhttp(this);
            return getResponseWithInterceptorChain();
        } finally {
            this.client.dispatcher().finished$okhttp(this);
        }
    }

    public void enqueue(Callback callback) {
        Intrinsics.checkParameterIsNotNull(callback, "responseCallback");
        synchronized (this) {
            if (!this.executed) {
                this.executed = true;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Already Executed".toString());
            }
        }
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.callStart();
        this.client.dispatcher().enqueue$okhttp(new AsyncCall(this, callback));
    }

    public void cancel() {
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        transmitter2.cancel();
    }

    public Timeout timeout() {
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        return transmitter2.timeout();
    }

    public RealCall clone() {
        return Companion.newRealCall(this.client, this.originalRequest, this.forWebSocket);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0004\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\fJ\u0006\u0010\r\u001a\u00020\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\u0012\u0010\u0011\u001a\u00020\b2\n\u0010\u0012\u001a\u00060\u0000R\u00020\fJ\b\u0010\u0013\u001a\u00020\bH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lokhttp3/RealCall$AsyncCall;", "Ljava/lang/Runnable;", "responseCallback", "Lokhttp3/Callback;", "(Lokhttp3/RealCall;Lokhttp3/Callback;)V", "callsPerHost", "Ljava/util/concurrent/atomic/AtomicInteger;", "executeOn", "", "executorService", "Ljava/util/concurrent/ExecutorService;", "get", "Lokhttp3/RealCall;", "host", "", "request", "Lokhttp3/Request;", "reuseCallsPerHostFrom", "other", "run", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: RealCall.kt */
    public final class AsyncCall implements Runnable {
        private volatile AtomicInteger callsPerHost = new AtomicInteger(0);
        private final Callback responseCallback;
        final /* synthetic */ RealCall this$0;

        public AsyncCall(RealCall realCall, Callback callback) {
            Intrinsics.checkParameterIsNotNull(callback, "responseCallback");
            this.this$0 = realCall;
            this.responseCallback = callback;
        }

        public final AtomicInteger callsPerHost() {
            return this.callsPerHost;
        }

        public final void reuseCallsPerHostFrom(AsyncCall asyncCall) {
            Intrinsics.checkParameterIsNotNull(asyncCall, "other");
            this.callsPerHost = asyncCall.callsPerHost;
        }

        public final String host() {
            return this.this$0.getOriginalRequest().url().host();
        }

        public final Request request() {
            return this.this$0.getOriginalRequest();
        }

        public final RealCall get() {
            return this.this$0;
        }

        public final void executeOn(ExecutorService executorService) {
            Intrinsics.checkParameterIsNotNull(executorService, "executorService");
            Thread.holdsLock(this.this$0.getClient().dispatcher());
            try {
                executorService.execute(this);
            } catch (RejectedExecutionException e) {
                InterruptedIOException interruptedIOException = new InterruptedIOException("executor rejected");
                interruptedIOException.initCause(e);
                RealCall.access$getTransmitter$p(this.this$0).noMoreExchanges(interruptedIOException);
                this.responseCallback.onFailure(this.this$0, interruptedIOException);
                this.this$0.getClient().dispatcher().finished$okhttp(this);
            } catch (Throwable th) {
                this.this$0.getClient().dispatcher().finished$okhttp(this);
                throw th;
            }
        }

        /* JADX WARNING: Removed duplicated region for block: B:17:0x0057 A[SYNTHETIC, Splitter:B:17:0x0057] */
        /* JADX WARNING: Removed duplicated region for block: B:19:0x0076 A[Catch:{ all -> 0x0050, all -> 0x009c }] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public void run() {
            /*
                r7 = this;
                java.lang.String r0 = "Callback failure for "
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "OkHttp "
                r1.<init>(r2)
                okhttp3.RealCall r2 = r7.this$0
                java.lang.String r2 = r2.redactedUrl()
                r1.append(r2)
                java.lang.String r1 = r1.toString()
                java.lang.Thread r2 = java.lang.Thread.currentThread()
                java.lang.String r3 = "currentThread"
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r2, r3)
                java.lang.String r3 = r2.getName()
                r2.setName(r1)
                okhttp3.RealCall r1 = r7.this$0     // Catch:{ all -> 0x009c }
                okhttp3.internal.connection.Transmitter r1 = okhttp3.RealCall.access$getTransmitter$p(r1)     // Catch:{ all -> 0x009c }
                r1.timeoutEnter()     // Catch:{ all -> 0x009c }
                r1 = 0
                okhttp3.RealCall r4 = r7.this$0     // Catch:{ IOException -> 0x0052 }
                okhttp3.Response r1 = r4.getResponseWithInterceptorChain()     // Catch:{ IOException -> 0x0052 }
                r4 = 1
                okhttp3.Callback r5 = r7.responseCallback     // Catch:{ IOException -> 0x004e }
                okhttp3.RealCall r6 = r7.this$0     // Catch:{ IOException -> 0x004e }
                okhttp3.Call r6 = (okhttp3.Call) r6     // Catch:{ IOException -> 0x004e }
                r5.onResponse(r6, r1)     // Catch:{ IOException -> 0x004e }
                okhttp3.RealCall r0 = r7.this$0     // Catch:{ all -> 0x009c }
                okhttp3.OkHttpClient r0 = r0.getClient()     // Catch:{ all -> 0x009c }
                okhttp3.Dispatcher r0 = r0.dispatcher()     // Catch:{ all -> 0x009c }
            L_0x004a:
                r0.finished$okhttp((okhttp3.RealCall.AsyncCall) r7)     // Catch:{ all -> 0x009c }
                goto L_0x008a
            L_0x004e:
                r1 = move-exception
                goto L_0x0055
            L_0x0050:
                r0 = move-exception
                goto L_0x008e
            L_0x0052:
                r4 = move-exception
                r1 = r4
                r4 = 0
            L_0x0055:
                if (r4 == 0) goto L_0x0076
                okhttp3.internal.platform.Platform$Companion r4 = okhttp3.internal.platform.Platform.Companion     // Catch:{ all -> 0x0050 }
                okhttp3.internal.platform.Platform r4 = r4.get()     // Catch:{ all -> 0x0050 }
                java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ all -> 0x0050 }
                r5.<init>(r0)     // Catch:{ all -> 0x0050 }
                okhttp3.RealCall r0 = r7.this$0     // Catch:{ all -> 0x0050 }
                java.lang.String r0 = r0.toLoggableString()     // Catch:{ all -> 0x0050 }
                r5.append(r0)     // Catch:{ all -> 0x0050 }
                java.lang.String r0 = r5.toString()     // Catch:{ all -> 0x0050 }
                java.lang.Throwable r1 = (java.lang.Throwable) r1     // Catch:{ all -> 0x0050 }
                r5 = 4
                r4.log(r5, r0, r1)     // Catch:{ all -> 0x0050 }
                goto L_0x007f
            L_0x0076:
                okhttp3.Callback r0 = r7.responseCallback     // Catch:{ all -> 0x0050 }
                okhttp3.RealCall r4 = r7.this$0     // Catch:{ all -> 0x0050 }
                okhttp3.Call r4 = (okhttp3.Call) r4     // Catch:{ all -> 0x0050 }
                r0.onFailure(r4, r1)     // Catch:{ all -> 0x0050 }
            L_0x007f:
                okhttp3.RealCall r0 = r7.this$0     // Catch:{ all -> 0x009c }
                okhttp3.OkHttpClient r0 = r0.getClient()     // Catch:{ all -> 0x009c }
                okhttp3.Dispatcher r0 = r0.dispatcher()     // Catch:{ all -> 0x009c }
                goto L_0x004a
            L_0x008a:
                r2.setName(r3)
                return
            L_0x008e:
                okhttp3.RealCall r1 = r7.this$0     // Catch:{ all -> 0x009c }
                okhttp3.OkHttpClient r1 = r1.getClient()     // Catch:{ all -> 0x009c }
                okhttp3.Dispatcher r1 = r1.dispatcher()     // Catch:{ all -> 0x009c }
                r1.finished$okhttp((okhttp3.RealCall.AsyncCall) r7)     // Catch:{ all -> 0x009c }
                throw r0     // Catch:{ all -> 0x009c }
            L_0x009c:
                r0 = move-exception
                r2.setName(r3)
                goto L_0x00a2
            L_0x00a1:
                throw r0
            L_0x00a2:
                goto L_0x00a1
            */
            throw new UnsupportedOperationException("Method not decompiled: okhttp3.RealCall.AsyncCall.run():void");
        }
    }

    public final String toLoggableString() {
        StringBuilder sb = new StringBuilder();
        sb.append(isCanceled() ? "canceled " : "");
        sb.append(this.forWebSocket ? "web socket" : NotificationCompat.CATEGORY_CALL);
        sb.append(" to ");
        sb.append(redactedUrl());
        return sb.toString();
    }

    public final String redactedUrl() {
        return this.originalRequest.url().redact();
    }

    public final Response getResponseWithInterceptorChain() throws IOException {
        List arrayList = new ArrayList();
        Collection collection = arrayList;
        CollectionsKt.addAll(collection, this.client.interceptors());
        collection.add(new RetryAndFollowUpInterceptor(this.client));
        collection.add(new BridgeInterceptor(this.client.cookieJar()));
        collection.add(new CacheInterceptor(this.client.cache()));
        collection.add(ConnectInterceptor.INSTANCE);
        if (!this.forWebSocket) {
            CollectionsKt.addAll(collection, this.client.networkInterceptors());
        }
        collection.add(new CallServerInterceptor(this.forWebSocket));
        Transmitter transmitter2 = this.transmitter;
        if (transmitter2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("transmitter");
        }
        try {
            Response proceed = new RealInterceptorChain(arrayList, transmitter2, (Exchange) null, 0, this.originalRequest, this, this.client.connectTimeoutMillis(), this.client.readTimeoutMillis(), this.client.writeTimeoutMillis()).proceed(this.originalRequest);
            Transmitter transmitter3 = this.transmitter;
            if (transmitter3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transmitter");
            }
            if (!transmitter3.isCanceled()) {
                Transmitter transmitter4 = this.transmitter;
                if (transmitter4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("transmitter");
                }
                transmitter4.noMoreExchanges((IOException) null);
                return proceed;
            }
            Util.closeQuietly((Closeable) proceed);
            throw new IOException("Canceled");
        } catch (IOException e) {
            Transmitter transmitter5 = this.transmitter;
            if (transmitter5 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("transmitter");
            }
            IOException noMoreExchanges = transmitter5.noMoreExchanges(e);
            if (noMoreExchanges == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.Throwable");
            }
            throw noMoreExchanges;
        } catch (Throwable th) {
            if (1 == 0) {
                Transmitter transmitter6 = this.transmitter;
                if (transmitter6 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("transmitter");
                }
                transmitter6.noMoreExchanges((IOException) null);
            }
            throw th;
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001e\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n¨\u0006\u000b"}, d2 = {"Lokhttp3/RealCall$Companion;", "", "()V", "newRealCall", "Lokhttp3/RealCall;", "client", "Lokhttp3/OkHttpClient;", "originalRequest", "Lokhttp3/Request;", "forWebSocket", "", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: RealCall.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RealCall newRealCall(OkHttpClient okHttpClient, Request request, boolean z) {
            Intrinsics.checkParameterIsNotNull(okHttpClient, "client");
            Intrinsics.checkParameterIsNotNull(request, "originalRequest");
            RealCall realCall = new RealCall(okHttpClient, request, z, (DefaultConstructorMarker) null);
            realCall.transmitter = new Transmitter(okHttpClient, realCall);
            return realCall;
        }
    }
}
