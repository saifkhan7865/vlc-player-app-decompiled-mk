package kotlinx.serialization.json.internal;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.text.StringsKt;
import kotlinx.serialization.DeserializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.encoding.Decoder;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonArray;
import kotlinx.serialization.json.JsonConfiguration;
import kotlinx.serialization.json.JsonDecoder;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonLiteral;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000Â\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\n\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b2\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0018\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0019\u001a\u00020\u0017H\u0014J\u0010\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0017H$J\b\u0010\u001c\u001a\u00020\u0006H\u0002J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u001f\u001a\u00020\u0006H\u0016J\b\u0010 \u001a\u00020!H\u0016J!\u0010\"\u001a\u0002H#\"\u0004\b\u0000\u0010#2\f\u0010$\u001a\b\u0012\u0004\u0012\u0002H#0%H\u0016¢\u0006\u0002\u0010&J\u0010\u0010'\u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u0010(\u001a\u00020)2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u0010*\u001a\u00020+2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u0010,\u001a\u00020-2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0018\u0010.\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u00100\u001a\u00020\u0015H\u0014J\u0010\u00101\u001a\u0002022\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0018\u00103\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u00104\u001a\u00020\u0015H\u0014J\u0010\u00105\u001a\u00020/2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u00106\u001a\u0002072\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u00108\u001a\u00020!2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0012\u00109\u001a\u0004\u0018\u00010:2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u0010;\u001a\u00020<2\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u0010=\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0017H\u0014J\u0010\u0010>\u001a\u00020?2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010@\u001a\u00020A2\u0006\u0010\u001b\u001a\u00020\u0017H\u0004J\u0010\u0010B\u001a\u00020:2\u0006\u0010C\u001a\u00020\u0017H\u0002J\u0014\u0010D\u001a\u00020E*\u00020A2\u0006\u0010F\u001a\u00020\u0017H\u0002J?\u0010C\u001a\u0002H#\"\b\b\u0000\u0010#*\u00020G*\u00020A2\u0006\u0010C\u001a\u00020\u00172\u0019\u0010H\u001a\u0015\u0012\u0004\u0012\u00020A\u0012\u0006\u0012\u0004\u0018\u0001H#0I¢\u0006\u0002\bJH\b¢\u0006\u0002\u0010KR\u0010\u0010\b\u001a\u00020\t8\u0004X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011\u0001\u0003LMN¨\u0006O"}, d2 = {"Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "Lkotlinx/serialization/internal/NamedValueDecoder;", "Lkotlinx/serialization/json/JsonDecoder;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonElement;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonElement;)V", "configuration", "Lkotlinx/serialization/json/JsonConfiguration;", "getJson", "()Lkotlinx/serialization/json/Json;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "getValue", "()Lkotlinx/serialization/json/JsonElement;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "composeName", "", "parentName", "childName", "currentElement", "tag", "currentObject", "decodeInline", "Lkotlinx/serialization/encoding/Decoder;", "decodeJsonElement", "decodeNotNullMark", "", "decodeSerializableValue", "T", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "decodeTaggedBoolean", "decodeTaggedByte", "", "decodeTaggedChar", "", "decodeTaggedDouble", "", "decodeTaggedEnum", "", "enumDescriptor", "decodeTaggedFloat", "", "decodeTaggedInline", "inlineDescriptor", "decodeTaggedInt", "decodeTaggedLong", "", "decodeTaggedNotNullMark", "decodeTaggedNull", "", "decodeTaggedShort", "", "decodeTaggedString", "endStructure", "", "getPrimitiveValue", "Lkotlinx/serialization/json/JsonPrimitive;", "unparsedPrimitive", "primitive", "asLiteral", "Lkotlinx/serialization/json/JsonLiteral;", "type", "", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Lkotlinx/serialization/json/JsonPrimitive;Ljava/lang/String;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "Lkotlinx/serialization/json/internal/JsonPrimitiveDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/JsonTreeListDecoder;", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TreeJsonDecoder.kt */
abstract class AbstractJsonTreeDecoder extends NamedValueDecoder implements JsonDecoder {
    protected final JsonConfiguration configuration;
    private final Json json;
    private final JsonElement value;

    public /* synthetic */ AbstractJsonTreeDecoder(Json json2, JsonElement jsonElement, DefaultConstructorMarker defaultConstructorMarker) {
        this(json2, jsonElement);
    }

    /* access modifiers changed from: protected */
    public String composeName(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, "parentName");
        Intrinsics.checkNotNullParameter(str2, "childName");
        return str2;
    }

    /* access modifiers changed from: protected */
    public abstract JsonElement currentElement(String str);

    /* access modifiers changed from: protected */
    public Void decodeTaggedNull(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return null;
    }

    public void endStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
    }

    public Json getJson() {
        return this.json;
    }

    public JsonElement getValue() {
        return this.value;
    }

    private AbstractJsonTreeDecoder(Json json2, JsonElement jsonElement) {
        this.json = json2;
        this.value = jsonElement;
        this.configuration = getJson().getConfiguration();
    }

    public SerializersModule getSerializersModule() {
        return getJson().getSerializersModule();
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0008, code lost:
        r0 = currentElement(r0);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final kotlinx.serialization.json.JsonElement currentObject() {
        /*
            r1 = this;
            java.lang.Object r0 = r1.getCurrentTagOrNull()
            java.lang.String r0 = (java.lang.String) r0
            if (r0 == 0) goto L_0x000e
            kotlinx.serialization.json.JsonElement r0 = r1.currentElement(r0)
            if (r0 != 0) goto L_0x0012
        L_0x000e:
            kotlinx.serialization.json.JsonElement r0 = r1.getValue()
        L_0x0012:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.AbstractJsonTreeDecoder.currentObject():kotlinx.serialization.json.JsonElement");
    }

    public JsonElement decodeJsonElement() {
        return currentObject();
    }

    public <T> T decodeSerializableValue(DeserializationStrategy<? extends T> deserializationStrategy) {
        Intrinsics.checkNotNullParameter(deserializationStrategy, "deserializer");
        return PolymorphicKt.decodeSerializableValuePolymorphic(this, deserializationStrategy);
    }

    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        CompositeDecoder compositeDecoder;
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        JsonElement currentObject = currentObject();
        SerialKind kind = serialDescriptor.getKind();
        if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.LIST.INSTANCE) || (kind instanceof PolymorphicKind)) {
            Json json2 = getJson();
            if (currentObject instanceof JsonArray) {
                return new JsonTreeListDecoder(json2, (JsonArray) currentObject);
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class) + " as the serialized body of " + serialDescriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()));
        } else if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.MAP.INSTANCE)) {
            Json json3 = getJson();
            SerialDescriptor carrierDescriptor = WriteModeKt.carrierDescriptor(serialDescriptor.getElementDescriptor(0), json3.getSerializersModule());
            SerialKind kind2 = carrierDescriptor.getKind();
            if ((kind2 instanceof PrimitiveKind) || Intrinsics.areEqual((Object) kind2, (Object) SerialKind.ENUM.INSTANCE)) {
                Json json4 = getJson();
                if (currentObject instanceof JsonObject) {
                    compositeDecoder = new JsonTreeMapDecoder(json4, (JsonObject) currentObject);
                } else {
                    throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class) + " as the serialized body of " + serialDescriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()));
                }
            } else if (json3.getConfiguration().getAllowStructuredMapKeys()) {
                Json json5 = getJson();
                if (currentObject instanceof JsonArray) {
                    compositeDecoder = new JsonTreeListDecoder(json5, (JsonArray) currentObject);
                } else {
                    throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonArray.class) + " as the serialized body of " + serialDescriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()));
                }
            } else {
                throw JsonExceptionsKt.InvalidKeyKindException(carrierDescriptor);
            }
            return compositeDecoder;
        } else {
            Json json6 = getJson();
            if (currentObject instanceof JsonObject) {
                return new JsonTreeDecoder(json6, (JsonObject) currentObject, (String) null, (SerialDescriptor) null, 12, (DefaultConstructorMarker) null);
            }
            throw JsonExceptionsKt.JsonDecodingException(-1, "Expected " + Reflection.getOrCreateKotlinClass(JsonObject.class) + " as the serialized body of " + serialDescriptor.getSerialName() + ", but had " + Reflection.getOrCreateKotlinClass(currentObject.getClass()));
        }
    }

    public boolean decodeNotNullMark() {
        return !(currentObject() instanceof JsonNull);
    }

    /* access modifiers changed from: protected */
    public final JsonPrimitive getPrimitiveValue(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        JsonElement currentElement = currentElement(str);
        JsonPrimitive jsonPrimitive = currentElement instanceof JsonPrimitive ? (JsonPrimitive) currentElement : null;
        if (jsonPrimitive != null) {
            return jsonPrimitive;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Expected JsonPrimitive at " + str + ", found " + currentElement, currentObject().toString());
    }

    /* access modifiers changed from: protected */
    public int decodeTaggedEnum(String str, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        return JsonNamesMapKt.getJsonNameIndexOrThrow$default(serialDescriptor, getJson(), getPrimitiveValue(str).getContent(), (String) null, 4, (Object) null);
    }

    /* access modifiers changed from: protected */
    public boolean decodeTaggedNotNullMark(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return currentElement(str) != JsonNull.INSTANCE;
    }

    /* access modifiers changed from: protected */
    public boolean decodeTaggedBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        JsonPrimitive primitiveValue = getPrimitiveValue(str);
        if (getJson().getConfiguration().isLenient() || !asLiteral(primitiveValue, TypedValues.Custom.S_BOOLEAN).isString()) {
            try {
                Boolean booleanOrNull = JsonElementKt.getBooleanOrNull(primitiveValue);
                if (booleanOrNull != null) {
                    return booleanOrNull.booleanValue();
                }
                throw new IllegalArgumentException();
            } catch (IllegalArgumentException unused) {
                unparsedPrimitive(TypedValues.Custom.S_BOOLEAN);
                throw new KotlinNothingValueException();
            }
        } else {
            throw JsonExceptionsKt.JsonDecodingException(-1, "Boolean literal for key '" + str + "' should be unquoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON.", currentObject().toString());
        }
    }

    /* access modifiers changed from: protected */
    public byte decodeTaggedByte(String str) {
        Byte b;
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            int i = JsonElementKt.getInt(getPrimitiveValue(str));
            if (-128 > i || i > 127) {
                b = null;
            } else {
                b = Byte.valueOf((byte) i);
            }
            if (b != null) {
                return b.byteValue();
            }
            unparsedPrimitive("byte");
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("byte");
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public short decodeTaggedShort(String str) {
        Short sh;
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            int i = JsonElementKt.getInt(getPrimitiveValue(str));
            if (-32768 > i || i > 32767) {
                sh = null;
            } else {
                sh = Short.valueOf((short) i);
            }
            if (sh != null) {
                return sh.shortValue();
            }
            unparsedPrimitive("short");
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("short");
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public int decodeTaggedInt(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            return JsonElementKt.getInt(getPrimitiveValue(str));
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("int");
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public long decodeTaggedLong(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            return JsonElementKt.getLong(getPrimitiveValue(str));
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("long");
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public float decodeTaggedFloat(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            float f = JsonElementKt.getFloat(getPrimitiveValue(str));
            if (getJson().getConfiguration().getAllowSpecialFloatingPointValues() || (!Float.isInfinite(f) && !Float.isNaN(f))) {
                return f;
            }
            throw JsonExceptionsKt.InvalidFloatingPointDecoded(Float.valueOf(f), str, currentObject().toString());
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(TypedValues.Custom.S_FLOAT);
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public double decodeTaggedDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            double d = JsonElementKt.getDouble(getPrimitiveValue(str));
            if (getJson().getConfiguration().getAllowSpecialFloatingPointValues() || (!Double.isInfinite(d) && !Double.isNaN(d))) {
                return d;
            }
            throw JsonExceptionsKt.InvalidFloatingPointDecoded(Double.valueOf(d), str, currentObject().toString());
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("double");
            throw new KotlinNothingValueException();
        }
    }

    /* access modifiers changed from: protected */
    public char decodeTaggedChar(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        try {
            return StringsKt.single(getPrimitiveValue(str).getContent());
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive("char");
            throw new KotlinNothingValueException();
        }
    }

    private final <T> T primitive(JsonPrimitive jsonPrimitive, String str, Function1<? super JsonPrimitive, ? extends T> function1) {
        try {
            T invoke = function1.invoke(jsonPrimitive);
            if (invoke != null) {
                return invoke;
            }
            unparsedPrimitive(str);
            throw new KotlinNothingValueException();
        } catch (IllegalArgumentException unused) {
            unparsedPrimitive(str);
            throw new KotlinNothingValueException();
        }
    }

    private final Void unparsedPrimitive(String str) {
        throw JsonExceptionsKt.JsonDecodingException(-1, "Failed to parse '" + str + '\'', currentObject().toString());
    }

    /* access modifiers changed from: protected */
    public String decodeTaggedString(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        JsonPrimitive primitiveValue = getPrimitiveValue(str);
        if (!getJson().getConfiguration().isLenient() && !asLiteral(primitiveValue, TypedValues.Custom.S_STRING).isString()) {
            throw JsonExceptionsKt.JsonDecodingException(-1, "String literal for key '" + str + "' should be quoted.\nUse 'isLenient = true' in 'Json {}` builder to accept non-compliant JSON.", currentObject().toString());
        } else if (!(primitiveValue instanceof JsonNull)) {
            return primitiveValue.getContent();
        } else {
            throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected 'null' value instead of string literal", currentObject().toString());
        }
    }

    private final JsonLiteral asLiteral(JsonPrimitive jsonPrimitive, String str) {
        JsonLiteral jsonLiteral = jsonPrimitive instanceof JsonLiteral ? (JsonLiteral) jsonPrimitive : null;
        if (jsonLiteral != null) {
            return jsonLiteral;
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Unexpected 'null' when " + str + " was expected");
    }

    /* access modifiers changed from: protected */
    public Decoder decodeTaggedInline(String str, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(serialDescriptor, "inlineDescriptor");
        if (StreamingJsonEncoderKt.isUnsignedNumber(serialDescriptor)) {
            return new JsonDecoderForUnsignedTypes(new StringJsonLexer(getPrimitiveValue(str).getContent()), getJson());
        }
        return super.decodeTaggedInline(str, serialDescriptor);
    }

    public Decoder decodeInline(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (getCurrentTagOrNull() != null) {
            return super.decodeInline(serialDescriptor);
        }
        return new JsonPrimitiveDecoder(getJson(), getValue()).decodeInline(serialDescriptor);
    }
}
