package io.ktor.server.response;

import io.ktor.http.CacheControl;
import io.ktor.http.ContentDisposition;
import io.ktor.http.ContentRangeKt;
import io.ktor.http.HeadersBuilder;
import io.ktor.http.HttpHeaders;
import io.ktor.http.RangeUnits;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.LongRange;

@Metadata(d1 = {"\u00008\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0012\u0010\u0000\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\u0004\u001a/\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\r\u001a-\u0010\u0006\u001a\u00020\u0001*\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\u0006\u0010\u000b\u001a\u00020\u000e¢\u0006\u0002\u0010\u000f\u001a/\u0010\u0006\u001a\u00020\u0001*\u00020\u00052\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n2\b\b\u0002\u0010\u000b\u001a\u00020\f¢\u0006\u0002\u0010\u0010\u001a\u0012\u0010\u0011\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0003\u001a\u00020\f\u001a\u001a\u0010\u0012\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0014\u001a\u001a\u0010\u0012\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\n\u001a\u001a\u0010\u0012\u001a\u00020\u0001*\u00020\u00052\u0006\u0010\u0013\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\f¨\u0006\u0015"}, d2 = {"cacheControl", "", "Lio/ktor/http/HeadersBuilder;", "value", "Lio/ktor/http/CacheControl;", "Lio/ktor/server/response/ApplicationResponse;", "contentRange", "range", "Lkotlin/ranges/LongRange;", "fullLength", "", "unit", "", "(Lio/ktor/http/HeadersBuilder;Lkotlin/ranges/LongRange;Ljava/lang/Long;Ljava/lang/String;)V", "Lio/ktor/http/RangeUnits;", "(Lio/ktor/server/response/ApplicationResponse;Lkotlin/ranges/LongRange;Ljava/lang/Long;Lio/ktor/http/RangeUnits;)V", "(Lio/ktor/server/response/ApplicationResponse;Lkotlin/ranges/LongRange;Ljava/lang/Long;Ljava/lang/String;)V", "etag", "header", "name", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationResponseProperties.kt */
public final class ApplicationResponsePropertiesKt {
    public static final void header(ApplicationResponse applicationResponse, String str, String str2) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(str2, "value");
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, str2, false, 4, (Object) null);
    }

    public static final void header(ApplicationResponse applicationResponse, String str, int i) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, String.valueOf(i), false, 4, (Object) null);
    }

    public static final void header(ApplicationResponse applicationResponse, String str, long j) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        ResponseHeaders.append$default(applicationResponse.getHeaders(), str, String.valueOf(j), false, 4, (Object) null);
    }

    public static final void etag(ApplicationResponse applicationResponse, String str) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(str, "value");
        header(applicationResponse, HttpHeaders.INSTANCE.getETag(), str);
    }

    public static final void cacheControl(ApplicationResponse applicationResponse, CacheControl cacheControl) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(cacheControl, "value");
        header(applicationResponse, HttpHeaders.INSTANCE.getCacheControl(), cacheControl.toString());
    }

    public static final void cacheControl(HeadersBuilder headersBuilder, CacheControl cacheControl) {
        Intrinsics.checkNotNullParameter(headersBuilder, "<this>");
        Intrinsics.checkNotNullParameter(cacheControl, "value");
        headersBuilder.set(HttpHeaders.INSTANCE.getCacheControl(), cacheControl.toString());
    }

    public static /* synthetic */ void contentRange$default(HeadersBuilder headersBuilder, LongRange longRange, Long l, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        contentRange(headersBuilder, longRange, l, str);
    }

    public static final void contentRange(HeadersBuilder headersBuilder, LongRange longRange, Long l, String str) {
        Intrinsics.checkNotNullParameter(headersBuilder, "<this>");
        Intrinsics.checkNotNullParameter(str, "unit");
        headersBuilder.append(HttpHeaders.INSTANCE.getContentRange(), ContentRangeKt.contentRangeHeaderValue(longRange, l, str));
    }

    public static /* synthetic */ void contentRange$default(ApplicationResponse applicationResponse, LongRange longRange, Long l, RangeUnits rangeUnits, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        contentRange(applicationResponse, longRange, l, rangeUnits);
    }

    public static final void contentRange(ApplicationResponse applicationResponse, LongRange longRange, Long l, RangeUnits rangeUnits) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(rangeUnits, "unit");
        contentRange(applicationResponse, longRange, l, rangeUnits.getUnitToken());
    }

    public static /* synthetic */ void contentRange$default(ApplicationResponse applicationResponse, LongRange longRange, Long l, String str, int i, Object obj) {
        if ((i & 2) != 0) {
            l = null;
        }
        if ((i & 4) != 0) {
            str = RangeUnits.Bytes.getUnitToken();
        }
        contentRange(applicationResponse, longRange, l, str);
    }

    public static final void contentRange(ApplicationResponse applicationResponse, LongRange longRange, Long l, String str) {
        Intrinsics.checkNotNullParameter(applicationResponse, "<this>");
        Intrinsics.checkNotNullParameter(str, "unit");
        header(applicationResponse, HttpHeaders.INSTANCE.getContentRange(), ContentRangeKt.contentRangeHeaderValue(longRange, l, str));
    }
}
