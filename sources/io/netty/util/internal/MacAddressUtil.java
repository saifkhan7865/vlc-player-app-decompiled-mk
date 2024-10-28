package io.netty.util.internal;

import io.netty.util.NetUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.LinkedHashMap;
import java.util.Map;

public final class MacAddressUtil {
    private static final int EUI48_MAC_ADDRESS_LENGTH = 6;
    private static final int EUI64_MAC_ADDRESS_LENGTH = 8;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) MacAddressUtil.class);

    public static byte[] bestAvailableMac() {
        int compareAddresses;
        byte[] bArr = EmptyArrays.EMPTY_BYTES;
        InetAddress inetAddress = NetUtil.LOCALHOST4;
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (NetworkInterface next : NetUtil.NETWORK_INTERFACES) {
            Enumeration<InetAddress> addressesFromNetworkInterface = SocketUtils.addressesFromNetworkInterface(next);
            if (addressesFromNetworkInterface.hasMoreElements()) {
                InetAddress nextElement = addressesFromNetworkInterface.nextElement();
                if (!nextElement.isLoopbackAddress()) {
                    linkedHashMap.put(next, nextElement);
                }
            }
        }
        for (Map.Entry entry : linkedHashMap.entrySet()) {
            NetworkInterface networkInterface = (NetworkInterface) entry.getKey();
            InetAddress inetAddress2 = (InetAddress) entry.getValue();
            if (!networkInterface.isVirtual()) {
                try {
                    byte[] hardwareAddressFromNetworkInterface = SocketUtils.hardwareAddressFromNetworkInterface(networkInterface);
                    int compareAddresses2 = compareAddresses(bArr, hardwareAddressFromNetworkInterface);
                    if (compareAddresses2 < 0 || (compareAddresses2 == 0 && ((compareAddresses = compareAddresses(inetAddress, inetAddress2)) < 0 || (compareAddresses == 0 && bArr.length < hardwareAddressFromNetworkInterface.length)))) {
                        inetAddress = inetAddress2;
                        bArr = hardwareAddressFromNetworkInterface;
                    }
                } catch (SocketException e) {
                    logger.debug("Failed to get the hardware address of a network interface: {}", networkInterface, e);
                }
            }
        }
        if (bArr == EmptyArrays.EMPTY_BYTES) {
            return null;
        }
        if (bArr.length != 6) {
            return Arrays.copyOf(bArr, 8);
        }
        byte[] bArr2 = new byte[8];
        System.arraycopy(bArr, 0, bArr2, 0, 3);
        bArr2[3] = -1;
        bArr2[4] = -2;
        System.arraycopy(bArr, 3, bArr2, 5, 3);
        return bArr2;
    }

    public static byte[] defaultMachineId() {
        byte[] bestAvailableMac = bestAvailableMac();
        if (bestAvailableMac != null) {
            return bestAvailableMac;
        }
        byte[] bArr = new byte[8];
        PlatformDependent.threadLocalRandom().nextBytes(bArr);
        logger.warn("Failed to find a usable hardware address from the network interfaces; using random bytes: {}", (Object) formatAddress(bArr));
        return bArr;
    }

    public static byte[] parseMAC(String str) {
        byte[] bArr;
        char c;
        int length = str.length();
        if (length == 17) {
            c = str.charAt(2);
            validateMacSeparator(c);
            bArr = new byte[6];
        } else if (length == 23) {
            c = str.charAt(2);
            validateMacSeparator(c);
            bArr = new byte[8];
        } else {
            throw new IllegalArgumentException("value is not supported [MAC-48, EUI-48, EUI-64]");
        }
        int length2 = bArr.length - 1;
        int i = 0;
        int i2 = 0;
        while (i < length2) {
            int i3 = i2 + 2;
            bArr[i] = StringUtil.decodeHexByte(str, i2);
            if (str.charAt(i3) == c) {
                i++;
                i2 += 3;
            } else {
                throw new IllegalArgumentException("expected separator '" + c + " but got '" + str.charAt(i3) + "' at index: " + i3);
            }
        }
        bArr[length2] = StringUtil.decodeHexByte(str, i2);
        return bArr;
    }

    private static void validateMacSeparator(char c) {
        if (c != ':' && c != '-') {
            throw new IllegalArgumentException("unsupported separator: " + c + " (expected: [:-])");
        }
    }

    public static String formatAddress(byte[] bArr) {
        StringBuilder sb = new StringBuilder(24);
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            sb.append(String.format("%02x:", new Object[]{Integer.valueOf(bArr[i] & 255)}));
        }
        return sb.substring(0, sb.length() - 1);
    }

    static int compareAddresses(byte[] bArr, byte[] bArr2) {
        if (bArr2 != null && bArr2.length >= 6) {
            int length = bArr2.length;
            int i = 0;
            while (i < length) {
                byte b = bArr2[i];
                if (b == 0 || b == 1) {
                    i++;
                } else {
                    byte b2 = bArr2[0];
                    if ((b2 & 1) != 0) {
                        return 1;
                    }
                    if ((b2 & 2) == 0) {
                        if (bArr.length == 0 || (bArr[0] & 2) != 0) {
                            return -1;
                        }
                        return 0;
                    } else if (bArr.length == 0 || (bArr[0] & 2) != 0) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            }
        }
        return 1;
    }

    private static int compareAddresses(InetAddress inetAddress, InetAddress inetAddress2) {
        return scoreAddress(inetAddress) - scoreAddress(inetAddress2);
    }

    private static int scoreAddress(InetAddress inetAddress) {
        if (inetAddress.isAnyLocalAddress() || inetAddress.isLoopbackAddress()) {
            return 0;
        }
        if (inetAddress.isMulticastAddress()) {
            return 1;
        }
        if (inetAddress.isLinkLocalAddress()) {
            return 2;
        }
        return inetAddress.isSiteLocalAddress() ? 3 : 4;
    }

    private MacAddressUtil() {
    }
}
