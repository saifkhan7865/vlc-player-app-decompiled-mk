package io.netty.handler.codec.compression;

import io.netty.buffer.ByteBuf;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.zip.Adler32;
import java.util.zip.CRC32;
import java.util.zip.Checksum;

abstract class ByteBufChecksum implements Checksum {
    private static final Method ADLER32_UPDATE_METHOD = updateByteBuffer(new Adler32());
    private static final Method CRC32_UPDATE_METHOD = updateByteBuffer(new CRC32());
    private final ByteProcessor updateProcessor = new ByteProcessor() {
        public boolean process(byte b) throws Exception {
            ByteBufChecksum.this.update(b);
            return true;
        }
    };

    ByteBufChecksum() {
    }

    private static Method updateByteBuffer(Checksum checksum) {
        if (PlatformDependent.javaVersion() >= 8) {
            try {
                Method declaredMethod = checksum.getClass().getDeclaredMethod("update", new Class[]{ByteBuffer.class});
                declaredMethod.invoke(checksum, new Object[]{ByteBuffer.allocate(1)});
                return declaredMethod;
            } catch (Throwable unused) {
            }
        }
        return null;
    }

    static ByteBufChecksum wrapChecksum(Checksum checksum) {
        Method method;
        Method method2;
        ObjectUtil.checkNotNull(checksum, "checksum");
        if (checksum instanceof ByteBufChecksum) {
            return (ByteBufChecksum) checksum;
        }
        if ((checksum instanceof Adler32) && (method2 = ADLER32_UPDATE_METHOD) != null) {
            return new ReflectiveByteBufChecksum(checksum, method2);
        }
        if (!(checksum instanceof CRC32) || (method = CRC32_UPDATE_METHOD) == null) {
            return new SlowByteBufChecksum(checksum);
        }
        return new ReflectiveByteBufChecksum(checksum, method);
    }

    public void update(ByteBuf byteBuf, int i, int i2) {
        if (byteBuf.hasArray()) {
            update(byteBuf.array(), byteBuf.arrayOffset() + i, i2);
        } else {
            byteBuf.forEachByte(i, i2, this.updateProcessor);
        }
    }

    private static final class ReflectiveByteBufChecksum extends SlowByteBufChecksum {
        private final Method method;

        ReflectiveByteBufChecksum(Checksum checksum, Method method2) {
            super(checksum);
            this.method = method2;
        }

        public void update(ByteBuf byteBuf, int i, int i2) {
            if (byteBuf.hasArray()) {
                update(byteBuf.array(), byteBuf.arrayOffset() + i, i2);
                return;
            }
            try {
                this.method.invoke(this.checksum, new Object[]{CompressionUtil.safeNioBuffer(byteBuf, i, i2)});
            } catch (Throwable unused) {
                throw new Error();
            }
        }
    }

    private static class SlowByteBufChecksum extends ByteBufChecksum {
        protected final Checksum checksum;

        SlowByteBufChecksum(Checksum checksum2) {
            this.checksum = checksum2;
        }

        public void update(int i) {
            this.checksum.update(i);
        }

        public void update(byte[] bArr, int i, int i2) {
            this.checksum.update(bArr, i, i2);
        }

        public long getValue() {
            return this.checksum.getValue();
        }

        public void reset() {
            this.checksum.reset();
        }
    }
}
