package io.ktor.server.sessions;

import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.jvm.internal.Intrinsics;

@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\u001a$\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0000¨\u0006\u0007"}, d2 = {"platformCache", "Lio/ktor/server/sessions/Cache;", "", "delegate", "Lio/ktor/server/sessions/SessionStorage;", "idleTimeout", "", "ktor-server-sessions"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: CacheStorageJvm.kt */
public final class CacheStorageJvmKt {
    public static final Cache<String, String> platformCache(SessionStorage sessionStorage, long j) {
        Intrinsics.checkNotNullParameter(sessionStorage, "delegate");
        return new BaseTimeoutCache<>(j, true, new SoftReferenceCache(new CacheStorageJvmKt$platformCache$referenceCache$1(sessionStorage, (Continuation<? super CacheStorageJvmKt$platformCache$referenceCache$1>) null)));
    }
}
