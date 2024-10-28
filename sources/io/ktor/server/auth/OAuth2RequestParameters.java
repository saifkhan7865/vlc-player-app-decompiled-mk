package io.ktor.server.auth;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lio/ktor/server/auth/OAuth2RequestParameters;", "", "()V", "ClientId", "", "ClientSecret", "Code", "GrantType", "Password", "RedirectUri", "ResponseType", "Scope", "State", "UserName", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth2.kt */
public final class OAuth2RequestParameters {
    public static final String ClientId = "client_id";
    public static final String ClientSecret = "client_secret";
    public static final String Code = "code";
    public static final String GrantType = "grant_type";
    public static final OAuth2RequestParameters INSTANCE = new OAuth2RequestParameters();
    public static final String Password = "password";
    public static final String RedirectUri = "redirect_uri";
    public static final String ResponseType = "response_type";
    public static final String Scope = "scope";
    public static final String State = "state";
    public static final String UserName = "username";

    private OAuth2RequestParameters() {
    }
}
