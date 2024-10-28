package io.ktor.utils.io.core;

import java.nio.ByteBuffer;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001\u001a\u001c\u0010\u0006\u001a\u00020\u0007*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0001¨\u0006\b"}, d2 = {"readAvailable", "", "Lio/ktor/utils/io/core/Input;", "dst", "Ljava/nio/ByteBuffer;", "length", "readFully", "", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: InputArraysJvm.kt */
public final class InputArraysJvmKt {
    public static /* synthetic */ void readFully$default(Input input, ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        readFully(input, byteBuffer, i);
    }

    public static final void readFully(Input input, ByteBuffer byteBuffer, int i) {
        Intrinsics.checkNotNullParameter(input, "<this>");
        Intrinsics.checkNotNullParameter(byteBuffer, "dst");
        if (readAvailable(input, byteBuffer, i) < i) {
            StringsKt.prematureEndOfStream(i);
            throw new KotlinNothingValueException();
        }
    }

    public static /* synthetic */ int readAvailable$default(Input input, ByteBuffer byteBuffer, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = byteBuffer.remaining();
        }
        return readAvailable(input, byteBuffer, i);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final int readAvailable(io.ktor.utils.io.core.Input r9, java.nio.ByteBuffer r10, int r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            java.lang.String r0 = "dst"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            r0 = 1
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadFirstHead(r9, r0)
            r2 = 0
            if (r1 != 0) goto L_0x0013
            goto L_0x0059
        L_0x0013:
            r3 = 0
        L_0x0014:
            r4 = r1
            io.ktor.utils.io.core.Buffer r4 = (io.ktor.utils.io.core.Buffer) r4     // Catch:{ all -> 0x005a }
            int r5 = r10.limit()     // Catch:{ all -> 0x005a }
            int r6 = r10.position()     // Catch:{ all -> 0x005a }
            int r7 = r4.getWritePosition()     // Catch:{ all -> 0x005a }
            int r8 = r4.getReadPosition()     // Catch:{ all -> 0x005a }
            int r7 = r7 - r8
            int r6 = r6 + r7
            int r6 = java.lang.Math.min(r5, r6)     // Catch:{ all -> 0x005a }
            r10.limit(r6)     // Catch:{ all -> 0x005a }
            int r6 = r10.remaining()     // Catch:{ all -> 0x005a }
            java.nio.ByteBuffer r7 = r4.m155getMemorySK3TCg8()     // Catch:{ all -> 0x005a }
            int r4 = r4.getReadPosition()     // Catch:{ all -> 0x005a }
            io.ktor.utils.io.bits.MemoryJvmKt.m1526copyTo62zg_DM((java.nio.ByteBuffer) r7, (java.nio.ByteBuffer) r10, (int) r4)     // Catch:{ all -> 0x005a }
            r10.limit(r5)     // Catch:{ all -> 0x005a }
            int r3 = r3 + r6
            boolean r4 = r10.hasRemaining()     // Catch:{ all -> 0x005a }
            if (r4 == 0) goto L_0x0055
            if (r3 >= r11) goto L_0x0055
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = io.ktor.utils.io.core.internal.UnsafeKt.prepareReadNextHead(r9, r1)     // Catch:{ all -> 0x0052 }
            if (r1 != 0) goto L_0x0014
            goto L_0x0058
        L_0x0052:
            r10 = move-exception
            r0 = 0
            goto L_0x005b
        L_0x0055:
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r9, r1)
        L_0x0058:
            r2 = r3
        L_0x0059:
            return r2
        L_0x005a:
            r10 = move-exception
        L_0x005b:
            if (r0 == 0) goto L_0x0060
            io.ktor.utils.io.core.internal.UnsafeKt.completeReadHead(r9, r1)
        L_0x0060:
            goto L_0x0062
        L_0x0061:
            throw r10
        L_0x0062:
            goto L_0x0061
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.core.InputArraysJvmKt.readAvailable(io.ktor.utils.io.core.Input, java.nio.ByteBuffer, int):int");
    }
}
