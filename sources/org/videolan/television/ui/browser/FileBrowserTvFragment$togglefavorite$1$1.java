package org.videolan.television.ui.browser;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.jvm.internal.DebugMetadata;
import kotlin.coroutines.jvm.internal.SuspendLambda;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.CoroutineScope;
import org.videolan.medialibrary.media.MediaLibraryItem;

@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H@"}, d2 = {"<anonymous>", "", "Lkotlinx/coroutines/CoroutineScope;"}, k = 3, mv = {1, 9, 0}, xi = 48)
@DebugMetadata(c = "org.videolan.television.ui.browser.FileBrowserTvFragment$togglefavorite$1$1", f = "FileBrowserTvFragment.kt", i = {0}, l = {312, 319}, m = "invokeSuspend", n = {"mw"}, s = {"L$0"})
/* compiled from: FileBrowserTvFragment.kt */
final class FileBrowserTvFragment$togglefavorite$1$1 extends SuspendLambda implements Function2<CoroutineScope, Continuation<? super Unit>, Object> {
    final /* synthetic */ MediaLibraryItem $item;
    Object L$0;
    int label;
    final /* synthetic */ FileBrowserTvFragment this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    FileBrowserTvFragment$togglefavorite$1$1(MediaLibraryItem mediaLibraryItem, FileBrowserTvFragment fileBrowserTvFragment, Continuation<? super FileBrowserTvFragment$togglefavorite$1$1> continuation) {
        super(2, continuation);
        this.$item = mediaLibraryItem;
        this.this$0 = fileBrowserTvFragment;
    }

    public final Continuation<Unit> create(Object obj, Continuation<?> continuation) {
        return new FileBrowserTvFragment$togglefavorite$1$1(this.$item, this.this$0, continuation);
    }

    public final Object invoke(CoroutineScope coroutineScope, Continuation<? super Unit> continuation) {
        return ((FileBrowserTvFragment$togglefavorite$1$1) create(coroutineScope, continuation)).invokeSuspend(Unit.INSTANCE);
    }

    /* JADX WARNING: type inference failed for: r8v19, types: [org.videolan.medialibrary.media.MediaLibraryItem, java.lang.Object] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0092  */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0095  */
    /* JADX WARNING: Removed duplicated region for block: B:25:0x00aa  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00ad  */
    /* JADX WARNING: Removed duplicated region for block: B:29:0x00c8  */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x00cb  */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00e3  */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.lang.Object invokeSuspend(java.lang.Object r8) {
        /*
            r7 = this;
            java.lang.Object r0 = kotlin.coroutines.intrinsics.IntrinsicsKt.getCOROUTINE_SUSPENDED()
            int r1 = r7.label
            r2 = 0
            r3 = 2
            r4 = 1
            if (r1 == 0) goto L_0x0027
            if (r1 == r4) goto L_0x001f
            if (r1 != r3) goto L_0x0017
            java.lang.Object r0 = r7.L$0
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = (org.videolan.television.ui.browser.FileBrowserTvFragment) r0
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0079
        L_0x0017:
            java.lang.IllegalStateException r8 = new java.lang.IllegalStateException
            java.lang.String r0 = "call to 'resume' before 'invoke' with coroutine"
            r8.<init>(r0)
            throw r8
        L_0x001f:
            java.lang.Object r1 = r7.L$0
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            kotlin.ResultKt.throwOnFailure(r8)
            goto L_0x0051
        L_0x0027:
            kotlin.ResultKt.throwOnFailure(r8)
            org.videolan.medialibrary.media.MediaLibraryItem r8 = r7.$item
            java.lang.String r1 = "null cannot be cast to non-null type org.videolan.medialibrary.interfaces.media.MediaWrapper"
            kotlin.jvm.internal.Intrinsics.checkNotNull(r8, r1)
            r1 = r8
            org.videolan.medialibrary.interfaces.media.MediaWrapper r1 = (org.videolan.medialibrary.interfaces.media.MediaWrapper) r1
            kotlinx.coroutines.CoroutineDispatcher r8 = kotlinx.coroutines.Dispatchers.getIO()
            kotlin.coroutines.CoroutineContext r8 = (kotlin.coroutines.CoroutineContext) r8
            org.videolan.television.ui.browser.FileBrowserTvFragment$togglefavorite$1$1$1 r5 = new org.videolan.television.ui.browser.FileBrowserTvFragment$togglefavorite$1$1$1
            org.videolan.television.ui.browser.FileBrowserTvFragment r6 = r7.this$0
            r5.<init>(r6, r1, r2)
            kotlin.jvm.functions.Function2 r5 = (kotlin.jvm.functions.Function2) r5
            r6 = r7
            kotlin.coroutines.Continuation r6 = (kotlin.coroutines.Continuation) r6
            r7.L$0 = r1
            r7.label = r4
            java.lang.Object r8 = kotlinx.coroutines.BuildersKt.withContext(r8, r5, r6)
            if (r8 != r0) goto L_0x0051
            return r0
        L_0x0051:
            org.videolan.television.ui.browser.FileBrowserTvFragment r8 = r7.this$0
            org.videolan.vlc.repository.BrowserFavRepository r4 = r8.browserFavRepository
            if (r4 != 0) goto L_0x005f
            java.lang.String r4 = "browserFavRepository"
            kotlin.jvm.internal.Intrinsics.throwUninitializedPropertyAccessException(r4)
            goto L_0x0060
        L_0x005f:
            r2 = r4
        L_0x0060:
            android.net.Uri r1 = r1.getUri()
            java.lang.String r4 = "getUri(...)"
            kotlin.jvm.internal.Intrinsics.checkNotNullExpressionValue(r1, r4)
            r4 = r7
            kotlin.coroutines.Continuation r4 = (kotlin.coroutines.Continuation) r4
            r7.L$0 = r8
            r7.label = r3
            java.lang.Object r1 = r2.browserFavExists(r1, r4)
            if (r1 != r0) goto L_0x0077
            return r0
        L_0x0077:
            r0 = r8
            r8 = r1
        L_0x0079:
            java.lang.Boolean r8 = (java.lang.Boolean) r8
            boolean r8 = r8.booleanValue()
            r0.favExists = r8
            org.videolan.television.ui.browser.FileBrowserTvFragment r8 = r7.this$0
            org.videolan.television.databinding.SongBrowserBinding r8 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r8 = r8.favoriteButton
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r7.this$0
            boolean r0 = r0.favExists
            if (r0 == 0) goto L_0x0095
            int r0 = org.videolan.television.R.drawable.ic_tv_browser_favorite
            goto L_0x0097
        L_0x0095:
            int r0 = org.videolan.television.R.drawable.ic_tv_browser_favorite_outline
        L_0x0097:
            r8.setImageResource(r0)
            org.videolan.television.ui.browser.FileBrowserTvFragment r8 = r7.this$0
            org.videolan.television.databinding.SongBrowserBinding r8 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r8 = r8.favoriteButton
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r7.this$0
            boolean r1 = r0.favExists
            if (r1 == 0) goto L_0x00ad
            int r1 = org.videolan.television.R.string.favorites_remove
            goto L_0x00af
        L_0x00ad:
            int r1 = org.videolan.television.R.string.favorites_add
        L_0x00af:
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r8.setContentDescription(r0)
            org.videolan.television.ui.browser.FileBrowserTvFragment r8 = r7.this$0
            org.videolan.television.databinding.SongBrowserBinding r8 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r8 = r8.imageButtonFavorite
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r7.this$0
            boolean r0 = r0.favExists
            if (r0 == 0) goto L_0x00cb
            int r0 = org.videolan.television.R.drawable.ic_fabtvmini_favorite
            goto L_0x00cd
        L_0x00cb:
            int r0 = org.videolan.television.R.drawable.ic_fabtvmini_favorite_outline
        L_0x00cd:
            r8.setImageResource(r0)
            org.videolan.television.ui.browser.FileBrowserTvFragment r8 = r7.this$0
            org.videolan.television.databinding.SongBrowserBinding r8 = r8.getBinding()
            androidx.appcompat.widget.AppCompatImageButton r8 = r8.imageButtonFavorite
            org.videolan.television.ui.browser.FileBrowserTvFragment r0 = r7.this$0
            boolean r1 = r0.favExists
            if (r1 == 0) goto L_0x00e3
            int r1 = org.videolan.television.R.string.favorites_remove
            goto L_0x00e5
        L_0x00e3:
            int r1 = org.videolan.television.R.string.favorites_add
        L_0x00e5:
            java.lang.String r0 = r0.getString(r1)
            java.lang.CharSequence r0 = (java.lang.CharSequence) r0
            r8.setContentDescription(r0)
            kotlin.Unit r8 = kotlin.Unit.INSTANCE
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: org.videolan.television.ui.browser.FileBrowserTvFragment$togglefavorite$1$1.invokeSuspend(java.lang.Object):java.lang.Object");
    }
}
