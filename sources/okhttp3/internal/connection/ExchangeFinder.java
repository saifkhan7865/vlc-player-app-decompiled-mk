package okhttp3.internal.connection;

import androidx.core.app.NotificationCompat;
import java.io.IOException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.Address;
import okhttp3.Call;
import okhttp3.EventListener;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Route;
import okhttp3.internal.Util;
import okhttp3.internal.connection.RouteSelector;
import okhttp3.internal.http.ExchangeCodec;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0002\u0010\fJ\b\u0010\r\u001a\u0004\u0018\u00010\u000eJ\u001e\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u0010J0\u0010\u001e\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0010H\u0002J8\u0010%\u001a\u00020\u000e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020 2\u0006\u0010\"\u001a\u00020 2\u0006\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u00102\u0006\u0010\u001d\u001a\u00020\u0010H\u0002J\u0006\u0010&\u001a\u00020\u0010J\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010'\u001a\u00020\u0010H\u0002J\u0006\u0010(\u001a\u00020)R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\r\u001a\u0004\u0018\u00010\u000eX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0013\u001a\u0004\u0018\u00010\u0014X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0016X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"Lokhttp3/internal/connection/ExchangeFinder;", "", "transmitter", "Lokhttp3/internal/connection/Transmitter;", "connectionPool", "Lokhttp3/internal/connection/RealConnectionPool;", "address", "Lokhttp3/Address;", "call", "Lokhttp3/Call;", "eventListener", "Lokhttp3/EventListener;", "(Lokhttp3/internal/connection/Transmitter;Lokhttp3/internal/connection/RealConnectionPool;Lokhttp3/Address;Lokhttp3/Call;Lokhttp3/EventListener;)V", "connectingConnection", "Lokhttp3/internal/connection/RealConnection;", "hasStreamFailure", "", "nextRouteToTry", "Lokhttp3/Route;", "routeSelection", "Lokhttp3/internal/connection/RouteSelector$Selection;", "routeSelector", "Lokhttp3/internal/connection/RouteSelector;", "find", "Lokhttp3/internal/http/ExchangeCodec;", "client", "Lokhttp3/OkHttpClient;", "chain", "Lokhttp3/Interceptor$Chain;", "doExtensiveHealthChecks", "findConnection", "connectTimeout", "", "readTimeout", "writeTimeout", "pingIntervalMillis", "connectionRetryEnabled", "findHealthyConnection", "hasRouteToTry", "retryCurrentRoute", "trackFailure", "", "okhttp"}, k = 1, mv = {1, 1, 15})
/* compiled from: ExchangeFinder.kt */
public final class ExchangeFinder {
    private final Address address;
    private final Call call;
    private RealConnection connectingConnection;
    private final RealConnectionPool connectionPool;
    private final EventListener eventListener;
    private boolean hasStreamFailure;
    private Route nextRouteToTry;
    private RouteSelector.Selection routeSelection;
    private final RouteSelector routeSelector;
    private final Transmitter transmitter;

    public ExchangeFinder(Transmitter transmitter2, RealConnectionPool realConnectionPool, Address address2, Call call2, EventListener eventListener2) {
        Intrinsics.checkParameterIsNotNull(transmitter2, "transmitter");
        Intrinsics.checkParameterIsNotNull(realConnectionPool, "connectionPool");
        Intrinsics.checkParameterIsNotNull(address2, "address");
        Intrinsics.checkParameterIsNotNull(call2, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkParameterIsNotNull(eventListener2, "eventListener");
        this.transmitter = transmitter2;
        this.connectionPool = realConnectionPool;
        this.address = address2;
        this.call = call2;
        this.eventListener = eventListener2;
        this.routeSelector = new RouteSelector(address2, realConnectionPool.getRouteDatabase(), call2, eventListener2);
    }

    public final ExchangeCodec find(OkHttpClient okHttpClient, Interceptor.Chain chain, boolean z) {
        Intrinsics.checkParameterIsNotNull(okHttpClient, "client");
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        try {
            return findHealthyConnection(chain.connectTimeoutMillis(), chain.readTimeoutMillis(), chain.writeTimeoutMillis(), okHttpClient.pingIntervalMillis(), okHttpClient.retryOnConnectionFailure(), z).newCodec$okhttp(okHttpClient, chain);
        } catch (RouteException e) {
            trackFailure();
            throw e;
        } catch (IOException e2) {
            trackFailure();
            throw new RouteException(e2);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x0016, code lost:
        if (r0.isHealthy(r9) != false) goto L_0x001c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x001c, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final okhttp3.internal.connection.RealConnection findHealthyConnection(int r4, int r5, int r6, int r7, boolean r8, boolean r9) throws java.io.IOException {
        /*
            r3 = this;
        L_0x0000:
            okhttp3.internal.connection.RealConnection r0 = r3.findConnection(r4, r5, r6, r7, r8)
            okhttp3.internal.connection.RealConnectionPool r1 = r3.connectionPool
            monitor-enter(r1)
            int r2 = r0.getSuccessCount$okhttp()     // Catch:{ all -> 0x001d }
            if (r2 != 0) goto L_0x000f
            monitor-exit(r1)
            return r0
        L_0x000f:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x001d }
            monitor-exit(r1)
            boolean r1 = r0.isHealthy(r9)
            if (r1 != 0) goto L_0x001c
            r0.noNewExchanges()
            goto L_0x0000
        L_0x001c:
            return r0
        L_0x001d:
            r4 = move-exception
            monitor-exit(r1)
            goto L_0x0021
        L_0x0020:
            throw r4
        L_0x0021:
            goto L_0x0020
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.findHealthyConnection(int, int, int, int, boolean, boolean):okhttp3.internal.connection.RealConnection");
    }

    /*  JADX ERROR: IndexOutOfBoundsException in pass: RegionMakerVisitor
        java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
        	at java.util.ArrayList.rangeCheck(ArrayList.java:659)
        	at java.util.ArrayList.get(ArrayList.java:435)
        	at jadx.core.dex.nodes.InsnNode.getArg(InsnNode.java:101)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:611)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverseMonitorExits(RegionMaker.java:619)
        	at jadx.core.dex.visitors.regions.RegionMaker.processMonitorEnter(RegionMaker.java:561)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:133)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMaker.processIf(RegionMaker.java:698)
        	at jadx.core.dex.visitors.regions.RegionMaker.traverse(RegionMaker.java:123)
        	at jadx.core.dex.visitors.regions.RegionMaker.makeRegion(RegionMaker.java:86)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:49)
        */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x005d  */
    private final okhttp3.internal.connection.RealConnection findConnection(int r19, int r20, int r21, int r22, boolean r23) throws java.io.IOException {
        /*
            r18 = this;
            r1 = r18
            r0 = 0
            r2 = r0
            okhttp3.internal.connection.RealConnection r2 = (okhttp3.internal.connection.RealConnection) r2
            r2 = r0
            okhttp3.Route r2 = (okhttp3.Route) r2
            kotlin.jvm.internal.Ref$ObjectRef r2 = new kotlin.jvm.internal.Ref$ObjectRef
            r2.<init>()
            okhttp3.internal.connection.RealConnectionPool r3 = r1.connectionPool
            monitor-enter(r3)
            okhttp3.internal.connection.Transmitter r4 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            boolean r4 = r4.isCanceled()     // Catch:{ all -> 0x01f9 }
            if (r4 != 0) goto L_0x01ef
            r4 = 0
            r1.hasStreamFailure = r4     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.Transmitter r5 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r5 = r5.getConnection()     // Catch:{ all -> 0x01f9 }
            r2.element = r5     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.Transmitter r5 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r5 = r5.getConnection()     // Catch:{ all -> 0x01f9 }
            if (r5 == 0) goto L_0x0044
            okhttp3.internal.connection.Transmitter r5 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r5 = r5.getConnection()     // Catch:{ all -> 0x01f9 }
            if (r5 != 0) goto L_0x0037
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01f9 }
        L_0x0037:
            boolean r5 = r5.getNoNewExchanges()     // Catch:{ all -> 0x01f9 }
            if (r5 == 0) goto L_0x0044
            okhttp3.internal.connection.Transmitter r5 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            java.net.Socket r5 = r5.releaseConnectionNoEvents()     // Catch:{ all -> 0x01f9 }
            goto L_0x0045
        L_0x0044:
            r5 = r0
        L_0x0045:
            okhttp3.internal.connection.Transmitter r6 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r6 = r6.getConnection()     // Catch:{ all -> 0x01f9 }
            if (r6 == 0) goto L_0x0059
            okhttp3.internal.connection.Transmitter r6 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r6 = r6.getConnection()     // Catch:{ all -> 0x01f9 }
            r7 = r0
            okhttp3.internal.connection.RealConnection r7 = (okhttp3.internal.connection.RealConnection) r7     // Catch:{ all -> 0x01f9 }
            r2.element = r0     // Catch:{ all -> 0x01f9 }
            goto L_0x005a
        L_0x0059:
            r6 = r0
        L_0x005a:
            r7 = 1
            if (r6 != 0) goto L_0x0092
            okhttp3.internal.connection.RealConnectionPool r8 = r1.connectionPool     // Catch:{ all -> 0x01f9 }
            okhttp3.Address r9 = r1.address     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.Transmitter r10 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            boolean r8 = r8.transmitterAcquirePooledConnection(r9, r10, r0, r4)     // Catch:{ all -> 0x01f9 }
            if (r8 == 0) goto L_0x0072
            okhttp3.internal.connection.Transmitter r6 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r6 = r6.getConnection()     // Catch:{ all -> 0x01f9 }
            r8 = r0
            r9 = 1
            goto L_0x0094
        L_0x0072:
            okhttp3.Route r8 = r1.nextRouteToTry     // Catch:{ all -> 0x01f9 }
            if (r8 == 0) goto L_0x007c
            r9 = r0
            okhttp3.Route r9 = (okhttp3.Route) r9     // Catch:{ all -> 0x01f9 }
            r1.nextRouteToTry = r0     // Catch:{ all -> 0x01f9 }
            goto L_0x0093
        L_0x007c:
            boolean r8 = r18.retryCurrentRoute()     // Catch:{ all -> 0x01f9 }
            if (r8 == 0) goto L_0x0092
            okhttp3.internal.connection.Transmitter r8 = r1.transmitter     // Catch:{ all -> 0x01f9 }
            okhttp3.internal.connection.RealConnection r8 = r8.getConnection()     // Catch:{ all -> 0x01f9 }
            if (r8 != 0) goto L_0x008d
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01f9 }
        L_0x008d:
            okhttp3.Route r8 = r8.route()     // Catch:{ all -> 0x01f9 }
            goto L_0x0093
        L_0x0092:
            r8 = r0
        L_0x0093:
            r9 = 0
        L_0x0094:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01f9 }
            monitor-exit(r3)
            if (r5 == 0) goto L_0x009c
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r5)
        L_0x009c:
            T r3 = r2.element
            okhttp3.internal.connection.RealConnection r3 = (okhttp3.internal.connection.RealConnection) r3
            if (r3 == 0) goto L_0x00b4
            okhttp3.EventListener r3 = r1.eventListener
            okhttp3.Call r5 = r1.call
            T r2 = r2.element
            okhttp3.internal.connection.RealConnection r2 = (okhttp3.internal.connection.RealConnection) r2
            if (r2 != 0) goto L_0x00af
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00af:
            okhttp3.Connection r2 = (okhttp3.Connection) r2
            r3.connectionReleased(r5, r2)
        L_0x00b4:
            if (r9 == 0) goto L_0x00c5
            okhttp3.EventListener r2 = r1.eventListener
            okhttp3.Call r3 = r1.call
            if (r6 != 0) goto L_0x00bf
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00bf:
            r5 = r6
            okhttp3.Connection r5 = (okhttp3.Connection) r5
            r2.connectionAcquired(r3, r5)
        L_0x00c5:
            if (r6 == 0) goto L_0x00cd
            if (r6 != 0) goto L_0x00cc
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00cc:
            return r6
        L_0x00cd:
            if (r8 != 0) goto L_0x00e8
            okhttp3.internal.connection.RouteSelector$Selection r2 = r1.routeSelection
            if (r2 == 0) goto L_0x00de
            if (r2 != 0) goto L_0x00d8
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x00d8:
            boolean r2 = r2.hasNext()
            if (r2 != 0) goto L_0x00e8
        L_0x00de:
            okhttp3.internal.connection.RouteSelector r2 = r1.routeSelector
            okhttp3.internal.connection.RouteSelector$Selection r2 = r2.next()
            r1.routeSelection = r2
            r2 = 1
            goto L_0x00e9
        L_0x00e8:
            r2 = 0
        L_0x00e9:
            r3 = r0
            java.util.List r3 = (java.util.List) r3
            okhttp3.internal.connection.RealConnectionPool r3 = r1.connectionPool
            monitor-enter(r3)
            okhttp3.internal.connection.Transmitter r5 = r1.transmitter     // Catch:{ all -> 0x01ec }
            boolean r5 = r5.isCanceled()     // Catch:{ all -> 0x01ec }
            if (r5 != 0) goto L_0x01e2
            if (r2 == 0) goto L_0x0118
            okhttp3.internal.connection.RouteSelector$Selection r2 = r1.routeSelection     // Catch:{ all -> 0x01ec }
            if (r2 != 0) goto L_0x0100
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ec }
        L_0x0100:
            java.util.List r2 = r2.getRoutes()     // Catch:{ all -> 0x01ec }
            okhttp3.internal.connection.RealConnectionPool r5 = r1.connectionPool     // Catch:{ all -> 0x01ec }
            okhttp3.Address r10 = r1.address     // Catch:{ all -> 0x01ec }
            okhttp3.internal.connection.Transmitter r11 = r1.transmitter     // Catch:{ all -> 0x01ec }
            boolean r4 = r5.transmitterAcquirePooledConnection(r10, r11, r2, r4)     // Catch:{ all -> 0x01ec }
            if (r4 == 0) goto L_0x0119
            okhttp3.internal.connection.Transmitter r4 = r1.transmitter     // Catch:{ all -> 0x01ec }
            okhttp3.internal.connection.RealConnection r6 = r4.getConnection()     // Catch:{ all -> 0x01ec }
            r9 = 1
            goto L_0x0119
        L_0x0118:
            r2 = r0
        L_0x0119:
            if (r9 != 0) goto L_0x0136
            if (r8 != 0) goto L_0x0128
            okhttp3.internal.connection.RouteSelector$Selection r4 = r1.routeSelection     // Catch:{ all -> 0x01ec }
            if (r4 != 0) goto L_0x0124
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ec }
        L_0x0124:
            okhttp3.Route r8 = r4.next()     // Catch:{ all -> 0x01ec }
        L_0x0128:
            okhttp3.internal.connection.RealConnection r6 = new okhttp3.internal.connection.RealConnection     // Catch:{ all -> 0x01ec }
            okhttp3.internal.connection.RealConnectionPool r4 = r1.connectionPool     // Catch:{ all -> 0x01ec }
            if (r8 != 0) goto L_0x0131
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01ec }
        L_0x0131:
            r6.<init>(r4, r8)     // Catch:{ all -> 0x01ec }
            r1.connectingConnection = r6     // Catch:{ all -> 0x01ec }
        L_0x0136:
            kotlin.Unit r4 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01ec }
            monitor-exit(r3)
            if (r9 == 0) goto L_0x0150
            okhttp3.EventListener r0 = r1.eventListener
            okhttp3.Call r2 = r1.call
            if (r6 != 0) goto L_0x0144
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0144:
            r3 = r6
            okhttp3.Connection r3 = (okhttp3.Connection) r3
            r0.connectionAcquired(r2, r3)
            if (r6 != 0) goto L_0x014f
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x014f:
            return r6
        L_0x0150:
            if (r6 != 0) goto L_0x0155
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0155:
            okhttp3.Call r3 = r1.call
            okhttp3.EventListener r4 = r1.eventListener
            r10 = r6
            r11 = r19
            r12 = r20
            r13 = r21
            r14 = r22
            r15 = r23
            r16 = r3
            r17 = r4
            r10.connect(r11, r12, r13, r14, r15, r16, r17)
            okhttp3.internal.connection.RealConnectionPool r3 = r1.connectionPool
            okhttp3.internal.connection.RouteDatabase r3 = r3.getRouteDatabase()
            if (r6 != 0) goto L_0x0176
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x0176:
            okhttp3.Route r4 = r6.route()
            r3.connected(r4)
            r3 = r0
            java.net.Socket r3 = (java.net.Socket) r3
            okhttp3.internal.connection.RealConnectionPool r3 = r1.connectionPool
            monitor-enter(r3)
            r4 = r0
            okhttp3.internal.connection.RealConnection r4 = (okhttp3.internal.connection.RealConnection) r4     // Catch:{ all -> 0x01df }
            r1.connectingConnection = r0     // Catch:{ all -> 0x01df }
            okhttp3.internal.connection.RealConnectionPool r4 = r1.connectionPool     // Catch:{ all -> 0x01df }
            okhttp3.Address r5 = r1.address     // Catch:{ all -> 0x01df }
            okhttp3.internal.connection.Transmitter r9 = r1.transmitter     // Catch:{ all -> 0x01df }
            boolean r2 = r4.transmitterAcquirePooledConnection(r5, r9, r2, r7)     // Catch:{ all -> 0x01df }
            if (r2 == 0) goto L_0x01ae
            if (r6 != 0) goto L_0x0199
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01df }
        L_0x0199:
            r6.setNoNewExchanges(r7)     // Catch:{ all -> 0x01df }
            if (r6 != 0) goto L_0x01a1
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01df }
        L_0x01a1:
            java.net.Socket r0 = r6.socket()     // Catch:{ all -> 0x01df }
            okhttp3.internal.connection.Transmitter r2 = r1.transmitter     // Catch:{ all -> 0x01df }
            okhttp3.internal.connection.RealConnection r6 = r2.getConnection()     // Catch:{ all -> 0x01df }
            r1.nextRouteToTry = r8     // Catch:{ all -> 0x01df }
            goto L_0x01c2
        L_0x01ae:
            okhttp3.internal.connection.RealConnectionPool r2 = r1.connectionPool     // Catch:{ all -> 0x01df }
            if (r6 != 0) goto L_0x01b5
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01df }
        L_0x01b5:
            r2.put(r6)     // Catch:{ all -> 0x01df }
            okhttp3.internal.connection.Transmitter r2 = r1.transmitter     // Catch:{ all -> 0x01df }
            if (r6 != 0) goto L_0x01bf
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x01df }
        L_0x01bf:
            r2.acquireConnectionNoEvents(r6)     // Catch:{ all -> 0x01df }
        L_0x01c2:
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x01df }
            monitor-exit(r3)
            if (r0 == 0) goto L_0x01ca
            okhttp3.internal.Util.closeQuietly((java.net.Socket) r0)
        L_0x01ca:
            okhttp3.EventListener r0 = r1.eventListener
            okhttp3.Call r2 = r1.call
            if (r6 != 0) goto L_0x01d3
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x01d3:
            r3 = r6
            okhttp3.Connection r3 = (okhttp3.Connection) r3
            r0.connectionAcquired(r2, r3)
            if (r6 != 0) goto L_0x01de
            kotlin.jvm.internal.Intrinsics.throwNpe()
        L_0x01de:
            return r6
        L_0x01df:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x01e2:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01ec }
            java.lang.String r2 = "Canceled"
            r0.<init>(r2)     // Catch:{ all -> 0x01ec }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01ec }
            throw r0     // Catch:{ all -> 0x01ec }
        L_0x01ec:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        L_0x01ef:
            java.io.IOException r0 = new java.io.IOException     // Catch:{ all -> 0x01f9 }
            java.lang.String r2 = "Canceled"
            r0.<init>(r2)     // Catch:{ all -> 0x01f9 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01f9 }
            throw r0     // Catch:{ all -> 0x01f9 }
        L_0x01f9:
            r0 = move-exception
            monitor-exit(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.findConnection(int, int, int, int, boolean):okhttp3.internal.connection.RealConnection");
    }

    public final RealConnection connectingConnection() {
        Thread.holdsLock(this.connectionPool);
        return this.connectingConnection;
    }

    public final void trackFailure() {
        Thread.holdsLock(this.connectionPool);
        synchronized (this.connectionPool) {
            this.hasStreamFailure = true;
            Unit unit = Unit.INSTANCE;
        }
    }

    public final boolean hasStreamFailure() {
        boolean z;
        synchronized (this.connectionPool) {
            z = this.hasStreamFailure;
        }
        return z;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0038, code lost:
        return r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final boolean hasRouteToTry() {
        /*
            r3 = this;
            okhttp3.internal.connection.RealConnectionPool r0 = r3.connectionPool
            monitor-enter(r0)
            okhttp3.Route r1 = r3.nextRouteToTry     // Catch:{ all -> 0x0039 }
            r2 = 1
            if (r1 == 0) goto L_0x000a
            monitor-exit(r0)
            return r2
        L_0x000a:
            boolean r1 = r3.retryCurrentRoute()     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0023
            okhttp3.internal.connection.Transmitter r1 = r3.transmitter     // Catch:{ all -> 0x0039 }
            okhttp3.internal.connection.RealConnection r1 = r1.getConnection()     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x001b
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x0039 }
        L_0x001b:
            okhttp3.Route r1 = r1.route()     // Catch:{ all -> 0x0039 }
            r3.nextRouteToTry = r1     // Catch:{ all -> 0x0039 }
            monitor-exit(r0)
            return r2
        L_0x0023:
            okhttp3.internal.connection.RouteSelector$Selection r1 = r3.routeSelection     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x002d
            boolean r1 = r1.hasNext()     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x0037
        L_0x002d:
            okhttp3.internal.connection.RouteSelector r1 = r3.routeSelector     // Catch:{ all -> 0x0039 }
            boolean r1 = r1.hasNext()     // Catch:{ all -> 0x0039 }
            if (r1 == 0) goto L_0x0036
            goto L_0x0037
        L_0x0036:
            r2 = 0
        L_0x0037:
            monitor-exit(r0)
            return r2
        L_0x0039:
            r1 = move-exception
            monitor-exit(r0)
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: okhttp3.internal.connection.ExchangeFinder.hasRouteToTry():boolean");
    }

    private final boolean retryCurrentRoute() {
        if (this.transmitter.getConnection() != null) {
            RealConnection connection = this.transmitter.getConnection();
            if (connection == null) {
                Intrinsics.throwNpe();
            }
            if (connection.getRouteFailureCount$okhttp() == 0) {
                RealConnection connection2 = this.transmitter.getConnection();
                if (connection2 == null) {
                    Intrinsics.throwNpe();
                }
                if (Util.canReuseConnectionFor(connection2.route().address().url(), this.address.url())) {
                    return true;
                }
            }
        }
        return false;
    }
}
