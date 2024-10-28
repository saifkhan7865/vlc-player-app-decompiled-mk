package io.ktor.http;

import java.nio.charset.Charset;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u00002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\f\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\b\u001a(\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bH\u0002\u001a \u0010\f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a$\u0010\r\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a\u001c\u0010\u0010\u001a\u00020\u0006*\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u000bH\u0002\u001a,\u0010\u0013\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u0006H\u0002\u001a$\u0010\u0015\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a$\u0010\u0016\u001a\u00020\u000e*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a$\u0010\u0017\u001a\u00020\u0006*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u0006H\u0002\u001a\u0012\u0010\u0018\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0002\u001a\u0014\u0010\u0019\u001a\u00020\u000f*\u00020\u000f2\u0006\u0010\u0007\u001a\u00020\u0002H\u0000\"\u001a\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004¨\u0006\u001a"}, d2 = {"ROOT_PATH", "", "", "getROOT_PATH", "()Ljava/util/List;", "count", "", "urlString", "startIndex", "endIndex", "char", "", "findScheme", "fillHost", "", "Lio/ktor/http/URLBuilder;", "indexOfColonInHostPort", "isLetter", "", "parseFile", "slashCount", "parseFragment", "parseMailto", "parseQuery", "takeFrom", "takeFromUnsafe", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: URLParser.kt */
public final class URLParserKt {
    private static final List<String> ROOT_PATH = CollectionsKt.listOf("");

    public static final List<String> getROOT_PATH() {
        return ROOT_PATH;
    }

    public static final URLBuilder takeFrom(URLBuilder uRLBuilder, String str) {
        Intrinsics.checkNotNullParameter(uRLBuilder, "<this>");
        Intrinsics.checkNotNullParameter(str, "urlString");
        if (StringsKt.isBlank(str)) {
            return uRLBuilder;
        }
        try {
            return takeFromUnsafe(uRLBuilder, str);
        } catch (Throwable th) {
            throw new URLParserException(str, th);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final io.ktor.http.URLBuilder takeFromUnsafe(io.ktor.http.URLBuilder r19, java.lang.String r20) {
        /*
            r0 = r19
            r1 = r20
            java.lang.String r2 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r0, r2)
            java.lang.String r2 = "urlString"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r1, r2)
            r2 = r1
            java.lang.CharSequence r2 = (java.lang.CharSequence) r2
            int r3 = r2.length()
            r4 = 0
        L_0x0016:
            r10 = -1
            r11 = 1
            if (r4 >= r3) goto L_0x0029
            char r5 = r2.charAt(r4)
            boolean r5 = kotlin.text.CharsKt.isWhitespace(r5)
            r5 = r5 ^ r11
            if (r5 == 0) goto L_0x0026
            goto L_0x002a
        L_0x0026:
            int r4 = r4 + 1
            goto L_0x0016
        L_0x0029:
            r4 = -1
        L_0x002a:
            int r3 = r2.length()
            int r3 = r3 + r10
            if (r3 < 0) goto L_0x0045
        L_0x0031:
            int r5 = r3 + -1
            char r6 = r2.charAt(r3)
            boolean r6 = kotlin.text.CharsKt.isWhitespace(r6)
            r6 = r6 ^ r11
            if (r6 == 0) goto L_0x0040
            r12 = r3
            goto L_0x0046
        L_0x0040:
            if (r5 >= 0) goto L_0x0043
            goto L_0x0045
        L_0x0043:
            r3 = r5
            goto L_0x0031
        L_0x0045:
            r12 = -1
        L_0x0046:
            int r13 = r12 + 1
            int r3 = findScheme(r1, r4, r13)
            java.lang.String r14 = "this as java.lang.String…ing(startIndex, endIndex)"
            if (r3 <= 0) goto L_0x0064
            int r5 = r4 + r3
            java.lang.String r5 = r1.substring(r4, r5)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r14)
            io.ktor.http.URLProtocol$Companion r6 = io.ktor.http.URLProtocol.Companion
            io.ktor.http.URLProtocol r5 = r6.createOrDefault(r5)
            r0.setProtocol(r5)
            int r3 = r3 + r11
            int r4 = r4 + r3
        L_0x0064:
            r15 = 47
            int r8 = count(r1, r4, r13, r15)
            int r4 = r4 + r8
            io.ktor.http.URLProtocol r3 = r19.getProtocol()
            java.lang.String r3 = r3.getName()
            java.lang.String r5 = "file"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)
            if (r3 == 0) goto L_0x007f
            parseFile(r0, r1, r4, r13, r8)
            return r0
        L_0x007f:
            io.ktor.http.URLProtocol r3 = r19.getProtocol()
            java.lang.String r3 = r3.getName()
            java.lang.String r5 = "mailto"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)
            if (r3 == 0) goto L_0x00a1
            if (r8 != 0) goto L_0x0095
            parseMailto(r0, r1, r4, r13)
            return r0
        L_0x0095:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = "Failed requirement."
            java.lang.String r1 = r1.toString()
            r0.<init>(r1)
            throw r0
        L_0x00a1:
            r3 = 2
            r16 = 0
            if (r8 < r3) goto L_0x0111
            r7 = r4
        L_0x00a7:
            java.lang.String r3 = "@/\\?#"
            char[] r4 = io.ktor.util.CharsetKt.toCharArray(r3)
            r17 = 4
            r18 = 0
            r6 = 0
            r3 = r2
            r5 = r7
            r9 = r7
            r7 = r17
            r11 = r8
            r8 = r18
            int r3 = kotlin.text.StringsKt.indexOfAny$default((java.lang.CharSequence) r3, (char[]) r4, (int) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)
            java.lang.Integer r3 = java.lang.Integer.valueOf(r3)
            r4 = r3
            java.lang.Number r4 = (java.lang.Number) r4
            int r4 = r4.intValue()
            if (r4 <= 0) goto L_0x00cc
            goto L_0x00ce
        L_0x00cc:
            r3 = r16
        L_0x00ce:
            if (r3 == 0) goto L_0x00d6
            int r3 = r3.intValue()
            r4 = r3
            goto L_0x00d7
        L_0x00d6:
            r4 = r13
        L_0x00d7:
            if (r4 >= r13) goto L_0x010d
            char r3 = r1.charAt(r4)
            r5 = 64
            if (r3 != r5) goto L_0x010d
            int r3 = indexOfColonInHostPort(r1, r9, r4)
            if (r3 == r10) goto L_0x00fe
            java.lang.String r5 = r1.substring(r9, r3)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r5, r14)
            r0.setEncodedUser(r5)
            int r3 = r3 + 1
            java.lang.String r3 = r1.substring(r3, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r14)
            r0.setEncodedPassword(r3)
            goto L_0x0108
        L_0x00fe:
            java.lang.String r3 = r1.substring(r9, r4)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r14)
            r0.setEncodedUser(r3)
        L_0x0108:
            int r7 = r4 + 1
            r8 = r11
            r11 = 1
            goto L_0x00a7
        L_0x010d:
            fillHost(r0, r1, r9, r4)
            goto L_0x0112
        L_0x0111:
            r11 = r8
        L_0x0112:
            r9 = r4
            if (r9 < r13) goto L_0x0126
            char r1 = r1.charAt(r12)
            if (r1 != r15) goto L_0x011e
            java.util.List<java.lang.String> r1 = ROOT_PATH
            goto L_0x0122
        L_0x011e:
            java.util.List r1 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0122:
            r0.setEncodedPathSegments(r1)
            return r0
        L_0x0126:
            if (r11 != 0) goto L_0x0132
            java.util.List r3 = r19.getEncodedPathSegments()
            r4 = 1
            java.util.List r3 = kotlin.collections.CollectionsKt.dropLast(r3, r4)
            goto L_0x0136
        L_0x0132:
            java.util.List r3 = kotlin.collections.CollectionsKt.emptyList()
        L_0x0136:
            r0.setEncodedPathSegments(r3)
            java.lang.String r3 = "?#"
            char[] r4 = io.ktor.util.CharsetKt.toCharArray(r3)
            r7 = 4
            r8 = 0
            r6 = 0
            r3 = r2
            r5 = r9
            int r2 = kotlin.text.StringsKt.indexOfAny$default((java.lang.CharSequence) r3, (char[]) r4, (int) r5, (boolean) r6, (int) r7, (java.lang.Object) r8)
            java.lang.Integer r2 = java.lang.Integer.valueOf(r2)
            r3 = r2
            java.lang.Number r3 = (java.lang.Number) r3
            int r3 = r3.intValue()
            if (r3 <= 0) goto L_0x0157
            r16 = r2
        L_0x0157:
            if (r16 == 0) goto L_0x015e
            int r2 = r16.intValue()
            goto L_0x015f
        L_0x015e:
            r2 = r13
        L_0x015f:
            if (r2 <= r9) goto L_0x01c6
            java.lang.String r3 = r1.substring(r9, r2)
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r14)
            java.util.List r4 = r19.getEncodedPathSegments()
            int r4 = r4.size()
            r5 = 1
            if (r4 != r5) goto L_0x0188
            java.util.List r4 = r19.getEncodedPathSegments()
            java.lang.Object r4 = kotlin.collections.CollectionsKt.first(r4)
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            int r4 = r4.length()
            if (r4 != 0) goto L_0x0188
            java.util.List r4 = kotlin.collections.CollectionsKt.emptyList()
            goto L_0x018c
        L_0x0188:
            java.util.List r4 = r19.getEncodedPathSegments()
        L_0x018c:
            java.lang.String r5 = "/"
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r5)
            if (r5 == 0) goto L_0x0197
            java.util.List<java.lang.String> r3 = ROOT_PATH
            goto L_0x01a8
        L_0x0197:
            r5 = r3
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r3 = 1
            char[] r6 = new char[r3]
            r3 = 0
            r6[r3] = r15
            r9 = 6
            r10 = 0
            r7 = 0
            r8 = 0
            java.util.List r3 = kotlin.text.StringsKt.split$default((java.lang.CharSequence) r5, (char[]) r6, (boolean) r7, (int) r8, (int) r9, (java.lang.Object) r10)
        L_0x01a8:
            r5 = 1
            if (r11 != r5) goto L_0x01ae
            java.util.List<java.lang.String> r5 = ROOT_PATH
            goto L_0x01b2
        L_0x01ae:
            java.util.List r5 = kotlin.collections.CollectionsKt.emptyList()
        L_0x01b2:
            java.util.Collection r5 = (java.util.Collection) r5
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.List r3 = kotlin.collections.CollectionsKt.plus(r5, r3)
            java.util.Collection r4 = (java.util.Collection) r4
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.List r3 = kotlin.collections.CollectionsKt.plus(r4, r3)
            r0.setEncodedPathSegments(r3)
            r9 = r2
        L_0x01c6:
            if (r9 >= r13) goto L_0x01d4
            char r2 = r1.charAt(r9)
            r3 = 63
            if (r2 != r3) goto L_0x01d4
            int r9 = parseQuery(r0, r1, r9, r13)
        L_0x01d4:
            parseFragment(r0, r1, r9, r13)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.URLParserKt.takeFromUnsafe(io.ktor.http.URLBuilder, java.lang.String):io.ktor.http.URLBuilder");
    }

    private static final void parseFile(URLBuilder uRLBuilder, String str, int i, int i2, int i3) {
        if (i3 == 2) {
            int indexOf$default = StringsKt.indexOf$default((CharSequence) str, '/', i, false, 4, (Object) null);
            if (indexOf$default == -1 || indexOf$default == i2) {
                String substring = str.substring(i, i2);
                Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
                uRLBuilder.setHost(substring);
                return;
            }
            String substring2 = str.substring(i, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setHost(substring2);
            String substring3 = str.substring(indexOf$default, i2);
            Intrinsics.checkNotNullExpressionValue(substring3, "this as java.lang.String…ing(startIndex, endIndex)");
            URLBuilderKt.setEncodedPath(uRLBuilder, substring3);
        } else if (i3 == 3) {
            uRLBuilder.setHost("");
            StringBuilder sb = new StringBuilder("/");
            String substring4 = str.substring(i, i2);
            Intrinsics.checkNotNullExpressionValue(substring4, "this as java.lang.String…ing(startIndex, endIndex)");
            sb.append(substring4);
            URLBuilderKt.setEncodedPath(uRLBuilder, sb.toString());
        } else {
            throw new IllegalArgumentException("Invalid file url: " + str);
        }
    }

    private static final void parseMailto(URLBuilder uRLBuilder, String str, int i, int i2) {
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, "@", i, false, 4, (Object) null);
        if (indexOf$default != -1) {
            String substring = str.substring(i, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setUser(CodecsKt.decodeURLPart$default(substring, 0, 0, (Charset) null, 7, (Object) null));
            String substring2 = str.substring(indexOf$default + 1, i2);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setHost(substring2);
            return;
        }
        throw new IllegalArgumentException("Invalid mailto url: " + str + ", it should contain '@'.");
    }

    private static final int parseQuery(URLBuilder uRLBuilder, String str, int i, int i2) {
        int i3 = i + 1;
        if (i3 == i2) {
            uRLBuilder.setTrailingQuery(true);
            return i2;
        }
        Integer valueOf = Integer.valueOf(StringsKt.indexOf$default((CharSequence) str, '#', i3, false, 4, (Object) null));
        if (valueOf.intValue() <= 0) {
            valueOf = null;
        }
        if (valueOf != null) {
            i2 = valueOf.intValue();
        }
        String substring = str.substring(i3, i2);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        QueryKt.parseQueryString$default(substring, 0, 0, false, 6, (Object) null).forEach(new URLParserKt$parseQuery$1(uRLBuilder));
        return i2;
    }

    private static final void parseFragment(URLBuilder uRLBuilder, String str, int i, int i2) {
        if (i < i2 && str.charAt(i) == '#') {
            String substring = str.substring(i + 1, i2);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setEncodedFragment(substring);
        }
    }

    private static final void fillHost(URLBuilder uRLBuilder, String str, int i, int i2) {
        Integer valueOf = Integer.valueOf(indexOfColonInHostPort(str, i, i2));
        if (valueOf.intValue() <= 0) {
            valueOf = null;
        }
        int intValue = valueOf != null ? valueOf.intValue() : i2;
        String substring = str.substring(i, intValue);
        Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
        uRLBuilder.setHost(substring);
        int i3 = intValue + 1;
        if (i3 < i2) {
            String substring2 = str.substring(i3, i2);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String…ing(startIndex, endIndex)");
            uRLBuilder.setPort(Integer.parseInt(substring2));
            return;
        }
        uRLBuilder.setPort(0);
    }

    private static final int findScheme(String str, int i, int i2) {
        int i3;
        int i4;
        char charAt = str.charAt(i);
        if (('a' > charAt || charAt >= '{') && ('A' > charAt || charAt >= '[')) {
            i4 = i;
            i3 = i4;
        } else {
            i4 = i;
            i3 = -1;
        }
        while (i4 < i2) {
            char charAt2 = str.charAt(i4);
            if (charAt2 != ':') {
                if (charAt2 == '/' || charAt2 == '?' || charAt2 == '#') {
                    break;
                }
                if (i3 == -1 && (('a' > charAt2 || charAt2 >= '{') && (('A' > charAt2 || charAt2 >= '[') && !(('0' <= charAt2 && charAt2 < ':') || charAt2 == '.' || charAt2 == '+' || charAt2 == '-')))) {
                    i3 = i4;
                }
                i4++;
            } else if (i3 == -1) {
                return i4 - i;
            } else {
                throw new IllegalArgumentException("Illegal character in scheme at position " + i3);
            }
        }
        return -1;
    }

    private static final int count(String str, int i, int i2, char c) {
        int i3 = 0;
        while (true) {
            int i4 = i + i3;
            if (i4 >= i2 || str.charAt(i4) != c) {
                return i3;
            }
            i3++;
        }
        return i3;
    }

    private static final int indexOfColonInHostPort(String str, int i, int i2) {
        boolean z = false;
        while (i < i2) {
            char charAt = str.charAt(i);
            if (charAt == '[') {
                z = true;
            } else if (charAt == ']') {
                z = false;
            } else if (charAt == ':' && !z) {
                return i;
            }
            i++;
        }
        return -1;
    }

    private static final boolean isLetter(char c) {
        char lowerCase = Character.toLowerCase(c);
        return 'a' <= lowerCase && lowerCase < '{';
    }
}
