package io.ktor.client.plugins.cache.storage;

import io.ktor.http.Url;
import io.ktor.util.CryptoKt;
import io.ktor.util.collections.ConcurrentMap;
import java.io.File;
import java.security.MessageDigest;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import kotlinx.coroutines.BuildersKt;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.CoroutineScopeKt;
import kotlinx.coroutines.Dispatchers;
import kotlinx.coroutines.sync.Mutex;

@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J/\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\u0010H@ø\u0001\u0000¢\u0006\u0002\u0010\u0011J\u001f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\u0006\u0010\r\u001a\u00020\u000eH@ø\u0001\u0000¢\u0006\u0002\u0010\u0014J\u0010\u0010\u0015\u001a\u00020\t2\u0006\u0010\r\u001a\u00020\u000eH\u0002J\u0019\u0010\u0016\u001a\u00020\f2\u0006\u0010\u0017\u001a\u00020\u0018H@ø\u0001\u0000¢\u0006\u0002\u0010\u0019J\u001f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\f0\u00132\u0006\u0010\u001a\u001a\u00020\tH@ø\u0001\u0000¢\u0006\u0002\u0010\u001bJ!\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u001e\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010\u001fJ!\u0010 \u001a\u00020\u001d2\u0006\u0010\u0017\u001a\u00020!2\u0006\u0010\"\u001a\u00020\fH@ø\u0001\u0000¢\u0006\u0002\u0010#J'\u0010 \u001a\u00020$2\u0006\u0010\u001a\u001a\u00020\t2\f\u0010%\u001a\b\u0012\u0004\u0012\u00020\f0&H@ø\u0001\u0000¢\u0006\u0002\u0010'R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\bX\u0004¢\u0006\u0002\n\u0000\u0002\u0004\n\u0002\b\u0019¨\u0006("}, d2 = {"Lio/ktor/client/plugins/cache/storage/FileCacheStorage;", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "directory", "Ljava/io/File;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "(Ljava/io/File;Lkotlinx/coroutines/CoroutineDispatcher;)V", "mutexes", "Lio/ktor/util/collections/ConcurrentMap;", "", "Lkotlinx/coroutines/sync/Mutex;", "find", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "url", "Lio/ktor/http/Url;", "varyKeys", "", "(Lio/ktor/http/Url;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAll", "", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "key", "readCache", "channel", "Lio/ktor/utils/io/ByteReadChannel;", "(Lio/ktor/utils/io/ByteReadChannel;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "urlHex", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "store", "", "data", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeCache", "Lio/ktor/utils/io/ByteChannel;", "cache", "(Lio/ktor/utils/io/ByteChannel;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "caches", "", "(Ljava/lang/String;Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: FileCacheStorage.kt */
final class FileCacheStorage implements CacheStorage {
    /* access modifiers changed from: private */
    public final File directory;
    private final CoroutineDispatcher dispatcher;
    /* access modifiers changed from: private */
    public final ConcurrentMap<String, Mutex> mutexes;

    public FileCacheStorage(File file, CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(file, "directory");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "dispatcher");
        this.directory = file;
        this.dispatcher = coroutineDispatcher;
        this.mutexes = new ConcurrentMap<>(0, 1, (DefaultConstructorMarker) null);
        file.mkdirs();
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ FileCacheStorage(File file, CoroutineDispatcher coroutineDispatcher, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(file, (i & 2) != 0 ? Dispatchers.getIO() : coroutineDispatcher);
    }

    public Object store(Url url, CachedResponseData cachedResponseData, Continuation<? super Unit> continuation) {
        Object withContext = BuildersKt.withContext(this.dispatcher, new FileCacheStorage$store$2(this, url, cachedResponseData, (Continuation<? super FileCacheStorage$store$2>) null), continuation);
        return withContext == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? withContext : Unit.INSTANCE;
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0032  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object findAll(io.ktor.http.Url r5, kotlin.coroutines.Continuation<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r6) {
        /*
            r4 = this;
            boolean r0 = r6 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1
            if (r0 == 0) goto L_0x0014
            r0 = r6
            io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1 r0 = (io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r6 = r0.label
            int r6 = r6 - r2
            r0.label = r6
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1 r0 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$findAll$1
            r0.<init>(r4, r6)
        L_0x0019:
            java.lang.Object r6 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0032
            if (r2 != r3) goto L_0x002a
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0042
        L_0x002a:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r6 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r6)
            throw r5
        L_0x0032:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r5 = r4.key(r5)
            r0.label = r3
            java.lang.Object r6 = r4.readCache((java.lang.String) r5, (kotlin.coroutines.Continuation<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>>) r0)
            if (r6 != r1) goto L_0x0042
            return r1
        L_0x0042:
            java.lang.Iterable r6 = (java.lang.Iterable) r6
            java.util.Set r5 = kotlin.collections.CollectionsKt.toSet(r6)
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.findAll(io.ktor.http.Url, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r6v4, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v3, resolved type: java.util.Map<java.lang.String, java.lang.String>} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0037  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0024  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object find(io.ktor.http.Url r6, java.util.Map<java.lang.String, java.lang.String> r7, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1 r0 = (io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1 r0 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$find$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 1
            if (r2 == 0) goto L_0x0037
            if (r2 != r3) goto L_0x002f
            java.lang.Object r6 = r0.L$0
            r7 = r6
            java.util.Map r7 = (java.util.Map) r7
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0049
        L_0x002f:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.String r6 = r5.key(r6)
            r0.L$0 = r7
            r0.label = r3
            java.lang.Object r8 = r5.readCache((java.lang.String) r6, (kotlin.coroutines.Continuation<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>>) r0)
            if (r8 != r1) goto L_0x0049
            return r1
        L_0x0049:
            java.util.Set r8 = (java.util.Set) r8
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            java.util.Iterator r6 = r8.iterator()
        L_0x0051:
            boolean r8 = r6.hasNext()
            if (r8 == 0) goto L_0x0094
            java.lang.Object r8 = r6.next()
            r0 = r8
            io.ktor.client.plugins.cache.storage.CachedResponseData r0 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r0
            boolean r1 = r7.isEmpty()
            if (r1 == 0) goto L_0x0065
            goto L_0x0095
        L_0x0065:
            java.util.Set r1 = r7.entrySet()
            java.util.Iterator r1 = r1.iterator()
        L_0x006d:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0095
            java.lang.Object r2 = r1.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r3 = r2.getKey()
            java.lang.String r3 = (java.lang.String) r3
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            java.util.Map r4 = r0.getVaryKeys()
            java.lang.Object r3 = r4.get(r3)
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r2)
            if (r2 != 0) goto L_0x006d
            goto L_0x0051
        L_0x0094:
            r8 = 0
        L_0x0095:
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.find(io.ktor.http.Url, java.util.Map, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    public final String key(Url url) {
        byte[] digest = MessageDigest.getInstance("MD5").digest(StringsKt.encodeToByteArray(url.toString()));
        Intrinsics.checkNotNullExpressionValue(digest, "getInstance(\"MD5\").diges…ng().encodeToByteArray())");
        return CryptoKt.hex(digest);
    }

    /* access modifiers changed from: private */
    public final Object writeCache(String str, List<CachedResponseData> list, Continuation<Object> continuation) {
        return CoroutineScopeKt.coroutineScope(new FileCacheStorage$writeCache$2(this, str, list, (Continuation<? super FileCacheStorage$writeCache$2>) null), continuation);
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00bb  */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x00e7 A[Catch:{ all -> 0x01e5 }] */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00ef A[SYNTHETIC, Splitter:B:43:0x00ef] */
    /* JADX WARNING: Removed duplicated region for block: B:59:0x013e A[SYNTHETIC, Splitter:B:59:0x013e] */
    /* JADX WARNING: Removed duplicated region for block: B:69:0x017a A[SYNTHETIC, Splitter:B:69:0x017a] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readCache(java.lang.String r19, kotlin.coroutines.Continuation<? super java.util.Set<io.ktor.client.plugins.cache.storage.CachedResponseData>> r20) {
        /*
            r18 = this;
            r1 = r18
            r0 = r19
            r2 = r20
            boolean r3 = r2 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1
            if (r3 == 0) goto L_0x001a
            r3 = r2
            io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1 r3 = (io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1) r3
            int r4 = r3.label
            r5 = -2147483648(0xffffffff80000000, float:-0.0)
            r4 = r4 & r5
            if (r4 == 0) goto L_0x001a
            int r2 = r3.label
            int r2 = r2 - r5
            r3.label = r2
            goto L_0x001f
        L_0x001a:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1 r3 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$1
            r3.<init>(r1, r2)
        L_0x001f:
            java.lang.Object r2 = r3.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r3.label
            r6 = 0
            r7 = 4
            r8 = 2
            r9 = 3
            r10 = 1
            r11 = 0
            if (r5 == 0) goto L_0x00bb
            if (r5 == r10) goto L_0x00a8
            if (r5 == r8) goto L_0x0086
            if (r5 == r9) goto L_0x0056
            if (r5 != r7) goto L_0x004e
            int r0 = r3.I$0
            java.lang.Object r0 = r3.L$2
            java.util.Set r0 = (java.util.Set) r0
            java.lang.Object r4 = r3.L$1
            java.io.Closeable r4 = (java.io.Closeable) r4
            java.lang.Object r3 = r3.L$0
            kotlinx.coroutines.sync.Mutex r3 = (kotlinx.coroutines.sync.Mutex) r3
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x004a }
            goto L_0x0195
        L_0x004a:
            r0 = move-exception
        L_0x004b:
            r2 = r0
            goto L_0x01b2
        L_0x004e:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x0056:
            int r0 = r3.I$2
            int r5 = r3.I$1
            int r6 = r3.I$0
            java.lang.Object r8 = r3.L$5
            java.util.Set r8 = (java.util.Set) r8
            java.lang.Object r12 = r3.L$4
            java.util.Set r12 = (java.util.Set) r12
            java.lang.Object r13 = r3.L$3
            io.ktor.utils.io.ByteReadChannel r13 = (io.ktor.utils.io.ByteReadChannel) r13
            java.lang.Object r14 = r3.L$2
            java.io.Closeable r14 = (java.io.Closeable) r14
            java.lang.Object r15 = r3.L$1
            kotlinx.coroutines.sync.Mutex r15 = (kotlinx.coroutines.sync.Mutex) r15
            java.lang.Object r7 = r3.L$0
            io.ktor.client.plugins.cache.storage.FileCacheStorage r7 = (io.ktor.client.plugins.cache.storage.FileCacheStorage) r7
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x0080 }
            r16 = r11
            r17 = r7
            r7 = r6
            r6 = r17
            goto L_0x0163
        L_0x0080:
            r0 = move-exception
            r2 = r0
            r4 = r14
            r3 = r15
            goto L_0x01b2
        L_0x0086:
            int r0 = r3.I$0
            java.lang.Object r5 = r3.L$3
            io.ktor.utils.io.ByteReadChannel r5 = (io.ktor.utils.io.ByteReadChannel) r5
            java.lang.Object r7 = r3.L$2
            java.io.Closeable r7 = (java.io.Closeable) r7
            java.lang.Object r8 = r3.L$1
            kotlinx.coroutines.sync.Mutex r8 = (kotlinx.coroutines.sync.Mutex) r8
            java.lang.Object r12 = r3.L$0
            io.ktor.client.plugins.cache.storage.FileCacheStorage r12 = (io.ktor.client.plugins.cache.storage.FileCacheStorage) r12
            kotlin.ResultKt.throwOnFailure(r2)     // Catch:{ all -> 0x00a2 }
            r17 = r7
            r7 = r5
            r5 = r17
            goto L_0x0128
        L_0x00a2:
            r0 = move-exception
            r2 = r0
            r4 = r7
        L_0x00a5:
            r3 = r8
            goto L_0x01b2
        L_0x00a8:
            java.lang.Object r0 = r3.L$2
            kotlinx.coroutines.sync.Mutex r0 = (kotlinx.coroutines.sync.Mutex) r0
            java.lang.Object r5 = r3.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r7 = r3.L$0
            io.ktor.client.plugins.cache.storage.FileCacheStorage r7 = (io.ktor.client.plugins.cache.storage.FileCacheStorage) r7
            kotlin.ResultKt.throwOnFailure(r2)
            r2 = r0
            r0 = r5
            r12 = r7
            goto L_0x00da
        L_0x00bb:
            kotlin.ResultKt.throwOnFailure(r2)
            io.ktor.util.collections.ConcurrentMap<java.lang.String, kotlinx.coroutines.sync.Mutex> r2 = r1.mutexes
            io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$mutex$1 r5 = io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$mutex$1.INSTANCE
            kotlin.jvm.functions.Function0 r5 = (kotlin.jvm.functions.Function0) r5
            java.lang.Object r2 = r2.computeIfAbsent(r0, r5)
            kotlinx.coroutines.sync.Mutex r2 = (kotlinx.coroutines.sync.Mutex) r2
            r3.L$0 = r1
            r3.L$1 = r0
            r3.L$2 = r2
            r3.label = r10
            java.lang.Object r5 = r2.lock(r11, r3)
            if (r5 != r4) goto L_0x00d9
            return r4
        L_0x00d9:
            r12 = r1
        L_0x00da:
            java.io.File r5 = new java.io.File     // Catch:{ all -> 0x01e5 }
            java.io.File r7 = r12.directory     // Catch:{ all -> 0x01e5 }
            r5.<init>(r7, r0)     // Catch:{ all -> 0x01e5 }
            boolean r0 = r5.exists()     // Catch:{ all -> 0x01e5 }
            if (r0 != 0) goto L_0x00ef
            java.util.Set r0 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x01e5 }
            r2.unlock(r11)
            return r0
        L_0x00ef:
            java.io.FileInputStream r0 = new java.io.FileInputStream     // Catch:{ Exception -> 0x01be }
            r0.<init>(r5)     // Catch:{ Exception -> 0x01be }
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ Exception -> 0x01be }
            boolean r5 = r0 instanceof java.io.BufferedInputStream     // Catch:{ Exception -> 0x01be }
            if (r5 == 0) goto L_0x00fd
            java.io.BufferedInputStream r0 = (java.io.BufferedInputStream) r0     // Catch:{ Exception -> 0x01be }
            goto L_0x0105
        L_0x00fd:
            java.io.BufferedInputStream r5 = new java.io.BufferedInputStream     // Catch:{ Exception -> 0x01be }
            r7 = 8192(0x2000, float:1.14794E-41)
            r5.<init>(r0, r7)     // Catch:{ Exception -> 0x01be }
            r0 = r5
        L_0x0105:
            r5 = r0
            java.io.Closeable r5 = (java.io.Closeable) r5     // Catch:{ Exception -> 0x01be }
            r0 = r5
            java.io.BufferedInputStream r0 = (java.io.BufferedInputStream) r0     // Catch:{ all -> 0x01ad }
            java.io.InputStream r0 = (java.io.InputStream) r0     // Catch:{ all -> 0x01ad }
            io.ktor.utils.io.ByteReadChannel r0 = io.ktor.utils.io.jvm.javaio.ReadingKt.toByteReadChannelWithArrayPool$default(r0, r11, r11, r9, r11)     // Catch:{ all -> 0x01ad }
            r3.L$0 = r12     // Catch:{ all -> 0x01ad }
            r3.L$1 = r2     // Catch:{ all -> 0x01ad }
            r3.L$2 = r5     // Catch:{ all -> 0x01ad }
            r3.L$3 = r0     // Catch:{ all -> 0x01ad }
            r3.I$0 = r6     // Catch:{ all -> 0x01ad }
            r3.label = r8     // Catch:{ all -> 0x01ad }
            java.lang.Object r7 = r0.readInt(r3)     // Catch:{ all -> 0x01ad }
            if (r7 != r4) goto L_0x0124
            return r4
        L_0x0124:
            r8 = r2
            r2 = r7
            r7 = r0
            r0 = 0
        L_0x0128:
            java.lang.Number r2 = (java.lang.Number) r2     // Catch:{ all -> 0x01a8 }
            int r2 = r2.intValue()     // Catch:{ all -> 0x01a8 }
            java.util.LinkedHashSet r13 = new java.util.LinkedHashSet     // Catch:{ all -> 0x01a8 }
            r13.<init>()     // Catch:{ all -> 0x01a8 }
            java.util.Set r13 = (java.util.Set) r13     // Catch:{ all -> 0x01a8 }
            r14 = r11
            r17 = r7
            r7 = r0
            r0 = r13
            r13 = r17
        L_0x013c:
            if (r6 >= r2) goto L_0x017a
            r3.L$0 = r12     // Catch:{ all -> 0x01a2 }
            r3.L$1 = r8     // Catch:{ all -> 0x01a2 }
            r3.L$2 = r5     // Catch:{ all -> 0x01a2 }
            r3.L$3 = r13     // Catch:{ all -> 0x01a2 }
            r3.L$4 = r0     // Catch:{ all -> 0x01a2 }
            r3.L$5 = r0     // Catch:{ all -> 0x01a2 }
            r3.I$0 = r7     // Catch:{ all -> 0x01a2 }
            r3.I$1 = r2     // Catch:{ all -> 0x01a2 }
            r3.I$2 = r6     // Catch:{ all -> 0x01a2 }
            r3.label = r9     // Catch:{ all -> 0x01a2 }
            java.lang.Object r15 = r12.readCache((io.ktor.utils.io.ByteReadChannel) r13, (kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData>) r3)     // Catch:{ all -> 0x01a2 }
            if (r15 != r4) goto L_0x0159
            return r4
        L_0x0159:
            r16 = r14
            r14 = r5
            r5 = r2
            r2 = r15
            r15 = r8
            r8 = r0
            r0 = r6
            r6 = r12
            r12 = r8
        L_0x0163:
            r8.add(r2)     // Catch:{ all -> 0x0173 }
            int r0 = r0 + r10
            r2 = r5
            r5 = r14
            r8 = r15
            r14 = r16
            r17 = r6
            r6 = r0
            r0 = r12
            r12 = r17
            goto L_0x013c
        L_0x0173:
            r0 = move-exception
            r2 = r0
            r4 = r14
            r3 = r15
            r11 = r16
            goto L_0x01b2
        L_0x017a:
            r3.L$0 = r8     // Catch:{ all -> 0x01a2 }
            r3.L$1 = r5     // Catch:{ all -> 0x01a2 }
            r3.L$2 = r0     // Catch:{ all -> 0x01a2 }
            r3.L$3 = r11     // Catch:{ all -> 0x01a2 }
            r3.L$4 = r11     // Catch:{ all -> 0x01a2 }
            r3.L$5 = r11     // Catch:{ all -> 0x01a2 }
            r3.I$0 = r7     // Catch:{ all -> 0x01a2 }
            r2 = 4
            r3.label = r2     // Catch:{ all -> 0x01a2 }
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannelKt.discard(r13, r3)     // Catch:{ all -> 0x01a2 }
            if (r2 != r4) goto L_0x0192
            return r4
        L_0x0192:
            r4 = r5
            r3 = r8
            r11 = r14
        L_0x0195:
            r4.close()     // Catch:{ Exception -> 0x019f, all -> 0x019c }
            r3.unlock(r11)
            return r0
        L_0x019c:
            r0 = move-exception
            r2 = r3
            goto L_0x01e6
        L_0x019f:
            r0 = move-exception
            r2 = r3
            goto L_0x01bf
        L_0x01a2:
            r0 = move-exception
            r2 = r0
            r4 = r5
            r3 = r8
            r11 = r14
            goto L_0x01b2
        L_0x01a8:
            r0 = move-exception
            r2 = r0
            r4 = r5
            goto L_0x00a5
        L_0x01ad:
            r0 = move-exception
            r3 = r2
            r4 = r5
            goto L_0x004b
        L_0x01b2:
            r4.close()     // Catch:{ all -> 0x01b6 }
            goto L_0x01bb
        L_0x01b6:
            r0 = move-exception
            r4 = r0
            io.ktor.utils.io.core.CloseableJVMKt.addSuppressedInternal(r2, r4)     // Catch:{ all -> 0x01bc }
        L_0x01bb:
            throw r2     // Catch:{ all -> 0x01bc }
        L_0x01bc:
            r0 = move-exception
            throw r0     // Catch:{ Exception -> 0x019f, all -> 0x019c }
        L_0x01be:
            r0 = move-exception
        L_0x01bf:
            org.slf4j.Logger r3 = io.ktor.client.plugins.cache.HttpCacheKt.getLOGGER()     // Catch:{ all -> 0x01e5 }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ all -> 0x01e5 }
            r4.<init>()     // Catch:{ all -> 0x01e5 }
            java.lang.String r5 = "Exception during cache lookup in a file: "
            r4.append(r5)     // Catch:{ all -> 0x01e5 }
            java.lang.Throwable r0 = (java.lang.Throwable) r0     // Catch:{ all -> 0x01e5 }
            java.lang.String r0 = kotlin.ExceptionsKt.stackTraceToString(r0)     // Catch:{ all -> 0x01e5 }
            r4.append(r0)     // Catch:{ all -> 0x01e5 }
            java.lang.String r0 = r4.toString()     // Catch:{ all -> 0x01e5 }
            r3.trace(r0)     // Catch:{ all -> 0x01e5 }
            java.util.Set r0 = kotlin.collections.SetsKt.emptySet()     // Catch:{ all -> 0x01e5 }
            r2.unlock(r11)
            return r0
        L_0x01e5:
            r0 = move-exception
        L_0x01e6:
            r2.unlock(r11)
            goto L_0x01eb
        L_0x01ea:
            throw r0
        L_0x01eb:
            goto L_0x01ea
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.readCache(java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r10v31, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r11v21, resolved type: io.ktor.client.plugins.cache.storage.CachedResponseData} */
    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00e0, code lost:
        r2 = r11;
        r11 = r10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x0131, code lost:
        r12 = r11.getStatusCode().getValue();
        r0.L$0 = r10;
        r0.L$1 = r11;
        r0.label = 2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x0144, code lost:
        if (r10.writeInt(r12, r0) != r1) goto L_0x0147;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x0146, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x0147, code lost:
        r8 = r11;
        r11 = r10;
        r10 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x014a, code lost:
        r2 = r10.getStatusCode().getDescription() + 10;
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.label = 3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x016f, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r11, r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x0172;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0171, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0172, code lost:
        r2 = new java.lang.StringBuilder();
        r2.append(r10.getVersion());
        r2.append(10);
        r2 = r2.toString();
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.label = 4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x0193, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r11, r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x00e0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0195, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0196, code lost:
        r10 = io.ktor.util.StringValuesKt.flattenEntries(r11.getHeaders());
        r12 = r10.size();
        r0.L$0 = r2;
        r0.L$1 = r11;
        r0.L$2 = r10;
        r0.label = 5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x01b1, code lost:
        if (r2.writeInt(r12, r0) != r1) goto L_0x01b4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x01b3, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x01b4, code lost:
        r10 = r10.iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x01bc, code lost:
        if (r10.hasNext() == false) goto L_0x0220;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x01be, code lost:
        r12 = r10.next();
        r12 = (java.lang.String) r12.component2();
        r5 = ((java.lang.String) r12.component1()) + 10;
        r0.L$0 = r2;
        r0.L$1 = r11;
        r0.L$2 = r10;
        r0.L$3 = r12;
        r0.label = 6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x01f1, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r2, r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x01f4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x01f3, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x01f4, code lost:
        r5 = r2;
        r2 = r11;
        r11 = r10;
        r10 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x01f8, code lost:
        r10 = r10 + 10;
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.L$2 = r11;
        r0.L$3 = null;
        r0.label = 7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x0219, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r5, r10, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x021c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x021b, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x021c, code lost:
        r10 = r11;
        r11 = r2;
        r2 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x0220, code lost:
        r5 = r11.getRequestTime().getTimestamp();
        r0.L$0 = r2;
        r0.L$1 = r11;
        r0.L$2 = null;
        r0.label = 8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0236, code lost:
        if (r2.writeLong(r5, r0) != r1) goto L_0x0239;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0238, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0239, code lost:
        r10 = r11;
        r11 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x023b, code lost:
        r5 = r10.getResponseTime().getTimestamp();
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.label = 9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x024f, code lost:
        if (r11.writeLong(r5, r0) != r1) goto L_0x0252;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0251, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0252, code lost:
        r5 = r10.getExpires().getTimestamp();
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.label = 10;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0264, code lost:
        if (r11.writeLong(r5, r0) != r1) goto L_0x0267;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0266, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0267, code lost:
        r12 = r10.getVaryKeys().size();
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.label = 11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x027b, code lost:
        if (r11.writeInt(r12, r0) != r1) goto L_0x027e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x027d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x027e, code lost:
        r12 = r10.getVaryKeys().entrySet().iterator();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x028e, code lost:
        if (r12.hasNext() == false) goto L_0x02f5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x0290, code lost:
        r2 = r12.next();
        r2 = (java.lang.String) r2.getValue();
        r5 = ((java.lang.String) r2.getKey()) + 10;
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.L$2 = r12;
        r0.L$3 = r2;
        r0.label = 12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x02c4, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r11, r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x02c7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x02c6, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x02c7, code lost:
        r5 = r11;
        r11 = r12;
        r8 = r2;
        r2 = r10;
        r10 = r8;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x02cc, code lost:
        r10 = r10 + 10;
        r0.L$0 = r5;
        r0.L$1 = r2;
        r0.L$2 = r11;
        r0.L$3 = null;
        r0.label = 13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x02ee, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r5, r10, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0) != r1) goto L_0x02f1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x02f0, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x02f1, code lost:
        r12 = r11;
        r10 = r2;
        r11 = r5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x02f5, code lost:
        r12 = r10.getBody().length;
        r0.L$0 = r11;
        r0.L$1 = r10;
        r0.L$2 = null;
        r0.label = 14;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x0308, code lost:
        if (r11.writeInt(r12, r0) != r1) goto L_0x030b;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x030a, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x030b, code lost:
        r10 = r10.getBody();
        r0.L$0 = null;
        r0.L$1 = null;
        r0.label = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x031d, code lost:
        if (io.ktor.utils.io.ByteWriteChannelKt.writeFully(r11, r10, r0) != r1) goto L_0x0320;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x031f, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0322, code lost:
        return kotlin.Unit.INSTANCE;
     */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x002f  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0034  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0055  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x0077  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0084  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0091  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x00af  */
    /* JADX WARNING: Removed duplicated region for block: B:20:0x00c4  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x00d5  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x00e4  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00f1  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x010a  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0027  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object writeCache(io.ktor.utils.io.ByteChannel r10, io.ktor.client.plugins.cache.storage.CachedResponseData r11, kotlin.coroutines.Continuation<? super kotlin.Unit> r12) {
        /*
            r9 = this;
            boolean r0 = r12 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$3
            if (r0 == 0) goto L_0x0014
            r0 = r12
            io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$3 r0 = (io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$3) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r12 = r0.label
            int r12 = r12 - r2
            r0.label = r12
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$3 r0 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$writeCache$3
            r0.<init>(r9, r12)
        L_0x0019:
            java.lang.Object r12 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 10
            switch(r2) {
                case 0: goto L_0x010a;
                case 1: goto L_0x00fd;
                case 2: goto L_0x00f1;
                case 3: goto L_0x00e4;
                case 4: goto L_0x00d5;
                case 5: goto L_0x00c4;
                case 6: goto L_0x00af;
                case 7: goto L_0x009e;
                case 8: goto L_0x0091;
                case 9: goto L_0x0084;
                case 10: goto L_0x0077;
                case 11: goto L_0x006a;
                case 12: goto L_0x0055;
                case 13: goto L_0x0041;
                case 14: goto L_0x0034;
                case 15: goto L_0x002f;
                default: goto L_0x0027;
            }
        L_0x0027:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L_0x002f:
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0320
        L_0x0034:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x030b
        L_0x0041:
            java.lang.Object r10 = r0.L$2
            java.util.Iterator r10 = (java.util.Iterator) r10
            java.lang.Object r11 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r11 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r11
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannel r2 = (io.ktor.utils.io.ByteChannel) r2
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r10
            r10 = r11
            r11 = r2
            goto L_0x028a
        L_0x0055:
            java.lang.Object r10 = r0.L$3
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r0.L$2
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r2 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r2 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r2
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteChannel r5 = (io.ktor.utils.io.ByteChannel) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x02cc
        L_0x006a:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x027e
        L_0x0077:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0267
        L_0x0084:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0252
        L_0x0091:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x023b
        L_0x009e:
            java.lang.Object r10 = r0.L$2
            java.util.Iterator r10 = (java.util.Iterator) r10
            java.lang.Object r11 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r11 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r11
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannel r2 = (io.ktor.utils.io.ByteChannel) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x01b8
        L_0x00af:
            java.lang.Object r10 = r0.L$3
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r0.L$2
            java.util.Iterator r11 = (java.util.Iterator) r11
            java.lang.Object r2 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r2 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r2
            java.lang.Object r5 = r0.L$0
            io.ktor.utils.io.ByteChannel r5 = (io.ktor.utils.io.ByteChannel) r5
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x01f8
        L_0x00c4:
            java.lang.Object r10 = r0.L$2
            java.util.List r10 = (java.util.List) r10
            java.lang.Object r11 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r11 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r11
            java.lang.Object r2 = r0.L$0
            io.ktor.utils.io.ByteChannel r2 = (io.ktor.utils.io.ByteChannel) r2
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x01b4
        L_0x00d5:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
        L_0x00e0:
            r2 = r11
            r11 = r10
            goto L_0x0196
        L_0x00e4:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0172
        L_0x00f1:
            java.lang.Object r10 = r0.L$1
            io.ktor.client.plugins.cache.storage.CachedResponseData r10 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r10
            java.lang.Object r11 = r0.L$0
            io.ktor.utils.io.ByteChannel r11 = (io.ktor.utils.io.ByteChannel) r11
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x014a
        L_0x00fd:
            java.lang.Object r10 = r0.L$1
            r11 = r10
            io.ktor.client.plugins.cache.storage.CachedResponseData r11 = (io.ktor.client.plugins.cache.storage.CachedResponseData) r11
            java.lang.Object r10 = r0.L$0
            io.ktor.utils.io.ByteChannel r10 = (io.ktor.utils.io.ByteChannel) r10
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x0131
        L_0x010a:
            kotlin.ResultKt.throwOnFailure(r12)
            r12 = r10
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            io.ktor.http.Url r5 = r11.getUrl()
            r2.append(r5)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.L$0 = r10
            r0.L$1 = r11
            r5 = 1
            r0.label = r5
            java.lang.Object r12 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r12, (java.lang.String) r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r12 != r1) goto L_0x0131
            return r1
        L_0x0131:
            io.ktor.http.HttpStatusCode r12 = r11.getStatusCode()
            int r12 = r12.getValue()
            r0.L$0 = r10
            r0.L$1 = r11
            r2 = 2
            r0.label = r2
            java.lang.Object r12 = r10.writeInt(r12, r0)
            if (r12 != r1) goto L_0x0147
            return r1
        L_0x0147:
            r8 = r11
            r11 = r10
            r10 = r8
        L_0x014a:
            r12 = r11
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            io.ktor.http.HttpStatusCode r5 = r10.getStatusCode()
            java.lang.String r5 = r5.getDescription()
            r2.append(r5)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.L$0 = r11
            r0.L$1 = r10
            r5 = 3
            r0.label = r5
            java.lang.Object r12 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r12, (java.lang.String) r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r12 != r1) goto L_0x0172
            return r1
        L_0x0172:
            r12 = r11
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            io.ktor.http.HttpProtocolVersion r5 = r10.getVersion()
            r2.append(r5)
            r2.append(r4)
            java.lang.String r2 = r2.toString()
            r0.L$0 = r11
            r0.L$1 = r10
            r5 = 4
            r0.label = r5
            java.lang.Object r12 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r12, (java.lang.String) r2, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r12 != r1) goto L_0x00e0
            return r1
        L_0x0196:
            io.ktor.http.Headers r10 = r11.getHeaders()
            io.ktor.util.StringValues r10 = (io.ktor.util.StringValues) r10
            java.util.List r10 = io.ktor.util.StringValuesKt.flattenEntries(r10)
            int r12 = r10.size()
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r10
            r5 = 5
            r0.label = r5
            java.lang.Object r12 = r2.writeInt(r12, r0)
            if (r12 != r1) goto L_0x01b4
            return r1
        L_0x01b4:
            java.util.Iterator r10 = r10.iterator()
        L_0x01b8:
            boolean r12 = r10.hasNext()
            if (r12 == 0) goto L_0x0220
            java.lang.Object r12 = r10.next()
            kotlin.Pair r12 = (kotlin.Pair) r12
            java.lang.Object r5 = r12.component1()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r12 = r12.component2()
            java.lang.String r12 = (java.lang.String) r12
            r6 = r2
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r5)
            r7.append(r4)
            java.lang.String r5 = r7.toString()
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r10
            r0.L$3 = r12
            r7 = 6
            r0.label = r7
            java.lang.Object r5 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r6, (java.lang.String) r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r5 != r1) goto L_0x01f4
            return r1
        L_0x01f4:
            r5 = r2
            r2 = r11
            r11 = r10
            r10 = r12
        L_0x01f8:
            r12 = r5
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r10)
            r6.append(r4)
            java.lang.String r10 = r6.toString()
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r11
            r0.L$3 = r3
            r6 = 7
            r0.label = r6
            java.lang.Object r10 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r12, (java.lang.String) r10, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r10 != r1) goto L_0x021c
            return r1
        L_0x021c:
            r10 = r11
            r11 = r2
            r2 = r5
            goto L_0x01b8
        L_0x0220:
            io.ktor.util.date.GMTDate r10 = r11.getRequestTime()
            long r5 = r10.getTimestamp()
            r0.L$0 = r2
            r0.L$1 = r11
            r0.L$2 = r3
            r10 = 8
            r0.label = r10
            java.lang.Object r10 = r2.writeLong(r5, r0)
            if (r10 != r1) goto L_0x0239
            return r1
        L_0x0239:
            r10 = r11
            r11 = r2
        L_0x023b:
            io.ktor.util.date.GMTDate r12 = r10.getResponseTime()
            long r5 = r12.getTimestamp()
            r0.L$0 = r11
            r0.L$1 = r10
            r12 = 9
            r0.label = r12
            java.lang.Object r12 = r11.writeLong(r5, r0)
            if (r12 != r1) goto L_0x0252
            return r1
        L_0x0252:
            io.ktor.util.date.GMTDate r12 = r10.getExpires()
            long r5 = r12.getTimestamp()
            r0.L$0 = r11
            r0.L$1 = r10
            r0.label = r4
            java.lang.Object r12 = r11.writeLong(r5, r0)
            if (r12 != r1) goto L_0x0267
            return r1
        L_0x0267:
            java.util.Map r12 = r10.getVaryKeys()
            int r12 = r12.size()
            r0.L$0 = r11
            r0.L$1 = r10
            r2 = 11
            r0.label = r2
            java.lang.Object r12 = r11.writeInt(r12, r0)
            if (r12 != r1) goto L_0x027e
            return r1
        L_0x027e:
            java.util.Map r12 = r10.getVaryKeys()
            java.util.Set r12 = r12.entrySet()
            java.util.Iterator r12 = r12.iterator()
        L_0x028a:
            boolean r2 = r12.hasNext()
            if (r2 == 0) goto L_0x02f5
            java.lang.Object r2 = r12.next()
            java.util.Map$Entry r2 = (java.util.Map.Entry) r2
            java.lang.Object r5 = r2.getKey()
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r2 = r2.getValue()
            java.lang.String r2 = (java.lang.String) r2
            r6 = r11
            io.ktor.utils.io.ByteWriteChannel r6 = (io.ktor.utils.io.ByteWriteChannel) r6
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            r7.<init>()
            r7.append(r5)
            r7.append(r4)
            java.lang.String r5 = r7.toString()
            r0.L$0 = r11
            r0.L$1 = r10
            r0.L$2 = r12
            r0.L$3 = r2
            r7 = 12
            r0.label = r7
            java.lang.Object r5 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r6, (java.lang.String) r5, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r5 != r1) goto L_0x02c7
            return r1
        L_0x02c7:
            r5 = r11
            r11 = r12
            r8 = r2
            r2 = r10
            r10 = r8
        L_0x02cc:
            r12 = r5
            io.ktor.utils.io.ByteWriteChannel r12 = (io.ktor.utils.io.ByteWriteChannel) r12
            java.lang.StringBuilder r6 = new java.lang.StringBuilder
            r6.<init>()
            r6.append(r10)
            r6.append(r4)
            java.lang.String r10 = r6.toString()
            r0.L$0 = r5
            r0.L$1 = r2
            r0.L$2 = r11
            r0.L$3 = r3
            r6 = 13
            r0.label = r6
            java.lang.Object r10 = io.ktor.utils.io.ByteWriteChannelKt.writeStringUtf8((io.ktor.utils.io.ByteWriteChannel) r12, (java.lang.String) r10, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r0)
            if (r10 != r1) goto L_0x02f1
            return r1
        L_0x02f1:
            r12 = r11
            r10 = r2
            r11 = r5
            goto L_0x028a
        L_0x02f5:
            byte[] r12 = r10.getBody()
            int r12 = r12.length
            r0.L$0 = r11
            r0.L$1 = r10
            r0.L$2 = r3
            r2 = 14
            r0.label = r2
            java.lang.Object r12 = r11.writeInt(r12, r0)
            if (r12 != r1) goto L_0x030b
            return r1
        L_0x030b:
            io.ktor.utils.io.ByteWriteChannel r11 = (io.ktor.utils.io.ByteWriteChannel) r11
            byte[] r10 = r10.getBody()
            r0.L$0 = r3
            r0.L$1 = r3
            r12 = 15
            r0.label = r12
            java.lang.Object r10 = io.ktor.utils.io.ByteWriteChannelKt.writeFully(r11, r10, r0)
            if (r10 != r1) goto L_0x0320
            return r1
        L_0x0320:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.writeCache(io.ktor.utils.io.ByteChannel, io.ktor.client.plugins.cache.storage.CachedResponseData, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x00d5, code lost:
        r23 = r13;
        r13 = r7;
        r7 = r23;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x01c5, code lost:
        r22 = r12;
        r12 = r8;
        r8 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:30:0x024b, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1 = (java.lang.String) r1;
        r2.L$0 = r0;
        r2.L$1 = r1;
        r2.label = 2;
        r5 = r0.readInt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:31:0x025b, code lost:
        if (r5 != r4) goto L_0x025e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:32:0x025d, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:33:0x025e, code lost:
        r7 = r0;
        r22 = r5;
        r5 = r1;
        r1 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:34:0x0264, code lost:
        r0 = ((java.lang.Number) r1).intValue();
        r2.L$0 = r7;
        r2.L$1 = r5;
        r2.I$0 = r0;
        r2.label = 3;
        r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r7, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:35:0x0277, code lost:
        if (r1 != r4) goto L_0x027a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:36:0x0279, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:37:0x027a, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r8 = new io.ktor.http.HttpStatusCode(r0, (java.lang.String) r1);
        r0 = io.ktor.http.HttpProtocolVersion.Companion;
        r2.L$0 = r7;
        r2.L$1 = r5;
        r2.L$2 = r8;
        r2.L$3 = r0;
        r2.label = 4;
        r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r7, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:38:0x0295, code lost:
        if (r1 != r4) goto L_0x0298;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:39:0x0297, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:40:0x0298, code lost:
        r22 = r7;
        r7 = r5;
        r5 = r8;
        r8 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:41:0x029e, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r0 = r0.parse((java.lang.CharSequence) r1);
        r2.L$0 = r8;
        r2.L$1 = r7;
        r2.L$2 = r5;
        r2.L$3 = r0;
        r2.label = 5;
        r1 = r8.readInt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:42:0x02b6, code lost:
        if (r1 != r4) goto L_0x02b9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:0x02b8, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:44:0x02b9, code lost:
        r1 = ((java.lang.Number) r1).intValue();
        r11 = null;
        r9 = new io.ktor.http.HeadersBuilder(0, 1, (kotlin.jvm.internal.DefaultConstructorMarker) null);
        r10 = 0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:0x02c7, code lost:
        if (r10 >= r1) goto L_0x031d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:46:0x02c9, code lost:
        r2.L$0 = r8;
        r2.L$1 = r7;
        r2.L$2 = r5;
        r2.L$3 = r0;
        r2.L$4 = r9;
        r2.L$5 = r11;
        r2.I$0 = r1;
        r2.I$1 = r10;
        r2.label = 6;
        r11 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r8, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:47:0x02e0, code lost:
        if (r11 != r4) goto L_0x02e3;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:48:0x02e2, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:49:0x02e3, code lost:
        r12 = r8;
        r8 = r9;
        r9 = r0;
        r0 = r10;
        r10 = r5;
        r5 = r1;
        r1 = r11;
        r11 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:50:0x02eb, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r7 = (java.lang.String) r1;
        r2.L$0 = r12;
        r2.L$1 = r11;
        r2.L$2 = r10;
        r2.L$3 = r9;
        r2.L$4 = r8;
        r2.L$5 = r7;
        r2.I$0 = r5;
        r2.I$1 = r0;
        r2.label = 7;
        r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r12, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:51:0x0308, code lost:
        if (r1 != r4) goto L_0x01c5;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:52:0x030a, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x030b, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r12.append(r7, (java.lang.String) r1);
        r1 = r5;
        r5 = r10;
        r7 = r11;
        r11 = null;
        r10 = r0 + 1;
        r0 = r9;
        r9 = r12;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x031d, code lost:
        r2.L$0 = r8;
        r2.L$1 = r7;
        r2.L$2 = r5;
        r2.L$3 = r0;
        r2.L$4 = r9;
        r2.L$5 = null;
        r2.label = 8;
        r1 = r8.readLong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x0332, code lost:
        if (r1 != r4) goto L_0x0335;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:0x0334, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:57:0x0335, code lost:
        r10 = r8;
        r8 = r5;
        r5 = r9;
        r9 = r7;
        r7 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:0x033a, code lost:
        r0 = io.ktor.util.date.DateJvmKt.GMTDate((java.lang.Long) r1);
        r2.L$0 = r10;
        r2.L$1 = r9;
        r2.L$2 = r8;
        r2.L$3 = r7;
        r2.L$4 = r5;
        r2.L$5 = r0;
        r2.label = 9;
        r1 = r10.readLong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0354, code lost:
        if (r1 != r4) goto L_0x0357;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:60:0x0356, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:61:0x0357, code lost:
        r1 = io.ktor.util.date.DateJvmKt.GMTDate((java.lang.Long) r1);
        r2.L$0 = r10;
        r2.L$1 = r9;
        r2.L$2 = r8;
        r2.L$3 = r7;
        r2.L$4 = r5;
        r2.L$5 = r0;
        r2.L$6 = r1;
        r2.label = 10;
        r11 = r10.readLong(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:62:0x0373, code lost:
        if (r11 != r4) goto L_0x0376;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:63:0x0375, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:64:0x0376, code lost:
        r12 = r10;
        r10 = r8;
        r8 = r5;
        r5 = r1;
        r1 = r11;
        r11 = r9;
        r9 = r7;
        r7 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:65:0x037e, code lost:
        r0 = io.ktor.util.date.DateJvmKt.GMTDate((java.lang.Long) r1);
        r2.L$0 = r12;
        r2.L$1 = r11;
        r2.L$2 = r10;
        r2.L$3 = r9;
        r2.L$4 = r8;
        r2.L$5 = r7;
        r2.L$6 = r5;
        r2.L$7 = r0;
        r2.label = 11;
        r1 = r12.readInt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:66:0x039c, code lost:
        if (r1 != r4) goto L_0x039f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:67:0x039e, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:0x039f, code lost:
        r1 = ((java.lang.Number) r1).intValue();
        r13 = kotlin.collections.MapsKt.createMapBuilder();
        r14 = r13;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:0x03aa, code lost:
        if (r6 >= r1) goto L_0x0433;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:70:0x03ac, code lost:
        r2.L$0 = r12;
        r2.L$1 = r11;
        r2.L$2 = r10;
        r2.L$3 = r9;
        r2.L$4 = r8;
        r2.L$5 = r7;
        r2.L$6 = r5;
        r2.L$7 = r0;
        r2.L$8 = r13;
        r2.L$9 = r14;
        r2.L$10 = null;
        r2.I$0 = r1;
        r2.I$1 = r6;
        r2.label = 12;
        r15 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r12, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:0x03cf, code lost:
        if (r15 != r4) goto L_0x03d2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:72:0x03d1, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:0x03d2, code lost:
        r22 = r11;
        r11 = r0;
        r0 = r12;
        r12 = r5;
        r5 = r1;
        r1 = r15;
        r15 = r9;
        r9 = r14;
        r14 = r8;
        r8 = r10;
        r10 = r13;
        r13 = r7;
        r7 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:74:0x03e1, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r1 = (java.lang.String) r1;
        r2.L$0 = r0;
        r2.L$1 = r7;
        r2.L$2 = r8;
        r2.L$3 = r15;
        r2.L$4 = r14;
        r2.L$5 = r13;
        r2.L$6 = r12;
        r2.L$7 = r11;
        r2.L$8 = r10;
        r2.L$9 = r9;
        r2.L$10 = r1;
        r2.I$0 = r5;
        r2.I$1 = r6;
        r25 = r1;
        r2.label = 13;
        r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r0, r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:75:0x040a, code lost:
        if (r1 != r4) goto L_0x040d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:76:0x040c, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:77:0x040d, code lost:
        r22 = r6;
        r6 = r25;
        r25 = r2;
        r2 = r9;
        r9 = r15;
        r15 = r10;
        r10 = r8;
        r8 = r14;
        r14 = r0;
        r0 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:78:0x041d, code lost:
        kotlin.jvm.internal.Intrinsics.checkNotNull(r1);
        r2.put(r6, (java.lang.String) r1);
        r6 = r0 + 1;
        r1 = r5;
        r0 = r11;
        r5 = r12;
        r11 = r13;
        r12 = r14;
        r13 = r15;
        r14 = r2;
        r2 = r25;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:79:0x0433, code lost:
        r1 = kotlin.collections.MapsKt.build(r13);
        r2.L$0 = r12;
        r2.L$1 = r11;
        r2.L$2 = r10;
        r2.L$3 = r9;
        r2.L$4 = r8;
        r2.L$5 = r7;
        r2.L$6 = r5;
        r2.L$7 = r0;
        r2.L$8 = r1;
        r2.L$9 = null;
        r2.L$10 = null;
        r2.label = 14;
        r6 = r12.readInt(r2);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:80:0x0456, code lost:
        if (r6 != r4) goto L_0x0459;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:81:0x0458, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:82:0x0459, code lost:
        r22 = r5;
        r5 = r0;
        r0 = r1;
        r1 = r6;
        r6 = r22;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:83:0x0460, code lost:
        r1 = new byte[((java.lang.Number) r1).intValue()];
        r2.L$0 = r11;
        r2.L$1 = r10;
        r2.L$2 = r9;
        r2.L$3 = r8;
        r2.L$4 = r7;
        r2.L$5 = r6;
        r2.L$6 = r5;
        r2.L$7 = r0;
        r2.L$8 = r1;
        r2.label = 15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:84:0x0482, code lost:
        if (io.ktor.utils.io.ByteReadChannelKt.readFully(r12, r1, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r2) != r4) goto L_0x0485;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:85:0x0484, code lost:
        return r4;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:86:0x0485, code lost:
        r20 = r0;
        r21 = r1;
        r18 = r5;
        r16 = r6;
        r15 = r7;
        r17 = r9;
        r14 = r10;
        r2 = r11;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:88:0x04a0, code lost:
        return new io.ktor.client.plugins.cache.storage.CachedResponseData(io.ktor.http.URLUtilsKt.Url(r2), r14, r15, r16, r17, r18, r8.build(), r20, r21);
     */
    /* JADX WARNING: Removed duplicated region for block: B:10:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x006a  */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:14:0x00dc  */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x011e  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0143  */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x016b  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x0188  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x01a6  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x01cc  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x01ee  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0203  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0218  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x0226  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0234  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x023c  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x002d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object readCache(io.ktor.utils.io.ByteReadChannel r25, kotlin.coroutines.Continuation<? super io.ktor.client.plugins.cache.storage.CachedResponseData> r26) {
        /*
            r24 = this;
            r0 = r25
            r1 = r26
            boolean r2 = r1 instanceof io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$3
            if (r2 == 0) goto L_0x001a
            r2 = r1
            io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$3 r2 = (io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$3) r2
            int r3 = r2.label
            r4 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r3 & r4
            if (r3 == 0) goto L_0x001a
            int r1 = r2.label
            int r1 = r1 - r4
            r2.label = r1
            r3 = r24
            goto L_0x0021
        L_0x001a:
            io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$3 r2 = new io.ktor.client.plugins.cache.storage.FileCacheStorage$readCache$3
            r3 = r24
            r2.<init>(r3, r1)
        L_0x0021:
            java.lang.Object r1 = r2.result
            java.lang.Object r4 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r5 = r2.label
            r6 = 0
            switch(r5) {
                case 0: goto L_0x023c;
                case 1: goto L_0x0234;
                case 2: goto L_0x0226;
                case 3: goto L_0x0218;
                case 4: goto L_0x0203;
                case 5: goto L_0x01ee;
                case 6: goto L_0x01cc;
                case 7: goto L_0x01a6;
                case 8: goto L_0x0188;
                case 9: goto L_0x016b;
                case 10: goto L_0x0143;
                case 11: goto L_0x011e;
                case 12: goto L_0x00dc;
                case 13: goto L_0x0093;
                case 14: goto L_0x006a;
                case 15: goto L_0x0035;
                default: goto L_0x002d;
            }
        L_0x002d:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r1 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r1)
            throw r0
        L_0x0035:
            java.lang.Object r0 = r2.L$8
            byte[] r0 = (byte[]) r0
            java.lang.Object r4 = r2.L$7
            java.util.Map r4 = (java.util.Map) r4
            java.lang.Object r5 = r2.L$6
            io.ktor.util.date.GMTDate r5 = (io.ktor.util.date.GMTDate) r5
            java.lang.Object r6 = r2.L$5
            io.ktor.util.date.GMTDate r6 = (io.ktor.util.date.GMTDate) r6
            java.lang.Object r7 = r2.L$4
            io.ktor.util.date.GMTDate r7 = (io.ktor.util.date.GMTDate) r7
            java.lang.Object r8 = r2.L$3
            io.ktor.http.HeadersBuilder r8 = (io.ktor.http.HeadersBuilder) r8
            java.lang.Object r9 = r2.L$2
            io.ktor.http.HttpProtocolVersion r9 = (io.ktor.http.HttpProtocolVersion) r9
            java.lang.Object r10 = r2.L$1
            io.ktor.http.HttpStatusCode r10 = (io.ktor.http.HttpStatusCode) r10
            java.lang.Object r2 = r2.L$0
            java.lang.String r2 = (java.lang.String) r2
            kotlin.ResultKt.throwOnFailure(r1)
            r21 = r0
            r20 = r4
            r18 = r5
            r16 = r6
            r15 = r7
            r17 = r9
            r14 = r10
            goto L_0x0492
        L_0x006a:
            java.lang.Object r0 = r2.L$8
            java.util.Map r0 = (java.util.Map) r0
            java.lang.Object r5 = r2.L$7
            io.ktor.util.date.GMTDate r5 = (io.ktor.util.date.GMTDate) r5
            java.lang.Object r6 = r2.L$6
            io.ktor.util.date.GMTDate r6 = (io.ktor.util.date.GMTDate) r6
            java.lang.Object r7 = r2.L$5
            io.ktor.util.date.GMTDate r7 = (io.ktor.util.date.GMTDate) r7
            java.lang.Object r8 = r2.L$4
            io.ktor.http.HeadersBuilder r8 = (io.ktor.http.HeadersBuilder) r8
            java.lang.Object r9 = r2.L$3
            io.ktor.http.HttpProtocolVersion r9 = (io.ktor.http.HttpProtocolVersion) r9
            java.lang.Object r10 = r2.L$2
            io.ktor.http.HttpStatusCode r10 = (io.ktor.http.HttpStatusCode) r10
            java.lang.Object r11 = r2.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0460
        L_0x0093:
            int r0 = r2.I$1
            int r5 = r2.I$0
            java.lang.Object r6 = r2.L$10
            java.lang.String r6 = (java.lang.String) r6
            java.lang.Object r9 = r2.L$9
            java.util.Map r9 = (java.util.Map) r9
            java.lang.Object r10 = r2.L$8
            java.util.Map r10 = (java.util.Map) r10
            java.lang.Object r11 = r2.L$7
            io.ktor.util.date.GMTDate r11 = (io.ktor.util.date.GMTDate) r11
            java.lang.Object r12 = r2.L$6
            io.ktor.util.date.GMTDate r12 = (io.ktor.util.date.GMTDate) r12
            java.lang.Object r13 = r2.L$5
            io.ktor.util.date.GMTDate r13 = (io.ktor.util.date.GMTDate) r13
            java.lang.Object r14 = r2.L$4
            io.ktor.http.HeadersBuilder r14 = (io.ktor.http.HeadersBuilder) r14
            java.lang.Object r15 = r2.L$3
            io.ktor.http.HttpProtocolVersion r15 = (io.ktor.http.HttpProtocolVersion) r15
            java.lang.Object r8 = r2.L$2
            io.ktor.http.HttpStatusCode r8 = (io.ktor.http.HttpStatusCode) r8
            java.lang.Object r7 = r2.L$1
            java.lang.String r7 = (java.lang.String) r7
            r25 = r0
            java.lang.Object r0 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r0 = (io.ktor.utils.io.ByteReadChannel) r0
            kotlin.ResultKt.throwOnFailure(r1)
            r22 = r0
            r0 = r25
            r25 = r2
            r2 = r9
            r9 = r15
            r15 = r10
            r10 = r8
            r8 = r14
            r14 = r22
        L_0x00d5:
            r23 = r13
            r13 = r7
            r7 = r23
            goto L_0x041d
        L_0x00dc:
            int r0 = r2.I$1
            int r5 = r2.I$0
            java.lang.Object r6 = r2.L$9
            java.util.Map r6 = (java.util.Map) r6
            java.lang.Object r7 = r2.L$8
            java.util.Map r7 = (java.util.Map) r7
            java.lang.Object r8 = r2.L$7
            io.ktor.util.date.GMTDate r8 = (io.ktor.util.date.GMTDate) r8
            java.lang.Object r9 = r2.L$6
            io.ktor.util.date.GMTDate r9 = (io.ktor.util.date.GMTDate) r9
            java.lang.Object r10 = r2.L$5
            io.ktor.util.date.GMTDate r10 = (io.ktor.util.date.GMTDate) r10
            java.lang.Object r11 = r2.L$4
            io.ktor.http.HeadersBuilder r11 = (io.ktor.http.HeadersBuilder) r11
            java.lang.Object r12 = r2.L$3
            io.ktor.http.HttpProtocolVersion r12 = (io.ktor.http.HttpProtocolVersion) r12
            java.lang.Object r13 = r2.L$2
            io.ktor.http.HttpStatusCode r13 = (io.ktor.http.HttpStatusCode) r13
            java.lang.Object r14 = r2.L$1
            java.lang.String r14 = (java.lang.String) r14
            java.lang.Object r15 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r15 = (io.ktor.utils.io.ByteReadChannel) r15
            kotlin.ResultKt.throwOnFailure(r1)
            r22 = r6
            r6 = r0
            r0 = r15
            r15 = r12
            r12 = r9
            r9 = r22
            r23 = r10
            r10 = r7
            r7 = r14
            r14 = r11
            r11 = r8
            r8 = r13
            r13 = r23
            goto L_0x03e1
        L_0x011e:
            java.lang.Object r0 = r2.L$7
            io.ktor.util.date.GMTDate r0 = (io.ktor.util.date.GMTDate) r0
            java.lang.Object r5 = r2.L$6
            io.ktor.util.date.GMTDate r5 = (io.ktor.util.date.GMTDate) r5
            java.lang.Object r7 = r2.L$5
            io.ktor.util.date.GMTDate r7 = (io.ktor.util.date.GMTDate) r7
            java.lang.Object r8 = r2.L$4
            io.ktor.http.HeadersBuilder r8 = (io.ktor.http.HeadersBuilder) r8
            java.lang.Object r9 = r2.L$3
            io.ktor.http.HttpProtocolVersion r9 = (io.ktor.http.HttpProtocolVersion) r9
            java.lang.Object r10 = r2.L$2
            io.ktor.http.HttpStatusCode r10 = (io.ktor.http.HttpStatusCode) r10
            java.lang.Object r11 = r2.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x039f
        L_0x0143:
            java.lang.Object r0 = r2.L$6
            io.ktor.util.date.GMTDate r0 = (io.ktor.util.date.GMTDate) r0
            java.lang.Object r5 = r2.L$5
            io.ktor.util.date.GMTDate r5 = (io.ktor.util.date.GMTDate) r5
            java.lang.Object r7 = r2.L$4
            io.ktor.http.HeadersBuilder r7 = (io.ktor.http.HeadersBuilder) r7
            java.lang.Object r8 = r2.L$3
            io.ktor.http.HttpProtocolVersion r8 = (io.ktor.http.HttpProtocolVersion) r8
            java.lang.Object r9 = r2.L$2
            io.ktor.http.HttpStatusCode r9 = (io.ktor.http.HttpStatusCode) r9
            java.lang.Object r10 = r2.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r5
            r5 = r0
            goto L_0x037e
        L_0x016b:
            java.lang.Object r0 = r2.L$5
            io.ktor.util.date.GMTDate r0 = (io.ktor.util.date.GMTDate) r0
            java.lang.Object r5 = r2.L$4
            io.ktor.http.HeadersBuilder r5 = (io.ktor.http.HeadersBuilder) r5
            java.lang.Object r7 = r2.L$3
            io.ktor.http.HttpProtocolVersion r7 = (io.ktor.http.HttpProtocolVersion) r7
            java.lang.Object r8 = r2.L$2
            io.ktor.http.HttpStatusCode r8 = (io.ktor.http.HttpStatusCode) r8
            java.lang.Object r9 = r2.L$1
            java.lang.String r9 = (java.lang.String) r9
            java.lang.Object r10 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r10 = (io.ktor.utils.io.ByteReadChannel) r10
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x0357
        L_0x0188:
            java.lang.Object r0 = r2.L$4
            io.ktor.http.HeadersBuilder r0 = (io.ktor.http.HeadersBuilder) r0
            java.lang.Object r5 = r2.L$3
            io.ktor.http.HttpProtocolVersion r5 = (io.ktor.http.HttpProtocolVersion) r5
            java.lang.Object r7 = r2.L$2
            io.ktor.http.HttpStatusCode r7 = (io.ktor.http.HttpStatusCode) r7
            java.lang.Object r8 = r2.L$1
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r9 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r9 = (io.ktor.utils.io.ByteReadChannel) r9
            kotlin.ResultKt.throwOnFailure(r1)
            r10 = r9
            r9 = r8
            r8 = r7
            r7 = r5
            r5 = r0
            goto L_0x033a
        L_0x01a6:
            int r0 = r2.I$1
            int r5 = r2.I$0
            java.lang.Object r7 = r2.L$5
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r2.L$4
            io.ktor.http.HeadersBuilder r8 = (io.ktor.http.HeadersBuilder) r8
            java.lang.Object r9 = r2.L$3
            io.ktor.http.HttpProtocolVersion r9 = (io.ktor.http.HttpProtocolVersion) r9
            java.lang.Object r10 = r2.L$2
            io.ktor.http.HttpStatusCode r10 = (io.ktor.http.HttpStatusCode) r10
            java.lang.Object r11 = r2.L$1
            java.lang.String r11 = (java.lang.String) r11
            java.lang.Object r12 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r12 = (io.ktor.utils.io.ByteReadChannel) r12
            kotlin.ResultKt.throwOnFailure(r1)
        L_0x01c5:
            r22 = r12
            r12 = r8
            r8 = r22
            goto L_0x030b
        L_0x01cc:
            int r0 = r2.I$1
            int r5 = r2.I$0
            java.lang.Object r7 = r2.L$4
            io.ktor.http.HeadersBuilder r7 = (io.ktor.http.HeadersBuilder) r7
            java.lang.Object r8 = r2.L$3
            io.ktor.http.HttpProtocolVersion r8 = (io.ktor.http.HttpProtocolVersion) r8
            java.lang.Object r9 = r2.L$2
            io.ktor.http.HttpStatusCode r9 = (io.ktor.http.HttpStatusCode) r9
            java.lang.Object r10 = r2.L$1
            java.lang.String r10 = (java.lang.String) r10
            java.lang.Object r11 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r11 = (io.ktor.utils.io.ByteReadChannel) r11
            kotlin.ResultKt.throwOnFailure(r1)
            r12 = r11
            r11 = r10
            r10 = r9
            r9 = r8
            r8 = r7
            goto L_0x02eb
        L_0x01ee:
            java.lang.Object r0 = r2.L$3
            io.ktor.http.HttpProtocolVersion r0 = (io.ktor.http.HttpProtocolVersion) r0
            java.lang.Object r5 = r2.L$2
            io.ktor.http.HttpStatusCode r5 = (io.ktor.http.HttpStatusCode) r5
            java.lang.Object r7 = r2.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x02b9
        L_0x0203:
            java.lang.Object r0 = r2.L$3
            io.ktor.http.HttpProtocolVersion$Companion r0 = (io.ktor.http.HttpProtocolVersion.Companion) r0
            java.lang.Object r5 = r2.L$2
            io.ktor.http.HttpStatusCode r5 = (io.ktor.http.HttpStatusCode) r5
            java.lang.Object r7 = r2.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r8 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r8 = (io.ktor.utils.io.ByteReadChannel) r8
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x029e
        L_0x0218:
            int r0 = r2.I$0
            java.lang.Object r5 = r2.L$1
            java.lang.String r5 = (java.lang.String) r5
            java.lang.Object r7 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r7 = (io.ktor.utils.io.ByteReadChannel) r7
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x027a
        L_0x0226:
            java.lang.Object r0 = r2.L$1
            java.lang.String r0 = (java.lang.String) r0
            java.lang.Object r5 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r5 = (io.ktor.utils.io.ByteReadChannel) r5
            kotlin.ResultKt.throwOnFailure(r1)
            r7 = r5
            r5 = r0
            goto L_0x0264
        L_0x0234:
            java.lang.Object r0 = r2.L$0
            io.ktor.utils.io.ByteReadChannel r0 = (io.ktor.utils.io.ByteReadChannel) r0
            kotlin.ResultKt.throwOnFailure(r1)
            goto L_0x024b
        L_0x023c:
            kotlin.ResultKt.throwOnFailure(r1)
            r2.L$0 = r0
            r1 = 1
            r2.label = r1
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r0, r2)
            if (r1 != r4) goto L_0x024b
            return r4
        L_0x024b:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            r2.L$0 = r0
            r2.L$1 = r1
            r5 = 2
            r2.label = r5
            java.lang.Object r5 = r0.readInt(r2)
            if (r5 != r4) goto L_0x025e
            return r4
        L_0x025e:
            r7 = r0
            r22 = r5
            r5 = r1
            r1 = r22
        L_0x0264:
            java.lang.Number r1 = (java.lang.Number) r1
            int r0 = r1.intValue()
            r2.L$0 = r7
            r2.L$1 = r5
            r2.I$0 = r0
            r1 = 3
            r2.label = r1
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r7, r2)
            if (r1 != r4) goto L_0x027a
            return r4
        L_0x027a:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            io.ktor.http.HttpStatusCode r8 = new io.ktor.http.HttpStatusCode
            r8.<init>(r0, r1)
            io.ktor.http.HttpProtocolVersion$Companion r0 = io.ktor.http.HttpProtocolVersion.Companion
            r2.L$0 = r7
            r2.L$1 = r5
            r2.L$2 = r8
            r2.L$3 = r0
            r1 = 4
            r2.label = r1
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r7, r2)
            if (r1 != r4) goto L_0x0298
            return r4
        L_0x0298:
            r22 = r7
            r7 = r5
            r5 = r8
            r8 = r22
        L_0x029e:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1
            io.ktor.http.HttpProtocolVersion r0 = r0.parse(r1)
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r5
            r2.L$3 = r0
            r1 = 5
            r2.label = r1
            java.lang.Object r1 = r8.readInt(r2)
            if (r1 != r4) goto L_0x02b9
            return r4
        L_0x02b9:
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            io.ktor.http.HeadersBuilder r9 = new io.ktor.http.HeadersBuilder
            r10 = 1
            r11 = 0
            r9.<init>(r6, r10, r11)
            r10 = 0
        L_0x02c7:
            if (r10 >= r1) goto L_0x031d
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r5
            r2.L$3 = r0
            r2.L$4 = r9
            r2.L$5 = r11
            r2.I$0 = r1
            r2.I$1 = r10
            r11 = 6
            r2.label = r11
            java.lang.Object r11 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r8, r2)
            if (r11 != r4) goto L_0x02e3
            return r4
        L_0x02e3:
            r12 = r8
            r8 = r9
            r9 = r0
            r0 = r10
            r10 = r5
            r5 = r1
            r1 = r11
            r11 = r7
        L_0x02eb:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r7 = r1
            java.lang.String r7 = (java.lang.String) r7
            r2.L$0 = r12
            r2.L$1 = r11
            r2.L$2 = r10
            r2.L$3 = r9
            r2.L$4 = r8
            r2.L$5 = r7
            r2.I$0 = r5
            r2.I$1 = r0
            r1 = 7
            r2.label = r1
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r12, r2)
            if (r1 != r4) goto L_0x01c5
            return r4
        L_0x030b:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            r12.append(r7, r1)
            r1 = 1
            int r0 = r0 + r1
            r1 = r5
            r5 = r10
            r7 = r11
            r11 = 0
            r10 = r0
            r0 = r9
            r9 = r12
            goto L_0x02c7
        L_0x031d:
            r2.L$0 = r8
            r2.L$1 = r7
            r2.L$2 = r5
            r2.L$3 = r0
            r2.L$4 = r9
            r1 = 0
            r2.L$5 = r1
            r1 = 8
            r2.label = r1
            java.lang.Object r1 = r8.readLong(r2)
            if (r1 != r4) goto L_0x0335
            return r4
        L_0x0335:
            r10 = r8
            r8 = r5
            r5 = r9
            r9 = r7
            r7 = r0
        L_0x033a:
            java.lang.Long r1 = (java.lang.Long) r1
            io.ktor.util.date.GMTDate r0 = io.ktor.util.date.DateJvmKt.GMTDate(r1)
            r2.L$0 = r10
            r2.L$1 = r9
            r2.L$2 = r8
            r2.L$3 = r7
            r2.L$4 = r5
            r2.L$5 = r0
            r1 = 9
            r2.label = r1
            java.lang.Object r1 = r10.readLong(r2)
            if (r1 != r4) goto L_0x0357
            return r4
        L_0x0357:
            java.lang.Long r1 = (java.lang.Long) r1
            io.ktor.util.date.GMTDate r1 = io.ktor.util.date.DateJvmKt.GMTDate(r1)
            r2.L$0 = r10
            r2.L$1 = r9
            r2.L$2 = r8
            r2.L$3 = r7
            r2.L$4 = r5
            r2.L$5 = r0
            r2.L$6 = r1
            r11 = 10
            r2.label = r11
            java.lang.Object r11 = r10.readLong(r2)
            if (r11 != r4) goto L_0x0376
            return r4
        L_0x0376:
            r12 = r10
            r10 = r8
            r8 = r5
            r5 = r1
            r1 = r11
            r11 = r9
            r9 = r7
            r7 = r0
        L_0x037e:
            java.lang.Long r1 = (java.lang.Long) r1
            io.ktor.util.date.GMTDate r0 = io.ktor.util.date.DateJvmKt.GMTDate(r1)
            r2.L$0 = r12
            r2.L$1 = r11
            r2.L$2 = r10
            r2.L$3 = r9
            r2.L$4 = r8
            r2.L$5 = r7
            r2.L$6 = r5
            r2.L$7 = r0
            r1 = 11
            r2.label = r1
            java.lang.Object r1 = r12.readInt(r2)
            if (r1 != r4) goto L_0x039f
            return r4
        L_0x039f:
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            java.util.Map r13 = kotlin.collections.MapsKt.createMapBuilder()
            r14 = r13
        L_0x03aa:
            if (r6 >= r1) goto L_0x0433
            r2.L$0 = r12
            r2.L$1 = r11
            r2.L$2 = r10
            r2.L$3 = r9
            r2.L$4 = r8
            r2.L$5 = r7
            r2.L$6 = r5
            r2.L$7 = r0
            r2.L$8 = r13
            r2.L$9 = r14
            r15 = 0
            r2.L$10 = r15
            r2.I$0 = r1
            r2.I$1 = r6
            r15 = 12
            r2.label = r15
            java.lang.Object r15 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r12, r2)
            if (r15 != r4) goto L_0x03d2
            return r4
        L_0x03d2:
            r22 = r11
            r11 = r0
            r0 = r12
            r12 = r5
            r5 = r1
            r1 = r15
            r15 = r9
            r9 = r14
            r14 = r8
            r8 = r10
            r10 = r13
            r13 = r7
            r7 = r22
        L_0x03e1:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            r2.L$0 = r0
            r2.L$1 = r7
            r2.L$2 = r8
            r2.L$3 = r15
            r2.L$4 = r14
            r2.L$5 = r13
            r2.L$6 = r12
            r2.L$7 = r11
            r2.L$8 = r10
            r2.L$9 = r9
            r2.L$10 = r1
            r2.I$0 = r5
            r2.I$1 = r6
            r25 = r1
            r1 = 13
            r2.label = r1
            java.lang.Object r1 = io.ktor.utils.io.ByteReadChannelKt.readUTF8Line(r0, r2)
            if (r1 != r4) goto L_0x040d
            return r4
        L_0x040d:
            r22 = r6
            r6 = r25
            r25 = r2
            r2 = r9
            r9 = r15
            r15 = r10
            r10 = r8
            r8 = r14
            r14 = r0
            r0 = r22
            goto L_0x00d5
        L_0x041d:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            java.lang.String r1 = (java.lang.String) r1
            r2.put(r6, r1)
            r1 = 1
            int r6 = r0 + 1
            r1 = r5
            r0 = r11
            r5 = r12
            r11 = r13
            r12 = r14
            r13 = r15
            r14 = r2
            r2 = r25
            goto L_0x03aa
        L_0x0433:
            java.util.Map r1 = kotlin.collections.MapsKt.build(r13)
            r2.L$0 = r12
            r2.L$1 = r11
            r2.L$2 = r10
            r2.L$3 = r9
            r2.L$4 = r8
            r2.L$5 = r7
            r2.L$6 = r5
            r2.L$7 = r0
            r2.L$8 = r1
            r6 = 0
            r2.L$9 = r6
            r2.L$10 = r6
            r6 = 14
            r2.label = r6
            java.lang.Object r6 = r12.readInt(r2)
            if (r6 != r4) goto L_0x0459
            return r4
        L_0x0459:
            r22 = r5
            r5 = r0
            r0 = r1
            r1 = r6
            r6 = r22
        L_0x0460:
            java.lang.Number r1 = (java.lang.Number) r1
            int r1 = r1.intValue()
            byte[] r1 = new byte[r1]
            r2.L$0 = r11
            r2.L$1 = r10
            r2.L$2 = r9
            r2.L$3 = r8
            r2.L$4 = r7
            r2.L$5 = r6
            r2.L$6 = r5
            r2.L$7 = r0
            r2.L$8 = r1
            r13 = 15
            r2.label = r13
            java.lang.Object r2 = io.ktor.utils.io.ByteReadChannelKt.readFully((io.ktor.utils.io.ByteReadChannel) r12, (byte[]) r1, (kotlin.coroutines.Continuation<? super kotlin.Unit>) r2)
            if (r2 != r4) goto L_0x0485
            return r4
        L_0x0485:
            r20 = r0
            r21 = r1
            r18 = r5
            r16 = r6
            r15 = r7
            r17 = r9
            r14 = r10
            r2 = r11
        L_0x0492:
            io.ktor.client.plugins.cache.storage.CachedResponseData r0 = new io.ktor.client.plugins.cache.storage.CachedResponseData
            io.ktor.http.Url r13 = io.ktor.http.URLUtilsKt.Url((java.lang.String) r2)
            io.ktor.http.Headers r19 = r8.build()
            r12 = r0
            r12.<init>(r13, r14, r15, r16, r17, r18, r19, r20, r21)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cache.storage.FileCacheStorage.readCache(io.ktor.utils.io.ByteReadChannel, kotlin.coroutines.Continuation):java.lang.Object");
    }
}
