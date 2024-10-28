package io.ktor.client.plugins;

import io.ktor.util.logging.KtorSimpleLoggerJvmKt;
import kotlin.Metadata;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.Job;
import org.slf4j.Logger;

@Metadata(d1 = {"\u0000\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002\"\u0012\u0010\u0000\u001a\u00060\u0001j\u0002`\u0002X\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"LOGGER", "Lorg/slf4j/Logger;", "Lio/ktor/util/logging/Logger;", "attachToClientEngineJob", "", "requestJob", "Lkotlinx/coroutines/CompletableJob;", "clientEngineJob", "Lkotlinx/coroutines/Job;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpRequestLifecycle.kt */
public final class HttpRequestLifecycleKt {
    /* access modifiers changed from: private */
    public static final Logger LOGGER = KtorSimpleLoggerJvmKt.KtorSimpleLogger("io.ktor.client.plugins.HttpRequestLifecycle");

    /* access modifiers changed from: private */
    public static final void attachToClientEngineJob(CompletableJob completableJob, Job job) {
        completableJob.invokeOnCompletion(new HttpRequestLifecycleKt$attachToClientEngineJob$1(job.invokeOnCompletion(new HttpRequestLifecycleKt$attachToClientEngineJob$handler$1(completableJob))));
    }
}
