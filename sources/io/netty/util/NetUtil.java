package io.netty.util;

import com.google.common.base.Ascii;
import io.netty.util.NetUtilInitializations;
import io.netty.util.internal.PlatformDependent;
import io.netty.util.internal.SystemPropertyUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Collection;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

public final class NetUtil {
    private static final int IPV4_MAX_CHAR_BETWEEN_SEPARATOR = 3;
    private static final boolean IPV4_PREFERRED;
    private static final int IPV4_SEPARATORS = 3;
    private static final boolean IPV6_ADDRESSES_PREFERRED;
    private static final int IPV6_BYTE_COUNT = 16;
    private static final int IPV6_MAX_CHAR_BETWEEN_SEPARATOR = 4;
    private static final int IPV6_MAX_CHAR_COUNT = 39;
    private static final int IPV6_MAX_SEPARATORS = 8;
    private static final int IPV6_MIN_SEPARATORS = 2;
    private static final int IPV6_WORD_COUNT = 8;
    public static final InetAddress LOCALHOST;
    public static final Inet4Address LOCALHOST4;
    public static final Inet6Address LOCALHOST6;
    public static final NetworkInterface LOOPBACK_IF;
    public static final Collection<NetworkInterface> NETWORK_INTERFACES;
    public static final int SOMAXCONN = ((Integer) AccessController.doPrivileged(new SoMaxConnAction())).intValue();
    /* access modifiers changed from: private */
    public static final InternalLogger logger;

    private static boolean inRangeEndExclusive(int i, int i2, int i3) {
        return i >= i2 && i < i3;
    }

    private static boolean isValidHexChar(char c) {
        return (c >= '0' && c <= '9') || (c >= 'A' && c <= 'F') || (c >= 'a' && c <= 'f');
    }

    private static boolean isValidIPv4MappedChar(char c) {
        return c == 'f' || c == 'F';
    }

    private static boolean isValidIPv4MappedSeparators(byte b, byte b2, boolean z) {
        return b == b2 && (b == 0 || (!z && b2 == -1));
    }

    private static boolean isValidNumericChar(char c) {
        return c >= '0' && c <= '9';
    }

    static {
        boolean z = SystemPropertyUtil.getBoolean("java.net.preferIPv4Stack", false);
        IPV4_PREFERRED = z;
        InternalLogger instance = InternalLoggerFactory.getInstance((Class<?>) NetUtil.class);
        logger = instance;
        String str = SystemPropertyUtil.get("java.net.preferIPv6Addresses", "false");
        if ("true".equalsIgnoreCase(str.trim())) {
            IPV6_ADDRESSES_PREFERRED = true;
        } else {
            IPV6_ADDRESSES_PREFERRED = false;
        }
        instance.debug("-Djava.net.preferIPv4Stack: {}", (Object) Boolean.valueOf(z));
        instance.debug("-Djava.net.preferIPv6Addresses: {}", (Object) str);
        Collection<NetworkInterface> networkInterfaces = NetUtilInitializations.networkInterfaces();
        NETWORK_INTERFACES = networkInterfaces;
        Inet4Address createLocalhost4 = NetUtilInitializations.createLocalhost4();
        LOCALHOST4 = createLocalhost4;
        Inet6Address createLocalhost6 = NetUtilInitializations.createLocalhost6();
        LOCALHOST6 = createLocalhost6;
        NetUtilInitializations.NetworkIfaceAndInetAddress determineLoopback = NetUtilInitializations.determineLoopback(networkInterfaces, createLocalhost4, createLocalhost6);
        LOOPBACK_IF = determineLoopback.iface();
        LOCALHOST = determineLoopback.address();
    }

    private static final class SoMaxConnAction implements PrivilegedAction<Integer> {
        private SoMaxConnAction() {
        }

        /* JADX WARNING: Removed duplicated region for block: B:37:0x008f A[Catch:{ all -> 0x00af }] */
        /* JADX WARNING: Removed duplicated region for block: B:39:0x00a7 A[SYNTHETIC, Splitter:B:39:0x00a7] */
        /* JADX WARNING: Removed duplicated region for block: B:47:0x00b3 A[SYNTHETIC, Splitter:B:47:0x00b3] */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public java.lang.Integer run() {
            /*
                r10 = this;
                java.lang.String r0 = "Failed to get SOMAXCONN from sysctl and file {}. Default: {}"
                boolean r1 = io.netty.util.internal.PlatformDependent.isWindows()
                if (r1 == 0) goto L_0x000b
                r1 = 200(0xc8, float:2.8E-43)
                goto L_0x000d
            L_0x000b:
                r1 = 128(0x80, float:1.794E-43)
            L_0x000d:
                java.io.File r2 = new java.io.File
                java.lang.String r3 = "/proc/sys/net/core/somaxconn"
                r2.<init>(r3)
                r3 = 0
                r4 = 0
                boolean r5 = r2.exists()     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                if (r5 == 0) goto L_0x0049
                java.io.BufferedReader r5 = new java.io.BufferedReader     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                java.io.FileReader r6 = new java.io.FileReader     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                r6.<init>(r2)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                r5.<init>(r6)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                java.lang.String r4 = r5.readLine()     // Catch:{ Exception -> 0x0047 }
                int r1 = java.lang.Integer.parseInt(r4)     // Catch:{ Exception -> 0x0047 }
                io.netty.util.internal.logging.InternalLogger r4 = io.netty.util.NetUtil.logger     // Catch:{ Exception -> 0x0047 }
                boolean r4 = r4.isDebugEnabled()     // Catch:{ Exception -> 0x0047 }
                if (r4 == 0) goto L_0x0045
                io.netty.util.internal.logging.InternalLogger r4 = io.netty.util.NetUtil.logger     // Catch:{ Exception -> 0x0047 }
                java.lang.String r6 = "{}: {}"
                java.lang.Integer r7 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0047 }
                r4.debug(r6, r2, r7)     // Catch:{ Exception -> 0x0047 }
            L_0x0045:
                r4 = r5
                goto L_0x0079
            L_0x0047:
                r4 = move-exception
                goto L_0x0085
            L_0x0049:
                java.lang.String r5 = "io.netty.net.somaxconn.trySysctl"
                boolean r5 = io.netty.util.internal.SystemPropertyUtil.getBoolean(r5, r3)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                if (r5 == 0) goto L_0x006b
                java.lang.String r5 = "kern.ipc.somaxconn"
                java.lang.Integer r5 = io.netty.util.NetUtil.sysctlGetInt(r5)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                if (r5 != 0) goto L_0x0066
                java.lang.String r5 = "kern.ipc.soacceptqueue"
                java.lang.Integer r5 = io.netty.util.NetUtil.sysctlGetInt(r5)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                if (r5 == 0) goto L_0x006c
                int r1 = r5.intValue()     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                goto L_0x006c
            L_0x0066:
                int r1 = r5.intValue()     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                goto L_0x006c
            L_0x006b:
                r5 = r4
            L_0x006c:
                if (r5 != 0) goto L_0x0079
                io.netty.util.internal.logging.InternalLogger r5 = io.netty.util.NetUtil.logger     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                java.lang.Integer r6 = java.lang.Integer.valueOf(r1)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
                r5.debug(r0, r2, r6)     // Catch:{ Exception -> 0x0081, all -> 0x007f }
            L_0x0079:
                if (r4 == 0) goto L_0x00aa
                r4.close()     // Catch:{ Exception -> 0x00aa }
                goto L_0x00aa
            L_0x007f:
                r0 = move-exception
                goto L_0x00b1
            L_0x0081:
                r5 = move-exception
                r9 = r5
                r5 = r4
                r4 = r9
            L_0x0085:
                io.netty.util.internal.logging.InternalLogger r6 = io.netty.util.NetUtil.logger     // Catch:{ all -> 0x00af }
                boolean r6 = r6.isDebugEnabled()     // Catch:{ all -> 0x00af }
                if (r6 == 0) goto L_0x00a5
                io.netty.util.internal.logging.InternalLogger r6 = io.netty.util.NetUtil.logger     // Catch:{ all -> 0x00af }
                java.lang.Integer r7 = java.lang.Integer.valueOf(r1)     // Catch:{ all -> 0x00af }
                r8 = 3
                java.lang.Object[] r8 = new java.lang.Object[r8]     // Catch:{ all -> 0x00af }
                r8[r3] = r2     // Catch:{ all -> 0x00af }
                r2 = 1
                r8[r2] = r7     // Catch:{ all -> 0x00af }
                r2 = 2
                r8[r2] = r4     // Catch:{ all -> 0x00af }
                r6.debug((java.lang.String) r0, (java.lang.Object[]) r8)     // Catch:{ all -> 0x00af }
            L_0x00a5:
                if (r5 == 0) goto L_0x00aa
                r5.close()     // Catch:{ Exception -> 0x00aa }
            L_0x00aa:
                java.lang.Integer r0 = java.lang.Integer.valueOf(r1)
                return r0
            L_0x00af:
                r0 = move-exception
                r4 = r5
            L_0x00b1:
                if (r4 == 0) goto L_0x00b6
                r4.close()     // Catch:{ Exception -> 0x00b6 }
            L_0x00b6:
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.util.NetUtil.SoMaxConnAction.run():java.lang.Integer");
        }
    }

    /* access modifiers changed from: private */
    public static Integer sysctlGetInt(String str) throws IOException {
        BufferedReader bufferedReader;
        Process start = new ProcessBuilder(new String[]{"sysctl", str}).start();
        try {
            bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream()));
            String readLine = bufferedReader.readLine();
            if (readLine != null && readLine.startsWith(str)) {
                for (int length = readLine.length() - 1; length > str.length(); length--) {
                    if (!Character.isDigit(readLine.charAt(length))) {
                        Integer valueOf = Integer.valueOf(readLine.substring(length + 1));
                        bufferedReader.close();
                        start.destroy();
                        return valueOf;
                    }
                }
            }
            bufferedReader.close();
            start.destroy();
            return null;
        } catch (Throwable th) {
            start.destroy();
            throw th;
        }
    }

    public static boolean isIpV4StackPreferred() {
        return IPV4_PREFERRED;
    }

    public static boolean isIpV6AddressesPreferred() {
        return IPV6_ADDRESSES_PREFERRED;
    }

    public static byte[] createByteArrayFromIpAddressString(String str) {
        if (isValidIpV4Address(str)) {
            return validIpV4ToBytes(str);
        }
        if (!isValidIpV6Address(str)) {
            return null;
        }
        if (str.charAt(0) == '[') {
            str = str.substring(1, str.length() - 1);
        }
        int indexOf = str.indexOf(37);
        if (indexOf >= 0) {
            str = str.substring(0, indexOf);
        }
        return getIPv6ByName(str, true);
    }

    public static InetAddress createInetAddressFromIpAddressString(String str) {
        if (isValidIpV4Address(str)) {
            try {
                return InetAddress.getByAddress(validIpV4ToBytes(str));
            } catch (UnknownHostException e) {
                throw new IllegalStateException(e);
            }
        } else if (!isValidIpV6Address(str)) {
            return null;
        } else {
            if (str.charAt(0) == '[') {
                str = str.substring(1, str.length() - 1);
            }
            int indexOf = str.indexOf(37);
            if (indexOf >= 0) {
                try {
                    int parseInt = Integer.parseInt(str.substring(indexOf + 1));
                    byte[] iPv6ByName = getIPv6ByName(str.substring(0, indexOf), true);
                    if (iPv6ByName == null) {
                        return null;
                    }
                    return Inet6Address.getByAddress((String) null, iPv6ByName, parseInt);
                } catch (UnknownHostException e2) {
                    throw new IllegalStateException(e2);
                } catch (NumberFormatException unused) {
                    return null;
                }
            } else {
                byte[] iPv6ByName2 = getIPv6ByName(str, true);
                if (iPv6ByName2 == null) {
                    return null;
                }
                try {
                    return InetAddress.getByAddress(iPv6ByName2);
                } catch (UnknownHostException e3) {
                    throw new IllegalStateException(e3);
                }
            }
        }
    }

    private static int decimalDigit(String str, int i) {
        return str.charAt(i) - '0';
    }

    private static byte ipv4WordToByte(String str, int i, int i2) {
        int decimalDigit = decimalDigit(str, i);
        int i3 = i + 1;
        if (i3 == i2) {
            return (byte) decimalDigit;
        }
        int decimalDigit2 = (decimalDigit * 10) + decimalDigit(str, i3);
        int i4 = i + 2;
        if (i4 == i2) {
            return (byte) decimalDigit2;
        }
        return (byte) ((decimalDigit2 * 10) + decimalDigit(str, i4));
    }

    static byte[] validIpV4ToBytes(String str) {
        int indexOf = str.indexOf(46, 1);
        byte ipv4WordToByte = ipv4WordToByte(str, 0, indexOf);
        int i = indexOf + 1;
        int indexOf2 = str.indexOf(46, indexOf + 2);
        byte ipv4WordToByte2 = ipv4WordToByte(str, i, indexOf2);
        int indexOf3 = str.indexOf(46, indexOf2 + 2);
        return new byte[]{ipv4WordToByte, ipv4WordToByte2, ipv4WordToByte(str, indexOf2 + 1, indexOf3), ipv4WordToByte(str, indexOf3 + 1, str.length())};
    }

    public static int ipv4AddressToInt(Inet4Address inet4Address) {
        byte[] address = inet4Address.getAddress();
        return (address[3] & 255) | ((address[0] & 255) << Ascii.CAN) | ((address[1] & 255) << Ascii.DLE) | ((address[2] & 255) << 8);
    }

    public static String intToIpAddress(int i) {
        StringBuilder sb = new StringBuilder(15);
        sb.append((i >> 24) & 255);
        sb.append('.');
        sb.append((i >> 16) & 255);
        sb.append('.');
        sb.append((i >> 8) & 255);
        sb.append('.');
        sb.append(i & 255);
        return sb.toString();
    }

    public static String bytesToIpAddress(byte[] bArr) {
        return bytesToIpAddress(bArr, 0, bArr.length);
    }

    public static String bytesToIpAddress(byte[] bArr, int i, int i2) {
        if (i2 == 4) {
            StringBuilder sb = new StringBuilder(15);
            sb.append(bArr[i] & 255);
            sb.append('.');
            sb.append(bArr[i + 1] & 255);
            sb.append('.');
            sb.append(bArr[i + 2] & 255);
            sb.append('.');
            sb.append(bArr[i + 3] & 255);
            return sb.toString();
        } else if (i2 == 16) {
            return toAddressString(bArr, i, false);
        } else {
            throw new IllegalArgumentException("length: " + i2 + " (expected: 4 or 16)");
        }
    }

    public static boolean isValidIpV6Address(String str) {
        return isValidIpV6Address((CharSequence) str);
    }

    public static boolean isValidIpV6Address(CharSequence charSequence) {
        int i;
        int i2;
        int length = charSequence.length();
        int i3 = 2;
        if (length < 2) {
            return false;
        }
        char charAt = charSequence.charAt(0);
        if (charAt == '[') {
            length--;
            if (charSequence.charAt(length) != ']') {
                return false;
            }
            charAt = charSequence.charAt(1);
            i = 1;
        } else {
            i = 0;
        }
        if (charAt != ':') {
            i3 = 0;
            i2 = -1;
        } else if (charSequence.charAt(i + 1) != ':') {
            return false;
        } else {
            int i4 = i;
            i += 2;
            i2 = i4;
        }
        int i5 = i;
        int i6 = 0;
        while (true) {
            if (i5 >= length) {
                break;
            }
            char charAt2 = charSequence.charAt(i5);
            if (!isValidHexChar(charAt2)) {
                if (charAt2 == '%') {
                    length = i5;
                    break;
                } else if (charAt2 != '.') {
                    if (charAt2 != ':' || i3 > 7) {
                        return false;
                    }
                    int i7 = i5 - 1;
                    if (charSequence.charAt(i7) != ':') {
                        i6 = 0;
                    } else if (i2 >= 0) {
                        return false;
                    } else {
                        i2 = i7;
                    }
                    i3++;
                } else if ((i2 < 0 && i3 != 6) || ((i3 == 7 && i2 >= i) || i3 > 7)) {
                    return false;
                } else {
                    int i8 = i5 - i6;
                    int i9 = i8 - 2;
                    if (isValidIPv4MappedChar(charSequence.charAt(i9))) {
                        if (!isValidIPv4MappedChar(charSequence.charAt(i8 - 3)) || !isValidIPv4MappedChar(charSequence.charAt(i8 - 4)) || !isValidIPv4MappedChar(charSequence.charAt(i8 - 5))) {
                            return false;
                        }
                        i9 = i8 - 7;
                    }
                    while (i9 >= i) {
                        char charAt3 = charSequence.charAt(i9);
                        if (charAt3 != '0' && charAt3 != ':') {
                            return false;
                        }
                        i9--;
                    }
                    int indexOf = AsciiString.indexOf(charSequence, '%', i8 + 7);
                    if (indexOf >= 0) {
                        length = indexOf;
                    }
                    return isValidIpV4Address(charSequence, i8, length);
                }
            } else if (i6 >= 4) {
                return false;
            } else {
                i6++;
            }
            i5++;
        }
        if (i2 >= 0) {
            if (i2 + 2 != length) {
                if (i6 <= 0) {
                    return false;
                }
                if (i3 < 8 || i2 <= i) {
                    return true;
                }
                return false;
            }
            return true;
        } else if (i3 != 7 || i6 <= 0) {
            return false;
        } else {
            return true;
        }
    }

    private static boolean isValidIpV4Word(CharSequence charSequence, int i, int i2) {
        char charAt;
        char charAt2;
        int i3 = i2 - i;
        if (i3 < 1 || i3 > 3 || (charAt = charSequence.charAt(i)) < '0') {
            return false;
        }
        if (i3 == 3) {
            char charAt3 = charSequence.charAt(i + 1);
            if (charAt3 < '0' || (charAt2 = charSequence.charAt(i + 2)) < '0') {
                return false;
            }
            if (charAt > '1' || charAt3 > '9' || charAt2 > '9') {
                if (charAt != '2' || charAt3 > '5') {
                    return false;
                }
                if (charAt2 <= '5' || (charAt3 < '5' && charAt2 <= '9')) {
                    return true;
                }
                return false;
            }
            return true;
        } else if (charAt > '9') {
            return false;
        } else {
            if (i3 == 1 || isValidNumericChar(charSequence.charAt(i + 1))) {
                return true;
            }
            return false;
        }
    }

    private static boolean isValidIPv4Mapped(byte[] bArr, int i, int i2, int i3) {
        boolean z = i3 + i2 >= 14;
        if (i > 12 || i < 2 || ((z && i2 >= 12) || !isValidIPv4MappedSeparators(bArr[i - 1], bArr[i - 2], z) || !PlatformDependent.isZero(bArr, 0, i - 3))) {
            return false;
        }
        return true;
    }

    public static boolean isValidIpV4Address(CharSequence charSequence) {
        return isValidIpV4Address(charSequence, 0, charSequence.length());
    }

    public static boolean isValidIpV4Address(String str) {
        return isValidIpV4Address(str, 0, str.length());
    }

    private static boolean isValidIpV4Address(CharSequence charSequence, int i, int i2) {
        if (charSequence instanceof String) {
            return isValidIpV4Address((String) charSequence, i, i2);
        }
        if (charSequence instanceof AsciiString) {
            return isValidIpV4Address((AsciiString) charSequence, i, i2);
        }
        return isValidIpV4Address0(charSequence, i, i2);
    }

    private static boolean isValidIpV4Address(String str, int i, int i2) {
        int indexOf;
        int indexOf2;
        int indexOf3;
        int i3 = i2 - i;
        if (i3 > 15 || i3 < 7 || (indexOf = str.indexOf(46, i + 1)) <= 0 || !isValidIpV4Word(str, i, indexOf) || (indexOf2 = str.indexOf(46, indexOf + 2)) <= 0 || !isValidIpV4Word(str, indexOf + 1, indexOf2) || (indexOf3 = str.indexOf(46, indexOf2 + 2)) <= 0 || !isValidIpV4Word(str, indexOf2 + 1, indexOf3) || !isValidIpV4Word(str, indexOf3 + 1, i2)) {
            return false;
        }
        return true;
    }

    private static boolean isValidIpV4Address(AsciiString asciiString, int i, int i2) {
        int indexOf;
        int indexOf2;
        int indexOf3;
        int i3 = i2 - i;
        if (i3 > 15 || i3 < 7 || (indexOf = asciiString.indexOf('.', i + 1)) <= 0 || !isValidIpV4Word(asciiString, i, indexOf) || (indexOf2 = asciiString.indexOf('.', indexOf + 2)) <= 0 || !isValidIpV4Word(asciiString, indexOf + 1, indexOf2) || (indexOf3 = asciiString.indexOf('.', indexOf2 + 2)) <= 0 || !isValidIpV4Word(asciiString, indexOf2 + 1, indexOf3) || !isValidIpV4Word(asciiString, indexOf3 + 1, i2)) {
            return false;
        }
        return true;
    }

    private static boolean isValidIpV4Address0(CharSequence charSequence, int i, int i2) {
        int indexOf;
        int indexOf2;
        int indexOf3;
        int i3 = i2 - i;
        if (i3 > 15 || i3 < 7 || (indexOf = AsciiString.indexOf(charSequence, '.', i + 1)) <= 0 || !isValidIpV4Word(charSequence, i, indexOf) || (indexOf2 = AsciiString.indexOf(charSequence, '.', indexOf + 2)) <= 0 || !isValidIpV4Word(charSequence, indexOf + 1, indexOf2) || (indexOf3 = AsciiString.indexOf(charSequence, '.', indexOf2 + 2)) <= 0 || !isValidIpV4Word(charSequence, indexOf2 + 1, indexOf3) || !isValidIpV4Word(charSequence, indexOf3 + 1, i2)) {
            return false;
        }
        return true;
    }

    public static Inet6Address getByName(CharSequence charSequence) {
        return getByName(charSequence, true);
    }

    public static Inet6Address getByName(CharSequence charSequence, boolean z) {
        byte[] iPv6ByName = getIPv6ByName(charSequence, z);
        if (iPv6ByName == null) {
            return null;
        }
        try {
            return Inet6Address.getByAddress((String) null, iPv6ByName, -1);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:106:0x0188, code lost:
        if (r7 <= 2) goto L_0x018b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:125:0x01d4, code lost:
        if (r0.charAt(r12) != ':') goto L_0x01db;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:141:0x0203, code lost:
        if (r3 == ':') goto L_0x0208;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x015b, code lost:
        if ((r6 - r9) <= 3) goto L_0x015f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0176, code lost:
        if (r4 == ':') goto L_0x017c;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static byte[] getIPv6ByName(java.lang.CharSequence r17, boolean r18) {
        /*
            r0 = r17
            r1 = 16
            byte[] r2 = new byte[r1]
            int r3 = r17.length()
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = -1
            r10 = 0
            r11 = 0
            r12 = 0
            r13 = 0
        L_0x0012:
            r16 = 0
            r15 = 58
            r4 = 4
            r5 = 2
            if (r6 >= r3) goto L_0x014f
            char r1 = r0.charAt(r6)
            r14 = 46
            if (r1 == r14) goto L_0x00ab
            if (r1 == r15) goto L_0x0049
            boolean r14 = isValidHexChar(r1)
            if (r14 == 0) goto L_0x0048
            if (r8 <= 0) goto L_0x0033
            boolean r14 = isValidNumericChar(r1)
            if (r14 != 0) goto L_0x0033
            goto L_0x0048
        L_0x0033:
            if (r9 >= 0) goto L_0x0037
            r9 = r6
            goto L_0x003c
        L_0x0037:
            int r14 = r6 - r9
            if (r14 <= r4) goto L_0x003c
            return r16
        L_0x003c:
            int r1 = io.netty.util.internal.StringUtil.decodeHexNibble((char) r1)
            int r4 = r6 - r9
            int r4 = r4 << r5
            int r1 = r1 << r4
            int r13 = r13 + r1
            r1 = 1
            goto L_0x0149
        L_0x0048:
            return r16
        L_0x0049:
            int r1 = r10 + 1
            int r9 = r6 - r9
            if (r9 > r4) goto L_0x00aa
            if (r8 > 0) goto L_0x00aa
            r14 = 8
            if (r1 > r14) goto L_0x00aa
            int r14 = r11 + 1
            r15 = 16
            if (r14 < r15) goto L_0x005c
            goto L_0x00aa
        L_0x005c:
            int r9 = 4 - r9
            int r5 = r9 << 2
            int r5 = r13 << r5
            if (r12 <= 0) goto L_0x0066
            int r12 = r12 + -2
        L_0x0066:
            r9 = r5 & 15
            int r9 = r9 << r4
            int r13 = r5 >> 4
            r13 = r13 & 15
            r9 = r9 | r13
            byte r9 = (byte) r9
            r2[r11] = r9
            int r11 = r11 + 2
            int r9 = r5 >> 8
            r9 = r9 & 15
            int r4 = r9 << 4
            r9 = 12
            int r5 = r5 >> r9
            r5 = r5 & 15
            r4 = r4 | r5
            byte r4 = (byte) r4
            r2[r14] = r4
            int r4 = r6 + 1
            if (r4 >= r3) goto L_0x00a4
            char r5 = r0.charAt(r4)
            r9 = 58
            if (r5 != r9) goto L_0x00a4
            int r6 = r6 + 2
            if (r7 != 0) goto L_0x00a3
            if (r6 >= r3) goto L_0x009b
            char r1 = r0.charAt(r6)
            if (r1 != r9) goto L_0x009b
            goto L_0x00a3
        L_0x009b:
            int r10 = r10 + 2
            int r1 = 14 - r11
            r12 = r1
            r6 = r4
            r7 = r11
            goto L_0x00a5
        L_0x00a3:
            return r16
        L_0x00a4:
            r10 = r1
        L_0x00a5:
            r1 = 1
            r9 = -1
            r13 = 0
            goto L_0x0149
        L_0x00aa:
            return r16
        L_0x00ab:
            int r8 = r8 + 1
            int r1 = r6 - r9
            r4 = 3
            if (r1 > r4) goto L_0x014e
            if (r9 < 0) goto L_0x014e
            if (r8 > r4) goto L_0x014e
            if (r10 <= 0) goto L_0x00be
            int r4 = r11 + r12
            r9 = 12
            if (r4 < r9) goto L_0x014e
        L_0x00be:
            int r4 = r6 + 1
            if (r4 >= r3) goto L_0x014e
            r4 = 16
            if (r11 >= r4) goto L_0x014e
            r4 = 1
            if (r8 != r4) goto L_0x0124
            if (r18 == 0) goto L_0x014e
            if (r11 == 0) goto L_0x00d3
            boolean r4 = isValidIPv4Mapped(r2, r11, r7, r12)
            if (r4 == 0) goto L_0x014e
        L_0x00d3:
            r4 = 3
            if (r1 != r4) goto L_0x00fa
            int r4 = r6 + -1
            char r4 = r0.charAt(r4)
            boolean r4 = isValidNumericChar(r4)
            if (r4 == 0) goto L_0x014e
            int r4 = r6 + -2
            char r4 = r0.charAt(r4)
            boolean r4 = isValidNumericChar(r4)
            if (r4 == 0) goto L_0x014e
            int r4 = r6 + -3
            char r4 = r0.charAt(r4)
            boolean r4 = isValidNumericChar(r4)
            if (r4 == 0) goto L_0x014e
        L_0x00fa:
            if (r1 != r5) goto L_0x0114
            int r4 = r6 + -1
            char r4 = r0.charAt(r4)
            boolean r4 = isValidNumericChar(r4)
            if (r4 == 0) goto L_0x014e
            int r4 = r6 + -2
            char r4 = r0.charAt(r4)
            boolean r4 = isValidNumericChar(r4)
            if (r4 == 0) goto L_0x014e
        L_0x0114:
            r4 = 1
            if (r1 != r4) goto L_0x0124
            int r4 = r6 + -1
            char r4 = r0.charAt(r4)
            boolean r4 = isValidNumericChar(r4)
            if (r4 != 0) goto L_0x0124
            goto L_0x014e
        L_0x0124:
            int r1 = 3 - r1
            int r1 = r1 << r5
            int r1 = r13 << r1
            r4 = r1 & 15
            int r4 = r4 * 100
            int r5 = r1 >> 4
            r5 = r5 & 15
            r9 = 10
            int r5 = r5 * 10
            int r4 = r4 + r5
            r5 = 8
            int r1 = r1 >> r5
            r1 = r1 & 15
            int r4 = r4 + r1
            r1 = 255(0xff, float:3.57E-43)
            if (r4 <= r1) goto L_0x0141
            return r16
        L_0x0141:
            int r1 = r11 + 1
            byte r4 = (byte) r4
            r2[r11] = r4
            r11 = r1
            goto L_0x00a5
        L_0x0149:
            int r6 = r6 + r1
            r1 = 16
            goto L_0x0012
        L_0x014e:
            return r16
        L_0x014f:
            r1 = 1
            if (r7 <= 0) goto L_0x0153
            goto L_0x0154
        L_0x0153:
            r1 = 0
        L_0x0154:
            if (r8 <= 0) goto L_0x01b5
            if (r9 <= 0) goto L_0x015e
            int r3 = r6 - r9
            r4 = 3
            if (r3 > r4) goto L_0x01b4
            goto L_0x015f
        L_0x015e:
            r4 = 3
        L_0x015f:
            if (r8 != r4) goto L_0x01b4
            r3 = 16
            if (r11 < r3) goto L_0x0166
            goto L_0x01b4
        L_0x0166:
            if (r10 == 0) goto L_0x018b
            if (r10 < r5) goto L_0x018a
            if (r1 != 0) goto L_0x0179
            r3 = 6
            if (r10 != r3) goto L_0x0179
            r3 = 0
            char r4 = r0.charAt(r3)
            r12 = 58
            if (r4 != r12) goto L_0x018b
            goto L_0x017c
        L_0x0179:
            r3 = 0
            r12 = 58
        L_0x017c:
            if (r1 == 0) goto L_0x018a
            r1 = 8
            if (r10 >= r1) goto L_0x018a
            char r0 = r0.charAt(r3)
            if (r0 != r12) goto L_0x018b
            if (r7 <= r5) goto L_0x018b
        L_0x018a:
            return r16
        L_0x018b:
            int r6 = r6 - r9
            r0 = 3
            int r4 = 3 - r6
            int r0 = r4 << 2
            int r0 = r13 << r0
            r1 = r0 & 15
            int r1 = r1 * 100
            int r3 = r0 >> 4
            r3 = r3 & 15
            r4 = 10
            int r3 = r3 * 10
            int r1 = r1 + r3
            r3 = 8
            int r0 = r0 >> r3
            r0 = r0 & 15
            int r1 = r1 + r0
            r0 = 255(0xff, float:3.57E-43)
            if (r1 <= r0) goto L_0x01ab
            return r16
        L_0x01ab:
            int r0 = r11 + 1
            byte r1 = (byte) r1
            r2[r11] = r1
        L_0x01b0:
            r1 = 16
            goto L_0x023f
        L_0x01b4:
            return r16
        L_0x01b5:
            int r12 = r3 + -1
            if (r9 <= 0) goto L_0x01bd
            int r14 = r6 - r9
            if (r14 > r4) goto L_0x0257
        L_0x01bd:
            if (r10 < r5) goto L_0x0257
            if (r1 != 0) goto L_0x01d7
            int r14 = r10 + 1
            r15 = 8
            if (r14 != r15) goto L_0x0257
            r14 = 0
            char r4 = r0.charAt(r14)
            r14 = 58
            if (r4 == r14) goto L_0x0257
            char r4 = r0.charAt(r12)
            if (r4 == r14) goto L_0x0257
            goto L_0x01db
        L_0x01d7:
            r14 = 58
            r15 = 8
        L_0x01db:
            if (r1 == 0) goto L_0x01f4
            if (r10 > r15) goto L_0x0257
            if (r10 != r15) goto L_0x01f4
            if (r7 > r5) goto L_0x01ea
            r1 = 0
            char r4 = r0.charAt(r1)
            if (r4 != r14) goto L_0x0257
        L_0x01ea:
            r1 = 14
            if (r7 < r1) goto L_0x01f4
            char r1 = r0.charAt(r12)
            if (r1 != r14) goto L_0x0257
        L_0x01f4:
            int r1 = r11 + 1
            r4 = 16
            if (r1 >= r4) goto L_0x0257
            if (r9 >= 0) goto L_0x0206
            int r3 = r3 - r5
            char r3 = r0.charAt(r3)
            r4 = 58
            if (r3 != r4) goto L_0x0257
            goto L_0x0208
        L_0x0206:
            r4 = 58
        L_0x0208:
            if (r7 <= r5) goto L_0x0212
            r3 = 0
            char r0 = r0.charAt(r3)
            if (r0 != r4) goto L_0x0212
            goto L_0x0257
        L_0x0212:
            if (r9 < 0) goto L_0x021e
            int r6 = r6 - r9
            r0 = 4
            if (r6 > r0) goto L_0x021f
            int r4 = 4 - r6
            int r3 = r4 << 2
            int r13 = r13 << r3
            goto L_0x021f
        L_0x021e:
            r0 = 4
        L_0x021f:
            r3 = r13 & 15
            int r3 = r3 << r0
            int r4 = r13 >> 4
            r4 = r4 & 15
            r3 = r3 | r4
            byte r3 = (byte) r3
            r2[r11] = r3
            int r3 = r11 + 2
            int r4 = r13 >> 8
            r4 = r4 & 15
            int r0 = r4 << 4
            r4 = 12
            int r4 = r13 >> 12
            r4 = r4 & 15
            r0 = r0 | r4
            byte r0 = (byte) r0
            r2[r1] = r0
            r0 = r3
            goto L_0x01b0
        L_0x023f:
            if (r0 >= r1) goto L_0x024b
            int r0 = r0 - r7
            int r1 = 16 - r0
            java.lang.System.arraycopy(r2, r7, r2, r1, r0)
            r0 = 0
            java.util.Arrays.fill(r2, r7, r1, r0)
        L_0x024b:
            if (r8 <= 0) goto L_0x0256
            r0 = 11
            r1 = -1
            r2[r0] = r1
            r0 = 10
            r2[r0] = r1
        L_0x0256:
            return r2
        L_0x0257:
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.util.NetUtil.getIPv6ByName(java.lang.CharSequence, boolean):byte[]");
    }

    public static String toSocketAddressString(InetSocketAddress inetSocketAddress) {
        StringBuilder sb;
        String valueOf = String.valueOf(inetSocketAddress.getPort());
        if (inetSocketAddress.isUnresolved()) {
            String hostname = getHostname(inetSocketAddress);
            sb = newSocketAddressStringBuilder(hostname, valueOf, !isValidIpV6Address(hostname));
        } else {
            InetAddress address = inetSocketAddress.getAddress();
            sb = newSocketAddressStringBuilder(toAddressString(address), valueOf, address instanceof Inet4Address);
        }
        sb.append(AbstractJsonLexerKt.COLON);
        sb.append(valueOf);
        return sb.toString();
    }

    public static String toSocketAddressString(String str, int i) {
        String valueOf = String.valueOf(i);
        StringBuilder newSocketAddressStringBuilder = newSocketAddressStringBuilder(str, valueOf, !isValidIpV6Address(str));
        newSocketAddressStringBuilder.append(AbstractJsonLexerKt.COLON);
        newSocketAddressStringBuilder.append(valueOf);
        return newSocketAddressStringBuilder.toString();
    }

    private static StringBuilder newSocketAddressStringBuilder(String str, String str2, boolean z) {
        int length = str.length();
        if (z) {
            StringBuilder sb = new StringBuilder(length + 1 + str2.length());
            sb.append(str);
            return sb;
        }
        StringBuilder sb2 = new StringBuilder(length + 3 + str2.length());
        if (length > 1 && str.charAt(0) == '[' && str.charAt(length - 1) == ']') {
            sb2.append(str);
            return sb2;
        }
        sb2.append(AbstractJsonLexerKt.BEGIN_LIST);
        sb2.append(str);
        sb2.append(AbstractJsonLexerKt.END_LIST);
        return sb2;
    }

    public static String toAddressString(InetAddress inetAddress) {
        return toAddressString(inetAddress, false);
    }

    public static String toAddressString(InetAddress inetAddress, boolean z) {
        if (inetAddress instanceof Inet4Address) {
            return inetAddress.getHostAddress();
        }
        if (inetAddress instanceof Inet6Address) {
            return toAddressString(inetAddress.getAddress(), 0, z);
        }
        throw new IllegalArgumentException("Unhandled type: " + inetAddress);
    }

    private static String toAddressString(byte[] bArr, int i, boolean z) {
        int i2;
        int i3;
        int[] iArr = new int[8];
        boolean z2 = false;
        int i4 = 0;
        while (true) {
            i2 = 1;
            if (i4 >= 8) {
                break;
            }
            int i5 = (i4 << 1) + i;
            iArr[i4] = (bArr[i5 + 1] & 255) | ((bArr[i5] & 255) << 8);
            i4++;
        }
        int i6 = -1;
        int i7 = -1;
        int i8 = 0;
        int i9 = -1;
        for (int i10 = 0; i10 < 8; i10++) {
            if (iArr[i10] == 0) {
                if (i7 < 0) {
                    i7 = i10;
                }
            } else if (i7 >= 0) {
                int i11 = i10 - i7;
                if (i11 > i8) {
                    i8 = i11;
                } else {
                    i7 = i9;
                }
                i9 = i7;
                i7 = -1;
            }
        }
        if (i7 < 0 || (i3 = 8 - i7) <= i8) {
            i7 = i9;
        } else {
            i8 = i3;
        }
        if (i8 == 1) {
            i8 = 0;
        } else {
            i6 = i7;
        }
        int i12 = i8 + i6;
        StringBuilder sb = new StringBuilder(39);
        if (i12 < 0) {
            sb.append(Integer.toHexString(iArr[0]));
            while (i2 < 8) {
                sb.append(AbstractJsonLexerKt.COLON);
                sb.append(Integer.toHexString(iArr[i2]));
                i2++;
            }
        } else {
            if (inRangeEndExclusive(0, i6, i12)) {
                sb.append("::");
                if (z && i12 == 5 && iArr[5] == 65535) {
                    z2 = true;
                }
            } else {
                sb.append(Integer.toHexString(iArr[0]));
            }
            while (i2 < 8) {
                if (!inRangeEndExclusive(i2, i6, i12)) {
                    if (!inRangeEndExclusive(i2 - 1, i6, i12)) {
                        if (!z2 || i2 == 6) {
                            sb.append(AbstractJsonLexerKt.COLON);
                        } else {
                            sb.append('.');
                        }
                    }
                    if (!z2 || i2 <= 5) {
                        sb.append(Integer.toHexString(iArr[i2]));
                    } else {
                        sb.append(iArr[i2] >> 8);
                        sb.append('.');
                        sb.append(iArr[i2] & 255);
                    }
                } else if (!inRangeEndExclusive(i2 - 1, i6, i12)) {
                    sb.append("::");
                }
                i2++;
            }
        }
        return sb.toString();
    }

    public static String getHostname(InetSocketAddress inetSocketAddress) {
        return PlatformDependent.javaVersion() >= 7 ? inetSocketAddress.getHostString() : inetSocketAddress.getHostName();
    }

    private NetUtil() {
    }
}
