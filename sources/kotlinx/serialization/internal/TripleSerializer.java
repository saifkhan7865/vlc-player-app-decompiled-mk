package kotlinx.serialization.internal;

import kotlin.Metadata;
import kotlin.Triple;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialDescriptorsKt;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.encoding.Encoder;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u0002*\u0004\b\u0002\u0010\u00032\u001a\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u00050\u0004B/\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004¢\u0006\u0002\u0010\tJ\"\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0011\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\"\u0010\u0012\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J*\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0018\u0010\u0019\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\u0004\u0012\u00028\u00020\u0005H\u0016R\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00028\u00010\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u001a"}, d2 = {"Lkotlinx/serialization/internal/TripleSerializer;", "A", "B", "C", "Lkotlinx/serialization/KSerializer;", "Lkotlin/Triple;", "aSerializer", "bSerializer", "cSerializer", "(Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;Lkotlinx/serialization/KSerializer;)V", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getDescriptor", "()Lkotlinx/serialization/descriptors/SerialDescriptor;", "decodeSequentially", "composite", "Lkotlinx/serialization/encoding/CompositeDecoder;", "decodeStructure", "deserialize", "decoder", "Lkotlinx/serialization/encoding/Decoder;", "serialize", "", "encoder", "Lkotlinx/serialization/encoding/Encoder;", "value", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Tuples.kt */
public final class TripleSerializer<A, B, C> implements KSerializer<Triple<? extends A, ? extends B, ? extends C>> {
    /* access modifiers changed from: private */
    public final KSerializer<A> aSerializer;
    /* access modifiers changed from: private */
    public final KSerializer<B> bSerializer;
    /* access modifiers changed from: private */
    public final KSerializer<C> cSerializer;
    private final SerialDescriptor descriptor = SerialDescriptorsKt.buildClassSerialDescriptor("kotlin.Triple", new SerialDescriptor[0], new TripleSerializer$descriptor$1(this));

    public TripleSerializer(KSerializer<A> kSerializer, KSerializer<B> kSerializer2, KSerializer<C> kSerializer3) {
        Intrinsics.checkNotNullParameter(kSerializer, "aSerializer");
        Intrinsics.checkNotNullParameter(kSerializer2, "bSerializer");
        Intrinsics.checkNotNullParameter(kSerializer3, "cSerializer");
        this.aSerializer = kSerializer;
        this.bSerializer = kSerializer2;
        this.cSerializer = kSerializer3;
    }

    public SerialDescriptor getDescriptor() {
        return this.descriptor;
    }

    public void serialize(Encoder encoder, Triple<? extends A, ? extends B, ? extends C> triple) {
        Intrinsics.checkNotNullParameter(encoder, "encoder");
        Intrinsics.checkNotNullParameter(triple, "value");
        CompositeEncoder beginStructure = encoder.beginStructure(getDescriptor());
        beginStructure.encodeSerializableElement(getDescriptor(), 0, this.aSerializer, triple.getFirst());
        beginStructure.encodeSerializableElement(getDescriptor(), 1, this.bSerializer, triple.getSecond());
        beginStructure.encodeSerializableElement(getDescriptor(), 2, this.cSerializer, triple.getThird());
        beginStructure.endStructure(getDescriptor());
    }

    public Triple<A, B, C> deserialize(Decoder decoder) {
        Intrinsics.checkNotNullParameter(decoder, "decoder");
        CompositeDecoder beginStructure = decoder.beginStructure(getDescriptor());
        if (beginStructure.decodeSequentially()) {
            return decodeSequentially(beginStructure);
        }
        return decodeStructure(beginStructure);
    }

    private final Triple<A, B, C> decodeSequentially(CompositeDecoder compositeDecoder) {
        Object decodeSerializableElement$default = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 0, this.aSerializer, (Object) null, 8, (Object) null);
        Object decodeSerializableElement$default2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 1, this.bSerializer, (Object) null, 8, (Object) null);
        Object decodeSerializableElement$default3 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 2, this.cSerializer, (Object) null, 8, (Object) null);
        compositeDecoder.endStructure(getDescriptor());
        return new Triple<>(decodeSerializableElement$default, decodeSerializableElement$default2, decodeSerializableElement$default3);
    }

    private final Triple<A, B, C> decodeStructure(CompositeDecoder compositeDecoder) {
        Object access$getNULL$p = TuplesKt.NULL;
        Object access$getNULL$p2 = TuplesKt.NULL;
        Object access$getNULL$p3 = TuplesKt.NULL;
        while (true) {
            int decodeElementIndex = compositeDecoder.decodeElementIndex(getDescriptor());
            if (decodeElementIndex == -1) {
                compositeDecoder.endStructure(getDescriptor());
                if (access$getNULL$p == TuplesKt.NULL) {
                    throw new SerializationException("Element 'first' is missing");
                } else if (access$getNULL$p2 == TuplesKt.NULL) {
                    throw new SerializationException("Element 'second' is missing");
                } else if (access$getNULL$p3 != TuplesKt.NULL) {
                    return new Triple<>(access$getNULL$p, access$getNULL$p2, access$getNULL$p3);
                } else {
                    throw new SerializationException("Element 'third' is missing");
                }
            } else if (decodeElementIndex == 0) {
                access$getNULL$p = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 0, this.aSerializer, (Object) null, 8, (Object) null);
            } else if (decodeElementIndex == 1) {
                access$getNULL$p2 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 1, this.bSerializer, (Object) null, 8, (Object) null);
            } else if (decodeElementIndex == 2) {
                access$getNULL$p3 = CompositeDecoder.DefaultImpls.decodeSerializableElement$default(compositeDecoder, getDescriptor(), 2, this.cSerializer, (Object) null, 8, (Object) null);
            } else {
                throw new SerializationException("Unexpected index " + decodeElementIndex);
            }
        }
    }
}
