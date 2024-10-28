package org.videolan.television.ui;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.SearchFragment$loadRows$1", f = "SearchFragment.kt", i = {}, l = {124}, m = "invokeSuspend", n = {}, s = {})
/* compiled from: SearchFragment.kt */
final class SearchFragment$loadRows$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ String $query;
    int label;
    final /* synthetic */ SearchFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    SearchFragment$loadRows$1(String str, SearchFragment searchFragment, Continuation<? super SearchFragment$loadRows$1> continuation) {
        super(2, continuation);
        this.$query = str;
        this.this$0 = searchFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new SearchFragment$loadRows$1(this.$query, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((SearchFragment$loadRows$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:20:0x0059  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x005b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r14) {
        /*
            r13 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r13.label
            r2 = 1
            if (r1 == 0) goto L_0x0017
            if (r1 != r2) goto L_0x000f
            kotlin.ResultKt.throwOnFailure(r14)
            goto L_0x004c
        L_0x000f:
            java.lang.IllegalStateException r14 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r14.<init>(r0)
            throw r14
        L_0x0017:
            kotlin.ResultKt.throwOnFailure(r14)
            java.lang.String r14 = r13.$query
            if (r14 == 0) goto L_0x01f3
            java.lang.CharSequence r14 = (java.lang.CharSequence) r14
            int r14 = r14.length()
            if (r14 != 0) goto L_0x0028
            goto L_0x01f3
        L_0x0028:
            org.videolan.television.ui.SearchFragment r14 = r13.this$0
            android.content.Context r14 = r14.getContext()
            r1 = 0
            if (r14 == 0) goto L_0x004f
            java.lang.String r3 = r13.$query
            kotlinx.coroutines.CoroutineDispatcher r4 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r4 = (kotlin.coroutines.CoroutineContext) r4
            org.videolan.television.ui.SearchFragment$loadRows$1$invokeSuspend$$inlined$getFromMl$1 r5 = new org.videolan.television.ui.SearchFragment$loadRows$1$invokeSuspend$$inlined$getFromMl$1
            r5.<init>(r14, r1, r3)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r14 = r13
            kotlin.coroutines.Continuation r14 = (kotlin.coroutines.Continuation) r14
            r13.label = r2
            java.lang.Object r14 = kotlinx.coroutines.BuildersKt.withContext(r4, r5, r14)
            if (r14 != r0) goto L_0x004c
            return r0
        L_0x004c:
            r1 = r14
            org.videolan.medialibrary.media.SearchAggregate r1 = (org.videolan.medialibrary.media.SearchAggregate) r1
        L_0x004f:
            r14 = 0
            if (r1 == 0) goto L_0x005b
            boolean r0 = r1.isEmpty()
            if (r0 == 0) goto L_0x0059
            goto L_0x005b
        L_0x0059:
            r0 = 0
            goto L_0x005c
        L_0x005b:
            r0 = 1
        L_0x005c:
            org.videolan.television.ui.SearchFragment r3 = r13.this$0
            r3.updateEmtyView(r0)
            if (r1 == 0) goto L_0x01f0
            if (r0 == 0) goto L_0x0067
            goto L_0x01f0
        L_0x0067:
            if (r0 != 0) goto L_0x007d
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r3 = r1.getTracks()
            if (r3 == 0) goto L_0x0072
            int r3 = r3.length
            if (r3 != 0) goto L_0x007c
        L_0x0072:
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r3 = r1.getVideos()
            if (r3 == 0) goto L_0x007d
            int r3 = r3.length
            if (r3 != 0) goto L_0x007c
            goto L_0x007d
        L_0x007c:
            r2 = 0
        L_0x007d:
            org.videolan.television.ui.CardPresenter r9 = new org.videolan.television.ui.CardPresenter
            org.videolan.television.ui.SearchFragment r3 = r13.this$0
            androidx.fragment.app.FragmentActivity r3 = r3.requireActivity()
            java.lang.String r4 = "requireActivity(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r3, r4)
            r4 = r3
            android.app.Activity r4 = (android.app.Activity) r4
            r7 = 6
            r8 = 0
            r5 = 0
            r6 = 0
            r3 = r9
            r3.<init>(r4, r5, r6, r7, r8)
            androidx.leanback.widget.ArrayObjectAdapter r3 = new androidx.leanback.widget.ArrayObjectAdapter
            androidx.leanback.widget.Presenter r9 = (androidx.leanback.widget.Presenter) r9
            r3.<init>((androidx.leanback.widget.Presenter) r9)
            if (r2 != 0) goto L_0x00b2
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r4 = r1.getVideos()
            if (r4 == 0) goto L_0x00b2
            int r5 = r4.length
            java.lang.Object[] r4 = java.util.Arrays.copyOf(r4, r5)
            java.util.List r4 = kotlin.collections.CollectionsKt.listOf(r4)
            java.util.Collection r4 = (java.util.Collection) r4
            r3.addAll(r14, r4)
        L_0x00b2:
            androidx.leanback.widget.ArrayObjectAdapter r4 = new androidx.leanback.widget.ArrayObjectAdapter
            r4.<init>((androidx.leanback.widget.Presenter) r9)
            if (r2 != 0) goto L_0x00cd
            org.videolan.medialibrary.interfaces.media.MediaWrapper[] r5 = r1.getTracks()
            if (r5 == 0) goto L_0x00cd
            int r6 = r5.length
            java.lang.Object[] r5 = java.util.Arrays.copyOf(r5, r6)
            java.util.List r5 = kotlin.collections.CollectionsKt.listOf(r5)
            java.util.Collection r5 = (java.util.Collection) r5
            r4.addAll(r14, r5)
        L_0x00cd:
            androidx.leanback.widget.ArrayObjectAdapter r5 = new androidx.leanback.widget.ArrayObjectAdapter
            r5.<init>((androidx.leanback.widget.Presenter) r9)
            if (r0 != 0) goto L_0x00e8
            org.videolan.medialibrary.interfaces.media.Artist[] r6 = r1.getArtists()
            if (r6 == 0) goto L_0x00e8
            int r7 = r6.length
            java.lang.Object[] r6 = java.util.Arrays.copyOf(r6, r7)
            java.util.List r6 = kotlin.collections.CollectionsKt.listOf(r6)
            java.util.Collection r6 = (java.util.Collection) r6
            r5.addAll(r14, r6)
        L_0x00e8:
            androidx.leanback.widget.ArrayObjectAdapter r6 = new androidx.leanback.widget.ArrayObjectAdapter
            r6.<init>((androidx.leanback.widget.Presenter) r9)
            if (r0 != 0) goto L_0x0103
            org.videolan.medialibrary.interfaces.media.Album[] r7 = r1.getAlbums()
            if (r7 == 0) goto L_0x0103
            int r8 = r7.length
            java.lang.Object[] r7 = java.util.Arrays.copyOf(r7, r8)
            java.util.List r7 = kotlin.collections.CollectionsKt.listOf(r7)
            java.util.Collection r7 = (java.util.Collection) r7
            r6.addAll(r14, r7)
        L_0x0103:
            androidx.leanback.widget.ArrayObjectAdapter r7 = new androidx.leanback.widget.ArrayObjectAdapter
            r7.<init>((androidx.leanback.widget.Presenter) r9)
            if (r0 != 0) goto L_0x011e
            org.videolan.medialibrary.interfaces.media.Genre[] r1 = r1.getGenres()
            if (r1 == 0) goto L_0x011e
            int r8 = r1.length
            java.lang.Object[] r1 = java.util.Arrays.copyOf(r1, r8)
            java.util.List r1 = kotlin.collections.CollectionsKt.listOf(r1)
            java.util.Collection r1 = (java.util.Collection) r1
            r7.addAll(r14, r1)
        L_0x011e:
            r8 = 0
            if (r2 != 0) goto L_0x0149
            int r14 = r3.size()
            if (r14 <= 0) goto L_0x0149
            org.videolan.television.ui.SearchFragment r14 = r13.this$0
            androidx.leanback.widget.ArrayObjectAdapter r14 = r14.rowsAdapter
            androidx.leanback.widget.ListRow r1 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.HeaderItem r10 = new androidx.leanback.widget.HeaderItem
            org.videolan.television.ui.SearchFragment r11 = r13.this$0
            android.content.res.Resources r11 = r11.getResources()
            int r12 = org.videolan.vlc.R.string.videos
            java.lang.String r11 = r11.getString(r12)
            r10.<init>(r8, r11)
            androidx.leanback.widget.ObjectAdapter r3 = (androidx.leanback.widget.ObjectAdapter) r3
            r1.<init>(r10, r3)
            r14.add(r1)
        L_0x0149:
            if (r2 != 0) goto L_0x0172
            int r14 = r4.size()
            if (r14 <= 0) goto L_0x0172
            org.videolan.television.ui.SearchFragment r14 = r13.this$0
            androidx.leanback.widget.ArrayObjectAdapter r14 = r14.rowsAdapter
            androidx.leanback.widget.ListRow r1 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.HeaderItem r2 = new androidx.leanback.widget.HeaderItem
            org.videolan.television.ui.SearchFragment r3 = r13.this$0
            android.content.res.Resources r3 = r3.getResources()
            int r10 = org.videolan.vlc.R.string.songs
            java.lang.String r3 = r3.getString(r10)
            r2.<init>(r8, r3)
            androidx.leanback.widget.ObjectAdapter r4 = (androidx.leanback.widget.ObjectAdapter) r4
            r1.<init>(r2, r4)
            r14.add(r1)
        L_0x0172:
            if (r0 != 0) goto L_0x019b
            int r14 = r5.size()
            if (r14 <= 0) goto L_0x019b
            org.videolan.television.ui.SearchFragment r14 = r13.this$0
            androidx.leanback.widget.ArrayObjectAdapter r14 = r14.rowsAdapter
            androidx.leanback.widget.ListRow r1 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.HeaderItem r2 = new androidx.leanback.widget.HeaderItem
            org.videolan.television.ui.SearchFragment r3 = r13.this$0
            android.content.res.Resources r3 = r3.getResources()
            int r4 = org.videolan.vlc.R.string.artists
            java.lang.String r3 = r3.getString(r4)
            r2.<init>(r8, r3)
            androidx.leanback.widget.ObjectAdapter r5 = (androidx.leanback.widget.ObjectAdapter) r5
            r1.<init>(r2, r5)
            r14.add(r1)
        L_0x019b:
            if (r0 != 0) goto L_0x01c4
            int r14 = r6.size()
            if (r14 <= 0) goto L_0x01c4
            org.videolan.television.ui.SearchFragment r14 = r13.this$0
            androidx.leanback.widget.ArrayObjectAdapter r14 = r14.rowsAdapter
            androidx.leanback.widget.ListRow r1 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.HeaderItem r2 = new androidx.leanback.widget.HeaderItem
            org.videolan.television.ui.SearchFragment r3 = r13.this$0
            android.content.res.Resources r3 = r3.getResources()
            int r4 = org.videolan.vlc.R.string.albums
            java.lang.String r3 = r3.getString(r4)
            r2.<init>(r8, r3)
            androidx.leanback.widget.ObjectAdapter r6 = (androidx.leanback.widget.ObjectAdapter) r6
            r1.<init>(r2, r6)
            r14.add(r1)
        L_0x01c4:
            if (r0 != 0) goto L_0x01ed
            int r14 = r7.size()
            if (r14 <= 0) goto L_0x01ed
            org.videolan.television.ui.SearchFragment r14 = r13.this$0
            androidx.leanback.widget.ArrayObjectAdapter r14 = r14.rowsAdapter
            androidx.leanback.widget.ListRow r0 = new androidx.leanback.widget.ListRow
            androidx.leanback.widget.HeaderItem r1 = new androidx.leanback.widget.HeaderItem
            org.videolan.television.ui.SearchFragment r2 = r13.this$0
            android.content.res.Resources r2 = r2.getResources()
            int r3 = org.videolan.vlc.R.string.genres
            java.lang.String r2 = r2.getString(r3)
            r1.<init>(r8, r2)
            androidx.leanback.widget.ObjectAdapter r7 = (androidx.leanback.widget.ObjectAdapter) r7
            r0.<init>(r1, r7)
            r14.add(r0)
        L_0x01ed:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x01f0:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        L_0x01f3:
            kotlin.Unit r14 = kotlin.Unit.INSTANCE
            return r14
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.SearchFragment$loadRows$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
