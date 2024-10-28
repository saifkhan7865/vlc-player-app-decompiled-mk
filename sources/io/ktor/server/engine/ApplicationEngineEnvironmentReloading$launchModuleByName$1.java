package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.engine.internal.CallableUtilsKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
final class ApplicationEngineEnvironmentReloading$launchModuleByName$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ClassLoader $currentClassLoader;
    final /* synthetic */ String $name;
    final /* synthetic */ Application $newInstance;
    final /* synthetic */ ApplicationEngineEnvironmentReloading this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ApplicationEngineEnvironmentReloading$launchModuleByName$1(ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading, ClassLoader classLoader, String str, Application application) {
        super(0);
        this.this$0 = applicationEngineEnvironmentReloading;
        this.$currentClassLoader = classLoader;
        this.$name = str;
        this.$newInstance = application;
    }

    public final void invoke() {
        CallableUtilsKt.executeModuleFunction(this.this$0, this.$currentClassLoader, this.$name, this.$newInstance);
    }
}
