package io.ktor.client.engine;

import io.ktor.client.HttpClient;
import io.ktor.client.engine.HttpClientEngine;
import io.ktor.util.CoroutinesUtilsKt;
import io.ktor.util.InternalAPI;
import java.util.Set;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0003\b'\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\f\u001a\u00020\rH\u0016J\u0011\u0010\u000e\u001a\u00020\u0006H@ø\u0001\u0000¢\u0006\u0002\u0010\u000fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0007\u001a\u00020\u00068VX\u0002¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\b\u0010\t\u0002\u0004\n\u0002\b\u0019¨\u0006\u0010"}, d2 = {"Lio/ktor/client/engine/HttpClientJvmEngine;", "Lio/ktor/client/engine/HttpClientEngine;", "engineName", "", "(Ljava/lang/String;)V", "clientContext", "Lkotlin/coroutines/CoroutineContext;", "coroutineContext", "getCoroutineContext", "()Lkotlin/coroutines/CoroutineContext;", "coroutineContext$delegate", "Lkotlin/Lazy;", "close", "", "createCallContext", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
@Deprecated(level = DeprecationLevel.ERROR, message = "Use HttpClientEngineBase instead.", replaceWith = @ReplaceWith(expression = "HttpClientEngineBase", imports = {}))
/* compiled from: HttpClientJvmEngine.kt */
public abstract class HttpClientJvmEngine implements HttpClientEngine {
    /* access modifiers changed from: private */
    public final CoroutineContext clientContext = CoroutinesUtilsKt.SilentSupervisor$default((Job) null, 1, (Object) null);
    private final Lazy coroutineContext$delegate;

    public HttpClientJvmEngine(String str) {
        Intrinsics.checkNotNullParameter(str, "engineName");
        this.coroutineContext$delegate = LazyKt.lazy(new HttpClientJvmEngine$coroutineContext$2(this, str));
    }

    public Set<HttpClientEngineCapability<?>> getSupportedCapabilities() {
        return HttpClientEngine.DefaultImpls.getSupportedCapabilities(this);
    }

    @InternalAPI
    public void install(HttpClient httpClient) {
        HttpClientEngine.DefaultImpls.install(this, httpClient);
    }

    public CoroutineContext getCoroutineContext() {
        return (CoroutineContext) this.coroutineContext$delegate.getValue();
    }

    /* access modifiers changed from: protected */
    public final Object createCallContext(Continuation<? super CoroutineContext> continuation) {
        CompletableJob Job = JobKt.Job((Job) this.clientContext.get(Job.Key));
        CoroutineContext plus = getCoroutineContext().plus(Job);
        Job job = (Job) continuation.getContext().get(Job.Key);
        Job.invokeOnCompletion(new HttpClientJvmEngine$createCallContext$2(job != null ? Job.DefaultImpls.invokeOnCompletion$default(job, true, false, new HttpClientJvmEngine$createCallContext$onParentCancelCleanupHandle$1(plus), 2, (Object) null) : null));
        return plus;
    }

    public void close() {
        Job job = JobKt.getJob(this.clientContext);
        Intrinsics.checkNotNull(job, "null cannot be cast to non-null type kotlinx.coroutines.CompletableJob");
        ((CompletableJob) job).complete();
    }
}
