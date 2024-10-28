package kotlinx.serialization.json.internal;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import androidx.tvprovider.media.tv.TvContractCompat;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.serialization.descriptors.SerialDescriptor;

@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0004\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0004\n\u0002\u0010\u0001\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a \u0010\u0007\u001a\u00020\b2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0000\u001a\u0010\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0000\u001a\u0018\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0005H\u0000\u001a \u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0011H\u0000\u001a\u0018\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0010\u001a\u00020\u0005H\u0000\u001a \u0010\u0013\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0002\u001a\u0016\u0010\u0014\u001a\u00020\u0011*\u00020\u00112\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0002\u001a\u0014\u0010\u0015\u001a\u00020\u0016*\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0003H\u0000¨\u0006\u0019"}, d2 = {"InvalidFloatingPointDecoded", "Lkotlinx/serialization/json/internal/JsonDecodingException;", "value", "", "key", "", "output", "InvalidFloatingPointEncoded", "Lkotlinx/serialization/json/internal/JsonEncodingException;", "InvalidKeyKindException", "keyDescriptor", "Lkotlinx/serialization/descriptors/SerialDescriptor;", "JsonDecodingException", "offset", "", "message", "input", "", "UnknownKeyException", "unexpectedFpErrorMessage", "minify", "throwInvalidFloatingPointDecoded", "", "Lkotlinx/serialization/json/internal/AbstractJsonLexer;", "result", "kotlinx-serialization-json"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: JsonExceptions.kt */
public final class JsonExceptionsKt {
    public static final JsonDecodingException JsonDecodingException(int i, String str) {
        Intrinsics.checkNotNullParameter(str, "message");
        if (i >= 0) {
            str = "Unexpected JSON token at offset " + i + ": " + str;
        }
        return new JsonDecodingException(str);
    }

    public static final JsonDecodingException JsonDecodingException(int i, String str, CharSequence charSequence) {
        Intrinsics.checkNotNullParameter(str, "message");
        Intrinsics.checkNotNullParameter(charSequence, TvContractCompat.PARAM_INPUT);
        return JsonDecodingException(i, str + "\nJSON input: " + minify(charSequence, i));
    }

    public static final JsonEncodingException InvalidFloatingPointEncoded(Number number, String str) {
        Intrinsics.checkNotNullParameter(number, "value");
        Intrinsics.checkNotNullParameter(str, "output");
        return new JsonEncodingException("Unexpected special floating-point value " + number + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + minify$default(str, 0, 1, (Object) null));
    }

    public static final Void throwInvalidFloatingPointDecoded(AbstractJsonLexer abstractJsonLexer, Number number) {
        Intrinsics.checkNotNullParameter(abstractJsonLexer, "<this>");
        Intrinsics.checkNotNullParameter(number, "result");
        AbstractJsonLexer.fail$default(abstractJsonLexer, "Unexpected special floating-point value " + number + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification", 0, AbstractJsonLexerKt.specialFlowingValuesHint, 2, (Object) null);
        throw new KotlinNothingValueException();
    }

    public static final JsonEncodingException InvalidKeyKindException(SerialDescriptor serialDescriptor) {
        Intrinsics.checkNotNullParameter(serialDescriptor, "keyDescriptor");
        return new JsonEncodingException("Value of type '" + serialDescriptor.getSerialName() + "' can't be used in JSON as a key in the map. It should have either primitive or enum kind, but its kind is '" + serialDescriptor.getKind() + "'.\nUse 'allowStructuredMapKeys = true' in 'Json {}' builder to convert such maps to [key1, value1, key2, value2,...] arrays.");
    }

    public static final JsonEncodingException InvalidFloatingPointEncoded(Number number, String str, String str2) {
        Intrinsics.checkNotNullParameter(number, "value");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, "output");
        return new JsonEncodingException(unexpectedFpErrorMessage(number, str, str2));
    }

    public static final JsonDecodingException InvalidFloatingPointDecoded(Number number, String str, String str2) {
        Intrinsics.checkNotNullParameter(number, "value");
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, "output");
        return JsonDecodingException(-1, unexpectedFpErrorMessage(number, str, str2));
    }

    private static final String unexpectedFpErrorMessage(Number number, String str, String str2) {
        return "Unexpected special floating-point value " + number + " with key " + str + ". By default, non-finite floating point values are prohibited because they do not conform JSON specification. It is possible to deserialize them using 'JsonBuilder.allowSpecialFloatingPointValues = true'\nCurrent output: " + minify$default(str2, 0, 1, (Object) null);
    }

    public static final JsonDecodingException UnknownKeyException(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(str2, TvContractCompat.PARAM_INPUT);
        return JsonDecodingException(-1, "Encountered an unknown key '" + str + "'.\nUse 'ignoreUnknownKeys = true' in 'Json {}' builder to ignore unknown keys.\nCurrent input: " + minify$default(str2, 0, 1, (Object) null));
    }

    static /* synthetic */ CharSequence minify$default(CharSequence charSequence, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = -1;
        }
        return minify(charSequence, i);
    }

    private static final CharSequence minify(CharSequence charSequence, int i) {
        if (charSequence.length() < 200) {
            return charSequence;
        }
        String str = ".....";
        if (i == -1) {
            int length = charSequence.length() - 60;
            if (length <= 0) {
                return charSequence;
            }
            return str + charSequence.subSequence(length, charSequence.length()).toString();
        }
        int i2 = i - 30;
        int i3 = i + 30;
        String str2 = i2 <= 0 ? "" : str;
        if (i3 >= charSequence.length()) {
            str = "";
        }
        return str2 + charSequence.subSequence(RangesKt.coerceAtLeast(i2, 0), RangesKt.coerceAtMost(i3, charSequence.length())).toString() + str;
    }
}
