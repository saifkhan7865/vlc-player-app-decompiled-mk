package kotlinx.serialization.json.internal;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import java.util.Map;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonArraySerializer;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonObjectSerializer;
import kotlinx.serialization.json.JsonPrimitive;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B!\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005¢\u0006\u0002\u0010\bJ\b\u0010\r\u001a\u00020\u0006H\u0016J\u0018\u0010\u000e\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\f2\u0006\u0010\u0010\u001a\u00020\u0006H\u0016R\u000e\u0010\t\u001a\u00020\nX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX.¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeMapEncoder;", "Lkotlinx/serialization/json/internal/JsonTreeEncoder;", "json", "Lkotlinx/serialization/json/Json;", "nodeConsumer", "Lkotlin/Function1;", "Lkotlinx/serialization/json/JsonElement;", "", "(Lkotlinx/serialization/json/Json;Lkotlin/jvm/functions/Function1;)V", "isKey", "", "tag", "", "getCurrent", "putElement", "key", "element", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TreeJsonEncoder.kt */
final class JsonTreeMapEncoder extends JsonTreeEncoder {
    private boolean isKey = true;
    private String tag;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonTreeMapEncoder(Json json, Function1<? super JsonElement, Unit> function1) {
        super(json, function1);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(function1, "nodeConsumer");
    }

    public void putElement(String str, JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(jsonElement, "element");
        if (!this.isKey) {
            Map<String, JsonElement> content = getContent();
            String str2 = this.tag;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("tag");
                str2 = null;
            }
            content.put(str2, jsonElement);
            this.isKey = true;
        } else if (jsonElement instanceof JsonPrimitive) {
            this.tag = ((JsonPrimitive) jsonElement).getContent();
            this.isKey = false;
        } else if (jsonElement instanceof JsonObject) {
            throw JsonExceptionsKt.InvalidKeyKindException(JsonObjectSerializer.INSTANCE.getDescriptor());
        } else if (jsonElement instanceof JsonArray) {
            throw JsonExceptionsKt.InvalidKeyKindException(JsonArraySerializer.INSTANCE.getDescriptor());
        } else {
            throw new NoWhenBranchMatchedException();
        }
    }

    public JsonElement getCurrent() {
        return new JsonObject(getContent());
    }
}
