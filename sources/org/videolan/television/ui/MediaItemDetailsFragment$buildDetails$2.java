package org.videolan.television.ui;

import androidx.fragment.app.FragmentActivity;
import androidx.leanback.widget.Action;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2", f = "MediaItemDetailsFragment.kt", i = {1, 2, 2, 2}, l = {392, 394, 396}, m = "invokeSuspend", n = {"cover", "cover", "browserFavExists", "isDir"}, s = {"L$0", "L$0", "Z$0", "I$0"})
/* compiled from: MediaItemDetailsFragment.kt */
final class MediaItemDetailsFragment$buildDetails$2 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ Action $actionAdd;
    final /* synthetic */ Action $actionDelete;
    final /* synthetic */ Action $actionEdit;
    final /* synthetic */ FragmentActivity $activity;
    int I$0;
    Object L$0;
    boolean Z$0;
    int label;
    final /* synthetic */ MediaItemDetailsFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    MediaItemDetailsFragment$buildDetails$2(MediaItemDetailsFragment mediaItemDetailsFragment, FragmentActivity fragmentActivity, Action action, Action action2, Action action3, Continuation<? super MediaItemDetailsFragment$buildDetails$2> continuation) {
        super(2, continuation);
        this.this$0 = mediaItemDetailsFragment;
        this.$activity = fragmentActivity;
        this.$actionDelete = action;
        this.$actionAdd = action2;
        this.$actionEdit = action3;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new MediaItemDetailsFragment$buildDetails$2(this.this$0, this.$activity, this.$actionDelete, this.$actionAdd, this.$actionEdit, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((MediaItemDetailsFragment$buildDetails$2) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: Removed duplicated region for block: B:21:0x0088  */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x00b0 A[RETURN] */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x00c7  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x00c9  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cc  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00fb  */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00fd  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x0114  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r18) {
        /*
            r17 = this;
            r0 = r17
            java.lang.Object r1 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r2 = r0.label
            r3 = 0
            r4 = 2
            r5 = 3
            r6 = 1
            r7 = 0
            if (r2 == 0) goto L_0x003d
            if (r2 == r6) goto L_0x0037
            if (r2 == r4) goto L_0x002c
            if (r2 != r5) goto L_0x0024
            int r1 = r0.I$0
            boolean r2 = r0.Z$0
            java.lang.Object r4 = r0.L$0
            android.graphics.Bitmap r4 = (android.graphics.Bitmap) r4
            kotlin.ResultKt.throwOnFailure(r18)
            r9 = r18
            goto L_0x00f3
        L_0x0024:
            java.lang.IllegalStateException r1 = new java.lang.IllegalStateException
            java.lang.String r2 = "call to 'resume' before 'invoke' with coroutine"
            r1.<init>(r2)
            throw r1
        L_0x002c:
            java.lang.Object r2 = r0.L$0
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
            kotlin.ResultKt.throwOnFailure(r18)
            r4 = r18
            goto L_0x00b1
        L_0x0037:
            kotlin.ResultKt.throwOnFailure(r18)
            r2 = r18
            goto L_0x007e
        L_0x003d:
            kotlin.ResultKt.throwOnFailure(r18)
            org.videolan.television.ui.MediaItemDetailsFragment r2 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r2 = r2.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r2.getMedia()
            int r2 = r2.getType()
            if (r2 == r6) goto L_0x0063
            org.videolan.television.ui.MediaItemDetailsFragment r2 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r2 = r2.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r2.getMedia()
            int r2 = r2.getType()
            if (r2 != 0) goto L_0x0061
            goto L_0x0063
        L_0x0061:
            r2 = r7
            goto L_0x0080
        L_0x0063:
            kotlinx.coroutines.CoroutineDispatcher r2 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r2 = (kotlin.coroutines.CoroutineContext) r2
            org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2$cover$1 r8 = new org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2$cover$1
            org.videolan.television.ui.MediaItemDetailsFragment r9 = r0.this$0
            r8.<init>(r9, r7)
            kotlin.jvm.functions.Function2 r8 = (kotlin.jvm.functions.Function2) r8
            r9 = r0
            kotlin.coroutines.Continuation r9 = (kotlin.coroutines.Continuation) r9
            r0.label = r6
            java.lang.Object r2 = kotlinx.coroutines.BuildersKt.withContext(r2, r8, r9)
            if (r2 != r1) goto L_0x007e
            return r1
        L_0x007e:
            android.graphics.Bitmap r2 = (android.graphics.Bitmap) r2
        L_0x0080:
            org.videolan.television.ui.MediaItemDetailsFragment r8 = r0.this$0
            org.videolan.vlc.repository.BrowserFavRepository r8 = r8.browserFavRepository
            if (r8 != 0) goto L_0x008e
            java.lang.String r8 = "browserFavRepository"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r8)
            r8 = r7
        L_0x008e:
            org.videolan.television.ui.MediaItemDetailsFragment r9 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r9 = r9.getViewModel()
            org.videolan.television.ui.MediaItemDetails r9 = r9.getMediaItemDetails()
            java.lang.String r9 = r9.getLocation()
            kotlin.jvm.internal.Intrinsics.checkNotNull(r9)
            android.net.Uri r9 = android.net.Uri.parse(r9)
            r10 = r0
            kotlin.coroutines.Continuation r10 = (kotlin.coroutines.Continuation) r10
            r0.L$0 = r2
            r0.label = r4
            java.lang.Object r4 = r8.browserFavExists(r9, r10)
            if (r4 != r1) goto L_0x00b1
            return r1
        L_0x00b1:
            java.lang.Boolean r4 = (java.lang.Boolean) r4
            boolean r4 = r4.booleanValue()
            org.videolan.television.ui.MediaItemDetailsFragment r8 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r8 = r8.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = r8.getMedia()
            int r8 = r8.getType()
            if (r8 != r5) goto L_0x00c9
            r8 = 1
            goto L_0x00ca
        L_0x00c9:
            r8 = 0
        L_0x00ca:
            if (r8 == 0) goto L_0x0103
            kotlinx.coroutines.CoroutineDispatcher r9 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r9 = (kotlin.coroutines.CoroutineContext) r9
            org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2$canSave$1 r10 = new org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2$canSave$1
            org.videolan.television.ui.MediaItemDetailsFragment r11 = r0.this$0
            r10.<init>(r11, r7)
            kotlin.jvm.functions.Function2 r10 = (kotlin.jvm.functions.Function2) r10
            r11 = r0
            kotlin.coroutines.Continuation r11 = (kotlin.coroutines.Continuation) r11
            r0.L$0 = r2
            r0.Z$0 = r4
            r0.I$0 = r8
            r0.label = r5
            java.lang.Object r9 = kotlinx.coroutines.BuildersKt.withContext(r9, r10, r11)
            if (r9 != r1) goto L_0x00ed
            return r1
        L_0x00ed:
            r1 = r8
            r16 = r4
            r4 = r2
            r2 = r16
        L_0x00f3:
            java.lang.Boolean r9 = (java.lang.Boolean) r9
            boolean r8 = r9.booleanValue()
            if (r8 == 0) goto L_0x00fd
            r3 = 1
            goto L_0x0109
        L_0x00fd:
            r8 = r1
            r16 = r4
            r4 = r2
            r2 = r16
        L_0x0103:
            r1 = r8
            r16 = r4
            r4 = r2
            r2 = r16
        L_0x0109:
            androidx.fragment.app.FragmentActivity r8 = r0.$activity
            boolean r8 = r8.isFinishing()
            if (r8 == 0) goto L_0x0114
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        L_0x0114:
            org.videolan.television.ui.MediaItemDetailsFragment r8 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r8 = r8.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r8 = r8.getMedia()
            android.net.Uri r8 = r8.getUri()
            java.lang.String r8 = r8.getScheme()
            boolean r8 = org.videolan.vlc.util.BrowserutilsKt.isSchemeNetwork(r8)
            org.videolan.television.ui.MediaItemDetailsFragment r9 = r0.this$0
            android.content.res.Resources r9 = r9.getResources()
            java.lang.String r10 = "getResources(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r9, r10)
            java.lang.String r10 = "detailsOverview"
            if (r1 == 0) goto L_0x01c0
            org.videolan.television.ui.MediaItemDetailsFragment r4 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r4 = r4.detailsOverview
            if (r4 != 0) goto L_0x0145
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r4 = r7
        L_0x0145:
            androidx.fragment.app.FragmentActivity r5 = r0.$activity
            android.content.Context r5 = (android.content.Context) r5
            org.videolan.television.ui.MediaItemDetailsFragment r11 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r11 = r11.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r11 = r11.getMedia()
            android.net.Uri r11 = r11.getUri()
            java.lang.String r11 = r11.getScheme()
            java.lang.String r12 = "file"
            boolean r11 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r11, (java.lang.Object) r12)
            if (r11 == 0) goto L_0x0166
            int r11 = org.videolan.vlc.R.drawable.ic_folder_big
            goto L_0x0168
        L_0x0166:
            int r11 = org.videolan.vlc.R.drawable.ic_network_big
        L_0x0168:
            android.graphics.drawable.Drawable r5 = androidx.core.content.ContextCompat.getDrawable(r5, r11)
            r4.setImageDrawable(r5)
            org.videolan.television.ui.MediaItemDetailsFragment r4 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r4 = r4.detailsOverview
            if (r4 != 0) goto L_0x017b
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r4 = r7
        L_0x017b:
            r4.setImageScaleUpAllowed(r6)
            org.videolan.television.ui.MediaItemDetailsFragment r4 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r4 = r4.actionsAdapter
            androidx.leanback.widget.Action r5 = new androidx.leanback.widget.Action
            int r6 = org.videolan.vlc.R.string.browse_folder
            java.lang.String r6 = r9.getString(r6)
            java.lang.CharSequence r6 = (java.lang.CharSequence) r6
            r11 = 6
            r5.<init>(r11, r6)
            r6 = 6
            r4.set(r6, r5)
            if (r3 == 0) goto L_0x01ab
            org.videolan.television.ui.MediaItemDetailsFragment r3 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r3 = r3.actionsAdapter
            if (r2 == 0) goto L_0x01a4
            androidx.leanback.widget.Action r4 = r0.$actionDelete
            goto L_0x01a6
        L_0x01a4:
            androidx.leanback.widget.Action r4 = r0.$actionAdd
        L_0x01a6:
            r5 = 11
            r3.set(r5, r4)
        L_0x01ab:
            if (r1 == 0) goto L_0x039f
            if (r8 == 0) goto L_0x039f
            if (r2 == 0) goto L_0x039f
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            r2 = 14
            androidx.leanback.widget.Action r3 = r0.$actionEdit
            r1.set(r2, r3)
            goto L_0x039f
        L_0x01c0:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r1 = r1.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            int r1 = r1.getType()
            r2 = 13
            r8 = 13
            r14 = 1
            r11 = 12
            r7 = 12
            if (r1 != r6) goto L_0x029d
            if (r4 != 0) goto L_0x01f6
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r1 = r1.detailsOverview
            if (r1 != 0) goto L_0x01e8
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r1 = 0
        L_0x01e8:
            androidx.fragment.app.FragmentActivity r4 = r0.$activity
            android.content.Context r4 = (android.content.Context) r4
            int r13 = org.videolan.vlc.R.drawable.ic_default_cone
            android.graphics.drawable.Drawable r4 = androidx.core.content.ContextCompat.getDrawable(r4, r13)
            r1.setImageDrawable(r4)
            goto L_0x020b
        L_0x01f6:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r1 = r1.detailsOverview
            if (r1 != 0) goto L_0x0202
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r1 = 0
        L_0x0202:
            org.videolan.television.ui.MediaItemDetailsFragment r13 = r0.this$0
            android.content.Context r13 = r13.getContext()
            r1.setImageBitmap(r13, r4)
        L_0x020b:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            boolean r1 = r1.fromHistory
            if (r1 == 0) goto L_0x0229
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r4 = new androidx.leanback.widget.Action
            int r13 = org.videolan.vlc.R.string.remove_from_history
            java.lang.String r13 = r9.getString(r13)
            java.lang.CharSequence r13 = (java.lang.CharSequence) r13
            r4.<init>(r11, r13)
            r1.set(r7, r4)
        L_0x0229:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r1 = r1.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            android.net.Uri r1 = r1.getUri()
            android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r1)
            if (r1 == 0) goto L_0x0253
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r4 = new androidx.leanback.widget.Action
            int r7 = org.videolan.vlc.R.string.go_to_folder
            java.lang.String r7 = r9.getString(r7)
            java.lang.CharSequence r7 = (java.lang.CharSequence) r7
            r4.<init>(r2, r7)
            r1.set(r8, r4)
        L_0x0253:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.play
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r2.<init>(r14, r3)
            r1.set(r6, r2)
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.listen
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r6 = 3
            r2.<init>(r6, r3)
            r1.set(r5, r2)
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.add_to_playlist
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r4 = 9
            r2.<init>(r4, r3)
            r3 = 9
            r1.set(r3, r2)
            goto L_0x039f
        L_0x029d:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r1 = r1.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            int r1 = r1.getType()
            if (r1 != 0) goto L_0x039f
            if (r4 != 0) goto L_0x02c9
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r1 = r1.detailsOverview
            if (r1 != 0) goto L_0x02bb
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r1 = 0
        L_0x02bb:
            androidx.fragment.app.FragmentActivity r4 = r0.$activity
            android.content.Context r4 = (android.content.Context) r4
            int r5 = org.videolan.vlc.R.drawable.ic_default_cone
            android.graphics.drawable.Drawable r4 = androidx.core.content.ContextCompat.getDrawable(r4, r5)
            r1.setImageDrawable(r4)
            goto L_0x02de
        L_0x02c9:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r1 = r1.detailsOverview
            if (r1 != 0) goto L_0x02d5
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r1 = 0
        L_0x02d5:
            org.videolan.television.ui.MediaItemDetailsFragment r5 = r0.this$0
            android.content.Context r5 = r5.getContext()
            r1.setImageBitmap(r5, r4)
        L_0x02de:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            boolean r1 = r1.fromHistory
            if (r1 == 0) goto L_0x02fc
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r4 = new androidx.leanback.widget.Action
            int r5 = org.videolan.vlc.R.string.remove_from_history
            java.lang.String r5 = r9.getString(r5)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r4.<init>(r11, r5)
            r1.set(r7, r4)
        L_0x02fc:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r1 = r1.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = r1.getMedia()
            android.net.Uri r1 = r1.getUri()
            android.net.Uri r1 = org.videolan.tools.KotlinExtensionsKt.retrieveParent(r1)
            if (r1 == 0) goto L_0x0326
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r4 = new androidx.leanback.widget.Action
            int r5 = org.videolan.vlc.R.string.go_to_folder
            java.lang.String r5 = r9.getString(r5)
            java.lang.CharSequence r5 = (java.lang.CharSequence) r5
            r4.<init>(r2, r5)
            r1.set(r8, r4)
        L_0x0326:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.play
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r2.<init>(r14, r3)
            r1.set(r6, r2)
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.play_from_start
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r4 = 8
            r2.<init>(r4, r3)
            r3 = 8
            r1.set(r3, r2)
            org.videolan.vlc.util.FileUtils r1 = org.videolan.vlc.util.FileUtils.INSTANCE
            org.videolan.television.ui.MediaItemDetailsFragment r2 = r0.this$0
            org.videolan.television.ui.MediaItemDetailsModel r2 = r2.getViewModel()
            org.videolan.medialibrary.interfaces.media.MediaWrapper r2 = r2.getMedia()
            android.net.Uri r2 = r2.getUri()
            boolean r1 = r1.canWrite((android.net.Uri) r2)
            if (r1 == 0) goto L_0x0385
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.download_subtitles
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r4 = 7
            r2.<init>(r4, r3)
            r3 = 7
            r1.set(r3, r2)
        L_0x0385:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.Action r2 = new androidx.leanback.widget.Action
            int r3 = org.videolan.vlc.R.string.add_to_playlist
            java.lang.String r3 = r9.getString(r3)
            java.lang.CharSequence r3 = (java.lang.CharSequence) r3
            r4 = 9
            r2.<init>(r4, r3)
            r3 = 9
            r1.set(r3, r2)
        L_0x039f:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.ArrayObjectAdapter r2 = r1.rowsAdapter
            if (r2 != 0) goto L_0x03ad
            java.lang.String r2 = "rowsAdapter"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r2)
            r2 = 0
        L_0x03ad:
            androidx.leanback.widget.ObjectAdapter r2 = (androidx.leanback.widget.ObjectAdapter) r2
            r1.setAdapter(r2)
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.DetailsOverviewRow r1 = r1.detailsOverview
            if (r1 != 0) goto L_0x03bf
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r10)
            r7 = 0
            goto L_0x03c0
        L_0x03bf:
            r7 = r1
        L_0x03c0:
            org.videolan.television.ui.MediaItemDetailsFragment r1 = r0.this$0
            androidx.leanback.widget.SparseArrayObjectAdapter r1 = r1.actionsAdapter
            androidx.leanback.widget.ObjectAdapter r1 = (androidx.leanback.widget.ObjectAdapter) r1
            r7.setActionsAdapter(r1)
            kotlin.Unit r1 = kotlin.Unit.INSTANCE
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.MediaItemDetailsFragment$buildDetails$2.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
