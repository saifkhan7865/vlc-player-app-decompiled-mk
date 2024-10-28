package io.ktor.client.plugins.cache.storage;

import io.ktor.http.Url;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.jvm.functions.Function0;

@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011J/\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b0\u0007H¦@ø\u0001\u0000¢\u0006\u0002\u0010\tJ\u001f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\u0006\u0010\u0004\u001a\u00020\u0005H¦@ø\u0001\u0000¢\u0006\u0002\u0010\fJ!\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0003H¦@ø\u0001\u0000¢\u0006\u0002\u0010\u0010\u0002\u0004\n\u0002\b\u0019¨\u0006\u0012"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CacheStorage;", "", "find", "Lio/ktor/client/plugins/cache/storage/CachedResponseData;", "url", "Lio/ktor/http/Url;", "varyKeys", "", "", "(Lio/ktor/http/Url;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "findAll", "", "(Lio/ktor/http/Url;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "store", "", "data", "(Lio/ktor/http/Url;Lio/ktor/client/plugins/cache/storage/CachedResponseData;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Companion", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
/* compiled from: HttpCacheStorage.kt */
public interface CacheStorage {
    public static final Companion Companion = Companion.$$INSTANCE;

    Object find(Url url, Map<String, String> map, Continuation<? super CachedResponseData> continuation);

    Object findAll(Url url, Continuation<? super Set<CachedResponseData>> continuation);

    Object store(Url url, CachedResponseData cachedResponseData, Continuation<? super Unit> continuation);

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lio/ktor/client/plugins/cache/storage/CacheStorage$Companion;", "", "()V", "Disabled", "Lio/ktor/client/plugins/cache/storage/CacheStorage;", "getDisabled", "()Lio/ktor/client/plugins/cache/storage/CacheStorage;", "Unlimited", "Lkotlin/Function0;", "getUnlimited", "()Lkotlin/jvm/functions/Function0;", "ktor-client-core"}, k = 1, mv = {1, 8, 0}, xi = 48)
    /* compiled from: HttpCacheStorage.kt */
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();
        private static final CacheStorage Disabled = DisabledStorage.INSTANCE;
        private static final Function0<CacheStorage> Unlimited = CacheStorage$Companion$Unlimited$1.INSTANCE;

        private Companion() {
        }

        public final Function0<CacheStorage> getUnlimited() {
            return Unlimited;
        }

        public final CacheStorage getDisabled() {
            return Disabled;
        }
    }
}
