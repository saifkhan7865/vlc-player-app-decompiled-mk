package io.ktor.utils.io.core;

import io.ktor.utils.io.bits.MemoryJvmKt;
import java.nio.ByteBuffer;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0005\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a \u0010\u0006\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005H\u0000\u001a,\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000eH\bø\u0001\u0000\u001a<\u0010\t\u001a\u00020\u0001*\u00020\u00032\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\f0\u000b2\u0006\u0010\r\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0001H\bø\u0001\u0000\u001a\u0012\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u0005\u001a\u001a\u0010\u0015\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0005\u001a\u001a\u0010\u0016\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e\u001a.\u0010\u0016\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u001a\"\u0010\u0017\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000e\u001a6\u0010\u0017\u001a\u00020\u0001*\u00020\u00142\u0006\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u00052\u0006\u0010\r\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u00012\b\b\u0002\u0010\u0011\u001a\u00020\u0001\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0018"}, d2 = {"discardUntilDelimiterImplMemory", "", "buffer", "Lio/ktor/utils/io/core/Buffer;", "delimiter", "", "discardUntilDelimitersImplMemory", "delimiter1", "delimiter2", "copyUntil", "predicate", "Lkotlin/Function1;", "", "dst", "Lio/ktor/utils/io/core/Output;", "", "offset", "length", "discardUntilDelimiter", "", "Lio/ktor/utils/io/core/Input;", "discardUntilDelimiters", "readUntilDelimiter", "readUntilDelimiters", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Scanner.kt */
public final class ScannerKt {
    public static /* synthetic */ int readUntilDelimiter$default(Input input, byte b, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length;
        }
        return readUntilDelimiter(input, b, bArr, i, i2);
    }

    public static /* synthetic */ int readUntilDelimiters$default(Input input, byte b, byte b2, byte[] bArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 8) != 0 ? 0 : i;
        if ((i3 & 16) != 0) {
            i2 = bArr.length;
        }
        return readUntilDelimiters(input, b, b2, bArr, i4, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0041, code lost:
        io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r10, r1);
     */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readUntilDelimiters(io.ktor.utils.io.core.Input r10, byte r11, byte r12, byte[] r13, int r14, int r15) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            if (r11 != r12) goto L_0x0011
            int r10 = readUntilDelimiter(r10, r11, r13, r14, r15)
            return r10
        L_0x0011:
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r10, r0)
            if (r1 != 0) goto L_0x001a
            r8 = r14
            goto L_0x0044
        L_0x001a:
            r8 = r14
        L_0x001b:
            r9 = r1
            io.ktor.utils.io.core.Buffer r9 = (io.ktor.utils.io.core.Buffer) r9     // Catch:{ all -> 0x0046 }
            r2 = r9
            r3 = r11
            r4 = r12
            r5 = r13
            r6 = r8
            r7 = r15
            int r2 = io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimitersImpl(r2, r3, r4, r5, r6, r7)     // Catch:{ all -> 0x0046 }
            int r8 = r8 + r2
            int r15 = r15 - r2
            int r2 = r9.getWritePosition()     // Catch:{ all -> 0x0046 }
            int r3 = r9.getReadPosition()     // Catch:{ all -> 0x0046 }
            if (r2 <= r3) goto L_0x0035
            goto L_0x0041
        L_0x0035:
            if (r15 <= 0) goto L_0x0041
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r10, r1)     // Catch:{ all -> 0x003e }
            if (r1 != 0) goto L_0x001b
            goto L_0x0044
        L_0x003e:
            r11 = move-exception
            r0 = 0
            goto L_0x0047
        L_0x0041:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r10, r1)
        L_0x0044:
            int r8 = r8 - r14
            return r8
        L_0x0046:
            r11 = move-exception
        L_0x0047:
            if (r0 == 0) goto L_0x004c
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r10, r1)
        L_0x004c:
            goto L_0x004e
        L_0x004d:
            throw r11
        L_0x004e:
            goto L_0x004d
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.readUntilDelimiters(io.ktor.utils.io.core.Input, byte, byte, byte[], int, int):int");
    }

    public static final int discardUntilDelimiterImplMemory(Buffer buffer, byte b) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer r2 = buffer.m155getMemorySK3TCg8();
        int i = readPosition;
        while (i < writePosition && r2.get(i) != b) {
            i++;
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - readPosition;
    }

    public static final int discardUntilDelimitersImplMemory(Buffer buffer, byte b, byte b2) {
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer r2 = buffer.m155getMemorySK3TCg8();
        int i = readPosition;
        while (i < writePosition) {
            byte b3 = r2.get(i);
            if (b3 == b || b3 == b2) {
                break;
            }
            i++;
        }
        buffer.discardUntilIndex$ktor_io(i);
        return i - readPosition;
    }

    public static final int copyUntil(Buffer buffer, Function1<? super Byte, Boolean> function1, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Intrinsics.checkNotNullParameter(bArr, "dst");
        int readPosition = buffer.getReadPosition();
        int min = Math.min(buffer.getWritePosition(), i2 + readPosition);
        ByteBuffer r3 = buffer.m155getMemorySK3TCg8();
        int i3 = readPosition;
        while (true) {
            if (i3 >= min) {
                break;
            } else if (function1.invoke(Byte.valueOf(r3.get(i3))).booleanValue()) {
                min = i3;
                break;
            } else {
                i3++;
            }
        }
        int i4 = min - readPosition;
        MemoryJvmKt.m1528copyTo9zorpBc(r3, bArr, readPosition, i4, i);
        return i4;
    }

    public static final int copyUntil(Buffer buffer, Function1<? super Byte, Boolean> function1, Output output) {
        Intrinsics.checkNotNullParameter(buffer, "<this>");
        Intrinsics.checkNotNullParameter(function1, "predicate");
        Intrinsics.checkNotNullParameter(output, "dst");
        int readPosition = buffer.getReadPosition();
        int writePosition = buffer.getWritePosition();
        ByteBuffer r2 = buffer.m155getMemorySK3TCg8();
        while (readPosition != writePosition && !function1.invoke(Byte.valueOf(r2.get(readPosition))).booleanValue()) {
            readPosition++;
        }
        int readPosition2 = readPosition - buffer.getReadPosition();
        OutputKt.writeFully(output, buffer, readPosition2);
        return readPosition2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long discardUntilDelimiter(io.ktor.utils.io.core.Input r8, byte r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r0)
            r2 = 0
            if (r1 != 0) goto L_0x000f
            goto L_0x0032
        L_0x000f:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0033 }
            int r5 = io.ktor.utils.io.core.ScannerJVMKt.discardUntilDelimiterImpl(r4, r9)     // Catch:{ all -> 0x0033 }
            long r6 = (long) r5     // Catch:{ all -> 0x0033 }
            long r2 = r2 + r6
            if (r5 <= 0) goto L_0x002f
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x0033 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0033 }
            if (r5 <= r4) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r1)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x000f
            goto L_0x0032
        L_0x002c:
            r9 = move-exception
            r0 = 0
            goto L_0x0034
        L_0x002f:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0032:
            return r2
        L_0x0033:
            r9 = move-exception
        L_0x0034:
            if (r0 == 0) goto L_0x0039
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0039:
            goto L_0x003b
        L_0x003a:
            throw r9
        L_0x003b:
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.discardUntilDelimiter(io.ktor.utils.io.core.Input, byte):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long discardUntilDelimiters(io.ktor.utils.io.core.Input r8, byte r9, byte r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r8, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r8, r0)
            r2 = 0
            if (r1 != 0) goto L_0x000f
            goto L_0x0032
        L_0x000f:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x0033 }
            int r5 = io.ktor.utils.io.core.ScannerJVMKt.discardUntilDelimitersImpl(r4, r9, r10)     // Catch:{ all -> 0x0033 }
            long r6 = (long) r5     // Catch:{ all -> 0x0033 }
            long r2 = r2 + r6
            if (r5 <= 0) goto L_0x002f
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x0033 }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x0033 }
            if (r5 <= r4) goto L_0x0025
            goto L_0x002f
        L_0x0025:
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r8, r1)     // Catch:{ all -> 0x002c }
            if (r1 != 0) goto L_0x000f
            goto L_0x0032
        L_0x002c:
            r9 = move-exception
            r0 = 0
            goto L_0x0034
        L_0x002f:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0032:
            return r2
        L_0x0033:
            r9 = move-exception
        L_0x0034:
            if (r0 == 0) goto L_0x0039
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r8, r1)
        L_0x0039:
            goto L_0x003b
        L_0x003a:
            throw r9
        L_0x003b:
            goto L_0x003a
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.discardUntilDelimiters(io.ktor.utils.io.core.Input, byte, byte):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readUntilDelimiter(io.ktor.utils.io.core.Input r5, byte r6, byte[] r7, int r8, int r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r5, r0)
            if (r1 != 0) goto L_0x0013
            r2 = r8
            goto L_0x0037
        L_0x0013:
            r2 = r8
        L_0x0014:
            r3 = r1
            io.ktor.utils.io.core.Buffer r3 = (io.ktor.utils.io.core.Buffer) r3     // Catch:{ all -> 0x0039 }
            int r4 = io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimiterImpl(r3, r6, r7, r2, r9)     // Catch:{ all -> 0x0039 }
            int r2 = r2 + r4
            int r9 = r9 - r4
            if (r9 <= 0) goto L_0x0034
            int r4 = r3.getWritePosition()     // Catch:{ all -> 0x0039 }
            int r3 = r3.getReadPosition()     // Catch:{ all -> 0x0039 }
            if (r4 <= r3) goto L_0x002a
            goto L_0x0034
        L_0x002a:
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r5, r1)     // Catch:{ all -> 0x0031 }
            if (r1 != 0) goto L_0x0014
            goto L_0x0037
        L_0x0031:
            r6 = move-exception
            r0 = 0
            goto L_0x003a
        L_0x0034:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x0037:
            int r2 = r2 - r8
            return r2
        L_0x0039:
            r6 = move-exception
        L_0x003a:
            if (r0 == 0) goto L_0x003f
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r5, r1)
        L_0x003f:
            goto L_0x0041
        L_0x0040:
            throw r6
        L_0x0041:
            goto L_0x0040
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.readUntilDelimiter(io.ktor.utils.io.core.Input, byte, byte[], int, int):int");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long readUntilDelimiter(io.ktor.utils.io.core.Input r7, byte r8, io.ktor.utils.io.core.Output r9) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            r2 = 0
            if (r1 != 0) goto L_0x0014
            goto L_0x0038
        L_0x0014:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x003c }
            int r5 = io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimiterImpl(r4, r8, r9)     // Catch:{ all -> 0x003c }
            long r5 = (long) r5     // Catch:{ all -> 0x003c }
            long r2 = r2 + r5
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x003c }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x003c }
            r6 = 0
            if (r5 <= r4) goto L_0x002a
            r4 = 1
            goto L_0x002b
        L_0x002a:
            r4 = 0
        L_0x002b:
            r4 = r4 ^ r0
            if (r4 != 0) goto L_0x0032
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
            goto L_0x0038
        L_0x0032:
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x0014
        L_0x0038:
            return r2
        L_0x0039:
            r8 = move-exception
            r0 = 0
            goto L_0x003d
        L_0x003c:
            r8 = move-exception
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x0042:
            goto L_0x0044
        L_0x0043:
            throw r8
        L_0x0044:
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.readUntilDelimiter(io.ktor.utils.io.core.Input, byte, io.ktor.utils.io.core.Output):long");
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x003f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final long readUntilDelimiters(io.ktor.utils.io.core.Input r7, byte r8, byte r9, io.ktor.utils.io.core.Output r10) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r7, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r7, r0)
            r2 = 0
            if (r1 != 0) goto L_0x0014
            goto L_0x0038
        L_0x0014:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x003c }
            int r5 = io.ktor.utils.io.core.ScannerJVMKt.readUntilDelimitersImpl(r4, r8, r9, r10)     // Catch:{ all -> 0x003c }
            long r5 = (long) r5     // Catch:{ all -> 0x003c }
            long r2 = r2 + r5
            int r5 = r4.getWritePosition()     // Catch:{ all -> 0x003c }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x003c }
            r6 = 0
            if (r5 <= r4) goto L_0x002a
            r4 = 1
            goto L_0x002b
        L_0x002a:
            r4 = 0
        L_0x002b:
            r4 = r4 ^ r0
            if (r4 != 0) goto L_0x0032
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
            goto L_0x0038
        L_0x0032:
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r7, r1)     // Catch:{ all -> 0x0039 }
            if (r1 != 0) goto L_0x0014
        L_0x0038:
            return r2
        L_0x0039:
            r8 = move-exception
            r0 = 0
            goto L_0x003d
        L_0x003c:
            r8 = move-exception
        L_0x003d:
            if (r0 == 0) goto L_0x0042
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r7, r1)
        L_0x0042:
            goto L_0x0044
        L_0x0043:
            throw r8
        L_0x0044:
            goto L_0x0043
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.ScannerKt.readUntilDelimiters(io.ktor.utils.io.core.Input, byte, byte, io.ktor.utils.io.core.Output):long");
    }
}
