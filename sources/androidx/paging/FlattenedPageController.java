package androidx.paging;

import androidx.paging.PageEvent;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0002B\u0005¢\u0006\u0002\u0010\u0003J\u000e\u0010\n\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u000bJ#\u0010\f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000e0\rH@ø\u0001\u0000¢\u0006\u0002\u0010\u0010J%\u0010\u0011\u001a\u00020\u00122\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f0\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u000e¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006\u0015"}, d2 = {"Landroidx/paging/FlattenedPageController;", "T", "", "()V", "list", "Landroidx/paging/FlattenedPageEventStorage;", "lock", "Lkotlinx/coroutines/sync/Mutex;", "maxEventIndex", "", "getCachedEvent", "Landroidx/paging/PageEvent$Insert;", "getStateAsEvents", "", "Lkotlin/collections/IndexedValue;", "Landroidx/paging/PageEvent;", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "record", "", "event", "(Lkotlin/collections/IndexedValue;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "paging-common"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CachedPageEventFlow.kt */
final class FlattenedPageController<T> {
    private final FlattenedPageEventStorage<T> list = new FlattenedPageEventStorage<>();
    private final Mutex lock = MutexKt.Mutex$default(false, 1, (Object) null);
    private int maxEventIndex = -1;

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object record(kotlin.collections.IndexedValue<? extends androidx.paging.PageEvent<T>> r6, kotlin.coroutines.Continuation<? super kotlin.Unit> r7) {
        /*
            r5 = this;
            boolean r0 = r7 instanceof androidx.paging.FlattenedPageController$record$1
            if (r0 == 0) goto L_0x0014
            r0 = r7
            androidx.paging.FlattenedPageController$record$1 r0 = (androidx.paging.FlattenedPageController$record$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r7 = r0.label
            int r7 = r7 - r2
            r0.label = r7
            goto L_0x0019
        L_0x0014:
            androidx.paging.FlattenedPageController$record$1 r0 = new androidx.paging.FlattenedPageController$record$1
            r0.<init>(r5, r7)
        L_0x0019:
            java.lang.Object r7 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0041
            if (r2 != r4) goto L_0x0039
            java.lang.Object r6 = r0.L$2
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r1 = r0.L$1
            kotlin.collections.IndexedValue r1 = (kotlin.collections.IndexedValue) r1
            java.lang.Object r0 = r0.L$0
            androidx.paging.FlattenedPageController r0 = (androidx.paging.FlattenedPageController) r0
            kotlin.ResultKt.throwOnFailure(r7)
            r7 = r6
            r6 = r1
            goto L_0x0056
        L_0x0039:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0041:
            kotlin.ResultKt.throwOnFailure(r7)
            kotlinx.coroutines.sync.Mutex r7 = r5.lock
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.label = r4
            java.lang.Object r0 = r7.lock(r3, r0)
            if (r0 != r1) goto L_0x0055
            return r1
        L_0x0055:
            r0 = r5
        L_0x0056:
            int r1 = r6.getIndex()     // Catch:{ all -> 0x006f }
            r0.maxEventIndex = r1     // Catch:{ all -> 0x006f }
            androidx.paging.FlattenedPageEventStorage<T> r0 = r0.list     // Catch:{ all -> 0x006f }
            java.lang.Object r6 = r6.getValue()     // Catch:{ all -> 0x006f }
            androidx.paging.PageEvent r6 = (androidx.paging.PageEvent) r6     // Catch:{ all -> 0x006f }
            r0.add(r6)     // Catch:{ all -> 0x006f }
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x006f }
            r7.unlock(r3)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x006f:
            r6 = move-exception
            r7.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlattenedPageController.record(kotlin.collections.IndexedValue, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003b  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x0077 A[Catch:{ all -> 0x0095 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object getStateAsEvents(kotlin.coroutines.Continuation<? super java.util.List<? extends kotlin.collections.IndexedValue<? extends androidx.paging.PageEvent<T>>>> r9) {
        /*
            r8 = this;
            boolean r0 = r9 instanceof androidx.paging.FlattenedPageController$getStateAsEvents$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            androidx.paging.FlattenedPageController$getStateAsEvents$1 r0 = (androidx.paging.FlattenedPageController$getStateAsEvents$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            androidx.paging.FlattenedPageController$getStateAsEvents$1 r0 = new androidx.paging.FlattenedPageController$getStateAsEvents$1
            r0.<init>(r8, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003b
            if (r2 != r4) goto L_0x0033
            java.lang.Object r1 = r0.L$1
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            java.lang.Object r0 = r0.L$0
            androidx.paging.FlattenedPageController r0 = (androidx.paging.FlattenedPageController) r0
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x004f
        L_0x0033:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x003b:
            kotlin.ResultKt.throwOnFailure(r9)
            kotlinx.coroutines.sync.Mutex r9 = r8.lock
            r0.L$0 = r8
            r0.L$1 = r9
            r0.label = r4
            java.lang.Object r0 = r9.lock(r3, r0)
            if (r0 != r1) goto L_0x004d
            return r1
        L_0x004d:
            r0 = r8
            r1 = r9
        L_0x004f:
            androidx.paging.FlattenedPageEventStorage<T> r9 = r0.list     // Catch:{ all -> 0x0095 }
            java.util.List r9 = r9.getAsEvents()     // Catch:{ all -> 0x0095 }
            int r0 = r0.maxEventIndex     // Catch:{ all -> 0x0095 }
            int r2 = r9.size()     // Catch:{ all -> 0x0095 }
            int r0 = r0 - r2
            int r0 = r0 + r4
            java.lang.Iterable r9 = (java.lang.Iterable) r9     // Catch:{ all -> 0x0095 }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ all -> 0x0095 }
            r4 = 10
            int r4 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r9, r4)     // Catch:{ all -> 0x0095 }
            r2.<init>(r4)     // Catch:{ all -> 0x0095 }
            java.util.Collection r2 = (java.util.Collection) r2     // Catch:{ all -> 0x0095 }
            java.util.Iterator r9 = r9.iterator()     // Catch:{ all -> 0x0095 }
            r4 = 0
        L_0x0071:
            boolean r5 = r9.hasNext()     // Catch:{ all -> 0x0095 }
            if (r5 == 0) goto L_0x008f
            java.lang.Object r5 = r9.next()     // Catch:{ all -> 0x0095 }
            int r6 = r4 + 1
            if (r4 >= 0) goto L_0x0082
            kotlin.collections.CollectionsKt.throwIndexOverflow()     // Catch:{ all -> 0x0095 }
        L_0x0082:
            androidx.paging.PageEvent r5 = (androidx.paging.PageEvent) r5     // Catch:{ all -> 0x0095 }
            kotlin.collections.IndexedValue r7 = new kotlin.collections.IndexedValue     // Catch:{ all -> 0x0095 }
            int r4 = r4 + r0
            r7.<init>(r4, r5)     // Catch:{ all -> 0x0095 }
            r2.add(r7)     // Catch:{ all -> 0x0095 }
            r4 = r6
            goto L_0x0071
        L_0x008f:
            java.util.List r2 = (java.util.List) r2     // Catch:{ all -> 0x0095 }
            r1.unlock(r3)
            return r2
        L_0x0095:
            r9 = move-exception
            r1.unlock(r3)
            goto L_0x009b
        L_0x009a:
            throw r9
        L_0x009b:
            goto L_0x009a
        */
        throw new UnsupportedOperationException("Method not decompiled: androidx.paging.FlattenedPageController.getStateAsEvents(kotlin.coroutines.Continuation):java.lang.Object");
    }

    public final PageEvent.Insert<T> getCachedEvent() {
        PageEvent pageEvent = (PageEvent) CollectionsKt.firstOrNull(this.list.getAsEvents());
        if (pageEvent == null || !(pageEvent instanceof PageEvent.Insert)) {
            return null;
        }
        PageEvent.Insert<T> insert = (PageEvent.Insert) pageEvent;
        if (insert.getLoadType() == LoadType.REFRESH) {
            return insert;
        }
        return null;
    }
}
