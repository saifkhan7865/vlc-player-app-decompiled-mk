package io.ktor.server.auth;

import kotlin.Metadata;

@Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Lio/ktor/server/auth/OAuthGrantTypes;", "", "()V", "AuthorizationCode", "", "Password", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuthCommon.kt */
public final class OAuthGrantTypes {
    public static final String AuthorizationCode = "authorization_code";
    public static final OAuthGrantTypes INSTANCE = new OAuthGrantTypes();
    public static final String Password = "password";

    private OAuthGrantTypes() {
    }
}
