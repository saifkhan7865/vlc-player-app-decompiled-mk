package org.videolan.vlc.viewmodels.mobile;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel$refresh$1", f = "AudioBrowserViewModel.kt", i = {}, l = {68, 70}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: AudioBrowserViewModel.kt */
final class AudioBrowserViewModel$refresh$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    int I$0;
    int I$1;
    Object L$0;
    int label;
    final /* synthetic */ AudioBrowserViewModel this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    AudioBrowserViewModel$refresh$1(AudioBrowserViewModel audioBrowserViewModel, Continuation<? super AudioBrowserViewModel$refresh$1> continuation) {
        super(2, continuation);
        this.this$0 = audioBrowserViewModel;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new AudioBrowserViewModel$refresh$1(this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((AudioBrowserViewModel$refresh$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:15:0x005d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0026
            if (r1 == r3) goto L_0x0022
            if (r1 != r2) goto L_0x001a
            int r1 = r7.I$1
            int r4 = r7.I$0
            java.lang.Object r5 = r7.L$0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r5 = (org.videolan.vlc.providers.medialibrary.MedialibraryProvider[]) r5
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0083
        L_0x001a:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0022:
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0052
        L_0x0026:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r8 = r7.this$0
            int r8 = r8.getCurrentTab()
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r1 = r7.this$0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r1 = r1.getProviders()
            int r1 = r1.length
            if (r8 >= r1) goto L_0x0052
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r8 = r7.this$0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r8 = r8.getProviders()
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r1 = r7.this$0
            int r1 = r1.getCurrentTab()
            r8 = r8[r1]
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.label = r3
            java.lang.Object r8 = r8.awaitRefresh(r1)
            if (r8 != r0) goto L_0x0052
            return r0
        L_0x0052:
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r8 = r7.this$0
            org.videolan.vlc.providers.medialibrary.MedialibraryProvider[] r8 = r8.getProviders()
            int r1 = r8.length
            r4 = 0
            r5 = r8
        L_0x005b:
            if (r4 >= r1) goto L_0x0085
            r8 = r5[r4]
            org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel r6 = r7.this$0
            int r6 = r6.getCurrentTab()
            if (r4 == r6) goto L_0x0083
            androidx.lifecycle.MutableLiveData r6 = r8.getLoading()
            boolean r6 = r6.hasObservers()
            if (r6 == 0) goto L_0x0083
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7.L$0 = r5
            r7.I$0 = r4
            r7.I$1 = r1
            r7.label = r2
            java.lang.Object r8 = r8.awaitRefresh(r6)
            if (r8 != r0) goto L_0x0083
            return r0
        L_0x0083:
            int r4 = r4 + r3
            goto L_0x005b
        L_0x0085:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.vlc.viewmodels.mobile.AudioBrowserViewModel$refresh$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
