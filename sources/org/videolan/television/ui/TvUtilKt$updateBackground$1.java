package org.videolan.television.ui;

import android.app.Activity;
import androidx.leanback.app.BackgroundManager;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.TvUtilKt$updateBackground$1", f = "TvUtil.kt", i = {0, 1}, l = {297, 306}, m = "invokeSuspend", n = {"$this$launch", "$this$launch"}, s = {"L$0", "L$0"})
/* compiled from: TvUtil.kt */
final class TvUtilKt$updateBackground$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Activity $activity;
    final /* synthetic */ BackgroundManager $bm;
    final /* synthetic */ Object $item;
    final /* synthetic */ float $screenRatio;
    private /* synthetic */ Object L$0;
    int label;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TvUtilKt$updateBackground$1(Object obj, float f, BackgroundManager backgroundManager, Activity activity, Continuation<? super TvUtilKt$updateBackground$1> continuation) {
        super(2, continuation);
        this.$item = obj;
        this.$screenRatio = f;
        this.$bm = backgroundManager;
        this.$activity = activity;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        TvUtilKt$updateBackground$1 tvUtilKt$updateBackground$1 = new TvUtilKt$updateBackground$1(this.$item, this.$screenRatio, this.$bm, this.$activity, continuation);
        tvUtilKt$updateBackground$1.L$0 = obj;
        return tvUtilKt$updateBackground$1;
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((TvUtilKt$updateBackground$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x00b7  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x00ba  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 2
            r3 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r3) goto L_0x001f
            if (r1 != r2) goto L_0x0017
            java.lang.Object r0 = r7.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x00af
        L_0x0017:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001f:
            java.lang.Object r0 = r7.L$0
            kotlinx.coroutines.CoroutineScope r0 = (kotlinx.coroutines.CoroutineScope) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0062
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r8)
            java.lang.Object r8 = r7.L$0
            kotlinx.coroutines.CoroutineScope r8 = (kotlinx.coroutines.CoroutineScope) r8
            java.lang.Object r1 = r7.$item
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            java.lang.String r1 = r1.getArtworkMrl()
            r4 = r1
            java.lang.CharSequence r4 = (java.lang.CharSequence) r4
            r5 = 0
            if (r4 == 0) goto L_0x0082
            int r4 = r4.length()
            if (r4 != 0) goto L_0x0043
            goto L_0x0082
        L_0x0043:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.television.ui.TvUtilKt$updateBackground$1$blurred$1 r4 = new org.videolan.television.ui.TvUtilKt$updateBackground$1$blurred$1
            float r6 = r7.$screenRatio
            r4.<init>(r1, r6, r5)
            kotlin.jvm.functions.Function2 r4 = (kotlin.jvm.functions.Function2) r4
            r1 = r7
            kotlin.coroutines.Continuation r1 = (kotlin.coroutines.Continuation) r1
            r7.L$0 = r8
            r7.label = r3
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r2, r4, r1)
            if (r1 != r0) goto L_0x0060
            return r0
        L_0x0060:
            r0 = r8
            r8 = r1
        L_0x0062:
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
            if (r0 != 0) goto L_0x006d
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x006d:
            if (r8 == 0) goto L_0x00ce
            androidx.leanback.app.BackgroundManager r0 = r7.$bm
            android.app.Activity r1 = r7.$activity
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r1 = r1.getResources()
            r2.<init>(r1, r8)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r0.setDrawable(r2)
            goto L_0x00ce
        L_0x0082:
            java.lang.Object r1 = r7.$item
            org.videolan.medialibrary.media.MediaLibraryItem r1 = (org.videolan.medialibrary.media.MediaLibraryItem) r1
            int r1 = r1.getItemType()
            r3 = 16
            if (r1 != r3) goto L_0x00ce
            kotlinx.coroutines.CoroutineDispatcher r1 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r1 = (kotlin.coroutines.CoroutineContext) r1
            org.videolan.television.ui.TvUtilKt$updateBackground$1$blurred$2 r3 = new org.videolan.television.ui.TvUtilKt$updateBackground$1$blurred$2
            java.lang.Object r4 = r7.$item
            float r6 = r7.$screenRatio
            r3.<init>(r4, r6, r5)
            kotlin.jvm.functions.Function2 r3 = (kotlin.jvm.functions.Function2) r3
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.L$0 = r8
            r7.label = r2
            java.lang.Object r1 = kotlinx.coroutines.BuildersKt.withContext(r1, r3, r4)
            if (r1 != r0) goto L_0x00ad
            return r0
        L_0x00ad:
            r0 = r8
            r8 = r1
        L_0x00af:
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            boolean r0 = kotlinx.coroutines.CoroutineScopeKt.isActive(r0)
            if (r0 != 0) goto L_0x00ba
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        L_0x00ba:
            if (r8 == 0) goto L_0x00ce
            androidx.leanback.app.BackgroundManager r0 = r7.$bm
            android.app.Activity r1 = r7.$activity
            android.graphics.drawable.BitmapDrawable r2 = new android.graphics.drawable.BitmapDrawable
            android.content.res.Resources r1 = r1.getResources()
            r2.<init>(r1, r8)
            android.graphics.drawable.Drawable r2 = (android.graphics.drawable.Drawable) r2
            r0.setDrawable(r2)
        L_0x00ce:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.TvUtilKt$updateBackground$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
