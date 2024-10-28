package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.ULong;
import kotlin.ULongArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u0002H\u0014ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\tJ(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J-\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000fH\u0014ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u000f*\u00020\u0002H\u0014ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u0005*\u00020\u0002H\u0014ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001e\u0010\u001fø\u0001\u0002\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lkotlinx/serialization/internal/ULongArraySerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlin/ULongArray;", "Lkotlinx/serialization/internal/PrimitiveArraySerializer;", "Lkotlin/ULong;", "Lkotlinx/serialization/internal/ULongArrayBuilder;", "()V", "empty", "empty-Y2RjT0g", "()[J", "readElement", "", "decoder", "Lkotlinx/serialization/encoding/CompositeDecoder;", "index", "", "builder", "checkIndex", "", "writeContent", "encoder", "Lkotlinx/serialization/encoding/CompositeEncoder;", "content", "size", "writeContent-0q3Fkuo", "(Lkotlinx/serialization/encoding/CompositeEncoder;[JI)V", "collectionSize", "collectionSize-QwZRm1k", "([J)I", "toBuilder", "toBuilder-QwZRm1k", "([J)Lkotlinx/serialization/internal/ULongArrayBuilder;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@ExperimentalSerializationApi
/* compiled from: PrimitiveArraysSerializers.kt */
public final class ULongArraySerializer extends PrimitiveArraySerializer<ULong, ULongArray, ULongArrayBuilder> implements KSerializer<ULongArray> {
    public static final ULongArraySerializer INSTANCE = new ULongArraySerializer();

    public /* bridge */ /* synthetic */ int collectionSize(Object obj) {
        return m2413collectionSizeQwZRm1k(((ULongArray) obj).m2017unboximpl());
    }

    public /* bridge */ /* synthetic */ Object empty() {
        return ULongArray.m2001boximpl(m2414emptyY2RjT0g());
    }

    public /* bridge */ /* synthetic */ Object toBuilder(Object obj) {
        return m2415toBuilderQwZRm1k(((ULongArray) obj).m2017unboximpl());
    }

    public /* bridge */ /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, Object obj, int i) {
        m2416writeContent0q3Fkuo(compositeEncoder, ((ULongArray) obj).m2017unboximpl(), i);
    }

    private ULongArraySerializer() {
        super(BuiltinSerializersKt.serializer(ULong.Companion));
    }

    /* access modifiers changed from: protected */
    /* renamed from: collectionSize-QwZRm1k  reason: not valid java name */
    public int m2413collectionSizeQwZRm1k(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "$this$collectionSize");
        return ULongArray.m2009getSizeimpl(jArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: toBuilder-QwZRm1k  reason: not valid java name */
    public ULongArrayBuilder m2415toBuilderQwZRm1k(long[] jArr) {
        Intrinsics.checkNotNullParameter(jArr, "$this$toBuilder");
        return new ULongArrayBuilder(jArr, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: empty-Y2RjT0g  reason: not valid java name */
    public long[] m2414emptyY2RjT0g() {
        return ULongArray.m2002constructorimpl(0);
    }

    /* access modifiers changed from: protected */
    public void readElement(CompositeDecoder compositeDecoder, int i, ULongArrayBuilder uLongArrayBuilder, boolean z) {
        Intrinsics.checkNotNullParameter(compositeDecoder, "decoder");
        Intrinsics.checkNotNullParameter(uLongArrayBuilder, "builder");
        uLongArrayBuilder.m2411appendVKZWuLQ$kotlinx_serialization_core(ULong.m1948constructorimpl(compositeDecoder.decodeInlineElement(getDescriptor(), i).decodeLong()));
    }

    /* access modifiers changed from: protected */
    /* renamed from: writeContent-0q3Fkuo  reason: not valid java name */
    public void m2416writeContent0q3Fkuo(CompositeEncoder compositeEncoder, long[] jArr, int i) {
        Intrinsics.checkNotNullParameter(compositeEncoder, "encoder");
        Intrinsics.checkNotNullParameter(jArr, "content");
        for (int i2 = 0; i2 < i; i2++) {
            compositeEncoder.encodeInlineElement(getDescriptor(), i2).encodeLong(ULongArray.m2008getsVKNKU(jArr, i2));
        }
    }
}
