package io.ktor.server.auth;

import io.ktor.http.Headers;
import io.ktor.http.HttpHeaders;
import io.ktor.http.auth.HttpAuthHeader;
import io.ktor.http.auth.HttpAuthHeaderKt;
import io.ktor.http.parsing.ParseException;
import io.ktor.server.plugins.BadRequestException;
import io.ktor.server.request.ApplicationRequest;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a\u000e\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H\u0000\u001a\f\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0003¨\u0006\u0004"}, d2 = {"parseAuthorizationHeader", "Lio/ktor/http/auth/HttpAuthHeader;", "Lio/ktor/http/Headers;", "Lio/ktor/server/request/ApplicationRequest;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Headers.kt */
public final class HeadersKt {
    public static final HttpAuthHeader parseAuthorizationHeader(ApplicationRequest applicationRequest) {
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        return parseAuthorizationHeader(applicationRequest.getHeaders());
    }

    public static final HttpAuthHeader parseAuthorizationHeader(Headers headers) {
        Intrinsics.checkNotNullParameter(headers, "<this>");
        String str = headers.get(HttpHeaders.INSTANCE.getAuthorization());
        if (str == null) {
            return null;
        }
        try {
            return HttpAuthHeaderKt.parseAuthorizationHeader(str);
        } catch (ParseException e) {
            throw new BadRequestException("Invalid auth header", e);
        }
    }
}
