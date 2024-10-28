package androidx.paging;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.CompletableDeferredKt;
import kotlinx.coroutines.Job;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\b\u0000\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003Be\u0012[\u0010\u0004\u001aW\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ#\u0010\u0019\u001a\u00020\r2\u0006\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0003H@ø\u0001\u0000¢\u0006\u0002\u0010\u001dR\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\r0\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0004¢\u0006\u0002\n\u0000Rh\u0010\u0004\u001aW\b\u0001\u0012\u0013\u0012\u00118\u0000¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b\u0012\u0013\u0012\u00118\u0001¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\t\u0012\u0013\u0012\u00110\n¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\f\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0005X\u0004ø\u0001\u0000¢\u0006\u0004\n\u0002\u0010\u0013R\u001c\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\u00100\u0015X\u0004¢\u0006\u0004\n\u0002\u0010\u0016R\u0018\u0010\u0017\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0015X\u0004¢\u0006\u0004\n\u0002\u0010\u0018\u0002\u0004\n\u0002\b\u0019¨\u0006\u001e"}, d2 = {"Landroidx/paging/UnbatchedFlowCombiner;", "T1", "T2", "", "send", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "t1", "t2", "Landroidx/paging/CombineSource;", "updateFrom", "Lkotlin/coroutines/Continuation;", "", "(Lkotlin/jvm/functions/Function4;)V", "initialDispatched", "Lkotlinx/coroutines/CompletableDeferred;", "lock", "Lkotlinx/coroutines/sync/Mutex;", "Lkotlin/jvm/functions/Function4;", "valueReceived", "", "[Lkotlinx/coroutines/CompletableDeferred;", "values", "[Ljava/lang/Object;", "onNext", "index", "", "value", "(ILjava/lang/Object;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FlowExt.kt */
public final class UnbatchedFlowCombiner<T1, T2> {
    private final CompletableDeferred<Unit> initialDispatched = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
    private final Mutex lock;
    private final Function4<T1, T2, CombineSource, Continuation<? super Unit>, Object> send;
    private final CompletableDeferred<Unit>[] valueReceived;
    private final Object[] values;

    public UnbatchedFlowCombiner(Function4<? super T1, ? super T2, ? super CombineSource, ? super Continuation<? super Unit>, ? extends Object> function4) {
        Intrinsics.checkNotNullParameter(function4, "send");
        this.send = function4;
        this.lock = MutexKt.Mutex$default(false, 1, (Object) null);
        CompletableDeferred<Unit>[] completableDeferredArr = new CompletableDeferred[2];
        for (int i = 0; i < 2; i++) {
            completableDeferredArr[i] = CompletableDeferredKt.CompletableDeferred$default((Job) null, 1, (Object) null);
        }
        this.valueReceived = completableDeferredArr;
        Object[] objArr = new Object[2];
        for (int i2 = 0; i2 < 2; i2++) {
            objArr[i2] = FlowExtKt.NULL;
        }
        this.values = objArr;
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v14, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r4v8, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00a8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00a9  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00b3 A[Catch:{ all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00c9 A[Catch:{ all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00d8 A[Catch:{ all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00db A[Catch:{ all -> 0x0109 }] */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00f8 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x00f9  */
    /* JADX WARNING: Removed duplicated region for block: B:63:0x00c0 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object onNext(int r17, java.lang.Object r18, kotlin.coroutines.Continuation<? super kotlin.Unit> r19) {
        /*
            r16 = this;
            r1 = r16
            r0 = r17
            r2 = r19
            boolean r3 = r2 instanceof androidx.paging.UnbatchedFlowCombiner$onNext$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            androidx.paging.UnbatchedFlowCombiner$onNext$1 r3 = (androidx.paging.UnbatchedFlowCombiner$onNext$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            androidx.paging.UnbatchedFlowCombiner$onNext$1 r3 = new androidx.paging.UnbatchedFlowCombiner$onNext$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 3
            r7 = 2
            r8 = 0
            r9 = 1
            if (r5 == 0) goto L_0x006a
            if (r5 == r9) goto L_0x005e
            if (r5 == r7) goto L_0x004c
            if (r5 != r6) goto L_0x0044
            java.lang.Object r0 = r3.L$1
            r4 = r0
            kotlinx.coroutines.sync.Mutex r4 = (kotlinx.coroutines.sync.Mutex) r4
            java.lang.Object r0 = r3.L$0
            androidx.paging.UnbatchedFlowCombiner r0 = (androidx.paging.UnbatchedFlowCombiner) r0
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0041 }
            goto L_0x00fa
        L_0x0041:
            r0 = move-exception
            goto L_0x010b
        L_0x0044:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x004c:
            int r0 = r3.I$0
            java.lang.Object r5 = r3.L$2
            kotlinx.coroutines.sync.Mutex r5 = (kotlinx.coroutines.sync.Mutex) r5
            java.lang.Object r7 = r3.L$1
            java.lang.Object r10 = r3.L$0
            androidx.paging.UnbatchedFlowCombiner r10 = (androidx.paging.UnbatchedFlowCombiner) r10
            kotlin.ResultKt.throwOnFailure(r2)
        L_0x005b:
            r2 = r0
            r0 = r10
            goto L_0x00ac
        L_0x005e:
            int r0 = r3.I$0
            java.lang.Object r5 = r3.L$1
            java.lang.Object r10 = r3.L$0
            androidx.paging.UnbatchedFlowCombiner r10 = (androidx.paging.UnbatchedFlowCombiner) r10
            kotlin.ResultKt.throwOnFailure(r2)
            goto L_0x0096
        L_0x006a:
            kotlin.ResultKt.throwOnFailure(r2)
            kotlinx.coroutines.CompletableDeferred<kotlin.Unit>[] r2 = r1.valueReceived
            r2 = r2[r0]
            boolean r2 = r2.isCompleted()
            if (r2 == 0) goto L_0x008a
            kotlinx.coroutines.CompletableDeferred<kotlin.Unit> r2 = r1.initialDispatched
            r3.L$0 = r1
            r5 = r18
            r3.L$1 = r5
            r3.I$0 = r0
            r3.label = r9
            java.lang.Object r2 = r2.await(r3)
            if (r2 != r4) goto L_0x0095
            return r4
        L_0x008a:
            r5 = r18
            kotlinx.coroutines.CompletableDeferred<kotlin.Unit>[] r2 = r1.valueReceived
            r2 = r2[r0]
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            r2.complete(r10)
        L_0x0095:
            r10 = r1
        L_0x0096:
            kotlinx.coroutines.sync.Mutex r2 = r10.lock
            r3.L$0 = r10
            r3.L$1 = r5
            r3.L$2 = r2
            r3.I$0 = r0
            r3.label = r7
            java.lang.Object r7 = r2.lock(r8, r3)
            if (r7 != r4) goto L_0x00a9
            return r4
        L_0x00a9:
            r7 = r5
            r5 = r2
            goto L_0x005b
        L_0x00ac:
            java.lang.Object[] r10 = r0.values     // Catch:{ all -> 0x0109 }
            int r11 = r10.length     // Catch:{ all -> 0x0109 }
            r12 = 0
            r13 = 0
        L_0x00b1:
            if (r13 >= r11) goto L_0x00c0
            r14 = r10[r13]     // Catch:{ all -> 0x0109 }
            java.lang.Object r15 = androidx.paging.FlowExtKt.NULL     // Catch:{ all -> 0x0109 }
            if (r14 != r15) goto L_0x00bd
            r10 = 1
            goto L_0x00c1
        L_0x00bd:
            int r13 = r13 + 1
            goto L_0x00b1
        L_0x00c0:
            r10 = 0
        L_0x00c1:
            java.lang.Object[] r11 = r0.values     // Catch:{ all -> 0x0109 }
            r11[r2] = r7     // Catch:{ all -> 0x0109 }
            int r7 = r11.length     // Catch:{ all -> 0x0109 }
            r13 = 0
        L_0x00c7:
            if (r13 >= r7) goto L_0x00d6
            r14 = r11[r13]     // Catch:{ all -> 0x0109 }
            java.lang.Object r15 = androidx.paging.FlowExtKt.NULL     // Catch:{ all -> 0x0109 }
            if (r14 != r15) goto L_0x00d3
            r4 = r5
            goto L_0x0101
        L_0x00d3:
            int r13 = r13 + 1
            goto L_0x00c7
        L_0x00d6:
            if (r10 == 0) goto L_0x00db
            androidx.paging.CombineSource r2 = androidx.paging.CombineSource.INITIAL     // Catch:{ all -> 0x0109 }
            goto L_0x00e2
        L_0x00db:
            if (r2 != 0) goto L_0x00e0
            androidx.paging.CombineSource r2 = androidx.paging.CombineSource.RECEIVER     // Catch:{ all -> 0x0109 }
            goto L_0x00e2
        L_0x00e0:
            androidx.paging.CombineSource r2 = androidx.paging.CombineSource.OTHER     // Catch:{ all -> 0x0109 }
        L_0x00e2:
            kotlin.jvm.functions.Function4<T1, T2, androidx.paging.CombineSource, kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r7 = r0.send     // Catch:{ all -> 0x0109 }
            java.lang.Object[] r10 = r0.values     // Catch:{ all -> 0x0109 }
            r11 = r10[r12]     // Catch:{ all -> 0x0109 }
            r9 = r10[r9]     // Catch:{ all -> 0x0109 }
            r3.L$0 = r0     // Catch:{ all -> 0x0109 }
            r3.L$1 = r5     // Catch:{ all -> 0x0109 }
            r3.L$2 = r8     // Catch:{ all -> 0x0109 }
            r3.label = r6     // Catch:{ all -> 0x0109 }
            java.lang.Object r2 = r7.invoke(r11, r9, r2, r3)     // Catch:{ all -> 0x0109 }
            if (r2 != r4) goto L_0x00f9
            return r4
        L_0x00f9:
            r4 = r5
        L_0x00fa:
            kotlinx.coroutines.CompletableDeferred<kotlin.Unit> r0 = r0.initialDispatched     // Catch:{ all -> 0x0041 }
            kotlin.Unit r2 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0041 }
            r0.complete(r2)     // Catch:{ all -> 0x0041 }
        L_0x0101:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0041 }
            r4.unlock(r8)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0109:
            r0 = move-exception
            r4 = r5
        L_0x010b:
            r4.unlock(r8)
            goto L_0x0110
        L_0x010f:
            throw r0
        L_0x0110:
            goto L_0x010f
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.UnbatchedFlowCombiner.onNext(int, java.lang.Object, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
