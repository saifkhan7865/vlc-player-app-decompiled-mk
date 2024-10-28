package kotlinx.serialization.internal;

import io.ktor.http.ContentDisposition;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0002\u0010\u0006J\u0013\u0010\n\u001a\u00020\b2\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0002J\b\u0010\r\u001a\u00020\u000eH\u0016R\u0014\u0010\u0007\u001a\u00020\bXD¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\t¨\u0006\u000f"}, d2 = {"Lkotlinx/serialization/internal/InlineClassDescriptor;", "Lkotlinx/serialization/internal/PluginGeneratedSerialDescriptor;", "name", "", "generatedSerializer", "Lkotlinx/serialization/internal/GeneratedSerializer;", "(Ljava/lang/String;Lkotlinx/serialization/internal/GeneratedSerializer;)V", "isInline", "", "()Z", "equals", "other", "", "hashCode", "", "kotlinx-serialization-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: InlineClassDescriptor.kt */
public final class InlineClassDescriptor extends PluginGeneratedSerialDescriptor {
    private final boolean isInline = true;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public InlineClassDescriptor(String str, GeneratedSerializer<?> generatedSerializer) {
        super(str, generatedSerializer, 1);
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(generatedSerializer, "generatedSerializer");
    }

    public boolean isInline() {
        return this.isInline;
    }

    public int hashCode() {
        return super.hashCode() * 31;
    }

    public boolean equals(Object obj) {
        SerialDescriptor serialDescriptor = this;
        if (serialDescriptor == obj) {
            return true;
        }
        if (obj instanceof InlineClassDescriptor) {
            SerialDescriptor serialDescriptor2 = (SerialDescriptor) obj;
            if (Intrinsics.areEqual((Object) serialDescriptor.getSerialName(), (Object) serialDescriptor2.getSerialName())) {
                InlineClassDescriptor inlineClassDescriptor = (InlineClassDescriptor) obj;
                if (inlineClassDescriptor.isInline() && Arrays.equals(getTypeParameterDescriptors$kotlinx_serialization_core(), inlineClassDescriptor.getTypeParameterDescriptors$kotlinx_serialization_core()) && serialDescriptor.getElementsCount() == serialDescriptor2.getElementsCount()) {
                    int elementsCount = serialDescriptor.getElementsCount();
                    int i = 0;
                    while (i < elementsCount) {
                        if (Intrinsics.areEqual((Object) serialDescriptor.getElementDescriptor(i).getSerialName(), (Object) serialDescriptor2.getElementDescriptor(i).getSerialName()) && Intrinsics.areEqual((Object) serialDescriptor.getElementDescriptor(i).getKind(), (Object) serialDescriptor2.getElementDescriptor(i).getKind())) {
                            i++;
                        }
                    }
                    return true;
                }
            }
        }
        return false;
    }
}
