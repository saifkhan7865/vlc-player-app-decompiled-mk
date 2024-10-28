package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Parameters;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000@\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010$\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u001a\u001f\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0002\b\t\u001a\u0006\u0010\n\u001a\u00020\u0005\u001aC\u0010\n\u001a\u00020\u000526\u0010\u000b\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000f0\r0\f\"\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000f0\r¢\u0006\u0002\u0010\u0010\u001a\u0016\u0010\n\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000e2\u0006\u0010\u0012\u001a\u00020\u000e\u001a\u001c\u0010\n\u001a\u00020\u00052\u0006\u0010\u0011\u001a\u00020\u000e2\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u000e0\u000f\u001a \u0010\n\u001a\u00020\u00052\u0018\u0010\u0014\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\u000f0\u0015\u001a\u0015\u0010\u0016\u001a\u00020\u0005*\u00020\u00052\u0006\u0010\u0017\u001a\u00020\u0005H\u0002¨\u0006\u0018"}, d2 = {"ParametersBuilder", "Lio/ktor/http/ParametersBuilder;", "size", "", "parameters", "Lio/ktor/http/Parameters;", "builder", "Lkotlin/Function1;", "", "Lkotlin/ExtensionFunctionType;", "parametersOf", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Lio/ktor/http/Parameters;", "name", "value", "values", "map", "", "plus", "other", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Parameters.kt */
public final class ParametersKt {
    public static final ParametersBuilder ParametersBuilder(int i) {
        return new ParametersBuilderImpl(i);
    }

    public static /* synthetic */ ParametersBuilder ParametersBuilder$default(int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8;
        }
        return ParametersBuilder(i);
    }

    public static final Parameters parametersOf() {
        return Parameters.Companion.getEmpty();
    }

    public static final Parameters parametersOf(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        return new ParametersSingleImpl(str, CollectionsKt.listOf(str2));
    }

    public static final Parameters parametersOf(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(list, "values");
        return new ParametersSingleImpl(str, list);
    }

    public static final Parameters parametersOf(Map<String, ? extends List<String>> map) {
        Intrinsics.checkNotNullParameter(map, "map");
        return new ParametersImpl(map);
    }

    public static final Parameters parametersOf(Pair<String, ? extends List<String>>... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        return new ParametersImpl(MapsKt.toMap(ArraysKt.asList((T[]) pairArr)));
    }

    public static final Parameters parameters(Function1<? super ParametersBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builder");
        Parameters.Companion companion = Parameters.Companion;
        ParametersBuilder ParametersBuilder$default = ParametersBuilder$default(0, 1, (Object) null);
        function1.invoke(ParametersBuilder$default);
        return ParametersBuilder$default.build();
    }

    public static final Parameters plus(Parameters parameters, Parameters parameters2) {
        Intrinsics.checkNotNullParameter(parameters, "<this>");
        Intrinsics.checkNotNullParameter(parameters2, "other");
        if (parameters.getCaseInsensitiveName() != parameters2.getCaseInsensitiveName()) {
            throw new IllegalArgumentException("Cannot concatenate Parameters with case-sensitive and case-insensitive names");
        } else if (parameters.isEmpty()) {
            return parameters2;
        } else {
            if (parameters2.isEmpty()) {
                return parameters;
            }
            Parameters.Companion companion = Parameters.Companion;
            ParametersBuilder ParametersBuilder$default = ParametersBuilder$default(0, 1, (Object) null);
            ParametersBuilder$default.appendAll(parameters);
            ParametersBuilder$default.appendAll(parameters2);
            return ParametersBuilder$default.build();
        }
    }
}
