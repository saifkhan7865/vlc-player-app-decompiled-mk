package kotlinx.serialization.json.internal;

import java.lang.annotation.Annotation;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.PolymorphicSerializerKt;
import kotlinx.serialization.SealedClassSerializer;
import kotlinx.serialization.SerializationStrategy;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.PrimitiveKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.Encoder;
import kotlinx.serialization.internal.AbstractPolymorphicSerializer;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonClassDiscriminator;
import kotlinx.serialization.json.JsonEncoder;
import kotlinx.serialization.json.JsonObject;

@Metadata(d1 = {"\u0000Z\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0000\u001a\u001a\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0001\u001a*\u0010\n\u001a\u00020\u00012\n\u0010\u000b\u001a\u0006\u0012\u0002\b\u00030\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u000e0\f2\u0006\u0010\u000f\u001a\u00020\u0007H\u0002\u001a\u0014\u0010\u000f\u001a\u00020\u0007*\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0000\u001a%\u0010\u0013\u001a\u0002H\u0014\"\u0004\b\u0000\u0010\u0014*\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u0002H\u00140\u0017H\u0000¢\u0006\u0002\u0010\u0018\u001aE\u0010\u0019\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0014*\u00020\u001a2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u0002H\u00140\f2\u0006\u0010\u001b\u001a\u0002H\u00142\u0012\u0010\u001c\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00010\u001dH\bø\u0001\u0000¢\u0006\u0002\u0010\u001e\u0002\u0007\n\u0005\b20\u0001¨\u0006\u001f"}, d2 = {"checkKind", "", "kind", "Lkotlinx/serialization/descriptors/SerialKind;", "throwSerializerNotFound", "", "type", "", "jsonTree", "Lkotlinx/serialization/json/JsonObject;", "validateIfSealed", "serializer", "Lkotlinx/serialization/SerializationStrategy;", "actualSerializer", "", "classDiscriminator", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "json", "Lkotlinx/serialization/json/Json;", "decodeSerializableValuePolymorphic", "T", "Lkotlinx/serialization/json/JsonDecoder;", "deserializer", "Lkotlinx/serialization/DeserializationStrategy;", "(Lkotlinx/serialization/json/JsonDecoder;Lkotlinx/serialization/DeserializationStrategy;)Ljava/lang/Object;", "encodePolymorphically", "Lkotlinx/serialization/json/JsonEncoder;", "value", "ifPolymorphic", "Lkotlin/Function1;", "(Lkotlinx/serialization/json/JsonEncoder;Lkotlinx/serialization/SerializationStrategy;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Polymorphic.kt */
public final class PolymorphicKt {
    public static final <T> void encodePolymorphically(JsonEncoder jsonEncoder, SerializationStrategy<? super T> serializationStrategy, T t, Function1<? super String, Unit> function1) {
        Intrinsics.checkNotNullParameter(jsonEncoder, "<this>");
        Intrinsics.checkNotNullParameter(serializationStrategy, "serializer");
        Intrinsics.checkNotNullParameter(function1, "ifPolymorphic");
        if (!(serializationStrategy instanceof AbstractPolymorphicSerializer) || jsonEncoder.getJson().getConfiguration().getUseArrayPolymorphism()) {
            serializationStrategy.serialize(jsonEncoder, t);
            return;
        }
        AbstractPolymorphicSerializer abstractPolymorphicSerializer = (AbstractPolymorphicSerializer) serializationStrategy;
        String classDiscriminator = classDiscriminator(serializationStrategy.getDescriptor(), jsonEncoder.getJson());
        Encoder encoder = jsonEncoder;
        Intrinsics.checkNotNull(t, "null cannot be cast to non-null type kotlin.Any");
        SerializationStrategy findPolymorphicSerializer = PolymorphicSerializerKt.findPolymorphicSerializer(abstractPolymorphicSerializer, encoder, t);
        validateIfSealed(abstractPolymorphicSerializer, findPolymorphicSerializer, classDiscriminator);
        checkKind(findPolymorphicSerializer.getDescriptor().getKind());
        function1.invoke(classDiscriminator);
        findPolymorphicSerializer.serialize(encoder, t);
    }

    /* access modifiers changed from: private */
    public static final void validateIfSealed(SerializationStrategy<?> serializationStrategy, SerializationStrategy<Object> serializationStrategy2, String str) {
        if ((serializationStrategy instanceof SealedClassSerializer) && JsonInternalDependenciesKt.jsonCachedSerialNames(serializationStrategy2.getDescriptor()).contains(str)) {
            String serialName = serializationStrategy.getDescriptor().getSerialName();
            String serialName2 = serializationStrategy2.getDescriptor().getSerialName();
            throw new IllegalStateException(("Sealed class '" + serialName2 + "' cannot be serialized as base class '" + serialName + "' because it has property name that conflicts with JSON class discriminator '" + str + "'. You can either change class discriminator in JsonConfiguration, rename property with @SerialName annotation or fall back to array polymorphism").toString());
        }
    }

    public static final void checkKind(SerialKind serialKind) {
        Intrinsics.checkNotNullParameter(serialKind, "kind");
        if (serialKind instanceof SerialKind.ENUM) {
            throw new IllegalStateException("Enums cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        } else if (serialKind instanceof PrimitiveKind) {
            throw new IllegalStateException("Primitives cannot be serialized polymorphically with 'type' parameter. You can use 'JsonBuilder.useArrayPolymorphism' instead".toString());
        } else if (serialKind instanceof PolymorphicKind) {
            throw new IllegalStateException("Actual serializer for polymorphic cannot be polymorphic itself".toString());
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0040, code lost:
        r2 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r2);
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final <T> T decodeSerializableValuePolymorphic(kotlinx.serialization.json.JsonDecoder r4, kotlinx.serialization.DeserializationStrategy<? extends T> r5) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r4, r0)
            java.lang.String r0 = "deserializer"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r5, r0)
            boolean r0 = r5 instanceof kotlinx.serialization.internal.AbstractPolymorphicSerializer
            if (r0 == 0) goto L_0x009f
            kotlinx.serialization.json.Json r0 = r4.getJson()
            kotlinx.serialization.json.JsonConfiguration r0 = r0.getConfiguration()
            boolean r0 = r0.getUseArrayPolymorphism()
            if (r0 == 0) goto L_0x001e
            goto L_0x009f
        L_0x001e:
            kotlinx.serialization.descriptors.SerialDescriptor r0 = r5.getDescriptor()
            kotlinx.serialization.json.Json r1 = r4.getJson()
            java.lang.String r0 = classDiscriminator(r0, r1)
            kotlinx.serialization.json.JsonElement r1 = r4.decodeJsonElement()
            kotlinx.serialization.descriptors.SerialDescriptor r2 = r5.getDescriptor()
            boolean r3 = r1 instanceof kotlinx.serialization.json.JsonObject
            if (r3 == 0) goto L_0x0069
            kotlinx.serialization.json.JsonObject r1 = (kotlinx.serialization.json.JsonObject) r1
            java.lang.Object r2 = r1.get((java.lang.Object) r0)
            kotlinx.serialization.json.JsonElement r2 = (kotlinx.serialization.json.JsonElement) r2
            if (r2 == 0) goto L_0x004b
            kotlinx.serialization.json.JsonPrimitive r2 = kotlinx.serialization.json.JsonElementKt.getJsonPrimitive(r2)
            if (r2 == 0) goto L_0x004b
            java.lang.String r2 = r2.getContent()
            goto L_0x004c
        L_0x004b:
            r2 = 0
        L_0x004c:
            kotlinx.serialization.internal.AbstractPolymorphicSerializer r5 = (kotlinx.serialization.internal.AbstractPolymorphicSerializer) r5
            r3 = r4
            kotlinx.serialization.encoding.CompositeDecoder r3 = (kotlinx.serialization.encoding.CompositeDecoder) r3
            kotlinx.serialization.DeserializationStrategy r5 = r5.findPolymorphicSerializerOrNull((kotlinx.serialization.encoding.CompositeDecoder) r3, (java.lang.String) r2)
            if (r5 == 0) goto L_0x0060
            kotlinx.serialization.json.Json r4 = r4.getJson()
            java.lang.Object r4 = kotlinx.serialization.json.internal.TreeJsonDecoderKt.readPolymorphicJson(r4, r0, r1, r5)
            return r4
        L_0x0060:
            throwSerializerNotFound(r2, r1)
            kotlin.KotlinNothingValueException r4 = new kotlin.KotlinNothingValueException
            r4.<init>()
            throw r4
        L_0x0069:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder
            java.lang.String r5 = "Expected "
            r4.<init>(r5)
            java.lang.Class<kotlinx.serialization.json.JsonObject> r5 = kotlinx.serialization.json.JsonObject.class
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            r4.append(r5)
            java.lang.String r5 = " as the serialized body of "
            r4.append(r5)
            java.lang.String r5 = r2.getSerialName()
            r4.append(r5)
            java.lang.String r5 = ", but had "
            r4.append(r5)
            java.lang.Class r5 = r1.getClass()
            kotlin.reflect.KClass r5 = kotlin.jvm.internal.Reflection.getOrCreateKotlinClass(r5)
            r4.append(r5)
            java.lang.String r4 = r4.toString()
            r5 = -1
            kotlinx.serialization.json.internal.JsonDecodingException r4 = kotlinx.serialization.json.internal.JsonExceptionsKt.JsonDecodingException(r5, r4)
            throw r4
        L_0x009f:
            kotlinx.serialization.encoding.Decoder r4 = (kotlinx.serialization.encoding.Decoder) r4
            java.lang.Object r4 = r5.deserialize(r4)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.PolymorphicKt.decodeSerializableValuePolymorphic(kotlinx.serialization.json.JsonDecoder, kotlinx.serialization.DeserializationStrategy):java.lang.Object");
    }

    public static final Void throwSerializerNotFound(String str, JsonObject jsonObject) {
        String str2;
        Intrinsics.checkNotNullParameter(jsonObject, "jsonTree");
        if (str == null) {
            str2 = "missing class discriminator ('null')";
        } else {
            str2 = "class discriminator '" + str + '\'';
        }
        throw JsonExceptionsKt.JsonDecodingException(-1, "Polymorphic serializer was not found for " + str2, jsonObject.toString());
    }

    public static final String classDiscriminator(SerialDescriptor serialDescriptor, Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        for (Annotation next : serialDescriptor.getAnnotations()) {
            if (next instanceof JsonClassDiscriminator) {
                return ((JsonClassDiscriminator) next).discriminator();
            }
        }
        return json.getConfiguration().getClassDiscriminator();
    }
}
