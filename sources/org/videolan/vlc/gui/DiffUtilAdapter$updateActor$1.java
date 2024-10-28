package org.videolan.vlc.gui;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \u0010\u0000\u001a\u00020\u0001\"\u0004\b\u0000\u0010\u0002\"\b\b\u0001\u0010\u0003*\u00020\u0004*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00020\u00060\u0005H@"}, d2 = {"<anonymous>", "", "D", "VH", "Landroidx/recyclerview/widget/RecyclerView$ViewHolder;", "Lkotlinx/coroutines/channels/ActorScope;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.DiffUtilAdapter$updateActor$1", f = "DiffUtilAdapter.kt", i = {}, l = {19, 19}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: DiffUtilAdapter.kt */
final class DiffUtilAdapter$updateActor$1 extends SuspendLambda implements Function2<ActorScope<List<? extends D>>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ DiffUtilAdapter<D, VH> this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    DiffUtilAdapter$updateActor$1(DiffUtilAdapter<D, VH> diffUtilAdapter, Continuation<? super DiffUtilAdapter$updateActor$1> continuation) {
        super(2, continuation);
        this.this$0 = diffUtilAdapter;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        DiffUtilAdapter$updateActor$1 diffUtilAdapter$updateActor$1 = new DiffUtilAdapter$updateActor$1(this.this$0, continuation);
        diffUtilAdapter$updateActor$1.L$0 = obj;
        return diffUtilAdapter$updateActor$1;
    }

    public final Object invoke(ActorScope<List<D>> actorScope, Continuation<? super Unit> continuation) {
        return ((DiffUtilAdapter$updateActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            if (r8 == 0) goto L_0x0065
            java.lang.Object r8 = r1.next()
            java.util.List r8 = (java.util.List) r8
            org.videolan.vlc.gui.DiffUtilAdapter<D, VH> r4 = r7.this$0
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r7.L$0 = r1
            r7.label = r2
            java.lang.Object r8 = r4.internalUpdate(r8, r5)
            if (r8 != r0) goto L_0x0015
            return r0
        L_0x0065:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.DiffUtilAdapter$updateActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
