package io.ktor.server.routing;

import io.ktor.server.application.ApplicationCall;
import io.ktor.server.application.ApplicationPlugin;
import io.ktor.server.application.CreatePluginUtilsKt;
import io.ktor.util.AttributeKey;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\"\u0017\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0004\"\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00020\u0006X\u0004¢\u0006\u0002\n\u0000\"(\u0010\t\u001a\u00020\b*\u00020\n2\u0006\u0010\u0007\u001a\u00020\b8@@BX\u000e¢\u0006\f\u001a\u0004\b\u0003\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"IgnoreTrailingSlash", "Lio/ktor/server/application/ApplicationPlugin;", "", "getIgnoreTrailingSlash", "()Lio/ktor/server/application/ApplicationPlugin;", "IgnoreTrailingSlashAttributeKey", "Lio/ktor/util/AttributeKey;", "value", "", "ignoreTrailingSlash", "Lio/ktor/server/application/ApplicationCall;", "(Lio/ktor/server/application/ApplicationCall;)Z", "setIgnoreTrailingSlash", "(Lio/ktor/server/application/ApplicationCall;Z)V", "ktor-server-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: IgnoreTrailingSlash.kt */
public final class IgnoreTrailingSlashKt {
    private static final ApplicationPlugin<Unit> IgnoreTrailingSlash = CreatePluginUtilsKt.createApplicationPlugin("IgnoreTrailingSlash", IgnoreTrailingSlashKt$IgnoreTrailingSlash$1.INSTANCE);
    private static final AttributeKey<Unit> IgnoreTrailingSlashAttributeKey = new AttributeKey<>("IgnoreTrailingSlashAttributeKey");

    public static final boolean getIgnoreTrailingSlash(ApplicationCall applicationCall) {
        Intrinsics.checkNotNullParameter(applicationCall, "<this>");
        return applicationCall.getAttributes().contains(IgnoreTrailingSlashAttributeKey);
    }

    /* access modifiers changed from: private */
    public static final void setIgnoreTrailingSlash(ApplicationCall applicationCall, boolean z) {
        if (z) {
            applicationCall.getAttributes().put(IgnoreTrailingSlashAttributeKey, Unit.INSTANCE);
        } else {
            applicationCall.getAttributes().remove(IgnoreTrailingSlashAttributeKey);
        }
    }

    public static final ApplicationPlugin<Unit> getIgnoreTrailingSlash() {
        return IgnoreTrailingSlash;
    }
}
