package org.videolan.vlc.gui.view;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.gui.view.FastScroller$actor$1", f = "FastScroller.kt", i = {}, l = {364, 373}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: FastScroller.kt */
final class FastScroller$actor$1 extends SuspendLambda implements Function2<ActorScope<Unit>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ FastScroller this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FastScroller$actor$1(FastScroller fastScroller, Continuation<? super FastScroller$actor$1> continuation) {
        super(2, continuation);
        this.this$0 = fastScroller;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        FastScroller$actor$1 fastScroller$actor$1 = new FastScroller$actor$1(this.this$0, continuation);
        fastScroller$actor$1.L$0 = obj;
        return fastScroller$actor$1;
    }

    public final Object invoke(ActorScope<Unit> actorScope, Continuation<? super Unit> continuation) {
        return ((FastScroller$actor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
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
            goto L_0x00be
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
            if (r9 == 0) goto L_0x00c1
            r1.next()
            org.videolan.vlc.gui.view.FastScroller r9 = r8.this$0
            boolean r9 = r9.fastScrolling
            if (r9 == 0) goto L_0x00be
            org.videolan.vlc.gui.view.FastScroller r9 = r8.this$0
            androidx.recyclerview.widget.LinearLayoutManager r9 = r9.layoutManager
            r4 = 0
            if (r9 != 0) goto L_0x0069
            java.lang.String r9 = "layoutManager"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r9 = r4
        L_0x0069:
            int r9 = r9.findFirstVisibleItemPosition()
            int r9 = r9 + r3
            org.videolan.vlc.gui.view.FastScroller r5 = r8.this$0
            org.videolan.resources.util.HeaderProvider r5 = r5.provider
            if (r5 == 0) goto L_0x00ae
            org.videolan.vlc.gui.view.FastScroller r6 = r8.this$0
            int r9 = r5.getPositionForSection(r9)
            java.lang.String r9 = r5.getSectionforPosition(r9)
            r5 = r9
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            int r5 = r5.length()
            if (r5 <= 0) goto L_0x00ae
            android.widget.TextView r5 = r6.bubble
            if (r5 != 0) goto L_0x0095
            java.lang.String r5 = "bubble"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r5)
            goto L_0x0096
        L_0x0095:
            r4 = r5
        L_0x0096:
            java.lang.StringBuilder r5 = new java.lang.StringBuilder
            java.lang.String r6 = " "
            r5.<init>(r6)
            r5.append(r9)
            r9 = 32
            r5.append(r9)
            java.lang.String r9 = r5.toString()
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            r4.setText(r9)
        L_0x00ae:
            r9 = r8
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r8.L$0 = r1
            r8.label = r2
            r4 = 100
            java.lang.Object r9 = kotlinx.coroutines.DelayKt.delay(r4, r9)
            if (r9 != r0) goto L_0x00be
            return r0
        L_0x00be:
            r9 = r1
            goto L_0x0036
        L_0x00c1:
            kotlin.Unit r9 = kotlin.Unit.INSTANCE
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.gui.view.FastScroller$actor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
