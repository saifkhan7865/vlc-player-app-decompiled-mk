package io.ktor.server.engine;

import io.ktor.server.application.Application;
import io.ktor.server.engine.internal.ReloadingException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Lambda;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: ApplicationEngineEnvironmentReloading.kt */
final class ApplicationEngineEnvironmentReloading$instantiateAndConfigureApplication$1 extends Lambda implements Function0<Unit> {
    final /* synthetic */ ClassLoader $currentClassLoader;
    final /* synthetic */ Application $newInstance;
    final /* synthetic */ ApplicationEngineEnvironmentReloading this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    ApplicationEngineEnvironmentReloading$instantiateAndConfigureApplication$1(ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading, ClassLoader classLoader, Application application) {
        super(0);
        this.this$0 = applicationEngineEnvironmentReloading;
        this.$currentClassLoader = classLoader;
        this.$newInstance = application;
    }

    public final void invoke() {
        ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading = this.this$0;
        ClassLoader classLoader = this.$currentClassLoader;
        Application application = this.$newInstance;
        for (String access$launchModuleByName : this.this$0.getModulesNames$ktor_server_host_common()) {
            applicationEngineEnvironmentReloading.launchModuleByName(access$launchModuleByName, classLoader, application);
        }
        ApplicationEngineEnvironmentReloading applicationEngineEnvironmentReloading2 = this.this$0;
        ClassLoader classLoader2 = this.$currentClassLoader;
        Application application2 = this.$newInstance;
        for (Function1 function1 : this.this$0.getModules$ktor_server_host_common()) {
            try {
                applicationEngineEnvironmentReloading2.launchModuleByName(ServerHostUtilsKt.methodName(function1), classLoader2, application2);
            } catch (ReloadingException unused) {
                function1.invoke(application2);
            }
        }
    }
}
