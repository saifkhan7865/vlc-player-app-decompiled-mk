package io.netty.handler.codec.http.cookie;

import io.ktor.http.ContentDisposition;
import io.ktor.util.date.GMTDateParser;
import io.netty.handler.codec.http.cookie.CookieHeaderNames;
import io.netty.util.internal.ObjectUtil;
import org.videolan.vlc.ArtworkProvider;

public class DefaultCookie implements Cookie {
    private String domain;
    private boolean httpOnly;
    private long maxAge = Long.MIN_VALUE;
    private final String name;
    private String path;
    private CookieHeaderNames.SameSite sameSite;
    private boolean secure;
    private String value;
    private boolean wrap;

    public DefaultCookie(String str, String str2) {
        this.name = ObjectUtil.checkNonEmptyAfterTrim(str, ContentDisposition.Parameters.Name);
        setValue(str2);
    }

    public String name() {
        return this.name;
    }

    public String value() {
        return this.value;
    }

    public void setValue(String str) {
        this.value = (String) ObjectUtil.checkNotNull(str, "value");
    }

    public boolean wrap() {
        return this.wrap;
    }

    public void setWrap(boolean z) {
        this.wrap = z;
    }

    public String domain() {
        return this.domain;
    }

    public void setDomain(String str) {
        this.domain = CookieUtil.validateAttributeValue("domain", str);
    }

    public String path() {
        return this.path;
    }

    public void setPath(String str) {
        this.path = CookieUtil.validateAttributeValue(ArtworkProvider.PATH, str);
    }

    public long maxAge() {
        return this.maxAge;
    }

    public void setMaxAge(long j) {
        this.maxAge = j;
    }

    public boolean isSecure() {
        return this.secure;
    }

    public void setSecure(boolean z) {
        this.secure = z;
    }

    public boolean isHttpOnly() {
        return this.httpOnly;
    }

    public void setHttpOnly(boolean z) {
        this.httpOnly = z;
    }

    public CookieHeaderNames.SameSite sameSite() {
        return this.sameSite;
    }

    public void setSameSite(CookieHeaderNames.SameSite sameSite2) {
        this.sameSite = sameSite2;
    }

    public int hashCode() {
        return name().hashCode();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Cookie)) {
            return false;
        }
        Cookie cookie = (Cookie) obj;
        if (!name().equals(cookie.name())) {
            return false;
        }
        if (path() == null) {
            if (cookie.path() != null) {
                return false;
            }
        } else if (cookie.path() == null || !path().equals(cookie.path())) {
            return false;
        }
        if (domain() == null) {
            return cookie.domain() == null;
        }
        return domain().equalsIgnoreCase(cookie.domain());
    }

    public int compareTo(Cookie cookie) {
        int compareTo = name().compareTo(cookie.name());
        if (compareTo != 0) {
            return compareTo;
        }
        if (path() == null) {
            if (cookie.path() != null) {
                return -1;
            }
        } else if (cookie.path() == null) {
            return 1;
        } else {
            int compareTo2 = path().compareTo(cookie.path());
            if (compareTo2 != 0) {
                return compareTo2;
            }
        }
        if (domain() == null) {
            if (cookie.domain() != null) {
                return -1;
            }
            return 0;
        } else if (cookie.domain() == null) {
            return 1;
        } else {
            return domain().compareToIgnoreCase(cookie.domain());
        }
    }

    /* access modifiers changed from: protected */
    @Deprecated
    public String validateValue(String str, String str2) {
        return CookieUtil.validateAttributeValue(str, str2);
    }

    public String toString() {
        StringBuilder stringBuilder = CookieUtil.stringBuilder();
        stringBuilder.append(name());
        stringBuilder.append('=');
        stringBuilder.append(value());
        if (domain() != null) {
            stringBuilder.append(", domain=");
            stringBuilder.append(domain());
        }
        if (path() != null) {
            stringBuilder.append(", path=");
            stringBuilder.append(path());
        }
        if (maxAge() >= 0) {
            stringBuilder.append(", maxAge=");
            stringBuilder.append(maxAge());
            stringBuilder.append(GMTDateParser.SECONDS);
        }
        if (isSecure()) {
            stringBuilder.append(", secure");
        }
        if (isHttpOnly()) {
            stringBuilder.append(", HTTPOnly");
        }
        if (sameSite() != null) {
            stringBuilder.append(", SameSite=");
            stringBuilder.append(sameSite());
        }
        return stringBuilder.toString();
    }
}
