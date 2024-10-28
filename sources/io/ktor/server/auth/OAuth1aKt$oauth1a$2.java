package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.auth.OAuthCallback;
import io.ktor.util.pipeline.PipelineContext;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth1aKt$oauth1a$2", f = "OAuth1a.kt", i = {2}, l = {39, 40, 43, 46}, m = "invokeSuspend", n = {"callbackRedirectUrl"}, s = {"L$0"})
/* compiled from: OAuth1a.kt */
final class OAuth1aKt$oauth1a$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ HttpClient $client;
    final /* synthetic */ OAuthServerSettings $provider;
    final /* synthetic */ PipelineContext<Unit, ApplicationCall> $this_oauth1a;
    final /* synthetic */ OAuthCallback.TokenPair $token;
    final /* synthetic */ Function2<ApplicationCall, OAuthServerSettings, String> $urlProvider;
    Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuth1aKt$oauth1a$2(Function2<? super ApplicationCall, ? super OAuthServerSettings, String> function2, PipelineContext<Unit, ApplicationCall> pipelineContext, OAuthServerSettings oAuthServerSettings, OAuthCallback.TokenPair tokenPair, HttpClient httpClient, Continuation<? super OAuth1aKt$oauth1a$2> continuation) {
        super(2, continuation);
        this.$urlProvider = function2;
        this.$this_oauth1a = pipelineContext;
        this.$provider = oAuthServerSettings;
        this.$token = tokenPair;
        this.$client = httpClient;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new OAuth1aKt$oauth1a$2(this.$urlProvider, this.$this_oauth1a, this.$provider, this.$token, this.$client, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((OAuth1aKt$oauth1a$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:32:0x00cc A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r0 = r18
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r2 == 0) goto L_0x0039
            if (r2 == r6) goto L_0x0033
            if (r2 == r5) goto L_0x002e
            if (r2 == r4) goto L_0x001f
            if (r2 != r3) goto L_0x0017
            goto L_0x002e
        L_0x0017:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x001f:
            java.lang.Object r2 = r0.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ IOException -> 0x002b }
            r9 = r2
            r2 = r19
            goto L_0x00a0
        L_0x002b:
            goto L_0x00b6
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r19)
            goto L_0x00cd
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r19)
            r2 = r19
            goto L_0x006b
        L_0x0039:
            kotlin.ResultKt.throwOnFailure(r19)
            kotlin.jvm.functions.Function2<io.ktor.server.application.ApplicationCall, io.ktor.server.auth.OAuthServerSettings, java.lang.String> r2 = r0.$urlProvider
            io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r7 = r0.$this_oauth1a
            java.lang.Object r7 = r7.getContext()
            io.ktor.server.application.ApplicationCall r7 = (io.ktor.server.application.ApplicationCall) r7
            io.ktor.server.auth.OAuthServerSettings r8 = r0.$provider
            java.lang.Object r2 = r2.invoke(r7, r8)
            r9 = r2
            java.lang.String r9 = (java.lang.String) r9
            io.ktor.server.auth.OAuthCallback$TokenPair r12 = r0.$token
            if (r12 != 0) goto L_0x0085
            io.ktor.client.HttpClient r7 = r0.$client
            io.ktor.server.auth.OAuthServerSettings r2 = r0.$provider
            r8 = r2
            io.ktor.server.auth.OAuthServerSettings$OAuth1aServerSettings r8 = (io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings) r8
            r12 = r0
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r0.label = r6
            r10 = 0
            r11 = 0
            r13 = 24
            r14 = 0
            java.lang.Object r2 = io.ktor.server.auth.OAuth1aKt.simpleOAuth1aStep1$default(r7, r8, r9, r10, r11, r12, r13, r14)
            if (r2 != r1) goto L_0x006b
            return r1
        L_0x006b:
            io.ktor.server.auth.OAuthCallback$TokenPair r2 = (io.ktor.server.auth.OAuthCallback.TokenPair) r2
            io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r3 = r0.$this_oauth1a
            java.lang.Object r3 = r3.getContext()
            io.ktor.server.application.ApplicationCall r3 = (io.ktor.server.application.ApplicationCall) r3
            io.ktor.server.auth.OAuthServerSettings r4 = r0.$provider
            io.ktor.server.auth.OAuthServerSettings$OAuth1aServerSettings r4 = (io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings) r4
            r6 = r0
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r0.label = r5
            java.lang.Object r2 = io.ktor.server.auth.OAuth1aKt.redirectAuthenticateOAuth1a((io.ktor.server.application.ApplicationCall) r3, (io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings) r4, (io.ktor.server.auth.OAuthCallback.TokenPair) r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r6)
            if (r2 != r1) goto L_0x00cd
            return r1
        L_0x0085:
            io.ktor.client.HttpClient r10 = r0.$client     // Catch:{ IOException -> 0x00b4 }
            io.ktor.server.auth.OAuthServerSettings r2 = r0.$provider     // Catch:{ IOException -> 0x00b4 }
            r11 = r2
            io.ktor.server.auth.OAuthServerSettings$OAuth1aServerSettings r11 = (io.ktor.server.auth.OAuthServerSettings.OAuth1aServerSettings) r11     // Catch:{ IOException -> 0x00b4 }
            r15 = r0
            kotlin.coroutines.Continuation r15 = (kotlin.coroutines.Continuation) r15     // Catch:{ IOException -> 0x00b4 }
            r0.L$0 = r9     // Catch:{ IOException -> 0x00b4 }
            r0.label = r4     // Catch:{ IOException -> 0x00b4 }
            r13 = 0
            r14 = 0
            r16 = 24
            r17 = 0
            java.lang.Object r2 = io.ktor.server.auth.OAuth1aKt.requestOAuth1aAccessToken$default(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ IOException -> 0x00b4 }
            if (r2 != r1) goto L_0x00a0
            return r1
        L_0x00a0:
            io.ktor.server.auth.OAuthAccessTokenResponse$OAuth1a r2 = (io.ktor.server.auth.OAuthAccessTokenResponse.OAuth1a) r2     // Catch:{ IOException -> 0x00b4 }
            io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r4 = r0.$this_oauth1a     // Catch:{ IOException -> 0x00b4 }
            java.lang.Object r4 = r4.getContext()     // Catch:{ IOException -> 0x00b4 }
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4     // Catch:{ IOException -> 0x00b4 }
            io.ktor.server.auth.AuthenticationContext r4 = io.ktor.server.auth.AuthenticationKt.getAuthentication(r4)     // Catch:{ IOException -> 0x00b4 }
            io.ktor.server.auth.Principal r2 = (io.ktor.server.auth.Principal) r2     // Catch:{ IOException -> 0x00b4 }
            r4.principal((io.ktor.server.auth.Principal) r2)     // Catch:{ IOException -> 0x00b4 }
            goto L_0x00cd
        L_0x00b4:
            r2 = r9
        L_0x00b6:
            io.ktor.util.pipeline.PipelineContext<kotlin.Unit, io.ktor.server.application.ApplicationCall> r4 = r0.$this_oauth1a
            java.lang.Object r4 = r4.getContext()
            io.ktor.server.application.ApplicationCall r4 = (io.ktor.server.application.ApplicationCall) r4
            r5 = r0
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r6 = 0
            r0.L$0 = r6
            r0.label = r3
            java.lang.Object r2 = io.ktor.server.auth.OAuthKt.oauthHandleFail(r4, r2, r5)
            if (r2 != r1) goto L_0x00cd
            return r1
        L_0x00cd:
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.auth.OAuth1aKt$oauth1a$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
