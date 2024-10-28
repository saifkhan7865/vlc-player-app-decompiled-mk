package io.ktor.server.sessions;

import io.ktor.http.CookieEncoding;
import java.util.LinkedHashMap;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\t\n\u0002\b\f\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002R\u001c\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0005\u0010\u0006\"\u0004\b\u0007\u0010\bR\u001a\u0010\t\u001a\u00020\nX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR\u001f\u0010\u000f\u001a\u0010\u0012\u0004\u0012\u00020\u0004\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0010¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u001a\u0010\u0013\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\u0016\"\u0004\b\u0017\u0010\u0018R$\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0019\u001a\u00020\u001a@FX\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u001c\u0010 \u001a\u0004\u0018\u00010\u0004X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b!\u0010\u0006\"\u0004\b\"\u0010\bR\u001a\u0010#\u001a\u00020\u0014X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b$\u0010\u0016\"\u0004\b%\u0010\u0018¨\u0006&"}, d2 = {"Lio/ktor/server/sessions/CookieConfiguration;", "", "()V", "domain", "", "getDomain", "()Ljava/lang/String;", "setDomain", "(Ljava/lang/String;)V", "encoding", "Lio/ktor/http/CookieEncoding;", "getEncoding", "()Lio/ktor/http/CookieEncoding;", "setEncoding", "(Lio/ktor/http/CookieEncoding;)V", "extensions", "", "getExtensions", "()Ljava/util/Map;", "httpOnly", "", "getHttpOnly", "()Z", "setHttpOnly", "(Z)V", "newMaxAge", "", "maxAgeInSeconds", "getMaxAgeInSeconds", "()J", "setMaxAgeInSeconds", "(J)V", "path", "getPath", "setPath", "secure", "getSecure", "setSecure", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransportCookie.kt */
public final class CookieConfiguration {
    private String domain;
    private CookieEncoding encoding = CookieEncoding.URI_ENCODING;
    private final Map<String, String> extensions = new LinkedHashMap();
    private boolean httpOnly = true;
    private long maxAgeInSeconds = SessionTransportCookieKt.DEFAULT_SESSION_MAX_AGE;
    private String path = "/";
    private boolean secure;

    public final long getMaxAgeInSeconds() {
        return this.maxAgeInSeconds;
    }

    public final void setMaxAgeInSeconds(long j) {
        if (j >= 0) {
            this.maxAgeInSeconds = j;
            return;
        }
        throw new IllegalArgumentException(("maxAgeInSeconds shouldn't be negative: " + j).toString());
    }

    public final CookieEncoding getEncoding() {
        return this.encoding;
    }

    public final void setEncoding(CookieEncoding cookieEncoding) {
        Intrinsics.checkNotNullParameter(cookieEncoding, "<set-?>");
        this.encoding = cookieEncoding;
    }

    public final String getDomain() {
        return this.domain;
    }

    public final void setDomain(String str) {
        this.domain = str;
    }

    public final String getPath() {
        return this.path;
    }

    public final void setPath(String str) {
        this.path = str;
    }

    public final boolean getSecure() {
        return this.secure;
    }

    public final void setSecure(boolean z) {
        this.secure = z;
    }

    public final boolean getHttpOnly() {
        return this.httpOnly;
    }

    public final void setHttpOnly(boolean z) {
        this.httpOnly = z;
    }

    public final Map<String, String> getExtensions() {
        return this.extensions;
    }
}
