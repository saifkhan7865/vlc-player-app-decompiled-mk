package io.netty.handler.codec.base64;

import com.google.common.base.Ascii;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.handler.codec.http2.Http2CodecUtil;
import io.netty.util.ByteProcessor;
import io.netty.util.internal.ObjectUtil;
import io.netty.util.internal.PlatformDependent;
import java.nio.ByteOrder;
import okio.Utf8;

public final class Base64 {
    private static final byte EQUALS_SIGN = 61;
    private static final byte EQUALS_SIGN_ENC = -1;
    private static final int MAX_LINE_LENGTH = 76;
    private static final byte NEW_LINE = 10;
    private static final byte WHITE_SPACE_ENC = -5;

    static int decodedBufferSize(int i) {
        return i - (i >>> 2);
    }

    private static int toInt(byte b) {
        return (b & 255) << Ascii.DLE;
    }

    private static int toIntBE(int i) {
        return (i & 255) | (16711680 & i) | (65280 & i);
    }

    private static int toIntBE(short s) {
        return ((s & Http2CodecUtil.MAX_UNSIGNED_BYTE) << 8) | ((65280 & s) << 8);
    }

    private static int toIntLE(int i) {
        return ((i & 16711680) >>> 16) | ((i & 255) << 16) | (65280 & i);
    }

    private static int toIntLE(short s) {
        return (s & 65280) | ((s & Http2CodecUtil.MAX_UNSIGNED_BYTE) << 16);
    }

    private static byte[] alphabet(Base64Dialect base64Dialect) {
        return ((Base64Dialect) ObjectUtil.checkNotNull(base64Dialect, "dialect")).alphabet;
    }

    /* access modifiers changed from: private */
    public static byte[] decodabet(Base64Dialect base64Dialect) {
        return ((Base64Dialect) ObjectUtil.checkNotNull(base64Dialect, "dialect")).decodabet;
    }

    private static boolean breakLines(Base64Dialect base64Dialect) {
        return ((Base64Dialect) ObjectUtil.checkNotNull(base64Dialect, "dialect")).breakLinesByDefault;
    }

    public static ByteBuf encode(ByteBuf byteBuf) {
        return encode(byteBuf, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, Base64Dialect base64Dialect) {
        return encode(byteBuf, breakLines(base64Dialect), base64Dialect);
    }

    public static ByteBuf encode(ByteBuf byteBuf, boolean z) {
        return encode(byteBuf, z, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, boolean z, Base64Dialect base64Dialect) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ByteBuf encode = encode(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), z, base64Dialect);
        byteBuf.readerIndex(byteBuf.writerIndex());
        return encode;
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2) {
        return encode(byteBuf, i, i2, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, Base64Dialect base64Dialect) {
        return encode(byteBuf, i, i2, breakLines(base64Dialect), base64Dialect);
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, boolean z) {
        return encode(byteBuf, i, i2, z, Base64Dialect.STANDARD);
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, boolean z, Base64Dialect base64Dialect) {
        return encode(byteBuf, i, i2, z, base64Dialect, byteBuf.alloc());
    }

    public static ByteBuf encode(ByteBuf byteBuf, int i, int i2, boolean z, Base64Dialect base64Dialect, ByteBufAllocator byteBufAllocator) {
        int i3 = i2;
        ObjectUtil.checkNotNull(byteBuf, "src");
        ObjectUtil.checkNotNull(base64Dialect, "dialect");
        ByteBuf order = byteBufAllocator.buffer(encodedBufferSize(i2, z)).order(byteBuf.order());
        byte[] alphabet = alphabet(base64Dialect);
        int i4 = i3 - 2;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        while (i5 < i4) {
            encode3to4(byteBuf, i5 + i, 3, order, i6, alphabet);
            i7 += 4;
            if (z && i7 == 76) {
                order.setByte(i6 + 4, 10);
                i6++;
                i7 = 0;
            }
            i5 += 3;
            i6 += 4;
        }
        if (i5 < i3) {
            encode3to4(byteBuf, i5 + i, i3 - i5, order, i6, alphabet);
            i6 += 4;
        }
        if (i6 > 1 && order.getByte(i6 - 1) == 10) {
            i6--;
        }
        return order.slice(0, i6);
    }

    private static void encode3to4(ByteBuf byteBuf, int i, int i2, ByteBuf byteBuf2, int i3, byte[] bArr) {
        int i4 = 0;
        if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
            if (i2 == 1) {
                i4 = toInt(byteBuf.getByte(i));
            } else if (i2 == 2) {
                i4 = toIntBE(byteBuf.getShort(i));
            } else if (i2 > 0) {
                i4 = toIntBE(byteBuf.getMedium(i));
            }
            encode3to4BigEndian(i4, i2, byteBuf2, i3, bArr);
            return;
        }
        if (i2 == 1) {
            i4 = toInt(byteBuf.getByte(i));
        } else if (i2 == 2) {
            i4 = toIntLE(byteBuf.getShort(i));
        } else if (i2 > 0) {
            i4 = toIntLE(byteBuf.getMedium(i));
        }
        encode3to4LittleEndian(i4, i2, byteBuf2, i3, bArr);
    }

    static int encodedBufferSize(int i, boolean z) {
        long j = (((long) i) << 2) / 3;
        long j2 = (3 + j) & -4;
        if (z) {
            j2 += j / 76;
        }
        if (j2 < 2147483647L) {
            return (int) j2;
        }
        return Integer.MAX_VALUE;
    }

    private static void encode3to4BigEndian(int i, int i2, ByteBuf byteBuf, int i3, byte[] bArr) {
        if (i2 == 1) {
            byteBuf.setInt(i3, (bArr[(i >>> 12) & 63] << Ascii.DLE) | (bArr[i >>> 18] << Ascii.CAN) | 15677);
        } else if (i2 == 2) {
            byteBuf.setInt(i3, (bArr[(i >>> 6) & 63] << 8) | (bArr[i >>> 18] << Ascii.CAN) | (bArr[(i >>> 12) & 63] << Ascii.DLE) | 61);
        } else if (i2 == 3) {
            byteBuf.setInt(i3, bArr[i & 63] | (bArr[i >>> 18] << Ascii.CAN) | (bArr[(i >>> 12) & 63] << Ascii.DLE) | (bArr[(i >>> 6) & 63] << 8));
        }
    }

    private static void encode3to4LittleEndian(int i, int i2, ByteBuf byteBuf, int i3, byte[] bArr) {
        if (i2 == 1) {
            byteBuf.setInt(i3, (bArr[(i >>> 12) & 63] << 8) | bArr[i >>> 18] | 1027407872);
        } else if (i2 == 2) {
            byteBuf.setInt(i3, (bArr[(i >>> 6) & 63] << Ascii.DLE) | bArr[i >>> 18] | (bArr[(i >>> 12) & 63] << 8) | 1023410176);
        } else if (i2 == 3) {
            byteBuf.setInt(i3, (bArr[i & 63] << Ascii.CAN) | bArr[i >>> 18] | (bArr[(i >>> 12) & 63] << 8) | (bArr[(i >>> 6) & 63] << Ascii.DLE));
        }
    }

    public static ByteBuf decode(ByteBuf byteBuf) {
        return decode(byteBuf, Base64Dialect.STANDARD);
    }

    public static ByteBuf decode(ByteBuf byteBuf, Base64Dialect base64Dialect) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ByteBuf decode = decode(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes(), base64Dialect);
        byteBuf.readerIndex(byteBuf.writerIndex());
        return decode;
    }

    public static ByteBuf decode(ByteBuf byteBuf, int i, int i2) {
        return decode(byteBuf, i, i2, Base64Dialect.STANDARD);
    }

    public static ByteBuf decode(ByteBuf byteBuf, int i, int i2, Base64Dialect base64Dialect) {
        return decode(byteBuf, i, i2, base64Dialect, byteBuf.alloc());
    }

    public static ByteBuf decode(ByteBuf byteBuf, int i, int i2, Base64Dialect base64Dialect, ByteBufAllocator byteBufAllocator) {
        ObjectUtil.checkNotNull(byteBuf, "src");
        ObjectUtil.checkNotNull(base64Dialect, "dialect");
        return new Decoder().decode(byteBuf, i, i2, byteBufAllocator, base64Dialect);
    }

    private static final class Decoder implements ByteProcessor {
        private final byte[] b4;
        private int b4Posn;
        private byte[] decodabet;
        private ByteBuf dest;
        private int outBuffPosn;

        private Decoder() {
            this.b4 = new byte[4];
        }

        /* access modifiers changed from: package-private */
        public ByteBuf decode(ByteBuf byteBuf, int i, int i2, ByteBufAllocator byteBufAllocator, Base64Dialect base64Dialect) {
            this.dest = byteBufAllocator.buffer(Base64.decodedBufferSize(i2)).order(byteBuf.order());
            this.decodabet = Base64.decodabet(base64Dialect);
            try {
                byteBuf.forEachByte(i, i2, this);
                return this.dest.slice(0, this.outBuffPosn);
            } catch (Throwable th) {
                this.dest.release();
                PlatformDependent.throwException(th);
                return null;
            }
        }

        /* JADX WARNING: Code restructure failed: missing block: B:1:0x0002, code lost:
            r0 = r5.decodabet;
         */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        public boolean process(byte r6) throws java.lang.Exception {
            /*
                r5 = this;
                if (r6 <= 0) goto L_0x002f
                byte[] r0 = r5.decodabet
                byte r1 = r0[r6]
                r2 = -5
                if (r1 < r2) goto L_0x002f
                r2 = -1
                r3 = 1
                if (r1 < r2) goto L_0x002e
                byte[] r1 = r5.b4
                int r2 = r5.b4Posn
                int r4 = r2 + 1
                r5.b4Posn = r4
                r1[r2] = r6
                r2 = 3
                if (r4 <= r2) goto L_0x002e
                int r2 = r5.outBuffPosn
                io.netty.buffer.ByteBuf r4 = r5.dest
                int r0 = decode4to3(r1, r4, r2, r0)
                int r2 = r2 + r0
                r5.outBuffPosn = r2
                r0 = 0
                r5.b4Posn = r0
                r1 = 61
                if (r6 == r1) goto L_0x002d
                goto L_0x002e
            L_0x002d:
                r3 = 0
            L_0x002e:
                return r3
            L_0x002f:
                java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
                java.lang.StringBuilder r1 = new java.lang.StringBuilder
                java.lang.String r2 = "invalid Base64 input character: "
                r1.<init>(r2)
                r6 = r6 & 255(0xff, float:3.57E-43)
                short r6 = (short) r6
                r1.append(r6)
                java.lang.String r6 = " (decimal)"
                r1.append(r6)
                java.lang.String r6 = r1.toString()
                r0.<init>(r6)
                throw r0
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.base64.Base64.Decoder.process(byte):boolean");
        }

        private static int decode4to3(byte[] bArr, ByteBuf byteBuf, int i, byte[] bArr2) {
            int i2;
            int i3;
            int i4;
            byte b = bArr[0];
            byte b2 = bArr[1];
            byte b3 = bArr[2];
            if (b3 == 61) {
                try {
                    byteBuf.setByte(i, ((bArr2[b] & 255) << 2) | ((bArr2[b2] & 255) >>> 4));
                    return 1;
                } catch (IndexOutOfBoundsException unused) {
                    throw new IllegalArgumentException("not encoded in Base64");
                }
            } else {
                byte b5 = bArr[3];
                if (b5 == 61) {
                    byte b6 = bArr2[b2];
                    try {
                        if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
                            i4 = ((b6 & Ascii.SI) << 4) | ((((bArr2[b] & Utf8.REPLACEMENT_BYTE) << 2) | ((b6 & 240) >> 4)) << 8) | ((bArr2[b3] & 252) >>> 2);
                        } else {
                            i4 = ((((b6 & Ascii.SI) << 4) | ((bArr2[b3] & 252) >>> 2)) << 8) | ((bArr2[b] & Utf8.REPLACEMENT_BYTE) << 2) | ((b6 & 240) >> 4);
                        }
                        byteBuf.setShort(i, i4);
                        return 2;
                    } catch (IndexOutOfBoundsException unused2) {
                        throw new IllegalArgumentException("not encoded in Base64");
                    }
                } else {
                    try {
                        if (byteBuf.order() == ByteOrder.BIG_ENDIAN) {
                            i3 = ((bArr2[b] & Utf8.REPLACEMENT_BYTE) << Ascii.DC2) | ((bArr2[b2] & 255) << Ascii.FF) | ((bArr2[b3] & 255) << 6);
                            i2 = bArr2[b5] & 255;
                        } else {
                            byte b7 = bArr2[b2];
                            byte b8 = bArr2[b3];
                            i3 = ((bArr2[b] & Utf8.REPLACEMENT_BYTE) << 2) | ((b7 & Ascii.SI) << Ascii.FF) | ((b7 & 240) >>> 4) | ((b8 & 3) << Ascii.SYN) | ((b8 & 252) << 6);
                            i2 = (bArr2[b5] & 255) << Ascii.DLE;
                        }
                        byteBuf.setMedium(i, i2 | i3);
                        return 3;
                    } catch (IndexOutOfBoundsException unused3) {
                        throw new IllegalArgumentException("not encoded in Base64");
                    }
                }
            }
        }
    }

    private Base64() {
    }
}
