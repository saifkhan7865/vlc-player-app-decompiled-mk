package org.videolan.moviepedia.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/moviepedia/viewmodel/MediaMetadataFull;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.moviepedia.viewmodel.MediaMetadataModel$updateActor$1", f = "MediaMetadataModel.kt", i = {}, l = {50, 52}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaMetadataModel.kt */
final class MediaMetadataModel$updateActor$1 extends SuspendLambda implements Function2<ActorScope<MediaMetadataFull>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaMetadataModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaMetadataModel$updateActor$1(MediaMetadataModel mediaMetadataModel, Continuation<? super MediaMetadataModel$updateActor$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaMetadataModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaMetadataModel$updateActor$1 mediaMetadataModel$updateActor$1 = new MediaMetadataModel$updateActor$1(this.this$0, continuation);
        mediaMetadataModel$updateActor$1.L$0 = obj;
        return mediaMetadataModel$updateActor$1;
    }

    public final Object invoke(ActorScope<MediaMetadataFull> actorScope, Continuation<? super Unit> continuation) {
        return ((MediaMetadataModel$updateActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r8)
        L_0x0015:
            r8 = r1
            goto L_0x0036
        L_0x0017:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001f:
            java.lang.Object r1 = r7.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0047
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.channels.ActorScope r8 = (kotlinx.coroutines.channels.ActorScope) r8
            kotlinx.coroutines.channels.Channel r8 = r8.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r8 = r8.iterator()
        L_0x0036:
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.L$0 = r8
            r7.label = r3
            java.lang.Object r1 = r8.hasNext(r1)
            if (r1 != r0) goto L_0x0044
            return r0
        L_0x0044:
            r6 = r1
            r1 = r8
            r8 = r6
        L_0x0047:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            if (r8 == 0) goto L_0x006e
            java.lang.Object r8 = r1.next()
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r8 = (org.videolan.moviepedia.viewmodel.MediaMetadataFull) r8
            org.videolan.moviepedia.viewmodel.MediaMetadataModel r4 = r7.this$0
            androidx.lifecycle.MediatorLiveData r4 = r4.getUpdateLiveData()
            r4.setValue(r8)
            r8 = r7
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r7.L$0 = r1
            r7.label = r2
            r4 = 100
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r4, r8)
            if (r8 != r0) goto L_0x0015
            return r0
        L_0x006e:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.moviepedia.viewmodel.MediaMetadataModel$updateActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
