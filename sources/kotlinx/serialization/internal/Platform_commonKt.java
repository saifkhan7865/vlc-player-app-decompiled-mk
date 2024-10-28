package kotlinx.serialization.internal;

import java.util.HashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KClass;
import kotlin.reflect.KClassifier;
import kotlin.reflect.KType;
import kotlin.reflect.KTypeParameter;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.KSerializer;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0000\u001a\u0012\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00070\n*\u00020\u0002H\u0000\u001a\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\f\"\u0004\b\u0000\u0010\r*\u0006\u0012\u0002\b\u00030\fH\b\u001a\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\u000e\"\u0004\b\u0000\u0010\r*\u0006\u0012\u0002\b\u00030\u000eH\b\u001a\u001d\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\r0\u000f\"\u0004\b\u0000\u0010\r*\u0006\u0012\u0002\b\u00030\u000fH\b\u001a\u001f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0011H\u0000¢\u0006\u0002\u0010\u0012\u001a6\u0010\u0013\u001a\u00020\u0014\"\u0004\b\u0000\u0010\r\"\u0004\b\u0001\u0010\u0015*\b\u0012\u0004\u0012\u0002H\r0\u00162\u0012\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u0002H\r\u0012\u0004\u0012\u0002H\u00150\u0018H\bø\u0001\u0000\u001a\u0012\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a*\u00020\u001cH\u0000\u001a\u0010\u0010\u0006\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u001aH\u0000\u001a\u0010\u0010\u001d\u001a\u00020\u001e*\u0006\u0012\u0002\b\u00030\u001aH\u0000\"\u001e\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00018\u0002X\u0004¢\u0006\n\n\u0002\u0010\u0005\u0012\u0004\b\u0003\u0010\u0004\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001f"}, d2 = {"EMPTY_DESCRIPTOR_ARRAY", "", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "getEMPTY_DESCRIPTOR_ARRAY$annotations", "()V", "[Lkotlinx/serialization/descriptors/SerialDescriptor;", "notRegisteredMessage", "", "className", "cachedSerialNames", "", "cast", "Lkotlinx/serialization/DeserializationStrategy;", "T", "Lkotlinx/serialization/KSerializer;", "Lkotlinx/serialization/SerializationStrategy;", "compactArray", "", "(Ljava/util/List;)[Lkotlinx/serialization/descriptors/SerialDescriptor;", "elementsHashCodeBy", "", "K", "", "selector", "Lkotlin/Function1;", "kclass", "Lkotlin/reflect/KClass;", "", "Lkotlin/reflect/KType;", "serializerNotRegistered", "", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Platform.common.kt */
public final class Platform_commonKt {
    private static final SerialDescriptor[] EMPTY_DESCRIPTOR_ARRAY = new SerialDescriptor[0];

    public static final <T> DeserializationStrategy<T> cast(DeserializationStrategy<?> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "<this>");
        return deserializationStrategy;
    }

    public static final <T> KSerializer<T> cast(KSerializer<?> kSerializer) {
        Intrinsics.checkNotNullParameter(kSerializer, "<this>");
        return kSerializer;
    }

    public static final <T> SerializationStrategy<T> cast(SerializationStrategy<?> serializationStrategy) {
        Intrinsics.checkNotNullParameter(serializationStrategy, "<this>");
        return serializationStrategy;
    }

    private static /* synthetic */ void getEMPTY_DESCRIPTOR_ARRAY$annotations() {
    }

    public static final Set<String> cachedSerialNames(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        if (serialDescriptor instanceof CachedNames) {
            return ((CachedNames) serialDescriptor).getSerialNames();
        }
        HashSet hashSet = new HashSet(serialDescriptor.getElementsCount());
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            hashSet.add(serialDescriptor.getElementName(i));
        }
        return hashSet;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:6:0x000e, code lost:
        r1 = (kotlinx.serialization.descriptors.SerialDescriptor[]) r1.toArray(new kotlinx.serialization.descriptors.SerialDescriptor[0]);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlinx.serialization.descriptors.SerialDescriptor[] compactArray(java.util.List<? extends kotlinx.serialization.descriptors.SerialDescriptor> r1) {
        /*
            r0 = r1
            java.util.Collection r0 = (java.util.Collection) r0
            if (r0 == 0) goto L_0x000b
            boolean r0 = r0.isEmpty()
            if (r0 == 0) goto L_0x000c
        L_0x000b:
            r1 = 0
        L_0x000c:
            if (r1 == 0) goto L_0x001b
            java.util.Collection r1 = (java.util.Collection) r1
            r0 = 0
            kotlinx.serialization.descriptors.SerialDescriptor[] r0 = new kotlinx.serialization.descriptors.SerialDescriptor[r0]
            java.lang.Object[] r1 = r1.toArray(r0)
            kotlinx.serialization.descriptors.SerialDescriptor[] r1 = (kotlinx.serialization.descriptors.SerialDescriptor[]) r1
            if (r1 != 0) goto L_0x001d
        L_0x001b:
            kotlinx.serialization.descriptors.SerialDescriptor[] r1 = EMPTY_DESCRIPTOR_ARRAY
        L_0x001d:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.internal.Platform_commonKt.compactArray(java.util.List):kotlinx.serialization.descriptors.SerialDescriptor[]");
    }

    public static final Void serializerNotRegistered(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        throw new SerializationException(notRegisteredMessage(kClass));
    }

    public static final String notRegisteredMessage(KClass<?> kClass) {
        Intrinsics.checkNotNullParameter(kClass, "<this>");
        String simpleName = kClass.getSimpleName();
        if (simpleName == null) {
            simpleName = "<local class name not available>";
        }
        return notRegisteredMessage(simpleName);
    }

    public static final String notRegisteredMessage(String str) {
        Intrinsics.checkNotNullParameter(str, "className");
        return "Serializer for class '" + str + "' is not found.\nPlease ensure that class is marked as '@Serializable' and that the serialization compiler plugin is applied.\n";
    }

    public static final KClass<Object> kclass(KType kType) {
        Intrinsics.checkNotNullParameter(kType, "<this>");
        KClassifier classifier = kType.getClassifier();
        if (classifier instanceof KClass) {
            return (KClass) classifier;
        }
        if (classifier instanceof KTypeParameter) {
            throw new IllegalStateException(("Captured type parameter " + classifier + " from generic non-reified function. Such functionality cannot be supported as " + classifier + " is erased, either specify serializer explicitly or make calling function inline with reified " + classifier).toString());
        }
        throw new IllegalStateException(("Only KClass supported as classifier, got " + classifier).toString());
    }

    public static final <T, K> int elementsHashCodeBy(Iterable<? extends T> iterable, Function1<? super T, ? extends K> function1) {
        Intrinsics.checkNotNullParameter(iterable, "<this>");
        Intrinsics.checkNotNullParameter(function1, "selector");
        int i = 1;
        for (Object invoke : iterable) {
            int i2 = i * 31;
            Object invoke2 = function1.invoke(invoke);
            i = i2 + (invoke2 != null ? invoke2.hashCode() : 0);
        }
        return i;
    }
}
