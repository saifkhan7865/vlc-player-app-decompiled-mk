package io.netty.handler.codec.compression;

import com.google.common.base.Ascii;
import io.netty.buffer.ByteBuf;

public final class Snappy {
    private static final int COPY_1_BYTE_OFFSET = 1;
    private static final int COPY_2_BYTE_OFFSET = 2;
    private static final int COPY_4_BYTE_OFFSET = 3;
    private static final int LITERAL = 0;
    private static final int MAX_HT_SIZE = 16384;
    private static final int MIN_COMPRESSIBLE_BYTES = 15;
    private static final int NOT_ENOUGH_INPUT = -1;
    private static final int PREAMBLE_NOT_FULL = -1;
    private State state = State.READING_PREAMBLE;
    private byte tag;
    private int written;

    private enum State {
        READING_PREAMBLE,
        READING_TAG,
        READING_LITERAL,
        READING_COPY
    }

    static int maskChecksum(long j) {
        return (int) (((j << 17) | (j >> 15)) - 1568478504);
    }

    public void reset() {
        this.state = State.READING_PREAMBLE;
        this.tag = 0;
        this.written = 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0055, code lost:
        encodeLiteral(r13, r14, r3 - r5);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005a, code lost:
        r4 = findMatchingLength(r13, r10 + 4, r3 + 4, r15) + 4;
        r5 = r3 + r4;
        encodeCopy(r14, r3 - r10, r4);
        r13.readerIndex(r13.readerIndex() + r4);
        r3 = r5 - 1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0074, code lost:
        if (r5 < r8) goto L_0x0077;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0077, code lost:
        r4 = r5 - r0;
        r1[hash(r13, r3, r2)] = (short) (r4 - 1);
        r3 = hash(r13, r5, r2);
        r10 = r0 + r1[r3];
        r1[r3] = (short) r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0095, code lost:
        if (r13.getInt(r5) == r13.getInt(r10)) goto L_0x00a0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x00a0, code lost:
        r3 = r5;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void encode(io.netty.buffer.ByteBuf r13, io.netty.buffer.ByteBuf r14, int r15) {
        /*
            r12 = this;
            r0 = 0
        L_0x0001:
            int r1 = r0 * 7
            int r1 = r15 >>> r1
            r2 = r1 & -128(0xffffffffffffff80, float:NaN)
            if (r2 == 0) goto L_0x0013
            r1 = r1 & 127(0x7f, float:1.78E-43)
            r1 = r1 | 128(0x80, float:1.794E-43)
            r14.writeByte(r1)
            int r0 = r0 + 1
            goto L_0x0001
        L_0x0013:
            r14.writeByte(r1)
            int r0 = r13.readerIndex()
            short[] r1 = getHashTable(r15)
            int r2 = r1.length
            int r2 = java.lang.Integer.numberOfLeadingZeros(r2)
            int r2 = r2 + 1
            int r3 = r15 - r0
            r4 = 15
            if (r3 < r4) goto L_0x00a6
            int r3 = r0 + 1
            int r4 = hash(r13, r3, r2)
            r5 = r0
        L_0x0032:
            r6 = 32
        L_0x0034:
            int r7 = r6 + 1
            int r6 = r6 >> 5
            int r6 = r6 + r3
            int r8 = r15 + -4
            if (r6 <= r8) goto L_0x003f
        L_0x003d:
            r0 = r5
            goto L_0x00a6
        L_0x003f:
            int r9 = hash(r13, r6, r2)
            short r10 = r1[r4]
            int r10 = r10 + r0
            int r11 = r3 - r0
            short r11 = (short) r11
            r1[r4] = r11
            int r4 = r13.getInt(r3)
            int r11 = r13.getInt(r10)
            if (r4 != r11) goto L_0x00a2
            int r4 = r3 - r5
            encodeLiteral(r13, r14, r4)
        L_0x005a:
            int r4 = r10 + 4
            int r5 = r3 + 4
            int r4 = findMatchingLength(r13, r4, r5, r15)
            int r4 = r4 + 4
            int r5 = r3 + r4
            int r3 = r3 - r10
            encodeCopy(r14, r3, r4)
            int r3 = r13.readerIndex()
            int r3 = r3 + r4
            r13.readerIndex(r3)
            int r3 = r5 + -1
            if (r5 < r8) goto L_0x0077
            goto L_0x003d
        L_0x0077:
            int r3 = hash(r13, r3, r2)
            int r4 = r5 - r0
            int r6 = r4 + -1
            short r6 = (short) r6
            r1[r3] = r6
            int r3 = hash(r13, r5, r2)
            short r6 = r1[r3]
            int r10 = r0 + r6
            short r4 = (short) r4
            r1[r3] = r4
            int r3 = r13.getInt(r5)
            int r4 = r13.getInt(r10)
            if (r3 == r4) goto L_0x00a0
            int r3 = r5 + 1
            int r4 = hash(r13, r3, r2)
            int r3 = r5 + 1
            goto L_0x0032
        L_0x00a0:
            r3 = r5
            goto L_0x005a
        L_0x00a2:
            r3 = r6
            r6 = r7
            r4 = r9
            goto L_0x0034
        L_0x00a6:
            if (r0 >= r15) goto L_0x00ac
            int r15 = r15 - r0
            encodeLiteral(r13, r14, r15)
        L_0x00ac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Snappy.encode(io.netty.buffer.ByteBuf, io.netty.buffer.ByteBuf, int):void");
    }

    private static int hash(ByteBuf byteBuf, int i, int i2) {
        return (byteBuf.getInt(i) * 506832829) >>> i2;
    }

    private static short[] getHashTable(int i) {
        int i2 = 256;
        while (i2 < 16384 && i2 < i) {
            i2 <<= 1;
        }
        return new short[i2];
    }

    private static int findMatchingLength(ByteBuf byteBuf, int i, int i2, int i3) {
        int i4 = 0;
        while (i2 <= i3 - 4 && byteBuf.getInt(i2) == byteBuf.getInt(i + i4)) {
            i2 += 4;
            i4 += 4;
        }
        while (i2 < i3 && byteBuf.getByte(i + i4) == byteBuf.getByte(i2)) {
            i2++;
            i4++;
        }
        return i4;
    }

    private static int bitsToEncode(int i) {
        int highestOneBit = Integer.highestOneBit(i);
        int i2 = 0;
        while (true) {
            highestOneBit >>= 1;
            if (highestOneBit == 0) {
                return i2;
            }
            i2++;
        }
    }

    static void encodeLiteral(ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (i < 61) {
            byteBuf2.writeByte((i - 1) << 2);
        } else {
            int i2 = i - 1;
            int bitsToEncode = bitsToEncode(i2) / 8;
            int i3 = bitsToEncode + 1;
            byteBuf2.writeByte((bitsToEncode + 60) << 2);
            for (int i4 = 0; i4 < i3; i4++) {
                byteBuf2.writeByte((i2 >> (i4 * 8)) & 255);
            }
        }
        byteBuf2.writeBytes(byteBuf, i);
    }

    private static void encodeCopyWithOffset(ByteBuf byteBuf, int i, int i2) {
        if (i2 >= 12 || i >= 2048) {
            byteBuf.writeByte(((i2 - 1) << 2) | 2);
            byteBuf.writeByte(i & 255);
            byteBuf.writeByte((i >> 8) & 255);
            return;
        }
        byteBuf.writeByte(((i2 - 4) << 2) | 1 | ((i >> 8) << 5));
        byteBuf.writeByte(i & 255);
    }

    private static void encodeCopy(ByteBuf byteBuf, int i, int i2) {
        while (i2 >= 68) {
            encodeCopyWithOffset(byteBuf, i, 64);
            i2 -= 64;
        }
        if (i2 > 64) {
            encodeCopyWithOffset(byteBuf, i, 60);
            i2 -= 60;
        }
        encodeCopyWithOffset(byteBuf, i, i2);
    }

    /* renamed from: io.netty.handler.codec.compression.Snappy$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$io$netty$handler$codec$compression$Snappy$State;

        /* JADX WARNING: Can't wrap try/catch for region: R(8:0|1|2|3|4|5|6|(3:7|8|10)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        static {
            /*
                io.netty.handler.codec.compression.Snappy$State[] r0 = io.netty.handler.codec.compression.Snappy.State.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$io$netty$handler$codec$compression$Snappy$State = r0
                io.netty.handler.codec.compression.Snappy$State r1 = io.netty.handler.codec.compression.Snappy.State.READING_PREAMBLE     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$Snappy$State     // Catch:{ NoSuchFieldError -> 0x001d }
                io.netty.handler.codec.compression.Snappy$State r1 = io.netty.handler.codec.compression.Snappy.State.READING_TAG     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$Snappy$State     // Catch:{ NoSuchFieldError -> 0x0028 }
                io.netty.handler.codec.compression.Snappy$State r1 = io.netty.handler.codec.compression.Snappy.State.READING_LITERAL     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$io$netty$handler$codec$compression$Snappy$State     // Catch:{ NoSuchFieldError -> 0x0033 }
                io.netty.handler.codec.compression.Snappy$State r1 = io.netty.handler.codec.compression.Snappy.State.READING_COPY     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.netty.handler.codec.compression.Snappy.AnonymousClass1.<clinit>():void");
        }
    }

    public void decode(ByteBuf byteBuf, ByteBuf byteBuf2) {
        while (byteBuf.isReadable()) {
            int i = AnonymousClass1.$SwitchMap$io$netty$handler$codec$compression$Snappy$State[this.state.ordinal()];
            if (i == 1) {
                int readPreamble = readPreamble(byteBuf);
                if (readPreamble != -1 && readPreamble != 0) {
                    byteBuf2.ensureWritable(readPreamble);
                    this.state = State.READING_TAG;
                } else {
                    return;
                }
            } else if (i != 2) {
                if (i == 3) {
                    int decodeLiteral = decodeLiteral(this.tag, byteBuf, byteBuf2);
                    if (decodeLiteral != -1) {
                        this.state = State.READING_TAG;
                        this.written += decodeLiteral;
                    } else {
                        return;
                    }
                } else if (i == 4) {
                    byte b = this.tag;
                    byte b2 = b & 3;
                    if (b2 == 1) {
                        int decodeCopyWith1ByteOffset = decodeCopyWith1ByteOffset(b, byteBuf, byteBuf2, this.written);
                        if (decodeCopyWith1ByteOffset != -1) {
                            this.state = State.READING_TAG;
                            this.written += decodeCopyWith1ByteOffset;
                        } else {
                            return;
                        }
                    } else if (b2 == 2) {
                        int decodeCopyWith2ByteOffset = decodeCopyWith2ByteOffset(b, byteBuf, byteBuf2, this.written);
                        if (decodeCopyWith2ByteOffset != -1) {
                            this.state = State.READING_TAG;
                            this.written += decodeCopyWith2ByteOffset;
                        } else {
                            return;
                        }
                    } else if (b2 == 3) {
                        int decodeCopyWith4ByteOffset = decodeCopyWith4ByteOffset(b, byteBuf, byteBuf2, this.written);
                        if (decodeCopyWith4ByteOffset != -1) {
                            this.state = State.READING_TAG;
                            this.written += decodeCopyWith4ByteOffset;
                        } else {
                            return;
                        }
                    } else {
                        continue;
                    }
                } else {
                    continue;
                }
            }
            if (byteBuf.isReadable()) {
                byte readByte = byteBuf.readByte();
                this.tag = readByte;
                byte b3 = readByte & 3;
                if (b3 == 0) {
                    this.state = State.READING_LITERAL;
                } else if (b3 == 1 || b3 == 2 || b3 == 3) {
                    this.state = State.READING_COPY;
                }
            } else {
                return;
            }
        }
    }

    private static int readPreamble(ByteBuf byteBuf) {
        int i = 0;
        int i2 = 0;
        while (byteBuf.isReadable()) {
            short readUnsignedByte = byteBuf.readUnsignedByte();
            int i3 = i2 + 1;
            i |= (readUnsignedByte & 127) << (i2 * 7);
            if ((readUnsignedByte & 128) == 0) {
                return i;
            }
            if (i3 < 4) {
                i2 = i3;
            } else {
                throw new DecompressionException("Preamble is greater than 4 bytes");
            }
        }
        return 0;
    }

    /* access modifiers changed from: package-private */
    public int getPreamble(ByteBuf byteBuf) {
        if (this.state != State.READING_PREAMBLE) {
            return 0;
        }
        int readerIndex = byteBuf.readerIndex();
        try {
            return readPreamble(byteBuf);
        } finally {
            byteBuf.readerIndex(readerIndex);
        }
    }

    static int decodeLiteral(byte b, ByteBuf byteBuf, ByteBuf byteBuf2) {
        byteBuf.markReaderIndex();
        int i = (b >> 2) & 63;
        switch (i) {
            case 60:
                if (byteBuf.isReadable()) {
                    i = byteBuf.readUnsignedByte();
                    break;
                } else {
                    return -1;
                }
            case 61:
                if (byteBuf.readableBytes() >= 2) {
                    i = byteBuf.readUnsignedShortLE();
                    break;
                } else {
                    return -1;
                }
            case 62:
                if (byteBuf.readableBytes() >= 3) {
                    i = byteBuf.readUnsignedMediumLE();
                    break;
                } else {
                    return -1;
                }
            case 63:
                if (byteBuf.readableBytes() >= 4) {
                    i = byteBuf.readIntLE();
                    break;
                } else {
                    return -1;
                }
        }
        int i2 = i + 1;
        if (byteBuf.readableBytes() < i2) {
            byteBuf.resetReaderIndex();
            return -1;
        }
        byteBuf2.writeBytes(byteBuf, i2);
        return i2;
    }

    private static int decodeCopyWith1ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (!byteBuf.isReadable()) {
            return -1;
        }
        int writerIndex = byteBuf2.writerIndex();
        int i2 = ((b & Ascii.FS) >> 2) + 4;
        short readUnsignedByte = (((b & 224) << 8) >> 5) | byteBuf.readUnsignedByte();
        validateOffset(readUnsignedByte, i);
        byteBuf2.markReaderIndex();
        if (readUnsignedByte < i2) {
            for (int i3 = i2 / readUnsignedByte; i3 > 0; i3--) {
                byteBuf2.readerIndex(writerIndex - readUnsignedByte);
                byteBuf2.readBytes(byteBuf2, (int) readUnsignedByte);
            }
            int i4 = i2 % readUnsignedByte;
            if (i4 != 0) {
                byteBuf2.readerIndex(writerIndex - readUnsignedByte);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(writerIndex - readUnsignedByte);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static int decodeCopyWith2ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (byteBuf.readableBytes() < 2) {
            return -1;
        }
        int writerIndex = byteBuf2.writerIndex();
        int i2 = ((b >> 2) & 63) + 1;
        int readUnsignedShortLE = byteBuf.readUnsignedShortLE();
        validateOffset(readUnsignedShortLE, i);
        byteBuf2.markReaderIndex();
        if (readUnsignedShortLE < i2) {
            for (int i3 = i2 / readUnsignedShortLE; i3 > 0; i3--) {
                byteBuf2.readerIndex(writerIndex - readUnsignedShortLE);
                byteBuf2.readBytes(byteBuf2, readUnsignedShortLE);
            }
            int i4 = i2 % readUnsignedShortLE;
            if (i4 != 0) {
                byteBuf2.readerIndex(writerIndex - readUnsignedShortLE);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(writerIndex - readUnsignedShortLE);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static int decodeCopyWith4ByteOffset(byte b, ByteBuf byteBuf, ByteBuf byteBuf2, int i) {
        if (byteBuf.readableBytes() < 4) {
            return -1;
        }
        int writerIndex = byteBuf2.writerIndex();
        int i2 = ((b >> 2) & 63) + 1;
        int readIntLE = byteBuf.readIntLE();
        validateOffset(readIntLE, i);
        byteBuf2.markReaderIndex();
        if (readIntLE < i2) {
            for (int i3 = i2 / readIntLE; i3 > 0; i3--) {
                byteBuf2.readerIndex(writerIndex - readIntLE);
                byteBuf2.readBytes(byteBuf2, readIntLE);
            }
            int i4 = i2 % readIntLE;
            if (i4 != 0) {
                byteBuf2.readerIndex(writerIndex - readIntLE);
                byteBuf2.readBytes(byteBuf2, i4);
            }
        } else {
            byteBuf2.readerIndex(writerIndex - readIntLE);
            byteBuf2.readBytes(byteBuf2, i2);
        }
        byteBuf2.resetReaderIndex();
        return i2;
    }

    private static void validateOffset(int i, int i2) {
        if (i == 0) {
            throw new DecompressionException("Offset is less than minimum permissible value");
        } else if (i < 0) {
            throw new DecompressionException("Offset is greater than maximum value supported by this implementation");
        } else if (i > i2) {
            throw new DecompressionException("Offset exceeds size of chunk");
        }
    }

    static int calculateChecksum(ByteBuf byteBuf) {
        return calculateChecksum(byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    static int calculateChecksum(ByteBuf byteBuf, int i, int i2) {
        Crc32c crc32c = new Crc32c();
        try {
            crc32c.update(byteBuf, i, i2);
            return maskChecksum(crc32c.getValue());
        } finally {
            crc32c.reset();
        }
    }

    static void validateChecksum(int i, ByteBuf byteBuf) {
        validateChecksum(i, byteBuf, byteBuf.readerIndex(), byteBuf.readableBytes());
    }

    static void validateChecksum(int i, ByteBuf byteBuf, int i2, int i3) {
        int calculateChecksum = calculateChecksum(byteBuf, i2, i3);
        if (calculateChecksum != i) {
            throw new DecompressionException("mismatching checksum: " + Integer.toHexString(calculateChecksum) + " (expected: " + Integer.toHexString(i) + ')');
        }
    }
}
