package kotlinx.serialization.internal;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.MissingFieldException;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u001a \u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007¨\u0006\u000b"}, d2 = {"throwArrayMissingFieldException", "", "seenArray", "", "goldenMaskArray", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "throwMissingFieldException", "seen", "", "goldenMask", "kotlinx-serialization-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: PluginExceptions.kt */
public final class PluginExceptionsKt {
    @InternalSerializationApi
    public static final void throwMissingFieldException(int i, int i2, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        List arrayList = new ArrayList();
        int i3 = (i ^ -1) & i2;
        for (int i4 = 0; i4 < 32; i4++) {
            if ((i3 & 1) != 0) {
                arrayList.add(serialDescriptor.getElementName(i4));
            }
            i3 >>>= 1;
        }
        throw new MissingFieldException((List<String>) arrayList, serialDescriptor.getSerialName());
    }

    @InternalSerializationApi
    public static final void throwArrayMissingFieldException(int[] iArr, int[] iArr2, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(iArr, "seenArray");
        Intrinsics.checkNotNullParameter(iArr2, "goldenMaskArray");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        List arrayList = new ArrayList();
        int length = iArr2.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr2[i] & (iArr[i] ^ -1);
            if (i2 != 0) {
                for (int i3 = 0; i3 < 32; i3++) {
                    if ((i2 & 1) != 0) {
                        arrayList.add(serialDescriptor.getElementName((i * 32) + i3));
                    }
                    i2 >>>= 1;
                }
            }
        }
        throw new MissingFieldException((List<String>) arrayList, serialDescriptor.getSerialName());
    }
}
