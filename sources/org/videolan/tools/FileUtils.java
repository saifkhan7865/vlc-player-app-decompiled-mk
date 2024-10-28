package org.videolan.tools;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.LongBuffer;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0012\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0003¨\u0006\u000b"}, d2 = {"Lorg/videolan/tools/FileUtils;", "", "()V", "computeHash", "", "file", "Ljava/io/File;", "computeHashForChunk", "", "buffer", "Ljava/nio/ByteBuffer;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: FileUtils.kt */
public final class FileUtils {
    public static final FileUtils INSTANCE = new FileUtils();

    private FileUtils() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:23:0x008f  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009c  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00a1  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.String computeHash(java.io.File r13) {
        /*
            r12 = this;
            java.lang.String r0 = "file"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r13, r0)
            long r0 = r13.length()
            r2 = 65536(0x10000, double:3.2379E-319)
            long r2 = kotlin.ranges.RangesKt.coerceAtMost((long) r2, (long) r0)
            r10 = 0
            java.io.FileInputStream r11 = new java.io.FileInputStream     // Catch:{ IOException -> 0x0087, all -> 0x0084 }
            r11.<init>(r13)     // Catch:{ IOException -> 0x0087, all -> 0x0084 }
            java.nio.channels.FileChannel r13 = r11.getChannel()     // Catch:{ IOException -> 0x0081, all -> 0x007f }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r13)     // Catch:{ IOException -> 0x007d }
            java.nio.channels.FileChannel$MapMode r5 = java.nio.channels.FileChannel.MapMode.READ_ONLY     // Catch:{ IOException -> 0x007d }
            r6 = 0
            r4 = r13
            r8 = r2
            java.nio.MappedByteBuffer r4 = r4.map(r5, r6, r8)     // Catch:{ IOException -> 0x007d }
            java.lang.String r5 = "map(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r4, r5)     // Catch:{ IOException -> 0x007d }
            java.nio.ByteBuffer r4 = (java.nio.ByteBuffer) r4     // Catch:{ IOException -> 0x007d }
            long r4 = r12.computeHashForChunk(r4)     // Catch:{ IOException -> 0x007d }
            int r3 = (int) r2     // Catch:{ IOException -> 0x007d }
            java.nio.ByteBuffer r2 = java.nio.ByteBuffer.allocateDirect(r3)     // Catch:{ IOException -> 0x007d }
            r3 = 65536(0x10000, float:9.18355E-41)
            long r6 = (long) r3     // Catch:{ IOException -> 0x007d }
            long r6 = r0 - r6
            r8 = 0
            long r6 = kotlin.ranges.RangesKt.coerceAtLeast((long) r6, (long) r8)     // Catch:{ IOException -> 0x007d }
            int r3 = r13.read(r2, r6)     // Catch:{ IOException -> 0x007d }
        L_0x0046:
            if (r3 <= 0) goto L_0x004f
            long r8 = (long) r3     // Catch:{ IOException -> 0x007d }
            long r6 = r6 + r8
            int r3 = r13.read(r2, r6)     // Catch:{ IOException -> 0x007d }
            goto L_0x0046
        L_0x004f:
            r2.flip()     // Catch:{ IOException -> 0x007d }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2)     // Catch:{ IOException -> 0x007d }
            long r2 = r12.computeHashForChunk(r2)     // Catch:{ IOException -> 0x007d }
            kotlin.jvm.internal.StringCompanionObject r6 = kotlin.jvm.internal.StringCompanionObject.INSTANCE     // Catch:{ IOException -> 0x007d }
            java.lang.String r6 = "%016x"
            long r0 = r0 + r4
            long r0 = r0 + r2
            java.lang.Long r0 = java.lang.Long.valueOf(r0)     // Catch:{ IOException -> 0x007d }
            r1 = 1
            java.lang.Object[] r2 = new java.lang.Object[r1]     // Catch:{ IOException -> 0x007d }
            r3 = 0
            r2[r3] = r0     // Catch:{ IOException -> 0x007d }
            java.lang.Object[] r0 = java.util.Arrays.copyOf(r2, r1)     // Catch:{ IOException -> 0x007d }
            java.lang.String r0 = java.lang.String.format(r6, r0)     // Catch:{ IOException -> 0x007d }
            java.lang.String r1 = "format(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r0, r1)     // Catch:{ IOException -> 0x007d }
            r13.close()
            r11.close()
            return r0
        L_0x007d:
            r0 = move-exception
            goto L_0x008a
        L_0x007f:
            r0 = move-exception
            goto L_0x009a
        L_0x0081:
            r0 = move-exception
            r13 = r10
            goto L_0x008a
        L_0x0084:
            r0 = move-exception
            r11 = r10
            goto L_0x009a
        L_0x0087:
            r0 = move-exception
            r13 = r10
            r11 = r13
        L_0x008a:
            r0.printStackTrace()     // Catch:{ all -> 0x0098 }
            if (r13 == 0) goto L_0x0092
            r13.close()
        L_0x0092:
            if (r11 == 0) goto L_0x0097
            r11.close()
        L_0x0097:
            return r10
        L_0x0098:
            r0 = move-exception
            r10 = r13
        L_0x009a:
            if (r10 == 0) goto L_0x009f
            r10.close()
        L_0x009f:
            if (r11 == 0) goto L_0x00a4
            r11.close()
        L_0x00a4:
            goto L_0x00a6
        L_0x00a5:
            throw r0
        L_0x00a6:
            goto L_0x00a5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.FileUtils.computeHash(java.io.File):java.lang.String");
    }

    private final long computeHashForChunk(ByteBuffer byteBuffer) {
        LongBuffer asLongBuffer = byteBuffer.order(ByteOrder.LITTLE_ENDIAN).asLongBuffer();
        long j = 0;
        while (asLongBuffer.hasRemaining()) {
            j += asLongBuffer.get();
        }
        return j;
    }
}
