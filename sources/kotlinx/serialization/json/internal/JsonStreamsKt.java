package kotlinx.serialization.json.internal;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.ExperimentalSerializationApi;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.json.DecodeSequenceMode;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.internal.StreamingJsonDecoder;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u00008\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a-\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0001¢\u0006\u0002\u0010\u0007\u001a8\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00042\b\b\u0002\u0010\n\u001a\u00020\u000bH\u0001\u001a-\u0010\b\u001a\b\u0012\u0004\u0012\u0002H\u00010\t\"\u0006\b\u0000\u0010\u0001\u0018\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\n\u001a\u00020\u000bH\b\u001a5\u0010\f\u001a\u00020\r\"\u0004\b\u0000\u0010\u0001*\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u0002H\u00010\u00112\u0006\u0010\u0012\u001a\u0002H\u0001H\u0001¢\u0006\u0002\u0010\u0013¨\u0006\u0014"}, d2 = {"decodeByReader", "T", "Lkotlinx/serialization/json/Json;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "reader", "Lkotlinx/serialization/json/internal/SerialReader;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/DeserializationStrategy;Lkotlinx/serialization/json/internal/SerialReader;)Ljava/lang/Object;", "decodeToSequenceByReader", "Lkotlin/sequences/Sequence;", "format", "Lkotlinx/serialization/json/DecodeSequenceMode;", "encodeByWriter", "", "writer", "Lkotlinx/serialization/json/internal/JsonWriter;", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/internal/JsonWriter;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)V", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonStreams.kt */
public final class JsonStreamsKt {
    public static final <T> void encodeByWriter(Json json, JsonWriter jsonWriter, SerializationStrategy<? super T> serializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(jsonWriter, "writer");
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        new StreamingJsonEncoder(jsonWriter, json, WriteMode.OBJ, new JsonEncoder[WriteMode.values().length]).encodeSerializableValue(serializationStrategy, t);
    }

    public static final <T> T decodeByReader(Json json, DeserializationStrategy<? extends T> deserializationStrategy, SerialReader serialReader) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        Intrinsics.checkNotNullParameter(serialReader, "reader");
        ReaderJsonLexer readerJsonLexer = new ReaderJsonLexer(serialReader, (char[]) null, 2, (DefaultConstructorMarker) null);
        try {
            T decodeSerializableValue = new StreamingJsonDecoder(json, WriteMode.OBJ, readerJsonLexer, deserializationStrategy.getDescriptor(), (StreamingJsonDecoder.DiscriminatorHolder) null).decodeSerializableValue(deserializationStrategy);
            readerJsonLexer.expectEof();
            return decodeSerializableValue;
        } finally {
            readerJsonLexer.release();
        }
    }

    public static /* synthetic */ Sequence decodeToSequenceByReader$default(Json json, SerialReader serialReader, DeserializationStrategy deserializationStrategy, DecodeSequenceMode decodeSequenceMode, int i, Object obj) {
        if ((i & 4) != 0) {
            decodeSequenceMode = DecodeSequenceMode.AUTO_DETECT;
        }
        return decodeToSequenceByReader(json, serialReader, deserializationStrategy, decodeSequenceMode);
    }

    @ExperimentalSerializationApi
    public static final <T> Sequence<T> decodeToSequenceByReader(Json json, SerialReader serialReader, DeserializationStrategy<? extends T> deserializationStrategy, DecodeSequenceMode decodeSequenceMode) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialReader, "reader");
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        Intrinsics.checkNotNullParameter(decodeSequenceMode, "format");
        return SequencesKt.constrainOnce(new JsonStreamsKt$decodeToSequenceByReader$$inlined$Sequence$1(JsonIteratorKt.JsonIterator(decodeSequenceMode, json, new ReaderJsonLexer(serialReader, new char[16384]), deserializationStrategy)));
    }

    public static /* synthetic */ Sequence decodeToSequenceByReader$default(Json json, SerialReader serialReader, DecodeSequenceMode decodeSequenceMode, int i, Object obj) {
        if ((i & 2) != 0) {
            decodeSequenceMode = DecodeSequenceMode.AUTO_DETECT;
        }
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialReader, "reader");
        Intrinsics.checkNotNullParameter(decodeSequenceMode, "format");
        SerializersModule serializersModule = json.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall((Object) "kotlinx.serialization.serializer.withModule");
        return decodeToSequenceByReader(json, serialReader, SerializersKt.serializer(serializersModule, (KType) null), decodeSequenceMode);
    }

    @ExperimentalSerializationApi
    public static final /* synthetic */ <T> Sequence<T> decodeToSequenceByReader(Json json, SerialReader serialReader, DecodeSequenceMode decodeSequenceMode) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialReader, "reader");
        Intrinsics.checkNotNullParameter(decodeSequenceMode, "format");
        SerializersModule serializersModule = json.getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall((Object) "kotlinx.serialization.serializer.withModule");
        return decodeToSequenceByReader(json, serialReader, SerializersKt.serializer(serializersModule, (KType) null), decodeSequenceMode);
    }
}
