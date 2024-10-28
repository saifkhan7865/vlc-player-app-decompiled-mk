package io.ktor.server.sessions;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentDisposition;
import io.ktor.http.Cookie;
import io.ktor.server.application.ApplicationCall;
import io.ktor.util.date.DateJvmKt;
import io.ktor.util.date.DateKt;
import io.ktor.util.date.GMTDate;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B#\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\u0010\tJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\r\u0010\u0014\u001a\u00020\u0015H\u0000¢\u0006\u0002\b\u0016J\u0012\u0010\u0017\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0003H\u0016J\b\u0010\u001a\u001a\u00020\u0003H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"Lio/ktor/server/sessions/SessionTransportCookie;", "Lio/ktor/server/sessions/SessionTransport;", "name", "", "configuration", "Lio/ktor/server/sessions/CookieConfiguration;", "transformers", "", "Lio/ktor/server/sessions/SessionTransportTransformer;", "(Ljava/lang/String;Lio/ktor/server/sessions/CookieConfiguration;Ljava/util/List;)V", "getConfiguration", "()Lio/ktor/server/sessions/CookieConfiguration;", "getName", "()Ljava/lang/String;", "getTransformers", "()Ljava/util/List;", "clear", "", "call", "Lio/ktor/server/application/ApplicationCall;", "clearCookie", "Lio/ktor/http/Cookie;", "clearCookie$ktor_server_sessions", "receive", "send", "value", "toString", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransportCookie.kt */
public final class SessionTransportCookie implements SessionTransport {
    private final CookieConfiguration configuration;
    private final String name;
    private final List<SessionTransportTransformer> transformers;

    public SessionTransportCookie(String str, CookieConfiguration cookieConfiguration, List<? extends SessionTransportTransformer> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(cookieConfiguration, "configuration");
        Intrinsics.checkNotNullParameter(list, "transformers");
        this.name = str;
        this.configuration = cookieConfiguration;
        this.transformers = list;
    }

    public final String getName() {
        return this.name;
    }

    public final CookieConfiguration getConfiguration() {
        return this.configuration;
    }

    public final List<SessionTransportTransformer> getTransformers() {
        return this.transformers;
    }

    public String receive(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        return SessionTransportTransformerKt.transformRead(this.transformers, applicationCall.getRequest().getCookies().get(this.name, this.configuration.getEncoding()));
    }

    public void send(ApplicationCall applicationCall, String str) {
        String str2 = str;
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(str2, "value");
        GMTDate gMTDate = null;
        GMTDate GMTDate$default = DateJvmKt.GMTDate$default((Long) null, 1, (Object) null);
        long maxAgeInSeconds = this.configuration.getMaxAgeInSeconds();
        if (maxAgeInSeconds != 0) {
            gMTDate = DateKt.plus(GMTDate$default, 1000 * maxAgeInSeconds);
        }
        applicationCall.getResponse().getCookies().append(new Cookie(this.name, SessionTransportTransformerKt.transformWrite(this.transformers, str2), this.configuration.getEncoding(), (int) RangesKt.coerceAtMost(maxAgeInSeconds, 2147483647L), gMTDate, this.configuration.getDomain(), this.configuration.getPath(), this.configuration.getSecure(), this.configuration.getHttpOnly(), this.configuration.getExtensions()));
    }

    public void clear(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        applicationCall.getResponse().getCookies().append(clearCookie$ktor_server_sessions());
    }

    public final Cookie clearCookie$ktor_server_sessions() {
        return new Cookie(this.name, "", this.configuration.getEncoding(), 0, GMTDate.Companion.getSTART(), this.configuration.getDomain(), this.configuration.getPath(), this.configuration.getSecure(), this.configuration.getHttpOnly(), this.configuration.getExtensions());
    }

    public String toString() {
        return "SessionTransportCookie: " + this.name;
    }
}
