package io.ktor.server.request;

import io.ktor.http.CodecsKt;
import io.ktor.http.Parameters;
import io.ktor.http.ParametersBuilder;
import io.ktor.http.ParametersKt;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0004"}, d2 = {"encodeParameters", "Lio/ktor/http/Parameters;", "Lio/ktor/server/request/ApplicationRequest;", "parameters", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationRequest.kt */
public final class ApplicationRequestKt {
    public static final Parameters encodeParameters(ApplicationRequest applicationRequest, Parameters parameters) {
        List list;
        Intrinsics.checkNotNullParameter(applicationRequest, "<this>");
        Intrinsics.checkNotNullParameter(parameters, "parameters");
        ParametersBuilder ParametersBuilder$default = ParametersKt.ParametersBuilder$default(0, 1, (Object) null);
        for (String str : applicationRequest.getRawQueryParameters().names()) {
            List<String> all = parameters.getAll(str);
            if (all != null) {
                Iterable<String> iterable = all;
                Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
                for (String decodeURLQueryComponent$default : iterable) {
                    arrayList.add(CodecsKt.decodeURLQueryComponent$default(decodeURLQueryComponent$default, 0, 0, true, (Charset) null, 11, (Object) null));
                }
                list = (List) arrayList;
            } else {
                list = null;
            }
            if (list == null) {
                list = CollectionsKt.emptyList();
            }
            ParametersBuilder$default.appendAll(CodecsKt.decodeURLQueryComponent$default(str, 0, 0, false, (Charset) null, 15, (Object) null), list);
        }
        return ParametersBuilder$default.build();
    }
}
