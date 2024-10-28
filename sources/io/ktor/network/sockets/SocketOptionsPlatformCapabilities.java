package io.ktor.network.sockets;

import androidx.tvprovider.media.tv.TvContractCompat;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.nio.channels.DatagramChannel;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000fJ\u000e\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0010J\u0010\u0010\u0011\u001a\u00020\u00012\u0006\u0010\u0012\u001a\u00020\tH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lio/ktor/network/sockets/SocketOptionsPlatformCapabilities;", "", "()V", "channelSetOption", "Ljava/lang/reflect/Method;", "datagramSetOption", "serverChannelSetOption", "standardSocketOptions", "", "", "Ljava/lang/reflect/Field;", "setReusePort", "", "channel", "Ljava/nio/channels/DatagramChannel;", "Ljava/nio/channels/ServerSocketChannel;", "Ljava/nio/channels/SocketChannel;", "socketOption", "name", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SocketOptionsPlatformCapabilities.kt */
public final class SocketOptionsPlatformCapabilities {
    public static final SocketOptionsPlatformCapabilities INSTANCE = new SocketOptionsPlatformCapabilities();
    private static final Method channelSetOption;
    private static final Method datagramSetOption;
    private static final Method serverChannelSetOption;
    private static final Map<String, Field> standardSocketOptions;

    private SocketOptionsPlatformCapabilities() {
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v1, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v4, resolved type: java.lang.Object[]} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r9v6, resolved type: java.lang.Object[]} */
    /* JADX WARNING: type inference failed for: r10v3 */
    /* JADX WARNING: type inference failed for: r12v18 */
    /* JADX WARNING: type inference failed for: r12v23 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:103:0x00f1 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:112:0x015d A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:122:0x01c9 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00a3 A[Catch:{ all -> 0x00f5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x010f A[Catch:{ all -> 0x0161 }] */
    /* JADX WARNING: Removed duplicated region for block: B:75:0x017b A[Catch:{ all -> 0x01cd }] */
    /* JADX WARNING: Unknown variable types count: 3 */
    static {
        /*
            java.lang.String r0 = "setOption"
            java.lang.String r1 = "socketChannelClass.methods"
            java.lang.String r2 = "java.net.SocketOption"
            io.ktor.network.sockets.SocketOptionsPlatformCapabilities r3 = new io.ktor.network.sockets.SocketOptionsPlatformCapabilities
            r3.<init>()
            INSTANCE = r3
            r3 = 0
            java.lang.String r4 = "java.net.StandardSocketOptions"
            java.lang.Class r4 = java.lang.Class.forName(r4)     // Catch:{ all -> 0x0083 }
            if (r4 == 0) goto L_0x007e
            java.lang.reflect.Field[] r4 = r4.getFields()     // Catch:{ all -> 0x0083 }
            if (r4 == 0) goto L_0x007e
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ all -> 0x0083 }
            r5.<init>()     // Catch:{ all -> 0x0083 }
            java.util.Collection r5 = (java.util.Collection) r5     // Catch:{ all -> 0x0083 }
            int r6 = r4.length     // Catch:{ all -> 0x0083 }
            r7 = 0
        L_0x0025:
            if (r7 >= r6) goto L_0x0045
            r8 = r4[r7]     // Catch:{ all -> 0x0083 }
            int r9 = r8.getModifiers()     // Catch:{ all -> 0x0083 }
            boolean r10 = java.lang.reflect.Modifier.isStatic(r9)     // Catch:{ all -> 0x0083 }
            if (r10 == 0) goto L_0x0042
            boolean r10 = java.lang.reflect.Modifier.isFinal(r9)     // Catch:{ all -> 0x0083 }
            if (r10 == 0) goto L_0x0042
            boolean r9 = java.lang.reflect.Modifier.isPublic(r9)     // Catch:{ all -> 0x0083 }
            if (r9 == 0) goto L_0x0042
            r5.add(r8)     // Catch:{ all -> 0x0083 }
        L_0x0042:
            int r7 = r7 + 1
            goto L_0x0025
        L_0x0045:
            java.util.List r5 = (java.util.List) r5     // Catch:{ all -> 0x0083 }
            java.lang.Iterable r5 = (java.lang.Iterable) r5     // Catch:{ all -> 0x0083 }
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r5, r4)     // Catch:{ all -> 0x0083 }
            int r4 = kotlin.collections.MapsKt.mapCapacity(r4)     // Catch:{ all -> 0x0083 }
            r6 = 16
            int r4 = kotlin.ranges.RangesKt.coerceAtLeast((int) r4, (int) r6)     // Catch:{ all -> 0x0083 }
            java.util.LinkedHashMap r6 = new java.util.LinkedHashMap     // Catch:{ all -> 0x0083 }
            r6.<init>(r4)     // Catch:{ all -> 0x0083 }
            java.util.Map r6 = (java.util.Map) r6     // Catch:{ all -> 0x0083 }
            java.util.Iterator r4 = r5.iterator()     // Catch:{ all -> 0x0083 }
        L_0x0064:
            boolean r5 = r4.hasNext()     // Catch:{ all -> 0x0083 }
            if (r5 == 0) goto L_0x0087
            java.lang.Object r5 = r4.next()     // Catch:{ all -> 0x0083 }
            r7 = r5
            java.lang.reflect.Field r7 = (java.lang.reflect.Field) r7     // Catch:{ all -> 0x0083 }
            java.lang.String r7 = r7.getName()     // Catch:{ all -> 0x0083 }
            java.lang.String r8 = "it.name"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r7, r8)     // Catch:{ all -> 0x0083 }
            r6.put(r7, r5)     // Catch:{ all -> 0x0083 }
            goto L_0x0064
        L_0x007e:
            java.util.Map r6 = kotlin.collections.MapsKt.emptyMap()     // Catch:{ all -> 0x0083 }
            goto L_0x0087
        L_0x0083:
            java.util.Map r6 = kotlin.collections.MapsKt.emptyMap()
        L_0x0087:
            standardSocketOptions = r6
            r4 = 2
            r5 = 0
            r6 = 1
            java.lang.Class r7 = java.lang.Class.forName(r2)     // Catch:{ all -> 0x00f5 }
            java.lang.String r8 = "java.nio.channels.SocketChannel"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ all -> 0x00f5 }
            java.lang.reflect.Method[] r9 = r8.getMethods()     // Catch:{ all -> 0x00f5 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)     // Catch:{ all -> 0x00f5 }
            java.lang.Object[] r9 = (java.lang.Object[]) r9     // Catch:{ all -> 0x00f5 }
            int r10 = r9.length     // Catch:{ all -> 0x00f5 }
            r11 = 0
        L_0x00a1:
            if (r11 >= r10) goto L_0x00f1
            r12 = r9[r11]     // Catch:{ all -> 0x00f5 }
            r13 = r12
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13     // Catch:{ all -> 0x00f5 }
            int r14 = r13.getModifiers()     // Catch:{ all -> 0x00f5 }
            boolean r15 = java.lang.reflect.Modifier.isPublic(r14)     // Catch:{ all -> 0x00f5 }
            if (r15 == 0) goto L_0x00ee
            boolean r14 = java.lang.reflect.Modifier.isStatic(r14)     // Catch:{ all -> 0x00f5 }
            if (r14 != 0) goto L_0x00ee
            java.lang.String r14 = r13.getName()     // Catch:{ all -> 0x00f5 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r0)     // Catch:{ all -> 0x00f5 }
            if (r14 == 0) goto L_0x00ee
            java.lang.Class[] r14 = r13.getParameterTypes()     // Catch:{ all -> 0x00f5 }
            int r14 = r14.length     // Catch:{ all -> 0x00f5 }
            if (r14 != r4) goto L_0x00ee
            java.lang.Class r14 = r13.getReturnType()     // Catch:{ all -> 0x00f5 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)     // Catch:{ all -> 0x00f5 }
            if (r14 == 0) goto L_0x00ee
            java.lang.Class[] r14 = r13.getParameterTypes()     // Catch:{ all -> 0x00f5 }
            r14 = r14[r3]     // Catch:{ all -> 0x00f5 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r7)     // Catch:{ all -> 0x00f5 }
            if (r14 == 0) goto L_0x00ee
            java.lang.Class[] r13 = r13.getParameterTypes()     // Catch:{ all -> 0x00f5 }
            r13 = r13[r6]     // Catch:{ all -> 0x00f5 }
            java.lang.Class<java.lang.Object> r14 = java.lang.Object.class
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ all -> 0x00f5 }
            if (r13 == 0) goto L_0x00ee
            goto L_0x00f2
        L_0x00ee:
            int r11 = r11 + 1
            goto L_0x00a1
        L_0x00f1:
            r12 = r5
        L_0x00f2:
            java.lang.reflect.Method r12 = (java.lang.reflect.Method) r12     // Catch:{ all -> 0x00f5 }
            goto L_0x00f6
        L_0x00f5:
            r12 = r5
        L_0x00f6:
            channelSetOption = r12
            java.lang.Class r7 = java.lang.Class.forName(r2)     // Catch:{ all -> 0x0161 }
            java.lang.String r8 = "java.nio.channels.ServerSocketChannel"
            java.lang.Class r8 = java.lang.Class.forName(r8)     // Catch:{ all -> 0x0161 }
            java.lang.reflect.Method[] r9 = r8.getMethods()     // Catch:{ all -> 0x0161 }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r1)     // Catch:{ all -> 0x0161 }
            java.lang.Object[] r9 = (java.lang.Object[]) r9     // Catch:{ all -> 0x0161 }
            int r10 = r9.length     // Catch:{ all -> 0x0161 }
            r11 = 0
        L_0x010d:
            if (r11 >= r10) goto L_0x015d
            r12 = r9[r11]     // Catch:{ all -> 0x0161 }
            r13 = r12
            java.lang.reflect.Method r13 = (java.lang.reflect.Method) r13     // Catch:{ all -> 0x0161 }
            int r14 = r13.getModifiers()     // Catch:{ all -> 0x0161 }
            boolean r15 = java.lang.reflect.Modifier.isPublic(r14)     // Catch:{ all -> 0x0161 }
            if (r15 == 0) goto L_0x015a
            boolean r14 = java.lang.reflect.Modifier.isStatic(r14)     // Catch:{ all -> 0x0161 }
            if (r14 != 0) goto L_0x015a
            java.lang.String r14 = r13.getName()     // Catch:{ all -> 0x0161 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r0)     // Catch:{ all -> 0x0161 }
            if (r14 == 0) goto L_0x015a
            java.lang.Class[] r14 = r13.getParameterTypes()     // Catch:{ all -> 0x0161 }
            int r14 = r14.length     // Catch:{ all -> 0x0161 }
            if (r14 != r4) goto L_0x015a
            java.lang.Class r14 = r13.getReturnType()     // Catch:{ all -> 0x0161 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r8)     // Catch:{ all -> 0x0161 }
            if (r14 == 0) goto L_0x015a
            java.lang.Class[] r14 = r13.getParameterTypes()     // Catch:{ all -> 0x0161 }
            r14 = r14[r3]     // Catch:{ all -> 0x0161 }
            boolean r14 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r14, (java.lang.Object) r7)     // Catch:{ all -> 0x0161 }
            if (r14 == 0) goto L_0x015a
            java.lang.Class[] r13 = r13.getParameterTypes()     // Catch:{ all -> 0x0161 }
            r13 = r13[r6]     // Catch:{ all -> 0x0161 }
            java.lang.Class<java.lang.Object> r14 = java.lang.Object.class
            boolean r13 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r13, (java.lang.Object) r14)     // Catch:{ all -> 0x0161 }
            if (r13 == 0) goto L_0x015a
            goto L_0x015e
        L_0x015a:
            int r11 = r11 + 1
            goto L_0x010d
        L_0x015d:
            r12 = r5
        L_0x015e:
            java.lang.reflect.Method r12 = (java.lang.reflect.Method) r12     // Catch:{ all -> 0x0161 }
            goto L_0x0162
        L_0x0161:
            r12 = r5
        L_0x0162:
            serverChannelSetOption = r12
            java.lang.Class r2 = java.lang.Class.forName(r2)     // Catch:{ all -> 0x01cd }
            java.lang.String r7 = "java.nio.channels.DatagramChannel"
            java.lang.Class r7 = java.lang.Class.forName(r7)     // Catch:{ all -> 0x01cd }
            java.lang.reflect.Method[] r8 = r7.getMethods()     // Catch:{ all -> 0x01cd }
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r8, r1)     // Catch:{ all -> 0x01cd }
            java.lang.Object[] r8 = (java.lang.Object[]) r8     // Catch:{ all -> 0x01cd }
            int r1 = r8.length     // Catch:{ all -> 0x01cd }
            r9 = 0
        L_0x0179:
            if (r9 >= r1) goto L_0x01c9
            r10 = r8[r9]     // Catch:{ all -> 0x01cd }
            r11 = r10
            java.lang.reflect.Method r11 = (java.lang.reflect.Method) r11     // Catch:{ all -> 0x01cd }
            int r12 = r11.getModifiers()     // Catch:{ all -> 0x01cd }
            boolean r13 = java.lang.reflect.Modifier.isPublic(r12)     // Catch:{ all -> 0x01cd }
            if (r13 == 0) goto L_0x01c6
            boolean r12 = java.lang.reflect.Modifier.isStatic(r12)     // Catch:{ all -> 0x01cd }
            if (r12 != 0) goto L_0x01c6
            java.lang.String r12 = r11.getName()     // Catch:{ all -> 0x01cd }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r0)     // Catch:{ all -> 0x01cd }
            if (r12 == 0) goto L_0x01c6
            java.lang.Class[] r12 = r11.getParameterTypes()     // Catch:{ all -> 0x01cd }
            int r12 = r12.length     // Catch:{ all -> 0x01cd }
            if (r12 != r4) goto L_0x01c6
            java.lang.Class r12 = r11.getReturnType()     // Catch:{ all -> 0x01cd }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r7)     // Catch:{ all -> 0x01cd }
            if (r12 == 0) goto L_0x01c6
            java.lang.Class[] r12 = r11.getParameterTypes()     // Catch:{ all -> 0x01cd }
            r12 = r12[r3]     // Catch:{ all -> 0x01cd }
            boolean r12 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r12, (java.lang.Object) r2)     // Catch:{ all -> 0x01cd }
            if (r12 == 0) goto L_0x01c6
            java.lang.Class[] r11 = r11.getParameterTypes()     // Catch:{ all -> 0x01cd }
            r11 = r11[r6]     // Catch:{ all -> 0x01cd }
            java.lang.Class<java.lang.Object> r12 = java.lang.Object.class
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)     // Catch:{ all -> 0x01cd }
            if (r11 == 0) goto L_0x01c6
            goto L_0x01ca
        L_0x01c6:
            int r9 = r9 + 1
            goto L_0x0179
        L_0x01c9:
            r10 = r5
        L_0x01ca:
            java.lang.reflect.Method r10 = (java.lang.reflect.Method) r10     // Catch:{ all -> 0x01cd }
            r5 = r10
        L_0x01cd:
            datagramSetOption = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.network.sockets.SocketOptionsPlatformCapabilities.<clinit>():void");
    }

    public final void setReusePort(SocketChannel socketChannel) {
        Intrinsics.checkNotNullParameter(socketChannel, TvContractCompat.PARAM_CHANNEL);
        Object socketOption = socketOption("SO_REUSEPORT");
        Method method = channelSetOption;
        Intrinsics.checkNotNull(method);
        method.invoke(socketChannel, new Object[]{socketOption, true});
    }

    public final void setReusePort(ServerSocketChannel serverSocketChannel) {
        Intrinsics.checkNotNullParameter(serverSocketChannel, TvContractCompat.PARAM_CHANNEL);
        Object socketOption = socketOption("SO_REUSEPORT");
        Method method = serverChannelSetOption;
        Intrinsics.checkNotNull(method);
        method.invoke(serverSocketChannel, new Object[]{socketOption, true});
    }

    public final void setReusePort(DatagramChannel datagramChannel) {
        Intrinsics.checkNotNullParameter(datagramChannel, TvContractCompat.PARAM_CHANNEL);
        Object socketOption = socketOption("SO_REUSEPORT");
        Method method = datagramSetOption;
        Intrinsics.checkNotNull(method);
        method.invoke(datagramChannel, new Object[]{socketOption, true});
    }

    private final Object socketOption(String str) {
        Field field = standardSocketOptions.get(str);
        Object obj = null;
        if (field != null) {
            obj = field.get((Object) null);
        }
        if (obj != null) {
            return obj;
        }
        throw new IOException("Socket option " + str + " is not supported");
    }
}
