package io.ktor.server.sessions;

import androidx.leanback.preference.LeanbackPreferenceDialogFragment;
import io.ktor.server.sessions.CacheReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0010\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u0002*\b\b\u0001\u0010\u0003*\u00020\u0002*\u001a\b\u0002\u0010\u0004 \u0001*\b\u0012\u0004\u0012\u0002H\u00030\u0005*\b\u0012\u0004\u0012\u0002H\u00010\u00062\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00030\u0007BR\u0012\"\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\t\u0012$\u0010\u000b\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\u0004\u0012\u00028\u00020\fø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\b\u0010\u001d\u001a\u00020\u001eH\u0002J\u0019\u0010\u001f\u001a\u00028\u00012\u0006\u0010 \u001a\u00028\u0000H@ø\u0001\u0000¢\u0006\u0002\u0010!J\u0017\u0010\"\u001a\u0004\u0018\u00018\u00012\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#J\u001d\u0010\"\u001a\u00020$2\u0006\u0010 \u001a\u00028\u00002\u0006\u0010%\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010&J\b\u0010'\u001a\u00020\u001eH\u0016J\u0017\u0010(\u001a\u0004\u0018\u00018\u00012\u0006\u0010 \u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010#R2\u0010\b\u001a\u001e\b\u0001\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\tø\u0001\u0000¢\u0006\n\n\u0002\u0010\u0011\u001a\u0004\b\u000f\u0010\u0010R\u001a\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00028\u00010\rX\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u0015\u001a\u00020\u00168BX\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R/\u0010\u000b\u001a \u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00010\r\u0012\u0004\u0012\u00028\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u0002\u0004\n\u0002\b\u0019¨\u0006)"}, d2 = {"Lio/ktor/server/sessions/ReferenceCache;", "K", "", "V", "R", "Ljava/lang/ref/Reference;", "Lio/ktor/server/sessions/CacheReference;", "Lio/ktor/server/sessions/Cache;", "calc", "Lkotlin/Function2;", "Lkotlin/coroutines/Continuation;", "wrapFunction", "Lkotlin/Function3;", "Ljava/lang/ref/ReferenceQueue;", "(Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)V", "getCalc", "()Lkotlin/jvm/functions/Function2;", "Lkotlin/jvm/functions/Function2;", "container", "Lio/ktor/server/sessions/BaseCache;", "queue", "workerThread", "Ljava/lang/Thread;", "getWorkerThread", "()Ljava/lang/Thread;", "workerThread$delegate", "Lkotlin/Lazy;", "getWrapFunction", "()Lkotlin/jvm/functions/Function3;", "forkThreadIfNeeded", "", "getOrCompute", "key", "(Ljava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "invalidate", "(Ljava/lang/Object;)Ljava/lang/Object;", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)Z", "invalidateAll", "peek", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheJvm.kt */
public class ReferenceCache<K, V, R extends Reference<V> & CacheReference<? extends K>> implements Cache<K, V> {
    private final Function2<K, Continuation<? super V>, Object> calc;
    /* access modifiers changed from: private */
    public final BaseCache<K, R> container = new BaseCache<>(new ReferenceCache$container$1(this, (Continuation<? super ReferenceCache$container$1>) null));
    /* access modifiers changed from: private */
    public final ReferenceQueue<V> queue = new ReferenceQueue<>();
    private final Lazy workerThread$delegate = LazyKt.lazy(new ReferenceCache$workerThread$2(this));
    private final Function3<K, V, ReferenceQueue<V>, R> wrapFunction;

    public Object getOrCompute(K k, Continuation<? super V> continuation) {
        return getOrCompute$suspendImpl(this, k, continuation);
    }

    public ReferenceCache(Function2<? super K, ? super Continuation<? super V>, ? extends Object> function2, Function3<? super K, ? super V, ? super ReferenceQueue<V>, ? extends R> function3) {
        Intrinsics.checkNotNullParameter(function2, "calc");
        Intrinsics.checkNotNullParameter(function3, "wrapFunction");
        this.calc = function2;
        this.wrapFunction = function3;
    }

    public final Function2<K, Continuation<? super V>, Object> getCalc() {
        return this.calc;
    }

    public final Function3<K, V, ReferenceQueue<V>, R> getWrapFunction() {
        return this.wrapFunction;
    }

    private final Thread getWorkerThread() {
        return (Thread) this.workerThread$delegate.getValue();
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0073 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static /* synthetic */ <K, V, R extends java.lang.ref.Reference<V> & io.ktor.server.sessions.CacheReference<? extends K>> java.lang.Object getOrCompute$suspendImpl(io.ktor.server.sessions.ReferenceCache<K, V, ? extends R> r5, K r6, kotlin.coroutines.Continuation<? super V> r7) {
        /*
            boolean r0 = r7 instanceof io.ktor.server.sessions.ReferenceCache$getOrCompute$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            io.ktor.server.sessions.ReferenceCache$getOrCompute$1 r0 = (io.ktor.server.sessions.ReferenceCache$getOrCompute$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            io.ktor.server.sessions.ReferenceCache$getOrCompute$1 r0 = new io.ktor.server.sessions.ReferenceCache$getOrCompute$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 == r4) goto L_0x0035
            if (r2 != r3) goto L_0x002d
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0072
        L_0x002d:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0035:
            java.lang.Object r6 = r0.L$1
            java.lang.Object r5 = r0.L$0
            io.ktor.server.sessions.ReferenceCache r5 = (io.ktor.server.sessions.ReferenceCache) r5
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0051
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r7)
            io.ktor.server.sessions.BaseCache<K, R> r7 = r5.container
            r0.L$0 = r5
            r0.L$1 = r6
            r0.label = r4
            java.lang.Object r7 = r7.getOrCompute(r6, r0)
            if (r7 != r1) goto L_0x0051
            return r1
        L_0x0051:
            java.lang.ref.Reference r7 = (java.lang.ref.Reference) r7
            java.lang.Object r2 = r7.get()
            if (r2 != 0) goto L_0x0073
            io.ktor.server.sessions.BaseCache<K, R> r2 = r5.container
            boolean r2 = r2.invalidate(r6, r7)
            if (r2 == 0) goto L_0x0064
            r7.enqueue()
        L_0x0064:
            r7 = 0
            r0.L$0 = r7
            r0.L$1 = r7
            r0.label = r3
            java.lang.Object r7 = r5.getOrCompute(r6, r0)
            if (r7 != r1) goto L_0x0072
            return r1
        L_0x0072:
            return r7
        L_0x0073:
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.ReferenceCache.getOrCompute$suspendImpl(io.ktor.server.sessions.ReferenceCache, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public V peek(K k) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Reference reference = (Reference) this.container.peek(k);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public V invalidate(K k) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Reference reference = (Reference) this.container.invalidate(k);
        if (reference != null) {
            return reference.get();
        }
        return null;
    }

    public boolean invalidate(K k, V v) {
        Intrinsics.checkNotNullParameter(k, LeanbackPreferenceDialogFragment.ARG_KEY);
        Intrinsics.checkNotNullParameter(v, "value");
        Reference reference = (Reference) this.container.peek(k);
        if (!Intrinsics.areEqual(reference != null ? reference.get() : null, (Object) v)) {
            return false;
        }
        reference.enqueue();
        return this.container.invalidate(k, reference);
    }

    public void invalidateAll() {
        this.container.invalidateAll();
    }

    /* access modifiers changed from: private */
    public final void forkThreadIfNeeded() {
        if (!getWorkerThread().isAlive()) {
            throw new IllegalStateException("Daemon thread is already dead");
        }
    }
}
