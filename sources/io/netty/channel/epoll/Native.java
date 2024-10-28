package io.netty.channel.epoll;

import io.netty.channel.epoll.NativeDatagramPacketArray;
import io.netty.channel.unix.Errors;
import io.netty.channel.unix.FileDescriptor;
import io.netty.channel.unix.Socket;
import io.netty.util.internal.NativeLibraryLoader;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.ThrowableUtil;
import io.netty.util.internal.logging.InternalLogger;
import java.io.IOException;

public final class Native {
    public static final int EPOLLERR = NativeStaticallyReferencedJniMethods.epollerr();
    public static final int EPOLLET = NativeStaticallyReferencedJniMethods.epollet();
    public static final int EPOLLIN = NativeStaticallyReferencedJniMethods.epollin();
    public static final int EPOLLOUT = NativeStaticallyReferencedJniMethods.epollout();
    public static final int EPOLLRDHUP = NativeStaticallyReferencedJniMethods.epollrdhup();
    static final boolean IS_SUPPORTING_RECVMMSG = NativeStaticallyReferencedJniMethods.isSupportingRecvmmsg();
    public static final boolean IS_SUPPORTING_SENDMMSG = NativeStaticallyReferencedJniMethods.isSupportingSendmmsg();
    @Deprecated
    public static final boolean IS_SUPPORTING_TCP_FASTOPEN;
    static final boolean IS_SUPPORTING_TCP_FASTOPEN_CLIENT;
    static final boolean IS_SUPPORTING_TCP_FASTOPEN_SERVER;
    static final boolean IS_SUPPORTING_UDP_SEGMENT = isSupportingUdpSegment();
    public static final String KERNEL_VERSION = NativeStaticallyReferencedJniMethods.kernelVersion();
    private static final int TCP_FASTOPEN_MODE;
    public static final int TCP_MD5SIG_MAXKEYLEN = NativeStaticallyReferencedJniMethods.tcpMd5SigMaxKeyLen();
    private static final int TFO_ENABLED_CLIENT_MASK = 1;
    private static final int TFO_ENABLED_SERVER_MASK = 2;
    private static final InternalLogger logger;

    private static native int epollBusyWait0(int i, long j, int i2);

    private static native int epollCreate();

    private static native int epollCtlAdd0(int i, int i2, int i3);

    private static native int epollCtlDel0(int i, int i2);

    private static native int epollCtlMod0(int i, int i2, int i3);

    static int epollReady(long j) {
        return (int) (j >> 32);
    }

    static boolean epollTimerWasUsed(long j) {
        return (j & 255) != 0;
    }

    private static native int epollWait(int i, long j, int i2, int i3);

    private static native long epollWait0(int i, long j, int i2, int i3, int i4, int i5, long j2);

    private static native int eventFd();

    public static native void eventFdRead(int i);

    public static native void eventFdWrite(int i, long j);

    private static native boolean isSupportingUdpSegment();

    public static native int offsetofEpollData();

    private static native int recvmmsg0(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3);

    private static native int recvmsg0(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket nativeDatagramPacket);

    /* access modifiers changed from: private */
    public static native int registerUnix();

    private static native int sendmmsg0(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3);

    public static native int sizeofEpollEvent();

    private static native int splice0(int i, long j, int i2, long j2, long j3);

    private static native int timerFd();

    /* JADX WARNING: Can't wrap try/catch for region: R(2:13|14) */
    /* JADX WARNING: Code restructure failed: missing block: B:10:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x0038, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:?, code lost:
        loadNativeLibrary();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r1 == null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x00a3, code lost:
        if (r1 != null) goto L_0x00a5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:?, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x00a9, code lost:
        throw r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0030, code lost:
        if (r1 != null) goto L_0x0032;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:13:0x003a */
    static {
        /*
            java.lang.Class<io.netty.channel.epoll.Native> r0 = io.netty.channel.epoll.Native.class
            io.netty.util.internal.logging.InternalLogger r1 = io.netty.util.internal.logging.InternalLoggerFactory.getInstance((java.lang.Class<?>) r0)
            logger = r1
            java.nio.channels.Selector r1 = java.nio.channels.Selector.open()     // Catch:{ IOException -> 0x000d }
            goto L_0x000e
        L_0x000d:
            r1 = 0
        L_0x000e:
            r2 = 5
            java.lang.Class[] r2 = new java.lang.Class[r2]
            java.lang.Class<io.netty.channel.unix.PeerCredentials> r3 = io.netty.channel.unix.PeerCredentials.class
            r4 = 0
            r2[r4] = r3
            java.lang.Class<io.netty.channel.DefaultFileRegion> r3 = io.netty.channel.DefaultFileRegion.class
            r5 = 1
            r2[r5] = r3
            java.lang.Class<java.nio.channels.FileChannel> r3 = java.nio.channels.FileChannel.class
            r6 = 2
            r2[r6] = r3
            java.lang.Class<java.io.FileDescriptor> r3 = java.io.FileDescriptor.class
            r7 = 3
            r2[r7] = r3
            java.lang.Class<io.netty.channel.epoll.NativeDatagramPacketArray$NativeDatagramPacket> r3 = io.netty.channel.epoll.NativeDatagramPacketArray.NativeDatagramPacket.class
            r7 = 4
            r2[r7] = r3
            io.netty.util.internal.ClassInitializerUtil.tryLoadClasses(r0, r2)
            offsetofEpollData()     // Catch:{ UnsatisfiedLinkError -> 0x003a }
            if (r1 == 0) goto L_0x0040
        L_0x0032:
            r1.close()     // Catch:{ IOException -> 0x0036 }
            goto L_0x0040
        L_0x0036:
            goto L_0x0040
        L_0x0038:
            r0 = move-exception
            goto L_0x00a3
        L_0x003a:
            loadNativeLibrary()     // Catch:{ all -> 0x0038 }
            if (r1 == 0) goto L_0x0040
            goto L_0x0032
        L_0x0040:
            io.netty.channel.epoll.Native$1 r0 = new io.netty.channel.epoll.Native$1
            r0.<init>()
            io.netty.channel.unix.Unix.registerInternal(r0)
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollin()
            EPOLLIN = r0
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollout()
            EPOLLOUT = r0
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollrdhup()
            EPOLLRDHUP = r0
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollet()
            EPOLLET = r0
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.epollerr()
            EPOLLERR = r0
            boolean r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.isSupportingSendmmsg()
            IS_SUPPORTING_SENDMMSG = r0
            boolean r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.isSupportingRecvmmsg()
            IS_SUPPORTING_RECVMMSG = r0
            boolean r0 = isSupportingUdpSegment()
            IS_SUPPORTING_UDP_SEGMENT = r0
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.tcpFastopenMode()
            TCP_FASTOPEN_MODE = r0
            r1 = r0 & 1
            if (r1 != r5) goto L_0x0084
            r1 = 1
            goto L_0x0085
        L_0x0084:
            r1 = 0
        L_0x0085:
            IS_SUPPORTING_TCP_FASTOPEN_CLIENT = r1
            r0 = r0 & r6
            if (r0 != r6) goto L_0x008c
            r0 = 1
            goto L_0x008d
        L_0x008c:
            r0 = 0
        L_0x008d:
            IS_SUPPORTING_TCP_FASTOPEN_SERVER = r0
            if (r1 != 0) goto L_0x0093
            if (r0 == 0) goto L_0x0094
        L_0x0093:
            r4 = 1
        L_0x0094:
            IS_SUPPORTING_TCP_FASTOPEN = r4
            int r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.tcpMd5SigMaxKeyLen()
            TCP_MD5SIG_MAXKEYLEN = r0
            java.lang.String r0 = io.netty.channel.epoll.NativeStaticallyReferencedJniMethods.kernelVersion()
            KERNEL_VERSION = r0
            return
        L_0x00a3:
            if (r1 == 0) goto L_0x00a8
            r1.close()     // Catch:{ IOException -> 0x00a8 }
        L_0x00a8:
            goto L_0x00aa
        L_0x00a9:
            throw r0
        L_0x00aa:
            goto L_0x00a9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.channel.epoll.Native.<clinit>():void");
    }

    public static FileDescriptor newEventFd() {
        return new FileDescriptor(eventFd());
    }

    public static FileDescriptor newTimerFd() {
        return new FileDescriptor(timerFd());
    }

    public static FileDescriptor newEpollCreate() {
        return new FileDescriptor(epollCreate());
    }

    @Deprecated
    public static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, FileDescriptor fileDescriptor2, int i, int i2) throws IOException {
        return epollReady(epollWait(fileDescriptor, epollEventArray, fileDescriptor2, i, i2, -1));
    }

    static long epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, FileDescriptor fileDescriptor2, int i, int i2, long j) throws IOException {
        int i3;
        int i4;
        int i5 = i;
        if (i5 == 0 && i2 == 0) {
            FileDescriptor fileDescriptor3 = fileDescriptor;
            EpollEventArray epollEventArray2 = epollEventArray;
            return ((long) epollWait(fileDescriptor, epollEventArray, 0)) << 32;
        }
        FileDescriptor fileDescriptor4 = fileDescriptor;
        EpollEventArray epollEventArray3 = epollEventArray;
        if (i5 == Integer.MAX_VALUE) {
            i4 = 0;
            i3 = 0;
        } else {
            i3 = i2;
            i4 = i5;
        }
        long epollWait0 = epollWait0(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length(), fileDescriptor2.intValue(), i4, i3, j);
        int epollReady = epollReady(epollWait0);
        if (epollReady >= 0) {
            return epollWait0;
        }
        throw Errors.newIOException("epoll_wait", epollReady);
    }

    static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, boolean z) throws IOException {
        return epollWait(fileDescriptor, epollEventArray, z ? 0 : -1);
    }

    static int epollWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray, int i) throws IOException {
        int epollWait = epollWait(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length(), i);
        if (epollWait >= 0) {
            return epollWait;
        }
        throw Errors.newIOException("epoll_wait", epollWait);
    }

    public static int epollBusyWait(FileDescriptor fileDescriptor, EpollEventArray epollEventArray) throws IOException {
        int epollBusyWait0 = epollBusyWait0(fileDescriptor.intValue(), epollEventArray.memoryAddress(), epollEventArray.length());
        if (epollBusyWait0 >= 0) {
            return epollBusyWait0;
        }
        throw Errors.newIOException("epoll_wait", epollBusyWait0);
    }

    public static void epollCtlAdd(int i, int i2, int i3) throws IOException {
        int epollCtlAdd0 = epollCtlAdd0(i, i2, i3);
        if (epollCtlAdd0 < 0) {
            throw Errors.newIOException("epoll_ctl", epollCtlAdd0);
        }
    }

    public static void epollCtlMod(int i, int i2, int i3) throws IOException {
        int epollCtlMod0 = epollCtlMod0(i, i2, i3);
        if (epollCtlMod0 < 0) {
            throw Errors.newIOException("epoll_ctl", epollCtlMod0);
        }
    }

    public static void epollCtlDel(int i, int i2) throws IOException {
        int epollCtlDel0 = epollCtlDel0(i, i2);
        if (epollCtlDel0 < 0) {
            throw Errors.newIOException("epoll_ctl", epollCtlDel0);
        }
    }

    public static int splice(int i, long j, int i2, long j2, long j3) throws IOException {
        int splice0 = splice0(i, j, i2, j2, j3);
        if (splice0 >= 0) {
            return splice0;
        }
        return Errors.ioResult("splice", splice0);
    }

    @Deprecated
    public static int sendmmsg(int i, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        return sendmmsg(i, Socket.isIPv6Preferred(), nativeDatagramPacketArr, i2, i3);
    }

    static int sendmmsg(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        int sendmmsg0 = sendmmsg0(i, z, nativeDatagramPacketArr, i2, i3);
        if (sendmmsg0 >= 0) {
            return sendmmsg0;
        }
        return Errors.ioResult("sendmmsg", sendmmsg0);
    }

    static int recvmmsg(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i2, int i3) throws IOException {
        int recvmmsg0 = recvmmsg0(i, z, nativeDatagramPacketArr, i2, i3);
        if (recvmmsg0 >= 0) {
            return recvmmsg0;
        }
        return Errors.ioResult("recvmmsg", recvmmsg0);
    }

    static int recvmsg(int i, boolean z, NativeDatagramPacketArray.NativeDatagramPacket nativeDatagramPacket) throws IOException {
        int recvmsg0 = recvmsg0(i, z, nativeDatagramPacket);
        if (recvmsg0 >= 0) {
            return recvmsg0;
        }
        return Errors.ioResult("recvmsg", recvmsg0);
    }

    private static void loadNativeLibrary() {
        if ("linux".equals(PlatformDependent.normalizedOs())) {
            String str = "netty_transport_native_epoll_" + PlatformDependent.normalizedArch();
            ClassLoader classLoader = PlatformDependent.getClassLoader(Native.class);
            try {
                NativeLibraryLoader.load(str, classLoader);
            } catch (UnsatisfiedLinkError e) {
                try {
                    NativeLibraryLoader.load("netty_transport_native_epoll", classLoader);
                    logger.debug("Failed to load {}", str, e);
                } catch (UnsatisfiedLinkError e2) {
                    ThrowableUtil.addSuppressed((Throwable) e, (Throwable) e2);
                    throw e;
                }
            }
        } else {
            throw new IllegalStateException("Only supported on Linux");
        }
    }

    private Native() {
    }
}
