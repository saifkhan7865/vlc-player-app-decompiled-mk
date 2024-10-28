package org.videolan.television.viewmodel;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.viewmodel.MainTvModel$historyActor$1", f = "MainTvModel.kt", i = {}, l = {105, 105}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MainTvModel.kt */
final class MainTvModel$historyActor$1 extends SuspendLambda implements Function2<ActorScope<Unit>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ MainTvModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MainTvModel$historyActor$1(MainTvModel mainTvModel, Continuation<? super MainTvModel$historyActor$1> continuation) {
        super(2, continuation);
        this.this$0 = mainTvModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        MainTvModel$historyActor$1 mainTvModel$historyActor$1 = new MainTvModel$historyActor$1(this.this$0, continuation);
        mainTvModel$historyActor$1.L$0 = obj;
        return mainTvModel$historyActor$1;
    }

    public final Object invoke(ActorScope<Unit> actorScope, Continuation<? super Unit> continuation) {
        return ((MainTvModel$historyActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:13:0x0044  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x004f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r7) {
        /*
            r6 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r6.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r7)
        L_0x0015:
            r7 = r1
            goto L_0x0036
        L_0x0017:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r0)
            throw r7
        L_0x001f:
            java.lang.Object r1 = r6.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r7)
            goto L_0x0047
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r7)
            java.lang.Object r7 = r6.L$0
            kotlinx.coroutines.channels.ActorScope r7 = (kotlinx.coroutines.channels.ActorScope) r7
            kotlinx.coroutines.channels.Channel r7 = r7.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r7 = r7.iterator()
        L_0x0036:
            r1 = r6
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r6.L$0 = r7
            r6.label = r3
            java.lang.Object r1 = r7.hasNext(r1)
            if (r1 != r0) goto L_0x0044
            return r0
        L_0x0044:
            r5 = r1
            r1 = r7
            r7 = r5
        L_0x0047:
            java.lang.Boolean r7 = (java.lang.Boolean) r7
            boolean r7 = r7.booleanValue()
            if (r7 == 0) goto L_0x0062
            r1.next()
            org.videolan.television.viewmodel.MainTvModel r7 = r6.this$0
            r4 = r6
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r6.L$0 = r1
            r6.label = r2
            java.lang.Object r7 = r7.setHistory(r4)
            if (r7 != r0) goto L_0x0015
            return r0
        L_0x0062:
            kotlin.Unit r7 = kotlin.Unit.INSTANCE
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.viewmodel.MainTvModel$historyActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
