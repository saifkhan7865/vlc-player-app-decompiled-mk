package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.util.Base64Kt;
import io.ktor.util.TextKt;
import io.ktor.util.date.GMTDate;
import io.netty.handler.codec.http.cookie.CookieHeaderNames;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.NoWhenBranchMatchedException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import kotlin.text.CharsKt;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import org.videolan.vlc.ArtworkProvider;

@Metadata(d1 = {"\u0000N\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\f\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\u001a#\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\b\u001a\u001b\u0010\r\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\u0006H\b\u001a\u0019\u0010\u000e\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u000fH\b\u001a\u001b\u0010\u0010\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\b\u0010\t\u001a\u0004\u0018\u00010\nH\b\u001a\u0016\u0010\u0011\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f\u001a\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\f\u001a$\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0016\u001a\u00020\u00062\b\b\u0002\u0010\u0017\u001a\u00020\u000f\u001a\u000e\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0016\u001a\u00020\u0006\u001a\u000e\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0019\u001a\u000e\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u0019\u001a\u0001\u0010\u001c\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\u00062\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\n\b\u0002\u0010\u001f\u001a\u0004\u0018\u00010 2\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\u00062\n\b\u0002\u0010\"\u001a\u0004\u0018\u00010\u00062\b\b\u0002\u0010#\u001a\u00020\u000f2\b\b\u0002\u0010$\u001a\u00020\u000f2\u0016\b\u0002\u0010%\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0006\u0012\u0004\u0018\u00010\u00060\u00152\b\b\u0002\u0010&\u001a\u00020\u000f\u001a\f\u0010'\u001a\u00020\u0006*\u00020\u0006H\u0002\u001a\f\u0010(\u001a\u00020\u000f*\u00020\u0004H\u0002\u001a\f\u0010)\u001a\u00020\u001e*\u00020\u0006H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006*"}, d2 = {"clientCookieHeaderPattern", "Lkotlin/text/Regex;", "cookieCharsShouldBeEscaped", "", "", "loweredPartNames", "", "cookiePart", "name", "value", "", "encoding", "Lio/ktor/http/CookieEncoding;", "cookiePartExt", "cookiePartFlag", "", "cookiePartUnencoded", "decodeCookieValue", "encodedValue", "encodeCookieValue", "parseClientCookiesHeader", "", "cookiesHeader", "skipEscaped", "parseServerSetCookieHeader", "Lio/ktor/http/Cookie;", "renderCookieHeader", "cookie", "renderSetCookieHeader", "maxAge", "", "expires", "Lio/ktor/util/date/GMTDate;", "domain", "path", "secure", "httpOnly", "extensions", "includeEncoding", "assertCookieName", "shouldEscapeInCookies", "toIntClamping", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Cookie.kt */
public final class CookieKt {
    private static final Regex clientCookieHeaderPattern = new Regex("(^|;)\\s*([^;=\\{\\}\\s]+)\\s*(=\\s*(\"[^\"]*\"|[^;]*))?");
    private static final Set<Character> cookieCharsShouldBeEscaped = SetsKt.setOf(';', ',', '\"');
    private static final Set<String> loweredPartNames = SetsKt.setOf("max-age", "expires", "domain", ArtworkProvider.PATH, "secure", "httponly", "$x-enc");

    @Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
    /* compiled from: Cookie.kt */
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        /* JADX WARNING: Can't wrap try/catch for region: R(11:0|1|2|3|4|5|6|7|8|9|11) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0010 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x0019 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0022 */
        static {
            /*
                io.ktor.http.CookieEncoding[] r0 = io.ktor.http.CookieEncoding.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                io.ktor.http.CookieEncoding r1 = io.ktor.http.CookieEncoding.RAW     // Catch:{ NoSuchFieldError -> 0x0010 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0010 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0010 }
            L_0x0010:
                io.ktor.http.CookieEncoding r1 = io.ktor.http.CookieEncoding.DQUOTES     // Catch:{ NoSuchFieldError -> 0x0019 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0019 }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0019 }
            L_0x0019:
                io.ktor.http.CookieEncoding r1 = io.ktor.http.CookieEncoding.BASE64_ENCODING     // Catch:{ NoSuchFieldError -> 0x0022 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0022 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0022 }
            L_0x0022:
                io.ktor.http.CookieEncoding r1 = io.ktor.http.CookieEncoding.URI_ENCODING     // Catch:{ NoSuchFieldError -> 0x002b }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x002b }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x002b }
            L_0x002b:
                $EnumSwitchMapping$0 = r0
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: io.ktor.http.CookieKt.WhenMappings.<clinit>():void");
        }
    }

    public static final Cookie parseServerSetCookieHeader(String str) {
        CookieEncoding cookieEncoding;
        String str2 = str;
        Intrinsics.checkNotNullParameter(str2, "cookiesHeader");
        Map<String, String> parseClientCookiesHeader = parseClientCookiesHeader(str2, false);
        for (Map.Entry entry : parseClientCookiesHeader.entrySet()) {
            GMTDate gMTDate = null;
            if (!StringsKt.startsWith$default((String) entry.getKey(), "$", false, 2, (Object) null)) {
                String str3 = parseClientCookiesHeader.get("$x-enc");
                if (str3 == null || (cookieEncoding = CookieEncoding.valueOf(str3)) == null) {
                    cookieEncoding = CookieEncoding.RAW;
                }
                CookieEncoding cookieEncoding2 = cookieEncoding;
                Map linkedHashMap = new LinkedHashMap(MapsKt.mapCapacity(parseClientCookiesHeader.size()));
                for (Map.Entry entry2 : parseClientCookiesHeader.entrySet()) {
                    linkedHashMap.put(TextKt.toLowerCasePreservingASCIIRules((String) entry2.getKey()), entry2.getValue());
                }
                String str4 = (String) entry.getKey();
                String decodeCookieValue = decodeCookieValue((String) entry.getValue(), cookieEncoding2);
                String str5 = (String) linkedHashMap.get("max-age");
                int intClamping = str5 != null ? toIntClamping(str5) : 0;
                String str6 = (String) linkedHashMap.get("expires");
                if (str6 != null) {
                    gMTDate = DateUtilsKt.fromCookieToGmtDate(str6);
                }
                GMTDate gMTDate2 = gMTDate;
                String str7 = (String) linkedHashMap.get("domain");
                String str8 = (String) linkedHashMap.get(ArtworkProvider.PATH);
                boolean containsKey = linkedHashMap.containsKey("secure");
                boolean containsKey2 = linkedHashMap.containsKey("httponly");
                LinkedHashMap linkedHashMap2 = new LinkedHashMap();
                for (Map.Entry next : parseClientCookiesHeader.entrySet()) {
                    String str9 = (String) next.getKey();
                    if (!loweredPartNames.contains(TextKt.toLowerCasePreservingASCIIRules(str9)) && !Intrinsics.areEqual((Object) str9, entry.getKey())) {
                        linkedHashMap2.put(next.getKey(), next.getValue());
                    }
                }
                return new Cookie(str4, decodeCookieValue, cookieEncoding2, intClamping, gMTDate2, str7, str8, containsKey, containsKey2, linkedHashMap2);
            }
        }
        throw new NoSuchElementException("Collection contains no element matching the predicate.");
    }

    public static /* synthetic */ Map parseClientCookiesHeader$default(String str, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        return parseClientCookiesHeader(str, z);
    }

    public static final Map<String, String> parseClientCookiesHeader(String str, boolean z) {
        Intrinsics.checkNotNullParameter(str, "cookiesHeader");
        return MapsKt.toMap(SequencesKt.map(SequencesKt.filter(SequencesKt.map(Regex.findAll$default(clientCookieHeaderPattern, str, 0, 2, (Object) null), CookieKt$parseClientCookiesHeader$1.INSTANCE), new CookieKt$parseClientCookiesHeader$2(z)), CookieKt$parseClientCookiesHeader$3.INSTANCE));
    }

    public static final String renderSetCookieHeader(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return renderSetCookieHeader$default(cookie.getName(), cookie.getValue(), cookie.getEncoding(), cookie.getMaxAgeInt(), cookie.getExpires(), cookie.getDomain(), cookie.getPath(), cookie.getSecure(), cookie.getHttpOnly(), cookie.getExtensions(), false, 1024, (Object) null);
    }

    public static final String renderCookieHeader(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "cookie");
        return cookie.getName() + '=' + encodeCookieValue(cookie.getValue(), cookie.getEncoding());
    }

    public static /* synthetic */ String renderSetCookieHeader$default(String str, String str2, CookieEncoding cookieEncoding, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, boolean z3, int i2, Object obj) {
        Map map2;
        int i3 = i2;
        CookieEncoding cookieEncoding2 = (i3 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding;
        boolean z4 = false;
        int i4 = (i3 & 8) != 0 ? 0 : i;
        String str5 = null;
        GMTDate gMTDate2 = (i3 & 16) != 0 ? null : gMTDate;
        String str6 = (i3 & 32) != 0 ? null : str3;
        if ((i3 & 64) == 0) {
            str5 = str4;
        }
        boolean z5 = (i3 & 128) != 0 ? false : z;
        if ((i3 & 256) == 0) {
            z4 = z2;
        }
        if ((i3 & 512) != 0) {
            map2 = MapsKt.emptyMap();
        } else {
            map2 = map;
        }
        return renderSetCookieHeader(str, str2, cookieEncoding2, i4, gMTDate2, str6, str5, z5, z4, map2, (i3 & 1024) != 0 ? true : z3);
    }

    public static final String renderSetCookieHeader(String str, String str2, CookieEncoding cookieEncoding, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map<String, String> map, boolean z3) {
        Integer num;
        String str5;
        String str6;
        String str7;
        String str8;
        String str9;
        String str10;
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        Intrinsics.checkNotNullParameter(cookieEncoding, "encoding");
        Intrinsics.checkNotNullParameter(map, "extensions");
        String str11 = assertCookieName(str) + '=' + encodeCookieValue(str2.toString(), cookieEncoding);
        String str12 = null;
        if (i > 0) {
            num = Integer.valueOf(i);
        } else {
            num = null;
        }
        String str13 = "";
        if (num != null) {
            str5 = "Max-Age=" + num;
        } else {
            str5 = str13;
        }
        if (gMTDate != null) {
            str12 = DateUtilsKt.toHttpDate(gMTDate);
        }
        if (str12 != null) {
            str6 = "Expires=" + str12;
        } else {
            str6 = str13;
        }
        CookieEncoding cookieEncoding2 = CookieEncoding.RAW;
        if (str3 != null) {
            str7 = "Domain=" + encodeCookieValue(str3.toString(), cookieEncoding2);
        } else {
            str7 = str13;
        }
        CookieEncoding cookieEncoding3 = CookieEncoding.RAW;
        if (str4 != null) {
            str8 = "Path=" + encodeCookieValue(str4.toString(), cookieEncoding3);
        } else {
            str8 = str13;
        }
        String str14 = z ? CookieHeaderNames.SECURE : str13;
        if (z2) {
            str9 = "HttpOnly";
        } else {
            str9 = str13;
        }
        Collection listOf = CollectionsKt.listOf(str11, str5, str6, str7, str8, str14, str9);
        Collection arrayList = new ArrayList(map.size());
        for (Map.Entry next : map.entrySet()) {
            String assertCookieName = assertCookieName((String) next.getKey());
            String str15 = (String) next.getValue();
            if (str15 != null) {
                CookieEncoding cookieEncoding4 = CookieEncoding.RAW;
                assertCookieName = assertCookieName + '=' + encodeCookieValue(str15.toString(), cookieEncoding4);
            }
            arrayList.add(assertCookieName);
        }
        Collection plus = CollectionsKt.plus(listOf, (List) arrayList);
        if (z3) {
            String name = cookieEncoding.name();
            if (name == null) {
                str10 = "$x-enc";
            } else {
                CookieEncoding cookieEncoding5 = CookieEncoding.RAW;
                str10 = "$x-enc=" + encodeCookieValue(name.toString(), cookieEncoding5);
            }
            str13 = str10;
        }
        Collection arrayList2 = new ArrayList();
        for (Object next2 : CollectionsKt.plus(plus, str13)) {
            if (((String) next2).length() > 0) {
                arrayList2.add(next2);
            }
        }
        return CollectionsKt.joinToString$default((List) arrayList2, "; ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
    }

    public static final String encodeCookieValue(String str, CookieEncoding cookieEncoding) {
        Intrinsics.checkNotNullParameter(str, "value");
        Intrinsics.checkNotNullParameter(cookieEncoding, "encoding");
        int i = WhenMappings.$EnumSwitchMapping$0[cookieEncoding.ordinal()];
        int i2 = 0;
        if (i == 1) {
            CharSequence charSequence = str;
            while (i2 < charSequence.length()) {
                if (!shouldEscapeInCookies(charSequence.charAt(i2))) {
                    i2++;
                } else {
                    throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in RAW format.  Consider URL_ENCODING mode");
                }
            }
            return str;
        } else if (i == 2) {
            CharSequence charSequence2 = str;
            if (!StringsKt.contains$default(charSequence2, '\"', false, 2, (Object) null)) {
                while (i2 < charSequence2.length()) {
                    if (shouldEscapeInCookies(charSequence2.charAt(i2))) {
                        return "\"" + str + '\"';
                    }
                    i2++;
                }
                return str;
            }
            throw new IllegalArgumentException("The cookie value contains characters that cannot be encoded in DQUOTES format. Consider URL_ENCODING mode");
        } else if (i == 3) {
            return Base64Kt.encodeBase64(str);
        } else {
            if (i == 4) {
                return CodecsKt.encodeURLParameter(str, true);
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    public static final String decodeCookieValue(String str, CookieEncoding cookieEncoding) {
        Intrinsics.checkNotNullParameter(str, "encodedValue");
        Intrinsics.checkNotNullParameter(cookieEncoding, "encoding");
        int i = WhenMappings.$EnumSwitchMapping$0[cookieEncoding.ordinal()];
        if (i == 1 || i == 2) {
            CharSequence charSequence = str;
            return (!StringsKt.startsWith$default(StringsKt.trimStart(charSequence).toString(), "\"", false, 2, (Object) null) || !StringsKt.endsWith$default(StringsKt.trimEnd(charSequence).toString(), "\"", false, 2, (Object) null)) ? str : StringsKt.removeSurrounding(StringsKt.trim(charSequence).toString(), (CharSequence) "\"");
        } else if (i == 3) {
            return Base64Kt.decodeBase64String(str);
        } else {
            if (i == 4) {
                return CodecsKt.decodeURLQueryComponent$default(str, 0, 0, true, (Charset) null, 11, (Object) null);
            }
            throw new NoWhenBranchMatchedException();
        }
    }

    private static final String assertCookieName(String str) {
        CharSequence charSequence = str;
        int i = 0;
        while (i < charSequence.length()) {
            if (!shouldEscapeInCookies(charSequence.charAt(i))) {
                i++;
            } else {
                throw new IllegalArgumentException("Cookie name is not valid: " + str);
            }
        }
        return str;
    }

    private static final boolean shouldEscapeInCookies(char c) {
        return CharsKt.isWhitespace(c) || Intrinsics.compare((int) c, 32) < 0 || cookieCharsShouldBeEscaped.contains(Character.valueOf(c));
    }

    private static final String cookiePart(String str, Object obj, CookieEncoding cookieEncoding) {
        if (obj == null) {
            return "";
        }
        return str + '=' + encodeCookieValue(obj.toString(), cookieEncoding);
    }

    private static final String cookiePartUnencoded(String str, Object obj) {
        if (obj == null) {
            return "";
        }
        return str + '=' + obj;
    }

    private static final String cookiePartFlag(String str, boolean z) {
        return z ? str : "";
    }

    private static final String cookiePartExt(String str, String str2) {
        if (str2 == null) {
            return str;
        }
        CookieEncoding cookieEncoding = CookieEncoding.RAW;
        return str + '=' + encodeCookieValue(str2.toString(), cookieEncoding);
    }

    private static final int toIntClamping(String str) {
        return (int) RangesKt.coerceIn(Long.parseLong(str), 0, 2147483647L);
    }
}
