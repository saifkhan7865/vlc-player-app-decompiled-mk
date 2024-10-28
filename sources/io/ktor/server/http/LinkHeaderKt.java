package io.ktor.server.http;

import io.ktor.http.HttpHeaders;
import io.ktor.http.LinkHeader;
import io.ktor.server.response.ApplicationResponse;
import io.ktor.server.response.ResponseHeaders;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000 \n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a+\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00060\b\"\u00020\u0006¢\u0006\u0002\u0010\t¨\u0006\n"}, d2 = {"link", "", "Lio/ktor/server/response/ApplicationResponse;", "header", "Lio/ktor/http/LinkHeader;", "uri", "", "rel", "", "(Lio/ktor/server/response/ApplicationResponse;Ljava/lang/String;[Ljava/lang/String;)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: LinkHeader.kt */
public final class LinkHeaderKt {
    public static final void link(ApplicationResponse applicationResponse, LinkHeader linkHeader) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(linkHeader, "header");
        ResponseHeaders.append$default(applicationResponse.getHeaders(), HttpHeaders.INSTANCE.getLink(), linkHeader.toString(), false, 4, (Object) null);
    }

    public static final void link(ApplicationResponse applicationResponse, String str, String... strArr) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(str, Constants.KEY_URI);
        Intrinsics.checkNotNullParameter(strArr, LinkHeader.Parameters.Rel);
        link(applicationResponse, new LinkHeader(str, (String[]) Arrays.copyOf(strArr, strArr.length)));
    }
}
