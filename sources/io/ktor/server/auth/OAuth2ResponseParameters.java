package io.ktor.server.auth;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lio/ktor/server/auth/OAuth2ResponseParameters;", "", "()V", "AccessToken", "", "Error", "ErrorDescription", "ExpiresIn", "RefreshToken", "TokenType", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth2.kt */
public final class OAuth2ResponseParameters {
    public static final String AccessToken = "access_token";
    public static final String Error = "error";
    public static final String ErrorDescription = "error_description";
    public static final String ExpiresIn = "expires_in";
    public static final OAuth2ResponseParameters INSTANCE = new OAuth2ResponseParameters();
    public static final String RefreshToken = "refresh_token";
    public static final String TokenType = "token_type";

    private OAuth2ResponseParameters() {
    }
}
