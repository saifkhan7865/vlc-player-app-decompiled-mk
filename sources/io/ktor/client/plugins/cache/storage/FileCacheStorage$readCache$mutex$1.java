package io.ktor.client.plugins.cache.storage;

import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.sync.Mutex;
import kotlinx.coroutines.sync.MutexKt;

@Metadata(d1 = {"\u0000\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/sync/Mutex;", "invoke"}, k = 3, mv = {1, 8, 0}, xi = 48)
/* compiled from: FileCacheStorage.kt */
final class FileCacheStorage$readCache$mutex$1 extends Lambda implements Function0<Mutex> {
    public static final FileCacheStorage$readCache$mutex$1 INSTANCE = new FileCacheStorage$readCache$mutex$1();

    FileCacheStorage$readCache$mutex$1() {
        super(0);
    }

    public final Mutex invoke() {
        return MutexKt.Mutex$default(false, 1, (Object) null);
    }
}
