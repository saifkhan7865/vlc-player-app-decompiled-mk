package org.videolan.tools;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.StringCompanionObject;
import kotlin.text.StringsKt;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u00004\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0001\u001a!\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\u0006\u0010\u0006\u001a\u00020\u0001¢\u0006\u0002\u0010\u000b\u001a\u0012\u0010\f\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\r\u001a\u00020\u0003\u001a\n\u0010\u000e\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u000f\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0010\u001a\u00020\u0001*\u00020\u0011\u001a\n\u0010\u0012\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0013\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0014\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0015\u001a\u00020\u0001*\u00020\u0016\u001a\f\u0010\u0017\u001a\u00020\u0001*\u00020\u0011H\u0007\u001a\n\u0010\u0018\u001a\u00020\u0001*\u00020\u0001\u001a\n\u0010\u0019\u001a\u00020\u0001*\u00020\u0001\"\u000e\u0010\u0000\u001a\u00020\u0001XT¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"TAG", "", "containsName", "", "list", "", "text", "startsWith", "", "array", "", "([Ljava/lang/String;Ljava/lang/String;)Z", "abbreviate", "maxLen", "addTrailingSlashIfNeeded", "firstLetterUppercase", "formatRateString", "", "getFileNameFromPath", "markBidi", "password", "readableSize", "", "readableString", "removeFileScheme", "stripTrailingSlash", "tools_release"}, k = 2, mv = {1, 9, 0}, xi = 48)
/* compiled from: Strings.kt */
public final class Strings {
    private static final String TAG = "VLC/UiTools/Strings";

    public static final String stripTrailingSlash(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return (!StringsKt.endsWith$default(str, "/", false, 2, (Object) null) || str.length() <= 1) ? str : StringsKt.dropLast(str, 1);
    }

    public static final String addTrailingSlashIfNeeded(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (StringsKt.endsWith$default(str, "/", false, 2, (Object) null)) {
            return str;
        }
        return str + '/';
    }

    public static final String formatRateString(float f) {
        StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
        String format = String.format(Locale.US, "%.2fx", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format, "format(...)");
        return format;
    }

    public static final String readableSize(long j) {
        if (j <= 0) {
            return Constants.GROUP_VIDEOS_FOLDER;
        }
        double d = (double) j;
        int log10 = (int) (Math.log10(d) / Math.log10(1000.0d));
        StringBuilder sb = new StringBuilder();
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.#");
        double pow = Math.pow(1000.0d, (double) log10);
        Double.isNaN(d);
        sb.append(decimalFormat.format(d / pow));
        sb.append(' ');
        sb.append(new String[]{"B", "KB", "MB", "GB", "TB"}[log10]);
        return sb.toString();
    }

    public static final String readableString(float f) {
        double d = (double) f;
        Double.isNaN(d);
        if (d % 1.0d == 0.0d) {
            StringCompanionObject stringCompanionObject = StringCompanionObject.INSTANCE;
            String format = String.format(TimeModel.NUMBER_FORMAT, Arrays.copyOf(new Object[]{Long.valueOf((long) f)}, 1));
            Intrinsics.checkNotNullExpressionValue(format, "format(...)");
            return format;
        }
        StringCompanionObject stringCompanionObject2 = StringCompanionObject.INSTANCE;
        String format2 = String.format("%s", Arrays.copyOf(new Object[]{Float.valueOf(f)}, 1));
        Intrinsics.checkNotNullExpressionValue(format2, "format(...)");
        return format2;
    }

    public static final String removeFileScheme(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt.startsWith$default(str, "file://", false, 2, (Object) null) ? StringsKt.drop(str, 7) : str;
    }

    public static final String getFileNameFromPath(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt.substringBeforeLast$default(str, '/', (String) null, 2, (Object) null);
    }

    public static final String firstLetterUppercase(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        if (str.length() == 0) {
            return "";
        }
        if (str.length() == 1) {
            Locale locale = Locale.getDefault();
            Intrinsics.checkNotNullExpressionValue(locale, "getDefault(...)");
            String upperCase = str.toUpperCase(locale);
            Intrinsics.checkNotNullExpressionValue(upperCase, "toUpperCase(...)");
            return upperCase;
        }
        char upperCase2 = Character.toUpperCase(str.charAt(0));
        String substring = str.substring(1);
        Intrinsics.checkNotNullExpressionValue(substring, "substring(...)");
        Locale locale2 = Locale.getDefault();
        Intrinsics.checkNotNullExpressionValue(locale2, "getDefault(...)");
        String lowerCase = substring.toLowerCase(locale2);
        Intrinsics.checkNotNullExpressionValue(lowerCase, "toLowerCase(...)");
        return upperCase2 + lowerCase;
    }

    public static final String password(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        return StringsKt.repeat("*", str.length());
    }

    public static final String abbreviate(String str, int i) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        String obj = StringsKt.trim((CharSequence) str).toString();
        if (obj.length() <= i) {
            return obj;
        }
        return StringsKt.trim((CharSequence) StringsKt.take(obj, i - 1)).toString() + "…";
    }

    public static final String markBidi(String str) {
        Intrinsics.checkNotNullParameter(str, "<this>");
        int length = str.length();
        int i = 0;
        while (i < length) {
            byte directionality = Character.getDirectionality(str.charAt(i));
            if (directionality == 1 || directionality == 2 || directionality == 16 || directionality == 17) {
                return "⁧" + str + "⁩";
            } else if (directionality == 0 || directionality == 14 || directionality == 15) {
                return str;
            } else {
                i++;
            }
        }
        return str;
    }

    public static final boolean startsWith(String[] strArr, String str) {
        Intrinsics.checkNotNullParameter(strArr, "array");
        Intrinsics.checkNotNullParameter(str, "text");
        for (String startsWith$default : strArr) {
            if (StringsKt.startsWith$default(str, startsWith$default, false, 2, (Object) null)) {
                return true;
            }
        }
        return false;
    }

    public static final int containsName(List<String> list, String str) {
        Intrinsics.checkNotNullParameter(list, "list");
        Intrinsics.checkNotNullParameter(str, "text");
        ListIterator<String> listIterator = list.listIterator(list.size());
        while (listIterator.hasPrevious()) {
            if (StringsKt.endsWith$default(listIterator.previous(), str, false, 2, (Object) null)) {
                return listIterator.nextIndex();
            }
        }
        return -1;
    }
}
