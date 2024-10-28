package org.videolan.vlc.viewmodels;

import androidx.lifecycle.ViewModelKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.CoroutineContext;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.CoroutineScope;
import kotlinx.coroutines.CoroutineStart;
import kotlinx.coroutines.channels.ActorKt;
import kotlinx.coroutines.channels.ActorScope;
import kotlinx.coroutines.channels.SendChannel;

@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\r\n\u0000\u0010\u0000\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00020\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "Lkotlinx/coroutines/channels/SendChannel;", "", "invoke"}, k = 3, mv = {1, 9, 0}, xi = 48)
/* compiled from: PlaylistModel.kt */
final class PlaylistModel$filterActor$2 extends Lambda implements Function0<SendChannel<? super CharSequence>> {
    final /* synthetic */ PlaylistModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PlaylistModel$filterActor$2(PlaylistModel playlistModel) {
        super(0);
        this.this$0 = playlistModel;
    }

    @Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\r\u0010\u0000\u001a\u00020\u0001*\n\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
    @DebugMetadata(c = "org.videolan.vlc.viewmodels.PlaylistModel$filterActor$2$1", f = "PlaylistModel.kt", i = {}, l = {62, 62}, m = "invokeSuspend", n = {}, s = {})
    /* renamed from: org.videolan.vlc.viewmodels.PlaylistModel$filterActor$2$1  reason: invalid class name */
    /* compiled from: PlaylistModel.kt */
    static final class AnonymousClass1 extends SuspendLambda implements Function2<ActorScope<CharSequence>, Continuation<? super Unit>, Object> {
        private /* synthetic */ Object L$0;
        int label;

        public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
            AnonymousClass1 r0 = new AnonymousClass1(playlistModel, continuation);
            r0.L$0 = obj;
            return r0;
        }

        public final Object invoke(ActorScope<CharSequence> actorScope, Continuation<? super Unit> continuation) {
            return ((AnonymousClass1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
                if (r8 == 0) goto L_0x0069
                java.lang.Object r8 = r1.next()
                java.lang.CharSequence r8 = (java.lang.CharSequence) r8
                org.videolan.vlc.viewmodels.PlaylistModel r4 = r2
                org.videolan.vlc.util.PlaylistFilterDelegate r4 = r4.getFilter()
                r5 = r7
                kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
                r7.L$0 = r1
                r7.label = r2
                java.lang.Object r8 = r4.filter(r8, r5)
                if (r8 != r0) goto L_0x0015
                return r0
            L_0x0069:
                kotlin.Unit r8 = kotlin.Unit.INSTANCE
                return r8
            */
            throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.PlaylistModel$filterActor$2.AnonymousClass1.invokeSuspend(java.lang.Object):java.lang.Object");
        }
    }

    public final SendChannel<CharSequence> invoke() {
        CoroutineScope viewModelScope = ViewModelKt.getViewModelScope(this.this$0);
        final PlaylistModel playlistModel = this.this$0;
        return ActorKt.actor$default(viewModelScope, (CoroutineContext) null, 0, (CoroutineStart) null, (Function1) null, new AnonymousClass1((Continuation<? super AnonymousClass1>) null), 15, (Object) null);
    }
}
