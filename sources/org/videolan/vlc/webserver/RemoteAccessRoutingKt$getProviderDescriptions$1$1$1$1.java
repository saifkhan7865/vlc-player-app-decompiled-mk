package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;
import org.videolan.vlc.webserver.RemoteAccessServer;
import org.videolan.vlc.webserver.websockets.RemoteAccessWebSockets;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1", f = "RemoteAccessRouting.kt", i = {}, l = {1393}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessRouting.kt */
final class RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaWrapper $datasetEntry;
    final /* synthetic */ String $desc;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1(MediaWrapper mediaWrapper, String str, Continuation<? super RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1> continuation) {
        super(2, continuation);
        this.$datasetEntry = mediaWrapper;
        this.$desc = str;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1(this.$datasetEntry, this.$desc, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessRoutingKt$getProviderDescriptions$1$1$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    public final Object invokeSuspend(Object obj) {
        Object coroutine_suspended = IntrinsicsKt.getCOROUTINE_SUSPENDED();
        int i = this.label;
        if (i == 0) {
            ResultKt.throwOnFailure(obj);
            RemoteAccessWebSockets remoteAccessWebSockets = RemoteAccessWebSockets.INSTANCE;
            String uri = this.$datasetEntry.getUri().toString();
            Intrinsics.checkNotNullExpressionValue(uri, "toString(...)");
            this.label = 1;
            if (remoteAccessWebSockets.sendToAll(new RemoteAccessServer.BrowserDescription(uri, this.$desc), this) == coroutine_suspended) {
                return coroutine_suspended;
            }
        } else if (i == 1) {
            ResultKt.throwOnFailure(obj);
        } else {
            throw new IllegalStateException("call to 'resume' before 'invoke' with coroutine");
        }
        return Unit.INSTANCE;
    }
}
