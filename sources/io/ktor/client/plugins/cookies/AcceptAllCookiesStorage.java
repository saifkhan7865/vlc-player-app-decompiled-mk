package io.ktor.client.plugins.cookies;

import io.ktor.http.Cookie;
import io.ktor.util.date.GMTDate;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\u0018\u00002\u00020\u0019B\u0007¢\u0006\u0004\b\u0001\u0010\u0002J#\u0010\b\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u0005H@ø\u0001\u0000¢\u0006\u0004\b\b\u0010\tJ\u0017\u0010\f\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\nH\u0002¢\u0006\u0004\b\f\u0010\rJ\u000f\u0010\u000e\u001a\u00020\u0007H\u0016¢\u0006\u0004\b\u000e\u0010\u0002J!\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00050\u000f2\u0006\u0010\u0004\u001a\u00020\u0003H@ø\u0001\u0000¢\u0006\u0004\b\u0010\u0010\u0011R\u001a\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00050\u00128\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014R\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017\u0002\u0004\n\u0002\b\u0019¨\u0006\u0018"}, d2 = {"Lio/ktor/client/plugins/cookies/AcceptAllCookiesStorage;", "<init>", "()V", "Lio/ktor/http/Url;", "requestUrl", "Lio/ktor/http/Cookie;", "cookie", "", "addCookie", "(Lio/ktor/http/Url;Lio/ktor/http/Cookie;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "timestamp", "cleanup", "(J)V", "close", "", "get", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "", "container", "Ljava/util/List;", "Lkotlinx/coroutines/sync/Mutex;", "mutex", "Lkotlinx/coroutines/sync/Mutex;", "ktor-client-core", "Lio/ktor/client/plugins/cookies/CookiesStorage;"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: AcceptAllCookiesStorage.kt */
public final class AcceptAllCookiesStorage implements CookiesStorage {
    private final List<Cookie> container = new ArrayList();
    private final Mutex mutex = MutexKt.Mutex$default(false, 1, (Object) null);
    private volatile /* synthetic */ long oldestCookie = 0;

    public void close() {
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x003f  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0060 A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0078 A[Catch:{ all -> 0x008f }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object get(io.ktor.http.Url r9, kotlin.coroutines.Continuation<? super java.util.List<io.ktor.http.Cookie>> r10) {
        /*
            r8 = this;
            boolean r0 = r10 instanceof io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1
            if (r0 == 0) goto L_0x0014
            r0 = r10
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1 r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r10 = r0.label
            int r10 = r10 - r2
            r0.label = r10
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1 r0 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$get$1
            r0.<init>(r8, r10)
        L_0x0019:
            java.lang.Object r10 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x003f
            if (r2 != r4) goto L_0x0037
            java.lang.Object r9 = r0.L$2
            kotlinx.coroutines.sync.Mutex r9 = (kotlinx.coroutines.sync.Mutex) r9
            java.lang.Object r1 = r0.L$1
            io.ktor.http.Url r1 = (io.ktor.http.Url) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage) r0
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0056
        L_0x0037:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r10 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r10)
            throw r9
        L_0x003f:
            kotlin.ResultKt.throwOnFailure(r10)
            kotlinx.coroutines.sync.Mutex r10 = r8.mutex
            r0.L$0 = r8
            r0.L$1 = r9
            r0.L$2 = r10
            r0.label = r4
            java.lang.Object r0 = r10.lock(r3, r0)
            if (r0 != r1) goto L_0x0053
            return r1
        L_0x0053:
            r0 = r8
            r1 = r9
            r9 = r10
        L_0x0056:
            long r4 = io.ktor.util.date.DateJvmKt.getTimeMillis()     // Catch:{ all -> 0x008f }
            long r6 = r0.oldestCookie     // Catch:{ all -> 0x008f }
            int r10 = (r4 > r6 ? 1 : (r4 == r6 ? 0 : -1))
            if (r10 < 0) goto L_0x0063
            r0.cleanup(r4)     // Catch:{ all -> 0x008f }
        L_0x0063:
            java.util.List<io.ktor.http.Cookie> r10 = r0.container     // Catch:{ all -> 0x008f }
            java.lang.Iterable r10 = (java.lang.Iterable) r10     // Catch:{ all -> 0x008f }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x008f }
            r0.<init>()     // Catch:{ all -> 0x008f }
            java.util.Collection r0 = (java.util.Collection) r0     // Catch:{ all -> 0x008f }
            java.util.Iterator r10 = r10.iterator()     // Catch:{ all -> 0x008f }
        L_0x0072:
            boolean r2 = r10.hasNext()     // Catch:{ all -> 0x008f }
            if (r2 == 0) goto L_0x0089
            java.lang.Object r2 = r10.next()     // Catch:{ all -> 0x008f }
            r4 = r2
            io.ktor.http.Cookie r4 = (io.ktor.http.Cookie) r4     // Catch:{ all -> 0x008f }
            boolean r4 = io.ktor.client.plugins.cookies.CookiesStorageKt.matches(r4, r1)     // Catch:{ all -> 0x008f }
            if (r4 == 0) goto L_0x0072
            r0.add(r2)     // Catch:{ all -> 0x008f }
            goto L_0x0072
        L_0x0089:
            java.util.List r0 = (java.util.List) r0     // Catch:{ all -> 0x008f }
            r9.unlock(r3)
            return r0
        L_0x008f:
            r10 = move-exception
            r9.unlock(r3)
            goto L_0x0095
        L_0x0094:
            throw r10
        L_0x0095:
            goto L_0x0094
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.get(io.ktor.http.Url, kotlin.coroutines.Continuation):java.lang.Object");
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:12:0x0045  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0068 A[Catch:{ all -> 0x0097 }] */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0025  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object addCookie(io.ktor.http.Url r6, io.ktor.http.Cookie r7, kotlin.coroutines.Continuation<? super kotlin.Unit> r8) {
        /*
            r5 = this;
            boolean r0 = r8 instanceof io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1
            if (r0 == 0) goto L_0x0014
            r0 = r8
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1 r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r8 = r0.label
            int r8 = r8 - r2
            r0.label = r8
            goto L_0x0019
        L_0x0014:
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1 r0 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$1
            r0.<init>(r5, r8)
        L_0x0019:
            java.lang.Object r8 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L_0x0045
            if (r2 != r4) goto L_0x003d
            java.lang.Object r6 = r0.L$3
            kotlinx.coroutines.sync.Mutex r6 = (kotlinx.coroutines.sync.Mutex) r6
            java.lang.Object r7 = r0.L$2
            io.ktor.http.Cookie r7 = (io.ktor.http.Cookie) r7
            java.lang.Object r1 = r0.L$1
            io.ktor.http.Url r1 = (io.ktor.http.Url) r1
            java.lang.Object r0 = r0.L$0
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage r0 = (io.ktor.client.plugins.cookies.AcceptAllCookiesStorage) r0
            kotlin.ResultKt.throwOnFailure(r8)
            r8 = r6
            r6 = r1
            goto L_0x005c
        L_0x003d:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r7 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r7)
            throw r6
        L_0x0045:
            kotlin.ResultKt.throwOnFailure(r8)
            kotlinx.coroutines.sync.Mutex r8 = r5.mutex
            r0.L$0 = r5
            r0.L$1 = r6
            r0.L$2 = r7
            r0.L$3 = r8
            r0.label = r4
            java.lang.Object r0 = r8.lock(r3, r0)
            if (r0 != r1) goto L_0x005b
            return r1
        L_0x005b:
            r0 = r5
        L_0x005c:
            java.lang.String r1 = r7.getName()     // Catch:{ all -> 0x0097 }
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch:{ all -> 0x0097 }
            boolean r1 = kotlin.text.StringsKt.isBlank(r1)     // Catch:{ all -> 0x0097 }
            if (r1 != 0) goto L_0x008f
            java.util.List<io.ktor.http.Cookie> r1 = r0.container     // Catch:{ all -> 0x0097 }
            io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$2$2 r2 = new io.ktor.client.plugins.cookies.AcceptAllCookiesStorage$addCookie$2$2     // Catch:{ all -> 0x0097 }
            r2.<init>(r7, r6)     // Catch:{ all -> 0x0097 }
            kotlin.jvm.functions.Function1 r2 = (kotlin.jvm.functions.Function1) r2     // Catch:{ all -> 0x0097 }
            kotlin.collections.CollectionsKt.removeAll(r1, r2)     // Catch:{ all -> 0x0097 }
            java.util.List<io.ktor.http.Cookie> r1 = r0.container     // Catch:{ all -> 0x0097 }
            io.ktor.http.Cookie r6 = io.ktor.client.plugins.cookies.CookiesStorageKt.fillDefaults(r7, r6)     // Catch:{ all -> 0x0097 }
            r1.add(r6)     // Catch:{ all -> 0x0097 }
            io.ktor.util.date.GMTDate r6 = r7.getExpires()     // Catch:{ all -> 0x0097 }
            if (r6 == 0) goto L_0x008f
            long r6 = r6.getTimestamp()     // Catch:{ all -> 0x0097 }
            long r1 = r0.oldestCookie     // Catch:{ all -> 0x0097 }
            int r4 = (r1 > r6 ? 1 : (r1 == r6 ? 0 : -1))
            if (r4 <= 0) goto L_0x008f
            r0.oldestCookie = r6     // Catch:{ all -> 0x0097 }
        L_0x008f:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE     // Catch:{ all -> 0x0097 }
            r8.unlock(r3)
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        L_0x0097:
            r6 = move-exception
            r8.unlock(r3)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.client.plugins.cookies.AcceptAllCookiesStorage.addCookie(io.ktor.http.Url, io.ktor.http.Cookie, kotlin.coroutines.Continuation):java.lang.Object");
    }

    private final void cleanup(long j) {
        CollectionsKt.removeAll(this.container, new AcceptAllCookiesStorage$cleanup$1(j));
        long j2 = Long.MAX_VALUE;
        for (Cookie expires : this.container) {
            GMTDate expires2 = expires.getExpires();
            if (expires2 != null) {
                j2 = Math.min(j2, expires2.getTimestamp());
            }
        }
        this.oldestCookie = j2;
    }
}
