package kotlinx.serialization.json;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.MagicApiIntrinsics;
import kotlin.reflect.KType;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.SerializersKt;
import kotlinx.serialization.StringFormat;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;
import kotlinx.serialization.json.internal.JsonStreamsKt;
import kotlinx.serialization.json.internal.JsonToStringWriter;
import kotlinx.serialization.json.internal.StreamingJsonDecoder;
import kotlinx.serialization.json.internal.StringJsonLexer;
import kotlinx.serialization.json.internal.TreeJsonDecoderKt;
import kotlinx.serialization.json.internal.TreeJsonEncoderKt;
import kotlinx.serialization.json.internal.WriteMode;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 %2\u00020\u0001:\u0001%B\u0017\b\u0004\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J'\u0010\u0011\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00142\u0006\u0010\u0015\u001a\u00020\u0016¢\u0006\u0002\u0010\u0017J \u0010\u0018\u001a\u0002H\u0012\"\u0006\b\u0000\u0010\u0012\u0018\u00012\b\b\u0001\u0010\u0019\u001a\u00020\u001aH\b¢\u0006\u0002\u0010\u001bJ)\u0010\u0018\u001a\u0002H\u0012\"\u0004\b\u0000\u0010\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u0002H\u00120\u00142\b\b\u0001\u0010\u0019\u001a\u00020\u001a¢\u0006\u0002\u0010\u001cJ'\u0010\u001d\u001a\u00020\u0016\"\u0004\b\u0000\u0010\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00120\u001f2\u0006\u0010 \u001a\u0002H\u0012¢\u0006\u0002\u0010!J'\u0010\"\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u00122\f\u0010\u001e\u001a\b\u0012\u0004\u0012\u0002H\u00120\u001f2\u0006\u0010 \u001a\u0002H\u0012¢\u0006\u0002\u0010#J\u0010\u0010$\u001a\u00020\u00162\b\b\u0001\u0010\u0019\u001a\u00020\u001aR\u001c\u0010\u0007\u001a\u00020\b8\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\t\u0010\n\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0001\u0002&'¨\u0006("}, d2 = {"Lkotlinx/serialization/json/Json;", "Lkotlinx/serialization/StringFormat;", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "(Lkotlinx/serialization/json/JsonConfiguration;Lkotlinx/serialization/modules/SerializersModule;)V", "_schemaCache", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache;", "get_schemaCache$kotlinx_serialization_json$annotations", "()V", "get_schemaCache$kotlinx_serialization_json", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache;", "getConfiguration", "()Lkotlinx/serialization/json/JsonConfiguration;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "decodeFromJsonElement", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "element", "Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/DeserializationStrategy;Lkotlinx/serialization/json/JsonElement;)Ljava/lang/Object;", "decodeFromString", "string", "", "(Ljava/lang/String;)Ljava/lang/Object;", "(Lkotlinx/serialization/DeserializationStrategy;Ljava/lang/String;)Ljava/lang/Object;", "encodeToJsonElement", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "value", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Lkotlinx/serialization/json/JsonElement;", "encodeToString", "(Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;)Ljava/lang/String;", "parseToJsonElement", "Default", "Lkotlinx/serialization/json/Json$Default;", "Lkotlinx/serialization/json/JsonImpl;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Json.kt */
public abstract class Json implements StringFormat {
    public static final Default Default = new Default((DefaultConstructorMarker) null);
    private final DescriptorSchemaCache _schemaCache;
    private final JsonConfiguration configuration;
    private final SerializersModule serializersModule;

    public /* synthetic */ Json(JsonConfiguration jsonConfiguration, SerializersModule serializersModule2, DefaultConstructorMarker defaultConstructorMarker) {
        this(jsonConfiguration, serializersModule2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Should not be accessed directly, use Json.schemaCache accessor instead", replaceWith = @ReplaceWith(expression = "schemaCache", imports = {}))
    public static /* synthetic */ void get_schemaCache$kotlinx_serialization_json$annotations() {
    }

    private Json(JsonConfiguration jsonConfiguration, SerializersModule serializersModule2) {
        this.configuration = jsonConfiguration;
        this.serializersModule = serializersModule2;
        this._schemaCache = new DescriptorSchemaCache();
    }

    public final JsonConfiguration getConfiguration() {
        return this.configuration;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public final DescriptorSchemaCache get_schemaCache$kotlinx_serialization_json() {
        return this._schemaCache;
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lkotlinx/serialization/json/Json$Default;", "Lkotlinx/serialization/json/Json;", "()V", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Json.kt */
    public static final class Default extends Json {
        public /* synthetic */ Default(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARNING: Illegal instructions before constructor call */
        /* Code decompiled incorrectly, please refer to instructions dump. */
        private Default() {
            /*
                r18 = this;
                kotlinx.serialization.json.JsonConfiguration r15 = new kotlinx.serialization.json.JsonConfiguration
                r14 = 8191(0x1fff, float:1.1478E-41)
                r16 = 0
                r1 = 0
                r2 = 0
                r3 = 0
                r4 = 0
                r5 = 0
                r6 = 0
                r7 = 0
                r8 = 0
                r9 = 0
                r10 = 0
                r11 = 0
                r12 = 0
                r13 = 0
                r0 = r15
                r17 = r15
                r15 = r16
                r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
                kotlinx.serialization.modules.SerializersModule r0 = kotlinx.serialization.modules.SerializersModuleBuildersKt.EmptySerializersModule()
                r1 = 0
                r2 = r18
                r3 = r17
                r2.<init>(r3, r0, r1)
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.Json.Default.<init>():void");
        }
    }

    public final <T> String encodeToString(SerializationStrategy<? super T> serializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        JsonToStringWriter jsonToStringWriter = new JsonToStringWriter();
        try {
            JsonStreamsKt.encodeByWriter(this, jsonToStringWriter, serializationStrategy, t);
            return jsonToStringWriter.toString();
        } finally {
            jsonToStringWriter.release();
        }
    }

    public final /* synthetic */ <T> T decodeFromString(String str) {
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        SerializersModule serializersModule2 = getSerializersModule();
        Intrinsics.reifiedOperationMarker(6, "T");
        MagicApiIntrinsics.voidMagicApiCall((Object) "kotlinx.serialization.serializer.withModule");
        return decodeFromString(SerializersKt.serializer(serializersModule2, (KType) null), str);
    }

    public final <T> T decodeFromString(DeserializationStrategy<? extends T> deserializationStrategy, String str) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        StringJsonLexer stringJsonLexer = new StringJsonLexer(str);
        T decodeSerializableValue = new StreamingJsonDecoder(this, WriteMode.OBJ, stringJsonLexer, deserializationStrategy.getDescriptor(), (StreamingJsonDecoder.DiscriminatorHolder) null).decodeSerializableValue(deserializationStrategy);
        stringJsonLexer.expectEof();
        return decodeSerializableValue;
    }

    public final <T> JsonElement encodeToJsonElement(SerializationStrategy<? super T> serializationStrategy, T t) {
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        return TreeJsonEncoderKt.writeJson(this, t, serializationStrategy);
    }

    public final <T> T decodeFromJsonElement(DeserializationStrategy<? extends T> deserializationStrategy, JsonElement jsonElement) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        Intrinsics.checkNotNullParameter(jsonElement, "element");
        return TreeJsonDecoderKt.readJson(this, jsonElement, deserializationStrategy);
    }

    public final JsonElement parseToJsonElement(String str) {
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        return (JsonElement) decodeFromString(JsonElementSerializer.INSTANCE, str);
    }
}
