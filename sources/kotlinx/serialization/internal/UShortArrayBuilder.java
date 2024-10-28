package kotlinx.serialization.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.UShortArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.ExperimentalSerializationApi;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0012\b\u0000\u0012\u0006\u0010\u0003\u001a\u00020\u0002ø\u0001\u0000¢\u0006\u0002\u0010\u0004J\u001d\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0000ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011J\u0018\u0010\u0012\u001a\u00020\u0002H\u0010ø\u0001\u0002ø\u0001\u0001ø\u0001\u0000¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010\u0015\u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\bH\u0010¢\u0006\u0002\b\u0017R\u0019\u0010\u0005\u001a\u00020\u0002X\u000eø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\n\u0002\u0010\u0006R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\b@RX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0002\u000f\n\u0002\b\u0019\n\u0005\b¡\u001e0\u0001\n\u0002\b!¨\u0006\u0018"}, d2 = {"Lkotlinx/serialization/internal/UShortArrayBuilder;", "Lkotlinx/serialization/internal/PrimitiveArrayBuilder;", "Lkotlin/UShortArray;", "bufferWithData", "([SLkotlin/jvm/internal/DefaultConstructorMarker;)V", "buffer", "[S", "<set-?>", "", "position", "getPosition$kotlinx_serialization_core", "()I", "append", "", "c", "Lkotlin/UShort;", "append-xj2QHRw$kotlinx_serialization_core", "(S)V", "build", "build-amswpOA$kotlinx_serialization_core", "()[S", "ensureCapacity", "requiredCapacity", "ensureCapacity$kotlinx_serialization_core", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@ExperimentalSerializationApi
/* compiled from: PrimitiveArraysSerializers.kt */
public final class UShortArrayBuilder extends PrimitiveArrayBuilder<UShortArray> {
    private short[] buffer;
    private int position;

    public /* synthetic */ UShortArrayBuilder(short[] sArr, DefaultConstructorMarker defaultConstructorMarker) {
        this(sArr);
    }

    public /* bridge */ /* synthetic */ Object build$kotlinx_serialization_core() {
        return UShortArray.m2106boximpl(m2420buildamswpOA$kotlinx_serialization_core());
    }

    private UShortArrayBuilder(short[] sArr) {
        Intrinsics.checkNotNullParameter(sArr, "bufferWithData");
        this.buffer = sArr;
        this.position = UShortArray.m2114getSizeimpl(sArr);
        ensureCapacity$kotlinx_serialization_core(10);
    }

    public int getPosition$kotlinx_serialization_core() {
        return this.position;
    }

    public void ensureCapacity$kotlinx_serialization_core(int i) {
        if (UShortArray.m2114getSizeimpl(this.buffer) < i) {
            short[] sArr = this.buffer;
            short[] copyOf = Arrays.copyOf(sArr, RangesKt.coerceAtLeast(i, UShortArray.m2114getSizeimpl(sArr) * 2));
            Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
            this.buffer = UShortArray.m2108constructorimpl(copyOf);
        }
    }

    /* renamed from: append-xj2QHRw$kotlinx_serialization_core  reason: not valid java name */
    public final void m2419appendxj2QHRw$kotlinx_serialization_core(short s) {
        PrimitiveArrayBuilder.ensureCapacity$kotlinx_serialization_core$default(this, 0, 1, (Object) null);
        short[] sArr = this.buffer;
        int position$kotlinx_serialization_core = getPosition$kotlinx_serialization_core();
        this.position = position$kotlinx_serialization_core + 1;
        UShortArray.m2118set01HTLdE(sArr, position$kotlinx_serialization_core, s);
    }

    /* renamed from: build-amswpOA$kotlinx_serialization_core  reason: not valid java name */
    public short[] m2420buildamswpOA$kotlinx_serialization_core() {
        short[] copyOf = Arrays.copyOf(this.buffer, getPosition$kotlinx_serialization_core());
        Intrinsics.checkNotNullExpressionValue(copyOf, "copyOf(this, newSize)");
        return UShortArray.m2108constructorimpl(copyOf);
    }
}