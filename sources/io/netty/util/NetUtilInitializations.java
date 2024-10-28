package io.netty.util;

import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Enumeration;

final class NetUtilInitializations {
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) NetUtilInitializations.class);

    private NetUtilInitializations() {
    }

    static Inet4Address createLocalhost4() {
        try {
            return (Inet4Address) InetAddress.getByAddress("localhost", new byte[]{Byte.MAX_VALUE, 0, 0, 1});
        } catch (Exception e) {
            PlatformDependent.throwException(e);
            return null;
        }
    }

    static Inet6Address createLocalhost6() {
        try {
            return (Inet6Address) InetAddress.getByAddress("localhost", new byte[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1});
        } catch (Exception e) {
            PlatformDependent.throwException(e);
            return null;
        }
    }

    static Collection<NetworkInterface> networkInterfaces() {
        ArrayList arrayList = new ArrayList();
        try {
            Enumeration<NetworkInterface> networkInterfaces = NetworkInterface.getNetworkInterfaces();
            if (networkInterfaces != null) {
                while (networkInterfaces.hasMoreElements()) {
                    arrayList.add(networkInterfaces.nextElement());
                }
            }
        } catch (SocketException e) {
            logger.warn("Failed to retrieve the list of available network interfaces", (Throwable) e);
        } catch (NullPointerException e2) {
            if (!PlatformDependent.isAndroid()) {
                throw e2;
            }
        }
        return Collections.unmodifiableList(arrayList);
    }

    /* JADX WARNING: type inference failed for: r9v0, types: [java.net.InetAddress, java.net.Inet6Address, java.lang.Object] */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x0078, code lost:
        r5 = r4.nextElement();
        r3 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x00ce, code lost:
        if (r5 != null) goto L_0x00d6;
     */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.net.Inet4Address, code=java.net.InetAddress, for r8v0, types: [java.lang.Object, java.net.Inet4Address] */
    /* JADX WARNING: Incorrect type for immutable var: ssa=java.net.Inet6Address, code=java.net.InetAddress, for r9v0, types: [java.net.InetAddress, java.net.Inet6Address, java.lang.Object] */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x008e  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00ae  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static io.netty.util.NetUtilInitializations.NetworkIfaceAndInetAddress determineLoopback(java.util.Collection<java.net.NetworkInterface> r7, java.net.InetAddress r8, java.net.InetAddress r9) {
        /*
            java.lang.String r0 = "Failed to find the loopback interface"
            java.lang.String r1 = "Using hard-coded IPv4 localhost address: {}"
            java.util.ArrayList r2 = new java.util.ArrayList
            r2.<init>()
            java.util.Iterator r7 = r7.iterator()
        L_0x000d:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L_0x0027
            java.lang.Object r3 = r7.next()
            java.net.NetworkInterface r3 = (java.net.NetworkInterface) r3
            java.util.Enumeration r4 = io.netty.util.internal.SocketUtils.addressesFromNetworkInterface(r3)
            boolean r4 = r4.hasMoreElements()
            if (r4 == 0) goto L_0x000d
            r2.add(r3)
            goto L_0x000d
        L_0x0027:
            java.util.Iterator r7 = r2.iterator()
        L_0x002b:
            boolean r3 = r7.hasNext()
            if (r3 == 0) goto L_0x004e
            java.lang.Object r3 = r7.next()
            java.net.NetworkInterface r3 = (java.net.NetworkInterface) r3
            java.util.Enumeration r4 = io.netty.util.internal.SocketUtils.addressesFromNetworkInterface(r3)
        L_0x003b:
            boolean r5 = r4.hasMoreElements()
            if (r5 == 0) goto L_0x002b
            java.lang.Object r5 = r4.nextElement()
            java.net.InetAddress r5 = (java.net.InetAddress) r5
            boolean r6 = r5.isLoopbackAddress()
            if (r6 == 0) goto L_0x003b
            goto L_0x0050
        L_0x004e:
            r3 = 0
            r5 = r3
        L_0x0050:
            if (r3 != 0) goto L_0x008c
            java.util.Iterator r7 = r2.iterator()     // Catch:{ SocketException -> 0x0086 }
        L_0x0056:
            boolean r2 = r7.hasNext()     // Catch:{ SocketException -> 0x0086 }
            if (r2 == 0) goto L_0x007e
            java.lang.Object r2 = r7.next()     // Catch:{ SocketException -> 0x0086 }
            java.net.NetworkInterface r2 = (java.net.NetworkInterface) r2     // Catch:{ SocketException -> 0x0086 }
            boolean r4 = r2.isLoopback()     // Catch:{ SocketException -> 0x0086 }
            if (r4 == 0) goto L_0x0056
            java.util.Enumeration r4 = io.netty.util.internal.SocketUtils.addressesFromNetworkInterface(r2)     // Catch:{ SocketException -> 0x0086 }
            boolean r6 = r4.hasMoreElements()     // Catch:{ SocketException -> 0x0086 }
            if (r6 == 0) goto L_0x0056
            java.lang.Object r7 = r4.nextElement()     // Catch:{ SocketException -> 0x007b }
            java.net.InetAddress r7 = (java.net.InetAddress) r7     // Catch:{ SocketException -> 0x007b }
            r5 = r7
            r3 = r2
            goto L_0x007e
        L_0x007b:
            r7 = move-exception
            r3 = r2
            goto L_0x0087
        L_0x007e:
            if (r3 != 0) goto L_0x008c
            io.netty.util.internal.logging.InternalLogger r7 = logger     // Catch:{ SocketException -> 0x0086 }
            r7.warn((java.lang.String) r0)     // Catch:{ SocketException -> 0x0086 }
            goto L_0x008c
        L_0x0086:
            r7 = move-exception
        L_0x0087:
            io.netty.util.internal.logging.InternalLogger r2 = logger
            r2.warn((java.lang.String) r0, (java.lang.Throwable) r7)
        L_0x008c:
            if (r3 == 0) goto L_0x00ae
            io.netty.util.internal.logging.InternalLogger r7 = logger
            java.lang.String r8 = r3.getName()
            java.lang.String r9 = r3.getDisplayName()
            java.lang.String r0 = r5.getHostAddress()
            r1 = 3
            java.lang.Object[] r1 = new java.lang.Object[r1]
            r2 = 0
            r1[r2] = r8
            r8 = 1
            r1[r8] = r9
            r8 = 2
            r1[r8] = r0
            java.lang.String r8 = "Loopback interface: {} ({}, {})"
            r7.debug((java.lang.String) r8, (java.lang.Object[]) r1)
            goto L_0x00d6
        L_0x00ae:
            if (r5 != 0) goto L_0x00d6
            java.net.NetworkInterface r7 = java.net.NetworkInterface.getByInetAddress(r9)     // Catch:{ Exception -> 0x00cd, all -> 0x00c4 }
            if (r7 == 0) goto L_0x00be
            io.netty.util.internal.logging.InternalLogger r7 = logger     // Catch:{ Exception -> 0x00cd, all -> 0x00c4 }
            java.lang.String r0 = "Using hard-coded IPv6 localhost address: {}"
            r7.debug((java.lang.String) r0, (java.lang.Object) r9)     // Catch:{ Exception -> 0x00cd, all -> 0x00c4 }
            goto L_0x00bf
        L_0x00be:
            r9 = r5
        L_0x00bf:
            if (r9 != 0) goto L_0x00c2
            goto L_0x00d0
        L_0x00c2:
            r8 = r9
            goto L_0x00d7
        L_0x00c4:
            r7 = move-exception
            if (r5 != 0) goto L_0x00cc
            io.netty.util.internal.logging.InternalLogger r9 = logger
            r9.debug((java.lang.String) r1, (java.lang.Object) r8)
        L_0x00cc:
            throw r7
        L_0x00cd:
            if (r5 != 0) goto L_0x00d6
        L_0x00d0:
            io.netty.util.internal.logging.InternalLogger r7 = logger
            r7.debug((java.lang.String) r1, (java.lang.Object) r8)
            goto L_0x00d7
        L_0x00d6:
            r8 = r5
        L_0x00d7:
            io.netty.util.NetUtilInitializations$NetworkIfaceAndInetAddress r7 = new io.netty.util.NetUtilInitializations$NetworkIfaceAndInetAddress
            r7.<init>(r3, r8)
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.NetUtilInitializations.determineLoopback(java.util.Collection, java.net.Inet4Address, java.net.Inet6Address):io.netty.util.NetUtilInitializations$NetworkIfaceAndInetAddress");
    }

    static final class NetworkIfaceAndInetAddress {
        private final InetAddress address;
        private final NetworkInterface iface;

        NetworkIfaceAndInetAddress(NetworkInterface networkInterface, InetAddress inetAddress) {
            this.iface = networkInterface;
            this.address = inetAddress;
        }

        public NetworkInterface iface() {
            return this.iface;
        }

        public InetAddress address() {
            return this.address;
        }
    }
}
