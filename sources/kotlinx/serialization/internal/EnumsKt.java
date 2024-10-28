package kotlinx.serialization.internal;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.KSerializer;

@Metadata(d1 = {"\u0000$\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010\u001b\n\u0002\b\b\u001ao\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\u0014\u0010\t\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00070\u00072\u000e\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u0007H\u0001¢\u0006\u0002\u0010\f\u001a_\u0010\r\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u00072\u000e\u0010\b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u00072\u0014\u0010\u000e\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\n\u0018\u00010\u00070\u0007H\u0001¢\u0006\u0002\u0010\u000f\u001a9\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007H\u0001¢\u0006\u0002\u0010\u0011¨\u0006\u0012"}, d2 = {"createAnnotatedEnumSerializer", "Lkotlinx/serialization/KSerializer;", "T", "", "serialName", "", "values", "", "names", "entryAnnotations", "", "classAnnotations", "(Ljava/lang/String;[Ljava/lang/Enum;[Ljava/lang/String;[[Ljava/lang/annotation/Annotation;[Ljava/lang/annotation/Annotation;)Lkotlinx/serialization/KSerializer;", "createMarkedEnumSerializer", "annotations", "(Ljava/lang/String;[Ljava/lang/Enum;[Ljava/lang/String;[[Ljava/lang/annotation/Annotation;)Lkotlinx/serialization/KSerializer;", "createSimpleEnumSerializer", "(Ljava/lang/String;[Ljava/lang/Enum;)Lkotlinx/serialization/KSerializer;", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Enums.kt */
public final class EnumsKt {
    @InternalSerializationApi
    public static final <T extends Enum<T>> KSerializer<T> createSimpleEnumSerializer(String str, T[] tArr) {
        Intrinsics.checkNotNullParameter(str, "serialName");
        Intrinsics.checkNotNullParameter(tArr, "values");
        return new EnumSerializer<>(str, tArr);
    }

    @InternalSerializationApi
    public static final <T extends Enum<T>> KSerializer<T> createMarkedEnumSerializer(String str, T[] tArr, String[] strArr, Annotation[][] annotationArr) {
        Intrinsics.checkNotNullParameter(str, "serialName");
        Intrinsics.checkNotNullParameter(tArr, "values");
        Intrinsics.checkNotNullParameter(strArr, "names");
        Intrinsics.checkNotNullParameter(annotationArr, "annotations");
        EnumDescriptor enumDescriptor = new EnumDescriptor(str, tArr.length);
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            T t = tArr[i];
            int i3 = i2 + 1;
            String str2 = (String) ArraysKt.getOrNull((T[]) strArr, i2);
            if (str2 == null) {
                str2 = t.name();
            }
            PluginGeneratedSerialDescriptor.addElement$default(enumDescriptor, str2, false, 2, (Object) null);
            Annotation[] annotationArr2 = (Annotation[]) ArraysKt.getOrNull((T[]) (Object[]) annotationArr, i2);
            if (annotationArr2 != null) {
                for (Annotation pushAnnotation : annotationArr2) {
                    enumDescriptor.pushAnnotation(pushAnnotation);
                }
            }
            i++;
            i2 = i3;
        }
        return new EnumSerializer<>(str, tArr, enumDescriptor);
    }

    @InternalSerializationApi
    public static final <T extends Enum<T>> KSerializer<T> createAnnotatedEnumSerializer(String str, T[] tArr, String[] strArr, Annotation[][] annotationArr, Annotation[] annotationArr2) {
        Intrinsics.checkNotNullParameter(str, "serialName");
        Intrinsics.checkNotNullParameter(tArr, "values");
        Intrinsics.checkNotNullParameter(strArr, "names");
        Intrinsics.checkNotNullParameter(annotationArr, "entryAnnotations");
        EnumDescriptor enumDescriptor = new EnumDescriptor(str, tArr.length);
        if (annotationArr2 != null) {
            for (Annotation pushClassAnnotation : annotationArr2) {
                enumDescriptor.pushClassAnnotation(pushClassAnnotation);
            }
        }
        int length = tArr.length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            T t = tArr[i];
            int i3 = i2 + 1;
            String str2 = (String) ArraysKt.getOrNull((T[]) strArr, i2);
            if (str2 == null) {
                str2 = t.name();
            }
            PluginGeneratedSerialDescriptor.addElement$default(enumDescriptor, str2, false, 2, (Object) null);
            Annotation[] annotationArr3 = (Annotation[]) ArraysKt.getOrNull((T[]) (Object[]) annotationArr, i2);
            if (annotationArr3 != null) {
                for (Annotation pushAnnotation : annotationArr3) {
                    enumDescriptor.pushAnnotation(pushAnnotation);
                }
            }
            i++;
            i2 = i3;
        }
        return new EnumSerializer<>(str, tArr, enumDescriptor);
    }
}
