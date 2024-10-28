package io.ktor.network.util;

import io.ktor.http.ContentDisposition;
import io.ktor.server.auth.OAuth2RequestParameters;
import io.netty.handler.codec.rtsp.RtspHeaders;
import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineName;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\f\b\u0000\u0018\u00002\u00020\fBN\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u0005\u0012\u0006\u0010\b\u001a\u00020\u0007\u0012\u001c\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\tø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\u000fJ\r\u0010\u0010\u001a\u00020\u000b¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0013\u001a\u0004\u0018\u00010\u0012H\u0002¢\u0006\u0004\b\u0013\u0010\u0014J\r\u0010\u0015\u001a\u00020\u000b¢\u0006\u0004\b\u0015\u0010\u0011J\r\u0010\u0016\u001a\u00020\u000b¢\u0006\u0004\b\u0016\u0010\u0011R\u001a\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00030\u00058\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0006\u0010\u0017R\u0014\u0010\u0002\u001a\u00020\u00018\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0002\u0010\u0018R-\u0010\r\u001a\u0018\b\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n\u0012\u0006\u0012\u0004\u0018\u00010\f0\t8\u0002X\u0004ø\u0001\u0000¢\u0006\u0006\n\u0004\b\r\u0010\u0019R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001aR\u0014\u0010\u0004\u001a\u00020\u00038\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0004\u0010\u001bR\u0018\u0010\u001c\u001a\u0004\u0018\u00010\u00128\u0002@\u0002X\u000e¢\u0006\u0006\n\u0004\b\u001c\u0010\u001d\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Lio/ktor/network/util/Timeout;", "", "name", "", "timeoutMs", "Lkotlin/Function0;", "clock", "Lkotlinx/coroutines/CoroutineScope;", "scope", "Lkotlin/Function1;", "Lkotlin/coroutines/Continuation;", "", "", "onTimeout", "<init>", "(Ljava/lang/String;JLkotlin/jvm/functions/Function0;Lkotlinx/coroutines/CoroutineScope;Lkotlin/jvm/functions/Function1;)V", "finish", "()V", "Lkotlinx/coroutines/Job;", "initTimeoutJob", "()Lkotlinx/coroutines/Job;", "start", "stop", "Lkotlin/jvm/functions/Function0;", "Ljava/lang/String;", "Lkotlin/jvm/functions/Function1;", "Lkotlinx/coroutines/CoroutineScope;", "J", "workerJob", "Lkotlinx/coroutines/Job;", "ktor-network"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: Utils.kt */
public final class Timeout {
    /* access modifiers changed from: private */
    public final Function0<Long> clock;
    volatile /* synthetic */ int isStarted = 0;
    volatile /* synthetic */ long lastActivityTime = 0;
    private final String name;
    /* access modifiers changed from: private */
    public final Function1<Continuation<? super Unit>, Object> onTimeout;
    private final CoroutineScope scope;
    /* access modifiers changed from: private */
    public final long timeoutMs;
    private Job workerJob = initTimeoutJob();

    public Timeout(String str, long j, Function0<Long> function0, CoroutineScope coroutineScope, Function1<? super Continuation<? super Unit>, ? extends Object> function1) {
        Intrinsics.checkNotNullParameter(str, ContentDisposition.Parameters.Name);
        Intrinsics.checkNotNullParameter(function0, RtspHeaders.Values.CLOCK);
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        Intrinsics.checkNotNullParameter(function1, "onTimeout");
        this.name = str;
        this.timeoutMs = j;
        this.clock = function0;
        this.scope = coroutineScope;
        this.onTimeout = function1;
    }

    public final void start() {
        this.lastActivityTime = this.clock.invoke().longValue();
        this.isStarted = 1;
    }

    public final void stop() {
        this.isStarted = 0;
    }

    public final void finish() {
        Job job = this.workerJob;
        if (job != null) {
            Job.DefaultImpls.cancel$default(job, (CancellationException) null, 1, (Object) null);
        }
    }

    private final Job initTimeoutJob() {
        if (this.timeoutMs == Long.MAX_VALUE) {
            return null;
        }
        CoroutineScope coroutineScope = this.scope;
        CoroutineContext coroutineContext = coroutineScope.getCoroutineContext();
        return BuildersKt__Builders_commonKt.launch$default(coroutineScope, coroutineContext.plus(new CoroutineName("Timeout " + this.name)), (CoroutineStart) null, new Timeout$initTimeoutJob$1(this, (Continuation<? super Timeout$initTimeoutJob$1>) null), 2, (Object) null);
    }
}
