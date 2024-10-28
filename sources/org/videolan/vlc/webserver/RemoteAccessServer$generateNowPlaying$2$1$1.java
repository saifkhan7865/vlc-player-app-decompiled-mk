package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessServer$generateNowPlaying$2$1$1", f = "RemoteAccessServer.kt", i = {}, l = {}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessServer.kt */
final class RemoteAccessServer$generateNowPlaying$2$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Ref.LongRef $sleepTimer;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessServer$generateNowPlaying$2$1$1(Ref.LongRef longRef, Continuation<? super RemoteAccessServer$generateNowPlaying$2$1$1> continuation) {
        super(2, continuation);
        this.$sleepTimer = longRef;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new RemoteAccessServer$generateNowPlaying$2$1$1(this.$sleepTimer, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessServer$generateNowPlaying$2$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:4:0x001a, code lost:
        r0 = r0.getTime();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r3) {
        /*
            r2 = this;
            kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r0 = r2.label
            if (r0 != 0) goto L_0x002c
            kotlin.ResultKt.throwOnFailure(r3)
            kotlin.jvm.internal.Ref$LongRef r3 = r2.$sleepTimer
            org.videolan.vlc.PlaybackService$Companion r0 = org.videolan.vlc.PlaybackService.Companion
            androidx.lifecycle.MutableLiveData r0 = r0.getPlayerSleepTime()
            java.lang.Object r0 = r0.getValue()
            java.util.Calendar r0 = (java.util.Calendar) r0
            if (r0 == 0) goto L_0x0025
            java.util.Date r0 = r0.getTime()
            if (r0 == 0) goto L_0x0025
            long r0 = r0.getTime()
            goto L_0x0027
        L_0x0025:
            r0 = 0
        L_0x0027:
            r3.element = r0
            kotlin.Unit r3 = kotlin.Unit.INSTANCE
            return r3
        L_0x002c:
            java.lang.IllegalStateException r3 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r3.<init>(r0)
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessServer$generateNowPlaying$2$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
