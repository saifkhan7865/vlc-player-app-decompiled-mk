package io.ktor.server.application;

import io.ktor.util.Attributes;
import io.ktor.util.AttributesJvmKt;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lio/ktor/util/Attributes;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationPlugin.kt */
final class ApplicationPluginKt$pluginRegistry$1 extends Lambda implements Function0<Attributes> {
    public static final ApplicationPluginKt$pluginRegistry$1 INSTANCE = new ApplicationPluginKt$pluginRegistry$1();

    ApplicationPluginKt$pluginRegistry$1() {
        super(0);
    }

    public final Attributes invoke() {
        return AttributesJvmKt.Attributes(true);
    }
}
