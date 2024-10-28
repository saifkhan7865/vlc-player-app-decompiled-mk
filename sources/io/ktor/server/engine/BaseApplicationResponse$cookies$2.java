package io.ktor.server.engine;

import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.response.ResponseCookies;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/server/response/ResponseCookies;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: BaseApplicationResponse.kt */
final class BaseApplicationResponse$cookies$2 extends Lambda implements Function0<ResponseCookies> {
    final /* synthetic */ BaseApplicationResponse this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    BaseApplicationResponse$cookies$2(BaseApplicationResponse baseApplicationResponse) {
        super(0);
        this.this$0 = baseApplicationResponse;
    }

    public final ResponseCookies invoke() {
        BaseApplicationResponse baseApplicationResponse = this.this$0;
        return new ResponseCookies(baseApplicationResponse, Intrinsics.areEqual((Object) OriginConnectionPointKt.getOrigin(baseApplicationResponse.getCall().getRequest()).getScheme(), (Object) "https") || Intrinsics.areEqual((Object) OriginConnectionPointKt.getOrigin(this.this$0.getCall().getRequest()).getScheme(), (Object) "wss"));
    }
}
