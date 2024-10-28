package org.videolan.vlc.webserver;

import androidx.lifecycle.LifecycleOwnerKt;
import androidx.lifecycle.Observer;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;

@Metadata(d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0002H\u0016¨\u0006\u0006"}, d2 = {"org/videolan/vlc/webserver/RemoteAccessService$startServerActor$1$observer$1", "Landroidx/lifecycle/Observer;", "Lorg/videolan/vlc/webserver/ServerStatus;", "onChanged", "", "serverStatus", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessService.kt */
public final class RemoteAccessService$startServerActor$1$observer$1 implements Observer<ServerStatus> {
    final /* synthetic */ RemoteAccessService this$0;

    RemoteAccessService$startServerActor$1$observer$1(RemoteAccessService remoteAccessService) {
        this.this$0 = remoteAccessService;
    }

    public void onChanged(ServerStatus serverStatus) {
        Intrinsics.checkNotNullParameter(serverStatus, "serverStatus");
        if (serverStatus == ServerStatus.STOPPED) {
            RemoteAccessServer remoteAccessServer = null;
            Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new RemoteAccessService$startServerActor$1$observer$1$onChanged$1(this.this$0, (Continuation<? super RemoteAccessService$startServerActor$1$observer$1$onChanged$1>) null), 3, (Object) null);
            RemoteAccessServer access$getServer$p = this.this$0.server;
            if (access$getServer$p == null) {
                Intrinsics.throwUninitializedPropertyAccessException("server");
            } else {
                remoteAccessServer = access$getServer$p;
            }
            remoteAccessServer.getServerStatus().removeObserver(this);
        }
    }
}
