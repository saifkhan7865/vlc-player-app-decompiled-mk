package io.ktor.server.application;

import io.ktor.util.KtorDsl;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.SupervisorKt;

@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\b\u0007\u0018\u00002\u00020\u00012\u00020\u0002B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\u000e\u001a\u00020\u000fR\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\u00020\tX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lio/ktor/server/application/Application;", "Lio/ktor/server/application/ApplicationCallPipeline;", "Lkotlinx/coroutines/CoroutineScope;", "environment", "Lio/ktor/server/application/ApplicationEnvironment;", "(Lio/ktor/server/application/ApplicationEnvironment;)V", "applicationJob", "Lkotlinx/coroutines/CompletableJob;", "coroutineContext", "Lkotlin/coroutines/CoroutineContext;", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "getEnvironment", "()Lio/ktor/server/application/ApplicationEnvironment;", "dispose", "", "ktor-server-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@KtorDsl
/* compiled from: Application.kt */
public final class Application extends ApplicationCallPipeline implements CoroutineScope {
    private final CompletableJob applicationJob;
    private final CoroutineContext coroutineContext;
    private final ApplicationEnvironment environment;

    public ApplicationEnvironment getEnvironment() {
        return this.environment;
    }

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public Application(ApplicationEnvironment applicationEnvironment) {
        super(applicationEnvironment.getDevelopmentMode(), applicationEnvironment);
        Intrinsics.checkNotNullParameter(applicationEnvironment, "environment");
        this.environment = applicationEnvironment;
        CompletableJob SupervisorJob = SupervisorKt.SupervisorJob((Job) getEnvironment().getParentCoroutineContext().get(Job.Key));
        this.applicationJob = SupervisorJob;
        this.coroutineContext = getEnvironment().getParentCoroutineContext().plus(SupervisorJob);
    }

    public CoroutineContext getCoroutineContext() {
        return this.coroutineContext;
    }

    public final void dispose() {
        Job.DefaultImpls.cancel$default((Job) this.applicationJob, (CancellationException) null, 1, (Object) null);
        ApplicationPluginKt.uninstallAllPlugins(this);
    }
}
