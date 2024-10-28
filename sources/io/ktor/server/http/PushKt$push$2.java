package io.ktor.server.http;

import io.ktor.http.Parameters;
import io.ktor.http.URLBuilderKt;
import io.ktor.server.response.ResponsePushBuilder;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lio/ktor/server/response/ResponsePushBuilder;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: Push.kt */
final class PushKt$push$2 extends Lambda implements Function1<ResponsePushBuilder, Unit> {
    final /* synthetic */ Parameters $encodedParameters;
    final /* synthetic */ String $encodedPath;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PushKt$push$2(String str, Parameters parameters) {
        super(1);
        this.$encodedPath = str;
        this.$encodedParameters = parameters;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ResponsePushBuilder) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ResponsePushBuilder responsePushBuilder) {
        Intrinsics.checkNotNullParameter(responsePushBuilder, "$this$push");
        URLBuilderKt.setEncodedPath(responsePushBuilder.getUrl(), this.$encodedPath);
        responsePushBuilder.getUrl().getEncodedParameters().clear();
        responsePushBuilder.getUrl().getEncodedParameters().appendAll(this.$encodedParameters);
    }
}
