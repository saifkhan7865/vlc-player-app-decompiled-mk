package io.ktor.server.util;

import androidx.core.app.FrameMetricsAggregator;
import androidx.core.app.NotificationCompat;
import io.ktor.http.Parameters;
import io.ktor.http.RequestConnectionPoint;
import io.ktor.http.URLBuilder;
import io.ktor.http.URLProtocol;
import io.ktor.server.application.ApplicationCall;
import io.ktor.server.plugins.OriginConnectionPointKt;
import io.ktor.server.request.ApplicationRequestPropertiesKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u001a\u0012\u0010\u0007\u001a\u00020\u0004*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\n2\u0019\b\u0002\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006H\bø\u0001\u0000\u0002\u0007\n\u0005\b20\u0001¨\u0006\u000b"}, d2 = {"url", "", "block", "Lkotlin/Function1;", "Lio/ktor/http/URLBuilder;", "", "Lkotlin/ExtensionFunctionType;", "createFromCall", "Lio/ktor/http/URLBuilder$Companion;", "call", "Lio/ktor/server/application/ApplicationCall;", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: URLBuilder.kt */
public final class URLBuilderKt {
    public static final URLBuilder createFromCall(URLBuilder.Companion companion, ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(companion, "<this>");
        Intrinsics.checkNotNullParameter(applicationCall, NotificationCompat.CATEGORY_CALL);
        RequestConnectionPoint origin = OriginConnectionPointKt.getOrigin(applicationCall.getRequest());
        URLBuilder uRLBuilder = new URLBuilder((URLProtocol) null, (String) null, 0, (String) null, (String) null, (List) null, (Parameters) null, (String) null, false, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null);
        URLProtocol uRLProtocol = URLProtocol.Companion.getByName().get(origin.getScheme());
        if (uRLProtocol == null) {
            uRLProtocol = new URLProtocol(origin.getScheme(), 0);
        }
        uRLBuilder.setProtocol(uRLProtocol);
        uRLBuilder.setHost(origin.getServerHost());
        uRLBuilder.setPort(origin.getServerPort());
        io.ktor.http.URLBuilderKt.setEncodedPath(uRLBuilder, ApplicationRequestPropertiesKt.path(applicationCall.getRequest()));
        uRLBuilder.getParameters().appendAll(applicationCall.getRequest().getQueryParameters());
        return uRLBuilder;
    }

    public static final String url(Function1<? super URLBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "block");
        URLBuilder uRLBuilder = new URLBuilder((URLProtocol) null, (String) null, 0, (String) null, (String) null, (List) null, (Parameters) null, (String) null, false, FrameMetricsAggregator.EVERY_DURATION, (DefaultConstructorMarker) null);
        function1.invoke(uRLBuilder);
        return uRLBuilder.buildString();
    }

    public static /* synthetic */ String url$default(ApplicationCall applicationCall, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            function1 = URLBuilderKt$url$1.INSTANCE;
        }
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        URLBuilder createFromCall = createFromCall(URLBuilder.Companion, applicationCall);
        function1.invoke(createFromCall);
        return createFromCall.buildString();
    }

    public static final String url(ApplicationCall applicationCall, Function1<? super URLBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        URLBuilder createFromCall = createFromCall(URLBuilder.Companion, applicationCall);
        function1.invoke(createFromCall);
        return createFromCall.buildString();
    }
}
