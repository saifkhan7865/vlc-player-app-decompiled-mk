package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.UByte;
import kotlin.UByteArray;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.builtins.BuiltinSerializersKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\bÁ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u0014\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00050\u0003B\u0007\b\u0002¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\u0002H\u0014ø\u0001\u0000ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\b\u0010\tJ(\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0012H\u0014J-\u0010\u0013\u001a\u00020\u000b2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0017\u001a\u00020\u000fH\u0014ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u0018\u0010\u0019J\u0019\u0010\u001a\u001a\u00020\u000f*\u00020\u0002H\u0014ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001b\u0010\u001cJ\u0019\u0010\u001d\u001a\u00020\u0005*\u00020\u0002H\u0014ø\u0001\u0001ø\u0001\u0002¢\u0006\u0004\b\u001e\u0010\u001fø\u0001\u0002\u0002\u000f\n\u0002\b!\n\u0005\b¡\u001e0\u0001\n\u0002\b\u0019¨\u0006 "}, d2 = {"Lkotlinx/serialization/internal/UByteArraySerializer;", "Lkotlinx/serialization/KSerializer;", "Lkotlin/UByteArray;", "Lkotlinx/serialization/internal/PrimitiveArraySerializer;", "Lkotlin/UByte;", "Lkotlinx/serialization/internal/UByteArrayBuilder;", "()V", "empty", "empty-TcUX1vc", "()[B", "readElement", "", "decoder", "Lkotlinx/serialization/encoding/CompositeDecoder;", "index", "", "builder", "checkIndex", "", "writeContent", "encoder", "Lkotlinx/serialization/encoding/CompositeEncoder;", "content", "size", "writeContent-Coi6ktg", "(Lkotlinx/serialization/encoding/CompositeEncoder;[BI)V", "collectionSize", "collectionSize-GBYM_sE", "([B)I", "toBuilder", "toBuilder-GBYM_sE", "([B)Lkotlinx/serialization/internal/UByteArrayBuilder;", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@ExperimentalSerializationApi
/* compiled from: PrimitiveArraysSerializers.kt */
public final class UByteArraySerializer extends PrimitiveArraySerializer<UByte, UByteArray, UByteArrayBuilder> implements KSerializer<UByteArray> {
    public static final UByteArraySerializer INSTANCE = new UByteArraySerializer();

    public /* bridge */ /* synthetic */ int collectionSize(Object obj) {
        return m2397collectionSizeGBYM_sE(((UByteArray) obj).m1859unboximpl());
    }

    public /* bridge */ /* synthetic */ Object empty() {
        return UByteArray.m1843boximpl(m2398emptyTcUX1vc());
    }

    public /* bridge */ /* synthetic */ Object toBuilder(Object obj) {
        return m2399toBuilderGBYM_sE(((UByteArray) obj).m1859unboximpl());
    }

    public /* bridge */ /* synthetic */ void writeContent(CompositeEncoder compositeEncoder, Object obj, int i) {
        m2400writeContentCoi6ktg(compositeEncoder, ((UByteArray) obj).m1859unboximpl(), i);
    }

    private UByteArraySerializer() {
        super(BuiltinSerializersKt.serializer(UByte.Companion));
    }

    /* access modifiers changed from: protected */
    /* renamed from: collectionSize-GBYM_sE  reason: not valid java name */
    public int m2397collectionSizeGBYM_sE(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "$this$collectionSize");
        return UByteArray.m1851getSizeimpl(bArr);
    }

    /* access modifiers changed from: protected */
    /* renamed from: toBuilder-GBYM_sE  reason: not valid java name */
    public UByteArrayBuilder m2399toBuilderGBYM_sE(byte[] bArr) {
        Intrinsics.checkNotNullParameter(bArr, "$this$toBuilder");
        return new UByteArrayBuilder(bArr, (DefaultConstructorMarker) null);
    }

    /* access modifiers changed from: protected */
    /* renamed from: empty-TcUX1vc  reason: not valid java name */
    public byte[] m2398emptyTcUX1vc() {
        return UByteArray.m1844constructorimpl(0);
    }

    /* access modifiers changed from: protected */
    public void readElement(CompositeDecoder compositeDecoder, int i, UByteArrayBuilder uByteArrayBuilder, boolean z) {
        Intrinsics.checkNotNullParameter(compositeDecoder, "decoder");
        Intrinsics.checkNotNullParameter(uByteArrayBuilder, "builder");
        uByteArrayBuilder.m2395append7apg3OU$kotlinx_serialization_core(UByte.m1792constructorimpl(compositeDecoder.decodeInlineElement(getDescriptor(), i).decodeByte()));
    }

    /* access modifiers changed from: protected */
    /* renamed from: writeContent-Coi6ktg  reason: not valid java name */
    public void m2400writeContentCoi6ktg(CompositeEncoder compositeEncoder, byte[] bArr, int i) {
        Intrinsics.checkNotNullParameter(compositeEncoder, "encoder");
        Intrinsics.checkNotNullParameter(bArr, "content");
        for (int i2 = 0; i2 < i; i2++) {
            compositeEncoder.encodeInlineElement(getDescriptor(), i2).encodeByte(UByteArray.m1850getw2LRezQ(bArr, i2));
        }
    }
}
