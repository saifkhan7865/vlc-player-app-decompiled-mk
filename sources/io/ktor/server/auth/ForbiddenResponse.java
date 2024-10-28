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

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u0004B\u0019\u0012\u0012\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006\"\u00020\u0003¢\u0006\u0002\u0010\u0007R\u001b\u0010\u0005\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00030\u0006¢\u0006\n\n\u0002\u0010\n\u001a\u0004\b\b\u0010\tR\u0014\u0010\u000b\u001a\u00020\f8VX\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u000f\u001a\u00020\u00108VX\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/server/auth/ForbiddenResponse;", "Lio/ktor/http/content/OutgoingContent$NoContent;", "challenge", "Lio/ktor/http/auth/HttpAuthHeader;", "(Lio/ktor/http/auth/HttpAuthHeader;)V", "challenges", "", "([Lio/ktor/http/auth/HttpAuthHeader;)V", "getChallenges", "()[Lio/ktor/http/auth/HttpAuthHeader;", "[Lio/ktor/http/auth/HttpAuthHeader;", "headers", "Lio/ktor/http/Headers;", "getHeaders", "()Lio/ktor/http/Headers;", "status", "Lio/ktor/http/HttpStatusCode;", "getStatus", "()Lio/ktor/http/HttpStatusCode;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ForbiddenResponse.kt */
public final class ForbiddenResponse extends OutgoingContent.NoContent {
    private final HttpAuthHeader[] challenges;

    public ForbiddenResponse(HttpAuthHeader... httpAuthHeaderArr) {
        Intrinsics.checkNotNullParameter(httpAuthHeaderArr, "challenges");
        this.challenges = httpAuthHeaderArr;
    }

    public final HttpAuthHeader[] getChallenges() {
        return this.challenges;
    }

    public HttpStatusCode getStatus() {
        return HttpStatusCode.Companion.getForbidden();
    }

    public Headers getHeaders() {
        if (!(this.challenges.length == 0)) {
            return HeadersKt.headersOf(HttpHeaders.INSTANCE.getWWWAuthenticate(), ArraysKt.joinToString$default((Object[]) this.challenges, (CharSequence) ", ", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) ForbiddenResponse$headers$1.INSTANCE, 30, (Object) null));
        }
        return Headers.Companion.getEmpty();
    }

    /* JADX WARNING: Illegal instructions before constructor call */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public ForbiddenResponse(io.ktor.http.auth.HttpAuthHeader r3) {
        /*
            r2 = this;
            r0 = 0
            if (r3 != 0) goto L_0x0006
            io.ktor.http.auth.HttpAuthHeader[] r3 = new io.ktor.http.auth.HttpAuthHeader[r0]
            goto L_0x000c
        L_0x0006:
            r1 = 1
            io.ktor.http.auth.HttpAuthHeader[] r1 = new io.ktor.http.auth.HttpAuthHeader[r1]
            r1[r0] = r3
            r3 = r1
        L_0x000c:
            int r0 = r3.length
            java.lang.Object[] r3 = java.util.Arrays.copyOf(r3, r0)
            io.ktor.http.auth.HttpAuthHeader[] r3 = (io.ktor.http.auth.HttpAuthHeader[]) r3
            r2.<init>((io.ktor.http.auth.HttpAuthHeader[]) r3)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.ForbiddenResponse.<init>(io.ktor.http.auth.HttpAuthHeader):void");
    }
}
