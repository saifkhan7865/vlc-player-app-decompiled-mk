package org.videolan.vlc.media;

import android.content.Context;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;
import org.videolan.vlc.media.MediaUtils;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/media/Action;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.media.MediaUtils$SuspendDialogCallback$actor$1", f = "MediaUtils.kt", i = {0, 1, 2}, l = {395, 401, 408}, m = "invokeSuspend", n = {"$this$actor", "$this$actor", "$this$actor"}, s = {"L$0", "L$0", "L$0"})
/* compiled from: MediaUtils.kt */
final class MediaUtils$SuspendDialogCallback$actor$1 extends SuspendLambda implements Function2<ActorScope<Action>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Context $context;
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaUtils.SuspendDialogCallback this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaUtils$SuspendDialogCallback$actor$1(MediaUtils.SuspendDialogCallback suspendDialogCallback, Context context, Continuation<? super MediaUtils$SuspendDialogCallback$actor$1> continuation) {
        super(2, continuation);
        this.this$0 = suspendDialogCallback;
        this.$context = context;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaUtils$SuspendDialogCallback$actor$1 mediaUtils$SuspendDialogCallback$actor$1 = new MediaUtils$SuspendDialogCallback$actor$1(this.this$0, this.$context, continuation);
        mediaUtils$SuspendDialogCallback$actor$1.L$0 = obj;
        return mediaUtils$SuspendDialogCallback$actor$1;
    }

    public final Object invoke(ActorScope<Action> actorScope, Continuation<? super Unit> continuation) {
        return ((MediaUtils$SuspendDialogCallback$actor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:14:0x005e A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:15:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:18:0x006a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r11) {
        /*
            r10 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r10.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0040
            if (r1 == r4) goto L_0x0034
            if (r1 == r3) goto L_0x0027
            if (r1 != r2) goto L_0x001f
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r5 = (kotlinx.coroutines.channels.ActorScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
        L_0x001c:
            r11 = r5
            goto L_0x00f5
        L_0x001f:
            java.lang.IllegalStateException r11 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r11.<init>(r0)
            throw r11
        L_0x0027:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r5 = (kotlinx.coroutines.channels.ActorScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x00b6
        L_0x0034:
            java.lang.Object r1 = r10.L$1
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            java.lang.Object r5 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r5 = (kotlinx.coroutines.channels.ActorScope) r5
            kotlin.ResultKt.throwOnFailure(r11)
            goto L_0x0062
        L_0x0040:
            kotlin.ResultKt.throwOnFailure(r11)
            java.lang.Object r11 = r10.L$0
            kotlinx.coroutines.channels.ActorScope r11 = (kotlinx.coroutines.channels.ActorScope) r11
            kotlinx.coroutines.channels.Channel r1 = r11.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r1 = r1.iterator()
        L_0x004f:
            r5 = r10
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r10.L$0 = r11
            r10.L$1 = r1
            r10.label = r4
            java.lang.Object r5 = r1.hasNext(r5)
            if (r5 != r0) goto L_0x005f
            return r0
        L_0x005f:
            r9 = r5
            r5 = r11
            r11 = r9
        L_0x0062:
            java.lang.Boolean r11 = (java.lang.Boolean) r11
            boolean r11 = r11.booleanValue()
            if (r11 == 0) goto L_0x0106
            java.lang.Object r11 = r1.next()
            org.videolan.vlc.media.Action r11 = (org.videolan.vlc.media.Action) r11
            org.videolan.vlc.media.Connect r6 = org.videolan.vlc.media.Connect.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x00cc
            org.videolan.vlc.PlaybackService$Companion r11 = org.videolan.vlc.PlaybackService.Companion
            org.videolan.vlc.PlaybackService r11 = r11.getInstance()
            if (r11 == 0) goto L_0x0093
            kotlinx.coroutines.channels.Channel r6 = r5.getChannel()
            org.videolan.vlc.media.Task r7 = new org.videolan.vlc.media.Task
            org.videolan.vlc.media.MediaUtils$SuspendDialogCallback r8 = r10.this$0
            kotlin.jvm.functions.Function2 r8 = r8.task
            r7.<init>(r11, r8)
            r6.m1139trySendJP2dKIU(r7)
            goto L_0x00ca
        L_0x0093:
            org.videolan.vlc.PlaybackService$Companion r11 = org.videolan.vlc.PlaybackService.Companion
            android.content.Context r6 = r10.$context
            r11.start(r6)
            org.videolan.vlc.PlaybackService$Companion r11 = org.videolan.vlc.PlaybackService.Companion
            kotlinx.coroutines.flow.MutableStateFlow r11 = r11.getServiceFlow()
            kotlinx.coroutines.flow.Flow r11 = (kotlinx.coroutines.flow.Flow) r11
            kotlinx.coroutines.flow.Flow r11 = kotlinx.coroutines.flow.FlowKt.filterNotNull(r11)
            r6 = r10
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r10.L$0 = r5
            r10.L$1 = r1
            r10.label = r3
            java.lang.Object r11 = kotlinx.coroutines.flow.FlowKt.first(r11, r6)
            if (r11 != r0) goto L_0x00b6
            return r0
        L_0x00b6:
            org.videolan.vlc.media.MediaUtils$SuspendDialogCallback r6 = r10.this$0
            org.videolan.vlc.PlaybackService r11 = (org.videolan.vlc.PlaybackService) r11
            kotlinx.coroutines.channels.Channel r7 = r5.getChannel()
            org.videolan.vlc.media.Task r8 = new org.videolan.vlc.media.Task
            kotlin.jvm.functions.Function2 r6 = r6.task
            r8.<init>(r11, r6)
            r7.m1139trySendJP2dKIU(r8)
        L_0x00ca:
            r11 = r5
            goto L_0x004f
        L_0x00cc:
            org.videolan.vlc.media.Disconnect r6 = org.videolan.vlc.media.Disconnect.INSTANCE
            boolean r6 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r6)
            if (r6 == 0) goto L_0x00da
            org.videolan.vlc.media.MediaUtils$SuspendDialogCallback r11 = r10.this$0
            r11.dismiss()
            goto L_0x00ca
        L_0x00da:
            boolean r6 = r11 instanceof org.videolan.vlc.media.Task
            if (r6 == 0) goto L_0x00ca
            org.videolan.vlc.media.Task r11 = (org.videolan.vlc.media.Task) r11
            kotlin.jvm.functions.Function2 r6 = r11.getTask()
            org.videolan.vlc.PlaybackService r11 = r11.getService()
            r10.L$0 = r5
            r10.L$1 = r1
            r10.label = r2
            java.lang.Object r11 = r6.invoke(r11, r10)
            if (r11 != r0) goto L_0x001c
            return r0
        L_0x00f5:
            org.videolan.vlc.media.MediaUtils$SuspendDialogCallback r5 = r10.this$0
            kotlinx.coroutines.Job r5 = r5.getJob()
            r6 = 0
            kotlinx.coroutines.Job.DefaultImpls.cancel$default((kotlinx.coroutines.Job) r5, (java.util.concurrent.CancellationException) r6, (int) r4, (java.lang.Object) r6)
            org.videolan.vlc.media.MediaUtils$SuspendDialogCallback r5 = r10.this$0
            r5.dismiss()
            goto L_0x004f
        L_0x0106:
            kotlin.Unit r11 = kotlin.Unit.INSTANCE
            return r11
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.media.MediaUtils$SuspendDialogCallback$actor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
