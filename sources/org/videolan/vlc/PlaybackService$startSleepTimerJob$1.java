package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$startSleepTimerJob$1", f = "PlaybackService.kt", i = {0, 1}, l = {1911, 1915}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
/* compiled from: PlaybackService.kt */
final class PlaybackService$startSleepTimerJob$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$startSleepTimerJob$1(PlaybackService playbackService, Continuation<? super PlaybackService$startSleepTimerJob$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlaybackService$startSleepTimerJob$1 playbackService$startSleepTimerJob$1 = new PlaybackService$startSleepTimerJob$1(this.this$0, continuation);
        playbackService$startSleepTimerJob$1.L$0 = obj;
        return playbackService$startSleepTimerJob$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$startSleepTimerJob$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0060, code lost:
        if (r5.mediaEndReached != false) goto L_0x0065;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0063, code lost:
        if (r11 != false) goto L_0x0065;
     */
    /* JADX WARNING: Removed duplicated region for block: B:11:0x0035  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x0086  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009a A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r4) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            java.lang.Object r1 = r10.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x002f
        L_0x0017:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x001f:
            java.lang.Object r1 = r10.L$0
            kotlinx.coroutines.CoroutineScope r1 = (kotlinx.coroutines.CoroutineScope) r1
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x007e
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.CoroutineScope r11 = (kotlinx.coroutines.CoroutineScope) r11
            r1 = r11
        L_0x002f:
            boolean r11 = kotlinx.coroutines.CoroutineScopeKt.isActive(r1)
            if (r11 == 0) goto L_0x009b
            org.videolan.vlc.PlaybackService$Companion r11 = org.videolan.vlc.PlaybackService.Companion
            androidx.lifecycle.MutableLiveData r11 = r11.getPlayerSleepTime()
            java.lang.Object r11 = r11.getValue()
            java.util.Calendar r11 = (java.util.Calendar) r11
            if (r11 == 0) goto L_0x007e
            org.videolan.vlc.PlaybackService r5 = r10.this$0
            long r6 = java.lang.System.currentTimeMillis()
            long r8 = r11.getTimeInMillis()
            int r11 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r11 <= 0) goto L_0x0053
            r11 = 1
            goto L_0x0054
        L_0x0053:
            r11 = 0
        L_0x0054:
            boolean r6 = r5.getWaitForMediaEnd()
            if (r6 == 0) goto L_0x0063
            if (r11 == 0) goto L_0x007e
            boolean r11 = r5.mediaEndReached
            if (r11 == 0) goto L_0x007e
            goto L_0x0065
        L_0x0063:
            if (r11 == 0) goto L_0x007e
        L_0x0065:
            kotlinx.coroutines.MainCoroutineDispatcher r11 = kotlinx.coroutines.Dispatchers.getMain()
            kotlin.coroutines.CoroutineContext r11 = (kotlin.coroutines.CoroutineContext) r11
            org.videolan.vlc.PlaybackService$startSleepTimerJob$1$1$1 r6 = new org.videolan.vlc.PlaybackService$startSleepTimerJob$1$1$1
            r7 = 0
            r6.<init>(r5, r7)
            kotlin.jvm.functions.Function2 r6 = (kotlin.jvm.functions.Function2) r6
            r10.L$0 = r1
            r10.label = r4
            java.lang.Object r11 = kotlinx.coroutines.BuildersKt.withContext(r11, r6, r10)
            if (r11 != r0) goto L_0x007e
            return r0
        L_0x007e:
            org.videolan.vlc.PlaybackService r11 = r10.this$0
            boolean r11 = r11.mediaEndReached
            if (r11 == 0) goto L_0x008b
            org.videolan.vlc.PlaybackService r11 = r10.this$0
            r11.mediaEndReached = r2
        L_0x008b:
            r11 = r10
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r10.L$0 = r1
            r10.label = r3
            r5 = 1000(0x3e8, double:4.94E-321)
            java.lang.Object r11 = kotlinx.coroutines.DelayKt.delay(r5, r11)
            if (r11 != r0) goto L_0x002f
            return r0
        L_0x009b:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService$startSleepTimerJob$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
