package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.net.Socket;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSocketFactory;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.CertificatePinner;
import okhttp3.EventListener;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.internal.Util;
import okhttp3.internal.http.ExchangeCodec;
import okhttp3.internal.platform.Platform;
import okio.Timeout;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006*\u0001 \u0018\u00002\u00020\u0001:\u0001FB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010#\u001a\u00020$2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010%\u001a\u00020$J\u0006\u0010&\u001a\u00020\tJ\u0006\u0010'\u001a\u00020$J\u0010\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020+H\u0002J\u0006\u0010,\u001a\u00020$J;\u0010-\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00100\u001a\u00020\t2\u0006\u00101\u001a\u00020\t2\u0006\u00102\u001a\u0002H.H\u0000¢\u0006\u0004\b3\u00104J\u0006\u00105\u001a\u00020\tJ)\u00106\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u00102\u001a\u0002H.2\u0006\u00107\u001a\u00020\tH\u0002¢\u0006\u0002\u00108J\u001d\u00109\u001a\u00020\u00152\u0006\u0010:\u001a\u00020;2\u0006\u0010<\u001a\u00020\tH\u0000¢\u0006\u0002\b=J\u0012\u0010\u001c\u001a\u0004\u0018\u00010/2\b\u00102\u001a\u0004\u0018\u00010/J\u000e\u0010>\u001a\u00020$2\u0006\u0010\u001d\u001a\u00020\u001eJ\b\u0010?\u001a\u0004\u0018\u00010@J\u0006\u0010\u001f\u001a\u00020AJ\u0006\u0010\"\u001a\u00020$J\u0006\u0010B\u001a\u00020$J!\u0010C\u001a\u0002H.\"\n\b\u0000\u0010.*\u0004\u0018\u00010/2\u0006\u0010D\u001a\u0002H.H\u0002¢\u0006\u0002\u0010ER\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\n\u001a\u0004\u0018\u00010\u000bX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\f\u0010\r\"\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0016\u001a\u0004\u0018\u00010\u0017X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u001a\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\u001bR\u000e\u0010\u001c\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u00020 X\u0004¢\u0006\u0004\n\u0002\u0010!R\u000e\u0010\"\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000¨\u0006G"}, d2 = {"Lokhttp3/internal/connection/Transmitter;", "", "client", "Lokhttp3/OkHttpClient;", "call", "Lokhttp3/Call;", "(Lokhttp3/OkHttpClient;Lokhttp3/Call;)V", "callStackTrace", "canceled", "", "connection", "Lokhttp3/internal/connection/RealConnection;", "getConnection", "()Lokhttp3/internal/connection/RealConnection;", "setConnection", "(Lokhttp3/internal/connection/RealConnection;)V", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "eventListener", "Lokhttp3/EventListener;", "exchange", "Lokhttp3/internal/connection/Exchange;", "exchangeFinder", "Lokhttp3/internal/connection/ExchangeFinder;", "exchangeRequestDone", "exchangeResponseDone", "isCanceled", "()Z", "noMoreExchanges", "request", "Lokhttp3/Request;", "timeout", "okhttp3/internal/connection/Transmitter$timeout$1", "Lokhttp3/internal/connection/Transmitter$timeout$1;", "timeoutEarlyExit", "acquireConnectionNoEvents", "", "callStart", "canRetry", "cancel", "createAddress", "Lokhttp3/Address;", "url", "Lokhttp3/HttpUrl;", "exchangeDoneDueToException", "exchangeMessageDone", "E", "Ljava/io/IOException;", "requestDone", "responseDone", "e", "exchangeMessageDone$okhttp", "(Lokhttp3/internal/connection/Exchange;ZZLjava/io/IOException;)Ljava/io/IOException;", "hasExchange", "maybeReleaseConnection", "force", "(Ljava/io/IOException;Z)Ljava/io/IOException;", "newExchange", "chain", "Lokhttp3/Interceptor$Chain;", "doExtensiveHealthChecks", "newExchange$okhttp", "prepareToConnect", "releaseConnectionNoEvents", "Ljava/net/Socket;", "Lokio/Timeout;", "timeoutEnter", "timeoutExit", "cause", "(Ljava/io/IOException;)Ljava/io/IOException;", "TransmitterReference", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: Transmitter.kt */
public final class Transmitter {
    private final Call call;
    private Object callStackTrace;
    private boolean canceled;
    private final OkHttpClient client;
    private RealConnection connection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private Exchange exchange;
    private ExchangeFinder exchangeFinder;
    private boolean exchangeRequestDone;
    private boolean exchangeResponseDone;
    private boolean noMoreExchanges;
    private Request request;
    private final Transmitter$timeout$1 timeout;
    private boolean timeoutEarlyExit;

    public Transmitter(OkHttpClient okHttpClient, Call call2) {
        Intrinsics.checkParameterIsNotNull(okHttpClient, "client");
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        this.client = okHttpClient;
        this.call = call2;
        this.connectionPool = okHttpClient.connectionPool().getDelegate$okhttp();
        this.eventListener = okHttpClient.eventListenerFactory().create(call2);
        Transmitter$timeout$1 transmitter$timeout$1 = new Transmitter$timeout$1(this);
        transmitter$timeout$1.timeout((long) okHttpClient.callTimeoutMillis(), TimeUnit.MILLISECONDS);
        this.timeout = transmitter$timeout$1;
    }

    public final RealConnection getConnection() {
        return this.connection;
    }

    public final void setConnection(RealConnection realConnection) {
        this.connection = realConnection;
    }

    public final boolean isCanceled() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.canceled;
        }
        return z;
    }

    public final Timeout timeout() {
        return this.timeout;
    }

    public final void timeoutEnter() {
        this.timeout.enter();
    }

    public final void timeoutEarlyExit() {
        if (!this.timeoutEarlyExit) {
            this.timeoutEarlyExit = true;
            this.timeout.exit();
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    private final <E extends IOException> E timeoutExit(E e) {
        if (this.timeoutEarlyExit || !this.timeout.exit()) {
            return e;
        }
        E interruptedIOException = new InterruptedIOException(RtspHeaders.Values.TIMEOUT);
        if (e != null) {
            interruptedIOException.initCause((Throwable) e);
        }
        return (IOException) interruptedIOException;
    }

    public final void callStart() {
        this.callStackTrace = Platform.Companion.get().getStackTraceForCloseable("response.body().close()");
        this.eventListener.callStart(this.call);
    }

    public final void prepareToConnect(Request request2) {
        Intrinsics.checkParameterIsNotNull(request2, "request");
        Request request3 = this.request;
        if (request3 != null) {
            if (request3 == null) {
                Intrinsics.throwNpe();
            }
            if (Util.canReuseConnectionFor(request3.url(), request2.url())) {
                ExchangeFinder exchangeFinder2 = this.exchangeFinder;
                if (exchangeFinder2 == null) {
                    Intrinsics.throwNpe();
                }
                if (exchangeFinder2.hasRouteToTry()) {
                    return;
                }
            }
            if (!(this.exchange == null)) {
                throw new IllegalStateException("Check failed.".toString());
            } else if (this.exchangeFinder != null) {
                maybeReleaseConnection((IOException) null, true);
                ExchangeFinder exchangeFinder3 = null;
                this.exchangeFinder = null;
            }
        }
        this.request = request2;
        this.exchangeFinder = new ExchangeFinder(this, this.connectionPool, createAddress(request2.url()), this.call, this.eventListener);
    }

    private final Address createAddress(HttpUrl httpUrl) {
        CertificatePinner certificatePinner;
        HostnameVerifier hostnameVerifier;
        SSLSocketFactory sSLSocketFactory;
        SSLSocketFactory sSLSocketFactory2 = null;
        HostnameVerifier hostnameVerifier2 = null;
        CertificatePinner certificatePinner2 = null;
        if (httpUrl.isHttps()) {
            sSLSocketFactory = this.client.sslSocketFactory();
            hostnameVerifier = this.client.hostnameVerifier();
            certificatePinner = this.client.certificatePinner();
        } else {
            sSLSocketFactory = null;
            hostnameVerifier = null;
            certificatePinner = null;
        }
        return new Address(httpUrl.host(), httpUrl.port(), this.client.dns(), this.client.socketFactory(), sSLSocketFactory, hostnameVerifier, certificatePinner, this.client.proxyAuthenticator(), this.client.proxy(), this.client.protocols(), this.client.connectionSpecs(), this.client.proxySelector());
    }

    public final Exchange newExchange$okhttp(Interceptor.Chain chain, boolean z) {
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        synchronized (this.connectionPool) {
            boolean z2 = true;
            if (!this.noMoreExchanges) {
                if (this.exchange != null) {
                    z2 = false;
                }
                if (z2) {
                    Unit unit = Unit.INSTANCE;
                } else {
                    throw new IllegalStateException("cannot make a new request because the previous response is still open: please call response.close()".toString());
                }
            } else {
                throw new IllegalStateException("released".toString());
            }
        }
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        if (exchangeFinder2 == null) {
            Intrinsics.throwNpe();
        }
        ExchangeCodec find = exchangeFinder2.find(this.client, chain, z);
        Call call2 = this.call;
        EventListener eventListener2 = this.eventListener;
        ExchangeFinder exchangeFinder3 = this.exchangeFinder;
        if (exchangeFinder3 == null) {
            Intrinsics.throwNpe();
        }
        Exchange exchange2 = new Exchange(this, call2, eventListener2, exchangeFinder3, find);
        synchronized (this.connectionPool) {
            this.exchange = exchange2;
            this.exchangeRequestDone = false;
            this.exchangeResponseDone = false;
        }
        return exchange2;
    }

    public final void acquireConnectionNoEvents(RealConnection realConnection) {
        Intrinsics.checkParameterIsNotNull(realConnection, "connection");
        Thread.holdsLock(this.connectionPool);
        if (this.connection == null) {
            this.connection = realConnection;
            realConnection.getTransmitters().add(new TransmitterReference(this, this.callStackTrace));
            return;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final Socket releaseConnectionNoEvents() {
        Thread.holdsLock(this.connectionPool);
        RealConnection realConnection = this.connection;
        if (realConnection == null) {
            Intrinsics.throwNpe();
        }
        Iterator<Reference<Transmitter>> it = realConnection.getTransmitters().iterator();
        boolean z = false;
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            Transmitter transmitter = this;
            if (Intrinsics.areEqual((Object) (Transmitter) it.next().get(), (Object) this)) {
                break;
            }
            i++;
        }
        if (i != -1) {
            z = true;
        }
        if (z) {
            RealConnection realConnection2 = this.connection;
            if (realConnection2 == null) {
                Intrinsics.throwNpe();
            }
            realConnection2.getTransmitters().remove(i);
            RealConnection realConnection3 = null;
            this.connection = null;
            if (realConnection2.getTransmitters().isEmpty()) {
                realConnection2.setIdleAtNanos$okhttp(System.nanoTime());
                if (this.connectionPool.connectionBecameIdle(realConnection2)) {
                    return realConnection2.socket();
                }
            }
            return null;
        }
        throw new IllegalStateException("Check failed.".toString());
    }

    public final void exchangeDoneDueToException() {
        synchronized (this.connectionPool) {
            if (!this.noMoreExchanges) {
                Exchange exchange2 = null;
                this.exchange = null;
                Unit unit = Unit.INSTANCE;
            } else {
                throw new IllegalStateException("Check failed.".toString());
            }
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0054, code lost:
        if (r1 == false) goto L_?;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:?, code lost:
        return maybeReleaseConnection(r6, false);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:?, code lost:
        return r6;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final <E extends java.io.IOException> E exchangeMessageDone$okhttp(okhttp3.internal.connection.Exchange r3, boolean r4, boolean r5, E r6) {
        /*
            r2 = this;
            java.lang.String r0 = "exchange"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r3, r0)
            okhttp3.internal.connection.RealConnectionPool r0 = r2.connectionPool
            monitor-enter(r0)
            okhttp3.internal.connection.Exchange r1 = r2.exchange     // Catch:{ all -> 0x005b }
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r1)     // Catch:{ all -> 0x005b }
            r1 = 1
            r3 = r3 ^ r1
            if (r3 == 0) goto L_0x0014
            monitor-exit(r0)
            return r6
        L_0x0014:
            r3 = 0
            if (r4 == 0) goto L_0x001d
            boolean r4 = r2.exchangeRequestDone     // Catch:{ all -> 0x005b }
            r4 = r4 ^ r1
            r2.exchangeRequestDone = r1     // Catch:{ all -> 0x005b }
            goto L_0x001e
        L_0x001d:
            r4 = 0
        L_0x001e:
            if (r5 == 0) goto L_0x0027
            boolean r5 = r2.exchangeResponseDone     // Catch:{ all -> 0x005b }
            if (r5 != 0) goto L_0x0025
            r4 = 1
        L_0x0025:
            r2.exchangeResponseDone = r1     // Catch:{ all -> 0x005b }
        L_0x0027:
            boolean r5 = r2.exchangeRequestDone     // Catch:{ all -> 0x005b }
            if (r5 == 0) goto L_0x0050
            boolean r5 = r2.exchangeResponseDone     // Catch:{ all -> 0x005b }
            if (r5 == 0) goto L_0x0050
            if (r4 == 0) goto L_0x0050
            okhttp3.internal.connection.Exchange r4 = r2.exchange     // Catch:{ all -> 0x005b }
            if (r4 != 0) goto L_0x0038
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x005b }
        L_0x0038:
            okhttp3.internal.connection.RealConnection r4 = r4.connection()     // Catch:{ all -> 0x005b }
            if (r4 != 0) goto L_0x0041
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x005b }
        L_0x0041:
            int r5 = r4.getSuccessCount$okhttp()     // Catch:{ all -> 0x005b }
            int r5 = r5 + r1
            r4.setSuccessCount$okhttp(r5)     // Catch:{ all -> 0x005b }
            r4 = 0
            r5 = r4
            okhttp3.internal.connection.Exchange r5 = (okhttp3.internal.connection.Exchange) r5     // Catch:{ all -> 0x005b }
            r2.exchange = r4     // Catch:{ all -> 0x005b }
            goto L_0x0051
        L_0x0050:
            r1 = 0
        L_0x0051:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x005b }
            monitor-exit(r0)
            if (r1 == 0) goto L_0x005a
            java.io.IOException r6 = r2.maybeReleaseConnection(r6, r3)
        L_0x005a:
            return r6
        L_0x005b:
            r3 = move-exception
            monitor-exit(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.Transmitter.exchangeMessageDone$okhttp(okhttp3.internal.connection.Exchange, boolean, boolean, java.io.IOException):java.io.IOException");
    }

    public final IOException noMoreExchanges(IOException iOException) {
        synchronized (this.connectionPool) {
            this.noMoreExchanges = true;
            Unit unit = Unit.INSTANCE;
        }
        return maybeReleaseConnection(iOException, false);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0019 A[Catch:{ all -> 0x0013 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final <E extends java.io.IOException> E maybeReleaseConnection(E r7, boolean r8) {
        /*
            r6 = this;
            kotlin.jvm.internal.Ref$ObjectRef r0 = new kotlin.jvm.internal.Ref$ObjectRef
            r0.<init>()
            okhttp3.internal.connection.RealConnectionPool r1 = r6.connectionPool
            monitor-enter(r1)
            r2 = 0
            r3 = 1
            if (r8 == 0) goto L_0x0016
            okhttp3.internal.connection.Exchange r4 = r6.exchange     // Catch:{ all -> 0x0013 }
            if (r4 != 0) goto L_0x0011
            goto L_0x0016
        L_0x0011:
            r4 = 0
            goto L_0x0017
        L_0x0013:
            r7 = move-exception
            goto L_0x0094
        L_0x0016:
            r4 = 1
        L_0x0017:
            if (r4 == 0) goto L_0x0086
            okhttp3.internal.connection.RealConnection r4 = r6.connection     // Catch:{ all -> 0x0013 }
            okhttp3.Connection r4 = (okhttp3.Connection) r4     // Catch:{ all -> 0x0013 }
            r0.element = r4     // Catch:{ all -> 0x0013 }
            okhttp3.internal.connection.RealConnection r4 = r6.connection     // Catch:{ all -> 0x0013 }
            r5 = 0
            if (r4 == 0) goto L_0x0033
            okhttp3.internal.connection.Exchange r4 = r6.exchange     // Catch:{ all -> 0x0013 }
            if (r4 != 0) goto L_0x0033
            if (r8 != 0) goto L_0x002e
            boolean r8 = r6.noMoreExchanges     // Catch:{ all -> 0x0013 }
            if (r8 == 0) goto L_0x0033
        L_0x002e:
            java.net.Socket r8 = r6.releaseConnectionNoEvents()     // Catch:{ all -> 0x0013 }
            goto L_0x0034
        L_0x0033:
            r8 = r5
        L_0x0034:
            okhttp3.internal.connection.RealConnection r4 = r6.connection     // Catch:{ all -> 0x0013 }
            if (r4 == 0) goto L_0x003d
            r4 = r5
            okhttp3.Connection r4 = (okhttp3.Connection) r4     // Catch:{ all -> 0x0013 }
            r0.element = r5     // Catch:{ all -> 0x0013 }
        L_0x003d:
            boolean r4 = r6.noMoreExchanges     // Catch:{ all -> 0x0013 }
            if (r4 == 0) goto L_0x0047
            okhttp3.internal.connection.Exchange r4 = r6.exchange     // Catch:{ all -> 0x0013 }
            if (r4 != 0) goto L_0x0047
            r4 = 1
            goto L_0x0048
        L_0x0047:
            r4 = 0
        L_0x0048:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0013 }
            monitor-exit(r1)
            if (r8 == 0) goto L_0x0050
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r8)
        L_0x0050:
            T r8 = r0.element
            okhttp3.Connection r8 = (okhttp3.Connection) r8
            if (r8 == 0) goto L_0x0066
            okhttp3.EventListener r8 = r6.eventListener
            okhttp3.Call r1 = r6.call
            T r0 = r0.element
            okhttp3.Connection r0 = (okhttp3.Connection) r0
            if (r0 != 0) goto L_0x0063
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0063:
            r8.connectionReleased(r1, r0)
        L_0x0066:
            if (r4 == 0) goto L_0x0085
            if (r7 == 0) goto L_0x006b
            r2 = 1
        L_0x006b:
            java.io.IOException r7 = r6.timeoutExit(r7)
            if (r2 == 0) goto L_0x007e
            okhttp3.EventListener r8 = r6.eventListener
            okhttp3.Call r0 = r6.call
            if (r7 != 0) goto L_0x007a
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x007a:
            r8.callFailed(r0, r7)
            goto L_0x0085
        L_0x007e:
            okhttp3.EventListener r8 = r6.eventListener
            okhttp3.Call r0 = r6.call
            r8.callEnd(r0)
        L_0x0085:
            return r7
        L_0x0086:
            java.lang.String r7 = "cannot release connection while it is in use"
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException     // Catch:{ all -> 0x0013 }
            java.lang.String r7 = r7.toString()     // Catch:{ all -> 0x0013 }
            r8.<init>(r7)     // Catch:{ all -> 0x0013 }
            java.lang.Throwable r8 = (java.lang.Throwable) r8     // Catch:{ all -> 0x0013 }
            throw r8     // Catch:{ all -> 0x0013 }
        L_0x0094:
            monitor-exit(r1)
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.Transmitter.maybeReleaseConnection(java.io.IOException, boolean):java.io.IOException");
    }

    public final boolean canRetry() {
        ExchangeFinder exchangeFinder2 = this.exchangeFinder;
        if (exchangeFinder2 == null) {
            Intrinsics.throwNpe();
        }
        if (exchangeFinder2.hasStreamFailure()) {
            ExchangeFinder exchangeFinder3 = this.exchangeFinder;
            if (exchangeFinder3 == null) {
                Intrinsics.throwNpe();
            }
            if (exchangeFinder3.hasRouteToTry()) {
                return true;
            }
        }
        return false;
    }

    public final boolean hasExchange() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.exchange != null;
        }
        return z;
    }

    public final void cancel() {
        Exchange exchange2;
        RealConnection realConnection;
        synchronized (this.connectionPool) {
            this.canceled = true;
            exchange2 = this.exchange;
            ExchangeFinder exchangeFinder2 = this.exchangeFinder;
            if (exchangeFinder2 == null || (realConnection = exchangeFinder2.connectingConnection()) == null) {
                realConnection = this.connection;
            }
            Unit unit = Unit.INSTANCE;
        }
        if (exchange2 != null) {
            exchange2.cancel();
        } else if (realConnection != null) {
            realConnection.cancel();
        }
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0002\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/Transmitter$TransmitterReference;", "Ljava/lang/ref/WeakReference;", "Lokhttp3/internal/connection/Transmitter;", "referent", "callStackTrace", "", "(Lokhttp3/internal/connection/Transmitter;Ljava/lang/Object;)V", "getCallStackTrace", "()Ljava/lang/Object;", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: Transmitter.kt */
    public static final class TransmitterReference extends WeakReference<Transmitter> {
        private final Object callStackTrace;

        public final Object getCallStackTrace() {
            return this.callStackTrace;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TransmitterReference(Transmitter transmitter, Object obj) {
            super(transmitter);
            Intrinsics.checkParameterIsNotNull(transmitter, "referent");
            this.callStackTrace = obj;
        }
    }
}
