package org.videolan.vlc.webserver;

import java.io.File;
import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$clearFileDownloads$1", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$clearFileDownloads$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int label;
    final /* synthetic */ RemoteAccessServer this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$clearFileDownloads$1(RemoteAccessServer remoteAccessServer, Continuation<? super RemoteAccessServer$clearFileDownloads$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessServer;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$clearFileDownloads$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessServer$clearFileDownloads$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        File[] listFiles;
        IntrinsicsKt.getCOROUTINE_SUSPENDED();
        if (this.label == 0) {
            ResultKt.throwOnFailure(obj);
            File file = new File(this.this$0.getDownloadFolder());
            if (file.isDirectory() && (listFiles = file.listFiles()) != null) {
                for (File delete : listFiles) {
                    delete.delete();
                }
            }
            return Unit.INSTANCE;
        }
        throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
    }
}
