package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.core.internal.UnsafeKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0002\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0002\u001a0\u0010\t\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a)\u0010\u000e\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u0012H\b\u001aA\u0010\u000e\u001a\u00020\u0001*\u00020\u00132\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\u0014\u001a\u00020\u00012\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\b\u001a9\u0010\u0015\u001a\u00020\u0001*\u00020\u00132\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00110\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\b\u001a\u0014\u0010\u0016\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\u0017\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a\u001c\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a\u001c\u0010\u0018\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a,\u0010\u0018\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a\u001c\u0010\u0019\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a,\u0010\u0019\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0000\u001a$\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a4\u0010\u001a\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a$\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a4\u0010\u001b\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a$\u0010\u001c\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0012H\u0000\u001a4\u0010\u001c\u001a\u00020\u0001*\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u0001H\u0000¨\u0006\u001d"}, d2 = {"discardUntilDelimiterImplArrays", "", "buffer", "Lio/ktor/utils/io/core/Buffer;", "delimiter", "", "discardUntilDelimitersImplArrays", "delimiter1", "delimiter2", "readUntilDelimiterArrays", "dst", "", "offset", "length", "copyUntilArrays", "predicate", "Lkotlin/Function1;", "", "Lio/ktor/utils/io/core/Output;", "Ljava/nio/ByteBuffer;", "bufferOffset", "copyUntilDirect", "discardUntilDelimiterImpl", "discardUntilDelimitersImpl", "readUntilDelimiterDirect", "readUntilDelimiterImpl", "readUntilDelimitersArrays", "readUntilDelimitersDirect", "readUntilDelimitersImpl", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ScannerJVM.kt */
public final class ScannerJVMKt {
    public static final int discardUntilDelimiterImpl(Buffer buffer, byte b) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (ByteBuffersKt.hasArray(buffer)) {
            return discardUntilDelimiterImplArrays(buffer, b);
        }
        return ScannerKt.discardUntilDelimiterImplMemory(buffer, b);
    }

    private static final int discardUntilDelimiterImplArrays(Buffer buffer, byte b) {
        int i;
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        byte[] array = r0.array();
        int arrayOffset = r0.arrayOffset() + r0.position() + buffer.getReadPosition();
        int writePosition = (buffer.getWritePosition() - buffer.getReadPosition()) + arrayOffset;
        if (writePosition <= array.length) {
            i = arrayOffset;
            while (i < writePosition && array[i] != b) {
                i++;
            }
        } else {
            i = arrayOffset;
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - arrayOffset;
    }

    public static final int discardUntilDelimitersImpl(Buffer buffer, byte b, byte b2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        if (ByteBuffersKt.hasArray(buffer)) {
            return discardUntilDelimitersImplArrays(buffer, b, b2);
        }
        return ScannerKt.discardUntilDelimitersImplMemory(buffer, b, b2);
    }

    private static final int discardUntilDelimitersImplArrays(Buffer buffer, byte b, byte b2) {
        int i;
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        byte[] array = r0.array();
        int arrayOffset = r0.arrayOffset() + r0.position() + buffer.getReadPosition();
        int writePosition = (buffer.getWritePosition() - buffer.getReadPosition()) + arrayOffset;
        if (writePosition <= array.length) {
            i = arrayOffset;
            while (i < writePosition) {
                byte b3 = array[i];
                if (b3 == b || b3 == b2) {
                    break;
                }
                i++;
            }
        } else {
            i = arrayOffset;
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - arrayOffset;
    }

    public static final int readUntilDelimiterImpl(Buffer buffer, byte b, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "dst");
        int length = bArr.length;
        if (ByteBuffersKt.hasArray(buffer)) {
            return readUntilDelimiterArrays(buffer, b, bArr, i, i2);
        }
        return readUntilDelimiterDirect(buffer, b, bArr, i, i2);
    }

    private static final int readUntilDelimiterArrays(Buffer buffer, byte b, byte[] bArr, int i, int i2) {
        int i3;
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        int min = Math.min(i2, buffer.getWritePosition() - buffer.getReadPosition());
        byte[] array = r0.array();
        int position = readPosition + r0.position() + r0.arrayOffset();
        int min2 = Math.min(min, r0.remaining()) + position;
        if (min2 <= array.length) {
            i3 = position;
            while (i3 < min2 && array[i3] != b) {
                i3++;
            }
        } else {
            i3 = position;
        }
        int i4 = i3 - position;
        System.arraycopy(array, position, bArr, i, i4);
        buffer.discardExact(i4);
        return i4;
    }

    public static final int readUntilDelimitersImpl(Buffer buffer, byte b, byte b2, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(bArr, "dst");
        int length = bArr.length;
        if (ByteBuffersKt.hasArray(buffer)) {
            return readUntilDelimitersArrays(buffer, b, b2, bArr, i, i2);
        }
        return readUntilDelimitersDirect(buffer, b, b2, bArr, i, i2);
    }

    private static final int readUntilDelimitersArrays(Buffer buffer, byte b, byte b2, byte[] bArr, int i, int i2) {
        int i3;
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        int min = Math.min(i2, buffer.getWritePosition() - buffer.getReadPosition());
        byte[] array = r0.array();
        int position = readPosition + r0.position() + r0.arrayOffset();
        int min2 = Math.min(min, r0.remaining()) + position;
        if (min2 <= array.length) {
            i3 = position;
            while (i3 < min2) {
                byte b3 = array[i3];
                if (b3 == b || b3 == b2) {
                    break;
                }
                i3++;
            }
        } else {
            i3 = position;
        }
        int i4 = i3 - position;
        System.arraycopy(array, position, bArr, i, i4);
        buffer.discardExact(i4);
        return i4;
    }

    public static final int readUntilDelimiterImpl(Buffer buffer, byte b, Output output) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(output, "dst");
        if (ByteBuffersKt.hasArray(buffer)) {
            return readUntilDelimiterArrays(buffer, b, output);
        }
        return readUntilDelimiterDirect(buffer, b, output);
    }

    public static final int readUntilDelimitersImpl(Buffer buffer, byte b, byte b2, Output output) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(output, "dst");
        if (ByteBuffersKt.hasArray(buffer)) {
            return readUntilDelimitersArrays(buffer, b, b2, output);
        }
        return readUntilDelimitersDirect(buffer, b, b2, output);
    }

    private static final int copyUntilDirect(ByteBuffer byteBuffer, Function1<? super Byte, Boolean> function1, byte[] bArr, int i, int i2) {
        int position = byteBuffer.position();
        int i3 = i2 + position;
        int i4 = position;
        while (i4 < byteBuffer.limit() && i4 < i3 && !function1.invoke(Byte.valueOf(byteBuffer.get(i4))).booleanValue()) {
            i4++;
        }
        int i5 = i4 - position;
        byteBuffer.get(bArr, i, i5);
        return i5;
    }

    private static final int copyUntilArrays(ByteBuffer byteBuffer, Function1<? super Byte, Boolean> function1, int i, byte[] bArr, int i2, int i3) {
        int i4;
        byte[] array = byteBuffer.array();
        int position = i + byteBuffer.position() + byteBuffer.arrayOffset();
        int min = Math.min(i3, byteBuffer.remaining()) + position;
        if (min <= array.length) {
            i4 = position;
            while (i4 < min && !function1.invoke(Byte.valueOf(array[i4])).booleanValue()) {
                i4++;
            }
        } else {
            i4 = position;
        }
        int i5 = i4 - position;
        System.arraycopy(array, position, bArr, i2, i5);
        return i5;
    }

    /* JADX INFO: finally extract failed */
    private static final int copyUntilArrays(Buffer buffer, Function1<? super Byte, Boolean> function1, Output output) {
        int i;
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        byte[] array = r0.array();
        int position = r0.position() + r0.arrayOffset() + buffer.getReadPosition();
        int position2 = r0.position() + r0.arrayOffset() + buffer.getWritePosition();
        ChunkBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, (ChunkBuffer) null);
        int i2 = 0;
        while (true) {
            try {
                Buffer buffer2 = prepareWriteHead;
                int min = Math.min((buffer2.getLimit() - buffer2.getWritePosition()) + position, position2);
                if (min <= array.length) {
                    i = position;
                    while (i < min && !function1.invoke(Byte.valueOf(array[i])).booleanValue()) {
                        i++;
                    }
                } else {
                    i = position;
                }
                int i3 = i - position;
                Intrinsics.checkNotNullExpressionValue(array, "array");
                BufferPrimitivesKt.writeFully(buffer2, array, position, i3);
                i2 += i3;
                if (buffer2.getLimit() <= buffer2.getWritePosition()) {
                    if (i >= position2) {
                        break;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, prepareWriteHead);
                    position = i;
                } else {
                    break;
                }
            } catch (Throwable th) {
                InlineMarker.finallyStart(1);
                output.afterHeadWrite();
                InlineMarker.finallyEnd(1);
                throw th;
            }
        }
        InlineMarker.finallyStart(1);
        output.afterHeadWrite();
        InlineMarker.finallyEnd(1);
        buffer.discardUntilIndex$ktor_io(i);
        return i2;
    }

    private static final int readUntilDelimiterDirect(Buffer buffer, byte b, byte[] bArr, int i, int i2) {
        int readPosition = buffer.getReadPosition();
        int min = Math.min(buffer.getWritePosition(), i2 + readPosition);
        ByteBuffer r1 = buffer.m155getMemorySK3TCg8();
        int i3 = readPosition;
        while (true) {
            if (i3 >= min) {
                break;
            } else if (r1.get(i3) == b) {
                min = i3;
                break;
            } else {
                i3++;
            }
        }
        int i4 = min - readPosition;
        MemoryJvmKt.m1528copyTo9zorpBc(r1, bArr, readPosition, i4, i);
        buffer.discardExact(i4);
        return i4;
    }

    private static final int readUntilDelimitersDirect(Buffer buffer, byte b, byte b2, byte[] bArr, int i, int i2) {
        int readPosition = buffer.getReadPosition();
        int min = Math.min(buffer.getWritePosition(), i2 + readPosition);
        ByteBuffer r1 = buffer.m155getMemorySK3TCg8();
        int i3 = readPosition;
        while (true) {
            if (i3 >= min) {
                break;
            }
            byte b3 = r1.get(i3);
            if (b3 == b || b3 == b2) {
                min = i3;
            } else {
                i3++;
            }
        }
        min = i3;
        int i4 = min - readPosition;
        MemoryJvmKt.m1528copyTo9zorpBc(r1, bArr, readPosition, i4, i);
        buffer.discardExact(i4);
        return i4;
    }

    public static final int readUntilDelimiterDirect(Buffer buffer, byte b, Output output) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(output, "dst");
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer r2 = buffer.m155getMemorySK3TCg8();
        while (readPosition != writePosition && r2.get(readPosition) != b) {
            readPosition++;
        }
        int readPosition2 = readPosition - buffer.getReadPosition();
        OutputKt.writeFully(output, buffer, readPosition2);
        return readPosition2;
    }

    /* JADX INFO: finally extract failed */
    public static final int readUntilDelimiterArrays(Buffer buffer, byte b, Output output) {
        int i;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(output, "dst");
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        byte[] array = r0.array();
        int position = r0.position() + r0.arrayOffset() + buffer.getReadPosition();
        int position2 = r0.position() + r0.arrayOffset() + buffer.getWritePosition();
        ChunkBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, (ChunkBuffer) null);
        int i2 = 0;
        while (true) {
            try {
                Buffer buffer2 = prepareWriteHead;
                int min = Math.min((buffer2.getLimit() - buffer2.getWritePosition()) + position, position2);
                if (min <= array.length) {
                    i = position;
                    while (true) {
                        if (i >= min) {
                            break;
                        } else if (array[i] == b) {
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    i = position;
                }
                int i3 = i - position;
                Intrinsics.checkNotNullExpressionValue(array, "array");
                BufferPrimitivesKt.writeFully(buffer2, array, position, i3);
                i2 += i3;
                if (buffer2.getLimit() <= buffer2.getWritePosition()) {
                    if (i >= position2) {
                        break;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, prepareWriteHead);
                    position = i;
                } else {
                    break;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
        output.afterHeadWrite();
        buffer.discardUntilIndex$ktor_io(i);
        return i2;
    }

    public static final int readUntilDelimitersDirect(Buffer buffer, byte b, byte b2, Output output) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(output, "dst");
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer r2 = buffer.m155getMemorySK3TCg8();
        while (readPosition != writePosition) {
            byte b3 = r2.get(readPosition);
            if (b3 == b || b3 == b2) {
                break;
            }
            readPosition++;
        }
        int readPosition2 = readPosition - buffer.getReadPosition();
        OutputKt.writeFully(output, buffer, readPosition2);
        return readPosition2;
    }

    /* JADX INFO: finally extract failed */
    public static final int readUntilDelimitersArrays(Buffer buffer, byte b, byte b2, Output output) {
        int i;
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(output, "dst");
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        byte[] array = r0.array();
        int position = r0.position() + r0.arrayOffset() + buffer.getReadPosition();
        int position2 = r0.position() + r0.arrayOffset() + buffer.getWritePosition();
        ChunkBuffer prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, (ChunkBuffer) null);
        int i2 = 0;
        while (true) {
            try {
                Buffer buffer2 = prepareWriteHead;
                int min = Math.min((buffer2.getLimit() - buffer2.getWritePosition()) + position, position2);
                if (min <= array.length) {
                    i = position;
                    while (true) {
                        if (i >= min) {
                            break;
                        }
                        byte b3 = array[i];
                        if (b3 == b) {
                            break;
                        } else if (b3 == b2) {
                            break;
                        } else {
                            i++;
                        }
                    }
                } else {
                    i = position;
                }
                int i3 = i - position;
                Intrinsics.checkNotNullExpressionValue(array, "array");
                BufferPrimitivesKt.writeFully(buffer2, array, position, i3);
                i2 += i3;
                if (buffer2.getLimit() <= buffer2.getWritePosition()) {
                    if (i >= position2) {
                        break;
                    }
                    prepareWriteHead = UnsafeKt.prepareWriteHead(output, 1, prepareWriteHead);
                    position = i;
                } else {
                    break;
                }
            } catch (Throwable th) {
                output.afterHeadWrite();
                throw th;
            }
        }
        output.afterHeadWrite();
        buffer.discardUntilIndex$ktor_io(i);
        return i2;
    }
}
