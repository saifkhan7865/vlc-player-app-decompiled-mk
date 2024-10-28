package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/Notification;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.MediaParsingService$setupScope$2", f = "MediaParsingService.kt", i = {}, l = {159, 160}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaParsingService.kt */
final class MediaParsingService$setupScope$2 extends SuspendLambda implements Function2<ActorScope<Notification>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaParsingService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaParsingService$setupScope$2(MediaParsingService mediaParsingService, Continuation<? super MediaParsingService$setupScope$2> continuation) {
        super(2, continuation);
        this.this$0 = mediaParsingService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaParsingService$setupScope$2 mediaParsingService$setupScope$2 = new MediaParsingService$setupScope$2(this.this$0, continuation);
        mediaParsingService$setupScope$2.L$0 = obj;
        return mediaParsingService$setupScope$2;
    }

    public final Object invoke(ActorScope<Notification> actorScope, Continuation<? super Unit> continuation) {
        return ((MediaParsingService$setupScope$2) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            goto L_0x0099
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
            if (r9 == 0) goto L_0x009b
            java.lang.Object r9 = r1.next()
            org.videolan.vlc.Notification r9 = (org.videolan.vlc.Notification) r9
            boolean r4 = r9 instanceof org.videolan.vlc.Show
            if (r4 == 0) goto L_0x0073
            org.videolan.vlc.MediaParsingService r4 = r8.this$0
            org.videolan.vlc.Show r9 = (org.videolan.vlc.Show) r9
            int r5 = r9.getDone()
            int r9 = r9.getScheduled()
            r6 = r8
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r9 = r4.showNotification(r5, r9, r6)
            if (r9 != r0) goto L_0x0099
            return r0
        L_0x0073:
            boolean r4 = r9 instanceof org.videolan.vlc.Error
            if (r4 == 0) goto L_0x008c
            org.videolan.vlc.MediaParsingService$Companion r4 = org.videolan.vlc.MediaParsingService.Companion
            androidx.lifecycle.MutableLiveData r4 = r4.getDiscoveryError()
            org.videolan.vlc.DiscoveryError r5 = new org.videolan.vlc.DiscoveryError
            org.videolan.vlc.Error r9 = (org.videolan.vlc.Error) r9
            java.lang.String r9 = r9.getEntryPoint()
            r5.<init>(r9)
            r4.setValue(r5)
            goto L_0x0099
        L_0x008c:
            org.videolan.vlc.Hide r4 = org.videolan.vlc.Hide.INSTANCE
            boolean r9 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r9, (java.lang.Object) r4)
            if (r9 == 0) goto L_0x0099
            org.videolan.vlc.MediaParsingService r9 = r8.this$0
            r9.hideNotification()
        L_0x0099:
            r9 = r1
            goto L_0x0036
        L_0x009b:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.MediaParsingService$setupScope$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
