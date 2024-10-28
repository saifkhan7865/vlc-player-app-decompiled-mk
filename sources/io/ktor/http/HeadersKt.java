package io.ktor.http;

import io.ktor.http.ContentDisposition;
import io.ktor.http.Headers;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0005\u001a\u001f\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0002\b\u0006\u001a\u0006\u0010\u0007\u001a\u00020\u0001\u001aC\u0010\u0007\u001a\u00020\u000126\u0010\b\u001a\u001c\u0012\u0018\b\u0001\u0012\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\f0\n0\t\"\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\f0\n¢\u0006\u0002\u0010\r\u001a\u0016\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\u000f\u001a\u00020\u000b\u001a\u001c\u0010\u0007\u001a\u00020\u00012\u0006\u0010\u000e\u001a\u00020\u000b2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000b0\f¨\u0006\u0011"}, d2 = {"headers", "Lio/ktor/http/Headers;", "builder", "Lkotlin/Function1;", "Lio/ktor/http/HeadersBuilder;", "", "Lkotlin/ExtensionFunctionType;", "headersOf", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Lio/ktor/http/Headers;", "name", "value", "values", "ktor-http"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: Headers.kt */
public final class HeadersKt {
    public static final Headers headersOf() {
        return Headers.Companion.getEmpty();
    }

    public static final Headers headersOf(String str, String str2) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        return new HeadersSingleImpl(str, CollectionsKt.listOf(str2));
    }

    public static final Headers headersOf(String str, List<String> list) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(list, "values");
        return new HeadersSingleImpl(str, list);
    }

    public static final Headers headersOf(Pair<String, ? extends List<String>>... pairArr) {
        Intrinsics.checkNotNullParameter(pairArr, "pairs");
        return new HeadersImpl(MapsKt.toMap(ArraysKt.asList((T[]) pairArr)));
    }

    public static final Headers headers(Function1<? super HeadersBuilder, Unit> function1) {
        Intrinsics.checkNotNullParameter(function1, "builder");
        Headers.Companion companion = Headers.Companion;
        HeadersBuilder headersBuilder = new HeadersBuilder(0, 1, (DefaultConstructorMarker) null);
        function1.invoke(headersBuilder);
        return headersBuilder.build();
    }
}
