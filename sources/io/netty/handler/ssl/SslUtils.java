package io.netty.handler.ssl;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.ByteBufUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.base64.Base64;
import io.netty.handler.codec.base64.Base64Dialect;
import io.netty.util.NetUtil;
import io.netty.util.internal.EmptyArrays;
import io.netty.util.internal.StringUtil;
import io.netty.util.internal.logging.InternalLogger;
import io.netty.util.internal.logging.InternalLoggerFactory;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Provider;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.net.ssl.KeyManager;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLHandshakeException;
import javax.net.ssl.TrustManager;

final class SslUtils {
    static final String[] DEFAULT_CIPHER_SUITES;
    static final String[] DEFAULT_TLSV13_CIPHER_SUITES;
    static final short DTLS_1_0 = -257;
    static final short DTLS_1_2 = -259;
    static final short DTLS_1_3 = -260;
    static final short DTLS_RECORD_HEADER_LENGTH = 13;
    static final int GMSSL_PROTOCOL_VERSION = 257;
    static final String INVALID_CIPHER = "SSL_NULL_WITH_NULL_NULL";
    static final int NOT_ENCRYPTED = -2;
    static final int NOT_ENOUGH_DATA = -1;
    static final int SSL_CONTENT_TYPE_ALERT = 21;
    static final int SSL_CONTENT_TYPE_APPLICATION_DATA = 23;
    static final int SSL_CONTENT_TYPE_CHANGE_CIPHER_SPEC = 20;
    static final int SSL_CONTENT_TYPE_EXTENSION_HEARTBEAT = 24;
    static final int SSL_CONTENT_TYPE_HANDSHAKE = 22;
    static final int SSL_RECORD_HEADER_LENGTH = 5;
    static final Set<String> TLSV13_CIPHERS = Collections.unmodifiableSet(new LinkedHashSet(Arrays.asList(new String[]{Ciphers.TLS_AES_256_GCM_SHA384, Ciphers.TLS_CHACHA20_POLY1305_SHA256, Ciphers.TLS_AES_128_GCM_SHA256, "TLS_AES_128_CCM_8_SHA256", "TLS_AES_128_CCM_SHA256"})));
    static final String[] TLSV13_CIPHER_SUITES;
    private static final boolean TLSV1_3_JDK_DEFAULT_ENABLED = isTLSv13EnabledByJDK0((Provider) null);
    private static final boolean TLSV1_3_JDK_SUPPORTED;
    private static final InternalLogger logger = InternalLoggerFactory.getInstance((Class<?>) SslUtils.class);

    private static short unsignedByte(byte b) {
        return (short) (b & 255);
    }

    static {
        String[] strArr = {Ciphers.TLS_AES_128_GCM_SHA256, Ciphers.TLS_AES_256_GCM_SHA384};
        TLSV13_CIPHER_SUITES = strArr;
        boolean isTLSv13SupportedByJDK0 = isTLSv13SupportedByJDK0((Provider) null);
        TLSV1_3_JDK_SUPPORTED = isTLSv13SupportedByJDK0;
        if (isTLSv13SupportedByJDK0) {
            DEFAULT_TLSV13_CIPHER_SUITES = strArr;
        } else {
            DEFAULT_TLSV13_CIPHER_SUITES = EmptyArrays.EMPTY_STRINGS;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(Ciphers.TLS_ECDHE_ECDSA_WITH_AES_256_GCM_SHA384);
        linkedHashSet.add(Ciphers.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256);
        linkedHashSet.add(Ciphers.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256);
        linkedHashSet.add(Ciphers.TLS_ECDHE_RSA_WITH_AES_256_GCM_SHA384);
        linkedHashSet.add(Ciphers.TLS_ECDHE_RSA_WITH_AES_128_CBC_SHA);
        linkedHashSet.add(Ciphers.TLS_ECDHE_RSA_WITH_AES_256_CBC_SHA);
        linkedHashSet.add(Ciphers.TLS_RSA_WITH_AES_128_GCM_SHA256);
        linkedHashSet.add(Ciphers.TLS_RSA_WITH_AES_128_CBC_SHA);
        linkedHashSet.add(Ciphers.TLS_RSA_WITH_AES_256_CBC_SHA);
        Collections.addAll(linkedHashSet, DEFAULT_TLSV13_CIPHER_SUITES);
        DEFAULT_CIPHER_SUITES = (String[]) linkedHashSet.toArray(EmptyArrays.EMPTY_STRINGS);
    }

    static boolean isTLSv13SupportedByJDK(Provider provider) {
        if (provider == null) {
            return TLSV1_3_JDK_SUPPORTED;
        }
        return isTLSv13SupportedByJDK0(provider);
    }

    private static boolean isTLSv13SupportedByJDK0(Provider provider) {
        try {
            return arrayContains(newInitContext(provider).getSupportedSSLParameters().getProtocols(), SslProtocols.TLS_v1_3);
        } catch (Throwable th) {
            logger.debug("Unable to detect if JDK SSLEngine with provider {} supports TLSv1.3, assuming no", provider, th);
            return false;
        }
    }

    static boolean isTLSv13EnabledByJDK(Provider provider) {
        if (provider == null) {
            return TLSV1_3_JDK_DEFAULT_ENABLED;
        }
        return isTLSv13EnabledByJDK0(provider);
    }

    private static boolean isTLSv13EnabledByJDK0(Provider provider) {
        try {
            return arrayContains(newInitContext(provider).getDefaultSSLParameters().getProtocols(), SslProtocols.TLS_v1_3);
        } catch (Throwable th) {
            logger.debug("Unable to detect if JDK SSLEngine with provider {} enables TLSv1.3 by default, assuming no", provider, th);
            return false;
        }
    }

    private static SSLContext newInitContext(Provider provider) throws NoSuchAlgorithmException, KeyManagementException {
        SSLContext sSLContext;
        if (provider == null) {
            sSLContext = SSLContext.getInstance("TLS");
        } else {
            sSLContext = SSLContext.getInstance("TLS", provider);
        }
        sSLContext.init((KeyManager[]) null, new TrustManager[0], (SecureRandom) null);
        return sSLContext;
    }

    static SSLContext getSSLContext(String str) throws NoSuchAlgorithmException, KeyManagementException, NoSuchProviderException {
        SSLContext sSLContext;
        if (StringUtil.isNullOrEmpty(str)) {
            sSLContext = SSLContext.getInstance(getTlsVersion());
        } else {
            sSLContext = SSLContext.getInstance(getTlsVersion(), str);
        }
        sSLContext.init((KeyManager[]) null, new TrustManager[0], (SecureRandom) null);
        return sSLContext;
    }

    private static String getTlsVersion() {
        return TLSV1_3_JDK_SUPPORTED ? SslProtocols.TLS_v1_3 : SslProtocols.TLS_v1_2;
    }

    static boolean arrayContains(String[] strArr, String str) {
        for (String equals : strArr) {
            if (str.equals(equals)) {
                return true;
            }
        }
        return false;
    }

    static void addIfSupported(Set<String> set, List<String> list, String... strArr) {
        for (String str : strArr) {
            if (set.contains(str)) {
                list.add(str);
            }
        }
    }

    static void useFallbackCiphersIfDefaultIsEmpty(List<String> list, Iterable<String> iterable) {
        if (list.isEmpty()) {
            for (String next : iterable) {
                if (!next.startsWith("SSL_") && !next.contains("_RC4_")) {
                    list.add(next);
                }
            }
        }
    }

    static void useFallbackCiphersIfDefaultIsEmpty(List<String> list, String... strArr) {
        useFallbackCiphersIfDefaultIsEmpty(list, (Iterable<String>) Arrays.asList(strArr));
    }

    static SSLHandshakeException toSSLHandshakeException(Throwable th) {
        if (th instanceof SSLHandshakeException) {
            return (SSLHandshakeException) th;
        }
        return (SSLHandshakeException) new SSLHandshakeException(th.getMessage()).initCause(th);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0048, code lost:
        if (r5 <= 5) goto L_0x004f;
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0051  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static int getEncryptedPacketLength(io.netty.buffer.ByteBuf r7, int r8) {
        /*
            short r0 = r7.getUnsignedByte(r8)
            r1 = 1
            r2 = 0
            switch(r0) {
                case 20: goto L_0x000b;
                case 21: goto L_0x000b;
                case 22: goto L_0x000b;
                case 23: goto L_0x000b;
                case 24: goto L_0x000b;
                default: goto L_0x0009;
            }
        L_0x0009:
            r0 = 0
            goto L_0x000c
        L_0x000b:
            r0 = 1
        L_0x000c:
            r3 = -1
            r4 = 3
            if (r0 == 0) goto L_0x004d
            int r5 = r8 + 1
            short r6 = r7.getUnsignedByte(r5)
            short r5 = r7.getShort(r5)
            if (r6 == r4) goto L_0x0040
            r6 = 257(0x101, float:3.6E-43)
            if (r5 != r6) goto L_0x0021
            goto L_0x0040
        L_0x0021:
            r6 = -257(0xfffffffffffffeff, float:NaN)
            if (r5 == r6) goto L_0x002d
            r6 = -259(0xfffffffffffffefd, float:NaN)
            if (r5 == r6) goto L_0x002d
            r6 = -260(0xfffffffffffffefc, float:NaN)
            if (r5 != r6) goto L_0x004e
        L_0x002d:
            int r2 = r7.readableBytes()
            int r5 = r8 + 13
            if (r2 >= r5) goto L_0x0036
            return r3
        L_0x0036:
            int r2 = r8 + 11
            int r2 = unsignedShortBE((io.netty.buffer.ByteBuf) r7, (int) r2)
            int r2 = r2 + 13
            r5 = r2
            goto L_0x004b
        L_0x0040:
            int r5 = r8 + 3
            int r5 = unsignedShortBE((io.netty.buffer.ByteBuf) r7, (int) r5)
            r6 = 5
            int r5 = r5 + r6
            if (r5 > r6) goto L_0x004b
            goto L_0x004f
        L_0x004b:
            r2 = r0
            goto L_0x004f
        L_0x004d:
            r2 = r0
        L_0x004e:
            r5 = 0
        L_0x004f:
            if (r2 != 0) goto L_0x0080
            short r0 = r7.getUnsignedByte(r8)
            r0 = r0 & 128(0x80, float:1.794E-43)
            r2 = 2
            if (r0 == 0) goto L_0x005c
            r0 = 2
            goto L_0x005d
        L_0x005c:
            r0 = 3
        L_0x005d:
            int r5 = r8 + r0
            int r5 = r5 + r1
            short r1 = r7.getUnsignedByte(r5)
            if (r1 == r2) goto L_0x006b
            if (r1 != r4) goto L_0x0069
            goto L_0x006b
        L_0x0069:
            r7 = -2
            return r7
        L_0x006b:
            if (r0 != r2) goto L_0x0075
            short r7 = shortBE((io.netty.buffer.ByteBuf) r7, (int) r8)
            r7 = r7 & 32767(0x7fff, float:4.5916E-41)
            int r7 = r7 + r2
            goto L_0x007c
        L_0x0075:
            short r7 = shortBE((io.netty.buffer.ByteBuf) r7, (int) r8)
            r7 = r7 & 16383(0x3fff, float:2.2957E-41)
            int r7 = r7 + r4
        L_0x007c:
            r5 = r7
            if (r5 > r0) goto L_0x0080
            return r3
        L_0x0080:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslUtils.getEncryptedPacketLength(io.netty.buffer.ByteBuf, int):int");
    }

    private static int unsignedShortBE(ByteBuf byteBuf, int i) {
        int unsignedShort = byteBuf.getUnsignedShort(i);
        return byteBuf.order() == ByteOrder.LITTLE_ENDIAN ? Integer.reverseBytes(unsignedShort) >>> 16 : unsignedShort;
    }

    private static short shortBE(ByteBuf byteBuf, int i) {
        short s = byteBuf.getShort(i);
        return byteBuf.order() == ByteOrder.LITTLE_ENDIAN ? Short.reverseBytes(s) : s;
    }

    private static int unsignedShortBE(ByteBuffer byteBuffer, int i) {
        return shortBE(byteBuffer, i) & 65535;
    }

    private static short shortBE(ByteBuffer byteBuffer, int i) {
        return byteBuffer.order() == ByteOrder.BIG_ENDIAN ? byteBuffer.getShort(i) : ByteBufUtil.swapShort(byteBuffer.getShort(i));
    }

    static int getEncryptedPacketLength(ByteBuffer[] byteBufferArr, int i) {
        ByteBuffer byteBuffer = byteBufferArr[i];
        if (byteBuffer.remaining() >= 5) {
            return getEncryptedPacketLength(byteBuffer);
        }
        ByteBuffer allocate = ByteBuffer.allocate(5);
        while (true) {
            int i2 = i + 1;
            ByteBuffer duplicate = byteBufferArr[i].duplicate();
            if (duplicate.remaining() > allocate.remaining()) {
                duplicate.limit(duplicate.position() + allocate.remaining());
            }
            allocate.put(duplicate);
            if (!allocate.hasRemaining()) {
                allocate.flip();
                return getEncryptedPacketLength(allocate);
            }
            i = i2;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static int getEncryptedPacketLength(java.nio.ByteBuffer r7) {
        /*
            int r0 = r7.position()
            byte r1 = r7.get(r0)
            short r1 = unsignedByte(r1)
            r2 = 1
            r3 = 0
            switch(r1) {
                case 20: goto L_0x0013;
                case 21: goto L_0x0013;
                case 22: goto L_0x0013;
                case 23: goto L_0x0013;
                case 24: goto L_0x0013;
                default: goto L_0x0011;
            }
        L_0x0011:
            r1 = 0
            goto L_0x0014
        L_0x0013:
            r1 = 1
        L_0x0014:
            r4 = 3
            if (r1 == 0) goto L_0x0038
            int r5 = r0 + 1
            byte r6 = r7.get(r5)
            short r6 = unsignedByte(r6)
            if (r6 == r4) goto L_0x002b
            short r5 = r7.getShort(r5)
            r6 = 257(0x101, float:3.6E-43)
            if (r5 != r6) goto L_0x0039
        L_0x002b:
            int r5 = r0 + 3
            int r5 = unsignedShortBE((java.nio.ByteBuffer) r7, (int) r5)
            r6 = 5
            int r5 = r5 + r6
            if (r5 > r6) goto L_0x0036
            goto L_0x003a
        L_0x0036:
            r3 = r1
            goto L_0x003a
        L_0x0038:
            r3 = r1
        L_0x0039:
            r5 = 0
        L_0x003a:
            if (r3 != 0) goto L_0x0074
            byte r1 = r7.get(r0)
            short r1 = unsignedByte(r1)
            r1 = r1 & 128(0x80, float:1.794E-43)
            r3 = 2
            if (r1 == 0) goto L_0x004b
            r1 = 2
            goto L_0x004c
        L_0x004b:
            r1 = 3
        L_0x004c:
            int r5 = r0 + r1
            int r5 = r5 + r2
            byte r2 = r7.get(r5)
            short r2 = unsignedByte(r2)
            if (r2 == r3) goto L_0x005e
            if (r2 != r4) goto L_0x005c
            goto L_0x005e
        L_0x005c:
            r7 = -2
            return r7
        L_0x005e:
            if (r1 != r3) goto L_0x0068
            short r7 = shortBE((java.nio.ByteBuffer) r7, (int) r0)
            r7 = r7 & 32767(0x7fff, float:4.5916E-41)
            int r7 = r7 + r3
            goto L_0x006f
        L_0x0068:
            short r7 = shortBE((java.nio.ByteBuffer) r7, (int) r0)
            r7 = r7 & 16383(0x3fff, float:2.2957E-41)
            int r7 = r7 + r4
        L_0x006f:
            r5 = r7
            if (r5 > r1) goto L_0x0074
            r7 = -1
            return r7
        L_0x0074:
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.ssl.SslUtils.getEncryptedPacketLength(java.nio.ByteBuffer):int");
    }

    static void handleHandshakeFailure(ChannelHandlerContext channelHandlerContext, Throwable th, boolean z) {
        channelHandlerContext.flush();
        if (z) {
            channelHandlerContext.fireUserEventTriggered(new SslHandshakeCompletionEvent(th));
        }
        channelHandlerContext.close();
    }

    static void zeroout(ByteBuf byteBuf) {
        if (!byteBuf.isReadOnly()) {
            byteBuf.setZero(0, byteBuf.capacity());
        }
    }

    static void zerooutAndRelease(ByteBuf byteBuf) {
        zeroout(byteBuf);
        byteBuf.release();
    }

    static ByteBuf toBase64(ByteBufAllocator byteBufAllocator, ByteBuf byteBuf) {
        ByteBuf encode = Base64.encode(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), true, Base64Dialect.STANDARD, byteBufAllocator);
        byteBuf.readerIndex(byteBuf.writerIndex());
        return encode;
    }

    static boolean isValidHostNameForSNI(String str) {
        return str != null && str.indexOf(46) > 0 && !str.endsWith(".") && !str.startsWith("/") && !NetUtil.isValidIpV4Address(str) && !NetUtil.isValidIpV6Address(str);
    }

    static boolean isTLSv13Cipher(String str) {
        return TLSV13_CIPHERS.contains(str);
    }

    private SslUtils() {
    }
}
