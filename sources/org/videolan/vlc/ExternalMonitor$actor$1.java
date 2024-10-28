package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/DeviceAction;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.ExternalMonitor$actor$1", f = "ExternalMonitor.kt", i = {1, 2}, l = {66, 190, 78}, m = "invokeSuspend", n = {"action", "action"}, s = {"L$1", "L$1"})
/* compiled from: ExternalMonitor.kt */
final class ExternalMonitor$actor$1 extends SuspendLambda implements Function2<ActorScope<DeviceAction>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    ExternalMonitor$actor$1(Continuation<? super ExternalMonitor$actor$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        ExternalMonitor$actor$1 externalMonitor$actor$1 = new ExternalMonitor$actor$1(continuation);
        externalMonitor$actor$1.L$0 = obj;
        return externalMonitor$actor$1;
    }

    public final Object invoke(ActorScope<DeviceAction> actorScope, Continuation<? super Unit> continuation) {
        return ((ExternalMonitor$actor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x006e  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00dd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r13) {
        /*
            r12 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r12.label
            java.lang.String r2 = " - "
            java.lang.String r3 = "ExternalMonitor"
            r4 = 3
            r5 = 2
            r6 = 1
            r7 = 0
            if (r1 == 0) goto L_0x0044
            if (r1 == r6) goto L_0x003c
            if (r1 == r5) goto L_0x002c
            if (r1 != r4) goto L_0x0024
            java.lang.Object r1 = r12.L$1
            org.videolan.vlc.DeviceAction r1 = (org.videolan.vlc.DeviceAction) r1
            java.lang.Object r8 = r12.L$0
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            kotlin.ResultKt.throwOnFailure(r13)
            r13 = r8
            goto L_0x0100
        L_0x0024:
            java.lang.IllegalStateException r13 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r13.<init>(r0)
            throw r13
        L_0x002c:
            java.lang.Object r1 = r12.L$1
            org.videolan.vlc.DeviceAction r1 = (org.videolan.vlc.DeviceAction) r1
            java.lang.Object r8 = r12.L$0
            kotlinx.coroutines.channels.ChannelIterator r8 = (kotlinx.coroutines.channels.ChannelIterator) r8
            kotlin.ResultKt.throwOnFailure(r13)
            r11 = r8
            r8 = r1
            r1 = r11
            goto L_0x00d5
        L_0x003c:
            java.lang.Object r1 = r12.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r13)
            goto L_0x0066
        L_0x0044:
            kotlin.ResultKt.throwOnFailure(r13)
            java.lang.Object r13 = r12.L$0
            kotlinx.coroutines.channels.ActorScope r13 = (kotlinx.coroutines.channels.ActorScope) r13
            kotlinx.coroutines.channels.Channel r13 = r13.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r13 = r13.iterator()
        L_0x0053:
            r1 = r12
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r12.L$0 = r13
            r12.L$1 = r7
            r12.label = r6
            java.lang.Object r1 = r13.hasNext(r1)
            if (r1 != r0) goto L_0x0063
            return r0
        L_0x0063:
            r11 = r1
            r1 = r13
            r13 = r11
        L_0x0066:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x013a
            java.lang.Object r13 = r1.next()
            org.videolan.vlc.DeviceAction r13 = (org.videolan.vlc.DeviceAction) r13
            boolean r8 = r13 instanceof org.videolan.vlc.MediaMounted
            if (r8 == 0) goto L_0x00e7
            r8 = r13
            org.videolan.vlc.MediaMounted r8 = (org.videolan.vlc.MediaMounted) r8
            java.lang.String r9 = r8.getUuid()
            java.lang.CharSequence r9 = (java.lang.CharSequence) r9
            int r9 = r9.length()
            if (r9 != 0) goto L_0x008a
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        L_0x008a:
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r10 = "Storage management: mount: "
            r9.<init>(r10)
            java.lang.String r10 = r8.getUuid()
            r9.append(r10)
            r9.append(r2)
            java.lang.String r8 = r8.getPath()
            r9.append(r8)
            java.lang.String r8 = r9.toString()
            android.util.Log.i(r3, r8)
            android.content.Context r8 = org.videolan.vlc.ExternalMonitor.ctx
            if (r8 != 0) goto L_0x00b5
            java.lang.String r8 = "ctx"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r8 = r7
        L_0x00b5:
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.vlc.ExternalMonitor$actor$1$invokeSuspend$$inlined$getFromMl$1 r10 = new org.videolan.vlc.ExternalMonitor$actor$1$invokeSuspend$$inlined$getFromMl$1
            r10.<init>(r8, r7, r13)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r8 = r12
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r12.L$0 = r1
            r12.L$1 = r13
            r12.label = r5
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r8)
            if (r8 != r0) goto L_0x00d2
            return r0
        L_0x00d2:
            r11 = r8
            r8 = r13
            r13 = r11
        L_0x00d5:
            java.lang.Boolean r13 = (java.lang.Boolean) r13
            boolean r13 = r13.booleanValue()
            if (r13 == 0) goto L_0x00e4
            org.videolan.vlc.ExternalMonitor r13 = org.videolan.vlc.ExternalMonitor.INSTANCE
            org.videolan.vlc.MediaMounted r8 = (org.videolan.vlc.MediaMounted) r8
            r13.notifyNewStorage(r8)
        L_0x00e4:
            r13 = r1
            goto L_0x0053
        L_0x00e7:
            boolean r8 = r13 instanceof org.videolan.vlc.MediaUnmounted
            if (r8 == 0) goto L_0x00e4
            r8 = r12
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r12.L$0 = r1
            r12.L$1 = r13
            r12.label = r4
            r9 = 100
            java.lang.Object r8 = kotlinx.coroutines.DelayKt.delay(r9, r8)
            if (r8 != r0) goto L_0x00fd
            return r0
        L_0x00fd:
            r11 = r1
            r1 = r13
            r13 = r11
        L_0x0100:
            java.lang.StringBuilder r8 = new java.lang.StringBuilder
            java.lang.String r9 = "Storage management: unmount: "
            r8.<init>(r9)
            r9 = r1
            org.videolan.vlc.MediaUnmounted r9 = (org.videolan.vlc.MediaUnmounted) r9
            java.lang.String r10 = r9.getUuid()
            r8.append(r10)
            r8.append(r2)
            java.lang.String r10 = r9.getPath()
            r8.append(r10)
            java.lang.String r8 = r8.toString()
            android.util.Log.i(r3, r8)
            org.videolan.medialibrary.interfaces.Medialibrary r8 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            java.lang.String r10 = r9.getUuid()
            java.lang.String r9 = r9.getPath()
            r8.removeDevice(r10, r9)
            kotlinx.coroutines.flow.MutableSharedFlow r8 = org.videolan.vlc.ExternalMonitor.storageChannel
            r8.tryEmit(r1)
            goto L_0x0053
        L_0x013a:
            kotlin.Unit r13 = kotlin.Unit.INSTANCE
            return r13
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.ExternalMonitor$actor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
