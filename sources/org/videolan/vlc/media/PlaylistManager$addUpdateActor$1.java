package org.videolan.vlc.media;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.PlaylistManager$addUpdateActor$1", f = "PlaylistManager.kt", i = {}, l = {627, 628, 630}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: PlaylistManager.kt */
final class PlaylistManager$addUpdateActor$1 extends SuspendLambda implements Function2<ActorScope<Unit>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ PlaylistManager this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistManager$addUpdateActor$1(PlaylistManager playlistManager, Continuation<? super PlaylistManager$addUpdateActor$1> continuation) {
        super(2, continuation);
        this.this$0 = playlistManager;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        PlaylistManager$addUpdateActor$1 playlistManager$addUpdateActor$1 = new PlaylistManager$addUpdateActor$1(this.this$0, continuation);
        playlistManager$addUpdateActor$1.L$0 = obj;
        return playlistManager$addUpdateActor$1;
    }

    public final Object invoke(ActorScope<Unit> actorScope, Continuation<? super Unit> continuation) {
        return ((PlaylistManager$addUpdateActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0080 A[RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 0
            r3 = 0
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0033
            if (r1 == r6) goto L_0x002b
            if (r1 == r5) goto L_0x0023
            if (r1 != r4) goto L_0x001b
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0043
        L_0x001b:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x0023:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x006c
        L_0x002b:
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0051
        L_0x0033:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.channels.ActorScope r9 = (kotlinx.coroutines.channels.ActorScope) r9
            kotlinx.coroutines.channels.Channel r9 = r9.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r9 = r9.iterator()
            r1 = r9
        L_0x0043:
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.L$0 = r1
            r8.label = r6
            java.lang.Object r9 = r1.hasNext(r9)
            if (r9 != r0) goto L_0x0051
            return r0
        L_0x0051:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0081
            r1.next()
            org.videolan.vlc.media.PlaylistManager r9 = r8.this$0
            r7 = r8
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r8.L$0 = r1
            r8.label = r5
            java.lang.Object r9 = org.videolan.vlc.media.PlaylistManager.determinePrevAndNextIndices$default(r9, r3, r7, r6, r2)
            if (r9 != r0) goto L_0x006c
            return r0
        L_0x006c:
            org.videolan.vlc.media.PlaylistManager r9 = r8.this$0
            r9.executeUpdate()
            org.videolan.vlc.media.PlaylistManager r9 = r8.this$0
            r7 = r8
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r8.L$0 = r1
            r8.label = r4
            java.lang.Object r9 = org.videolan.vlc.media.PlaylistManager.saveMediaList$default(r9, r3, r7, r6, r2)
            if (r9 != r0) goto L_0x0043
            return r0
        L_0x0081:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.PlaylistManager$addUpdateActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
