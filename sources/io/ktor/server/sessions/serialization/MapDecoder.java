package io.ktor.server.sessions.serialization;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.internal.NamedValueDecoder;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0010\u0010\u0010\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0002J\u0010\u0010\u0012\u001a\u00020\f2\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0010\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0010\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0018\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u0014H\u0014J\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0010\u0010\u001f\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0010\u0010 \u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0010\u0010\"\u001a\u00020\u00162\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0012\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0010\u0010%\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u0005H\u0014J\u0018\u0010&\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010'\u001a\u00020\fH\u0014R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000¨\u0006("}, d2 = {"Lio/ktor/server/sessions/serialization/MapDecoder;", "Lkotlinx/serialization/internal/NamedValueDecoder;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "string", "", "(Lkotlinx/serialization/modules/SerializersModule;Ljava/lang/String;)V", "parameterNames", "", "parameters", "Lio/ktor/http/Parameters;", "position", "", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "size", "currentElement", "tag", "decodeElementIndex", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "decodeTaggedBoolean", "", "decodeTaggedChar", "", "decodeTaggedDouble", "", "decodeTaggedEnum", "enumDescriptor", "decodeTaggedFloat", "", "decodeTaggedInt", "decodeTaggedLong", "", "decodeTaggedNotNullMark", "decodeTaggedNull", "", "decodeTaggedString", "elementName", "index", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: MapDecoder.kt */
public final class MapDecoder extends NamedValueDecoder {
    private final List<String> parameterNames;
    private final Parameters parameters;
    private int position = -1;
    private final SerializersModule serializersModule;
    private final int size;

    /* access modifiers changed from: protected */
    public Void decodeTaggedNull(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return null;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public MapDecoder(SerializersModule serializersModule2, String str) {
        Intrinsics.checkNotNullParameter(serializersModule2, "serializersModule");
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        this.serializersModule = serializersModule2;
        Parameters parseQueryString$default = QueryKt.parseQueryString$default(str, 0, 0, true, 6, (Object) null);
        this.parameters = parseQueryString$default;
        List<String> list = CollectionsKt.toList(parseQueryString$default.names());
        this.parameterNames = list;
        this.size = list.size() * 2;
    }

    /* access modifiers changed from: protected */
    public String elementName(SerialDescriptor serialDescriptor, int i) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        return this.parameterNames.get(i / 2);
    }

    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        int i = this.position;
        if (i >= this.size - 1) {
            return -1;
        }
        int i2 = i + 1;
        this.position = i2;
        return i2;
    }

    private final String currentElement(String str) {
        if (this.position % 2 != 0) {
            str = this.parameters.get(str);
            Intrinsics.checkNotNull(str);
        }
        if (StringsKt.startsWith$default(str, "#bo", false, 2, (Object) null)) {
            return StringsKt.drop(str, 3);
        }
        if (StringsKt.startsWith$default(str, "#ch", false, 2, (Object) null)) {
            return StringsKt.drop(str, 3);
        }
        return StringsKt.drop(str, 2);
    }

    /* access modifiers changed from: protected */
    public boolean decodeTaggedBoolean(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return Intrinsics.areEqual((Object) currentElement(str), (Object) "t");
    }

    /* access modifiers changed from: protected */
    public char decodeTaggedChar(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return currentElement(str).charAt(0);
    }

    /* access modifiers changed from: protected */
    public double decodeTaggedDouble(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return Double.parseDouble(currentElement(str));
    }

    /* access modifiers changed from: protected */
    public float decodeTaggedFloat(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return Float.parseFloat(currentElement(str));
    }

    /* access modifiers changed from: protected */
    public int decodeTaggedInt(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return Integer.parseInt(currentElement(str));
    }

    /* access modifiers changed from: protected */
    public long decodeTaggedLong(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return Long.parseLong(currentElement(str));
    }

    /* access modifiers changed from: protected */
    public String decodeTaggedString(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return currentElement(str);
    }

    /* access modifiers changed from: protected */
    public boolean decodeTaggedNotNullMark(String str) {
        Intrinsics.checkNotNullParameter(str, "tag");
        return !Intrinsics.areEqual((Object) currentElement(str), (Object) "#n");
    }

    /* access modifiers changed from: protected */
    public int decodeTaggedEnum(String str, SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(str, "tag");
        Intrinsics.checkNotNullParameter(serialDescriptor, "enumDescriptor");
        String decodeTaggedString = decodeTaggedString(str);
        int elementIndex = serialDescriptor.getElementIndex(decodeTaggedString);
        if (elementIndex != -3) {
            return elementIndex;
        }
        throw new IllegalStateException(serialDescriptor.getSerialName() + " does not contain element with name '" + decodeTaggedString + '\'');
    }
}
