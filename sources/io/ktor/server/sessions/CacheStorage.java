package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0019\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ\u0019\u0010\u000f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u000eJ!\u0010\u0010\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH@ø\u0001\u0000¢\u0006\u0002\u0010\u0012R\u001a\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007X\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n\u0002\u0004\n\u0002\b\u0019¨\u0006\u0013"}, d2 = {"Lio/ktor/server/sessions/CacheStorage;", "Lio/ktor/server/sessions/SessionStorage;", "delegate", "idleTimeout", "", "(Lio/ktor/server/sessions/SessionStorage;J)V", "cache", "Lio/ktor/server/sessions/Cache;", "", "getDelegate", "()Lio/ktor/server/sessions/SessionStorage;", "invalidate", "", "id", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "read", "write", "value", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "ktor-server-sessions"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheStorage.kt */
public final class CacheStorage implements SessionStorage {
    private final Cache<String, String> cache;
    private final SessionStorage delegate;

    public CacheStorage(SessionStorage sessionStorage, long j) {
        Intrinsics.checkNotNullParameter(sessionStorage, "delegate");
        this.delegate = sessionStorage;
        this.cache = CacheStorageJvmKt.platformCache(sessionStorage, j);
    }

    public final SessionStorage getDelegate() {
        return this.delegate;
    }

    public Object read(String str, Continuation<? super String> continuation) {
        return this.cache.getOrCompute(str, continuation);
    }

    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r7v8, resolved type: java.lang.Object} */
    /* JADX DEBUG: Multi-variable search result rejected for TypeSearchVarInfo{r8v5, resolved type: java.lang.String} */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0049  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x0068  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x006b  */
    /* JADX WARNING: Removed duplicated region for block: B:8:0x0026  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Object write(java.lang.String r7, java.lang.String r8, kotlin.coroutines.Continuation<? super kotlin.Unit> r9) {
        /*
            r6 = this;
            boolean r0 = r9 instanceof io.ktor.server.sessions.CacheStorage$write$1
            if (r0 == 0) goto L_0x0014
            r0 = r9
            io.ktor.server.sessions.CacheStorage$write$1 r0 = (io.ktor.server.sessions.CacheStorage$write$1) r0
            int r1 = r0.label
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r1 = r1 & r2
            if (r1 == 0) goto L_0x0014
            int r9 = r0.label
            int r9 = r9 - r2
            r0.label = r9
            goto L_0x0019
        L_0x0014:
            io.ktor.server.sessions.CacheStorage$write$1 r0 = new io.ktor.server.sessions.CacheStorage$write$1
            r0.<init>(r6, r9)
        L_0x0019:
            java.lang.Object r9 = r0.result
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 2
            r4 = 1
            r5 = 0
            if (r2 == 0) goto L_0x0049
            if (r2 == r4) goto L_0x0036
            if (r2 != r3) goto L_0x002e
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0081
        L_0x002e:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L_0x0036:
            java.lang.Object r7 = r0.L$2
            r8 = r7
            java.lang.String r8 = (java.lang.String) r8
            java.lang.Object r7 = r0.L$1
            java.lang.String r7 = (java.lang.String) r7
            java.lang.Object r2 = r0.L$0
            io.ktor.server.sessions.CacheStorage r2 = (io.ktor.server.sessions.CacheStorage) r2
            kotlin.ResultKt.throwOnFailure(r9)     // Catch:{ all -> 0x0047 }
            goto L_0x005c
        L_0x0047:
            goto L_0x0061
        L_0x0049:
            kotlin.ResultKt.throwOnFailure(r9)
            r0.L$0 = r6     // Catch:{ all -> 0x005f }
            r0.L$1 = r7     // Catch:{ all -> 0x005f }
            r0.L$2 = r8     // Catch:{ all -> 0x005f }
            r0.label = r4     // Catch:{ all -> 0x005f }
            java.lang.Object r9 = r6.read(r7, r0)     // Catch:{ all -> 0x005f }
            if (r9 != r1) goto L_0x005b
            return r1
        L_0x005b:
            r2 = r6
        L_0x005c:
            java.lang.String r9 = (java.lang.String) r9     // Catch:{ all -> 0x0047 }
            goto L_0x0062
        L_0x005f:
            r2 = r6
        L_0x0061:
            r9 = r5
        L_0x0062:
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r8)
            if (r9 == 0) goto L_0x006b
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        L_0x006b:
            io.ktor.server.sessions.Cache<java.lang.String, java.lang.String> r9 = r2.cache
            r9.invalidate(r7)
            io.ktor.server.sessions.SessionStorage r9 = r2.delegate
            r0.L$0 = r5
            r0.L$1 = r5
            r0.L$2 = r5
            r0.label = r3
            java.lang.Object r7 = r9.write(r7, r8, r0)
            if (r7 != r1) goto L_0x0081
            return r1
        L_0x0081:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: io.ktor.server.sessions.CacheStorage.write(java.lang.String, java.lang.String, kotlin.coroutines.Continuation):java.lang.Object");
    }

    public Object invalidate(String str, Continuation<? super Unit> continuation) {
        this.cache.invalidate(str);
        Object invalidate = this.delegate.invalidate(str, continuation);
        return invalidate == IntrinsicsKt.getCOROUTINE_SUSPENDED() ? invalidate : Unit.INSTANCE;
    }
}
