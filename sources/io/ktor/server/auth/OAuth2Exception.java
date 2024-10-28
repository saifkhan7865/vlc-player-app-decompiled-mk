package io.ktor.server.auth;

import io.ktor.util.internal.ExceptionUtilsJvmKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CopyableThrowable;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0005\t\n\u000b\f\rB\u0019\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\u0002\u0010\u0006R\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\b\u0001\u0005\u000e\u000f\u0010\u0011\u0012¨\u0006\u0013"}, d2 = {"Lio/ktor/server/auth/OAuth2Exception;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "errorCode", "(Ljava/lang/String;Ljava/lang/String;)V", "getErrorCode", "()Ljava/lang/String;", "InvalidGrant", "InvalidNonce", "MissingAccessToken", "UnknownException", "UnsupportedGrantType", "Lio/ktor/server/auth/OAuth2Exception$InvalidGrant;", "Lio/ktor/server/auth/OAuth2Exception$InvalidNonce;", "Lio/ktor/server/auth/OAuth2Exception$MissingAccessToken;", "Lio/ktor/server/auth/OAuth2Exception$UnknownException;", "Lio/ktor/server/auth/OAuth2Exception$UnsupportedGrantType;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth2.kt */
public abstract class OAuth2Exception extends Exception {
    private final String errorCode;

    public /* synthetic */ OAuth2Exception(String str, String str2, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, str2);
    }

    private OAuth2Exception(String str, String str2) {
        super(str);
        this.errorCode = str2;
    }

    public final String getErrorCode() {
        return this.errorCode;
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/server/auth/OAuth2Exception$InvalidGrant;", "Lio/ktor/server/auth/OAuth2Exception;", "message", "", "(Ljava/lang/String;)V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuth2.kt */
    public static final class InvalidGrant extends OAuth2Exception {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public InvalidGrant(String str) {
            super(str, "invalid_grant", (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/auth/OAuth2Exception$InvalidNonce;", "Lio/ktor/server/auth/OAuth2Exception;", "()V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuth2.kt */
    public static final class InvalidNonce extends OAuth2Exception {
        public InvalidNonce() {
            super("Nonce verification failed", (String) null, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/auth/OAuth2Exception$MissingAccessToken;", "Lio/ktor/server/auth/OAuth2Exception;", "()V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuth2.kt */
    public static final class MissingAccessToken extends OAuth2Exception {
        public MissingAccessToken() {
            super("OAuth2 server response is OK neither error nor access token provided", (String) null, (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\b\u001a\u00020\u0000H\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\t"}, d2 = {"Lio/ktor/server/auth/OAuth2Exception$UnsupportedGrantType;", "Lio/ktor/server/auth/OAuth2Exception;", "Lkotlinx/coroutines/CopyableThrowable;", "grantType", "", "(Ljava/lang/String;)V", "getGrantType", "()Ljava/lang/String;", "createCopy", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuth2.kt */
    public static final class UnsupportedGrantType extends OAuth2Exception implements CopyableThrowable<UnsupportedGrantType> {
        private final String grantType;

        public final String getGrantType() {
            return this.grantType;
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnsupportedGrantType(String str) {
            super("OAuth2 server doesn't support grant type " + str, "unsupported_grant_type", (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "grantType");
            this.grantType = str;
        }

        public UnsupportedGrantType createCopy() {
            UnsupportedGrantType unsupportedGrantType = new UnsupportedGrantType(this.grantType);
            ExceptionUtilsJvmKt.initCauseBridge(unsupportedGrantType, this);
            return unsupportedGrantType;
        }
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\u0018\u00002\u00020\u00012\b\u0012\u0004\u0012\u00020\u00000\u0002B\u0015\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\u0000H\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lio/ktor/server/auth/OAuth2Exception$UnknownException;", "Lio/ktor/server/auth/OAuth2Exception;", "Lkotlinx/coroutines/CopyableThrowable;", "details", "", "errorCode", "(Ljava/lang/String;Ljava/lang/String;)V", "createCopy", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuth2.kt */
    public static final class UnknownException extends OAuth2Exception implements CopyableThrowable<UnknownException> {
        private final String details;

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnknownException(String str, String str2) {
            super(str + " (error code = " + str2 + ')', str2, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "details");
            Intrinsics.checkNotNullParameter(str2, "errorCode");
            this.details = str;
        }

        public UnknownException createCopy() {
            String str = this.details;
            String errorCode = getErrorCode();
            Intrinsics.checkNotNull(errorCode);
            UnknownException unknownException = new UnknownException(str, errorCode);
            ExceptionUtilsJvmKt.initCauseBridge(unknownException, this);
            return unknownException;
        }
    }
}
