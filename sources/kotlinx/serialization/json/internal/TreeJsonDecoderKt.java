package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.InternalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;

@Metadata(d1 = {"\u0000$\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0007¢\u0006\u0002\u0010\u0007\u001a5\u0010\b\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0003\u001a\u00020\u000b2\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u0002H\u00010\u0006H\u0000¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"readJson", "T", "Lkotlinx/serialization/json/Json;", "element", "Lkotlinx/serialization/json/JsonElement;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "readPolymorphicJson", "discriminator", "", "Lkotlinx/serialization/json/JsonObject;", "(Lkotlinx/serialization/json/Json;Ljava/lang/String;Lkotlinx/serialization/json/JsonObject;Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: TreeJsonDecoder.kt */
public final class TreeJsonDecoderKt {
    @InternalSerializationApi
    public static final <T> T readJson(Json json, JsonElement jsonElement, DeserializationStrategy<? extends T> deserializationStrategy) {
        AbstractJsonTreeDecoder abstractJsonTreeDecoder;
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(jsonElement, "element");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        if (jsonElement instanceof JsonObject) {
            abstractJsonTreeDecoder = new JsonTreeDecoder(json, (JsonObject) jsonElement, (String) null, (SerialDescriptor) null, 12, (DefaultConstructorMarker) null);
        } else if (jsonElement instanceof JsonArray) {
            abstractJsonTreeDecoder = new JsonTreeListDecoder(json, (JsonArray) jsonElement);
        } else if (!(jsonElement instanceof JsonLiteral) && !Intrinsics.areEqual((Object) jsonElement, (Object) JsonNull.INSTANCE)) {
            throw new NoWhenBranchMatchedException();
        } else {
            abstractJsonTreeDecoder = new JsonPrimitiveDecoder(json, (JsonPrimitive) jsonElement);
        }
        return abstractJsonTreeDecoder.decodeSerializableValue(deserializationStrategy);
    }

    public static final <T> T readPolymorphicJson(Json json, String str, JsonObject jsonObject, DeserializationStrategy<? extends T> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(str, "discriminator");
        Intrinsics.checkNotNullParameter(jsonObject, "element");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        return new JsonTreeDecoder(json, jsonObject, str, deserializationStrategy.getDescriptor()).decodeSerializableValue(deserializationStrategy);
    }
}
