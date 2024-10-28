package io.netty.channel.kqueue;

import io.netty.channel.DefaultFileRegion;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.Errors;
import io.netty.channel.unix.IovArray;
import io.netty.channel.unix.NativeInetAddress;
import io.netty.channel.unix.PeerCredentials;
import io.netty.channel.unix.Socket;
import io.netty.util.internal.ObjectUtil;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;

final class BsdSocket extends Socket {
    private static final int APPLE_SND_LOW_AT_MAX = 131072;
    static final int BSD_SND_LOW_AT_MAX = Math.min(131072, 32768);
    private static final int FREEBSD_SND_LOW_AT_MAX = 32768;
    private static final int UNSPECIFIED_SOURCE_INTERFACE = 0;

    private static native int connectx(int i, int i2, boolean z, byte[] bArr, int i3, int i4, boolean z2, byte[] bArr2, int i5, int i6, int i7, long j, int i8, int i9);

    private static native String[] getAcceptFilter(int i) throws IOException;

    private static native PeerCredentials getPeerCredentials(int i) throws IOException;

    private static native int getSndLowAt(int i) throws IOException;

    private static native int getTcpNoPush(int i) throws IOException;

    private static native int isTcpFastOpen(int i) throws IOException;

    private static native long sendFile(int i, DefaultFileRegion defaultFileRegion, long j, long j2, long j3) throws IOException;

    private static native void setAcceptFilter(int i, String str, String str2) throws IOException;

    private static native void setSndLowAt(int i, int i2) throws IOException;

    private static native void setTcpFastOpen(int i, int i2) throws IOException;

    private static native void setTcpNoPush(int i, int i2) throws IOException;

    BsdSocket(int i) {
        super(i);
    }

    /* access modifiers changed from: package-private */
    public void setAcceptFilter(AcceptFilter acceptFilter) throws IOException {
        setAcceptFilter(intValue(), acceptFilter.filterName(), acceptFilter.filterArgs());
    }

    /* access modifiers changed from: package-private */
    public void setTcpNoPush(boolean z) throws IOException {
        setTcpNoPush(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void setSndLowAt(int i) throws IOException {
        setSndLowAt(intValue(), i);
    }

    public void setTcpFastOpen(boolean z) throws IOException {
        setTcpFastOpen(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public boolean isTcpNoPush() throws IOException {
        return getTcpNoPush(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public int getSndLowAt() throws IOException {
        return getSndLowAt(intValue());
    }

    /* access modifiers changed from: package-private */
    public AcceptFilter getAcceptFilter() throws IOException {
        String[] acceptFilter = getAcceptFilter(intValue());
        return acceptFilter == null ? AcceptFilter.PLATFORM_UNSUPPORTED : new AcceptFilter(acceptFilter[0], acceptFilter[1]);
    }

    public boolean isTcpFastOpen() throws IOException {
        return isTcpFastOpen(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public PeerCredentials getPeerCredentials() throws IOException {
        return getPeerCredentials(intValue());
    }

    /* access modifiers changed from: package-private */
    public long sendFile(DefaultFileRegion defaultFileRegion, long j, long j2, long j3) throws IOException {
        defaultFileRegion.open();
        long sendFile = sendFile(intValue(), defaultFileRegion, j, j2, j3);
        if (sendFile >= 0) {
            return sendFile;
        }
        return (long) Errors.ioResult("sendfile", (int) sendFile);
    }

    /* access modifiers changed from: package-private */
    public int connectx(InetSocketAddress inetSocketAddress, InetSocketAddress inetSocketAddress2, IovArray iovArray, boolean z) throws IOException {
        int i;
        int i2;
        int i3;
        byte[] bArr;
        boolean z2;
        int i4;
        byte[] bArr2;
        int i5;
        long j;
        byte[] bArr3;
        int i6;
        IovArray iovArray2 = iovArray;
        ObjectUtil.checkNotNull(inetSocketAddress2, "Destination InetSocketAddress cannot be null.");
        int i7 = 0;
        if (z) {
            i = Native.CONNECT_TCP_FASTOPEN;
        } else {
            i = 0;
        }
        if (inetSocketAddress == null) {
            bArr = null;
            z2 = false;
            i3 = 0;
            i2 = 0;
        } else {
            InetAddress address = inetSocketAddress.getAddress();
            boolean useIpv6 = useIpv6(this, address);
            if (address instanceof Inet6Address) {
                bArr3 = address.getAddress();
                i6 = ((Inet6Address) address).getScopeId();
            } else {
                bArr3 = NativeInetAddress.ipv4MappedIpv6Address(address.getAddress());
                i6 = 0;
            }
            i3 = i6;
            bArr = bArr3;
            i2 = inetSocketAddress.getPort();
            z2 = useIpv6;
        }
        InetAddress address2 = inetSocketAddress2.getAddress();
        boolean useIpv62 = useIpv6(this, address2);
        if (address2 instanceof Inet6Address) {
            byte[] address3 = address2.getAddress();
            i4 = ((Inet6Address) address2).getScopeId();
            bArr2 = address3;
        } else {
            bArr2 = NativeInetAddress.ipv4MappedIpv6Address(address2.getAddress());
            i4 = 0;
        }
        int port = inetSocketAddress2.getPort();
        if (iovArray2 == null || iovArray.count() == 0) {
            j = 0;
            i5 = 0;
        } else {
            long memoryAddress = iovArray2.memoryAddress(0);
            int count = iovArray.count();
            long size = iovArray.size();
            if (size <= 2147483647L) {
                j = memoryAddress;
                i5 = count;
                i7 = (int) size;
            } else {
                throw new IOException("IovArray.size() too big: " + size + " bytes.");
            }
        }
        int connectx = connectx(intValue(), 0, z2, bArr, i3, i2, useIpv62, bArr2, i4, port, i, j, i5, i7);
        if (connectx == Errors.ERRNO_EINPROGRESS_NEGATIVE) {
            return -i7;
        }
        return connectx < 0 ? Errors.ioResult("connectx", connectx) : connectx;
    }

    public static BsdSocket newSocketStream() {
        return new BsdSocket(newSocketStream0());
    }

    public static BsdSocket newSocketStream(InternetProtocolFamily internetProtocolFamily) {
        return new BsdSocket(newSocketStream0(internetProtocolFamily));
    }

    public static BsdSocket newSocketDgram() {
        return new BsdSocket(newSocketDgram0());
    }

    public static BsdSocket newSocketDgram(InternetProtocolFamily internetProtocolFamily) {
        return new BsdSocket(newSocketDgram0(internetProtocolFamily));
    }

    public static BsdSocket newSocketDomain() {
        return new BsdSocket(newSocketDomain0());
    }

    public static BsdSocket newSocketDomainDgram() {
        return new BsdSocket(newSocketDomainDgram0());
    }
}
