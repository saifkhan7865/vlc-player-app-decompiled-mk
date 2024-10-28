package com.squareup.moshi.adapters;

import j$.util.DesugarTimeZone;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;
import org.bouncycastle.pqc.legacy.math.linearalgebra.Matrix;

final class Iso8601Utils {
    static final String GMT_ID = "GMT";
    static final TimeZone TIMEZONE_Z = DesugarTimeZone.getTimeZone(GMT_ID);

    Iso8601Utils() {
    }

    public static String format(Date date) {
        GregorianCalendar gregorianCalendar = new GregorianCalendar(TIMEZONE_Z, Locale.US);
        gregorianCalendar.setTime(date);
        StringBuilder sb = new StringBuilder(24);
        padInt(sb, gregorianCalendar.get(1), 4);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(2) + 1, 2);
        sb.append('-');
        padInt(sb, gregorianCalendar.get(5), 2);
        sb.append('T');
        padInt(sb, gregorianCalendar.get(11), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        padInt(sb, gregorianCalendar.get(12), 2);
        sb.append(AbstractJsonLexerKt.COLON);
        padInt(sb, gregorianCalendar.get(13), 2);
        sb.append('.');
        padInt(sb, gregorianCalendar.get(14), 3);
        sb.append(Matrix.MATRIX_TYPE_ZERO);
        return sb.toString();
    }

    /* JADX WARNING: Removed duplicated region for block: B:44:0x00d3 A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0194 }] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x018c A[Catch:{ IllegalArgumentException | IndexOutOfBoundsException -> 0x0194 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static java.util.Date parse(java.lang.String r17) {
        /*
            r1 = r17
            java.lang.String r0 = "Mismatching time zone indicator: "
            java.lang.String r2 = "GMT"
            java.lang.String r3 = "Invalid time zone indicator '"
            r4 = 4
            r5 = 0
            int r6 = parseInt(r1, r5, r4)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r7 = 45
            boolean r8 = checkOffset(r1, r4, r7)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r8 == 0) goto L_0x0017
            r4 = 5
        L_0x0017:
            int r8 = r4 + 2
            int r10 = parseInt(r1, r4, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            boolean r11 = checkOffset(r1, r8, r7)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r11 == 0) goto L_0x0025
            int r8 = r4 + 3
        L_0x0025:
            int r4 = r8 + 2
            int r11 = parseInt(r1, r8, r4)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r12 = 84
            boolean r12 = checkOffset(r1, r4, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r13 = 1
            if (r12 != 0) goto L_0x0045
            int r14 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r14 > r4) goto L_0x0045
            java.util.GregorianCalendar r0 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            int r10 = r10 - r13
            r0.<init>(r6, r10, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.util.Date r0 = r0.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            return r0
        L_0x0045:
            r14 = 43
            r15 = 90
            if (r12 == 0) goto L_0x00c9
            int r4 = r8 + 3
            int r12 = r8 + 5
            int r4 = parseInt(r1, r4, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r9 = 58
            boolean r16 = checkOffset(r1, r12, r9)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r16 == 0) goto L_0x005d
            int r12 = r8 + 6
        L_0x005d:
            int r8 = r12 + 2
            int r16 = parseInt(r1, r12, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            boolean r9 = checkOffset(r1, r8, r9)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r9 == 0) goto L_0x006c
            int r12 = r12 + 3
            r8 = r12
        L_0x006c:
            int r9 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r9 <= r8) goto L_0x00c3
            char r9 = r1.charAt(r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r9 == r15) goto L_0x00c3
            if (r9 == r14) goto L_0x00c3
            if (r9 == r7) goto L_0x00c3
            int r9 = r8 + 2
            int r12 = parseInt(r1, r8, r9)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r13 = 59
            if (r12 <= r13) goto L_0x008c
            r13 = 63
            if (r12 >= r13) goto L_0x008c
            r12 = 59
        L_0x008c:
            r13 = 46
            boolean r13 = checkOffset(r1, r9, r13)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r13 == 0) goto L_0x00bd
            int r9 = r8 + 3
            int r13 = r8 + 4
            int r13 = indexOfNonDigit(r1, r13)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            int r8 = r8 + 6
            int r8 = java.lang.Math.min(r13, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            int r5 = parseInt(r1, r9, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            int r8 = r8 - r9
            int r8 = 3 - r8
            double r8 = (double) r8     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r14 = 4621819117588971520(0x4024000000000000, double:10.0)
            double r8 = java.lang.Math.pow(r14, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            double r14 = (double) r5
            java.lang.Double.isNaN(r14)
            double r8 = r8 * r14
            int r5 = (int) r8
            r8 = r5
            r9 = r16
            r5 = r4
            r4 = r13
            goto L_0x00cd
        L_0x00bd:
            r5 = r4
            r4 = r9
            r9 = r16
            r8 = 0
            goto L_0x00cd
        L_0x00c3:
            r5 = r4
            r4 = r8
            r9 = r16
            r8 = 0
            goto L_0x00cc
        L_0x00c9:
            r5 = 0
            r8 = 0
            r9 = 0
        L_0x00cc:
            r12 = 0
        L_0x00cd:
            int r13 = r17.length()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r13 <= r4) goto L_0x018c
            char r13 = r1.charAt(r4)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r14 = 90
            if (r13 != r14) goto L_0x00df
            java.util.TimeZone r0 = TIMEZONE_Z     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            goto L_0x015d
        L_0x00df:
            r14 = 43
            if (r13 == r14) goto L_0x00fd
            if (r13 != r7) goto L_0x00e6
            goto L_0x00fd
        L_0x00e6:
            java.lang.IndexOutOfBoundsException r0 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r2.<init>(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r2.append(r13)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r3 = "'"
            r2.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r2 = r2.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0.<init>(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
        L_0x00fd:
            java.lang.String r3 = r1.substring(r4)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r4 = "+0000"
            boolean r4 = r4.equals(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r4 != 0) goto L_0x015b
            java.lang.String r4 = "+00:00"
            boolean r4 = r4.equals(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r4 == 0) goto L_0x0112
            goto L_0x015b
        L_0x0112:
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r4.<init>(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r4.append(r3)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r2 = r4.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.util.TimeZone r3 = j$.util.DesugarTimeZone.getTimeZone(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r4 = r3.getID()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            boolean r7 = r4.equals(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r7 != 0) goto L_0x0159
            java.lang.String r7 = ":"
            java.lang.String r13 = ""
            java.lang.String r4 = r4.replace(r7, r13)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            boolean r4 = r4.equals(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            if (r4 == 0) goto L_0x013b
            goto L_0x0159
        L_0x013b:
            java.lang.IndexOutOfBoundsException r4 = new java.lang.IndexOutOfBoundsException     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r5.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r5.append(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r0 = " given, resolves to "
            r5.append(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r0 = r3.getID()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r5.append(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r0 = r5.toString()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r4.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            throw r4     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
        L_0x0159:
            r0 = r3
            goto L_0x015d
        L_0x015b:
            java.util.TimeZone r0 = TIMEZONE_Z     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
        L_0x015d:
            java.util.GregorianCalendar r2 = new java.util.GregorianCalendar     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r2.<init>(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 0
            r2.setLenient(r0)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 1
            r2.set(r0, r6)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            int r10 = r10 - r0
            r0 = 2
            r2.set(r0, r10)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 5
            r2.set(r0, r11)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 11
            r2.set(r0, r5)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 12
            r2.set(r0, r9)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 13
            r2.set(r0, r12)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            r0 = 14
            r2.set(r0, r8)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.util.Date r0 = r2.getTime()     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            return r0
        L_0x018c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            java.lang.String r2 = "No time zone indicator"
            r0.<init>(r2)     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
            throw r0     // Catch:{ IndexOutOfBoundsException -> 0x0196, IllegalArgumentException -> 0x0194 }
        L_0x0194:
            r0 = move-exception
            goto L_0x0197
        L_0x0196:
            r0 = move-exception
        L_0x0197:
            com.squareup.moshi.JsonDataException r2 = new com.squareup.moshi.JsonDataException
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            java.lang.String r4 = "Not an RFC 3339 date: "
            r3.<init>(r4)
            r3.append(r1)
            java.lang.String r1 = r3.toString()
            r2.<init>(r1, r0)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.squareup.moshi.adapters.Iso8601Utils.parse(java.lang.String):java.util.Date");
    }

    private static boolean checkOffset(String str, int i, char c) {
        return i < str.length() && str.charAt(i) == c;
    }

    private static int parseInt(String str, int i, int i2) throws NumberFormatException {
        int i3;
        int i4;
        if (i < 0 || i2 > str.length() || i > i2) {
            throw new NumberFormatException(str);
        }
        if (i < i2) {
            i4 = i + 1;
            int digit = Character.digit(str.charAt(i), 10);
            if (digit >= 0) {
                i3 = -digit;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        } else {
            i3 = 0;
            i4 = i;
        }
        while (i4 < i2) {
            int i5 = i4 + 1;
            int digit2 = Character.digit(str.charAt(i4), 10);
            if (digit2 >= 0) {
                i3 = (i3 * 10) - digit2;
                i4 = i5;
            } else {
                throw new NumberFormatException("Invalid number: " + str.substring(i, i2));
            }
        }
        return -i3;
    }

    private static void padInt(StringBuilder sb, int i, int i2) {
        String num = Integer.toString(i);
        for (int length = i2 - num.length(); length > 0; length--) {
            sb.append('0');
        }
        sb.append(num);
    }

    private static int indexOfNonDigit(String str, int i) {
        while (i < str.length()) {
            char charAt = str.charAt(i);
            if (charAt < '0' || charAt > '9') {
                return i;
            }
            i++;
        }
        return str.length();
    }
}
