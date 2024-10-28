package io.ktor.server.response;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Cookie;
import io.ktor.http.CookieEncoding;
import io.ktor.http.CookieKt;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0010\t\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nJz\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0019H\u0007Jz\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\f2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u001a2\n\b\u0002\u0010\u0012\u001a\u0004\u0018\u00010\u00132\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\f2\b\b\u0002\u0010\u0016\u001a\u00020\u00052\b\b\u0002\u0010\u0017\u001a\u00020\u00052\u0016\b\u0002\u0010\u0018\u001a\u0010\u0012\u0004\u0012\u00020\f\u0012\u0006\u0012\u0004\u0018\u00010\f0\u0019J(\u0010\u001b\u001a\u00020\b2\u0006\u0010\u000b\u001a\u00020\f2\n\b\u0002\u0010\u0014\u001a\u0004\u0018\u00010\f2\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\fH\u0007J\u0013\u0010\u001c\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lio/ktor/server/response/ResponseCookies;", "", "response", "Lio/ktor/server/response/ApplicationResponse;", "secureTransport", "", "(Lio/ktor/server/response/ApplicationResponse;Z)V", "append", "", "item", "Lio/ktor/http/Cookie;", "name", "", "value", "encoding", "Lio/ktor/http/CookieEncoding;", "maxAge", "", "expires", "Lio/ktor/util/date/GMTDate;", "domain", "path", "secure", "httpOnly", "extensions", "", "", "appendExpired", "get", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ResponseCookies.kt */
public final class ResponseCookies {
    private final ApplicationResponse response;
    private final boolean secureTransport;

    public ResponseCookies(ApplicationResponse applicationResponse, boolean z) {
        Intrinsics.checkNotNullParameter(applicationResponse, "response");
        this.response = applicationResponse;
        this.secureTransport = z;
    }

    public final Cookie get(String str) {
        Object obj;
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Iterable<String> values = this.response.getHeaders().values("Set-Cookie");
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(values, 10));
        for (String parseServerSetCookieHeader : values) {
            arrayList.add(CookieKt.parseServerSetCookieHeader(parseServerSetCookieHeader));
        }
        Iterator it = ((List) arrayList).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((Cookie) obj).getName(), (Object) str)) {
                break;
            }
        }
        return (Cookie) obj;
    }

    public final void append(Cookie cookie) {
        Intrinsics.checkNotNullParameter(cookie, "item");
        if (!cookie.getSecure() || this.secureTransport) {
            ResponseHeaders.append$default(this.response.getHeaders(), "Set-Cookie", CookieKt.renderSetCookieHeader(cookie), false, 4, (Object) null);
            return;
        }
        throw new IllegalArgumentException("You should set secure cookie only via secure transport (HTTPS)");
    }

    public static /* synthetic */ void append$default(ResponseCookies responseCookies, String str, String str2, CookieEncoding cookieEncoding, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, int i2, Object obj) {
        Map map2;
        int i3 = i2;
        CookieEncoding cookieEncoding2 = (i3 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding;
        GMTDate gMTDate2 = (i3 & 16) != 0 ? null : gMTDate;
        String str5 = (i3 & 32) != 0 ? null : str3;
        String str6 = (i3 & 64) != 0 ? null : str4;
        boolean z3 = (i3 & 128) != 0 ? false : z;
        boolean z4 = (i3 & 256) != 0 ? false : z2;
        if ((i3 & 512) != 0) {
            map2 = MapsKt.emptyMap();
        } else {
            map2 = map;
        }
        responseCookies.append(str, str2, cookieEncoding2, i, gMTDate2, str5, str6, z3, z4, (Map<String, String>) map2);
    }

    @Deprecated(level = DeprecationLevel.ERROR, message = "Convert maxAge to Long")
    public final void append(String str, String str2, CookieEncoding cookieEncoding, int i, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        CookieEncoding cookieEncoding2 = cookieEncoding;
        Intrinsics.checkNotNullParameter(cookieEncoding2, "encoding");
        Map<String, String> map2 = map;
        Intrinsics.checkNotNullParameter(map2, "extensions");
        append(str, str2, cookieEncoding2, (long) i, gMTDate, str3, str4, z, z2, map2);
    }

    public static /* synthetic */ void append$default(ResponseCookies responseCookies, String str, String str2, CookieEncoding cookieEncoding, long j, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map map, int i, Object obj) {
        Map map2;
        int i2 = i;
        CookieEncoding cookieEncoding2 = (i2 & 4) != 0 ? CookieEncoding.URI_ENCODING : cookieEncoding;
        long j2 = (i2 & 8) != 0 ? 0 : j;
        GMTDate gMTDate2 = (i2 & 16) != 0 ? null : gMTDate;
        String str5 = (i2 & 32) != 0 ? null : str3;
        String str6 = (i2 & 64) != 0 ? null : str4;
        boolean z3 = (i2 & 128) != 0 ? false : z;
        boolean z4 = (i2 & 256) != 0 ? false : z2;
        if ((i2 & 512) != 0) {
            map2 = MapsKt.emptyMap();
        } else {
            map2 = map;
        }
        responseCookies.append(str, str2, cookieEncoding2, j2, gMTDate2, str5, str6, z3, z4, (Map<String, String>) map2);
    }

    public final void append(String str, String str2, CookieEncoding cookieEncoding, long j, GMTDate gMTDate, String str3, String str4, boolean z, boolean z2, Map<String, String> map) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        Intrinsics.checkNotNullParameter(cookieEncoding, "encoding");
        Map<String, String> map2 = map;
        Intrinsics.checkNotNullParameter(map2, "extensions");
        append(new Cookie(str, str2, cookieEncoding, (int) RangesKt.coerceAtMost(j, 2147483647L), gMTDate, str3, str4, z, z2, map2));
    }

    public static /* synthetic */ void appendExpired$default(ResponseCookies responseCookies, String str, String str2, String str3, int i, Object obj) {
        if ((i & 2) != 0) {
            str2 = null;
        }
        if ((i & 4) != 0) {
            str3 = null;
        }
        responseCookies.appendExpired(str, str2, str3);
    }

    @Deprecated(message = "This method doesn't bypass all flags and extensions so it will be removed in future major release. Please consider using append with expires parameter instead.", replaceWith = @ReplaceWith(expression = "append(name, \"\", CookieEncoding.URI_ENCODING, 0, GMTDate(), domain, path, secure, httpOnly, extensions)", imports = {}))
    public final void appendExpired(String str, String str2, String str3) {
        String str4 = str;
        Intrinsics.checkNotNullParameter(str4, ContentDisposition.Parameters.Name);
        append$default(this, str4, "", (CookieEncoding) null, 0, GMTDate.Companion.getSTART(), str2, str3, false, false, (Map) null, 908, (Object) null);
    }
}
