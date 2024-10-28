package io.ktor.server.http;

import io.ktor.http.Parameters;
import io.ktor.http.QueryKt;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.DefaultResponsePushBuilder;
import io.ktor.server.response.ResponsePushBuilder;
import io.ktor.server.response.UseHttp2Push;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u001a%\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0017\u0010\u0003\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00010\u0004¢\u0006\u0002\b\u0006H\u0007\u001a\u0014\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a\u001c\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\t\u001a\u00020\b2\u0006\u0010\n\u001a\u00020\u000bH\u0007¨\u0006\f"}, d2 = {"push", "", "Lio/ktor/server/application/ApplicationCall;", "block", "Lkotlin/Function1;", "Lio/ktor/server/response/ResponsePushBuilder;", "Lkotlin/ExtensionFunctionType;", "pathAndQuery", "", "encodedPath", "encodedParameters", "Lio/ktor/http/Parameters;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Push.kt */
public final class PushKt {
    @UseHttp2Push
    public static final void push(ApplicationCall applicationCall, String str, Parameters parameters) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(str, "encodedPath");
        Intrinsics.checkNotNullParameter(parameters, "encodedParameters");
        push(applicationCall, (Function1<? super ResponsePushBuilder, Unit>) new PushKt$push$2(str, parameters));
    }

    @UseHttp2Push
    public static final void push(ApplicationCall applicationCall, Function1<? super ResponsePushBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        ApplicationResponse response = applicationCall.getResponse();
        DefaultResponsePushBuilder defaultResponsePushBuilder = new DefaultResponsePushBuilder(applicationCall);
        function1.invoke(defaultResponsePushBuilder);
        response.push(defaultResponsePushBuilder);
    }

    @UseHttp2Push
    public static final void push(ApplicationCall applicationCall, String str) {
        Pair pair;
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(str, "pathAndQuery");
        int indexOf$default = StringsKt.indexOf$default((CharSequence) str, "?", 0, false, 6, (Object) null);
        if (indexOf$default == -1) {
            pair = TuplesKt.to(str, "");
        } else {
            String substring = str.substring(0, indexOf$default);
            Intrinsics.checkNotNullExpressionValue(substring, "this as java.lang.String…ing(startIndex, endIndex)");
            String substring2 = str.substring(indexOf$default + 1);
            Intrinsics.checkNotNullExpressionValue(substring2, "this as java.lang.String).substring(startIndex)");
            pair = TuplesKt.to(substring, substring2);
        }
        push(applicationCall, (String) pair.component1(), QueryKt.parseQueryString$default((String) pair.component2(), 0, 0, false, 6, (Object) null));
    }
}
