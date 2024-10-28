package io.ktor.server.auth;

import io.ktor.http.Parameters;
import kotlin.Metadata;
import kotlin.UInt$$ExternalSyntheticBackport0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0002\u0003\u0004B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0002\u0005\u0006¨\u0006\u0007"}, d2 = {"Lio/ktor/server/auth/OAuthAccessTokenResponse;", "Lio/ktor/server/auth/Principal;", "()V", "OAuth1a", "OAuth2", "Lio/ktor/server/auth/OAuthAccessTokenResponse$OAuth1a;", "Lio/ktor/server/auth/OAuthAccessTokenResponse$OAuth2;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuthCommon.kt */
public abstract class OAuthAccessTokenResponse implements Principal {
    public /* synthetic */ OAuthAccessTokenResponse(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private OAuthAccessTokenResponse() {
    }

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0003J\t\u0010\u0015\u001a\u00020\u0016HÖ\u0001J\t\u0010\u0017\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0018"}, d2 = {"Lio/ktor/server/auth/OAuthAccessTokenResponse$OAuth1a;", "Lio/ktor/server/auth/OAuthAccessTokenResponse;", "token", "", "tokenSecret", "extraParameters", "Lio/ktor/http/Parameters;", "(Ljava/lang/String;Ljava/lang/String;Lio/ktor/http/Parameters;)V", "getExtraParameters", "()Lio/ktor/http/Parameters;", "getToken", "()Ljava/lang/String;", "getTokenSecret", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthCommon.kt */
    public static final class OAuth1a extends OAuthAccessTokenResponse {
        private final Parameters extraParameters;
        private final String token;
        private final String tokenSecret;

        public static /* synthetic */ OAuth1a copy$default(OAuth1a oAuth1a, String str, String str2, Parameters parameters, int i, Object obj) {
            if ((i & 1) != 0) {
                str = oAuth1a.token;
            }
            if ((i & 2) != 0) {
                str2 = oAuth1a.tokenSecret;
            }
            if ((i & 4) != 0) {
                parameters = oAuth1a.extraParameters;
            }
            return oAuth1a.copy(str, str2, parameters);
        }

        public final String component1() {
            return this.token;
        }

        public final String component2() {
            return this.tokenSecret;
        }

        public final Parameters component3() {
            return this.extraParameters;
        }

        public final OAuth1a copy(String str, String str2, Parameters parameters) {
            Intrinsics.checkNotNullParameter(str, "token");
            Intrinsics.checkNotNullParameter(str2, "tokenSecret");
            Intrinsics.checkNotNullParameter(parameters, "extraParameters");
            return new OAuth1a(str, str2, parameters);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OAuth1a)) {
                return false;
            }
            OAuth1a oAuth1a = (OAuth1a) obj;
            return Intrinsics.areEqual((Object) this.token, (Object) oAuth1a.token) && Intrinsics.areEqual((Object) this.tokenSecret, (Object) oAuth1a.tokenSecret) && Intrinsics.areEqual((Object) this.extraParameters, (Object) oAuth1a.extraParameters);
        }

        public int hashCode() {
            return (((this.token.hashCode() * 31) + this.tokenSecret.hashCode()) * 31) + this.extraParameters.hashCode();
        }

        public String toString() {
            return "OAuth1a(token=" + this.token + ", tokenSecret=" + this.tokenSecret + ", extraParameters=" + this.extraParameters + ')';
        }

        public final String getToken() {
            return this.token;
        }

        public final String getTokenSecret() {
            return this.tokenSecret;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ OAuth1a(String str, String str2, Parameters parameters, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, (i & 4) != 0 ? Parameters.Companion.getEmpty() : parameters);
        }

        public final Parameters getExtraParameters() {
            return this.extraParameters;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OAuth1a(String str, String str2, Parameters parameters) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "token");
            Intrinsics.checkNotNullParameter(str2, "tokenSecret");
            Intrinsics.checkNotNullParameter(parameters, "extraParameters");
            this.token = str;
            this.tokenSecret = str2;
            this.extraParameters = parameters;
        }
    }

    @Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B?\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u0003¢\u0006\u0002\u0010\u000bB1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\u0003\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\fJ\t\u0010\u0017\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0018\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0019\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u001a\u001a\u0004\u0018\u00010\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\tHÆ\u0003J=\u0010\u001c\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u00032\b\b\u0002\u0010\b\u001a\u00020\tHÆ\u0001J\u0013\u0010\u001d\u001a\u00020\u001e2\b\u0010\u001f\u001a\u0004\u0018\u00010 HÖ\u0003J\t\u0010!\u001a\u00020\"HÖ\u0001J\t\u0010#\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0007\u001a\u0004\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u000eR\"\u0010\n\u001a\u0004\u0018\u00010\u00032\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003@BX\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u000eR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u000e¨\u0006$"}, d2 = {"Lio/ktor/server/auth/OAuthAccessTokenResponse$OAuth2;", "Lio/ktor/server/auth/OAuthAccessTokenResponse;", "accessToken", "", "tokenType", "expiresIn", "", "refreshToken", "extraParameters", "Lio/ktor/http/Parameters;", "state", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lio/ktor/http/Parameters;Ljava/lang/String;)V", "(Ljava/lang/String;Ljava/lang/String;JLjava/lang/String;Lio/ktor/http/Parameters;)V", "getAccessToken", "()Ljava/lang/String;", "getExpiresIn", "()J", "getExtraParameters", "()Lio/ktor/http/Parameters;", "getRefreshToken", "<set-?>", "getState", "getTokenType", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "", "hashCode", "", "toString", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuthCommon.kt */
    public static final class OAuth2 extends OAuthAccessTokenResponse {
        private final String accessToken;
        private final long expiresIn;
        private final Parameters extraParameters;
        private final String refreshToken;
        private String state;
        private final String tokenType;

        public static /* synthetic */ OAuth2 copy$default(OAuth2 oAuth2, String str, String str2, long j, String str3, Parameters parameters, int i, Object obj) {
            if ((i & 1) != 0) {
                str = oAuth2.accessToken;
            }
            if ((i & 2) != 0) {
                str2 = oAuth2.tokenType;
            }
            String str4 = str2;
            if ((i & 4) != 0) {
                j = oAuth2.expiresIn;
            }
            long j2 = j;
            if ((i & 8) != 0) {
                str3 = oAuth2.refreshToken;
            }
            String str5 = str3;
            if ((i & 16) != 0) {
                parameters = oAuth2.extraParameters;
            }
            return oAuth2.copy(str, str4, j2, str5, parameters);
        }

        public final String component1() {
            return this.accessToken;
        }

        public final String component2() {
            return this.tokenType;
        }

        public final long component3() {
            return this.expiresIn;
        }

        public final String component4() {
            return this.refreshToken;
        }

        public final Parameters component5() {
            return this.extraParameters;
        }

        public final OAuth2 copy(String str, String str2, long j, String str3, Parameters parameters) {
            Intrinsics.checkNotNullParameter(str, "accessToken");
            Intrinsics.checkNotNullParameter(str2, "tokenType");
            Intrinsics.checkNotNullParameter(parameters, "extraParameters");
            return new OAuth2(str, str2, j, str3, parameters);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof OAuth2)) {
                return false;
            }
            OAuth2 oAuth2 = (OAuth2) obj;
            return Intrinsics.areEqual((Object) this.accessToken, (Object) oAuth2.accessToken) && Intrinsics.areEqual((Object) this.tokenType, (Object) oAuth2.tokenType) && this.expiresIn == oAuth2.expiresIn && Intrinsics.areEqual((Object) this.refreshToken, (Object) oAuth2.refreshToken) && Intrinsics.areEqual((Object) this.extraParameters, (Object) oAuth2.extraParameters);
        }

        public int hashCode() {
            int hashCode = ((((this.accessToken.hashCode() * 31) + this.tokenType.hashCode()) * 31) + UInt$$ExternalSyntheticBackport0.m(this.expiresIn)) * 31;
            String str = this.refreshToken;
            return ((hashCode + (str == null ? 0 : str.hashCode())) * 31) + this.extraParameters.hashCode();
        }

        public String toString() {
            return "OAuth2(accessToken=" + this.accessToken + ", tokenType=" + this.tokenType + ", expiresIn=" + this.expiresIn + ", refreshToken=" + this.refreshToken + ", extraParameters=" + this.extraParameters + ')';
        }

        public final String getAccessToken() {
            return this.accessToken;
        }

        public final String getTokenType() {
            return this.tokenType;
        }

        public final long getExpiresIn() {
            return this.expiresIn;
        }

        public final String getRefreshToken() {
            return this.refreshToken;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ OAuth2(String str, String str2, long j, String str3, Parameters parameters, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, j, str3, (i & 16) != 0 ? Parameters.Companion.getEmpty() : parameters);
        }

        public final Parameters getExtraParameters() {
            return this.extraParameters;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public OAuth2(String str, String str2, long j, String str3, Parameters parameters) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "accessToken");
            Intrinsics.checkNotNullParameter(str2, "tokenType");
            Intrinsics.checkNotNullParameter(parameters, "extraParameters");
            this.accessToken = str;
            this.tokenType = str2;
            this.expiresIn = j;
            this.refreshToken = str3;
            this.extraParameters = parameters;
        }

        public final String getState() {
            return this.state;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public /* synthetic */ OAuth2(String str, String str2, long j, String str3, Parameters parameters, String str4, int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(str, str2, j, str3, (i & 16) != 0 ? Parameters.Companion.getEmpty() : parameters, (i & 32) != 0 ? null : str4);
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        public OAuth2(String str, String str2, long j, String str3, Parameters parameters, String str4) {
            this(str, str2, j, str3, parameters);
            Intrinsics.checkNotNullParameter(str, "accessToken");
            Intrinsics.checkNotNullParameter(str2, "tokenType");
            Intrinsics.checkNotNullParameter(parameters, "extraParameters");
            this.state = str4;
        }
    }
}
