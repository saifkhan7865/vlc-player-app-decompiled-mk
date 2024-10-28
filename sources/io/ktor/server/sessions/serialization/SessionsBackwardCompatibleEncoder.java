package io.ktor.server.sessions.serialization;

import io.ktor.http.CodecsKt;
import io.ktor.http.HttpUrlEncodedKt;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.PolymorphicKind;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractEncoder;
import kotlinx.serialization.encoding.CompositeEncoder;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u0000\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\u0018\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0016J\b\u0010\u001c\u001a\u00020\u001aH\u0016J\u0010\u0010\u001d\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0012\u0010!\u001a\u0004\u0018\u00010\b2\u0006\u0010\u001e\u001a\u00020\u001fH\u0002J\u0006\u0010\"\u001a\u00020\bR\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0000X\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010\u0007X\u000e¢\u0006\u0002\n\u0000R\u001c\u0010\t\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0018\u00010\nX\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\bX\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\bX.¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006#"}, d2 = {"Lio/ktor/server/sessions/serialization/SessionsBackwardCompatibleEncoder;", "Lkotlinx/serialization/encoding/AbstractEncoder;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "(Lkotlinx/serialization/modules/SerializersModule;)V", "currentClassEncoder", "currentList", "", "", "currentMap", "", "mapKey", "nextElementName", "parametersBuilder", "Lio/ktor/http/ParametersBuilder;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeEncoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "encodeElement", "", "index", "", "encodeEnum", "", "enumDescriptor", "encodeNull", "encodeValue", "value", "", "endStructure", "primitiveValue", "result", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsBackwardCompatibleEncoder.kt */
public final class SessionsBackwardCompatibleEncoder extends AbstractEncoder {
    private SessionsBackwardCompatibleEncoder currentClassEncoder;
    private List<String> currentList;
    private Map<String, String> currentMap;
    private String mapKey;
    private String nextElementName;
    private final ParametersBuilder parametersBuilder = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
    private final SerializersModule serializersModule;

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public SessionsBackwardCompatibleEncoder(SerializersModule serializersModule2) {
        Intrinsics.checkNotNullParameter(serializersModule2, "serializersModule");
        this.serializersModule = serializersModule2;
    }

    public final String result() {
        SessionsBackwardCompatibleEncoder sessionsBackwardCompatibleEncoder = this.currentClassEncoder;
        if (sessionsBackwardCompatibleEncoder == null) {
            return HttpUrlEncodedKt.formUrlEncode(this.parametersBuilder.build());
        }
        Intrinsics.checkNotNull(sessionsBackwardCompatibleEncoder);
        return sessionsBackwardCompatibleEncoder.result();
    }

    public CompositeEncoder beginStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        SerialKind kind = serialDescriptor.getKind();
        if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.LIST.INSTANCE)) {
            this.currentList = new ArrayList();
        } else if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.MAP.INSTANCE)) {
            this.currentMap = new LinkedHashMap();
        } else if (!Intrinsics.areEqual((Object) kind, (Object) StructureKind.CLASS.INSTANCE) && !Intrinsics.areEqual((Object) kind, (Object) PolymorphicKind.SEALED.INSTANCE)) {
            throw new IllegalArgumentException("Unsupported kind: " + serialDescriptor.getKind());
        } else {
            SessionsBackwardCompatibleEncoder sessionsBackwardCompatibleEncoder = new SessionsBackwardCompatibleEncoder(getSerializersModule());
            this.currentClassEncoder = sessionsBackwardCompatibleEncoder;
            Intrinsics.checkNotNull(sessionsBackwardCompatibleEncoder);
            return sessionsBackwardCompatibleEncoder;
        }
        return super.beginStructure(serialDescriptor);
    }

    public void endStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        SessionsBackwardCompatibleEncoder sessionsBackwardCompatibleEncoder = this.currentClassEncoder;
        if (sessionsBackwardCompatibleEncoder != null) {
            Intrinsics.checkNotNull(sessionsBackwardCompatibleEncoder);
            String result = sessionsBackwardCompatibleEncoder.result();
            ParametersBuilder parametersBuilder2 = this.parametersBuilder;
            String str = this.nextElementName;
            if (str == null) {
                Intrinsics.throwUninitializedPropertyAccessException("nextElementName");
                str = null;
            }
            parametersBuilder2.append(str, "##" + result);
            this.currentClassEncoder = null;
        } else {
            List<String> list = this.currentList;
            if (list != null) {
                Intrinsics.checkNotNull(list);
                String encodeURLQueryComponent$default = CodecsKt.encodeURLQueryComponent$default(CollectionsKt.joinToString$default(list, "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, SessionsBackwardCompatibleEncoder$endStructure$encoded$1.INSTANCE, 30, (Object) null), false, false, (Charset) null, 7, (Object) null);
                ParametersBuilder parametersBuilder3 = this.parametersBuilder;
                String str2 = this.nextElementName;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nextElementName");
                    str2 = null;
                }
                parametersBuilder3.append(str2, "#cl" + encodeURLQueryComponent$default);
                this.currentList = null;
            } else {
                Map<String, String> map = this.currentMap;
                if (map != null) {
                    Intrinsics.checkNotNull(map);
                    Collection arrayList = new ArrayList(map.size());
                    for (Map.Entry next : map.entrySet()) {
                        arrayList.add(CodecsKt.encodeURLQueryComponent$default((String) next.getKey(), false, false, (Charset) null, 7, (Object) null) + '=' + CodecsKt.encodeURLQueryComponent$default((String) next.getValue(), false, false, (Charset) null, 7, (Object) null));
                    }
                    String encodeURLQueryComponent$default2 = CodecsKt.encodeURLQueryComponent$default(CollectionsKt.joinToString$default((List) arrayList, "&", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null), false, false, (Charset) null, 7, (Object) null);
                    ParametersBuilder parametersBuilder4 = this.parametersBuilder;
                    String str3 = this.nextElementName;
                    if (str3 == null) {
                        Intrinsics.throwUninitializedPropertyAccessException("nextElementName");
                        str3 = null;
                    }
                    parametersBuilder4.append(str3, "#m" + encodeURLQueryComponent$default2);
                    this.currentMap = null;
                }
            }
        }
        super.endStructure(serialDescriptor);
    }

    public void encodeValue(Object obj) {
        Intrinsics.checkNotNullParameter(obj, "value");
        String primitiveValue = primitiveValue(obj);
        if (primitiveValue != null) {
            List<String> list = this.currentList;
            if (list != null) {
                Intrinsics.checkNotNull(list);
                list.add(primitiveValue);
                return;
            }
            Map<String, String> map = this.currentMap;
            String str = null;
            if (map == null) {
                ParametersBuilder parametersBuilder2 = this.parametersBuilder;
                String str2 = this.nextElementName;
                if (str2 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("nextElementName");
                } else {
                    str = str2;
                }
                parametersBuilder2.append(str, primitiveValue);
            } else if (this.mapKey != null) {
                Intrinsics.checkNotNull(map);
                String str3 = this.mapKey;
                Intrinsics.checkNotNull(str3);
                map.put(str3, primitiveValue);
                this.mapKey = null;
            } else {
                this.mapKey = primitiveValue;
            }
        }
    }

    public boolean encodeElement(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (Intrinsics.areEqual((Object) serialDescriptor.getKind(), (Object) StructureKind.LIST.INSTANCE) || Intrinsics.areEqual((Object) serialDescriptor.getKind(), (Object) StructureKind.MAP.INSTANCE)) {
            return true;
        }
        this.nextElementName = serialDescriptor.getElementName(i);
        return true;
    }

    public void encodeEnum(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        encodeValue(serialDescriptor.getElementName(i));
    }

    public void encodeNull() {
        ParametersBuilder parametersBuilder2 = this.parametersBuilder;
        String str = this.nextElementName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("nextElementName");
            str = null;
        }
        parametersBuilder2.append(str, "#n");
    }

    private final String primitiveValue(Object obj) {
        if (obj instanceof Integer) {
            return "#i" + obj;
        } else if (obj instanceof Long) {
            return "#l" + obj;
        } else if (obj instanceof Float) {
            return "#f" + obj;
        } else if (obj instanceof Double) {
            return "#f" + obj;
        } else if (obj instanceof Boolean) {
            return "#bo" + StringsKt.first(obj.toString());
        } else if (obj instanceof Character) {
            return "#ch" + obj;
        } else if (obj instanceof String) {
            return "#s" + obj;
        } else if (!(obj instanceof Enum)) {
            return null;
        } else {
            return "#s" + ((Enum) obj).name();
        }
    }
}
