package kotlinx.serialization.json.internal;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.internal.JsonInternalDependenciesKt;
import kotlinx.serialization.json.Json;
import kotlinx.serialization.json.JsonElement;
import kotlinx.serialization.json.JsonElementKt;
import kotlinx.serialization.json.JsonNamingStrategy;
import kotlinx.serialization.json.JsonNull;
import kotlinx.serialization.json.JsonObject;
import kotlinx.serialization.json.JsonPrimitive;
import kotlinx.serialization.json.JsonSchemaCacheKt;

@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0012\u0018\u00002\u00020\u0001B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\n\b\u0002\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\n\b\u0002\u0010\b\u001a\u0004\u0018\u00010\t¢\u0006\u0002\u0010\nJ\u0018\u0010\u0011\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000eH\u0002J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\tH\u0016J \u0010\u0016\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u0007H\u0002J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0017\u001a\u00020\u0007H\u0014J\u0010\u0010\u001a\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\tH\u0016J\b\u0010\u001b\u001a\u00020\fH\u0016J\u0018\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\t2\u0006\u0010\u0013\u001a\u00020\u000eH\u0014J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0012\u001a\u00020\tH\u0016R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u0004\u0018\u00010\tX\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001f"}, d2 = {"Lkotlinx/serialization/json/internal/JsonTreeDecoder;", "Lkotlinx/serialization/json/internal/AbstractJsonTreeDecoder;", "json", "Lkotlinx/serialization/json/Json;", "value", "Lkotlinx/serialization/json/JsonObject;", "polyDiscriminator", "", "polyDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "(Lkotlinx/serialization/json/Json;Lkotlinx/serialization/json/JsonObject;Ljava/lang/String;Lkotlinx/serialization/descriptors/SerialDescriptor;)V", "forceNull", "", "position", "", "getValue", "()Lkotlinx/serialization/json/JsonObject;", "absenceIsNull", "descriptor", "index", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "coerceInputValue", "tag", "currentElement", "Lkotlinx/serialization/json/JsonElement;", "decodeElementIndex", "decodeNotNullMark", "elementName", "endStructure", "", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: TreeJsonDecoder.kt */
class JsonTreeDecoder extends AbstractJsonTreeDecoder {
    private boolean forceNull;
    private final SerialDescriptor polyDescriptor;
    private final String polyDiscriminator;
    private int position;
    private final JsonObject value;

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(json, jsonObject, (i & 4) != 0 ? null : str, (i & 8) != 0 ? null : serialDescriptor);
    }

    public JsonObject getValue() {
        return this.value;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public JsonTreeDecoder(Json json, JsonObject jsonObject, String str, SerialDescriptor serialDescriptor) {
        super(json, jsonObject, (DefaultConstructorMarker) null);
        Intrinsics.checkNotNullParameter(json, "json");
        Intrinsics.checkNotNullParameter(jsonObject, "value");
        this.value = jsonObject;
        this.polyDiscriminator = str;
        this.polyDescriptor = serialDescriptor;
    }

    private final boolean coerceInputValue(SerialDescriptor serialDescriptor, int i, String str) {
        Json json = getJson();
        SerialDescriptor elementDescriptor = serialDescriptor.getElementDescriptor(i);
        if (!elementDescriptor.isNullable() && (currentElement(str) instanceof JsonNull)) {
            return true;
        }
        if (Intrinsics.areEqual((Object) elementDescriptor.getKind(), (Object) SerialKind.ENUM.INSTANCE) && (!elementDescriptor.isNullable() || !(currentElement(str) instanceof JsonNull))) {
            JsonElement currentElement = currentElement(str);
            String str2 = null;
            JsonPrimitive jsonPrimitive = currentElement instanceof JsonPrimitive ? (JsonPrimitive) currentElement : null;
            if (jsonPrimitive != null) {
                str2 = JsonElementKt.getContentOrNull(jsonPrimitive);
            }
            if (str2 != null && JsonNamesMapKt.getJsonNameIndex(elementDescriptor, json, str2) == -3) {
                return true;
            }
        }
        return false;
    }

    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        while (this.position < serialDescriptor.getElementsCount()) {
            int i = this.position;
            this.position = i + 1;
            String tag = getTag(serialDescriptor, i);
            int i2 = this.position - 1;
            this.forceNull = false;
            if ((getValue().containsKey(tag) || absenceIsNull(serialDescriptor, i2)) && (!this.configuration.getCoerceInputValues() || !coerceInputValue(serialDescriptor, i2, tag))) {
                return i2;
            }
        }
        return -1;
    }

    private final boolean absenceIsNull(SerialDescriptor serialDescriptor, int i) {
        boolean z = !getJson().getConfiguration().getExplicitNulls() && !serialDescriptor.isElementOptional(i) && serialDescriptor.getElementDescriptor(i).isNullable();
        this.forceNull = z;
        return z;
    }

    public boolean decodeNotNullMark() {
        return !this.forceNull && super.decodeNotNullMark();
    }

    /* access modifiers changed from: protected */
    public String elementName(SerialDescriptor serialDescriptor, int i) {
        String str;
        Object obj;
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        JsonNamingStrategy namingStrategy = JsonNamesMapKt.namingStrategy(serialDescriptor, getJson());
        String elementName = serialDescriptor.getElementName(i);
        if (namingStrategy == null && (!this.configuration.getUseAlternativeNames() || getValue().keySet().contains(elementName))) {
            return elementName;
        }
        Map<String, Integer> deserializationNamesMap = JsonNamesMapKt.deserializationNamesMap(getJson(), serialDescriptor);
        Iterator it = getValue().keySet().iterator();
        while (true) {
            str = null;
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            Integer num = deserializationNamesMap.get((String) obj);
            if (num != null && num.intValue() == i) {
                break;
            }
        }
        String str2 = (String) obj;
        if (str2 != null) {
            return str2;
        }
        if (namingStrategy != null) {
            str = namingStrategy.serialNameForJson(serialDescriptor, i, elementName);
        }
        return str == null ? elementName : str;
    }

    /* access modifiers changed from: protected */
    public JsonElement currentElement(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return (JsonElement) MapsKt.getValue(getValue(), str);
    }

    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (serialDescriptor == this.polyDescriptor) {
            return this;
        }
        return super.beginStructure(serialDescriptor);
    }

    public void endStructure(SerialDescriptor serialDescriptor) {
        Set<String> set;
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (!this.configuration.getIgnoreUnknownKeys() && !(serialDescriptor.getKind() instanceof PolymorphicKind)) {
            JsonNamingStrategy namingStrategy = JsonNamesMapKt.namingStrategy(serialDescriptor, getJson());
            if (namingStrategy == null && !this.configuration.getUseAlternativeNames()) {
                set = JsonInternalDependenciesKt.jsonCachedSerialNames(serialDescriptor);
            } else if (namingStrategy != null) {
                set = JsonNamesMapKt.deserializationNamesMap(getJson(), serialDescriptor).keySet();
            } else {
                Set jsonCachedSerialNames = JsonInternalDependenciesKt.jsonCachedSerialNames(serialDescriptor);
                Map map = (Map) JsonSchemaCacheKt.getSchemaCache(getJson()).get(serialDescriptor, JsonNamesMapKt.getJsonDeserializationNamesKey());
                Set keySet = map != null ? map.keySet() : null;
                if (keySet == null) {
                    keySet = SetsKt.emptySet();
                }
                set = SetsKt.plus(jsonCachedSerialNames, keySet);
            }
            for (String next : getValue().keySet()) {
                if (!set.contains(next) && !Intrinsics.areEqual((Object) next, (Object) this.polyDiscriminator)) {
                    throw JsonExceptionsKt.UnknownKeyException(next, getValue().toString());
                }
            }
        }
    }
}
