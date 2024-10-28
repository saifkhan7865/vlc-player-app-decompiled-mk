package io.ktor.http;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/http/URLDecodeException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "ktor-http"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Codecs.kt */
public final class URLDecodeException extends Exception {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public URLDecodeException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "message");
    }
}