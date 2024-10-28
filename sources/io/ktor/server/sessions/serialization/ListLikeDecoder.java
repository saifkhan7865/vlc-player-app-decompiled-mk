package io.ktor.server.sessions.serialization;

import androidx.constraintlayout.core.motion.utils.TypedValues;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;
import kotlinx.serialization.encoding.AbstractDecoder;
import kotlinx.serialization.modules.SerializersModule;

@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u0002\b\u0000\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\r\u001a\u00020\u000eH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\b\u0010\u0011\u001a\u00020\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\b2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u0010\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\b\u0010\u0018\u001a\u00020\u0019H\u0016J\b\u0010\u001a\u001a\u00020\bH\u0016J\b\u0010\u001b\u001a\u00020\u001cH\u0016J\b\u0010\u001d\u001a\u00020\u000eH\u0016J\n\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0016J\b\u0010 \u001a\u00020\u0005H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\nX\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006!"}, d2 = {"Lio/ktor/server/sessions/serialization/ListLikeDecoder;", "Lkotlinx/serialization/encoding/AbstractDecoder;", "serializersModule", "Lkotlinx/serialization/modules/SerializersModule;", "string", "", "(Lkotlinx/serialization/modules/SerializersModule;Ljava/lang/String;)V", "currentIndex", "", "items", "", "getSerializersModule", "()Lkotlinx/serialization/modules/SerializersModule;", "decodeBoolean", "", "decodeChar", "", "decodeDouble", "", "decodeElementIndex", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "decodeEnum", "enumDescriptor", "decodeFloat", "", "decodeInt", "decodeLong", "", "decodeNotNullMark", "decodeNull", "", "decodeString", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ListLikeDecoder.kt */
public final class ListLikeDecoder extends AbstractDecoder {
    private int currentIndex = -1;
    private final List<String> items;
    private final SerializersModule serializersModule;

    public Void decodeNull() {
        return null;
    }

    public SerializersModule getSerializersModule() {
        return this.serializersModule;
    }

    public ListLikeDecoder(SerializersModule serializersModule2, String str) {
        Intrinsics.checkNotNullParameter(serializersModule2, "serializersModule");
        Intrinsics.checkNotNullParameter(str, TypedValues.Custom.S_STRING);
        this.serializersModule = serializersModule2;
        this.items = StringsKt.split$default((CharSequence) str, new String[]{"&"}, false, 0, 6, (Object) null);
    }

    public int decodeElementIndex(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        int i = this.currentIndex + 1;
        this.currentIndex = i;
        if (i == this.items.size()) {
            return -1;
        }
        return this.currentIndex;
    }

    public boolean decodeBoolean() {
        return Intrinsics.areEqual((Object) this.items.get(this.currentIndex), (Object) "#bot");
    }

    public char decodeChar() {
        return this.items.get(this.currentIndex).charAt(3);
    }

    public double decodeDouble() {
        return Double.parseDouble(StringsKt.drop(this.items.get(this.currentIndex), 2));
    }

    public float decodeFloat() {
        return Float.parseFloat(StringsKt.drop(this.items.get(this.currentIndex), 2));
    }

    public int decodeInt() {
        return Integer.parseInt(StringsKt.drop(this.items.get(this.currentIndex), 2));
    }

    public long decodeLong() {
        return Long.parseLong(StringsKt.drop(this.items.get(this.currentIndex), 2));
    }

    public String decodeString() {
        return StringsKt.drop(this.items.get(this.currentIndex), 2);
    }

    public boolean decodeNotNullMark() {
        return !Intrinsics.areEqual((Object) this.items.get(this.currentIndex), (Object) "#n");
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
