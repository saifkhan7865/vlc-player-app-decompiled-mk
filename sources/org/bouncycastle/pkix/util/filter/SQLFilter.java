package org.bouncycastle.pkix.util.filter;

public class SQLFilter implements Filter {
    public String doFilter(String str) {
        String str2;
        int i;
        StringBuffer stringBuffer = new StringBuffer(str);
        int i2 = 0;
        while (i2 < stringBuffer.length()) {
            char charAt = stringBuffer.charAt(i2);
            if (charAt == 10) {
                i = i2 + 1;
                str2 = "\\n";
            } else if (charAt == 13) {
                i = i2 + 1;
                str2 = "\\r";
            } else if (charAt == '\"') {
                i = i2 + 1;
                str2 = "\\\"";
            } else if (charAt == '\'') {
                i = i2 + 1;
                str2 = "\\'";
            } else if (charAt == '-') {
                i = i2 + 1;
                str2 = "\\-";
            } else if (charAt == '/') {
                i = i2 + 1;
                str2 = "\\/";
            } else if (charAt == ';') {
                i = i2 + 1;
                str2 = "\\;";
            } else if (charAt == '=') {
                i = i2 + 1;
                str2 = "\\=";
            } else if (charAt != '\\') {
                i2++;
            } else {
                i = i2 + 1;
                str2 = "\\\\";
            }
            stringBuffer.replace(i2, i, str2);
            i2 = i;
            i2++;
        }
        return stringBuffer.toString();
    }

    public String doFilterUrl(String str) {
        return doFilter(str);
    }
}
