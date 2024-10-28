package org.videolan.vlc.webserver;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", ""}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.webserver.RemoteAccessService$startServerActor$1", f = "RemoteAccessService.kt", i = {}, l = {59, 62, 65, 77}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: RemoteAccessService.kt */
final class RemoteAccessService$startServerActor$1 extends SuspendLambda implements Function2<ActorScope<String>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    int label;
    final /* synthetic */ RemoteAccessService this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    RemoteAccessService$startServerActor$1(RemoteAccessService remoteAccessService, Continuation<? super RemoteAccessService$startServerActor$1> continuation) {
        super(2, continuation);
        this.this$0 = remoteAccessService;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        RemoteAccessService$startServerActor$1 remoteAccessService$startServerActor$1 = new RemoteAccessService$startServerActor$1(this.this$0, continuation);
        remoteAccessService$startServerActor$1.L$0 = obj;
        return remoteAccessService$startServerActor$1;
    }

    public final Object invoke(ActorScope<String> actorScope, Continuation<? super Unit> continuation) {
        return ((RemoteAccessService$startServerActor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0056  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r12) {
        /*
            r11 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r11.label
            r2 = 4
            r3 = 3
            r4 = 2
            r5 = 1
            if (r1 == 0) goto L_0x002e
            if (r1 == r5) goto L_0x0026
            if (r1 == r4) goto L_0x001d
            if (r1 == r3) goto L_0x001d
            if (r1 != r2) goto L_0x0015
            goto L_0x001d
        L_0x0015:
            java.lang.IllegalStateException r12 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r12.<init>(r0)
            throw r12
        L_0x001d:
            java.lang.Object r1 = r11.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x00e4
        L_0x0026:
            java.lang.Object r1 = r11.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r12)
            goto L_0x004e
        L_0x002e:
            kotlin.ResultKt.throwOnFailure(r12)
            java.lang.Object r12 = r11.L$0
            kotlinx.coroutines.channels.ActorScope r12 = (kotlinx.coroutines.channels.ActorScope) r12
            kotlinx.coroutines.channels.Channel r12 = r12.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r12 = r12.iterator()
        L_0x003d:
            r1 = r11
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r11.L$0 = r12
            r11.label = r5
            java.lang.Object r1 = r12.hasNext(r1)
            if (r1 != r0) goto L_0x004b
            return r0
        L_0x004b:
            r10 = r1
            r1 = r12
            r12 = r10
        L_0x004e:
            java.lang.Boolean r12 = (java.lang.Boolean) r12
            boolean r12 = r12.booleanValue()
            if (r12 == 0) goto L_0x0102
            java.lang.Object r12 = r1.next()
            java.lang.String r12 = (java.lang.String) r12
            int r6 = r12.hashCode()
            r7 = -1978971863(0xffffffff8a0b4929, float:-6.7063665E-33)
            r8 = 0
            java.lang.String r9 = "server"
            if (r6 == r7) goto L_0x00dc
            r7 = -1072186985(0xffffffffc017b997, float:-2.3707025)
            if (r6 == r7) goto L_0x00b8
            r7 = 402746524(0x18016c9c, float:1.6727693E-24)
            if (r6 == r7) goto L_0x0074
            goto L_0x00e4
        L_0x0074:
            java.lang.String r6 = "action_restart_server"
            boolean r12 = r12.equals(r6)
            if (r12 != 0) goto L_0x007d
            goto L_0x00e4
        L_0x007d:
            org.videolan.vlc.webserver.RemoteAccessService$startServerActor$1$observer$1 r12 = new org.videolan.vlc.webserver.RemoteAccessService$startServerActor$1$observer$1
            org.videolan.vlc.webserver.RemoteAccessService r6 = r11.this$0
            r12.<init>(r6)
            org.videolan.vlc.webserver.RemoteAccessService r6 = r11.this$0
            org.videolan.vlc.webserver.RemoteAccessServer r6 = r6.server
            if (r6 != 0) goto L_0x0090
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            r6 = r8
        L_0x0090:
            androidx.lifecycle.LiveData r6 = r6.getServerStatus()
            org.videolan.vlc.webserver.RemoteAccessService r7 = r11.this$0
            androidx.lifecycle.LifecycleOwner r7 = (androidx.lifecycle.LifecycleOwner) r7
            androidx.lifecycle.Observer r12 = (androidx.lifecycle.Observer) r12
            r6.observe(r7, r12)
            org.videolan.vlc.webserver.RemoteAccessService r12 = r11.this$0
            org.videolan.vlc.webserver.RemoteAccessServer r12 = r12.server
            if (r12 != 0) goto L_0x00a9
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x00aa
        L_0x00a9:
            r8 = r12
        L_0x00aa:
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.L$0 = r1
            r11.label = r2
            java.lang.Object r12 = r8.stop(r12)
            if (r12 != r0) goto L_0x00e4
            return r0
        L_0x00b8:
            java.lang.String r6 = "action_stop_server"
            boolean r12 = r12.equals(r6)
            if (r12 != 0) goto L_0x00c1
            goto L_0x00e4
        L_0x00c1:
            org.videolan.vlc.webserver.RemoteAccessService r12 = r11.this$0
            org.videolan.vlc.webserver.RemoteAccessServer r12 = r12.server
            if (r12 != 0) goto L_0x00cd
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x00ce
        L_0x00cd:
            r8 = r12
        L_0x00ce:
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.L$0 = r1
            r11.label = r4
            java.lang.Object r12 = r8.stop(r12)
            if (r12 != r0) goto L_0x00e4
            return r0
        L_0x00dc:
            java.lang.String r6 = "action_start_server"
            boolean r12 = r12.equals(r6)
            if (r12 != 0) goto L_0x00e7
        L_0x00e4:
            r12 = r1
            goto L_0x003d
        L_0x00e7:
            org.videolan.vlc.webserver.RemoteAccessService r12 = r11.this$0
            org.videolan.vlc.webserver.RemoteAccessServer r12 = r12.server
            if (r12 != 0) goto L_0x00f3
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r9)
            goto L_0x00f4
        L_0x00f3:
            r8 = r12
        L_0x00f4:
            r12 = r11
            kotlin.coroutines.Continuation r12 = (kotlin.coroutines.Continuation) r12
            r11.L$0 = r1
            r11.label = r3
            java.lang.Object r12 = r8.start(r12)
            if (r12 != r0) goto L_0x00e4
            return r0
        L_0x0102:
            kotlin.Unit r12 = kotlin.Unit.INSTANCE
            return r12
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.webserver.RemoteAccessService$startServerActor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
