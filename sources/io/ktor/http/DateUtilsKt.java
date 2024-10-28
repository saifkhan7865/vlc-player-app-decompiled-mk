package io.ktor.http;

import io.ktor.util.date.GMTDate;
import io.ktor.util.date.GMTDateParser;
import io.ktor.util.date.InvalidDateStringException;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.fusesource.jansi.AnsiRenderer;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\u001a\n\u0010\u0003\u001a\u00020\u0004*\u00020\u0002\u001a\n\u0010\u0005\u001a\u00020\u0004*\u00020\u0002\u001a\u0014\u0010\u0006\u001a\u00020\u0002*\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007H\u0002\u001a\n\u0010\t\u001a\u00020\u0002*\u00020\u0004\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"HTTP_DATE_FORMATS", "", "", "fromCookieToGmtDate", "Lio/ktor/util/date/GMTDate;", "fromHttpToGmtDate", "padZero", "", "length", "toHttpDate", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: DateUtils.kt */
public final class DateUtilsKt {
    private static final List<String> HTTP_DATE_FORMATS = CollectionsKt.listOf("***, dd MMM YYYY hh:mm:ss zzz", "****, dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d hh:mm:ss YYYY", "***, dd-MMM-YYYY hh:mm:ss zzz", "***, dd-MMM-YYYY hh-mm-ss zzz", "***, dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh:mm:ss zzz", "*** dd MMM YYYY hh:mm:ss zzz", "*** dd-MMM-YYYY hh-mm-ss zzz", "***,dd-MMM-YYYY hh:mm:ss zzz", "*** MMM d YYYY hh:mm:ss zzz");

    public static final GMTDate fromHttpToGmtDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String obj = StringsKt.trim((CharSequence) str).toString();
        for (String gMTDateParser : HTTP_DATE_FORMATS) {
            try {
                return new GMTDateParser(gMTDateParser).parse(str);
            } catch (InvalidDateStringException unused) {
            }
        }
        throw new IllegalStateException(("Failed to parse date: " + obj).toString());
    }

    public static final GMTDate fromCookieToGmtDate(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String obj = StringsKt.trim((CharSequence) str).toString();
        try {
            return new CookieDateParser().parse(obj);
        } catch (InvalidCookieDateException unused) {
            return fromHttpToGmtDate(obj);
        }
    }

    public static final String toHttpDate(GMTDate gMTDate) {
        Intrinsics.checkNotNullParameter(gMTDate, "<this>");
        StringBuilder sb = new StringBuilder();
        sb.append(gMTDate.getDayOfWeek().getValue() + ", ");
        sb.append(padZero(gMTDate.getDayOfMonth(), 2) + ' ');
        sb.append(gMTDate.getMonth().getValue() + ' ');
        sb.append(padZero(gMTDate.getYear(), 4));
        sb.append(AnsiRenderer.CODE_TEXT_SEPARATOR + padZero(gMTDate.getHours(), 2) + AbstractJsonLexerKt.COLON + padZero(gMTDate.getMinutes(), 2) + AbstractJsonLexerKt.COLON + padZero(gMTDate.getSeconds(), 2) + ' ');
        sb.append("GMT");
        String sb2 = sb.toString();
        Intrinsics.checkNotNullExpressionValue(sb2, "StringBuilder().apply(builderAction).toString()");
        return sb2;
    }

    private static final String padZero(int i, int i2) {
        return StringsKt.padStart(String.valueOf(i), i2, '0');
    }
}
