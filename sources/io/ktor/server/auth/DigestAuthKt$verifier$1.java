package io.ktor.server.auth;

import io.ktor.http.HttpMethod;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.ContinuationImpl;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.jvm.functions.Function3;

@Metadata(k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.DigestAuthKt", f = "DigestAuth.kt", i = {0, 0, 0}, l = {222}, m = "verifier", n = {"$this$verifier", "method", "digester"}, s = {"L$0", "L$1", "L$2"})
/* compiled from: DigestAuth.kt */
final class DigestAuthKt$verifier$1 extends ContinuationImpl {
    Object L$0;
    Object L$1;
    Object L$2;
    int label;
    /* synthetic */ Object result;

    DigestAuthKt$verifier$1(Continuation<? super DigestAuthKt$verifier$1> continuation) {
        super(continuation);
    }

    public final Object invokeSuspend(Object obj) {
        this.result = obj;
        this.label |= Integer.MIN_VALUE;
        return DigestAuthKt.verifier((DigestCredential) null, (HttpMethod) null, (MessageDigest) null, (Function3<? super String, ? super String, ? super Continuation<? super byte[]>, ? extends Object>) null, this);
    }
}
