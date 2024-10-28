package io.ktor.server.auth;

import io.ktor.server.response.ApplicationResponseFunctionsKt;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004H@"}, d2 = {"<anonymous>", "", "Lio/ktor/server/auth/FormAuthChallengeContext;", "it", "Lio/ktor/server/auth/UserPasswordCredential;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.FormAuthenticationProvider$Config$challenge$1", f = "FormAuth.kt", i = {}, l = {86}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FormAuth.kt */
final class FormAuthenticationProvider$Config$challenge$1 extends SuspendLambda implements Function3<FormAuthChallengeContext, UserPasswordCredential, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $redirectUrl;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FormAuthenticationProvider$Config$challenge$1(String str, Continuation<? super FormAuthenticationProvider$Config$challenge$1> continuation) {
        super(3, continuation);
        this.$redirectUrl = str;
    }

    public final Object invoke(FormAuthChallengeContext formAuthChallengeContext, UserPasswordCredential userPasswordCredential, Continuation<? super Unit> continuation) {
        FormAuthenticationProvider$Config$challenge$1 formAuthenticationProvider$Config$challenge$1 = new FormAuthenticationProvider$Config$challenge$1(this.$redirectUrl, continuation);
        formAuthenticationProvider$Config$challenge$1.L$0 = formAuthChallengeContext;
        return formAuthenticationProvider$Config$challenge$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            this.label = 1;
            if (ApplicationResponseFunctionsKt.respondRedirect$default(((FormAuthChallengeContext) this.L$0).getCall(), this.$redirectUrl, false, (Continuation) this, 2, (Object) null) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
