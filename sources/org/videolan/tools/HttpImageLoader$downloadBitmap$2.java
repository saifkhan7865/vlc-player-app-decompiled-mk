package org.videolan.tools;

import android.graphics.Bitmap;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "Landroid/graphics/Bitmap;", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.tools.HttpImageLoader$downloadBitmap$2", f = "HttpImageLoader.kt", i = {0}, l = {98}, m = "invokeSuspend", n = {"$this$withLock_u24default$iv"}, s = {"L$0"})
/* compiled from: HttpImageLoader.kt */
final class HttpImageLoader$downloadBitmap$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Bitmap>, Object> {
    final /* synthetic */ String $imageUrl;
    Object L$0;
    Object L$1;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    HttpImageLoader$downloadBitmap$2(String str, Continuation<? super HttpImageLoader$downloadBitmap$2> continuation) {
        super(2, continuation);
        this.$imageUrl = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new HttpImageLoader$downloadBitmap$2(this.$imageUrl, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Bitmap> continuation) {
        return ((HttpImageLoader$downloadBitmap$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v2, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v4, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v6, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v7, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v9, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v10, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v11, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v13, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v8, resolved type: java.io.InputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v14, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v15, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v20, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v25, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v26, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v27, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v28, resolved type: java.net.HttpURLConnection} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r3v29, resolved type: java.net.HttpURLConnection} */
    /* JADX WARNING: type inference failed for: r5v0, types: [android.graphics.Rect, kotlinx.coroutines.Job, java.lang.Object] */
    /* JADX WARNING: type inference failed for: r5v1 */
    /* JADX WARNING: type inference failed for: r5v3 */
    /* JADX WARNING: type inference failed for: r5v4 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x012b  */
    /* JADX WARNING: Removed duplicated region for block: B:71:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:73:? A[RETURN, SYNTHETIC] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:50:0x00f3=Splitter:B:50:0x00f3, B:57:0x010c=Splitter:B:57:0x010c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.String r0 = "null cannot be cast to non-null type java.net.HttpURLConnection"
            java.lang.String r1 = ""
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r10.label
            r4 = 1
            r5 = 0
            if (r3 == 0) goto L_0x0024
            if (r3 != r4) goto L_0x001c
            java.lang.Object r2 = r10.L$1
            java.lang.String r2 = (java.lang.String) r2
            java.lang.Object r3 = r10.L$0
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x003e
        L_0x001c:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0024:
            kotlin.ResultKt.throwOnFailure(r11)
            kotlinx.coroutines.sync.Mutex r3 = org.videolan.tools.HttpImageLoader.jobsLocker
            java.lang.String r11 = r10.$imageUrl
            r6 = r10
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r10.L$0 = r3
            r10.L$1 = r11
            r10.label = r4
            java.lang.Object r6 = r3.lock(r5, r6)
            if (r6 != r2) goto L_0x003d
            return r2
        L_0x003d:
            r2 = r11
        L_0x003e:
            androidx.collection.SimpleArrayMap r11 = org.videolan.tools.HttpImageLoader.currentJobs     // Catch:{ all -> 0x012f }
            kotlinx.coroutines.CompletableDeferred r6 = kotlinx.coroutines.CompletableDeferredKt.CompletableDeferred$default(r5, r4, r5)     // Catch:{ all -> 0x012f }
            java.lang.Object r11 = r11.put(r2, r6)     // Catch:{ all -> 0x012f }
            kotlinx.coroutines.CompletableDeferred r11 = (kotlinx.coroutines.CompletableDeferred) r11     // Catch:{ all -> 0x012f }
            r3.unlock(r5)
            java.net.URL r11 = new java.net.URL     // Catch:{ IOException -> 0x0109, IllegalArgumentException -> 0x00f0, all -> 0x00ed }
            java.lang.String r2 = r10.$imageUrl     // Catch:{ IOException -> 0x0109, IllegalArgumentException -> 0x00f0, all -> 0x00ed }
            r11.<init>(r2)     // Catch:{ IOException -> 0x0109, IllegalArgumentException -> 0x00f0, all -> 0x00ed }
            java.net.URLConnection r2 = r11.openConnection()     // Catch:{ IOException -> 0x0109, IllegalArgumentException -> 0x00f0, all -> 0x00ed }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r2, r0)     // Catch:{ IOException -> 0x0109, IllegalArgumentException -> 0x00f0, all -> 0x00ed }
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch:{ IOException -> 0x0109, IllegalArgumentException -> 0x00f0, all -> 0x00ed }
            java.io.BufferedInputStream r3 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x00ea, IllegalArgumentException -> 0x00e7, all -> 0x00e5 }
            java.io.InputStream r6 = r2.getInputStream()     // Catch:{ IOException -> 0x00ea, IllegalArgumentException -> 0x00e7, all -> 0x00e5 }
            r3.<init>(r6)     // Catch:{ IOException -> 0x00ea, IllegalArgumentException -> 0x00e7, all -> 0x00e5 }
            java.io.InputStream r3 = (java.io.InputStream) r3     // Catch:{ IOException -> 0x00ea, IllegalArgumentException -> 0x00e7, all -> 0x00e5 }
            android.graphics.BitmapFactory$Options r6 = new android.graphics.BitmapFactory$Options     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            r6.<init>()     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            r6.inJustDecodeBounds = r4     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            android.graphics.BitmapFactory.decodeStream(r3, r5, r6)     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            r4 = 0
            r6.inJustDecodeBounds = r4     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            int r4 = r6.outHeight     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            int r7 = r6.outWidth     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            int r4 = java.lang.Math.max(r4, r7)     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            float r4 = (float) r4     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            r7 = 150(0x96, float:2.1E-43)
            int r7 = org.videolan.tools.KotlinExtensionsKt.getDp(r7)     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            float r7 = (float) r7     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            float r4 = r4 / r7
            r7 = 1065353216(0x3f800000, float:1.0)
            int r7 = (r4 > r7 ? 1 : (r4 == r7 ? 0 : -1))
            if (r7 <= 0) goto L_0x0097
            double r7 = (double) r4     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            double r7 = java.lang.Math.floor(r7)     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            float r4 = (float) r7     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            int r4 = (int) r4     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            r6.inSampleSize = r4     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
        L_0x0097:
            java.net.URLConnection r11 = r11.openConnection()     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r11, r0)     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            java.net.HttpURLConnection r11 = (java.net.HttpURLConnection) r11     // Catch:{ IOException -> 0x00e3, IllegalArgumentException -> 0x00e1 }
            java.io.BufferedInputStream r0 = new java.io.BufferedInputStream     // Catch:{ IOException -> 0x00dd, IllegalArgumentException -> 0x00d9, all -> 0x00d5 }
            java.io.InputStream r2 = r11.getInputStream()     // Catch:{ IOException -> 0x00dd, IllegalArgumentException -> 0x00d9, all -> 0x00d5 }
            r0.<init>(r2)     // Catch:{ IOException -> 0x00dd, IllegalArgumentException -> 0x00d9, all -> 0x00d5 }
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ IOException -> 0x00dd, IllegalArgumentException -> 0x00d9, all -> 0x00d5 }
            android.graphics.Bitmap r2 = android.graphics.BitmapFactory.decodeStream(r0, r5, r6)     // Catch:{ IOException -> 0x00cf, IllegalArgumentException -> 0x00c9, all -> 0x00c3 }
            java.lang.String r3 = r10.$imageUrl     // Catch:{ IOException -> 0x00cf, IllegalArgumentException -> 0x00c9, all -> 0x00c3 }
            org.videolan.tools.BitmapCache r4 = org.videolan.tools.BitmapCache.INSTANCE     // Catch:{ IOException -> 0x00cf, IllegalArgumentException -> 0x00c9, all -> 0x00c3 }
            r4.addBitmapToMemCache((java.lang.String) r3, (android.graphics.Bitmap) r2)     // Catch:{ IOException -> 0x00cf, IllegalArgumentException -> 0x00c9, all -> 0x00c3 }
            org.videolan.tools.CloseableUtils r1 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1.close(r0)
            r11.disconnect()
            r5 = r2
            goto L_0x011f
        L_0x00c3:
            r1 = move-exception
            r2 = r11
            r5 = r0
            r11 = r1
            goto L_0x0122
        L_0x00c9:
            r2 = move-exception
            r3 = r0
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x00f3
        L_0x00cf:
            r2 = move-exception
            r3 = r0
            r9 = r2
            r2 = r11
            r11 = r9
            goto L_0x010c
        L_0x00d5:
            r0 = move-exception
            r2 = r11
            r11 = r0
            goto L_0x0121
        L_0x00d9:
            r0 = move-exception
            r2 = r11
            r11 = r0
            goto L_0x00f3
        L_0x00dd:
            r0 = move-exception
            r2 = r11
            r11 = r0
            goto L_0x010c
        L_0x00e1:
            r11 = move-exception
            goto L_0x00f3
        L_0x00e3:
            r11 = move-exception
            goto L_0x010c
        L_0x00e5:
            r11 = move-exception
            goto L_0x0122
        L_0x00e7:
            r11 = move-exception
            r3 = r5
            goto L_0x00f3
        L_0x00ea:
            r11 = move-exception
            r3 = r5
            goto L_0x010c
        L_0x00ed:
            r11 = move-exception
            r2 = r5
            goto L_0x0122
        L_0x00f0:
            r11 = move-exception
            r2 = r5
            r3 = r2
        L_0x00f3:
            java.lang.String r0 = r11.getMessage()     // Catch:{ all -> 0x0120 }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ all -> 0x0120 }
            android.util.Log.e(r1, r0, r11)     // Catch:{ all -> 0x0120 }
            org.videolan.tools.CloseableUtils r11 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r3 = (java.io.Closeable) r3
            r11.close(r3)
            if (r2 == 0) goto L_0x011f
        L_0x0105:
            r2.disconnect()
            goto L_0x011f
        L_0x0109:
            r11 = move-exception
            r2 = r5
            r3 = r2
        L_0x010c:
            java.lang.String r0 = r11.getMessage()     // Catch:{ all -> 0x0120 }
            java.lang.Throwable r11 = (java.lang.Throwable) r11     // Catch:{ all -> 0x0120 }
            android.util.Log.e(r1, r0, r11)     // Catch:{ all -> 0x0120 }
            org.videolan.tools.CloseableUtils r11 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r3 = (java.io.Closeable) r3
            r11.close(r3)
            if (r2 == 0) goto L_0x011f
            goto L_0x0105
        L_0x011f:
            return r5
        L_0x0120:
            r11 = move-exception
        L_0x0121:
            r5 = r3
        L_0x0122:
            org.videolan.tools.CloseableUtils r0 = org.videolan.tools.CloseableUtils.INSTANCE
            java.io.Closeable r5 = (java.io.Closeable) r5
            r0.close(r5)
            if (r2 == 0) goto L_0x012e
            r2.disconnect()
        L_0x012e:
            throw r11
        L_0x012f:
            r11 = move-exception
            r3.unlock(r5)
            goto L_0x0135
        L_0x0134:
            throw r11
        L_0x0135:
            goto L_0x0134
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.HttpImageLoader$downloadBitmap$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
