package io.ktor.utils.io.bits;

import io.netty.handler.codec.rtsp.RtspHeaders;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import kotlin.Metadata;
import kotlin.UByteArray;
import kotlin.UIntArray;
import kotlin.ULongArray;
import kotlin.UShortArray;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0010\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\n\u001a>\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\t\u0010\f\u001a>\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\n\u001a>\u0010\r\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u000e2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u000f\u0010\f\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00112\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0013\u001a>\u0010\u0010\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00112\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0012\u0010\u0014\u001a>\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0018\u001a>\u0010\u0015\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u00162\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u0017\u0010\u0019\u001a>\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u001b2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001d\u001a>\u0010\u001a\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010\u0005\u001a\u00020\u001b2\b\b\u0002\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\u001c\u0010\u001e\u001a>\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010\n\u001a>\u0010\u001f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00062\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b\"\u0010\f\u001a>\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\n\u001a>\u0010#\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b$\u0010\f\u001a>\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0013\u001a>\u0010%\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00112\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b&\u0010\u0014\u001a>\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u00162\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0018\u001a>\u0010'\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u00162\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b(\u0010\u0019\u001a>\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010 \u001a\u00020\u001b2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001d\u001a>\u0010)\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u000b2\u0006\u0010 \u001a\u00020\u001b2\b\b\u0002\u0010!\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\u0004H\bø\u0001\u0000ø\u0001\u0001¢\u0006\u0004\b*\u0010\u001e\u0002\u000b\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006+"}, d2 = {"loadByteArray", "", "Lio/ktor/utils/io/bits/Memory;", "offset", "", "destination", "", "destinationOffset", "count", "loadByteArray-9zorpBc", "(Ljava/nio/ByteBuffer;I[BII)V", "", "(Ljava/nio/ByteBuffer;J[BII)V", "loadUByteArray", "Lkotlin/UByteArray;", "loadUByteArray-KqtU1YU", "loadUIntArray", "Lkotlin/UIntArray;", "loadUIntArray-EM3dPTA", "(Ljava/nio/ByteBuffer;I[III)V", "(Ljava/nio/ByteBuffer;J[III)V", "loadULongArray", "Lkotlin/ULongArray;", "loadULongArray-bNlDJKc", "(Ljava/nio/ByteBuffer;I[JII)V", "(Ljava/nio/ByteBuffer;J[JII)V", "loadUShortArray", "Lkotlin/UShortArray;", "loadUShortArray-m8CCUi4", "(Ljava/nio/ByteBuffer;I[SII)V", "(Ljava/nio/ByteBuffer;J[SII)V", "storeByteArray", "source", "sourceOffset", "storeByteArray-9zorpBc", "storeUByteArray", "storeUByteArray-KqtU1YU", "storeUIntArray", "storeUIntArray-EM3dPTA", "storeULongArray", "storeULongArray-bNlDJKc", "storeUShortArray", "storeUShortArray-m8CCUi4", "ktor-io"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PrimiteArrays.kt */
public final class PrimiteArraysKt {
    /* renamed from: loadByteArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1575loadByteArray9zorpBc$default(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1528copyTo9zorpBc(byteBuffer, bArr, i, i3, i2);
    }

    /* renamed from: loadByteArray-9zorpBc  reason: not valid java name */
    public static final void m1573loadByteArray9zorpBc(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1528copyTo9zorpBc(byteBuffer, bArr, i, i3, i2);
    }

    /* renamed from: loadByteArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1576loadByteArray9zorpBc$default(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = bArr.length - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1529copyTo9zorpBc(byteBuffer, bArr, j, i2, i4);
    }

    /* renamed from: loadByteArray-9zorpBc  reason: not valid java name */
    public static final void m1574loadByteArray9zorpBc(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1529copyTo9zorpBc(byteBuffer, bArr, j, i2, i);
    }

    /* renamed from: loadUByteArray-KqtU1YU$default  reason: not valid java name */
    public static /* synthetic */ void m1579loadUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UByteArray.m1851getSizeimpl(bArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1528copyTo9zorpBc(byteBuffer, bArr, i, i3, i2);
    }

    /* renamed from: loadUByteArray-KqtU1YU  reason: not valid java name */
    public static final void m1577loadUByteArrayKqtU1YU(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1528copyTo9zorpBc(byteBuffer, bArr, i, i3, i2);
    }

    /* renamed from: loadUByteArray-KqtU1YU$default  reason: not valid java name */
    public static /* synthetic */ void m1580loadUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = UByteArray.m1851getSizeimpl(bArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1529copyTo9zorpBc(byteBuffer, bArr, j, i2, i4);
    }

    /* renamed from: loadUByteArray-KqtU1YU  reason: not valid java name */
    public static final void m1578loadUByteArrayKqtU1YU(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUByteArray");
        Intrinsics.checkNotNullParameter(bArr, RtspHeaders.Values.DESTINATION);
        MemoryJvmKt.m1529copyTo9zorpBc(byteBuffer, bArr, j, i2, i);
    }

    /* renamed from: loadUShortArray-m8CCUi4$default  reason: not valid java name */
    public static /* synthetic */ void m1591loadUShortArraym8CCUi4$default(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UShortArray.m2114getSizeimpl(sArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(sArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1629loadShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: loadUShortArray-m8CCUi4  reason: not valid java name */
    public static final void m1589loadUShortArraym8CCUi4(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(sArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1629loadShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: loadUShortArray-m8CCUi4$default  reason: not valid java name */
    public static /* synthetic */ void m1592loadUShortArraym8CCUi4$default(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = UShortArray.m2114getSizeimpl(sArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(sArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1630loadShortArray9zorpBc(byteBuffer, j, sArr, i4, i2);
    }

    /* renamed from: loadUShortArray-m8CCUi4  reason: not valid java name */
    public static final void m1590loadUShortArraym8CCUi4(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUShortArray");
        Intrinsics.checkNotNullParameter(sArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1630loadShortArray9zorpBc(byteBuffer, j, sArr, i, i2);
    }

    /* renamed from: loadUIntArray-EM3dPTA$default  reason: not valid java name */
    public static /* synthetic */ void m1583loadUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UIntArray.m1930getSizeimpl(iArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(iArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1621loadIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: loadUIntArray-EM3dPTA  reason: not valid java name */
    public static final void m1581loadUIntArrayEM3dPTA(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(iArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1621loadIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: loadUIntArray-EM3dPTA$default  reason: not valid java name */
    public static /* synthetic */ void m1584loadUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = UIntArray.m1930getSizeimpl(iArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(iArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1622loadIntArray9zorpBc(byteBuffer, j, iArr, i4, i2);
    }

    /* renamed from: loadUIntArray-EM3dPTA  reason: not valid java name */
    public static final void m1582loadUIntArrayEM3dPTA(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadUIntArray");
        Intrinsics.checkNotNullParameter(iArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1622loadIntArray9zorpBc(byteBuffer, j, iArr, i, i2);
    }

    /* renamed from: loadULongArray-bNlDJKc$default  reason: not valid java name */
    public static /* synthetic */ void m1587loadULongArraybNlDJKc$default(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = ULongArray.m2009getSizeimpl(jArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(jArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1625loadLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: loadULongArray-bNlDJKc  reason: not valid java name */
    public static final void m1585loadULongArraybNlDJKc(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(jArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1625loadLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: loadULongArray-bNlDJKc$default  reason: not valid java name */
    public static /* synthetic */ void m1588loadULongArraybNlDJKc$default(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = ULongArray.m2009getSizeimpl(jArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(jArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1626loadLongArray9zorpBc(byteBuffer, j, jArr, i4, i2);
    }

    /* renamed from: loadULongArray-bNlDJKc  reason: not valid java name */
    public static final void m1586loadULongArraybNlDJKc(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$loadULongArray");
        Intrinsics.checkNotNullParameter(jArr, RtspHeaders.Values.DESTINATION);
        PrimitiveArraysJvmKt.m1626loadLongArray9zorpBc(byteBuffer, j, jArr, i, i2);
    }

    /* renamed from: storeByteArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1595storeByteArray9zorpBc$default(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = bArr.length - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i2, i3).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1510copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, i3, i);
    }

    /* renamed from: storeByteArray-9zorpBc$default  reason: not valid java name */
    public static /* synthetic */ void m1596storeByteArray9zorpBc$default(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = bArr.length - i;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i, i2).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1511copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, (long) i2, j);
    }

    /* renamed from: storeUByteArray-KqtU1YU$default  reason: not valid java name */
    public static /* synthetic */ void m1599storeUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UByteArray.m1851getSizeimpl(bArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i2, i3).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1510copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, i3, i);
    }

    /* renamed from: storeUByteArray-KqtU1YU$default  reason: not valid java name */
    public static /* synthetic */ void m1600storeUByteArrayKqtU1YU$default(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i = 0;
        }
        if ((i3 & 8) != 0) {
            i2 = UByteArray.m1851getSizeimpl(bArr) - i;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i, i2).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1511copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, (long) i2, j);
    }

    /* renamed from: storeUShortArray-m8CCUi4$default  reason: not valid java name */
    public static /* synthetic */ void m1611storeUShortArraym8CCUi4$default(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UShortArray.m2114getSizeimpl(sArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(sArr, "source");
        PrimitiveArraysJvmKt.m1649storeShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: storeUShortArray-m8CCUi4  reason: not valid java name */
    public static final void m1609storeUShortArraym8CCUi4(ByteBuffer byteBuffer, int i, short[] sArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(sArr, "source");
        PrimitiveArraysJvmKt.m1649storeShortArray9zorpBc(byteBuffer, i, sArr, i2, i3);
    }

    /* renamed from: storeUShortArray-m8CCUi4$default  reason: not valid java name */
    public static /* synthetic */ void m1612storeUShortArraym8CCUi4$default(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = UShortArray.m2114getSizeimpl(sArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(sArr, "source");
        PrimitiveArraysJvmKt.m1650storeShortArray9zorpBc(byteBuffer, j, sArr, i4, i2);
    }

    /* renamed from: storeUShortArray-m8CCUi4  reason: not valid java name */
    public static final void m1610storeUShortArraym8CCUi4(ByteBuffer byteBuffer, long j, short[] sArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUShortArray");
        Intrinsics.checkNotNullParameter(sArr, "source");
        PrimitiveArraysJvmKt.m1650storeShortArray9zorpBc(byteBuffer, j, sArr, i, i2);
    }

    /* renamed from: storeUIntArray-EM3dPTA$default  reason: not valid java name */
    public static /* synthetic */ void m1603storeUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = UIntArray.m1930getSizeimpl(iArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(iArr, "source");
        PrimitiveArraysJvmKt.m1641storeIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: storeUIntArray-EM3dPTA  reason: not valid java name */
    public static final void m1601storeUIntArrayEM3dPTA(ByteBuffer byteBuffer, int i, int[] iArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(iArr, "source");
        PrimitiveArraysJvmKt.m1641storeIntArray9zorpBc(byteBuffer, i, iArr, i2, i3);
    }

    /* renamed from: storeUIntArray-EM3dPTA$default  reason: not valid java name */
    public static /* synthetic */ void m1604storeUIntArrayEM3dPTA$default(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = UIntArray.m1930getSizeimpl(iArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(iArr, "source");
        PrimitiveArraysJvmKt.m1642storeIntArray9zorpBc(byteBuffer, j, iArr, i4, i2);
    }

    /* renamed from: storeUIntArray-EM3dPTA  reason: not valid java name */
    public static final void m1602storeUIntArrayEM3dPTA(ByteBuffer byteBuffer, long j, int[] iArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUIntArray");
        Intrinsics.checkNotNullParameter(iArr, "source");
        PrimitiveArraysJvmKt.m1642storeIntArray9zorpBc(byteBuffer, j, iArr, i, i2);
    }

    /* renamed from: storeULongArray-bNlDJKc$default  reason: not valid java name */
    public static /* synthetic */ void m1607storeULongArraybNlDJKc$default(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3, int i4, Object obj) {
        if ((i4 & 4) != 0) {
            i2 = 0;
        }
        if ((i4 & 8) != 0) {
            i3 = ULongArray.m2009getSizeimpl(jArr) - i2;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(jArr, "source");
        PrimitiveArraysJvmKt.m1645storeLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: storeULongArray-bNlDJKc  reason: not valid java name */
    public static final void m1605storeULongArraybNlDJKc(ByteBuffer byteBuffer, int i, long[] jArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(jArr, "source");
        PrimitiveArraysJvmKt.m1645storeLongArray9zorpBc(byteBuffer, i, jArr, i2, i3);
    }

    /* renamed from: storeULongArray-bNlDJKc$default  reason: not valid java name */
    public static /* synthetic */ void m1608storeULongArraybNlDJKc$default(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2, int i3, Object obj) {
        int i4 = (i3 & 4) != 0 ? 0 : i;
        if ((i3 & 8) != 0) {
            i2 = ULongArray.m2009getSizeimpl(jArr) - i4;
        }
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(jArr, "source");
        PrimitiveArraysJvmKt.m1646storeLongArray9zorpBc(byteBuffer, j, jArr, i4, i2);
    }

    /* renamed from: storeULongArray-bNlDJKc  reason: not valid java name */
    public static final void m1606storeULongArraybNlDJKc(ByteBuffer byteBuffer, long j, long[] jArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeULongArray");
        Intrinsics.checkNotNullParameter(jArr, "source");
        PrimitiveArraysJvmKt.m1646storeLongArray9zorpBc(byteBuffer, j, jArr, i, i2);
    }

    /* renamed from: storeByteArray-9zorpBc  reason: not valid java name */
    public static final void m1593storeByteArray9zorpBc(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i2, i3).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1510copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, i3, i);
    }

    /* renamed from: storeByteArray-9zorpBc  reason: not valid java name */
    public static final void m1594storeByteArray9zorpBc(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i, i2).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1511copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, (long) i2, j);
    }

    /* renamed from: storeUByteArray-KqtU1YU  reason: not valid java name */
    public static final void m1597storeUByteArrayKqtU1YU(ByteBuffer byteBuffer, int i, byte[] bArr, int i2, int i3) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i2, i3).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1510copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, i3, i);
    }

    /* renamed from: storeUByteArray-KqtU1YU  reason: not valid java name */
    public static final void m1598storeUByteArrayKqtU1YU(ByteBuffer byteBuffer, long j, byte[] bArr, int i, int i2) {
        Intrinsics.checkNotNullParameter(byteBuffer, "$this$storeUByteArray");
        Intrinsics.checkNotNullParameter(bArr, "source");
        ByteBuffer order = ByteBuffer.wrap(bArr, i, i2).slice().order(ByteOrder.BIG_ENDIAN);
        Intrinsics.checkNotNullExpressionValue(order, "wrap(this, offset, lengt…der(ByteOrder.BIG_ENDIAN)");
        Memory.m1511copyToJT6ljtQ(Memory.m1509constructorimpl(order), byteBuffer, 0, (long) i2, j);
    }
}
