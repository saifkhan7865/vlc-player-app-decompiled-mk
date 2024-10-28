package io.ktor.server.application;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0017\u0018\u00002\u00060\u0001j\u0002`\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005¨\u0006\u0006"}, d2 = {"Lio/ktor/server/application/DuplicateApplicationPluginException;", "Ljava/lang/Exception;", "Lkotlin/Exception;", "message", "", "(Ljava/lang/String;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(message = "Please use DuplicatePluginException instead", replaceWith = @ReplaceWith(expression = "DuplicatePluginException", imports = {}))
/* compiled from: ApplicationPlugin.kt */
public class DuplicateApplicationPluginException extends Exception {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DuplicateApplicationPluginException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "message");
    }
}
