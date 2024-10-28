package io.ktor.utils.io.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlinx.coroutines.CompletableJob;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0000\b\u0000\u0018\u00002\u00020\u0010B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J\u0017\u0010\u0006\u001a\u00020\u00052\b\u0010\u0004\u001a\u0004\u0018\u00010\u0003¢\u0006\u0004\b\u0006\u0010\u0007J\r\u0010\b\u001a\u00020\u0005¢\u0006\u0004\b\b\u0010\u0002J!\u0010\f\u001a\u00020\u00052\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@ø\u0001\u0000¢\u0006\u0004\b\f\u0010\rJ!\u0010\u000e\u001a\u00020\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tH@ø\u0001\u0000¢\u0006\u0004\b\u000e\u0010\r\u0002\u0004\n\u0002\b\u0019¨\u0006\u000f"}, d2 = {"Lio/ktor/utils/io/internal/AwaitingSlot;", "<init>", "()V", "", "cause", "", "cancel", "(Ljava/lang/Throwable;)V", "resume", "Lkotlin/Function0;", "", "sleepCondition", "sleep", "(Lkotlin/jvm/functions/Function0;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "trySuspend", "ktor-io", ""}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AwaitingSlot.kt */
public final class AwaitingSlot {
    private static final /* synthetic */ AtomicReferenceFieldUpdater suspension$FU = AtomicReferenceFieldUpdater.newUpdater(AwaitingSlot.class, Object.class, "suspension");
    private volatile /* synthetic */ Object suspension = null;

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x004d  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0050  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object sleep(kotlin.jvm.functions.Function0<java.lang.Boolean> r5, kotlin.coroutines.Continuation<? super kotlin.Unit> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.utils.io.internal.AwaitingSlot$sleep$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.utils.io.internal.AwaitingSlot$sleep$1 r0 = (io.ktor.utils.io.internal.AwaitingSlot$sleep$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.internal.AwaitingSlot$sleep$1 r0 = new io.ktor.utils.io.internal.AwaitingSlot$sleep$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.internal.AwaitingSlot r5 = (io.ktor.utils.io.internal.AwaitingSlot) r5
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0045
        L_0x002e:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r6)
            r0.L$0 = r4
            r0.label = r3
            java.lang.Object r6 = r4.trySuspend(r5, r0)
            if (r6 != r1) goto L_0x0044
            return r1
        L_0x0044:
            r5 = r4
        L_0x0045:
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x0050
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        L_0x0050:
            r5.resume()
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.AwaitingSlot.sleep(kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final void resume() {
        CompletableJob completableJob = (CompletableJob) suspension$FU.getAndSet(this, (Object) null);
        if (completableJob != null) {
            completableJob.complete();
        }
    }

    public final void cancel(Throwable th) {
        CompletableJob completableJob = (CompletableJob) suspension$FU.getAndSet(this, (Object) null);
        if (completableJob != null) {
            if (th != null) {
                completableJob.completeExceptionally(th);
            } else {
                completableJob.complete();
            }
        }
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x002b, code lost:
        if (r6 != 0) goto L_0x005e;
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object trySuspend(kotlin.jvm.functions.Function0<java.lang.Boolean> r6, kotlin.coroutines.Continuation<? super java.lang.Boolean> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1 r0 = (io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1 r0 = new io.ktor.utils.io.internal.AwaitingSlot$trySuspend$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            int r6 = r0.I$0
            kotlin.ResultKt.throwOnFailure(r7)
            if (r6 == 0) goto L_0x005d
            goto L_0x005e
        L_0x002e:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0036:
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = 0
            kotlinx.coroutines.CompletableJob r2 = kotlinx.coroutines.JobKt.Job$default((kotlinx.coroutines.Job) r7, (int) r3, (java.lang.Object) r7)
            java.util.concurrent.atomic.AtomicReferenceFieldUpdater r4 = suspension$FU
            boolean r7 = androidx.concurrent.futures.AbstractResolvableFuture$SafeAtomicHelper$$ExternalSyntheticBackportWithForwarding0.m(r4, r5, r7, r2)
            if (r7 == 0) goto L_0x005d
            java.lang.Object r6 = r6.invoke()
            java.lang.Boolean r6 = (java.lang.Boolean) r6
            boolean r6 = r6.booleanValue()
            if (r6 == 0) goto L_0x005d
            r0.I$0 = r3
            r0.label = r3
            java.lang.Object r6 = r2.join(r0)
            if (r6 != r1) goto L_0x005e
            return r1
        L_0x005d:
            r3 = 0
        L_0x005e:
            java.lang.Boolean r6 = kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r3)
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.utils.io.internal.AwaitingSlot.trySuspend(kotlin.jvm.functions.Function0, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
