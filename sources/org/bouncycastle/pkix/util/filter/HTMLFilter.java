package org.bouncycastle.pkix.util.filter;

public class HTMLFilter implements Filter {
    public String doFilter(String str) {
        String str2;
        int i;
        StringBuffer stringBuffer = new StringBuffer(str);
        int i2 = 0;
        while (i2 < stringBuffer.length()) {
            char charAt = stringBuffer.charAt(i2);
            if (charAt == '\"') {
                i = i2 + 1;
                str2 = "&#34";
            } else if (charAt == '#') {
                i = i2 + 1;
                str2 = "&#35";
            } else if (charAt == '+') {
                i = i2 + 1;
                str2 = "&#43";
            } else if (charAt == '-') {
                i = i2 + 1;
                str2 = "&#45";
            } else if (charAt == '>') {
                i = i2 + 1;
                str2 = "&#62";
            } else if (charAt == ';') {
                i = i2 + 1;
                str2 = "&#59";
            } else if (charAt != '<') {
                switch (charAt) {
                    case '%':
                        i = i2 + 1;
                        str2 = "&#37";
                        break;
                    case '&':
                        i = i2 + 1;
                        str2 = "&#38";
                        break;
                    case '\'':
                        i = i2 + 1;
                        str2 = "&#39";
                        break;
                    case '(':
                        i = i2 + 1;
                        str2 = "&#40";
                        break;
                    case ')':
                        i = i2 + 1;
                        str2 = "&#41";
                        break;
                    default:
                        i2 -= 3;
                        continue;
                }
            } else {
                i = i2 + 1;
                str2 = "&#60";
            }
            stringBuffer.replace(i2, i, str2);
            i2 += 4;
        }
        return stringBuffer.toString();
    }

    public String doFilterUrl(String str) {
        return doFilter(str);
    }
}
