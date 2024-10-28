package io.ktor.server.sessions;

import androidx.core.app.NotificationCompat;
import io.ktor.http.ContentDisposition;
import io.ktor.http.HttpHeaders;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponsePropertiesKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0002\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010\u0011\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0003H\u0016J\b\u0010\u0013\u001a\u00020\u0003H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0014"}, d2 = {"Lio/ktor/server/sessions/SessionTransportHeader;", "Lio/ktor/server/sessions/SessionTransport;", "name", "", "transformers", "", "Lio/ktor/server/sessions/SessionTransportTransformer;", "(Ljava/lang/String;Ljava/util/List;)V", "getName", "()Ljava/lang/String;", "getTransformers", "()Ljava/util/List;", "clear", "", "call", "Lio/ktor/server/application/ApplicationCall;", "receive", "send", "value", "toString", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: SessionTransportHeader.kt */
public final class SessionTransportHeader implements SessionTransport {
    private final String name;
    private final List<SessionTransportTransformer> transformers;

    public void clear(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
    }

    public SessionTransportHeader(String str, List<? extends SessionTransportTransformer> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(list, "transformers");
        this.name = str;
        this.transformers = list;
        HttpHeaders.INSTANCE.checkHeaderName(str);
    }

    public final String getName() {
        return this.name;
    }

    public final List<SessionTransportTransformer> getTransformers() {
        return this.transformers;
    }

    public String receive(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        return SessionTransportTransformerKt.transformRead(this.transformers, applicationCall.getRequest().getHeaders().get(this.name));
    }

    public void send(ApplicationCall applicationCall, String str) {
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        Intrinsics.checkNotNullParameter(str, "value");
        ApplicationResponsePropertiesKt.header(applicationCall.getResponse(), this.name, SessionTransportTransformerKt.transformWrite(this.transformers, str));
    }

    public String toString() {
        return "SessionTransportHeader: " + this.name;
    }
}
