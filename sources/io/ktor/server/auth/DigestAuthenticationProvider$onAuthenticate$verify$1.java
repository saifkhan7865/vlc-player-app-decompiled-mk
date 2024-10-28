package io.ktor.server.auth;

import io.ktor.http.HttpMethod;
import io.ktor.server.application.ApplicationCall;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lio/ktor/server/auth/DigestCredential;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.DigestAuthenticationProvider$onAuthenticate$verify$1", f = "DigestAuth.kt", i = {}, l = {46}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DigestAuth.kt */
final class DigestAuthenticationProvider$onAuthenticate$verify$1 extends SuspendLambda implements Function2<DigestCredential, Continuation<? super Boolean>, Object> {
    final /* synthetic */ ApplicationCall $call;
    /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DigestAuthenticationProvider this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DigestAuthenticationProvider$onAuthenticate$verify$1(ApplicationCall applicationCall, DigestAuthenticationProvider digestAuthenticationProvider, Continuation<? super DigestAuthenticationProvider$onAuthenticate$verify$1> continuation) {
        super(2, continuation);
        this.$call = applicationCall;
        this.this$0 = digestAuthenticationProvider;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DigestAuthenticationProvider$onAuthenticate$verify$1 digestAuthenticationProvider$onAuthenticate$verify$1 = new DigestAuthenticationProvider$onAuthenticate$verify$1(this.$call, this.this$0, continuation);
        digestAuthenticationProvider$onAuthenticate$verify$1.L$0 = obj;
        return digestAuthenticationProvider$onAuthenticate$verify$1;
    }

    public final Object invoke(DigestCredential digestCredential, Continuation<? super Boolean> continuation) {
        return ((DigestAuthenticationProvider$onAuthenticate$verify$1) create(digestCredential, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            HttpMethod method = this.$call.getRequest().getLocal().getMethod();
            MessageDigest instance = MessageDigest.getInstance(this.this$0.algorithmName);
            Intrinsics.checkNotNullExpressionValue(instance, "getInstance(algorithmName)");
            this.label = 1;
            obj = DigestAuthKt.verifier((DigestCredential) this.L$0, method, instance, this.this$0.userNameRealmPasswordDigestProvider, this);
            if (obj == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return obj;
    }
}
