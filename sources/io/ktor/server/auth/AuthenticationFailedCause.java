package io.ktor.server.auth;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00020\u0001:\u0003\u0003\u0004\u0005B\u0007\b\u0004¢\u0006\u0002\u0010\u0002\u0001\u0003\u0006\u0007\b¨\u0006\t"}, d2 = {"Lio/ktor/server/auth/AuthenticationFailedCause;", "", "()V", "Error", "InvalidCredentials", "NoCredentials", "Lio/ktor/server/auth/AuthenticationFailedCause$Error;", "Lio/ktor/server/auth/AuthenticationFailedCause$InvalidCredentials;", "Lio/ktor/server/auth/AuthenticationFailedCause$NoCredentials;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AuthenticationFailedCause.kt */
public abstract class AuthenticationFailedCause {
    public /* synthetic */ AuthenticationFailedCause(DefaultConstructorMarker defaultConstructorMarker) {
        this();
    }

    private AuthenticationFailedCause() {
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/auth/AuthenticationFailedCause$NoCredentials;", "Lio/ktor/server/auth/AuthenticationFailedCause;", "()V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AuthenticationFailedCause.kt */
    public static final class NoCredentials extends AuthenticationFailedCause {
        public static final NoCredentials INSTANCE = new NoCredentials();

        private NoCredentials() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/auth/AuthenticationFailedCause$InvalidCredentials;", "Lio/ktor/server/auth/AuthenticationFailedCause;", "()V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AuthenticationFailedCause.kt */
    public static final class InvalidCredentials extends AuthenticationFailedCause {
        public static final InvalidCredentials INSTANCE = new InvalidCredentials();

        private InvalidCredentials() {
            super((DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\b\u0016\u0018\u00002\u00020\u0001B#\b\u0017\u0012\u0012\u0010\u0002\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00040\u0003\"\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007B\r\u0012\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0002\u0010\tR\u001a\u0010\u0005\u001a\u00020\u00068FX\u0004¢\u0006\f\u0012\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0011\u0010\b\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\r¨\u0006\u000f"}, d2 = {"Lio/ktor/server/auth/AuthenticationFailedCause$Error;", "Lio/ktor/server/auth/AuthenticationFailedCause;", "placeholder", "", "", "cause", "", "([Lkotlin/Unit;Ljava/lang/String;)V", "message", "(Ljava/lang/String;)V", "getCause$annotations", "()V", "getCause", "()Ljava/lang/String;", "getMessage", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: AuthenticationFailedCause.kt */
    public static class Error extends AuthenticationFailedCause {
        private final String message;

        @Deprecated(level = DeprecationLevel.ERROR, message = "Use message instead.", replaceWith = @ReplaceWith(expression = "message", imports = {}))
        public static /* synthetic */ void getCause$annotations() {
        }

        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public Error(String str) {
            super((DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
            this.message = str;
        }

        public final String getMessage() {
            return this.message;
        }

        /* JADX INFO: this call moved to the top of the method (can break code semantics) */
        @Deprecated(level = DeprecationLevel.ERROR, message = "Use message instead of cause.")
        public Error(Unit[] unitArr, String str) {
            this(str);
            Intrinsics.checkNotNullParameter(unitArr, "placeholder");
            Intrinsics.checkNotNullParameter(str, "cause");
        }

        public final String getCause() {
            return this.message;
        }
    }
}
