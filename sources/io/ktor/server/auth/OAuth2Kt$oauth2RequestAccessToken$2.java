package io.ktor.server.auth;

import io.ktor.client.HttpClient;
import io.ktor.client.request.HttpRequestBuilder;
import io.ktor.http.HttpMethod;
import io.ktor.util.NonceManager;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function1;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth2Kt", f = "OAuth2.kt", i = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 2, 2, 2}, l = {173, 448, 235}, m = "oauth2RequestAccessToken", n = {"client", "method", "usedRedirectUrl", "baseUrl", "clientId", "clientSecret", "state", "code", "extraParameters", "configure", "grantType", "useBasicAuth", "passParamsInURL", "baseUrl", "state", "baseUrl", "state", "response"}, s = {"L$0", "L$1", "L$2", "L$3", "L$4", "L$5", "L$6", "L$7", "L$8", "L$9", "L$10", "Z$0", "Z$1", "L$0", "L$1", "L$0", "L$1", "L$2"})
/* compiled from: OAuth2.kt */
final class OAuth2Kt$oauth2RequestAccessToken$2 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$10;
    Object L$2;
    Object L$3;
    Object L$4;
    Object L$5;
    Object L$6;
    Object L$7;
    Object L$8;
    Object L$9;
    boolean Z$0;
    boolean Z$1;
    int label;
    /* synthetic */ Object result;

    OAuth2Kt$oauth2RequestAccessToken$2(Continuation<? super OAuth2Kt$oauth2RequestAccessToken$2> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return OAuth2Kt.oauth2RequestAccessToken((HttpClient) null, (HttpMethod) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (List<Pair<String, String>>) null, (Function1<? super HttpRequestBuilder, Unit>) null, false, (NonceManager) null, false, (String) null, this);
    }
}
