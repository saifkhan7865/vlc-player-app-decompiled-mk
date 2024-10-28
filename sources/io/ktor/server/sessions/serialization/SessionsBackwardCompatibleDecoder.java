package io.ktor.server.sessions.serialization;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.CodecsKt;
import io.ktor.http.HttpUrlEncodedKt;
import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import java.nio.charset.Charset;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.descriptors.SerialKind;
import kotlinx.serialization.descriptors.StructureKind;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.encoding.CompositeDecoder;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010(\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\b\u0010\u0012\u001a\u00020\u0013H\u0016J\b\u0010\u0014\u001a\u00020\u0015H\u0016J\b\u0010\u0016\u001a\u00020\u0017H\u0016J\u0010\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0011H\u0016J\u0010\u0010\u001a\u001a\u00020\u00192\u0006\u0010\u001b\u001a\u00020\u0011H\u0016J\b\u0010\u001c\u001a\u00020\u001dH\u0016J\b\u0010\u001e\u001a\u00020\u0019H\u0016J\b\u0010\u001f\u001a\u00020 H\u0016J\b\u0010!\u001a\u00020\u0013H\u0016J\n\u0010\"\u001a\u0004\u0018\u00010#H\u0016J\b\u0010$\u001a\u00020\u0005H\u0016R\u000e\u0010\u0007\u001a\u00020\u0005X.¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lio/ktor/server/sessions/serialization/SessionsBackwardCompatibleDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "string", "", "(Lkotlinx/serialization/modules/SerializersModule;Ljava/lang/String;)V", "currentName", "parameterNames", "", "parameters", "Lio/ktor/http/Parameters;", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "beginStructure", "Lkotlinx/serialization/encoding/CompositeDecoder;", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "decodeBoolean", "", "decodeChar", "", "decodeDouble", "", "decodeElementIndex", "", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeInt", "decodeLong", "", "decodeNotNullMark", "decodeNull", "", "decodeString", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionsBackwardCompatibleDecoder.kt */
public final class SessionsBackwardCompatibleDecoder extends AbstractDecoder {
    private String currentName;
    private final Iterator<String> parameterNames;
    private final Parameters parameters;
    private final SerializersModule serializersModule;
    private final String string;

    public Void decodeNull() {
        return null;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public SessionsBackwardCompatibleDecoder(SerializersModule serializersModule2, String str) {
        Intrinsics.checkNotNullParameter(serializersModule2, "serializersModule");
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        this.serializersModule = serializersModule2;
        this.string = str;
        Parameters parseQueryString$default = QueryKt.parseQueryString$default(str, 0, 0, true, 6, (Object) null);
        this.parameters = parseQueryString$default;
        this.parameterNames = parseQueryString$default.names().iterator();
    }

    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (!this.parameterNames.hasNext()) {
            return -1;
        }
        String next = this.parameterNames.next();
        this.currentName = next;
        if (next == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            next = null;
        }
        return serialDescriptor.getElementIndex(next);
    }

    public CompositeDecoder beginStructure(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        if (this.currentName == null) {
            return new SessionsBackwardCompatibleDecoder(getSerializersModule(), this.string);
        }
        SerialKind kind = serialDescriptor.getKind();
        String str = null;
        if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.LIST.INSTANCE)) {
            Parameters parameters2 = this.parameters;
            String str2 = this.currentName;
            if (str2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentName");
            } else {
                str = str2;
            }
            String str3 = parameters2.get(str);
            Intrinsics.checkNotNull(str3);
            return new ListLikeDecoder(getSerializersModule(), CodecsKt.decodeURLQueryComponent$default(StringsKt.drop(str3, 3), 0, 0, false, (Charset) null, 15, (Object) null));
        } else if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.MAP.INSTANCE)) {
            Parameters parameters3 = this.parameters;
            String str4 = this.currentName;
            if (str4 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentName");
            } else {
                str = str4;
            }
            String str5 = parameters3.get(str);
            Intrinsics.checkNotNull(str5);
            return new MapDecoder(getSerializersModule(), HttpUrlEncodedKt.formUrlEncode(QueryKt.parseQueryString$default(CodecsKt.decodeURLQueryComponent$default(StringsKt.drop(str5, 2), 0, 0, false, (Charset) null, 15, (Object) null), 0, 0, true, 6, (Object) null)));
        } else if (Intrinsics.areEqual((Object) kind, (Object) StructureKind.CLASS.INSTANCE)) {
            Parameters parameters4 = this.parameters;
            String str6 = this.currentName;
            if (str6 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("currentName");
            } else {
                str = str6;
            }
            String str7 = parameters4.get(str);
            Intrinsics.checkNotNull(str7);
            return new SessionsBackwardCompatibleDecoder(getSerializersModule(), CodecsKt.decodeURLQueryComponent$default(StringsKt.drop(str7, 2), 0, 0, false, (Charset) null, 15, (Object) null));
        } else {
            throw new IllegalArgumentException("Unsupported kind: " + serialDescriptor.getKind());
        }
    }

    public boolean decodeBoolean() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return Intrinsics.areEqual((Object) str2, (Object) "#bot");
    }

    public char decodeChar() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return str2.charAt(3);
    }

    public double decodeDouble() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return Double.parseDouble(StringsKt.drop(str2, 2));
    }

    public float decodeFloat() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return Float.parseFloat(StringsKt.drop(str2, 2));
    }

    public int decodeInt() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return Integer.parseInt(StringsKt.drop(str2, 2));
    }

    public long decodeLong() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return Long.parseLong(StringsKt.drop(str2, 2));
    }

    public String decodeString() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return StringsKt.drop(str2, 2);
    }

    public boolean decodeNotNullMark() {
        Parameters parameters2 = this.parameters;
        String str = this.currentName;
        if (str == null) {
            Intrinsics.throwUninitializedPropertyAccessException("currentName");
            str = null;
        }
        String str2 = parameters2.get(str);
        Intrinsics.checkNotNull(str2);
        return !Intrinsics.areEqual((Object) str2, (Object) "#n");
    }

    public int decodeEnum(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        String decodeString = decodeString();
        int elementIndex = serialDescriptor.getElementIndex(decodeString);
        if (elementIndex != -3) {
            return elementIndex;
        }
        throw new IllegalStateException(serialDescriptor.getSerialName() + " does not contain element with name '" + decodeString + '\'');
    }
}
