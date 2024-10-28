package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$loadBackdrop$1", f = "MediaScrapingTvshowDetailsFragment.kt", i = {}, l = {152}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
final class MediaScrapingTvshowDetailsFragment$loadBackdrop$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $url;
    int label;
    final /* synthetic */ MediaScrapingTvshowDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowDetailsFragment$loadBackdrop$1(String str, MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, Continuation<? super MediaScrapingTvshowDetailsFragment$loadBackdrop$1> continuation) {
        super(2, continuation);
        this.$url = str;
        this.this$0 = mediaScrapingTvshowDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvshowDetailsFragment$loadBackdrop$1(this.$url, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvshowDetailsFragment$loadBackdrop$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x003e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r6) {
        /*
            r5 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r5.label
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L_0x0018
            if (r1 != r3) goto L_0x0010
            kotlin.ResultKt.throwOnFailure(r6)
            goto L_0x0038
        L_0x0010:
            java.lang.IllegalStateException r6 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r6.<init>(r0)
            throw r6
        L_0x0018:
            kotlin.ResultKt.throwOnFailure(r6)
            java.lang.String r6 = r5.$url
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            if (r6 == 0) goto L_0x003b
            int r6 = r6.length()
            if (r6 != 0) goto L_0x0028
            goto L_0x003b
        L_0x0028:
            org.videolan.tools.HttpImageLoader r6 = org.videolan.tools.HttpImageLoader.INSTANCE
            java.lang.String r1 = r5.$url
            r4 = r5
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r5.label = r3
            java.lang.Object r6 = r6.downloadBitmap(r1, r4)
            if (r6 != r0) goto L_0x0038
            return r0
        L_0x0038:
            android.graphics.Bitmap r6 = (android.graphics.Bitmap) r6
            goto L_0x003c
        L_0x003b:
            r6 = r2
        L_0x003c:
            if (r6 == 0) goto L_0x0050
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment r0 = r5.this$0
            androidx.leanback.app.BackgroundManager r0 = r0.backgroundManager
            if (r0 != 0) goto L_0x004c
            java.lang.String r0 = "backgroundManager"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r0)
            goto L_0x004d
        L_0x004c:
            r2 = r0
        L_0x004d:
            r2.setBitmap(r6)
        L_0x0050:
            kotlin.Unit r6 = kotlin.Unit.INSTANCE
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$loadBackdrop$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
