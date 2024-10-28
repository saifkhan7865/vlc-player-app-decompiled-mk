package org.videolan.vlc.webserver;

import android.util.Log;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.flow.FlowKt;
import org.videolan.tools.AppScope;
import org.videolan.tools.Connection;
import org.videolan.tools.NetworkMonitor;
import org.videolan.tools.SettingsKt;
import org.videolan.vlc.providers.NetworkProvider;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$start$3", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$start$3 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$start$3(RemoteAccessServer remoteAccessServer, Continuation<? super RemoteAccessServer$start$3> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessServer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$start$3(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessServer$start$3) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            if (!this.this$0.settings.getBoolean(SettingsKt.REMOTE_ACCESS_NETWORK_BROWSER_CONTENT, false)) {
                Log.i("HttpSharingServer", "Preventing the network monitor to be collected as the network browsing is disabled");
                return Unit.INSTANCE;
            }
            final NetworkProvider networkProvider = new NetworkProvider(this.this$0.context, this.this$0.getNetworkSharesLiveData(), (String) null);
            final RemoteAccessServer remoteAccessServer = this.this$0;
            FlowKt.launchIn(FlowKt.onEach(((NetworkMonitor) NetworkMonitor.Companion.getInstance(this.this$0.context)).getConnectionFlow(), new AnonymousClass1((Continuation<? super AnonymousClass1>) null)), AppScope.INSTANCE);
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }

    @Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H@"}, d2 = {"<anonymous>", "", "it", "Lorg/videolan/tools/Connection;"}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$start$3$1", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.webserver.RemoteAccessServer$start$3$1  reason: invalid class name */
    /* compiled from: RemoteAccessServer.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<Connection, Continuation<? super Unit>, Object> {
        /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(networkProvider, remoteAccessServer, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(Connection connection, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(connection, continuation)).invokeSuspend(Unit.INSTANCE);
        }

        public final Object invokeSuspend(Object obj) {
            IntrinsicsKt.getCOROUTINE_SUSPENDED();
            if (this.label == 0) {
                ResultKt.throwOnFailure(obj);
                if (((Connection) this.L$0).getConnected()) {
                    networkProvider.refresh();
                } else {
                    remoteAccessServer.getNetworkSharesLiveData().clear();
                }
                return Unit.INSTANCE;
            }
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
