package io.netty.channel.unix;

import com.google.common.base.Ascii;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public final class NativeInetAddress {
    private static final byte[] IPV4_MAPPED_IPV6_PREFIX = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, -1, -1};
    final byte[] address;
    final int scopeId;

    public static NativeInetAddress newInstance(InetAddress inetAddress) {
        byte[] address2 = inetAddress.getAddress();
        if (inetAddress instanceof Inet6Address) {
            return new NativeInetAddress(address2, ((Inet6Address) inetAddress).getScopeId());
        }
        return new NativeInetAddress(ipv4MappedIpv6Address(address2));
    }

    public NativeInetAddress(byte[] bArr, int i) {
        this.address = bArr;
        this.scopeId = i;
    }

    public NativeInetAddress(byte[] bArr) {
        this(bArr, 0);
    }

    public byte[] address() {
        return this.address;
    }

    public int scopeId() {
        return this.scopeId;
    }

    public static byte[] ipv4MappedIpv6Address(byte[] bArr) {
        byte[] bArr2 = new byte[16];
        copyIpv4MappedIpv6Address(bArr, bArr2);
        return bArr2;
    }

    public static void copyIpv4MappedIpv6Address(byte[] bArr, byte[] bArr2) {
        byte[] bArr3 = IPV4_MAPPED_IPV6_PREFIX;
        System.arraycopy(bArr3, 0, bArr2, 0, bArr3.length);
        System.arraycopy(bArr, 0, bArr2, 12, bArr.length);
    }

    public static InetSocketAddress address(byte[] bArr, int i, int i2) {
        InetAddress inetAddress;
        int i3 = i + i2;
        int decodeInt = decodeInt(bArr, i3 - 4);
        if (i2 == 8) {
            byte[] bArr2 = new byte[4];
            System.arraycopy(bArr, i, bArr2, 0, 4);
            inetAddress = InetAddress.getByAddress(bArr2);
        } else if (i2 == 24) {
            try {
                byte[] bArr3 = new byte[16];
                System.arraycopy(bArr, i, bArr3, 0, 16);
                inetAddress = Inet6Address.getByAddress((String) null, bArr3, decodeInt(bArr, i3 - 8));
            } catch (UnknownHostException e) {
                throw new Error("Should never happen", e);
            }
        } else {
            throw new Error();
        }
        return new InetSocketAddress(inetAddress, decodeInt);
    }

    static int decodeInt(byte[] bArr, int i) {
        return (bArr[i + 3] & 255) | ((bArr[i] & 255) << Ascii.CAN) | ((bArr[i + 1] & 255) << Ascii.DLE) | ((bArr[i + 2] & 255) << 8);
    }
}
