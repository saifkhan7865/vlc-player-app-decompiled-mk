package kotlinx.serialization.json;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0003H\u0016J\b\u0010\t\u001a\u00020\u0003H\u0016¨\u0006\n"}, d2 = {"kotlinx/serialization/json/JsonNamingStrategy$Builtins$SnakeCase$1", "Lkotlinx/serialization/json/JsonNamingStrategy;", "serialNameForJson", "", "descriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "elementIndex", "", "serialName", "toString", "kotlinx-serialization-json"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonNamingStrategy.kt */
public final class JsonNamingStrategy$Builtins$SnakeCase$1 implements JsonNamingStrategy {
    JsonNamingStrategy$Builtins$SnakeCase$1() {
    }

    public String serialNameForJson(SerialDescriptor serialDescriptor, int i, String str) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "descriptor");
        Intrinsics.checkNotNullParameter(str, "serialName");
        StringBuilder sb = new StringBuilder(str.length() * 2);
        CharSequence charSequence = str;
        Character ch = null;
        int i2 = 0;
        for (int i3 = 0; i3 < charSequence.length(); i3++) {
            char charAt = charSequence.charAt(i3);
            if (Character.isUpperCase(charAt)) {
                if (i2 == 0) {
                    CharSequence charSequence2 = sb;
                    if (charSequence2.length() > 0 && StringsKt.last(charSequence2) != '_') {
                        sb.append('_');
                    }
                }
                if (ch != null) {
                    sb.append(ch.charValue());
                }
                i2++;
                ch = Character.valueOf(Character.toLowerCase(charAt));
            } else {
                if (ch != null) {
                    if (i2 > 1 && Character.isLetter(charAt)) {
                        sb.append('_');
                    }
                    sb.append(ch);
                    ch = null;
                    i2 = 0;
                }
                sb.append(charAt);
            }
        }
        if (ch != null) {
            sb.append(ch);
        }
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder(capacity).…builderAction).toString()");
        return sb2;
    }

    public String toString() {
        return "kotlinx.serialization.json.JsonNamingStrategy.SnakeCase";
    }
}
