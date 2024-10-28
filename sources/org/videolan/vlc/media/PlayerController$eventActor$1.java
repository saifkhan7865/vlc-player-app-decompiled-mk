package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;
import org.videolan.libvlc.MediaPlayer;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/libvlc/MediaPlayer$Event;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlayerController$eventActor$1", f = "PlayerController.kt", i = {}, l = {318, 338}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlayerController.kt */
final class PlayerController$eventActor$1 extends SuspendLambda implements Function2<ActorScope<MediaPlayer.Event>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlayerController this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlayerController$eventActor$1(PlayerController playerController, Continuation<? super PlayerController$eventActor$1> continuation) {
        super(2, continuation);
        this.this$0 = playerController;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlayerController$eventActor$1 playerController$eventActor$1 = new PlayerController$eventActor$1(this.this$0, continuation);
        playerController$eventActor$1.L$0 = obj;
        return playerController$eventActor$1;
    }

    public final Object invoke(ActorScope<MediaPlayer.Event> actorScope, Continuation<? super Unit> continuation) {
        return ((PlayerController$eventActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x004f  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x00e4  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r15) {
        /*
            r14 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r14.label
            r2 = 1
            r3 = 2
            if (r1 == 0) goto L_0x0027
            if (r1 == r2) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            java.lang.Object r1 = r14.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x00e1
        L_0x0017:
            java.lang.IllegalStateException r15 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r15.<init>(r0)
            throw r15
        L_0x001f:
            java.lang.Object r1 = r14.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r15)
            goto L_0x0047
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r15)
            java.lang.Object r15 = r14.L$0
            kotlinx.coroutines.channels.ActorScope r15 = (kotlinx.coroutines.channels.ActorScope) r15
            kotlinx.coroutines.channels.Channel r15 = r15.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r15 = r15.iterator()
        L_0x0036:
            r1 = r14
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r14.L$0 = r15
            r14.label = r2
            java.lang.Object r1 = r15.hasNext(r1)
            if (r1 != r0) goto L_0x0044
            return r0
        L_0x0044:
            r13 = r1
            r1 = r15
            r15 = r13
        L_0x0047:
            java.lang.Boolean r15 = (java.lang.Boolean) r15
            boolean r15 = r15.booleanValue()
            if (r15 == 0) goto L_0x00e4
            java.lang.Object r15 = r1.next()
            org.videolan.libvlc.MediaPlayer$Event r15 = (org.videolan.libvlc.MediaPlayer.Event) r15
            int r4 = r15.type
            r5 = 260(0x104, float:3.64E-43)
            if (r4 == r5) goto L_0x00c5
            r5 = 261(0x105, float:3.66E-43)
            if (r4 == r5) goto L_0x00bf
            r5 = 273(0x111, float:3.83E-43)
            if (r4 == r5) goto L_0x00b1
            switch(r4) {
                case 266: goto L_0x00ab;
                case 267: goto L_0x0085;
                case 268: goto L_0x007b;
                case 269: goto L_0x0071;
                case 270: goto L_0x0067;
                default: goto L_0x0066;
            }
        L_0x0066:
            goto L_0x00cb
        L_0x0067:
            org.videolan.vlc.media.PlayerController r4 = r14.this$0
            boolean r5 = r15.getPausable()
            r4.setPausable(r5)
            goto L_0x00cb
        L_0x0071:
            org.videolan.vlc.media.PlayerController r4 = r14.this$0
            boolean r5 = r15.getSeekable()
            r4.setSeekable(r5)
            goto L_0x00cb
        L_0x007b:
            org.videolan.vlc.media.PlayerController r4 = r14.this$0
            float r5 = r15.getPositionChanged()
            r4.setLastPosition(r5)
            goto L_0x00cb
        L_0x0085:
            long r4 = r15.getTimeChanged()
            org.videolan.vlc.media.PlayerController r6 = r14.this$0
            long r6 = r6.lastTime
            long r6 = r4 - r6
            long r6 = java.lang.Math.abs(r6)
            r8 = 950(0x3b6, double:4.694E-321)
            int r10 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r10 <= 0) goto L_0x00cb
            org.videolan.vlc.media.PlayerController r6 = r14.this$0
            r11 = 2
            r12 = 0
            r9 = 0
            r7 = r4
            org.videolan.vlc.media.PlayerController.updateProgress$default(r6, r7, r9, r11, r12)
            org.videolan.vlc.media.PlayerController r6 = r14.this$0
            r6.lastTime = r4
            goto L_0x00cb
        L_0x00ab:
            org.videolan.vlc.media.PlayerController r4 = r14.this$0
            r4.setPlaybackStopped()
            goto L_0x00cb
        L_0x00b1:
            org.videolan.vlc.media.PlayerController r5 = r14.this$0
            long r8 = r15.getLengthChanged()
            r10 = 1
            r11 = 0
            r6 = 0
            org.videolan.vlc.media.PlayerController.updateProgress$default(r5, r6, r8, r10, r11)
            goto L_0x00cb
        L_0x00bf:
            org.videolan.vlc.media.PlayerController$Companion r4 = org.videolan.vlc.media.PlayerController.Companion
            org.videolan.vlc.media.PlayerController.playbackState = r3
            goto L_0x00cb
        L_0x00c5:
            org.videolan.vlc.media.PlayerController$Companion r4 = org.videolan.vlc.media.PlayerController.Companion
            r4 = 3
            org.videolan.vlc.media.PlayerController.playbackState = r4
        L_0x00cb:
            org.videolan.vlc.media.PlayerController r4 = r14.this$0
            org.videolan.vlc.media.MediaPlayerEventListener r4 = r4.mediaplayerEventListener
            if (r4 == 0) goto L_0x00e1
            r5 = r14
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r14.L$0 = r1
            r14.label = r3
            java.lang.Object r15 = r4.onEvent(r15, r5)
            if (r15 != r0) goto L_0x00e1
            return r0
        L_0x00e1:
            r15 = r1
            goto L_0x0036
        L_0x00e4:
            kotlin.Unit r15 = kotlin.Unit.INSTANCE
            return r15
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlayerController$eventActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
