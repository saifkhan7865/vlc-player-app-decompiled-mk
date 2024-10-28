package org.videolan.vlc.viewmodels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/viewmodels/MediaAction;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.CallBackDelegate$registerCallBacks$2", f = "CallBackDelegate.kt", i = {}, l = {111}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: CallBackDelegate.kt */
final class CallBackDelegate$registerCallBacks$2 extends SuspendLambda implements Function2<ActorScope<MediaAction>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ CallBackDelegate this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    CallBackDelegate$registerCallBacks$2(CallBackDelegate callBackDelegate, Continuation<? super CallBackDelegate$registerCallBacks$2> continuation) {
        super(2, continuation);
        this.this$0 = callBackDelegate;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        CallBackDelegate$registerCallBacks$2 callBackDelegate$registerCallBacks$2 = new CallBackDelegate$registerCallBacks$2(this.this$0, continuation);
        callBackDelegate$registerCallBacks$2.L$0 = obj;
        return callBackDelegate$registerCallBacks$2;
    }

    public final Object invoke(ActorScope<MediaAction> actorScope, Continuation<? super Unit> continuation) {
        return ((CallBackDelegate$registerCallBacks$2) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r9) {
        /*
            r8 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r8.label
            r2 = 1
            if (r1 == 0) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            java.lang.Object r1 = r8.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r9)
            goto L_0x0039
        L_0x0013:
            java.lang.IllegalStateException r9 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r9.<init>(r0)
            throw r9
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r9)
            java.lang.Object r9 = r8.L$0
            kotlinx.coroutines.channels.ActorScope r9 = (kotlinx.coroutines.channels.ActorScope) r9
            kotlinx.coroutines.channels.Channel r9 = r9.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r9 = r9.iterator()
            r1 = r9
        L_0x002b:
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.L$0 = r1
            r8.label = r2
            java.lang.Object r9 = r1.hasNext(r9)
            if (r9 != r0) goto L_0x0039
            return r0
        L_0x0039:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r9 = r9.booleanValue()
            if (r9 == 0) goto L_0x0076
            java.lang.Object r9 = r1.next()
            org.videolan.vlc.viewmodels.MediaAction r9 = (org.videolan.vlc.viewmodels.MediaAction) r9
            boolean r3 = r9 instanceof org.videolan.vlc.viewmodels.MediaDeletedAction
            r4 = 0
            if (r3 == 0) goto L_0x005f
            org.videolan.vlc.viewmodels.MediaDeletedAction r9 = (org.videolan.vlc.viewmodels.MediaDeletedAction) r9
            long[] r9 = r9.getIds()
            org.videolan.vlc.viewmodels.CallBackDelegate r3 = r8.this$0
            int r5 = r9.length
        L_0x0055:
            if (r4 >= r5) goto L_0x002b
            r6 = r9[r4]
            r3.deleteThumbnail(r6)
            int r4 = r4 + 1
            goto L_0x0055
        L_0x005f:
            boolean r3 = r9 instanceof org.videolan.vlc.viewmodels.MediaConvertedExternalAction
            if (r3 == 0) goto L_0x002b
            org.videolan.vlc.viewmodels.MediaConvertedExternalAction r9 = (org.videolan.vlc.viewmodels.MediaConvertedExternalAction) r9
            long[] r9 = r9.getIds()
            org.videolan.vlc.viewmodels.CallBackDelegate r3 = r8.this$0
            int r5 = r9.length
        L_0x006c:
            if (r4 >= r5) goto L_0x002b
            r6 = r9[r4]
            r3.deleteThumbnail(r6)
            int r4 = r4 + 1
            goto L_0x006c
        L_0x0076:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.CallBackDelegate$registerCallBacks$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
