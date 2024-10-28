package io.ktor.server.auth;

import io.ktor.http.HttpUrlEncodedKt;
import java.io.Writer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Ljava/io/Writer;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.server.auth.OAuth1aKt$requestOAuth1aAccessToken$body$1$1", f = "OAuth1a.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: OAuth1a.kt */
final class OAuth1aKt$requestOAuth1aAccessToken$body$1$1 extends SuspendLambda implements Function2<Writer, Continuation<? super Unit>, Object> {
    final /* synthetic */ List<Pair<String, String>> $params;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    OAuth1aKt$requestOAuth1aAccessToken$body$1$1(List<Pair<String, String>> list, Continuation<? super OAuth1aKt$requestOAuth1aAccessToken$body$1$1> continuation) {
        super(2, continuation);
        this.$params = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        OAuth1aKt$requestOAuth1aAccessToken$body$1$1 oAuth1aKt$requestOAuth1aAccessToken$body$1$1 = new OAuth1aKt$requestOAuth1aAccessToken$body$1$1(this.$params, continuation);
        oAuth1aKt$requestOAuth1aAccessToken$body$1$1.L$0 = obj;
        return oAuth1aKt$requestOAuth1aAccessToken$body$1$1;
    }

    public final Object invoke(Writer writer, Continuation<? super Unit> continuation) {
        return ((OAuth1aKt$requestOAuth1aAccessToken$body$1$1) create(writer, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            HttpUrlEncodedKt.formUrlEncodeTo(this.$params, (Appendable) (Writer) this.L$0);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
