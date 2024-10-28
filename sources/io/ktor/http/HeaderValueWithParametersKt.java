package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.util.StringValuesBuilder;
import io.netty.util.internal.StringUtil;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlin.text.Typography;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\u001a\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t\u001a\n\u0010\n\u001a\u00020\u0007*\u00020\u0007\u001a\u0019\u0010\u000b\u001a\u00020\u0004*\u00020\u00072\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\b\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0007H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0010*\u00020\u0007H\u0002\u001a\n\u0010\u0012\u001a\u00020\u0007*\u00020\u0007\u001a\u0018\u0010\u0013\u001a\u00020\u0004*\u00020\u00072\n\u0010\f\u001a\u00060\rj\u0002`\u000eH\u0002\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"HeaderFieldValueSeparators", "", "", "append", "", "Lio/ktor/util/StringValuesBuilder;", "name", "", "value", "Lio/ktor/http/HeaderValueWithParameters;", "escapeIfNeeded", "escapeIfNeededTo", "out", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "isQuoted", "", "needQuotes", "quote", "quoteTo", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HeaderValueWithParameters.kt */
public final class HeaderValueWithParametersKt {
    private static final Set<Character> HeaderFieldValueSeparators;

    static {
        Character valueOf = Character.valueOf(Typography.less);
        Character valueOf2 = Character.valueOf(Typography.greater);
        Character valueOf3 = Character.valueOf(AbstractJsonLexerKt.COLON);
        Character valueOf4 = Character.valueOf(AbstractJsonLexerKt.STRING_ESC);
        Character valueOf5 = Character.valueOf(AbstractJsonLexerKt.BEGIN_LIST);
        Character valueOf6 = Character.valueOf(AbstractJsonLexerKt.END_LIST);
        Character valueOf7 = Character.valueOf(AbstractJsonLexerKt.BEGIN_OBJ);
        Character ch = valueOf7;
        HeaderFieldValueSeparators = SetsKt.setOf('(', ')', valueOf, valueOf2, '@', ',', ';', valueOf3, valueOf4, '\"', '/', valueOf5, valueOf6, '?', '=', ch, Character.valueOf(AbstractJsonLexerKt.END_OBJ), ' ', 9, 10, Character.valueOf(StringUtil.CARRIAGE_RETURN));
    }

    public static final void append(StringValuesBuilder stringValuesBuilder, String str, HeaderValueWithParameters headerValueWithParameters) {
        Intrinsics.checkNotNullParameter(stringValuesBuilder, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(headerValueWithParameters, "value");
        stringValuesBuilder.append(str, headerValueWithParameters.toString());
    }

    public static final String escapeIfNeeded(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return needQuotes(str) ? quote(str) : str;
    }

    private static final void escapeIfNeededTo(String str, StringBuilder sb) {
        if (needQuotes(str)) {
            sb.append(quote(str));
        } else {
            sb.append(str);
        }
    }

    /* access modifiers changed from: private */
    public static final boolean needQuotes(String str) {
        if (str.length() == 0) {
            return true;
        }
        if (isQuoted(str)) {
            return false;
        }
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (HeaderFieldValueSeparators.contains(Character.valueOf(str.charAt(i)))) {
                return true;
            }
        }
        return false;
    }

    private static final boolean isQuoted(String str) {
        if (str.length() < 2) {
            return false;
        }
        CharSequence charSequence = str;
        if (StringsKt.first(charSequence) != '\"' || StringsKt.last(charSequence) != '\"') {
            return false;
        }
        int i = 1;
        do {
            int indexOf$default = StringsKt.indexOf$default(charSequence, '\"', i, false, 4, (Object) null);
            if (indexOf$default == StringsKt.getLastIndex(charSequence)) {
                break;
            }
            int i2 = 0;
            for (int i3 = indexOf$default - 1; str.charAt(i3) == '\\'; i3--) {
                i2++;
            }
            if (i2 % 2 == 0) {
                return false;
            }
            i = indexOf$default + 1;
        } while (i < str.length());
        return true;
    }

    public static final String quote(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        StringBuilder sb = new StringBuilder();
        quoteTo(str, sb);
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private static final void quoteTo(String str, StringBuilder sb) {
        sb.append("\"");
        int length = str.length();
        for (int i = 0; i < length; i++) {
            char charAt = str.charAt(i);
            if (charAt == '\\') {
                sb.append("\\\\");
            } else if (charAt == 10) {
                sb.append("\\n");
            } else if (charAt == 13) {
                sb.append("\\r");
            } else if (charAt == 9) {
                sb.append("\\t");
            } else if (charAt == '\"') {
                sb.append("\\\"");
            } else {
                sb.append(charAt);
            }
        }
        sb.append("\"");
    }
}
