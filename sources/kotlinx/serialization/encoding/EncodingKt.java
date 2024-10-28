package kotlinx.serialization.encoding;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a;\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\nH\bø\u0001\u0000\u001ab\u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u000b*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\f\u001a\b\u0012\u0004\u0012\u0002H\u000b0\r24\b\u0004\u0010\u0007\u001a.\u0012\u0004\u0012\u00020\t\u0012\u0013\u0012\u00110\u0006¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\u0004\u0012\u0002H\u000b\u0012\u0004\u0012\u00020\u00010\u000e¢\u0006\u0002\b\nH\bø\u0001\u0000\u001a3\u0010\u0012\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0019\b\u0004\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00010\b¢\u0006\u0002\b\nH\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u0013"}, d2 = {"encodeCollection", "", "Lkotlinx/serialization/encoding/Encoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "collectionSize", "", "block", "Lkotlin/Function1;", "Lkotlinx/serialization/encoding/CompositeEncoder;", "Lkotlin/ExtensionFunctionType;", "E", "collection", "", "Lkotlin/Function3;", "Lkotlin/ParameterName;", "name", "index", "encodeStructure", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Encoding.kt */
public final class EncodingKt {
    public static final void encodeStructure(Encoder encoder, SerialDescriptor serialDescriptor, Function1<? super CompositeEncoder, Unit> function1) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(function1, "block");
        CompositeEncoder beginStructure = encoder.beginStructure(serialDescriptor);
        function1.invoke(beginStructure);
        beginStructure.endStructure(serialDescriptor);
    }

    public static final void encodeCollection(Encoder encoder, SerialDescriptor serialDescriptor, int i, Function1<? super CompositeEncoder, Unit> function1) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(function1, "block");
        CompositeEncoder beginCollection = encoder.beginCollection(serialDescriptor, i);
        function1.invoke(beginCollection);
        beginCollection.endStructure(serialDescriptor);
    }

    public static final <E> void encodeCollection(Encoder encoder, SerialDescriptor serialDescriptor, Collection<? extends E> collection, Function3<? super CompositeEncoder, ? super Integer, ? super E, Unit> function3) {
        Intrinsics.checkNotNullParameter(encoder, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(collection, "collection");
        Intrinsics.checkNotNullParameter(function3, "block");
        CompositeEncoder beginCollection = encoder.beginCollection(serialDescriptor, collection.size());
        int i = 0;
        for (Object next : collection) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            function3.invoke(beginCollection, Integer.valueOf(i), next);
            i = i2;
        }
        beginCollection.endStructure(serialDescriptor);
    }
}
