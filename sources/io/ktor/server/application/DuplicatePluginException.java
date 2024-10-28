package io.ktor.server.application;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004¨\u0006\u0005"}, d2 = {"Lio/ktor/server/application/DuplicatePluginException;", "Lio/ktor/server/application/DuplicateApplicationPluginException;", "message", "", "(Ljava/lang/String;)V", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationPlugin.kt */
public final class DuplicatePluginException extends DuplicateApplicationPluginException {
    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public DuplicatePluginException(String str) {
        super(str);
        Intrinsics.checkNotNullParameter(str, "message");
    }
}
