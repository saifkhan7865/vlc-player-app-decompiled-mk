package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsFragment$loadBackdrop$1", f = "MediaItemDetailsFragment.kt", i = {}, l = {200, 202}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaItemDetailsFragment.kt */
final class MediaItemDetailsFragment$loadBackdrop$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ MediaItemDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaItemDetailsFragment$loadBackdrop$1(String str, MediaItemDetailsFragment mediaItemDetailsFragment, Continuation<? super MediaItemDetailsFragment$loadBackdrop$1> continuation) {
        super(2, continuation);
        this.$url = str;
        this.this$0 = mediaItemDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaItemDetailsFragment$loadBackdrop$1(this.$url, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaItemDetailsFragment$loadBackdrop$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:26:0x0085  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 2
            r3 = 0
            r4 = 1
            if (r1 == 0) goto L_0x0020
            if (r1 == r4) goto L_0x001c
            if (r1 != r2) goto L_0x0014
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0081
        L_0x0014:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x001c:
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0040
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = r5.$url
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x0043
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0030
            goto L_0x0043
        L_0x0030:
            org.videolan.tools.HttpImageLoader r6 = org.videolan.tools.HttpImageLoader.INSTANCE
            java.lang.String r1 = r5.$url
            r2 = r5
            kotlin.coroutines.Continuation r2 = (kotlin.coroutines.Continuation) r2
            r5.label = r4
            java.lang.Object r6 = r6.downloadBitmap(r1, r2)
            if (r6 != r0) goto L_0x0040
            return r0
        L_0x0040:
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            goto L_0x0083
        L_0x0043:
            org.videolan.television.ui.MediaItemDetailsFragment r6 = r5.this$0
            org.videolan.television.ui.MediaItemDetailsModel r6 = r6.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r6.getMedia()
            int r6 = r6.getType()
            if (r6 == r4) goto L_0x0066
            org.videolan.television.ui.MediaItemDetailsFragment r6 = r5.this$0
            org.videolan.television.ui.MediaItemDetailsModel r6 = r6.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r6 = r6.getMedia()
            int r6 = r6.getType()
            if (r6 != 0) goto L_0x0064
            goto L_0x0066
        L_0x0064:
            r6 = r3
            goto L_0x0083
        L_0x0066:
            kotlinx.coroutines.CoroutineDispatcher r6 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r6 = (kotlin.coroutines.CoroutineContext) r6
            org.videolan.television.ui.MediaItemDetailsFragment$loadBackdrop$1$1 r1 = new org.videolan.television.ui.MediaItemDetailsFragment$loadBackdrop$1$1
            org.videolan.television.ui.MediaItemDetailsFragment r4 = r5.this$0
            r1.<init>(r4, r3)
            kotlin.jvm.functions.Function2 r1 = (kotlin.jvm.functions.Function2) r1
            r4 = r5
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5.label = r2
            java.lang.Object r6 = kotlinx.coroutines.BuildersKt.withContext(r6, r1, r4)
            if (r6 != r0) goto L_0x0081
            return r0
        L_0x0081:
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
        L_0x0083:
            if (r6 == 0) goto L_0x0097
            org.videolan.television.ui.MediaItemDetailsFragment r0 = r5.this$0
            androidx.leanback.app.BackgroundManager r0 = r0.backgroundManager
            if (r0 != 0) goto L_0x0093
            java.lang.String r0 = "backgroundManager"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x0094
        L_0x0093:
            r3 = r0
        L_0x0094:
            r3.setBitmap(r6)
        L_0x0097:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaItemDetailsFragment$loadBackdrop$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
