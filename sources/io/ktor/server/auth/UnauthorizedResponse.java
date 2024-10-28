package io.ktor.server.auth;

import io.ktor.http.Headers;
import io.ktor.http.HeadersKt;
import io.ktor.http.HttpHeaders;
import io.ktor.http.HttpStatusCode;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.content.OutgoingContent;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0019\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004¢\u0006\u0002\u0010\u0005R\u001b\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003¢\u0006\n\n\u0002\u0010\b\u001a\u0004\b\u0006\u0010\u0007R\u0014\u0010\t\u001a\u00020\n8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u0016\u0010\r\u001a\u0004\u0018\u00010\u000e8VX\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0011"}, d2 = {"Lio/ktor/server/auth/UnauthorizedResponse;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "challenges", "", "Lio/ktor/http/auth/HttpAuthHeader;", "([Lio/ktor/http/auth/HttpAuthHeader;)V", "getChallenges", "()[Lio/ktor/http/auth/HttpAuthHeader;", "[Lio/ktor/http/auth/HttpAuthHeader;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: UnauthorizedResponse.kt */
public final class UnauthorizedResponse extends OutgoingContent.NoContent {
    private final HttpAuthHeader[] challenges;

    public UnauthorizedResponse(HttpAuthHeader... httpAuthHeaderArr) {
        Intrinsics.checkNotNullParameter(httpAuthHeaderArr, "challenges");
        this.challenges = httpAuthHeaderArr;
    }

    public final HttpAuthHeader[] getChallenges() {
        return this.challenges;
    }

    public HttpStatusCode getStatus() {
        return HttpStatusCode.Companion.getUnauthorized();
    }

    public Headers getHeaders() {
        if (!(this.challenges.length == 0)) {
            return HeadersKt.headersOf(HttpHeaders.INSTANCE.getWWWAuthenticate(), ArraysKt.joinToString$default((Object[]) this.challenges, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) UnauthorizedResponse$headers$1.INSTANCE, 30, (Object) null));
        }
        return Headers.Companion.getEmpty();
    }
}
