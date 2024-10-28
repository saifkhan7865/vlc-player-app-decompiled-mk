package io.ktor.client.plugins.cache.storage;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 8, 0}, xi = 48)
@DebugMetadata(c = "io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2", f = "FileCacheStorage.kt", i = {0, 0, 1, 1, 1}, l = {202, 102}, m = "invokeSuspend", n = {"$this$coroutineScope", "$this$withLock_u24default$iv", "$this$withLock_u24default$iv", "$this$use$iv", "closed$iv"}, s = {"L$0", "L$1", "L$0", "L$1", "I$0"})
/* compiled from: FileCacheStorage.kt */
final class FileCacheStorage$writeCache$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final /* synthetic */ List<CachedResponseData> $caches;
    final /* synthetic */ String $urlHex;
    int I$0;
    private /* synthetic */ Object L$0;
    Object L$1;
    Object L$2;
    Object L$3;
    Object L$4;
    int label;
    final /* synthetic */ FileCacheStorage this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileCacheStorage$writeCache$2(FileCacheStorage fileCacheStorage, String str, List<CachedResponseData> list, Continuation<? super FileCacheStorage$writeCache$2> continuation) {
        super(2, continuation);
        this.this$0 = fileCacheStorage;
        this.$urlHex = str;
        this.$caches = list;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FileCacheStorage$writeCache$2 fileCacheStorage$writeCache$2 = new FileCacheStorage$writeCache$2(this.this$0, this.$urlHex, this.$caches, continuation);
        fileCacheStorage$writeCache$2.L$0 = obj;
        return fileCacheStorage$writeCache$2;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<Object> continuation) {
        return ((FileCacheStorage$writeCache$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.io.BufferedOutputStream} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v11, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: kotlinx.coroutines.CoroutineScope} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v21, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r1v20, resolved type: java.io.Closeable} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r0v22, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r2v12, resolved type: kotlinx.coroutines.sync.Mutex} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r19) {
        /*
            r18 = this;
            r8 = r18
            java.lang.String r9 = "Exception during saving a cache to a file: "
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            r10 = 0
            if (r1 == 0) goto L_0x004a
            if (r1 == r3) goto L_0x0031
            if (r1 != r2) goto L_0x0029
            java.lang.Object r0 = r8.L$1
            r1 = r0
            java.io.Closeable r1 = (java.io.Closeable) r1
            java.lang.Object r0 = r8.L$0
            r2 = r0
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            kotlin.ResultKt.throwOnFailure(r19)     // Catch:{ all -> 0x0025 }
            r13 = r1
            r1 = r19
            goto L_0x00e3
        L_0x0025:
            r0 = move-exception
            r3 = r0
            goto L_0x0102
        L_0x0029:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0031:
            java.lang.Object r1 = r8.L$4
            java.util.List r1 = (java.util.List) r1
            java.lang.Object r4 = r8.L$3
            java.lang.String r4 = (java.lang.String) r4
            java.lang.Object r5 = r8.L$2
            io.ktor.client.plugins.cache.storage.FileCacheStorage r5 = (io.ktor.client.plugins.cache.storage.FileCacheStorage) r5
            java.lang.Object r6 = r8.L$1
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r8.L$0
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            kotlin.ResultKt.throwOnFailure(r19)
            r11 = r6
            goto L_0x0082
        L_0x004a:
            kotlin.ResultKt.throwOnFailure(r19)
            java.lang.Object r1 = r8.L$0
            r7 = r1
            kotlinx.coroutines.CoroutineScope r7 = (kotlinx.coroutines.CoroutineScope) r7
            io.ktor.client.plugins.cache.storage.FileCacheStorage r1 = r8.this$0
            io.ktor.util.collections.ConcurrentMap r1 = r1.mutexes
            java.lang.String r4 = r8.$urlHex
            io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2$mutex$1 r5 = io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2$mutex$1.INSTANCE
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            java.lang.Object r1 = r1.computeIfAbsent(r4, r5)
            kotlinx.coroutines.sync.Mutex r1 = (kotlinx.coroutines.sync.Mutex) r1
            io.ktor.client.plugins.cache.storage.FileCacheStorage r5 = r8.this$0
            java.lang.String r4 = r8.$urlHex
            java.util.List<io.ktor.client.plugins.cache.storage.CachedResponseData> r6 = r8.$caches
            r11 = r8
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r8.L$0 = r7
            r8.L$1 = r1
            r8.L$2 = r5
            r8.L$3 = r4
            r8.L$4 = r6
            r8.label = r3
            java.lang.Object r11 = r1.lock(r10, r11)
            if (r11 != r0) goto L_0x0080
            return r0
        L_0x0080:
            r11 = r1
            r1 = r6
        L_0x0082:
            r12 = r7
            r6 = 0
            io.ktor.utils.io.ByteChannel r3 = io.ktor.utils.io.ByteChannelKt.ByteChannel$default(r6, r3, r10)     // Catch:{ all -> 0x012f }
            java.io.FileOutputStream r7 = new java.io.FileOutputStream     // Catch:{ Exception -> 0x010e }
            java.io.File r13 = new java.io.File     // Catch:{ Exception -> 0x010e }
            java.io.File r14 = r5.directory     // Catch:{ Exception -> 0x010e }
            r13.<init>(r14, r4)     // Catch:{ Exception -> 0x010e }
            r7.<init>(r13)     // Catch:{ Exception -> 0x010e }
            java.io.OutputStream r7 = (java.io.OutputStream) r7     // Catch:{ Exception -> 0x010e }
            boolean r4 = r7 instanceof java.io.BufferedOutputStream     // Catch:{ Exception -> 0x010e }
            if (r4 == 0) goto L_0x009f
            java.io.BufferedOutputStream r7 = (java.io.BufferedOutputStream) r7     // Catch:{ Exception -> 0x010e }
            goto L_0x00a7
        L_0x009f:
            java.io.BufferedOutputStream r4 = new java.io.BufferedOutputStream     // Catch:{ Exception -> 0x010e }
            r13 = 8192(0x2000, float:1.14794E-41)
            r4.<init>(r7, r13)     // Catch:{ Exception -> 0x010e }
            r7 = r4
        L_0x00a7:
            java.io.Closeable r7 = (java.io.Closeable) r7     // Catch:{ Exception -> 0x010e }
            r4 = r7
            java.io.BufferedOutputStream r4 = (java.io.BufferedOutputStream) r4     // Catch:{ all -> 0x00fd }
            io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2$1$1$1 r13 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2$1$1$1     // Catch:{ all -> 0x00fd }
            r13.<init>(r3, r1, r5, r10)     // Catch:{ all -> 0x00fd }
            r15 = r13
            kotlin.jvm.functions.Function2 r15 = (kotlin.jvm.functions.Function2) r15     // Catch:{ all -> 0x00fd }
            r16 = 3
            r17 = 0
            r13 = 0
            r14 = 0
            kotlinx.coroutines.Job unused = kotlinx.coroutines.BuildersKt__Builders_commonKt.launch$default(r12, r13, r14, r15, r16, r17)     // Catch:{ all -> 0x00fd }
            r1 = r3
            io.ktor.utils.io.ByteReadChannel r1 = (io.ktor.utils.io.ByteReadChannel) r1     // Catch:{ all -> 0x00fd }
            r3 = r4
            java.io.OutputStream r3 = (java.io.OutputStream) r3     // Catch:{ all -> 0x00fd }
            r8.L$0 = r11     // Catch:{ all -> 0x00fd }
            r8.L$1 = r7     // Catch:{ all -> 0x00fd }
            r8.L$2 = r10     // Catch:{ all -> 0x00fd }
            r8.L$3 = r10     // Catch:{ all -> 0x00fd }
            r8.L$4 = r10     // Catch:{ all -> 0x00fd }
            r8.I$0 = r6     // Catch:{ all -> 0x00fd }
            r8.label = r2     // Catch:{ all -> 0x00fd }
            r4 = 0
            r6 = 2
            r12 = 0
            r2 = r3
            r3 = r4
            r5 = r18
            r13 = r7
            r7 = r12
            java.lang.Object r1 = io.ktor.utils.io.jvm.javaio.WritingKt.copyTo$default(r1, r2, r3, r5, r6, r7)     // Catch:{ all -> 0x00fb }
            if (r1 != r0) goto L_0x00e2
            return r0
        L_0x00e2:
            r2 = r11
        L_0x00e3:
            java.lang.Number r1 = (java.lang.Number) r1     // Catch:{ all -> 0x00f7 }
            long r0 = r1.longValue()     // Catch:{ all -> 0x00f7 }
            java.lang.Long r0 = kotlin.coroutines.jvm.internal.Boxing.boxLong(r0)     // Catch:{ all -> 0x00f7 }
            r13.close()     // Catch:{ Exception -> 0x00f4, all -> 0x00f1 }
            goto L_0x012b
        L_0x00f1:
            r0 = move-exception
            r11 = r2
            goto L_0x0130
        L_0x00f4:
            r0 = move-exception
            r11 = r2
            goto L_0x010f
        L_0x00f7:
            r0 = move-exception
            r3 = r0
        L_0x00f9:
            r1 = r13
            goto L_0x0102
        L_0x00fb:
            r0 = move-exception
            goto L_0x00ff
        L_0x00fd:
            r0 = move-exception
            r13 = r7
        L_0x00ff:
            r3 = r0
            r2 = r11
            goto L_0x00f9
        L_0x0102:
            r1.close()     // Catch:{ all -> 0x0106 }
            goto L_0x010b
        L_0x0106:
            r0 = move-exception
            r1 = r0
            io.ktor.utils.io.core.CloseableJVMKt.addSuppressedInternal(r3, r1)     // Catch:{ all -> 0x010c }
        L_0x010b:
            throw r3     // Catch:{ all -> 0x010c }
        L_0x010c:
            r0 = move-exception
            throw r0     // Catch:{ Exception -> 0x00f4, all -> 0x00f1 }
        L_0x010e:
            r0 = move-exception
        L_0x010f:
            org.slf4j.Logger r1 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()     // Catch:{ all -> 0x012f }
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch:{ all -> 0x012f }
            r2.<init>(r9)     // Catch:{ all -> 0x012f }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x012f }
            java.lang.String r0 = kotlin.ExceptionsKt.stackTraceToString(r0)     // Catch:{ all -> 0x012f }
            r2.append(r0)     // Catch:{ all -> 0x012f }
            java.lang.String r0 = r2.toString()     // Catch:{ all -> 0x012f }
            r1.trace(r0)     // Catch:{ all -> 0x012f }
            kotlin.Unit r0 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x012f }
            r2 = r11
        L_0x012b:
            r2.unlock(r10)
            return r0
        L_0x012f:
            r0 = move-exception
        L_0x0130:
            r11.unlock(r10)
            goto L_0x0135
        L_0x0134:
            throw r0
        L_0x0135:
            goto L_0x0134
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
