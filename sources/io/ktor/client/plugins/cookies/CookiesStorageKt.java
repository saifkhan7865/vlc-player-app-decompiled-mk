package io.ktor.client.plugins.cookies;

import io.ktor.http.Cookie;
import io.ktor.http.CookieEncoding;
import io.ktor.http.URLUtilsKt;
import io.ktor.http.Url;
import io.ktor.util.date.GMTDate;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u0007\u001a\u0014\u0010\b\u001a\u00020\u0006*\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0000\u001a\u0014\u0010\u000b\u001a\u00020\f*\u00020\u00062\u0006\u0010\t\u001a\u00020\nH\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\r"}, d2 = {"addCookie", "", "Lio/ktor/client/plugins/cookies/CookiesStorage;", "urlString", "", "cookie", "Lio/ktor/http/Cookie;", "(Lio/ktor/client/plugins/cookies/CookiesStorage;Ljava/lang/String;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "fillDefaults", "requestUrl", "Lio/ktor/http/Url;", "matches", "", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CookiesStorage.kt */
public final class CookiesStorageKt {
    public static final Object addCookie(CookiesStorage cookiesStorage, String str, Cookie cookie, Continuation<? super Unit> continuation) {
        Object addCookie = cookiesStorage.addCookie(URLUtilsKt.Url(str), cookie, continuation);
        return addCookie == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? addCookie : Unit.INSTANCE;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0091, code lost:
        if (kotlin.text.StringsKt.endsWith$default(r3, "." + r0, false, 2, (java.lang.Object) null) == false) goto L_0x0093;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final boolean matches(io.ktor.http.Cookie r10, io.ktor.http.Url r11) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r10, r0)
            java.lang.String r0 = "requestUrl"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r11, r0)
            java.lang.String r0 = r10.getDomain()
            if (r0 == 0) goto L_0x00c8
            java.lang.String r0 = io.ktor.util.TextKt.toLowerCasePreservingASCIIRules(r0)
            if (r0 == 0) goto L_0x00c8
            r1 = 1
            char[] r2 = new char[r1]
            r3 = 46
            r4 = 0
            r2[r4] = r3
            java.lang.String r0 = kotlin.text.StringsKt.trimStart((java.lang.String) r0, (char[]) r2)
            if (r0 == 0) goto L_0x00c8
            r10.getPath()
            java.lang.String r2 = r10.getPath()
            if (r2 == 0) goto L_0x00bc
            r3 = r2
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r5 = 47
            r6 = 2
            r7 = 0
            boolean r3 = kotlin.text.StringsKt.endsWith$default((java.lang.CharSequence) r3, (char) r5, (boolean) r4, (int) r6, (java.lang.Object) r7)
            if (r3 == 0) goto L_0x003b
            goto L_0x004e
        L_0x003b:
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = r10.getPath()
            r2.append(r3)
            r2.append(r5)
            java.lang.String r2 = r2.toString()
        L_0x004e:
            java.lang.String r3 = r11.getHost()
            java.lang.String r3 = io.ktor.util.TextKt.toLowerCasePreservingASCIIRules(r3)
            java.lang.String r8 = r11.getEncodedPath()
            r9 = r8
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            boolean r9 = kotlin.text.StringsKt.endsWith$default((java.lang.CharSequence) r9, (char) r5, (boolean) r4, (int) r6, (java.lang.Object) r7)
            if (r9 == 0) goto L_0x0064
            goto L_0x0073
        L_0x0064:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            r9.<init>()
            r9.append(r8)
            r9.append(r5)
            java.lang.String r8 = r9.toString()
        L_0x0073:
            boolean r5 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r0)
            if (r5 != 0) goto L_0x0094
            boolean r5 = io.ktor.http.IpParserKt.hostIsIp(r3)
            if (r5 != 0) goto L_0x0093
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r9 = "."
            r5.<init>(r9)
            r5.append(r0)
            java.lang.String r0 = r5.toString()
            boolean r0 = kotlin.text.StringsKt.endsWith$default(r3, r0, r4, r6, r7)
            if (r0 != 0) goto L_0x0094
        L_0x0093:
            return r4
        L_0x0094:
            java.lang.String r0 = "/"
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r0)
            if (r0 != 0) goto L_0x00a9
            boolean r0 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r8, (java.lang.Object) r2)
            if (r0 != 0) goto L_0x00a9
            boolean r0 = kotlin.text.StringsKt.startsWith$default(r8, r2, r4, r6, r7)
            if (r0 != 0) goto L_0x00a9
            return r4
        L_0x00a9:
            boolean r10 = r10.getSecure()
            if (r10 == 0) goto L_0x00bb
            io.ktor.http.URLProtocol r10 = r11.getProtocol()
            boolean r10 = io.ktor.http.URLProtocolKt.isSecure(r10)
            if (r10 == 0) goto L_0x00ba
            goto L_0x00bb
        L_0x00ba:
            r1 = 0
        L_0x00bb:
            return r1
        L_0x00bc:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Path field should have the default value"
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        L_0x00c8:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "Domain field should have the default value"
            java.lang.String r11 = r11.toString()
            r10.<init>(r11)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.CookiesStorageKt.matches(io.ktor.http.Cookie, io.ktor.http.Url):boolean");
    }

    public static final Cookie fillDefaults(Cookie cookie, Url url) {
        Cookie cookie2 = cookie;
        Intrinsics.checkNotNullParameter(cookie, "<this>");
        Intrinsics.checkNotNullParameter(url, "requestUrl");
        String path = cookie.getPath();
        if (path == null || !StringsKt.startsWith$default(path, "/", false, 2, (Object) null)) {
            cookie2 = Cookie.copy$default(cookie, (String) null, (String) null, (CookieEncoding) null, 0, (GMTDate) null, (String) null, url.getEncodedPath(), false, false, (Map) null, 959, (Object) null);
        }
        CharSequence domain = cookie2.getDomain();
        return (domain == null || StringsKt.isBlank(domain)) ? Cookie.copy$default(cookie2, (String) null, (String) null, (CookieEncoding) null, 0, (GMTDate) null, url.getHost(), (String) null, false, false, (Map) null, 991, (Object) null) : cookie2;
    }
}
