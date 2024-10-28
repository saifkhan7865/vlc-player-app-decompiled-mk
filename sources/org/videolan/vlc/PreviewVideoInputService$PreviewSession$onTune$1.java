package org.videolan.vlc;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.vlc.PreviewVideoInputService;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.PreviewVideoInputService$PreviewSession$onTune$1", f = "PreviewVideoInputService.kt", i = {1, 1}, l = {129, 68, 76}, m = "invokeSuspend", n = {"media", "start"}, s = {"L$0", "J$0"})
/* compiled from: PreviewVideoInputService.kt */
final class PreviewVideoInputService$PreviewSession$onTune$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ long $id;
    long J$0;
    Object L$0;
    int label;
    final /* synthetic */ PreviewVideoInputService this$0;
    final /* synthetic */ PreviewVideoInputService.PreviewSession this$1;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    PreviewVideoInputService$PreviewSession$onTune$1(PreviewVideoInputService previewVideoInputService, long j, PreviewVideoInputService.PreviewSession previewSession, Continuation<? super PreviewVideoInputService$PreviewSession$onTune$1> continuation) {
        super(2, continuation);
        this.this$0 = previewVideoInputService;
        this.$id = j;
        this.this$1 = previewSession;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new PreviewVideoInputService$PreviewSession$onTune$1(this.this$0, this.$id, this.this$1, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((PreviewVideoInputService$PreviewSession$onTune$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e3 A[Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x0131 A[Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }, RETURN] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r20) {
        /*
            r19 = this;
            r1 = r19
            java.lang.String r0 = ":start-time="
            java.lang.Object r2 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r3 = r1.label
            java.lang.String r4 = "Could not prepare media player"
            r5 = 3
            r6 = 2
            r7 = 1
            r8 = 0
            java.lang.String r9 = "PreviewInputService"
            r10 = 0
            if (r3 == 0) goto L_0x0042
            if (r3 == r7) goto L_0x003c
            if (r3 == r6) goto L_0x002e
            if (r3 != r5) goto L_0x0026
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            goto L_0x0132
        L_0x0020:
            r0 = move-exception
            goto L_0x0138
        L_0x0023:
            r0 = move-exception
            goto L_0x0143
        L_0x0026:
            java.lang.IllegalStateException r0 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r0.<init>(r2)
            throw r0
        L_0x002e:
            long r6 = r1.J$0
            java.lang.Object r0 = r1.L$0
            org.videolan.libvlc.interfaces.IMedia r0 = (org.videolan.libvlc.interfaces.IMedia) r0
            kotlin.ResultKt.throwOnFailure(r20)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r14 = r0
            r16 = r6
            goto L_0x00d7
        L_0x003c:
            kotlin.ResultKt.throwOnFailure(r20)
            r3 = r20
            goto L_0x0064
        L_0x0042:
            kotlin.ResultKt.throwOnFailure(r20)
            org.videolan.vlc.PreviewVideoInputService r3 = r1.this$0
            android.content.Context r3 = (android.content.Context) r3
            long r11 = r1.$id
            kotlinx.coroutines.CoroutineDispatcher r13 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r13 = (kotlin.coroutines.CoroutineContext) r13
            org.videolan.vlc.PreviewVideoInputService$PreviewSession$onTune$1$invokeSuspend$$inlined$getFromMl$1 r14 = new org.videolan.vlc.PreviewVideoInputService$PreviewSession$onTune$1$invokeSuspend$$inlined$getFromMl$1
            r14.<init>(r3, r10, r11)
            kotlin.jvm.functions.Function2 r14 = (kotlin.jvm.functions.Function2) r14
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3
            r1.label = r7
            java.lang.Object r3 = kotlinx.coroutines.BuildersKt.withContext(r13, r14, r3)
            if (r3 != r2) goto L_0x0064
            return r2
        L_0x0064:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r3 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r3
            if (r3 != 0) goto L_0x0083
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            java.lang.String r2 = "Could not find video "
            r0.<init>(r2)
            long r2 = r1.$id
            r0.append(r2)
            java.lang.String r0 = r0.toString()
            android.util.Log.w(r9, r0)
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1
            r0.notifyVideoUnavailable(r8)
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        L_0x0083:
            org.videolan.vlc.PreviewVideoInputService r7 = r1.this$0     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.libvlc.interfaces.IMediaFactory r7 = r7.getFactory$vlc_android_release()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.resources.VLCInstance r11 = org.videolan.resources.VLCInstance.INSTANCE     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.PreviewVideoInputService r12 = r1.this$0     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            java.lang.Object r11 = r11.getInstance(r12)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.libvlc.interfaces.ILibVLC r11 = (org.videolan.libvlc.interfaces.ILibVLC) r11     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            android.net.Uri r12 = r3.getUri()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.libvlc.interfaces.IMedia r7 = r7.getFromUri(r11, r12)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            long r11 = r3.getLength()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r13 = 0
            int r15 = (r11 > r13 ? 1 : (r11 == r13 ? 0 : -1))
            if (r15 > 0) goto L_0x00a7
            r11 = r13
            goto L_0x00af
        L_0x00a7:
            long r11 = r3.getLength()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            long r11 = org.videolan.vlc.util.KextensionsKt.random(r11)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
        L_0x00af:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r3.<init>(r0)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r13 = 1000(0x3e8, double:4.94E-321)
            long r13 = r11 / r13
            r3.append(r13)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            java.lang.String r0 = r3.toString()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r7.addOption(r0)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r3 = r1
            kotlin.coroutines.Continuation r3 = (kotlin.coroutines.Continuation) r3     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r1.L$0 = r7     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r1.J$0 = r11     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r1.label = r6     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            java.lang.Object r0 = r0.awaitSurface(r3)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            if (r0 != r2) goto L_0x00d4
            return r2
        L_0x00d4:
            r14 = r7
            r16 = r11
        L_0x00d7:
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.media.PlayerController r0 = r0.getPlayer()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.libvlc.interfaces.IVLCVout r0 = r0.getVout()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            if (r0 == 0) goto L_0x0102
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r3 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            android.view.Surface r6 = r3.surface     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            if (r6 != 0) goto L_0x00f1
            java.lang.String r6 = "surface"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r6)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r6 = r10
        L_0x00f1:
            r0.setVideoSurface(r6, r10)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r0.attachViews(r10)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            int r6 = r3.width     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            int r3 = r3.height     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r0.setWindowSize(r6, r3)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
        L_0x0102:
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.media.PlayerController r0 = r0.getPlayer()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r0.setVideoAspectRatio(r10)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.media.PlayerController r0 = r0.getPlayer()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r3 = 0
            r0.setVideoScale(r3)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.media.PlayerController r13 = r0.getPlayer()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            kotlin.jvm.internal.Intrinsics.checkNotNull(r14)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r15 = r0
            org.videolan.vlc.media.MediaPlayerEventListener r15 = (org.videolan.vlc.media.MediaPlayerEventListener) r15     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r18 = r1
            kotlin.coroutines.Continuation r18 = (kotlin.coroutines.Continuation) r18     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r1.L$0 = r10     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r1.label = r5     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            java.lang.Object r0 = r13.startPlayback$vlc_android_release(r14, r15, r16, r18)     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            if (r0 != r2) goto L_0x0132
            return r2
        L_0x0132:
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            r0.notifyVideoAvailable()     // Catch:{ IOException -> 0x0023, IllegalStateException -> 0x0020 }
            goto L_0x014d
        L_0x0138:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r9, r4, r0)
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1
            r0.notifyVideoUnavailable(r8)
            goto L_0x014d
        L_0x0143:
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            android.util.Log.e(r9, r4, r0)
            org.videolan.vlc.PreviewVideoInputService$PreviewSession r0 = r1.this$1
            r0.notifyVideoUnavailable(r8)
        L_0x014d:
            kotlin.Unit r0 = kotlin.Unit.INSTANCE
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.PreviewVideoInputService$PreviewSession$onTune$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
