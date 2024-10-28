package kotlinx.serialization.encoding;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u0000 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a>\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0004\u0010\u0005\u001a\u0013\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u0002H\u00010\u0006¢\u0006\u0002\b\bH\bø\u0001\u0000¢\u0006\u0002\u0010\t\u0002\u0007\n\u0005\b20\u0001¨\u0006\n"}, d2 = {"decodeStructure", "T", "Lkotlinx/serialization/encoding/Decoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "block", "Lkotlin/Function1;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/serialization/encoding/Decoder;Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Decoding.kt */
public final class DecodingKt {
    public static final <T> T decodeStructure(Decoder decoder, SerialDescriptor serialDescriptor, Function1<? super CompositeDecoder, ? extends T> function1) {
        Intrinsics.checkNotNullParameter(decoder, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(function1, "block");
        CompositeDecoder beginStructure = decoder.beginStructure(serialDescriptor);
        T invoke = function1.invoke(beginStructure);
        beginStructure.endStructure(serialDescriptor);
        return invoke;
    }
}
