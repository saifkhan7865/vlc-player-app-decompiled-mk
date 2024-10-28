package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.channels.ActorScope;

@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\b\u0012\u0004\u0012\u00020\u00030\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/channels/ActorScope;", "Lorg/videolan/vlc/MediaEvent;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.StoragesMonitor$actor$1", f = "StoragesMonitor.kt", i = {1, 2, 3}, l = {36, 40, 77, 60}, m = "invokeSuspend", n = {"action", "action", "action"}, s = {"L$1", "L$1", "L$1"})
/* compiled from: StoragesMonitor.kt */
final class StoragesMonitor$actor$1 extends SuspendLambda implements Function2<ActorScope<MediaEvent>, Continuation<? super Unit>, Object> {
    private /* synthetic */ Object L$0;
    Object L$1;
    int label;

    StoragesMonitor$actor$1(Continuation<? super StoragesMonitor$actor$1> continuation) {
        super(2, continuation);
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        StoragesMonitor$actor$1 storagesMonitor$actor$1 = new StoragesMonitor$actor$1(continuation);
        storagesMonitor$actor$1.L$0 = obj;
        return storagesMonitor$actor$1;
    }

    public final Object invoke(ActorScope<MediaEvent> actorScope, Continuation<? super Unit> continuation) {
        return ((StoragesMonitor$actor$1) create(actorScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x007d  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00db  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x0104  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 0
            r3 = 4
            r4 = 3
            r5 = 2
            r6 = 1
            if (r1 == 0) goto L_0x0053
            if (r1 == r6) goto L_0x004b
            if (r1 == r5) goto L_0x003b
            if (r1 == r4) goto L_0x002b
            if (r1 != r3) goto L_0x0023
            java.lang.Object r1 = r13.L$1
            org.videolan.vlc.MediaEvent r1 = (org.videolan.vlc.MediaEvent) r1
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            kotlin.ResultKt.throwOnFailure(r14)
            r14 = r7
            goto L_0x0179
        L_0x0023:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x002b:
            java.lang.Object r1 = r13.L$1
            org.videolan.vlc.MediaEvent r1 = (org.videolan.vlc.MediaEvent) r1
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            kotlin.ResultKt.throwOnFailure(r14)
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00fc
        L_0x003b:
            java.lang.Object r1 = r13.L$1
            org.videolan.vlc.MediaEvent r1 = (org.videolan.vlc.MediaEvent) r1
            java.lang.Object r7 = r13.L$0
            kotlinx.coroutines.channels.ChannelIterator r7 = (kotlinx.coroutines.channels.ChannelIterator) r7
            kotlin.ResultKt.throwOnFailure(r14)
            r12 = r7
            r7 = r1
            r1 = r12
            goto L_0x00d3
        L_0x004b:
            java.lang.Object r1 = r13.L$0
            kotlinx.coroutines.channels.ChannelIterator r1 = (kotlinx.coroutines.channels.ChannelIterator) r1
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x0075
        L_0x0053:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.Object r14 = r13.L$0
            kotlinx.coroutines.channels.ActorScope r14 = (kotlinx.coroutines.channels.ActorScope) r14
            kotlinx.coroutines.channels.Channel r14 = r14.getChannel()
            kotlinx.coroutines.channels.ChannelIterator r14 = r14.iterator()
        L_0x0062:
            r1 = r13
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r13.L$0 = r14
            r13.L$1 = r2
            r13.label = r6
            java.lang.Object r1 = r14.hasNext(r1)
            if (r1 != r0) goto L_0x0072
            return r0
        L_0x0072:
            r12 = r1
            r1 = r14
            r14 = r12
        L_0x0075:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x018c
            java.lang.Object r14 = r1.next()
            org.videolan.vlc.MediaEvent r14 = (org.videolan.vlc.MediaEvent) r14
            boolean r7 = r14 instanceof org.videolan.vlc.Mount
            java.lang.String r8 = " - "
            java.lang.String r9 = "StoragesMonitor"
            if (r7 == 0) goto L_0x013e
            r7 = r14
            org.videolan.vlc.Mount r7 = (org.videolan.vlc.Mount) r7
            java.lang.String r10 = r7.getUuid()
            java.lang.CharSequence r10 = (java.lang.CharSequence) r10
            int r10 = r10.length()
            if (r10 != 0) goto L_0x009d
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x009d:
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            java.lang.String r11 = "Storage management: mount: "
            r10.<init>(r11)
            java.lang.String r11 = r7.getUuid()
            r10.append(r11)
            r10.append(r8)
            java.lang.String r8 = r7.getPath()
            r10.append(r8)
            java.lang.String r8 = r10.toString()
            android.util.Log.i(r9, r8)
            java.lang.String r7 = r7.getPath()
            r8 = r13
            kotlin.coroutines.Continuation r8 = (kotlin.coroutines.Continuation) r8
            r13.L$0 = r1
            r13.L$1 = r14
            r13.label = r5
            java.lang.Object r7 = org.videolan.vlc.util.KextensionsKt.scanAllowed(r7, r8)
            if (r7 != r0) goto L_0x00d0
            return r0
        L_0x00d0:
            r12 = r7
            r7 = r14
            r14 = r12
        L_0x00d3:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x013b
            android.content.Context r14 = r7.getCtx()
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.vlc.StoragesMonitor$actor$1$invokeSuspend$$inlined$getFromMl$1 r9 = new org.videolan.vlc.StoragesMonitor$actor$1$invokeSuspend$$inlined$getFromMl$1
            r9.<init>(r14, r2, r7)
            kotlin.jvm.functions.Function2 r9 = (kotlin.jvm.functions.Function2) r9
            r14 = r13
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r13.L$0 = r1
            r13.L$1 = r7
            r13.label = r4
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r8, r9, r14)
            if (r14 != r0) goto L_0x00fc
            return r0
        L_0x00fc:
            java.lang.Boolean r14 = (java.lang.Boolean) r14
            boolean r14 = r14.booleanValue()
            if (r14 == 0) goto L_0x013b
            android.content.Intent r8 = new android.content.Intent
            android.content.Context r9 = r7.getCtx()
            java.lang.Class<org.videolan.vlc.gui.DialogActivity> r10 = org.videolan.vlc.gui.DialogActivity.class
            r8.<init>(r9, r10)
            java.lang.String r9 = "deviceDialog"
            r8.setAction(r9)
            r9 = r7
            org.videolan.vlc.Mount r9 = (org.videolan.vlc.Mount) r9
            java.lang.String r10 = r9.getPath()
            java.lang.String r11 = "extra_path"
            r8.putExtra(r11, r10)
            java.lang.String r10 = "extra_uuid"
            java.lang.String r9 = r9.getUuid()
            r8.putExtra(r10, r9)
            java.lang.String r9 = "extra_scan"
            r8.putExtra(r9, r14)
            r14 = 268468224(0x10008000, float:2.5342157E-29)
            r8.addFlags(r14)
            android.content.Context r14 = r7.getCtx()
            r14.startActivity(r8)
        L_0x013b:
            r14 = r1
            goto L_0x0062
        L_0x013e:
            boolean r7 = r14 instanceof org.videolan.vlc.Unmount
            if (r7 == 0) goto L_0x013b
            java.lang.StringBuilder r7 = new java.lang.StringBuilder
            java.lang.String r10 = "Storage management: unmount: "
            r7.<init>(r10)
            r10 = r14
            org.videolan.vlc.Unmount r10 = (org.videolan.vlc.Unmount) r10
            java.lang.String r11 = r10.getUuid()
            r7.append(r11)
            r7.append(r8)
            java.lang.String r8 = r10.getPath()
            r7.append(r8)
            java.lang.String r7 = r7.toString()
            android.util.Log.i(r9, r7)
            r7 = r13
            kotlin.coroutines.Continuation r7 = (kotlin.coroutines.Continuation) r7
            r13.L$0 = r1
            r13.L$1 = r14
            r13.label = r3
            r8 = 100
            java.lang.Object r7 = kotlinx.coroutines.DelayKt.delay(r8, r7)
            if (r7 != r0) goto L_0x0176
            return r0
        L_0x0176:
            r12 = r1
            r1 = r14
            r14 = r12
        L_0x0179:
            org.videolan.medialibrary.interfaces.Medialibrary r7 = org.videolan.medialibrary.interfaces.Medialibrary.getInstance()
            org.videolan.vlc.Unmount r1 = (org.videolan.vlc.Unmount) r1
            java.lang.String r8 = r1.getUuid()
            java.lang.String r1 = r1.getPath()
            r7.removeDevice(r8, r1)
            goto L_0x0062
        L_0x018c:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.StoragesMonitor$actor$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
