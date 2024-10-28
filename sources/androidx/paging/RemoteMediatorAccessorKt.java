package androidx.paging;

import io.ktor.server.auth.OAuth2RequestParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001aD\u0010\u0000\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\u0001\"\b\b\u0000\u0010\u0002*\u00020\u0004\"\b\b\u0001\u0010\u0003*\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0012\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u0002H\u0002\u0012\u0004\u0012\u0002H\u00030\bH\u0000¨\u0006\t"}, d2 = {"RemoteMediatorAccessor", "Landroidx/paging/RemoteMediatorAccessor;", "Key", "Value", "", "scope", "Lkotlinx/coroutines/CoroutineScope;", "delegate", "Landroidx/paging/RemoteMediator;", "paging-common"}, k = 2, mv = {1, 8, 0}, xi = 48)
/* compiled from: RemoteMediatorAccessor.kt */
public final class RemoteMediatorAccessorKt {
    public static final <Key, Value> RemoteMediatorAccessor<Key, Value> RemoteMediatorAccessor(CoroutineScope coroutineScope, RemoteMediator<Key, Value> remoteMediator) {
        Intrinsics.checkNotNullParameter(coroutineScope, OAuth2RequestParameters.Scope);
        Intrinsics.checkNotNullParameter(remoteMediator, "delegate");
        return new RemoteMediatorAccessImpl<>(coroutineScope, remoteMediator);
    }
}
