package io.ktor.server.auth;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u00002\u00060\u0001j\u0002`\u0002:\u0002\u0006\u0007B\u000f\b\u0004\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005\u0001\u0002\b\t¨\u0006\n"}, d2 = {"Lio/ktor/server/auth/OAuth1aException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "MissingTokenException", "UnknownException", "Lio/ktor/server/auth/OAuth1aException$MissingTokenException;", "Lio/ktor/server/auth/OAuth1aException$UnknownException;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: OAuth1a.kt */
public abstract class OAuth1aException extends Exception {
    public /* synthetic */ OAuth1aException(String str, DefaultConstructorMarker defaultConstructorMarker) {
        this(str);
    }

    private OAuth1aException(String str) {
        super(str);
    }

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002¨\u0006\u0003"}, d2 = {"Lio/ktor/server/auth/OAuth1aException$MissingTokenException;", "Lio/ktor/server/auth/OAuth1aException;", "()V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: OAuth1a.kt */
    public static final class MissingTokenException extends OAuth1aException {
        public MissingTokenException() {
            super("The OAuth1a server didn't provide access token", (DefaultConstructorMarker) null);
        }
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/server/auth/OAuth1aException$UnknownException;", "Lio/ktor/server/auth/OAuth1aException;", "message", "", "(Ljava/lang/String;)V", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
    @Deprecated(level = DeprecationLevel.ERROR, message = "This is no longer thrown.")
    /* compiled from: OAuth1a.kt */
    public static final class UnknownException extends OAuth1aException {
        /* JADX INFO: super call moved to the top of the method (can break code semantics) */
        public UnknownException(String str) {
            super(str, (DefaultConstructorMarker) null);
            Intrinsics.checkNotNullParameter(str, "message");
        }
    }
}
