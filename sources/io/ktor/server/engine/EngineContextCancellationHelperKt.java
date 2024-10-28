package io.ktor.server.engine;

import androidx.lifecycle.CoroutineLiveDataKt;
import io.ktor.server.engine.internal.ApplicationUtilsJvmKt;
import io.ktor.util.InternalAPI;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableJob;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.GlobalScope;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.JobKt;

@Metadata(d1 = {"\u0000.\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0002\b\u0002\u001a2\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u001c\u0010\u0003\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0004H\u0007ø\u0001\u0000¢\u0006\u0002\u0010\b\u001a\u001e\u0010\t\u001a\u00020\u0001*\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\f\u0002\u0004\n\u0002\b\u0019¨\u0006\u000e"}, d2 = {"launchOnCancellation", "Lkotlinx/coroutines/CompletableJob;", "Lkotlinx/coroutines/Job;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "(Lkotlinx/coroutines/Job;Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/CompletableJob;", "stopServerOnCancellation", "Lio/ktor/server/engine/ApplicationEngine;", "gracePeriodMillis", "", "timeoutMillis", "ktor-server-host-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: EngineContextCancellationHelper.kt */
public final class EngineContextCancellationHelperKt {
    public static /* synthetic */ CompletableJob stopServerOnCancellation$default(ApplicationEngine applicationEngine, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            j = 50;
        }
        if ((i & 2) != 0) {
            j2 = CoroutineLiveDataKt.DEFAULT_TIMEOUT;
        }
        return stopServerOnCancellation(applicationEngine, j, j2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:2:0x0019, code lost:
        r9 = launchOnCancellation(r0, new io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1(r9, r10, r12, (kotlin.coroutines.Continuation<? super io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1>) null));
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static final kotlinx.coroutines.CompletableJob stopServerOnCancellation(io.ktor.server.engine.ApplicationEngine r9, long r10, long r12) {
        /*
            java.lang.String r0 = "<this>"
            kotlin.jvm.internal.Intrinsics.checkNotNullParameter(r9, r0)
            io.ktor.server.engine.ApplicationEngineEnvironment r0 = r9.getEnvironment()
            kotlin.coroutines.CoroutineContext r0 = r0.getParentCoroutineContext()
            kotlinx.coroutines.Job$Key r1 = kotlinx.coroutines.Job.Key
            kotlin.coroutines.CoroutineContext$Key r1 = (kotlin.coroutines.CoroutineContext.Key) r1
            kotlin.coroutines.CoroutineContext$Element r0 = r0.get(r1)
            kotlinx.coroutines.Job r0 = (kotlinx.coroutines.Job) r0
            if (r0 == 0) goto L_0x002b
            io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1 r8 = new io.ktor.server.engine.EngineContextCancellationHelperKt$stopServerOnCancellation$1
            r7 = 0
            r1 = r8
            r2 = r9
            r3 = r10
            r5 = r12
            r1.<init>(r2, r3, r5, r7)
            kotlin.jvm.functions.Function1 r8 = (kotlin.jvm.functions.Function1) r8
            kotlinx.coroutines.CompletableJob r9 = launchOnCancellation(r0, r8)
            if (r9 != 0) goto L_0x0031
        L_0x002b:
            r9 = 1
            r10 = 0
            kotlinx.coroutines.CompletableJob r9 = kotlinx.coroutines.JobKt.Job$default((kotlinx.coroutines.Job) r10, (int) r9, (java.lang.Object) r10)
        L_0x0031:
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.engine.EngineContextCancellationHelperKt.stopServerOnCancellation(io.ktor.server.engine.ApplicationEngine, long, long):kotlinx.coroutines.CompletableJob");
    }

    @InternalAPI
    public static final CompletableJob launchOnCancellation(Job job, Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(job, "<this>");
        Intrinsics.checkNotNullParameter(function1, "block");
        CompletableJob Job = JobKt.Job(job);
        Job unused = BuildersKt__Builders_commonKt.launch$default(GlobalScope.INSTANCE, job.plus(ApplicationUtilsJvmKt.getIOBridge(Dispatchers.INSTANCE)), (CoroutineStart) null, new EngineContextCancellationHelperKt$launchOnCancellation$1(Job, function1, (Continuation<? super EngineContextCancellationHelperKt$launchOnCancellation$1>) null), 2, (Object) null);
        return Job;
    }
}
