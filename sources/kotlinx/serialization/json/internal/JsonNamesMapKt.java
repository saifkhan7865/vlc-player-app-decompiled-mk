package kotlinx.serialization.json.internal;

import io.ktor.http.ContentDisposition;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.SerializationException;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonNames;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonSchemaCacheKt;
import kotlinx.serialization.json.internal.DescriptorSchemaCache;

@Metadata(d1 = {"\u0000T\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0011\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u001a \u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002\u001a \u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u0002*\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u000eH\u0000\u001a\u001c\u0010\u0013\u001a\u00020\u0003*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u0004H\u0000\u001a\u001c\u0010\u0015\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u0003H\u0000\u001a&\u0010\u0017\u001a\u00020\u0004*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0016\u001a\u00020\u00032\b\b\u0002\u0010\u0018\u001a\u00020\u0003H\u0000\u001a\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001a*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000\u001a'\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\n*\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u001c\u001a\u00020\u001aH\u0000¢\u0006\u0002\u0010\u001d\u001a[\u0010\u001e\u001a\u00020\u001f*\u00020\u00102\u0006\u0010 \u001a\u00020\u000e2!\u0010!\u001a\u001d\u0012\u0013\u0012\u00110\u001f¢\u0006\f\b#\u0012\b\b\u0016\u0012\u0004\b\b($\u0012\u0004\u0012\u00020\u001f0\"2\u000e\u0010%\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030&2\u000e\b\u0002\u0010'\u001a\b\u0012\u0004\u0012\u00020(0&H\bø\u0001\u0000\".\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00040\u00020\u00018\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u0005\u0010\u0006\u001a\u0004\b\u0007\u0010\b\"(\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\n0\u00018\u0000X\u0004¢\u0006\u000e\n\u0000\u0012\u0004\b\u000b\u0010\u0006\u001a\u0004\b\f\u0010\b\u0002\u0007\n\u0005\b20\u0001¨\u0006)"}, d2 = {"JsonDeserializationNamesKey", "Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "", "", "", "getJsonDeserializationNamesKey$annotations", "()V", "getJsonDeserializationNamesKey", "()Lkotlinx/serialization/json/internal/DescriptorSchemaCache$Key;", "JsonSerializationNamesKey", "", "getJsonSerializationNamesKey$annotations", "getJsonSerializationNamesKey", "buildDeserializationNamesMap", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "json", "Lkotlinx/serialization/json/Json;", "deserializationNamesMap", "descriptor", "getJsonElementName", "index", "getJsonNameIndex", "name", "getJsonNameIndexOrThrow", "suffix", "namingStrategy", "Lkotlinx/serialization/json/JsonNamingStrategy;", "serializationNamesIndices", "strategy", "(Lkotlinx/serialization/descriptors/SerialDescriptor;Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonNamingStrategy;)[Ljava/lang/String;", "tryCoerceValue", "", "elementDescriptor", "peekNull", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "consume", "peekString", "Lkotlin/Function0;", "onEnumCoercing", "", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonNamesMap.kt */
public final class JsonNamesMapKt {
    private static final DescriptorSchemaCache.Key<Map<String, Integer>> JsonDeserializationNamesKey = new DescriptorSchemaCache.Key<>();
    private static final DescriptorSchemaCache.Key<String[]> JsonSerializationNamesKey = new DescriptorSchemaCache.Key<>();

    public static /* synthetic */ void getJsonDeserializationNamesKey$annotations() {
    }

    public static /* synthetic */ void getJsonSerializationNamesKey$annotations() {
    }

    public static final DescriptorSchemaCache.Key<Map<String, Integer>> getJsonDeserializationNamesKey() {
        return JsonDeserializationNamesKey;
    }

    public static final DescriptorSchemaCache.Key<String[]> getJsonSerializationNamesKey() {
        return JsonSerializationNamesKey;
    }

    /* JADX WARNING: type inference failed for: r3v0, types: [java.util.Map, java.util.Map<java.lang.String, java.lang.Integer>] */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static final void buildDeserializationNamesMap$putOrThrow(java.util.Map<java.lang.String, java.lang.Integer> r3, kotlinx.serialization.descriptors.SerialDescriptor r4, java.lang.String r5, int r6) {
        /*
            boolean r0 = r3.containsKey(r5)
            if (r0 != 0) goto L_0x000e
            java.lang.Integer r4 = java.lang.Integer.valueOf(r6)
            r3.put(r5, r4)
            return
        L_0x000e:
            kotlinx.serialization.json.internal.JsonException r0 = new kotlinx.serialization.json.internal.JsonException
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            java.lang.String r2 = "The suggested name '"
            r1.<init>(r2)
            r1.append(r5)
            java.lang.String r2 = "' for property "
            r1.append(r2)
            java.lang.String r6 = r4.getElementName(r6)
            r1.append(r6)
            java.lang.String r6 = " is already one of the names for property "
            r1.append(r6)
            java.lang.Object r3 = kotlin.collections.MapsKt.getValue(r3, r5)
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            java.lang.String r3 = r4.getElementName(r3)
            r1.append(r3)
            java.lang.String r3 = " in "
            r1.append(r3)
            r1.append(r4)
            java.lang.String r3 = r1.toString()
            r0.<init>(r3)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: kotlinx.serialization.json.internal.JsonNamesMapKt.buildDeserializationNamesMap$putOrThrow(java.util.Map, kotlinx.serialization.descriptors.SerialDescriptor, java.lang.String, int):void");
    }

    /* access modifiers changed from: private */
    public static final Map<String, Integer> buildDeserializationNamesMap(SerialDescriptor serialDescriptor, Json json) {
        String[] names;
        Map<String, Integer> linkedHashMap = new LinkedHashMap<>();
        JsonNamingStrategy namingStrategy = namingStrategy(serialDescriptor, json);
        int elementsCount = serialDescriptor.getElementsCount();
        for (int i = 0; i < elementsCount; i++) {
            Collection arrayList = new ArrayList();
            for (Object next : serialDescriptor.getElementAnnotations(i)) {
                if (next instanceof JsonNames) {
                    arrayList.add(next);
                }
            }
            JsonNames jsonNames = (JsonNames) CollectionsKt.singleOrNull((List) arrayList);
            if (!(jsonNames == null || (names = jsonNames.names()) == null)) {
                for (String buildDeserializationNamesMap$putOrThrow : names) {
                    buildDeserializationNamesMap$putOrThrow(linkedHashMap, serialDescriptor, buildDeserializationNamesMap$putOrThrow, i);
                }
            }
            if (namingStrategy != null) {
                buildDeserializationNamesMap$putOrThrow(linkedHashMap, serialDescriptor, namingStrategy.serialNameForJson(serialDescriptor, i, serialDescriptor.getElementName(i)), i);
            }
        }
        return linkedHashMap.isEmpty() ? MapsKt.emptyMap() : linkedHashMap;
    }

    public static final Map<String, Integer> deserializationNamesMap(Json json, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return (Map) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(serialDescriptor, JsonDeserializationNamesKey, new JsonNamesMapKt$deserializationNamesMap$1(serialDescriptor, json));
    }

    public static final String[] serializationNamesIndices(SerialDescriptor serialDescriptor, Json json, JsonNamingStrategy jsonNamingStrategy) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(jsonNamingStrategy, "strategy");
        return (String[]) JsonSchemaCacheKt.getSchemaCache(json).getOrPut(serialDescriptor, JsonSerializationNamesKey, new JsonNamesMapKt$serializationNamesIndices$1(serialDescriptor, jsonNamingStrategy));
    }

    public static final String getJsonElementName(SerialDescriptor serialDescriptor, Json json, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        JsonNamingStrategy namingStrategy = namingStrategy(serialDescriptor, json);
        return namingStrategy == null ? serialDescriptor.getElementName(i) : serializationNamesIndices(serialDescriptor, json, namingStrategy)[i];
    }

    public static final JsonNamingStrategy namingStrategy(SerialDescriptor serialDescriptor, Json json) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        if (Intrinsics.areEqual((Object) serialDescriptor.getKind(), (Object) StructureKind.CLASS.INSTANCE)) {
            return json.getConfiguration().getNamingStrategy();
        }
        return null;
    }

    private static final int getJsonNameIndex$getJsonNameIndexSlowPath(Json json, SerialDescriptor serialDescriptor, String str) {
        Integer num = deserializationNamesMap(json, serialDescriptor).get(str);
        if (num != null) {
            return num.intValue();
        }
        return -3;
    }

    public static final int getJsonNameIndex(SerialDescriptor serialDescriptor, Json json, String str) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        if (namingStrategy(serialDescriptor, json) != null) {
            return getJsonNameIndex$getJsonNameIndexSlowPath(json, serialDescriptor, str);
        }
        int elementIndex = serialDescriptor.getElementIndex(str);
        if (elementIndex == -3 && json.getConfiguration().getUseAlternativeNames()) {
            return getJsonNameIndex$getJsonNameIndexSlowPath(json, serialDescriptor, str);
        }
        return elementIndex;
    }

    public static /* synthetic */ int getJsonNameIndexOrThrow$default(SerialDescriptor serialDescriptor, Json json, String str, String str2, int i, Object obj) {
        if ((i & 4) != 0) {
            str2 = "";
        }
        return getJsonNameIndexOrThrow(serialDescriptor, json, str, str2);
    }

    public static final int getJsonNameIndexOrThrow(SerialDescriptor serialDescriptor, Json json, String str, String str2) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "<this>");
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "suffix");
        int jsonNameIndex = getJsonNameIndex(serialDescriptor, json, str);
        if (jsonNameIndex != -3) {
            return jsonNameIndex;
        }
        throw new SerializationException(serialDescriptor.getSerialName() + " does not contain element with name '" + str + '\'' + str2);
    }

    public static /* synthetic */ boolean tryCoerceValue$default(Json json, SerialDescriptor serialDescriptor, Function1 function1, Function0 function0, Function0 function02, int i, Object obj) {
        String str;
        if ((i & 8) != 0) {
            function02 = JsonNamesMapKt$tryCoerceValue$1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(function1, "peekNull");
        Intrinsics.checkNotNullParameter(function0, "peekString");
        Intrinsics.checkNotNullParameter(function02, "onEnumCoercing");
        if (!serialDescriptor.isNullable() && ((Boolean) function1.invoke(true)).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) serialDescriptor.getKind(), (Object) SerialKind.ENUM.INSTANCE) || ((serialDescriptor.isNullable() && ((Boolean) function1.invoke(false)).booleanValue()) || (str = (String) function0.invoke()) == null || getJsonNameIndex(serialDescriptor, json, str) != -3)) {
            return false;
        }
        function02.invoke();
        return true;
    }

    public static final boolean tryCoerceValue(Json json, SerialDescriptor serialDescriptor, Function1<? super Boolean, Boolean> function1, Function0<String> function0, Function0<Unit> function02) {
        String invoke;
        Intrinsics.checkNotNullParameter(json, "<this>");
        Intrinsics.checkNotNullParameter(serialDescriptor, "elementDescriptor");
        Intrinsics.checkNotNullParameter(function1, "peekNull");
        Intrinsics.checkNotNullParameter(function0, "peekString");
        Intrinsics.checkNotNullParameter(function02, "onEnumCoercing");
        if (!serialDescriptor.isNullable() && function1.invoke(true).booleanValue()) {
            return true;
        }
        if (!Intrinsics.areEqual((Object) serialDescriptor.getKind(), (Object) SerialKind.ENUM.INSTANCE) || ((serialDescriptor.isNullable() && function1.invoke(false).booleanValue()) || (invoke = function0.invoke()) == null || getJsonNameIndex(serialDescriptor, json, invoke) != -3)) {
            return false;
        }
        function02.invoke();
        return true;
    }
}
