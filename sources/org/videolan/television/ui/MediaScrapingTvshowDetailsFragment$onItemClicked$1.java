package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$onItemClicked$1", f = "MediaScrapingTvshowDetailsFragment.kt", i = {}, l = {312}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: MediaScrapingTvshowDetailsFragment.kt */
final class MediaScrapingTvshowDetailsFragment$onItemClicked$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Object $item;
    int label;
    final /* synthetic */ MediaScrapingTvshowDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaScrapingTvshowDetailsFragment$onItemClicked$1(Object obj, MediaScrapingTvshowDetailsFragment mediaScrapingTvshowDetailsFragment, Continuation<? super MediaScrapingTvshowDetailsFragment$onItemClicked$1> continuation) {
        super(2, continuation);
        this.$item = obj;
        this.this$0 = mediaScrapingTvshowDetailsFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaScrapingTvshowDetailsFragment$onItemClicked$1(this.$item, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaScrapingTvshowDetailsFragment$onItemClicked$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0064  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r10) {
        /*
            r9 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r9.label
            java.lang.String r2 = "requireActivity(...)"
            r3 = 1
            if (r1 == 0) goto L_0x0019
            if (r1 != r3) goto L_0x0011
            kotlin.ResultKt.throwOnFailure(r10)
            goto L_0x005c
        L_0x0011:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r0)
            throw r10
        L_0x0019:
            kotlin.ResultKt.throwOnFailure(r10)
            java.lang.Object r10 = r9.$item
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r10
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = r10.getMedia()
            if (r10 != 0) goto L_0x0061
            java.lang.Object r10 = r9.$item
            org.videolan.moviepedia.database.models.MediaMetadataWithImages r10 = (org.videolan.moviepedia.database.models.MediaMetadataWithImages) r10
            org.videolan.moviepedia.database.models.MediaMetadata r10 = r10.getMetadata()
            java.lang.Long r10 = r10.getMlId()
            r1 = 0
            if (r10 == 0) goto L_0x005f
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment r4 = r9.this$0
            java.lang.Number r10 = (java.lang.Number) r10
            long r5 = r10.longValue()
            androidx.fragment.app.FragmentActivity r10 = r4.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)
            android.content.Context r10 = (android.content.Context) r10
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$onItemClicked$1$invokeSuspend$lambda$1$$inlined$getFromMl$1 r7 = new org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$onItemClicked$1$invokeSuspend$lambda$1$$inlined$getFromMl$1
            r7.<init>(r10, r1, r5)
            kotlin.jvm.functions.Function2 r7 = (kotlin.jvm.functions.Function2) r7
            r9.label = r3
            java.lang.Object r10 = kotlinx.coroutines.BuildersKt.withContext(r4, r7, r9)
            if (r10 != r0) goto L_0x005c
            return r0
        L_0x005c:
            org.videolan.medialibrary.interfaces.media.MediaWrapper r10 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r10
            goto L_0x0061
        L_0x005f:
            r5 = r1
            goto L_0x0062
        L_0x0061:
            r5 = r10
        L_0x0062:
            if (r5 == 0) goto L_0x0078
            org.videolan.television.ui.MediaScrapingTvshowDetailsFragment r10 = r9.this$0
            org.videolan.television.ui.TvUtil r3 = org.videolan.television.ui.TvUtil.INSTANCE
            androidx.fragment.app.FragmentActivity r10 = r10.requireActivity()
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r10, r2)
            r4 = r10
            android.content.Context r4 = (android.content.Context) r4
            r7 = 4
            r8 = 0
            r6 = 0
            org.videolan.television.ui.TvUtil.showMediaDetail$default(r3, r4, r5, r6, r7, r8)
        L_0x0078:
            kotlin.Unit r10 = kotlin.Unit.INSTANCE
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaScrapingTvshowDetailsFragment$onItemClicked$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
