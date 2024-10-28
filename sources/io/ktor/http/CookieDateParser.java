package io.ktor.http;

import io.ktor.util.date.GMTDate;
import io.netty.handler.codec.rtsp.RtspHeaders;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.videolan.vlc.gui.helpers.OnRepeatListener;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J-\u0010\u0003\u001a\u00020\u0004\"\u0004\b\u0000\u0010\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00072\b\u0010\t\u001a\u0004\u0018\u0001H\u0005H\u0002¢\u0006\u0002\u0010\nJ&\u0010\u000b\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00070\u000fH\u0002J\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\u0012"}, d2 = {"Lio/ktor/http/CookieDateParser;", "", "()V", "checkFieldNotNull", "", "T", "source", "", "name", "field", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Object;)V", "checkRequirement", "requirement", "", "msg", "Lkotlin/Function0;", "parse", "Lio/ktor/util/date/GMTDate;", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CookieUtils.kt */
public final class CookieDateParser {
    private final <T> void checkFieldNotNull(String str, String str2, T t) {
        if (t == null) {
            throw new InvalidCookieDateException(str, "Could not find " + str2);
        }
    }

    private final void checkRequirement(String str, boolean z, Function0<String> function0) {
        if (!z) {
            throw new InvalidCookieDateException(str, function0.invoke());
        }
    }

    public final GMTDate parse(String str) {
        Intrinsics.checkNotNullParameter(str, "source");
        StringLexer stringLexer = new StringLexer(str);
        CookieDateBuilder cookieDateBuilder = new CookieDateBuilder();
        stringLexer.acceptWhile(CookieDateParser$parse$1.INSTANCE);
        while (stringLexer.getHasRemaining()) {
            if (stringLexer.test(CookieDateParser$parse$2.INSTANCE)) {
                int index = stringLexer.getIndex();
                stringLexer.acceptWhile(CookieDateParser$parse$token$1$1.INSTANCE);
                String substring = stringLexer.getSource().substring(index, stringLexer.getIndex());
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                CookieUtilsKt.handleToken(cookieDateBuilder, substring);
                stringLexer.acceptWhile(CookieDateParser$parse$3.INSTANCE);
            }
        }
        Integer year = cookieDateBuilder.getYear();
        IntRange intRange = new IntRange(70, 99);
        boolean z = false;
        if (year == null || !intRange.contains(year.intValue())) {
            IntRange intRange2 = new IntRange(0, 69);
            if (year != null && intRange2.contains(year.intValue())) {
                Integer year2 = cookieDateBuilder.getYear();
                Intrinsics.checkNotNull(year2);
                cookieDateBuilder.setYear(Integer.valueOf(year2.intValue() + OnRepeatListener.DEFAULT_SPEEDUP_DELAY));
            }
        } else {
            Integer year3 = cookieDateBuilder.getYear();
            Intrinsics.checkNotNull(year3);
            cookieDateBuilder.setYear(Integer.valueOf(year3.intValue() + 1900));
        }
        checkFieldNotNull(str, "day-of-month", cookieDateBuilder.getDayOfMonth());
        checkFieldNotNull(str, "month", cookieDateBuilder.getMonth());
        checkFieldNotNull(str, "year", cookieDateBuilder.getYear());
        checkFieldNotNull(str, RtspHeaders.Values.TIME, cookieDateBuilder.getHours());
        checkFieldNotNull(str, RtspHeaders.Values.TIME, cookieDateBuilder.getMinutes());
        checkFieldNotNull(str, RtspHeaders.Values.TIME, cookieDateBuilder.getSeconds());
        IntRange intRange3 = new IntRange(1, 31);
        Integer dayOfMonth = cookieDateBuilder.getDayOfMonth();
        checkRequirement(str, dayOfMonth != null && intRange3.contains(dayOfMonth.intValue()), CookieDateParser$parse$4.INSTANCE);
        Integer year4 = cookieDateBuilder.getYear();
        Intrinsics.checkNotNull(year4);
        checkRequirement(str, year4.intValue() >= 1601, CookieDateParser$parse$5.INSTANCE);
        Integer hours = cookieDateBuilder.getHours();
        Intrinsics.checkNotNull(hours);
        checkRequirement(str, hours.intValue() <= 23, CookieDateParser$parse$6.INSTANCE);
        Integer minutes = cookieDateBuilder.getMinutes();
        Intrinsics.checkNotNull(minutes);
        checkRequirement(str, minutes.intValue() <= 59, CookieDateParser$parse$7.INSTANCE);
        Integer seconds = cookieDateBuilder.getSeconds();
        Intrinsics.checkNotNull(seconds);
        if (seconds.intValue() <= 59) {
            z = true;
        }
        checkRequirement(str, z, CookieDateParser$parse$8.INSTANCE);
        return cookieDateBuilder.build();
    }
}
