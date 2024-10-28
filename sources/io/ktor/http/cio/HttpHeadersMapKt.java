package io.ktor.http.cio;

import io.ktor.utils.io.pool.DefaultPool;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u00000\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a \u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\n\u0010\r\u001a\u00060\u000ej\u0002`\u000fH\u0000\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0004\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0003XT¢\u0006\u0002\n\u0000\"\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"EMPTY_INT_LIST", "", "EXPECTED_HEADERS_QTY", "", "HEADER_ARRAY_POOL_SIZE", "HEADER_SIZE", "IntArrayPool", "Lio/ktor/utils/io/pool/DefaultPool;", "dumpTo", "", "Lio/ktor/http/cio/HttpHeadersMap;", "indent", "", "out", "Ljava/lang/Appendable;", "Lkotlin/text/Appendable;", "ktor-http-cio"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpHeadersMap.kt */
public final class HttpHeadersMapKt {
    /* access modifiers changed from: private */
    public static final int[] EMPTY_INT_LIST = new int[0];
    private static final int EXPECTED_HEADERS_QTY = 64;
    private static final int HEADER_ARRAY_POOL_SIZE = 1000;
    private static final int HEADER_SIZE = 8;
    /* access modifiers changed from: private */
    public static final DefaultPool<int[]> IntArrayPool = new HttpHeadersMapKt$IntArrayPool$1();

    public static final void dumpTo(HttpHeadersMap httpHeadersMap, String str, Appendable appendable) {
        Intrinsics.checkNotNullParameter(httpHeadersMap, "<this>");
        Intrinsics.checkNotNullParameter(str, "indent");
        Intrinsics.checkNotNullParameter(appendable, "out");
        int size = httpHeadersMap.getSize();
        for (int i = 0; i < size; i++) {
            appendable.append(str);
            appendable.append(httpHeadersMap.nameAt(i));
            appendable.append(" => ");
            appendable.append(httpHeadersMap.valueAt(i));
            appendable.append("\n");
        }
    }
}
