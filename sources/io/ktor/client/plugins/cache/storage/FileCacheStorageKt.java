package io.ktor.client.plugins.cache.storage;

import java.io.File;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineDispatcher;
import kotlinx.coroutines.Dispatchers;

@Metadata(d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0018\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005¨\u0006\u0006"}, d2 = {"FileStorage", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "directory", "Ljava/io/File;", "dispatcher", "Lkotlinx/coroutines/CoroutineDispatcher;", "ktor-client-core"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: FileCacheStorage.kt */
public final class FileCacheStorageKt {
    public static /* synthetic */ CacheStorage FileStorage$default(File file, CoroutineDispatcher coroutineDispatcher, int i, Object obj) {
        if ((i & 2) != 0) {
            coroutineDispatcher = Dispatchers.getIO();
        }
        return FileStorage(file, coroutineDispatcher);
    }

    public static final CacheStorage FileStorage(File file, CoroutineDispatcher coroutineDispatcher) {
        Intrinsics.checkNotNullParameter(file, "directory");
        Intrinsics.checkNotNullParameter(coroutineDispatcher, "dispatcher");
        return new CachingCacheStorage(new FileCacheStorage(file, coroutineDispatcher));
    }
}
