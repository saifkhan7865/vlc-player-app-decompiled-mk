package io.netty.handler.codec.http.websocketx;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.util.CharsetUtil;
import io.netty.util.concurrent.FastThreadLocal;
import io.netty.util.internal.PlatformDependent;
import j$.util.Base64;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

final class WebSocketUtil {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final FastThreadLocal<MessageDigest> MD5 = new FastThreadLocal<MessageDigest>() {
        /* access modifiers changed from: protected */
        public MessageDigest initialValue() throws Exception {
            try {
                return MessageDigest.getInstance("MD5");
            } catch (NoSuchAlgorithmException unused) {
                throw new InternalError("MD5 not supported on this platform - Outdated?");
            }
        }
    };
    private static final FastThreadLocal<MessageDigest> SHA1 = new FastThreadLocal<MessageDigest>() {
        /* access modifiers changed from: protected */
        public MessageDigest initialValue() throws Exception {
            try {
                return MessageDigest.getInstance("SHA1");
            } catch (NoSuchAlgorithmException unused) {
                throw new InternalError("SHA-1 not supported on this platform - Outdated?");
            }
        }
    };

    static int byteAtIndex(int i, int i2) {
        return (i >> ((3 - i2) * 8)) & 255;
    }

    static byte[] md5(byte[] bArr) {
        return digest(MD5, bArr);
    }

    static byte[] sha1(byte[] bArr) {
        return digest(SHA1, bArr);
    }

    private static byte[] digest(FastThreadLocal<MessageDigest> fastThreadLocal, byte[] bArr) {
        MessageDigest messageDigest = fastThreadLocal.get();
        messageDigest.reset();
        return messageDigest.digest(bArr);
    }

    static String base64(byte[] bArr) {
        if (PlatformDependent.javaVersion() >= 8) {
            return Base64.getEncoder().encodeToString(bArr);
        }
        ByteBuf wrappedBuffer = Unpooled.wrappedBuffer(bArr);
        try {
            wrappedBuffer = io.netty.handler.codec.base64.Base64.encode(wrappedBuffer);
            return wrappedBuffer.toString(CharsetUtil.UTF_8);
        } catch (Throwable th) {
            throw th;
        } finally {
            wrappedBuffer.release();
        }
    }

    static byte[] randomBytes(int i) {
        byte[] bArr = new byte[i];
        PlatformDependent.threadLocalRandom().nextBytes(bArr);
        return bArr;
    }

    static int randomNumber(int i, int i2) {
        double nextDouble = PlatformDependent.threadLocalRandom().nextDouble();
        double d = (double) i;
        double d2 = (double) (i2 - i);
        Double.isNaN(d2);
        Double.isNaN(d);
        return (int) (d + (nextDouble * d2));
    }

    private WebSocketUtil() {
    }
}
