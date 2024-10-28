package io.ktor.server.auth;

import io.ktor.server.auth.FormAuthenticationProvider;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a/\u0010\u0002\u001a\u00020\u0003*\u00020\u00042\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u00062\u0017\u0010\u0007\u001a\u0013\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u00030\b¢\u0006\u0002\b\n\"\u000e\u0010\u0000\u001a\u00020\u0001XD¢\u0006\u0002\n\u0000*\\\u0010\u000b\"+\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f¢\u0006\u0002\b\n2+\b\u0001\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00010\f¢\u0006\u0002\b\n¨\u0006\u0010"}, d2 = {"formAuthenticationChallengeKey", "", "form", "", "Lio/ktor/server/auth/AuthenticationConfig;", "name", "", "configure", "Lkotlin/Function1;", "Lio/ktor/server/auth/FormAuthenticationProvider$Config;", "Lkotlin/ExtensionFunctionType;", "FormAuthChallengeFunction", "Lkotlin/Function3;", "Lio/ktor/server/auth/FormAuthChallengeContext;", "Lio/ktor/server/auth/UserPasswordCredential;", "Lkotlin/coroutines/Continuation;", "ktor-server-auth"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: FormAuth.kt */
public final class FormAuthKt {
    /* access modifiers changed from: private */
    public static final Object formAuthenticationChallengeKey = "FormAuth";

    public static /* synthetic */ void form$default(AuthenticationConfig authenticationConfig, String str, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            str = null;
        }
        form(authenticationConfig, str, function1);
    }

    public static final void form(AuthenticationConfig authenticationConfig, String str, Function1<? super FormAuthenticationProvider.Config, Unit> function1) {
        Intrinsics.checkNotNullParameter(authenticationConfig, "<this>");
        Intrinsics.checkNotNullParameter(function1, "configure");
        FormAuthenticationProvider.Config config = new FormAuthenticationProvider.Config(str);
        function1.invoke(config);
        authenticationConfig.register(config.build$ktor_server_auth());
    }
}
