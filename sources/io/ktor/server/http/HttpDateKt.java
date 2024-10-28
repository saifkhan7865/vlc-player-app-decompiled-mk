package io.ktor.server.http;

import io.ktor.http.DateUtilsKt;
import io.ktor.util.date.DateJvmKt;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\t\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0007¨\u0006\u0003"}, d2 = {"toHttpDateString", "", "", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpDate.kt */
public final class HttpDateKt {
    @Deprecated(level = DeprecationLevel.ERROR, message = "This will be removed in future releases.", replaceWith = @ReplaceWith(expression = "GMTDate(this).toHttpDate()", imports = {"io.ktor.util.date.GMTDate"}))
    public static final String toHttpDateString(long j) {
        return DateUtilsKt.toHttpDate(DateJvmKt.GMTDate(Long.valueOf(j)));
    }
}
