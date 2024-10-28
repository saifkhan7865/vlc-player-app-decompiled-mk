package io.netty.handler.codec.http;

import io.netty.util.AsciiString;

public enum HttpStatusClass {
    INFORMATIONAL(100, 200, "Informational"),
    SUCCESS(200, MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, "Success"),
    REDIRECTION(MaterialCardViewHelper.DEFAULT_FADE_ANIM_DURATION, 400, "Redirection"),
    CLIENT_ERROR(400, 500, "Client Error"),
    SERVER_ERROR(500, 600, "Server Error"),
    UNKNOWN(0, 0, "Unknown Status") {
        public boolean contains(int i) {
            return i < 100 || i >= 600;
        }
    };
    
    private static final HttpStatusClass[] statusArray = null;
    private final AsciiString defaultReasonPhrase;
    private final int max;
    private final int min;

    private static int digit(char c) {
        return c - '0';
    }

    private static int fast_div100(int i) {
        return (int) ((((long) i) * 1374389535) >> 37);
    }

    private static boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    static {
        HttpStatusClass httpStatusClass;
        HttpStatusClass httpStatusClass2;
        HttpStatusClass httpStatusClass3;
        HttpStatusClass httpStatusClass4;
        HttpStatusClass httpStatusClass5;
        HttpStatusClass[] httpStatusClassArr = new HttpStatusClass[6];
        statusArray = httpStatusClassArr;
        httpStatusClassArr[1] = httpStatusClass;
        httpStatusClassArr[2] = httpStatusClass2;
        httpStatusClassArr[3] = httpStatusClass3;
        httpStatusClassArr[4] = httpStatusClass4;
        httpStatusClassArr[5] = httpStatusClass5;
    }

    public static HttpStatusClass valueOf(int i) {
        HttpStatusClass httpStatusClass = UNKNOWN;
        if (httpStatusClass.contains(i)) {
            return httpStatusClass;
        }
        return statusArray[fast_div100(i)];
    }

    public static HttpStatusClass valueOf(CharSequence charSequence) {
        if (charSequence == null || charSequence.length() != 3) {
            return UNKNOWN;
        }
        char charAt = charSequence.charAt(0);
        return (!isDigit(charAt) || !isDigit(charSequence.charAt(1)) || !isDigit(charSequence.charAt(2))) ? UNKNOWN : valueOf(digit(charAt) * 100);
    }

    private HttpStatusClass(int i, int i2, String str) {
        this.min = i;
        this.max = i2;
        this.defaultReasonPhrase = AsciiString.cached(str);
    }

    public boolean contains(int i) {
        return i >= this.min && i < this.max;
    }

    /* access modifiers changed from: package-private */
    public AsciiString defaultReasonPhrase() {
        return this.defaultReasonPhrase;
    }
}
