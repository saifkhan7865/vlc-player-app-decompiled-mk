package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.JsonNamingStrategy;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001H\n¢\u0006\u0004\b\u0003\u0010\u0004"}, d2 = {"<anonymous>", "", "", "invoke", "()[Ljava/lang/String;"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonNamesMap.kt */
final class JsonNamesMapKt$serializationNamesIndices$1 extends Lambda implements Function0<String[]> {
    final /* synthetic */ JsonNamingStrategy $strategy;
    final /* synthetic */ SerialDescriptor $this_serializationNamesIndices;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    JsonNamesMapKt$serializationNamesIndices$1(SerialDescriptor serialDescriptor, JsonNamingStrategy jsonNamingStrategy) {
        super(0);
        this.$this_serializationNamesIndices = serialDescriptor;
        this.$strategy = jsonNamingStrategy;
    }

    public final String[] invoke() {
        int elementsCount = this.$this_serializationNamesIndices.getElementsCount();
        String[] strArr = new String[elementsCount];
        for (int i = 0; i < elementsCount; i++) {
            strArr[i] = this.$strategy.serialNameForJson(this.$this_serializationNamesIndices, i, this.$this_serializationNamesIndices.getElementName(i));
        }
        return strArr;
    }
}
