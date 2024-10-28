package io.ktor.utils.io.nio;

import io.ktor.utils.io.bits.Memory;
import io.ktor.utils.io.bits.MemoryJvmKt;
import io.ktor.utils.io.core.Buffer;
import io.ktor.utils.io.core.BytePacketBuilder;
import io.ktor.utils.io.core.ByteReadPacket;
import io.ktor.utils.io.core.Input;
import io.ktor.utils.io.core.StringsKt;
import io.ktor.utils.io.core.internal.ChunkBuffer;
import io.ktor.utils.io.pool.ObjectPool;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.nio.ByteBuffer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import kotlin.Deprecated;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a3\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0007\u0010\b\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\nH\u0007\u001a\u0012\u0010\u000b\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u000f\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u0012\u0010\u0010\u001a\u00020\f*\u00020\u00022\u0006\u0010\r\u001a\u00020\u000e\u001a\u001c\u0010\u0011\u001a\u00020\f*\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002\u001a3\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00042\b\b\u0002\u0010\u0017\u001a\u00020\u00012\b\b\u0002\u0010\u0006\u001a\u00020\u0001ø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0018\u0010\u0019\u001a\u0014\u0010\u0014\u001a\u00020\u0001*\u00020\u00152\u0006\u0010\t\u001a\u00020\nH\u0007\u001a%\u0010\u001a\u001a\u0004\u0018\u00010\f*\u00020\u00152\u0017\u0010\u001b\u001a\u0013\u0012\u0004\u0012\u00020\u001d\u0012\u0004\u0012\u00020\u001e0\u001c¢\u0006\u0002\b\u001f\u001a\u0012\u0010\u001a\u001a\u00020 *\u00020\u00152\u0006\u0010!\u001a\u00020\f\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006\""}, d2 = {"read", "", "Ljava/nio/channels/ReadableByteChannel;", "destination", "Lio/ktor/utils/io/bits/Memory;", "destinationOffset", "maxLength", "read-UAd2zVI", "(Ljava/nio/channels/ReadableByteChannel;Ljava/nio/ByteBuffer;II)I", "buffer", "Lio/ktor/utils/io/core/Buffer;", "readPacketAtLeast", "Lio/ktor/utils/io/core/ByteReadPacket;", "n", "", "readPacketAtMost", "readPacketExact", "readPacketImpl", "min", "max", "write", "Ljava/nio/channels/WritableByteChannel;", "source", "sourceOffset", "write-UAd2zVI", "(Ljava/nio/channels/WritableByteChannel;Ljava/nio/ByteBuffer;II)I", "writePacket", "builder", "Lkotlin/Function1;", "Lio/ktor/utils/io/core/BytePacketBuilder;", "", "Lkotlin/ExtensionFunctionType;", "", "p", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Channels.kt */
public final class ChannelsKt {
    public static final boolean writePacket(WritableByteChannel writableByteChannel, ByteReadPacket byteReadPacket) {
        Input input;
        ChunkBuffer prepareRead;
        int readPosition;
        int write;
        Intrinsics.checkNotNullParameter(writableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(byteReadPacket, "p");
        do {
            try {
                input = byteReadPacket;
                prepareRead = input.prepareRead(1);
                if (prepareRead != null) {
                    readPosition = prepareRead.getReadPosition();
                    Buffer buffer = prepareRead;
                    ByteBuffer r6 = buffer.m155getMemorySK3TCg8();
                    int readPosition2 = buffer.getReadPosition();
                    int writePosition = buffer.getWritePosition() - readPosition2;
                    ByteBuffer r62 = Memory.m1519slice87lwejk(r6, readPosition2, writePosition);
                    write = writableByteChannel.write(r62);
                    if (r62.limit() == writePosition) {
                        buffer.discardExact(r62.position());
                        int readPosition3 = prepareRead.getReadPosition();
                        if (readPosition3 >= readPosition) {
                            if (readPosition3 == prepareRead.getWritePosition()) {
                                input.ensureNext(prepareRead);
                            } else {
                                input.setHeadPosition(readPosition3);
                            }
                            if (byteReadPacket.getEndOfInput()) {
                                return true;
                            }
                        } else {
                            throw new IllegalStateException("Buffer's position shouldn't be rewinded");
                        }
                    } else {
                        throw new IllegalStateException("Buffer's limit change is not allowed".toString());
                    }
                } else {
                    StringsKt.prematureEndOfStream(1);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th) {
                byteReadPacket.release();
                throw th;
            }
        } while (write != 0);
        return false;
    }

    public static final ByteReadPacket readPacketExact(ReadableByteChannel readableByteChannel, long j) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "<this>");
        return readPacketImpl(readableByteChannel, j, j);
    }

    public static final ByteReadPacket readPacketAtLeast(ReadableByteChannel readableByteChannel, long j) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "<this>");
        return readPacketImpl(readableByteChannel, j, Long.MAX_VALUE);
    }

    public static final ByteReadPacket readPacketAtMost(ReadableByteChannel readableByteChannel, long j) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "<this>");
        return readPacketImpl(readableByteChannel, 1, j);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        return new io.ktor.utils.io.core.ByteReadPacket(r10, r6);
     */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x005d A[Catch:{ all -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x006c A[Catch:{ all -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x007f A[Catch:{ all -> 0x0119 }] */
    /* JADX WARNING: Removed duplicated region for block: B:60:0x00f5 A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final io.ktor.utils.io.core.ByteReadPacket readPacketImpl(java.nio.channels.ReadableByteChannel r18, long r19, long r21) {
        /*
            r0 = r19
            r2 = r21
            r4 = 0
            int r6 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r6 < 0) goto L_0x0141
            int r6 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            if (r6 > 0) goto L_0x0121
            int r6 = (r2 > r4 ? 1 : (r2 == r4 ? 0 : -1))
            if (r6 != 0) goto L_0x0019
            io.ktor.utils.io.core.ByteReadPacket$Companion r0 = io.ktor.utils.io.core.ByteReadPacket.Companion
            io.ktor.utils.io.core.ByteReadPacket r0 = r0.getEmpty()
            return r0
        L_0x0019:
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r6 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.pool.ObjectPool r6 = r6.getPool()
            io.ktor.utils.io.core.internal.ChunkBuffer$Companion r7 = io.ktor.utils.io.core.internal.ChunkBuffer.Companion
            io.ktor.utils.io.core.internal.ChunkBuffer r7 = r7.getEmpty()
            r8 = r4
            r10 = r7
            r11 = r10
        L_0x0028:
            int r12 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r12 < 0) goto L_0x003b
            int r12 = (r8 > r0 ? 1 : (r8 == r0 ? 0 : -1))
            if (r12 != 0) goto L_0x0035
            int r12 = (r0 > r4 ? 1 : (r0 == r4 ? 0 : -1))
            if (r12 != 0) goto L_0x0035
            goto L_0x003b
        L_0x0035:
            io.ktor.utils.io.core.ByteReadPacket r0 = new io.ktor.utils.io.core.ByteReadPacket
            r0.<init>(r10, r6)
            return r0
        L_0x003b:
            long r12 = r2 - r8
            r14 = 2147483647(0x7fffffff, double:1.060997895E-314)
            long r12 = kotlin.ranges.RangesKt.coerceAtMost((long) r12, (long) r14)     // Catch:{ all -> 0x0119 }
            int r13 = (int) r12     // Catch:{ all -> 0x0119 }
            r12 = r11
            io.ktor.utils.io.core.Buffer r12 = (io.ktor.utils.io.core.Buffer) r12     // Catch:{ all -> 0x0119 }
            int r14 = r12.getLimit()     // Catch:{ all -> 0x0119 }
            int r12 = r12.getWritePosition()     // Catch:{ all -> 0x0119 }
            int r14 = r14 - r12
            r12 = 200(0xc8, float:2.8E-43)
            if (r14 > r12) goto L_0x005a
            if (r14 < r13) goto L_0x0058
            goto L_0x005a
        L_0x0058:
            r12 = 0
            goto L_0x005b
        L_0x005a:
            r12 = r11
        L_0x005b:
            if (r12 != 0) goto L_0x006a
            java.lang.Object r12 = r6.borrow()     // Catch:{ all -> 0x0119 }
            r14 = r12
            io.ktor.utils.io.core.internal.ChunkBuffer r14 = (io.ktor.utils.io.core.internal.ChunkBuffer) r14     // Catch:{ all -> 0x0119 }
            if (r10 != r7) goto L_0x0068
            r10 = r14
            r11 = r10
        L_0x0068:
            io.ktor.utils.io.core.internal.ChunkBuffer r12 = (io.ktor.utils.io.core.internal.ChunkBuffer) r12     // Catch:{ all -> 0x0119 }
        L_0x006a:
            if (r11 == r12) goto L_0x0070
            r11.setNext(r12)     // Catch:{ all -> 0x0119 }
            r11 = r12
        L_0x0070:
            r14 = r12
            io.ktor.utils.io.core.Buffer r14 = (io.ktor.utils.io.core.Buffer) r14     // Catch:{ all -> 0x0119 }
            int r15 = r14.getLimit()     // Catch:{ all -> 0x0119 }
            int r14 = r14.getWritePosition()     // Catch:{ all -> 0x0119 }
            int r15 = r15 - r14
            r14 = 1
            if (r14 > r15) goto L_0x00f5
            java.nio.ByteBuffer r16 = r12.m155getMemorySK3TCg8()     // Catch:{ all -> 0x0119 }
            java.nio.ByteBuffer r4 = r16.duplicate()     // Catch:{ all -> 0x0119 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r4)     // Catch:{ all -> 0x0119 }
            int r5 = r12.getWritePosition()     // Catch:{ all -> 0x0119 }
            int r14 = r12.getLimit()     // Catch:{ all -> 0x0119 }
            r4.limit(r14)     // Catch:{ all -> 0x0119 }
            r4.position(r5)     // Catch:{ all -> 0x0119 }
            int r14 = r4.limit()     // Catch:{ all -> 0x0119 }
            r17 = r7
            int r7 = r4.remaining()     // Catch:{ all -> 0x0119 }
            if (r7 <= r13) goto L_0x00ac
            int r7 = r4.position()     // Catch:{ all -> 0x0119 }
            int r7 = r7 + r13
            r4.limit(r7)     // Catch:{ all -> 0x0119 }
        L_0x00ac:
            r7 = r18
            int r13 = r7.read(r4)     // Catch:{ all -> 0x0119 }
            r7 = -1
            if (r13 == r7) goto L_0x00d6
            r4.limit(r14)     // Catch:{ all -> 0x0119 }
            long r13 = (long) r13     // Catch:{ all -> 0x0119 }
            long r8 = r8 + r13
            int r4 = r4.position()     // Catch:{ all -> 0x0119 }
            int r4 = r4 - r5
            if (r4 < 0) goto L_0x00cc
            if (r4 > r15) goto L_0x00cc
            r12.commitWritten(r4)     // Catch:{ all -> 0x0119 }
            r7 = r17
            r4 = 0
            goto L_0x0028
        L_0x00cc:
            r0 = 1
            io.ktor.utils.io.internal.jvm.ErrorsKt.wrongBufferPositionChangeError(r4, r0)     // Catch:{ all -> 0x0119 }
            kotlin.KotlinNothingValueException r0 = new kotlin.KotlinNothingValueException     // Catch:{ all -> 0x0119 }
            r0.<init>()     // Catch:{ all -> 0x0119 }
            throw r0     // Catch:{ all -> 0x0119 }
        L_0x00d6:
            java.io.EOFException r2 = new java.io.EOFException     // Catch:{ all -> 0x0119 }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x0119 }
            r3.<init>()     // Catch:{ all -> 0x0119 }
            java.lang.String r4 = "Premature end of stream: was read "
            r3.append(r4)     // Catch:{ all -> 0x0119 }
            r3.append(r8)     // Catch:{ all -> 0x0119 }
            java.lang.String r4 = " bytes of "
            r3.append(r4)     // Catch:{ all -> 0x0119 }
            r3.append(r0)     // Catch:{ all -> 0x0119 }
            java.lang.String r0 = r3.toString()     // Catch:{ all -> 0x0119 }
            r2.<init>(r0)     // Catch:{ all -> 0x0119 }
            throw r2     // Catch:{ all -> 0x0119 }
        L_0x00f5:
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x0119 }
            r0.<init>()     // Catch:{ all -> 0x0119 }
            java.lang.String r1 = "size "
            r0.append(r1)     // Catch:{ all -> 0x0119 }
            r1 = 1
            r0.append(r1)     // Catch:{ all -> 0x0119 }
            java.lang.String r1 = " is greater than buffer's remaining capacity "
            r0.append(r1)     // Catch:{ all -> 0x0119 }
            r0.append(r15)     // Catch:{ all -> 0x0119 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0119 }
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException     // Catch:{ all -> 0x0119 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x0119 }
            r1.<init>(r0)     // Catch:{ all -> 0x0119 }
            throw r1     // Catch:{ all -> 0x0119 }
        L_0x0119:
            r0 = move-exception
            r1 = r10
            io.ktor.utils.io.core.internal.ChunkBuffer r1 = (io.ktor.utils.io.core.internal.ChunkBuffer) r1
            io.ktor.utils.io.core.BuffersKt.releaseAll(r10, r6)
            throw r0
        L_0x0121:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "min shouldn't be greater than max: "
            r4.<init>(r5)
            r4.append(r0)
            java.lang.String r0 = " > "
            r4.append(r0)
            r4.append(r2)
            java.lang.String r0 = r4.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            throw r1
        L_0x0141:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            java.lang.String r3 = "min shouldn't be negative: "
            r2.<init>(r3)
            r2.append(r0)
            java.lang.String r0 = r2.toString()
            java.lang.IllegalArgumentException r1 = new java.lang.IllegalArgumentException
            java.lang.String r0 = r0.toString()
            r1.<init>(r0)
            goto L_0x015a
        L_0x0159:
            throw r1
        L_0x015a:
            goto L_0x0159
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.nio.ChannelsKt.readPacketImpl(java.nio.channels.ReadableByteChannel, long, long):io.ktor.utils.io.core.ByteReadPacket");
    }

    /* renamed from: read-UAd2zVI  reason: not valid java name */
    public static final int m166readUAd2zVI(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "$this$read");
        Intrinsics.checkNotNullParameter(byteBuffer, RtspHeaders.Values.DESTINATION);
        return readableByteChannel.read(MemoryJvmKt.sliceSafe(byteBuffer, i, i2));
    }

    /* renamed from: write-UAd2zVI  reason: not valid java name */
    public static final int m168writeUAd2zVI(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer, int i, int i2) {
        Intrinsics.checkNotNullParameter(writableByteChannel, "$this$write");
        Intrinsics.checkNotNullParameter(byteBuffer, "source");
        return writableByteChannel.write(MemoryJvmKt.sliceSafe(byteBuffer, i, i2));
    }

    public static final ByteReadPacket writePacket(WritableByteChannel writableByteChannel, Function1<? super BytePacketBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(writableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(function1, "builder");
        BytePacketBuilder bytePacketBuilder = new BytePacketBuilder((ObjectPool) null, 1, (DefaultConstructorMarker) null);
        try {
            function1.invoke(bytePacketBuilder);
            ByteReadPacket build = bytePacketBuilder.build();
            try {
                if (writePacket(writableByteChannel, build)) {
                    return null;
                }
                return build;
            } catch (Throwable th) {
                build.release();
                throw th;
            }
        } catch (Throwable th2) {
            bytePacketBuilder.release();
            throw th2;
        }
    }

    @Deprecated(message = "Use read(Memory) instead.")
    public static final int read(ReadableByteChannel readableByteChannel, Buffer buffer) {
        Intrinsics.checkNotNullParameter(readableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        if (buffer.getLimit() - buffer.getWritePosition() == 0) {
            return 0;
        }
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int writePosition = buffer.getWritePosition();
        int read = readableByteChannel.read(MemoryJvmKt.sliceSafe(r0, writePosition, buffer.getLimit() - writePosition));
        if (read == -1) {
            return -1;
        }
        buffer.commitWritten(read);
        return read;
    }

    /* renamed from: read-UAd2zVI$default  reason: not valid java name */
    public static /* synthetic */ int m167readUAd2zVI$default(ReadableByteChannel readableByteChannel, ByteBuffer byteBuffer, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteBuffer.limit() - i;
        }
        return m166readUAd2zVI(readableByteChannel, byteBuffer, i, i2);
    }

    @Deprecated(message = "Use write(Memory) instead.")
    public static final int write(WritableByteChannel writableByteChannel, Buffer buffer) {
        Intrinsics.checkNotNullParameter(writableByteChannel, "<this>");
        Intrinsics.checkNotNullParameter(buffer, "buffer");
        ByteBuffer r0 = buffer.m155getMemorySK3TCg8();
        int readPosition = buffer.getReadPosition();
        int write = writableByteChannel.write(MemoryJvmKt.sliceSafe(r0, readPosition, buffer.getWritePosition() - readPosition));
        buffer.discardExact(write);
        return write;
    }

    /* renamed from: write-UAd2zVI$default  reason: not valid java name */
    public static /* synthetic */ int m169writeUAd2zVI$default(WritableByteChannel writableByteChannel, ByteBuffer byteBuffer, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 0;
        }
        if ((i3 & 4) != 0) {
            i2 = byteBuffer.limit() - i;
        }
        return m168writeUAd2zVI(writableByteChannel, byteBuffer, i, i2);
    }
}
