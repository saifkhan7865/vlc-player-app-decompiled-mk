package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;
import org.videolan.medialibrary.interfaces.media.MediaWrapper;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/medialibrary/interfaces/media/MediaWrapper;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsModel$updateActor$1", f = "MediaItemDetailsFragment.kt", i = {}, l = {461}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaItemDetailsFragment.kt */
final class MediaItemDetailsModel$updateActor$1 extends SuspendLambda implements Function2<ActorScope<MediaWrapper>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MediaItemDetailsModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaItemDetailsModel$updateActor$1(MediaItemDetailsModel mediaItemDetailsModel, Continuation<? super MediaItemDetailsModel$updateActor$1> continuation) {
        super(2, continuation);
        this.this$0 = mediaItemDetailsModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MediaItemDetailsModel$updateActor$1 mediaItemDetailsModel$updateActor$1 = new MediaItemDetailsModel$updateActor$1(this.this$0, continuation);
        mediaItemDetailsModel$updateActor$1.L$0 = obj;
        return mediaItemDetailsModel$updateActor$1;
    }

    public final Object invoke(ActorScope<MediaWrapper> actorScope, Continuation<? super Unit> continuation) {
        return ((MediaItemDetailsModel$updateActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:12:0x0041  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r5) {
        /*
            r4 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r4.label
            r2 = 1
            if (r1 == 0) goto L_0x001b
            if (r1 != r2) goto L_0x0013
            java.lang.Object r1 = r4.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r5)
            goto L_0x0039
        L_0x0013:
            java.lang.IllegalStateException r5 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r5.<init>(r0)
            throw r5
        L_0x001b:
            kotlin.ResultKt.throwOnFailure(r5)
            java.lang.Object r5 = r4.L$0
            kotlinx.coroutines.channels.ActorScope r5 = (kotlinx.coroutines.channels.ActorScope) r5
            kotlinx.coroutines.channels.Channel r5 = r5.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r5 = r5.iterator()
            r1 = r5
        L_0x002b:
            r5 = r4
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r4.L$0 = r1
            r4.label = r2
            java.lang.Object r5 = r1.hasNext(r5)
            if (r5 != r0) goto L_0x0039
            return r0
        L_0x0039:
            java.lang.Boolean r5 = (java.lang.Boolean) r5
            boolean r5 = r5.booleanValue()
            if (r5 == 0) goto L_0x0051
            java.lang.Object r5 = r1.next()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r5 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r5
            org.videolan.television.ui.MediaItemDetailsModel r3 = r4.this$0
            androidx.lifecycle.MediatorLiveData r3 = r3.getBrowserFavUpdated()
            r3.setValue(r5)
            goto L_0x002b
        L_0x0051:
            kotlin.Unit r5 = kotlin.Unit.INSTANCE
            return r5
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaItemDetailsModel$updateActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
