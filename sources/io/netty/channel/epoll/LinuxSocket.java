package io.netty.channel.epoll;

import com.google.common.base.Ascii;
import io.netty.channel.ChannelException;
import io.netty.channel.DefaultFileRegion;
import io.netty.channel.epoll.NativeDatagramPacketArray;
import io.netty.channel.socket.InternetProtocolFamily;
import io.netty.channel.unix.Errors;
import io.netty.channel.unix.NativeInetAddress;
import io.netty.channel.unix.PeerCredentials;
import io.netty.channel.unix.Socket;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SocketUtils;
import java.io.IOException;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

public final class LinuxSocket extends Socket {
    static final InetAddress INET6_ANY = unsafeInetAddrByName("::");
    private static final InetAddress INET_ANY = unsafeInetAddrByName("0.0.0.0");
    private static final long MAX_UINT32_T = 4294967295L;

    private static native int bindVSock(int i, int i2, int i3);

    private static native int connectVSock(int i, int i2, int i3);

    private static native int getInterface(int i, boolean z);

    private static native int getIpMulticastLoop(int i, boolean z) throws IOException;

    private static native PeerCredentials getPeerCredentials(int i) throws IOException;

    private static native int getSoBusyPoll(int i) throws IOException;

    private static native int getTcpDeferAccept(int i) throws IOException;

    private static native void getTcpInfo(int i, long[] jArr) throws IOException;

    private static native int getTcpKeepCnt(int i) throws IOException;

    private static native int getTcpKeepIdle(int i) throws IOException;

    private static native int getTcpKeepIntvl(int i) throws IOException;

    private static native int getTcpNotSentLowAt(int i) throws IOException;

    private static native int getTcpUserTimeout(int i) throws IOException;

    private static native int getTimeToLive(int i) throws IOException;

    private static native int isIpFreeBind(int i) throws IOException;

    private static native int isIpRecvOrigDestAddr(int i) throws IOException;

    private static native int isIpTransparent(int i) throws IOException;

    private static native int isTcpCork(int i) throws IOException;

    private static native int isTcpQuickAck(int i) throws IOException;

    private static native int isUdpGro(int i) throws IOException;

    private static native void joinGroup(int i, boolean z, byte[] bArr, byte[] bArr2, int i2, int i3) throws IOException;

    private static native void joinSsmGroup(int i, boolean z, byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3) throws IOException;

    private static native void leaveGroup(int i, boolean z, byte[] bArr, byte[] bArr2, int i2, int i3) throws IOException;

    private static native void leaveSsmGroup(int i, boolean z, byte[] bArr, byte[] bArr2, int i2, int i3, byte[] bArr3) throws IOException;

    private static native byte[] localVSockAddress(int i);

    private static native int newVSockStreamFd();

    private static native byte[] remoteVSockAddress(int i);

    private static native long sendFile(int i, DefaultFileRegion defaultFileRegion, long j, long j2, long j3) throws IOException;

    private static native void setInterface(int i, boolean z, byte[] bArr, int i2, int i3) throws IOException;

    private static native void setIpFreeBind(int i, int i2) throws IOException;

    private static native void setIpMulticastLoop(int i, boolean z, int i2) throws IOException;

    private static native void setIpRecvOrigDestAddr(int i, int i2) throws IOException;

    private static native void setIpTransparent(int i, int i2) throws IOException;

    private static native void setSoBusyPoll(int i, int i2) throws IOException;

    private static native void setTcpCork(int i, int i2) throws IOException;

    private static native void setTcpDeferAccept(int i, int i2) throws IOException;

    private static native void setTcpFastOpen(int i, int i2) throws IOException;

    private static native void setTcpKeepCnt(int i, int i2) throws IOException;

    private static native void setTcpKeepIdle(int i, int i2) throws IOException;

    private static native void setTcpKeepIntvl(int i, int i2) throws IOException;

    private static native void setTcpMd5Sig(int i, boolean z, byte[] bArr, int i2, byte[] bArr2) throws IOException;

    private static native void setTcpNotSentLowAt(int i, int i2) throws IOException;

    private static native void setTcpQuickAck(int i, int i2) throws IOException;

    private static native void setTcpUserTimeout(int i, int i2) throws IOException;

    private static native void setTimeToLive(int i, int i2) throws IOException;

    private static native void setUdpGro(int i, int i2) throws IOException;

    LinuxSocket(int i) {
        super(i);
    }

    /* access modifiers changed from: package-private */
    public InternetProtocolFamily family() {
        return this.ipv6 ? InternetProtocolFamily.IPv6 : InternetProtocolFamily.IPv4;
    }

    /* access modifiers changed from: package-private */
    public int sendmmsg(NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i, int i2) throws IOException {
        return Native.sendmmsg(intValue(), this.ipv6, nativeDatagramPacketArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public int recvmmsg(NativeDatagramPacketArray.NativeDatagramPacket[] nativeDatagramPacketArr, int i, int i2) throws IOException {
        return Native.recvmmsg(intValue(), this.ipv6, nativeDatagramPacketArr, i, i2);
    }

    /* access modifiers changed from: package-private */
    public int recvmsg(NativeDatagramPacketArray.NativeDatagramPacket nativeDatagramPacket) throws IOException {
        return Native.recvmsg(intValue(), this.ipv6, nativeDatagramPacket);
    }

    /* access modifiers changed from: package-private */
    public void setTimeToLive(int i) throws IOException {
        setTimeToLive(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setInterface(InetAddress inetAddress) throws IOException {
        NativeInetAddress newInstance = NativeInetAddress.newInstance(inetAddress);
        setInterface(intValue(), this.ipv6, newInstance.address(), newInstance.scopeId(), interfaceIndex(inetAddress));
    }

    /* access modifiers changed from: package-private */
    public void setNetworkInterface(NetworkInterface networkInterface) throws IOException {
        InetAddress deriveInetAddress = deriveInetAddress(networkInterface, family() == InternetProtocolFamily.IPv6);
        if (!deriveInetAddress.equals(family() == InternetProtocolFamily.IPv4 ? INET_ANY : INET6_ANY)) {
            NativeInetAddress newInstance = NativeInetAddress.newInstance(deriveInetAddress);
            setInterface(intValue(), this.ipv6, newInstance.address(), newInstance.scopeId(), interfaceIndex(networkInterface));
            return;
        }
        throw new IOException("NetworkInterface does not support " + family());
    }

    /* access modifiers changed from: package-private */
    public InetAddress getInterface() throws IOException {
        NetworkInterface networkInterface = getNetworkInterface();
        if (networkInterface == null) {
            return null;
        }
        Enumeration<InetAddress> addressesFromNetworkInterface = SocketUtils.addressesFromNetworkInterface(networkInterface);
        if (addressesFromNetworkInterface.hasMoreElements()) {
            return addressesFromNetworkInterface.nextElement();
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public NetworkInterface getNetworkInterface() throws IOException {
        int i = getInterface(intValue(), this.ipv6);
        if (!this.ipv6) {
            InetAddress inetAddress = inetAddress(i);
            if (inetAddress != null) {
                return NetworkInterface.getByInetAddress(inetAddress);
            }
            return null;
        } else if (PlatformDependent.javaVersion() >= 7) {
            return NetworkInterface.getByIndex(i);
        } else {
            return null;
        }
    }

    private static InetAddress inetAddress(int i) {
        try {
            return InetAddress.getByAddress(new byte[]{(byte) ((i >>> 24) & 255), (byte) ((i >>> 16) & 255), (byte) ((i >>> 8) & 255), (byte) (i & 255)});
        } catch (UnknownHostException unused) {
            return null;
        }
    }

    /* access modifiers changed from: package-private */
    public void joinGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) throws IOException {
        NativeInetAddress newInstance = NativeInetAddress.newInstance(inetAddress);
        boolean z = inetAddress instanceof Inet6Address;
        NativeInetAddress newInstance2 = NativeInetAddress.newInstance(deriveInetAddress(networkInterface, z));
        if (inetAddress2 == null) {
            joinGroup(intValue(), this.ipv6 && z, newInstance.address(), newInstance2.address(), newInstance.scopeId(), interfaceIndex(networkInterface));
        } else if (inetAddress2.getClass() == inetAddress.getClass()) {
            joinSsmGroup(intValue(), this.ipv6 && z, newInstance.address(), newInstance2.address(), newInstance.scopeId(), interfaceIndex(networkInterface), NativeInetAddress.newInstance(inetAddress2).address());
        } else {
            throw new IllegalArgumentException("Source address is different type to group");
        }
    }

    /* access modifiers changed from: package-private */
    public void leaveGroup(InetAddress inetAddress, NetworkInterface networkInterface, InetAddress inetAddress2) throws IOException {
        NativeInetAddress newInstance = NativeInetAddress.newInstance(inetAddress);
        boolean z = inetAddress instanceof Inet6Address;
        NativeInetAddress newInstance2 = NativeInetAddress.newInstance(deriveInetAddress(networkInterface, z));
        if (inetAddress2 == null) {
            leaveGroup(intValue(), this.ipv6 && z, newInstance.address(), newInstance2.address(), newInstance.scopeId(), interfaceIndex(networkInterface));
        } else if (inetAddress2.getClass() == inetAddress.getClass()) {
            leaveSsmGroup(intValue(), this.ipv6 && z, newInstance.address(), newInstance2.address(), newInstance.scopeId(), interfaceIndex(networkInterface), NativeInetAddress.newInstance(inetAddress2).address());
        } else {
            throw new IllegalArgumentException("Source address is different type to group");
        }
    }

    private static int interfaceIndex(NetworkInterface networkInterface) {
        if (PlatformDependent.javaVersion() >= 7) {
            return networkInterface.getIndex();
        }
        return -1;
    }

    private static int interfaceIndex(InetAddress inetAddress) throws IOException {
        NetworkInterface byInetAddress;
        if (PlatformDependent.javaVersion() < 7 || (byInetAddress = NetworkInterface.getByInetAddress(inetAddress)) == null) {
            return -1;
        }
        return byInetAddress.getIndex();
    }

    /* access modifiers changed from: package-private */
    public void setTcpDeferAccept(int i) throws IOException {
        setTcpDeferAccept(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setTcpQuickAck(boolean z) throws IOException {
        setTcpQuickAck(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void setTcpCork(boolean z) throws IOException {
        setTcpCork(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void setSoBusyPoll(int i) throws IOException {
        setSoBusyPoll(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setTcpNotSentLowAt(long j) throws IOException {
        if (j < 0 || j > 4294967295L) {
            throw new IllegalArgumentException("tcpNotSentLowAt must be a uint32_t");
        }
        setTcpNotSentLowAt(intValue(), (int) j);
    }

    /* access modifiers changed from: package-private */
    public void setTcpFastOpen(int i) throws IOException {
        setTcpFastOpen(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setTcpKeepIdle(int i) throws IOException {
        setTcpKeepIdle(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setTcpKeepIntvl(int i) throws IOException {
        setTcpKeepIntvl(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setTcpKeepCnt(int i) throws IOException {
        setTcpKeepCnt(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setTcpUserTimeout(int i) throws IOException {
        setTcpUserTimeout(intValue(), i);
    }

    /* access modifiers changed from: package-private */
    public void setIpFreeBind(boolean z) throws IOException {
        setIpFreeBind(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void setIpTransparent(boolean z) throws IOException {
        setIpTransparent(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public void setIpRecvOrigDestAddr(boolean z) throws IOException {
        setIpRecvOrigDestAddr(intValue(), z ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public int getTimeToLive() throws IOException {
        return getTimeToLive(intValue());
    }

    /* access modifiers changed from: package-private */
    public void getTcpInfo(EpollTcpInfo epollTcpInfo) throws IOException {
        getTcpInfo(intValue(), epollTcpInfo.info);
    }

    /* access modifiers changed from: package-private */
    public void setTcpMd5Sig(InetAddress inetAddress, byte[] bArr) throws IOException {
        NativeInetAddress newInstance = NativeInetAddress.newInstance(inetAddress);
        setTcpMd5Sig(intValue(), this.ipv6, newInstance.address(), newInstance.scopeId(), bArr);
    }

    /* access modifiers changed from: package-private */
    public boolean isTcpCork() throws IOException {
        return isTcpCork(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public int getSoBusyPoll() throws IOException {
        return getSoBusyPoll(intValue());
    }

    /* access modifiers changed from: package-private */
    public int getTcpDeferAccept() throws IOException {
        return getTcpDeferAccept(intValue());
    }

    /* access modifiers changed from: package-private */
    public boolean isTcpQuickAck() throws IOException {
        return isTcpQuickAck(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public long getTcpNotSentLowAt() throws IOException {
        return ((long) getTcpNotSentLowAt(intValue())) & 4294967295L;
    }

    /* access modifiers changed from: package-private */
    public int getTcpKeepIdle() throws IOException {
        return getTcpKeepIdle(intValue());
    }

    /* access modifiers changed from: package-private */
    public int getTcpKeepIntvl() throws IOException {
        return getTcpKeepIntvl(intValue());
    }

    /* access modifiers changed from: package-private */
    public int getTcpKeepCnt() throws IOException {
        return getTcpKeepCnt(intValue());
    }

    /* access modifiers changed from: package-private */
    public int getTcpUserTimeout() throws IOException {
        return getTcpUserTimeout(intValue());
    }

    /* access modifiers changed from: package-private */
    public boolean isIpFreeBind() throws IOException {
        return isIpFreeBind(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isIpTransparent() throws IOException {
        return isIpTransparent(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public boolean isIpRecvOrigDestAddr() throws IOException {
        return isIpRecvOrigDestAddr(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public PeerCredentials getPeerCredentials() throws IOException {
        return getPeerCredentials(intValue());
    }

    /* access modifiers changed from: package-private */
    public boolean isLoopbackModeDisabled() throws IOException {
        return getIpMulticastLoop(intValue(), this.ipv6) == 0;
    }

    /* access modifiers changed from: package-private */
    public void setLoopbackModeDisabled(boolean z) throws IOException {
        setIpMulticastLoop(intValue(), this.ipv6, z ^ true ? 1 : 0);
    }

    /* access modifiers changed from: package-private */
    public boolean isUdpGro() throws IOException {
        return isUdpGro(intValue()) != 0;
    }

    /* access modifiers changed from: package-private */
    public void setUdpGro(boolean z) throws IOException {
        setUdpGro(intValue(), z ? 1 : 0);
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

    public void bindVSock(VSockAddress vSockAddress) throws IOException {
        int bindVSock = bindVSock(intValue(), vSockAddress.getCid(), vSockAddress.getPort());
        if (bindVSock < 0) {
            throw Errors.newIOException("bindVSock", bindVSock);
        }
    }

    public boolean connectVSock(VSockAddress vSockAddress) throws IOException {
        int connectVSock = connectVSock(intValue(), vSockAddress.getCid(), vSockAddress.getPort());
        if (connectVSock < 0) {
            return Errors.handleConnectErrno("connectVSock", connectVSock);
        }
        return true;
    }

    public VSockAddress remoteVSockAddress() {
        byte[] remoteVSockAddress = remoteVSockAddress(intValue());
        if (remoteVSockAddress == null) {
            return null;
        }
        return new VSockAddress(getIntAt(remoteVSockAddress, 0), getIntAt(remoteVSockAddress, 4));
    }

    public VSockAddress localVSockAddress() {
        byte[] localVSockAddress = localVSockAddress(intValue());
        if (localVSockAddress == null) {
            return null;
        }
        return new VSockAddress(getIntAt(localVSockAddress, 0), getIntAt(localVSockAddress, 4));
    }

    private static int getIntAt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | (bArr[i] << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
    }

    private static InetAddress deriveInetAddress(NetworkInterface networkInterface, boolean z) {
        InetAddress inetAddress = z ? INET6_ANY : INET_ANY;
        if (networkInterface != null) {
            Enumeration<InetAddress> inetAddresses = networkInterface.getInetAddresses();
            while (inetAddresses.hasMoreElements()) {
                InetAddress nextElement = inetAddresses.nextElement();
                if ((nextElement instanceof Inet6Address) == z) {
                    return nextElement;
                }
            }
        }
        return inetAddress;
    }

    public static LinuxSocket newSocket(int i) {
        return new LinuxSocket(i);
    }

    public static LinuxSocket newVSockStream() {
        return new LinuxSocket(newVSockStream0());
    }

    static int newVSockStream0() {
        int newVSockStreamFd = newVSockStreamFd();
        if (newVSockStreamFd >= 0) {
            return newVSockStreamFd;
        }
        throw new ChannelException((Throwable) Errors.newIOException("newVSockStream", newVSockStreamFd));
    }

    public static LinuxSocket newSocketStream(boolean z) {
        return new LinuxSocket(newSocketStream0(z));
    }

    public static LinuxSocket newSocketStream(InternetProtocolFamily internetProtocolFamily) {
        return new LinuxSocket(newSocketStream0(internetProtocolFamily));
    }

    public static LinuxSocket newSocketStream() {
        return newSocketStream(isIPv6Preferred());
    }

    public static LinuxSocket newSocketDgram(boolean z) {
        return new LinuxSocket(newSocketDgram0(z));
    }

    public static LinuxSocket newSocketDgram(InternetProtocolFamily internetProtocolFamily) {
        return new LinuxSocket(newSocketDgram0(internetProtocolFamily));
    }

    public static LinuxSocket newSocketDgram() {
        return newSocketDgram(isIPv6Preferred());
    }

    public static LinuxSocket newSocketDomain() {
        return new LinuxSocket(newSocketDomain0());
    }

    public static LinuxSocket newSocketDomainDgram() {
        return new LinuxSocket(newSocketDomainDgram0());
    }

    private static InetAddress unsafeInetAddrByName(String str) {
        try {
            return InetAddress.getByName(str);
        } catch (UnknownHostException e) {
            throw new ChannelException((Throwable) e);
        }
    }
}
