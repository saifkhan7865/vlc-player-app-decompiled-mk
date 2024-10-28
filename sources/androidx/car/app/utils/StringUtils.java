package androidx.car.app.utils;

public final class StringUtils {
    private static final int MAX_SHORT_STRING_LENGTH = 16;

    public static String shortenString(String str) {
        if (str.length() <= 16) {
            return str;
        }
        return str.substring(0, 8) + "~" + str.substring(str.length() - 8);
    }

    private StringUtils() {
    }
}
