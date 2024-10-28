package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.moviepedia.viewmodel.MediaMetadataFull;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$updateMetadata$1$1", f = "MediaScrapingTvshowDetailsFragment.kt", i = {}, l = {165}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
final class MediaScrapingTvshowDetailsFragment$updateMetadata$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaMetadataFull $tvShow;
    Object L$0;
    Object L$1;
    int label;
    final /* synthetic */ MediaScrapingTvshowDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowDetailsFragment$updateMetadata$1$1(MediaMetadataFull mediaMetadataFull, MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, Continuation<? super MediaScrapingTvshowDetailsFragment$updateMetadata$1$1> continuation) {
        super(2, continuation);
        this.$tvShow = mediaMetadataFull;
        this.this$0 = mediaScrapingTvshowDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvshowDetailsFragment$updateMetadata$1$1(this.$tvShow, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvshowDetailsFragment$updateMetadata$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:8:0x002c, code lost:
        r8 = r8.getMetadata();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 1
            if (r1 == 0) goto L_0x0020
            if (r1 != r2) goto L_0x0018
            java.lang.Object r0 = r7.L$1
            android.content.Context r0 = (android.content.Context) r0
            java.lang.Object r1 = r7.L$0
            androidx.leanback.widget.DetailsOverviewRow r1 = (androidx.leanback.widget.DetailsOverviewRow) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0084
        L_0x0018:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x0020:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r8 = r7.$tvShow
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r8 = r8.getMetadata()
            r1 = 0
            if (r8 == 0) goto L_0x0037
            org.videolan.moviepedia.database.models.MediaMetadata r8 = r8.getMetadata()
            if (r8 == 0) goto L_0x0037
            java.lang.String r8 = r8.getCurrentPoster()
            goto L_0x0038
        L_0x0037:
            r8 = r1
        L_0x0038:
            java.lang.CharSequence r8 = (java.lang.CharSequence) r8
            if (r8 == 0) goto L_0x0089
            int r8 = r8.length()
            if (r8 != 0) goto L_0x0043
            goto L_0x0089
        L_0x0043:
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment r8 = r7.this$0
            androidx.leanback.widget.DetailsOverviewRow r8 = r8.detailsOverview
            if (r8 != 0) goto L_0x0051
            java.lang.String r8 = "detailsOverview"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r8 = r1
        L_0x0051:
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment r3 = r7.this$0
            androidx.fragment.app.FragmentActivity r3 = r3.requireActivity()
            android.content.Context r3 = (android.content.Context) r3
            org.videolan.tools.HttpImageLoader r4 = org.videolan.tools.HttpImageLoader.INSTANCE
            org.videolan.moviepedia.viewmodel.MediaMetadataFull r5 = r7.$tvShow
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r5 = r5.getMetadata()
            if (r5 == 0) goto L_0x006d
            org.videolan.moviepedia.database.models.MediaMetadata r5 = r5.getMetadata()
            if (r5 == 0) goto L_0x006d
            java.lang.String r1 = r5.getCurrentPoster()
        L_0x006d:
            kotlin.jvm.internal.Intrinsics.checkNotNull(r1)
            r5 = r7
            kotlin.coroutines.Continuation r5 = (kotlin.coroutines.Continuation) r5
            r7.L$0 = r8
            r7.L$1 = r3
            r7.label = r2
            java.lang.Object r1 = r4.downloadBitmap(r1, r5)
            if (r1 != r0) goto L_0x0080
            return r0
        L_0x0080:
            r0 = r3
            r6 = r1
            r1 = r8
            r8 = r6
        L_0x0084:
            android.graphics.Bitmap r8 = (android.graphics.Bitmap) r8
            r1.setImageBitmap(r0, r8)
        L_0x0089:
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$updateMetadata$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
