package io.ktor.server.auth;

import io.ktor.server.auth.DigestAuthenticationProvider;
import java.security.MessageDigest;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import kotlinx.serialization.json.internal.AbstractJsonLexerKt;

@Metadata(d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\u0010\u0000\u001a\n \u0002*\u0004\u0018\u00010\u00010\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004H@"}, d2 = {"<anonymous>", "", "kotlin.jvm.PlatformType", "userName", "", "realm"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.DigestAuthenticationProvider$Config$digestProvider$1", f = "DigestAuth.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DigestAuth.kt */
final class DigestAuthenticationProvider$Config$digestProvider$1 extends SuspendLambda implements Function3<String, String, Continuation<? super byte[]>, Object> {
    /* synthetic */ Object L$0;
    /* synthetic */ Object L$1;
    int label;
    final /* synthetic */ DigestAuthenticationProvider.Config this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DigestAuthenticationProvider$Config$digestProvider$1(DigestAuthenticationProvider.Config config, Continuation<? super DigestAuthenticationProvider$Config$digestProvider$1> continuation) {
        super(3, continuation);
        this.this$0 = config;
    }

    public final Object invoke(String str, String str2, Continuation<? super byte[]> continuation) {
        DigestAuthenticationProvider$Config$digestProvider$1 digestAuthenticationProvider$Config$digestProvider$1 = new DigestAuthenticationProvider$Config$digestProvider$1(this.this$0, continuation);
        digestAuthenticationProvider$Config$digestProvider$1.L$0 = str;
        digestAuthenticationProvider$Config$digestProvider$1.L$1 = str2;
        return digestAuthenticationProvider$Config$digestProvider$1.invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            MessageDigest instance = MessageDigest.getInstance(this.this$0.getAlgorithmName());
            instance.reset();
            byte[] bytes = (((String) this.L$0) + AbstractJsonLexerKt.COLON + ((String) this.L$1)).getBytes(Charsets.UTF_8);
            Intrinsics.checkNotNullExpressionValue(bytes, "this as java.lang.String).getBytes(charset)");
            instance.update(bytes);
            return instance.digest();
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
