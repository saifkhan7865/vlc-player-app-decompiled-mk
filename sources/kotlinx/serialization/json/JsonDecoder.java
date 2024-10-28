package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;

@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0007\u001a\u00020\bH&R\u0012\u0010\u0003\u001a\u00020\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006¨\u0006\t"}, d2 = {"Lkotlinx/serialization/json/JsonDecoder;", "Lkotlinx/serialization/encoding/Decoder;", "Lkotlinx/serialization/encoding/CompositeDecoder;", "json", "Lkotlinx/serialization/json/Json;", "getJson", "()Lkotlinx/serialization/json/Json;", "decodeJsonElement", "Lkotlinx/serialization/json/JsonElement;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonDecoder.kt */
public interface JsonDecoder extends Decoder, CompositeDecoder {
    JsonElement decodeJsonElement();

    Json getJson();

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: JsonDecoder.kt */
    public static final class DefaultImpls {
        public static int decodeCollectionSize(JsonDecoder jsonDecoder, SerialDescriptor serialDescriptor) {
            Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
            return CompositeDecoder.DefaultImpls.decodeCollectionSize(jsonDecoder, serialDescriptor);
        }

        @ExperimentalSerializationApi
        public static <T> T decodeNullableSerializableValue(JsonDecoder jsonDecoder, DeserializationStrategy<? extends T> deserializationStrategy) {
            Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
            return Decoder.DefaultImpls.decodeNullableSerializableValue(jsonDecoder, deserializationStrategy);
        }

        @ExperimentalSerializationApi
        public static boolean decodeSequentially(JsonDecoder jsonDecoder) {
            return CompositeDecoder.DefaultImpls.decodeSequentially(jsonDecoder);
        }

        public static <T> T decodeSerializableValue(JsonDecoder jsonDecoder, DeserializationStrategy<? extends T> deserializationStrategy) {
            Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
            return Decoder.DefaultImpls.decodeSerializableValue(jsonDecoder, deserializationStrategy);
        }
    }
}
