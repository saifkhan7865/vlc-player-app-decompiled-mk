package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/CbAction;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PlaybackService$setupScope$1", f = "PlaybackService.kt", i = {}, l = {817, 825}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaybackService.kt */
final class PlaybackService$setupScope$1 extends SuspendLambda implements Function2<ActorScope<CbAction>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlaybackService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaybackService$setupScope$1(PlaybackService playbackService, Continuation<? super PlaybackService$setupScope$1> continuation) {
        super(2, continuation);
        this.this$0 = playbackService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlaybackService$setupScope$1 playbackService$setupScope$1 = new PlaybackService$setupScope$1(this.this$0, continuation);
        playbackService$setupScope$1.L$0 = obj;
        return playbackService$setupScope$1;
    }

    public final Object invoke(ActorScope<CbAction> actorScope, Continuation<? super Unit> continuation) {
        return ((PlaybackService$setupScope$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x012c
        L_0x0017:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001f:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0047
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.channels.ActorScope r9 = (kotlinx.coroutines.channels.ActorScope) r9
            kotlinx.coroutines.channels.Channel r9 = r9.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r9 = r9.iterator()
        L_0x0036:
            r1 = r8
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r8.L$0 = r9
            r8.label = r3
            java.lang.Object r1 = r9.hasNext(r1)
            if (r1 != r0) goto L_0x0044
            return r0
        L_0x0044:
            r7 = r1
            r1 = r9
            r9 = r7
        L_0x0047:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x012f
            java.lang.Object r9 = r1.next()
            org.videolan.vlc.CbAction r9 = (org.videolan.vlc.CbAction) r9
            org.videolan.vlc.CbUpdate r4 = org.videolan.vlc.CbUpdate.INSTANCE
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x0077
            org.videolan.vlc.PlaybackService r9 = r8.this$0
            java.util.List r9 = r9.callbacks
            java.util.Iterator r9 = r9.iterator()
        L_0x0067:
            boolean r4 = r9.hasNext()
            if (r4 == 0) goto L_0x012c
            java.lang.Object r4 = r9.next()
            org.videolan.vlc.PlaybackService$Callback r4 = (org.videolan.vlc.PlaybackService.Callback) r4
            r4.update()
            goto L_0x0067
        L_0x0077:
            boolean r4 = r9 instanceof org.videolan.vlc.CbMediaEvent
            if (r4 == 0) goto L_0x009c
            org.videolan.vlc.PlaybackService r4 = r8.this$0
            java.util.List r4 = r4.callbacks
            java.util.Iterator r4 = r4.iterator()
        L_0x0085:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x012c
            java.lang.Object r5 = r4.next()
            org.videolan.vlc.PlaybackService$Callback r5 = (org.videolan.vlc.PlaybackService.Callback) r5
            r6 = r9
            org.videolan.vlc.CbMediaEvent r6 = (org.videolan.vlc.CbMediaEvent) r6
            org.videolan.libvlc.interfaces.IMedia$Event r6 = r6.getEvent()
            r5.onMediaEvent(r6)
            goto L_0x0085
        L_0x009c:
            boolean r4 = r9 instanceof org.videolan.vlc.CbMediaPlayerEvent
            if (r4 == 0) goto L_0x00c1
            org.videolan.vlc.PlaybackService r4 = r8.this$0
            java.util.List r4 = r4.callbacks
            java.util.Iterator r4 = r4.iterator()
        L_0x00aa:
            boolean r5 = r4.hasNext()
            if (r5 == 0) goto L_0x012c
            java.lang.Object r5 = r4.next()
            org.videolan.vlc.PlaybackService$Callback r5 = (org.videolan.vlc.PlaybackService.Callback) r5
            r6 = r9
            org.videolan.vlc.CbMediaPlayerEvent r6 = (org.videolan.vlc.CbMediaPlayerEvent) r6
            org.videolan.libvlc.MediaPlayer$Event r6 = r6.getEvent()
            r5.onMediaPlayerEvent(r6)
            goto L_0x00aa
        L_0x00c1:
            boolean r4 = r9 instanceof org.videolan.vlc.CbRemove
            if (r4 == 0) goto L_0x00d5
            org.videolan.vlc.PlaybackService r4 = r8.this$0
            java.util.List r4 = r4.callbacks
            org.videolan.vlc.CbRemove r9 = (org.videolan.vlc.CbRemove) r9
            org.videolan.vlc.PlaybackService$Callback r9 = r9.getCb()
            r4.remove(r9)
            goto L_0x012c
        L_0x00d5:
            boolean r4 = r9 instanceof org.videolan.vlc.CbAdd
            if (r4 == 0) goto L_0x00e9
            org.videolan.vlc.PlaybackService r4 = r8.this$0
            java.util.List r4 = r4.callbacks
            org.videolan.vlc.CbAdd r9 = (org.videolan.vlc.CbAdd) r9
            org.videolan.vlc.PlaybackService$Callback r9 = r9.getCb()
            r4.add(r9)
            goto L_0x012c
        L_0x00e9:
            org.videolan.vlc.ShowNotification r4 = org.videolan.vlc.ShowNotification.INSTANCE
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x00f7
            org.videolan.vlc.PlaybackService r9 = r8.this$0
            r9.showNotificationInternal()
            goto L_0x012c
        L_0x00f7:
            boolean r4 = r9 instanceof org.videolan.vlc.HideNotification
            if (r4 == 0) goto L_0x0107
            org.videolan.vlc.PlaybackService r4 = r8.this$0
            org.videolan.vlc.HideNotification r9 = (org.videolan.vlc.HideNotification) r9
            boolean r9 = r9.getRemove()
            r4.hideNotificationInternal(r9)
            goto L_0x012c
        L_0x0107:
            org.videolan.vlc.UpdateMeta r4 = org.videolan.vlc.UpdateMeta.INSTANCE
            boolean r4 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)
            if (r4 == 0) goto L_0x011f
            org.videolan.vlc.PlaybackService r9 = r8.this$0
            r4 = r8
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r9 = r9.updateMetadataInternal(r4)
            if (r9 != r0) goto L_0x012c
            return r0
        L_0x011f:
            org.videolan.vlc.UpdateState r4 = org.videolan.vlc.UpdateState.INSTANCE
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)
            if (r9 == 0) goto L_0x012c
            org.videolan.vlc.PlaybackService r9 = r8.this$0
            r9.executeUpdate(r3)
        L_0x012c:
            r9 = r1
            goto L_0x0036
        L_0x012f:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PlaybackService$setupScope$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
