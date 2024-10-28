package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/auth/OAuthCallback;", "", "()V", "TokenPair", "TokenSingle", "Lio/ktor/server/auth/OAuthCallback$TokenPair;", "Lio/ktor/server/auth/OAuthCallback$TokenSingle;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuthCommon.kt */
public abstract class OAuthCallback {
    public /* synthetic */ OAuthCallback(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private OAuthCallback() {
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/ktor/server/auth/OAuthCallback$TokenPair;", "Lio/ktor/server/auth/OAuthCallback;", "token", "", "tokenSecret", "(Ljava/lang/String;Ljava/lang/String;)V", "getToken", "()Ljava/lang/String;", "getTokenSecret", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthCommon.kt */
    public static final class TokenPair extends OAuthCallback {
        private final String token;
        private final String tokenSecret;

        public static /* synthetic */ TokenPair copy$default(TokenPair tokenPair, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tokenPair.token;
            }
            if ((i & 2) != 0) {
                str2 = tokenPair.tokenSecret;
            }
            return tokenPair.copy(str, str2);
        }

        public final String component1() {
            return this.token;
        }

        public final String component2() {
            return this.tokenSecret;
        }

        public final TokenPair copy(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "token");
            Intrinsics.checkNotNullParameter(str2, "tokenSecret");
            return new TokenPair(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TokenPair)) {
                return false;
            }
            TokenPair tokenPair = (TokenPair) obj;
            return Intrinsics.areEqual((Object) this.token, (Object) tokenPair.token) && Intrinsics.areEqual((Object) this.tokenSecret, (Object) tokenPair.tokenSecret);
        }

        public int hashCode() {
            return (this.token.hashCode() * 31) + this.tokenSecret.hashCode();
        }

        public String toString() {
            return "TokenPair(token=" + this.token + ", tokenSecret=" + this.tokenSecret + ')';
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TokenPair(String str, String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "token");
            Intrinsics.checkNotNullParameter(str2, "tokenSecret");
            this.token = str;
            this.tokenSecret = str2;
        }

        public final String getToken() {
            return this.token;
        }

        public final String getTokenSecret() {
            return this.tokenSecret;
        }
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005J\t\u0010\t\u001a\u00020\u0003HÆ\u0003J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u001d\u0010\u000b\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fHÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\u0013"}, d2 = {"Lio/ktor/server/auth/OAuthCallback$TokenSingle;", "Lio/ktor/server/auth/OAuthCallback;", "token", "", "state", "(Ljava/lang/String;Ljava/lang/String;)V", "getState", "()Ljava/lang/String;", "getToken", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthCommon.kt */
    public static final class TokenSingle extends OAuthCallback {
        private final String state;
        private final String token;

        public static /* synthetic */ TokenSingle copy$default(TokenSingle tokenSingle, String str, String str2, int i, Object obj) {
            if ((i & 1) != 0) {
                str = tokenSingle.token;
            }
            if ((i & 2) != 0) {
                str2 = tokenSingle.state;
            }
            return tokenSingle.copy(str, str2);
        }

        public final String component1() {
            return this.token;
        }

        public final String component2() {
            return this.state;
        }

        public final TokenSingle copy(String str, String str2) {
            Intrinsics.checkNotNullParameter(str, "token");
            Intrinsics.checkNotNullParameter(str2, OAuth2RequestParameters.State);
            return new TokenSingle(str, str2);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof TokenSingle)) {
                return false;
            }
            TokenSingle tokenSingle = (TokenSingle) obj;
            return Intrinsics.areEqual((Object) this.token, (Object) tokenSingle.token) && Intrinsics.areEqual((Object) this.state, (Object) tokenSingle.state);
        }

        public int hashCode() {
            return (this.token.hashCode() * 31) + this.state.hashCode();
        }

        public String toString() {
            return "TokenSingle(token=" + this.token + ", state=" + this.state + ')';
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public TokenSingle(String str, String str2) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "token");
            Intrinsics.checkNotNullParameter(str2, OAuth2RequestParameters.State);
            this.token = str;
            this.state = str2;
        }

        public final String getState() {
            return this.state;
        }

        public final String getToken() {
            return this.token;
        }
    }
}
