package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.ref.Reference;
import java.net.Proxy;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.ConnectionPool;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Transmitter;
import okhttp3.internal.platform.Platform;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000i\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0003*\u0001\n\u0018\u0000 12\u00020\u0001:\u00011B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\bJ\u000e\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005J\u0016\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u000e\u0010\"\u001a\u00020\r2\u0006\u0010#\u001a\u00020\u0014J\u0006\u0010$\u001a\u00020\u0003J\u0006\u0010%\u001a\u00020\u001dJ\u0006\u0010&\u001a\u00020\u0003J\u0018\u0010'\u001a\u00020\u00032\u0006\u0010#\u001a\u00020\u00142\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u000e\u0010(\u001a\u00020\u001d2\u0006\u0010#\u001a\u00020\u0014J.\u0010)\u001a\u00020\r2\u0006\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-2\u000e\u0010.\u001a\n\u0012\u0004\u0012\u00020\u001f\u0018\u00010/2\u0006\u00100\u001a\u00020\rR\u0010\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0004\n\u0002\u0010\u000bR\u001a\u0010\f\u001a\u00020\rX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019¨\u00062"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool;", "", "maxIdleConnections", "", "keepAliveDuration", "", "timeUnit", "Ljava/util/concurrent/TimeUnit;", "(IJLjava/util/concurrent/TimeUnit;)V", "cleanupRunnable", "okhttp3/internal/connection/RealConnectionPool$cleanupRunnable$1", "Lokhttp3/internal/connection/RealConnectionPool$cleanupRunnable$1;", "cleanupRunning", "", "getCleanupRunning", "()Z", "setCleanupRunning", "(Z)V", "connections", "Ljava/util/ArrayDeque;", "Lokhttp3/internal/connection/RealConnection;", "keepAliveDurationNs", "routeDatabase", "Lokhttp3/internal/connection/RouteDatabase;", "getRouteDatabase", "()Lokhttp3/internal/connection/RouteDatabase;", "cleanup", "now", "connectFailed", "", "failedRoute", "Lokhttp3/Route;", "failure", "Ljava/io/IOException;", "connectionBecameIdle", "connection", "connectionCount", "evictAll", "idleConnectionCount", "pruneAndGetAllocationCount", "put", "transmitterAcquirePooledConnection", "address", "Lokhttp3/Address;", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "routes", "", "requireMultiplexed", "Companion", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: RealConnectionPool.kt */
public final class RealConnectionPool {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(0, Integer.MAX_VALUE, 60, TimeUnit.SECONDS, new SynchronousQueue(), Util.threadFactory("OkHttp ConnectionPool", true));
    private final RealConnectionPool$cleanupRunnable$1 cleanupRunnable = new RealConnectionPool$cleanupRunnable$1(this);
    private boolean cleanupRunning;
    private final ArrayDeque<RealConnection> connections = new ArrayDeque<>();
    private final long keepAliveDurationNs;
    private final int maxIdleConnections;
    private final RouteDatabase routeDatabase = new RouteDatabase();

    public RealConnectionPool(int i, long j, TimeUnit timeUnit) {
        Intrinsics.checkParameterIsNotNull(timeUnit, "timeUnit");
        this.maxIdleConnections = i;
        this.keepAliveDurationNs = timeUnit.toNanos(j);
        if (!(j > 0)) {
            throw new IllegalArgumentException(("keepAliveDuration <= 0: " + j).toString());
        }
    }

    public final RouteDatabase getRouteDatabase() {
        return this.routeDatabase;
    }

    public final boolean getCleanupRunning() {
        return this.cleanupRunning;
    }

    public final void setCleanupRunning(boolean z) {
        this.cleanupRunning = z;
    }

    public final synchronized int idleConnectionCount() {
        int i;
        Iterable<RealConnection> iterable = this.connections;
        i = 0;
        if (!(iterable instanceof Collection) || !((Collection) iterable).isEmpty()) {
            for (RealConnection transmitters : iterable) {
                if (transmitters.getTransmitters().isEmpty() && (i = i + 1) < 0) {
                    CollectionsKt.throwCountOverflow();
                }
            }
        }
        return i;
    }

    public final synchronized int connectionCount() {
        return this.connections.size();
    }

    public final boolean transmitterAcquirePooledConnection(Address address, Transmitter transmitter, List<Route> list, boolean z) {
        Intrinsics.checkParameterIsNotNull(address, "address");
        Intrinsics.checkParameterIsNotNull(transmitter, "transmitter");
        Thread.holdsLock(this);
        Iterator<RealConnection> it = this.connections.iterator();
        while (it.hasNext()) {
            RealConnection next = it.next();
            if ((!z || next.isMultiplexed()) && next.isEligible$okhttp(address, list)) {
                Intrinsics.checkExpressionValueIsNotNull(next, "connection");
                transmitter.acquireConnectionNoEvents(next);
                return true;
            }
        }
        return false;
    }

    public final void put(RealConnection realConnection) {
        Intrinsics.checkParameterIsNotNull(realConnection, "connection");
        Thread.holdsLock(this);
        if (!this.cleanupRunning) {
            this.cleanupRunning = true;
            executor.execute(this.cleanupRunnable);
        }
        this.connections.add(realConnection);
    }

    public final boolean connectionBecameIdle(RealConnection realConnection) {
        Intrinsics.checkParameterIsNotNull(realConnection, "connection");
        Thread.holdsLock(this);
        if (realConnection.getNoNewExchanges() || this.maxIdleConnections == 0) {
            this.connections.remove(realConnection);
            return true;
        }
        notifyAll();
        return false;
    }

    public final void evictAll() {
        List<RealConnection> arrayList = new ArrayList<>();
        synchronized (this) {
            Iterator<RealConnection> it = this.connections.iterator();
            Intrinsics.checkExpressionValueIsNotNull(it, "connections.iterator()");
            while (it.hasNext()) {
                RealConnection next = it.next();
                if (next.getTransmitters().isEmpty()) {
                    next.setNoNewExchanges(true);
                    Intrinsics.checkExpressionValueIsNotNull(next, "connection");
                    arrayList.add(next);
                    it.remove();
                }
            }
            Unit unit = Unit.INSTANCE;
        }
        for (RealConnection socket : arrayList) {
            Util.closeQuietly(socket.socket());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0059, code lost:
        if (r0 != null) goto L_0x005e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x005b, code lost:
        kotlin.jvm.internal.Intrinsics.throwNpe();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x005e, code lost:
        okhttp3.internal.Util.closeQuietly(r0.socket());
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x0067, code lost:
        return 0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final long cleanup(long r12) {
        /*
            r11 = this;
            r0 = 0
            r1 = r0
            okhttp3.internal.connection.RealConnection r1 = (okhttp3.internal.connection.RealConnection) r1
            monitor-enter(r11)
            java.util.ArrayDeque<okhttp3.internal.connection.RealConnection> r1 = r11.connections     // Catch:{ all -> 0x0068 }
            java.util.Iterator r1 = r1.iterator()     // Catch:{ all -> 0x0068 }
            r2 = 0
            r3 = -9223372036854775808
            r5 = 0
            r6 = 0
        L_0x0010:
            boolean r7 = r1.hasNext()     // Catch:{ all -> 0x0068 }
            if (r7 == 0) goto L_0x0039
            java.lang.Object r7 = r1.next()     // Catch:{ all -> 0x0068 }
            okhttp3.internal.connection.RealConnection r7 = (okhttp3.internal.connection.RealConnection) r7     // Catch:{ all -> 0x0068 }
            java.lang.String r8 = "connection"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r8)     // Catch:{ all -> 0x0068 }
            int r8 = r11.pruneAndGetAllocationCount(r7, r12)     // Catch:{ all -> 0x0068 }
            if (r8 <= 0) goto L_0x002a
            int r6 = r6 + 1
            goto L_0x0010
        L_0x002a:
            int r5 = r5 + 1
            long r8 = r7.getIdleAtNanos$okhttp()     // Catch:{ all -> 0x0068 }
            long r8 = r12 - r8
            int r10 = (r8 > r3 ? 1 : (r8 == r3 ? 0 : -1))
            if (r10 <= 0) goto L_0x0010
            r0 = r7
            r3 = r8
            goto L_0x0010
        L_0x0039:
            long r12 = r11.keepAliveDurationNs     // Catch:{ all -> 0x0068 }
            int r1 = (r3 > r12 ? 1 : (r3 == r12 ? 0 : -1))
            if (r1 >= 0) goto L_0x0053
            int r1 = r11.maxIdleConnections     // Catch:{ all -> 0x0068 }
            if (r5 <= r1) goto L_0x0044
            goto L_0x0053
        L_0x0044:
            if (r5 <= 0) goto L_0x0049
            long r12 = r12 - r3
            monitor-exit(r11)
            return r12
        L_0x0049:
            if (r6 <= 0) goto L_0x004d
            monitor-exit(r11)
            return r12
        L_0x004d:
            r11.cleanupRunning = r2     // Catch:{ all -> 0x0068 }
            monitor-exit(r11)
            r12 = -1
            return r12
        L_0x0053:
            java.util.ArrayDeque<okhttp3.internal.connection.RealConnection> r12 = r11.connections     // Catch:{ all -> 0x0068 }
            r12.remove(r0)     // Catch:{ all -> 0x0068 }
            monitor-exit(r11)
            if (r0 != 0) goto L_0x005e
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x005e:
            java.net.Socket r12 = r0.socket()
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r12)
            r12 = 0
            return r12
        L_0x0068:
            r12 = move-exception
            monitor-exit(r11)
            goto L_0x006c
        L_0x006b:
            throw r12
        L_0x006c:
            goto L_0x006b
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.RealConnectionPool.cleanup(long):long");
    }

    private final int pruneAndGetAllocationCount(RealConnection realConnection, long j) {
        List<Reference<Transmitter>> transmitters = realConnection.getTransmitters();
        int i = 0;
        while (i < transmitters.size()) {
            Reference reference = transmitters.get(i);
            if (reference.get() != null) {
                i++;
            } else if (reference != null) {
                Platform.Companion.get().logCloseableLeak("A connection to " + realConnection.route().address().url() + " was leaked. Did you forget to close a response body?", ((Transmitter.TransmitterReference) reference).getCallStackTrace());
                transmitters.remove(i);
                realConnection.setNoNewExchanges(true);
                if (transmitters.isEmpty()) {
                    realConnection.setIdleAtNanos$okhttp(j - this.keepAliveDurationNs);
                    return 0;
                }
            } else {
                throw new TypeCastException("null cannot be cast to non-null type okhttp3.internal.connection.Transmitter.TransmitterReference");
            }
        }
        return transmitters.size();
    }

    public final void connectFailed(Route route, IOException iOException) {
        Intrinsics.checkParameterIsNotNull(route, "failedRoute");
        Intrinsics.checkParameterIsNotNull(iOException, "failure");
        if (route.proxy().type() != Proxy.Type.DIRECT) {
            Address address = route.address();
            address.proxySelector().connectFailed(address.url().uri(), route.proxy().address(), iOException);
        }
        this.routeDatabase.failed(route);
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bR\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lokhttp3/internal/connection/RealConnectionPool$Companion;", "", "()V", "executor", "Ljava/util/concurrent/ThreadPoolExecutor;", "get", "Lokhttp3/internal/connection/RealConnectionPool;", "connectionPool", "Lokhttp3/ConnectionPool;", "okhttp"}, k = 1, mv = {1, 1, 15})
    /* compiled from: RealConnectionPool.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final RealConnectionPool get(ConnectionPool connectionPool) {
            Intrinsics.checkParameterIsNotNull(connectionPool, "connectionPool");
            return connectionPool.getDelegate$okhttp();
        }
    }
}
