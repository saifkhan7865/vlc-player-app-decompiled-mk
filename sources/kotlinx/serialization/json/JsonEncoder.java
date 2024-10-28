package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.encoding.Encoder;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\u000b"}, d2 = {"Lkotlinx/serialization/json/JsonEncoder;", "Lkotlinx/serialization/encoding/Encoder;", "Lkotlinx/serialization/encoding/CompositeEncoder;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "encodeJsonElement", "", "element", "Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonEncoder.kt */
public interface JsonEncoder extends Encoder, CompositeEncoder {
    void encodeJsonElement(JsonElement jsonElement);

    Json getJson();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: JsonEncoder.kt */
    public static final class DefaultImpls {
        public static CompositeEncoder beginCollection(JsonEncoder jsonEncoder, SerialDescriptor serialDescriptor, int i) {
            Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
            return Encoder.DefaultImpls.beginCollection(jsonEncoder, serialDescriptor, i);
        }

        @ExperimentalSerializationApi
        public static void encodeNotNullMark(JsonEncoder jsonEncoder) {
            Encoder.DefaultImpls.encodeNotNullMark(jsonEncoder);
        }

        @ExperimentalSerializationApi
        public static <T> void encodeNullableSerializableValue(JsonEncoder jsonEncoder, SerializationStrategy<? super T> serializationStrategy, T t) {
            Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
            Encoder.DefaultImpls.encodeNullableSerializableValue(jsonEncoder, serializationStrategy, t);
        }

        public static <T> void encodeSerializableValue(JsonEncoder jsonEncoder, SerializationStrategy<? super T> serializationStrategy, T t) {
            Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
            Encoder.DefaultImpls.encodeSerializableValue(jsonEncoder, serializationStrategy, t);
        }

        @ExperimentalSerializationApi
        public static boolean shouldEncodeElementDefault(JsonEncoder jsonEncoder, SerialDescriptor serialDescriptor, int i) {
            Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
            return CompositeEncoder.DefaultImpls.shouldEncodeElementDefault(jsonEncoder, serialDescriptor, i);
        }
    }
}
