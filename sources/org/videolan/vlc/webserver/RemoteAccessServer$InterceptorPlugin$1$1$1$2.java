package org.videolan.vlc.webserver;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import java.util.List;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.Boxing;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.webserver.RemoteAccessServer;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0000\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$InterceptorPlugin$1$1$1$2", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$InterceptorPlugin$1$1$1$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Object>, Object> {
    final /* synthetic */ RemoteAccessServer.RemoteAccessConnection $connection;
    final /* synthetic */ List<RemoteAccessServer.RemoteAccessConnection> $oldConnections;
    int label;
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$InterceptorPlugin$1$1$1$2(RemoteAccessServer remoteAccessServer, List<RemoteAccessServer.RemoteAccessConnection> list, RemoteAccessServer.RemoteAccessConnection remoteAccessConnection, Continuation<? super RemoteAccessServer$InterceptorPlugin$1$1$1$2> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessServer;
        this.$oldConnections = list;
        this.$connection = remoteAccessConnection;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$InterceptorPlugin$1$1$1$2(this.this$0, this.$oldConnections, this.$connection, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<Object> continuation) {
        return ((RemoteAccessServer$InterceptorPlugin$1$1$1$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            try {
                MutableLiveData access$get_serverConnections$p = this.this$0._serverConnections;
                List<RemoteAccessServer.RemoteAccessConnection> list = this.$oldConnections;
                Intrinsics.checkNotNull(list);
                List mutableList = CollectionsKt.toMutableList(list);
                mutableList.add(this.$connection);
                access$get_serverConnections$p.setValue(mutableList);
                return Unit.INSTANCE;
            } catch (Exception e) {
                return Boxing.boxInt(Log.e("InterceptorPlugin", e.getMessage(), e));
            }
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
    }
}
