package org.videolan.tools;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00010\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.tools.KotlinExtensionsKt$conflatedActor$1", f = "KotlinExtensions.kt", i = {}, l = {84, 85, 86}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: KotlinExtensions.kt */
final class KotlinExtensionsKt$conflatedActor$1 extends SuspendLambda implements Function2<ActorScope<Unit>, Continuation<? super Unit>, Object> {
    final /* synthetic */ Function1<Continuation<? super Unit>, Object> $action;
    final /* synthetic */ long $time;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    KotlinExtensionsKt$conflatedActor$1(Function1<? super Continuation<? super Unit>, ? extends Object> function1, long j, Continuation<? super KotlinExtensionsKt$conflatedActor$1> continuation) {
        super(2, continuation);
        this.$action = function1;
        this.$time = j;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        KotlinExtensionsKt$conflatedActor$1 kotlinExtensionsKt$conflatedActor$1 = new KotlinExtensionsKt$conflatedActor$1(this.$action, this.$time, continuation);
        kotlinExtensionsKt$conflatedActor$1.L$0 = obj;
        return kotlinExtensionsKt$conflatedActor$1;
    }

    public final Object invoke(ActorScope<Unit> actorScope, Continuation<? super Unit> continuation) {
        return ((KotlinExtensionsKt$conflatedActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0057  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x006f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            r2 = 3
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0031
            if (r1 == r4) goto L_0x0029
            if (r1 == r3) goto L_0x0021
            if (r1 != r2) goto L_0x0019
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0041
        L_0x0019:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0021:
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x0067
        L_0x0029:
            java.lang.Object r1 = r9.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x004f
        L_0x0031:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.L$0
            kotlinx.coroutines.channels.ActorScope r10 = (kotlinx.coroutines.channels.ActorScope) r10
            kotlinx.coroutines.channels.Channel r10 = r10.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r10 = r10.iterator()
            r1 = r10
        L_0x0041:
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r9.L$0 = r1
            r9.label = r4
            java.lang.Object r10 = r1.hasNext(r10)
            if (r10 != r0) goto L_0x004f
            return r0
        L_0x004f:
            java.lang.Boolean r10 = (java.lang.Boolean) r10
            boolean r10 = r10.booleanValue()
            if (r10 == 0) goto L_0x007d
            r1.next()
            kotlin.jvm.functions.Function1<kotlin.coroutines.Continuation<? super kotlin.Unit>, java.lang.Object> r10 = r9.$action
            r9.L$0 = r1
            r9.label = r3
            java.lang.Object r10 = r10.invoke(r9)
            if (r10 != r0) goto L_0x0067
            return r0
        L_0x0067:
            long r5 = r9.$time
            r7 = 0
            int r10 = (r5 > r7 ? 1 : (r5 == r7 ? 0 : -1))
            if (r10 <= 0) goto L_0x0041
            r10 = r9
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r9.L$0 = r1
            r9.label = r2
            java.lang.Object r10 = kotlinx.coroutines.DelayKt.delay(r5, r10)
            if (r10 != r0) goto L_0x0041
            return r0
        L_0x007d:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.tools.KotlinExtensionsKt$conflatedActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
