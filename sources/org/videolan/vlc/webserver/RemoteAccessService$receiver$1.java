package org.videolan.vlc.webserver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import androidx.lifecycle.LifecycleOwnerKt;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.Job;
import org.videolan.resources.Constants;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0017¨\u0006\b"}, d2 = {"org/videolan/vlc/webserver/RemoteAccessService$receiver$1", "Landroid/content/BroadcastReceiver;", "onReceive", "", "context", "Landroid/content/Context;", "intent", "Landroid/content/Intent;", "webserver_release"}, k = 1, mv = {1, 9, 0}, xi = 48)
/* compiled from: RemoteAccessService.kt */
public final class RemoteAccessService$receiver$1 extends BroadcastReceiver {
    final /* synthetic */ RemoteAccessService this$0;

    RemoteAccessService$receiver$1(RemoteAccessService remoteAccessService) {
        this.this$0 = remoteAccessService;
    }

    public void onReceive(Context context, Intent intent) {
        String action;
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(intent, "intent");
        if (this.this$0.server != null && (action = intent.getAction()) != null) {
            switch (action.hashCode()) {
                case -1978971863:
                    if (action.equals(Constants.ACTION_START_SERVER)) {
                        this.this$0.startServerActor.m1139trySendJP2dKIU(Constants.ACTION_START_SERVER);
                        return;
                    }
                    return;
                case -1072186985:
                    if (action.equals(Constants.ACTION_STOP_SERVER)) {
                        this.this$0.startServerActor.m1139trySendJP2dKIU(Constants.ACTION_STOP_SERVER);
                        return;
                    }
                    return;
                case -814714461:
                    if (action.equals(Constants.ACTION_DISABLE_SERVER)) {
                        Job unused = BuildersKt__Builders_commonKt.launch$default(LifecycleOwnerKt.getLifecycleScope(this.this$0), (CoroutineContext) null, (CoroutineStart) null, new RemoteAccessService$receiver$1$onReceive$1(this.this$0, (Continuation<? super RemoteAccessService$receiver$1$onReceive$1>) null), 3, (Object) null);
                        return;
                    }
                    return;
                case 402746524:
                    if (action.equals(Constants.ACTION_RESTART_SERVER)) {
                        this.this$0.startServerActor.m1139trySendJP2dKIU(Constants.ACTION_RESTART_SERVER);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    }
}
