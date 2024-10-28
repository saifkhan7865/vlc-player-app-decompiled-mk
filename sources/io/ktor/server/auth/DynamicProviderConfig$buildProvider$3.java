package io.ktor.server.auth;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0019\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0002\u0010\u0006\u0002\u0004\n\u0002\b\u0019¨\u0006\u0007"}, d2 = {"io/ktor/server/auth/DynamicProviderConfig$buildProvider$3", "Lio/ktor/server/auth/AuthenticationProvider;", "onAuthenticate", "", "context", "Lio/ktor/server/auth/AuthenticationContext;", "(Lio/ktor/server/auth/AuthenticationContext;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-auth"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: DynamicProviderConfig.kt */
public final class DynamicProviderConfig$buildProvider$3 extends AuthenticationProvider {
    final /* synthetic */ DynamicProviderConfig this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DynamicProviderConfig$buildProvider$3(DynamicProviderConfig dynamicProviderConfig) {
        super(dynamicProviderConfig);
        this.this$0 = dynamicProviderConfig;
    }

    public Object onAuthenticate(AuthenticationContext authenticationContext, Continuation<? super Unit> continuation) {
        Function1 access$getAuthenticateFunction$p = this.this$0.authenticateFunction;
        if (access$getAuthenticateFunction$p == null) {
            Intrinsics.throwUninitializedPropertyAccessException("authenticateFunction");
            access$getAuthenticateFunction$p = null;
        }
        access$getAuthenticateFunction$p.invoke(authenticationContext);
        return Unit.INSTANCE;
    }
}
