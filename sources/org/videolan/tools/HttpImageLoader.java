package org.videolan.tools;

import android.graphics.Bitmap;
import androidx.collection.SimpleArrayMap;
import kotlin.Metadata;
import kotlinx.coroutines.CompletableDeferred;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000b\u001a\u00020\u0005H@¢\u0006\u0002\u0010\fR\"\u0010\u0003\u001a\u0016\u0012\u0004\u0012\u00020\u0005\u0012\f\u0012\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00060\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/videolan/tools/HttpImageLoader;", "", "()V", "currentJobs", "Landroidx/collection/SimpleArrayMap;", "", "Lkotlinx/coroutines/CompletableDeferred;", "Landroid/graphics/Bitmap;", "jobsLocker", "Lkotlinx/coroutines/sync/Mutex;", "downloadBitmap", "imageUrl", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "tools_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: HttpImageLoader.kt */
public final class HttpImageLoader {
    public static final HttpImageLoader INSTANCE = new HttpImageLoader();
    /* access modifiers changed from: private */
    public static final SimpleArrayMap<String, CompletableDeferred<Bitmap>> currentJobs = new SimpleArrayMap<>();
    /* access modifiers changed from: private */
    public static final Mutex jobsLocker = MutexKt.Mutex$default(false, 1, (Object) null);

    private HttpImageLoader() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009b  */
    /* JADX WARNING: Removed duplicated region for block: B:35:0x00ab  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00b4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x00e4 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:47:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00f3 A[Catch:{ all -> 0x0100 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0028  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object downloadBitmap(java.lang.String r9, kotlin.coroutines.Continuation<? super android.graphics.Bitmap> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof org.videolan.tools.HttpImageLoader$downloadBitmap$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            org.videolan.tools.HttpImageLoader$downloadBitmap$1 r0 = (org.videolan.tools.HttpImageLoader$downloadBitmap$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            org.videolan.tools.HttpImageLoader$downloadBitmap$1 r0 = new org.videolan.tools.HttpImageLoader$downloadBitmap$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x006a
            if (r2 == r6) goto L_0x005c
            if (r2 == r5) goto L_0x0054
            if (r2 == r4) goto L_0x004b
            if (r2 != r3) goto L_0x0043
            java.lang.Object r9 = r0.L$3
            kotlinx.coroutines.sync.Mutex r9 = (kotlinx.coroutines.sync.Mutex) r9
            java.lang.Object r1 = r0.L$2
            android.graphics.Bitmap r1 = (android.graphics.Bitmap) r1
            java.lang.Object r2 = r0.L$1
            java.lang.Object r0 = r0.L$0
            java.lang.String r0 = (java.lang.String) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00e9
        L_0x0043:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x004b:
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00cf
        L_0x0054:
            java.lang.Object r9 = r0.L$0
            java.lang.String r9 = (java.lang.String) r9
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x00a8
        L_0x005c:
            java.lang.Object r9 = r0.L$1
            kotlinx.coroutines.sync.Mutex r9 = (kotlinx.coroutines.sync.Mutex) r9
            java.lang.Object r2 = r0.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r10)
            r10 = r9
            r9 = r2
            goto L_0x0084
        L_0x006a:
            kotlin.ResultKt.throwOnFailure(r10)
            org.videolan.tools.BitmapCache r10 = org.videolan.tools.BitmapCache.INSTANCE
            android.graphics.Bitmap r10 = r10.getBitmapFromMemCache((java.lang.String) r9)
            if (r10 != 0) goto L_0x00b2
            kotlinx.coroutines.sync.Mutex r10 = jobsLocker
            r0.L$0 = r9
            r0.L$1 = r10
            r0.label = r6
            java.lang.Object r2 = r10.lock(r7, r0)
            if (r2 != r1) goto L_0x0084
            return r1
        L_0x0084:
            androidx.collection.SimpleArrayMap<java.lang.String, kotlinx.coroutines.CompletableDeferred<android.graphics.Bitmap>> r2 = currentJobs     // Catch:{ all -> 0x00ad }
            java.lang.Object r2 = r2.get(r9)     // Catch:{ all -> 0x00ad }
            kotlinx.coroutines.CompletableDeferred r2 = (kotlinx.coroutines.CompletableDeferred) r2     // Catch:{ all -> 0x00ad }
            if (r2 == 0) goto L_0x0095
            boolean r6 = r2.isActive()     // Catch:{ all -> 0x00ad }
            if (r6 == 0) goto L_0x0095
            goto L_0x0096
        L_0x0095:
            r2 = r7
        L_0x0096:
            r10.unlock(r7)
            if (r2 == 0) goto L_0x00ab
            r0.L$0 = r9
            r0.L$1 = r7
            r0.label = r5
            java.lang.Object r10 = r2.await(r0)
            if (r10 != r1) goto L_0x00a8
            return r1
        L_0x00a8:
            android.graphics.Bitmap r10 = (android.graphics.Bitmap) r10
            goto L_0x00b2
        L_0x00ab:
            r10 = r7
            goto L_0x00b2
        L_0x00ad:
            r9 = move-exception
            r10.unlock(r7)
            throw r9
        L_0x00b2:
            if (r10 == 0) goto L_0x00b5
            return r10
        L_0x00b5:
            kotlinx.coroutines.CoroutineDispatcher r10 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r10 = (kotlin.coroutines.CoroutineContext) r10
            org.videolan.tools.HttpImageLoader$downloadBitmap$2 r2 = new org.videolan.tools.HttpImageLoader$downloadBitmap$2
            r2.<init>(r9, r7)
            kotlin.jvm.functions.Function2 r2 = (kotlin.jvm.functions.Function2) r2
            r0.L$0 = r9
            r0.L$1 = r7
            r0.label = r4
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r10, r2, r0)
            if (r10 != r1) goto L_0x00cf
            return r1
        L_0x00cf:
            r2 = r10
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            kotlinx.coroutines.sync.Mutex r4 = jobsLocker
            r0.L$0 = r9
            r0.L$1 = r10
            r0.L$2 = r2
            r0.L$3 = r4
            r0.label = r3
            java.lang.Object r0 = r4.lock(r7, r0)
            if (r0 != r1) goto L_0x00e5
            return r1
        L_0x00e5:
            r0 = r9
            r1 = r2
            r9 = r4
            r2 = r10
        L_0x00e9:
            androidx.collection.SimpleArrayMap<java.lang.String, kotlinx.coroutines.CompletableDeferred<android.graphics.Bitmap>> r10 = currentJobs     // Catch:{ all -> 0x0100 }
            java.lang.Object r10 = r10.get(r0)     // Catch:{ all -> 0x0100 }
            kotlinx.coroutines.CompletableDeferred r10 = (kotlinx.coroutines.CompletableDeferred) r10     // Catch:{ all -> 0x0100 }
            if (r10 == 0) goto L_0x00fa
            boolean r10 = r10.complete(r1)     // Catch:{ all -> 0x0100 }
            kotlin.coroutines.jvm.internal.Boxing.boxBoolean(r10)     // Catch:{ all -> 0x0100 }
        L_0x00fa:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0100 }
            r9.unlock(r7)
            return r2
        L_0x0100:
            r10 = move-exception
            r9.unlock(r7)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.HttpImageLoader.downloadBitmap(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
